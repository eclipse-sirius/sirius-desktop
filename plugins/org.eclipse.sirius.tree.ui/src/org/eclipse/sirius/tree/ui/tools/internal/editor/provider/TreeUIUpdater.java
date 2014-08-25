/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.DRepresentationNotificationFilter;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Sets;

/**
 * A class responsible to update the UI part of a {@link DTree}.
 *
 * @author nlepine
 */
public class TreeUIUpdater extends ResourceSetListenerImpl {

    /** The structured viewer to update. */
    private DTreeViewer dTreeViewer;

    private Set<DTreeItem> toExpand;

    private Set<DTreeItem> toCollapse;

    private Set<Object> toRefreshInViewer;

    private Set<Object> toUpdateInViewer;

    /**
     * Creates a new tree content adapter with the given session.
     *
     * @param dTreeViewer
     *            the {@link DTreeViewer} to update according to
     *            {@link org.eclipse.sirius.tree.DTree} model changes
     * @param dRepresentation
     *            the {@link DRepresentation} for which we update the
     *            {@link DTreeViewer}
     */
    public TreeUIUpdater(DTreeViewer dTreeViewer, DRepresentation dRepresentation) {
        super(new DRepresentationNotificationFilter(dRepresentation));
        this.dTreeViewer = dTreeViewer;
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dRepresentation);
        if (domain != null) {
            domain.addResourceSetListener(this);
        }
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    private void notifyChanged(final Notification notification) {
        final Object notifier = notification.getNotifier();
        if (notifier instanceof DTree) {
            handleDTreeNotification(notification, (DTree) notifier);
        } else if (notifier instanceof DTreeItem) {
            handleDTreeItemNotification(notification, (DTreeItem) notifier);
        } else if (notifier instanceof TreeItemStyle) {
            handleDTreeItemNotification(notification, (TreeItemStyle) notifier);
        } else if (notifier instanceof RGBValues) {
            handleDTreItemNotification(notification, (RGBValues) notifier);
        }
    }

    private void handleDTreeNotification(final Notification n, final DTree tree) {
        final int featureID = n.getFeatureID(DTree.class);

        switch (featureID) {
        case TreePackage.DTREE__OWNED_TREE_ITEMS:
        case TreePackage.DTREE__TARGET:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
            refreshViewer(tree);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
            break;
        default:
            break;
        }
    }

    private void handleDTreeItemNotification(final Notification n, final DTreeItem dTreeItem) {
        final int featureID = n.getFeatureID(DTreeItem.class);

        switch (featureID) {
        case TreePackage.DTREE_ITEM__NAME:
        case TreePackage.DTREE_ITEM__OWNED_STYLE:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            updateViewer(dTreeItem);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            break;
        case TreePackage.DTREE_ITEM__OWNED_TREE_ITEMS:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_SWT_LINE_KEY);
            refreshViewer(dTreeItem);
            // The refresh doesn't update swt TreeItem expansion then we must do
            // it
            if (dTreeItem.isExpanded()) {
                toExpand.add(dTreeItem);
            }
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_LINE_KEY);
            break;
        case TreePackage.DTREE_ITEM__EXPANDED:
            if (n.getNewValue() instanceof Boolean) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_LINE_COLAPSE_STATE_KEY);
                final boolean expanded = n.getNewBooleanValue();
                if (expanded) {
                    toExpand.add(dTreeItem);
                } else {
                    toCollapse.add(dTreeItem);
                }
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHANGE_SWT_LINE_COLAPSE_STATE_KEY);
            }
            break;
        default:
            break;
        }
    }

    /**
     * @param n
     * @param notifier
     */
    private void handleDTreeItemNotification(final Notification n, final TreeItemStyle notifier) {
        final int featureID = n.getFeatureID(TreeItemStyle.class);

        switch (featureID) {
        case TreePackage.TREE_ITEM_STYLE__BACKGROUND_COLOR:
        case TreePackage.TREE_ITEM_STYLE__LABEL_COLOR:
        case TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT:
        case TreePackage.TREE_ITEM_STYLE__LABEL_SIZE:
        case TreePackage.TREE_ITEM_STYLE__SHOW_ICON:
            final DTreeItem treeItem = (DTreeItem) notifier.eContainer();
            if (treeItem != null) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
                updateViewer(treeItem);
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            }
            break;
        default:
            break;
        }
    }

    private void handleDTreItemNotification(Notification notification, RGBValues notifier) {
        final int featureID = notification.getFeatureID(TreeItemStyle.class);

        switch (featureID) {
        case ViewpointPackage.RGB_VALUES__BLUE:
        case ViewpointPackage.RGB_VALUES__GREEN:
        case ViewpointPackage.RGB_VALUES__RED:
            EObject notifierContainer = notifier.eContainer();
            if (notifierContainer instanceof TreeItemStyle) {
                TreeItemStyle treeItemStyle = (TreeItemStyle) notifierContainer;
                EObject treeitemStyleContainer = treeItemStyle.eContainer();
                if (treeitemStyleContainer != null) {
                    DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
                    updateViewer(treeitemStyleContainer);
                    DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
                }
            }
            break;
        default:
            break;
        }
    }

    private void refreshViewer(final Object object) {
        if (object != null) {
            toRefreshInViewer.add(object);
        }
    }

    private void updateViewer(final Object object) {
        if (object != null) {
            toUpdateInViewer.add(object);
        }
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        super.resourceSetChanged(event);
        this.toRefreshInViewer = Sets.newLinkedHashSet();
        this.toUpdateInViewer = Sets.newLinkedHashSet();
        this.toCollapse = Sets.newLinkedHashSet();
        this.toExpand = Sets.newLinkedHashSet();
        for (Notification notif : event.getNotifications()) {
            notifyChanged(notif);
        }

        if (this.toRefreshInViewer.size() > 0 || this.toUpdateInViewer.size() > 0 || this.toCollapse.size() > 0 || this.toExpand.size() > 0) {
            final Object[] objectsToUpdateInViewer = Sets.difference(toUpdateInViewer, toRefreshInViewer).toArray(new Object[0]);
            Runnable runnable = new TreeUIUpdaterRunnable(dTreeViewer, toRefreshInViewer, objectsToUpdateInViewer, toExpand, toCollapse);
            PlatformUI.getWorkbench().getDisplay().asyncExec(runnable);
        }
    }

    /**
     * Dispose this {@link TreeUIUpdater}.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        dTreeViewer = null;
        toExpand = null;
        toCollapse = null;
        toRefreshInViewer = null;
        toUpdateInViewer = null;
    }
}
