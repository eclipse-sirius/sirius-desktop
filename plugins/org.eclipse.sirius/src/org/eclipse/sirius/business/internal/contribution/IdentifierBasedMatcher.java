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
package org.eclipse.sirius.business.internal.contribution;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

/**
 * A matcher that uses logical identifiers to match elements from two different
 * models.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class IdentifierBasedMatcher implements Matcher {
    private final Function<EObject, Object> idFunction;

    /**
     * Constructor.
     * 
     * @param idFunction
     *            the function to use to obtain the identifier from a model
     *            element.
     */
    public IdentifierBasedMatcher(Function<EObject, Object> idFunction) {
        this.idFunction = Preconditions.checkNotNull(idFunction);
    }

    /**
     * {@inheritDoc}
     */
    public boolean areSameLogicalElement(EObject obj1, EObject obj2) {
        Object id1 = idFunction.apply(Preconditions.checkNotNull(obj1));
        Object id2 = idFunction.apply(Preconditions.checkNotNull(obj2));
        return Objects.equal(id1, id2);
    }

    /**
     * {@inheritDoc}
     */
    public BiMap<EObject, EObject> computeMatches(Collection<EObject> values1, Collection<EObject> values2) {
        BiMap<EObject, EObject> result = HashBiMap.create();
        Map<Object, EObject> matchingElements = computeIds(values2);
        for (EObject obj : values1) {
            Object id1 = idFunction.apply(obj);
            EObject match = matchingElements.get(id1);
            if (match != null && values2.contains(match)) {
                result.put(obj, match);
            }
        }
        return result;
    }

    private Map<Object, EObject> computeIds(Iterable<EObject> elements) {
        Map<Object, EObject> result = Maps.newHashMap();
        for (EObject obj : elements) {
            result.put(idFunction.apply(obj), obj);
        }
        return result;
    }
}
