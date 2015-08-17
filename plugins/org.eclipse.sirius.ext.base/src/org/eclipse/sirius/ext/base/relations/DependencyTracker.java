/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.base.relations;

import java.text.MessageFormat;
import java.util.Set;

import org.eclipse.sirius.ext.base.Messages;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * Tracks the dependencies and reverse dependencies from a given set of elements
 * of type <code>T</code>.
 * 
 * @param <T>
 *            the type of elements to keep track of.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class DependencyTracker<T> {
    /**
     * The relation which defines the direct dependency: from an element to the
     * set of elements it depends on.
     */
    private final Relation<T> relation;

    /**
     * We must keep the set of tracked elements separately to handle elements
     * which have no dependencies: these would not appear in
     * <code>dependencies.keySet()</code>.
     */
    private final Set<T> trackedElements = Sets.newHashSet();

    /**
     * From an element to the elements it depends on.
     */
    private final Multimap<T, T> dependencies = HashMultimap.create();

    /**
     * From an element to the elements which depend on it.
     */
    private final Multimap<T, T> reverseDependencies = HashMultimap.create();

    /**
     * Constructor.
     * 
     * @param relation
     *            the relation to track.
     */
    public DependencyTracker(Relation<T> relation) {
        this.relation = Preconditions.checkNotNull(relation);
    }

    /**
     * Returns the set of all root elements whose dependencies are tracked.
     * 
     * @return the elements whose dependencies are tracked.
     */
    public Set<T> getTrackedElements() {
        return ImmutableSet.copyOf(trackedElements);
    }

    /**
     * Returns the set of elements the specified element depends on.
     * 
     * @param element
     *            a tracked element.
     * @return the set of elements it depends on.
     */
    public Set<T> getDependencies(T element) {
        Preconditions.checkNotNull(element);
        Preconditions.checkArgument(trackedElements.contains(element), MessageFormat.format(Messages.DependencyTracker_error_untrackedElement, element));
        return ImmutableSet.copyOf(dependencies.get(element));
    }

    /**
     * Returns all the elements which depend on the specified one.
     * 
     * @param element
     *            an element, which may not be a tracked element.
     * @return the set of all elements (some of which may not be tracked), which
     *         depend on the specified one.
     */
    public Set<T> getReverseDependencies(T element) {
        Preconditions.checkNotNull(element);
        return ImmutableSet.copyOf(reverseDependencies.get(element));
    }

    /**
     * Add the specified element to the set of elements to track. If the element
     * is already tracked, triggers an {@link #update(T) update} of its
     * information instead.
     * 
     * @param element
     *            the element to add.
     */
    public synchronized void add(T element) {
        Preconditions.checkNotNull(element);
        if (dependencies.containsKey(element)) {
            update(element);
        } else {
            dependencies.putAll(element, relation.apply(element));
            for (T dependency : dependencies.get(element)) {
                reverseDependencies.put(dependency, element);
            }
            trackedElements.add(element);
        }
    }

    /**
     * Remove the specified element from the set of elements to track. Does
     * nothing if the element is not already tracked.
     * 
     * @param element
     *            the element to add.
     */
    public synchronized void remove(T element) {
        if (!dependencies.containsKey(element)) {
            return;
        }
        for (T dependency : dependencies.get(element)) {
            reverseDependencies.remove(dependency, element);
        }
        dependencies.removeAll(element);
        trackedElements.remove(element);
    }

    /**
     * Update the dependencies information for the specified element. If the
     * element is not already tracked, this is equivalent to
     * {@link #add(Object) adding} it to the tracked set.
     * 
     * @param element
     *            the tracked element whose dependencies should be updated.
     */
    public synchronized void update(T element) {
        Preconditions.checkNotNull(element);
        remove(element);
        add(element);
    }
}
