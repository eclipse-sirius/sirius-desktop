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

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

/**
 * Parameters of the print job.
 * 
 * @author mchauvin
 */
public class PrintJobParameters {

    private Printer printer;

    private double scaleFactor;

    private Rectangle trim;

    /**
     * Create new parameters.
     * 
     * @param printer
     *            the printer
     * @param scaleFactor
     *            the scale factor
     * @param trim
     *            area of the page that the printer cannot print
     */
    public PrintJobParameters(final Printer printer, final double scaleFactor, final Rectangle trim) {
        this.printer = printer;
        this.scaleFactor = scaleFactor;
        this.trim = trim;
    }

    /**
     * Get the printer.
     * 
     * @return the printer
     */
    public Printer getPrinter() {
        return printer;
    }

    /**
     * Get the scale factor.
     * 
     * @return the scaleFactor
     */
    public double getScaleFactor() {
        return scaleFactor;
    }

    /**
     * Get the trim.
     * 
     * @return the trim
     */
    public Rectangle getTrim() {
        return trim;
    }

}
