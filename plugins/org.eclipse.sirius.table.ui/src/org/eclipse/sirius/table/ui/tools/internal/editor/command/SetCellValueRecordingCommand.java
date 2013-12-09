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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * A Recording Command to call tableCommandFactory.buildSetCellValueFromTool.
 * 
 * @author smonnier
 */
public class SetCellValueRecordingCommand extends RecordingCommand {

    private DCell cell;

    private Object value;

    private ITableCommandFactory tableCommandFactory;

    /**
     * Create a new SetCellValueRecordingCommand.
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param tableCommandFactory
     *            Table command factory.
     * @param cell
     *            current DCell.
     * @param value
     *            value to set.
     */
    public SetCellValueRecordingCommand(TransactionalEditingDomain domain, String label, ITableCommandFactory tableCommandFactory, DCell cell, Object value) {
        super(domain, label);
        this.tableCommandFactory = tableCommandFactory;
        this.cell = cell;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        final Command command = tableCommandFactory.buildSetCellValueFromTool(cell, value);
        if (command.canExecute()) {
            command.execute();
        }
    }

}
