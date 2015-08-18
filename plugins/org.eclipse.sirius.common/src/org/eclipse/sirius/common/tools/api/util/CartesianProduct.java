/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EObject;

/**
 * The {@link CartesianProduct} is useful to browse all the lists combinations.
 * 
 * 
 * @author cbrun
 * 
 */
public class CartesianProduct implements Iterable<EObjectCouple>, Iterator<EObjectCouple> {

    private Collection<?> set2;

    private Iterator<?> it1;

    private Iterator<?> it2;

    private Object obj1;

    private RefreshIdsHolder ids;

    /**
     * Build an iterator providing a cartesian product of two collections.
     * 
     * @param set1
     *            first set
     * @param set2
     *            second set
     * @param ids
     *            the holder of refresh ids.
     */
    public CartesianProduct(final Collection<?> set1, final Collection<?> set2, RefreshIdsHolder ids) {
        this.set2 = set2;
        it1 = set1.iterator();
        this.ids = ids;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasNext() {
        if (it2 == null && set2.size() == 0) {
            return false;
        }
        return obj1 != null || it1.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    public EObjectCouple next() {
        if (it1 != null || it2 == null && !it1.hasNext()) {
            if (obj1 == null) {
                obj1 = it1.next();
            }
            Object obj2 = null;
            if (it2 == null) {
                it2 = set2.iterator();
            }
            obj2 = it2.next();
            final EObjectCouple couple = new EObjectCouple((EObject) obj1, (EObject) obj2, ids);
            if (!it2.hasNext()) {
                obj1 = null;
                it2 = null;
            }
            return couple;
        }
        throw new NoSuchElementException();
    }

    /**
     * Remove the iterator value from the list. {@inheritDoc} throw
     * UnsupportedOperationException
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProductIterator"; //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<EObjectCouple> iterator() {
        return this;
    }

}
