/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ExecutionOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceLaunchToolEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceNodeCreationPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;

/**
 * Custom edit part to customize what happens inside an operand.
 * 
 * @author pcdavid
 */
public class OperandCompartmentEditPart extends DNodeContainerViewNodeContainerCompartment2EditPart {
    /**
     * The visual ID. Same as a normal container compartment.
     * 
     * @see DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID
     */
    public static final int VISUAL_ID = 7002;

    /**
     * Constructor.
     * 
     * @param view
     *            the view <code>controlled</code> by this editpart.
     */
    public OperandCompartmentEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        ExecutionOperations.replaceEditPolicy(this, EditPolicy.CONTAINER_ROLE, new SequenceNodeCreationPolicy(), NodeCreationEditPolicy.class);

        // Handle $endBefore for launch tools.
        installEditPolicy(org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants.REQ_LAUNCH_TOOL, new SequenceLaunchToolEditPolicy());
    }
}
