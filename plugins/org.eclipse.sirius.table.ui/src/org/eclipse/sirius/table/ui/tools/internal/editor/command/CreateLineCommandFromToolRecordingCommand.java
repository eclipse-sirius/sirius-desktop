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

import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * A Recording Command to call
 * tableCommandFactory.buildCreateLineCommandFromTool.
 * 
 * @author smonnier
 */
public class CreateLineCommandFromToolRecordingCommand extends RecordingCommand {

    /**
     * The line concerned with this action
     */
    private DLine line;

    private DTable table;

    private ITableCommandFactory tableCommandFactory;

    private CreateTool createTool;

    /**
     * Create a new CreateLineCommandFromToolRecordingCommand.
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param line
     *            current DLine
     * @param table
     *            current DTable
     * @param tableCommandFactory
     *            current ITableCommandFactory
     * @param createTool
     *            current CreateTool
     */
    public CreateLineCommandFromToolRecordingCommand(TransactionalEditingDomain domain, String label, DLine line, DTable table, ITableCommandFactory tableCommandFactory, CreateTool createTool) {
        super(domain, label);
        this.line = line;
        this.table = table;
        this.tableCommandFactory = tableCommandFactory;
        this.createTool = createTool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        // The CreateLineAction can be launch from the table
        EObject target;
        LineContainer lineContainer;
        if (line != null) {
            target = line.getTarget();
            lineContainer = (LineContainer) line.eContainer();
        } else {
            target = table.getTarget();
            lineContainer = table;
        }
        tableCommandFactory.buildCreateLineCommandFromTool(lineContainer, target, createTool).execute();
    }

}
