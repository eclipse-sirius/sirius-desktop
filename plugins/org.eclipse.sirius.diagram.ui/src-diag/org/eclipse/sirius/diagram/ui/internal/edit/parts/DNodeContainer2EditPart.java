/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

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
     * @was-generated
     */
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(SiriusVisualIDRegistry.getType(DNodeContainerName2EditPart.VISUAL_ID));
    }
}
