/*******************************************************************************
 * Copyright (c) 2012, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.command;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshSemanticOrderingsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.GateCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.DelegatingDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;

/**
 * A specialized diagram command factory which knows how to handle the additional variables passed to sequence tools.
 * 
 * 
 * @author mporhel
 */
public final class SequenceDelegatingCommandFactory extends DelegatingDiagramCommandFactory {
    /** The editing domain. */
    private final TransactionalEditingDomain domain;

    private final SequenceDiagram seqDiag;

    private EObject predecessor;

    private EventEnd startingEndPredecessor;

    private EventEnd finishingEndPredecessor;

    private EventEnd endBefore;

    private List<EObject> coverage;

    private Point location;

    /**
     * Constructor.
     * 
     * @param baseFactory
     *            the base command factory.
     * @param domain
     *            the current editing domain.
     * @param seqDiag
     *            the current sequence diagram.
     */
    private SequenceDelegatingCommandFactory(IDiagramCommandFactory baseFactory, TransactionalEditingDomain domain, SequenceDiagram seqDiag) {
        super(baseFactory);
        this.domain = domain;
        this.seqDiag = seqDiag;
    }

    /**
     * Constructor.
     * 
     * @param baseFactory
     *            the base command factory.
     * @param domain
     *            the current editing domain.
     * @param seqDiag
     *            the current sequence diagram.
     * @param startPredecessor
     *            the first click end predecessor.
     * @param finishPredecessor
     *            the second click end predecessor.
     */
    public SequenceDelegatingCommandFactory(IDiagramCommandFactory baseFactory, TransactionalEditingDomain domain, SequenceDiagram seqDiag, EventEnd startPredecessor, EventEnd finishPredecessor) {
        this(baseFactory, domain, seqDiag);
        this.startingEndPredecessor = startPredecessor;
        this.finishingEndPredecessor = finishPredecessor;
    }

    /**
     * Constructor.
     * 
     * @param baseFactory
     *            the base command factory.
     * @param domain
     *            the current editing domain.
     * @param seqDiag
     *            the current sequence diagram.
     * @param startingEndPredecessor
     *            the first click end predecessor.
     * @param finishingEndPredecessor
     *            the second click end predecessor.
     * @param coverage
     *            the semantic coverage.
     */
    public SequenceDelegatingCommandFactory(IDiagramCommandFactory baseFactory, TransactionalEditingDomain domain, SequenceDiagram seqDiag, EventEnd startingEndPredecessor,
            EventEnd finishingEndPredecessor, List<EObject> coverage) {
        this(baseFactory, domain, seqDiag, startingEndPredecessor, finishingEndPredecessor);
        this.coverage = coverage;

    }

    /**
     * Constructor.
     * 
     * @param baseFactory
     *            the base command factory.
     * @param domain
     *            the current editing domain.
     * @param seqDiag
     *            the current sequence diagram.
     * @param startingEndPredecessor
     *            the first click end predecessor.
     * @param finishingEndPredecessor
     *            the second click end predecessor.
     * @param location
     *            the clic location.
     */
    public SequenceDelegatingCommandFactory(IDiagramCommandFactory baseFactory, TransactionalEditingDomain domain, SequenceDiagram seqDiag, EventEnd startingEndPredecessor,
            EventEnd finishingEndPredecessor, Point location) {
        this(baseFactory, domain, seqDiag, startingEndPredecessor, finishingEndPredecessor);
        this.location = location;
    }

    /**
     * Constructor.
     * 
     * @param baseFactory
     *            the base command factory.
     * @param domain
     *            the current editing domain.
     * @param seqDiag
     *            the current sequence diagram.
     * @param endBefore
     *            the end before the click.
     * @param location
     *            the clic location.
     * 
     */
    public SequenceDelegatingCommandFactory(IDiagramCommandFactory baseFactory, TransactionalEditingDomain domain, SequenceDiagram seqDiag, EventEnd endBefore, Point location) {
        this(baseFactory, domain, seqDiag);
        this.endBefore = endBefore;
        this.location = location;
    }

    /**
     * Constructor.
     * 
     * @param baseFactory
     *            the base command factory.
     * @param domain
     *            the current editing domain.
     * @param seqDiag
     *            the current sequence diagram.
     * @param predecessor
     *            the semantic EObject before the click before the click.
     * @param location
     *            the clic location.
     * 
     */
    public SequenceDelegatingCommandFactory(IDiagramCommandFactory baseFactory, TransactionalEditingDomain domain, SequenceDiagram seqDiag, EObject predecessor, Point location) {
        this(baseFactory, domain, seqDiag);
        this.predecessor = predecessor;
        this.location = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildCreateContainerCommandFromTool(DDiagram diagram, ContainerCreationDescription tool) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, startingEndPredecessor)) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else if (tool instanceof InteractionUseCreationTool) {
            result = buildInteractionUseCreationFromTool(diagram, (InteractionUseCreationTool) tool);
        } else if (tool instanceof CombinedFragmentCreationTool) {
            result = buildCombinedFragmentCreationFromTool(diagram, (CombinedFragmentCreationTool) tool);
        } else {
            result = super.buildCreateContainerCommandFromTool(diagram, tool);
        }
        return result;
    }

    private org.eclipse.emf.common.command.Command buildInteractionUseCreationFromTool(DDiagram diagram, InteractionUseCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateInteractionUseCommandFromTool(diagram, tool, startingEndPredecessor, finishingEndPredecessor, coverage);
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation((SequenceDDiagram) diagram)));
        return emfCommand;
    }

    private org.eclipse.emf.common.command.Command buildCombinedFragmentCreationFromTool(DDiagram diagram, CombinedFragmentCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateCombinedFragmentCommandFromTool(diagram, tool, startingEndPredecessor, finishingEndPredecessor, coverage);
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation((SequenceDDiagram) diagram)));
        return emfCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildGenericToolCommandFromTool(EObject containerView, ToolDescription tool) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, endBefore)) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else {
            result = buildSequenceGenericToolCommandFromTool(containerView, tool);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildSelectionWizardCommandFromTool(SelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, endBefore)) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else {
            result = buildSequenceSelectionWizardCommandFromTool(tool, dContainer, selectedElement);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildPaneBasedSelectionWizardCommandFromTool(PaneBasedSelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, endBefore)) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else {
            result = buildSequencePaneBasedSelectionWizardCommandFromTool(tool, dContainer, selectedElement);
        }
        return result;
    }

    private Command buildSequenceGenericToolCommandFromTool(EObject containerView, ToolDescription tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildSequenceGenericToolCommandFromTool(containerView, tool, endBefore, location, seqDiag.getNotationDiagram());
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(seqDiag.getSequenceDDiagram())));
        return emfCommand;
    }

    private Command buildSequenceSelectionWizardCommandFromTool(SelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildSequenceSelectionWizardCommandFromTool(tool, dContainer, selectedElement, endBefore, location);
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(seqDiag.getSequenceDDiagram())));
        return emfCommand;
    }

    private Command buildSequencePaneBasedSelectionWizardCommandFromTool(PaneBasedSelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildSequencePaneBasedSelectionWizardCommandFromTool(tool, dContainer, selectedElement, endBefore, location);
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(seqDiag.getSequenceDDiagram())));
        return emfCommand;
    }

    @Override
    public Command buildCreateNodeCommandFromTool(DDiagramElementContainer container, NodeCreationDescription tool) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, startingEndPredecessor)) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else if (tool instanceof ObservationPointCreationTool) {
            result = buildObservationPointCreationCommandFromTool(container, (ObservationPointCreationTool) tool);
        } else if (tool instanceof GateCreationTool) {
            result = buildGateCreationCommandFromTool(container, (GateCreationTool) tool);
        } else {
            result = super.buildCreateNodeCommandFromTool(container, tool);
        }
        return result;
    }

    @Override
    public org.eclipse.emf.common.command.Command buildCreateNodeCommandFromTool(DNode node, NodeCreationDescription tool) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, startingEndPredecessor)) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else if (tool instanceof InstanceRoleCreationTool) {
            result = buildInstanceRoleCreationCommandFromTool(seqDiag.getSequenceDDiagram(), (InstanceRoleCreationTool) tool);
        } else if (tool instanceof ExecutionCreationTool) {
            result = buildExecutionCreationCommandFromTool(node, (ExecutionCreationTool) tool);
        } else if (tool instanceof StateCreationTool) {
            result = buildStateCreationCommandFromTool(node, (StateCreationTool) tool);
        } else if (tool instanceof ObservationPointCreationTool) {
            result = buildObservationPointCreationCommandFromTool(node, (ObservationPointCreationTool) tool);
        } else {
            result = super.buildCreateNodeCommandFromTool(node, tool);
        }
        return result;
    }

    @Override
    public org.eclipse.emf.common.command.Command buildCreateNodeCommandFromTool(DDiagram diagram, NodeCreationDescription tool) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, startingEndPredecessor) || diagram != seqDiag.getSequenceDDiagram()) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else if (tool instanceof InstanceRoleCreationTool) {
            result = buildInstanceRoleCreationCommandFromTool(seqDiag.getSequenceDDiagram(), (InstanceRoleCreationTool) tool);
        } else if (tool instanceof ObservationPointCreationTool) {
            result = buildObservationPointCreationCommandFromTool(seqDiag.getSequenceDDiagram(), (ObservationPointCreationTool) tool);
        } else {
            result = super.buildCreateNodeCommandFromTool(diagram, tool);
        }
        return result;
    }

    @Override
    public org.eclipse.emf.common.command.Command buildCreateContainerCommandFromTool(DDiagramElementContainer nodeContainer, ContainerCreationDescription tool) {
        final org.eclipse.emf.common.command.Command result;
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(seqDiag, startingEndPredecessor)) {
            result = org.eclipse.emf.common.command.UnexecutableCommand.INSTANCE;
        } else if (tool instanceof OperandCreationTool) {
            result = buildOperandCreationCommandFromTool(nodeContainer, (OperandCreationTool) tool);
        } else if (tool instanceof GateCreationTool) {
            result = buildGateCreationCommandFromTool(nodeContainer, (GateCreationTool) tool);
        } else {
            result = super.buildCreateContainerCommandFromTool(nodeContainer, tool);
        }
        return result;
    }

    private Command buildInstanceRoleCreationCommandFromTool(SequenceDDiagram diagram, InstanceRoleCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateInstanceRoleCommandFromTool(diagram, tool, predecessor, location);
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diagram)));
        return emfCommand;
    }

    private org.eclipse.emf.common.command.Command buildExecutionCreationCommandFromTool(DNode node, ExecutionCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateExecutionCommandFromTool(node, tool, startingEndPredecessor, finishingEndPredecessor, location);
        SequenceDDiagram diagram = (SequenceDDiagram) node.getParentDiagram();
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diagram)));
        return emfCommand;
    }

    private org.eclipse.emf.common.command.Command buildObservationPointCreationCommandFromTool(DDiagramElement diagramElement, ObservationPointCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateObservationPointCommandFromTool(diagramElement, tool, startingEndPredecessor, finishingEndPredecessor);
        SequenceDDiagram diagram = (SequenceDDiagram) diagramElement.getParentDiagram();
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diagram)));
        return emfCommand;
    }

    private org.eclipse.emf.common.command.Command buildObservationPointCreationCommandFromTool(SequenceDDiagram diag, ObservationPointCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateObservationPointCommandFromTool(diag, tool, startingEndPredecessor, finishingEndPredecessor);
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diag)));
        return emfCommand;
    }

    private org.eclipse.emf.common.command.Command buildStateCreationCommandFromTool(DNode node, StateCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateStateCommandFromTool(node, tool, startingEndPredecessor, finishingEndPredecessor);
        SequenceDDiagram diagram = (SequenceDDiagram) node.getParentDiagram();
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diagram)));
        return emfCommand;
    }

    private org.eclipse.emf.common.command.Command buildOperandCreationCommandFromTool(DDiagramElementContainer nodeContainer, OperandCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateOperantCommandFromTool(nodeContainer, tool, startingEndPredecessor, finishingEndPredecessor);
        SequenceDDiagram diagram = (SequenceDDiagram) nodeContainer.getParentDiagram();
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diagram)));
        return emfCommand;
    }

    private org.eclipse.emf.common.command.Command buildGateCreationCommandFromTool(DDiagramElementContainer nodeContainer, GateCreationTool tool) {
        org.eclipse.emf.common.command.Command emfCommand = ToolCommandBuilder.buildCreateGateCommandFromTool(nodeContainer, tool, startingEndPredecessor, finishingEndPredecessor, location);
        SequenceDDiagram diagram = (SequenceDDiagram) nodeContainer.getParentDiagram();
        emfCommand = emfCommand.chain(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diagram)));
        return emfCommand;
    }
}
