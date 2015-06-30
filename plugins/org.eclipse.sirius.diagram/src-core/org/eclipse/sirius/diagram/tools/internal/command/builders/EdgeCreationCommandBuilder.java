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
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.helper.task.CreateDEdgeTask;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.api.ui.resource.ISiriusMessages;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;

/**
 * .
 * 
 * @author mchauvin
 */
public class EdgeCreationCommandBuilder extends AbstractDiagramCommandBuilder {

    /**
     * Current tool description from which this CommandBuilder build a Command.
     */
    protected final EdgeCreationDescription tool;

    /**
     * {@link EdgeTarget} source from which the current
     * EdgeCreationCommandBuilder operations are executed.
     */
    protected final EdgeTarget source;

    /**
     * {@link EdgeTarget} target from which the current
     * EdgeCreationCommandBuilder operations are executed.
     */
    protected final EdgeTarget target;

    /**
     * Create a new edge creation command builder instance.
     * 
     * @param tool
     *            an edge creation tool
     * @param source
     *            the source node of the edge
     * @param target
     *            the target node of the edge
     */
    public EdgeCreationCommandBuilder(final EdgeCreationDescription tool, final EdgeTarget source, final EdgeTarget target) {
        this.tool = tool;
        this.source = source;
        this.target = target;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    @Override
    public Command buildCommand() {
        if (canCreateEdge()) {
            EObject sourceTarget = ((DSemanticDecorator) source).getTarget();
            EObject targetTarget = ((DSemanticDecorator) target).getTarget();
            final DCommand result = buildCreateEdgeCommandFromTool(sourceTarget, targetTarget);
            result.getTasks().add(buildCreateEdgeTask(result));
            addRefreshTask((DDiagramElement) source, result, tool);

            Option<DDiagram> parentDiagram = new EObjectQuery(source).getParentDiagram();
            result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(sourceTarget), sourceTarget, parentDiagram.get()));
            return result;
        }
        return UnexecutableCommand.INSTANCE;
    }

    private boolean canCreateEdge() {
        boolean valid = permissionAuthority.canEditInstance(source);
        valid = valid && permissionAuthority.canEditInstance(target);
        valid = valid && (source instanceof DSemanticDecorator);
        valid = valid && (target instanceof DSemanticDecorator);
        valid = valid && checkPrecondition();
        return valid;
    }

    /**
     * {@inheritDoc}
     */
    protected boolean checkPrecondition() {
        boolean valid = true;

        if (tool.getPrecondition() != null && !StringUtil.isEmpty(tool.getPrecondition().trim())) {
            EObject sourceTarget = ((DSemanticDecorator) source).getTarget();
            EObject targetTarget = ((DSemanticDecorator) target).getTarget();

            Option<DDiagram> diagram = getDDiagram();

            EObject container = null;

            if (diagram.some() && diagram.get() instanceof DSemanticDecorator) {
                container = ((DSemanticDecorator) diagram.get()).getTarget();
            } else {
                SiriusPlugin.getDefault().warning(ISiriusMessages.IS_NOT_A_DECORATE_SEMANTIC_ELEMENT, null);
            }

            final IInterpreter interpreter = InterpreterUtil.getInterpreter(sourceTarget);

            interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_PRE, sourceTarget);
            interpreter.setVariable(IInterpreterSiriusVariables.TARGET_PRE, targetTarget);
            interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW_PRE, source);
            interpreter.setVariable(IInterpreterSiriusVariables.TARGET_VIEW_PRE, target);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());

            valid = evaluatePrecondition(interpreter, sourceTarget, tool.getPrecondition());

            interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_PRE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_PRE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW_PRE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET_VIEW_PRE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }

        return valid;
    }

    /**
     * Return the connection start precondition value.
     * 
     * @return boolean true if the condition is checked, false otherwise
     */
    public boolean checkStartPrecondition() {
        boolean valid = true;

        if (tool.getConnectionStartPrecondition() != null && !StringUtil.isEmpty(tool.getConnectionStartPrecondition().trim())) {
            EObject sourceTarget = ((DSemanticDecorator) source).getTarget();
            Option<DDiagram> diagram = new EObjectQuery(source).getParentDiagram();
            EObject container = null;
            if (diagram.some() && diagram.get() instanceof DSemanticDecorator) {
                container = ((DSemanticDecorator) diagram.get()).getTarget();
            } else {
                SiriusPlugin.getDefault().warning(ISiriusMessages.IS_NOT_A_DECORATE_SEMANTIC_ELEMENT, null);
            }

            final IInterpreter interpreter = InterpreterUtil.getInterpreter(sourceTarget);

            interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_PRE, sourceTarget);
            interpreter.setVariable(IInterpreterSiriusVariables.SOURCE_VIEW_PRE, source);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());

            valid = evaluatePrecondition(interpreter, sourceTarget, tool.getConnectionStartPrecondition());

            interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_PRE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE_VIEW_PRE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }

        return valid;
    }

    /**
     * Create a command to create an Edge.
     * 
     * @param semanticSource
     *            the source semantic element of the edge.
     * @param semanticTarget
     *            the target semantic element of the edge.
     * @return a command that is able to create an edge.
     */
    protected DCommand buildCreateEdgeCommandFromTool(final EObject semanticSource, final EObject semanticTarget) {
        final DCommand result = createEnclosingCommand();
        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        variables.put(tool.getSourceVariable(), semanticSource);
        variables.put(tool.getSourceViewVariable(), source);
        variables.put(tool.getTargetVariable(), semanticTarget);
        variables.put(tool.getTargetViewVariable(), target);
        initSubvariables(tool.getSourceVariable(), variables, semanticSource);
        initSubvariables(tool.getTargetVariable(), variables, semanticTarget);

        Option<DDiagram> parentDiagram = new EObjectQuery(source).getParentDiagram();
        result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(source), uiCallback));
        result.getTasks().add(taskHelper.buildTaskFromModelOperation(parentDiagram.get(), semanticSource, tool.getInitialOperation().getFirstModelOperations()));
        return result;
    }

    /**
     * Initializes user-defined sub-variables.
     * 
     * @param var
     *            the container of the sub-variables to initialize.
     * @param variables
     *            the global variables environment in which to put the result.
     * @param modelElement
     *            the context.
     */
    protected void initSubvariables(final VariableContainer var, final Map<AbstractVariable, Object> variables, final EObject modelElement) {
        final Iterator<SubVariable> it = var.getSubVariables().iterator();
        while (it.hasNext()) {
            final AbstractVariable curVar = it.next();
            if (!variables.containsKey(curVar)) {
                variables.put(curVar, modelElement);
                if (curVar instanceof AcceleoVariable) {
                    // TODO code the initialization of an AcceleoVariable
                }
            }
            if (curVar instanceof VariableContainer) {
                initSubvariables((VariableContainer) curVar, variables, modelElement);
            }
        }
    }

    private ICommandTask buildCreateEdgeTask(final DCommand createdObjects) {
        final ICommandTask result = new CreateDEdgeTask(tool, createdObjects, modelAccessor, source, target);
        return result;
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
        Option<DDiagram> diagram = new EObjectQuery(source).getParentDiagram();
        if (!diagram.some()) {
            diagram = new EObjectQuery(target).getParentDiagram();
        }
        return diagram;
    }

}
