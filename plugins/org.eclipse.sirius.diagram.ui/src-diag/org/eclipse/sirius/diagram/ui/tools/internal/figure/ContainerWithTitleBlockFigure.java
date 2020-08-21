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
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LabelBorderStyleIds;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.swt.graphics.Color;

import com.google.common.collect.Iterables;

/**
 * A figure that draw a line around the container label to represent a title block.
 * 
 * @author smonnier
 */
public class ContainerWithTitleBlockFigure extends SiriusDefaultSizeNodeFigure {

    private DStylizable viewNode;

    private LabelBorderStyleDescription labelBorderStyleDescription;

    /**
     * Create a new {@link ContainerWithTitleBlockFigure}.
     * 
     * @param width
     *            the width.
     * @param height
     *            the height.
     * @param viewNode
     *            the node.
     * @param labelBorderStyleDescription
     *            the LabelBorderStyleDescription.
     */
    public ContainerWithTitleBlockFigure(int width, int height, DStylizable viewNode, LabelBorderStyleDescription labelBorderStyleDescription) {
        super(width, height);
        this.viewNode = viewNode;
        this.labelBorderStyleDescription = labelBorderStyleDescription;
    }

    /**
     * This method is overridden to draw a line over the border node.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void paintBorder(Graphics graphics) {
        super.paintBorder(graphics);
        graphics.setLineStyle(Graphics.LINE_SOLID);
        graphics.setLineWidth(1);
        graphics.setForegroundColor(geBorderColor());

        Iterable<ViewNodeContainerFigureDesc> filter = Iterables.filter(getChildren(), ViewNodeContainerFigureDesc.class);
        if (Iterables.size(filter) == 1) {
            ViewNodeContainerFigureDesc child = filter.iterator().next();
            SiriusWrapLabel containerLabelFigure = child.getLabelFigure();

            // Only LabelBorderStyleWithBeveledCorner is supported.
            if (containerLabelFigure != null && containerLabelFigure.isVisible() && LabelBorderStyleIds.LABEL_BORDER_STYLE_WITH_BEVELED_CORNERS_ID.equals(labelBorderStyleDescription.getId())) {
                Rectangle containerLabelFigureBounds = containerLabelFigure.getBounds();
                paintTitleBlockOnLabel(graphics, containerLabelFigureBounds);
            }
        }
    }

    /**
     * Finds where is located the label in the container.
     * 
     * @param graphics
     *            the Graphics that draw lines
     * @param label
     *            the label to draw a border around
     */
    private void paintTitleBlockOnLabel(Graphics graphics, Rectangle label) {
        PointList pointList = getTitleBlockPointLists(label);
        if (pointList != null && pointList.size() != 0) {
            graphics.drawPolyline(pointList);
        }
    }

    /**
     * Calculate the PointLists corresponding to the specified label alignment.
     * 
     * @param label
     *            the label to draw a border around
     * @return the PointLists corresponding to the west border.
     */
    private PointList getTitleBlockPointLists(Rectangle label) {
        PointList pointList = new PointList();

        LabelAlignment labelAlignment = getLabelAlignment();
        if (LabelAlignment.LEFT.equals(labelAlignment)) {
            pointList.addAll(getSouthSide(label));
            pointList.addAll(getEastSide(label));
        } else if (LabelAlignment.CENTER.equals(labelAlignment)) {
            pointList.addAll(getWestSide(label));
            pointList.addAll(getSouthSide(label));
            pointList.addAll(getEastSide(label));
        } else if (LabelAlignment.RIGHT.equals(labelAlignment)) {
            pointList.addAll(getWestSide(label));
            pointList.addAll(getSouthSide(label));
        }
        return pointList;
    }

    private PointList getWestSide(Rectangle label) {
        int cornerHeight = labelBorderStyleDescription.getCornerHeight();
        int cornerWidth = labelBorderStyleDescription.getCornerWidth();
        PointList pointList = new PointList();
        // Draw west vertical line
        pointList.addPoint(label.x - cornerWidth, label.y - IContainerLabelOffsets.LABEL_OFFSET);
        pointList.addPoint(label.x - cornerWidth, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET - cornerHeight);
        // Draw west corner
        pointList.addPoint(label.x - cornerWidth, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET - cornerHeight);
        pointList.addPoint(label.x, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET);
        return pointList;
    }

    /**
     * Calculate the PointLists corresponding to the south border.
     * 
     * @param label
     *            the label to draw a border around
     * @return the PointLists corresponding to the south border.
     */
    private PointList getSouthSide(Rectangle label) {
        PointList pointList = new PointList();
        // Draw horizontal line
        pointList.addPoint(label.x, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET);
        pointList.addPoint(label.x + label.width, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET);
        return pointList;
    }

    /**
     * Calculate the PointLists corresponding to the east border.
     * 
     * @param label
     *            the label to draw a border around
     * @return the PointLists corresponding to the east border.
     */
    private PointList getEastSide(Rectangle label) {
        int cornerHeight = labelBorderStyleDescription.getCornerHeight();
        int cornerWidth = labelBorderStyleDescription.getCornerWidth();
        PointList pointList = new PointList();
        // Draw east corner
        pointList.addPoint(label.x + label.width, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET);
        pointList.addPoint(label.x + label.width + cornerWidth, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET - cornerHeight);
        // Draw east vertical line
        pointList.addPoint(label.x + label.width + cornerWidth, label.y + label.height + IContainerLabelOffsets.LABEL_OFFSET - cornerHeight);
        pointList.addPoint(label.x + label.width + cornerWidth, label.y - IContainerLabelOffsets.LABEL_OFFSET);
        return pointList;
    }

    /**
     * Investigate the border color of the DDiagramElement to use it as the color of the title block.
     * 
     * @return the defined color of the title block
     */
    private Color geBorderColor() {
        try {
            if (viewNode.getStyle() instanceof BorderedStyle) {
                RGBValues borderColor = ((BorderedStyle) viewNode.getStyle()).getBorderColor();
                if (borderColor != null) {
                    return new Color(null, borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue());
                }
            }
        } catch (IllegalStateException e) {
            if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                // We do not log this exception that might be caused by an unreachable distant resource.
            } else {
                throw e;
            }
        }
        return ColorConstants.black;
    }

    /**
     * Investigate the border size of the DDiagramElement to use it as the width of the title block.
     * 
     * @return the defined width of the title block
     */
    public int getBorderWidth() {
        try {
            if (viewNode.getStyle() instanceof BorderedStyle) {
                // The title block width must value at least 1
                return Math.max(((BorderedStyle) viewNode.getStyle()).getBorderSize(), 1);
            }
        } catch (IllegalStateException e) {
            if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                // We do not log this exception that might be caused by an unreachable distant resource.
            } else {
                throw e;
            }
        }
        return 1;
    }

    /**
     * Investigate the label alignment of the DDiagramElement to use it as the alignment of the title block.
     * 
     * @return the defined label alignment of the title block
     */
    private LabelAlignment getLabelAlignment() {
        LabelAlignment alignment = (LabelAlignment) ViewpointPackage.eINSTANCE.getLabelStyle_LabelAlignment().getDefaultValue();
        try {
            if (viewNode.getStyle() instanceof LabelStyle) {
                alignment = ((LabelStyle) viewNode.getStyle()).getLabelAlignment();
            }
        } catch (IllegalStateException e) {
            if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                // We do not log this exception that might be caused by an unreachable distant resource.
            } else {
                throw e;
            }
        }
        return alignment;
    }

}
