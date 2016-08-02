/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.awt.GradientPaint;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.util.GraphicsUtilities;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusGraphicsToGraphics2DAdaptor;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;

/**
 * Helper for the creation of gradient from ViewGradientFigureDesc.
 *
 * @author mporhel
 *
 */
public final class GradientHelper {

    private GradientHelper() {

    }

    /**
     * Set the gradation of colors.
     *
     * @param graphics
     *            the graphics
     * @param figure
     *            the figure
     */
    public static void setColorsGradation(final Graphics graphics, final ViewGradientFigureDesc figure) {

        final Rectangle zoomedBounds = GraphicsUtilities.zoomFillRectangle(graphics, figure.getBounds());
        if (zoomedBounds != null) {
            SWTGraphics swtGraphics = GraphicsUtilities.getSWTGraphics(graphics);
            if (swtGraphics != null) {
                final Pattern pattern = GradientHelper.getGradientPattern(figure.getBackgroundStyle().getValue(), zoomedBounds, figure.getBackgroundColor(), figure.getGradientColor());
                swtGraphics.setBackgroundPattern(pattern);
            } else {
                SiriusGraphicsToGraphics2DAdaptor siriusGraphics2DAdaptor = GraphicsUtilities.getSiriusGraphicsToGraphics2DAdaptor(graphics);
                if (siriusGraphics2DAdaptor != null) {
                    siriusGraphics2DAdaptor.setBackgroundPattern(figure.getBackgroundStyle().getValue(), zoomedBounds, figure.getBackgroundColor(), figure.getGradientColor());
                }
            }
        }
    }

    /**
     * Returns the pattern corresponding to the wanted gradient.
     *
     * @param backgroundStyle
     *            the background style
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            the gradient color
     * @return the wanted pattern
     */
    public static Pattern getGradientPattern(final int backgroundStyle, final Rectangle bounds, final Color backgroundColor, final Color gradientColor) {
        final Pattern pattern;
        switch (backgroundStyle) {
        case BackgroundStyle.GRADIENT_TOP_TO_BOTTOM:
            pattern = GradientHelper.getGradientTopToBottom(bounds, backgroundColor, gradientColor);
            break;
        case BackgroundStyle.LIQUID:
            pattern = GradientHelper.getGradientDiag(bounds, backgroundColor, gradientColor);
            break;
        case BackgroundStyle.GRADIENT_LEFT_TO_RIGHT:
        default:
            pattern = GradientHelper.getGradientLeftToRight(bounds, backgroundColor, gradientColor);
            break;
        }
        return pattern;
    }

    /**
     * Returns the pattern corresponding to the wanted gradient.
     * 
     * @param backgroundStyle
     *            the background style
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            the gradient color
     * @return the wanted pattern
     */
    public static GradientPaint getGradientPaint(int backgroundStyle, Rectangle bounds, Color backgroundColor, Color gradientColor) {
        GradientPaint gradientPaint;
        switch (backgroundStyle) {
        case BackgroundStyle.GRADIENT_TOP_TO_BOTTOM:
            gradientPaint = GradientHelper.getGradientPaintTopToBottom(bounds, backgroundColor, gradientColor);
            break;
        case BackgroundStyle.LIQUID:
            gradientPaint = GradientHelper.getGradientPaintDiag(bounds, backgroundColor, gradientColor);
            break;
        case BackgroundStyle.GRADIENT_LEFT_TO_RIGHT:
        default:
            gradientPaint = GradientHelper.getGradientPaintLeftToRight(bounds, backgroundColor, gradientColor);
            break;
        }
        return gradientPaint;
    }

    /**
     * Returns the pattern corresponding to the LeftToRight gradient.
     *
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            the gradient color
     * @return the corresponding pattern.
     */
    public static Pattern getGradientLeftToRight(final Rectangle bounds, final Color backgroundColor, final Color gradientColor) {
        return VisualBindingManager.getDefault().getPatternFromValue(bounds.x, bounds.y, bounds.width, 0, backgroundColor, gradientColor);
    }

    /**
     * Returns the pattern corresponding to the diagonal gradient.
     *
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            the gradient color
     * @return a diagonal pattern
     */
    public static Pattern getGradientDiag(final Rectangle bounds, final Color backgroundColor, final Color gradientColor) {
        // size of the square use to compute gradient
        int x = bounds.x;
        int y = bounds.y;
        int i = (bounds.width + bounds.height) / 2;

        int gradientZoneWidth = Math.max(bounds.width, i);
        if (gradientZoneWidth != bounds.width) {
            x = bounds.x - (i - bounds.width) / 2;
        }

        int gradientZoneHeight = Math.max(bounds.height, i);
        if (gradientZoneHeight != bounds.height) {
            y = bounds.y - (i - bounds.height) / 2;
        }
        return VisualBindingManager.getDefault().getPatternFromValue(x, y, gradientZoneWidth, gradientZoneHeight, backgroundColor, gradientColor);
    }

    /**
     * Returns the pattern corresponding to the TopToBottom gradient.
     *
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            the gradient color
     * @return the corresponding pattern.
     */
    public static Pattern getGradientTopToBottom(final Rectangle bounds, final Color backgroundColor, final Color gradientColor) {
        return VisualBindingManager.getDefault().getPatternFromValue(bounds.x, bounds.y, 0, bounds.height, backgroundColor, gradientColor);
    }

    /**
     * Returns the pattern corresponding to the LeftToRight gradient.
     * 
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            teh gradient color
     * @return the correesponding pattern.
     */
    public static GradientPaint getGradientPaintLeftToRight(final Rectangle bounds, final Color backgroundColor, final Color gradientColor) {
        return VisualBindingManager.getDefault().getGradientPaintFromValue(bounds.x, bounds.y, bounds.width, 0, backgroundColor, gradientColor);
    }

    /**
     * Returns the pattern corresponding to the diagonal gradient.
     * 
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            the gradient color
     * @return a diagonal pattern
     */
    public static GradientPaint getGradientPaintDiag(final Rectangle bounds, final Color backgroundColor, final Color gradientColor) {
        // size of the square use to compute gradient
        int x = bounds.x;
        int y = bounds.y;
        int i = (bounds.width + bounds.height) / 2;

        int gradientZoneWidth = Math.max(bounds.width, i);
        if (gradientZoneWidth != bounds.width) {
            x = bounds.x - (i - bounds.width) / 2;
        }

        int gradientZoneHeight = Math.max(bounds.height, i);
        if (gradientZoneHeight != bounds.height) {
            y = bounds.y - (i - bounds.height) / 2;
        }
        return VisualBindingManager.getDefault().getGradientPaintFromValue(x, y, gradientZoneWidth, gradientZoneHeight, backgroundColor, gradientColor);
    }

    /**
     * Returns the pattern corresponding to the TopToBottom gradient.
     * 
     * @param bounds
     *            the bounds
     * @param backgroundColor
     *            the background color
     * @param gradientColor
     *            the gradient color
     * @return the correesponding pattern.
     */
    public static GradientPaint getGradientPaintTopToBottom(final Rectangle bounds, final Color backgroundColor, final Color gradientColor) {
        return VisualBindingManager.getDefault().getGradientPaintFromValue(bounds.x, bounds.y, 0, bounds.height, backgroundColor, gradientColor);
    }
}
