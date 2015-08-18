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
 * A {@link java.util.Comparator} for {@link DColumn}s.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DColumnSorter extends DTableElementSorter<DColumn> {

    private DLine dLine;

    /**
     * Default constructor.
     * 
     * @param dLine
     *            the {@link DLine} from which sort
     * @param sortDirection
     *            the sort direction
     */
    public DColumnSorter(DLine dLine, int sortDirection) {
        super(sortDirection);
        this.dLine = dLine;
    }

    /**
     * {@inheritDoc}
     */
    protected String getSortLabel(final DColumn column) {
        Option<DCell> optionalCell = TableHelper.getCell(dLine, column);
        if (optionalCell.some()) {
            return optionalCell.get().getLabel();
        } else {
            return ""; //$NON-NLS-1$
        }
    }

}
