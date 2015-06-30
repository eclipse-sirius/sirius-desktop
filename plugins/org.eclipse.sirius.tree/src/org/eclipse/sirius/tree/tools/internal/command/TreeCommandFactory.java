/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.tools.internal.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.AbstractCommandFactory;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.InvalidPermissionCommand;
import org.eclipse.sirius.tools.api.command.NoNullResourceCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.view.JavaActionFromToolCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.internal.helper.RefreshTreeElementTask;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.business.internal.refresh.CreateTreeTask;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Sets;

/**
 * A command factory that creates commands that can be undone.
 * 
 * @author nlepine
 */
public class TreeCommandFactory extends AbstractCommandFactory implements ITreeCommandFactory {
    private TaskHelper commandTaskHelper;

    /**
     * Create a new Factory. the autoRefresh is by default deactivated
     * 
     * @param domain
     *            : current editing domain.
     */
    public TreeCommandFactory(final TransactionalEditingDomain domain) {
        super(domain);
    }

    private IPermissionAuthority getPermissionAuthority() {
        return modelAccessor.getPermissionAuthority();
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
     * Returns a command that can delete the specified element.
     * 
     * @param element
     *            the element to delete (a {@link DLine} or a
     *            {@link DTargetColumn}).
     * @return a command that can delete the specified element.
     */
    @Override
    public Command buildDeleteTreeElement(final DTreeElement element) {
        Command cmd = UnexecutableCommand.INSTANCE;
        if (element instanceof DTreeItem) {
            if (!getPermissionAuthority().canEditInstance(element)) {
                cmd = new InvalidPermissionCommand(domain, element);
            } else {
                if (!getPermissionAuthority().canEditInstance(element.eContainer())) {
                    cmd = new InvalidPermissionCommand(domain, element.eContainer());
                } else {
                    final SiriusCommand result = new SiriusCommand(domain);
                    TreeItemDeletionTool deleteTool = getDeleteTool(element);
                    final DTree parentTree = TreeHelper.getTree(element);
                    if (deleteTool != null) {
                        addDeleteTreeElementFromTool(result, element, deleteTool);
                        addRefreshTask(parentTree, result, deleteTool);
                        Option<DRepresentation> dRepresentation = new EObjectQuery(element).getRepresentation();
                        result.getTasks().add(new ElementsToSelectTask(deleteTool, InterpreterUtil.getInterpreter(element), element, dRepresentation.get()));

                        cmd = new NoNullResourceCommand(result, element);
                    } else {
                        result.getTasks().add(new DeleteWithoutToolTask(element, modelAccessor, commandTaskHelper));
                        addRefreshTask(parentTree, result, deleteTool);
                        cmd = new NoNullResourceCommand(result, element);
                    }
                }
            }
        }
        return cmd;
    }

    private void addDeleteTreeElementFromTool(final SiriusCommand cmd, final DTreeElement element, final TreeItemDeletionTool deleteTool) {
        final EObject semanticElement = ((DSemanticDecorator) element).getTarget();
        final EObject rootContainer = TreeHelper.getTree(element).getTarget();
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
            if (tool instanceof TreeItemDeletionTool) {
                final TreeItemDeletionTool deleteTool = (TreeItemDeletionTool) tool;
                if (containerView instanceof DTreeElement) {
                    variables.put(TreeHelper.getVariable(deleteTool, IInterpreterSiriusVariables.ROOT), TreeHelper.getTree(containerView).getTarget());
                } else if (containerView instanceof DTree) {
                    variables.put(TreeHelper.getVariable(deleteTool, IInterpreterSiriusVariables.ROOT), ((DTree) containerView).getTarget());
                }
                variables.put(TreeHelper.getVariable(deleteTool, IInterpreterSiriusVariables.ELEMENT), semanticCurrentElement);
                // Initialization of the variables
                result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(semanticCurrentElement), uiCallBack));
                // Creation of the tasks to execute the tool
                result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TreeHelper.getTree(containerView), semanticCurrentElement, deleteTool.getFirstModelOperation()));
            } else if (tool instanceof TreeItemCreationTool) {
                final TreeItemCreationTool creationTool = (TreeItemCreationTool) tool;
                if (containerView instanceof DTreeElement) {
                    variables.put(TreeHelper.getVariable(creationTool, IInterpreterSiriusVariables.ROOT), TreeHelper.getTree(containerView).getTarget());
                    if (containerView instanceof DTreeItem) {
                        variables.put(TreeHelper.getVariable(creationTool, IInterpreterSiriusVariables.CONTAINER), ((DTreeItem) containerView).getTarget());
                    }
                } else if (containerView instanceof DTree) {
                    variables.put(TreeHelper.getVariable(creationTool, IInterpreterSiriusVariables.ROOT), ((DTree) containerView).getTarget());
                    variables.put(TreeHelper.getVariable(creationTool, IInterpreterSiriusVariables.CONTAINER), ((DTree) containerView).getTarget());
                }
                variables.put(TreeHelper.getVariable(creationTool, IInterpreterSiriusVariables.ELEMENT), semanticCurrentElement);
                // Initialization of the variables
                result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(semanticCurrentElement), uiCallBack));
                // Creation of the tasks to execute the tool
                result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TreeHelper.getTree(containerView), semanticCurrentElement, creationTool.getFirstModelOperation()));
            }
        }
        return result;
    }

    private TreeItemDeletionTool getDeleteTool(final DTreeElement element) {
        TreeItemDeletionTool result = null;
        if (element instanceof DTreeItem) {
            result = ((DTreeItem) element).getActualMapping().getDelete();
        }
        return result;
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
    public Command buildCreateLineCommandFromTool(final DTreeItemContainer lineContainer, final EObject semanticCurrentElement, final TreeItemCreationTool tool) {
        Command result = UnexecutableCommand.INSTANCE;
        if (!getPermissionAuthority().canEditInstance(lineContainer)) {
            result = new InvalidPermissionCommand(domain, lineContainer);
        } else {
            if (commandTaskHelper.checkPrecondition(semanticCurrentElement, tool)) {
                SiriusCommand createLineCommand = buildCommandFromModelOfTool(semanticCurrentElement, tool, lineContainer);
                addRefreshTask(lineContainer, createLineCommand, tool);
                Option<DRepresentation> dRepresentation = new EObjectQuery(lineContainer).getRepresentation();
                createLineCommand.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(lineContainer), semanticCurrentElement, dRepresentation.get()));

                result = createLineCommand;
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
     *            {@link DTree} creation
     * @return a command that is able to create a table.
     */
    public DCommand buildCreateTreeFromDescription(final TreeDescription description, final EObject semanticElement, IProgressMonitor monitor) {
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
        command.getTasks().add(new CreateTreeTask(description, semanticElement, monitor));
        return command;
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
        cmd.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TreeHelper.getTree(target), target.getTarget(), desc.getInitialOperation().getFirstModelOperations()));
        return cmd;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory#buildDirectEditLabelFromTool(org.eclipse.sirius.tree.DTreeItem,
     *      org.eclipse.sirius.tree.description.TreeItemEditionTool,
     *      java.lang.String)
     */
    @Override
    public Command buildDirectEditLabelFromTool(final DTreeItem editedTreeItem, TreeItemEditionTool directEditTool, String newValue) {
        SiriusCommand result = new SiriusCommand(domain, "Direct Edit on " + editedTreeItem.getName());
        if (!getPermissionAuthority().canEditInstance(editedTreeItem)) {
            result = new InvalidPermissionCommand(domain, editedTreeItem);
        } else {
            if (editedTreeItem.getUpdater() != null) {
                if (editedTreeItem.getUpdater().getDirectEdit() != null) {

                    // Step 1 : variables initialization
                    EObject interpreterContext = editedTreeItem.getTarget();

                    final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
                    variables.put(directEditTool.getRoot(), TreeHelper.getTree(editedTreeItem).getTarget());
                    variables.put(directEditTool.getElement(), editedTreeItem.getTarget());

                    result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(interpreterContext), uiCallBack));

                    if (directEditTool.getMask() != null) {
                        /*
                         * First we need to init the mask variables.
                         */
                        final String messageFormat = directEditTool.getMask().getMask();
                        result.getTasks().add(new InitInterpreterFromParsedVariableTask2(InterpreterUtil.getInterpreter(interpreterContext), messageFormat, newValue));
                    }

                    // Step 2 : build the task from the model operations
                    // specified in this tool
                    result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(TreeHelper.getTree(editedTreeItem), interpreterContext, directEditTool.getFirstModelOperation()));

                    // Add a RefreshTreeElementTask to have DTreeItem refreshed
                    // on direct edit even in REFRESH_AUTO mode at false
                    ICommandTask refreshTreeElementTask = new RefreshTreeElementTask(editedTreeItem);
                    result.getTasks().add(refreshTreeElementTask);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory#buildDragAndDropItemFromTool(org.eclipse.sirius.tree.DTreeItem,
     *      org.eclipse.sirius.tree.DTreeItem,
     *      org.eclipse.sirius.tree.description.TreeItemDragTool)
     */
    @Override
    public Command buildDropItemFromTool(EObject dropped, DTreeItemContainer dropTarget, Collection<DTreeItem> precedingSiblings, TreeItemContainerDropTool dropTool) {
        final SiriusCommand result = new SiriusCommand(domain, "Drop the item " + dropped);

        EObject dropSem = dropped;
        DSemanticDecorator dropDec = null;
        DSemanticDecorator oldContainer = null;
        if (dropped instanceof DSemanticDecorator) {
            dropDec = (DSemanticDecorator) dropped;
            dropSem = dropDec.getTarget();
            oldContainer = getOldContainer(dropDec);
        }

        // Step 1 : variables initialization
        EObject interpreterContext = dropTarget.getTarget();

        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        if (dropDec != null) {
            variables.put(dropTool.getOldContainer(), oldContainer);
        }
        variables.put(dropTool.getNewContainer(), dropTarget.getTarget());
        variables.put(dropTool.getElement(), dropSem);
        variables.put(dropTool.getNewViewContainer(), dropTarget);
        variables.put(dropTool.getPrecedingSiblings(), precedingSiblings);

        result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(interpreterContext), uiCallBack));

        // Step 2 : build the task from the model operations
        // specified in this tool
        DTree droppedTree = TreeHelper.getTree(dropTarget);
        if (dropTool.getFirstModelOperation() != null) {
            result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(droppedTree, interpreterContext, dropTool.getFirstModelOperation()));
        }
        // Step 3 : adding task to refresh
        if (droppedTree != null) {
            addRefreshTask(droppedTree, result, dropTool);
        }
        if (dropDec != null) {
            addRefreshTask(dropDec, result, dropTool);
        }
        Option<DRepresentation> dRepresentation = new EObjectQuery(dropTarget).getRepresentation();
        result.getTasks().add(new ElementsToSelectTask(dropTool, InterpreterUtil.getInterpreter(dropTarget), dropTarget.getTarget(), dRepresentation.get()));

        return result;
    }

    private DSemanticDecorator getOldContainer(DSemanticDecorator dropDec) {
        EObject semanticOldContainer = null;
        DSemanticDecorator oldContainer = null;

        EObject currentOldContainer = dropDec.eContainer();
        while (currentOldContainer != null && semanticOldContainer == null) {
            if (currentOldContainer instanceof DSemanticDecorator) {
                oldContainer = (DSemanticDecorator) currentOldContainer;
                semanticOldContainer = oldContainer.getTarget();
            }
            currentOldContainer = currentOldContainer.eContainer();
        }
        return oldContainer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory#buildOperationActionFromTool(org.eclipse.sirius.viewpoint.description.tool.OperationAction,
     *      org.eclipse.sirius.tree.DTreeItem)
     */
    @Override
    public Command buildOperationActionFromTool(OperationAction operationAction, final DTreeItem selectedItem) {
        final SiriusCommand result = new SiriusCommand(domain, operationAction.getName() + " on " + selectedItem.getName());
        // Step 1 : variables initialization
        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        EObject interpreterContext = selectedItem.getTarget();
        variables.put(operationAction.getView(), selectedItem);
        result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(interpreterContext), uiCallBack));

        // Step 2 : adding task from model operations
        DTree targetTree = TreeHelper.getTree(selectedItem);
        result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(targetTree, interpreterContext, operationAction.getInitialOperation().getFirstModelOperations()));

        // Step 3 : adding task to refresh
        addRefreshTask(targetTree, result, null);
        Option<DRepresentation> dRepresentation = new EObjectQuery(selectedItem).getRepresentation();
        result.getTasks().add(new ElementsToSelectTask(operationAction, InterpreterUtil.getInterpreter(selectedItem.getTarget()), selectedItem.getTarget(), dRepresentation.get()));

        return result.chain(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                selectedItem.setExpanded(true);
            }
        });
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory#buildJavaActionFromTool(org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction,
     *      org.eclipse.sirius.tree.DTreeItem,
     *      org.eclipse.sirius.tools.api.ui.IExternalJavaAction)
     */
    @Override
    public Command buildJavaActionFromTool(ExternalJavaAction javaActionItem, DTreeItem selectedItem, IExternalJavaAction javaAction) {
        final CompoundCommand compoundCommand = new CompoundCommand();
        // Step 1 : creating the command from the java action tool
        Set<DSemanticDecorator> views = Sets.newLinkedHashSet();
        views.add(selectedItem);
        final JavaActionFromToolCommand javaActionFromToolCommand = new JavaActionFromToolCommand(this.domain, javaAction, javaActionItem, views);
        compoundCommand.append(javaActionFromToolCommand);

        // Step 2 : creating a command for refreshing the representation
        final SiriusCommand dCommand = new SiriusCommand(this.domain, selectedItem.getName());
        addRefreshTask(selectedItem, dCommand, javaActionItem);
        Option<DRepresentation> dRepresentation = new EObjectQuery(selectedItem).getRepresentation();
        dCommand.getTasks().add(new ElementsToSelectTask(javaActionItem, InterpreterUtil.getInterpreter(selectedItem.getTarget()), selectedItem.getTarget(), dRepresentation.get()));

        compoundCommand.append(dCommand);

        return compoundCommand;
    }
}
