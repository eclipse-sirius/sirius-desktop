/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor.internal.pages;

/**
 * A command refreshing the tab label of a multi-page editor's page.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class RenameTabLabelCommand {

    /**
     * The label to use when renaming the tab.
     */
    private String newLabel;

    /**
     * Initialize the command with required information.
     * 
     * @param newLabel
     *            the label to use when renaming the tab.
     */
    public RenameTabLabelCommand(String newLabel) {
        this.newLabel = newLabel;
    }

    /**
     * Returns the label to use when renaming the tab.
     * 
     * @return the label to use when renaming the tab.
     */
    public String getNewLabel() {
        return newLabel;
    }
}
