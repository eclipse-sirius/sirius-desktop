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

import java.util.Optional;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.sirius.tree.DTreeItem;

/**
 * A specific adapter to store, temporarily, the depth of a tree item, during an "expand all" action. This adapter is
 * added to a tree item expanded during an "expand all" action. It is then removed when it is used in the
 * {@link ExpandAllTreeItemsChangeTrigger}.
 * 
 * @author Laurent Redor
 */
public class ExpandAllDepthAdapter implements Adapter {

    /**
     * The depth of the corresponding tree item.
     */
    int depth;

    /**
     * Default constructor.
     * 
     * @param depth
     *            The depth of the corresponding tree item.
     */
    protected ExpandAllDepthAdapter(int depth) {
        this.depth = depth;
    }

    /**
     * Create an adapter with the <code>depth</code> and add it to the <code>dTreeItem</code>. If an existing adapter
     * exists, it is removed before adding the new one.
     * 
     * @param dTreeItem
     *            The current {@link DTreeItem}
     * @param depth
     *            The depth to set
     */
    public static void createAdapterOnDTreeItem(DTreeItem dTreeItem, int depth) {
        Optional<Adapter> optionalDepthAdapter = dTreeItem.eAdapters().stream().filter(ExpandAllDepthAdapter.class::isInstance).findFirst();
        if (optionalDepthAdapter.isPresent()) {
            dTreeItem.eAdapters().remove(optionalDepthAdapter.get());
        }
        dTreeItem.eAdapters().add(new ExpandAllDepthAdapter(depth));
    }

    /**
     * Get the depth store in the corresponding adapter in <code>dTreeItem</code>. 0 is returned if there is no existing
     * adapter (it should not occur). The adapter is automatically removed by this method.
     * 
     * @param dTreeItem
     *            The current {@link DTreeItem}
     * @return the depth
     */
    public static Integer getDepth(DTreeItem dTreeItem) {
        int depth = 0;
        Optional<Adapter> optionalDepthAdapter = dTreeItem.eAdapters().stream().filter(ExpandAllDepthAdapter.class::isInstance).findFirst();
        if (optionalDepthAdapter.isPresent()) {
            depth = ((ExpandAllDepthAdapter) optionalDepthAdapter.get()).getDepth();
            dTreeItem.eAdapters().remove(optionalDepthAdapter.get());
        }
        return depth;
    }

    /**
     * Get the depth stored in the adapter.
     * 
     * @return the depth
     */
    public int getDepth() {
        return this.depth;
    }

    @Override
    public void notifyChanged(Notification notification) {
        // Do nothing
    }

    @Override
    public Notifier getTarget() {
        return null;
    }

    @Override
    public void setTarget(Notifier newTarget) {
    }

    @Override
    public boolean isAdapterForType(Object type) {
        return type instanceof ExpandAllTreeItemsChangeTrigger;
    }
}
