/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor.internal.pages;

import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;

/**
 * A command to reorder a page to another location identified by a page id among
 * its parent editor pages.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ReorderPageCommand {

    /**
     * The kind of positioning to achieve.
     */
    PositioningKind positioningKind;

    /**
     * The id of the page to be placed relatively to.
     */
    String targetPageId;

    /**
     * Initialize the command with required information.
     * 
     * @param positioningKind
     *            the kind of positioning to achieve.
     * @param targetPageId
     *            the id of the page to be placed relatively to.
     */
    public ReorderPageCommand(PositioningKind positioningKind, String targetPageId) {
        super();
        this.positioningKind = positioningKind;
        this.targetPageId = targetPageId;
    }

    /**
     * Returns the id of the page to be placed relatively to.
     * 
     * @return the id of the page to be placed relatively to.
     */
    public String getTargetPageId() {
        return targetPageId;
    }

    /**
     * Returns the kind of positioning to achieve.
     * 
     * @return the kind of positioning to achieve.
     */
    public PositioningKind getPositioningKind() {
        return positioningKind;
    }
}
