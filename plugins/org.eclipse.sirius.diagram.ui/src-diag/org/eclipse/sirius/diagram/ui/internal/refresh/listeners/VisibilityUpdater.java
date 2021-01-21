/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.listener.NotificationReceiver;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;

/**
 * A ResourceSet listener to refresh visibility when graphical filters are updated.
 * 
 * @author mporhel
 */
public class VisibilityUpdater extends ResourceSetListenerImpl {

    private boolean needsRefresh;

    private VisibilityNotificationReceiver notificationReceiver;

    private DDiagram dDiagram;

    /**
     * Default constructor.
     * 
     * @param domain
     *            {@link TransactionalEditingDomain}
     * @param dDiagram
     *            {@link DDiagram}.
     */
    public VisibilityUpdater(TransactionalEditingDomain domain, DDiagram dDiagram) {
        super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters())));
        this.dDiagram = dDiagram;
        this.notificationReceiver = new VisibilityNotificationReceiver();
        domain.addResourceSetListener(this);
        dDiagram.eAdapters().add(notificationReceiver);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrecommitOnly() {
        return true;
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
        Command cmd = null;
        final Collection<DDiagramElement> elementsToRefresh = extractElementToRefresh(event);
        if (!elementsToRefresh.isEmpty()) {
            cmd = new UpdateVisibilityCommand(getTarget(), dDiagram, elementsToRefresh);
        }
        return cmd;
    }

    private Collection<DDiagramElement> extractElementToRefresh(ResourceSetChangeEvent event) {
        boolean filtersRefresh = isAutoRefresh() || needsRefresh;

        // if needs needs refresh, we are doing it so, we deactivate the updater
        needsRefresh = false;

        Collection<DDiagramElement> elementsToRefresh = new HashSet<>();
        for (Notification notif : event.getNotifications()) {
            Object notifier = notif.getNotifier();
            if (notifier instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = (DDiagramElement) notifier;
                DDiagram parentDDiagram = dDiagramElement.getParentDiagram();
                if (parentDDiagram != null && parentDDiagram.equals(dDiagram) && checkValues(filtersRefresh, notif.getNewValue(), notif.getOldValue())) {
                    elementsToRefresh.add(dDiagramElement);
                }
            }
        }
        return elementsToRefresh;
    }

    private boolean checkValues(boolean filtersRefresh, Object newValue, Object oldValue) {
        boolean checked = false;
        if (!(newValue instanceof AbsoluteBoundsFilter || oldValue instanceof AbsoluteBoundsFilter)) {
            checked = checkValue(filtersRefresh, newValue) || checkValue(filtersRefresh, oldValue);
        }
        return checked;
    }

    private boolean checkValue(boolean filtersRefresh, Object value) {
        return filtersRefresh || value instanceof GraphicalFilter && !(value instanceof AppliedCompositeFilters || value instanceof CollapseFilter);
    }

    private boolean isAutoRefresh() {
        return new DRepresentationQuery(dDiagram).isAutoRefresh();
    }

    /**
     * 
     * @author mporhel
     * 
     */
    private static class UpdateVisibilityCommand extends RecordingCommand {

        private DDiagram diagram;

        private Collection<DDiagramElement> elementsToRefresh;

        /**
         * Default constructor.
         * 
         * @param domain
         *            domain.
         * @param diagram
         *            current diagram.
         * @param elementsToRefresh
         *            elements which visibility needs to be refreshed
         */
        public UpdateVisibilityCommand(TransactionalEditingDomain domain, DDiagram diagram, Collection<DDiagramElement> elementsToRefresh) {
            super(domain, Messages.UpdateVisibilityCommand_label);
            this.diagram = diagram;
            this.elementsToRefresh = elementsToRefresh;
        }

        @Override
        protected void doExecute() {
            NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY);
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_VISIBILITY_KEY);

            Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
            DisplayServiceManager.INSTANCE.getDisplayService().activateCache();
            DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);

            for (DDiagramElement element : elementsToRefresh) {
                element.setVisible(DisplayServiceManager.INSTANCE.getDisplayService().computeVisibility(mappingManager, diagram, element));
            }

            DisplayServiceManager.INSTANCE.getDisplayService().deactivateCache();
            NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.STOP, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_VISIBILITY_KEY);
        }
    }

    private class VisibilityNotificationReceiver implements NotificationReceiver, Adapter {

        private Notifier target;

        /**
         * {@inheritDoc}
         */
        @Override
        public void receive(int kind, int notification) {
            switch (notification) {
            case org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE:
                switch (kind) {

                case org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START:
                    needsRefresh = true;
                    break;

                case org.eclipse.sirius.common.tools.api.listener.Notification.Kind.STOP:
                    needsRefresh = false;
                    break;

                default:
                    break;
                }
                break;

            default:
                break;
            }
        }

        @Override
        public Notifier getTarget() {
            return target;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAdapterForType(Object type) {
            return type instanceof DDiagram;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChanged(Notification notification) {
            // do nothing

        }

        @Override
        public void setTarget(Notifier newTarget) {
            target = newTarget;
        }
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        dDiagram.eAdapters().remove(notificationReceiver);
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        dDiagram = null;
    }
}
