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
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
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
    protected void createDefaultEditPolicies() {

        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.CONTAINER_ROLE, new ContainerCreationEditPolicy());
    }

    /**
     * @not-generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof DNodeContainerNameEditPart) {
            ((DNodeContainerNameEditPart) childEditPart).setLabel(getPrimaryShape().getLabelFigure());
            return true;
        }
        return super.addFixedChild(childEditPart);
    }

    /**
     * @not-generated
     */
    protected void removeChildVisual(EditPart childEditPart) {
        /* workaround, we don't want the view node container to remove it's name */
        if (!(childEditPart instanceof DNodeContainerNameEditPart)) {
            if (removeFixedChild(childEditPart)) {
                return;
            }
            super.removeChildVisual(childEditPart);
        }
    }

    /**
     * @not-generated : fix for the viewnodecontainername which was failing.
     */
    protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        if (editPart instanceof DNodeContainerNameEditPart)
            return getPrimaryShape();
        return super.getContentPaneFor(editPart);
    }

    /**
     * @was-generated
     */
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(SiriusVisualIDRegistry.getType(DNodeContainerNameEditPart.VISUAL_ID));
    }
}
