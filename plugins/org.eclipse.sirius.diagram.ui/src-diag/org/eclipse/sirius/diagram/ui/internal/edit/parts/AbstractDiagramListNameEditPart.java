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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;

/**
 * @was-generated
 */
public abstract class AbstractDiagramListNameEditPart extends AbstractDiagramElementContainerNameEditPart implements ITextAwareEditPart {

    /**
     * @was-generated
     */
    public AbstractDiagramListNameEditPart(final View view) {
        super(view);
    }

    /**
     * We want a special behavior with direct editing.
     * 
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

        // Remove this edit policy because it interfers with next new edit
        // policy
        removeEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE);
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeCreationEditPolicy() {

            /**
             * {@inheritDoc}
             */
            @Override
            public EditPart getTargetEditPart(final Request request) {
                // Forward this request to container : label = container
                return getHost().getParent();
            }
        });
    }
}
