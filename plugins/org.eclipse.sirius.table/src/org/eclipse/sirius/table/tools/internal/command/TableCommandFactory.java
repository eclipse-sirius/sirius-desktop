/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.tools.internal.command;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.helper.task.label.InitInterpreterFromParsedVariableTask2;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.internal.helper.task.DeleteDRepresentationElementsTask;
import org.eclipse.sirius.business.internal.helper.task.DeleteWithoutToolTask;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.internal.helper.task.CreateTableTask;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.tools.api.command.AbstractCommandFactory;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.InvalidPermissionCommand;
import org.eclipse.sirius.tools.api.command.NoNullResourceCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A command factory that creates commands that can be undone.
 * 
 * @author lredor
 */
public class TableCommandFactory extends AbstractCommandFactory implements ITableCommandFactory {
    private TaskHelper commandTaskHelper;

    /**
     * Create a new Factory. the autoRefresh is by default deactivated
     * 
     * @param domain
     *            : current editing domain.
     */
    public TableCommandFactory(final TransactionalEditingDomain domain) {
        super(domain);
    }

    /**
     * Create a new Factory. the autoRefresh is by default deactivated
     * 
     * @param domain
     *            : current editing domain.
     * @param modelAccessor
     *            the model Accessor
     */
    public TableCommandFactory(final TransactionalEditingDomain domain, final ModelAccessor modelAccessor) {
        super(domain);
    }

    private IPermissionAuthority getPermissionAuthority() {
        return modelAccessor.getPermissionAuthority();
    }

    /**
     * Returns a command that can delete the specified element.
     * 
     * @param element
     *            the element to delete (a {@link DLine} or a
     *            {@link DTargetColumn}).
     * @return a command that can delete the specified element.
     */
    @Override
    public Command buildDeleteTableElement(final DTableElement element) {
        Command cmd = UnexecutableCommand.INSTANCE;
        if (element instanceof DLine || element instanceof DTargetColumn) {
            if (!getPermissionAuthority().canEditInstance(element)) {
                cmd = new InvalidPermissionCommand(domain, element);
            } else {
                if (!getPermissionAuthority().canEditInstance(element.eContainer())) {
                    cmd = new InvalidPermissionCommand(domain, element.eContainer());
                } else {

                    final SiriusCommand result = new SiriusCommand(domain);
                    DeleteTool deleteTool = getDeleteTool(element);
                    final DTable parentTable = TableHelper.getTable(element);
                    if (deleteTool != null) {
                        addDeleteTableElementFromTool(result, element, deleteTool);
                        addRefreshTask(parentTable, result, deleteTool);
                        cmd = new NoNullResourceCommand(result, element);
                    } else {
                        result.getTasks().add(new DeleteWithoutToolTask(element, modelAccessor, commandTaskHelper));
                        addRefreshTask(parentTable, result, deleteTool);
                        cmd = new NoNullResourceCommand(result, element);
                    }
                }
            }
        }
        return cmd;
    }

    /**
     * Create a command that creates a line.
     * 
     * @param lineContainer
     *            container element in which the command should put the created
     *            line.
     * @param semanticCurrentElement
     *            the semantic current element
     * @param tool
     *            {@link CreateTool} used to build the command.
     * @return a command able to create the line and putting it in the
     *         container, corresponding to the {@link CreateTool}.
     */
    @Override
    public Command buildCreateLineCommandFromTool(final LineContainer lineContainer, final EObject semanticCurrentElement, final CreateTool tool) {
        Command result = UnexecutableCommand.INSTANCE;
        if (!getPermissionAuthority().canEditInstance(lineContainer)) {
            result = new InvalidPermissionCommand(domain, lineContainer);
        } else {
            if (commandTaskHelper.checkPrecondition(semanticCurrentElement, tool)) {
                SiriusCommand createLineCommand = buildCommandFromModelOfTool(semanticCurrentElement, tool, lineContainer);
                addRefreshTask(lineContainer, createLineCommand, tool);
                Option<DRepresentation> dRepresentation = new EObjectQuery(lineContainer).getRepresentation();
                createLineCommand.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(lineContainer.getTarget()), lineContainer.getTarget(), dRepresentation.get()));
                result = createLineCommand;
            }
        }
        return result;
    }

    /**
     * Create a command that creates a column.
     * 
     * @param containerView
     *            container element in which the command should put the created
     *            line.
     * @param semanticCurrentElement
     *            the semantic current element
     * @param tool
     *            {@link CreateTool} used to build the command.
     * @return a command able to create the line and putting it in the
     *         container, corresponding to the {@link CreateTool}.
     */
    @Override
    public Command buildCreateColumnCommandFromTool(final DTable containerView, final EObject semanticCurrentElement, final CreateTool tool) {
        Command result = UnexecutableCommand.INSTANCE;
        if (!getPermissionAuthority().canEditInstance(containerView)) {
            result = new InvalidPermissionCommand(domain, containerView);
        } else {
            if (commandTaskHelper.checkPrecondition(semanticCurrentElement, tool)) {
                SiriusCommand createColumnCommand = buildCommandFromModelOfTool(semanticCurrentElement, tool, containerView);
                // Add the task that creates the element DTargetColumn
                // result.getTasks().add(new CreateDLineTask(tool, result,
                // modelAccessor, lineContainer));
                addRefreshTask(containerView, createColumnCommand, tool);
                Option<DRepresentation> dRepresentation = new EObjectQuery(containerView).getRepresentation();
                createColumnCommand.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(containerView.getTarget()), containerView.getTarget(), dRepresentation.get()));
                result = createColumnCommand;
            }
        }
        return result;
    }

    private DeleteTool getDeleteTool(final DTableElement element) {
        DeleteTool result = null;
        if (element instanceof DLine) {
            result = ((DLine) element).getOriginMapping().getDelete();
        } else if (element instanceof DTargetColumn) {
            final ColumnMapping columnMapping = ((DTargetColumn) element).getOriginMapping();
            if (columnMapping instanceof ElementColumnMapping) {
                result = ((ElementColumnMapping) columnMapping).getDelete();
            }
        }
        return result;
    }

    private void addDeleteTableElementFromTool(final SiriusCommand cmd, final DTableElement element, final DeleteTool deleteTool) {
        final EObject semanticElement = ((DSemanticDecorator) element).getTarget();
        final EObject rootContainer = TableHelper.getTable(element).getTarget();
        // check precondition
        boolean delete = true;
        if (deleteTool.getPrecondition() != null && !StringUtil.isEmpty(deleteTool.getPrecondition().trim())) {
            delete = false;
            final IInterpreter acceleoInterpreter = InterpreterUtil.getInterpreter(semanticElement);
            acceleoInterpreter.setVariable(IInterpreterSiriusVariables.ROOT, rootContainer);
            acceleoInterpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, semanticElement);
            try {
                delete = acceleoInterpreter.evaluateBoolean(semanticElement, deleteTool.getPrecondition());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(deleteTool, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
            }
            acceleoInterpreter.unSetVariable(IInterpreterSiriusVariables.ROOT);
            acceleoInterpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
        }
        if (delete) {
            cmd.getTasks().addAll(buildCommandFromModelOfTool(semanticElement, deleteTool, element.eContainer()).getTasks());
            cmd.getTasks().add(new DeleteDRepresentationElementsTask(modelAccessor, cmd, commandTaskHelper, element));
        } else {
            cmd.getTasks().add(UnexecutableTask.INSTANCE);
        }
    }

    /**
     * Build a command that covers all the model operations corresponding to a
     * the semantic container and a
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription}.
     * 
     * @param semanticCurrentElement
     *            the semantic current Element.
     * @param tool
     *            the
     *            {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription}
     *            .
     * @param containerView
     *            the container View
     * @return a command able to execute the tool.
     */
    protected SiriusCommand buildCommandFromModelOfTool(final EObject semanticCurrentElement, final AbstractToolDescription tool, final EObject containerView) {
        SiriusCommand result = new SiriusCommand(domain);
        if (!getPermissionAuthority().canEditInstance(containerView)) {
            result = new InvalidPermissionCommand(domain, containerView);
        } else {
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            if (tool instanceof DeleteTool) {
                final DeleteTool deleteTool = (DeleteTool) tool;
                if (containerView instanceof DTableElement) {
                    variables.put(TableHelper.getVariable(deleteTool, IInterpreterSiriusVariables.ROOT), TableHelper.getTable(containerView).getTarget());
                } else if (containerView instanceof DTable) {
                    variables.put(TableHelper.getVariable(deleteTool, IInterpreterSiriusVariables.ROOT), ((DTable) containerView).getTarget());
                }
                variables.put(TableHelper.getVariable(deleteTool, IInterpreterSiriusVariables.ELEMENT), semanticCurrentElement);
                // Initialization of the variables
                result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(semanticCurrentElement), uiCallBack));
                // Creation of the tasks to execute the tool
                result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TableHelper.getTable(containerView), semanticCurrentElement, deleteTool.getFirstModelOperation()));
            } else if (tool instanceof CreateTool) {
                final CreateTool creationTool = (CreateTool) tool;
                if (containerView instanceof DTableElement) {
                    variables.put(TableHelper.getVariable(creationTool, IInterpreterSiriusVariables.ROOT), TableHelper.getTable(containerView).getTarget());
                    if (containerView instanceof DLine) {
                        variables.put(TableHelper.getVariable(creationTool, IInterpreterSiriusVariables.CONTAINER), ((DLine) containerView).getTarget());
                    }
                } else if (containerView instanceof DTable) {
                    TableVariable rootVariable = TableHelper.getVariable(creationTool, IInterpreterSiriusVariables.ROOT);
                    if (rootVariable != null) {
                        variables.put(rootVariable, ((DTable) containerView).getTarget());
                    }
                    TableVariable containerVariable = TableHelper.getVariable(creationTool, IInterpreterSiriusVariables.CONTAINER);
                    if (containerVariable != null) {
                        variables.put(containerVariable, ((DTable) containerView).getTarget());
                    }
                }
                TableVariable elementVariable = TableHelper.getVariable(creationTool, IInterpreterSiriusVariables.ELEMENT);
                if (elementVariable != null) {
                    variables.put(elementVariable, semanticCurrentElement);
                }
                // Initialization of the variables
                result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(semanticCurrentElement), uiCallBack));
                // Creation of the tasks to execute the tool
                result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TableHelper.getTable(containerView), semanticCurrentElement, creationTool.getFirstModelOperation()));
            }
        }
        return result;
    }

    /**
     * Build a command that covers all the model operations corresponding to a
     * the {@link DCell currentCell} and the corresponding
     * {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool
     * directEdit tool} or
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     * createCell tool}.
     * 
     * @param currentCell
     *            the current edited cell.
     * @param tool
     *            The table tool (create or edit)
     * @param newValue
     *            the new value for this cell
     * @return a command able to execute the direct Edit Tool.
     */
    private SiriusCommand buildCommandFromCell(final DCell currentCell, final TableTool tool, final Object newValue) {
        SiriusCommand result = new SiriusCommand(domain, "Set cell content");
        if (!getPermissionAuthority().canEditInstance(currentCell)) {
            result = new InvalidPermissionCommand(domain, currentCell);
        } else {
            EObject interpreterContext = currentCell.getTarget();
            if (interpreterContext == null) {
                interpreterContext = currentCell.getLine().getTarget();
            }

            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            variables.put(TableHelper.getVariable(tool, IInterpreterSiriusVariables.ROOT), TableHelper.getTable(currentCell).getTarget());
            variables.put(TableHelper.getVariable(tool, IInterpreterSiriusTableVariables.LINE_SEMANTIC), currentCell.getLine().getTarget());
            if (currentCell.getColumn() instanceof DTargetColumn) {
                variables.put(TableHelper.getVariable(tool, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC), ((DTargetColumn) currentCell.getColumn()).getTarget());
            }
            if (tool instanceof LabelEditTool) {
                variables.put(TableHelper.getVariable(tool, IInterpreterSiriusVariables.ELEMENT), currentCell.getTarget());
            }
            // Initialization of the variables
            result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(interpreterContext), uiCallBack));
            if (tool instanceof LabelEditTool) {
                final LabelEditTool labelEditTool = (LabelEditTool) tool;
                if (labelEditTool.getMask() != null) {
                    /*
                     * First we need to init the mask variables.
                     */
                    final String messageFormat = labelEditTool.getMask().getMask();
                    result.getTasks().add(new InitInterpreterFromParsedVariableTask2(InterpreterUtil.getInterpreter(interpreterContext), messageFormat, newValue));
                }
            }
            if (tool instanceof CreateCellTool) {
                final CreateCellTool createCellTool = (CreateCellTool) tool;
                if (createCellTool.getMask() != null) {
                    /*
                     * First we need to init the mask variables.
                     */
                    final String messageFormat = createCellTool.getMask().getMask();
                    result.getTasks().add(new InitInterpreterFromParsedVariableTask2(InterpreterUtil.getInterpreter(interpreterContext), messageFormat, newValue));
                }
            }
            // Creation of the tasks to execute the tool
            result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TableHelper.getTable(currentCell), interpreterContext, tool.getFirstModelOperation()));
        }
        return result;
    }

    /**
     * Build a command that covers all the model operations corresponding to a
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     * createCell tool} for the current {@link DLine line} and
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn column}.
     * 
     * @param currentLine
     *            the line corresponding to the intersection that need a
     *            creation of a new cell.
     * @param currentColumn
     *            the column corresponding to the intersection that need a
     *            creation of a new cell.
     * @param tool
     *            The create tool
     * @param newValue
     *            the new value for this cell
     * @return a command able to execute the create Tool.
     */
    private SiriusCommand buildCommandFromIntersection(final DLine currentLine, final DTargetColumn currentColumn, final CreateCellTool tool, final Object newValue) {
        SiriusCommand result = new SiriusCommand(domain, "Set cell content");
        if (!getPermissionAuthority().canEditInstance(currentLine)) {
            result = new InvalidPermissionCommand(domain, currentLine);
        } else {
            if (!getPermissionAuthority().canEditInstance(currentColumn)) {
                result = new InvalidPermissionCommand(domain, currentColumn);
            } else {
                EObject interpreterContext = currentLine.getTarget();

                final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
                variables.put(TableHelper.getVariable(tool, IInterpreterSiriusVariables.ROOT), TableHelper.getTable(currentLine).getTarget());
                variables.put(TableHelper.getVariable(tool, IInterpreterSiriusTableVariables.LINE_SEMANTIC), currentLine.getTarget());
                variables.put(TableHelper.getVariable(tool, IInterpreterSiriusTableVariables.COLUMN_SEMANTIC), currentColumn.getTarget());

                // Initialization of the variables
                result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(interpreterContext), uiCallBack));
                if (tool.getMask() != null) {
                    /*
                     * First we need to init the mask variables.
                     */
                    final String messageFormat = tool.getMask().getMask();
                    result.getTasks().add(new InitInterpreterFromParsedVariableTask2(InterpreterUtil.getInterpreter(interpreterContext), messageFormat, newValue));
                }
                // Creation of the tasks to execute the tool
                result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TableHelper.getTable(currentLine), interpreterContext, tool.getFirstModelOperation()));
            }
        }
        return result;
    }

    /**
     * Return the commandTaskHelper.
     * 
     * @return the commandTaskHelper
     */
    public TaskHelper getCommandTaskHelper() {
        return commandTaskHelper;
    }

    /**
     * @return the modelAccessor
     */
    private ModelAccessor getModelAccessor() {
        return modelAccessor;
    }

    /**
     * Set the model accessor.
     * 
     * @param modelAccessor
     *            the modelAccessor to set
     */
    @Override
    public void setModelAccessor(final ModelAccessor modelAccessor) {
        this.modelAccessor = modelAccessor;
        commandTaskHelper = new TaskHelper(modelAccessor, uiCallBack);
    }

    /**
     * Create a command that set the content of a crossTable cell.
     * 
     * @param editedCell
     *            The cell to set
     * @param newValue
     *            the new value for this cell
     * @return a command able to set the content of a cell, corresponding to the
     *         {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool
     *         LabelEditTool} or
     *         {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     *         CreateCellTool}.
     */
    @Override
    public Command buildSetCellValueFromTool(final DCell editedCell, final Object newValue) {
        Command result = UnexecutableCommand.INSTANCE;
        if (!getPermissionAuthority().canEditInstance(editedCell)) {
            result = new InvalidPermissionCommand(domain, editedCell);
        } else {
            if (editedCell.getUpdater() != null) {
                if (editedCell.getUpdater().getDirectEdit() != null) {
                    result = buildCommandFromCell(editedCell, editedCell.getUpdater().getDirectEdit(), newValue);
                    addRefreshTask(TableHelper.getTable(editedCell), (SiriusCommand) result, null);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildCreateCellFromTool(org.eclipse.sirius.table.metamodel.table.DLine,
     *      org.eclipse.sirius.table.metamodel.table.DTargetColumn,
     *      java.lang.Object)
     */
    @Override
    public Command buildCreateCellFromTool(DLine line, DTargetColumn column, Object newValue) {
        Command result = UnexecutableCommand.INSTANCE;
        if (!getPermissionAuthority().canEditInstance(line)) {
            result = new InvalidPermissionCommand(domain, line);
        } else {
            if (!getPermissionAuthority().canEditInstance(column)) {
                result = new InvalidPermissionCommand(domain, column);
            } else {
                final Option<CreateCellTool> optionalCreateCellTool = TableHelper.getCreateCellTool(line, column);
                if (optionalCreateCellTool.some()) {
                    result = buildCommandFromIntersection(line, column, optionalCreateCellTool.get(), newValue);
                    addRefreshTask(TableHelper.getTable(line), (SiriusCommand) result, optionalCreateCellTool.get());
                    Option<DRepresentation> dRepresentation = new EObjectQuery(line).getRepresentation();
                    ((SiriusCommand) result).getTasks().add(
                            new ElementsToSelectTask(optionalCreateCellTool.get(), InterpreterUtil.getInterpreter(line.getTarget()), line.getTarget(), dRepresentation.get()));
                }
            }
        }
        return result;
    }

    /**
     * Create a command that is able to create a table.
     * 
     * @param description
     *            the tool that describes how to create the table.
     * @param semanticElement
     *            the element from which the table will be created.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of
     *            {@link DTable} creation
     * @return a command that is able to create a table.
     */
    public DCommand buildCreateTableFromDescription(final TableDescription description, final EObject semanticElement, IProgressMonitor monitor) {
        final DCommand command = new SiriusCommand(domain) {
            /**
             * Creation of a table must not be undoable ! <BR>
             * {@inheritDoc}
             * 
             * @see org.eclipse.emf.transaction.RecordingCommand#canUndo()
             */
            @Override
            public boolean canUndo() {
                return false;
            }
        };

        command.getTasks().add(new CreateTableTask(description, semanticElement, monitor));

        return command;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildSetValue(org.eclipse.emf.ecore.EObject,
     *      java.lang.String, java.lang.Object)
     */
    @Override
    public Command buildSetValue(final EObject instance, final String name, final Object value) {
        if (getPermissionAuthority().canEditInstance(instance)) {
            final SiriusCommand result = new SiriusCommand(domain);
            result.getTasks().add(new AbstractCommandTask() {

                private final Object newValue = value;

                @Override
                public String getLabel() {
                    return "Set " + name + " value";
                }

                @Override
                public void execute() {
                    try {
                        if (!getModelAccessor().eGet(instance, name).equals(newValue)) {
                            getModelAccessor().eSet(instance, name, newValue);
                        }
                    } catch (final LockedInstanceException e) {
                        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                    } catch (final FeatureNotFoundException e) {
                        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                    }
                }
            });
            return result;
        } else {
            return new InvalidPermissionCommand(domain, instance);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildAddValue(org.eclipse.emf.ecore.EObject,
     *      java.lang.String, java.lang.Object)
     */
    @Override
    public Command buildAddValue(final EObject instance, final String name, final Object value) {
        if (getPermissionAuthority().canEditInstance(instance)) {
            final SiriusCommand result = new SiriusCommand(domain);
            result.getTasks().add(new AbstractCommandTask() {

                @Override
                public String getLabel() {
                    return "Add " + name + " value";
                }

                @Override
                public void execute() {
                    try {
                        getModelAccessor().eAdd(instance, name, value);
                    } catch (final LockedInstanceException e) {
                        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                    } catch (final FeatureNotFoundException e) {
                        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                    }
                }
            });
            return result;
        } else {
            return new InvalidPermissionCommand(domain, instance);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildClearValue(org.eclipse.emf.ecore.EObject,
     *      java.lang.String, java.lang.Object)
     */
    @Override
    public Command buildClearValue(final EObject instance, final String name) {
        if (getPermissionAuthority().canEditInstance(instance)) {

            final SiriusCommand result = new SiriusCommand(domain);
            result.getTasks().add(new AbstractCommandTask() {

                @Override
                public String getLabel() {
                    return "Clear " + name;
                }

                @Override
                public void execute() {
                    try {
                        getModelAccessor().eClear(instance, name);
                    } catch (final LockedInstanceException e) {
                        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                    }
                }
            });
            return result;
        } else {
            return new InvalidPermissionCommand(domain, instance);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildDoExecuteDetailsOperation(org.eclipse.sirius.viewpoint.DSemanticDecorator,
     *      org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription,
     *      java.lang.String)
     */
    @Override
    public AbstractCommand buildDoExecuteDetailsOperation(final DSemanticDecorator target, final RepresentationCreationDescription desc, final String newRepresentationName) {
        final SiriusCommand cmd = new SiriusCommand(domain);
        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        variables.put(desc.getContainerViewVariable(), target);
        final Map<AbstractVariable, String> stringVariables = new HashMap<AbstractVariable, String>();
        stringVariables.put(desc.getRepresentationNameVariable(), newRepresentationName);
        final ICommandTask initInterpreterVariables = new InitInterpreterVariablesTask(variables, stringVariables, InterpreterUtil.getInterpreter(target), uiCallBack);
        cmd.getTasks().add(initInterpreterVariables);
        cmd.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TableHelper.getTable(target), target.getTarget(), desc.getInitialOperation().getFirstModelOperations()));
        return cmd;
    }
}
