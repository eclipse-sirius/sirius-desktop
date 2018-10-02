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
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Inner class to check if the edit part is selected.
 * 
 * @author smonnier
 */
public class CheckToolIsActivated extends DefaultCondition {

    private final String toolLabel;

    private final SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param editor
     *            the {@link SWTBotSiriusDiagramEditor}.
     * 
     * @param toolLabel
     *            the label of the tool
     */
    public CheckToolIsActivated(SWTBotSiriusDiagramEditor editor, String toolLabel) {
        this.toolLabel = toolLabel;
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return toolLabel != null && editor.getActiveTool() != null && toolLabel.equals(editor.getActiveTool().getLabel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return null;
    }
}
