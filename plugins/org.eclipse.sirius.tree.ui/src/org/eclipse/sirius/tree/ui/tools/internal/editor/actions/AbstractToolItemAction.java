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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.description.TreeItemTool;

/**
 * An abstract class for all actions on {@link DLine}.
 * 
 * @author nlepine
 */
public abstract class AbstractToolItemAction extends AbstractToolAction {

    /**
     * The line concerned with this action
     */
    private DTreeItem line;

    /**
     * Creates a new action with the given text and the given line.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param editingDomain
     *            The transactional editing domain
     * @param treeCommandFactory
     *            The EMF command factory
     * @param treeTool
     *            The tool corresponding to this action
     */
    public AbstractToolItemAction(final String text, final TransactionalEditingDomain editingDomain, final ITreeCommandFactory treeCommandFactory, final TreeItemTool treeTool) {
        super(text, editingDomain, treeCommandFactory, treeTool);
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
    public AbstractToolItemAction(final String text, final ImageDescriptor image, final TransactionalEditingDomain editingDomain, final ITreeCommandFactory treeCommandFactory,
            final TreeItemTool treeTool) {
        super(text, image, editingDomain, treeCommandFactory, treeTool);
    }

    /**
     * Return the line.
     * 
     * @return the line
     */
    public DTreeItem getTreeItem() {
        return line;
    }

    /**
     * Set the line on which the tool of this action applied.
     * 
     * @param line
     *            the line to set
     */
    public void setLine(final DTreeItem line) {
        this.line = line;
    }
}
