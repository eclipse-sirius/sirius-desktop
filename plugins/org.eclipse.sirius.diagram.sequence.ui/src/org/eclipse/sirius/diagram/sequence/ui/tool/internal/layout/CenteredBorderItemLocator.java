/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;

/**
 * Specific border item locator to keep the RootExecution always in the middle
 * of the south face of an InstanceRole.
 * 
 * @author smonnier
 * 
 */
public class CenteredBorderItemLocator extends DBorderItemLocator {

    /**
     * Create an {@link CenteredBorderItemLocator} with the specified
     * parentFigure.
     * 
     * @param parentFigure
     *            the parent figure.
     */
    public CenteredBorderItemLocator(final IFigure parentFigure) {
        super(parentFigure);
    }

    /**
     * Create a {@link CenteredBorderItemLocator} with the specified item,
     * parentFigure and constraint.
     * 
     * @param borderItem
     *            the border item.
     * @param parentFigure
     *            the parent figure.
     * @param constraint
     *            the constraint.
     */
    public CenteredBorderItemLocator(final IFigure borderItem, final IFigure parentFigure, final Rectangle constraint) {
        super(borderItem, parentFigure, constraint);
    }

    /**
     * Create a {@link CenteredBorderItemLocator} with the specified item and
     * preferredSide.
     * 
     * @param parentFigure
     *            the parent figure.
     * @param preferredSide
     *            the preferred side.
     */
    public CenteredBorderItemLocator(final IFigure parentFigure, final int preferredSide) {
        super(parentFigure, preferredSide);
    }

    /**
     * Get the preferred location. This method is overridden to set the position
     * of the border item in the middle of the south border.
     * 
     * @param borderItem
     *            the border item
     * @return point
     */
    @Override
    protected Point getPreferredLocation(IFigure borderItem) {
        Point setFigurePosition = setFigurePosition(borderItem, super.getPreferredLocation(borderItem));
        if (setFigurePosition != null) {
            return setFigurePosition;
        }
        return super.getPreferredLocation(borderItem);
    }

    /**
     * Calculate the position of the middle of the south border.
     * 
     * @param borderItem
     *            the border item figure
     * @param borderItemPoint
     *            the super getPreferredLocation result that return the south
     *            west corner.
     * @return the position where to set the root execution bordered node
     */
    private Point setFigurePosition(IFigure borderItem, Point borderItemPoint) {
        Rectangle parentBorder = getParentBorder();
        if (parentBorder != null) {
            Rectangle executionFigureBounds = borderItem.getBounds();
            return new Point(parentBorder.x + parentBorder.width / 2 - executionFigureBounds.width / 2, borderItemPoint.y);
        }
        return null;
    }

    /**
     * This method is overridden to keep the calculated center in
     * setFigurePosition method, even if the bordered node is larger than its
     * parent. As instance, the Destroy End node can be larger that its parent
     * lifeline.
     * 
     * {@inheritDoc}
     * 
     * @param suggestedLocation
     *            suggested location
     * @param suggestedSide
     *            suggested side
     * @param borderItem
     *            the border item.
     * @return point the location point
     */
    @Override
    protected Point locateOnParent(Point suggestedLocation, int suggestedSide, IFigure borderItem) {
        return new Point(suggestedLocation.x, super.locateOnParent(suggestedLocation, suggestedSide, borderItem).y);
    }
}
