/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;

/**
 * The figure used for DNodeEditPart and its variants.
 * 
 * @author pcdavid
 */
public class ViewNodeFigure extends RectangleFigure {
    private final SiriusWrapLabel nodeLabel = new SiriusWrapLabel();

    /**
     * Create a new figure.
     */
    public ViewNodeFigure() {
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

    /**
     * Return the figure for this node's label.
     * 
     * @return the figure for this node's label.
     */
    public SiriusWrapLabel getNodeLabel() {
        return this.nodeLabel;
    }
}
