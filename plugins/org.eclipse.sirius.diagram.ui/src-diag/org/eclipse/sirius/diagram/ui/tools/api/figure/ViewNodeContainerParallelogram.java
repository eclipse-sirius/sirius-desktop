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
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ParallelogramFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;

/**
 * Figure for the parallelogram shape.
 * 
 * @author cbrun
 * 
 */
public class ViewNodeContainerParallelogram extends ParallelogramFigure implements ViewNodeContainerFigureDesc {

    private SiriusWrapLabel fContainerLabelFigure;

    private View view;

    /**
     * Constructor.
     * 
     * @param view
     *            the model view of the part showing the figure.
     */
    public ViewNodeContainerParallelogram(View view) {
        // setLayoutManager(new XYLayout());
        this.view = view;
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
                ShowingViewUtil.initGraphicsForVisibleAndInvisibleElements(this, graphics, view);
                try {
                    super.paint(graphics);
                    graphics.restoreState();
                } finally {
                    graphics.popState();
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
     * @see org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc#getLabelFigure()
     */
    @Override
    public SiriusWrapLabel getLabelFigure() {
        return fContainerLabelFigure;
    }
}
