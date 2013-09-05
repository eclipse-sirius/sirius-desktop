/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
import java.util.LinkedHashSet;

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

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import org.eclipse.sirius.business.api.session.SessionListener;

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
        if (session != null) {
            // Detect controlled resources for future resource add.
            detectControlledResources();
            session.getTransactionalEditingDomain().addResourceSetListener(this);
        }
    }

    /**
     * Dispose the current listener and remove it from the transactional editing
     * domain.
     */
    public void dispose() {
        if (session != null) {
            session.getTransactionalEditingDomain().removeResourceSetListener(this);
            session = null;
        }
    }

    private void detectControlledResources() {
        final Collection<Resource> semantics = session.getSemanticResources();
        final Collection<Resource> resourcesToCheck = new ArrayList<Resource>(session.getTransactionalEditingDomain().getResourceSet().getResources());
        Collection<Resource> newControlledResources = new LinkedHashSet<Resource>();
        Collection<Resource> controlledResources = new ArrayList<Resource>(session.getControlledResources());
        for (final Resource resource : Iterables.filter(resourcesToCheck, Predicates.not(Predicates.in(controlledResources)))) {
            if (hasControlledRootInSemantics(resource, semantics) && !controlledResources.contains(resource)) {
                newControlledResources.add(resource);
            }
        }

        if (!newControlledResources.isEmpty()) {
            session.getControlledResources().addAll(newControlledResources);
            session.transfertNotification(SessionListener.SEMANTIC_CHANGE);
        }

    }

    private boolean hasControlledRootInSemantics(Resource resource, Collection<Resource> semantics) {
        Predicate<EObject> isControlled = new Predicate<EObject>() {
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
        Collection<Object> addedResources = Sets.newHashSet();
        Collection<Object> removedResources = Sets.newHashSet();

        // No check on notifer and feature : already done by filter.
        for (Notification notif : Iterables.filter(event.getNotifications(), Notification.class)) {
            categorizeNotification(notif, addedResources, removedResources);
        }

        if (session != null) {
            // See if this removal code should be called once we also remove sub-resources when a semantic resource is removed.
            // if (!removedResources.isEmpty()) {
            // //session.getControlledResources().removeAll(removedResources);
            // }

            if (!addedResources.isEmpty()) {
                return new ControlledResourcesDetectionCommand(session.getTransactionalEditingDomain());
            }
        }

        return null;
    }

    private void categorizeNotification(Notification notif, Collection<Object> addedResources, Collection<Object> removedResources) {
        switch (notif.getEventType()) {
        case Notification.ADD:
        case Notification.SET:
            addedResources.add(notif.getNewValue());
            break;
        case Notification.ADD_MANY:
            if (notif.getNewValue() instanceof Collection) {
                addedResources.addAll((Collection<?>) notif.getNewValue());
            } else {
                addedResources.add(notif.getNewValue());
            }
            break;
        case Notification.REMOVE:
        case Notification.UNSET:
            removedResources.add(notif.getOldValue());
            break;
        case Notification.REMOVE_MANY:
            if (notif.getOldValue() instanceof Collection) {
                removedResources.addAll((Collection<?>) notif.getOldValue());
            } else {
                removedResources.add(notif.getOldValue());
            }
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationFilter getFilter() {
        return NotificationFilter.createFeatureFilter(ResourceSet.class, ResourceSet.RESOURCE_SET__RESOURCES).and(NotificationFilter.NOT_TOUCH);
    }

    private class ControlledResourcesDetectionCommand extends RecordingCommand {

        /**
         * Constructor.
         * 
         * @param domain
         *            the editing domain.
         */
        public ControlledResourcesDetectionCommand(TransactionalEditingDomain domain) {
            super(domain, "Controleld resource detection");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void doExecute() {
            detectControlledResources();
        }
    }
}
