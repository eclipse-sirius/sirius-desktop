/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.tool;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.ExecutionCreationCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.FrameCreationCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.InstanceRoleCreationCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.MessageCreationCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.ObservationPointCreationCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.OperandCreationCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.SequenceGenericToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.SequencePaneBasedSelectionWizardCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.SequenceSelectionWizardCommandBuilder;
import org.eclipse.sirius.diagram.sequence.internal.tool.command.builders.StateCreationCommandBuilder;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.tools.internal.command.builders.NodeCreationCommandBuilder;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.EMFCommandFactoryUI;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.CommandBuilder;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;

/**
 * Helper class to build the concrete commands executing the user-specified tools on specific parameters.
 * 
 * @author pcdavid
 */
public final class ToolCommandBuilder {

    private ToolCommandBuilder() {
        // Prevent instantiation.
    }

    /**
     * Builds the command which will execute the user-tasks specified operations to reorder an event.
     * 
     * @param sequenceDDiagram
     *            the DRepresentation to use for the ModelOperations
     * @param reorderTool
     *            the user-defined reordering tool.
     * @param event
     *            the semantic element of the event which was moved.
     * @param startingEndPredecessor
     *            the event end immediately preceding the reordered event's starting end after the move.
     * @param finishingEndPredecessor
     *            the event end immediately preceding the reordered event's finishing end after the move.
     * @return a command which executes the user-specified operations with the appropriate variables set.
     */
    public static SiriusCommand buildReorderCommand(SequenceDDiagram sequenceDDiagram, ReorderTool reorderTool, EObject event, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor) {
        ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(event);
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(event);

        SiriusCommand result = new SiriusCommand(domain, new IdentifiedElementQuery(reorderTool).getLabel());
        if (reorderTool.getOnEventMovedOperation().getFirstModelOperations() != null) {
            result.getTasks().add(ToolCommandBuilder.buildVariablesInitializationTask(reorderTool, event, startingEndPredecessor, finishingEndPredecessor));
            TaskHelper taskHelper = new TaskHelper(modelAccessor, new EMFCommandFactoryUI());

            result.getTasks().add(taskHelper.buildTaskFromModelOperation(sequenceDDiagram, event, reorderTool.getOnEventMovedOperation().getFirstModelOperations()));
        }
        return result;
    }

    private static InitInterpreterVariablesTask buildVariablesInitializationTask(ReorderTool reorderTool, EObject event, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor) {
        Map<AbstractVariable, Object> variables = new HashMap<>();
        variables.put(reorderTool.getStartingEndPredecessorAfter(), startingEndPredecessor);
        variables.put(reorderTool.getFinishingEndPredecessorAfter(), finishingEndPredecessor);
        return new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(event), null);
    }

    /**
     * Builds the command which will execute the user-tasks specified operations to reorder an instance role.
     * 
     * @param sequenceDDiagram
     *            the DRepresentation to use for the ModelOperations
     * @param reorderTool
     *            the user-defined instance role reordering tool.
     * @param element
     *            the semantic element of the instance role which was moved.
     * @param predecessorBefore
     *            the semantic element corresponding to the instance role immediately preceding the reordered instance
     *            role before the move.
     * @param predecessorAfter
     *            the semantic element corresponding to the instance role immediately preceding the reordered instance
     *            role after the move.
     * @return a command which executes the user-specified operations with the appropriate variables set.
     */
    public static SiriusCommand buildInstanceRoleReorderCommand(SequenceDDiagram sequenceDDiagram, InstanceRoleReorderTool reorderTool, EObject element, EObject predecessorBefore,
            EObject predecessorAfter) {
        ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(element);
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);

        SiriusCommand result = new SiriusCommand(domain, new IdentifiedElementQuery(reorderTool).getLabel());
        if (reorderTool.getInstanceRoleMoved().getFirstModelOperations() != null) {
            result.getTasks().add(ToolCommandBuilder.buildVariablesInitializationTask(reorderTool, element, predecessorBefore, predecessorAfter));
            TaskHelper taskHelper = new TaskHelper(modelAccessor, new EMFCommandFactoryUI());
            result.getTasks().add(taskHelper.buildTaskFromModelOperation(sequenceDDiagram, element, reorderTool.getInstanceRoleMoved().getFirstModelOperations()));
        }
        return result;
    }

    private static InitInterpreterVariablesTask buildVariablesInitializationTask(InstanceRoleReorderTool reorderTool, EObject element, EObject predecessorBefore, EObject predecessorAfter) {
        Map<AbstractVariable, Object> variables = new HashMap<>();
        variables.put(reorderTool.getPredecessorBefore(), predecessorBefore);
        variables.put(reorderTool.getPredecessorAfter(), predecessorAfter);
        return new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(element), null);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new message.
     * 
     * @param source
     *            the source of the new message to create.
     * @param target
     *            the target of the new message to create.
     * @param tool
     *            the tool to use to create the message.
     * @param startingEndBefore
     *            the event end which precedes graphically the source location of the new message, to be used by the
     *            tool do decide where to insert the message in the semantic model.
     * @param finishingEndBefore
     *            the event end which precedes graphically the target location of the new message, to be used by the
     *            tool do decide where to insert the message in the semantic model.
     * @return a command to create the message.
     */
    public static Command buildCreateMessageCommand(final EdgeTarget source, final EdgeTarget target, final MessageCreationTool tool, final EventEnd startingEndBefore,
            final EventEnd finishingEndBefore) {
        CommandBuilder builder = new MessageCreationCommandBuilder(tool, source, target, startingEndBefore, finishingEndBefore);
        return getCommand(builder, source);
    }

    private static Command getCommand(CommandBuilder builder, EObject dObject) {
        ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(dObject);
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dObject);
        EMFCommandFactoryUI uiCallback = new EMFCommandFactoryUI();
        builder.init(accessor, domain, uiCallback);
        return builder.buildCommand();
    }

    private static Command getCommand(CommandBuilder builder, Session session) {
        EMFCommandFactoryUI uiCallback = new EMFCommandFactoryUI();
        builder.init(session.getModelAccessor(), session.getTransactionalEditingDomain(), uiCallback);
        return builder.buildCommand();
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new instance role.
     * 
     * @param diagram
     *            the sequence diagram on which to create the new instance role.
     * @param tool
     *            the tool to use to create the instance role.
     * @param predecessor
     *            the semantic element corresponding to the instance role graphically preceding the x location of the
     *            new instance role.
     * @param location
     *            the clicked location.
     * @return a command to create the instance role.
     */
    public static Command buildCreateInstanceRoleCommandFromTool(final DDiagram diagram, final InstanceRoleCreationTool tool, final EObject predecessor, Point location) {
        CommandBuilder builder = new InstanceRoleCreationCommandBuilder(tool, diagram, predecessor, location);
        return getCommand(builder, diagram);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new execution.
     * 
     * @param node
     *            the node on which to create the new execution.
     * @param tool
     *            the tool to use to create the execution.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new execution.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new execution.
     * @param location
     *            the clicked location.
     * @return a command to create the execution.
     */
    public static Command buildCreateExecutionCommandFromTool(final DNode node, final ExecutionCreationTool tool, final EventEnd startingEndPredecessor, final EventEnd finishingEndPredecessor,
            Point location) {
        CommandBuilder builder = new ExecutionCreationCommandBuilder(tool, node, startingEndPredecessor, finishingEndPredecessor, location);
        Session session = SessionManager.INSTANCE.getSession(node.getTarget());
        return getCommand(builder, session);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new observation point.
     * 
     * @param diagramElement
     *            the clicked diagram element.
     * @param tool
     *            the tool to use to create the execution.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new execution.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new execution.
     * @return a command to create the execution.
     */
    public static Command buildCreateObservationPointCommandFromTool(final DDiagramElement diagramElement, final ObservationPointCreationTool tool, final EventEnd startingEndPredecessor,
            final EventEnd finishingEndPredecessor) {
        NodeCreationCommandBuilder builder = new ObservationPointCreationCommandBuilder(tool, diagramElement, startingEndPredecessor, finishingEndPredecessor);
        Session session = SessionManager.INSTANCE.getSession(diagramElement.getTarget());
        return getCommand(builder, session);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new observation point.
     * 
     * @param diagram
     *            the diagram on which to create the new execution.
     * @param tool
     *            the tool to use to create the execution.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new execution.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new execution.
     * @return a command to create the execution.
     */
    public static Command buildCreateObservationPointCommandFromTool(final DDiagram diagram, final ObservationPointCreationTool tool, final EventEnd startingEndPredecessor,
            final EventEnd finishingEndPredecessor) {
        CommandBuilder builder = new ObservationPointCreationCommandBuilder(tool, diagram, startingEndPredecessor, finishingEndPredecessor);
        return getCommand(builder, diagram);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new state.
     * 
     * @param node
     *            the node on which to create the new state.
     * @param tool
     *            the tool to use to create the state.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new state.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new state.
     * @return a command to create the state.
     */
    public static Command buildCreateStateCommandFromTool(final DNode node, final StateCreationTool tool, final EventEnd startingEndPredecessor, final EventEnd finishingEndPredecessor) {
        CommandBuilder builder = new StateCreationCommandBuilder(tool, node, startingEndPredecessor, finishingEndPredecessor);
        Session session = SessionManager.INSTANCE.getSession(node.getTarget());
        return getCommand(builder, session);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new interaction use.
     * 
     * @param diagram
     *            the diagram on which to create the new IU.
     * @param tool
     *            the tool to use to create the IU.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new IU.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new IU.
     * @param coverage
     *            the semantic elements representing the lifelines graphically covered by the initial area of the IU.
     * @return a command to create the IU.
     */
    public static Command buildCreateInteractionUseCommandFromTool(DDiagram diagram, InteractionUseCreationTool tool, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor,
            List<EObject> coverage) {
        return ToolCommandBuilder.buildCreateFrameCommandFromTool(diagram, tool, startingEndPredecessor, finishingEndPredecessor, coverage);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new combined fragment.
     * 
     * @param diagram
     *            the diagram on which to create the new CF.
     * @param tool
     *            the tool to use to create the CF.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new CF.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new CF.
     * @param coverage
     *            the semantic elements representing the lifelines graphically covered by the initial area of the CF.
     * @return a command to create the CF.
     */
    public static Command buildCreateCombinedFragmentCommandFromTool(DDiagram diagram, CombinedFragmentCreationTool tool, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor,
            List<EObject> coverage) {
        return ToolCommandBuilder.buildCreateFrameCommandFromTool(diagram, tool, startingEndPredecessor, finishingEndPredecessor, coverage);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new combined fragment.
     * 
     * @param diagram
     *            the diagram on which to create the new execution.
     * @param tool
     *            the tool to use to create the combined fragment.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new CF.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new CF.
     * @param coverage
     *            the semantic elements representing the lifelines graphically covered by the initial area of the CF.
     * @return a command to create the execution.
     */
    private static Command buildCreateFrameCommandFromTool(DDiagram diagram, final ContainerCreationDescription tool, final EventEnd startingEndPredecessor, final EventEnd finishingEndPredecessor,
            final List<EObject> coverage) {
        CommandBuilder builder = new FrameCreationCommandBuilder(tool, diagram, startingEndPredecessor, finishingEndPredecessor, coverage);
        return getCommand(builder, diagram);
    }

    /**
     * Builds the command which will execute the user-specified operations to create a new operand.
     * 
     * @param nodeContainer
     *            the container in which to create the operand.
     * @param tool
     *            the tool to use to create the operand.
     * @param startingEndPredecessor
     *            the event end graphically preceding the starting position of the new Operand.
     * @param finishingEndPredecessor
     *            the event end graphically preceding the finishing position of the new Operand.
     * @return a command to create the execution.
     */
    public static org.eclipse.emf.common.command.Command buildCreateOperantCommandFromTool(DDiagramElementContainer nodeContainer, final OperandCreationTool tool,
            final EventEnd startingEndPredecessor, final EventEnd finishingEndPredecessor) {
        CommandBuilder builder = new OperandCreationCommandBuilder(tool, nodeContainer, startingEndPredecessor, finishingEndPredecessor);
        Session session = SessionManager.INSTANCE.getSession(nodeContainer.getTarget());
        return getCommand(builder, session);
    }

    /**
     * Check if startingEndPredecessor is a starting {@link EventEnd} of {@link CombinedFragment}.
     * 
     * @param sequenceDiagram
     *            the {@link SequenceDiagram} on which checks the property
     * @param startingEndPredecessor
     *            the {@link EventEnd} to evaluate
     * @return true if startingEndPredecessor is a starting {@link EventEnd} of {@link CombinedFragment}
     */
    public static boolean isStartingEventEndOfCombinedFragment(SequenceDiagram sequenceDiagram, EventEnd startingEndPredecessor) {
        if (sequenceDiagram != null && startingEndPredecessor instanceof SingleEventEnd && ((SingleEventEnd) startingEndPredecessor).isStart()) {
            ISequenceEvent ise = EventEndHelper.findISequenceEvent((SingleEventEnd) startingEndPredecessor, sequenceDiagram);
            return ise instanceof CombinedFragment;
        }
        return false;
    }

    /**
     * Builds the command which will execute the user-specified operations with a generic tool.
     * 
     * @param containerView
     *            the clicked diagram element.
     * @param tool
     *            the generic tool.
     * @param endBefore
     *            the event end graphically preceding the position
     * @param location
     *            the clicked location.
     * @param diagram
     *            the GMF {@link Diagram}
     * @return a command to execute the tool.
     */
    public static Command buildSequenceGenericToolCommandFromTool(EObject containerView, ToolDescription tool, EventEnd endBefore, Point location, Diagram diagram) {
        CommandBuilder builder = new SequenceGenericToolCommandBuilder(tool, containerView, endBefore, location, diagram);
        return getCommand(builder, containerView);
    }

    /**
     * Builds the command which will execute the user-specified operations with a pane based selection tool.
     * 
     * @param tool
     *            the pane based selection tool.
     * @param dContainer
     *            the clicked diagram element.
     * @param selectedElement
     *            the selected elements
     * @param endBefore
     *            the event end graphically preceding the position
     * @param location
     *            the clicked location.
     * @return a command to execute the tool.
     */
    public static Command buildSequencePaneBasedSelectionWizardCommandFromTool(PaneBasedSelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement,
            EventEnd endBefore, Point location) {
        CommandBuilder builder = new SequencePaneBasedSelectionWizardCommandBuilder(tool, dContainer, selectedElement, endBefore, location);
        Session session = SessionManager.INSTANCE.getSession(dContainer.getTarget());
        return getCommand(builder, session);
    }

    /**
     * Builds the command which will execute the user-specified operations with a selection tool.
     * 
     * @param tool
     *            the selection tool.
     * @param dContainer
     *            the clicked diagram element.
     * @param selectedElement
     *            the selected elements
     * @param endBefore
     *            the event end graphically preceding the position
     * @param location
     *            the clicked location.
     * @return a command to execute the tool.
     */
    public static Command buildSequenceSelectionWizardCommandFromTool(SelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement, EventEnd endBefore,
            Point location) {
        CommandBuilder builder = new SequenceSelectionWizardCommandBuilder(tool, dContainer, selectedElement, endBefore, location);
        Session session = SessionManager.INSTANCE.getSession(dContainer.getTarget());
        return getCommand(builder, session);
    }
}
