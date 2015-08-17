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
import java.util.Collections;
import java.util.Set;

import org.eclipse.sirius.ext.base.Messages;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * The transitive closure of a given {@link Relation}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class TransitiveClosure<T> implements Relation<T> {
    private final Relation<T> relation;

    /**
     * Constructor.
     * 
     * @param relation
     *            the relation for which to compute the transitive closure.
     */
    public TransitiveClosure(Relation<T> relation) {
        this.relation = Preconditions.checkNotNull(relation);
    }

    @Override
    public Set<T> apply(T from) {
        Preconditions.checkNotNull(from);
        Set<T> result = Sets.newHashSet(relation.apply(from));

        Set<T> startingPoints = ImmutableSet.copyOf(result);
        while (!startingPoints.isEmpty()) {
            Set<T> newDependencies = Sets.newHashSet();
            for (T startPoint : startingPoints) {
                newDependencies.addAll(relation.apply(startPoint));
            }
            newDependencies.removeAll(result);
            result.addAll(newDependencies);
            startingPoints = newDependencies;
        }

        return Collections.unmodifiableSet(result);
    }

    @Override
    public String toString() {
        return MessageFormat.format(Messages.TransitiveClosure_message, relation);
    }
}
