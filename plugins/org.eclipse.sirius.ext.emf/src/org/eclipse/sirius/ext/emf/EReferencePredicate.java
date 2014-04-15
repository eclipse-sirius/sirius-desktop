/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.emf;

import org.eclipse.emf.ecore.EReference;

/**
 * A simple interface to be used to select/filter an {@link EReference}
 * according to some caller-supplied arbitrary criterion.
 * 
 * Note: this only exists to avoid exposing Guava-specific types, otherwise a
 * simple Predicate could be used.
 * 
 * @author pcdavid
 */
public interface EReferencePredicate {
    /**
     * Applies the predicate to the specified reference.
     * 
     * @param ref
     *            the reference to test.
     * @return the result of the predicate's test.
     */
    boolean apply(EReference ref);
}
