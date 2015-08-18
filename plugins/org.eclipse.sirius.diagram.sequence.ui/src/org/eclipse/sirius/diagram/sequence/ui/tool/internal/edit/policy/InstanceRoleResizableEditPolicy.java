/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.IdentityCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ResizeViewOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.InstanceRoleMoveValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.InstanceRoleResizeValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.business.internal.operation.MoveViewOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirResizableEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.command.DoNothingCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * A specific AirResizableEditPolicy to manage instance roles move & resize
 * requests. Constraints :
 * <ul>
 * <li>LayoutConstants#LIFELINES_START_X left margin must be always respected</li>
 * <li>LayoutConstants#LIFELINES_START_Y top margin must be always respected</li>
 * <li>LayoutConstants#LIFELINES_MIN_X_GAP minimum gap between neighbors
 * InstanceRoles must be always respected</li>
 * <li>Resize must not be possible to the top if
 * LayoutConstants#LIFELINES_START_Y is not respected</li>
 * <li>Resize must not be possible to the left if
 * LayoutConstants#LIFELINES_START_X is not respected</li>
 * <li>Drop a InstanceRole in another index of its initial index in the left to
 * right ordered list of InstanceRole must not shift InstanceRole at it left but
 * only shift InstanceRole at it right</li>
 * </ul>
 * 
 * @author smonnier, edugueperoux
 */
public class InstanceRoleResizableEditPolicy extends AirResizableEditPolicy {

    private static final String INSTANCE_ROLE_MOVE_COMMAND_NAME = "InstanceRole move";

    private static final String INSTANCE_ROLE_RESIZE_COMMAND_NAME = "InstanceRole resize";

    /**
     * Manage move requests.
     * 
     * @param request
     *            to move InstanceRoleEditPart's figure
     * 
     * @return {@link Command} to move InstanceRoleEditPart's figure
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        cancelVerticalMoveDelta(request);
        Command result = DoNothingCommand.INSTANCE;
        RequestQuery requestQuery = new RequestQuery(request);
        if (getHost().getSelected() == EditPart.SELECTED_PRIMARY && requestQuery.isMove()) {
            InstanceRoleMoveValidator validator = new InstanceRoleMoveValidator();
            List<InstanceRole> instanceRoles = requestQuery.getInstanceRoles();
            validator.setSequenceElements(instanceRoles);

            result = UnexecutableCommand.INSTANCE;
            if (validator.isValid(request)) {
                result = getMoveCommand(validator, requestQuery);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        cancelVerticalMoveDelta(request);
        super.showChangeBoundsFeedback(request);
    }

    private Command getMoveCommand(InstanceRoleMoveValidator validator, RequestQuery requestQuery) {
        GraphicalEditPart host = (GraphicalEditPart) getHost();
        TransactionalEditingDomain transactionalEditingDomain = host.getEditingDomain();
        CompositeTransactionalCommand compositeCommand = new CompositeTransactionalCommand(transactionalEditingDomain, INSTANCE_ROLE_MOVE_COMMAND_NAME);
        ICommand moveCmds = getMoveViewCommands(transactionalEditingDomain, validator.getMoveDeltas());
        if (moveCmds != null && moveCmds.canExecute()) {
            compositeCommand.compose(moveCmds);
        }
        postProcessDefaultCommand((InstanceRoleEditPart) getHost(), compositeCommand, requestQuery);

        ICommand solution = compositeCommand;
        if (!solution.canExecute()) {
            solution = IdentityCommand.INSTANCE;
        }
        return new ICommandProxy(solution);
    }

    /**
     * Manage resize requests.
     * 
     * @param request
     *            to resize InstanceRoleEditPart's figure
     * 
     * @return {@link Command} to resize InstanceRoleEditPart's figure
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {

        Command result = DoNothingCommand.INSTANCE;
        RequestQuery requestQuery = new RequestQuery(request);
        if (getHost().getSelected() == EditPart.SELECTED_PRIMARY && requestQuery.isResize()) {
            InstanceRoleResizeValidator validator = new InstanceRoleResizeValidator();
            List<InstanceRole> instanceRoles = requestQuery.getInstanceRoles();
            validator.setSequenceElements(instanceRoles);

            result = UnexecutableCommand.INSTANCE;
            if (validator.isValid(request)) {
                result = getResizeCommand(validator, new RequestQuery(request));
            }
        }

        return result;
    }

    private Command getResizeCommand(InstanceRoleResizeValidator validator, RequestQuery requestQuery) {
        GraphicalEditPart host = (GraphicalEditPart) getHost();
        TransactionalEditingDomain transactionalEditingDomain = host.getEditingDomain();

        CompositeTransactionalCommand compositeCommand = new CompositeTransactionalCommand(transactionalEditingDomain, INSTANCE_ROLE_RESIZE_COMMAND_NAME);
        ICommand moveViewCmd = getMoveViewCommands(transactionalEditingDomain, validator.getMoveDeltas());
        if (moveViewCmd != null && moveViewCmd.canExecute()) {
            compositeCommand.compose(moveViewCmd);
        }
        ICommand resizeViewCmd = getResizeViewCommands(transactionalEditingDomain, validator.getSizeDeltas());
        if (resizeViewCmd != null && resizeViewCmd.canExecute()) {
            compositeCommand.compose(resizeViewCmd);
        }

        postProcessDefaultCommand((InstanceRoleEditPart) getHost(), compositeCommand, requestQuery);

        ICommand solution = compositeCommand;
        if (!solution.canExecute()) {
            solution = IdentityCommand.INSTANCE;
        }
        return new ICommandProxy(solution);
    }

    /**
     * Get {@link MoveViewOperation} composition from moveDeltas map.
     * 
     * @param transactionalEditingDomain
     *            TransactionalEditingDomain used to construct the result
     *            ICommand
     * @param moveDeltas
     * 
     * @return composition of {@link MoveViewOperation} or null if move/resize
     *         is not needed
     */
    private ICommand getMoveViewCommands(TransactionalEditingDomain transactionalEditingDomain, Map<InstanceRole, Point> moveDeltas) {
        CompositeTransactionalCommand compositeCommand = new CompositeTransactionalCommand(transactionalEditingDomain, ""); //$NON-NLS-1$

        // handle move lost nodes
        Multimap<InstanceRole, LostMessageEnd> lostNodes = HashMultimap.create();

        InstanceRoleEditPart irep = (InstanceRoleEditPart) getHost();
        SequenceDiagram diagram = irep.getInstanceRole().getDiagram();
        for (Message msg : diagram.getAllMessages()) {
            ISequenceNode sourceElement = msg.getSourceElement();
            Option<Lifeline> sourceLifeline = sourceElement.getLifeline();
            ISequenceNode targetElement = msg.getTargetElement();
            Option<Lifeline> targetLifeline = targetElement.getLifeline();
            if (sourceLifeline.some() && targetElement instanceof LostMessageEnd) {
                lostNodes.put(sourceLifeline.get().getInstanceRole(), (LostMessageEnd) targetElement);
            } else if (sourceElement instanceof LostMessageEnd && targetLifeline.some()) {
                lostNodes.put(targetLifeline.get().getInstanceRole(), (LostMessageEnd) sourceElement);
            }
        }

        // Move commands to shift siblings InstanceRoles
        for (Entry<InstanceRole, Point> entry : moveDeltas.entrySet()) {
            Point delta = entry.getValue();
            if (delta.x != 0 || delta.y != 0) {
                InstanceRole instanceRole = entry.getKey();
                addMoveViewCommand(transactionalEditingDomain, compositeCommand, delta, instanceRole);

                // move lost message ends
                for (LostMessageEnd lme : lostNodes.get(instanceRole)) {
                    addMoveViewCommand(transactionalEditingDomain, compositeCommand, delta, lme);
                }

            }
        }
        return compositeCommand;
    }

    private void addMoveViewCommand(TransactionalEditingDomain transactionalEditingDomain, CompositeTransactionalCommand compositeCommand, Point delta, ISequenceNode sequenceNode) {
        IAdaptable adapter = new EObjectAdapter(sequenceNode.getNotationNode());
        AbstractModelChangeOperation<Void> moveViewOperation = new MoveViewOperation(DiagramUIMessages.SetLocationCommand_Label_Resize, adapter, delta);
        IUndoableOperation moveCmd = CommandFactory.createICommand(transactionalEditingDomain, moveViewOperation);
        compositeCommand.add(moveCmd);
    }

    /**
     * Get {@link ResizeViewOperation} composition from sizeDeltas map.
     * 
     * @param transactionalEditingDomain
     *            TransactionalEditingDomain used to construct the result
     *            ICommand
     * 
     * @return composition of {@link ResizeViewOperation}
     */
    private ICommand getResizeViewCommands(TransactionalEditingDomain transactionalEditingDomain, Map<InstanceRole, Dimension> sizeDeltas) {
        CompositeTransactionalCommand compositeCommand = new CompositeTransactionalCommand(transactionalEditingDomain, ""); //$NON-NLS-1$
        // Resize Commands
        for (Entry<InstanceRole, Dimension> entry : sizeDeltas.entrySet()) {
            Dimension sizeDelta = entry.getValue();

            if (sizeDelta.width != 0 || sizeDelta.height != 0) {
                InstanceRole instanceRole = entry.getKey();
                IAdaptable adapter = new EObjectAdapter(instanceRole.getNotationView());
                AbstractModelChangeOperation<Void> resizeViewOperation = new ResizeViewOperation(DiagramUIMessages.SetLocationCommand_Label_Resize, adapter, sizeDelta);
                IUndoableOperation resizeCmd = CommandFactory.createICommand(transactionalEditingDomain, resizeViewOperation);
                compositeCommand.add(resizeCmd);
            }
        }
        if (!compositeCommand.canExecute()) {
            return null;
        }
        return compositeCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAutoSizeCommand(Request request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Cancel vertical changes of the given request.
     * 
     * @param request
     *            a request.
     */
    protected void cancelVerticalMoveDelta(ChangeBoundsRequest request) {
        if (request == null) {
            return;
        }

        RequestQuery query = new RequestQuery(request);
        if (query.isMove()) {
            Point moveDelta = request.getMoveDelta().getCopy();
            if (moveDelta != null) {
                request.setMoveDelta(new Point(moveDelta.x, 0));
            }
        }
    }

    private void postProcessDefaultCommand(InstanceRoleEditPart self, CompositeTransactionalCommand command, RequestQuery requestQuery) {
        if (command != null && command.canExecute()) {
            SequenceEditPartsOperations.addRefreshGraphicalOrderingCommand(command, self);
            SequenceEditPartsOperations.addRefreshSemanticOrderingCommand(command, self);
            if (requestQuery.isMultiSelectionOperation()) {
                SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(command, self.getInstanceRole(), requestQuery.getInstanceRoles());
            } else {
                SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(command, self.getInstanceRole());
            }
        }
    }
}
