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
package org.eclipse.sirius.diagram.ui.tools.internal.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

import com.google.common.base.Predicate;

/**
 * An AdapterFactoryContentProvider which filters some of its children properly,
 * i.e. if all the children of an elements are filtered, hasChildren() return
 * false, thus avoiding erroneous "+" buttons in tree viewers.
 * 
 * @author pcdavid
 */
public final class FilteredTreeContentProvider extends AdapterFactoryContentProvider {
    private final Predicate<Object> predicate;

    /**
     * Constructor.
     * 
     * @param adapterFactory
     *            the adapter factory to use.
     * @param predicate
     *            the predicate used to filter elements. It should return
     *            <code>true</code> for elements which must be removed.
     */
    public FilteredTreeContentProvider(AdapterFactory adapterFactory, Predicate<Object> predicate) {
        super(adapterFactory);
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getChildren(Object object) {
        Object[] unfiltered = super.getChildren(object);
        return filter(unfiltered);
    }
    
    @Override
    public Object[] getElements(Object object) {
        Object[] unfiltered = super.getElements(object);
        return filter(unfiltered);
    }
    
    private Object[] filter(Object[] unfiltered) {
        List<Object> filtered = new ArrayList<>();
        for (Object o : unfiltered) {
            if (predicate.apply(o)) {
                filtered.add(o);
            }
        }
        return filtered.toArray(new Object[filtered.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChildren(Object object) {
        Object[] children = getChildren(object);
        return children != null && (children.length != 0);
    }
}
