/*******************************************************************************
 * Copyright (c) 2014, 2024 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.operation.RemoveBendpointsOperation;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.DForestRouter;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A Model Change Trigger that execute the {@link CenterEdgeEndModelChangeOperation} when features defined in
 * {@link RefreshEdgeLayoutScopePredicate} are updated.
 * 
 * @author Florian Barbin
 */
@SuppressWarnings("restriction")
public class EdgeLayoutUpdaterModelChangeTrigger implements ModelChangeTrigger {

    public static final int PRIORITY = FilterListener.COMPOSITE_FILTER_REFRESH_PRIORITY + 1;

    /**
     * List of features for which we consider that the edge layout must be recompute.
     */
    private static final Set<EStructuralFeature> REFRESH_FEATURES = new HashSet<EStructuralFeature>();

    /**
     * Sublist of <code>REFRESH_FEATURES</code> that also have other features as consequence.
     */
    private static final Set<EStructuralFeature> REFRESH_FEATURES_WITH_CONSEQUENCE = new HashSet<EStructuralFeature>();

    /**
     * List of features that are standard consequences of <code>REFRESH_FEATURES_WITH_CONSEQUENCE</code>.
     */
    private static final Set<EStructuralFeature> CONSEQUENCE_FEATURES = new HashSet<EStructuralFeature>();

    /**
     * List of features for which we consider that the edge bendpoints must be recompute after change into oblique edge.
     */
    private static final Set<EStructuralFeature> ROUTING_FEATURES = new HashSet<EStructuralFeature>();

    /**
     * List of features concerning move or resize of the source/target of an edge.
     */
    private static final Set<EStructuralFeature> MOVE_OR_RESIZE_FEATURES = new HashSet<EStructuralFeature>();

    static {
        REFRESH_FEATURES_WITH_CONSEQUENCE.add(DiagramPackage.Literals.EDGE_STYLE__CENTERED);
        REFRESH_FEATURES_WITH_CONSEQUENCE.add(NotationPackage.Literals.ROUTING_STYLE__ROUTING);

        REFRESH_FEATURES.addAll(REFRESH_FEATURES_WITH_CONSEQUENCE);
        REFRESH_FEATURES.add(DiagramPackage.Literals.DEDGE__OWNED_STYLE);
        REFRESH_FEATURES.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES);

        CONSEQUENCE_FEATURES.add(ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES);
        CONSEQUENCE_FEATURES.add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE);

        ROUTING_FEATURES.add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE);
        ROUTING_FEATURES.add(NotationPackage.Literals.ROUTING_STYLE__ROUTING);

        MOVE_OR_RESIZE_FEATURES.add(NotationPackage.Literals.LOCATION__X);
        MOVE_OR_RESIZE_FEATURES.add(NotationPackage.Literals.LOCATION__Y);
        MOVE_OR_RESIZE_FEATURES.add(NotationPackage.Literals.SIZE__WIDTH);
        MOVE_OR_RESIZE_FEATURES.add(NotationPackage.Literals.SIZE__HEIGHT);
        MOVE_OR_RESIZE_FEATURES.add(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT);
        MOVE_OR_RESIZE_FEATURES.add(NotationPackage.Literals.EDGE__BENDPOINTS);
    }

    private TransactionalEditingDomain domain;

    private SessionEventBroker eventBroker;

    private RefreshEdgeLayoutNotificationFilter refreshEdgeLayoutNotificationFilter;

    /**
     * Constructor. Add this EdgeLayoutUpdaterModelChangeTrigger to the session event broker of the given session.
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

        Map<Notification, Edge> notifToEdge = new HashMap<>();
        Map<Notification, Node> notifToNode = new HashMap<>();
        List<View> movedOrResizedViews = new ArrayList<>();
        prepareData(notifications, notifToEdge, notifToNode, movedOrResizedViews);

        for (Notification notification : notifications) {
            boolean isChangedIntoOblique = changeIntoOblique(operations, notification, notifToEdge);
            // Only consider notification of
            // EdgeLayoutUpdaterModelChangeTrigger.REFRESH_FEATURES list and for
            // which the source or the target has not been moved
            if (isRefreshEdgeLayoutNeededForNotification(notification, notifToEdge, movedOrResizedViews) && !isChangedIntoOblique) {
                Optional<Edge> optionalGmfEdge = getCorrespondingEdge(notification, notifToEdge);
                if (optionalGmfEdge.isPresent() && edgesWithCreatedCommand.add(optionalGmfEdge.get())) {
                    // if there are several notifications, we do not try to
                    // retrieve draw2D informations since they could be out of
                    // date.
                    boolean useFigure = otherNotificationsAreConsequences(notification, optionalGmfEdge.get(), notifications, notifToEdge);
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

    /**
     * Change edge into oblique line if the edge should be change from rectilinear or tree style into oblique.
     * 
     * @param operations
     *            list of operations to execute in the precommit listener,
     * @param notification
     *            notification that trigger action,
     * @param notifToEdge
     *            map to store the edge corresponding to the notification,
     * 
     * @return true if the edge has been changed into an oblique line.
     */
    private boolean changeIntoOblique(Collection<AbstractModelChangeOperation<Void>> operations, Notification notification, Map<Notification, Edge> notifToEdge) {
        boolean isChangedIntoOblique = false;
        if (ROUTING_FEATURES.contains(notification.getFeature())) {
            Optional<Edge> optionalGmfEdge = getCorrespondingEdge(notification, notifToEdge);
            if (optionalGmfEdge.isPresent()) {
                // when edge routing style is changed into oblique, prepare the new point list in oblique line
                DEdgeEditPart edgeEditPart = getEdgeEditPart(optionalGmfEdge.get());
                if (edgeEditPart != null) {
                    Connection connection = edgeEditPart.getConnectionFigure();
                    ConnectionRouter oldConnectionRouter = connection.getConnectionRouter();
                    EdgeQuery edgeQuery = new EdgeQuery(optionalGmfEdge.get());
                    if ((oldConnectionRouter instanceof RectilinearRouter || oldConnectionRouter instanceof DForestRouter) && edgeQuery.isEdgeWithObliqueRoutingStyle()) {
                        RemoveBendpointsOperation removeBendpointsOperation = new RemoveBendpointsOperation(edgeEditPart);
                        operations.add(removeBendpointsOperation);
                        isChangedIntoOblique = true;
                    }
                }
            }
        }
        return isChangedIntoOblique;
    }

    /**
     * Get all {@link View}s that have been moved or resized from notifications.
     * 
     * @param notifications
     *            The notifications
     * @param notifToEdge
     *            map to store the edge corresponding to the notification
     * @param notifToNode
     *            map to store the node corresponding to the notification
     * @param moveOrResizeViews
     *            resulting list of moved or resized views
     */
    private void prepareData(Collection<Notification> notifications, Map<Notification, Edge> notifToEdge, Map<Notification, Node> notifToNode, List<View> moveOrResizeViews) {
        for (Notification notification : notifications) {
            if (MOVE_OR_RESIZE_FEATURES.contains(notification.getFeature())) {
                getCorrespondingView(notification, notifToEdge, notifToNode).ifPresent(moveOrResizeViews::add);
            }
        }
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
     * Dispose this trigger. The trigger is removed from the session event broker.
     */
    public void dispose() {
        refreshEdgeLayoutNotificationFilter = null;
        eventBroker.removeLocalTrigger(this);
        eventBroker = null;
        domain = null;

    }

    /**
     * Test whether the other notifications are consequences of the given one. For instance, in case of a manual
     * modification of the Sirius routing style (from Style tab of Properties view), we also update the GMF style and we
     * add the routing style within the custom features. This method aims to check whether we are in the case of an
     * individual modification or a global one.
     * 
     * @param notification
     *            the notification for which we are notified.
     * @param gmfEdge
     *            the GMF edge associated to the <code>notification</code>
     * @param notifications
     *            the whole notification list.
     * @param notifToview
     *            Map to retrieve the View if yet computed
     * @return true if the notifications list contains only notifications induced by the first one.
     */
    public boolean otherNotificationsAreConsequences(final Notification notification, final Edge gmfEdge, Collection<Notification> notifications, Map<Notification, Edge> notifToEdge) {
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
                        Optional<Edge> optionalEdge = getCorrespondingEdge(currentNotification, notifToEdge);
                        if (optionalEdge.isPresent()) {
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
     * Test whether the edge centering should be refreshed for this notification.
     * 
     * @param notification
     *            The {@link Notification} to check.
     * @param notifToEdge
     *            Map to retrieve the Edge if yet computed
     * @param movedOrResizedViews
     *            Map to retrieve the Node if yet computed
     * @return true if this notification concerns the edge ends centering, false otherwise.
     */
    private boolean isRefreshEdgeLayoutNeededForNotification(final Notification notification, Map<Notification, Edge> notifToEdge, List<View> movedOrResizedViews) {
        if (REFRESH_FEATURES.contains(notification.getFeature())) {
            Optional<Edge> optionalEdge = getCorrespondingEdge(notification, notifToEdge);
            if (optionalEdge.isPresent()) {
                // If one of the source or target of the edge is moved, then the refresh will be done in the context of
                // the full refresh so it is not the responsibility to this ModelChangeTrigger to do it
                final Edge referenceEdge = optionalEdge.get();
                if (!movedOrResizedViews.contains(referenceEdge.getSource()) && !movedOrResizedViews.contains(referenceEdge.getTarget())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Search the corresponding GMF view associated to this notification.
     * 
     * @param notification
     *            The {@link Notification} to analyze
     * @param notifToEdge
     *            Map to retrieve the Edge if yet computed
     * @param notifToNode
     *            Map to retrieve the Node if yet computed
     * @return an optional {@link View}
     */
    private Optional<? extends View> getCorrespondingView(Notification notification, Map<Notification, Edge> notifToEdge, Map<Notification, Node> notifToNode) {
        Optional<? extends View> result = getCorrespondingNode(notification, notifToNode);
        if (!result.isPresent()) {
            result = getCorrespondingEdge(notification, notifToEdge);
        }
        return result;
    }

    /**
     * Search the corresponding GMF edge associated to this notification.
     * 
     * @param notification
     *            The {@link Notification} to analyze
     * @param notifToEdge
     *            Map to retrieve the edge if yet computed
     * @return an optional {@link Edge}
     */
    private Optional<Edge> getCorrespondingEdge(Notification notification, Map<Notification, Edge> notifToEdge) {
        if (notifToEdge.containsKey(notification)) { // value may be null
            return Optional.ofNullable(notifToEdge.get(notification));
        }

        Edge gmfEdge = null;
        Object notifier = notification.getNotifier();
        if (notifier instanceof Edge) {
            gmfEdge = (Edge) notifier;
        } else if (notifier instanceof DEdge) {
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
        notifToEdge.put(notification, gmfEdge);

        return Optional.ofNullable(gmfEdge);
    }

    /**
     * Search the corresponding GMF node associated to this notification.
     * 
     * @param notification
     *            The {@link Notification} to analyze
     * @param notifToNode
     *            Map to retrieve the Node if yet computed
     * @return an optional {@link Node}
     */
    private Optional<Node> getCorrespondingNode(Notification notification, Map<Notification, Node> notifToNode) {
        if (notifToNode.containsKey(notification)) { // value may be null
            return Optional.ofNullable(notifToNode.get(notification));
        }

        Node gmfNode = null;
        Object notifier = notification.getNotifier();
        if (notifier instanceof DNode) {
            gmfNode = SiriusGMFHelper.getGmfNode((DNode) notifier);
        } else if (notifier instanceof Style) {
            EObject container = ((Style) notifier).eContainer();
            if (container instanceof DNode) {
                gmfNode = SiriusGMFHelper.getGmfNode((DNode) container);
            }
        } else if (notifier instanceof Location) {
            EObject container = ((Location) notifier).eContainer();
            if (container instanceof Node) {
                gmfNode = ((Node) container);
            }
        }
        notifToNode.put(notification, gmfNode);

        return Optional.ofNullable(gmfNode);
    }

    /**
     * Get the edit part corresponding to a given edge. The edit part returned can be null, for example in case of
     * creation of new edge.
     * 
     * @param edge
     *            the edge with the edit part to recover
     * @return the edit part corresponding to a given edge.
     */
    private DEdgeEditPart getEdgeEditPart(Edge edge) {
        DEdgeEditPart edgeEditPart = null;
        Optional<GraphicalEditPart> option = Optional.empty();
        final IEditorPart editorPart = EclipseUIUtil.getActiveEditor();
        if (editorPart instanceof DiagramEditor) {
            option = GMFHelper.getGraphicalEditPart(edge, (DiagramEditor) editorPart);

        } else {
            // If the active editor is not a DiagramEditor, that means the focus is
            // on a VSM or an other editor that triggered this operation
            List<?> editors = EditorService.getInstance().getRegisteredEditorParts();
            for (Iterator<?> it = editors.iterator(); it.hasNext();) {
                Object object = it.next();
                if (object instanceof DiagramEditor) {
                    option = GMFHelper.getGraphicalEditPart(edge, (DiagramEditor) object);
                }
                if (option.isPresent()) {
                    break;
                }
            }
        }
        if (option.isPresent()) {
            GraphicalEditPart editPart = option.get();
            if (editPart instanceof DEdgeEditPart) {
                edgeEditPart = ((DEdgeEditPart) editPart);
            }
        }
        return edgeEditPart;
    }
}
