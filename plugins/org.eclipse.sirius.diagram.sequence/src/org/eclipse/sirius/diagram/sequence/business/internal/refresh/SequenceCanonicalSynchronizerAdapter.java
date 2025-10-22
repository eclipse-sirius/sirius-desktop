/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.VerticalPositionFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SetVerticalRangeOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceElementQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.RangeComparator;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceDiagramQuery;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.FilterListener;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * {@link ModelChangeTrigger} which return a {@link Command} to be executed just a SynchronizeGMFModelCommand to correct
 * y location of execution (Node) and bendpoints of message (Edge).
 * 
 * @author edugueperoux
 */
public class SequenceCanonicalSynchronizerAdapter implements ModelChangeTrigger {

    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    public static final int SEQUENCE_CANONICAL_REFRESH_PRIORITY = FilterListener.COMPOSITE_FILTER_REFRESH_PRIORITY + 1;

    /**
     * Overridden to return a Command to adapt CanonicalSynchronizer work for sequence.
     * 
     * {@inheritDoc}
     */
    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        Option<Command> result;
        CompoundCommand compoundCommand = new CompoundCommand();
        for (Notification notification : notifications) {
            Object newValue = notification.getNewValue();
            if (SequenceCanonicalSynchronizerAdapterScope.isNotificationForNodeAdding(notification)) {
                Node newNode = (Node) newValue;

                cancelArrangeNewNodeCommandforSequenceDiagram(newNode);

                Command fixSequenceNodeLowerBoundCmd = getFixSequenceNodeCreationRangeCmd(newNode);
                if (fixSequenceNodeLowerBoundCmd != null) {
                    compoundCommand.append(fixSequenceNodeLowerBoundCmd);
                }

            } else if (SequenceCanonicalSynchronizerAdapterScope.isNotificationForEdgeAdding(notification)) {
                Edge newEdge = (Edge) newValue;
                Command fixBendpointsForSequenceMessageCmd = getFixBendpointsForSequenceMessageCmd(newEdge);
                if (fixBendpointsForSequenceMessageCmd != null) {
                    compoundCommand.append(fixBendpointsForSequenceMessageCmd);
                }
            }
        }
        Command cmd = compoundCommand;
        result = Options.newSome(cmd);
        return result;
    }

    private void cancelArrangeNewNodeCommandforSequenceDiagram(Node newNode) {
        Diagram diagram = newNode.getDiagram();
        if (diagram != null && ISequenceElementAccessor.getSequenceDiagram(diagram).some()) {
            SiriusLayoutDataManager.INSTANCE.removeLayoutViews(diagram);
        }
    }

    private Command getFixSequenceNodeCreationRangeCmd(Node newNode) {
        Command fixSequenceNodeLowerBoundCmd = null;
        EObject element = newNode.getElement();
        EObject eContainer = element.eContainer();
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(newNode);
        if (eContainer instanceof AbstractDNode && ISequenceElementAccessor.getISequenceNode(newNode).some()) {
            AbstractDNode containerDNode = (AbstractDNode) eContainer;
            if (containerDNode.getOwnedBorderedNodes().contains(element) && element instanceof AbstractDNode) {
                AbstractDNode borderedDNode = (AbstractDNode) element;
                LayoutData layoutData = null;
                SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(true);
                try {
                    layoutData = SiriusLayoutDataManager.INSTANCE.getData(borderedDNode, true);
                } finally {
                    SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(false);
                }
                if (layoutData != null) {
                    Point location = layoutData.getLocation();
                    int y = location.y;

                    fixSequenceNodeLowerBoundCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.LOCATION__Y, y);
                    Dimension size = layoutData.getSize();
                    if (size != null && newNode.getLayoutConstraint() instanceof Size) {
                        fixSequenceNodeLowerBoundCmd = addSetSizeCmd(fixSequenceNodeLowerBoundCmd, domain, size, newNode);
                    }
                    expandSimpleExecution(newNode, fixSequenceNodeLowerBoundCmd, domain, y);
                } else {
                    fixSequenceNodeLowerBoundCmd = getFlaggedRangeApplicationCommand(newNode, domain);
                }
            } else {
                // Set height of operand to -1 to have
                // SequenceVerticalLayout set the size correctly
                Option<Operand> operandOption = ISequenceElementAccessor.getOperand(newNode);
                if (operandOption.some()) {
                    Command resetOperandHeightCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__HEIGHT, -1);
                    fixSequenceNodeLowerBoundCmd = resetOperandHeightCmd;
                    Command resetOperandWidthCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__WIDTH, -1);
                    fixSequenceNodeLowerBoundCmd = fixSequenceNodeLowerBoundCmd.chain(resetOperandWidthCmd);
                }
            }
        } else {
            // Set height of combined fragment to -1 to have
            // SequenceVerticalLayout set the size correctly
            Option<CombinedFragment> combinedFragmentOption = ISequenceElementAccessor.getCombinedFragment(newNode);
            if (combinedFragmentOption.some() && ((Bounds) newNode.getLayoutConstraint()).getHeight() == LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT) {
                Command resetCombinedFragmentHeightCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__HEIGHT, -1);
                fixSequenceNodeLowerBoundCmd = resetCombinedFragmentHeightCmd;
                Command resetCombinedFragmentWidthCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__WIDTH, -1);
                fixSequenceNodeLowerBoundCmd = fixSequenceNodeLowerBoundCmd.chain(resetCombinedFragmentWidthCmd);
            } else {
                Option<InteractionUse> interactionUseOption = ISequenceElementAccessor.getInteractionUse(newNode);
                if (interactionUseOption.some() && ((Bounds) newNode.getLayoutConstraint()).getHeight() == LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT) {
                    Command resetInteractionUseHeightCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__HEIGHT, -1);
                    fixSequenceNodeLowerBoundCmd = resetInteractionUseHeightCmd;
                    Command resetInteractionUseWidthCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__WIDTH, -1);
                    fixSequenceNodeLowerBoundCmd = fixSequenceNodeLowerBoundCmd.chain(resetInteractionUseWidthCmd);
                }
            }
        }
        return fixSequenceNodeLowerBoundCmd;
    }

    private Command addSetSizeCmd(Command globalCmd, TransactionalEditingDomain domain, Dimension size, Node newNode) {
        Command result = null;
        LayoutConstraint createdNodeLayoutConstraint = newNode.getLayoutConstraint();
        if (createdNodeLayoutConstraint instanceof Size) {
            int width = size.width;
            int height = size.height;

            if (newNode.getElement() instanceof DDiagramElement && new DDiagramElementQuery((DDiagramElement) newNode.getElement()).isCollapsed()) {
                DDiagramElement dde = (DDiagramElement) newNode.getElement();
                CollapseFilter filter = Iterables.filter(dde.getGraphicalFilters(), CollapseFilter.class).iterator().next();

                Command setFilterHeightCmd = SetCommand.create(domain, filter, DiagramPackage.Literals.COLLAPSE_FILTER__HEIGHT, height);
                Command setHeightCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__HEIGHT, height);
                result = globalCmd.chain(setFilterHeightCmd);
                result = result.chain(setHeightCmd);
            } else {
                Command setWidthCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__WIDTH, width);
                Command setHeightCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__HEIGHT, height);
                result = globalCmd.chain(setWidthCmd);
                result = result.chain(setHeightCmd);
            }
        }
        return result;
    }

    private Command getFlaggedRangeApplicationCommand(View newView, TransactionalEditingDomain domain) {
        Command flagCommand = null;
        Option<ISequenceEvent> ise = ISequenceElementAccessor.getISequenceEvent(newView);

        if (ise.some()) {
            Rectangle flag = new ISequenceElementQuery(ise.get()).getFlaggedAbsoluteBounds();

            // Sequence Refresh extension could report an existing flag
            // (external reparent, reconnect, ...)
            if (flag.x == LayoutConstants.EXTERNAL_CHANGE_FLAG.x) {
                Range flaggedRange = new Range(flag.y, flag.bottom());
                flagCommand = CommandFactory.createRecordingCommand(domain, new SetVerticalRangeOperation(ise.get(), flaggedRange));

                if (newView instanceof Node) {
                    LayoutConstraint newViewLC = ((Node) newView).getLayoutConstraint();
                    if (newViewLC instanceof Size) {
                        Command setWidthCmd = SetCommand.create(domain, newViewLC, NotationPackage.Literals.SIZE__WIDTH, flag.width);
                        flagCommand = flagCommand.chain(setWidthCmd);
                    }
                }
            }
        }

        return flagCommand;
    }

    /*
     * Simple Execution creation implies that GMF Node at creation have a LayoutConstants.DEFAULT_EXECUTION_HEIGHT as
     * minimum height and tries to take the maximum available space up to the next event.
     */
    private void expandSimpleExecution(Node newNode, Command fixSequenceNodeLowerBoundCmd, TransactionalEditingDomain domain, int y) {
        Option<Execution> execOption = ISequenceElementAccessor.getExecution(newNode);
        if (execOption.some() && newNode.getSourceEdges().isEmpty() && newNode.getTargetEdges().isEmpty()) {
            Execution execution = execOption.get();
            View parentView = (View) newNode.eContainer();
            Option<ISequenceEvent> parentSequenceEvent = ISequenceElementAccessor.getISequenceEvent(parentView);
            Range parentEventRange = null;
            if (parentSequenceEvent.some()) {
                parentEventRange = parentSequenceEvent.get().getVerticalRange();
            }

            int finalHeight = LayoutConstants.DEFAULT_EXECUTION_HEIGHT;

            // At this step the location of the new create exection
            // is not yet corrected, will be corrected by the above
            // SetCommand
            int lowerRange = y;
            if (parentEventRange != null) {
                lowerRange += parentEventRange.getLowerBound();
            }
            int upperRange = lowerRange + execution.getProperLogicalBounds().height;
            // Doesn't take newly created execution into account
            if (hasEventEndsAfterUpperRange(execution, lowerRange, Collections.<ISequenceEvent> singleton(execution))) {
                int lowerBoundOfNextEventEnd = getRangeLimitOfNextEventEndOf(execution, lowerRange, Collections.<ISequenceEvent> singleton(execution));
                int newUpperRange = lowerBoundOfNextEventEnd - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
                if (newUpperRange > upperRange) {
                    finalHeight = newUpperRange - lowerRange;
                    Command setExecHeightCmd = SetCommand.create(domain, newNode.getLayoutConstraint(), NotationPackage.Literals.SIZE__HEIGHT, finalHeight);
                    fixSequenceNodeLowerBoundCmd.chain(setExecHeightCmd);
                }
            }
        }
    }

    private int getRangeLimitOfNextEventEndOf(Execution execution, int upperRange, Set<ISequenceEvent> sequenceEventToIgnores) {
        SequenceDiagram sequenceDiagram = execution.getDiagram();
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        int rangeLimitOfNextEventEnd = 0;

        EventEnd eventEndAfter = null;
        VerticalPositionFunction vpf = new VerticalPositionFunction(sequenceDDiagram);
        for (EventEnd end : sequenceDDiagram.getGraphicalOrdering().getEventEnds()) {
            int pos = vpf.apply(end);
            if (pos != VerticalPositionFunction.INVALID_POSITION && pos > upperRange) {
                eventEndAfter = end;
                break;
            }
        }
        int parentBottomMargin = LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        if (eventEndAfter != null) {
            rangeLimitOfNextEventEnd = new VerticalPositionFunction(sequenceDDiagram).apply(eventEndAfter);
        } else {
            rangeLimitOfNextEventEnd = upperRange + LayoutConstants.DEFAULT_EXECUTION_HEIGHT + parentBottomMargin;
        }
        return rangeLimitOfNextEventEnd;
    }

    private boolean hasEventEndsAfterUpperRange(Execution execution, int upperRange, Set<ISequenceEvent> sequenceEventToIgnores) {
        SequenceDiagram sequenceDiagram = execution.getDiagram();
        Lifeline lifeline = execution.getLifeline().get();
        Set<ISequenceEvent> eventEndsAfterUpperRange = getEventEndsAfterUpperRange(sequenceDiagram, lifeline, upperRange, sequenceEventToIgnores);
        Set<ISequenceEvent> eventEndsOnUpperRange = getEventEndsOnUpperRange(sequenceDiagram, lifeline, upperRange, sequenceEventToIgnores);
        return !eventEndsAfterUpperRange.isEmpty() || !eventEndsOnUpperRange.isEmpty();
    }

    private Set<ISequenceEvent> getEventEndsAfterUpperRange(SequenceDiagram sequenceDiagram, Lifeline lifeline, int upperRange, Set<ISequenceEvent> sequenceEventToIgnores) {
        Set<ISequenceEvent> eventEndsAfterUpperRange = new TreeSet<ISequenceEvent>(new RangeComparator());
        Set<ISequenceEvent> allSequenceEventsInUpperRange = new SequenceDiagramQuery(sequenceDiagram).getAllSequenceEventsUpperThan(upperRange);
        allSequenceEventsInUpperRange.removeAll(sequenceEventToIgnores);
        eventEndsAfterUpperRange.addAll(Sets.filter(allSequenceEventsInUpperRange, Predicates.not(Predicates.instanceOf(Lifeline.class))));
        return eventEndsAfterUpperRange;
    }

    private Set<ISequenceEvent> getEventEndsOnUpperRange(SequenceDiagram sequenceDiagram, Lifeline lifeline, int upperRange, Set<ISequenceEvent> sequenceEventToIgnores) {
        Set<ISequenceEvent> eventEndsOnUpperRange = new TreeSet<ISequenceEvent>(new RangeComparator());
        Set<ISequenceEvent> allSequenceEventsOnRange = new SequenceDiagramQuery(sequenceDiagram).getAllSequenceEventsOn(upperRange);
        allSequenceEventsOnRange.removeAll(sequenceEventToIgnores);
        eventEndsOnUpperRange.addAll(Sets.filter(allSequenceEventsOnRange, Predicates.not(Predicates.instanceOf(Lifeline.class))));
        return eventEndsOnUpperRange;
    }

    private Command getFixBendpointsForSequenceMessageCmd(final Edge newEdge) {
        Command changeBendpointsCmd = null;
        if (newEdge != null && ISequenceElementAccessor.getMessage(newEdge).some()) {
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(newEdge);
            changeBendpointsCmd = new FixBendpointsOnCreationCommand(domain, newEdge);
            changeBendpointsCmd = changeBendpointsCmd.chain(getFlaggedRangeApplicationCommand(newEdge, domain));
        }
        return changeBendpointsCmd;
    }

    /**
     * Overridden to specify a priority upper than {@link CollapseFilterListener#COLLAPSE_FILTER_REFRESH_PRIORITY} to be
     * executed after it.
     * 
     * {@inheritDoc}
     */
    @Override
    public int priority() {
        return SEQUENCE_CANONICAL_REFRESH_PRIORITY;
    }
}
