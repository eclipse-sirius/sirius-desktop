/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IContainerLabelOffsets;
import org.eclipse.swt.graphics.Color;

/**
 * Basic implementation of RoundedRectangle shape with gradient and label
 * capabilities.
 * 
 * @author mporhel
 */
public class GradientRoundedRectangle extends RoundedRectangle implements ViewNodeContainerFigureDesc, ViewGradientFigureDesc, IRoundedCorner {

    private SiriusWrapLabel fLabelFigure;

    private Color gradientColor;

    private BackgroundStyle backgroundStyle;

    /**
     * Create a new {@link GradientRoundedRectangle}.
     * 
     * @param dimension
     *            dimension of the corner (with radius, height radius)
     * @param backgroundStyle
     *            style of the wanted gradient
     * 
     */
    public GradientRoundedRectangle(final Dimension dimension, final BackgroundStyle backgroundStyle) {
        this.backgroundStyle = backgroundStyle;
        this.setCornerDimensions(new Dimension(MapModeUtil.getMapMode().DPtoLP(dimension.width), MapModeUtil.getMapMode().DPtoLP(dimension.height)));
        createBorder();
        createContents();
    }

    /**
     * Create a new {@link GradientRoundedRectangle}.
     */
    public GradientRoundedRectangle() {
        this(new Dimension(8, 8), BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL);
    }

    /**
     * Sets the gradient color.
     * 
     * @param color
     *            The gradient color
     */
    public void setGradientColor(final Color color) {
        this.gradientColor = color;
    }

    /**
     * {@inheritDoc}
     * 
     * @see ViewGradientFigureDesc#getGradientColor()
     */
    public Color getGradientColor() {
        return this.gradientColor;
    }

    /**
     * Create the content of the figure.
     */
    protected void createContents() {
        fLabelFigure = new SiriusWrapLabel();
        fLabelFigure.setText("  "); //$NON-NLS-1$
        fLabelFigure.setTextWrap(true);
        this.add(fLabelFigure);
    }

    /**
     * Create the border.
     */
    protected void createBorder() {
        this.setBorder(new MarginBorder(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0));
    }

    /**
     * Return the label figure.
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc#getLabelFigure()
     * @return the label figure.
     */
    public SiriusWrapLabel getLabelFigure() {
        return fLabelFigure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillShape(Graphics graphics) {
        if (getGradientColor() != null) {
            GradientHelper.setColorsGradation(graphics, this);
        } else {
            graphics.setBackgroundColor(getBackgroundColor());
        }
        super.fillShape(graphics);
    }

    /**
     * {@inheritDoc}
     * 
     * @see ViewGradientFigureDesc#getBackgroundStyle()
     */
    public BackgroundStyle getBackgroundStyle() {
        return backgroundStyle;
    }

    /**
     * {@inheritDoc}
     */
    public int getCornerHeight() {
        return this.getCornerDimensions().height;
    }

    /**
     * {@inheritDoc}
     */
    public int getCornerWidth() {
        return this.getCornerDimensions().width;
    }
}
