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

import org.eclipse.sirius.table.metamodel.table.DColumn;

import com.google.common.base.Preconditions;

/**
 * A class aggregating all the queries (read-only!) having a {@link DColumn} as
 * a starting point.
 * 
 * @author lredor
 * 
 */
public class DColumnQuery {

    private final DColumn column;

    /**
     * Creates a new query.
     * 
     * @param dColumn
     *            the {@link DColumn} to query.
     */
    public DColumnQuery(DColumn dColumn) {
        Preconditions.checkNotNull(dColumn);
        this.column = dColumn;
    }

    /**
     * Return the column index in the table.
     * 
     * @return the column index in the table.
     */
    public int getColumnIndex() {
        return column.getTable().getColumns().indexOf(column);
    }
}
