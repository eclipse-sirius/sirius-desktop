/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SessionListener;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Pre-commit listener to handle controlled resources.
 * 
 * @author mporhel
 */
public class ControlledResourcesDetector extends ResourceSetListenerImpl {

    private DAnalysisSessionImpl session;

    /**
     * Constructor.
     * 
     * @param session
     *            the session to populate.
     */
    public ControlledResourcesDetector(DAnalysisSessionImpl session) {
        this.session = Preconditions.checkNotNull(session);
    }

    @Override
    public NotificationFilter getFilter() {
        return NotificationFilter.createFeatureFilter(ResourceSet.class, ResourceSet.RESOURCE_SET__RESOURCES).and(NotificationFilter.NOT_TOUCH);
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * Looks for already loaded resources and then add this listener to the
     * session's transactional editing domain.
     */
    public void initialize() {
        // Detect controlled resources for future resource add.
        detectControlledResources();
        session.getTransactionalEditingDomain().addResourceSetListener(this);
    }

    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        // No check on notifier and feature : already done by filter.
        for (Notification notif : Iterables.filter(event.getNotifications(), Notification.class)) {
            int change = notif.getEventType();
            boolean resourcesWereAdded = change == Notification.ADD || change == Notification.SET || change == Notification.ADD_MANY;
            if (resourcesWereAdded) {
                return new ControlledResourcesDetectionCommand(session.getTransactionalEditingDomain());
            }
        }
        return null;
    }

    /**
     * Dispose the current listener and remove it from the transactional editing
     * domain.
     */
    public void dispose() {
        if (session != null) {
            session.getTransactionalEditingDomain().removeResourceSetListener(this);
        }
        session = null;
    }

    /**
     * Detects controlled resources and update controlledResourcesList in
     * {@link DAnalysisSessionImpl}.
     */
    void detectControlledResources() {
        Collection<Resource> semantics = session.getSemanticResources();
        Collection<Resource> allResources = Lists.newArrayList(session.getTransactionalEditingDomain().getResourceSet().getResources());
        Collection<Resource> controlledResources = session.getControlledResources();

        // Add controlled resources which are in the ResourceSet but not yet known as such.
        boolean addedControlledResources = false;
        for (Resource resource : allResources) {
            // FIXME This does not consider resources which are in controlledResources but not actually controlled anymore.
            if (!controlledResources.contains(resource) && hasControlledRootIn(resource, semantics)) {
                controlledResources.add(resource);
                addedControlledResources = true;
                session.registerResourceInCrossReferencer(resource);
            }
        }

        // Remove controlled resources which are not in the ResourceSet anymore.
        boolean removedControlledResources = controlledResources.retainAll(allResources);

        if (addedControlledResources || removedControlledResources) {
            session.notifyListeners(SessionListener.SEMANTIC_CHANGE);
            session.setSemanticResourcesNotUptodate();
        }
    }

    private static boolean hasControlledRootIn(Resource resource, Collection<Resource> scope) {
        for (EObject root : resource.getContents()) {
            EObject container = root.eContainer();
            Resource containerResource = container != null ? container.eResource() : null;
            if (containerResource != resource && scope.contains(containerResource)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simply wraps a call to {@link #detectControlledResources()} into a
     * {@link RecordingCommand}.
     */
    private class ControlledResourcesDetectionCommand extends RecordingCommand {
        public ControlledResourcesDetectionCommand(TransactionalEditingDomain domain) {
            super(domain, "Controlled resource detection");
        }

        @Override
        protected void doExecute() {
            detectControlledResources();
        }
    }
}
