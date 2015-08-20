/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;

/**
 * A manager to handle collapsed border nodes.
 * 
 * @author Florian Barbin
 *
 */
public class BorderNodeCollapseManager {

    /**
     * Keep the collapsed node bounds to restore them when the drag action is
     * over. Only used in the scenario where we drag a collapsed node.
     */
    private Rectangle collapsedRectangle;

    /**
     * We expand the given rect bounds to avoid conflicts. Old bounds are saved
     * in collapsedRectangle attribute to be able to restore them once the drag
     * and drop is over.
     * 
     * @param hostEditPart
     *            the collapsed figure edit part. The new expands constraints
     *            will be apply to the figure until the restoreCollapsedNode
     *            method is called.
     * @param rect
     *            the bounds to expand.
     * @return the expanded bounds.
     */
    public Rectangle expandCollapsedNodeBounds(IBorderItemEditPart hostEditPart, final PrecisionRectangle rect) {
        if (isCollapsed(hostEditPart)) {
            IBorderItemLocator borderItemLocator = hostEditPart.getBorderItemLocator();
            if (borderItemLocator instanceof DBorderItemLocator) {
                if (collapsedRectangle == null) {
                    collapsedRectangle = ((DBorderItemLocator) borderItemLocator).getCurrentConstraint();
                }
                EditPart parentEditPart = hostEditPart.getParent();
                Rectangle parentBounds = null;
                if (parentEditPart instanceof IGraphicalEditPart) {
                    IFigure parentFigure = ((IGraphicalEditPart) parentEditPart).getFigure();
                    parentBounds = parentFigure.getBounds().getCopy();
                    if (parentFigure instanceof NodeFigure) {
                        parentBounds = ((NodeFigure) parentFigure).getHandleBounds().getCopy();
                    }

                    Dimension initialDim = getInitialDimension(hostEditPart);
                    Rectangle newBoundsAbsolute = PortLayoutHelper.getUncollapseCandidateLocation(initialDim, rect, parentBounds);

                    Rectangle newBoundsFromFigure = PortLayoutHelper.getUncollapseCandidateLocation(initialDim, collapsedRectangle, null);

                    borderItemLocator.setConstraint(newBoundsFromFigure);
                    ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);

                    return newBoundsAbsolute;
                }
            }
        }
        return null;

    }

    /**
     * Returns true if the given edit part node is collapsed.
     * 
     * @param editPart
     *            the edit part.
     * @return true if collapsed, otherwise false.
     */
    public boolean isCollapsed(IGraphicalEditPart editPart) {
        EObject element = editPart.resolveSemanticElement();
        if (element instanceof DDiagramElement) {
            DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement) element);
            return query.isIndirectlyCollapsed();
        }
        return false;
    }

    /**
     * Restores the expanded node original collapsed state.
     * 
     * @param borderItemEP
     *            the border node edit part.
     */
    public void restoreCollapsedNode(IBorderItemEditPart borderItemEP) {
        IBorderItemLocator borderItemLocator = borderItemEP.getBorderItemLocator();
        if (borderItemLocator instanceof DBorderItemLocator) {
            borderItemLocator.setConstraint(collapsedRectangle.getCopy());
            ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
        }
    }

    /**
     * Provides the initial dimension of the given edit part figure before
     * collapsing.
     * 
     * @param editPart
     *            the Edit Part.
     * @return the expanded dimension.
     */
    public Dimension getInitialDimension(IGraphicalEditPart editPart) {
        Object node = editPart.getModel();
        if (node instanceof Node) {
            NodeQuery query = new NodeQuery((Node) node);
            return query.getOriginalDimensionBeforeCollapse();
        }
        return new Dimension(0, 0);
    }

    /**
     * Disposes the current manager.
     */
    public void dispose() {
        collapsedRectangle = null;

    }

    /**
     * Provides the saved collapsed bounds. Can be null.
     * 
     * @return the rectangle if the bounds have been saved, otherwise null.
     */
    public Rectangle getCollapsedRectangle() {
        return collapsedRectangle;
    }

    /**
     * Returns whether the collapsed border node has been expanded or not.
     * 
     * @return true if it has been expanded, otherwise false.
     */
    public boolean hasBeenExpanded() {
        return collapsedRectangle != null;
    }
}
