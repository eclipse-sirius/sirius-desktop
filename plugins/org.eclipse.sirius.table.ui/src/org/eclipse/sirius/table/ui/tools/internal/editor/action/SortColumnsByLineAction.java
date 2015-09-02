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
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.commands.SortDColumnsCommand;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.swt.SWT;

/**
 * Action to sort columns by line. This action is only for CrossTable.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SortColumnsByLineAction extends Action {
    /**
     * The last Line use to sort
     */
    private static DLine sortedBy;

    /**
     * The last sort direction (SWT.NONE, SWT.UP or SWT.DOWN)
     */
    private static int sortDirection = SWT.NONE;

    /**
     * The transactional editing domain
     */
    private final TransactionalEditingDomain domain;

    /**
     * The line concerned with this action
     */
    private DLine line;

    /**
     * Constructor.
     *
     * @param domain
     *            The transactional editing domain
     */
    public SortColumnsByLineAction(final TransactionalEditingDomain domain) {
        super(Messages.SortColumnsByLineAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.SORT_BY_LINE));
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

        if (sortedBy != null && sortedBy.equals(line)) {
            sortDirection = sortDirection == SWT.UP ? SWT.DOWN : SWT.UP;
        } else {
            sortDirection = SWT.UP;
            sortedBy = line;
        }

        final DTable table = TableHelper.getTable(line);

        Command sortDColumnsCmd = new SortDColumnsCommand(domain, table, sortedBy, sortDirection);
        domain.getCommandStack().execute(sortDColumnsCmd);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return TableHelper.getTable(line).getDescription() instanceof CrossTableDescription;
    }

    /**
     * Set the line on which this action applied.
     *
     * @param line
     *            the line to set
     */
    public void setLine(final DLine line) {
        this.line = line;
    }

}
