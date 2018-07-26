/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

/**
 * The figure used for DNodeEditPart and its variants.
 * 
 * @author pcdavid
 */
public class ViewNodeFigure extends RectangleFigure {
    private final SiriusWrapLabel nodeLabel = new SiriusWrapLabel() {
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

    private View view;

    /**
     * Create a new figure.
     * 
     * @param view
     *            the model view of the part showing the figure.
     */
    public ViewNodeFigure(View view) {
        this.view = view;
        final FlowLayout layoutThis = new FlowLayout();
        layoutThis.setStretchMinorAxis(false);
        layoutThis.setMinorAlignment(FlowLayout.ALIGN_TOPLEFT);

        layoutThis.setMajorAlignment(FlowLayout.ALIGN_TOPLEFT);
        layoutThis.setMajorSpacing(5);
        layoutThis.setMinorSpacing(5);
        layoutThis.setHorizontal(true);

        this.setLayoutManager(layoutThis);

        this.setFill(false);
        this.setOutline(false);
        this.setLineWidth(0);

        nodeLabel.setTextWrap(true);
        nodeLabel.setTextAlignment(PositionConstants.CENTER);
        nodeLabel.setTextWrapAlignment(PositionConstants.CENTER);
        nodeLabel.setLabelAlignment(PositionConstants.CENTER);
        nodeLabel.setForegroundColor(ColorConstants.black);
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
     * Return the figure for this node's label.
     * 
     * @return the figure for this node's label.
     */
    public SiriusWrapLabel getNodeLabel() {
        return this.nodeLabel;
    }
}
