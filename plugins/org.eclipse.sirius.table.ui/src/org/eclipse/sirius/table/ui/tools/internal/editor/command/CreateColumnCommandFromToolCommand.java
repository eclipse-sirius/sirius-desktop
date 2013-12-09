/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.command;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * A Recording Command to call
 * tableCommandFactory.buildCreateColumnCommandFromTool.
 * 
 * @author smonnier
 */
public class CreateColumnCommandFromToolCommand extends RecordingCommand {

    /**
     * The column concerned with this action
     */
    private DTargetColumn column;

    private DTable table;

    private ITableCommandFactory tableCommandFactory;

    private CreateTool createTool;

    /**
     * Create a new CreateColumnCommandFromToolCommand.
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param column
     *            current DTargetColumn
     * @param table
     *            current DTable
     * @param tableCommandFactory
     *            current ITableCommandFactory
     * @param createTool
     *            current CreateTool
     */
    public CreateColumnCommandFromToolCommand(TransactionalEditingDomain domain, String label, DTargetColumn column, DTable table, ITableCommandFactory tableCommandFactory, CreateTool createTool) {
        super(domain, label);
        this.column = column;
        this.table = table;
        this.tableCommandFactory = tableCommandFactory;
        this.createTool = createTool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        // The CreateTargetColumnAction can be launch from the table
        EObject target;
        DTable columnContainer;
        if (column != null) {
            target = column.getTarget();
            columnContainer = (DTable) column.eContainer();
        } else {
            target = table.getTarget();
            columnContainer = table;
        }
        tableCommandFactory.buildCreateColumnCommandFromTool(columnContainer, target, createTool).execute();
    }

}
