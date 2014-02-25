/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.locators.BracketLabelLocator;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.locators.BracketResizableLabelLocator;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.BracketResizableShapeLabelEditPolicy;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * @was-generated NOT
 */
public class DEdgeNameEditPart extends AbstractDEdgeNameEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 6001;

    /**
     * @not-generated
     */
    static {
        registerSnapBackPosition(SiriusVisualIDRegistry.getType(org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart.VISUAL_ID), new Point(0, -10));
    }

    /**
     * @was-generated
     */
    public DEdgeNameEditPart(View view) {
        super(view);
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        if (getParent() instanceof BracketEdgeEditPart) {
            installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new BracketResizableShapeLabelEditPolicy());
        }
    }

    /**
     * Overridden to set a bracket specific labelLocator for the center label to
     * have the middle as reference point.
     * 
     * {@inheritDoc}
     */
    @Override
    public void refreshBounds() {
        if (getParent() instanceof BracketEdgeEditPart) {
            if (isResizable()) {
                handleResizableRefreshBounds();
            } else {
                handleNonResizableRefreshBoundS();
            }
        } else {
            super.refreshBounds();
        }
    }

    /**
     * handles non resizable lable refresh bounds
     */
    private void handleNonResizableRefreshBoundS() {
        int dx = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
        int dy = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
        Point offset = new Point(dx, dy);
        ((AbstractGraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new BracketLabelLocator(((AbstractConnectionEditPart) getParent()).getConnectionFigure(), offset,
                getKeyPoint()));

    }

    /**
     * handles resizable lable refresh bounds
     */
    private void handleResizableRefreshBounds() {
        int dx = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
        int dy = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
        int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
        int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
        Rectangle rectangle = new Rectangle(dx, dy, width, height);
        ((AbstractGraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new BracketResizableLabelLocator(((AbstractConnectionEditPart) getParent()).getConnectionFigure(), rectangle,
                getKeyPoint()));
    }

    /**
     * @was-generated
     */
    public int getKeyPoint() {
        return ConnectionLocator.MIDDLE;
    }

}
