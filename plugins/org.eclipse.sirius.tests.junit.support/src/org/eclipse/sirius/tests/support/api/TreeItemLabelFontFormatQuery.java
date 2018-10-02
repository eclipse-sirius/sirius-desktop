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
package org.eclipse.sirius.tests.support.api;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Query that get the label font format of a {@link TreeItem} , i.e.
 * {@link TreeItem#getFont()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemLabelFontFormatQuery extends RunnableWithResult.Impl<List<FontFormat>> {

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
        List<FontFormat> treeItemLabelFormat = new ArrayList<FontFormat>();
        // Search italic and bold status in FontData
        Font font = treeItem.getFont(index);
        FontData[] fontData = font.getFontData();
        if (fontData.length > 0) {
            int style = fontData[0].getStyle();
            switch (style) {
            case SWT.BOLD:
                FontFormatHelper.setFontFormat(treeItemLabelFormat, FontFormat.BOLD_LITERAL);
                break;
            case SWT.ITALIC:
                FontFormatHelper.setFontFormat(treeItemLabelFormat, FontFormat.ITALIC_LITERAL);
                break;
            case SWT.BOLD | SWT.ITALIC:
                FontFormatHelper.setFontFormat(treeItemLabelFormat, FontFormat.BOLD_LITERAL);
                FontFormatHelper.setFontFormat(treeItemLabelFormat, FontFormat.ITALIC_LITERAL);
                break;
            default:
                break;
            }
        }
        // Search underline and strike through status in StyleRange
        // Use a constant originally computed with private method
        // org.eclipse.jface.viewers.ViewerRow.getStyleRangesDataKey(int)
        String dataKey = "org.eclipse.jfacestyled_label_key_" + index;
        Object styledData = treeItem.getData(dataKey);
        if (styledData != null) {
            StyleRange[] styledDataArray = (StyleRange[]) styledData;
            StyleRange styleRange = styledDataArray[0];
            if (styleRange.strikeout) {
                FontFormatHelper.setFontFormat(treeItemLabelFormat, FontFormat.STRIKE_THROUGH_LITERAL);
            }
            if (styleRange.underline) {
                FontFormatHelper.setFontFormat(treeItemLabelFormat, FontFormat.UNDERLINE_LITERAL);
            }
        }

        setResult(treeItemLabelFormat);
    }
}
