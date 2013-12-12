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

import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * The union of two or more relations.
 * 
 * @param <T>
 *            the type of the elements in the relation.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class UnionRelation<T> implements Relation<T> {
    private final Set<Relation<T>> baseRelations;

    /**
     * Constructor.
     * 
     * @param baseRelations
     *            the relations to consider in the union.
     */
    public UnionRelation(Relation<T>... baseRelations) {
        this.baseRelations = ImmutableSet.copyOf(Iterators.forArray(Preconditions.checkNotNull(baseRelations)));
    }

    /**
     * {@inheritDoc}
     */
    public Set<T> apply(T from) {
        Set<T> result = Sets.newHashSet();
        for (Relation<T> rel : baseRelations) {
            result.addAll(rel.apply(from));
        }
        return ImmutableSet.copyOf(result);
    }
}
