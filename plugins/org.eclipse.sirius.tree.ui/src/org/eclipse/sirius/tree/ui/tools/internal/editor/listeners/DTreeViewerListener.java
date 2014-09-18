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
package org.eclipse.sirius.tree.ui.tools.internal.editor.listeners;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.DTreeItemExpansionChangeCommand;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.helper.RefreshTreeElementTask;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A {@link ITreeViewerListener} to update the DTree model when a SWT TreeItem
 * is collapsed/expanded.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTreeViewerListener implements ITreeViewerListener {

    private Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link Session} owning models.
     */
    public DTreeViewerListener(Session session) {
        this.session = session;
    }

    @Override
    public void treeExpanded(final TreeExpansionEvent event) {
        if (event.getElement() instanceof DTreeItem) {
            DTreeItem dTreeItem = (DTreeItem) event.getElement();
            if (!dTreeItem.isExpanded()) {
                TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                CommandStack commandStack = domain.getCommandStack();
                CompoundCommand expandDTreeItemCmd = new CompoundCommand("Expand " + dTreeItem.getName() + " tree item");
                GlobalContext globalContext = new GlobalContext(session.getModelAccessor(), session.getInterpreter(), session.getSemanticResources());
                expandDTreeItemCmd.append(new DTreeItemExpansionChangeCommand(globalContext, domain, dTreeItem, true));
                if (!Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null)) {
                    SiriusCommand result = new SiriusCommand(domain);
                    result.getTasks().add(new RefreshTreeElementTask((DTreeItem) event.getElement(), domain));
                    expandDTreeItemCmd.append(result);
                }
                commandStack.execute(expandDTreeItemCmd);
            }
        }
    }

    @Override
    public void treeCollapsed(final TreeExpansionEvent event) {
        if (event.getElement() instanceof DTreeItem) {
            DTreeItem dTreeItem = (DTreeItem) event.getElement();
            if (dTreeItem.isExpanded()) {
                TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                CommandStack commandStack = domain.getCommandStack();
                GlobalContext globalContext = new GlobalContext(session.getModelAccessor(), session.getInterpreter(), session.getSemanticResources());
                Command collapseDTreeItemCmd = new DTreeItemExpansionChangeCommand(globalContext, domain, dTreeItem, false);
                commandStack.execute(collapseDTreeItemCmd);
            }
        }
    }
}
