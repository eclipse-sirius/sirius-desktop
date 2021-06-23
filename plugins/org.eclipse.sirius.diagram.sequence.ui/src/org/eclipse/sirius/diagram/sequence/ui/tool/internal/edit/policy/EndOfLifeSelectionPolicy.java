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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ui.Messages;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.EndOfLifeOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.ShiftDescendantMessagesOperation;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.SequenceDiagramEPQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SpecificBorderItemSelectionEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.base.Preconditions;

/**
 * This policy controls the moves of {@link EndOfLifeEditPart}s, and in particular how such moves actually resize the
 * parent lifeline.
 * 
 * @author pcdavid
 */
public class EndOfLifeSelectionPolicy extends SpecificBorderItemSelectionEditPolicy {
    /**
     * Overridden to validate the host type.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void setHost(EditPart host) {
        Preconditions.checkArgument(host instanceof EndOfLifeEditPart);
        super.setHost(host);
    }

    /**
     * Convenience method to return the host part with the correct type.
     * 
     * @return returns the host of this policy, with the appropriate type.
     */
    protected EndOfLifeEditPart getEOL() {
        return (EndOfLifeEditPart) getHost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        Command result = UnexecutableCommand.INSTANCE;
        constrainMoveVertically(request);
        if (canResizeLifeline(getEOL(), request)) {
            ChangeBoundsRequest cbr = EndOfLifeOperations.getLifelineResizeRequest(request, getEOL(), getEOL());
            if (cbr != null) {
                result = getLifelineMovesCommand(getEOL(), cbr);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSourceFeedback(Request request) {
        constrainMoveVertically(request);
        super.showSourceFeedback(request);
        if (request instanceof ChangeBoundsRequest && canResizeLifeline(getEOL(), (ChangeBoundsRequest) request)) {
            EndOfLifeOperations.showEndOfLifeFeedback(request, getEOL(), getEOL());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eraseSourceFeedback(Request request) {
        constrainMoveVertically(request);
        super.eraseSourceFeedback(request);
        EndOfLifeOperations.eraseEndOfLifeFeedback((LifelineEditPart) getEOL().getParent(), request);
    }

    /**
     * Modifies the requested moves so that the part only moves vertically. Horizontal motion is disabled/ignored.
     */
    private void constrainMoveVertically(Request request) {
        if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
            cbr.setMoveDelta(new Point(0, cbr.getMoveDelta().y));
        }
    }

    private Command getLifelineMovesCommand(EndOfLifeEditPart self, ChangeBoundsRequest cbr) {
        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(self.getEditingDomain(), Messages.EndOfLifeSelectionPolicy_lifelineMoveCommand);
        Option<EndOfLife> endOfLife = ISequenceElementAccessor.getEndOfLife(self.getNotationView());
        LifelineEditPart lep = self.getLifelineEditPart();
        boolean destroyed = endOfLife.some() && endOfLife.get().isExplicitelyDestroyed();

        if (lep != null) {
            if (destroyed) {
                /*
                 * Moving a real EOL (i.e. one which represents the explicit destruction of the message) only resizes
                 * the corresponding lifeline.
                 */
                addLifelineResizeCommand(self, cbr, ctc, lep);
            } else {
                /*
                 * Otherwise the EOL is just a handle to control the vertical range of the whole scenario, so we resize
                 * all the lifelines which are free, i.e. not explicitly destroyed at some specific position.
                 */
                SequenceDiagramEditPart diagram = EditPartsHelper.getSequenceDiagramPart(self);
                for (LifelineEditPart lifeline : new SequenceDiagramEPQuery(diagram).getAllLifelines()) {
                    if (!((Lifeline) lifeline.getISequenceEvent()).isExplicitlyDestroyed()) {
                        addLifelineResizeCommand(self, cbr, ctc, lifeline);
                    }
                }
            }
        }
        ICommandProxy iCommandProxy = new ICommandProxy(ctc);
        iCommandProxy.setLabel(ctc.getLabel());
        return iCommandProxy;
    }

    private void addLifelineResizeCommand(EndOfLifeEditPart self, ChangeBoundsRequest cbr, CompositeTransactionalCommand ctc, LifelineEditPart lep) {
        cbr.setEditParts(lep);
        ctc.compose(new CommandProxy(lep.getCommand(cbr)));

        Dimension sizeDelta = cbr.getSizeDelta().getCopy();
        GraphicalHelper.screen2logical(sizeDelta, getEOL());
        TransactionalEditingDomain domain = getEOL().getEditingDomain();
        ctc.compose(CommandFactory.createICommand(domain, new ShiftDescendantMessagesOperation(lep.getISequenceEvent(), sizeDelta.height, true, false, false)));
    }

    private boolean canResizeLifeline(EndOfLifeEditPart self, ChangeBoundsRequest request) {
        Option<EndOfLife> endOfLife = ISequenceElementAccessor.getEndOfLife(self.getNotationView());
        boolean destroyed = endOfLife.some() && endOfLife.get().isExplicitelyDestroyed();
        boolean result = !destroyed || request.isConstrainedMove();
        LifelineEditPart lep = self.getLifelineEditPart();
        if (result && lep != null && (org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants.REQ_DROP.equals(request.getType()) || RequestConstants.REQ_MOVE.equals(request.getType()))) {
            Range range = lep.getISequenceEvent().getVerticalRange();
            int moveDeltaY = request.getMoveDelta().y;
            int rangeLimit = getRangeLimit(self, lep, range, moveDeltaY, destroyed);
            result = range.getUpperBound() + moveDeltaY >= rangeLimit;
        } else {
            result = false;
        }
        return result;
    }

    private int getRangeLimit(EndOfLifeEditPart self, LifelineEditPart lep, Range range, int moveDeltaY, boolean isDestroyed) {
        SequenceDiagramEditPart sdep = EditPartsHelper.getSequenceDiagramPart(self);
        int rangeLimit = range.getLowerBound() + LayoutConstants.EXECUTION_CHILDREN_MARGIN;

        if (!isDestroyed) {
            View lifelineView = (View) lep.getModel();
            Lifeline lifeline = ISequenceElementAccessor.getLifeline(lifelineView).get();
            DNode dNode = (DNode) lifelineView.getElement();

            rangeLimit = Math.max(rangeLimit, new DNodeQuery(dNode).getDefaultDimension().height + lifeline.getVerticalRange().getLowerBound());
            EventEnd lastEnd = SequenceGraphicalHelper.getEndBefore((SequenceDDiagram) sdep.resolveSemanticElement(), range.getUpperBound());
            if (moveDeltaY < 0 && lastEnd != null) {
                SingleEventEnd see;
                if (lastEnd instanceof SingleEventEnd) {
                    see = (SingleEventEnd) lastEnd;
                } else {
                    see = ((CompoundEventEnd) lastEnd).getEventEnds().iterator().next();
                }

                ISequenceEventEditPart ise = EditPartsHelper.findISequenceEvent(see, sdep);
                rangeLimit = Math.max(rangeLimit, SequenceEditPartsOperations.getVerticalRange(ise).getUpperBound() + LayoutConstants.EXECUTION_CHILDREN_MARGIN);
            }
        } else {
            rangeLimit = Math.max(lep.getISequenceEvent().getOccupiedRange().getUpperBound() + LayoutConstants.EXECUTION_CHILDREN_MARGIN, rangeLimit);
        }
        return rangeLimit;
    }

}
