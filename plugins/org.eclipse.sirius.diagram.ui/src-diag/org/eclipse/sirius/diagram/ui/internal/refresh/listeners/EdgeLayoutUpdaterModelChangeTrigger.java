/*******************************************************************************
 * Copyright (c) 2014, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A Model Change Trigger that execute the
 * {@link CenterEdgeEndModelChangeOperation} when features defined in
 * {@link RefreshEdgeLayoutScopePredicate} are updated.
 * 
 * @author Florian Barbin
 */
public class EdgeLayoutUpdaterModelChangeTrigger implements ModelChangeTrigger {

    public static final int PRIORITY = FilterListener.COMPOSITE_FILTER_REFRESH_PRIORITY + 1;

    /**
     * List of features for which we consider that the edge layout must be
     * recompute.
     */
    private static final Set<EStructuralFeature> REFRESH_FEATURES = new HashSet<EStructuralFeature>();

    /**
     * Sublist of <code>REFRESH_FEATURES</code> that also have other features as
     * consequence.
     */
    private static final Set<EStructuralFeature> REFRESH_FEATURES_WITH_CONSEQUENCE = new HashSet<EStructuralFeature>();

    /**
     * List of features that are standard consequences of
     * <code>REFRESH_FEATURES_WITH_CONSEQUENCE</code>.
     */
    private static final Set<EStructuralFeature> CONSEQUENCE_FEATURES = new HashSet<EStructuralFeature>();

    static {
        REFRESH_FEATURES_WITH_CONSEQUENCE.add(DiagramPackage.Literals.EDGE_STYLE__CENTERED);
        REFRESH_FEATURES_WITH_CONSEQUENCE.add(NotationPackage.Literals.ROUTING_STYLE__ROUTING);

        REFRESH_FEATURES.addAll(REFRESH_FEATURES_WITH_CONSEQUENCE);
        REFRESH_FEATURES.add(DiagramPackage.Literals.DEDGE__OWNED_STYLE);
        REFRESH_FEATURES.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES);

        CONSEQUENCE_FEATURES.add(ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES);
        CONSEQUENCE_FEATURES.add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE);
    }

    private TransactionalEditingDomain domain;

    private SessionEventBroker eventBroker;

    private RefreshEdgeLayoutNotificationFilter refreshEdgeLayoutNotificationFilter;

    /**
     * Constructor. Add this EdgeLayoutUpdaterModelChangeTrigger to the session
     * event broker of the given session.
     * 
     * @param domain
     *            the editing domain.
     * @param session
     *            the session.
     * @param dDiagram
     *            the ddiagram.
     */
    public EdgeLayoutUpdaterModelChangeTrigger(Session session, DDiagram dDiagram) {
        this.domain = session.getTransactionalEditingDomain();
        eventBroker = session.getEventBroker();
        refreshEdgeLayoutNotificationFilter = new RefreshEdgeLayoutNotificationFilter(dDiagram);
        eventBroker.addLocalTrigger(refreshEdgeLayoutNotificationFilter, this);
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        Command command = null;

        // this collection contains gmf edges for which we already created a
        // CenterEdgeEndModelChangeOperation. This list aims to avoid creating
        // multi operation for a same gmfEdge in the case we are several
        // notification for it.
        Collection<Edge> edgesWithCreatedCommand = new HashSet<Edge>();
        Collection<AbstractModelChangeOperation<Void>> operations = new ArrayList<AbstractModelChangeOperation<Void>>();
        for (Notification notification : notifications) {
            // Only consider notification of
            // RefreshEdgeLayoutNotificationFilter.REFRESH_FEATURES list
            if (isRefreshEdgeLayoutNeededForNotification(notification)) {
                Option<Edge> optionalGmfEdge = getCorrespondingEdge(notification);
                if (optionalGmfEdge.some() && edgesWithCreatedCommand.add(optionalGmfEdge.get())) {
                    // if there are several notifications, we do not try to
                    // retrieve draw2D informations since they could be out of
                    // date.
                    boolean useFigure = otherNotificationsAreConsequences(notification, optionalGmfEdge.get(), notifications);
                    AbstractModelChangeOperation<Void> operation = new CenterEdgeEndModelChangeOperation(optionalGmfEdge.get(), useFigure);
                    operations.add(operation);
                }
            }
        }
        if (!operations.isEmpty()) {
            command = new EdgeLayoutUpdaterCommand(domain, operations);
        }

        return Options.newSome(command);
    }

    private static final class EdgeLayoutUpdaterCommand extends RecordingCommand {

        private Collection<AbstractModelChangeOperation<Void>> operations;

        public EdgeLayoutUpdaterCommand(TransactionalEditingDomain domain, Collection<AbstractModelChangeOperation<Void>> operations) {
            super(domain);
            this.operations = operations;
        }

        @Override
        protected void doExecute() {
            for (AbstractModelChangeOperation<Void> operation : operations) {
                operation.execute();
            }
        }

    }

    @Override
    public int priority() {
        return PRIORITY;
    }

    /**
     * Dispose this trigger. The trigger is removed from the session event
     * broker.
     */
    public void dispose() {
        refreshEdgeLayoutNotificationFilter = null;
        eventBroker.removeLocalTrigger(this);
        eventBroker = null;
        domain = null;

    }

    /**
     * Test whether the other notifications are consequences of the given one.
     * For instance, in case of a manual modification of the Sirius routing
     * style (from Style tab of Properties view), we also update the GMF style
     * and we add the routing style within the custom features. This method aims
     * to check whether we are in the case of an individual modification or a
     * global one.
     * 
     * @param notification
     *            the notification for which we are notified.
     * @param gmfEdge
     *            the GMF edge associated to the <code>notification</code>
     * @param notifications
     *            the whole notification list.
     * @return true if the notifications list contains only notifications
     *         induced by the first one.
     */
    public boolean otherNotificationsAreConsequences(final Notification notification, final Edge gmfEdge, Collection<Notification> notifications) {
        boolean otherNotificationsAreIndirectlyConcerned = false;
        if (notifications.size() == 1 && REFRESH_FEATURES.contains(notifications.iterator().next().getFeature())) {
            otherNotificationsAreIndirectlyConcerned = true;
        } else if (REFRESH_FEATURES_WITH_CONSEQUENCE.contains(notification.getFeature())) {
            otherNotificationsAreIndirectlyConcerned = Iterables.all(notifications, new Predicate<Notification>() {
                @Override
                public boolean apply(Notification currentNotification) {
                    boolean considerAsConsequence = false;
                    if (currentNotification == notification) {
                        considerAsConsequence = true;
                    } else {
                        Option<Edge> optionalEdge = getCorrespondingEdge(currentNotification);
                        if (optionalEdge.some()) {
                            considerAsConsequence = optionalEdge.get().equals(gmfEdge) && CONSEQUENCE_FEATURES.contains(currentNotification.getFeature());
                        }
                    }
                    return considerAsConsequence;
                }
            });
        }
        return otherNotificationsAreIndirectlyConcerned;
    }

    /**
     * Test whether the edge centering should be refreshed for this
     * notification.
     * 
     * @param notification
     *            The {@link Notification} to check.
     * @return true if this notification concerns the edge ends centering, false
     *         otherwise.
     */
    private boolean isRefreshEdgeLayoutNeededForNotification(Notification notification) {
        return REFRESH_FEATURES.contains(notification.getFeature());
    }

    /**
     * Search the corresponding GMF edge associated to this notification.
     * 
     * @param notification
     *            The {@link Notification} to analyze
     * @return an optional {@link Edge}
     */
    private Option<Edge> getCorrespondingEdge(Notification notification) {
        Edge gmfEdge = null;
        Object notifier = notification.getNotifier();
        if (notifier instanceof DEdge) {
            gmfEdge = SiriusGMFHelper.getGmfEdge((DEdge) notifier);
        } else if (notifier instanceof EdgeStyle) {
            EObject container = ((EdgeStyle) notifier).eContainer();
            if (container instanceof DEdge) {
                gmfEdge = SiriusGMFHelper.getGmfEdge((DEdge) container);
            }
        } else if (notifier instanceof RoutingStyle) {
            EObject container = ((RoutingStyle) notifier).eContainer();
            if (container instanceof Edge) {
                gmfEdge = ((Edge) container);
            }
        } else if (notifier instanceof Diagram && notification.getNewValue() instanceof Edge) {
            gmfEdge = (Edge) notification.getNewValue();
        } else if (notifier instanceof RelativeBendpoints) {
            gmfEdge = (Edge) ((RelativeBendpoints) notifier).eContainer();
        }
        if (gmfEdge == null) {
            return Options.newNone();
        } else {
            return Options.newSome(gmfEdge);
        }
    }
}
