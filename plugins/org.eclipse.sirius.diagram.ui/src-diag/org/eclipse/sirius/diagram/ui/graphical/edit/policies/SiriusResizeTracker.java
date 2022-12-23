/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

/**
 * Override of {@link ResizeTracker} to allow a resize that also moves all
 * children. The previous behavior (the one of original {@link ResizeTracker})
 * is accessible with the shortcut F3.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusResizeTracker extends ResizeTracker {

    /**
     * Constant passed to extended data of the request to keep the location of
     * the children of resized part fix.
     */
    public static final String CHILDREN_MOVE_MODE_KEY = "keepSameAbsoluteLocation"; //$NON-NLS-1$

    /**
     * The default mode for {@link #childrenMoveMode}.
     */
    public static final boolean DEFAULT_CHILDREN_MOVE_MODE = true;

    /**
     * The key shortcut used to change the default behavior of childrenMoveMode.
     * 'r' as relative : the position of the children would remain relative to
     * the resized container
     */
    protected static final int CHILDREN_MOVE_MODE_SHORTCUT_KEY = SWT.F3;

    /**
     * The mode of the resize tracker:
     * <UL>
     * <LI>true when the children of the resized element should stay at the same
     * absolute location,</LI>
     * <LI>false otherwise.</LI>
     * </UL>
     * This variable is used when the request is updated to add an extended data
     * to the request.
     */
    boolean childrenMoveMode = DEFAULT_CHILDREN_MOVE_MODE;

    /**
     * The mode of this tracker concerning the snap to shape:
     * <UL>
     * <LI>true to snap to all shapes and not only brothers ones,</LI>
     * <LI>false otherwise.</LI>
     * </UL>
     * This variable is used to update the request.
     */
    boolean snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
    
    /**
     * Local copy of the parent's field, which is private.
     * Needed for our local override of {@link #enforceConstraintsForResize(ChangeBoundsRequest)}.
     */
    private GraphicalEditPart owner;
    
    /**
     * Default constructor.
     * 
     * @param owner
     *            of the resize handle which returned this tracker
     * @param direction
     *            the direction
     */
    public SiriusResizeTracker(GraphicalEditPart owner, int direction) {
        super(owner, direction);
        this.owner = owner;
    }

    @Override
    protected Request createSourceRequest() {
        ChangeBoundsRequest request;
        // Create a specific request (see javadoc of SnapChangeBoundsRequest for
        // more details).
        request = new SnapChangeBoundsRequest(REQ_RESIZE);
        request.setResizeDirection(getResizeDirection());
        return request;
    }

    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        boolean keyHandled = false;
        if (SiriusResizeTracker.CHILDREN_MOVE_MODE_SHORTCUT_KEY == event.keyCode) {
            childrenMoveMode = !SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE;
            keyHandled = true;
        } else if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = !NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            keyHandled = true;
        }
        if (keyHandled) {
            return keyHandled;
        }
        return super.handleKeyDown(event);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        boolean keyHandled = false;
        if (SiriusResizeTracker.CHILDREN_MOVE_MODE_SHORTCUT_KEY == event.keyCode) {
            childrenMoveMode = SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE;
            keyHandled = true;
        } else if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            keyHandled = true;
        }
        if (keyHandled) {
            return keyHandled;
        }
        return super.handleKeyUp(event);
    }

    /**
     * Set {@link CHILDREN_MOVE_MODE_KEY} extended data after update of request
     * (the extended data are cleaned during the
     * {@link ResizeTracker#updateSourceRequest()}). Also update the request
     * with information about snapToAll mode.
     */
    @Override
    protected void updateSourceRequest() {
        if (getSourceRequest() instanceof SnapChangeBoundsRequest) {
            if (snapToAllShape) {
                ((SnapChangeBoundsRequest) getSourceRequest()).setSnapToAllShape(true);
            } else {
                ((SnapChangeBoundsRequest) getSourceRequest()).setSnapToAllShape(false);
            }
        }
        super.updateSourceRequest();
        if (childrenMoveMode) {
            getSourceRequest().getExtendedData().put(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY, Boolean.TRUE);
        } else {
            getSourceRequest().getExtendedData().put(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY, Boolean.FALSE);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Cleans up the mode to original state.
     * 
     * @see org.eclipse.gef.tools.ResizeTracker#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        // Clean up the mode to original state.
        childrenMoveMode = SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE;
        snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
        return result;
    }
    
    /**
     * Copied from GEF 3.14. GEF 3.15 changed the behavior in
     * https://github.com/eclipse/gef-classic/commit/f6b571bc67e1acb7d6b8ba0f4af9880afd5f9328
     */
    @Override
    protected void enforceConstraintsForResize(ChangeBoundsRequest changeBoundsRequest) {
        // adjust request, so that minimum and maximum size constraints are
        // respected
        if (owner != null) {
            PrecisionRectangle originalConstraint = new PrecisionRectangle(owner.getFigure().getBounds());
            owner.getFigure().translateToAbsolute(originalConstraint);
            PrecisionRectangle manipulatedConstraint = new PrecisionRectangle(
                    changeBoundsRequest.getTransformedRectangle(originalConstraint));
            owner.getFigure().translateToRelative(manipulatedConstraint);
            // validate constraint (maximum and minimum size are regarded to be
            // 'normalized', i.e. relative to this figure's bounds coordinates).
            manipulatedConstraint
                    .setSize(Dimension.max(manipulatedConstraint.getSize(), getMinimumSizeFor(changeBoundsRequest)));
            manipulatedConstraint
                    .setSize(Dimension.min(manipulatedConstraint.getSize(), getMaximumSizeFor(changeBoundsRequest)));
            // translate back to absolute
            owner.getFigure().translateToAbsolute(manipulatedConstraint);
            Dimension newSizeDelta = manipulatedConstraint.getSize().getShrinked(originalConstraint.getSize());
            changeBoundsRequest.setSizeDelta(newSizeDelta);
            // The 3 lines below have been added in GEF 3.15 but change the behavior in a way
            // that break some of our tests. Comment them to revert to the previous behavior.
            // if (isTopOrLeftResize()) {
            //   enforceResizeConstraintsForBottomRightCorner(changeBoundsRequest);
            // }
        }
    }
}
