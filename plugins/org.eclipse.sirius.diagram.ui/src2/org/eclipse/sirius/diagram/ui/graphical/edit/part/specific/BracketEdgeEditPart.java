/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.part.specific;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionAnchor;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketRelativeBendpoint;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.DiagramTargetingTool;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.locators.BracketConnectionDecoratorLocator;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.BracketBendpointEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.DeleteFromDiagramEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.HideSiriusElementEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DEdgeItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.BracketConnectionRouter;

/**
 * A custom edge edit part to override router, anchors, anchors decorations and
 * bendpoints edit policy.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketEdgeEditPart extends AbstractDiagramEdgeEditPart {

    /** Unique identifier for dimension view. */
    public static final int VISUAL_ID = 4002;

    /**
     * A common {@link BracketConnectionQuery} to the source and target anchor.
     */
    private BracketConnectionQuery bracketConnectionQuery;

    /** The {@link ConnectionRouter} used by the dimension connection figure. */
    private ConnectionRouter connectionRouter;

    /**
     * Default constructor.
     * 
     * @param view
     *            the underlying {@link View}.
     */
    public BracketEdgeEditPart(View view) {
        super(view);
    }

    /**
     * Install the {@link BracketBendpointEditPolicy}. {@inheritDoc}
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        // Copied from DEdgeEditPart
        removeEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE);
        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
        CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        compoundEditPolicy.addEditPolicy(new SiriusGraphicalNodeEditPolicy());
        compoundEditPolicy.addEditPolicy(new HideSiriusElementEditPolicy());
        compoundEditPolicy.addEditPolicy(new DeleteFromDiagramEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, compoundEditPolicy);
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DEdgeItemSemanticEditPolicy());
        // Dimension specific edit policy
        installBendpointEditPolicy();
    }

    /**
     * {@inheritDoc}
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * Add the specified {@link EditPart} to this {@link BracketEdgeEditPart}.
     * 
     * @param childEditPart
     *            the {@link EditPart} to add
     * @return true if the childEditPart has been added to its parent
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        boolean added = false;
        if (childEditPart instanceof DEdgeNameEditPart) {
            ((DEdgeNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureViewEdgeNameFigure());
            added = true;
        } else if (childEditPart instanceof DEdgeEndNameEditPart) {
            ((DEdgeEndNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureViewEndEdgeNameFigure());
            added = true;
        } else if (childEditPart instanceof DEdgeBeginNameEditPart) {
            ((DEdgeBeginNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureViewBeginEdgeNameFigure());
            added = true;
        }
        return added;
    }

    /**
     * Get the primary shape figure, the {@link ViewEdgeFigure}.
     * 
     * @return the primary shape figure
     */
    private AbstractDiagramEdgeEditPart.ViewEdgeFigure getPrimaryShape() {
        return (AbstractDiagramEdgeEditPart.ViewEdgeFigure) getFigure();
    }

    /**
     * Overridden to keep the diagramEditPart as target.
     * 
     * {@inheritDoc}
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new DiagramTargetingTool(this);
    }

    /**
     * Update the routings constraints from the model constraints.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void refreshBendpoints() {
        if (bracketConnectionQuery != null) {
            List<BracketRelativeBendpoint> figureConstraint = null;
            final RelativeBendpoints bendpoints = (RelativeBendpoints) getEdge().getBendpoints();
            @SuppressWarnings("unchecked")
            final List<RelativeBendpoint> gmfRelativeBendpoints = bendpoints.getPoints();
            if (hasDefaultBendpoints(gmfRelativeBendpoints)) {
                figureConstraint = getDefaultFigureConstraint();
            } else {
                figureConstraint = bracketConnectionQuery.getBracketRelativeBendointFromGMFRelativeBendpoints(gmfRelativeBendpoints);
            }
            getConnectionFigure().setRoutingConstraint(figureConstraint);
        } else {
            super.refreshBendpoints();
        }
    }

    /**
     * Get default draw2d bendpoints.
     * 
     * @return default draw2d bendpoints
     */
    protected List<BracketRelativeBendpoint> getDefaultFigureConstraint() {
        final List<BracketRelativeBendpoint> defaultFigureConstraint = new ArrayList<BracketRelativeBendpoint>();
        defaultFigureConstraint.add(new BracketRelativeBendpoint(getConnectionFigure(), BracketConnectionQuery.DEFAULT_SOURCE_SIDE.ordinal(), BracketConnectionQuery.DEFAULT_DIRECTION.ordinal(),
                BracketConnectionQuery.DEFAULT_OFFSET));
        return defaultFigureConstraint;
    }

    /**
     * Tells if the specified {@link RelativeBendpoint}s are default one.
     * 
     * @param gmfRelativeBendpoints
     *            the specified {@link RelativeBendpoint}s
     * @return true if the specified {@link RelativeBendpoint}s are default one,
     *         false else
     */
    private boolean hasDefaultBendpoints(List<RelativeBendpoint> gmfRelativeBendpoints) {
        boolean hasDimensionBendpoints = false;
        if (gmfRelativeBendpoints.size() == 1) {
            final RelativeBendpoint firstRelativeBendpoint = gmfRelativeBendpoints.get(0);
            hasDimensionBendpoints = firstRelativeBendpoint.getTargetY() == -1;
        }
        return !hasDimensionBendpoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshRouterChange() {
        super.refreshRouterChange();
        installBendpointEditPolicy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshRoutingStyles() {
        super.refreshRoutingStyles();
        installBendpointEditPolicy();
    }

    /**
     * Install DimensionBendpointEditPolicy.
     */
    private void installBendpointEditPolicy() {
        installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new BracketBendpointEditPolicy(this));
        getConnectionFigure().setCursor(null);
    }

    /**
     * Force our own router for sequence messages.
     */
    @Override
    protected void installRouter() {
        if (getConnectionRouter() != null) {
            // AbstractDiagramEdgeEditPart.activate() call installRouter will
            // this should be done after the
            // figure creation.
            getPrimaryShape().setConnectionRouter(getConnectionRouter());
        } else {
            super.installRouter();
        }
    }

    /**
     * Get the {@link ConnectionRouter} for the dimension connection figure.
     * 
     * @return the {@link ConnectionRouter} for the dimension connection figure
     */
    private ConnectionRouter getConnectionRouter() {
        if (connectionRouter == null && bracketConnectionQuery != null) {
            connectionRouter = new BracketConnectionRouter(bracketConnectionQuery);
        }
        return connectionRouter;
    }

    /**
     * Overridden to instantiate the {@link BracketConnectionQuery}.
     * {@inheritDoc}
     */
    @Override
    protected Connection createConnectionFigure() {
        final Connection connection = super.createConnectionFigure();
        bracketConnectionQuery = new BracketConnectionQuery(connection);
        return connection;
    }

    /**
     * We provide our own specific anchor. {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor() {
        ConnectionAnchor sourceConnectionAnchor;
        if (getSource() instanceof GraphicalEditPart) {
            final GraphicalEditPart sourceGraphicalEditPart = (GraphicalEditPart) getSource();
            final IFigure owner = sourceGraphicalEditPart.getFigure();
            sourceConnectionAnchor = new BracketConnectionAnchor(owner);
        } else {
            sourceConnectionAnchor = super.getSourceConnectionAnchor();
        }
        return sourceConnectionAnchor;
    }

    /**
     * We provide our own specific anchor. {@inheritDoc}
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor() {
        ConnectionAnchor targetConnectionAnchor;
        if (getSource() instanceof GraphicalEditPart) {
            final GraphicalEditPart targetGraphicalEditPart = (GraphicalEditPart) getTarget();
            final IFigure owner = targetGraphicalEditPart.getFigure();
            targetConnectionAnchor = new BracketConnectionAnchor(owner);
        } else {
            targetConnectionAnchor = super.getTargetConnectionAnchor();
        }
        return targetConnectionAnchor;
    }

    /**
     * We do not allow other decorations that our owns. {@inheritDoc}
     */
    @Override
    public void refreshSourceDecoration() {
        final EdgeStyle style = getStyle();
        if (style == null || style.getSourceArrow() == null)
            return;
        super.refreshSourceDecoration();
        final RotatableDecoration decoration = ((ViewEdgeFigure) getConnectionFigure()).getSourceDecoration();

        if (decoration != null) {
            this.getPolylineConnectionFigure().setSourceDecoration(decoration,
                    new BracketConnectionDecoratorLocator(getConnectionFigure(), BracketConnectionDecoratorLocator.DORIGIN, bracketConnectionQuery));
        }
    }

    /**
     * We do not allow other decorations that our owns. {@inheritDoc}
     */
    @Override
    public void refreshTargetDecoration() {
        final EdgeStyle style = getStyle();
        if (style == null || style.getTargetArrow() == null)
            return;
        super.refreshTargetDecoration();
        final RotatableDecoration decoration = ((ViewEdgeFigure) getConnectionFigure()).getTargetDecoration();
        if (decoration != null) {
            this.getPolylineConnectionFigure().setTargetDecoration(decoration,
                    new BracketConnectionDecoratorLocator(getConnectionFigure(), BracketConnectionDecoratorLocator.DTARGET, bracketConnectionQuery));
        }
    }

    /**
     * Gets the style of the edge.
     * 
     * @return the edge style
     */
    private BracketEdgeStyle getStyle() {
        final EObject airdElement = this.resolveSemanticElement();
        if (airdElement instanceof DEdge) {
            final DEdge edge = (DEdge) airdElement;
            if (edge.getStyle() instanceof BracketEdgeStyle) {
                return (BracketEdgeStyle) edge.getStyle();
            }
        }
        return null;
    }

}
