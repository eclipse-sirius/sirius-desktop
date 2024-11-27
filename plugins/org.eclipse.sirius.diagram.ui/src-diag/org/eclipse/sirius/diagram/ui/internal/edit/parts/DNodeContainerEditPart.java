/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ContainerCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * @was-generated
 */
public class DNodeContainerEditPart extends AbstractDiagramContainerEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 2002;

    /**
     * @was-generated
     */
    public DNodeContainerEditPart(View view) {
        super(view);
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {

        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.CONTAINER_ROLE, new ContainerCreationEditPolicy());
    }

    /**
     * @was-generated
     */
    @Override
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(SiriusVisualIDRegistry.getType(DNodeContainerNameEditPart.VISUAL_ID));
    }

    /**
     * DNodeContainerEditPart is used for DNodeContainer directly contained by
     * the DDiagram, it can not be a region.
     */
    @Override
    public boolean isRegion() {
        return false;
    }

    /**
     * DNodeContainerEditPart is used for DNodeContainer directly contained by
     * the DDiagram, it can not be a region.
     * 
     * {@inheritDoc}
     */
    @Override
    public int getParentStackDirection() {
        return PositionConstants.NONE;
    }

}
