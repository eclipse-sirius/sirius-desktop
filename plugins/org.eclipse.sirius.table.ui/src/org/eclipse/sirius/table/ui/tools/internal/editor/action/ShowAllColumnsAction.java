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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

/**
 * Action to show all the columns of the tables.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ShowAllColumnsAction extends AbstractTransactionalTableAction {

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
    public ShowAllColumnsAction(final DTable dTable, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(dTable, Messages.ShowAllColumnsAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.REVEAL_IMG), editingDomain, tableCommandFactory);
    }

    @Override
    public void run() {
        super.run();
        String commandLabel = MessageFormat.format(Messages.Action_setValues, TablePackage.eINSTANCE.getDColumn_Visible().getName());
        String name = TablePackage.eINSTANCE.getDColumn_Visible().getName();
        CompoundCommand compoundCommand = new CompoundCommand(commandLabel);
        for (EObject instance : getTable().getColumns()) {
            Command cmd = getTableCommandFactory().buildSetValue(instance, name, true);
            compoundCommand.append(cmd);
        }
        getEditingDomain().getCommandStack().execute(compoundCommand);
    }
}
