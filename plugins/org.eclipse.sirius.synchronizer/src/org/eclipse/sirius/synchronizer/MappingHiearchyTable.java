/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * This class represents a set of hierarchy. It allows for the computation of
 * every hierarchies based on the given mapping and their individual
 * relationships.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class MappingHiearchyTable {

    private Multimap<Mapping, MappingHiearchy> hiearchies = HashMultimap.create();

    /**
     * Returns a list of MappingHierachy corresponding to the hierarchy of the
     * given Mapping.
     * 
     * @param from
     *            the mapping to get the hierarchy from
     * @return the hierarchy of the given mapping
     */
    public Collection<MappingHiearchy> getHierarchy(Mapping from) {
        return hiearchies.get(from);
    }

    /**
     * Computes the hierarchy of all given mappings.
     * 
     * @param mappings
     *            a collection of Mappings to compute the hierarchy from
     */
    public void compute(Collection<? extends Mapping> mappings) {
        Collection<? extends Mapping> leaves = getLeaves(mappings);
        for (Mapping mapping : leaves) {
            MappingHiearchy newHierarchy = new MappingHiearchy(mapping);
            hiearchies.put(mapping, newHierarchy);
            Iterator<Mapping> it = newHierarchy.fromMostSpecificToMostGeneral();
            while (it.hasNext()) {
                Mapping superMapping = it.next();
                hiearchies.put(superMapping, newHierarchy);
            }

        }
    }

    private Collection<? extends Mapping> getLeaves(Collection<? extends Mapping> mappings) {
        Set<Mapping> hasChildren = Sets.newLinkedHashSet();
        for (Mapping mapping : mappings) {
            if (mapping.getSuper().some()) {
                hasChildren.add(mapping.getSuper().get());
            }
        }
        return Sets.difference(Sets.newLinkedHashSet(mappings), hasChildren);
    }
}
