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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.LineMode;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.MoveEdgeGroupManager;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointCreationInvisibleHandle;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointMoveHandle;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusLineSegMoveInvisibleHandle;

/**
 * A specific policy to handle the snapToAll and moveEdgeGroup features.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusConnectionBendpointEditPolicy extends ConnectionBendpointEditPolicy {

    /**
     * Default constructor.
     */
    public SiriusConnectionBendpointEditPolicy() {
        super(LineMode.OBLIQUE);
    }

    public SiriusConnectionBendpointEditPolicy(LineMode orthogonalFree) {
        super(orthogonalFree);
    }

    @Override
    public Command getCommand(Request request) {
        MoveEdgeGroupManager nodesGroupMoveManager = new MoveEdgeGroupManager(request);
        if (nodesGroupMoveManager.isToolActivated()) {
            return nodesGroupMoveManager.getCommand();

        } else {
            return super.getCommand(request);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected List createManualHandles() {
        List list = new ArrayList();
        ConnectionEditPart connEP = (ConnectionEditPart) getHost();
        PointList points = getConnection().getPoints();
        for (int i = 1; i < points.size() - 1; i++) {
            addInvisibleCreationHandle(list, connEP, i - 1);
            // Use a SiriusBendpointMoveHandle to handle the snap to all shapes
            // feature
            list.add(new SiriusBendpointMoveHandle(connEP, i, new BendpointLocator(getConnection(), i)));
        }
        addInvisibleCreationHandle(list, connEP, points.size() - 2);
        return list;
    }

    @Override
    public void showSourceFeedback(Request request) {
        MoveEdgeGroupManager nodesGroupMoveManager = new MoveEdgeGroupManager(request);
        if (nodesGroupMoveManager.isToolActivated()) {
            nodesGroupMoveManager.showGroupFeedback();
        } else {
            nodesGroupMoveManager.eraseGroupFeedback();
            super.showSourceFeedback(request);
        }
    }

    @Override
    public void eraseSourceFeedback(Request request) {
        MoveEdgeGroupManager nodesGroupMoveManager = new MoveEdgeGroupManager(request);
        nodesGroupMoveManager.eraseGroupFeedback();
        super.eraseSourceFeedback(request);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected void addInvisibleCreationHandle(List list, org.eclipse.gef.ConnectionEditPart connEP, int i) {
        if (getLineSegMode() != LineMode.OBLIQUE) {
            list.add(new SiriusLineSegMoveInvisibleHandle(connEP, i));
        } else {
            list.add(new SiriusBendpointCreationInvisibleHandle(connEP, i));
        }
    }
}
