/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * An {@link org.eclipse.emf.ecore.EObject} instance which wraps a
 * {@link Collection} instance.
 * 
 * @author mchauvin
 */
public class EObjectCollectionWrapper extends EObjectImpl {

    private Collection<?> wrappedCollection;

    /**
     * Constructor.
     * 
     * @param c
     *            the wrapped collection.
     */
    public EObjectCollectionWrapper(final Collection<?> c) {
        super();
        wrappedCollection = c;
    }

    /**
     * Get the wrapped collection.
     * 
     * @return the wrapped collection.
     */
    public Collection<?> getCollection() {
        return wrappedCollection;
    }

}
