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
package org.eclipse.sirius.diagram.ui.tools.internal.ruler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToGeometryEx;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractBorderedDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SnapChangeBoundsRequest;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SnapBendpointRequest;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.ext.gef.query.EditPartQuery;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Lists;

/**
 * Overridden to support all visible shapes in SnapToShape and not only brothers
 * ones. See {@link NoCopyDragEditPartsTrackerEx#SNAP_TO_ALL_SHAPE_KEY}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusSnapToGeometry extends SnapToGeometryEx {

    boolean snapToAll;

    /**
     * A vertical or horizontal snapping point.<BR>
     * Only overridden to have access to constructor.
     */
    class SiriusEntry extends Entry {
        protected SiriusEntry(int type, int location) {
            super(type, location);
        }
    }

    /**
     * Default constructor.
     * 
     * @param container
     *            the container editpart
     */
    public SiriusSnapToGeometry(GraphicalEditPart container) {
        super(container);
    }

    @Override
    public int snapRectangle(Request request, int snapOrientation, PrecisionRectangle baseRect, PrecisionRectangle result) {
        // Get snapToAll mode from request (set by the
        // SiriusDragEditPartsTrackerEx or by the SiriusResizeTracker)
        Object snapToAllExtendedData = request.getExtendedData().get(NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL_SHAPE_KEY);
        boolean oldSnapToAll = snapToAll;
        snapToAll = (snapToAllExtendedData == null && NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE) || (snapToAllExtendedData != null && ((Boolean) snapToAllExtendedData).booleanValue());
        if (!snapToAll) {
            if (request instanceof SnapChangeBoundsRequest) {
                snapToAll = ((SnapChangeBoundsRequest) request).isSnapToAllShape();
            } else if (request instanceof SnapBendpointRequest) {
                snapToAll = ((SnapBendpointRequest) request).isSnapToAllShape();
            }
        }

        if (oldSnapToAll != snapToAll) {
            // Reset previous computed horizontal rows and vertical column
            // being snapped to.
            rows = null;
            cols = null;
        }

        return super.snapRectangle(request, snapOrientation, baseRect, result);
    }

    @Override
    protected List generateSnapPartsList(List exclusions) {
        if (!snapToAll) {
            return super.generateSnapPartsList(exclusions);
        } else {
            // Get all potential snap targets
            List<Class<?>> expectedClasses = Lists.newArrayList();
            expectedClasses.add(AbstractBorderedDiagramElementEditPart.class);
            expectedClasses.add(AbstractDiagramBorderNodeEditPart.class);
            List<EditPart> snapPartsList = new ArrayList<EditPart>(new EditPartQuery(container.getRoot()).getAllChildren(false, expectedClasses));
            // Add children of elements that are being dragged
            List<EditPart> exclusionsWithChildren = new ArrayList<EditPart>();
            for (Object editPart : exclusions) {
                if (editPart instanceof EditPart) {
                    exclusionsWithChildren.add((EditPart) editPart);
                    exclusionsWithChildren.addAll(new EditPartQuery((EditPart) editPart).getAllChildren(false, expectedClasses));
                }
            }
            // Don't snap to any figure that is being dragged
            snapPartsList.removeAll(exclusionsWithChildren);

            // Don't snap to hidden figures (not visible for end-user)
            for (Iterator<EditPart> iter = snapPartsList.iterator(); iter.hasNext(); /* */) {
                EditPart snapPart = iter.next();
                if (snapPart instanceof IGraphicalEditPart && !new org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery((IGraphicalEditPart) snapPart).isVisibleOnViewport()) {
                    iter.remove();
                }
            }

            return snapPartsList;
        }

    }

    @Override
    protected void populateRowsAndCols(List parts) {
        if (!snapToAll) {
            super.populateRowsAndCols(parts);
        } else {
            rows = new Entry[parts.size() * 3];
            cols = new Entry[parts.size() * 3];
            for (int i = 0; i < parts.size(); i++) {
                IGraphicalEditPart child = (IGraphicalEditPart) parts.get(i);
                Rectangle bounds = GraphicalHelper.getAbsoluteBounds(child);
                makeRelative(container.getContentPane(), bounds);
                cols[i * 3] = new SiriusEntry(-1, bounds.x);
                rows[i * 3] = new SiriusEntry(-1, bounds.y);
                cols[i * 3 + 1] = new SiriusEntry(0, bounds.x + (bounds.width - 1) / 2);
                rows[i * 3 + 1] = new SiriusEntry(0, bounds.y + (bounds.height - 1) / 2);
                cols[i * 3 + 2] = new SiriusEntry(1, bounds.right() - 1);
                rows[i * 3 + 2] = new SiriusEntry(1, bounds.bottom() - 1);
            }
        }
    }
}
