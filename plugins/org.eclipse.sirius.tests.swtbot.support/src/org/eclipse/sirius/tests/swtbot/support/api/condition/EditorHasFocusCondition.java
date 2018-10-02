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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Condition that validates that an editor has the focus.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class EditorHasFocusCondition extends DefaultCondition {

    private final SWTBotSiriusDiagramEditor swtbotEditor;

    /**
     * Default constructor.
     * 
     * @param swtbotEditor
     *            the {@link SWTBotSiriusDiagramEditor} that should has the focus.
     */
    public EditorHasFocusCondition(SWTBotSiriusDiagramEditor swtbotEditor) {
        super();
        this.swtbotEditor = swtbotEditor;
    }

    /**
     * test that the editor has the focus.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        IWorkbenchPart activePart = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActivePart();
        IEditorPart editorPart = swtbotEditor.getReference().getEditor(false);
        return editorPart != null && activePart != null && editorPart.equals(activePart);
    }

    @Override
    public String getFailureMessage() {
        return "The focus has not been set on the editor";
    }

}
