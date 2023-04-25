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
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

/**
 * This action delete the TargetColumn (the corresponding semantic element).<BR>
 *
 * @author lredor
 */
public class DeleteTargetColumnAction extends AbstractTargetColumnAction<DeleteTool> {

    /**
     * Constructor. The deleteTool can be null if there is nothing specific to do.
     *
     * @param deleteTool
     *            The tool to do some other actions
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public DeleteTargetColumnAction(final DeleteTool deleteTool, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(Messages.DeleteTargetColumnAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.DELETE_IMG), editingDomain, tableCommandFactory, deleteTool);
        setToolTipText(Messages.DeleteTargetColumnAction_tooltip);
        DeleteTool tool = getTool();
        if (tool != null) {
            setText(new IdentifiedElementQuery(tool).getLabel());
            setToolTipText(MessageTranslator.INSTANCE.getMessage(tool, tool.getDocumentation()));
        }
    }

    @Override
    public void run() {
        super.run();
        Command cmd = tableCommandFactory.buildDeleteTableElement(getColumn());
        String label = getText();
        cmd = new CommandWrapper(label, label, cmd);
        getEditingDomain().getCommandStack().execute(cmd);
    }

    @Override
    public boolean canExecute() {
        return tool == null // By default (no tool), element can be deleted
                || TableVariablesHelper.isAxisToolEnable(tool, getColumn());
    }

}
