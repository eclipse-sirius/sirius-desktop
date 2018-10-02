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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Check that no elements on the diagram are selected and therefore the
 * selection of the editor is the diagram itself.
 * 
 * @author pcdavid
 */
public class CheckDiagramSelected extends DefaultCondition {

    /**
     * Current editor.
     */
    private final SWTBotSiriusDiagramEditor editor;

    /**
     * Default constructor.
     * 
     * @param editor
     *            the current editor.
     */
    public CheckDiagramSelected(SWTBotSiriusDiagramEditor editor) {
        super();
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        ISelection selection = editor.getSelection();
        return selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1 && ((IStructuredSelection) selection).getFirstElement() instanceof DiagramEditPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "Failed to select the diagram.";
    }
}
