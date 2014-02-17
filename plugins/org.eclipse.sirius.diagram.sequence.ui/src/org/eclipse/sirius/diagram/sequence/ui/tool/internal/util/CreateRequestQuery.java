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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Queries on GEF and GMF requests.
 * 
 * @author pcdavid
 */
public class CreateRequestQuery extends RequestQuery {

    private CreateRequest createRequest;

    private SequenceDiagramEditPart sdep;

    /**
     * Constructor.
     * 
     * @param request
     *            the request to query.
     * @param sdep
     *            the diagram part to get zoom and scroll information.
     */
    public CreateRequestQuery(CreateRequest request, SequenceDiagramEditPart sdep) {
        super(request);
        this.createRequest = request;
        this.sdep = sdep;
    }

    @Override
    protected List<IGraphicalEditPart> getEditParts() {
        return Collections.<IGraphicalEditPart> singletonList(sdep);
    }

    /**
     * Get {@link Rectangle} image of the delta requested by the current
     * {@link CreateRequest}.
     * 
     * {@inheritDoc}
     */
    @Override
    public Rectangle getLogicalDelta() {
        Point location = createRequest.getLocation();
        if (location == null) {
            location = new Point(0, 0);
        }

        Dimension size = createRequest.getSize();
        if (size == null) {
            size = new Dimension(0, 0);
        }
        Rectangle result = new Rectangle(location, size);
        if (sdep != null) {
            GraphicalHelper.screen2logical(result, sdep);
        }

        return result;
    }

}
