/*******************************************************************************
 * Copyright (c) 2009, 2026 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.swt.SWT;

/**
 * CreationTool so that the current tool will remain active (locked) if the user
 * is pressing the ctrl key.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class CreationTool extends org.eclipse.gef.tools.CreationTool {

    /**
     * Default constructor. Sets the default and disabled cursors.
     */
    public CreationTool() {
        super();
    }

    /**
     * Constructs a new CreationTool with the given factory.
     * 
     * @param aFactory
     *            the creation factory
     */
    public CreationTool(final CreationFactory aFactory) {
        super(aFactory);
    }

    /**
     * Overridden to have public acces to this method.
     * 
     * {@inheritDoc}
     */
    @Override
    public Request createTargetRequest() {
        return super.createTargetRequest();
    }

    /**
     * Overridden so that the current tool will remain active (locked) if the
     * user is pressing the ctrl key (or cmd key for mac users).
     */
    @Override
    protected void handleFinished() {
        if (!getCurrentInput().isModKeyDown(SWT.MOD1)) {
            super.handleFinished();
        } else {
            reactivate();
        }
    }

    /**
     * Overridden so that the snap to grid or snap to geometry is considered for
     * the creation.
     */
    @Override
    protected void updateTargetRequest() {
        super.updateTargetRequest();
        CreateRequest req = getTargetRequest();
        req.getExtendedData().remove(RequestConstants.CREATION_RAW_LOCATION);
        req.getExtendedData().remove(RequestConstants.CREATION_SNAPPED_LOCATION);
        if (!isInState(STATE_DRAG_IN_PROGRESS)) {
            if (!getCurrentInput().isAltKeyDown()) {
                if (getTargetEditPart() != null) {
                    SnapToHelper helper = getTargetEditPart().getAdapter(SnapToHelper.class);
                    if (helper != null) {
                        Point rawLocation = getLocation().getCopy();
                        PrecisionPoint preciseLocation = new PrecisionPoint(rawLocation);
                        PrecisionPoint resultSnappedLocation = new PrecisionPoint(rawLocation);
                        helper.snapPoint(req, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, preciseLocation, resultSnappedLocation);
                        req.getExtendedData().put(RequestConstants.CREATION_RAW_LOCATION, rawLocation);
                        req.getExtendedData().put(RequestConstants.CREATION_SNAPPED_LOCATION, resultSnappedLocation.getCopy());
                        req.setLocation(resultSnappedLocation);
                    }
                }
            }
        }
    }
}
