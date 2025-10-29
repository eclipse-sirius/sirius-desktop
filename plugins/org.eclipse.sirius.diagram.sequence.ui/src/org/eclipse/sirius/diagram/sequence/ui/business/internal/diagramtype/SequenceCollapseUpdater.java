/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.business.internal.diagramtype;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ObservationPoint;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CollapseUpdater;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.draw2d.figure.ICollapseMode;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A specific @link
 * {@link org.eclipse.sirius.diagram.business.api.diagramtype.ICollapseUpdater} to
 * manage correctly the
 * {@link org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution}
 * .
 * 
 * @author lredor
 */
public class SequenceCollapseUpdater extends CollapseUpdater {

    private Predicate<DDiagramElement> specificCollapsePredicate = Predicates.or(AbstractNodeEvent.viewpointElementPredicate(), Lifeline.viewpointElementPredicate(),
            ObservationPoint.viewpointElementPredicate());

    /**
     * Default constructor.
     */
    public SequenceCollapseUpdater() {
    }

    /**
     * {@inheritDoc}
     */
    protected void synchronizeCollapseFiltersAndGMFBounds(DDiagramElement element, Option<Node> optionalNode, boolean add, Class<? extends CollapseFilter> kindOfFilter) {

        if (InstanceRole.viewpointElementPredicate().apply(element))
            return;

        super.synchronizeCollapseFiltersAndGMFBounds(element, optionalNode, add, kindOfFilter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.CollapseUpdater#collapseBounds(Node,
     *      DDiagramElement)
     */
    public void collapseBounds(Node node, DDiagramElement element) {
        if (!specificCollapsePredicate.apply(element)) {
            super.collapseBounds(node, element);
        } else {
            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                Bounds newBounds = NotationFactory.eINSTANCE.createBounds();
                // Initialize the collapsed bounds
                newBounds.setX(bounds.getX() + bounds.getWidth() / 2);
                newBounds.setWidth(ICollapseMode.COLLAPSED_DIMENSION.width);

                int newY = bounds.getY();
                int newHeight = bounds.getHeight();
                if (ObservationPoint.notationPredicate().apply(node)) {
                    newY = bounds.getY() + bounds.getHeight() / 2;
                    newHeight = ICollapseMode.COLLAPSED_DIMENSION.height;
                }
                newBounds.setY(newY);
                newBounds.setHeight(newHeight);
                node.setLayoutConstraint(newBounds);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.CollapseUpdater#getExpandedBounds(Node,
     *      DDiagramElement)
     */
    @Override
    public Option<Bounds> getExpandedBounds(Node node, DDiagramElement element) {
        Option<Bounds> optionalBounds;
        if (!specificCollapsePredicate.apply(element)) {
            optionalBounds = super.getExpandedBounds(node, element);
        } else {
            CollapseFilter filter = Iterables.getFirst(Iterables.filter(element.getGraphicalFilters(), CollapseFilter.class), null);
            if (filter != null) {
                optionalBounds = getExpandedBounds(node, new Dimension(filter.getWidth(), filter.getHeight()));
            } else {
                optionalBounds = Options.newNone();
            }
        }
        return optionalBounds;
    }

    /**
     * Get the expanded bounds of this node (the bound of this node must be in
     * collapsed state before calling this method).
     * 
     * @param node
     *            The node to expand.
     * @param expandedSize
     *            The expanded size of this node
     * @return An optional bounds. The bounds can be null. For example, if the
     *         current layout constraint of the <code>node</code> is not a
     *         Bounds.
     */
    public Option<Bounds> getExpandedBounds(Node node, Dimension expandedSize) {
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            Bounds bounds = (Bounds) layoutConstraint;
            Bounds newBounds = NotationFactory.eINSTANCE.createBounds();
            newBounds.setX(bounds.getX() - expandedSize.width / 2);
            int newY = bounds.getY();
            if (ObservationPoint.notationPredicate().apply(node)) {
                newY = bounds.getY() - expandedSize.height / 2;
            }
            newBounds.setY(newY);
            newBounds.setWidth(expandedSize.width);
            newBounds.setHeight(expandedSize.height);
            return Options.newSome(newBounds);
        }
        return Options.newNone();
    }
}
