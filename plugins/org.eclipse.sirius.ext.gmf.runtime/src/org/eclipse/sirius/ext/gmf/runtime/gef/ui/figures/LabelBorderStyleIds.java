/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures;

/**
 * Store all ids of label border style descriptions.
 * 
 * @author smonnier
 */
public final class LabelBorderStyleIds {
    /**
     * Returns the identifier used for beveled corners.
     */
    public static final String LABEL_BORDER_STYLE_WITH_BEVELED_CORNERS_ID = "labelBorderStyleWithBeveledCorner"; //$NON-NLS-1$

    /**
     * Returns the identifier used for border style description for container.
     * This impacts the container content pane: it starts after the label.
     */
    public static final String LABEL_FULL_BORDER_STYLE_FOR_CONTAINER_ID = "labelBorderForContainer"; //$NON-NLS-1$

    /**
     * Returns the identifier used for the no label border style for lists. No
     * impact on content panes.
     */
    public static final String NO_LABEL_BORDER_STYLE_FOR_LISTS_ID = "nolabelBorderForList"; //$NON-NLS-1$

    /**
     * Avoid instantiation from external.
     */
    private LabelBorderStyleIds() {
        // empty.
    }
}
