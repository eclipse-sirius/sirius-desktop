/*******************************************************************************    
 * Copyright (c) 2009, 2014 Obeo and others. All rights 
 * reserved. This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License 2.0 which accompanies this    
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0  
 *  
 * Contributors: Obeo - initial API and implementation  
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.internal.commands.SortDLinesCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * A {@link Listener} to sort
 * {@link org.eclipse.sirius.table.metamodel.table.DLine}s order according to
 * clicked {@link DColumn}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DLinesSorter implements Listener {

    private TransactionalEditingDomain domain;

    private DTable dTable;

    private DColumn sortedBy;

    private int sortDirection = SWT.NONE;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} to use to update
     *            {@link org.eclipse.sirius.table.metamodel.table.DLine}s order
     *            according to clicked {@link DColumn}.
     * @param dTable
     *            the {@link DTable} to update
     */
    public DLinesSorter(TransactionalEditingDomain domain, DTable dTable) {
        this.domain = domain;
        this.dTable = dTable;
    }

    @Override
    public void handleEvent(final Event e) {
        if (e.widget instanceof TreeColumn) {
            TreeColumn treeColumn = (TreeColumn) e.widget;
            DColumn dColumn = (DColumn) treeColumn.getData(DTableViewerManager.TABLE_COLUMN_DATA);
            if ((sortedBy == null && dColumn == null) || (sortedBy != null && sortedBy.equals(dColumn))) {
                sortDirection = sortDirection == SWT.UP ? SWT.DOWN : SWT.UP;
            } else {
                sortDirection = SWT.UP;
                sortedBy = dColumn;
            }
            Command sortDLinesCmd = new SortDLinesCommand(domain, dTable, sortedBy, sortDirection);
            domain.getCommandStack().execute(sortDLinesCmd);
        }
    }

}
