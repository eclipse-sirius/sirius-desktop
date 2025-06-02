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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation;

import java.text.MessageFormat;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.ReparentExecutionOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetMessageRangeOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetVerticalRangeOperation;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.GateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LostMessageEndEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceNodeCreationPolicy;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceSiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;

import com.google.common.collect.Iterables;

/**
 * Helper class to factor common code between root executions (lifelines) and normal executions which can not be shared
 * by inheritance.
 * 
 * @author pcdavid, smonnier
 */
public final class ExecutionOperations {
    private ExecutionOperations() {
        // Prevent instantiations.
    }

    /**
     * Install/replace a NodeCreationEditPolicy on the CONTAINER_ROLE which is aware of the ExecutionCreationTool and
     * calls it instead of the generic NodeCreationDescription.
     * 
     * @param self
     *            the edit part.
     */
    public static void installExecutionAwareNodeCreationPolicy(ISequenceEventEditPart self) {
        ExecutionOperations.replaceEditPolicy(self, EditPolicy.CONTAINER_ROLE, new SequenceNodeCreationPolicy(), NodeCreationEditPolicy.class);
        ExecutionOperations.replaceEditPolicy(self, EditPolicy.GRAPHICAL_NODE_ROLE, new SequenceSiriusGraphicalNodeEditPolicy(), SiriusGraphicalNodeEditPolicy.class);
    }

    /**
     * .
     * 
     * @param self
     *            .
     * @param role
     *            .
     * @param editPolicy
     *            .
     * @param policyType
     *            .
     */
    public static void replaceEditPolicy(IGraphicalEditPart self, String role, EditPolicy editPolicy, Class<?> policyType) {
        if (self.getEditPolicy(role) instanceof CompoundEditPolicy) {
            /*
             * See AbstractDiagramNodeEditPartOperation.createDefaultEditPolicies (IAbstractDiagramNodeEditPart self)
             * for the expected structure of the policy on CONTAINER_ROLE.
             */
            CompoundEditPolicy cep = (CompoundEditPolicy) self.getEditPolicy(role);
            for (int i = 0; i < cep.getEditPolicies().size(); i++) {
                if (policyType.isInstance(cep.getEditPolicies().get(i))) {
                    cep.getEditPolicies().set(i, editPolicy);
                    break;
                }
            }
            self.installEditPolicy(role, cep);
        } else {
            self.installEditPolicy(role, editPolicy);
        }

    }

    /**
     * If possible, make the whole figure's area a valid location for a SlidableAnchor. Executions are usually very
     * narrow vertically, and the default setting (0.5) makes the zone usable to anchor a message too small to be
     * usable.
     * 
     * @param figure
     *            the figure to adjust.
     */
    public static void adjustFigureSlidableArea(NodeFigure figure) {
        if (figure instanceof AirDefaultSizeNodeFigure) {
            ((AirDefaultSizeNodeFigure) figure).setSlidableAnchorArea(1.0);
        }
    }

    /**
     * Returns a command to reconnect any connection incident to the specified execution (which is about to be removed)
     * to the parent of that execution while keeping the same vertical range.
     * 
     * @param exec
     *            the execution which is about to be removed.
     * @return a command to reconnect all the edges incident to that execution on its parent, while keeping the same
     *         vertical range.
     */
    public static org.eclipse.emf.common.command.Command getReconnectEdgesToParentCommand(ExecutionEditPart exec) {
        org.eclipse.emf.common.command.CompoundCommand result = new org.eclipse.emf.common.command.CompoundCommand();

        ISequenceEventEditPart parentEP = new EditPartQuery(exec).getFirstAncestorOfType(ISequenceEventEditPart.class);
        AbstractNodeEvent execution = (AbstractNodeEvent) exec.getISequenceEvent();
        ISequenceEvent parent = execution.getHierarchicalParentEvent();
        Range parentRange = parent.getVerticalRange();

        /**
         * Previously, we assumed that the call/return messages of an execution will be deleted with it ; and GMF could
         * be confused if they were reconnected (leaving invalid edit parts on the diagram, leading to a grey cross
         * appearing which can not be removed except by closing/reopening the diagram).
         * <p>
         * This assumption was highly specific to a particular semantic of the delete tool associated with the
         * execution. Now the reconnection is done before the call of the delete tool.
         */

        for (SequenceMessageEditPart msg : Iterables.filter(exec.getSourceConnections(), SequenceMessageEditPart.class)) {
            if (msg.getSource() != msg.getTarget()) {
                result.append(ExecutionOperations.getSourceReconnectionCommand(parentEP, parentRange, msg));
            }
        }
        for (SequenceMessageEditPart msg : Iterables.filter(exec.getTargetConnections(), SequenceMessageEditPart.class)) {
            if (msg.getSource() != msg.getTarget()) {
                result.append(ExecutionOperations.getTargetReconnectionCommand(parentEP, parentRange, msg));
            }
        }
        for (SequenceMessageEditPart msg : Iterables.filter(exec.getSourceConnections(), SequenceMessageEditPart.class)) {
            Message message = (Message) msg.getISequenceEvent();
            if (msg.getSource() == msg.getTarget()) {
                result.append(ExecutionOperations.getReflectiveReconnectionCommand(parentEP.getISequenceEvent(), parentRange, message, parentEP.getEditingDomain()));
            }
        }

        if (result.isEmpty()) {
            return IdentityCommand.INSTANCE;
        } else {
            return result;
        }
    }

    private static Command getReflectiveReconnectionCommand(ISequenceEvent parent, Range parentRange, Message msg, TransactionalEditingDomain domain) {
        CompoundCommand result = new CompoundCommand();
        Range currentRange = msg.getVerticalRange();

        SetMessageRangeOperation smrc = new SetMessageRangeOperation((Edge) msg.getNotationView(), currentRange, true);
        // The source side is reconnected to the parent of the execution being
        // deleted.
        smrc.setSource(parent.getNotationView(), new Rectangle(0, parentRange.getLowerBound(), 0, parentRange.width()));
        // The target side is reconnected to the parent of the execution being
        // removed.
        smrc.setTarget(parent.getNotationView(), new Rectangle(0, parentRange.getLowerBound(), 0, parentRange.width()));
        result.append(CommandFactory.createRecordingCommand(domain, smrc));
        return result;
    }

    private static Command getSourceReconnectionCommand(ISequenceEventEditPart parent, Range parentRange, SequenceMessageEditPart msg) {
        CompoundCommand result = new CompoundCommand();
        Range currentRange = msg.getISequenceEvent().getVerticalRange();

        SetMessageRangeOperation smrc = new SetMessageRangeOperation((Edge) msg.getNotationView(), currentRange, true);
        // The source side is reconnected to the parent of the execution being
        // deleted.
        smrc.setSource(parent.getNotationView(), new Rectangle(0, parentRange.getLowerBound(), 0, parentRange.width()));
        // The target side does not change but we need to compute its bounds and
        // it may not be an ISequenceEvent (e.g. an InstanceRole)
        IGraphicalEditPart target = (IGraphicalEditPart) msg.getTarget();
        Rectangle targetBounds;
        if (target instanceof ISequenceEventEditPart) {
            Range targetRange = SequenceEditPartsOperations.getVerticalRange((ISequenceEventEditPart) target);
            targetBounds = new Rectangle(0, targetRange.getLowerBound(), 0, targetRange.width());
        } else if (target instanceof GateEditPart gep) {
            Gate gate = gep.getGate();
            targetBounds = gate.getProperLogicalBounds().getCopy();

        } else {
            targetBounds = target.getFigure().getBounds().getCopy();
            if (target.getFigure().getParent() != null) {
                target.getFigure().getParent().translateToAbsolute(targetBounds);
            }
        }
        smrc.setTarget(target.getNotationView(), targetBounds);
        result.append(CommandFactory.createRecordingCommand(parent.getEditingDomain(), smrc));
        return result;
    }

    private static Command getTargetReconnectionCommand(ISequenceEventEditPart parent, Range parentRange, SequenceMessageEditPart msg) {
        CompoundCommand result = new CompoundCommand();
        Range currentRange = msg.getISequenceEvent().getVerticalRange();

        SetMessageRangeOperation smrc = new SetMessageRangeOperation((Edge) msg.getNotationView(), currentRange, true);
        // The source side does not change but we need to compute its bounds.
        IGraphicalEditPart source = (IGraphicalEditPart) msg.getSource();
        Rectangle sourceBounds;
        if (source instanceof ISequenceEventEditPart isep) {
            Range sourceRange = SequenceEditPartsOperations.getVerticalRange(isep);
            sourceBounds = new Rectangle(0, sourceRange.getLowerBound(), 0, sourceRange.width());
        } else if (source instanceof LostMessageEndEditPart lep) {
            LostMessageEnd lostMessageEnd = lep.getLostMessageEnd();
            sourceBounds = lostMessageEnd.getProperLogicalBounds().getCopy();
        } else if (source instanceof GateEditPart gep) {
            Gate gate = gep.getGate();
            sourceBounds = gate.getProperLogicalBounds().getCopy();
        } else {
            throw new RuntimeException(MessageFormat.format(Messages.ExecutionOperations_invalidMessageSource, String.valueOf(source)));
        }
        smrc.setSource(source.getNotationView(), sourceBounds);
        // The target side is reconnected to the parent of the execution being
        // removed.
        smrc.setTarget(parent.getNotationView(), new Rectangle(0, parentRange.getLowerBound(), 0, parentRange.width()));
        result.append(CommandFactory.createRecordingCommand(parent.getEditingDomain(), smrc));
        return result;
    }

    /**
     * Returns a command to reconnect all the direct sub-executions of the specified execution (which is about to be
     * removed) to the parent of that execution while keeping the same vertical range.
     * 
     * @param removedExecEditPart
     *            the execution which is about to be removed.
     * @return a command to reconnect all the direct sub-executions of that execution on its parent, while keeping the
     *         same vertical range.
     */
    public static org.eclipse.emf.common.command.Command getReconnectSubExecutionsToParentCommand(final ExecutionEditPart removedExecEditPart) {
        org.eclipse.emf.common.command.CompoundCommand result = new org.eclipse.emf.common.command.CompoundCommand();

        ISequenceEvent removedExec = removedExecEditPart.getISequenceEvent();
        if (removedExec != null) {
            ISequenceEvent futureParent = removedExec.getHierarchicalParentEvent();
            TransactionalEditingDomain domain = removedExecEditPart.getEditingDomain();
            for (View hierarchicalChild : Iterables.filter(Iterables.filter(removedExec.getNotationView().getChildren(), View.class), AbstractNodeEvent.notationPredicate())) {
                Option<AbstractNodeEvent> child = ISequenceElementAccessor.getAbstractNodeEvent(hierarchicalChild);
                if (child.some()) {
                    final Range childRange = child.get().getVerticalRange();
                    result.append(CommandFactory.createRecordingCommand(domain, new ReparentExecutionOperation(child.get(), futureParent)));
                    result.append(CommandFactory.createRecordingCommand(domain, new SetVerticalRangeOperation(child.get(), childRange)));
                }
            }
        }
        if (result.isEmpty()) {
            return IdentityCommand.INSTANCE;
        } else {
            return result;
        }
    }
}
