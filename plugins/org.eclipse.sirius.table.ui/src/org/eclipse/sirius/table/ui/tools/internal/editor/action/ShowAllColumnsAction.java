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

import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.command.TableCommandFactorySetValueRecordingCommand;

/**
 * Action to show all the columns of the tables.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
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
        super(dTable, "Show hidden columns", DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.REVEAL_IMG), editingDomain, tableCommandFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        super.run();
        // new NotificationTask(getTable(), Notification.Kind.START,
        // Notification.USER_OPERATION_ON_MANY_ELEMENTS).execute();
        getEditingDomain().getCommandStack().execute(
                new TableCommandFactorySetValueRecordingCommand(getEditingDomain(), "Set " + TablePackage.eINSTANCE.getDColumn_Visible().getName() + " values", getTableCommandFactory(), getTable()
                        .getColumns(), TablePackage.eINSTANCE.getDColumn_Visible().getName(), true));
        // new NotificationTask(getTable(), Notification.Kind.STOP,
        // Notification.USER_OPERATION_ON_MANY_ELEMENTS).execute();
    }
}
