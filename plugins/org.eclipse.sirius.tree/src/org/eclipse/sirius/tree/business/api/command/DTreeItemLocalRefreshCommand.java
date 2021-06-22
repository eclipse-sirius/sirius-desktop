/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo. 
 * All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which accompanies this distribution, and is
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.api.command;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.interaction.DTreeItemUserInteraction;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.tools.internal.Messages;

/**
 * EMF Command to do a local refresh of a set of {@link DTreeItem}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTreeItemLocalRefreshCommand extends RecordingCommand {

    private GlobalContext globalContext;

    private Collection<DTreeItem> dTreeItems;

    private boolean fullRefresh;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which do changes
     * @param globalContext
     *            the {@link GlobalContext} to do refresh
     * @param dTreeItems
     *            the {@link DTreeItem} to refresh
     * @param fullRefresh
     *            true to do a full refresh of the {@link DTreeItem} and its children recursively
     */
    public DTreeItemLocalRefreshCommand(TransactionalEditingDomain domain, GlobalContext globalContext, Collection<DTreeItem> dTreeItems, boolean fullRefresh) {
        super(domain, Messages.DTreeItemLocalRefreshCommand_refreshLocally);
        this.globalContext = globalContext;
        this.dTreeItems = dTreeItems;
        this.fullRefresh = fullRefresh;
    }

    @Override
    protected void doExecute() {
        for (DTreeItem dTreeItem : dTreeItems) {
            new DTreeItemUserInteraction(dTreeItem, globalContext).refreshContent(fullRefresh);
        }
    }

}
