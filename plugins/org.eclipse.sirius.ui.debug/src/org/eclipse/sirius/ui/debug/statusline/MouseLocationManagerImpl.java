/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.statusline;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * Implementation of
 * {@link org.eclipse.sirius.viewpoint.diagram.debug.statusline.MouseLocationManager}
 */
public class MouseLocationManagerImpl implements MouseLocationManager {
    public void updateMouseLocation(final Point absoluteMouseLocation, final Point relativeMouseLocation) {
        final MouseLocationControl control = MouseLocationContribution.CONTROL_MANAGER.getMouseLocationControl();

        if (control != null && !control.isDisposed()) {
            control.setText(toString(absoluteMouseLocation, relativeMouseLocation));
        }
    }

    private String toString(final Point logicalPoint, final Point screenPoint) {
        String resultAbsolute = toString(LOGICAL_PREFIX, logicalPoint, " | ");
        String resultRelative = toString(SCREEN_PREFIX, screenPoint, null);
        return resultAbsolute + resultRelative;
    }

    private String toString(final String label, final Point point, final String suffix) {
        String result;
        if (point == null) {
            result = StringUtil.EMPTY_STRING;
        } else {
            result = label + point.x + " : " + point.y;
        }
        if (suffix != null && !StringUtil.isEmpty(result)) {
            result += suffix;
        }
        return result;
    }
}
