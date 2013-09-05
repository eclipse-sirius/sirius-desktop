/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.gmf.runtime.notation.View;

/**
 * A singleton that provides {@link ViewOrdering}.
 * 
 * @author ymortier
 */
public final class ViewOrderingHint {

    /** The shared instance. */
    private static ViewOrderingHint instance = new ViewOrderingHint();

    /** All view ordering. */
    private Map<View, ViewOrdering> viewOrderingsStock;

    /** All edge view ordering. */
    private Map<View, AbstractEdgeViewOrdering> edgeViewOrderingsStock;

    /**
     * Avoid instantiation from external.
     */
    private ViewOrderingHint() {
        this.viewOrderingsStock = new WeakHashMap<View, ViewOrdering>();
        this.edgeViewOrderingsStock = new WeakHashMap<View, AbstractEdgeViewOrdering>();
    }

    /**
     * Return the shared instance.
     * 
     * @return the shared instance.
     */
    public static ViewOrderingHint getInstance() {
        return instance;
    }

    /**
     * Put a new {@link ViewOrdering}.
     * 
     * @param view
     *            the container.
     * @param viewOrdering
     *            the view ordering.
     */
    public void putViewOrdering(final View view, final ViewOrdering viewOrdering) {
        final ViewOrdering oldOrdering = this.viewOrderingsStock.get(view);
        if (oldOrdering == null) {
            this.viewOrderingsStock.put(view, viewOrdering);
        } else if (oldOrdering instanceof SimpleCompositeViewOrdering) {
            final SimpleCompositeViewOrdering compositeViewOrdering = (SimpleCompositeViewOrdering) oldOrdering;
            compositeViewOrdering.addViewOrdering(viewOrdering);
        } else if (oldOrdering != viewOrdering) {
            final SimpleCompositeViewOrdering compositeViewOrdering = new SimpleCompositeViewOrdering();
            compositeViewOrdering.addViewOrdering(oldOrdering);
            compositeViewOrdering.addViewOrdering(viewOrdering);
            this.viewOrderingsStock.put(view, compositeViewOrdering);
        }
    }

    /**
     * Put a new {@link AbstractEdgeViewOrdering}.
     * 
     * @param view
     *            the container.
     * @param edgeViewOrdering
     *            the view ordering.
     */
    public void putEdgeViewOrdering(final View view, final AbstractEdgeViewOrdering edgeViewOrdering) {
        final ViewOrdering oldOrdering = this.edgeViewOrderingsStock.get(view);
        if (oldOrdering == null) {
            this.edgeViewOrderingsStock.put(view, edgeViewOrdering);
        } else if (oldOrdering instanceof SimpleCompositeEdgeViewOrdering) {
            final SimpleCompositeEdgeViewOrdering compositeViewOrdering = (SimpleCompositeEdgeViewOrdering) oldOrdering;
            compositeViewOrdering.addEdgeViewOrdering(edgeViewOrdering);
        } else if (oldOrdering instanceof AbstractEdgeViewOrdering) {
            final SimpleCompositeEdgeViewOrdering compositeViewOrdering = new SimpleCompositeEdgeViewOrdering();
            compositeViewOrdering.addEdgeViewOrdering((AbstractEdgeViewOrdering) oldOrdering);
            compositeViewOrdering.addEdgeViewOrdering(edgeViewOrdering);
            this.edgeViewOrderingsStock.put(view, compositeViewOrdering);
        }
    }

    /**
     * Return the view ordering to use by the specified container edit part.
     * 
     * @param view
     *            the edit part.
     * @return the view ordering to use by the specified container edit part.
     */
    public ViewOrdering consumeViewOrdering(final View view) {
        final ViewOrdering result = this.viewOrderingsStock.get(view);
        this.viewOrderingsStock.remove(view);
        return result;
    }

    /**
     * Return the edge view ordering to use by the specified container edit
     * part.
     * 
     * @param view
     *            the edit part.
     * @return the edge view ordering to use by the specified container edit
     *         part.
     */
    public AbstractEdgeViewOrdering consumeEdgeViewOrdering(final View view) {
        final AbstractEdgeViewOrdering result = this.edgeViewOrderingsStock.get(view);
        this.edgeViewOrderingsStock.remove(view);
        return result;
    }

    /**
     * Removes all registered Hints for the given {@link View}.
     * 
     * @param view
     *            the {@link View} to remove all registered Hints from
     */
    public void removeAllHints(View view) {
        removeHints(view);
    }

    /**
     * Removes all registered Hints for the given View and all its children.
     * 
     * @param view
     *            the view to remove the registered Hints from
     */
    private void removeHints(View view) {
        if (this.viewOrderingsStock.get(view) != null) {
            this.viewOrderingsStock.remove(view);
        }
        if (this.edgeViewOrderingsStock.get(view) != null) {
            this.edgeViewOrderingsStock.remove(view);
        }
        for (Object child : view.getChildren()) {
            if (child instanceof View) {
                removeHints((View) child);
            }
        }
    }

}
