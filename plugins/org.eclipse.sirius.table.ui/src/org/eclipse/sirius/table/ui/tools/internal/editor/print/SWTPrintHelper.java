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

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;

/**
 * An helper to simplify print operation. the print job is started in
 * asynchronous mode. Orientation of paper is only available since SWT 3.5
 * 
 * @author mchauvin
 */
public class SWTPrintHelper implements PrintHelper {

    private Printer printer;

    private PrintCallback callback;

    /* dots per inches adaptation between display and print device */
    private double scaleFactor;

    /* The "trim" is the area of the page that the printer cannot print on */
    private Rectangle trim;

    private Display display;

    /**
     * Create a new instance.
     * 
     * @param data
     *            the printer data
     * @param display
     *            the active display
     * @param callback
     *            the printer callback
     */
    public SWTPrintHelper(final PrinterData data, final Display display, final PrintCallback callback) {
        this.callback = callback;
        this.display = display;

        final RunnableWithResult runnable = new RunnableWithResult.Impl() {
            public void run() {
                setResult(new Printer(data));
            }
        };
        display.syncExec(runnable);
        this.printer = (Printer) runnable.getResult();
        computeScale();
        trim = printer.computeTrim(0, 0, 0, 0);
    }

    private void computeScale() {
        final RunnableWithResult runnable = new RunnableWithResult.Impl() {
            public void run() {
                setResult(display.getDPI());
            }
        };
        display.syncExec(runnable);
        final Point screenDPI = (Point) runnable.getResult();
        final Point printerDPI = printer.getDPI();
        final double dpiScale = ((double) printerDPI.x) / ((double) screenDPI.x);
        this.scaleFactor = dpiScale;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.ui.tools.internal.editor.print.PrintHelper#launchPrintJob(java.lang.String)
     */
    public void launchPrintJob(final String name) {
        launchPrintJobIntern(name);
    }

    /**
     * Launch a print job.
     * 
     * @param name
     *            the job name
     */
    private void launchPrintJobIntern(final String name) {
        if (printer.startJob(name)) {
            callback.setParameters(new PrintJobParameters(printer, scaleFactor, trim));
            final int pageNumber = callback.numberOfPages();
            for (int i = 0; i < pageNumber; i++) {
                launchPrintPage(i);
            }
            printer.endJob();
        }
    }

    private void launchPrintPage(final int page) {
        if (printer.startPage()) {
            callback.printPage(page);
            printer.endPage();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.ui.tools.internal.editor.print.PrintHelper#dispose()
     */
    public void dispose() {
        printer.dispose();
    }

}
