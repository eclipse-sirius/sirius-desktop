/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.api.query;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;

import com.google.common.base.Preconditions;

/**
 * A class aggregating all the queries (read-only!) having a {@link DTable} as a
 * starting point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DTableQuery {

    private final DTable table;

    /**
     * Creates a new query.
     * 
     * @param table
     *            the cell to query.
     */
    public DTableQuery(DTable table) {
        Preconditions.checkNotNull(table);
        this.table = table;
    }

    /**
     * Return all the cells of this table.
     * 
     * @return all the cells of this table.
     */
    public List<DCell> getCells() {
        List<DCell> cells = new ArrayList<DCell>();
        for (DLine line : getAllLines()) {
            cells.addAll(line.getCells());
        }
        return cells;
    }

    /**
     * Return the first cell of a table.
     * 
     * @return the first cell of a table.
     */
    public Option<DCell> getFirstCell() {
        List<DCell> cells = getCells();
        if (cells.isEmpty()) {
            return Options.newNone();
        }
        return Options.newSome(cells.get(0));
    }

    /**
     * Return all the lines (including subLines) of this table.
     * 
     * @return List of all the lines (including subLines) of this table.
     */
    public List<DLine> getAllLines() {
        return getAllLines(table);
    }

    /**
     * Return all the lines (including subLines) of this lineContainer.
     * 
     * @param lineContainer
     *            The concerned lineContainer.
     * @return List of all the lines (including subLines) of the lineContainer.
     */
    protected List<DLine> getAllLines(LineContainer lineContainer) {
        List<DLine> allLines = new ArrayList<DLine>();
        for (DLine dLine : lineContainer.getLines()) {
            allLines.add(dLine);
            allLines.addAll(getAllLines(dLine));
        }
        return allLines;
    }
}
