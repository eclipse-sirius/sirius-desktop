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
 * Query that get the background color of a {@link TreeItem} , i.e.
 * {@link TreeItem#getBackground()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemBackgroundColorQuery extends RunnableWithResult.Impl<RGBValues> {

    private final TreeItem treeItem;

    private int index;

    /**
     * Construct a {@link TreeItemBackgroundColorQuery} to get the background
     * color of a {@link TreeItem} .
     * 
     * @param treeItem
     *            the {@link TreeItem}
     */
    public TreeItemBackgroundColorQuery(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * Construct a {@link TreeItemBackgroundColorQuery} to get the background
     * color of a {@link TreeItem} .
     * 
     * @param treeItem
     *            the {@link TreeItem}
     * @param index
     *            the index in the {@link TreeItem} for which to get the
     *            background color
     */
    public TreeItemBackgroundColorQuery(TreeItem treeItem, int index) {
        this.treeItem = treeItem;
        this.index = index;
    }

    /**
     * Overridden to test {@link TreeItem#getExpanded()}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void run() {
        Color background = treeItem.getBackground(index);
        RGBValues backgroundRGBvalues = VisualBindingManager.getDefault().createRGBvalues(background.getRGB());
        setResult(backgroundRGBvalues);
    }

}
