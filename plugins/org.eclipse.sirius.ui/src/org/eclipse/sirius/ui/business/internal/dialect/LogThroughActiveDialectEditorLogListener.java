/*******************************************************************************
 * Copyright (c) 2012, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.dialect;

import java.text.MessageFormat;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A {@link ILogListener} that reacts to specific exceptions by logging them
 * through the active {@link DialectEditor} (if any).
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 */
public final class LogThroughActiveDialectEditorLogListener implements ILogListener {
    /**
     * This log listener instance.
     */
    public static final LogThroughActiveDialectEditorLogListener INSTANCE = new LogThroughActiveDialectEditorLogListener();

    /**
     * The label provider to use for displaying elements name.
     */
    private ICommonLabelProvider labelProvider;

    private LogThroughActiveDialectEditorLogListener() {
    }

    @Override
    public void logging(IStatus status, String plugin) {
        boolean hasBeenLoggedThroughDialect = false;
        // Always consider final cause of exception
        final Throwable exception = getFinalCause(status);
        // Step 1: check preferences (should indicate that errors should be
        // logged through a pop-up)
        if (SiriusEditPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusUIPreferencesKeys.PREF_REACT_TO_PERMISSION_ISSUES_BY_GRAPHICAL_DISPLAY.name())) {
            // Step 2: logging this error using a through the dialect if
            // possible and required
            if (SiriusEditPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusUIPreferencesKeys.PREF_DISPLAY_PERMISSION_ISSUES_THROUGH_DIALOG.name())) {
                IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
                if (activeEditor instanceof DialectEditor) {
                    if (shouldBeLoggedThroughDialect((DialectEditor) activeEditor, exception)) {
                        ((DialectEditor) activeEditor).getDialogFactory().informUserOfEvent(IStatus.ERROR, getErrorMessage(exception));
                        hasBeenLoggedThroughDialect = true;
                    }
                }
            }
            // Step 3: if could not log through active dialect, and if exception
            // requires logging, opening a pop-up
            // Notice that we do not display such pop-ups while eclipse is
            // starting (can be confusing for end-user)
            if (!hasBeenLoggedThroughDialect && shouldBeLoggedThroughPopup(exception) && PlatformUI.isWorkbenchRunning() && !PlatformUI.getWorkbench().isStarting()) {
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.LogThroughActiveDialectEditorLogListener_permissionError,
                                getErrorMessage(exception));
                    }
                });
            }
        }
    }

    private Throwable getFinalCause(IStatus status) {
        Throwable exception = status.getException();
        while (exception != null && exception.getCause() != null) {
            exception = exception.getCause();
        }
        return exception;
    }

    /**
     * Indicates whether the given exception should be logged through the active
     * dialect or not.
     *
     * @param exception
     *            the exception that is being logged in the error log
     * @return true if the given exception should be logged through the active
     *         dialect, false otherwise
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
     * @param exception
     *            the exception that is being logged in the error log
     * @return true if the given exception should be logged through a pop-up,
     *         false otherwise
     */
    private boolean shouldBeLoggedThroughPopup(Throwable exception) {
        boolean shouldBeLogged = false;
        // We only consider LockedInstanceException and SecurityException with
        // message
        shouldBeLogged = exception instanceof LockedInstanceException || exception instanceof SecurityException;
        if (shouldBeLogged) {
            String message = getErrorMessage(exception);
            shouldBeLogged = message != null && !message.isEmpty();
        }
        return shouldBeLogged;
    }

    /**
     * Returns the error message corresponding to the given exception.
     *
     * @param exception
     *            the exception to get the error message from
     * @return the error message corresponding to the given exception
     */
    private String getErrorMessage(Throwable exception) {
        String errorMessage = exception.getMessage();
        if (exception instanceof LockedInstanceException) {
            EObject lockedElement = ((LockedInstanceException) exception).getLockedElement();
            if (lockedElement != null) {
                errorMessage = MessageFormat.format(LockedInstanceException.PERMISSION_ISSUE_MESSAGE, getLabelProvider().getText(lockedElement));
            }
        }
        return errorMessage;
    }

    /**
     * Returns the label provider to use for displaying locked elements.
     *
     * @return the label provider to use for displaying locked elements.
     */
    private ICommonLabelProvider getLabelProvider() {
        if (labelProvider == null) {
            labelProvider = new SiriusCommonLabelProvider();
        }
        return labelProvider;
    }
}
