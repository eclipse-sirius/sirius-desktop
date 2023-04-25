/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.table.business.api.helper.TableVariablesHelper;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Action to launch the createTool of the targetColumn.
 * 
 * @author lredor
 */
public class CreateTargetColumnAction extends AbstractTargetColumnAction<CreateTool> {

    private DTable table;

    /**
     * Constructor.
     * 
     * @param createTool
     *            The tool to do some other actions
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public CreateTargetColumnAction(final CreateTool createTool, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(MessageTranslator.INSTANCE.getMessage(createTool, new IdentifiedElementQuery(createTool).getLabel()),
                DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.CREATE_COLUMN), editingDomain, tableCommandFactory,
                createTool);
    }

    @Override
    public void run() {
        Command cmd = tableCommandFactory.buildCreateColumnCommandFromTool(getContainer(), getTool());
        String label = getText();
        cmd = new CommandWrapper(label, label, cmd);
        getEditingDomain().getCommandStack().execute(cmd);
    }

    private DSemanticDecorator getContainer() {
        DTargetColumn column = getColumn();
        if (column != null) {
            return column;
        } else {
            return getTable();
        }
    }


    @Override
    public boolean canExecute() {
        return TableVariablesHelper.isAxisToolEnable(tool, getContainer());
    }



    /**
     * Return the table.
     * 
     * @return the table
     */
    public DTable getTable() {
        return table;
    }

    /**
     * Set the table on which the tool of this action applied.
     * 
     * @param table
     *            the table to set
     */
    public void setTable(final DTable table) {
        this.table = table;
    }
}
