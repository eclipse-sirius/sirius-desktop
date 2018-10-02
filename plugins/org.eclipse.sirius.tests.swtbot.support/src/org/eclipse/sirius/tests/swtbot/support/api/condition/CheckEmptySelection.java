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

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Check that no element are selected on editor.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 * 
 */
public class CheckEmptySelection extends DefaultCondition {

    private final SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param editor
     *            current Editor
     * 
     */
    public CheckEmptySelection(SWTBotSiriusDiagramEditor editor) {
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        ISelection selection = editor.getSelection();
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            return structuredSelection.size() == 1 && structuredSelection.getFirstElement() instanceof DiagramEditPart;

        }
        return selection.isEmpty();
    }

    @Override
    public String getFailureMessage() {
        return "Couldn't evalutate selection before Timeout";
    }

}
