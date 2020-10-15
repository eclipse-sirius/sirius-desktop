/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.util.Range;
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
        }
    }

    @Override
    protected boolean handleButtonDown(int button) {
        boolean res = super.handleButtonDown(button);
        CacheHelper.initCaches();
        SequenceMessageEditPart smep = (SequenceMessageEditPart) getSourceEditPart();
        if (new ISequenceEventQuery(smep.getISequenceEvent()).isReflectiveMessage()) {
            Range range = smep.getISequenceEvent().getVerticalRange();
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
        }
        return res;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.sirius.diagram.ui.tools.internal.ui.SelectConnectionEditPartTracker#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        CacheHelper.clearCaches();
        return super.handleButtonUp(button);
    }

}
