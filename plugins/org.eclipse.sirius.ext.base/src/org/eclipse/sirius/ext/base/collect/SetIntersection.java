/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * The {@link SetIntersection} is able to provide intersection and differences
 * on two sets created without iterating on these sets. You may want to use it
 * if you need the intersection of two sets or the differences and don't want to
 * reach high complexity calling addAll/retainAll.
 * 
 * Both sets are called "old" and "new", you can then call addInOld() to add a
 * new Element in the old set and addInNew() to add an element to the new set.
 * Then the getters will give you the delta's and the intersection.
 * 
 * This implementation leverage the hashcode of the objects, you can be sure the
 * objects collections returned by the getters are the "first added instance"
 * even if consecutive calls with objects having same hascode are done.
 * 
 * @author cbrun
 * 
 * @param <E>
 *            : type contained in the sets.
 */
public class SetIntersection<E> {
    /*
     * Why using maps of hashcode/element instead of a an HashSet ? Because we
     * want to be sure the first element added in newElements for instance, will
     * be the instance added in the keptElements when an new element with the
     * same hashcode is encountered.
     */
    Map<Integer, E> newElements;

    Map<Integer, E> keptElements;

    Map<Integer, E> removedElements;

    /**
     * The duplicates old elements
     */
    List<E> duplicateOldElements;

    // Contains all the elements add with the add InNew method
    Map<Integer, E> allElements;

    /**
     * Create a new {@link SetIntersection}.
     */
    public SetIntersection() {
        newElements = new HashMap<Integer, E>();
        keptElements = new HashMap<Integer, E>();
        removedElements = new HashMap<Integer, E>();
        allElements = new LinkedHashMap<Integer, E>();
        duplicateOldElements = new ArrayList<>();
    }

    /**
     * Add a new element in the old set.
     * 
     * @param newObj
     *            element to add.
     */
    public void addInOld(final E newObj) {
        assert newObj != null;
        final Integer hash = newObj.hashCode();
        if (newElements.containsKey(hash)) {
            final E removed = newElements.remove(hash);
            keptElements.put(hash, removed);
        } else {
            if (removedElements.containsKey(hash)) {
                duplicateOldElements.add(newObj);
            } else {
                removedElements.put(hash, newObj);
            }
        }

    }

    /**
     * Add a new element in the new set.
     * 
     * @param newObj
     *            element to add.
     */
    public void addInNew(final E newObj) {
        assert newObj != null;
        final Integer hash = newObj.hashCode();
        if (removedElements.containsKey(hash)) {
            final E removed = removedElements.remove(hash);
            keptElements.put(hash, removed);
            allElements.put(hash, removed);
        } else {
            /*
             * This extra check is needed if you addInNew several times identic
             * objects.
             */
            if (!keptElements.containsKey(hash)) {
                newElements.put(hash, newObj);
                allElements.put(hash, newObj);
            }
        }

    }

    /**
     * Return the new - old difference.
     * 
     * @return the new - old difference.
     */
    public Collection<E> getNewElements() {
        return newElements.values();
    }

    /**
     * Return the old/new intersection.
     * 
     * @return the old/new intersection.
     */
    public Iterable<E> getKeptElements() {
        return keptElements.values();
    }

    /**
     * Return the old - new difference.
     * 
     * @return the old - new difference.
     */
    public Iterable<E> getRemovedElements() {
        Collection<E> result = Lists.newArrayList(removedElements.values());
        if (duplicateOldElements != null && !duplicateOldElements.isEmpty()) {
            result.addAll(duplicateOldElements);
        }
        return result;
    }

    /**
     * Return all the elements adding to this list.
     * 
     * @return the all
     */
    public Iterable<E> getAllElements() {
        return allElements.values();
    }

}
