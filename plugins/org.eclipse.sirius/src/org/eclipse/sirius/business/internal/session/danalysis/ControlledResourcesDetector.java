/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.internal.query.ResourceQueryInternal;
import org.eclipse.sirius.tools.api.Messages;

import com.google.common.base.Preconditions;

/**
 * Pre-commit listener to handle controlled resources.
 * 
 * @author mporhel
 */
public class ControlledResourcesDetector extends ResourceSetListenerImpl {
    /**
     * The events of interest for {@link ControlledResourcesDetector}.
     */
    private static final NotificationFilter CONTROLLED_RESOURCES_EVENTS = NotificationFilter.createFeatureFilter(ResourceSet.class, ResourceSet.RESOURCE_SET__RESOURCES)
            .and(NotificationFilter.NOT_TOUCH);

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

    /**
     * Add this listener on the editingDomain.
     */
    public void initialize() {
        session.getTransactionalEditingDomain().addResourceSetListener(this);
    }

    @Override
    public NotificationFilter getFilter() {
        return ControlledResourcesDetector.CONTROLLED_RESOURCES_EVENTS;
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        // No check on notifier and feature : already done by filter.
        for (Notification notif : event.getNotifications()) {
            int change = notif.getEventType();
            boolean resourcesWereAdded = change == Notification.ADD || change == Notification.SET || change == Notification.ADD_MANY;
            if (resourcesWereAdded) {
                return new RefreshControlledResourcesCommand(session);
            }
        }
        return null;
    }

    /**
     * Dispose the current listener and remove it from the transactional editing domain.
     */
    public void dispose() {
        if (session != null) {
            session.getTransactionalEditingDomain().removeResourceSetListener(this);
        }
        session = null;
    }

    /**
     * Refresh a session's {@link DAnalysisSession.getControlledResources()} list, adding newly controlled semantic
     * resources and removing obsolete ones which are not in the ResourceSet anymore.
     */
    static void refreshControlledResources(DAnalysisSessionImpl session) {
        Collection<Resource> semantics = session.getSemanticResources();
        Collection<Resource> allResources = new ArrayList<Resource>(session.getTransactionalEditingDomain().getResourceSet().getResources());
        Collection<Resource> controlledResources = session.getControlledResources();

        // Add controlled resources which are in the ResourceSet but not yet
        // known as such.
        boolean addedControlledResources = false;
        for (Resource resource : allResources) {
            // FIXME This does not consider resources which are in
            // controlledResources but not actually controlled anymore.
            if (!controlledResources.contains(resource) && new ResourceQueryInternal(resource).hasControlledRootIn(semantics)) {
                // Use addUnique if possible, we already checks for containment
                // just above.
                if (controlledResources instanceof InternalEList<?>) {
                    ((InternalEList<Resource>) controlledResources).addUnique(resource);
                } else {
                    controlledResources.add(resource);
                }
                addedControlledResources = true;
                session.registerResourceInCrossReferencer(resource);
            }
        }

        // Only keep controlled resources which are still in the ResourceSet.
        boolean removedControlledResources = controlledResources.retainAll(allResources);

        if (addedControlledResources || removedControlledResources) {
            session.notifyListeners(SessionListener.SEMANTIC_CHANGE);
            session.setSemanticResourcesNotUptodate();
        }
    }

    /**
     * Simply wraps a call to {@link #refreshControlledResources()} into a {@link RecordingCommand}.
     */
    private static class RefreshControlledResourcesCommand extends RecordingCommand {
        private final DAnalysisSessionImpl session;

        RefreshControlledResourcesCommand(DAnalysisSessionImpl session) {
            super(session.getTransactionalEditingDomain(), Messages.ControlledResourcesDetector_refreshCommandLabel);
            this.session = session;
        }

        @Override
        protected void doExecute() {
            refreshControlledResources(session);
        }
    }
}
