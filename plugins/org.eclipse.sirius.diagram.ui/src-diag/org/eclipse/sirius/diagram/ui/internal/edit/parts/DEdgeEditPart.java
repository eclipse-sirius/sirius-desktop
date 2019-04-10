/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionLineSegEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.DeleteFromDiagramEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.HideSiriusElementEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.TreeLayoutConnectionLineSegEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DEdgeItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SiriusConnectionBendpointEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SiriusSelectConnectionEditPartTracker;

/**
 * @was-generated
 */
public class DEdgeEditPart extends AbstractDiagramEdgeEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 4001;

    /**
     * @was-generated
     */
    public DEdgeEditPart(View view) {
        super(view);
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        removeEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE);
        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
        CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        compoundEditPolicy.addEditPolicy(new SiriusGraphicalNodeEditPolicy());
        compoundEditPolicy.addEditPolicy(new HideSiriusElementEditPolicy());
        compoundEditPolicy.addEditPolicy(new DeleteFromDiagramEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, compoundEditPolicy);
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DEdgeItemSemanticEditPolicy());
    }

    /**
     * @was-generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DEdgeNameEditPart) {
            ((DEdgeNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureViewEdgeNameFigure());
            return true;
        }
        if (childEditPart instanceof DEdgeEndNameEditPart) {
            ((DEdgeEndNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureViewEndEdgeNameFigure());
            return true;
        }
        if (childEditPart instanceof DEdgeBeginNameEditPart) {
            ((DEdgeBeginNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureViewBeginEdgeNameFigure());
            return true;
        }
        return false;
    }

    /**
     * @was-generated
     */
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model so
     * you may safely remove <i>generated</i> tag and modify it.
     * 
     * @was-generated
     */
    @Override
    protected Connection createConnectionFigure() {
        return super.createConnectionFigure();
    }

    /**
     * @was-generated
     */
    public AbstractDiagramEdgeEditPart.ViewEdgeFigure getPrimaryShape() {
        return (AbstractDiagramEdgeEditPart.ViewEdgeFigure) getFigure();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getRoot()
     */
    @Override
    public RootEditPart getRoot() {
        if (getParent() != null)
            return super.getRoot();
        return null;
    }

    @Override
    protected void deactivateFigure() {
        // Can happen when semantic element associated to the edge has been
        // deleted
        if (getRoot() == null || figure.getParent() != getLayer(CONNECTION_LAYER)) {
            return;
        }
        super.deactivateFigure();
    }

    /**
     * Override to install the specific connection bendpoints EditPolicy to
     * correctly handle tree router.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        if (EditPolicy.CONNECTION_BENDPOINTS_ROLE.equals(key)) {
            if (editPolicy instanceof ConnectionLineSegEditPolicy) {
                super.installEditPolicy(key, new TreeLayoutConnectionLineSegEditPolicy());
            } else if (editPolicy != null && editPolicy.getClass().equals(ConnectionBendpointEditPolicy.class)) {
                super.installEditPolicy(key, new SiriusConnectionBendpointEditPolicy());
            } else {
                super.installEditPolicy(key, editPolicy);
            }
        } else {
            super.installEditPolicy(key, editPolicy);
        }
    }

    @Override
    public DragTracker getDragTracker(Request req) {
        return new SiriusSelectConnectionEditPartTracker(this);
    }

    @Override
    protected void refreshVisibility() {
        boolean gmfModelVisibility = ((View) getModel()).isVisible();
        if (gmfModelVisibility) {
            // By default, only the GMF model visibility status is considered. But if the source or the target is
            // contained in a collapsed compartment the edge must not be visible (like in
            // org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart.ConnectionRefreshMgr.refreshConnections(ShapeCompartmentEditPart)).
            IGraphicalEditPart source = (IGraphicalEditPart) this.getSource();
            IGraphicalEditPart target = (IGraphicalEditPart) this.getTarget();
            if (source == null || target == null) {
                setVisibility(false);
            } else if (!source.getFigure().isShowing() || !target.getFigure().isShowing()) {
                setVisibility(false);
            } else {
                setVisibility(true);
            }
        } else {
            setVisibility(false);
        }
    }
}
