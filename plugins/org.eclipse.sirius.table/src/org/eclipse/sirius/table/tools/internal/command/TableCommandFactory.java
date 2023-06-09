/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.tools.internal.command;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
import org.eclipse.sirius.business.api.helper.task.label.InitInterpreterFromParsedVariableTask2;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.internal.helper.task.DeleteDRepresentationElementsTask;
import org.eclipse.sirius.business.internal.helper.task.DeleteWithoutToolTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.helper.TableVariablesHelper;
import org.eclipse.sirius.table.business.internal.helper.task.CreateTableTask;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CellEditorTool;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.interpreter.IInterpreterSiriusTableVariables;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
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
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;

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
     * <p>
     * Precondition must be evaluated before calling this method: Usually on UiAction 'canExecute'.
     * </p>
     *
     * @param element
     *            the element to delete (a {@link DLine} or a {@link DTargetColumn}).
     * @return a command that can delete the specified element.
     */
    @Override
    public Command buildDeleteTableElement(final DTableElement element) {
        Command cmd = UnexecutableCommand.INSTANCE;

        if (element instanceof DLine || element instanceof DTargetColumn) {
            // We check that, in case of tool, the tool allows the deletion
            
            return createAuthorisedCommand(element, element.eContainer(), () -> {
                DeleteTool deleteTool = getDeleteTool(element);
                final SiriusCommand result;
                if (deleteTool != null) {
                    result = addDeleteTableElementFromTool(element, deleteTool);
                } else {
                    result = new SiriusCommand(domain);
                    result.getTasks().add(new DeleteWithoutToolTask(element, modelAccessor, commandTaskHelper));
                }
                addRefreshTask(TableHelper.getTable(element), result, deleteTool);
                return new NoNullResourceCommand(result, element);
            });
        }
        return cmd;
    }


    @Override
    public Command buildCreateLineCommandFromTool(LineContainer lineContainer, CreateTool tool) {
        return buildCreateCommandFromTool(lineContainer, lineContainer.getTarget(), 
                TableVariablesHelper.getVariables(lineContainer), tool);
    }

    @Override
    public Command buildCreateColumnCommandFromTool(DSemanticDecorator containerView, CreateTool tool) {
        return buildCreateCommandFromTool(containerView, containerView.getTarget(), 
                TableVariablesHelper.getVariables(containerView), tool);
    }
    

    @Override @Deprecated
    public Command buildCreateLineCommandFromTool(final LineContainer lineContainer, final EObject semanticCurrentElement, final CreateTool tool) {
        // legacy method:
        // not used by UI anymore but interface.
        
        return buildCreateCommandFromTool(lineContainer, semanticCurrentElement, 
                mapOf(IInterpreterSiriusVariables.ROOT, TableHelper.getTable(lineContainer).getTarget(),
                        IInterpreterSiriusVariables.CONTAINER, lineContainer.getTarget(),
                        IInterpreterSiriusVariables.ELEMENT, semanticCurrentElement), 
                tool);
    }

    @Override @Deprecated
    public Command buildCreateColumnCommandFromTool(final DTable containerView, final EObject semanticCurrentElement, final CreateTool tool) {
        // legacy method:
        // not used by UI anymore but interface.

        return buildCreateCommandFromTool(containerView, semanticCurrentElement, 
                mapOf(IInterpreterSiriusVariables.ROOT, containerView.getTarget(),
                        IInterpreterSiriusVariables.CONTAINER, containerView.getTarget(),
                        IInterpreterSiriusVariables.ELEMENT, semanticCurrentElement), 
                tool);
    }
    
    private <TT extends AbstractToolDescription & TableTool> Command buildCreateCommandFromTool(
            final DSemanticDecorator view, 
            final EObject semanticCurrentElement, Map<String, EObject> variables,
            final TT tool) {
        return createAuthorisedCommand(view, () -> {        
            SiriusCommand result = buildCommandFromTool(TableHelper.getTable(view),
                    semanticCurrentElement,  variables, tool);
            addRefreshTask(view, result, tool);
            addSelectTask(view, result, tool);
            return result;
        });
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

    private SiriusCommand addDeleteTableElementFromTool(final DTableElement element, final DeleteTool deleteTool) {
        final SiriusCommand result = buildCommandFromTool(element, deleteTool);

        result.getTasks().add(new DeleteDRepresentationElementsTask(modelAccessor, result, commandTaskHelper, element) {
            @Override
            protected void addDialectSpecificAdditionalDeleteSubTasks(DSemanticDecorator semDec, List<ICommandTask> subTasks) {
                super.addDialectSpecificAdditionalDeleteSubTasks(semDec, subTasks);
                if (semDec instanceof DCell) {
                    final DCell cell = (DCell) semDec;
                    subTasks.add(createTask("", () -> cell.setColumn(null))); //$NON-NLS-1$
                }
            }
        });
        return result;
    }

    /**
     * Build a command that covers all the model operations corresponding to a the semantic container and a
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription}.
     *
     * @param view
     *            the container View
     * @param tool
     *            the {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription} .
     * @return a command able to execute the tool.
     */
    @Deprecated // 1 case ?
    protected SiriusCommand buildCommandFromTool(final DSemanticDecorator view, final TableTool tool) {
        return buildCommandFromTool(TableHelper.getTable(view), view.getTarget(), TableVariablesHelper.getVariables(view), tool);
    }
    

    /**
     * Build a command that covers all the model operations corresponding to a the semantic container and a
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription}.
     *
     * @param table
     *            the containing table
     * @param semanticCurrentElement
     *            the semantic current Element.
     * @param variables
     *            used in operation
     * @param tool
     *            the {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription} .
     * @return a command able to execute the tool.
     */
    protected SiriusCommand buildCommandFromTool(final DTable table, final EObject semanticCurrentElement, final Map<String, EObject> variables, final TableTool tool) {
        SiriusCommand result = new SiriusCommand(domain);
        
        // Initialization of the variables
        addInitVariablesTask(result, semanticCurrentElement, TableVariablesHelper.getTableVariables(tool, variables));
        
        // Creation of the tasks to execute the tool
        addOperationTask(result, table, semanticCurrentElement, tool.getFirstModelOperation());
    
        return result;
    }

    /**
     * Build a command that covers all the model operations corresponding to a the {@link DCell currentCell} and the
     * corresponding {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool directEdit tool},
     * {@link org.eclipse.sirius.table.metamodel.table.description.CellEditorTool cellEditor tool} or
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool createCell tool}.
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
        SiriusCommand result = new SiriusCommand(domain, Messages.TableCommandFactory_setCellContent);

        EObject interpreterContext = currentCell.getTarget();
        if (interpreterContext == null) {
            interpreterContext = currentCell.getLine().getTarget();
        }

        final Map<String, Object> variables = new HashMap<>(TableVariablesHelper.getVariables(currentCell));

        EditMaskVariables mask = null;
        if (tool instanceof LabelEditTool) {
            mask = ((LabelEditTool) tool).getMask();
        } else if (tool instanceof CreateCellTool) {
            mask = ((CreateCellTool) tool).getMask();
        } else if (tool instanceof CellEditorTool) {
            // For custom editor, value may be anything provided by CellEditSupport.
            variables.put(IInterpreterSiriusTableVariables.CELL_EDITOR_RESULT, newValue);
        }
        
        // Initialization of the variables
        addInitVariablesTask(result, interpreterContext, TableVariablesHelper.getTableVariables(tool, variables));
        addMaskInitTask(result, interpreterContext, mask, newValue);
        // Creation of the tasks to execute the tool
        addOperationTask(result, currentCell, interpreterContext, tool.getFirstModelOperation());
    
        return result;
    }

    /**
     * Build a command that covers all the model operations corresponding to a
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool createCell tool} for the current
     * {@link DLine line} and {@link org.eclipse.sirius.table.metamodel.table.DColumn column}.
     *
     * @param currentLine
     *            the line corresponding to the intersection that need a creation of a new cell.
     * @param currentColumn
     *            the column corresponding to the intersection that need a creation of a new cell.
     * @param tool
     *            The create tool
     * @param newValue
     *            the new value for this cell
     * @return a command able to execute the create Tool.
     */
    private SiriusCommand buildCommandFromIntersection(final DLine currentLine, final DTargetColumn currentColumn, final CreateCellTool tool, final Object newValue) {
        SiriusCommand result = new SiriusCommand(domain, Messages.TableCommandFactory_setCellContent);
        EObject interpreterContext = currentLine.getTarget();

        final Map<String, EObject> variables = TableVariablesHelper.getVariables(currentLine, currentColumn);

        // Initialization of the variables
        addInitVariablesTask(result, interpreterContext, TableVariablesHelper.getTableVariables(tool, variables));
        addMaskInitTask(result, interpreterContext, tool.getMask(), newValue);

        // Creation of the tasks to execute the tool
        addOperationTask(result, currentLine, interpreterContext, tool.getFirstModelOperation());
        
        return result;
    }

    /**
     * Return the commandTaskHelper.
     *
     * @return the commandTaskHelper
     */
    @Deprecated // unused
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
     *         {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool LabelEditTool},
     *         {@link org.eclipse.sirius.table.metamodel.table.description.CellEditorTool CellEditorTool} or
     *         {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool CreateCellTool}.
     */
    @Override
    public Command buildSetCellValueFromTool(final DCell editedCell, final Object newValue) {
        CellUpdater updater = editedCell.getUpdater();
        if (updater == null || (updater.getDirectEdit() == null && updater.getCellEditor() == null)) {
            return UnexecutableCommand.INSTANCE;
        }        
        return createAuthorisedCommand(editedCell, () -> {
            // If both directEdit and cellEditor is defined, the cell editor has priority 
            // (explained in documentation).
            TableTool toolEdit; 
            if (updater.getCellEditor() != null) {
                toolEdit = updater.getCellEditor();
            } else {
                toolEdit = updater.getDirectEdit();
            }
            SiriusCommand result = buildCommandFromCell(editedCell, toolEdit, newValue);
            addRefreshTask(TableHelper.getTable(editedCell), result, null);
            return result;
        });
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildCreateCellFromTool(org.eclipse.sirius.table.metamodel.table.DLine,
     *      org.eclipse.sirius.table.metamodel.table.DTargetColumn, java.lang.Object)
     */
    @Override
    public Command buildCreateCellFromTool(DLine line, DTargetColumn column, Object newValue) {
        return createAuthorisedCommand(line, column, ()-> {
            final Option<CreateCellTool> optionalCreateCellTool = TableHelper.getCreateCellTool(line, column);
            if (!optionalCreateCellTool.some()) {
                return UnexecutableCommand.INSTANCE;
            }
            SiriusCommand result = buildCommandFromIntersection(line, column, optionalCreateCellTool.get(), newValue);
            addRefreshTask(TableHelper.getTable(line), result, optionalCreateCellTool.get());
            addSelectTask(line, result, optionalCreateCellTool.get());
            
            return result;
        });
    }

    /**
     * Create a command that is able to create a table.
     *
     * @param description
     *            the tool that describes how to create the table.
     * @param semanticElement
     *            the element from which the table will be created.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of {@link DTable} creation
     * @return a command that is able to create a table.
     */
    public DCommand buildCreateTableFromDescription(final TableDescription description, final EObject semanticElement, IProgressMonitor monitor) {
        // Creation of a table must not be undoable !
        final DCommand command = new SiriusCommand(domain) {

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
        return createAuthorisedCommand(instance, ()-> {
            final SiriusCommand result = new SiriusCommand(domain);
            result.getTasks().add(createTask(MessageFormat.format(Messages.TableCommandFactory_setValue, name), 
                () -> {
                    try {
                        if (!getModelAccessor().eGet(instance, name).equals(value)) {
                            getModelAccessor().eSet(instance, name, value);
                        }
                    } catch (final FeatureNotFoundException e) {
                        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                    }
                }));
            return result;
        });

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildAddValue(org.eclipse.emf.ecore.EObject,
     *      java.lang.String, java.lang.Object)
     */
    @Override
    public Command buildAddValue(final EObject instance, final String name, final Object value) {
        return createAuthorisedCommand(instance, ()-> {
            final SiriusCommand result = new SiriusCommand(domain);
            result.getTasks().add(createTask(MessageFormat.format(Messages.TableCommandFactory_addValue, name),
                () -> {
                    try {
                        getModelAccessor().eAdd(instance, name, value);
                    } catch (final FeatureNotFoundException e) {
                        SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                    }
                }));

            return result;
        });
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildClearValue(org.eclipse.emf.ecore.EObject,
     *      java.lang.String, java.lang.Object)
     */
    @Override
    public Command buildClearValue(final EObject instance, final String name) {
        return createAuthorisedCommand(instance, ()-> {
            final SiriusCommand result = new SiriusCommand(domain);
            String label = MessageFormat.format(Messages.TableCommandFactory_clearValue, name);
            result.getTasks().add(createTask(label, () -> getModelAccessor().eClear(instance, name)));
            return result;
        });
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.table.tools.api.command.ITableCommandFactory#buildDoExecuteDetailsOperation(org.eclipse.sirius.viewpoint.DSemanticDecorator,
     *      org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription, java.lang.String)
     */
    @Override
    public AbstractCommand buildDoExecuteDetailsOperation(final DSemanticDecorator target, final RepresentationCreationDescription desc, final String newRepresentationName) {
        final SiriusCommand result = new SiriusCommand(domain);
        
        result.getTasks().add(new InitInterpreterVariablesTask(
                mapOf(desc.getContainerViewVariable(), target), 
                mapOf(desc.getRepresentationNameVariable(), newRepresentationName), 
                InterpreterUtil.getInterpreter(target), uiCallBack));
        
        addOperationTask(result, target, target.getTarget(), desc.getInitialOperation().getFirstModelOperations());
        return result;
    }
    
    private Command createAuthorisedCommand(EObject view1, EObject view2, Supplier<Command> factory) {
        Command result;
        if (!getPermissionAuthority().canEditInstance(view1)) {
            result = new InvalidPermissionCommand(domain, view1);
        } else {
            result = createAuthorisedCommand(view2, factory);
        }
        return result;
    }

    private Command createAuthorisedCommand(EObject view, Supplier<Command> factory) {
        Command result;
        if (!getPermissionAuthority().canEditInstance(view)) {
            result = new InvalidPermissionCommand(domain, view);
        } else {
            result = factory.get();
            if (result == null) {
                result = UnexecutableCommand.INSTANCE;
            }
        }
        return result;
    }

    private void addSelectTask(DSemanticDecorator view, SiriusCommand result, final AbstractToolDescription tool) {
        Option<DRepresentation> dRepresentation = new EObjectQuery(view).getRepresentation();
        result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(view.getTarget()), view.getTarget(), dRepresentation.get()));
    }
    
    private void addInitVariablesTask(SiriusCommand result, EObject context, Map<AbstractVariable, Object> variables) {
        result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(context), uiCallBack));
    }
    
    private void addMaskInitTask(SiriusCommand result, EObject context, EditMaskVariables mask, final Object newValue) {
        if (mask != null && mask.getMask() != null) {
            // First we need to init the mask variables.
            result.getTasks().add(new InitInterpreterFromParsedVariableTask2(InterpreterUtil.getInterpreter(context), mask.getMask(), newValue));
        }
    }
    
    private void addOperationTask(SiriusCommand result, DSemanticDecorator tableElement, EObject context, ModelOperation operation) {
        DTable table = TableHelper.getTable(tableElement);
        result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(table, context, operation));
    }

    private static AbstractCommandTask createTask(String label, Runnable task) {
        return new AbstractCommandTask() {

            @Override
            public String getLabel() {
                return label;
            }
    
            @Override
            public void execute() {
                try {
                    task.run();
                } catch (LockedInstanceException e) {
                    SiriusPlugin.getDefault().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
                }
            }
        };
    }
    
    private static <K, V> Map<K, V> mapOf(K k1, V v1) {
        // XXX For Java 9+, replace Map.of()
        Map<K, V> result = new HashMap<>();
        result.put(k1, v1);
        return Collections.unmodifiableMap(result);
    }

    private static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3) {
        // XXX For Java 9+, replace Map.of()
        Map<K, V> result = new HashMap<>();
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        
        return Collections.unmodifiableMap(result);
    }

}
