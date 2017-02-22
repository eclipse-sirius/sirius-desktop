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
 * Common interface for printer helpers.
 * 
 * @author mchauvin
 */
public interface PrintHelper {

    /**
     * Launch a print job. launching is currently done synchronously.
     * 
     * @param name
     *            the job name
     */
    void launchPrintJob(String name);

    /**
     * Dispose the system resources created.
     */
    void dispose();

}
