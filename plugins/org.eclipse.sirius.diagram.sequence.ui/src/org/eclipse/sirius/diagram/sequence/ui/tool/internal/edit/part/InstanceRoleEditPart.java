/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ConnectionAnchorOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.InstanceRoleResizableEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.InstanceRoleSiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.SouthCenteredBorderItemLocator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceNoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * The edit part for lifeline instance roles.
 * 
 * @author pcdavid, smonnier
 */
public class InstanceRoleEditPart extends DNodeEditPart {

    private Notification lastNotification;

    /**
     * Constructor.
     * 
     * @param view
     *            the view.
     */
    public InstanceRoleEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveDiagramElement());
        super.addNotify();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveDiagramElement());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        ConnectionAnchor anchor = super.getTargetConnectionAnchor(request);
        if (anchor != null && new RequestQuery(request).isSequenceMessageCreation()) {
            anchor = ConnectionAnchorOperation.safeCenterAnchor(anchor);
        }
        return anchor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        ConnectionAnchor anchor = super.getSourceConnectionAnchor(request);
        if (anchor != null && new RequestQuery(request).isSequenceMessageCreation()) {
            anchor = ConnectionAnchorOperation.safeCenterAnchor(anchor);
        }
        return anchor;
    }

    /**
     * This method has been overridden to use a specific BorderItemLocator to place the lifeline properly.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public IBorderItemLocator createBorderItemLocator(IFigure figure, DDiagramElement vpElementBorderItem) {
        IBorderItemLocator result;
        if (vpElementBorderItem.getMapping() instanceof ExecutionMapping) {
            result = new SouthCenteredBorderItemLocator(figure, LayoutEditPartConstants.ROOT_EXECUTION_BORDER_ITEM_OFFSET);
        } else {
            result = super.createBorderItemLocator(figure, vpElementBorderItem);
        }
        return result;
    }

    /**
     * Overridden to install a specific edit policy managing the moving and resizing requests on lifelines.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            super.installEditPolicy(key, new InstanceRoleResizableEditPolicy());
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new InstanceRoleSiriusGraphicalNodeEditPolicy());

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    public InstanceRole getInstanceRole() {
        return ISequenceElementAccessor.getInstanceRole(getNotationView()).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new SequenceNoCopyDragEditPartsTrackerEx(this);
    }

    @Override
    protected void handleNotificationEvent(Notification notification) {
        super.handleNotificationEvent(notification);
        if (notification.getFeature().equals(NotationPackage.eINSTANCE.getLocation_X()) || notification.getFeature().equals(NotationPackage.eINSTANCE.getSize_Width())) {
            int oldEastLocation = 0;
            int newEastLocation = 0;
            // Get instance role location/dimension
            Rectangle instanceRoleBounds = GraphicalHelper.getAbsoluteBounds(this);
            // Don't forget the margin
            int margin = InteractionContainerEditPart.MARGIN;

            if (notification.getFeature().equals(NotationPackage.eINSTANCE.getSize_Width())) {
                // Note for some reason when resizing instanceRoleBounds.getRight() already has the new value which is
                // not the case during a move so we are using instanceRoleBounds.x + notification.getOldIntValue()
                // instead
                oldEastLocation = instanceRoleBounds.x + notification.getOldIntValue();
                // Get instance role resize from notification
                int newWidth = notification.getNewIntValue();
                // Compute new east bound for interaction container from this instance role
                newEastLocation = instanceRoleBounds.x + newWidth + margin;
            } else if (notification.getFeature().equals(NotationPackage.eINSTANCE.getLocation_X())) {
                oldEastLocation = instanceRoleBounds.getRight().x;
                // Get instance role location from notification
                int newX = notification.getNewIntValue();
                // Compute new east bound for interaction container from this instance role
                newEastLocation = newX + instanceRoleBounds.width + margin;
            }

            // Get interaction container edit part (child of the diagram edit part)
            Optional<InteractionContainerEditPart> optionalInteractionContainerEditPart = this.getParent().getChildren().stream().filter(InteractionContainerEditPart.class::isInstance)
                    .map(InteractionContainerEditPart.class::cast).findFirst();
            if (optionalInteractionContainerEditPart.isPresent()) {
                InteractionContainerEditPart interactionContainerEditPart = optionalInteractionContainerEditPart.get();
                Rectangle interactionContainerBounds = GraphicalHelper.getAbsoluteBounds(interactionContainerEditPart);
                // The interaction container east bound needs to be updated if:
                // - the calculated position is further
                // - the previous interaction container bound was calculated from this instance role (border location
                // matches the right side+ margin) and it remains the instance role the further on the right
                if (interactionContainerBounds.getRight().x < newEastLocation || oldEastLocation + margin == interactionContainerBounds.getRight().x && isTheFurthestEast(newEastLocation)) {
                    // Change Interaction Container size along with the end of lifeline resize
                    interactionContainerEditPart.setSize(new Dimension(newEastLocation, interactionContainerBounds.height));
                } else if (oldEastLocation + margin == interactionContainerBounds.getRight().x) {
                    InstanceRoleEditPart furthestEastInstanceRoleEditPart = getFurthestEastInstanceRoleEditPart(newEastLocation);
                    if (this.equals(furthestEastInstanceRoleEditPart)) {
                        // No change in order for the InstanceRole
                        interactionContainerEditPart.setSize(new Dimension(newEastLocation, interactionContainerBounds.height));
                    } else {
                        // This Instance Role is not the furthest on the east side anymore
                        interactionContainerEditPart.setSize(new Dimension(GraphicalHelper.getAbsoluteBounds(furthestEastInstanceRoleEditPart).getRight().x + InteractionContainerEditPart.MARGIN,
                                interactionContainerBounds.height));
                    }
                }
            }
        }
    }

    // Check that after the move, this InstanceRole is still the furthest on the right side of the diagram
    private boolean isTheFurthestEast(int x) {
        List<InstanceRoleEditPart> collect = this.getParent().getChildren().stream().filter(InstanceRoleEditPart.class::isInstance).map(InstanceRoleEditPart.class::cast).collect(Collectors.toList());
        for (InstanceRoleEditPart instanceRoleEditPart : collect) {
            if (instanceRoleEditPart != this && GraphicalHelper.getAbsoluteBounds(instanceRoleEditPart).x > x) {
                return false;
            }
        }
        return true;
    }

    private InstanceRoleEditPart getFurthestEastInstanceRoleEditPart(int x) {
        InstanceRoleEditPart furthestEastinstanceRoleEditPart = this;
        List<InstanceRoleEditPart> instanceRoleEditPartList = this.getParent().getChildren().stream().filter(InstanceRoleEditPart.class::isInstance).map(InstanceRoleEditPart.class::cast).toList();
        for (InstanceRoleEditPart instanceRoleEditPart : instanceRoleEditPartList) {
            if (instanceRoleEditPart != this && GraphicalHelper.getAbsoluteBounds(instanceRoleEditPart).x > x) {
                furthestEastinstanceRoleEditPart = instanceRoleEditPart;
            }
        }
        return furthestEastinstanceRoleEditPart;
    }

}
