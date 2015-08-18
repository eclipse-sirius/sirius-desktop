/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.RangeGuide;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNode2ItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Iterables;

/**
 * A specialized semantic edit policy for sequence diagram elements.
 * <ul>
 * <li>Overrides the REQ_CONNECTION_END to invoke the appropriate message
 * creation tool with the additional variables required for its insertion at the
 * right place in the semantic model.</li>
 * <li>Shows horizontal feedback lines when an execution is moved/resized.</li>
 * </ul>
 * 
 * @author pcdavid
 */
public class ExecutionSemanticEditPolicy extends DNode2ItemSemanticEditPolicy {

    private RangeGuide forbiddenRangeArea;

    /**
     * Overridden to handle the REQ_CONNECTION_END specially as the location at
     * which a message is created has a semantic meaning.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(final Request request) {
        Command superCommand = super.getCommand(request);
        Command command = customizeEndOfLifeCreationCommand(request, superCommand);
        return command;
    }

    private Command customizeEndOfLifeCreationCommand(final Request request, Command superCommand) {
        Command command;
        command = superCommand;

        if (request instanceof CreateViewRequest) {
            CreateViewRequest cvr = (CreateViewRequest) request;
            EndOfLifeMapping endOfLifeMapping = getEndOfLifeMapping(cvr);
            if (cvr.getLocation().x == -1 && cvr.getLocation().y == -1 && endOfLifeMapping != null) {
                // adding an EndOfLifeEditPart will resize the RootExecution
                // to
                // keep the same global size on the lifeline
                String label = superCommand != null ? superCommand.getLabel() : ""; //$NON-NLS-1$
                CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(getEditingDomain(), label);

                if (superCommand != null) {
                    ctc.compose(new CommandProxy(superCommand));
                }

                ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_RESIZE);
                cbr.setResizeDirection(PositionConstants.SOUTH);
                cbr.getSizeDelta().height = -calculateHalfSizeOfEndOfLifeEditPartToCreate(endOfLifeMapping);
                ctc.compose(new CommandProxy(getHost().getCommand(cbr)));

                ICommandProxy iCommandProxy = new ICommandProxy(ctc);
                iCommandProxy.setLabel(ctc.getLabel());
                command = iCommandProxy;
            }
        }
        return command;
    }

    /**
     * Calculate the half size of the EndOfLifeEditPart that will be created.
     * 
     * @param request
     *            the request to create a new connection targeting an
     *            {@link org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart}
     * @return half the size of the EndOfLifeEditPart that will be created
     */
    private int calculateHalfSizeOfEndOfLifeEditPartToCreate(EndOfLifeMapping endOfLifeMapping) {
        NodeStyleDescription style = endOfLifeMapping.getStyle();
        int halfsize = 0;
        if (style instanceof SquareDescription) {
            SquareDescription sd = (SquareDescription) style;
            halfsize = (sd.getHeight() * LayoutUtils.SCALE) / 2;
            if (halfsize < LayoutUtils.SCALE) {
                // minimum size displayed
                halfsize = LayoutUtils.SCALE;
            }
        } else if (style instanceof WorkspaceImageDescription) {
            WorkspaceImageDescription wid = (WorkspaceImageDescription) style;
            String sizeComputationExpression = wid.getSizeComputationExpression();
            Integer sizeComputationInteger = Integer.valueOf(sizeComputationExpression);
            if (sizeComputationInteger != null) {
                halfsize = (sizeComputationInteger * LayoutUtils.SCALE) / 2;
            }
        }
        return halfsize;
    }

    /**
     * Overridden to have feedback on forbidden combined fragment header range.
     * 
     * {@inheritDoc}
     */
    @Override
    public void showTargetFeedback(Request request) {
        removeForbiddenRangeFeedback();

        if (request instanceof CreateRequest && ((CreateRequest) request).getLocation() != null) {
            ISequenceEventEditPart host = (ISequenceEventEditPart) getHost();
            ISequenceEvent sequenceEvent = host.getISequenceEvent();
            SequenceDiagram sequenceDiagram = sequenceEvent.getDiagram();

            CreateRequest createRequest = (CreateRequest) request;
            Point location = createRequest.getLocation().getCopy();
            GraphicalHelper.screen2logical(location, (IGraphicalEditPart) getHost());
            EventEnd startingEndPredecessor = SequenceGraphicalHelper.getEndBefore(sequenceDiagram.getSequenceDDiagram(), location.y);
            if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(sequenceDiagram, startingEndPredecessor) && startingEndPredecessor instanceof SingleEventEnd) {
                IFigure layer = getFeedbackLayer();
                // show forbidden range
                SingleEventEnd singleEventEnd = (SingleEventEnd) startingEndPredecessor;
                ISequenceEvent ise = EventEndHelper.findISequenceEvent(singleEventEnd, sequenceDiagram);
                if (layer != null && ise instanceof CombinedFragment) {
                    Range verticalRange = ise.getVerticalRange();
                    Rectangle screenRange = new Rectangle(0, verticalRange.getLowerBound(), 0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT);
                    screenRange.performScale(GraphicalHelper.getZoom(getHost()));
                    Range forbiddenRange = RangeHelper.verticalRange(screenRange);

                    forbiddenRangeArea = new RangeGuide(ColorConstants.red, forbiddenRange, true);
                    Rectangle bounds = layer.getBounds().getCopy();
                    bounds.height = forbiddenRange.width();
                    bounds.y = forbiddenRange.getLowerBound();
                    forbiddenRangeArea.setBounds(bounds);
                    layer.add(forbiddenRangeArea);
                }
            } else {
                super.showTargetFeedback(request);
            }
        } else {
            super.showTargetFeedback(request);
        }
    }

    /**
     * Overridden to erase feedback of forbidden combined fragment header range.
     * 
     * {@inheritDoc}
     */
    @Override
    public void eraseTargetFeedback(Request request) {
        removeForbiddenRangeFeedback();

        super.eraseTargetFeedback(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditPart getTargetEditPart(Request request) {
        EditPart result = super.getTargetEditPart(request);
        RequestQuery requestQuery = new RequestQuery(request);
        if (request instanceof CreateConnectionRequest && requestQuery.isStandardMessageCreation() && getHost() instanceof ISequenceEventEditPart
                && org.eclipse.gef.RequestConstants.REQ_CONNECTION_END.equals(request.getType())) {
            CreateConnectionRequest createRequest = (CreateConnectionRequest) request;
            ISequenceEventEditPart ise = (ISequenceEventEditPart) getHost();

            if (ise instanceof ExecutionEditPart) {
                ise = new EditPartQuery(ise).getFirstAncestorOfType(LifelineEditPart.class);
            } else if (!(ise instanceof LifelineEditPart)) {
                ise = null;
            }

            EditPart source = createRequest.getSourceEditPart();
            if (source instanceof ExecutionEditPart) {
                source = new EditPartQuery((IGraphicalEditPart) source).getFirstAncestorOfType(LifelineEditPart.class);
            } else if (!(source instanceof LifelineEditPart)) {
                source = null;
            }

            // if ise lifeline equals source lifeline : reflexive message
            if (ise != null && !ise.equals(source)) {
                Point firstClickLocation = SequenceEditPartsOperations.getConnectionSourceLocation(createRequest, ise);
                if (firstClickLocation != null) {
                    GraphicalHelper.screen2logical(firstClickLocation, ise);

                    ISequenceEvent sequenceEvent = ise.getISequenceEvent();
                    Range targetRange = sequenceEvent.getVerticalRange();
                    for (ExecutionEditPart potentialTarget : EditPartsHelper.getAllExecutions(ise)) {
                        Range range = potentialTarget.getISequenceEvent().getVerticalRange();
                        if (targetRange.includes(range) && range.includes(firstClickLocation.y)) {
                            targetRange = range;
                            result = potentialTarget;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Investigate the request to return the EndOfLifeMapping if existent
     * 
     * @param createViewRequest
     *            the current request
     * @return the EndOfLifeMapping if existent
     */
    private EndOfLifeMapping getEndOfLifeMapping(CreateViewRequest createViewRequest) {
        Iterable<ViewDescriptor> filter = Iterables.filter(createViewRequest.getViewDescriptors(), ViewDescriptor.class);
        for (ViewDescriptor viewDescriptor : filter) {
            IAdaptable elementAdapter = viewDescriptor.getElementAdapter();
            if (elementAdapter instanceof EObjectAdapter) {
                EObjectAdapter eobjectAdapter = (EObjectAdapter) elementAdapter;
                Object realObject = eobjectAdapter.getRealObject();
                if (realObject instanceof DNode) {
                    DNode dns = (DNode) realObject;
                    NodeMapping actualMapping = dns.getActualMapping();
                    if (actualMapping instanceof EndOfLifeMapping) {
                        return (EndOfLifeMapping) actualMapping;
                    }
                }
            }
        }
        return null;
    }

    /**
     * .
     * 
     * @param sequenceElement
     *            .
     * @param sequenceDiagram
     *            .
     * @param firstClickLocation
     *            .
     * @param secondClickLocation
     *            .
     * @return .
     */
    public static boolean isCombinedFragmentTitleRangeEdgeCreation(ISequenceElement sequenceElement, SequenceDiagram sequenceDiagram, Point firstClickLocation, Point secondClickLocation) {
        boolean result = false;
        EventEnd startingEndPredecessor = SequenceGraphicalHelper.getEndBefore(sequenceDiagram.getSequenceDDiagram(), firstClickLocation.y);
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(sequenceDiagram, startingEndPredecessor)) {
            result = true;
        }

        EventEnd finishingEndPredecessor = SequenceGraphicalHelper.getEndBefore(sequenceDiagram.getSequenceDDiagram(), secondClickLocation.y);
        if (ToolCommandBuilder.isStartingEventEndOfCombinedFragment(sequenceDiagram, finishingEndPredecessor)) {
            result = true;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        CompoundCommand cc = new CompoundCommand();
        addDestroyChildNodesCommand(cc);
        addDestroyShortcutsCommand(cc);
        cc.add(getGEFWrapper(new DestroyElementCommand(req)));
        return cc.unwrap();
    }

    private void removeForbiddenRangeFeedback() {
        IFigure layer = getFeedbackLayer();
        if (forbiddenRangeArea != null && layer.getChildren().contains(forbiddenRangeArea)) {
            layer.remove(forbiddenRangeArea);
        }
    }

    private IFigure getFeedbackLayer() {
        IFigure layer = LayerManager.Helper.find(getHost()).getLayer(LayerConstants.FEEDBACK_LAYER);
        return layer;
    }
}
