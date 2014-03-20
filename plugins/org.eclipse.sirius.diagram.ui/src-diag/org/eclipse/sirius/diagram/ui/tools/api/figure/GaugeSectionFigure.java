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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.TransparentFigureGraphicsModifier;

/**
 * Figure of a section of a quadriptyque.
 * 
 * @author ymortier
 */
public class GaugeSectionFigure extends AbstractTransparentNode {

    /** The minimal value. */
    private int min;

    /** The maximal value. */
    private int max;

    /** The value. */
    private int value;

    private final TextFlow textFlow = new TextFlow(StringUtil.EMPTY_STRING);

    /**
     * Creates a new <code>QuadriptyqueSectionFigure</code>.
     * 
     * @param min
     *            the minimal value.
     * @param max
     *            the maximal value.
     * @param value
     *            the value.
     */
    public GaugeSectionFigure(final int min, final int max, final int value) {
        this.setLayoutManager(new FlowLayout());
        final FlowPage flowPage = new FlowPage();
        flowPage.add(textFlow);
        this.add(flowPage);
        this.textFlow.setForegroundColor(ColorConstants.white);
        this.min = min;
        this.max = max;
        this.value = value;
        this.setBorder(new LineBorder());
    }

    /**
     * Returns the minimal value.
     * 
     * @return the minimal value.
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the minimal value.
     * 
     * @param min
     *            the minimal value.
     */
    public void setMin(final int min) {
        this.min = min;
    }

    /**
     * Returns the maximal value.
     * 
     * @return the maximal value.
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the maximal value.
     * 
     * @param max
     *            the maximal value.
     */
    public void setMax(final int max) {
        this.max = max;
    }

    /**
     * Returns the value.
     * 
     * @return the value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value.
     * 
     * @param value
     *            the value.
     */
    public void setValue(final int value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
     */
    @Override
    protected boolean useLocalCoordinates() {
        return true;
    }

    /**
     * Set the section label.
     * 
     * @param label
     *            new label value.
     */
    public void setLabel(final String label) {
        this.textFlow.setText(label);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(final Graphics graphics) {
        TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, graphics);
        modifier.pushState();

        super.paintFigure(graphics);
        final Rectangle bounds = new Rectangle(this.getBounds());
        final int divisor = max < min ? 1 : max - min;
        final int val = this.value > max ? max - min : this.value - min;
        final int y;
        if (divisor == 0) {
            y = val == 0 ? 0 : bounds.height;
        } else {
            y = (int) (bounds.height * ((double) val / divisor));
        }

        graphics.setLineWidth(0);
        graphics.setBackgroundColor(this.getBackgroundColor());
        graphics.setForegroundColor(this.getBackgroundColor());
        graphics.fillRectangle(+bounds.x, bounds.y, bounds.width, bounds.height - y);
        graphics.setForegroundColor(this.getForegroundColor());
        graphics.setBackgroundColor(this.getForegroundColor());
        graphics.fillRectangle(bounds.x, bounds.y + bounds.height - y, bounds.width, y);

        modifier.popState();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#paintBorder(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintBorder(final Graphics graphics) {
        graphics.setBackgroundColor(ColorConstants.white);
        graphics.setForegroundColor(ColorConstants.white);
        super.paintBorder(graphics);
    }

    /**
     * Create the default section.
     * 
     * @return a default section.
     */
    public static GaugeSectionFigure createDefaultSection() {
        return new GaugeSectionFigure(1, 10, 5);
    }
}
