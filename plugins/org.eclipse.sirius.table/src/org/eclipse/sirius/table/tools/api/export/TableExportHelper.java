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
package org.eclipse.sirius.table.tools.api.export;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.internal.export.TableExportHelperImpl;

/**
 * Helper to export table.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface TableExportHelper {

    /**
     * The shared instance.
     */
    TableExportHelper INSTANCE = TableExportHelperImpl.init();

    /**
     * Save content to file.
     * 
     * @param content
     *            the content to write
     * @param fileName
     *            the file name
     */
    void saveContent(String content, String fileName);

    /**
     * Export to CSV a table.
     * 
     * @param table
     *            the table to export
     * @return the result as a {@link String}
     */
    String exportToCsv(DTable table);

}
