/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
