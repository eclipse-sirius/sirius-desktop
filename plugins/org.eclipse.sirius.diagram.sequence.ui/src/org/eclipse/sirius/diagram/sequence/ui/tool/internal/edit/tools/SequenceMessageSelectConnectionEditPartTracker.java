/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.tools;

import java.util.Map;
import java.util.Optional;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceMessageEditPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.ui.SequenceDragEditPartsTrackerEx.SequenceCacheDragTrackerHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SelectConnectionEditPartTracker;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Specific connection selection tracker to handle move of messageToSelf messages.
 * 
 * @author mporhel
 * 
 */
@SuppressWarnings("restriction")
public class SequenceMessageSelectConnectionEditPartTracker extends SelectConnectionEditPartTracker implements DragTracker {

    private boolean fromTop = true;

    private BendpointRequest bendpointRequest;

    private boolean msgToSelfMove;

    private Optional<Point> initialClick = Optional.empty();

    /**
     * Method SequenceMessageSelectConnectionEditPartTracker.
     * 
     * @param owner
     *            ConnectionNodeEditPart that creates and owns the tracker object
     */
    public SequenceMessageSelectConnectionEditPartTracker(ConnectionEditPart owner) {
        super(owner);
    }

    @Override
    protected Request createSourceRequest() {
        Request rq = super.createSourceRequest();
        if (rq instanceof BendpointRequest) {
            bendpointRequest = (BendpointRequest) rq;
        }
        return rq;
    }

    @Override
    protected void updateSourceRequest() {
        super.updateSourceRequest();
        if (bendpointRequest != null) {
            Map<Object, Object> extData = bendpointRequest.getExtendedData();
            if (msgToSelfMove) {
                extData.put(SequenceMessageEditPart.MSG_TO_SELF_TOP_MOVE, fromTop);
            } else {
                extData.remove(SequenceMessageEditPart.MSG_TO_SELF_TOP_MOVE);
            }
            if (initialClick.isPresent()) {
                extData.put(SequenceMessageEditPart.MSG_OBLIQUE_CBR_INITAL_CLICK, initialClick.get());
            } else {
                extData.remove(SequenceMessageEditPart.MSG_OBLIQUE_CBR_INITAL_CLICK);
            }
        }
    }

    @Override
    protected boolean handleButtonDown(int button) {
        boolean res = super.handleButtonDown(button);
        SequenceMessageEditPart smep = (SequenceMessageEditPart) getSourceEditPart();
        SequenceCacheDragTrackerHelper.handleButtonDown(smep);

        ISequenceEvent iSequenceEvent = smep.getISequenceEvent();
        if (new ISequenceEventQuery(iSequenceEvent).isReflectiveMessage()) {
            Range range = iSequenceEvent.getVerticalRange();
            Point location = getLocation().getCopy();
            GraphicalHelper.screen2logical(location, smep);

            Connection connection = smep.getConnectionFigure();

            int x = connection.getPoints().getMidpoint().x;
            if (x == location.x) {
                msgToSelfMove = false;
            } else {
                fromTop = location.y <= range.getLowerBound() || location.y < range.middleValue();
                msgToSelfMove = true;
            }
        } else if (new ISequenceEventQuery(iSequenceEvent).isObliqueMessage() && initialClick.isEmpty()) {
            Point location = getLocation().getCopy();
            GraphicalHelper.screen2logical(location, smep);
            // Store logical clicked position on button down
            // Scroll might change before button up.
            initialClick = Optional.of(location.getCopy());
            Rectangle properLogicalBounds = iSequenceEvent.getProperLogicalBounds();
            if (properLogicalBounds.width < 0) {
                // right to left message
                if (initialClick.get().x <= (properLogicalBounds.right() - properLogicalBounds.width / 4)) {
                    // move target
                    initialClick.get().x = SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_TARGET;
                } else if (initialClick.get().x >= (properLogicalBounds.left() + properLogicalBounds.width / 4)) {
                    // move source
                    initialClick.get().x = SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_SOURCE;
                } else {
                    // move message
                    initialClick.get().x = SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_MESSAGE;
                }

            } else {
                // left to right message
                if (initialClick.get().x <= (properLogicalBounds.left() + properLogicalBounds.width / 4)) {
                    // move source
                    initialClick.get().x = SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_SOURCE;
                } else if (initialClick.get().x >= (properLogicalBounds.right() - properLogicalBounds.width / 4)) {
                    // move target
                    initialClick.get().x = SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_TARGET;
                } else {
                    // move message
                    initialClick.get().x = SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_MESSAGE;
                }
            }
        }
        return res;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.sirius.diagram.ui.tools.internal.ui.SelectConnectionEditPartTracker#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        // Cleanup initial click;
        initialClick = Optional.empty();
        SequenceCacheDragTrackerHelper.handleButtonUp((IGraphicalEditPart) getSourceEditPart());
        return super.handleButtonUp(button);
    }

}
