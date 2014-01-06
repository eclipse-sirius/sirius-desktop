/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.base.cache;

import java.util.LinkedHashMap;

/**
 * This class is a itself a debate but I am pretty sure you
 * will find it useful one day or another.
 * 
 * @author mchauvin
 * @param <K>
 *            the class for the key
 * @param <V>
 *            the class for the value
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    /**
     * The serial version id.
     */
    private static final long serialVersionUID = 1L;

    private int maxSize;

    /**
     * Default constructor.
     * 
     * @param initialSize
     *            the initial size of the cache.
     * @param maxSize
     *            the maximum size of the cache
     */
    public LRUCache(final int initialSize, final int maxSize) {
        super(initialSize);
        this.maxSize = maxSize;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(final java.util.Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }
}
