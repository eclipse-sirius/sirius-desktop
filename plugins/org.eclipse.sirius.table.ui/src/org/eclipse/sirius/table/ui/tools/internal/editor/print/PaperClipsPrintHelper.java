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

import org.eclipse.sirius.table.ui.tools.internal.paperclips.ImagePrint;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.LinePrint;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.PaperClips;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.Print;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.PrintJob;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.ScalePrint;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.border.LineBorder;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.grid.DefaultGridLook;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.grid.GridColumn;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.grid.GridPrint;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.page.PageDecoration;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.page.PageNumberPageDecoration;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.page.PagePrint;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.page.SimplePageDecoration;
import org.eclipse.sirius.table.ui.tools.internal.paperclips.text.TextPrint;
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

    private final PrinterData printerData;

    private final Control control;

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

    @Override
    public void launchPrintJob(final String name) {
        Print print = createPrint(name);
        PaperClips.print(new PrintJob(name, print).setMargins(72), printerData);
    }

    private Print createPrint(final String name) {
        Print body = getBody();
        PageDecoration header = getHeader(name);
        PageDecoration footer = getFooter();
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

            final Print cell = createCell("", col.getImage(), col.getText(), SWT.CENTER); //$NON-NLS-1$
            grid.addHeader(cell);
        }

        /* Add content rows */
        createTreeItemRows("", grid, tree.getItems(), columns); //$NON-NLS-1$
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
        final String newOffset = offset + "   "; //$NON-NLS-1$
        createTreeItemRows(newOffset, grid, item.getItems(), columns);
    }

    private Print createCell(final String offset, final Image image, final String text, final int align) {
        if (image == null) {
            return new TextPrint(text, align);
        }

        final GridPrint grid = new GridPrint("p, p, d"); //$NON-NLS-1$
        grid.add(new TextPrint(offset));
        grid.add(new ImagePrint(image.getImageData(), image.getDevice().getDPI()));
        grid.add(new TextPrint(text, align));
        return grid;
    }

    private PageDecoration getHeader(final String name) {
        final GridPrint headerGrid = new GridPrint("d:g"); //$NON-NLS-1$
        headerGrid.add(new TextPrint(name));
        headerGrid.add(new LinePrint(SWT.HORIZONTAL), GridPrint.REMAINDER);
        return new SimplePageDecoration(headerGrid);
    }

    private PageDecoration getFooter() {
        return new PageNumberPageDecoration();
    }

    @Override
    public void dispose() {
        // do nothing
    }

}
