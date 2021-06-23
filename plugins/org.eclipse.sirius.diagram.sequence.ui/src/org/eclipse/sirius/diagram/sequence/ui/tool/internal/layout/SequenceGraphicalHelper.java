/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.VerticalPositionFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Utility class to collect helper methods which deal with GraphicalOrdering but which are not part of its API.
 * 
 * @author pcdavid
 */
public final class SequenceGraphicalHelper {
    private SequenceGraphicalHelper() {
        // Prevent instantiation.
    }

    /**
     * Finds and returns the EventEnd which corresponds to the first event graphically above the specified Y coordinate
     * in a diagram.
     * 
     * @param diagram
     *            the diagram.
     * @param y
     *            the Y coordinate (in logical space)
     * @return the EventEnd which corresponds to the first event above Y.
     */
    public static EventEnd getEndBefore(SequenceDDiagram diagram, int y) {
        VerticalPositionFunction vpf = new VerticalPositionFunction(diagram);
        for (EventEnd end : Lists.reverse(diagram.getGraphicalOrdering().getEventEnds())) {
            int pos = vpf.apply(end);
            if (pos != VerticalPositionFunction.INVALID_POSITION && pos <= y) {
                return end;
            }
        }
        return null;
    }

    /**
     * Finds and returns the InstanceRole semantic element which corresponds to the first element graphically above the
     * specified X coordinate in a diagram.
     * 
     * @param diagram
     *            the diagram.
     * @param x
     *            the X coordinate (in logical space)
     * @return the element which corresponds to the first InstanceRole above X.
     */
    public static EObject getInstanceRoleBefore(SequenceDDiagram diagram, int x) {
        Iterable<Diagram> diagramViews = Iterables.filter(ISequenceElementAccessor.getViewsForSemanticElement(diagram, diagram.getTarget()), Diagram.class);
        if (!Iterables.isEmpty(diagramViews)) {
            Option<SequenceDiagram> seqDiag = ISequenceElementAccessor.getSequenceDiagram(diagramViews.iterator().next());
            if (seqDiag.some()) {
                for (InstanceRole ir : Lists.reverse(seqDiag.get().getSortedInstanceRole())) {
                    int pos = ir.getProperLogicalBounds().x;
                    if (pos <= x) {
                        return ir.getSemanticTargetElement().get();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Finds and returns the EventEnd which corresponds to the first event graphically below the specified Y coordinate
     * in a diagram.
     * 
     * @param diagram
     *            the diagram.
     * @param y
     *            the Y coordinate
     * @return the EventEnd which corresponds to the first event below Y.
     */
    public static EventEnd getEndAfter(SequenceDDiagram diagram, int y) {
        VerticalPositionFunction vpf = new VerticalPositionFunction(diagram);
        for (EventEnd end : diagram.getGraphicalOrdering().getEventEnds()) {
            int pos = vpf.apply(end);
            if (pos != VerticalPositionFunction.INVALID_POSITION && pos > y) {
                return end;
            }
        }
        return null;
    }

    /**
     * Returns the normalized Y location of the Center of an edit part, using only information from the GMF model (not
     * Draw2D figures).
     * 
     * @param part
     *            the edit part.
     * @return the absolute, normalized Y location of the Center of the DNodeEditPart.
     */
    public static int getAbsoluteYCenterFromGMFView(IGraphicalEditPart part) {
        if (part instanceof ShapeNodeEditPart) {
            Rectangle absoluteboundsRectangle = SequenceGraphicalHelper.getAbsoluteBoundsFromGMFView(part);
            return absoluteboundsRectangle.getCenter().y;
        }
        // FIXME This uses Draw2D info
        Point location = part.getFigure().getBounds().getCenter().getCopy();
        GraphicalHelper.screen2logical(location, part);
        return location.y;
    }

    /**
     * Returns the normalized Y location of the Center of an edit part, using only information from the GMF model (not
     * Draw2D figures).
     * 
     * Use information from the GMF Notation model if available (which is most of the time), and fall back to the Draw2D
     * Figure's bounds otherwise. The figure's bounds is always available, but depending on the time of the call it may
     * be out of date (pending a revalidate/repaint).
     * 
     * @param part
     *            the edit part
     * 
     * @return the absolute normalized bounds of the ISequenceEvent edit part
     */
    public static Rectangle getAbsoluteBoundsFromGMFView(IGraphicalEditPart part) {
        Rectangle rect;

        /*
         * Initialize with Draw2D information. May be out of date under some conditions.
         */
        rect = part.getFigure().getBounds().getCopy();
        if (part instanceof LifelineEditPart) {
            SequenceGraphicalHelper.handleLifelineEditPartOffset(rect);
        }

        /*
         * Handle Node case with Notation information
         */
        if (part.getNotationView() instanceof Node) {
            Node node = (Node) part.getNotationView();
            Point absLoc = GMFNotationHelper.getAbsoluteLocation(node);

            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                rect.setLocation(absLoc.x, absLoc.y);

                // prevent auto size
                if (bounds.getWidth() != -1) {
                    rect.width = bounds.getWidth();
                }

                // prevent auto size
                if (bounds.getHeight() != -1) {
                    rect.height = bounds.getHeight();
                }

                if (!(part instanceof InstanceRoleEditPart)) {
                    SequenceGraphicalHelper.handleLifelineEditPartOffset(rect);
                }
            }
        }

        return rect;
    }

    private static void handleLifelineEditPartOffset(Rectangle rect) {
        // FIXME LifelineEditPart are placed with offset which did
        // not appear in GMF model
        rect.translate(0, IBorderItemOffsets.DEFAULT_OFFSET.height);
    }

    /**
     * Return the absolute anchor position for the given range and anchor.
     * 
     * @param anchor
     *            the current anchor.
     * @param range
     *            range of the ISequenceEvent which supports the anchor.
     * @return the absolute anchor position.
     */
    public static int getAnchorAbsolutePosition(IdentityAnchor anchor, Range range) {
        PrecisionPoint rel = anchor != null ? BaseSlidableAnchor.parseTerminalString(anchor.getId()) : new PrecisionPoint(0.5, 0.5);
        return range.getLowerBound() + (int) Math.round(rel.preciseY() * range.width());
    }
}
