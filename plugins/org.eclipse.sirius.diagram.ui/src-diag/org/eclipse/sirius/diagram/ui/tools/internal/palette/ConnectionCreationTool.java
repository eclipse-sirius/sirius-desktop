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
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;

/**
 * ConnectionCreationTool so that the current tool will remain active (locked)
 * if the user is pressing the ctrl key.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ConnectionCreationTool extends org.eclipse.gef.tools.ConnectionCreationTool {

    /**
     * Default Constructor.
     */
    public ConnectionCreationTool() {
        super();
        setUnloadWhenFinished(true);
    }

    /**
     * Constructs a new ConnectionCreationTool with the given factory.
     * 
     * @param factory
     *            the creation factory
     */
    public ConnectionCreationTool(final CreationFactory factory) {
        super(factory);
        setUnloadWhenFinished(true);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.tools.AbstractConnectionCreationTool#handleMove()
     */
    @Override
    protected boolean handleMove() {
        final boolean bool = super.handleMove();
        if (isInState(STATE_CONNECTION_STARTED)) {
            // Expose the diagram as the user scrolls in the area handled by the
            // autoexpose helper.
            updateAutoexposeHelper();
        }
        return bool;
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
     * Overridden to allow feedback with disabled cursor on connection start &
     * end.
     * 
     * {@inheritDoc}
     */
    @Override
    protected Cursor calculateCursor() {
        Command command = getCurrentCommand();
        if (command == null || !command.canExecute()) {
            return getDisabledCursor();
        }
        return super.calculateCursor();
    }

    /**
     * Overridden so that the snap to grid or snap to geometry is considered for
     * the creation.
     */
    @Override
    protected void updateTargetRequest() {
        super.updateTargetRequest();
        if (!getCurrentInput().isAltKeyDown()) {
            if (getTargetEditPart() != null) {
                SnapToHelper helper = (SnapToHelper) getTargetEditPart().getAdapter(SnapToHelper.class);
                if (helper != null) {
                    CreateConnectionRequest req = (CreateConnectionRequest) getTargetRequest();
                    PrecisionPoint preciseLocation = new PrecisionPoint(getLocation());
                    PrecisionPoint result = new PrecisionPoint(getLocation());
                    helper.snapPoint(req, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, preciseLocation, result);
                    req.setLocation(result.getCopy());
                }
            }
        }
    }
}
