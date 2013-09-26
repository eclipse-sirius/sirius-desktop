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
package org.eclipse.sirius.diagram.internal.edit.parts;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.internal.providers.SiriusParserProvider;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.LabelPosition;
import org.eclipse.sirius.viewpoint.NodeStyle;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
    }

    /**
     * @was-generated
     */
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
    public void refreshBounds() {
        final EObject eObj = this.resolveSemanticElement();
        if (eObj instanceof DNode) {
            if (((NodeStyle) ((DNode) eObj).getStyle()).getLabelPosition() == LabelPosition.BORDER_LITERAL) {
                final int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
                final int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
                final int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
                final int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
                getBorderItemLocator().setConstraint(new Rectangle(x, y, width, height));
            } else {
                getBorderItemLocator().setConstraint(new Rectangle(0, 0, 0, 0));
                getFigure().setBounds(new Rectangle(0, 0, 0, 0));
            }
        }
    }

    /**
     * @was-generated
     */
    public void setLabel(final IFigure figure) {
        unregisterVisuals();
        setFigure(figure);
        defaultText = getLabelTextHelper(figure);
        registerVisuals();
        refreshVisuals();
    }

    /**
     * @was-generated
     */
    public IParser getParser() {
        if (parser == null) {
            final String parserHint = ((View) getModel()).getType();
            final IAdaptable hintAdapter = new SiriusParserProvider.HintAdapter(SiriusElementTypes.DNode_2001, getParserElement(), parserHint);
            parser = ParserService.getInstance().getParser(hintAdapter);
        }
        return parser;
    }

    /**
     * @not-generated : the label is invisible when we must show the center
     *                label.
     */
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
    protected void handleNotificationEvent(final Notification event) {
        final Object feature = event.getFeature();
        if (ViewpointPackage.eINSTANCE.getDNode_OwnedStyle() == feature) {
            refreshVisuals();
        }
        super.handleNotificationEvent(event);
    }

    /**
     * @was-generated
     */
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

    /**
     * @not-generated
     */
    public static class ViewNodeNameFigureDesc extends SiriusWrapLabel {

        /**
         * @was-generated
         */
        public ViewNodeNameFigureDesc() {
            this.setText("<...>");
        }
    }
}
