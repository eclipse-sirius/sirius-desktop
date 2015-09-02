/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.EndOfLifeMoveOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.ShiftDirectSubExecutionsOperation;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ShiftDescendantMessagesOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.CreateMessageCreationValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewLocationHint;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * {@link SiriusGraphicalNodeEditPolicy} specific to sequence to manage creation
 * of message targeting InstanceRole (create & destroy messages).
 * 
 * @author edugueperoux
 */
public class InstanceRoleSiriusGraphicalNodeEditPolicy extends SiriusGraphicalNodeEditPolicy {

    /**
     * overriden to handle Ymove of InstanceRoles when connecting a
     * "create message".
     * 
     * @param request
     *            the request to create a new connection targeting an
     *            {@link org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart}
     * @return a command that wrap the Y move
     */
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        Command result = UnexecutableCommand.INSTANCE;

        TransactionalEditingDomain domain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        InstanceRoleEditPart host = (InstanceRoleEditPart) getHost();
        ISequenceElement sequenceElement = host.getInstanceRole();
        SequenceDiagram sequenceDiagram = sequenceElement.getDiagram();

        RequestQuery requestQuery = new RequestQuery(request);

        if (requestQuery.isCreateMessageCreation()) {
            CreateMessageCreationValidator validator = new CreateMessageCreationValidator();

            EditPart sourceEditPart = request.getSourceEditPart();
            EditPart targetEditPart = request.getTargetEditPart();

            Option<Lifeline> lifelineSource = ISequenceElementAccessor.getISequenceElement((View) sourceEditPart.getModel()).get().getLifeline();
            Option<Lifeline> lifelineTarget = ISequenceElementAccessor.getISequenceElement((View) targetEditPart.getModel()).get().getLifeline();

            InstanceRole instanceRoleSource = lifelineSource.get().getInstanceRole();
            InstanceRole instanceRoleTarget = lifelineTarget.get().getInstanceRole();

            validator.setSource(instanceRoleSource);
            validator.setTarget(instanceRoleTarget);

            Point firstClickLocation = SequenceEditPartsOperations.getConnectionSourceLocation(request, host);
            Point secondClickLocation = SequenceEditPartsOperations.getConnectionTargetLocation(request, host);

            // Creation message will be horizontal.
            if (!ExecutionSemanticEditPolicy.isCombinedFragmentTitleRangeEdgeCreation(sequenceElement, sequenceDiagram, firstClickLocation, firstClickLocation)) {

                validator.setFirstClickLocation(firstClickLocation);
                validator.setSecondClickLocation(secondClickLocation);

                if (validator.isValid(request)) {
                    result = super.getConnectionCompleteCommand(request);
                }
            }
        } else {

            // OLD code
            // TODO EDU : refactor this following with validators
            result = super.getConnectionCompleteCommand(request);
            if (result != null && result.canExecute() && validateIsConnectingCreateMessage(request)) {
                CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(domain, Messages.InstanceRoleSiriusGraphicalNodeEditPolicy_createParticipantMessageAndMoveDownLifelineCompositeCommand);
                Point sourceLocation = ((Point) ViewLocationHint.getInstance().getData(org.eclipse.gef.RequestConstants.REQ_CONNECTION_START)).getCopy();
                final LifelineEditPart lep = EditPartsHelper.getAllLifelines((IGraphicalEditPart) getHost()).get(0);

                if (((AbstractGraphicalEditPart) getHost()).getTargetConnections().isEmpty() && validateIsNotCreateMessageToSelf(request) && validateNoEventBeforeCreate(sourceLocation, lep)
                        && validateNotCreatingMessageInDifferentOperands(request, sourceLocation)) {

                    // Create a ChangeBoundsRequest to move down the targeted
                    // InstanceRoleEditPart to the Y position of connection
                    // source
                    ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
                    Rectangle bounds = ((AbstractGraphicalEditPart) getHost()).getFigure().getBounds();
                    Point scrollSize = GraphicalHelper.getScrollSize((IGraphicalEditPart) getHost());
                    int yMoveNeeded = sourceLocation.y - bounds.y - bounds.height / 2 + scrollSize.y;

                    changeBoundsRequest.setMoveDelta(new Point(0, yMoveNeeded));
                    // add a specific extended data to enable vertical move of
                    // an
                    // InstanceRoleEditPart
                    changeBoundsRequest.setConstrainedMove(true);
                    changeBoundsRequest.setEditParts(getHost());
                    ctc.compose(new CommandProxy(getHost().getCommand(changeBoundsRequest)));
                    ctc.compose(new CommandProxy(result));

                    /*
                     * These additional commands adjust the positions of the
                     * executions and messages on the lifeline so that visually
                     * they do not move. They are dual to the commands we add
                     * when moving a normal execution, as in that case we want
                     * all the executions and messages it contains to move
                     * along.
                     */
                    final int deltaY = changeBoundsRequest.getMoveDelta().y;

                    // Avoid EndOfLife Move
                    Lifeline lifeline = (Lifeline) lep.getISequenceEvent();
                    ctc.compose(CommandFactory.createICommand(domain, new EndOfLifeMoveOperation(lifeline, -deltaY)));
                    ctc.compose(CommandFactory.createICommand(domain, new ShiftDirectSubExecutionsOperation(lep.getISequenceEvent(), -deltaY)));
                    ctc.compose(CommandFactory.createICommand(domain, new ShiftDescendantMessagesOperation(lep.getISequenceEvent(), deltaY, true, false, true)));
                    SequenceEditPartsOperations.addRefreshSemanticOrderingCommand(ctc, (IGraphicalEditPart) getHost());
                    SequenceEditPartsOperations.addRefreshGraphicalOrderingCommand(ctc, (IGraphicalEditPart) getHost());

                } else {
                    result = UnexecutableCommand.INSTANCE;
                }
                result = new ICommandProxy(ctc);
            }
        }
        return result;
    }

    /**
     * validate if the request will create a "create message".
     * 
     * @param request
     *            the request to create a new connection targeting an
     *            {@link org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart}
     * @return if the request will create a "create message"
     */
    private boolean validateIsConnectingCreateMessage(CreateConnectionRequest request) {
        // validate request type
        boolean result = org.eclipse.gef.RequestConstants.REQ_CONNECTION_END.equals(request.getType()) && request.getNewObject() instanceof MessageCreationTool;
        // validate request connection ends
        result = result && getHost().equals(request.getTargetEditPart()) && request.getSourceEditPart() instanceof DNode2EditPart;
        return result;
    }

    /**
     * validate the request will not create a "create message" to self.
     * 
     * @param request
     *            the request to create a "create message".
     * @return the validation that this request will not create a
     *         "create message" to self.
     */
    private boolean validateIsNotCreateMessageToSelf(CreateConnectionRequest request) {
        // validate request does not add a create message to self
        return EditPartsHelper.findParentLifeline((IGraphicalEditPart) request.getSourceEditPart()).getParent() != request.getTargetEditPart();
    }

    /**
     * Validates that a message is not created between two elements that are not
     * in the same operand.
     * 
     * @param request
     *            current create connection request
     * @param location
     *            location of target
     * @return the validation that a message is not created between two elements
     *         that are not in the same operand.
     */
    private boolean validateNotCreatingMessageInDifferentOperands(CreateConnectionRequest request, Point location) {
        boolean result = false;
        if (getHost() instanceof InstanceRoleEditPart && request.getSourceEditPart() instanceof ISequenceEventEditPart) {
            GraphicalHelper.screen2logical(location, (InstanceRoleEditPart) getHost());

            InstanceRoleEditPart targetEditPart = (InstanceRoleEditPart) getHost();
            Option<Operand> targetParentOperand;
            if (targetEditPart.getInstanceRole().getLifeline().some()) {
                targetParentOperand = targetEditPart.getInstanceRole().getLifeline().get().getParentOperand(location.y);
            } else {
                targetParentOperand = Options.newNone();
            }

            ISequenceEventEditPart sourceEditPart = (ISequenceEventEditPart) request.getSourceEditPart();
            Option<Operand> sourceParentOperand;
            if (sourceEditPart.getISequenceEvent() instanceof Lifeline) {
                sourceParentOperand = ((Lifeline) sourceEditPart.getISequenceEvent()).getParentOperand(location.y);
            } else {
                sourceParentOperand = sourceEditPart.getISequenceEvent().getParentOperand();
            }
            if (targetParentOperand.some()) {
                // if the target is in an operand, the source has to be in the
                // same operand.
                result = sourceParentOperand.some() && targetParentOperand.get().equals(sourceParentOperand.get());
            } else {
                // if the target is not in an operand, the source has to be in
                // no operand as well.
                result = !sourceParentOperand.some();
            }
        }
        return result;
    }

    /**
     * Validates that there is no message on the targeted
     * {@link org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart}
     * before sourceLocation Y position. There can not be any message before
     * creation.
     * 
     * @param sourceLocation
     *            location where the create message has its source
     * @param lep
     * @return if there is no message on the targeted
     *         {@link org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart}
     *         before sourceLocation
     */
    private boolean validateNoEventBeforeCreate(final Point sourceLocation, LifelineEditPart lep) {
        ISequenceEvent lifeline = lep.getISequenceEvent();
        int firstEventInTargetInstanceRole = lifeline.getVerticalRange().getUpperBound();

        // Filter the combined fragment that contain source location to be able
        // to add a create message
        Predicate<ISequenceEvent> notParentCombinedFragment = new Predicate<ISequenceEvent>() {

            @Override
            public boolean apply(ISequenceEvent input) {
                if (input instanceof CombinedFragment) {
                    CombinedFragment combinedFragment = (CombinedFragment) input;
                    return !combinedFragment.getVerticalRange().includes(sourceLocation.y);
                }
                return true;
            }
        };

        for (ISequenceEvent ise : Iterables.filter(lifeline.getSubEvents(), notParentCombinedFragment)) {
            firstEventInTargetInstanceRole = Math.min(firstEventInTargetInstanceRole, ise.getVerticalRange().getLowerBound());
        }

        return sourceLocation.y < firstEventInTargetInstanceRole;
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
        SequenceEditPartsOperations.appendFullRefresh((IGraphicalEditPart) getHost(), result);
        addStoreLayoutDataCommand(result, edgeLayoutData);
        SequenceEditPartsOperations.buildCreateEdgeCommand((IGraphicalEditPart) getHost(), result, request, source, target, edgeCreationDescription, cmdFactoryProvider);
        SequenceEditPartsOperations.appendFullRefresh((IGraphicalEditPart) getHost(), result);
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
