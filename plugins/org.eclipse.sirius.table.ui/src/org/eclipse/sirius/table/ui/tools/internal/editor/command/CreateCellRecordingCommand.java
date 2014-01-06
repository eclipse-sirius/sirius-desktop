/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * A Recording Command to call tableCommandFactory.buildCreateCellFromTool.
 * 
 * @author lredor
 */
public class CreateCellRecordingCommand extends RecordingCommand {

    private DLine line;

    private DTargetColumn column;

    private Object value;

    private ITableCommandFactory tableCommandFactory;

    /**
     * Create a new CreateCellRecordingCommand.
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param tableCommandFactory
     *            Table command factory.
     * @param line
     *            current DLine.
     * @param column
     *            current DTargetColumn.
     * @param value
     *            value to set.
     */
    public CreateCellRecordingCommand(TransactionalEditingDomain domain, String label, ITableCommandFactory tableCommandFactory, DLine line, DTargetColumn column, Object value) {
        super(domain, label);
        this.tableCommandFactory = tableCommandFactory;
        this.line = line;
        this.column = column;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        final Command command = tableCommandFactory.buildCreateCellFromTool(line, column, value);
        if (command.canExecute()) {
            command.execute();
        }
    }

}
