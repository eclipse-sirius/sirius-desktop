/***********************************************************************
 * Copyright (c) 2008, 2019 Anyware Technologies and others.
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Simon Bernard (Anyware Technologies) - initial API and implementation
 *    Cedric Brun (Obeo) - changes to consider the included shape and not the wrapping one.
 *
 * $Id: AlphaDropShadowBorder.java,v 1.1 2008/08/12 13:24:50 jlescot Exp $
 **********************************************************************/
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures;

import org.eclipse.draw2d.AbstractBackground;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.DiagramColorConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.DropShadowBorder;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IPolygonAnchorableFigure;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.sirius.ext.draw2d.figure.IRoundedCorner;
import org.eclipse.swt.graphics.Color;

/**
 * A border using a shadow<br>
 * creation : 17 mai. 08
 * 
 * @author <a href="mailto:simon.bernard@anyware-tech.com">Simon Bernard</a> CHECKSTYLE:OFF
 */
public class AlphaDropShadowBorder extends AbstractBackground implements DropShadowBorder {

    private static final int DEFAULT_SHIFT_VALUE = 1;

    private static final Color SHADOW_COLOR = DiagramColorConstants.diagramDarkGray;

    private static final int DEFAULT_TRANSPARENCY = 65;

    private boolean shouldDrawShadow = true;

    private int shift = DEFAULT_SHIFT_VALUE;

    private IFigure shape;

    /**
     * Get the default shadow size of an {@link AlphaDropShadowBorder} figure.
     * 
     * @return The default shadow size
     */
    public static int getDefaultShadowSize() {
        return DEFAULT_SHIFT_VALUE * 2;
    }

    /**
     * 
     * @param shape
     */
    public AlphaDropShadowBorder(IFigure shape) {
        super();
        this.shape = shape;
    }

    @Override
    public void setShouldDrawDropShadow(boolean drawDropShadow) {
        shouldDrawShadow = drawDropShadow;
    }

    @Override
    public boolean shouldDrawDropShadow() {
        return shouldDrawShadow;
    }

    /**
     * Method for determining the inset the border will take up on the shape.
     * 
     * @param figure
     *            Figure that will be inset from the border
     * @return Insets the Insets for the border on the given figure.
     */
    @Override
    public Insets getInsets(IFigure figure) {
        Insets insetsNew = new Insets();
        insetsNew.top = 0;
        insetsNew.left = 0;
        IMapMode mapMode = MapModeUtil.getMapMode(figure);
        insetsNew.bottom = mapMode.DPtoLP(shift * 2);
        insetsNew.right = mapMode.DPtoLP(shift * 2);
        return insetsNew;
    }

    @Override
    public Insets getTransparentInsets(IFigure figure) {
        Insets insetsNew = new Insets();
        insetsNew.top = 0;
        insetsNew.left = 0;
        IMapMode mapMode = MapModeUtil.getMapMode(figure);
        insetsNew.bottom = mapMode.DPtoLP(shift * 2);
        insetsNew.right = mapMode.DPtoLP(shift * 2);
        return insetsNew;
    }

    @Override
    public void paintBackground(IFigure figure, Graphics graphics, Insets insets) {
        if (shouldDrawDropShadow() && shape != null && shape.getParent() != null) {
            int ORIGINALALPHA = graphics.getAlpha();
            graphics.pushState();
            graphics.setBackgroundColor(SHADOW_COLOR);
            graphics.setAlpha(DEFAULT_TRANSPARENCY);
            if (shape instanceof IRoundedCorner) {
                int cWidth = ((IRoundedCorner) shape).getCornerWidth();
                int cHeight = ((IRoundedCorner) shape).getCornerWidth();
                Rectangle bounds = shape.getBounds().getCopy();
                bounds.translate(shift, shift);
                graphics.fillRoundRectangle(bounds, cWidth, cHeight);
                bounds.translate(shift, shift);
                graphics.fillRoundRectangle(bounds, cWidth, cHeight);

            } else if (shape instanceof IPolygonAnchorableFigure) {
                PointList polygonPoints = ((IPolygonAnchorableFigure) shape).getPolygonPoints();
                polygonPoints.translate(shift, shift);
                graphics.fillPolygon(polygonPoints);
                polygonPoints.translate(shift, shift);
                graphics.fillPolygon(polygonPoints);
            } else {
                Rectangle bounds = figure.getBounds().getCopy();
                bounds.translate(shift, shift);
                graphics.fillRoundRectangle(bounds, 0, 0);
                bounds.translate(shift, shift);
                graphics.fillRoundRectangle(bounds, 0, 0);
            }
            graphics.setAlpha(ORIGINALALPHA);
            graphics.popState();
        }
    }
}
