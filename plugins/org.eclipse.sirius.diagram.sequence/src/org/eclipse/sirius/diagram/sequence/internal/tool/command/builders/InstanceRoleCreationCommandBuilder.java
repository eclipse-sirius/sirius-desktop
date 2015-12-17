/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.internal.tool.command.builders;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.tools.internal.command.builders.NodeCreationCommandBuilder;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;

/**
 * Specific NodeCreationCommandBuilder for InstanceRole.
 * 
 * @author mporhel
 */
public class InstanceRoleCreationCommandBuilder extends NodeCreationCommandBuilder {

    /**
     * the semantic InstanceRole Predecessor.
     */
    protected EObject predecessor;

    private Point location;

    /**
     * Constructor to renseign default elts needed by NodeCreationCommandBuilder
     * and value needed by InstanceroleCreationTool.
     * 
     * @param tool
     *            the InstanceRoleCreationTool tool
     * @param diagram
     *            the diagram on which the created element should be displayed
     * @param predecessor
     *            the semantic InstanceRole Predecessor
     * @param location
     *            the clicked location.
     */
    public InstanceRoleCreationCommandBuilder(InstanceRoleCreationTool tool, DDiagram diagram, EObject predecessor, Point location) {
        super(tool, diagram);
        this.predecessor = predecessor;
        this.location = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand buildCreateNodeCommandFromTool(EObject semanticContainer, EObject container) {
        final DCommand result = createEnclosingCommand();

        if (location != null && result instanceof SequenceCreatedEventsFlaggingSiriusCommand) {
            ((SequenceCreatedEventsFlaggingSiriusCommand) result).setLostNodesLocation(location);
        }

        if (permissionAuthority.canEditInstance(diagramElement)) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticContainer);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            variables.put(tool.getVariable(), semanticContainer);
            variables.put(tool.getViewVariable(), container);
            // <added for InstanceRoleCreationTool>
            if (tool instanceof InstanceRoleCreationTool) {
                InstanceRoleCreationTool instanceRoleCreationTool = (InstanceRoleCreationTool) tool;
                variables.put(instanceRoleCreationTool.getPredecessor(), predecessor);
            }
            // </added for InstanceRoleCreationTool>
            addDiagramVariable(result, container, interpreter);
            if (diagram != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                result.getTasks().add(taskHelper.buildTaskFromModelOperation(diagram, semanticContainer, tool.getInitialOperation().getFirstModelOperations()));
            }

        } else {
            result.getTasks().add(UnexecutableTask.INSTANCE);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand createEnclosingCommand() {
        return new SequenceCreatedEventsFlaggingSiriusCommand(editingDomain, getEnclosingCommandLabel(), diagram, InstanceRole.viewpointElementPredicate());
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * Overridden to have OrderedElementCreationTool.startingEndPredecessor &
     * OrderedElementCreationTool.finishingEndPredecessor variables in tool's
     * precondition expression.
     */
    @Override
    protected boolean evaluatePrecondition(IInterpreter interpreter, EObject semanticContainer, String precondition) {
        if (tool instanceof InstanceRoleCreationTool) {
            InstanceRoleCreationTool instanceRoleCreationTool = (InstanceRoleCreationTool) tool;
            interpreter.setVariable(instanceRoleCreationTool.getPredecessor().getName(), predecessor);
        }
        boolean result = super.evaluatePrecondition(interpreter, semanticContainer, precondition);
        if (tool instanceof InstanceRoleCreationTool) {
            InstanceRoleCreationTool instanceRoleCreationTool = (InstanceRoleCreationTool) tool;
            interpreter.unSetVariable(instanceRoleCreationTool.getPredecessor().getName());
        }
        return result;
    }

}
