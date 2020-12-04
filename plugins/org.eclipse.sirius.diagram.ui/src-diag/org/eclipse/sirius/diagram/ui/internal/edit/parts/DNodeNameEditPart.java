/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

/**
 * @was-generated
 */
public class DNodeNameEditPart extends AbstractGeneratedDiagramNameEditPart implements ITextAwareEditPart, IBorderItemEditPart {

    /**
     * @was-generated
     */
    static {
        registerSnapBackPosition(SiriusVisualIDRegistry.getType(NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID), new Point(0, 0));
        registerSnapBackPosition(SiriusVisualIDRegistry.getType(NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID), new Point(0, 0));
        registerSnapBackPosition(SiriusVisualIDRegistry.getType(NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID), new Point(0, 0));
        registerSnapBackPosition(SiriusVisualIDRegistry.getType(NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID), new Point(0, 0));
    }

    /**
     * @was-generated
     */
    public DNodeNameEditPart(final View view) {
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
    }

    /**
     * @was-generated
     */
    @Override
    public IBorderItemLocator getBorderItemLocator() {
        final IFigure parentFigure = getFigure().getParent();
        if (parentFigure != null && parentFigure.getLayoutManager() != null) {
            final Object constraint = parentFigure.getLayoutManager().getConstraint(getFigure());
            return (IBorderItemLocator) constraint;
        }
        return null;
    }

    /**
     * @not-generated
     */
    @Override
    public void refreshBounds() {
        final EObject eObj = this.resolveSemanticElement();
        if (eObj instanceof DNode) {
            if (((NodeStyle) ((DNode) eObj).getStyle()).getLabelPosition() == LabelPosition.BORDER_LITERAL) {
                final int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
                final int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
                final int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
                final int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
                getBorderItemLocator().setConstraint(new Rectangle(x, y, width, height));
                getFigure().revalidate();
            } else {
                getBorderItemLocator().setConstraint(new Rectangle(0, 0, 0, 0));
                getFigure().setBounds(new Rectangle(0, 0, 0, 0));
            }
        }
    }

    /**
     * @was-generated
     */
    @Override
    public void setLabel(final IFigure figure) {
        unregisterVisuals();
        setFigure(figure);
        defaultText = getLabelTextHelper(figure);
        registerVisuals();
        refreshVisuals();
    }

    @Override
    protected IElementType getParserElementType() {
        return SiriusElementTypes.DNode_2001;
    }

    /**
     * @not-generated : the label is invisible when we must show the center label.
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        final EObject eObj = this.resolveSemanticElement();
        if (eObj instanceof DNode) {
            if (((NodeStyle) ((DNode) eObj).getStyle()).getLabelPosition() != LabelPosition.BORDER_LITERAL) {
                this.getFigure().setVisible(false);
            } else {
                this.getFigure().setVisible(true);
            }
        }
    }

    /**
     * @was-generated
     */
    @Override
    protected void handleNotificationEvent(final Notification event) {
        final Object feature = event.getFeature();
        if (DiagramPackage.eINSTANCE.getDNode_OwnedStyle() == feature) {
            refreshVisuals();
        }
        super.handleNotificationEvent(event);
    }

    /**
     * @was-generated
     */
    @Override
    protected IFigure createFigure() {
        final IFigure label = createFigurePrim();
        defaultText = getLabelTextHelper(label);
        return label;
    }

    /**
     * @not-generated
     */
    protected IFigure createFigurePrim() {
        SiriusWrapLabel figure = new ViewNodeNameFigureDesc();
        figure.setTextWrap(true);
        return figure;
    }

    public class ViewNodeNameFigureDesc extends SiriusWrapLabel {

        @Override
        public void paint(Graphics graphics) {
            if (getModel() instanceof View) {
                ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, (View) getModel());
                try {
                    CommonEditPartOperation.setGraphicsTraceabilityId(graphics, () -> resolveTargetSemanticElement());
                    super.paint(graphics);
                    CommonEditPartOperation.setGraphicsTraceabilityId(graphics, null);
                    graphics.restoreState();
                } finally {
                    graphics.popState();
                }
            } else {
                super.paint(graphics);
            }
        }

        /**
         * @was-generated
         */
        public ViewNodeNameFigureDesc() {
            this.setText("<...>"); //$NON-NLS-1$
        }
    }
}
