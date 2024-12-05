/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.api.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A helper class to build and print a table-formatter report in text mode. Useful only to present debugging
 * information.
 * 
 * @author pcdavid
 */
@SuppressWarnings("nls")
public class TabularReport {
    private final List<String> headers;

    private final List<List<String>> lines;

    /**
     * Default constructor.
     * 
     * @param headers
     *            List of header names.
     */
    public TabularReport(String... headers) {
        this.headers = new ArrayList<>(Arrays.asList(headers));
        this.lines = new ArrayList<>();
    }

    /**
     * Add one line for each {@link String} of data list.
     * 
     * @param data
     *            The list of lines to add.
     */
    public void addLine(List<String> data) {
        this.lines.add(data);
    }

    /**
     * Add one line for each {@link String} of data list.
     * 
     * @param data
     *            The list of lines to add.
     */
    public void addLine(String... data) {
        addLine(new ArrayList<>(Arrays.asList(data)));
    }

    /**
     * Return a string representation of the {@link TabularReport}.
     *
     * @return a string representation of the {@link TabularReport}.
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        int[] width = computeColumnsWidth();
        int totalWidth = computeTotalTableWidth(width);

        printSeparator(sb, totalWidth);
        printLine(sb, headers, width);
        printSeparator(sb, totalWidth);
        for (List<String> line : lines) {
            printLine(sb, line, width);
        }
        printSeparator(sb, totalWidth);

        return sb.toString();
    }

    private void printLine(StringBuilder sb, List<String> data, int[] width) {
        int i = 0;
        for (String cell : data) {
            sb.append("| ");
            printPadded(sb, cell, width[i]);
            i += 1;
        }
        sb.append("|");
        sb.append("\n");
    }

    private void printPadded(StringBuilder sb, String str, int width) {
        sb.append(str);
        fill(sb, ' ', width - str.length() + 1);
    }

    private int computeTotalTableWidth(int[] columnsWidth) {
        int totalWidth = 0;
        for (int element : columnsWidth) {
            totalWidth += element + 3;
        }
        totalWidth += 1;
        return totalWidth;
    }

    private int[] computeColumnsWidth() {
        int[] width = new int[headers.size()];
        for (int i = 0; i < width.length; i++) {
            width[i] = headers.get(i).length();
        }
        for (List<String> line : lines) {
            for (int i = 0; i < line.size(); i++) {
                width[i] = Math.max(width[i], line.get(i).length());
            }
        }
        return width;
    }

    private void printSeparator(StringBuilder sb, int width) {
        fill(sb, '-', width);
        sb.append("\n");
    }

    private void fill(StringBuilder sb, char c, int n) {
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
    }
}
