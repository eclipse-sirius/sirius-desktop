/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * This action delete the TargetColumn (the corresponding semantic element).<BR>
 *
 * @author lredor
 */
public class DeleteTargetColumnAction extends AbstractTargetColumnAction {

    /**
     * Constructor. The deleteTool can be null if there is nothing specific to
     * do.
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
        if (getDeleteTool() != null) {
            setText(new IdentifiedElementQuery(getDeleteTool()).getLabel());
            setToolTipText(getDeleteTool().getDocumentation());
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
        boolean canExecute = true;
        if (getDeleteTool() != null) {
            if (getDeleteTool().getFirstModelOperation() == null) {
                canExecute = false;
            } else {
                if (getDeleteTool().getPrecondition() != null && !StringUtil.isEmpty(getDeleteTool().getPrecondition().trim())) {
                    final IInterpreter interpreter = InterpreterUtil.getInterpreter(getColumn().getTarget());
                    interpreter.setVariable(IInterpreterSiriusVariables.ROOT, TableHelper.getTable(getColumn()).getTarget());
                    interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, getColumn().getTarget());
                    interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((DTable) getColumn().eContainer()).getTarget());
                    try {
                        canExecute = interpreter.evaluateBoolean(getColumn().getTarget(), getDeleteTool().getPrecondition());
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(getDeleteTool(), ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                    }
                    interpreter.unSetVariable(IInterpreterSiriusVariables.ROOT);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
                    interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                }
            }
        }
        return canExecute;
    }

    /**
     * The tool of this action or null if there is no specific delete action.
     *
     * @return The tool of this action or null if there is no specific delete
     *         action
     */
    public DeleteTool getDeleteTool() {
        DeleteTool tool = null;
        final TableTool tableTool = getTool();
        if (tableTool instanceof DeleteTool) {
            tool = (DeleteTool) tableTool;
        }
        return tool;
    }
}
