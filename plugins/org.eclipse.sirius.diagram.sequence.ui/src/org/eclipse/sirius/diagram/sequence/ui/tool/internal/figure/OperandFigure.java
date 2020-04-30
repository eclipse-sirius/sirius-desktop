/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LifelineNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.OneLineMarginBorder;

/**
 * Custom figure to paint only a bottom dash line instead of a full border.
 * 
 * @author smonnier
 */
public class OperandFigure extends GradientRoundedRectangle {

    private Operand operand;

    /**
     * Create a new {@link LifelineNodeFigure}.
     * 
     * @param dimension
     *            dimension of the corner (with radius, height radius)
     * @param backgroundStyle
     *            style of the wanted gradient
     * @param operand
     *            the current operand
     */
    public OperandFigure(final Dimension dimension, final BackgroundStyle backgroundStyle, Operand operand) {
        super(dimension, backgroundStyle.getValue(), null);

        this.operand = operand;

        this.setFill(false);
        this.setOutline(false);
    }

    /**
     * The outline of the shape is disabled for Operands which have a specific border figure.
     */
    @Override
    public void setOutline(boolean b) {
        super.setOutline(false);
    }

    @Override
    protected void createBorder() {
        OneLineMarginBorder oneLineBorder = new OneLineMarginBorder(PositionConstants.BOTTOM);
        oneLineBorder.setStyle(Graphics.LINE_CUSTOM);
        oneLineBorder.setLineDash(LayoutConstants.OPERAND_DASH_STYLE);

        setBorder(oneLineBorder);
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to paint only a bottom dash line instead of a full border.
     */
    @Override
    protected void paintBorder(Graphics graphics) {
        if (!isLastOperand()) {
            super.paintBorder(graphics);
        }
    }
    
    /**
     * Create the content of the figure.
     */
    @Override
    protected void createContents() {
        fLabelFigure = new OperandLabel(this.view);
        fLabelFigure.setText("  "); //$NON-NLS-1$
        fLabelFigure.setTextWrap(true);
        this.add(fLabelFigure);
    }

    /**
     * Check if it is the last operand. In that case we do not need to paint the operand separator.
     */
    private boolean isLastOperand() {
        boolean isLast = false;
        if (operand != null) {
            isLast = operand.isLastOperand();
        }
        return isLast;
    }
}
