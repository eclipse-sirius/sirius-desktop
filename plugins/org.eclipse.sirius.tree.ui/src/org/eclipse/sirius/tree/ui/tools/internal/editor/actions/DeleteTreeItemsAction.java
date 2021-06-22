/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.internal.query.DTreeItemInternalQuery;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;

/**
 * This action delete the line (the corresponding semantic element).
 * 
 * @author nlepine
 */
public class DeleteTreeItemsAction extends Action {
    private final ITreeCommandFactory treeCommandFactory;

    private final TransactionalEditingDomain editingDomain;

    private final Collection<DTreeItem> items = new ArrayList<>();

    /**
     * Constructor. The deleteTool can be null if there is nothing specific to do (only the delete of the line). <BR>
     * When a {@link DeleteTool} is specified the normal delete is not done.
     * 
     * @param editingDomain
     *            The transactional editing domain
     * @param treeCommandFactory
     *            The EMF command factory
     * 
     */
    public DeleteTreeItemsAction(final TransactionalEditingDomain editingDomain, final ITreeCommandFactory treeCommandFactory) {
        super(Messages.DeleteTreeItemsAction_deleteTreeItem, DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.DELETE_IMG));
        this.editingDomain = editingDomain;
        this.treeCommandFactory = treeCommandFactory;
    }

    @Override
    public void run() {
        super.run();

        CompoundCommand cc = new CompoundCommand(getText());
        for (DTreeItem item : items) {
            cc.append(treeCommandFactory.buildDeleteTreeElement(item));
        }
        getEditingDomain().getCommandStack().execute(cc);

        items.clear();
    }

    private TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    private TreeItemDeletionTool getDeleteTool(DTreeItem treeItem) {
        TreeItemDeletionTool tool = null;
        if (treeItem != null) {
            TreeItemMapping actualMapping = treeItem.getActualMapping();
            if (actualMapping != null) {
                tool = actualMapping.getDelete();
            }
        }
        return tool;
    }

    /**
     * Set the tree items to delete.
     * 
     * @param itemsToDelete
     *            the items to delete.
     */
    public void setItems(Collection<DTreeItem> itemsToDelete) {
        items.clear();
        if (itemsToDelete != null) {
            items.addAll(itemsToDelete);
        }

        if (items.size() == 1) {
            setText(Messages.DeleteTreeItemsAction_deleteTreeItem);
            setToolTipText(Messages.DeleteTreeItemsAction_deleteTargetSemanticElement);

            TreeItemDeletionTool deleteTool = getDeleteTool(items.iterator().next());
            if (deleteTool != null) {
                setText(new IdentifiedElementQuery(deleteTool).getLabel());
                setToolTipText(MessageTranslator.INSTANCE.getMessage(deleteTool, deleteTool.getDocumentation()));
            }
        } else if (items.size() > 1) {
            setText(Messages.DeleteTreeItemsAction_deleteTreeItems);
            setToolTipText(Messages.DeleteTreeItemsAction_deleteTargetSemanticElements);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.ui.tools.internal.editor.action.AbstractToolAction#canExecute()
     */
    public boolean canExecute() {
        boolean canExecute = !items.isEmpty();

        for (DTreeItem itemToDelete : items) {
            canExecute = canExecute && new DTreeItemInternalQuery(itemToDelete).canBeDeleted();

            if (!canExecute) {
                break;
            }
        }

        return canExecute;
    }

}
