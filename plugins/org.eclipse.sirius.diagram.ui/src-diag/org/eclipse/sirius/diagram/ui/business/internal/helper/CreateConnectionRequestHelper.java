/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.helper;

import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.EdgeCreationDescriptionQuery;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.tools.internal.command.builders.EdgeCreationCommandBuilder;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * A Helper to compute some information from {@link CreateConnectionRequest}.
 * 
 * @author fbarbin
 *
 */
public final class CreateConnectionRequestHelper {

    private CreateConnectionRequestHelper() {

    }

    /**
     * Compute the request extended data according to the target edit part.
     * 
     * @param request
     *            the request
     * @param connectableEditPart
     *            the target edit part.
     * @return true if the request is applicable, false otherwise.
     */
    public static boolean computeConnectionStartExtendedData(CreateConnectionRequest request, INodeEditPart connectableEditPart) {

        Map<Object, Object> extendedData = request.getExtendedData();

        ConnectionAnchor sourceAnchor = connectableEditPart.getSourceConnectionAnchor(request);
        String newSourceTerminal = connectableEditPart.mapConnectionAnchorToTerminal(sourceAnchor);

        Point sourceLocation = getConvertedLocation(request.getLocation().getCopy(), connectableEditPart, false);

        extendedData.put(SiriusGraphicalNodeEditPolicy.GMF_EDGE_LOCATION_SOURCE, sourceLocation.getCopy());
        extendedData.put(SiriusGraphicalNodeEditPolicy.GMF_EDGE_SOURCE_TERMINAL, newSourceTerminal);

        org.eclipse.gef.GraphicalEditPart graphicalEditPart = connectableEditPart;
        DSemanticDecorator decorateSemanticElement = null;
        if (graphicalEditPart.getModel() instanceof View) {
            View view = (View) graphicalEditPart.getModel();
            if (view.getElement() instanceof DSemanticDecorator) {
                decorateSemanticElement = (DSemanticDecorator) view.getElement();
            }
        }
        if (decorateSemanticElement instanceof EdgeTarget && request.getNewObject() instanceof AbstractToolDescription) {
            AbstractToolDescription abstractToolDescription = (AbstractToolDescription) request.getNewObject();
            if (abstractToolDescription instanceof EdgeCreationDescription) {
                EdgeCreationDescription edgeCreationDescription = (EdgeCreationDescription) abstractToolDescription;

                boolean canCreate = new EdgeCreationDescriptionQuery(edgeCreationDescription).isValidAsSourceElement((DMappingBased) decorateSemanticElement);
                canCreate = canCreate && new EdgeCreationCommandBuilder(edgeCreationDescription, (EdgeTarget) decorateSemanticElement, null).checkStartPrecondition();
                if (canCreate) {
                    extendedData.put(SiriusGraphicalNodeEditPolicy.GMF_EDGE_CREATION_DESCRIPTION, edgeCreationDescription);
                    extendedData.put(SiriusGraphicalNodeEditPolicy.GMF_EDGE_TARGET_SOURCE, decorateSemanticElement);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Convert a location to a location relative to its parent ( <code>referencePart</code>).
     * 
     * @param pointToConvert
     *            The point to convert
     * @param referencePart
     *            The reference edit part.
     * @param feedbackCoordinates
     *            true if the pointToConvert is from feedback, false otherwise (coordinates from request). The
     *            coordinates from feedback must be first adapted to remove diagram scrollbar to retrieve same
     *            coordinates as from request.
     * @return The converted point.
     */
    private static Point getConvertedLocation(Point pointToConvert, EditPart referencePart, boolean feedbackCoordinates) {
        Point realLocation;
        if (pointToConvert != null && referencePart instanceof GraphicalEditPart) {
            final IFigure fig = ((GraphicalEditPart) referencePart).getFigure();
            if (feedbackCoordinates) {
                // Remove diagram scrollbar
                pointToConvert.translate(GraphicalHelper.getScrollSize((GraphicalEditPart) referencePart).negate());
            }
            fig.translateToRelative(pointToConvert);
            final Point containerLocation = fig.getBounds().getLocation();
            realLocation = new Point(pointToConvert.x - containerLocation.x, pointToConvert.y - containerLocation.y);
            if (fig instanceof ResizableCompartmentFigure) {
                final Point scrollOffset = ((ResizableCompartmentFigure) fig).getScrollPane().getViewport().getViewLocation();
                realLocation = new Point(realLocation.x + scrollOffset.x, realLocation.y + scrollOffset.y);
            }

        } else {
            realLocation = pointToConvert;
        }
        return realLocation;
    }

}
