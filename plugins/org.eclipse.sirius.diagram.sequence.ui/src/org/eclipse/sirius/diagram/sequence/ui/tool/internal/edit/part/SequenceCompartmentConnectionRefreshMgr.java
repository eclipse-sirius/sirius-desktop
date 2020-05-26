/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DCompartmentConnectionRefreshMgr;

/**
 * We need a custom ConnectionRefreshMgr to handle the special case of operands and combined fragments. The figures of
 * operands are built in a way that they have the exact same size of their container. There is no margin, insets,
 * scrollbar or other artifacts that would make the parent figure bigger than its children. However, by default the
 * Rectangle.contains implementation excludes all points from the right and bottom border (it uses x < (rect.x +
 * rect.width) instead of x <= (rect.x + rect.width)). For this figure we want those points to be considered as
 * belonging to the figure to avoid hiding edges that have a reference point located exactly on those edges. See
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=553321 for specific description.
 * 
 * @author Laurent Redor
 *
 */
public class SequenceCompartmentConnectionRefreshMgr extends DCompartmentConnectionRefreshMgr {

    // CHECKSTYLE:OFF Copy of GMF Code (ShapeCompartmentEditPart)
    /*
     * The following implementation is an exact copy of the super implementation except that the we use a custom method
     * to check if the given point is contains in the bounds
     */
    @Override
    protected boolean isFigureVisible(IFigure figure, Point loc, IFigure stopFigure) {
        if (!(figure.isShowing())) {
            return false;
        } else {
            Rectangle bounds = figure.getBounds().getCopy();
            figure.translateToAbsolute(bounds);
            if (!(customConstains(bounds, loc))) { // Initially if(!bounds.contains(loc))
                return false;
            }
        }

        IFigure parent = figure.getParent();
        while (parent != null && parent != stopFigure) {
            return isFigureVisible(parent, loc, stopFigure);
        }
        return true;
    }
    // CHECKSTYLE:OFF Copy of GMF Code (ShapeCompartmentEditPart)

    private boolean customConstains(Rectangle bounds, Point loc) {
        return loc.y >= bounds.y //
                && loc.y <= bounds.y + bounds.height // Initially loc.y < bounds.y + bounds.height
                && loc.x >= bounds.x //
                && loc.x <= bounds.x + bounds.width; // Initially loc.x < bounds.x + bounds.width
    }
}
