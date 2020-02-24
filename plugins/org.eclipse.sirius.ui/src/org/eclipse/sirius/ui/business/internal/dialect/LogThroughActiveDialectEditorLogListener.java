/*******************************************************************************
 * Copyright (c) 2012, 2020 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.dialect;

import java.lang.ref.SoftReference;
import java.util.function.Function;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchMessages;

import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;

/**
 * A {@link ILogListener} that reacts to specific exceptions by logging them through the active {@link DialectEditor}
 * (if any).
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 */
public final class LogThroughActiveDialectEditorLogListener implements ILogListener {
    /**
     * The message sent when a save all failed.
     */
    public static final String SAVE_ALL_FAILED_MESSAGE = NLS.bind(WorkbenchMessages.EditorManager_operationFailed, WorkbenchMessages.SaveAll_text);

    /**
     * The default error message provider used by this {@link ILogListener}.
     */
    public static final Function<Throwable, String> DEFAULT_ERROR_MESSAGE_PROVIDER = new ErrorMessageProvider();

    /**
     * This log listener instance.
     */
    public static final LogThroughActiveDialectEditorLogListener INSTANCE = new LogThroughActiveDialectEditorLogListener();

    private Function<Throwable, String> errorMessageProvider = DEFAULT_ERROR_MESSAGE_PROVIDER;

    /**
     * Remembers the last error logged to avoid repeated notifications. Use a soft reference to avoid leaks as the log
     * listener is a global singleton, and some exceptions may have references to large application-level objects.
     */
    private SoftReference<Throwable> previousError;

    private LogThroughActiveDialectEditorLogListener() {
    }

    @Override
    public void logging(IStatus status, String plugin) {
        // Always consider final cause of exception
        final Throwable exception = (status != null && status.getException() != null) ? Throwables.getRootCause(status.getException()) : null;
        synchronized (this) {
            if (previousError != null && previousError.get() == exception) {
                // Ignore direct repetitions of the exact same exception, which can
                // happen when multiple code paths log the same cause.
                return;
            }
            previousError = new SoftReference<>(exception);
        }
        boolean hasBeenLoggedThroughDialect = false;
        // Step 1: check preferences (should indicate that errors should be
        // logged through a pop-up)
        if (SiriusEditPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusUIPreferencesKeys.PREF_REACT_TO_PERMISSION_ISSUES_BY_GRAPHICAL_DISPLAY.name())) {
            // Step 2: logging this error using a through the dialect if
            // possible and required
            if (SiriusEditPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusUIPreferencesKeys.PREF_DISPLAY_PERMISSION_ISSUES_THROUGH_DIALOG.name())) {
                IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
                if (activeEditor instanceof DialectEditor) {
                    if (shouldBeLoggedThroughDialect((DialectEditor) activeEditor, exception)) {
                        ((DialectEditor) activeEditor).getDialogFactory().informUserOfEvent(IStatus.ERROR, errorMessageProvider.apply(exception));
                        hasBeenLoggedThroughDialect = true;
                    }
                }
            }
            // Step 3: if could not log through active dialect, and if exception
            // requires logging, opening a pop-up
            // Notice that we do not display such pop-ups while eclipse is
            // starting (can be confusing for end-user)
            final String statusMessage = status != null ? status.getMessage() : ""; //$NON-NLS-1$
            if (!hasBeenLoggedThroughDialect && shouldBeLoggedThroughPopup(statusMessage, exception) && PlatformUI.isWorkbenchRunning() && !PlatformUI.getWorkbench().isStarting()) {
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.LogThroughActiveDialectEditorLogListener_permissionError,
                                errorMessageProvider.apply(exception));
                    }
                });
            }
        }
    }

    /**
     * Indicates whether the given exception should be logged through the active dialect or not.
     *
     * @param exception
     *            the exception that is being logged in the error log
     * @return true if the given exception should be logged through the active dialect, false otherwise
     */
    private boolean shouldBeLoggedThroughDialect(DialectEditor editor, Throwable exception) {
        // We only log LockedInstance exceptions
        if (exception instanceof LockedInstanceException) {
            final EObject lockedElement = ((LockedInstanceException) exception).getLockedElement();
            final DRepresentation activeRepresentation = editor.getRepresentation();
            // we only log the exception in the current dialect editor if the
            // locked element is referenced by this representation
            boolean isConcerningObjectsOfCurrentEditor = false;
            if (lockedElement != null && activeRepresentation != null) {
                Session session = null;
                if (lockedElement instanceof DSemanticDecorator) {
                    session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) lockedElement).getTarget());
                } else {
                    session = SessionManager.INSTANCE.getSession(lockedElement);
                }
                Iterable<Setting> representationsElementsReferencingLockedElement = Iterables.filter(session.getSemanticCrossReferencer().getInverseReferences(lockedElement),
                        new Predicate<Setting>() {
                            @Override
                            public boolean apply(Setting input) {
                                if (input.getEObject() instanceof DSemanticDecorator) {
                                    DRepresentation concernedRepresentation = null;
                                    if (input.getEObject() instanceof DRepresentation) {
                                        concernedRepresentation = (DRepresentation) input.getEObject();
                                    } else {
                                        if (input.getEObject() instanceof DRepresentationElement) {
                                            concernedRepresentation = new DRepresentationElementQuery((DRepresentationElement) input.getEObject()).getParentRepresentation();
                                        }
                                    }
                                    return concernedRepresentation == activeRepresentation;
                                }
                                return false;
                            }
                        });
                isConcerningObjectsOfCurrentEditor = representationsElementsReferencingLockedElement.iterator().hasNext();
            }
            return isConcerningObjectsOfCurrentEditor;
        }
        return false;
    }

    /**
     * Indicates whether the given exception should be logged through a pop-up.
     *
     * @param statusMessage
     *            The initial message of the status containing exception
     * @param exception
     *            the exception that is being logged in the error log
     * @return true if the given exception should be logged through a pop-up, false otherwise
     */
    protected boolean shouldBeLoggedThroughPopup(String statusMessage, Throwable exception) {
        boolean shouldBeLogged = false;
        // We only consider SecurityException with message and
        // LockedInstanceException with message having a status without message
        // "Save All Failed" (as it corresponds to a save on Properties view
        // already handled by another save)
        shouldBeLogged = (exception instanceof LockedInstanceException && !SAVE_ALL_FAILED_MESSAGE.equals(statusMessage)) || exception instanceof SecurityException;
        if (shouldBeLogged) {
            String message = errorMessageProvider.apply(exception);
            shouldBeLogged = !StringUtil.isEmpty(message);
        }
        return shouldBeLogged;
    }

    /**
     * Change the error message provider of this listener to display a different message.
     * 
     * @param errorMessageProvider
     *            The new error message provider to set.
     */
    public void setErrorMessageProvider(Function<Throwable, String> errorMessageProvider) {
        if (errorMessageProvider == null) {
            throw new IllegalArgumentException(Messages.LogThroughActiveDialectEditorLogListener_wrongErrorMessageProvider);
        } else {
            this.errorMessageProvider = errorMessageProvider;
        }
    }

    /**
     * Set the default error message provider of this listener.
     */
    public void resetErrorMessageProvider() {
        this.errorMessageProvider = DEFAULT_ERROR_MESSAGE_PROVIDER;
    }
}
