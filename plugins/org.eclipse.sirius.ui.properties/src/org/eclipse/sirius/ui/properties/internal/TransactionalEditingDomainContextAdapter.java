/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.IConsumer;
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Integrates EEF properties views with a specific
 * {@link TransactionalEditingDomain} (for example the associated with a Sirius
 * session).
 * 
 * @author pcdavid
 *
 */
public class TransactionalEditingDomainContextAdapter implements EditingContextAdapter {
    /**
     * Describes the model changes we want to react to.
     */
    private static final NotificationFilter FILTER = NotificationFilter.NOT_TOUCH.and(NotificationFilter.createNotifierTypeFilter(EObject.class));

    /**
     * The editing domain to integrate with.
     */
    private final TransactionalEditingDomain ted;

    /**
     * The pre-commit listener used to detect model changes and call back EEF.
     */
    private final ResourceSetListener preCommitListener = new Listener();

    /**
     * The callback to invoke to notify the EEF side when the model has changed.
     */
    private IConsumer<List<Notification>> callback;

    /**
     * A spying logger used to detect errors occuring during command execution.
     */
    private RuntimeLoggerSpy spy = new RuntimeLoggerSpy(RuntimeLoggerManager.INSTANCE);

    /**
     * Create a new detector.
     * 
     * @param ted
     *            the editing domain to integrate with.
     */
    public TransactionalEditingDomainContextAdapter(TransactionalEditingDomain ted) {
        this.ted = Preconditions.checkNotNull(ted);
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
    public synchronized void onModelChange(IConsumer<List<Notification>> trigger) {
        if (this.callback == null) {
            ted.addResourceSetListener(preCommitListener);
        }
        this.callback = trigger;
    }

    @Override
    public void removeModelChangeConsumer() {
        this.callback = null;
        ted.removeResourceSetListener(preCommitListener);
    }

    @Override
    public EditingDomain getEditingDomain() {
        return this.ted;
    }

    /**
     * A logger which only remembers the errors it was notified of.
     * 
     * @author pcdavid
     */
    private static final class RuntimeLoggerSpy implements RuntimeLogger {
        private final List<IStatus> errors = new ArrayList<>();

        private RuntimeLoggerManager manager;

        public RuntimeLoggerSpy(RuntimeLoggerManager instance) {
            this.manager = instance;
        }

        public void enable() {
            if (manager instanceof RuntimeLoggerManagerImpl) {
                ((RuntimeLoggerManagerImpl) manager).add(this);
            }
        }

        public void disable() {
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

    /**
     * The actual implementation of the pre-commit listener.
     * 
     * @author pcdavid
     *
     */
    private class Listener extends ResourceSetListenerImpl {
        public Listener() {
            super(FILTER);
        }

        @Override
        public boolean isPostcommitOnly() {
            return true;
        }

        @Override
        public void resourceSetChanged(final ResourceSetChangeEvent event) {
            IConsumer<List<Notification>> t = callback;
            if (t != null) {
                t.apply(Lists.newArrayList(event.getNotifications()));
            }
        }
    }
}
