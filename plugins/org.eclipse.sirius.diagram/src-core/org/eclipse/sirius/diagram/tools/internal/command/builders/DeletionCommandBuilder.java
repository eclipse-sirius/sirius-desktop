/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.DeleteEObjectTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
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
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Delete command builder.
 * 
 * @author mchauvin
 */
public class DeletionCommandBuilder extends AbstractDiagramCommandBuilder {

    /** The default label for the enclosing command. */
    protected static final String DELETE = "Delete";

    /** The label for delete from diagram. */
    protected static final String DELETE_FROM_DIAGRAM_LABEL = "Delete from diagram";

    /** The label for delete from model without tool. */
    protected static final String DELETE_FROM_MODEL = "Delete from model ";

    /** The label for delete diagram. */
    protected static final String DELETE_DIAGRAM_LABEL = "Delete diagram";

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
            vpCmd.getTasks().add(new DeleteDRepresentationTask(diagram));
            cmd = vpCmd;
        }
        return cmd;
    }

    private Command buildDeleteDiagramElementFromDiagram() {
        if (permissionAuthority.canDeleteInstance(diagramElement)) {
            final DCommand cmd = createEnclosingCommand();
            cmd.getTasks().add(new DeleteEObjectTask(diagramElement, modelAccessor));

            final List<EObject> contents = Lists.newArrayList(this.modelAccessor.eAllContents(diagramElement, "EdgeTarget")); //$NON-NLS-1$
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
        // We first check that the permission authority allows to delete the
        // given diagram element
        if (permissionAuthority.canDeleteInstance(diagramElement)) {

            // We also check that the underlying semantic elements can be
            // deleted
            Set<EObject> semanticElements = getSemanticElementsToDestroy(diagramElement);

            // If both graphical and semantic elements can be deleted, we build
            // the command
            if (semanticElements != null) {
                final DCommand result = createEnclosingCommand();
                setTool();
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

    private void addDeleteDiagramElementFromTool(final DCommand cmd) {
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
                delete = false;
                delete = interpreter.evaluateBoolean(semanticContainer, tool.getPrecondition());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(tool, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
        }
        if (delete) {

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
        } else {
            cmd.getTasks().add(UnexecutableTask.INSTANCE);
        }
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
     * Add all the semantic elements to destroy to delete the specified diagram
     * element.
     */
    private Set<EObject> getSemanticElementsToDestroy(final DDiagramElement currentDiagramElement) {
        Set<EObject> elementsToDestroy = Sets.newLinkedHashSet();
        boolean canDelete = appendSemanticElementsToDestroy(currentDiagramElement, elementsToDestroy);
        return canDelete ? elementsToDestroy : null;
    }

    private boolean appendSemanticElementsToDestroy(final DDiagramElement currentDiagramElement, Set<EObject> elementsToDestroy) {
        boolean canDelete = true;

        for (final EObject semantic : currentDiagramElement.getSemanticElements()) {
            if (semantic != null && !elementsToDestroy.contains(semantic)) {
                if (!permissionAuthority.canDeleteInstance(semantic)) {
                    canDelete = false;
                    break;
                } else {
                    elementsToDestroy.add(semantic);
                }
            }
        } // for

        if (canDelete) {
            for (final EObject child : currentDiagramElement.eContents()) {
                if (child instanceof DDiagramElement) {
                    if (!appendSemanticElementsToDestroy((DDiagramElement) child, elementsToDestroy)) {
                        canDelete = false;
                        break;
                    }
                }
            } // for
        }

        return canDelete;
    }

    @Override
    protected String getEnclosingCommandLabel() {
        String commandLabel = DELETE;
        if (diagram != null) {
            commandLabel = DELETE_DIAGRAM_LABEL;
        } else if (diagramElement != null) {
            if (deleteFromDiagram) {
                commandLabel = DELETE_FROM_DIAGRAM_LABEL;
            } else if (tool == null) {
                commandLabel = DELETE_FROM_MODEL;
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
