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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewer;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * This class is an EMF Adapter which listen change in the model to update a
 * {@link org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewer}.
 * 
 * @author nlepine
 * 
 */
public class DTreeContentAdapter extends ResourceSetListenerImpl {

    private DTreeViewerManager dTreeViewerManager;

    /** The structured viewer to update. */
    private DTreeViewer dTreeViewer;

    /**
     * Creates a new tree content adapter with the given session.
     * 
     * @param dTreeViewerManager
     *            the structured viewer to update
     */
    public DTreeContentAdapter(DTreeViewerManager dTreeViewerManager) {
        this.dTreeViewerManager = dTreeViewerManager;
        this.dTreeViewer = (DTreeViewer) dTreeViewerManager.getTreeViewer();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isPostcommitOnly()
     */
    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    private boolean isCustom(Notification notif) {
        return notif.getEventType() == -1;
    }

    private void notifyChanged(final Notification notification) {
        if (dTreeViewer == null || dTreeViewer.getControl() == null || dTreeViewer.getControl().isDisposed()) {
            if (notification.getNotifier() instanceof EObject) {
                final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(notification.getNotifier());
                if (domain != null) {
                    domain.removeResourceSetListener(this);
                }
            }
            return;
        }

        final Object notifier = notification.getNotifier();

        if (notifier instanceof Resource) {
            final Resource resource = (Resource) notifier;

            // Indicates that at least one description file has changed or was
            // reloaded
            if (SiriusUtil.DESCRIPTION_MODEL_EXTENSION.equals(resource.getURI().fileExtension())) {
                if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_MODIFIED || notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED) {
                    dTreeViewerManager.setDescriptionFileChanged(true);
                }
            }
        }

        if (isChangeAboutDTreeModel(notification)) {
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
    }

    /**
     * Checks if the specified {@link Notification} is about a change a a
     * {@link DTree}.
     * 
     * @param notification
     *            the specified {@link Notification}
     * @return true if the specified {@link Notification} is about a change a a
     *         {@link DTree}, false else
     */
    private boolean isChangeAboutDTreeModel(final Notification notification) {
        boolean isImpactingNotification = false;
        Object notifier = notification.getNotifier();
        if (notifier instanceof EObject) {
            EObject eObject = (EObject) notifier;
            while (eObject != null && !isImpactingNotification) {
                if (eObject instanceof DTree) {
                    isImpactingNotification = true;
                } else {
                    eObject = eObject.eContainer();
                }
            }
        }
        return isImpactingNotification;
    }

    /**
     * @param n
     * @param notifier
     */
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

    /**
     * @param n
     * @param notifier
     */
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
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    if (dTreeViewer != null && dTreeViewer.getControl() != null && !dTreeViewer.getControl().isDisposed()) {
                        dTreeViewer.setExpandedState(dTreeItem, dTreeItem.isExpanded());
                    }
                }
            });
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_LINE_KEY);
            break;
        case TreePackage.DTREE_ITEM__EXPANDED:
            if (n.getNewValue() instanceof Boolean) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_LINE_COLAPSE_STATE_KEY);
                final boolean expanded = n.getNewBooleanValue();
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        if (dTreeViewer != null && dTreeViewer.getControl() != null && !dTreeViewer.getControl().isDisposed()) {
                            dTreeViewer.setExpandedState(dTreeItem, expanded);
                        }
                    }
                });
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
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    if (dTreeViewer != null && dTreeViewer.getControl() != null && !dTreeViewer.getControl().isDisposed()) {
                        dTreeViewer.refresh(object, true);
                    }
                }
            });
        }
    }

    private void updateViewer(final Object object) {
        if (object != null) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    if (dTreeViewer != null && dTreeViewer.getControl() != null && !dTreeViewer.getControl().isDisposed()) {
                        dTreeViewer.update(object, null);
                    }
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        super.resourceSetChanged(event);
        for (Notification notif : Iterables.filter(event.getNotifications(), Notification.class)) {
            if (!isCustom(notif)) {
                notifyChanged(notif);
            }
        }
    }
}
