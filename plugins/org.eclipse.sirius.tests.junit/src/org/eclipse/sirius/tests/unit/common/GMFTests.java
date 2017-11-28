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
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.FontNameContributionItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.part.NullEditorInput;
import org.eclipse.ui.operations.OperationHistoryActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import junit.framework.TestCase;

public class GMFTests extends TestCase {

    public void testDeadlock() throws Exception {

        WorkspaceEditingDomainFactory factory = new WorkspaceEditingDomainFactory();
        final TransactionalEditingDomain domain = factory.createEditingDomain();

        FontNameContributionItem item = new FontNameContributionItem(getPage());
        IEditorPart editor = openTextEditor();

        /* associate undo context from command stack to editor */
        final IUndoContext undoContext = ((IWorkspaceCommandStack) domain.getCommandStack()).getDefaultUndoContext();
        OperationHistoryActionHandler undoAction = new UndoActionHandler(editor.getEditorSite(), undoContext);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(undoAction, IAbstractTextEditorHelpContextIds.UNDO_ACTION);
        undoAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_UNDO);
        editor.getEditorSite().getActionBars().setGlobalActionHandler(ITextEditorActionConstants.UNDO, undoAction);

        item.update();

        final Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    domain.getCommandStack().execute(new RecordingCommand(domain) {
                        @Override
                        protected void doExecute() {
                            // do nothing only the test
                        }
                    });
                    domain.getCommandStack().undo();
                }
            }
        };
        t.start();
        t.join();

        for (int i = 0; i < 500; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // do nothing
            }
            synchronizationWithUIThread();
        }
    }

    private IEditorPart openTextEditor() throws Exception {
        final IWorkbenchPage page = getPage();
        return page.openEditor(new NullEditorInput(), "org.eclipse.ui.DefaultTextEditor");
    }

    private IWorkbenchPage getPage() throws Exception {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    protected void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
        }
    }

}
