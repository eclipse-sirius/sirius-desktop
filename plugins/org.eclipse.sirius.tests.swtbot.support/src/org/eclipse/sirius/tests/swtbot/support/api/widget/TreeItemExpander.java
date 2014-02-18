/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Expands/Collapses a {@link TreeItem}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemExpander implements Runnable {

    private final TreeItem treeItem;

    private final boolean expand;

    /**
     * Construct a {@link TreeItemExpander} to expand or collapse a
     * {@link TreeItem}.
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @param expand
     *            true if we should expand, false if we should collapse
     */
    public TreeItemExpander(TreeItem treeItem, boolean expand) {
        this.treeItem = treeItem;
        this.expand = expand;
    }

    /**
     * Overridden to test {@link TreeItem#getExpanded()}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void run() {
        treeItem.setExpanded(expand);
        Event event = new Event();
        event.item = treeItem;
        treeItem.getParent().notifyListeners(expand ? SWT.Expand : SWT.Collapse, event);
    }
}
