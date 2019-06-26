/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.task.DeleteEObjectTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.internal.helper.task.DeleteDRepresentationElementsTask;
import org.eclipse.sirius.business.internal.helper.task.DeleteDRepresentationTask;
import org.eclipse.sirius.business.internal.helper.task.DeleteWithoutToolTask;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.NoNullResourceCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Iterables;

/**
 * Delete command builder.
 * 
 * @author mchauvin
 */
public class DeletionCommandBuilder extends AbstractDiagramCommandBuilder {

    /**
     * The diagram to delete.
     */
    private DDiagram diagram;

    /**
     * The diagram element to delete.
     */
    private DDiagramElement diagramElement;

    /**
     * The tool if there is one.
     */
    private DeleteElementDescription tool;

    private boolean deleteFromDiagram;

    /**
     * Create a deletion command builder not to create a command, but only to
     * add delete diagram tasks.
     */
    public DeletionCommandBuilder() {
    }

    /**
     * Create a deletion command builder to delete a diagram.
     * 
     * @param diagram
     *            the diagram to delete
     */
    public DeletionCommandBuilder(final DDiagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Create a deletion command builder to delete a diagram element.
     * 
     * @param diagramElement
     *            the diagram element to delete
     * @param deleteFromDiagram
     *            define if the delete should be a graphical one, or a semantic
     *            one.
     */
    public DeletionCommandBuilder(final DDiagramElement diagramElement, final boolean deleteFromDiagram) {
        this.diagramElement = diagramElement;
        this.deleteFromDiagram = deleteFromDiagram;
    }

    @Override
    public Command buildCommand() {
        Command command = UnexecutableCommand.INSTANCE;
        if (diagram != null) {
            command = buildDeleteDiagram();
        } else if (diagramElement != null) {
            command = deleteFromDiagram ? buildDeleteDiagramElementFromDiagram() : buildDeleteDiagramElement();
        }
        return command;
    }

    private Command buildDeleteDiagram() {
        Command cmd = UnexecutableCommand.INSTANCE;
        if (permissionAuthority.canDeleteInstance(diagram)) {
            final DCommand vpCmd = createEnclosingCommand();
            /* delete the diagram */
            vpCmd.getTasks().add(new DeleteDRepresentationTask(new DRepresentationQuery(diagram).getRepresentationDescriptor()));
            cmd = vpCmd;
        }
        return cmd;
    }

    private Command buildDeleteDiagramElementFromDiagram() {
        if (permissionAuthority.canDeleteInstance(diagramElement)) {
            final DCommand cmd = createEnclosingCommand();
            cmd.getTasks().add(new DeleteEObjectTask(diagramElement, modelAccessor));

            final List<EObject> contents = new ArrayList<EObject>(this.modelAccessor.eAllContents(diagramElement, "EdgeTarget")); //$NON-NLS-1$
            contents.add(diagramElement);
            for (final EObject element : contents) {
                if (element instanceof EdgeTarget) {
                    final EdgeTarget target = (EdgeTarget) element;
                    for (final DEdge edge : Iterables.concat(target.getIncomingEdges(), target.getOutgoingEdges())) {
                        cmd.getTasks().add(new DeleteEObjectTask(edge, modelAccessor));
                    }
                }
            }
            return cmd;
        }
        return UnexecutableCommand.INSTANCE;
    }

    private Command buildDeleteDiagramElement() {

        Command cmd = UnexecutableCommand.INSTANCE;
        setTool();

        // We first check that
        // * in case of tool, the tool allows the deletion
        // * the permission authority allows to delete the
        // given diagram element
        if ((tool == null || isDeleteAllowedByTool()) && permissionAuthority.canDeleteInstance(diagramElement)) {

            // We also check that the underlying semantic elements can be
            // deleted
            Set<EObject> semanticElements = getRootSemanticElementsToDestroy(diagramElement);

            // If both graphical and semantic elements can be deleted, we build
            // the command
            if (semanticElements != null) {
                final DCommand result = createEnclosingCommand();
                if (tool != null) {
                    addDeleteDiagramElementFromTool(result);
                    addRefreshTask(diagramElement, result, tool);
                    Option<DDiagram> parentDiagram = new EObjectQuery(diagramElement).getParentDiagram();
                    result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(diagramElement), diagramElement.getTarget(), parentDiagram.get()));
                    cmd = new NoNullResourceCommand(result, diagramElement);
                } else {
                    cmd = buildDeleteDiagramElementCommandWithoutTool(result, semanticElements);
                }
            }
        }

        return cmd;
    }

    private void setTool() {
        DiagramElementMapping mapping = null;
        mapping = diagramElement.getDiagramElementMapping();
        if (mapping != null) {
            tool = mapping.getDeletionDescription();
        }
    }

    /**
     * Add tasks on deletion command from tool</br>
     * This method does not check if the delete allows deletion
     * 
     * @param cmd
     *            the command on which to add tasks
     */
    private void addDeleteDiagramElementFromTool(final DCommand cmd) {
        final EObject semanticContainer = ((DSemanticDecorator) diagramElement).getTarget();
        final EObject viewContainer = diagramElement.eContainer();

        cmd.getTasks().addAll(buildDeleteFromToolTask(semanticContainer, viewContainer).getTasks());
        cmd.getTasks().add(new DeleteDRepresentationElementsTask(modelAccessor, cmd, taskHelper, diagramElement) {

            @Override
            protected void addDialectSpecificAdditionalDeleteSubTasks(DSemanticDecorator decorator, List<ICommandTask> subTasks) {
                // Nothing to add per default.
                // If the semantic decorator is related to edges,
                // these edges should also be deleted
                if (decorator instanceof EdgeTarget) {
                    EdgeTarget edgeTarget = (EdgeTarget) decorator;
                    for (final DEdge edge : Iterables.concat(edgeTarget.getIncomingEdges(), edgeTarget.getOutgoingEdges())) {
                        subTasks.add(new DeleteEObjectTask(edge, modelAccessor));
                    }
                }
            }
        });
        if (diagramElement instanceof DEdge) {
            Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(((DEdge) diagramElement).getActualMapping()).getEdgeMapping();
            if (edgeMapping.some() && !edgeMapping.get().isUseDomainElement()) {
                // Add the delete task for relation edges only.
                cmd.getTasks().add(new DeleteEObjectTask(diagramElement, modelAccessor));
            }
        }
    }

    /**
     * Check the delete availability from tool</br>
     * If a deletion tool exists and if the condition expression returns false,
     * the deletion is not available
     * 
     * @return if the deletion is available from tool
     */
    private boolean isDeleteAllowedByTool() {
        final EObject semanticContainer = ((DSemanticDecorator) diagramElement).getTarget();
        final EObject viewContainer = diagramElement.eContainer();
        // check precondition
        boolean delete = true;
        if (tool.getPrecondition() != null && !StringUtil.isEmpty(tool.getPrecondition().trim())) {
            delete = false;
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(diagramElement);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, viewContainer);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEW, diagramElement);
            interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, semanticContainer);
            try {
                delete = interpreter.evaluateBoolean(semanticContainer, tool.getPrecondition());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(tool, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
        }
        return delete;
    }

    private DCommand buildDeleteFromToolTask(final EObject deletedSemanticElement, final EObject containerView) {
        final DCommand result = createEnclosingCommand();
        if (permissionAuthority.canEditInstance(containerView)) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(deletedSemanticElement);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            variables.put(tool.getContainerView(), containerView);
            variables.put(tool.getElement(), deletedSemanticElement);
            variables.put(tool.getElementView(), diagramElement);

            addDiagramVariable(result, containerView, interpreter);

            Option<DDiagram> parentDiagram = new EObjectQuery(containerView).getParentDiagram();
            if (tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                result.getTasks().add(taskHelper.buildTaskFromModelOperation(parentDiagram.get(), deletedSemanticElement, tool.getInitialOperation().getFirstModelOperations()));
            }
        } else {
            result.getTasks().add(UnexecutableTask.INSTANCE);
        }
        return result;
    }

    private Command buildDeleteDiagramElementCommandWithoutTool(final DCommand result, final Set<EObject> semanticElements) {
        Command cmd;

        if (semanticElements.isEmpty()) {
            // Do not return unexecutable command, actions will be deactivated
            // when an element is not deletable, here the result needs to be
            // return to handle multi-selection.
            cmd = result;
        } else {
            // Now delete all the diagram elements corresponding to
            // the semantic elements to delete
            ICommandTask deleteWithoutToolTask = new DeleteWithoutToolTask(diagramElement, semanticElements, modelAccessor, taskHelper) {

                @Override
                protected void addDialectSpecificAdditionalDeleteSubTasks(DSemanticDecorator decorator, List<ICommandTask> subTasks) {
                    // Nothing to add per default.
                    // If the semantic decorator is related to edges,
                    // these edges should also be deleted
                    if (decorator instanceof EdgeTarget) {
                        EdgeTarget edgeTarget = (EdgeTarget) decorator;
                        for (final DEdge edge : Iterables.concat(edgeTarget.getIncomingEdges(), edgeTarget.getOutgoingEdges())) {
                            subTasks.add(new DeleteEObjectTask(edge, modelAccessor));
                        }
                    }
                }
            };
            result.getTasks().add(deleteWithoutToolTask);

            // Add a refresh task if the autoRefresh is on.
            addRefreshTask(diagramElement, result, tool);
            /* Avoid notifications of the outline on each change */
            cmd = new NoNullResourceCommand(result, diagramElement);
        }
        return cmd;
    }

    /**
     * Get root semantic elements to destroy to delete the specified diagram
     * element.
     * 
     * Note: this method can returns more than root semantic elements to
     * destroy, in case DDiagramElement tree to delete doesn't mirror the
     * semantic tree, the result can be not minimized.
     */
    private Set<EObject> getRootSemanticElementsToDestroy(final DDiagramElement currentDiagramElement) {
        Set<EObject> elementsToDestroy = new LinkedHashSet<>();
        boolean canDelete = appendRootSemanticElementsToDestroy(currentDiagramElement, elementsToDestroy);
        return canDelete ? elementsToDestroy : null;
    }

    private boolean appendRootSemanticElementsToDestroy(final DDiagramElement currentDiagramElement, Set<EObject> elementsToDestroy) {
        boolean canDelete = true;

        for (final EObject semantic : currentDiagramElement.getSemanticElements()) {
            if (semantic != null && !EcoreUtil.isAncestor(elementsToDestroy, semantic)) {
                if (!permissionAuthority.canDeleteInstance(semantic)) {
                    canDelete = false;
                    break;
                } else {
                    elementsToDestroy.add(semantic);
                }
            }
        }

        if (canDelete) {
            for (final EObject child : currentDiagramElement.eContents()) {
                if (child instanceof DDiagramElement) {
                    if (!appendRootSemanticElementsToDestroy((DDiagramElement) child, elementsToDestroy)) {
                        canDelete = false;
                        break;
                    }
                }
            }
        }

        return canDelete;
    }

    @Override
    protected String getEnclosingCommandLabel() {
        String commandLabel = Messages.DeletionCommandBuilder_deleteLabel;
        if (diagram != null) {
            commandLabel = Messages.DeletionCommandBuilder_deleteDiagramLabel;
        } else if (diagramElement != null) {
            if (deleteFromDiagram) {
                commandLabel = Messages.DeletionCommandBuilder_deleteFromDiagramLabel;
            } else if (tool == null) {
                commandLabel = Messages.DeletionCommandBuilder_deleteFromModelLabel;
            } else {
                commandLabel = new IdentifiedElementQuery(tool).getLabel();
            }
        }
        return commandLabel;
    }

    @Override
    protected Option<DDiagram> getDDiagram() {
        if (diagram == null && diagramElement != null) {
            return Options.newSome(diagramElement.getParentDiagram());
        }
        return Options.newSome(diagram);
    }
}
