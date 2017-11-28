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
package org.eclipse.sirius.tests.unit.diagram.benchmark;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ErrorEditorPart;

import junit.framework.TestCase;

/**
 * An helper to open and close properly an editor.
 * 
 * @author mchauvin
 */
public class EditorManagementHelper {

    private IEditorPart editor;

    private IUndoContext context;

    private final IOperationHistory operationHistory;

    public EditorManagementHelper(final IOperationHistory operationHistory) {
        this.operationHistory = operationHistory;
    }

    /**
     * Open a new editor.
     * 
     * @param session
     *            the session
     * @param representation
     *            the representation
     */
    public void openEditor(final Session session, final DRepresentation representation) {
        editor = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        context = (IUndoContext) ((DiagramEditor) editor).getAdapter(IUndoContext.class);
        operationHistory.setLimit(context, 0);

        TestCase.assertNotNull("Couldn't open editor", editor);
        TestCase.assertFalse("Error opening the editor", editor instanceof ErrorEditorPart);
    }

    /**
     * Close the previously opened editor.
     */
    public void closeEditor() {
        TestCase.assertTrue(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(editor, false));
        operationHistory.dispose(context, true, true, true);
    }

    /**
     * Get the opened editor.
     * 
     * @return the opened editor.
     */
    public IEditorPart getEditor() {
        return editor;
    }

}
