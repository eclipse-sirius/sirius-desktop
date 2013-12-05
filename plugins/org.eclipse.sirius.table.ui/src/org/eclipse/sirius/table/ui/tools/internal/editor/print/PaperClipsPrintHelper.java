/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.print;

import org.eclipse.nebula.paperclips.core.ImagePrint;
import org.eclipse.nebula.paperclips.core.LinePrint;
import org.eclipse.nebula.paperclips.core.PaperClips;
import org.eclipse.nebula.paperclips.core.Print;
import org.eclipse.nebula.paperclips.core.PrintJob;
import org.eclipse.nebula.paperclips.core.ScalePrint;
import org.eclipse.nebula.paperclips.core.border.LineBorder;
import org.eclipse.nebula.paperclips.core.grid.DefaultGridLook;
import org.eclipse.nebula.paperclips.core.grid.GridColumn;
import org.eclipse.nebula.paperclips.core.grid.GridPrint;
import org.eclipse.nebula.paperclips.core.page.PageDecoration;
import org.eclipse.nebula.paperclips.core.page.PageNumberPageDecoration;
import org.eclipse.nebula.paperclips.core.page.PagePrint;
import org.eclipse.nebula.paperclips.core.page.SimplePageDecoration;
import org.eclipse.nebula.paperclips.core.text.TextPrint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * An helper to handle print with paperclips library.
 * 
 * @author mchauvin
 */
public class PaperClipsPrintHelper implements PrintHelper {

    private PrinterData printerData;

    private Control control;

    /**
     * Create a new PaperClips helper.
     * 
     * @param printerData
     *            the printer data
     * @param control
     *            the control to print
     */
    public PaperClipsPrintHelper(final PrinterData printerData, final Control control) {
        this.printerData = printerData;
        this.control = control;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.ui.tools.internal.editor.print.PrintHelper#launchPrintJob(java.lang.String)
     */
    public void launchPrintJob(final String name) {
        final Print print = createPrint(name);
        PaperClips.print(new PrintJob(name, print).setMargins(72), printerData);

    }

    private Print createPrint(final String name) {

        /* body */
        final Print body = getBody();
        /* header and footer */
        final PageDecoration header = getHeader(name);
        final PageDecoration footer = getFooter();

        return new PagePrint(header, body, footer);
    }

    private Print getBody() {
        final Tree tree = findTree();

        /* Create GridPrint with all columns at default size. */
        final DefaultGridLook look = new DefaultGridLook();
        look.setCellBorder(new LineBorder());
        final RGB background = tree.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND).getRGB();
        look.setHeaderBackground(background);
        look.setFooterBackground(background);

        final GridPrint grid = new GridPrint(look);

        /* Add header and footer to match table column names. */

        final TreeColumn[] columns = tree.getColumns();
        for (final TreeColumn col : columns) {
            /*
             * Add the column to the grid with alignment applied, default width,
             * no weight
             */
            grid.addColumn(new GridColumn(col.getAlignment(), SWT.DEFAULT, 0));

            final Print cell = createCell("", col.getImage(), col.getText(), SWT.CENTER);
            grid.addHeader(cell);
        }

        /* Add content rows */
        createTreeItemRows("", grid, tree.getItems(), columns);
        return new ScalePrint(grid);

    }

    private Tree findTree() {
        Tree tree = null;
        if (control instanceof Tree) {
            tree = (Tree) control;
        } else if (control instanceof Composite && ((Composite) control).getChildren()[0] instanceof Tree) {
            tree = (Tree) ((Composite) control).getChildren()[0];
        }
        return tree;
    }

    private void createTreeItemRows(final String offset, final GridPrint grid, final TreeItem[] items, final TreeColumn[] columns) {
        for (TreeItem item : items) {
            createTreeItemrow(offset, grid, item, columns);
        }
    }

    private void createTreeItemrow(final String offset, final GridPrint grid, final TreeItem item, final TreeColumn[] columns) {
        for (int i = 0; i < columns.length; i++) {
            grid.add(createCell(offset, item.getImage(i), item.getText(i), columns[i].getAlignment()));
        }
        final String newOffset = offset + "   ";
        createTreeItemRows(newOffset, grid, item.getItems(), columns);
    }

    private Print createCell(final String offset, final Image image, final String text, final int align) {
        if (image == null) {
            return new TextPrint(text, align);
        }

        final GridPrint grid = new GridPrint("p, p, d");
        grid.add(new TextPrint(offset));
        grid.add(new ImagePrint(image.getImageData(), image.getDevice().getDPI()));
        grid.add(new TextPrint(text, align));
        return grid;
    }

    private PageDecoration getHeader(final String name) {
        final GridPrint headerGrid = new GridPrint("d:g");
        headerGrid.add(new TextPrint(name));
        headerGrid.add(new LinePrint(SWT.HORIZONTAL), GridPrint.REMAINDER);
        return new SimplePageDecoration(headerGrid);
    }

    private PageDecoration getFooter() {
        return new PageNumberPageDecoration();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.ui.tools.internal.editor.print.PrintHelper#dispose()
     */
    public void dispose() {
        // do nothing
    }

}
