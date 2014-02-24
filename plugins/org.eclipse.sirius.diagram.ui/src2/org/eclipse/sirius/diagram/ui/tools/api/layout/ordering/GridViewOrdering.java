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

import java.util.Collection;

import org.eclipse.gmf.runtime.notation.View;

/**
 * An ordering that is able to order views into a grid.
 * 
 * @author ymortier
 */
public interface GridViewOrdering {

    /**
     * Returns the views sorted by this ordering.
     * 
     * @return the views sorted by this ordering.
     */
    GridView getSortedViewsAsGrid();

    /**
     * Returns <code>true</code> if this {@link ViewOrdering} is able to manage
     * the specified {@link View}.
     * 
     * @param view
     *            the view to check.
     * @return <code>true</code> if this {@link ViewOrdering} is able to manage
     *         the specified {@link View}.
     */
    boolean isAbleToManageView(View view);

    /**
     * Set the list of views to sort.
     * 
     * @param views
     *            the list of views to sort.
     * @param <T>
     *            class which extends View
     */
    <T extends View> void setViews(Collection<T> views);

}
