/*******************************************************************************
 * Copyright (c) 2009, 2020 Kiel University and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * Request for automatic layout on a set of edit parts of a diagram.
 * 
 * Copied from org.eclipse.elk.conn.gmf.ApplyLayoutRequest of commit
 * 53a98c9c35bc38b6b7513e0e73fd9d688c34937f.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 * 
 */
public class ApplyLayoutRequest extends Request {

    /** the request type used to apply layout. */
    public static final String REQ_APPLY_LAYOUT = "apply layout";

    /** list of layout graph elements and the corresponding edit parts. */
    private List<Pair<ElkGraphElement, IGraphicalEditPart>> mappingList = new LinkedList<>();

    /** the upper bound for horizontal coordinates. */
    private double boundx = Float.MAX_VALUE;

    /** the upper bound for vertical coordinates. */
    private double boundy = Float.MAX_VALUE;

    /** the scale factor for all coordinates. */
    private double scale = 1.0f;

    /**
     * Creates a request to apply layout.
     */
    public ApplyLayoutRequest() {
        super(REQ_APPLY_LAYOUT);
    }

    /**
     * Adds the given graph element and edit part to the request, if it has been
     * modified by a layout algorithm.
     * 
     * @param element
     *            graph element with layout data
     * @param editPart
     *            the corresponding edit part
     */
    public void addElement(final ElkGraphElement element, final IGraphicalEditPart editPart) {
        // Back in the old days (Pepperidge Farm remembers...), an element was
        // added to the mappingList only if
        // its layout information had been modified. For the moment, we have
        // removed the modified flag from graph
        // elements. If they return, this would be a place to check for them.
        mappingList.add(new Pair<ElkGraphElement, IGraphicalEditPart>(element, editPart));
    }

    /**
     * Returns a list of the graph elements and edit parts of this request.
     * 
     * @return a list with graph elements and corresponding edit parts
     */
    public List<Pair<ElkGraphElement, IGraphicalEditPart>> getElements() {
        return mappingList;
    }

    /**
     * Set an upper bound on the coordinates of the layout graph.
     * 
     * @param x
     *            the upper bound for horizontal coordinates
     * @param y
     *            the upper bound for vertical coordinates
     */
    public void setUpperBound(final double x, final double y) {
        this.boundx = x;
        this.boundy = y;
    }

    /**
     * Returns the upper bound for horizontal coordinates.
     * 
     * @return the x bound
     */
    public double getXBound() {
        return boundx;
    }

    /**
     * Returns the upper bound for vertical coordinates.
     * 
     * @return the y bound
     */
    public double getYBound() {
        return boundy;
    }

    /**
     * Returns the factor to use for scaling coordinates.
     * 
     * @return the scale factor
     */
    public double getScale() {
        return scale;
    }

    /**
     * Sets the factor to use for scaling coordinates.
     * 
     * @param thescale
     *            the scale factor
     */
    public void setScale(final double thescale) {
        this.scale = thescale;
    }

}
