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

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * A Recording Command to call tableCommandFactory.buildDeleteTableElement.
 * 
 * @author smonnier
 */
public class DeleteElementRecordingCommand extends RecordingCommand {

    ITableCommandFactory tableCommandFactory;

    DTableElement elementToDelete;

    /**
     * Create a new DeleteElementRecordingCommand.
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param tableCommandFactory
     *            current ITableCommandFactory
     * @param elementToDelete
     *            DTableElement to delete
     */
    public DeleteElementRecordingCommand(TransactionalEditingDomain domain, String label, ITableCommandFactory tableCommandFactory, DTableElement elementToDelete) {
        super(domain, label);
        this.tableCommandFactory = tableCommandFactory;
        this.elementToDelete = elementToDelete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        tableCommandFactory.buildDeleteTableElement(elementToDelete).execute();
    }

}
