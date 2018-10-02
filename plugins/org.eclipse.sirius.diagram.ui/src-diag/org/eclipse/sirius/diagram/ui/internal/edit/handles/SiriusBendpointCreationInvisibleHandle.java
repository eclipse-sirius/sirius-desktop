/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.edit.handles;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gmf.runtime.gef.ui.internal.handles.BendpointCreationInvisibleHandle;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SiriusConnectionBendpointTrackerEx;

/**
 * A Sirius specific BendpointCreationInvisibleHandle to handle the
 * "move edge group" feature by using a
 * {@link SiriusConnectionBendpointTrackerEx} instead of the GMF
 * ConnectionBendpointTracker.
 * 
 * @author Florian Barbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusBendpointCreationInvisibleHandle extends BendpointCreationInvisibleHandle {

    public SiriusBendpointCreationInvisibleHandle(ConnectionEditPart owner, int index) {
        super(owner, index);
    }

    @Override
    protected DragTracker createDragTracker() {
        SiriusConnectionBendpointTrackerEx tracker;
        tracker = new SiriusConnectionBendpointTrackerEx((ConnectionEditPart) getOwner(), getIndex());
        tracker.setType(RequestConstants.REQ_CREATE_BENDPOINT);
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }

}
