/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.commands;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;

/**
 * A {@link java.util.Comparator} for {@link DLine}s.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DLineSorter extends DTableElementSorter<DLine> {

    private DColumn dColumn;

    /**
     * Default constructor.
     * 
     * @param dColumn
     *            the {@link DColumn} from which sort
     * @param sortDirection
     *            the sort direction
     */
    public DLineSorter(DColumn dColumn, int sortDirection) {
        super(sortDirection);
        this.dColumn = dColumn;
    }

    /**
     * {@inheritDoc}
     */
    protected String getSortLabel(final DLine line) {
        String result = ""; //$NON-NLS-1$
        if (dColumn == null) {
            // The sorted column is the header
            result = line.getLabel();
        } else {
            Option<DCell> optionalCell = TableHelper.getCell(line, dColumn);
            if (optionalCell.some()) {
                result = optionalCell.get().getLabel();
            }
        }
        return result;
    }
}
