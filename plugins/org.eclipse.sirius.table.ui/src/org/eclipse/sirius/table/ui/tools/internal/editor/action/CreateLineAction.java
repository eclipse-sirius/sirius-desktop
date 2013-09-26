/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.command.CreateLineCommandFromToolRecordingCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Action to launch the createTool of the lineContainer (line or table).
 * 
 * @author lredor
 */
public class CreateLineAction extends AbstractLineAction {
    DTable table;

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
    public CreateLineAction(final CreateTool createTool, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(new IdentifiedElementQuery(createTool).getLabel(), DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.CREATE_LINE), editingDomain, tableCommandFactory, createTool);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        super.run();
        getEditingDomain().getCommandStack().execute(new CreateLineCommandFromToolRecordingCommand(getEditingDomain(), getText(), getLine(), table, tableCommandFactory, getCreateTool()));
    }

    private CreateTool getCreateTool() {
        CreateTool tool = null;
        final TableTool tableTool = getTool();
        if (tableTool instanceof CreateTool) {
            tool = (CreateTool) tableTool;
        }
        return tool;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.ui.tools.internal.editor.action.AbstractToolAction#canExecute()
     */
    @Override
    public boolean canExecute() {
        boolean canExecute = true;
        if (getCreateTool() != null) {
            if (getCreateTool().getFirstModelOperation() == null) {
                canExecute = false;
            } else {
                if (getCreateTool().getPrecondition() != null && !StringUtil.isEmpty(getCreateTool().getPrecondition().trim())) {
                    IInterpreter interpreter;
                    if (getLine() != null) {
                        interpreter = InterpreterUtil.getInterpreter(getLine().getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ROOT, TableHelper.getTable(getLine()).getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, getLine().getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((LineContainer) getLine().eContainer()).getTarget());
                    } else {
                        interpreter = InterpreterUtil.getInterpreter(getTable().getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ROOT, getTable().getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, getTable().getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, null);
                    }
                    try {
                        canExecute = interpreter.evaluateBoolean(getLine().getTarget(), getCreateTool().getPrecondition());
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(getCreateTool(), ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
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
