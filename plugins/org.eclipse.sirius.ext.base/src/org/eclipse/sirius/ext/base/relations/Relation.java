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

import java.util.Set;

/**
 * Defines a binary relation between elements of type <code>T</code>.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public interface Relation<T> {
    /**
     * Returns all the elements which are related to the specified element
     * through this relation.
     * 
     * @param from
     *            an element.
     * @return the set of all the elements <code>to</code> such as
     *         <code>(from, to)</code> are in this relation.
     */
    Set<T> apply(T from);
}
