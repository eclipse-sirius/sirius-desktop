/*******************************************************************************
 * Copyright (c) 2013, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.graphical.figures.OverlayLabel;
import org.eclipse.sirius.diagram.ui.graphical.figures.OverlayLabelsDrawerFigure;
import org.eclipse.sirius.ext.draw2d.figure.IRoundedCorner;
import org.eclipse.sirius.ext.draw2d.figure.ViewGradientFigureDesc;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;
import org.eclipse.swt.graphics.Color;

/**
 * Basic implementation of RoundedRectangle shape with gradient and label capabilities.
 * 
 * @author mporhel
 */
public class GradientRoundedRectangle extends RoundedRectangle implements ViewNodeContainerFigureDesc, ViewGradientFigureDesc, IRoundedCorner {

    private static final int GRADIENT_LEFT_TO_RIGHT_LITERAL = 0;

    /**
     * The label.
     */
    protected SiriusWrapLabel fLabelFigure;

    /**
     * The GMF view.
     */
    protected View view;

    private Color gradientColor;

    private int backgroundStyle;

    private boolean useOverlayLabel;

    /**
     * Create a new {@link GradientRoundedRectangle}.
     * 
     * @param dimension
     *            dimension of the corner (with radius, height radius)
     * @param backgroundStyle
     *            style of the wanted gradient
     * @param view
     *            the model view of the part showing the figure.
     * @param useOverlayLabel
     *            true to use an {@link OverlayLabel} as label, false otherwise (see javadoc of
     *            {@link OverlayLabelsDrawerFigure} to see how this kind of figure is managed).
     */
    public GradientRoundedRectangle(final Dimension dimension, final int backgroundStyle, View view, boolean useOverlayLabel) {
        this.view = view;
        this.backgroundStyle = backgroundStyle;
        this.useOverlayLabel = useOverlayLabel;
        this.setCornerDimensions(new Dimension(MapModeUtil.getMapMode().DPtoLP(dimension.width), MapModeUtil.getMapMode().DPtoLP(dimension.height)));
        createBorder();
        createContents();
    }

    /**
     * Create a new {@link GradientRoundedRectangle}.
     * 
     * @param dimension
     *            dimension of the corner (with radius, height radius)
     * @param backgroundStyle
     *            style of the wanted gradient
     * @param view
     *            the model view of the part showing the figure.
     */
    public GradientRoundedRectangle(final Dimension dimension, final int backgroundStyle, View view) {
        this(dimension, backgroundStyle, view, false);
    }

    /**
     * Create a new {@link GradientRoundedRectangle}.
     * 
     * @param view
     *            the model view of the part showing the figure.
     */
    public GradientRoundedRectangle(View view) {
        this(new Dimension(8, 8), GRADIENT_LEFT_TO_RIGHT_LITERAL, view);
    }

    @Override
    public void paint(Graphics graphics) {

        if (view != null) {
            ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, view);
            try {
                super.paint(graphics);
                graphics.restoreState();
            } finally {
                graphics.popState();
            }
        } else {
            super.paint(graphics);
        }
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
    @Override
    public Color getGradientColor() {
        return this.gradientColor;
    }

    /**
     * Create the content of the figure.
     */
    protected void createContents() {
        if (useOverlayLabel) {
            fLabelFigure = new OverlayLabel(view);
        } else {
            fLabelFigure = new SiriusWrapLabel() {
                @Override
                public void paint(Graphics graphics) {
                    if (view != null) {
                        ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, view);
                        try {
                            super.paint(graphics);
                            graphics.restoreState();
                        } finally {
                            graphics.popState();
                        }
                    } else {
                        super.paint(graphics);
                    }
                }
            };
        }
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
     * @see org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc#getLabelFigure()
     * @return the label figure.
     */
    @Override
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
    @Override
    public int getBackgroundStyle() {
        return backgroundStyle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCornerHeight() {
        return this.getCornerDimensions().height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCornerWidth() {
        return this.getCornerDimensions().width;
    }
}
