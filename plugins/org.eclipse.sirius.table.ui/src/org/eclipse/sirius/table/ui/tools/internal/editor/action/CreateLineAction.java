/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Action to launch the createTool of the lineContainer (line or table).
 * 
 * @author lredor
 */
public class CreateLineAction extends AbstractLineAction {

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
    public CreateLineAction(final CreateTool createTool, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(MessageTranslator.INSTANCE.getMessage(createTool, new IdentifiedElementQuery(createTool).getLabel()),
                DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.CREATE_LINE), editingDomain, tableCommandFactory, createTool);
    }

    @Override
    public void run() {
        super.run();
        EObject target = getTarget();
        LineContainer lineContainer;
        DLine dLine = getLine();
        if (dLine != null) {
            lineContainer = (LineContainer) dLine.eContainer();
        } else {
            lineContainer = table;
        }
        Command cmd = tableCommandFactory.buildCreateLineCommandFromTool(lineContainer, target, getCreateTool());
        String label = getText();
        cmd = new CommandWrapper(label, label, cmd);
        getEditingDomain().getCommandStack().execute(cmd);
    }

    private CreateTool getCreateTool() {
        CreateTool tool = null;
        final TableTool tableTool = getTool();
        if (tableTool instanceof CreateTool) {
            tool = (CreateTool) tableTool;
        }
        return tool;
    }

    private EObject getTarget() {
        DLine dLine = getLine();
        if (dLine != null) {
            return dLine.getTarget();
        } else {
            return getTable().getTarget();
        }
    }

    @Override
    public boolean canExecute() {
        boolean canExecute = true;
        if (getCreateTool() != null) {
            if (getCreateTool().getFirstModelOperation() == null) {
                canExecute = false;
            } else {
                if (getCreateTool().getPrecondition() != null && !StringUtil.isEmpty(getCreateTool().getPrecondition().trim())) {
                    EObject target = getTarget();
                    IInterpreter interpreter = InterpreterUtil.getInterpreter(target);
                    interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, target);
                    if (getLine() != null) {
                        interpreter.setVariable(IInterpreterSiriusVariables.ROOT, TableHelper.getTable(getLine()).getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, ((LineContainer) getLine().eContainer()).getTarget());
                    } else {
                        interpreter.setVariable(IInterpreterSiriusVariables.ROOT, getTable().getTarget());
                        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, null);
                    }
                    try {
                        canExecute = interpreter.evaluateBoolean(target, getCreateTool().getPrecondition());
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
