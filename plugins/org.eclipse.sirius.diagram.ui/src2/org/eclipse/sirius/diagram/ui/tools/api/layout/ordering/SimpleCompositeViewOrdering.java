/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.gmf.runtime.notation.View;

/**
 * A simple composite view ordering.
 * 
 * @author ymortier
 */
public class SimpleCompositeViewOrdering extends AbstractViewOrdering {

    /** All view orderings. */
    private List<ViewOrdering> viewOrderings = new LinkedList<ViewOrdering>();

    /**
     * Adds a new {@link ViewOrdering}.
     * 
     * @param viewOrdering
     *            the {@link ViewOrdering} to add.
     */
    public void addViewOrdering(final ViewOrdering viewOrdering) {
        this.viewOrderings.add(viewOrdering);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractViewOrdering#sortViews(java.util.List)
     */
    @Override
    protected List<View> sortViews(final List<View> views) {
        //
        // First populate a map :
        // - ViewOrdering -> List of views to sort.
        final Iterator<View> iterAllViews = views.iterator();
        final Map<ViewOrdering, List<View>> viewOrderingsToViews = new HashMap<ViewOrdering, List<View>>();
        final List<View> nullViewOrdering = new LinkedList<View>();
        while (iterAllViews.hasNext()) {
            final View currentView = iterAllViews.next();
            final ViewOrdering viewOrdering = this.getViewOrderingFor(currentView);
            if (viewOrdering == null) {
                nullViewOrdering.add(currentView);
            } else {
                List<View> currentViews = viewOrderingsToViews.get(viewOrdering);
                if (currentViews == null) {
                    currentViews = new LinkedList<View>();
                    viewOrderingsToViews.put(viewOrdering, currentViews);
                }
                currentViews.add(currentView);
            }
        }
        //
        // Second, sort all views.
        final List<View> sortedViews = new LinkedList<View>();
        final Iterator<ViewOrdering> iterViewOrderings = this.viewOrderings.listIterator();
        while (iterViewOrderings.hasNext()) {
            final ViewOrdering currentViewOrdering = iterViewOrderings.next();
            final List<View> correspondingViews = viewOrderingsToViews.get(currentViewOrdering);
            if (correspondingViews != null) {
                currentViewOrdering.setViews(correspondingViews);
                sortedViews.addAll(currentViewOrdering.getSortedViews());
            }
        }
        //
        // Third, Append all views that have no ViewOrdering
        sortedViews.addAll(nullViewOrdering);
        return sortedViews;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#isAbleToManageView(org.eclipse.gmf.runtime.notation.View)
     */
    public boolean isAbleToManageView(final View view) {
        return getViewOrderingFor(view) != null;
    }

    /**
     * Return the first {@link ViewOrdering} that is able to manage the
     * specified view or <code>null</code> if no {@link ViewOrdering} is
     * available for the view.
     * 
     * @param view
     *            the view to manage.
     * @return the first {@link ViewOrdering} that is able to manage the
     *         specified view or <code>null</code> if no {@link ViewOrdering} is
     *         available for the view.
     */
    protected ViewOrdering getViewOrderingFor(final View view) {
        final Iterator<ViewOrdering> iterViewOrderings = this.viewOrderings.listIterator();
        while (iterViewOrderings.hasNext()) {
            final ViewOrdering currentViewOrdering = iterViewOrderings.next();
            if (currentViewOrdering.isAbleToManageView(view)) {
                return currentViewOrdering;
            }
        }
        return null;
    }

}
