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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Represents a combined fragment container.
 * 
 * @author pcdavid
 */
public class CombinedFragment extends AbstractFrame {
    /**
     * The visual ID. Same as a normal container
     * 
     * @see DNodeContainerEditPart.VISUAL_ID.
     */
    public static final int VISUAL_ID = 2002;

    /**
     * The visual ID of the compartment contained by the combined fragment. It is this compartment that contains the
     * operands.
     * 
     * @see DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID.
     */
    public static final int COMPARTMENT_VISUAL_ID = 7001;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getCombinedFragmentMapping());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing the combined fragment.
     */
    CombinedFragment(Node node) {
        super(node);
        Preconditions.checkArgument(CombinedFragment.notationPredicate().apply(node), Messages.CombinedFragment_nonCombinedFragmentNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an combined fragment.
     * 
     * @return a predicate to check whether a GMF View represents an combined fragment.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, CombinedFragment.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a GMF View represents an combined fragment compartment.
     * 
     * @return a predicate to check whether a GMF View represents an combined fragment compartment.
     */
    public static Predicate<View> compartmentNotationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), COMPARTMENT_VISUAL_ID, CombinedFragment.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an execution.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    @Override
    public Range getVerticalRange() {
        // Rectangle logicalBounds = getProperLogicalBounds();
        // return new Range(logicalBounds.y, logicalBounds.bottom());
        return new SequenceNodeQuery(getNotationNode()).getVerticalRange();
    }

    @Override
    public void setVerticalRange(Range range) throws IllegalStateException {
        RangeSetter.setVerticalRange(this, range);
    }

    @Override
    public List<ISequenceEvent> getSubEvents() {
        return Lists.newArrayList(Iterables.filter(getOperands(), ISequenceEvent.class));
    }

    /**
     * Get the operands of the current combined fragment.
     * 
     * @return the operands of the current combined fragment.
     */
    public List<Operand> getOperands() {
        List<Operand> result = null;
        if (CacheHelper.isStructuralCacheEnabled()) {
            result = CacheHelper.getCombinedFragmentToOperandsCache().get(this);
        }

        if (result == null) {
            result = new ArrayList<>();
            Predicate<View> compartementView = new Predicate<View>() {

                @Override
                public boolean apply(View input) {
                    return input.getType().equals(Integer.toString(COMPARTMENT_VISUAL_ID));
                }
            };
            // The combined fragment contains a compartment that contains the
            // operands
            for (View view : Iterables.filter(Iterables.filter(this.view.eContents(), View.class), compartementView)) {
                // Filtering compartments
                for (View viewChild : Iterables.filter(view.eContents(), View.class)) {
                    // Filtering operands
                    Option<Operand> operand = ISequenceElementAccessor.getOperand(viewChild);
                    if (operand.some()) {
                        result.add(operand.get());
                    }
                }
            }
            Collections.sort(result, RangeHelper.lowerBoundOrdering().onResultOf(ISequenceEvent.VERTICAL_RANGE));
            if (CacheHelper.isStructuralCacheEnabled()) {
                CacheHelper.getCombinedFragmentToOperandsCache().put(this, result);
            }
        }
        return result;
    }

    /**
     * Finds the operand identified by the index.
     * 
     * @param index
     *            the position of the wanted operand
     * @return an Option of Operand if there is an operand at the given index, an Options.newNone() otherwise
     */
    public Option<Operand> getOperand(int index) {
        try {
            return Options.newSome(getOperands().get(index));
        } catch (IndexOutOfBoundsException e) {
            return Options.newNone();
        }
    }

    /**
     * calculate the index of the operand among the list of operands in this {@link CombinedFragment}.
     * 
     * @param operand
     *            the operand to find out its index
     * @return the index of the operand
     */
    public int getIndexOfOperand(Operand operand) {
        return getOperands().lastIndexOf(operand);
    }

    /**
     * Finds the first operand of this {@link CombinedFragment}.
     * 
     * @return the first operand of this {@link CombinedFragment}
     */
    public Operand getFirstOperand() {
        // A combined fragment always have at least one operand
        return getOperand(0).get();
    }

    /**
     * Finds the last operand of this {@link CombinedFragment}.
     * 
     * @return the last operand of this {@link CombinedFragment}
     */
    public Operand getLastOperand() {
        // A combined fragment always have at least one operand
        int lastIndex = getOperands().size() - 1;
        return getOperand(lastIndex).get();
    }
}
