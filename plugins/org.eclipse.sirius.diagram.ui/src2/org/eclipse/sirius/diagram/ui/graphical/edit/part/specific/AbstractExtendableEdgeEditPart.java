/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.part.specific;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.AbstractNodeDecorateSemanticElementOrdering;

/**
 * A useful class to add some magic elements on a connection. The connection is
 * straight and doesn't contains bendpoints.
 * 
 * 
 * @author ymortier
 */
public abstract class AbstractExtendableEdgeEditPart extends DEdgeEditPart {

    /** The default router. */
    private static final ConnectionRouter ROUTER = new BendpointConnectionRouter();

    /**
     * Create a new {@link AbstractExtendableEdgeEditPart}.
     * 
     * @param view
     *            the view.
     */
    public AbstractExtendableEdgeEditPart(final View view) {
        super(view);
    }

    /**
     * Return all objects to show on the edge.
     * 
     * @return all objects to show on the edge.
     */
    public abstract List<EObject> getObjectsToShow();

    /**
     * Return the ordering.
     * 
     * @return the ordering.
     */
    public abstract AbstractNodeDecorateSemanticElementOrdering getElementOrdering();

    /**
     * Return the semantic object of this element.
     * 
     * @return the semantic object of this element.
     */
    public EObject getSemanticObject() {
        final EObject element = this.resolveSemanticElement();
        if (element instanceof DEdge) {
            return ((DEdge) element).getTarget();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.internal.edit.parts.DEdgeEditPart#installRouter()
     */
    @Override
    protected void installRouter() {
        super.installRouter();
        if (this.resolveSemanticElement() instanceof DEdge) {
            this.getPrimaryShape().setConnectionRouter(ROUTER);
        }
    }

    /**
     * The figure.
     * 
     * @author ymortier
     */
    public class ExtendableEdgeFigure extends ViewEdgeFigure {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.internal.edit.parts.ViewEdgeEditPart.ViewEdgeFigure#paintFigure(org.eclipse.draw2d.Graphics)
         */
        @Override
        public void paintFigure(final Graphics graphics) {
            final EObject element = resolveSemanticElement();
            if (element != null && DEdge.class.isInstance(element)) {
                super.paintFigure(graphics);
                //
                // Paint the elements.
                //
                //
                // copy the list to avoid modifications.
                final List<EObject> objects = new ArrayList<EObject>(AbstractExtendableEdgeEditPart.this.getObjectsToShow());
                //
                // order the list.
                final AbstractNodeDecorateSemanticElementOrdering ordering = AbstractExtendableEdgeEditPart.this.getElementOrdering();
                if (ordering != null) {
                    Collections.sort(objects, new Comparator<EObject>() {
                        public int compare(final EObject arg0, final EObject arg1) {
                            return ordering.compare(arg0, arg1);
                        }
                    });
                }
                //
                // paint components.
                //
                // Size of the edge.
                final Point firstPoint = this.getStart().x < this.getEnd().x ? this.getStart() : this.getEnd();
                final Point lastPoint = this.getStart().x < this.getEnd().x ? this.getEnd() : this.getStart();
                final int lengthX = lastPoint.x - firstPoint.x;
                final int stepX = lengthX / (objects.size() + 1);
                final int lengthY = lastPoint.y - firstPoint.y;
                final int stepY = lengthY / (objects.size() + 1);

                for (int i = 0; i < objects.size(); i++) {
                    final EObject edgeElement = objects.get(i);
                    final int currentStepX = stepX * (i + 1);
                    final int currentX = firstPoint.x + currentStepX;
                    final int currentStepY = stepY * (i + 1);
                    final int currentY = firstPoint.y + currentStepY;
                    AbstractExtendableEdgeEditPart.this.paintElement(graphics, edgeElement, new Point(currentX, currentY));
                }
            }
        }
    }

    /**
     * Paints the specified element.
     * 
     * @param g
     *            the graphics.
     * @param element
     *            the element to paint.
     * @param position
     *            the position on the edge.
     */
    public abstract void paintElement(Graphics g, EObject element, Point position);

}
