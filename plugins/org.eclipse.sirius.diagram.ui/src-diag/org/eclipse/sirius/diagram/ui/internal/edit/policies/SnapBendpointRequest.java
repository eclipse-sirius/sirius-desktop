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

import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.tools.ConnectionBendpointTracker;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;

/**
 * A BendpointRequest with a specific data concerning the snapToAll feature. The
 * {@link ConnectionBendpointTracker} does not allow to use the extendedMetaData
 * of the Request because it is clean before it can be used in the
 * {@link org.eclipse.gef.SnapToGeometry}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SnapBendpointRequest extends BendpointRequest {
    /**
     * Reflect the mode of the tracker concerning the snap to shape:
     * <UL>
     * <LI>true to snap to all shapes and not only brothers ones,</LI>
     * <LI>false otherwise.</LI>
     * </UL>
     */
    boolean snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;

    /**
     * Creates a ChangeBoundsRequest with the given type.
     * 
     * @param type
     *            The type of Request.
     */
    public SnapBendpointRequest() {
        super();
    }

    /**
     * Return true if the snap to all shape mode is activated, false otherwise.
     * 
     * @return the snapToAllShape status
     */
    public boolean isSnapToAllShape() {
        return snapToAllShape;
    }

    /**
     * Activate or deactivate the snap to all shape mode.
     * 
     * @param snapToAllShape
     *            the snapToAllShape to set
     */
    public void setSnapToAllShape(boolean snapToAllShape) {
        this.snapToAllShape = snapToAllShape;
    }
}
