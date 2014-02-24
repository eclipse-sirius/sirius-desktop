/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.ordering;

import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramUtil;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingProvider;

/**
 * A tree ordered view ordering {@link ViewOrdering} provider.
 * 
 * @author mchauvin
 */
public class OrderedTreeViewOrderingProvider implements ViewOrderingProvider {

    /** the ordering. */

    private Map<OrderedTreeLayout, OrderedTreeOrdering> orderings = new WeakHashMap<OrderedTreeLayout, OrderedTreeOrdering>();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrderingProvider#getViewOrdering(org.eclipse.sirius.viewpoint.description.DiagramElementMapping)
     */
    public ViewOrdering getViewOrdering(final DiagramElementMapping mapping) {

        if (mapping instanceof AbstractNodeMapping) {
            final AbstractNodeMapping nodeMapping = (AbstractNodeMapping) mapping;
            final DiagramDescription desc = SiriusDiagramUtil.findDiagramDescription(nodeMapping);
            final Layout layout = desc.getLayout();
            if (layout instanceof OrderedTreeLayout) {
                if (((OrderedTreeLayout) layout).getNodeMapping().contains(mapping)) {
                    return getOrdering((OrderedTreeLayout) layout);
                }
            }
        }
        return null;
        /*
         * if (provides(mapping)) return getOrdering((NodeMapping)mapping);
         * return null;
         */
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrderingProvider#provides(org.eclipse.sirius.viewpoint.description.DiagramElementMapping)
     */
    public boolean provides(final DiagramElementMapping mapping) {
        if (mapping instanceof AbstractNodeMapping) {

            final AbstractNodeMapping nodeMapping = (AbstractNodeMapping) mapping;
            final DiagramDescription desc = SiriusDiagramUtil.findDiagramDescription(nodeMapping);
            final Layout layout = desc.getLayout();
            if (layout instanceof OrderedTreeLayout) {
                if (((OrderedTreeLayout) layout).getNodeMapping().contains(mapping)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return the ordering.
     * 
     * @return the ordering.
     */
    private OrderedTreeOrdering getOrdering(final OrderedTreeLayout layout) {

        if (orderings.containsKey(layout)) {
            return orderings.get(layout);
        }

        final OrderedTreeOrdering ordering = new OrderedTreeOrdering(layout);
        ordering.setUserAwareCapable(true);
        orderings.put(layout, ordering);
        return ordering;
    }
}
