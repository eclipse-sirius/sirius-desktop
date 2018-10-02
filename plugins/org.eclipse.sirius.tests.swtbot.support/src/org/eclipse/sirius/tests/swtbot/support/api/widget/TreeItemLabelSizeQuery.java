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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Query that get the label size of a {@link TreeItem} , i.e.
 * {@link TreeItem#getFont()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemLabelSizeQuery extends RunnableWithResult.Impl<Integer> {

    private final TreeItem treeItem;

    private int index;

    /**
     * Construct a {@link TreeItemLabelSizeQuery} to see if a {@link TreeItem}
     * is expanded.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to check expansion
     */
    public TreeItemLabelSizeQuery(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * Construct a {@link TreeItemLabelSizeQuery} to see if a {@link TreeItem}
     * is expanded.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to check expansion
     * @param index
     *            the index in the {@link TreeItem} for which to get the label
     */
    public TreeItemLabelSizeQuery(TreeItem treeItem, int index) {
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
        Font font = treeItem.getFont(index);
        FontData[] fontData = font.getFontData();
        if (fontData.length > 0) {
            int labelSize = fontData[0].getHeight();
            setResult(labelSize);
        }
    }

}
