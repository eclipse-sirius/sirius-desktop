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

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Query that get the label font format of a {@link TreeItem} , i.e.
 * {@link TreeItem#getFont()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemLabelFontFormatQuery extends RunnableWithResult.Impl<FontFormat> {

    private final TreeItem treeItem;

    private int index;

    /**
     * Construct a {@link TreeItemLabelFontFormatQuery} to get the font format
     * of a TreeItem.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to get the font format
     */
    public TreeItemLabelFontFormatQuery(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * Construct a {@link TreeItemLabelFontFormatQuery} to get the font format
     * of a TreeItem.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to get the font format
     * 
     * @param index
     *            the index in the {@link TreeItem} for which to get the font
     *            format
     */
    public TreeItemLabelFontFormatQuery(TreeItem treeItem, int index) {
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
            int style = fontData[0].getStyle();
            switch (style) {
            case SWT.NORMAL:
                setResult(FontFormat.NORMAL_LITERAL);
                break;
            case SWT.BOLD:
                setResult(FontFormat.BOLD_LITERAL);
                break;
            case SWT.ITALIC:
                setResult(FontFormat.ITALIC_LITERAL);
                break;
            default:
            }
        }
    }
}
