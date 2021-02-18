/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.business.api.query.DEdgeQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.business.internal.query.StyleConfigurationQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * Common operations for label edit parts.
 * 
 * @author ymortier
 */
public final class DiagramNameEditPartOperation {

    /**
     * Avoid instantiation.
     */
    private DiagramNameEditPartOperation() {

    }

    /**
     * Refreshes the font of the label.
     * 
     * @param self
     *            the label edit part.
     */
    public static void refreshFont(final IDiagramNameEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        final IFigure figure = self.getFigure();
        if (eObj instanceof DStylizable) {
            Style style = null;

            if (self instanceof DEdgeBeginNameEditPart && eObj instanceof DEdge) {
                Option<BeginLabelStyle> beginLabelStyle = new DEdgeQuery((DEdge) eObj).getBeginLabelStyle();
                if (beginLabelStyle.some()) {
                    DiagramNameEditPartOperation.refreshFont(self, figure, beginLabelStyle.get());
                } else {
                    figure.setVisible(false);
                }
            } else if (self instanceof DEdgeEndNameEditPart && eObj instanceof DEdge) {
                Option<EndLabelStyle> endLabelStyle = new DEdgeQuery((DEdge) eObj).getEndLabelStyle();
                if (endLabelStyle.some()) {
                    DiagramNameEditPartOperation.refreshFont(self, figure, endLabelStyle.get());
                } else {
                    figure.setVisible(false);
                }
            } else if (self instanceof DEdgeNameEditPart && eObj instanceof DEdge) {
                Option<CenterLabelStyle> centerLabelStyle = new DEdgeQuery((DEdge) eObj).getCenterLabelStyle();
                if (centerLabelStyle.some()) {
                    DiagramNameEditPartOperation.refreshFont(self, figure, centerLabelStyle.get());
                } else {
                    figure.setVisible(false);
                }
            } else {
                style = ((DStylizable) eObj).getStyle();
                if (style instanceof BasicLabelStyle) {
                    DiagramNameEditPartOperation.refreshFont(self, figure, (BasicLabelStyle) style);
                }
            }
        }
    }

    /**
     * @param self
     * @param figure
     * @param style
     */
    private static void refreshFont(final IDiagramNameEditPart self, final IFigure figure, BasicLabelStyle style) {
        // Set basic label style
        final BasicLabelStyle lStyle = style;
        figure.setFont(VisualBindingManager.getDefault().getFontFromLabelStyle(lStyle, DiagramNameEditPartOperation.getFontName(self)));

        final FontStyle fontStyle = DiagramNameEditPartOperation.getFontStyle(self);
        if (fontStyle != null && figure instanceof SiriusWrapLabel) {
            final SiriusWrapLabel wrap = (SiriusWrapLabel) figure;
            wrap.setTextUnderline(VisualBindingManager.getDefault().isUnderlineFromLabelStyle(lStyle));
            wrap.setTextStrikeThrough(VisualBindingManager.getDefault().isStrikeThroughFromLabelStyle(lStyle));
        }

        RGBValues labelRGBColor = lStyle.getLabelColor();
        Color labelColor = VisualBindingManager.getDefault().getLabelColorFromRGBValues(labelRGBColor);
        if (!(figure.getForegroundColor() != null && figure.getForegroundColor().equals(labelColor))) {
            figure.setForegroundColor(labelColor);
        }
    }

    /**
     * Returns the name of the font to use.
     * 
     * @param self
     *            the edit part.
     */
    private static String getFontName(final IDiagramNameEditPart self) {
        final FontStyle fontStyle = DiagramNameEditPartOperation.getFontStyle(self);
        if (fontStyle != null) {
            return fontStyle.getFontName();
        }
        return null;
    }

    private static FontStyle getFontStyle(final IDiagramNameEditPart self) {
        FontStyle fontStyle = (FontStyle) self.getNotationView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
        if (fontStyle == null && self.getParent() instanceof IDiagramElementEditPart) {
            final IDiagramElementEditPart parent = (IDiagramElementEditPart) self.getParent();
            fontStyle = (FontStyle) parent.getNotationView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
        }
        return fontStyle;
    }

    /**
     * <p>
     * Returns the icon of the label.
     * </p>
     * 
     * @param self
     *            the label edit part.
     * @return the image of the label.
     */
    public static Image getLabelIcon(final IDiagramNameEditPart self) {
        final EObject element = self.resolveSemanticElement();

        if (element instanceof DDiagramElement && element instanceof DStylizable) {
            //
            // Customize icon with style.
            final DiagramElementMapping mapping = ((DDiagramElement) element).getDiagramElementMapping();
            final Style style = ((DStylizable) element).getStyle();
            final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(mapping, style);
            final Image image = new StyleConfigurationQuery(styleConfiguration).getLabelIcon((DDiagramElement) element, self);
            return image;

        }
        return null;
    }

}
