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

import java.util.List;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * This implementation of the {@link SetIntersection} is using Google Collection
 * to retrieve the sets intersections and as such should be a lot quicker than
 * the original {@link SetIntersection}.
 * 
 * @author cbrun
 * 
 * @param <E>
 */
public class GSetIntersection<E> extends SetIntersection<E> {
    /**
     * The new elements.
     */
    private Set<E> newElements = Sets.newLinkedHashSet();

    /**
     * the old elements indexed by hashcode, we need to duplicate this info to
     * be able to always return the old instances when needed.
     */
    private BiMap<Integer, E> oldElements = HashBiMap.create();

    /**
     * list used only elements which have several equivalents in "old".
     */
    private List<E> extraElementsToDelete = Lists.newArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInNew(final E newObj) {
        newElements.add(newObj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInOld(final E oldObj) {
        E previouslyStored = oldElements.put(oldObj.hashCode(), oldObj);
        if (previouslyStored != null) {
            /*
             * the element was there already we need to keep it around to delete
             * it later on.
             */
            extraElementsToDelete.add(oldObj);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllElements() {
        /* keep the order of new elements */
        List<E> intersection = Lists.newArrayList();
        for (E newElement : newElements) {
            E alreadyThere = oldElements.get(newElement.hashCode());
            if (alreadyThere != null) {
                intersection.add(alreadyThere);
            } else {
                intersection.add(newElement);
            }
        }
        return intersection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getKeptElements() {
        /* keep the order of new elements */
        List<E> intersection = Lists.newArrayList();
        for (E newElement : newElements) {
            E alreadyThere = oldElements.get(newElement.hashCode());
            if (alreadyThere != null) {
                intersection.add(alreadyThere);
            }
        }
        return intersection;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getRemovedElements() {
        return Iterables.concat(extraElementsToDelete, Sets.difference(oldElements.values(), newElements));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<E> getNewElements() {
        return Sets.difference(newElements, oldElements.values());

    }

}
