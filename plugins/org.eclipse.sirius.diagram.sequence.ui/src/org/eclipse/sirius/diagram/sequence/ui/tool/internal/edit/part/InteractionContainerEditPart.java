/*******************************************************************************
 * Copyright (c) 2024 CEA.
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

import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceNoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.RubberbandDragTracker;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Special edit part for the interaction container.
 * 
 * @author smonnier
 */
public class InteractionContainerEditPart extends DNodeContainerEditPart implements ISequenceEventEditPart {

    /**
     * Default margin of the Interaction Container.
     */
    public static final int MARGIN = 50;

    /**
     * Default width of the Interaction Container.
     */
    private static final int DEFAULT_WIDTH = 30 + 80;

    /**
     * Default height of the Interaction Container.
     */
    private static final int DEFAULT_HEIGHT = 500;

    /**
     * Standard constructor, as expected by GMF.
     * 
     * @param view
     *            the view.
     */
    public InteractionContainerEditPart(View view) {
        super(view);
        setUseOverlayLabel(true);
    }

    @Override
    public void addNotify() {
        SequenceEditPartsOperations.registerDiagramElement(this, resolveDiagramElement());
        super.addNotify();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        SequenceEditPartsOperations.unregisterDiagramElement(this, resolveDiagramElement());
    }

    /**
     * Overridden to install a specific edit policy managing the moving and resizing requests on combined fragment.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
            // No user feedback wanted, don't install this policy
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }

    @Override
    protected NodeFigure createMainFigure() {
        NodeFigure figure = super.createMainFigure();
        forceInteractionContainerDefaultSize(figure);
        return figure;
    }

    @Override
    protected void addDropShadow(NodeFigure figure, IFigure shape) {
        // Remove the shadow border to avoid unwanted spacing
        figure.setBorder(null);
    }

    @Override
    public void refresh() {
        if (!(getParent() instanceof InteractionContainerEditPart)) {
            super.refresh();
            forceInteractionContainerDefaultSize((NodeFigure) getMainFigure());
        }
    }

    /**
     * Set the size of the Interaction Container figure.
     * 
     * @param newDimension
     *            the new dimension of the Interaction Container
     */
    public void setSize(Dimension newDimension) {
        IFigure mainFigure = getMainFigure();
        if (mainFigure instanceof DefaultSizeNodeFigure defaultSizeNodeFigure) {
            EObject eObj = this.resolveSemanticElement();
            if (eObj instanceof DDiagramElementContainer) {
                DDiagramElementContainer container = (DDiagramElementContainer) eObj;
                if (container.getOwnedStyle() instanceof FlatContainerStyle) {
                    defaultSizeNodeFigure.setLocation(new Point(0, 0));
                    defaultSizeNodeFigure.setDefaultSize(newDimension);
                }
            }
        }
    }

    private void forceInteractionContainerDefaultSize(NodeFigure figure) {
        if (figure instanceof DefaultSizeNodeFigure defaultSizeNodeFigure) {
            EObject eObj = this.resolveSemanticElement();
            if (eObj instanceof DDiagramElementContainer) {
                DDiagramElementContainer container = (DDiagramElementContainer) eObj;
                Dimension computeInteractionContainerSize = computeInteractionContainerSize();
                if (container.getOwnedStyle() instanceof FlatContainerStyle) {
                    defaultSizeNodeFigure.setLocation(new Point(0, 0));
                    defaultSizeNodeFigure.setDefaultSize(computeInteractionContainerSize);
                }
            }
        }
    }

    private SequenceDiagramEditPart getSequenceDiagramEditPart() {
        EditPart parent = this.getParent();
        while (parent != null && !(parent instanceof SequenceDiagramEditPart)) {
            parent = parent.getParent();
        }
        return (SequenceDiagramEditPart) parent;
    }

    private Dimension computeInteractionContainerSize() {
        Dimension computedDimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        if (getSequenceDiagramEditPart() != null && getSequenceDiagramEditPart().getChildren() != null) {
            for (EditPart childEditPart : getSequenceDiagramEditPart().getChildren()) {
                if (childEditPart instanceof InstanceRoleEditPart instanceRoleEditPart) {
                    computeInteractionContainerSizeFromInstanceRole(computedDimension, instanceRoleEditPart);
                } else if (childEditPart instanceof LostMessageEndEditPart) {
                    // A lost message can be further on the right than a parent instance role
                    GraphicalEditPart iREP = (GraphicalEditPart) childEditPart;
                    Bounds lostMessageBounds = null;
                    lostMessageBounds = (Bounds) ((Node) iREP.getModel()).getLayoutConstraint();
                    if (lostMessageBounds.getX() + lostMessageBounds.getWidth() > computedDimension.width) {
                        computedDimension.setWidth(lostMessageBounds.getX() + lostMessageBounds.getWidth() + MARGIN);
                    }
                }
            }
        }
        return computedDimension;
    }

    /**
     * scan the instance role and its children dimension in order to have the interaction container dimension updated to
     * ensure it wraps around it.
     * 
     * @param computedDimension
     *            the interaction container dimension currently being computed
     * @param instanceRoleEditPart
     *            the instance role being investigated
     */
    private void computeInteractionContainerSizeFromInstanceRole(Dimension computedDimension, InstanceRoleEditPart instanceRoleEditPart) {
        Bounds instanceRoleBounds = null;
        instanceRoleBounds = (Bounds) ((Node) instanceRoleEditPart.getModel()).getLayoutConstraint();
        // First, analyse the instance role bounds
        if (instanceRoleBounds.getX() + instanceRoleBounds.getWidth() > computedDimension.width) {
            computedDimension.setWidth(instanceRoleBounds.getX() + instanceRoleBounds.getWidth() + MARGIN);
        }

        // Then, analyse the contained lifeline.
        Optional<LifelineEditPart> optionalLifelineEditPart = instanceRoleEditPart.getChildren().stream().filter(LifelineEditPart.class::isInstance).map(LifelineEditPart.class::cast).findFirst();
        if (optionalLifelineEditPart.isPresent()) {
            LifelineEditPart lifelineEditPart = optionalLifelineEditPart.get();
            Optional<EndOfLifeEditPart> optionalEndOfLifeEditPart = lifelineEditPart.getChildren().stream().filter(EndOfLifeEditPart.class::isInstance).map(EndOfLifeEditPart.class::cast)
                    .findFirst();
            // If the lifeline ends with an end of line, then the end of line is the element at the bottom of the
            // sequence diagram and can be used to determine the height of the interaction container
            if (optionalEndOfLifeEditPart.isPresent()) {
                EndOfLifeEditPart endOfLifeEditPart = optionalEndOfLifeEditPart.get();
                Rectangle eolAbsoluteBounds = GraphicalHelper.getAbsoluteBounds(endOfLifeEditPart);
                Bounds eolBounds = (Bounds) ((Node) endOfLifeEditPart.getModel()).getLayoutConstraint();
                if (eolAbsoluteBounds.x != 0 && eolAbsoluteBounds.getBottom().y > computedDimension.height) {
                    computedDimension.setHeight(eolAbsoluteBounds.getBottom().y + MARGIN);
                } else if (instanceRoleBounds != null && instanceRoleBounds.getY() + instanceRoleBounds.getHeight() + eolBounds.getY() + eolBounds.getHeight() > computedDimension.height) {
                    computedDimension.setHeight(instanceRoleBounds.getY() + instanceRoleBounds.getHeight() + eolBounds.getY() + eolBounds.getHeight() + MARGIN);
                }
            } else {
                // There are no End of life graphical element at the end of the lifeline so the bottom of the
                // lifeline is the bottom of the diagram
                if (instanceRoleBounds != null && instanceRoleBounds.getY() + instanceRoleBounds.getHeight() > computedDimension.height) {
                    computedDimension.setHeight(instanceRoleBounds.getY() + instanceRoleBounds.getHeight() + MARGIN);
                }
            }

        }
    }


    @Override
    public ISequenceEvent getISequenceEvent() {
        return ISequenceElementAccessor.getCombinedFragment(getNotationView()).get();
    }

    @Override
    public DragTracker getDragTracker(final Request request) {
        DragTracker result = null;
        if (request instanceof SelectionRequest selectionRequest && selectionRequest.getLastButtonPressed() == 3) {
            result = new SequenceDragEditPartsTrackerEx(this);
        } else {
            if (request instanceof SelectionRequest selectionRequest && selectionRequest.isAltKeyPressed()) {
                result = new RubberbandDragTracker();
            } else {
                return new SequenceNoCopyDragEditPartsTrackerEx(this);
            }
        }
        return result;
    }

    @Override
    public EditPart getTargetEditPart(Request request) {
        return getParent();
    }

}
