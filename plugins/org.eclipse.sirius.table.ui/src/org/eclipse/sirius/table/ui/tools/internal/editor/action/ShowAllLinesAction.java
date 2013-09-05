/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

/**
 * Action to show all the lines of the tables.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class ShowAllLinesAction extends AbstractTransactionalTableAction {
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
    public ShowAllLinesAction(final DTable dTable, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(dTable, "Show hidden lines", DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.REVEAL_IMG), editingDomain, tableCommandFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        super.run();
        getEditingDomain().getCommandStack().execute(new ShowLineRecordingCommand(getEditingDomain(), "Set " + TablePackage.eINSTANCE.getDLine_Visible().getName() + " values"));
    }

    /**
     * Show all the lines of the table.
     */
    private void showLines() {
        showLines(getTable());
    }

    /**
     * Show all the lines of this line container.
     * 
     * @param lineContainer
     *            The line container
     */
    private void showLines(final LineContainer lineContainer) {
        for (final DLine line : lineContainer.getLines()) {
            getTableCommandFactory().buildSetValue(line, TablePackage.eINSTANCE.getDLine_Visible().getName(), true).execute();
            showLines(line);
        }
    }

    private class ShowLineRecordingCommand extends RecordingCommand {

        public ShowLineRecordingCommand(TransactionalEditingDomain domain, String label) {
            super(domain, label);
        }

        @Override
        protected void doExecute() {
            showLines();
        }

    }
}
