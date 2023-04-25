/*******************************************************************************
 * Copyright (c) 2012, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.commands;

import java.util.Comparator;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.swt.SWT;

/**
 * A Command to sort {@link DLine}s order according to a {@link DColumn}.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SortDLinesCommand extends RecordingCommand {

    private DTable dTable;

    private Comparator<DLine> dLineSorter;

    /**
     * Default constructor.
     *
     * @param domain
     *            the {@link TransactionalEditingDomain} on which this command
     *            will be executed
     * @param dTable
     *            the {@link DTable}
     * @param sortDirection
     *            the sort Direction
     * @param dColumn
     *            the {@link DColumn}
     */
    public SortDLinesCommand(TransactionalEditingDomain domain, DTable dTable, DColumn dColumn, int sortDirection) {
        super(domain, sortDirection == SWT.UP ? Messages.SortDLinesCommand_ascendingSorting : Messages.SortDLinesCommand_descendingSorting);
        this.dTable = dTable;
        this.dLineSorter = new DLineSorter(dColumn, sortDirection);
    }

    @Override
    protected void doExecute() {
        sortLinesBy(dTable);
    }

    /**
     * Sort the lines of the lineContainer by the alphabetical order of the
     * label of the cells of the column.
     *
     * @param lineContainer
     *            The lineContainer (table or line)
     */
    private void sortLinesBy(final LineContainer lineContainer) {
        if (!lineContainer.getLines().isEmpty()) {
            ECollections.sort(lineContainer.getLines(), dLineSorter);

            // Sort the sub-lines
            for (final DLine line : lineContainer.getLines()) {
                sortLinesBy(line);
            }
        }
    }
}
