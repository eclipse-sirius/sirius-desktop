/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Query that get the label color of a {@link TreeItem} , i.e.
 * {@link TreeItem#getForeground()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemLabelColorQuery extends RunnableWithResult.Impl<RGBValues> {

    private final TreeItem treeItem;

    private int index;

    /**
     * Construct a {@link TreeItemLabelColorQuery} to get the label color.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to get the label color
     */
    public TreeItemLabelColorQuery(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * Construct a {@link TreeItemLabelColorQuery} to get the label color.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to get the label color
     * @param index
     *            the index in the {@link TreeItem} for which to get the
     *            background color
     */
    public TreeItemLabelColorQuery(TreeItem treeItem, int index) {
        this.treeItem = treeItem;
        this.index = index;
    }

    /**
     * Overridden to test {@link TreeItem#getForeground()}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void run() {
        Color foreground = treeItem.getForeground(index);
        RGBValues foregroundRGBvalues = VisualBindingManager.getDefault().createRGBvalues(foreground.getRGB());
        setResult(foregroundRGBvalues);
    }

}
