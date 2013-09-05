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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;

/**
 * {@link Listener} to prevents expansion/collapse {@link Event} while
 * {@link IPermissionAuthority} disallow
 * {@link TreePackage#DTREE_ITEM__EXPANDED} change.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTreeItemExpansionChecker implements Listener {

    private IPermissionAuthority permissionAuthority;

    private Control control;

    /**
     * Default constructor.
     * 
     * @param control
     *            the {@link Control} to listen
     */
    public DTreeItemExpansionChecker(Control control) {
        this.control = control;
        control.getDisplay().addFilter(SWT.Expand, this);
        control.getDisplay().addFilter(SWT.Collapse, this);
    }

    public void setPermissionAuthority(IPermissionAuthority permissionAuthority) {
        this.permissionAuthority = permissionAuthority;
    }

    /**
     * Overridden to handle {@link SWT#Collapse} and {@link SWT#Expand} events.
     * 
     * {@inheritDoc}
     */
    public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.Collapse:
            handleTreeCollapse(event);
            break;
        case SWT.Expand:
            handleTreeExpand(event);
            break;
        default:
            break;
        }

    }

    /**
     * Handle the undo of the swt TreeItem collapse if the current
     * {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeCollapse(Event event) {
        if (!isEventForDTreeItemExpandable(event)) {
            event.type = SWT.None;
            final TreeItem treeItem = (TreeItem) event.item;
            Display.getDefault().asyncExec(new Runnable() {

                public void run() {
                    treeItem.setExpanded(true);
                }

            });

        }
    }

    /**
     * Handle the undo the swt TreeItem expansion if the current
     * {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeExpand(Event event) {
        if (!isEventForDTreeItemExpandable(event)) {
            event.type = SWT.None;
            final TreeItem treeItem = (TreeItem) event.item;
            Display.getDefault().asyncExec(new Runnable() {

                public void run() {
                    treeItem.setExpanded(false);
                }

            });
        }
    }

    /**
     * Tells if the specified {@link Event} is a event of a {@link TreeItem}
     * collapse/expansion which should be allowed by the current
     * {@link IPermissionAuthority}.
     * 
     * @param event
     *            the specified {@link Event}
     * @return true if the specified {@link Event} is allowed by the current
     *         {@link IPermissionAuthority}, false else
     */
    private boolean isEventForDTreeItemExpandable(Event event) {
        boolean isEventForDTreeItemExpandable = true;
        if (event.item instanceof TreeItem) {
            TreeItem treeItem = (TreeItem) event.item;
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                boolean canEditFeature = permissionAuthority != null && permissionAuthority.canEditFeature(dTreeItem, TreePackage.Literals.DTREE_ITEM__EXPANDED.getName());
                isEventForDTreeItemExpandable = canEditFeature;
            }
        }
        return isEventForDTreeItemExpandable;
    }

    /**
     * remove THis {@link DTreeItemExpansionChecker} as {@link Listener} of the
     * Tree.
     */
    public void dispose() {
        control.getDisplay().removeFilter(SWT.Expand, this);
        control.getDisplay().removeFilter(SWT.Collapse, this);
    }

}
