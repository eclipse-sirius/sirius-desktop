/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.view;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;

/**
 * Store the layout information of a DEdge label at a given time. This class is
 * used only during drag'n'drop.
 * 
 * @author dlecan
 */
public class EdgeLabelLayoutData extends AbstractEdgeLayoutData {

    private Point labelLocation;

    private Rectangle labelBounds;

    /**
     * Constructor.
     * 
     * @param parent
     *            the parent layout data.
     * @param target
     *            The node to deal with
     * @param gmfEdge
     *            the corresponding GMF edge
     */
    public EdgeLabelLayoutData(final LayoutData parent, final DEdge target, final Edge gmfEdge) {
        super(parent, target, gmfEdge);
    }

    /**
     * Initialize this object.
     * 
     * @param gmfEdge
     *            the corresponding GMF edge
     */
    @Override
    protected void init(final Edge gmfEdge) {
        final Node labelNode = SiriusGMFHelper.getLabelNode(gmfEdge);
        if (labelNode != null && labelNode.getLayoutConstraint() instanceof Bounds) {
            final Bounds oldLabelBounds = (Bounds) labelNode.getLayoutConstraint();
            labelLocation = new Point(oldLabelBounds.getX(), oldLabelBounds.getY());
            labelBounds = new Rectangle(oldLabelBounds.getX(), oldLabelBounds.getY(), oldLabelBounds.getWidth(), oldLabelBounds.getHeight());
        } else if (labelNode != null && labelNode.getLayoutConstraint() instanceof Location) {
            final Location oldLabelLocation = (Location) labelNode.getLayoutConstraint();
            labelLocation = new Point(oldLabelLocation.getX(), oldLabelLocation.getY());
        }
    }

    /**
     * Return this EdgeLayoutData if the edge is the target of it.
     * 
     * @param edge
     *            The search element
     * @param ignoreConsumeState
     *            true to ignore the consume state and to authorize to consume
     *            an already consumed data, false otherwise
     * @return the corresponding EdgeLayoutData or null if not found.
     */
    public EdgeLabelLayoutData getData(final DEdge edge, boolean ignoreConsumeState) {
        return (ignoreConsumeState || !isConsume()) && getTarget().equals(edge) ? this : null;
    }

    /**
     * Returns the labelLocation.
     * 
     * @return The labelLocation.
     */
    public Point getLocation() {
        return labelLocation;
    }

    /**
     * Returns the labelBounds.
     * 
     * @return The labelBounds.
     */
    public Rectangle getLabelBounds() {
        return labelBounds;
    }
}
