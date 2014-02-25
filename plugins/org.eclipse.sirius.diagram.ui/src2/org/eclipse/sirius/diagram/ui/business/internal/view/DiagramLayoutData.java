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

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;

/**
 * A DiagramLayoutData is the <i>root</i> of an LayoutDataHint.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DiagramLayoutData extends RootLayoutData {

    // /**
    // * This constructor is used when you want to store the layout data of the
    // * DDiagram of editPart and all its children.
    // *
    // * @param editPart .
    // * @param location .
    // */
    // public DiagramLayoutData(final ShapeEditPart editPart, final Point
    // location) {
    // super(editPart, location);
    // }

    /**
     * This constructor is used when you want to store the layout data of only
     * one element which the parent is the DDiagram of editPart.
     * 
     * @param editPart
     *            .
     * @param location
     *            .
     * @param size
     *            the future size
     */
    public DiagramLayoutData(final EditPart editPart, final Point location, final Dimension size) {
        super(editPart, location, size);
    }

    /**
     * Initialize this object (with a new location) and all its children.
     * 
     * @param nodeTarget
     *            The node to deal with
     * @param gmfNode
     *            the corresponding GMF node
     * @param futureLocation
     *            The future location of the node (or null if the editPart
     *            location is OK)
     */
    @Override
    protected void init(final AbstractDNode nodeTarget, final Node gmfNode, final Point futureLocation) {
        super.init(nodeTarget, gmfNode);
        if (futureLocation != null) {
            // Override the location of the nodeTarget
            setLocation(futureLocation);
        }
    }

    /**
     * Initialize this object (with a new location and size). The children of
     * this object is not added because the
     * 
     * @param parentNodeTarget
     *            The parent of the node to deal with
     * @param parentGmfNode
     *            the corresponding GMF node
     * @param futureLocation
     *            The future location of the node (or null if the editPart
     *            location is OK)
     * @param futureSize
     *            The future size of the node (or null if the editPart size is
     *            OK)
     */
    protected void init(final AbstractDNode parentNodeTarget, final Node parentGmfNode, final Point futureLocation, final Dimension futureSize) {
        setTarget(parentNodeTarget);
        final LayoutConstraint constaint = parentGmfNode.getLayoutConstraint();
        if (futureSize != null) {
            setSize(futureSize);
        } else if (constaint instanceof Size) {
            setSize(new Dimension(((Size) constaint).getWidth(), ((Size) constaint).getHeight()));
        }
        if (futureLocation != null) {
            // Take this location as a priority value if it's not null
            setLocation(futureLocation);
        } else if (constaint instanceof Location) {
            setLocation(new Point(((Location) constaint).getX(), ((Location) constaint).getY()));
        }
        setChildren(new ArrayList<LayoutData>());
    }

    /**
     * Search recursively in in all the DiagramLayoutData is there is one which
     * have the diagram for target.
     * 
     * @param diagram
     *            The search diagram
     * @param ignoreConsumeState
     *            true to ignore the consume state and to authorize to consume
     *            an already consumed data, false otherwise
     * @return the corresponding LayoutData or null if not found.
     */
    @Override
    public LayoutData getData(final DDiagram diagram, boolean ignoreConsumeState) {
        final LayoutData result = (ignoreConsumeState || !isConsume()) && getTarget().equals(diagram) ? this : null;
        return result;
    }
}
