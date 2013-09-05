/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;

/**
 * @was-generated
 */
public class DNodeContainer2EditPart extends AbstractDiagramContainerEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3008;

    /**
     * Construct the edit part from the view.
     * 
     * @param view
     *            the view.
     */
    public DNodeContainer2EditPart(View view) {
        super(view);
    }

    /**
     * @not-generated
     */
    protected void createDefaultEditPolicies() {
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy() {
            public Command getCommand(final Request request) {
                if (understandsRequest(request)) {
                    if (request instanceof CreateViewRequest) {
                        final CreateViewRequest request2 = (CreateViewRequest) request;
                        final Command originalCommand = super.getCreateCommand(request2);
                        return DNodeContainer2EditPart.this.getPortCreationCommand(originalCommand, request2);
                    }
                    return super.getCommand(request);
                }
                return null;
            }
        });

        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
    }

    /**
     * @not-generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DNodeContainerName2EditPart) {
            ((DNodeContainerName2EditPart) childEditPart).setLabel(getPrimaryShape().getLabelFigure());
            return true;
        }
        return super.addFixedChild(childEditPart);
    }

    /**
     * @not-generated
     */
    protected void removeChildVisual(EditPart childEditPart) {
        /* workaround, we don't want the view node container to remove it's name */
        if (!(childEditPart instanceof DNodeContainerName2EditPart)) {
            if (removeFixedChild(childEditPart)) {
                return;
            }
            super.removeChildVisual(childEditPart);
        }
    }

    /**
     * @not-generated
     */
    public IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        if (editPart instanceof DNodeContainerName2EditPart)
            return getPrimaryShape();
        return super.getContentPaneFor(editPart);
    }

    /**
     * @was-generated
     */
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(SiriusVisualIDRegistry.getType(DNodeContainerName2EditPart.VISUAL_ID));
    }
}
