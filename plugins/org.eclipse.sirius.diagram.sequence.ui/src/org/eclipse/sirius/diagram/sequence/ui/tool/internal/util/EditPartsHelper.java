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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Helper class with utilities to deal with GMF edit parts.
 * 
 * @author pcdavid
 */
public final class EditPartsHelper {

    /**
     * A predicate which checks that a given edit part is active.
     * 
     * @author pcdavid
     */
    private enum IsValidPredicate implements Predicate<IGraphicalEditPart> {
        INSTANCE;

        public boolean apply(IGraphicalEditPart input) {
            return input.getParent() != null;
        }
    }

    private EditPartsHelper() {
        // Prevents instantiation.
    }

    /**
     * Returns a predicate which checks that a given edit part is active.
     * 
     * @return a predicate which checks that a given edit part is active.
     */
    public static Predicate<IGraphicalEditPart> isValid() {
        return IsValidPredicate.INSTANCE;
    }

    /**
     * Finds all the sequence messages whose source or target is the specified
     * element or any of its descendant edit parts, without duplicates.
     * 
     * @param element
     *            the element from which to start the search for messages.
     * @return the messages found without duplicates.
     */
    public static Set<SequenceMessageEditPart> getAllMessages(IGraphicalEditPart element) {
        Set<SequenceMessageEditPart> allMessages = Sets.newHashSet();
        allMessages.addAll(EditPartsHelper.getAllMessagesFrom(element));
        allMessages.addAll(EditPartsHelper.getAllMessagesTo(element));
        return allMessages;
    }

    /**
     * Finds all the sequence messages whose source is the specified element or
     * any of its descendant edit parts.
     * 
     * @param element
     *            the element from which to start the search for messages.
     * @return the messages found.
     */
    public static List<SequenceMessageEditPart> getAllMessagesFrom(IGraphicalEditPart element) {
        List<SequenceMessageEditPart> messagesParts = Lists.newArrayList();
        EditPartsHelper.addAllMessagesFrom(element, messagesParts);
        return messagesParts;
    }

    /**
     * Finds all the sequence messages whose target is the specified element or
     * any of its descendant edit parts.
     * 
     * @param element
     *            the element from which to start the search for messages.
     * @return the messages found.
     */
    private static List<SequenceMessageEditPart> getAllMessagesTo(IGraphicalEditPart element) {
        List<SequenceMessageEditPart> messagesParts = Lists.newArrayList();
        EditPartsHelper.addAllMessagesTo(element, messagesParts);
        return messagesParts;
    }

    /**
     * Finds all the (non root) executions contained inside the specified
     * element or any of its descendant edit parts.
     * 
     * @param element
     *            the element from which to start the search for executions.
     * @return the executions found.
     */
    public static List<ExecutionEditPart> getAllExecutions(IGraphicalEditPart element) {
        return Lists.newArrayList(Iterators.filter(Iterators.filter(new EditPartsTreeIterator(element), ExecutionEditPart.class), EditPartsHelper.isValid()));
    }

    /**
     * Finds all the lifelines contained inside the specified element or any of
     * its descendant edit parts.
     * 
     * @param element
     *            the element from which to start the search for executions.
     * @return the executions found.
     * 
     */
    public static List<LifelineEditPart> getAllLifelines(IGraphicalEditPart element) {
        return Lists.newArrayList(Iterators.filter(Iterators.filter(new EditPartsTreeIterator(element), LifelineEditPart.class), EditPartsHelper.isValid()));
    }

    /**
     * Finds all the sequence messages whose source is the specified element or
     * any of its descendant edit parts and add them to a collection.
     * 
     * @param element
     *            the element from which to start the search for messages.
     * @param messages
     *            the collection in which to add the messages.
     */
    private static void addAllMessagesFrom(IGraphicalEditPart element, Collection<SequenceMessageEditPart> messages) {
        for (IGraphicalEditPart connectionPart : Iterables.filter(element.getSourceConnections(), IGraphicalEditPart.class)) {
            if (connectionPart instanceof SequenceMessageEditPart && EditPartsHelper.isValid().apply(connectionPart)) {
                messages.add((SequenceMessageEditPart) connectionPart);
            }
        }
        if (element instanceof SequenceMessageEditPart && EditPartsHelper.isValid().apply(element)) {
            messages.add((SequenceMessageEditPart) element);
        }
        for (IGraphicalEditPart child : Iterables.filter(element.getChildren(), IGraphicalEditPart.class)) {
            EditPartsHelper.addAllMessagesFrom(child, messages);
        }
    }

    /**
     * Finds all the sequence messages whose target is the specified element or
     * any of its descendant edit parts and add them to a collection.
     * 
     * @param element
     *            the element from which to start the search for messages.
     * @param messages
     *            the collection in which to add the messages.
     */
    private static void addAllMessagesTo(IGraphicalEditPart element, Collection<SequenceMessageEditPart> messages) {
        for (IGraphicalEditPart connectionPart : Iterables.filter(element.getTargetConnections(), IGraphicalEditPart.class)) {
            if (connectionPart instanceof SequenceMessageEditPart && EditPartsHelper.isValid().apply(connectionPart)) {
                messages.add((SequenceMessageEditPart) connectionPart);
            }
        }
        if (element instanceof SequenceMessageEditPart && EditPartsHelper.isValid().apply(element)) {
            messages.add((SequenceMessageEditPart) element);
        }
        for (IGraphicalEditPart child : Iterables.filter(element.getChildren(), IGraphicalEditPart.class)) {
            EditPartsHelper.addAllMessagesTo(child, messages);
        }
    }

    /**
     * Finds the parent {@link LifelineEditPart}.
     * 
     * @param part
     *            the {@link IGraphicalEditPart} to find its parent
     *            {@link LifelineEditPart}.
     * @return the parent {@link LifelineEditPart}
     */
    public static LifelineEditPart findParentLifeline(IGraphicalEditPart part) {
        final LifelineEditPart result;
        if (part instanceof LifelineEditPart && EditPartsHelper.isValid().apply(part)) {
            result = (LifelineEditPart) part;
        } else if (part != null) {
            result = new EditPartQuery(part).getFirstAncestorOfType(LifelineEditPart.class);
        } else {
            result = null;
        }
        return result;
    }

    /**
     * Finds and returns the ISequenceEvent corresponding to the given
     * SingleEventEnd.
     * 
     * @param end
     *            the end to look for
     * @param sdep
     *            the SequenceDiagramEditPart to inspect
     * @return the ISequenceEvent corresponding to the given part
     */
    public static ISequenceEventEditPart findISequenceEvent(SingleEventEnd end, SequenceDiagramEditPart sdep) {
        for (ISequenceEventEditPart ise : Iterables.concat(EditPartsHelper.getAllExecutions(sdep), EditPartsHelper.getAllMessagesFrom(sdep))) {
            EObject semanticEvent = ise.resolveTargetSemanticElement();
            if (end.getSemanticEvent().equals(semanticEvent)) {
                return ise;
            }
        }
        return null;
    }

    /**
     * Returns the sequence diagram edit part owning the edit part.
     * 
     * @param editPart
     *            the edit part.
     * @return the sequence diagram edit part owning the part.
     */
    public static SequenceDiagramEditPart getSequenceDiagramPart(IGraphicalEditPart editPart) {
        if (editPart instanceof SequenceDiagramEditPart) {
            return (SequenceDiagramEditPart) editPart;
        }
        IGraphicalEditPart current = editPart;
        if (editPart instanceof ConnectionEditPart) {
            ConnectionEditPart conn = (ConnectionEditPart) editPart;
            current = (IGraphicalEditPart) conn.getSource();
        }
        return new EditPartQuery(current).getFirstAncestorOfType(SequenceDiagramEditPart.class);
    }

    /**
     * Get a {@link SequenceDiagram} from a EditPart of Sequence representation.
     * 
     * @param host
     *            editPart of Sequence representation
     * @param <T>
     *            any subtype of EditPart
     * @return the {@link SequenceDiagram} of a sequence representation
     */
    public static <T extends EditPart> SequenceDiagram getSequenceDiagram(T host) {
        EditPart parent = host;
        while (parent != null && !(parent instanceof ISequenceEventEditPart || parent instanceof SequenceDiagramEditPart)) {
            parent = parent.getParent();
        }
        SequenceDiagram sequenceDiagram = null;
        if (parent instanceof SequenceDiagramEditPart) {
            sequenceDiagram = ((SequenceDiagramEditPart) parent).getSequenceDiagram();
        } else if (parent instanceof ISequenceEventEditPart) {
            sequenceDiagram = ((ISequenceEventEditPart) parent).getISequenceEvent().getDiagram();
        }
        return sequenceDiagram;
    }
}
