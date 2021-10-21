/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.base.relations;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;

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
        this.baseRelations = ImmutableSet.copyOf(Iterators.forArray(Objects.requireNonNull(baseRelations)));
    }

    @Override
    public Set<T> apply(T from) {
        Set<T> result = new HashSet<>();
        for (Relation<T> rel : baseRelations) {
            result.addAll(rel.apply(from));
        }
        return ImmutableSet.copyOf(result);
    }
}
