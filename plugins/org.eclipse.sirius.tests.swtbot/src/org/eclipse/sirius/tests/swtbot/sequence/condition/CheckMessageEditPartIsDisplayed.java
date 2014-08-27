/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Checks if a message edit part is displayed.
 * 
 * @author smonnier
 */
public class CheckMessageEditPartIsDisplayed extends DefaultCondition {

    private final String label;

    private final SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param label
     *            label of the edit part to wait for.
     */
    public CheckMessageEditPartIsDisplayed(String label, SWTBotSiriusDiagramEditor editor) {
        this.label = label;
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     */
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
    public String getFailureMessage() {
        return "The widget with label '" + label + "' has not been found before timeout";
    }
}
