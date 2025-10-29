/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.ordering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingFactory;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

/**
 * Helper class to factor common code for semantic and graphical orders
 * refreshing.
 * 
 * @author pcdavid
 */
public final class RefreshOrderingHelper {
    @SuppressWarnings("unchecked")
    private static final Predicate<DDiagramElement> DELIMITED_EVENT_PREDICATE = Predicates.or(AbstractNodeEvent.viewpointElementPredicate(), State.viewpointElementPredicate(),
            InteractionUse.viewpointElementPredicate(), CombinedFragment.viewpointElementPredicate(), Operand.viewpointElementPredicate());

    private RefreshOrderingHelper() {
        // Prevent instantiation.
    }

    /**
     * Replaces the content of a list with new contents, but only if there are
     * actual differences between the two versions.
     * 
     * @param <T>
     *            the type of elements in the lists.
     * @param oldValue
     *            the old list of values. Modified in place if necessary.
     * @param newValue
     *            the new list of values. Not modified.
     * @return true if the content is updated, false otherwise.
     */
    public static <T> boolean updateIfNeeded(List<T> oldValue, List<T> newValue) {
        if (RefreshOrderingHelper.differentContents(oldValue, newValue)) {
            oldValue.clear();
            oldValue.addAll(newValue);
            return true;
        }
        return false;
    }

    /**
     * Determines if two lists have the same contents in the same order or not.
     * Elements are compared using {@link #equals(Object)}.
     * 
     * @param <T>
     *            the type of elements in the lists.
     * @param oldValue
     *            the old list of values.
     * @param newValue
     *            the new lists of values.
     * @return <code>true</code> if the list of new values have different
     *         contents or the same elements but in a different order rom the
     *         old one.
     */
    public static <T> boolean differentContents(List<T> oldValue, List<T> newValue) {
        if (oldValue.size() != newValue.size()) {
            return true;
        } else {
            return !Iterables.elementsEqual(oldValue, newValue);
        }
    }

    /**
     * Returns all the starting and finishing ends of all the events in the
     * specified diagram.
     * 
     * @param sequenceDiagram
     *            a sequence diagram.
     * @return all the starting and finishing ends of all the events in the
     *         specified diagram.
     */
    public static Iterable<? extends EventEnd> getAllEventEnds(SequenceDDiagram sequenceDiagram) {
        Multimap<EObject, SingleEventEnd> semanticEndToSingleEventEnds = ArrayListMultimap.<EObject, SingleEventEnd> create();
        List<EventEnd> result = new ArrayList<>();

        RefreshOrderingHelper.addAllSingleEventEnds(sequenceDiagram, semanticEndToSingleEventEnds);

        RefreshOrderingHelper.minimizeEventEnds(result, semanticEndToSingleEventEnds);

        return result;
    }

    private static void minimizeEventEnds(List<EventEnd> result, Multimap<EObject, SingleEventEnd> semanticEndToSingleEventEnds) {
        for (EObject semanticEnd : semanticEndToSingleEventEnds.keySet()) {
            Collection<SingleEventEnd> sees = semanticEndToSingleEventEnds.get(semanticEnd);
            if (sees.isEmpty()) {
                continue;
            }

            if (sees.size() == 1) {
                result.add(sees.iterator().next());
            } else {
                CompoundEventEnd cee = OrderingFactory.eINSTANCE.createCompoundEventEnd();
                cee.setSemanticEnd(semanticEnd);

                if (sees.size() == 2 && RefreshOrderingHelper.countEvents(sees) == 1) {
                    // start first
                    Iterables.addAll(cee.getEventEnds(), Iterables.filter(sees, EventEndHelper.IS_START));
                    Iterables.addAll(cee.getEventEnds(), Iterables.filter(sees, Predicates.not(EventEndHelper.IS_START)));
                } else {
                    // end first
                    Iterables.addAll(cee.getEventEnds(), Iterables.filter(sees, Predicates.not(EventEndHelper.IS_START)));
                    Iterables.addAll(cee.getEventEnds(), Iterables.filter(sees, EventEndHelper.IS_START));
                }
                result.add(cee);
            }
        }
    }

    private static int countEvents(Collection<SingleEventEnd> sees) {
        Set<EObject> events = new HashSet<>();
        for (SingleEventEnd see : sees) {
            events.add(see.getSemanticEvent());
        }
        return events.size();
    }

    private static void addAllSingleEventEnds(SequenceDDiagram sequenceDiagram, Multimap<EObject, SingleEventEnd> semanticEndToSingleEventEnd) {

        // Messages
        for (DEdge edge : Iterables.filter(sequenceDiagram.getEdges(), Message.viewpointElementPredicate())) {
            /*
             * Target may be null if the semantic element has been removed from
             * the model but the canonical refresh has not happened yet.
             */
            if (edge.getTarget() != null) {
                RefreshOrderingHelper.add(RefreshOrderingHelper.getStartingEnd(edge, DescriptionPackage.eINSTANCE.getMessageMapping_SendingEndFinderExpression()), semanticEndToSingleEventEnd);
                RefreshOrderingHelper.add(RefreshOrderingHelper.getFinishingEnd(edge, DescriptionPackage.eINSTANCE.getMessageMapping_ReceivingEndFinderExpression()), semanticEndToSingleEventEnd);
            }
        }

        // Delimited elements : Executions, States, InteractionUse, Frames,
        // Operands
        for (DDiagramElement node : Iterables.filter(Iterables.filter(AllContents.of(sequenceDiagram), DDiagramElement.class), RefreshOrderingHelper.DELIMITED_EVENT_PREDICATE)) {
            /*
             * Target may be null if the semantic element has been removed from
             * the model but the canonical refresh has not happened yet.
             */
            if (node.getTarget() != null) {
                SingleEventEnd startingEnd = RefreshOrderingHelper.getStartingEnd(node, DescriptionPackage.eINSTANCE.getDelimitedEventMapping_StartingEndFinderExpression());
                SingleEventEnd finishingEnd = RefreshOrderingHelper.getFinishingEnd(node, DescriptionPackage.eINSTANCE.getDelimitedEventMapping_FinishingEndFinderExpression());

                RefreshOrderingHelper.add(startingEnd, semanticEndToSingleEventEnd);
                RefreshOrderingHelper.add(finishingEnd, semanticEndToSingleEventEnd);
            }
        }
    }

    private static void add(SingleEventEnd see, Multimap<EObject, SingleEventEnd> endToEventEnds) {
        if (see != null && see.getSemanticEnd() != null) {
            endToEventEnds.put(see.getSemanticEnd(), see);
        }
    }

    /**
     * Helper to create an event end representing the start of an event.
     * 
     * @param semanticEvent
     *            the semantic element representing the event itself.
     * @param semanticEnd
     *            the semantic element representing the starting end of the
     *            event.
     * @return an EventEnd representing the starting end of the event.
     */
    public static SingleEventEnd createStartingEventEnd(EObject semanticEvent, EObject semanticEnd) {
        SingleEventEnd result = OrderingFactory.eINSTANCE.createSingleEventEnd();
        result.setStart(true);
        result.setSemanticEvent(semanticEvent);
        result.setSemanticEnd(semanticEnd);
        return result;
    }

    /**
     * Helper to create an event end representing the finishing of an event.
     * 
     * @param semanticEvent
     *            the semantic element representing the event itself.
     * @param semanticEnd
     *            the semantic element representing the finishing end of the
     *            event.
     * @return an EventEnd representing the finishing end of the event.
     */
    public static SingleEventEnd createFinishingEventEnd(EObject semanticEvent, EObject semanticEnd) {
        SingleEventEnd result = OrderingFactory.eINSTANCE.createSingleEventEnd();
        result.setStart(false);
        result.setSemanticEvent(semanticEvent);
        result.setSemanticEnd(semanticEnd);
        return result;
    }

    private static SingleEventEnd getStartingEnd(DDiagramElement dde, EAttribute startingEnd) {
        EObject semanticEvent = dde.getTarget();
        IInterpreter interpreter = InterpreterUtil.getInterpreter(dde);
        RuntimeLoggerInterpreter loggerInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
        EObject sendingEnd = loggerInterpreter.evaluateEObject(semanticEvent, dde.getDiagramElementMapping(), startingEnd);
        return RefreshOrderingHelper.createStartingEventEnd(semanticEvent, sendingEnd);
    }

    private static SingleEventEnd getFinishingEnd(DDiagramElement dde, EAttribute finishingEnd) {
        EObject semanticEvent = dde.getTarget();
        IInterpreter interpreter = InterpreterUtil.getInterpreter(dde);
        RuntimeLoggerInterpreter loggerInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
        EObject receivingEnd = loggerInterpreter.evaluateEObject(semanticEvent, dde.getDiagramElementMapping(), finishingEnd);
        return RefreshOrderingHelper.createFinishingEventEnd(semanticEvent, receivingEnd);
    }
}
