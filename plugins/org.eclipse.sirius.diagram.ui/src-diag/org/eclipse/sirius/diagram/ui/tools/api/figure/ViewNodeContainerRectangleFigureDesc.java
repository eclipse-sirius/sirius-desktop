/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IContainerLabelOffsets;

/**
 * Basic implementation of {@link ViewNodeContainerFigureDesc} with a rectangle shape.
 * 
 * @author cbrun
 * 
 */
public class ViewNodeContainerRectangleFigureDesc extends RectangleFigure implements ViewNodeContainerFigureDesc {

    private SiriusWrapLabel fContainerLabelFigure;

    private View view;

    /**
     * Create a new {@link ViewNodeContainerFigureDesc}.
     * 
     * @param view
     *            the model view of the part showing the figure.
     */
    public ViewNodeContainerRectangleFigureDesc(View view) {
        this.view = view;
        this.setFill(false);
        createContents();
        this.setBorder(new MarginBorder(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0));
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

    private void createContents() {
        fContainerLabelFigure = new SiriusWrapLabel() {
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
        fContainerLabelFigure.setText("  "); //$NON-NLS-1$
        fContainerLabelFigure.setTextWrap(true);
        this.add(fContainerLabelFigure);
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     * @see org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc#getLabelFigure()
     */
    @Override
    public SiriusWrapLabel getLabelFigure() {
        return fContainerLabelFigure;
    }
}
