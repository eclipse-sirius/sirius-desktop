/*******************************************************************************
 * Copyright (c) 2016, 2021 Obeo.
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
package org.eclipse.sirius.ui.properties.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.LockStatusChangeEvent;
import org.eclipse.eef.core.api.LockStatusChangeEvent.LockStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.logger.RuntimeLogger;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.internal.logger.RuntimeLoggerManagerImpl;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.ui.RefreshHelper;

/**
 * Integrates EEF properties views with a specific {@link TransactionalEditingDomain} (for example the associated with a
 * Sirius session).
 * 
 * @author pcdavid
 *
 */
public class TransactionalEditingDomainContextAdapter implements EditingContextAdapter {
    private static final String FORCE_REFRESH_SYSTEM_FLAG = "org.eclipse.sirius.properties.forceRefreshOnRepresentationsChange"; //$NON-NLS-1$

    /**
     * Describes the model changes we want to react to.
     */
    private static final NotificationFilter FILTER = NotificationFilter.NOT_TOUCH.and(NotificationFilter.createNotifierTypeFilter(EObject.class));

    /**
     * The callback to invoke to notify the EEF side when the model has changed.
     */
    protected Consumer<List<Notification>> onModelChanged;

    /**
     * The editing domain to integrate with.
     */
    private final TransactionalEditingDomain ted;

    /**
     * The post-commit listener used to detect model changes and call back EEF.
     */
    private final ResourceSetListener postCommitListener = new PostCommitListener();

    /**
     * A spying logger used to detect errors occuring during command execution.
     */
    private RuntimeLoggerSpy spy = new RuntimeLoggerSpy(RuntimeLoggerManager.INSTANCE);

    /**
     * The {@link IPermissionAuthority} to use to obtain lock status information.
     */
    private IPermissionAuthority auth;

    /**
     * The listener that will be notified when the lock status of an element changes in Sirius.
     */
    private IAuthorityListener authListener;

    /**
     * The EEF-side listeners to notify when lock status change.
     */
    private List<Consumer<Collection<LockStatusChangeEvent>>> lockStatusListeners = new CopyOnWriteArrayList<>();

    /**
     * Create a new detector.
     * 
     * @param ted
     *            the editing domain to integrate with.
     */
    public TransactionalEditingDomainContextAdapter(TransactionalEditingDomain ted) {
        this.ted = Objects.requireNonNull(ted);
        ModelAccessor ma = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(ted.getResourceSet());
        this.auth = ma.getPermissionAuthority();
        this.authListener = new PermissionsListener();
    }

    @Override
    public IStatus performModelChange(final Runnable effect) {
        RecordingCommand cmd = new RecordingCommand(ted) {
            @Override
            protected void doExecute() {
                effect.run();
            };
        };
        IStatus result = Status.OK_STATUS;
        spy.enable();
        try {
            ted.getCommandStack().execute(cmd);
            if (spy.hasSeenErrors()) {
                IStatus[] errors = spy.getErrors();
                if (errors.length > 1) {
                    result = new MultiStatus(SiriusUIPropertiesPlugin.PLUGIN_ID, 0, errors, Messages.TransactionalEditingDomainContextAdapter_errorDuringCommand, null);
                } else {
                    result = errors[0];
                }
            }
            // CHECKSTYLE:OFF
        } catch (Throwable th) {
            // CHECKSTYLE:ON
            // Unexpected error, not detected and logged by the Sirius runtime.
            result = new Status(IStatus.ERROR, SiriusUIPropertiesPlugin.PLUGIN_ID, Messages.TransactionalEditingDomainContextAdapter_errorDuringCommand, th);
        } finally {
            spy.disable();
        }
        return result;
    }

    @Override
    public void registerModelChangeListener(Consumer<List<Notification>> listener) {
        if (this.onModelChanged == null) {
            ted.addResourceSetListener(postCommitListener);
        }
        this.onModelChanged = listener;
    }

    @Override
    public void unregisterModelChangeListener() {
        this.onModelChanged = null;
        ted.removeResourceSetListener(postCommitListener);
    }

    @Override
    public EditingDomain getEditingDomain() {
        return this.ted;
    }

    @Override
    public void addLockStatusChangedListener(Consumer<Collection<LockStatusChangeEvent>> listener) {
        if (this.lockStatusListeners.isEmpty()) {
            auth.addAuthorityListener(authListener);
        }
        this.lockStatusListeners.add(listener);
    }

    @Override
    public void removeLockStatusChangedListener(Consumer<Collection<LockStatusChangeEvent>> listener) {
        this.lockStatusListeners.remove(listener);
        if (this.lockStatusListeners.isEmpty()) {
            auth.removeAuthorityListener(authListener);
        }
    }

    @Override
    public LockStatus getLockStatus(EObject obj) {
        LockStatus result = convertLockStatus(this.auth.getLockStatus(obj));
        if (result == LockStatus.UNLOCKED && !this.auth.canEditInstance(obj)) {
            result = LockStatus.LOCKED_PERMISSION;
        }
        return result;
    }

    /**
     * Tests the FORCE_REFRESH_SYSTEM_FLAG.
     * 
     * @return <code>true</code> if the FORCE_REFRESH_SYSTEM_FLAG was set.
     */
    protected static boolean forceRefreshOnRepresentationChanges() {
        return Boolean.TRUE.toString().equals(System.getProperty(FORCE_REFRESH_SYSTEM_FLAG, Boolean.FALSE.toString()));
    }

    private LockStatus convertLockStatus(org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus siriusStatus) {
        LockStatus result = null;
        switch (siriusStatus) {
        case LOCKED_BY_ME:
            result = LockStatus.LOCKED_BY_ME;
            break;
        case LOCKED_BY_OTHER:
            result = LockStatus.LOCKED_BY_OTHER;
            break;
        case NOT_LOCKED:
            result = LockStatus.UNLOCKED;
            break;
        default:
            throw new IllegalArgumentException();
        }
        return result;
    }

    /**
     * The post-commit listener which triggers a refresh of the EEF page when possibly impacting changes occur in the
     * Sirius session models.
     * 
     * @author pcdavid
     */
    private class PostCommitListener extends ResourceSetListenerImpl {
        PostCommitListener() {
            super(FILTER);
        }

        @Override
        public boolean isPostcommitOnly() {
            return true;
        }

        @Override
        public void resourceSetChanged(final ResourceSetChangeEvent event) {
            if (onModelChanged != null && (forceRefreshOnRepresentationChanges() || RefreshHelper.isImpactingNotification(event.getNotifications()))) {
                onModelChanged.accept(new ArrayList<>(event.getNotifications()));
            }
        }
    }

    /**
     * Forwards permission changes notifications received from Sirius (via IAuthorityListener) to all currently
     * registered listeners on the EEF side.
     * 
     * @author pcdavid
     */
    private final class PermissionsListener implements IAuthorityListener {
        @Override
        public void notifyIsReleased(Collection<EObject> instances) {
            Collection<LockStatusChangeEvent> events = new ArrayList<>();
            for (EObject o : instances) {
                events.add(new LockStatusChangeEvent(o, LockStatus.UNLOCKED));
            }
            notifyListeners(events);
        }

        @Override
        public void notifyIsReleased(EObject instance) {
            notifyIsReleased(Collections.singleton(instance));
        }

        @Override
        public void notifyIsLocked(Collection<EObject> instances) {
            Collection<LockStatusChangeEvent> events = new ArrayList<>();
            for (EObject o : instances) {
                boolean lockedByMe = TransactionalEditingDomainContextAdapter.this.isLockedByMe(o);
                events.add(new LockStatusChangeEvent(o, lockedByMe ? LockStatus.LOCKED_BY_ME : LockStatus.LOCKED_BY_OTHER));
            }
            notifyListeners(events);
        }

        @Override
        public void notifyIsLocked(EObject instance) {
            notifyIsLocked(Collections.singleton(instance));
        }

        private void notifyListeners(Collection<LockStatusChangeEvent> events) {
            for (Consumer<Collection<LockStatusChangeEvent>> l : lockStatusListeners) {
                l.accept(events);
            }
        }

    }

    /**
     * Tests if an element we already know to be locked is locked by me (and not by other).
     * 
     * @param lockedElement
     *            an element we know is locked.
     * @return <code>true</code> if the element is locked by me, <code>false</code> otherwise.
     */
    protected boolean isLockedByMe(EObject lockedElement) {
        LockStatus status = getLockStatus(lockedElement);
        return status == LockStatus.LOCKED_BY_ME;
    }

    /**
     * A logger which only remembers the errors it was notified of. Listens to both a Sirius RuntimeLoggerManager, which
     * receives errors in expressions evaluations, and to the plaform's ILogListener where LockedInstanceException and
     * SecurityException are sent.
     * 
     * @author pcdavid
     */
    private static final class RuntimeLoggerSpy implements RuntimeLogger {
        private final List<IStatus> errors = new ArrayList<>();

        private RuntimeLoggerManager manager;

        private ILogListener platformSpy = new ILogListener() {
            @Override
            public void logging(IStatus status, String plugin) {
                Throwable cause = Optional.ofNullable(status).map(IStatus::getException).map(this::getRootCause).orElse(null);
                if (cause instanceof LockedInstanceException || cause instanceof SecurityException) {
                    if (status.getSeverity() < IStatus.ERROR) {
                        /*
                         * LockedInstanceException may be reported as plain warnings here, but we want them to be
                         * considered as errors for the purpose of performModelChange, so that UIs can be correctly
                         * rolled back to a consistent state when they occur.
                         */
                        errors.add(new Status(IStatus.ERROR, status.getPlugin(), status.getCode(), status.getMessage(), status.getException()));
                    } else {
                        errors.add(status);
                    }
                }
            }

            private Throwable getRootCause(Throwable throwable) {
                Throwable rootCause = throwable;
                while (rootCause.getCause() != null) {
                    rootCause = rootCause.getCause();
                }
                return rootCause;
            }
        };

        RuntimeLoggerSpy(RuntimeLoggerManager instance) {
            this.manager = instance;
        }

        public void enable() {
            if (manager instanceof RuntimeLoggerManagerImpl) {
                ((RuntimeLoggerManagerImpl) manager).add(this);
            }
            Platform.addLogListener(platformSpy);
        }

        public void disable() {
            Platform.removeLogListener(platformSpy);
            if (manager instanceof RuntimeLoggerManagerImpl) {
                ((RuntimeLoggerManagerImpl) manager).remove(this);
            }
            errors.clear();
        }

        public boolean hasSeenErrors() {
            return !errors.isEmpty();
        }

        public IStatus[] getErrors() {
            return errors.toArray(new IStatus[errors.size()]);
        }

        @Override
        public void info(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
        }

        @Override
        public void info(EObject odesignObject, EStructuralFeature feature, String message) {
        }

        @Override
        public void warning(EObject odesignObject, EStructuralFeature feature, String message) {
        }

        @Override
        public void warning(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
        }

        @Override
        public void error(EObject odesignObject, EStructuralFeature feature, Throwable exception) {
            errors.add(new Status(IStatus.ERROR, SiriusUIPropertiesPlugin.PLUGIN_ID, exception.getMessage(), exception));
        }

        @Override
        public void error(EObject odesignObject, EStructuralFeature feature, String message) {
            errors.add(new Status(IStatus.ERROR, SiriusUIPropertiesPlugin.PLUGIN_ID, message));
        }

        @Override
        public void clearAll() {
        }

        @Override
        public void clear(EObject eObject) {
        }
    }
}
