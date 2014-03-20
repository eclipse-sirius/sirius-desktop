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
package org.eclipse.sirius.diagram.ui.tools.api.draw2d.ui.figures;

import java.util.Iterator;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;

/**
 * A set of methods that are useful when manipulating figures on the real
 * coordinates system of the diagram (and not for the visible area like
 * translateToAbsolute or translateToRelative).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class FigureUtilities {
    /**
     * Constructor to prevent instantiation
     */
    private FigureUtilities() {
    }

    /**
     * <p>
     * Returns a Point representing the margins associated to the given figure
     * and its children.
     * </p>
     * <p>
     * Used to correct issues occurring during Node Creation and DnD inside
     * Containers. The bordered node is not concerned by this shift.
     * </p>
     * 
     * @param fig
     *            the figure to consider
     * @param isConcernedBorderedNode
     *            true if the shift concerned bordered node, false otherwise.
     * @param editPart
     *            the current edit part
     * @return a Point representing the margins associated to the given figure
     *         and its children
     */
    public static Point getShiftFromMarginOffset(ResizableCompartmentFigure fig, boolean isConcernedBorderedNode, EditPart editPart) {
        // ignore shift for creation of bordered node.
        if (isConcernedBorderedNode) {
            return new Point(0, 0);
        }
        int leftShift = 0;
        int topShif = 0;
        // DnD and Node Creation in a container add extra x
        // and y values of 5 pixels
        // If the target EditPart is an
        // AbstractDNodeContainerCompartmentEditPart, we consider the shift
        // Margins associated to the figure linked with this editPart
        if (editPart instanceof AbstractDNodeContainerCompartmentEditPart) {

            Iterator<?> childrenFiguresIterator = fig.getChildren().iterator();
            while (childrenFiguresIterator.hasNext()) {
                Object next = childrenFiguresIterator.next();

                if (next instanceof IFigure) {
                    IFigure childrenFigure = (IFigure) next;
                    Border border = childrenFigure.getBorder();
                    if (border != null) {
                        Insets insets = border.getInsets(childrenFigure);
                        if (insets != null) {
                            leftShift += insets.left;
                            topShif += insets.top;
                        }
                    }
                }
            }
        }
        return new Point(leftShift, topShif);
    }
}
