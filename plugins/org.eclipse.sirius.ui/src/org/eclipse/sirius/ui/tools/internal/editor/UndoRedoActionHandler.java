/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;

/**
 * Action Handler used to provide appropriate undo and redo Action Handlers for
 * a given Editor Site.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class UndoRedoActionHandler extends Action {

    /**
     * The {@link IUndoContext} to use for creating Undo and Redo Action
     * Handlers.
     */
    private IUndoContext undoContext;

    /**
     * The created Redo Action Handler.
     */
    private RedoActionHandler redoActionHandler;

    /**
     * The created Undo Action Handler
     */
    private UndoActionHandler undoActionHandler;

    /**
     * The EditorSite on which create the Undo and Redo Action Handlers.
     */
    private IEditorSite site;

    /**
     * The {@link TransactionalEditingDomain} to use for getting the undo
     * context.
     */
    private TransactionalEditingDomain domain;

    /**
     * Creates a new UndoRedoActionHandler.
     * 
     * @param domain
     *            the editing domain that will provide the undo context
     * @param editorSite
     *            the editor Site on which install this undoRedoAction handler
     */
    public UndoRedoActionHandler(TransactionalEditingDomain domain, IEditorSite editorSite) {
        super();
        this.domain = domain;
        this.site = editorSite;
        initializeUndoRedoActionHandlers();
    }

    /**
     * Initializes a {@link RedoActionHandler} and an {@link UndoActionHandler}
     * on the requested Editor Site, with the undoContext associated to the
     * Editing Domain.
     */
    private void initializeUndoRedoActionHandlers() {
        // Step 1 : getting the undo Context
        IUndoContext editorUndoContext = getUndoContext();

        if (editorUndoContext != null) {
            // Step 2 : Create the undo action handler
            undoActionHandler = new UndoActionHandler(site, editorUndoContext);
            site.getActionBars().setGlobalActionHandler(ActionFactory.UNDO.getId(), undoActionHandler);

            // Step 3 : Create the redo action handler
            redoActionHandler = new RedoActionHandler(site, editorUndoContext);
            site.getActionBars().setGlobalActionHandler(ActionFactory.REDO.getId(), redoActionHandler);
        }
    }

    /**
     * Returns the undo context of this UndoRedoActionHandler.
     * 
     * @return the undo context or <code>null</code> if not available
     */
    protected IUndoContext getUndoContext() {
        if (undoContext == null) {
            if (domain != null && domain.getCommandStack() instanceof IWorkspaceCommandStack) {
                // We use the command stack to get the default Undo Context
                undoContext = ((IWorkspaceCommandStack) domain.getCommandStack()).getDefaultUndoContext();
            }
        }
        return undoContext;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.ActionFactory.IWorkbenchAction#dispose()
     */
    public void dispose() {

        // We have to dispose the defined Action Handlers
        if (redoActionHandler != null) {
            redoActionHandler.dispose();
        }
        if (undoActionHandler != null) {
            undoActionHandler.dispose();
        }
    }
}
