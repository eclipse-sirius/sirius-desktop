/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.description.TreeItemTool;

/**
 * An abstract class for all actions which launch tool (DeleteTool, CreateTool).
 * 
 * @author nlepine
 */
public class AbstractToolAction extends Action {
    TransactionalEditingDomain editingDomain;

    ITreeCommandFactory treeCommandFactory;

    /**
     * The tool corresponding to this action.
     */
    TreeItemTool tool;

    /**
     * Creates a new action with the given text.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param editingDomain
     *            The transactional editing domain
     * @param treeCommandFactory
     *            The EMF command factory
     * @param treeTool
     *            The tool corresponding to this action
     * 
     */
    public AbstractToolAction(final String text, final TransactionalEditingDomain editingDomain, final ITreeCommandFactory treeCommandFactory, final TreeItemTool treeTool) {
        super(text);
        this.editingDomain = editingDomain;
        this.treeCommandFactory = treeCommandFactory;
        this.tool = treeTool;
    }

    /**
     * Creates a new action with the given text and style.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @param editingDomain
     *            The transactional editing domain
     * @param treeCommandFactory
     *            The EMF command factory
     * @param treeTool
     *            The tool corresponding to this action
     */
    public AbstractToolAction(final String text, final ImageDescriptor image, final TransactionalEditingDomain editingDomain, final ITreeCommandFactory treeCommandFactory, final TreeItemTool treeTool) {
        super(text, image);
        this.editingDomain = editingDomain;
        this.treeCommandFactory = treeCommandFactory;
        this.tool = treeTool;
    }

    /**
     * return The transactional editing domain.
     * 
     * @return the editingDomain
     */
    protected TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Return the EMF command factory.
     * 
     * @return the treeCommandFactory
     */
    protected ITreeCommandFactory getITreeCommandFactory() {
        return treeCommandFactory;
    }

    /**
     * The default implementation of this <code>AbstractToolAction</code> method
     * return false. Subclasses should override this method.
     * 
     * @return true if this action can be execute, false otherwise
     */
    public boolean canExecute() {
        return false;
    }

    protected TreeItemTool getTool() {
        return tool;
    }
}
