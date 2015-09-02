/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.commands.SortDLinesCommand;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.swt.SWT;

/**
 * Action to sort lines by column.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SortLinesByColumnAction extends Action {

    /**
     * The last column use to sort
     */
    private static DColumn sortedBy;

    /**
     * The last sort direction (SWT.NONE, SWT.UP or SWT.DOWN)
     */
    private static int sortDirection = SWT.NONE;

    private final TransactionalEditingDomain domain;

    /**
     * The column concerned with this action (or null for the header column)
     */
    private DColumn column;

    /**
     * The table concerned with this action (must be set if column is null)
     */
    private DTable table;

    /**
     * Default constructor.
     *
     * @param domain
     *            The transactional editing domain
     */
    public SortLinesByColumnAction(final TransactionalEditingDomain domain) {
        super(Messages.SortLinesByColumnAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.SORT_BY_COLUMN));
        this.domain = domain;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        super.run();

        if ((sortedBy != null && sortedBy.equals(column)) || (sortedBy == null && column == null)) {
            sortDirection = sortDirection == SWT.UP ? SWT.DOWN : SWT.UP;
        } else {
            sortDirection = SWT.UP;
            sortedBy = column;
        }
        Command sortDLinesCmd = new SortDLinesCommand(domain, table, sortedBy, sortDirection);
        domain.getCommandStack().execute(sortDLinesCmd);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Set the column on which this action applied.
     *
     * @param column
     *            the column to set
     */
    public void setColumn(final DColumn column) {
        this.column = column;
        if (column != null) {
            setTable(TableHelper.getTable(column));
        }
    }

    /**
     * Set the table on which this action applied.
     *
     * @param table
     *            the table to set
     */
    public void setTable(final DTable table) {
        this.table = table;
    }

}
