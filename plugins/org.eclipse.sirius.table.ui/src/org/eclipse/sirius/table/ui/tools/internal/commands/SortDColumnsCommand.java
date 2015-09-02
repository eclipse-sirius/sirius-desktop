/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.commands;

import java.util.Comparator;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.swt.SWT;

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
     * @param dTable
     *            the table to modify.
     * @param dLine
     *            the line on which to sort the columns.
     * @param sortDirection
     *            the direction in which to sort. One of SWT.UP or SWT.DOWN.
     */
    public SortDColumnsCommand(TransactionalEditingDomain domain, DTable dTable, DLine dLine, int sortDirection) {
        super(domain, sortDirection == SWT.UP ? Messages.SortDColumnsCommand_ascendingSorting : Messages.SortDColumnsCommand_descendingSorting);
        this.dTable = dTable;
        this.dColumnSorter = new DColumnSorter(dLine, sortDirection);
    }

    @Override
    protected void doExecute() {
        ECollections.sort(dTable.getColumns(), dColumnSorter);
    }
}
