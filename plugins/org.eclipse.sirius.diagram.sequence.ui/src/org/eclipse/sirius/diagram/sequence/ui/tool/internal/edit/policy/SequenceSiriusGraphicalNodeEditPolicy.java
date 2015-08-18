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

import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.FeedbackHelper;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.AbstractMessageCreationValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.CreateMessageCreationValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.DefaultMessageCreationValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.DestroyMessageCreationValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceMessagesRouter;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * This edit policy is overridden to use our own connection router. This way
 * during the creation of a message to self, the feedback will just be an edge
 * between the first click and the pointer, instead of a polyline connected to
 * the bottom of the source edit part (lifeline).
 * 
 * @author smonnier
 * 
 */
public class SequenceSiriusGraphicalNodeEditPolicy extends SiriusGraphicalNodeEditPolicy {

    /**
     * Constant used to store the location in draw2d absolute coordinates of the
     * click on the {@link EdgeTarget} source.
     */
    protected static final String DRAW2D_EDGE_LOCATION_SOURCE = "edge.absolute.location.source"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    protected FeedbackHelper getFeedbackHelper(CreateConnectionRequest request) {
        if (feedbackHelper == null) {
            feedbackHelper = new FeedbackHelper();
            Point p = request.getLocation();
            connectionFeedback = createDummyConnection(request);
            connectionFeedback.setConnectionRouter(new SequenceMessagesRouter());
            connectionFeedback.setSourceAnchor(getSourceConnectionAnchor(request));
            feedbackHelper.setConnection(connectionFeedback);
            addFeedback(connectionFeedback);
            feedbackHelper.update(null, p);
        }
        return feedbackHelper;
    }

    /**
     * Overridden to use LayerConstants.SCALED_FEEDBACK_LAYER instead of
     * LayerConstants.FEEDBACK_LAYER, to have connection creation feedback
     * scalable with zoom & scroll. If LayerConstants.SCALED_FEEDBACK_LAYER
     * layer not available, return by default a LayerConstants.FEEDBACK_LAYER
     * layer.
     * 
     * {@inheritDoc}
     */
    @Override
    protected IFigure getFeedbackLayer() {
        IFigure feedbackLayer = null;
        IFigure scaledLayers = getLayer(LayerConstants.SCALABLE_LAYERS);
        if (scaledLayers instanceof LayeredPane) {
            Layer layer = ((LayeredPane) scaledLayers).getLayer(LayerConstants.SCALED_FEEDBACK_LAYER);
            if (layer != null) {
                feedbackLayer = layer;
            }
        }
        if (feedbackLayer == null) {
            feedbackLayer = super.getFeedbackLayer();
        }
        return feedbackLayer;
    }

    /**
     * Overridden to save the position on the source.
     * 
     * @param request
     *            the request to create a new connection targeting an
     *            {@link org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart}
     * @return super.getConnectionCreateCommand() using CreateConnectionRequest
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        Command connectionCreateCommand = super.getConnectionCreateCommand(request);
        if (new RequestQuery(request).isSequenceMessageCreation() && request.getLocation() != null && org.eclipse.gef.RequestConstants.REQ_CONNECTION_START.equals(request.getType())) {
            ISequenceEventEditPart host = (ISequenceEventEditPart) getHost();
            ISequenceEvent sequenceEvent = host.getISequenceEvent();
            SequenceDiagram sequenceDiagram = sequenceEvent.getDiagram();

            Point location = request.getLocation().getCopy();
            GraphicalHelper.screen2logical(location, (IGraphicalEditPart) getHost());
            request.getExtendedData().put(DRAW2D_EDGE_LOCATION_SOURCE, location.getCopy());
            EventEnd startingEndPredecessor = SequenceGraphicalHelper.getEndBefore(sequenceDiagram.getSequenceDDiagram(), location.y);
            if (!sequenceEvent.canChildOccupy(null, new Range(location.y, location.y)) || ToolCommandBuilder.isStartingEventEndOfCombinedFragment(sequenceDiagram, startingEndPredecessor)) {
                connectionCreateCommand = UnexecutableCommand.INSTANCE;
            }
        }
        return connectionCreateCommand;
    }

    /**
     * Overridden to manage create message creation.
     * 
     * {@inheritDoc}
     */
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        Command result = UnexecutableCommand.INSTANCE;
        if (request instanceof CreateConnectionViewRequest) {
            result = super.getConnectionCompleteCommand(request);
        } else {
            RequestQuery requestQuery = new RequestQuery(request);
            if (!requestQuery.isSequenceMessageCreation()) {
                result = super.getConnectionCompleteCommand(request);
            } else {
                ISequenceEventEditPart host = (ISequenceEventEditPart) getHost();
                ISequenceEvent sequenceEvent = host.getISequenceEvent();
                SequenceDiagram sequenceDiagram = sequenceEvent.getDiagram();

                EditPart sourceEditPart = request.getSourceEditPart();
                EditPart targetEditPart = request.getTargetEditPart();

                Option<ISequenceElement> sequenceEventSource = ISequenceElementAccessor.getISequenceElement((View) sourceEditPart.getModel());
                Option<ISequenceElement> sequenceEventTarget = ISequenceElementAccessor.getISequenceElement((View) targetEditPart.getModel());

                Map<?, ?> extendedData = request.getExtendedData();
                Point firstClickLocation = (Point) extendedData.get(DRAW2D_EDGE_LOCATION_SOURCE);
                Point secondClickLocation = request.getLocation().getCopy();
                GraphicalHelper.screen2logical(secondClickLocation, (IGraphicalEditPart) getHost());

                if (firstClickLocation != null && secondClickLocation != null
                        && !ExecutionSemanticEditPolicy.isCombinedFragmentTitleRangeEdgeCreation(sequenceEvent, sequenceDiagram, firstClickLocation, secondClickLocation)) {
                    AbstractMessageCreationValidator validator = null;

                    if (requestQuery.isCreateMessageCreation()) {
                        validator = new CreateMessageCreationValidator();
                    } else if (requestQuery.isDestroyMessageCreation()) {
                        validator = new DestroyMessageCreationValidator();
                    } else {
                        validator = new DefaultMessageCreationValidator();
                    }

                    validator.setSource(sequenceEventSource.get());
                    validator.setTarget(sequenceEventTarget.get());

                    validator.setFirstClickLocation(firstClickLocation);
                    validator.setSecondClickLocation(secondClickLocation);

                    if ((request.getSourceEditPart() instanceof NoteEditPart) || validator.isValid(request)) {
                        result = super.getConnectionCompleteCommand(request);
                    }
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy#buildCreateEdgeCommand(org.eclipse.gef.requests.CreateConnectionRequest,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.viewpoint.description.tool.EdgeCreationDescription,
     *      org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider,
     *      org.eclipse.sirius.diagram.business.internal.view.EdgeLayoutData)
     */
    @Override
    protected Command buildCreateEdgeCommand(CreateConnectionRequest request, EdgeTarget source, EdgeTarget target, EdgeCreationDescription edgeCreationDescription,
            IDiagramCommandFactoryProvider cmdFactoryProvider, EdgeLayoutData edgeLayoutData) {
        CompoundCommand result = new CompoundCommand();
        IGraphicalEditPart host = (IGraphicalEditPart) getHost();
        SequenceEditPartsOperations.appendFullRefresh(host, result);
        addStoreLayoutDataCommand(result, edgeLayoutData);
        SequenceEditPartsOperations.buildCreateEdgeCommand(host, result, request, source, target, edgeCreationDescription, cmdFactoryProvider);
        SequenceEditPartsOperations.appendFullRefresh(host, result);
        return result;
    }

    /**
     * Overridden to forbidden explicit source reconnection of Message.
     * 
     * {@inheritDoc}
     */
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        Command result = null;
        if (request.getConnectionEditPart() instanceof SequenceMessageEditPart) {
            result = UnexecutableCommand.INSTANCE;
        } else {
            result = super.getReconnectSourceCommand(request);
        }
        return result;
    }

    /**
     * Overridden to forbidden explicit target reconnection of Message.
     * 
     * {@inheritDoc}
     */
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        Command result = null;
        if (request.getConnectionEditPart() instanceof SequenceMessageEditPart) {
            result = UnexecutableCommand.INSTANCE;
        } else {
            result = super.getReconnectTargetCommand(request);
        }
        return result;
    }

    /**
     * The snap to grid should be disabled in sequence diagram, but to avoid
     * confusion the specific method for edge behavior with snap to grid is
     * disabled.
     * 
     * {@inheritDoc}
     */
    @Override
    protected EdgeLayoutData getEdgeLayoutDataWithSnapToGrid(CreateConnectionRequest request, INodeEditPart sourceEditPart, INodeEditPart targetEditPart, Point sourceLocation, Point targetLocation) {
        return super.getEdgeLayoutData(request, sourceEditPart, targetEditPart, sourceLocation, targetLocation);
    }
}
