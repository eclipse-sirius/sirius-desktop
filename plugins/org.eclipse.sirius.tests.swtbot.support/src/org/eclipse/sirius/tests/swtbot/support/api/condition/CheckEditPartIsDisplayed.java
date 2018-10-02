/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
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

import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Checks if a message edit part is displayed.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class CheckEditPartIsDisplayed extends DefaultCondition {

    private final String label;

    private final SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param label
     *            label of the edit part to wait for.
     * 
     * @param editor
     *            the editor containing the edit part we are looking for.
     */
    public CheckEditPartIsDisplayed(String label, SWTBotSiriusDiagramEditor editor) {
        this.label = label;
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        try {
            return editor.getEditPart(label) != null;
        } catch (WidgetNotFoundException e) {
            // The widget has not yet been found
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "The widget with label '" + label + "' has not been found before timeout";
    }
}
