/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
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
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.helper.task.CreateContainerTask;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;

/**
 * .
 * 
 * @author mchauvin
 */
public class ContainerCreationCommandBuilder extends AbstractDiagramCommandBuilder {

    /**
     * Current tool description from which this CommandBuilder build a Command.
     */
    protected ContainerCreationDescription tool;

    /**
     * {@link DDiagramElementContainer} on which the current
     * ContainerCreationCommandBuilder's operations are executed.
     */
    protected DDiagramElementContainer nodeContainer;

    /**
     * {@link DDiagramElementContainer} on which (or the parent diagram of the
     * diagram element container on which) the current
     * ContainerCreationCommandBuilder's operations are executed.
     */
    protected DDiagram diagram;

    private final boolean createInDiagram;

    /**
     * Create a new container creation command builder instance.
     * 
     * @param tool
     *            a container creation tool
     * @param nodeContainer
     *            the node container in which the created container should be
     *            displayed
     */
    public ContainerCreationCommandBuilder(final ContainerCreationDescription tool, final DDiagramElementContainer nodeContainer) {
        this.tool = tool;
        this.nodeContainer = nodeContainer;
        this.diagram = nodeContainer.getParentDiagram();
        this.createInDiagram = false;
    }

    /**
     * Create a new container creation command builder instance.
     * 
     * @param tool
     *            a container creation tool
     * @param diagram
     *            the diagram in which the created element should be displayed
     */
    public ContainerCreationCommandBuilder(final ContainerCreationDescription tool, final DDiagram diagram) {
        this.tool = tool;
        this.diagram = diagram;
        this.createInDiagram = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    @Override
    public Command buildCommand() {
        Command command = UnexecutableCommand.INSTANCE;
        if (createInDiagram && diagram != null) {
            command = buildInDiagramCommand();
        } else if (nodeContainer != null) {
            command = buildInDNodeContainerCommand();
        }
        return command;
    }

    private Command buildInDiagramCommand() {
        EObject model = null;
        if (permissionAuthority.canEditInstance(diagram)) {
            if (diagram instanceof DSemanticDiagram) {
                model = ((DSemanticDiagram) diagram).getTarget();
            }
            if (model != null && checkPrecondition(diagram, tool)) {
                final DCommand result = buildCreateNodeCommandFromTool(model, diagram);
                result.getTasks().add(new CreateContainerTask(tool, result, modelAccessor, diagram));
                addRefreshTask(diagram, result, tool);
                result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(model), model, diagram));
                return result;
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    private Command buildInDNodeContainerCommand() {
        if (canCreateContainerInTarget()) {
            final EObject model = nodeContainer.getTarget();
            if (model != null && checkPrecondition(nodeContainer, tool)) {
                final DCommand result = buildCreateNodeCommandFromTool(model, nodeContainer);
                result.getTasks().add(new CreateContainerTask(tool, result, modelAccessor, nodeContainer));
                addRefreshTask(nodeContainer, result, tool);
                result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(model), model, diagram));
                return result;
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Build a command to create a {@link org.eclipse.sirius.viewpoint.DNode}
     * considering the semantic container and a
     * {@link ContainerCreationDescription}.
     * 
     * @param semanticContainer
     *            the semantic container.
     * @param container
     *            the container
     * @return a command able to create the
     *         {@link org.eclipse.sirius.viewpoint.DNode}.
     */
    protected DCommand buildCreateNodeCommandFromTool(final EObject semanticContainer, final EObject container) {
        final DCommand result = createEnclosingCommand();
        if (canCreateContainerInTarget()) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticContainer);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            variables.put(tool.getVariable(), semanticContainer);
            variables.put(tool.getViewVariable(), container);
            addDiagramVariable(result, container, interpreter);

            Option<DDiagram> parentDiagram = new EObjectQuery(container).getParentDiagram();
            result.getTasks().add(taskHelper.buildTaskFromModelOperation(parentDiagram.get(), semanticContainer, tool.getInitialOperation().getFirstModelOperations()));
        } else {
            result.getTasks().add(UnexecutableTask.INSTANCE);
        }
        return result;
    }

    /**
     * Indicates if the target of the Container to create (DDiagramElement or
     * directly the DDiagram) can be editable (by calling the
     * PermissionAuthority).
     * 
     * @return true if the container can be created, false otherwise
     */
    private boolean canCreateContainerInTarget() {
        boolean containerCanBeCreatedInTarget = false;
        if (nodeContainer != null) {
            containerCanBeCreatedInTarget = permissionAuthority.canEditInstance(nodeContainer);
            if (containerCanBeCreatedInTarget) {
                EObject target = nodeContainer.getTarget();
                containerCanBeCreatedInTarget = target != null && !target.eIsProxy();
            }
        } else if (diagram != null) {
            containerCanBeCreatedInTarget = permissionAuthority.canEditInstance(diagram);
            if (containerCanBeCreatedInTarget && diagram instanceof DSemanticDecorator) {
                EObject target = ((DSemanticDecorator) diagram).getTarget();
                containerCanBeCreatedInTarget = target != null && !target.eIsProxy();
            }
        }
        return containerCanBeCreatedInTarget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEnclosingCommandLabel() {
        return new IdentifiedElementQuery(tool).getLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<DDiagram> getDDiagram() {
        return Options.newSome(diagram);
    }
}
