/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.emf;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterators;

/**
 * Adapter class to treat an <code>EObject</code> as an <code>Iterable</code> on
 * its direct contents.
 * <p>
 * Usage:
 * 
 * <pre>
 * for (EObject obj : Contents.of(root)) {
 *     // code
 * }
 * </pre>
 * 
 * @author pcdavid
 */
public final class Contents implements Iterable<EObject> {
    private final EObject root;

    /**
     * Constructor.
     * 
     * @param root
     *            the root element.
     */
    public Contents(EObject root) {
        this.root = root;
    }

    /**
     * Factory method.
     * 
     * @param obj
     *            the root element.
     * @return an iterable on all the direct sub-elements of the root.
     */
    public static Iterable<EObject> of(EObject obj) {
        return new Contents(obj);
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<EObject> iterator() {
        return (root != null) ? root.eContents().iterator() : Iterators.<EObject> emptyIterator();
    }
}
