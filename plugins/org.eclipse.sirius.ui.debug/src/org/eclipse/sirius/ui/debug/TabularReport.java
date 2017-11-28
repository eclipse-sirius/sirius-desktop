/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * A helper class to build and print a table-formatter report in text mode.
 * Useful only to present debugging information.
 * 
 * @author pcdavid
 */
public class TabularReport {
    private final List<String> headers;

    private final List<List<String>> lines;

    public TabularReport(String... headers) {
        this.headers = Lists.newArrayList(headers);
        this.lines = new ArrayList<>();
    }

    public void addLine(List<String> data) {
        this.lines.add(data);
    }

    public void addLine(String... data) {
        addLine(Lists.newArrayList(data));
    }

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
