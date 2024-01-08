/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNode4ItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;

/**
 * @was-generated
 */
public class DNode4EditPart extends AbstractDiagramBorderNodeInternalEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3012;

    private Object savedConstraint;

    /**
     * Construct the edit part from the view.
     * 
     * @param view
     *            the view.
     */
    public DNode4EditPart(final View view) {
        super(view);
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());

        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNode4ItemSemanticEditPolicy());
    }

    /**
     * @was-generated
     */
    @Override
    protected void addBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
        if (borderItemEditPart instanceof DNodeNameEditPart) {
            if (this.resolveSemanticElement() instanceof DNode) {
                final DNode node = (DNode) this.resolveSemanticElement();
                final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(node.getDiagramElementMapping(), node.getStyle());
                final IBorderItemLocator locator = styleConfiguration.getNameBorderItemLocator(node, getMainFigure());
                borderItemContainer.add(borderItemEditPart.getFigure(), locator);
            }
        } else if (borderItemEditPart instanceof DNode4EditPart && borderItemEditPart.resolveSemanticElement() instanceof DDiagramElement) {
            IBorderItemLocator locator = null;
            if (!(this.savedConstraint instanceof BorderItemLocator)) {
                locator = this.createBorderItemLocator(getMainFigure(), (DDiagramElement) borderItemEditPart.resolveSemanticElement());
                locator.setConstraint(borderItemEditPart.getFigure().getBounds());
            } else {
                locator = (BorderItemLocator) this.savedConstraint;
                this.savedConstraint = null;
            }
            borderItemContainer.add(borderItemEditPart.getFigure(), locator);
        } else {
            super.addBorderItem(borderItemContainer, borderItemEditPart);
        }
    }

    /**
     * @was-generated
     */
    @Override
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(SiriusVisualIDRegistry.getType(NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID));
    }
}
