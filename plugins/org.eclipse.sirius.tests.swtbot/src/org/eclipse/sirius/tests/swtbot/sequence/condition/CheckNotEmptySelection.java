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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

public class CheckNotEmptySelection extends DefaultCondition {

    private int expectedReturnMessageNumber;

    private SWTBotSiriusDiagramEditor editor;

    private Class<SequenceMessageEditPart> expectedType;

    /**
     * Constructor.
     * 
     */
    public CheckNotEmptySelection(SWTBotSiriusDiagramEditor editor) {
        this.editor = editor;
    }

    public CheckNotEmptySelection(SWTBotSiriusDiagramEditor editor, Class<SequenceMessageEditPart> expectedType) {
        this.editor = editor;
        this.expectedType = expectedType;
    }

    public boolean test() throws Exception {
        ISelection selection = editor.getSelection();
        if (expectedType == null) {
            return !selection.isEmpty();
        } else if (selection instanceof StructuredSelection) {
            StructuredSelection sel = (StructuredSelection) selection;
            return expectedType.isInstance(sel.getFirstElement());
        }
        return false;
    }

    public String getFailureMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
