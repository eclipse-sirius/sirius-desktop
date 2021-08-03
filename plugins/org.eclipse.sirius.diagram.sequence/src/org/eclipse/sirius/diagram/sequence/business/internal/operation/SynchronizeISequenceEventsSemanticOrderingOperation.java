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
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Refreshes the semantic ordering of a an element of a sequence diagram to reflect the current graphical ordering. This
 * command assumes that the <code>GraphicalMessageOrdering</code> and the <code>SemanticMessageOrdering</code> are up to
 * date according to the current visual (resp. semantic) order but that when they do not match, the graphical ordering
 * is the authoritative one and the semantic ordering should be changed to match it, through the appropriate use of the
 * user-specified <code>ReorderTool</code>.
 * 
 * @author pcdavid, smonnier
 */
public class SynchronizeISequenceEventsSemanticOrderingOperation extends AbstractModelChangeOperation<Void> {
    private static final boolean STARTING_END = true;

    private static final boolean FINISHING_END = false;

    private ISequenceEvent event;

    private final Set<ISequenceEvent> selection = new LinkedHashSet<>();

    private final SequenceDDiagram sequenceDiagram;

    private final SequenceDiagram diagram;

    private final Set<ISequenceEvent> reordered = new HashSet<>();

    private final Set<ISequenceEvent> allElementsToReorder = new LinkedHashSet<>();

    /**
     * Constructor.
     * 
     * @param event
     *            the event to move to its new location in the semantic order.
     */
    public SynchronizeISequenceEventsSemanticOrderingOperation(ISequenceEvent event) {
        super(Messages.SynchronizeISequenceEventsSemanticOrderingOperation_operationName);
        this.event = Preconditions.checkNotNull(event);
        this.diagram = event.getDiagram();
        this.sequenceDiagram = this.diagram.getSequenceDDiagram();
    }

    /**
     * Constructor.
     * 
     * @param event
     *            the event to move to its new location in the semantic order.
     * @param selection
     *            additional events to reorder
     */
    public SynchronizeISequenceEventsSemanticOrderingOperation(ISequenceEvent event, Collection<ISequenceEvent> selection) {
        this(event);
        Preconditions.checkNotNull(selection);
        this.selection.addAll(selection);
    }

    @Override
    public Void execute() {
        if (event instanceof AbstractNodeEvent && event.getParentEvent() == null) {
            // The edit-part which requested the synchronize has been removed
            // from the hierarchy.
            // Look for its replacement edit part on the same semantic element
            DDiagramElement dde = (DDiagramElement) event.getNotationView().getElement();
            Collection<ISequenceEvent> events = ISequenceElementAccessor.getEventsForSemanticElement(diagram, dde.getTarget());
            if (!events.isEmpty()) {
                event = events.iterator().next();
            }
        }

        Iterables.addAll(allElementsToReorder, new ISequenceEventQuery(event).getAllSequenceEventToMoveWith(selection));

        updateSemanticPositions();
        return null;
    }

    private void updateSemanticPositions() {
        updateSemanticPosition(event);
        for (ISequenceEvent selected : selection) {
            updateSemanticPosition(selected);
        }
    }

    private void updateSemanticPosition(ISequenceEvent eventToUpdate) {
        DDiagramElement dde = resolveDiagramElement(eventToUpdate);
        if (dde == null || reordered.contains(eventToUpdate)) {
            return;
        }

        ReorderTool reorderTool = findReorderTool(dde);
        if (reorderTool == null) {
            return;
        }

        EObject semanticElement = dde.getTarget();
        EList<EventEnd> endsBySemanticOrder = sequenceDiagram.getSemanticOrdering().getEventEnds();
        EList<EventEnd> endsByGraphicalOrder = sequenceDiagram.getGraphicalOrdering().getEventEnds();

        List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(eventToUpdate);
        List<EventEnd> compoundEnds = getCompoundEnds(eventToUpdate, ends);

        // Ignore the ends of the descendants: they are treated by recursive
        // calls.
        Set<EventEnd> toIgnore = selectEndsToIgnore(eventToUpdate, endsBySemanticOrder, ends, compoundEnds);

        // The semantic ordering contains the state before the move
        EventEnd startingEndPredecessorBefore = findEndPredecessor(semanticElement, STARTING_END, endsBySemanticOrder, toIgnore);
        EventEnd finishingEndPredecessorBefore = findEndPredecessor(semanticElement, FINISHING_END, endsBySemanticOrder, toIgnore);

        // The graphical ordering contains the state after
        EventEnd startingEndPredecessorAfter = findEndPredecessor(semanticElement, STARTING_END, endsByGraphicalOrder, toIgnore);
        EventEnd finishingEndPredecessorAfter = findEndPredecessor(semanticElement, FINISHING_END, endsByGraphicalOrder, toIgnore);

        // Handle lost messages
        if (eventToUpdate.isLogicallyInstantaneous() && eventToUpdate instanceof Message && ends.size() == 1 && !Iterables.any(ends, Predicates.instanceOf(CompoundEventEnd.class))) {
            SingleEventEnd see = (SingleEventEnd) ends.iterator().next();
            if (see.isStart()) {
                finishingEndPredecessorBefore = startingEndPredecessorBefore;
                finishingEndPredecessorAfter = startingEndPredecessorAfter;
            } else {
                startingEndPredecessorBefore = finishingEndPredecessorBefore;
                startingEndPredecessorAfter = finishingEndPredecessorAfter;
            }

        }

        if (!Objects.equal(startingEndPredecessorBefore, startingEndPredecessorAfter) || !Objects.equal(finishingEndPredecessorBefore, finishingEndPredecessorAfter)
                || !Iterables.elementsEqual(endsByGraphicalOrder, endsBySemanticOrder)) {
            applySemanticReordering(semanticElement, startingEndPredecessorAfter, finishingEndPredecessorAfter, reorderTool);
            applyCompoundReordering(semanticElement, ends, compoundEnds, startingEndPredecessorAfter, finishingEndPredecessorAfter, reorderTool);
            reordered.add(eventToUpdate);

            new RefreshSemanticOrderingsOperation(sequenceDiagram).execute();
            updateSubEventsSemanticPositions(eventToUpdate);
        }
    }

    private DDiagramElement resolveDiagramElement(ISequenceEvent eventToUpdate) {
        EObject element = eventToUpdate.getNotationView().getElement();
        if (element instanceof DDiagramElement) {
            return (DDiagramElement) element;
        }
        throw new RuntimeException(MessageFormat.format(Messages.SynchronizeISequenceEventsSemanticOrderingOperation_invalidISequenceEventContext, eventToUpdate));
    }

    private List<EventEnd> getCompoundEnds(ISequenceEvent eventToUpdate, List<EventEnd> ends) {
        List<ISequenceEvent> compoundEvents = EventEndHelper.getCompoundEvents(eventToUpdate);
        Predicate<ISequenceEvent> isLogicallyInstantaneousNonReorderedEvent = new Predicate<ISequenceEvent>() {
            @Override
            public boolean apply(ISequenceEvent input) {
                return input.isLogicallyInstantaneous() && !reordered.contains(input);
            };
        };
        Iterable<ISequenceEvent> compoundEventsToReorder = Iterables.filter(compoundEvents, isLogicallyInstantaneousNonReorderedEvent);
        Iterable<EventEnd> nonReorderedEndsOfCompoundEvents = Iterables.concat(Iterables.transform(compoundEventsToReorder, EventEndHelper.EVENT_ENDS));
        Predicate<EventEnd> isCompoundEnd = Predicates.and(Predicates.instanceOf(SingleEventEnd.class), Predicates.not(Predicates.in(ends)));

        return Lists.newArrayList(Iterables.filter(nonReorderedEndsOfCompoundEvents, isCompoundEnd));
    }

    private void updateSubEventsSemanticPositions(ISequenceEvent eventToUpdate) {
        for (ISequenceEvent subEvent : eventToUpdate.getEventsToMoveWith()) {
            updateSemanticPosition(subEvent);
        }
    }

    private void applyCompoundReordering(EObject semanticElement, List<EventEnd> ends, List<EventEnd> compoundEnds, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor,
            ReorderTool reorderTool) {
        if (compoundEnds.isEmpty() || ends.size() != 2) {
            return;
        }

        EventEnd startEventEnd = null;
        EventEnd endEventEnd = null;
        List<EObject> startSemanticEvents = new ArrayList<>();

        // The main event has been reordered, the order of its ends might have
        // changed.
        for (EventEnd ee : ends) {
            SingleEventEnd see = EventEndHelper.getSingleEventEnd(ee, semanticElement);
            if (see.isStart()) {
                startEventEnd = ee;
                startSemanticEvents = EventEndHelper.getSemanticEvents(startEventEnd);
            } else {
                endEventEnd = ee;
            }
        }

        for (EventEnd ee : compoundEnds) {
            List<EObject> eeSemElts = EventEndHelper.getSemanticEvents(ee);
            EventEnd otherEnd = Iterables.any(eeSemElts, Predicates.in(startSemanticEvents)) ? startEventEnd : endEventEnd;
            EventEnd predecessor = Iterables.any(eeSemElts, Predicates.in(startSemanticEvents)) ? startingEndPredecessor : finishingEndPredecessor;

            for (EObject elt : eeSemElts) {
                SingleEventEnd singleEventEnd = EventEndHelper.getSingleEventEnd(otherEnd, elt);
                ISequenceEvent ise = EventEndHelper.findISequenceEvent(singleEventEnd, diagram);
                if (!singleEventEnd.isStart()) {
                    // SemanticEvent is before otherEnd
                    applySemanticReordering(elt, predecessor, ee, reorderTool);
                } else {
                    // SemanticEvent is after otherEnd
                    applySemanticReordering(elt, predecessor, otherEnd, reorderTool);
                }
                reordered.add(ise);
            }
        }
    }

    private Set<EventEnd> selectEndsToIgnore(ISequenceEvent ise, List<EventEnd> endsBySemanticOrder, final List<EventEnd> iseEnds, final List<EventEnd> compoundEnds) {
        final Iterable<ISequenceEvent> movedElements = Iterables.filter(allElementsToReorder, Predicates.not(Predicates.in(reordered)));
        final Set<EObject> semanticLinked = Sets.newHashSet(Iterables.filter(Iterables.transform(movedElements, ISequenceElement.SEMANTIC_TARGET), Predicates.notNull()));
        final Predicate<EObject> isLinkedSubEventEnd = new Predicate<EObject>() {
            @Override
            public boolean apply(EObject input) {
                return semanticLinked.contains(input);
            }
        };

        final Set<EObject> semanticDescendants = Sets
                .newHashSet(Iterables.filter(Iterables.transform(new ISequenceEventQuery(ise).getAllDescendants(), ISequenceElement.SEMANTIC_TARGET), Predicates.notNull()));
        final Predicate<EObject> isSemanticSubEventEnd = new Predicate<EObject>() {
            @Override
            public boolean apply(EObject input) {
                return semanticDescendants.contains(input);
            }
        };

        Predicate<EventEnd> toIgnore = new Predicate<EventEnd>() {
            @Override
            public boolean apply(EventEnd input) {
                return !iseEnds.contains(input) && (Iterables.any(EventEndHelper.getSemanticEvents(input), Predicates.or(isSemanticSubEventEnd, isLinkedSubEventEnd)) || compoundEnds.contains(input));
            }
        };
        HashSet<EventEnd> newHashSet = Sets.newHashSet(Iterables.filter(endsBySemanticOrder, toIgnore));
        return newHashSet;
    }

    private EventEnd findEndPredecessor(EObject semanticElement, boolean startingEnd, List<EventEnd> eventEnds, Set<EventEnd> toIgnore) {
        EventEnd result = null;
        for (EventEnd end : Iterables.filter(eventEnds, Predicates.not(Predicates.in(toIgnore)))) {
            if (isLookedEnd(semanticElement, startingEnd, end)) {
                break;
            } else {
                result = end;
            }
        }
        return result;
    }

    private boolean isLookedEnd(EObject semanticElement, boolean startingEnd, EventEnd end) {
        boolean currentMovedEnd = false;
        List<EObject> semanticEvents = EventEndHelper.getSemanticEvents(end);
        if (semanticEvents.contains(semanticElement)) {
            boolean lookedEventEnd = EventEndHelper.getSingleEventEnd(end, semanticElement).isStart() == startingEnd;
            boolean punctualCompoundEvent = EventEndHelper.PUNCTUAL_COMPOUND_EVENT_END.apply(end);
            currentMovedEnd = lookedEventEnd || punctualCompoundEvent;
        }
        return currentMovedEnd;
    }

    private void applySemanticReordering(EObject semanticElement, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor, ReorderTool reorderTool) {
        SiriusCommand cmd = ToolCommandBuilder.buildReorderCommand(sequenceDiagram, reorderTool, semanticElement, startingEndPredecessor, finishingEndPredecessor);
        cmd.execute();
    }

    private ReorderTool findReorderTool(DDiagramElement diagramElement) {
        if (diagramElement != null) {
            Collection<AbstractToolDescription> allTools;
            Session session = SessionManager.INSTANCE.getSession(diagramElement);
            if (session != null) {
                allTools = new DiagramComponentizationManager().getAllTools(session.getSelectedViewpoints(false), sequenceDiagram.getDescription());
            } else {
                allTools = new DiagramDescriptionQuery(sequenceDiagram.getDescription()).getAllTools();
            }

            DiagramElementMapping mappingToCheck = new DiagramElementMappingQuery(diagramElement.getDiagramElementMapping()).getRootMapping();

            // TODO Consider layers activation
            for (ReorderTool toolDesc : Iterables.filter(allTools, ReorderTool.class)) {
                if (toolDesc.getMappings().contains(mappingToCheck)) {
                    return toolDesc;
                }
            }
        }
        return null;
    }

}
