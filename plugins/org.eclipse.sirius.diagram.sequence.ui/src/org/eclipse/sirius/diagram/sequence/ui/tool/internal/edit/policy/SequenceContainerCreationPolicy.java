/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.VerticalSpaceExpansionOrReduction;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.command.SequenceDelegatingCommandFactory;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.FrameCreationValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.CreateRequestQuery;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ContainerCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.CreationUtil;
import org.eclipse.sirius.diagram.ui.tools.api.command.DoNothingCommand;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.requests.DistributeRequest;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * An extension of the standard Sirius ContainerCreationEditPolicy which knows how to handle the specific tools use to
 * create frames (i.e. Interaction Uses and Combined Fragments).
 * 
 * @author pcdavid
 */
public class SequenceContainerCreationPolicy extends ContainerCreationEditPolicy {

    /**
     * Additional figures for feedback.
     */
    protected Collection<Figure> guides = new ArrayList<>();

    @Override
    protected Command getCreateNodeOnDiagramCommand(CreateRequest request, NodeCreationDescription tool, DDiagram diagram) {
        Command result;
        if (tool instanceof ObservationPointCreationTool && diagram instanceof SequenceDDiagram) {
            SequenceDDiagram diag = (SequenceDDiagram) diagram;
            Point location = request.getLocation().getCopy();
            GraphicalHelper.screen2logical(location, (IGraphicalEditPart) getHost());

            EventEnd startingEndPredecessor = SequenceGraphicalHelper.getEndBefore(diag, location.y);
            EventEnd finishingEndEndPredecessor = startingEndPredecessor;
            if (request.getSize() != null) {
                Dimension size = request.getSize().getCopy();
                GraphicalHelper.screen2logical(size, (IGraphicalEditPart) getHost());
                finishingEndEndPredecessor = SequenceGraphicalHelper.getEndBefore(diag, location.y + size.height);
            }

            CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(startingEndPredecessor, finishingEndEndPredecessor, location), getRealLocation(request), request.getSize(),
                    getHost());
            result = creationUtil.getNodeCreationCommand(diagram, tool);
        } else if (tool instanceof InstanceRoleCreationTool && diagram instanceof SequenceDDiagram) {
            SequenceDDiagram diag = (SequenceDDiagram) diagram;
            Point location = request.getLocation().getCopy();
            GraphicalHelper.screen2logical(location, (IGraphicalEditPart) getHost());

            EObject predecessor = SequenceGraphicalHelper.getInstanceRoleBefore(diag, location.x);
            CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(predecessor, location), getRealLocation(request), request.getSize(), getHost());
            result = creationUtil.getNodeCreationCommand(diagram, tool);
        } else {
            result = super.getCreateNodeOnDiagramCommand(request, tool, diagram);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getCreateContainerOnDiagramCommand(CreateRequest request, ContainerCreationDescription ccdTool, DDiagram diagram) {
        Command result;

        boolean frameCreationTool = ccdTool instanceof InteractionUseCreationTool || ccdTool instanceof CombinedFragmentCreationTool;
        if (frameCreationTool && getHost() instanceof SequenceDiagramEditPart && diagram instanceof SequenceDDiagram) {
            SequenceDiagramEditPart sdep = (SequenceDiagramEditPart) getHost();
            TransactionalEditingDomain domain = sdep.getEditingDomain();
            Diagram gmfDiagram = sdep.getDiagramView();
            SequenceDiagram sequenceDiagram = ISequenceElementAccessor.getSequenceDiagram(gmfDiagram).get();
            FrameCreationValidator creationValidator = FrameCreationValidator.getOrCreateValidator(sequenceDiagram, ccdTool, new CreateRequestQuery(request, sdep));

            if (creationValidator.isValid()) {
                EventEnd startingEndPredecessor = creationValidator.getStartingEndPredecessor();
                EventEnd finishingEndPredecessor = creationValidator.getFinishingEndPredecessor();
                List<EObject> coverage = creationValidator.getCoverage();
                Range expansionZone = creationValidator.getExpansionZone();

                CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(startingEndPredecessor, finishingEndPredecessor, coverage, getCreationRange(request)),
                        getRealLocation(request), getRealSize(ccdTool, request), getHost());
                result = creationUtil.getContainerCreationDescription(diagram, ccdTool);

                // Add a vertical expansion command if we do inclusion
                if (expansionZone != null && !expansionZone.isEmpty() && result != null && result.canExecute()) {
                    // Shift the element to not include int the range of the
                    // AbstractFrame to create
                    VerticalSpaceExpansionOrReduction verticalSpaceExpansion = new VerticalSpaceExpansionOrReduction(sequenceDiagram, expansionZone, 0, Collections.<ISequenceEvent> emptyList());
                    ICommand expandSubEventsCmd = CommandFactory.createICommand(domain, verticalSpaceExpansion);

                    result = new ICommandProxy(expandSubEventsCmd).chain(result);
                }
                if (result != null) {
                    if (ccdTool instanceof InteractionUseCreationTool) {
                        result.setLabel(Messages.SequenceContainerCreationPolicy_interactionUseCreationCommand);
                    } else if (ccdTool instanceof CombinedFragmentCreationTool) {
                        result.setLabel(Messages.SequenceContainerCreationPolicy_combinedFragmentCreationCommand);
                    }
                }
            } else {
                result = creationValidator.getCoverage().isEmpty() ? DoNothingCommand.INSTANCE : UnexecutableCommand.INSTANCE;
            }
        } else {
            result = super.getCreateContainerOnDiagramCommand(request, ccdTool, diagram);
        }
        return result;
    }

    /**
     * Overridden to show the feedback of the expansion zone for InteractionUse/CombinedFragment creation when there is
     * inclusion of existing sequence events in its creation range and vertical space expansion is needed for some
     * sequence events.
     * 
     * 
     * {@inheritDoc}
     */
    @Override
    public void showTargetFeedback(Request request) {
        eraseTargetFeedback(request);
        if (request instanceof CreateRequest && REQ_CREATE.equals(request.getType()) && this.getHost() instanceof SequenceDiagramEditPart) {
            SequenceDiagramEditPart sdep = (SequenceDiagramEditPart) getHost();
            CreateRequest createRequest = (CreateRequest) request;
            Option<ISequenceElement> seqDiag = ISequenceElementAccessor.getISequenceElement((View) this.getHost().getModel());
            AbstractToolDescription tool = getTool(createRequest);
            if (seqDiag.some() && seqDiag.get() instanceof SequenceDiagram && (tool instanceof InteractionUseCreationTool || tool instanceof CombinedFragmentCreationTool)) {
                FrameCreationValidator validator = FrameCreationValidator.getOrCreateValidator((SequenceDiagram) seqDiag.get(), (ContainerCreationDescription) tool,
                        new CreateRequestQuery(createRequest, sdep));
                if (validator != null) {
                    SequenceInteractionFeedBackBuilder feedBackBuilder = new SequenceInteractionFeedBackBuilder(validator, getFeedbackLayer(), (IGraphicalEditPart) getHost());
                    for (Figure fig : feedBackBuilder.buildFeedBack()) {
                        addFeedback(fig);
                        guides.add(fig);
                    }
                }
            }
        }
    }

    /**
     * Overridden to erase feedback for AbstractFrame creation request.
     * 
     * {@inheritDoc}
     */
    @Override
    public void eraseTargetFeedback(Request request) {
        removeFeedBackOnGuides();
    }

    private void removeFeedBackOnGuides() {
        if (guides != null && !guides.isEmpty()) {
            for (Figure hGuide : guides) {
                removeFeedback(hGuide);
            }
            guides.clear();
        }
    }

    /**
     * Add a IFigure to the feedbackLayer.
     * 
     * @param figure
     *            the feedback figure to add
     */
    protected void addFeedback(IFigure figure) {
        getFeedbackLayer().add(figure);
    }

    /**
     * Returns the layer used for displaying feedback.
     * 
     * @return the feedback layer
     */
    protected IFigure getFeedbackLayer() {
        return getLayer(LayerConstants.FEEDBACK_LAYER);
    }

    /**
     * Convenience method to return the host's Figure.
     * 
     * @return The host GraphicalEditPart's Figure
     */
    protected IFigure getHostFigure() {
        return ((GraphicalEditPart) getHost()).getFigure();
    }

    /**
     * Obtains the specified layer.
     * 
     * @param layer
     *            the key identifying the layer
     * @return the requested layer
     */
    protected IFigure getLayer(Object layer) {
        return LayerManager.Helper.find(getHost()).getLayer(layer);
    }

    /**
     * Removes the specified <code>Figure</code> from the {@link LayerConstants#FEEDBACK_LAYER}.
     * 
     * @param figure
     *            the feedback to remove
     */
    protected void removeFeedback(IFigure figure) {
        getFeedbackLayer().remove(figure);
    }

    private Range getCreationRange(CreateRequest request) {
        Point realLocation = getRealLocation(request);
        Range result = Range.emptyRange();
        if (request.getSize() != null) {
            result = new Range(realLocation.y, realLocation.y + request.getSize().height);
        }
        return result;
    }

    private Dimension getRealSize(ContainerCreationDescription ccdTool, CreateRequest request) {
        Dimension realSize = request.getSize();
        if (realSize == null) {
            realSize = new Dimension(0, 0);
            if (ccdTool instanceof InteractionUseCreationTool) {
                realSize.setHeight(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT);
            } else if (ccdTool instanceof CombinedFragmentCreationTool) {
                realSize.setHeight(LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT);
            }
        }
        return realSize;
    }

    private IDiagramCommandFactory getDiagramCommandFactory(EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor, List<EObject> coverage, Range creationRange) {
        SequenceDiagram seqDiag = null;
        EditPart host = getHost();
        if (host instanceof SequenceDiagramEditPart) {
            seqDiag = ((SequenceDiagramEditPart) host).getSequenceDiagram();
        }

        final DDiagramEditor diagramEditor = (DDiagramEditor) host.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor == null || seqDiag == null) {
            return null;
        }
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(seqDiag.getNotationDiagram());
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = cmdFactoryProvider.getCommandFactory(domain);
        return new SequenceDelegatingCommandFactory(diagramCommandFactory, domain, seqDiag, startingEndPredecessor, finishingEndPredecessor, coverage);
    }

    private IDiagramCommandFactory getDiagramCommandFactory(EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor, Point location) {
        EditPart host = getHost();
        SequenceDiagram seqDiag = EditPartsHelper.getSequenceDiagram(host);
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(seqDiag.getNotationDiagram());
        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor == null) {
            return null;
        }
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);

        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = cmdFactoryProvider.getCommandFactory(domain);
        return new SequenceDelegatingCommandFactory(diagramCommandFactory, domain, seqDiag, startingEndPredecessor, finishingEndPredecessor);
    }

    private IDiagramCommandFactory getDiagramCommandFactory(EObject predecessor, Point location) {
        EditPart host = getHost();
        SequenceDiagram seqDiag = EditPartsHelper.getSequenceDiagram(host);
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(seqDiag.getNotationDiagram());
        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor == null) {
            return null;
        }
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);

        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = cmdFactoryProvider.getCommandFactory(domain);
        return new SequenceDelegatingCommandFactory(diagramCommandFactory, domain, seqDiag, predecessor, location);
    }

    @Override
    protected Command getDistributeCommand(DistributeRequest request) {
        return UnexecutableCommand.INSTANCE;
    }
}
