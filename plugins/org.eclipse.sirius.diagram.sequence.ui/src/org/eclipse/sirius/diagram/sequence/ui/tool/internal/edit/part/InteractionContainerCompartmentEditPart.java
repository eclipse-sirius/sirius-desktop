/*******************************************************************************
 * Copyright (c) 2025 CEA.
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

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.description.tool.GateCreationTool;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ExecutionOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceNodeCreationPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;

/**
 * Special DNodeContainerViewNodeContainerCompartmentEditPart contained by an interaction container. This class was
 * created in order to forward command request to the parent SequenceDiagramEditPart.
 * 
 * @author smonnier
 */
public class InteractionContainerCompartmentEditPart extends DNodeContainerViewNodeContainerCompartmentEditPart {

    /**
     * Default constructor.
     * 
     * @param view
     *            {@link View} element that will be represented by this edit part.
     */
    public InteractionContainerCompartmentEditPart(View view) {
        super(view);
    }

    @Override
    public Command getCommand(Request request) {
        if (!(request instanceof CreateRequest && ((CreateRequest) request).getNewObject() instanceof GateCreationTool) && getParent() instanceof InteractionContainerEditPart
                && getParent().getParent() instanceof SequenceDiagramEditPart sequenceDiagramEditPart) {
            // Forward the request to the SequenceDiagramEditPart
            return sequenceDiagramEditPart.getCommand(request);
        }
        return super.getCommand(request);
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        ExecutionOperations.replaceEditPolicy(this, EditPolicy.CONTAINER_ROLE, new SequenceNodeCreationPolicy(), NodeCreationEditPolicy.class);
    }

}
