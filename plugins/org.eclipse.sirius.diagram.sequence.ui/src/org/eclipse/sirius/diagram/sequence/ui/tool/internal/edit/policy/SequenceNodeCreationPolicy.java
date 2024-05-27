/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.VerticalPositionFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.command.SequenceDelegatingCommandFactory;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.FrameCreationValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.CreationUtil;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.api.requests.DistributeRequest;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A node creation edit policy which invokes the ExecutionCreationTool correctly
 * (with all the proper variables).
 * 
 * @author pcdavid
 */
public class SequenceNodeCreationPolicy extends NodeCreationEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        Option<SequenceDiagramEditPart> sdep = shouldRetargetToDiagram(request);
        if (sdep.some()) {
            return sdep.get().getCommand(request);
        }
        return super.getCreateCommand(request);
    }

    private Option<SequenceDiagramEditPart> shouldRetargetToDiagram(CreateRequest request) {
        if (REQ_CREATE.equals(request.getType()) && !(getHost() instanceof StateEditPart)) {
            AbstractToolDescription tool = getTool(request);
            if (tool instanceof InteractionUseCreationTool || tool instanceof CombinedFragmentCreationTool) {
                /*
                 * If the user is trying to create an IU or CF by clicking on a
                 * lifeline or execution, redirect the request to the diagram,
                 * but note the original target so that the initial coverage can
                 * be computed.
                 */
                IGraphicalEditPart self = (IGraphicalEditPart) getHost();
                SequenceDiagramEditPart sdep = EditPartsHelper.getSequenceDiagramPart(self);
                if (sdep != null) {
                    Map<Object, Object> extData = request.getExtendedData();
                    extData.put(FrameCreationValidator.ORIGINAL_TARGET, getHost());
                    return Options.newSome(sdep);
                }
            }
        }
        return Options.newNone();
    }

    /**
     * Overridden to show feedback for Execution creation.
     * 
     * {@inheritDoc}
     */
    @Override
    public void showTargetFeedback(Request request) {
        if (request instanceof CreateRequest) {
            Option<SequenceDiagramEditPart> sdep = shouldRetargetToDiagram((CreateRequest) request);
            if (sdep.some() && sdep.get().getEditPolicy(EditPolicy.CONTAINER_ROLE) != null) {
                sdep.get().getEditPolicy(EditPolicy.CONTAINER_ROLE).showTargetFeedback(request);
            }
        }
        super.showTargetFeedback(request);
    }

    /**
     * Overridden to erase feedback for Execution creation.
     * 
     * {@inheritDoc}
     */
    @Override
    public void eraseTargetFeedback(Request request) {
        if (request instanceof CreateRequest) {
            Option<SequenceDiagramEditPart> sdep = shouldRetargetToDiagram((CreateRequest) request);
            if (sdep.some() && sdep.get().getEditPolicy(EditPolicy.CONTAINER_ROLE) != null) {
                sdep.get().getEditPolicy(EditPolicy.CONTAINER_ROLE).eraseTargetFeedback(request);
            }
        }
        super.eraseTargetFeedback(request);
    }

    /**
     * Overridden to invoke ExecutionCreationTool correctly.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected Command getCreateNodeOnNodeCommand(CreateRequest request, NodeCreationDescription tool, DNode viewnode, EditPart parentEditPartToUse) {
        if (tool instanceof ExecutionCreationTool || tool instanceof StateCreationTool || tool instanceof ObservationPointCreationTool) {
            SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(getHost());
            SequenceDDiagram diagram = sequenceDiagram.getSequenceDDiagram();

            Point location = request.getLocation().getCopy();
            GraphicalHelper.screen2logical(location, (IGraphicalEditPart) getHost());
            EventEnd startingEndPredecessor = SequenceGraphicalHelper.getEndBefore(diagram, location.y);
            EventEnd startingEndSuccessor = SequenceGraphicalHelper.getEndAfter(diagram, location.y);
            if (tool instanceof ExecutionCreationTool) {
                Point bottomRight = location.getCopy().getTranslated(0, 1);
                bottomRight = new Point(location.x + 2 * LayoutUtils.SCALE, computeBottomY(diagram, location, startingEndSuccessor));
                GraphicalHelper.logical2screen(bottomRight, (IGraphicalEditPart) getHost());
                request.setSize(new Dimension(LayoutConstants.DEFAULT_EXECUTION_WIDTH, LayoutConstants.DEFAULT_EXECUTION_HEIGHT));
            }
            CreationUtil creationUtil = new CreationUtil(getDiagramCommandFactory(startingEndPredecessor, startingEndPredecessor, location), getRealLocation(request, parentEditPartToUse),
                    request.getSize(), getHost());
            return creationUtil.getNodeCreationCommand(viewnode, tool);
        } else {
            return super.getCreateNodeOnNodeCommand(request, tool, viewnode, parentEditPartToUse);
        }
    }

    /**
     * Overridden to invoke OperandCreationTool correctly.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected Command getCreateContainerInContainerCommand(CreateRequest request, ContainerCreationDescription tool, DDiagramElementContainer viewNodeContainer) {
        final Command result;
        if (isCreatingOperandInCombinedFragment(tool, viewNodeContainer)) {
            Option<Operand> operand = getOperand(viewNodeContainer);
            if (operand.some()) {
                SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(getHost());
                SequenceDDiagram diagram = sequenceDiagram.getSequenceDDiagram();

                EventEnd startingEndPredecessor = SequenceGraphicalHelper.getEndBefore(diagram, operand.get().getVerticalRange().getUpperBound() - 1);

                Point location = request.getLocation().getCopy();
                GraphicalHelper.screen2logical(location, (IGraphicalEditPart) getHost());

                CreationUtil creationUtil = new CreationUtil(request, getDiagramCommandFactory(startingEndPredecessor, startingEndPredecessor, location), getRealLocation(request, getHost()),
                        getHost());
                result = creationUtil.getContainerCreationDescription((DDiagramElementContainer) viewNodeContainer.eContainer(), tool);
            } else {
                result = UnexecutableCommand.INSTANCE;
            }
        } else {
            result = super.getCreateContainerInContainerCommand(request, tool, viewNodeContainer);
        }
        return result;
    }

    private Option<Operand> getOperand(DDiagramElementContainer viewNodeContainer) {
        Collection<View> views = ISequenceElementAccessor.getViewsForSemanticElement((SequenceDDiagram) viewNodeContainer.getParentDiagram(), viewNodeContainer.getTarget());
        List<View> operandViews = Lists.newArrayList(Iterables.filter(views, Operand.notationPredicate()));
        for (View view : operandViews) {
            Option<Operand> optOperand = ISequenceElementAccessor.getOperand(view);
            if (optOperand.some()) {
                return optOperand;
            }
        }
        return Options.newNone();
    }

    private boolean isCreatingOperandInCombinedFragment(ContainerCreationDescription tool, DDiagramElementContainer viewNodeContainer) {
        return tool instanceof OperandCreationTool && Operand.viewpointElementPredicate().apply(viewNodeContainer)
                && CombinedFragment.viewpointElementPredicate().apply((DDiagramElement) viewNodeContainer.eContainer());
    }

    private int computeBottomY(SequenceDDiagram diagram, Point location, EventEnd startingEndSuccessor) {
        int nextY;
        int parentBottomMargin = LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        if (startingEndSuccessor != null) {
            nextY = new VerticalPositionFunction(diagram).apply(startingEndSuccessor);
        } else {
            nextY = location.y + LayoutConstants.DEFAULT_EXECUTION_HEIGHT + parentBottomMargin;
        }
        int bottomY = Math.max(location.y + LayoutConstants.DEFAULT_EXECUTION_HEIGHT, nextY - parentBottomMargin);
        return bottomY;
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
        return new SequenceDelegatingCommandFactory(diagramCommandFactory, domain, seqDiag, startingEndPredecessor, finishingEndPredecessor, location);
    }

    @Override
    protected Command getDistributeCommand(DistributeRequest request) {
        return UnexecutableCommand.INSTANCE;
    }
}
