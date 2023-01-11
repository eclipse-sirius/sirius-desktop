/*******************************************************************************
 * Copyright (c) 2023 Obeo.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.listeners;

import java.text.MessageFormat;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.TreeRefreshContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.ui.provider.Messages;

/**
 * Abstract class to refresh tree elements when a SWT TreeItem is collapsed or expanded.
 * 
 * @author Laurent Redor
 */
public abstract class AbstractDTreeItemRunnableWithProgress implements IRunnableWithProgress {

    /**
     * The current session.
     */
    private final Session session;

    /**
     * The status concerning expand/collapse all.
     */
    private final boolean all;

    /**
     * The domain used to build command.
     */
    private TransactionalEditingDomain domain;

    /**
     * The global context used to build command.
     */
    private GlobalContext globalContext;

    /**
     * The command stack used to execute command.
     */
    private CommandStack commandStack;

    /**
     * Default constructor.
     * 
     * @param session
     *            the session
     * @param dTreeItem
     *            the tree item
     */
    public AbstractDTreeItemRunnableWithProgress(Session session, DTreeItem dTreeItem) {
        if (dTreeItem == null) {
            throw new IllegalArgumentException(Messages.AbstractDTreeItemRunnableWithProgress_nullDTreeItemParameter);
        }
        this.session = session;
        this.all = false;
    }

    /**
     * Constructor to collapse/expand several items in the same time (depending on <code>all</code> parameter value.
     * 
     * @param session
     *            the session
     * @param all
     *            true if this runnable concerns an expand/collapse all {@link DTreeItem}s.
     * @param size
     *            The size of elements to collapse/expand
     */
    public AbstractDTreeItemRunnableWithProgress(Session session, boolean all, int size) {
        if (size == 0) {
            throw new IllegalArgumentException(Messages.AbstractDTreeItemRunnableWithProgress_emptyDTreeItemsParameter);
        }
        this.session = session;
        this.all = all;
    }

    /**
     * Return if this runnable concerns an expand/collapse all {@link DTreeItem}s.
     */
    protected boolean isCollapseOrExpandAll() {
        return all;
    }

    /**
     * Return the session.
     * 
     * @return the session
     */
    protected Session getSession() {
        return session;
    }

    /**
     * Return the domain used to build the command.
     * 
     * @return the domain used to build the command
     */
    protected TransactionalEditingDomain getDomain() {
        if (domain == null) {
            domain = getSession().getTransactionalEditingDomain();
        }
        return domain;
    }

    /**
     * Return the global context used to build command.
     * 
     * @return the global context used to build command
     */
    protected GlobalContext getGlobalContext() {
        if (globalContext == null) {
            globalContext = new TreeRefreshContext(getSession().getModelAccessor(), getSession().getInterpreter(), getSession().getSemanticResources(), getSession().getTransactionalEditingDomain());
        }
        return globalContext;
    }

    /**
     * Return the command stack used to execute command.
     * 
     * @return the command stack used to execute command
     */
    protected CommandStack getCommandStack() {
        if (commandStack == null) {
            commandStack = domain.getCommandStack();
        }
        return commandStack;
    }

    /**
     * Get the command label.
     * 
     * @param dTreeItem
     *            DTreeItem used to build the command label.
     * @param singleElementCommandName
     *            The pattern to use for a single collapse/expand
     * @param allElementsCommandName
     *            The pattern to use for a collapse/expand all
     * @param isCollapseOrExpandAll
     *            true if the command concerns a collapse/expand all, false otherwise.
     * @return the command label
     */
    public static String getCommandLabel(DTreeItem dTreeItem, String singleElementCommandName, String allElementsCommandName, boolean isCollapseOrExpandAll) {
        String commandLabel;
        if (isCollapseOrExpandAll) {
            commandLabel = MessageFormat.format(allElementsCommandName, dTreeItem.getName());
        } else {
            commandLabel = MessageFormat.format(singleElementCommandName, dTreeItem.getName());
        }
        return commandLabel;
    }
}
