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
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.helper.task.CreateDNodeTask;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;

/**
 * Command builder for node creation.
 * 
 * @author mchauvin
 */
public class NodeCreationCommandBuilder extends AbstractDiagramCommandBuilder {

    /**
     * Current tool description from which this CommandBuilder build a Command.
     */
    protected final NodeCreationDescription tool;

    /**
     * {@link DDiagramElement} on which the current NodeCreationDescription's
     * operations are executed.
     */
    protected DDiagramElement diagramElement;

    /**
     * {@link DDiagram} on which (or parent of the diagramElement on which) the
     * current NodeCreationDescription's operations are executed.
     */
    protected DDiagram diagram;

    private final boolean createInDiagram;

    /**
     * Create a new node creation command builder instance.
     * 
     * @param tool
     *            a node creation tool
     * @param diagramElement
     *            the diagram element in which the created element should be
     *            displayed
     */
    public NodeCreationCommandBuilder(final NodeCreationDescription tool, final DDiagramElement diagramElement) {
        this.tool = tool;
        this.diagramElement = diagramElement;
        this.diagram = diagramElement != null ? diagramElement.getParentDiagram() : null;
        this.createInDiagram = false;
    }

    /**
     * Create a new node creation command builder instance.
     * 
     * @param tool
     *            a node creation tool
     * @param diagram
     *            the diagram in which the created element should be displayed
     */
    public NodeCreationCommandBuilder(final NodeCreationDescription tool, final DDiagram diagram) {
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
        if (createInDiagram) {
            return buildInDiagramCommand();
        } else {
            return buildInDiagramElementCommand();
        }

    }

    private Command buildInDiagramElementCommand() {
        if (canCreateNodeInTarget()) {
            if (checkPrecondition(diagramElement, tool)) {
                final DCommand result = buildCreateNodeCommandFromTool(diagramElement.getTarget(), diagramElement);
                result.getTasks().add(buildCreateNodeTask(result));
                addRefreshTask(diagramElement, result, tool);
                Option<DDiagram> parentDiagram = new EObjectQuery(diagramElement).getParentDiagram();
                result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(diagramElement.getTarget()), diagramElement.getTarget(), parentDiagram.get()));
                return result;
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    private Command buildInDiagramCommand() {
        if (permissionAuthority.canEditInstance(diagram)) {
            EObject model = null;
            if (diagram instanceof DSemanticDiagram) {
                model = ((DSemanticDiagram) diagram).getTarget();
            }
            if (model != null && checkPrecondition(diagram, tool)) {
                final DCommand result = buildCreateNodeCommandFromTool(model, diagram);
                result.getTasks().add(new CreateDNodeTask(tool, result, modelAccessor, diagram));
                addRefreshTask(diagram, result, tool);
                result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(model), model, diagram));
                return result;
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Build a command to create a {@link DNode} considering the semantic
     * container and a {@link NodeCreationDescription}.
     * 
     * @param semanticContainer
     *            the semantic container.
     * @param container
     *            the container
     * @return a command able to create the {@link DNode}.
     */
    protected DCommand buildCreateNodeCommandFromTool(final EObject semanticContainer, final EObject container) {
        final DCommand result = createEnclosingCommand();
        // the CDOLockBasedPermissionAuthority can't deal with null
        // objects
        if (canCreateNodeInTarget()) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticContainer);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            variables.put(tool.getVariable(), semanticContainer);
            variables.put(tool.getViewVariable(), container);
            addDiagramVariable(result, container, interpreter);

            if (tool.getInitialOperation().getFirstModelOperations() != null) {
                result.getTasks().add(taskHelper.buildTaskFromModelOperation(diagram, semanticContainer, tool.getInitialOperation().getFirstModelOperations()));
            }

        } else {
            result.getTasks().add(UnexecutableTask.INSTANCE);
        }
        return result;
    }

    /**
     * Indicates if the target of the Node to create (DDiagramElement or
     * directly the DDiagram) can be editable (by calling the
     * PermissionAuthority).
     * 
     * @return true if the node can be created, false otherwise
     */
    private boolean canCreateNodeInTarget() {
        boolean nodeCanBeCreatedInTarget = false;
        if (diagramElement != null) {
            nodeCanBeCreatedInTarget = permissionAuthority.canEditInstance(diagramElement);
            if (nodeCanBeCreatedInTarget) {
                EObject target = diagramElement.getTarget();
                nodeCanBeCreatedInTarget = target != null && !target.eIsProxy();
            }
        } else if (diagram != null) {
            nodeCanBeCreatedInTarget = permissionAuthority.canEditInstance(diagram);
            if (nodeCanBeCreatedInTarget && diagram instanceof DSemanticDecorator) {
                EObject target = ((DSemanticDecorator) diagram).getTarget();
                nodeCanBeCreatedInTarget = target != null && !target.eIsProxy();
            }
        }
        return nodeCanBeCreatedInTarget;
    }

    private ICommandTask buildCreateNodeTask(final DCommand createdObjects) {
        ICommandTask task = null;
        if (diagramElement instanceof DNode) {
            task = new CreateDNodeTask(tool, createdObjects, modelAccessor, (DNode) diagramElement);
        } else if (diagramElement instanceof DDiagramElementContainer) {
            task = new CreateDNodeTask(tool, createdObjects, modelAccessor, (DDiagramElementContainer) diagramElement);
        }
        return task;
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
