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

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.BiMap;

/**
 * Matches elements from two physically different models as logically
 * equivalent.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public interface Matcher {
    /**
     * Tests whether two elements, which may be different Java instances,
     * represent the same logical elements (in the context of the matcher).
     * 
     * @param obj1
     *            the first element.
     * @param obj2
     *            the second element.
     * @return <code>true</code> if both EObject represent the same logical
     *         element.
     */
    boolean areSameLogicalElement(EObject obj1, EObject obj2);

    /**
     * Compute matching pairs of logically equivalent objects from the two
     * specified lists.
     * 
     * @param values1
     *            a list of {@link EObject}s.
     * @param values2
     *            a list of {@link EObject}s.
     * @return for each element e1 of list1 for which there is a logically
     *         equivalent element e2 in values2, an entry in the map exists from
     *         e1 to e2.
     */
    BiMap<EObject, EObject> computeMatches(Collection<EObject> values1, Collection<EObject> values2);
}
