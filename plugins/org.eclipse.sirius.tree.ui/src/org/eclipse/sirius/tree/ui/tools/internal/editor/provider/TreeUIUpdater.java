/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.DRepresentationNotificationFilter;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * A class responsible to update the UI part of a {@link DTree}.
 *
 * @author nlepine
 */
public class TreeUIUpdater extends ResourceSetListenerImpl {

    /** The structured viewer to update. */
    private DTreeViewer dTreeViewer;

    private Set<DTreeItem> toExpands;

    private Set<DTreeItem> toCollapses;

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

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        this.toRefreshInViewer = new LinkedHashSet<>();
        this.toUpdateInViewer = new LinkedHashSet<>();
        this.toCollapses = new LinkedHashSet<>();
        this.toExpands = new LinkedHashSet<>();
        analyseNotifications(event.getNotifications());
        updateDTreeViewer();
    }

    private void analyseNotifications(List<Notification> notifications) {
        for (Notification notification : notifications) {
            analyseNotification(notification);
        }
    }

    private void analyseNotification(Notification notification) {
        Object notifier = notification.getNotifier();
        if (isDTreeItemContainerOwnedTreeItems(notification)) {
            DTreeItemContainer dTreeItemContainer = (DTreeItemContainer) notifier;
            analyseDTreeItemContainer(dTreeItemContainer);
        } else if (notification.getFeature() == ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET && notifier instanceof DTree) {
            toRefreshInViewer.add(notifier);
        } else if (notification.getFeature() == ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__NAME || notification.getFeature() == TreePackage.Literals.DTREE_ITEM__OWNED_STYLE) {
            toUpdateInViewer.add(notifier);
        } else if (isExpansionChange(notification)) {
            DTreeItem dTreeItem = (DTreeItem) notifier;
            analyseExpansion(dTreeItem);
        } else if (isTreeItemStyleAttributeChange(notification)) {
            TreeItemStyle treeItemStyle = (TreeItemStyle) notifier;
            DTreeItem dTreeItem = (DTreeItem) treeItemStyle.eContainer();
            if (dTreeItem != null) {
                toUpdateInViewer.add(dTreeItem);
            }
        } else if (notification.getNotifier() instanceof TreeItemStyle && isRGBValuesChange(notification)) {
            toUpdateInViewer.add(notification.getNotifier());
        }
    }

    private boolean isDTreeItemContainerOwnedTreeItems(Notification notification) {
        return notification.getFeature() == TreePackage.Literals.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS;
    }

    private void analyseDTreeItemContainer(DTreeItemContainer dTreeItemContainer) {
        toRefreshInViewer.add(dTreeItemContainer);
        if (dTreeItemContainer instanceof DTreeItem) {
            DTreeItem dTreeItem = (DTreeItem) dTreeItemContainer;
            // By default the refresh only add DTreeItem collapsed by
            // default,
            // but we can't do this assumption.
            if (dTreeItem.isExpanded()) {
                toExpands.add(dTreeItem);
                analyseExpansionStateOfCreatedChildren(dTreeItem.getOwnedTreeItems());
            }
        }
    }

    private void analyseExpansionStateOfCreatedChildren(Collection<DTreeItem> ownedTreeItems) {
        for (DTreeItem dTreeItem : ownedTreeItems) {
            if (dTreeItem.isExpanded()) {
                toExpands.add(dTreeItem);
                analyseExpansionStateOfCreatedChildren(dTreeItem.getOwnedTreeItems());
            }
        }
    }

    private boolean isExpansionChange(Notification notification) {
        return notification.getFeature() == TreePackage.Literals.DTREE_ITEM__EXPANDED;
    }

    private void analyseExpansion(DTreeItem dTreeItem) {
        if (dTreeItem.isExpanded()) {
            toExpands.add(dTreeItem);
        } else {
            toCollapses.add(dTreeItem);
        }
    }

    private boolean isTreeItemStyleAttributeChange(Notification notification) {
        return notification.getFeature() != StylePackage.Literals.STYLE_DESCRIPTION && notification.getFeature() != ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES
                && notification.getNotifier() instanceof TreeItemStyle;
    }

    private boolean isRGBValuesChange(Notification notification) {
        return notification.getFeature() instanceof EAttribute && ((EAttribute) notification.getFeature()).getEType() == ViewpointPackage.Literals.RGB_VALUES;
    }

    private void updateDTreeViewer() {
        if (!toRefreshInViewer.isEmpty() || !toUpdateInViewer.isEmpty() || !toCollapses.isEmpty() || !toExpands.isEmpty()) {
            LinkedHashSet<Object> objectsToUpdateInViewer = new LinkedHashSet<>(toUpdateInViewer);
            objectsToUpdateInViewer.removeAll(toRefreshInViewer);
            Runnable runnable = new TreeUIUpdaterRunnable(dTreeViewer, toRefreshInViewer, objectsToUpdateInViewer.toArray(new Object[0]), toExpands, toCollapses);
            EclipseUIUtil.displayAsyncExec(runnable);
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
        toExpands = null;
        toCollapses = null;
        toRefreshInViewer = null;
        toUpdateInViewer = null;
    }
}
