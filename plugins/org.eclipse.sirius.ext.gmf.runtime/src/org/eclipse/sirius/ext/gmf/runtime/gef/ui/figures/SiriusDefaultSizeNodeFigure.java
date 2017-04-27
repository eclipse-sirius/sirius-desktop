/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

/**
 * This class in only here to workaround a bug in {@link org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure}, bugzilla
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=519250. This is not the class NodeFigure that is overriden, because in
 * Sirius, only SiriusDefaultSizeNodeFigure is used.<BR>
 * The NodeFigure figure uses getBounds() of figure to compute anchor from point. But later, when the point is computed
 * from anchor, the getBox() is used (and so getHandleBounds() instead of getBounds()).
 * 
 * Compute anchor from point (line 174 of DefaultSizeNodeFigure, getBounds() is used):
 * <UL>
 * <LI>BaseSlidableAnchor.getAnchorRelativeLocation(Point, Rectangle) line: 302</LI>
 * <LI>DefaultSizeNodeFigure(NodeFigure).createConnectionAnchor(Point) line: 174</LI>
 * <LI>DefaultSizeNodeFigure(NodeFigure).getTargetConnectionAnchorAt(Point) line: 157</LI>
 * </UL>
 * 
 * Compute point from anchor (line 67 of SlidableAnchor, getHandleBounds() is used):
 * <UL>
 * <LI>SlidableAnchor.getBox() line: 67</LI>
 * <LI>BaseSlidableAnchor.getAnchorPosition() line: 147</LI>
 * </UL>
 * 
 * 
 * @author lredor
 */
public class SiriusDefaultSizeNodeFigure extends DefaultSizeNodeFigure {

    /**
     * Constructor.
     * 
     * @param defSize
     *            a <code>Dimension</code> that is used to initialize the default size
     */
    public SiriusDefaultSizeNodeFigure(Dimension defSize) {
        super(defSize);
    }

    /**
     * Constructor.
     * 
     * @param width
     *            the initial width to initialize the default size with
     * @param height
     *            the initial height to initialize the default size with
     */
    public SiriusDefaultSizeNodeFigure(int width, int height) {
        super(width, height);
    }

    @Override
    protected ConnectionAnchor createConnectionAnchor(Point p) {
        ConnectionAnchor result;
        if (p == null) {
            result = getConnectionAnchor(szAnchor);
        } else {
            Point temp = p.getCopy();
            translateToRelative(temp);
            // getHandleBounds() is used here instead of getBounds().
            PrecisionPoint pt = BaseSlidableAnchor.getAnchorRelativeLocation(temp, getHandleBounds());
            if (isDefaultAnchorArea(pt)) {
                result = getConnectionAnchor(szAnchor);
            } else {
                result = createAnchor(pt);
            }
        }
        return result;
    }
}
