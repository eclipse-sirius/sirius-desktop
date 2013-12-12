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
package org.eclipse.sirius.ext.base.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Iterators;

/**
 * This class is an utility wrapper to enhance performances, calling addAll with
 * a collection will not really add every elements but just chain the new
 * collection to the MultipleCollection. Then iterating through the iterator()
 * call will browsed all the chained collections. <br>
 * <b>Use with care ! Half of the methods you could need and we should provide
 * because of the {@link Collection} interface will simply throw an
 * {@link UnsupportedOperationException}. Make sure you never return such a
 * collection to a client as most of the operations will fail !</b>
 * 
 * @author cbrun
 * 
 * @param <E>
 *            types to contains in the collection.
 */
public class MultipleCollection<E> implements Collection<E> {

    private final Collection<Collection<? extends E>> sets = new ArrayList<Collection<? extends E>>();

    /**
     * Create a new MultipleCollection.
     * 
     */
    public MultipleCollection() {
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean add(final E arg0) {
        if (!sets.iterator().hasNext()) {
            sets.add(new ArrayList<E>());
        }
        final Collection firstCollection = sets.iterator().next();
        return firstCollection.add(arg0);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean addAll(final Collection<? extends E> arg0) {
        sets.add(arg0);
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void clear() {
        sets.clear();

    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean contains(final Object arg0) {
        boolean contained = false;
        for (final Collection<? extends E> col : sets) {
            if (col.contains(arg0)) {
                contained = true;
            }
        }
        return contained;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean containsAll(final Collection<?> arg0) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        for (final Collection<? extends E> col : sets) {
            if (!col.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Iterator<E> iterator() {
        if (sets.size() > 0) {
            final List<Iterator<? extends E>> iterators = new ArrayList<Iterator<? extends E>>();
            for (final Collection<? extends E> col : sets) {
                iterators.add(col.iterator());
            }
            return Iterators.concat(iterators.iterator());
        } else {
            return new ArrayList().iterator();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean remove(final Object arg0) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean removeAll(final Collection<?> arg0) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean retainAll(final Collection<?> arg0) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int size() {
        int size = 0;
        for (final Collection<? extends E> col : sets) {
            size += col.size();
        }
        return size;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Object[] toArray() {

        int size = 0;
        for (final Collection<? extends E> collection : sets) {
            size += collection.size();
        }
        final Object[] array = new Object[size];
        int i = 0;
        for (final Collection<? extends E> collection : sets) {
            final Object[] subArray = collection.toArray();
            for (int j = 0; j < subArray.length; j++) {
                array[i + j] = subArray[j];
            }
            i++;
        }
        return array;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public <T> T[] toArray(final T[] arg0) {
        throw new UnsupportedOperationException();
    }

}
