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
package org.eclipse.sirius.table.tools.internal.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.api.export.TableExportHelper;
import org.eclipse.sirius.table.tools.internal.TablePlugin;
import org.eclipse.sirius.table.tools.internal.export.csv.TableCsvHelper;

/**
 * Helper to export table.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public final class TableExportHelperImpl implements TableExportHelper {

    private static final String EXCEPTION_ON_SAVE_CONTENT = "Error while saving the exported content."; //$NON-NLS-1$

    private TableExportHelperImpl() {
    }

    /**
     * Create a new instance.
     * 
     * @return a new instance
     */
    public static TableExportHelper init() {
        return new TableExportHelperImpl();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.tools.api.export.TableExportHelper#saveContent(java.lang.String,
     *      java.lang.String)
     */
    public void saveContent(final String content, final String fileName) {
        final File file = new File(fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
        } catch (final FileNotFoundException exception) {
            TablePlugin.getPlugin().error(EXCEPTION_ON_SAVE_CONTENT, exception);
        } catch (final IOException exception) {
            TablePlugin.getPlugin().error(EXCEPTION_ON_SAVE_CONTENT, exception);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (final IOException exception) {
                TablePlugin.getPlugin().error(EXCEPTION_ON_SAVE_CONTENT, exception);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.tools.api.export.TableExportHelper#exportToCsv(org.eclipse.sirius.table.metamodel.table.DTable)
     */
    public String exportToCsv(final DTable table) {
        final Iterable<Iterable<String>> tableDescriptors = TableCsvHelper.getTableDescriptor(table);
        final StringBuilder contentBuilder = new StringBuilder();
        for (Iterable<java.lang.String> line : tableDescriptors) {
            for (String cellValue : line) {
                contentBuilder.append(cellValue).append(';');
            }
            contentBuilder.append(TableCsvHelper.EOL_CHARACTER);
        }
        return contentBuilder.toString();
    }

}
