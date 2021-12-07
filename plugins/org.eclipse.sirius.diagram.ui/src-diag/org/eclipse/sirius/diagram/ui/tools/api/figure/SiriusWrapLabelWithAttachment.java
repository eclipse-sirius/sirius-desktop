/*******************************************************************************
 * Copyright (c) 2016, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

/**
 * A specific SiriusWrapLabel for labels of edges that allow to show/hide a label attachment connected between label and
 * edge during selection.
 * 
 * @author Laurent Redor
 */
public class SiriusWrapLabelWithAttachment extends SiriusWrapLabel {

    /**
     * The location of the label (one of
     * {@link org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants.SOURCE_LOCATION} ,
     * {@link org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants.MIDDLE_LOCATION} or
     * {@link org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants.TARGET_LOCATION} ).
     */
    private int location;

    private Polyline attachment;

    /**
     * Construct an empty Label.
     * 
     * @param location
     *            The location of the label (one of
     *            {@link org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants.SOURCE_LOCATION} ,
     *            {@link org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants.MIDDLE_LOCATION} or
     *            {@link org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants.TARGET_LOCATION} ).
     * @param attachment
     *            The corresponding polyline attachment
     */
    public SiriusWrapLabelWithAttachment(int location, Polyline attachment) {
        super();
        this.location = location;
        this.attachment = attachment;
    }

    @Override
    public void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        if (attachment.isVisible()) {
            // Compute the location only if attachment is visible (to avoid
            // unnecessary computation)
            setAttachmentLocation();
        }
    }

    /**
     * Check the value of preference.
     * 
     * @return true if the preference is checked, false otherwise.
     */
    private boolean displayNoteAttachment() {
        return DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiPreferencesKeys.PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION.name());
    }

    /**
     * Set the attachment location (starting and ending points) according to the <code>location</code> of label on the
     * edge and to the <label bounds.
     */
    protected void setAttachmentLocation() {
        PointList ptList = ((Connection) getParent()).getPoints();
        Point referencePointOnEdge = PointListUtilities.calculatePointRelativeToLine(ptList, 0, location, true);
        Rectangle labelBounds = getBounds();

        Point midTop = new Point(labelBounds.x + labelBounds.width / 2, labelBounds.y);
        Point midBottom = new Point(labelBounds.x + labelBounds.width / 2, labelBounds.y + labelBounds.height);
        Point midLeft = new Point(labelBounds.x, labelBounds.y + labelBounds.height / 2);
        Point midRight = new Point(labelBounds.x + labelBounds.width, labelBounds.y + labelBounds.height / 2);

        Point startPoint = midTop;

        int x = labelBounds.x + labelBounds.width / 2 - referencePointOnEdge.x;
        int y = labelBounds.y + labelBounds.height / 2 - referencePointOnEdge.y;

        if (y > 0 && y > x && y > -x) {
            startPoint = midTop;
        } else if (y < 0 && y < x && y < -x) {
            startPoint = midBottom;
        } else if (x < 0 && y > x && y < -x) {
            startPoint = midRight;
        } else {
            startPoint = midLeft;
        }

        if (attachment.getPoints().size() == 0 || !startPoint.equals(attachment.getStart())) {
            attachment.setStart(startPoint);
        }
        if (!referencePointOnEdge.equals(attachment.getEnd())) {
            attachment.setEnd(referencePointOnEdge);
        }
    }

    /**
     * Method to make attachment visible (called on Edge or Label selection).
     */
    public void showAttachment() {
        if (attachment != null && displayNoteAttachment()) {
            attachment.setVisible(true);
        }
    }

    /**
     * Method to make attachment unvisible (called on Edge or Label deselection).
     */
    public void hideAttachment() {
        if (attachment != null) {
            attachment.setVisible(false);
        }
    }

    public int getLocationField() {
        return location;
    }

}
