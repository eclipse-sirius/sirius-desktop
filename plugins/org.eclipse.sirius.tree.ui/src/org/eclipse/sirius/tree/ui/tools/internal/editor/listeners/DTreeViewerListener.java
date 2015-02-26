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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

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
        treeExpandingCollapsingAction(event, true, "expanding the tree.");
    }

    @Override
    public void treeCollapsed(final TreeExpansionEvent event) {
        treeExpandingCollapsingAction(event, false, "collapsing the tree.");
    }

    /**
     * Expanding/collapsing a DTreeItem.
     * 
     * @param event
     *            the tree expansion event
     * @param expand
     *            true to expand, false to collapse
     * @param errorMessage
     *            the error message while expanding/collapsing the tree
     */
    private void treeExpandingCollapsingAction(final TreeExpansionEvent event, final boolean expand, final String errorMessage) {
        if (event.getElement() instanceof DTreeItem) {
            final DTreeItem dTreeItem = (DTreeItem) event.getElement();
            if ((expand && !dTreeItem.isExpanded()) || (!expand && dTreeItem.isExpanded())) {
                final Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
                activeShell.getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            monitorDialog.run(true, false, new ExpandDTreeItemRunnableWithProgress(session, dTreeItem, expand));
                        } catch (InvocationTargetException e) {
                            TreeUIPlugin.INSTANCE.log(new Status(IStatus.ERROR, TreeUIPlugin.ID, "Error while " + errorMessage, e));
                        } catch (InterruptedException e) {
                            TreeUIPlugin.INSTANCE.log(new Status(IStatus.WARNING, TreeUIPlugin.ID, "Interruption while " + errorMessage, e));
                        }
                    }
                });

            }
        }
    }
}
