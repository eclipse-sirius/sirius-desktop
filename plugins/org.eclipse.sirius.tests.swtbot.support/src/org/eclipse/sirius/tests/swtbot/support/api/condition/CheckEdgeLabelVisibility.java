/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Inner class to check if the label of this edge edit part is hidden.
 * 
 * @author smonnier
 */
public class CheckEdgeLabelVisibility extends DefaultCondition {

    private final String label;

    private final SWTBotSiriusDiagramEditor editor;

    private final boolean isVisible;

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor
     * @param label
     *            name of the edit part to wait for its hiding.
     * @param isVisible
     *            check is the label should be visible or hidden
     */
    public CheckEdgeLabelVisibility(SWTBotSiriusDiagramEditor editor, String label, boolean isVisible) {
        this.label = label;
        this.editor = editor;
        this.isVisible = isVisible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return ((AbstractGraphicalEditPart) editor.getEditPart(label).part()).getFigure().isVisible() == isVisible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        if (isVisible) {
            return "The label is still hidden.";
        } else {
            return "The label is still visible.";
        }
    }
}
