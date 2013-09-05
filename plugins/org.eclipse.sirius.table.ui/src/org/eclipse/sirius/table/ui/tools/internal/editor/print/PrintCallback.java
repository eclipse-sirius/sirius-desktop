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

/**
 * A print callback.
 * 
 * @author mchauvin
 */
public interface PrintCallback {
    /**
     * Give the callback the print job parameters.
     * 
     * @param parameters
     *            the parameters
     */
    void setParameters(PrintJobParameters parameters);

    /**
     * Get the number of page to print.
     * 
     * @return the number of page
     */
    int numberOfPages();

    /**
     * print a page method. This method will be called for each page to print.
     * 
     * @param page
     *            page;
     */
    void printPage(int page);
}
