/*******************************************************************************
 * Copyright (c) 2025 CEA.
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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;

/**
 * Represents an execution on a lifeline or another parent execution.
 * 
 * @author mporhel, pcdavid, smonnier
 */
public abstract class AbstractSequenceNodeEvent extends AbstractSequenceNode {

    /**
     * Predicate to filter Frames and Operand from possible new parents of an execution reparent.
     */
    public static final Predicate<ISequenceEvent> NO_REPARENTABLE_EVENTS = new Predicate<ISequenceEvent>() {
        @Override
        public boolean apply(ISequenceEvent input) {
            return input instanceof AbstractFrame || input instanceof State || input instanceof Operand || input instanceof Message;
        }
    };

    /**
     * The visual ID. Same as a normal bordered node.
     * 
     * see org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart. VISUAL_ID
     */
    public static final int VISUAL_ID = 3001;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return (AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getExecutionMapping())
                    || AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getStateMapping()))
                    && !InstanceRole.viewpointElementPredicate().apply((DDiagramElement) input.eContainer());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing this execution.
     */
    AbstractSequenceNodeEvent(Node node) {
        super(node);
        // Preconditions.checkArgument(AbstractNodeEvent.notationPredicate().apply(node),
        // Messages.AbstractNodeEvent_nonAbstractNodeEventNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an execution.
     * 
     * @return a predicate to check whether a GMF View represents an execution.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, AbstractSequenceNodeEvent.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an execution.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    /**
     * Returns all the messages linked to one of the sides (top or bottom) of this execution. The resulting list can be:
     * <ul>
     * <li>empty, meaning there is no messages linked to any of this execution's sides.</li>
     * <li>a list with a single element, in which case that element is the message linked to the top side of this
     * execution.</li>
     * <li>a list with two element, in which case the first element is the message linked to the top side of this
     * execution and the second is the message linked to the bottom side.</li>
     * </ul>
     * Note that the case where there is only one linked message and it is linked to the bottom side is not supported.
     * 
     * @return all the messages linked to one of the sides (top or bottom) of this execution.
     */
    public abstract List<Message> getLinkedMessages();

    /**
     * Returns the hierarchical parent event of this event (from a Notation point of view), if any.
     * 
     * @param noFoundParentExceptionMessage
     *            message of the RuntimeException which will be created if no parent is found.
     * 
     * @return the hierarchical parent event of this event, if any.
     */
    protected ISequenceEvent getHierarchicalParentEvent(String noFoundParentExceptionMessage) {
        if (CacheHelper.isStructuralCacheEnabled()) {
            ISequenceEvent hierarchicalParent = CacheHelper.getAbstractNodeEventToHierarchicalParentCache().get(this);
            if (hierarchicalParent != null) {
                return hierarchicalParent;
            }
        }

        EObject viewContainer = this.view.eContainer();
        if (viewContainer instanceof View) {
            View parentView = (View) viewContainer;
            Option<ISequenceEvent> parentElement = ISequenceElementAccessor.getISequenceEvent(parentView);
            if (parentElement.some()) {
                if (CacheHelper.isStructuralCacheEnabled()) {
                    CacheHelper.getAbstractNodeEventToHierarchicalParentCache().put(this, parentElement.get());
                }

                return parentElement.get();
            }
        }
        throw new RuntimeException(MessageFormat.format(noFoundParentExceptionMessage, this));
    }
}
