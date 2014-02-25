/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

/**
 * Store all ids of label border style descriptions.
 * 
 * @author smonnier
 */
public final class LabelBorderStyleIds {

    private static LabelBorderStyleIds instance;

    /**
     * Avoid instantiation from external.
     */
    private LabelBorderStyleIds() {
        // empty.
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static LabelBorderStyleIds getInstance() {
        if (instance == null) {
            instance = new LabelBorderStyleIds();
        }
        return instance;
    }

    /**
     * Returns the identifier used for beveled corners.
     * 
     * @return the identifier used for beveled corners.
     */
    public static String getLabelBorderStyleWithBeveledCornersIds() {
        return "labelBorderStyleWithBeveledCorner";
    }
}
