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
package org.eclipse.sirius.table.ui.tools.internal.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;

import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;

/**
 * A Command to sort {@link DColumn}s order.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SortDColumnsCommand extends RecordingCommand {

    private DTable dTable;

    private Comparator<DColumn> dColumnSorter;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which this command
     *            will be executed
     */
    public SortDColumnsCommand(TransactionalEditingDomain domain, DTable dTable, DLine dLine, int sortDirection) {
        super(domain, (sortDirection == SWT.UP ? "Ascending " : "Descending ") + "columns sorting");
        this.dTable = dTable;
        this.dColumnSorter = new DColumnSorter(dLine, sortDirection);
    }

    @Override
    protected void doExecute() {
        List<DColumn> dColumns = new ArrayList<DColumn>(dTable.getColumns());
        Collections.sort(dColumns, dColumnSorter);
        dTable.getColumns().clear();
        // We add each DColumn instead of doing a addAll() because otherwise on
        // sorting the first swt column take all the editor width
        for (DColumn dColumn : dColumns) {
            dTable.getColumns().add(dColumn);
        }
    }

}
