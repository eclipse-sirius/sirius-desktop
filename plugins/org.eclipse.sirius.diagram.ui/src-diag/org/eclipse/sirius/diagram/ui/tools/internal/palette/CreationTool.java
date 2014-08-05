/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
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
        if (!isInState(STATE_DRAG_IN_PROGRESS)) {
            if (!getCurrentInput().isAltKeyDown()) {
                if (getTargetEditPart() != null) {
                    SnapToHelper helper = (SnapToHelper) getTargetEditPart().getAdapter(SnapToHelper.class);
                    if (helper != null) {
                        PrecisionPoint preciseLocation = new PrecisionPoint(getLocation());
                        PrecisionPoint result = new PrecisionPoint(getLocation());
                        CreateRequest req = getCreateRequest();
                        helper.snapPoint(req, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, preciseLocation, result);
                        req.setLocation(result.getCopy());
                    }
                }
            }
        }
    }
}
