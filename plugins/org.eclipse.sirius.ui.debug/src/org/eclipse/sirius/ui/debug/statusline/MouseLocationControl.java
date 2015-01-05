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

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

/**
 * Custom control to display mouse location.
 */
public class MouseLocationControl extends CLabel {

    private static final String COORD_PATTERN = "00000 : 00000";

    /**
     * Constructor.
     * 
     * @param parent
     *            Parent composite
     * @param style
     *            Style
     */
    public MouseLocationControl(final Composite parent, final int style) {
        super(parent, style);
    }

    @Override
    public Point computeSize(final int wHint, final int hHint, final boolean changed) {
        final GC gc = new GC(this);
        final Point p = gc.textExtent(MouseLocationManager.LOGICAL_PREFIX + COORD_PATTERN + MouseLocationManager.SEP + MouseLocationManager.SCREEN_PREFIX + COORD_PATTERN);
        gc.dispose();
        return p;
    }
}
