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
package org.eclipse.sirius.table.tools.internal.export.csv;

import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;

/**
 * A Table filler.
 * 
 * @author mchauvin
 */
public class TableFiller {

    private static final char QUOTE_CHARACTER = '\'';

    /**
     * Windows CR character.
     */
    private static final String WINDOWS_CR_CHARACTER = "\r\n"; //$NON-NLS-1$

    /**
     * Character used as delimiter.
     */
    private char delimiter;

    /**
     * Character used to replace the character used as delimiter.
     */
    private char replacementDelimiter;

    /**
     * Constructor.
     * 
     * @param delimiter
     *            .
     * @param replacementDelimiter
     *            .
     */
    public TableFiller(final char delimiter, final char replacementDelimiter) {
        this.delimiter = delimiter;
        this.replacementDelimiter = replacementDelimiter;
    }

    /**
     * Get content label for a line.
     * 
     * @param line
     *            the line
     * @return the content label
     */
    public String getContent(final DLine line) {
        return replaceDelimiter(getDelimiter(), getReplacementDelimiter(), notNullLabel(line.getLabel()));
    }

    /**
     * Get content label for a column.
     * 
     * @param column
     *            a column
     * @return the content label
     */
    public String getContent(final DColumn column) {
        return replaceDelimiter(getDelimiter(), getReplacementDelimiter(), notNullLabel(column.getLabel()));
    }

    private String notNullLabel(String label) {
        return label != null ? label : StringUtil.EMPTY_STRING;
    }

    /**
     * Get content label for a cell.
     * 
     * @param cell
     *            the cell
     * @return if the cell is blank, {@link ICommonConstants#EMPTY_STRING} is
     *         returned.
     */
    public String getContent(final DCell cell) {
        String label = new DCellQuery(cell).getExportableLabel();
        if (null != label) {
            // Use standard replacement policy.
            label = replaceDelimiter(getDelimiter(), getReplacementDelimiter(), label);
            // Replace \r\n character with \n
            if (label.contains(WINDOWS_CR_CHARACTER)) {
                label = replaceDelimiter(WINDOWS_CR_CHARACTER, StringUtil.EMPTY_STRING + TableCsvHelper.EOL_CHARACTER, label);
            }
            // Prepend and append quote for labels that contain EOL (i.e '\n')
            // character.
            if (label.contains(StringUtil.EMPTY_STRING + TableCsvHelper.EOL_CHARACTER)) {
                // Double all quotes in multi-lines case.
                label = replaceDelimiter(String.valueOf(QUOTE_CHARACTER), String.valueOf(QUOTE_CHARACTER) + String.valueOf(QUOTE_CHARACTER), label);
                // Prepend and append quote
                label = QUOTE_CHARACTER + label + QUOTE_CHARACTER;
            }
        } else {
            label = StringUtil.EMPTY_STRING;
        }
        return label;
    }

    /**
     * Replace delimiter.
     * 
     * @param delimiter2
     *            the delimiter to replace
     * @param newDelimiter
     *            the new delimiter
     * @param content
     *            the content in which change should occurs
     * @return the resulting content
     */
    protected String replaceDelimiter(final String delimiter2, final String newDelimiter, final String content) {
        return content.replaceAll(delimiter2, newDelimiter);
    }

    /**
     * Get the delimiter.
     * 
     * @return a not <code>null</code> string.
     */
    protected String getDelimiter() {
        return StringUtil.EMPTY_STRING + delimiter;
    }

    /**
     * Get the replacement delimiter.
     * 
     * @return a not <code>null</code> string.
     */
    protected String getReplacementDelimiter() {
        return StringUtil.EMPTY_STRING + replacementDelimiter;
    }
}
