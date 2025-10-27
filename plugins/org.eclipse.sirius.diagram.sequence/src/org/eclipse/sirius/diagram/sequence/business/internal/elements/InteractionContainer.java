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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

/**
 * Represents an interaction container.
 * 
 * @author smonnier
 */
public class InteractionContainer extends AbstractSequenceNode {

    /**
     * The visual ID. Same as a normal container
     * 
     * @see DNodeContainerEditPart.VISUAL_ID.
     */
    public static final int VISUAL_ID = 2002;

    /**
     * The visual ID of the compartment contained by the InteractionContainer.
     * 
     * see
     * org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID
     */
    public static final int COMPARTMENT_VISUAL_ID = 7001;

    /**
     * Default margin of the Interaction Container.
     */
    public static final int MARGIN = 50;

    /**
     * Default width of the Interaction Container.
     */
    public static final int DEFAULT_WIDTH = 80 + MARGIN * 2;

    /**
     * Default height of the Interaction Container.
     */
    public static final int DEFAULT_HEIGHT = 500;

    InteractionContainer(Node node) {
        super(node);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an combined fragment.
     * 
     * @return a predicate to check whether a GMF View represents an combined fragment.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, InteractionContainer.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a GMF View represents an combined fragment compartment.
     * 
     * @return a predicate to check whether a GMF View represents an combined fragment compartment.
     */
    public static Predicate<View> compartmentNotationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), COMPARTMENT_VISUAL_ID, InteractionContainer.viewpointElementPredicate());
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
     * Predicate to check whether a Sirius DDiagramElement represents an Interaction Container.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getInteractionContainerMapping());
        }
    }

    /**
     * Get proper logical bounds.
     * 
     * @return proper logical bounds
     */
    @Override
    public Rectangle getProperLogicalBounds() {
        /*
         * InteractionContainers are directly on the diagram itself, so we can use the raw GMF bounds as is.
         */
        return getRawNotationBounds();
    }

    /**
     * Get the gates of the current combined fragment.
     * 
     * @return the gates of the current combined fragment.
     */
    public Collection<Gate> getGates() {
        List<Gate> result = null;
        if (CacheHelper.isStructuralCacheEnabled()) {
            result = CacheHelper.getParentToGatesCache().get(this);
        }

        if (result == null) {
            result = new ArrayList<>();
            Predicate<View> gateView = new Predicate<View>() {

                @Override
                public boolean apply(View input) {
                    return input.getType().equals(Integer.toString(Gate.VISUAL_ID));
                }
            };
            for (View view : Iterables.filter(Iterables.filter(this.view.eContents(), View.class), gateView)) {
                Option<Gate> gate = ISequenceElementAccessor.getGate(view);
                if (gate.some()) {
                    result.add(gate.get());
                }
            }
            Collections.sort(result, Ordering.natural().onResultOf(AbstractFrame.gateOrderingFunction()));
            if (CacheHelper.isStructuralCacheEnabled()) {
                CacheHelper.getParentToGatesCache().put(this, result);
            }
        }
        return result;
    }

}
