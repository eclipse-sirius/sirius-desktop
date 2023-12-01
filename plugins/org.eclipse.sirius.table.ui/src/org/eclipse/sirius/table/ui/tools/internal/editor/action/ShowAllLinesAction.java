/*******************************************************************************
 * Copyright (c) 2008, 2023 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.text.MessageFormat;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

/**
 * Action to show all the lines of the tables.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
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
        super(dTable, Messages.ShowAllLinesAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.REVEAL_IMG), editingDomain, tableCommandFactory);
    }

    @Override
    public void run() {
        super.run();
        CompoundCommand compoundCommand = new CompoundCommand(MessageFormat.format(Messages.Action_setValues, TablePackage.eINSTANCE.getDLine_Visible().getName()));
        showLines(compoundCommand, getTable());
        getEditingDomain().getCommandStack().execute(compoundCommand);
    }


    /**
     * Show all the lines of this line container.
     *
     * @param compoundCommand
     *
     * @param lineContainer
     *            The line container
     */
    private void showLines(CompoundCommand compoundCommand, final LineContainer lineContainer) {
        for (final DLine line : lineContainer.getLines()) {
            Command cmd = getTableCommandFactory().buildSetValue(line, TablePackage.eINSTANCE.getDLine_Visible().getName(), true);
            compoundCommand.append(cmd);
            showLines(compoundCommand, line);
        }
    }

}
