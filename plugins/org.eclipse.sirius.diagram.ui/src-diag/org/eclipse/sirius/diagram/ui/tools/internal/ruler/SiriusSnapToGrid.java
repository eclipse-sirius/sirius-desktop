/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.ruler;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToGridEx;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.ui.internal.refresh.NodePositionHelper;

@SuppressWarnings("restriction") // gmf is very (too) restrictive
public class SiriusSnapToGrid extends SnapToGridEx {

    /**
     * Default constructor.
     * 
     * @param container edit part
     */
    public SiriusSnapToGrid(GraphicalEditPart container) {
        super(container);
    }

    @Override
    public int snapRectangle(Request request, int snapLocations, PrecisionRectangle rect, PrecisionRectangle result) {
        Point oldOrigin = origin;

        if (request instanceof ChangeBoundsRequest cbr) {
            // only case for snap to grid
            ajustForFixedSizeNode(cbr);
        }
        try {
            return super.snapRectangle(request, snapLocations, rect, result);
        } finally {
            origin = oldOrigin;
        }
    }

    private void ajustForFixedSizeNode(ChangeBoundsRequest request) {
        if (request.getEditParts().size() == 1 // for single-selection,
                && request.getEditParts().get(0) instanceof ShapeNodeEditPart ep // of nodes
                && ep.getModel() instanceof Node node // with size (for example, list elements have no size)
                && node.getLayoutConstraint() instanceof Size size) {

            // It is easier to shift the origin than to alter the result.
            origin = origin.getCopy().translate(NodePositionHelper.getShiftToCenter(node, size,
                    // In Sirius diagram, gridX == gridY
                    gridX));
        }
    }

}
