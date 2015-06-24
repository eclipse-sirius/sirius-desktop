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
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.LineMode;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointMoveHandle;

/**
 * A specific policy to handle the snapToAll feature.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusConnectionBendpointEditPolicy extends ConnectionBendpointEditPolicy {
    /**
     * Default constructor.
     */
    public SiriusConnectionBendpointEditPolicy() {
        super(LineMode.OBLIQUE);
    }

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
}
