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
package org.eclipse.sirius.tree.business.api.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.interaction.DTreeItemUserInteraction;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;

/**
 * EMF Command to expand/collapse a {@link DTreeItem}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTreeItemExpansionChangeCommand extends RecordingCommand {

    private Session session;

    private DTreeItem dTreeItem;

    private boolean expand;

    /**
     * Default constructor.
     * 
     * @param session
     *            The {@link Session} on which execute this command.
     * @param dTreeItem
     *            the {@link DTreeItem} to expand/collapse
     * @param expand
     *            true to expand, false to collapse
     */
    public DTreeItemExpansionChangeCommand(Session session, DTreeItem dTreeItem, boolean expand) {
        super(session.getTransactionalEditingDomain());
        this.session = session;
        this.dTreeItem = dTreeItem;
        this.expand = expand;
    }

    @Override
    protected void doExecute() {
        GlobalContext ctx = new GlobalContext(session.getModelAccessor(), session);
        if (expand) {
            new DTreeItemUserInteraction(dTreeItem, ctx).expand();
        } else {
            new DTreeItemUserInteraction(dTreeItem, ctx).collapse();
        }
    }
}
