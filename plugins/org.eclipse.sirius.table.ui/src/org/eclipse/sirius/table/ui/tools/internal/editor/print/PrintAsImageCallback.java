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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.description.SystemColors;

/**
 * Callback to print a given displayed control as an image.
 * 
 * @author mchauvin
 */
public class PrintAsImageCallback implements PrintCallback {

    private static final double HEADER_MARGIN_PERCENT = 0.05;

    private static final double FOOTER_MARGIN_PERCENT = 0.05;

    private PageSetup pageSetup;

    private Image imageToPrint;

    private PrintJobParameters printParameters;

    private double scaleFactor;

    private ImageData imageData;

    private ImageHeightBoundsForPage[] heightBounds;

    private Display display;

    private int maxPageHeight;

    private int maxPageWidth;

    private int headerMargin;

    private int footerMargin;

    /**
     * Create a new callback to print a control as an image.
     * 
     * @param pageSetup
     *            the page setup
     * @param control
     *            the control to print
     */
    public PrintAsImageCallback(final PageSetup pageSetup, final Control control) {
        this.pageSetup = pageSetup;
        this.display = control.getDisplay();
        this.imageToPrint = takeSnapshot(control);
    }

    /**
     * Take a snapshot of a given control into an image. The returned image
     * should be disposed.
     * 
     * @param control
     *            the control
     * @return the created snapshot image.
     * 
     */
    private Image takeSnapshot(final Control control) {
        /* necessary to avoid blank shadow, but does not work all the time */
        display.syncExec(new Runnable() {
            public void run() {
                synchronizationWithUIThread();
                control.redraw();
                control.update();
            }

            private void synchronizationWithUIThread() {
                while (display.readAndDispatch()) {
                    // do nothing
                }
            }
        });

        RunnableWithResult<Image> runnable = new RunnableWithResult.Impl<Image>() {
            public void run() {
                Point size = control.getSize();
                Image snap = new Image(display, size.x, size.y);
                GC gc = new GC(control);
                gc.copyArea(snap, 0, 0);
                gc.dispose();
                setResult(snap);
            }
        };
        display.syncExec(runnable);
        return runnable.getResult();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.table.ui.tools.internal.editor.print.SWTPrintHelper.PrintCallback#setParameters(org.eclipse.sirius.viewpoint.table.ui.tools.internal.editor.print.SWTPrintHelper.PrintJobParameters)
     */
    public void setParameters(final PrintJobParameters parameters) {
        this.printParameters = parameters;
        this.imageData = imageToPrint.getImageData();

        computeMaxPageWidth();
        adaptScaleFactor();
        computeMaxPageHeightAndMargin();
        setHeightBounds();
    }

    /**
     * Adapt the scale factor to not clip the image if the width is too big.
     */
    private void adaptScaleFactor() {
        final int test = (int) (imageData.width * printParameters.getScaleFactor());
        if (test > maxPageWidth) {
            scaleFactor = (double) maxPageWidth / (double) imageData.width;
        }
    }

    private void setHeightBounds() {
        final int numberOfPages = numberOfPages();

        this.heightBounds = new ImageHeightBoundsForPage[numberOfPages];
        for (int i = 0; i < numberOfPages; i++) {

            final int height = computeHeightForPage(i);

            int src;
            if (i == 0) {
                src = 0;
            } else {
                src = this.heightBounds[i - 1].getDest() + 1;
            }
            this.heightBounds[i] = new ImageHeightBoundsForPage(src, src + height);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.table.ui.tools.internal.editor.print.SWTPrintHelper.PrintCallback#numberOfPages()
     */
    public int numberOfPages() {
        /* return the entire division +1 */
        return (imageData.height / maxPageHeight) + 1;
    }

    /**
     * Compute the maximum height possible for a page.
     * 
     * @return the maximum height possible
     */
    private void computeMaxPageHeightAndMargin() {
        final int max = PageSetup.size(PageSetup.inches(pageSetup.getDimension().getHeight()), printParameters.getPrinter().getDPI().y);
        headerMargin = computeMargin(max, HEADER_MARGIN_PERCENT);
        footerMargin = computeMargin(max, FOOTER_MARGIN_PERCENT);
        maxPageHeight = max - headerMargin - footerMargin;
    }

    private int computeMargin(final int max, final double percent) {
        return (int) (max * percent);
    }

    /**
     * Compute the maximum width possible for a page.
     * 
     * @return the maximum width possible
     */
    private void computeMaxPageWidth() {
        maxPageWidth = PageSetup.size(PageSetup.inches(pageSetup.getDimension().getWidth()), printParameters.getPrinter().getDPI().x);
    }

    private int computeHeightForPage(final int pageIndex) {
        final int numberOfPages = numberOfPages();
        int height = maxPageHeight;
        if (numberOfPages == 1) {
            height = imageData.height;
        } else if (pageIndex == numberOfPages - 1) {
            height = imageData.height % maxPageHeight;
        }
        return height;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.table.ui.tools.internal.editor.print.SWTPrintHelper.PrintCallback#printPage(int)
     */
    public void printPage(final int page) {

        final ImageHeightBoundsForPage bounds = this.heightBounds[page];
        final int srcY = bounds.getScr();
        final int srcHeight = bounds.getDest();
        final int destHeight = bounds.getSize();

        display.syncExec(new Runnable() {
            public void run() {
                /* Get the ImageData and create a new printer Image from it */
                final Image printImage = new Image(printParameters.getPrinter(), imageData);

                /* draw the image in a new GC */
                final GC printGC = new GC(printParameters.getPrinter());
                final Rectangle trim = printParameters.getTrim();
                printGC.drawImage(printImage, 0, srcY, imageData.width, srcHeight, -trim.x, -trim.y + headerMargin, (int) (scaleFactor * imageData.width), (int) (scaleFactor * destHeight));

                drawFooters(printGC, page + 1);

                /* dispose GC and created image */
                printGC.dispose();
                printImage.dispose();
            }

            private void drawFooters(final GC gc, final int page) {
                final Color black = VisualBindingManager.getDefault().getColorFromName(SystemColors.BLACK_LITERAL.getName());
                gc.setForeground(black);

                final Rectangle trim = printParameters.getTrim();

                gc.drawLine(-trim.x, maxPageHeight + headerMargin + 1, maxPageWidth, maxPageHeight + headerMargin + 1);
                gc.drawText("page " + page + "/" + numberOfPages(), -trim.x, maxPageHeight + headerMargin + 5); //$NON-NLS-2$
            }

        });

    }

    /**
     * Dispose the created resources.
     */
    public void dispose() {
        this.imageToPrint.dispose();
    }

    /**
     * Store height bounds of an image for a page.
     * 
     * @author mchauvin
     */
    private static class ImageHeightBoundsForPage {

        private int scr;

        private int dest;

        private int size;

        /**
         * Create a new instance.
         * 
         * @param src
         *            the source
         * @param dest
         *            the destination
         */
        public ImageHeightBoundsForPage(final int src, final int dest) {
            this.scr = src;
            this.dest = dest;
            this.size = dest - src;
        }

        /**
         * Get the source coordinate.
         * 
         * @return the source coordinate
         */
        public int getScr() {
            return scr;
        }

        /**
         * Get the destination coordinate.
         * 
         * @return the destination coordinate
         */
        public int getDest() {
            return dest;
        }

        /**
         * Get the size.
         * 
         * @return the size the size
         */
        public int getSize() {
            return size;
        }

    }

}
