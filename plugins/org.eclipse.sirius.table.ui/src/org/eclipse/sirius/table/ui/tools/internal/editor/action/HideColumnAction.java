/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.text.MessageFormat;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

/**
 * Hide the column.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class HideColumnAction extends AbstractTransactionalTableAction {

    /** The column concerned with this action. */
    private DColumn column;

    /**
     * Creates a new action.
     *
     * @param dTable
     *            {@link DTable} to use
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public HideColumnAction(final DTable dTable, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(dTable, Messages.HideColumnAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.HIDE_IMG), editingDomain, tableCommandFactory);
    }

    /**
     * Set the column on which the tool of this action applied.
     *
     * @param column
     *            the column to set
     */
    public void setColumn(final DColumn column) {
        this.column = column;
    }

    @Override
    public void run() {
        super.run();
        String label = MessageFormat.format(Messages.Action_setValue, TablePackage.eINSTANCE.getDColumn_Visible().getName());
        Command cmd = getTableCommandFactory().buildSetValue(column, TablePackage.eINSTANCE.getDColumn_Visible().getName(), false);
        cmd = new CommandWrapper(label, label, cmd);
        getEditingDomain().getCommandStack().execute(cmd);
    }
}
