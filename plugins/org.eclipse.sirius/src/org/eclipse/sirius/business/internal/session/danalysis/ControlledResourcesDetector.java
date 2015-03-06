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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SessionListener;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Pre-commit listener to handle controlled resources.
 * 
 * @author mporhel
 */
public class ControlledResourcesDetector extends ResourceSetListenerImpl {

    private DAnalysisSessionImpl session;

    /**
     * Construcotr.
     * 
     * @param session
     *            the session to populate.
     */
    public ControlledResourcesDetector(DAnalysisSessionImpl session) {
        this.session = Preconditions.checkNotNull(session);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    /**
     * Looks for already loaded resources and then add this listener to the
     * session's transactional editing domain.
     */
    public void init() {
        // Detect controlled resources for future resource add.
        detectControlledResources();
        session.getTransactionalEditingDomain().addResourceSetListener(this);
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
    public void detectControlledResources() {
        final Collection<Resource> semantics = session.getSemanticResources();
        final Collection<Resource> resourcesToCheck = new ArrayList<Resource>(session.getTransactionalEditingDomain().getResourceSet().getResources());
        List<Resource> controlledResources = session.getControlledResources();
        List<Resource> controlledResourcesBefore = new ArrayList<Resource>(session.getControlledResources());

        // add controlled resource
        for (final Resource resource : Iterables.filter(resourcesToCheck, Predicates.not(Predicates.in(controlledResources)))) {
            if (hasControlledRootInSemantics(resource, semantics)) {
                controlledResources.add(resource);
            }
        }

        // remove controlled resource if it is not in the resourceSet anymore
        ListIterator<Resource> listIterator = controlledResources.listIterator();
        while (listIterator.hasNext()) {
            Resource resource = listIterator.next();
            if (!resourcesToCheck.contains(resource))
                listIterator.remove();
        }

        if (!controlledResourcesBefore.equals(controlledResources)) {
            session.transfertNotification(SessionListener.SEMANTIC_CHANGE);
            session.setSemanticResourcesNotUptodate();
        }

    }

    private boolean hasControlledRootInSemantics(Resource resource, Collection<Resource> semantics) {
        Predicate<EObject> isControlled = new Predicate<EObject>() {
            @Override
            public boolean apply(EObject input) {
                return AdapterFactoryEditingDomain.isControlled(input);
            }
        };

        for (EObject root : Iterables.filter(resource.getContents(), isControlled)) {
            EObject rootContainer = EcoreUtil.getRootContainer(root);
            if (rootContainer != null && rootContainer != root) {
                Resource rootResource = rootContainer.eResource();
                if (semantics.contains(rootResource)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
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
     * {@inheritDoc}
     */
    @Override
    public NotificationFilter getFilter() {
        return NotificationFilter.createFeatureFilter(ResourceSet.class, ResourceSet.RESOURCE_SET__RESOURCES).and(NotificationFilter.NOT_TOUCH);
    }

    /**
     * Simply wraps a call to {@link #detectControlledResources()} into a
     * {@link RecordingCommand}.
     */
    private class ControlledResourcesDetectionCommand extends RecordingCommand {
        public ControlledResourcesDetectionCommand(TransactionalEditingDomain domain) {
            super(domain, "Controled resource detection");
        }

        @Override
        protected void doExecute() {
            detectControlledResources();
        }
    }
}
