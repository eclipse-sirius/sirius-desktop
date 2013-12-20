/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.swt;

import org.eclipse.sirius.ext.base.cache.LRUCache;
import org.eclipse.swt.graphics.Resource;

/**
 * A cache to store SWT Resources, and dispose the last Resource used when the
 * cache is full.
 * 
 * @author mchauvin
 * 
 * @param <K>
 * @param <V>
 */
public class SWTResourceLRUCache<K, V extends Resource> extends LRUCache<K, V> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param initialSize
     *            the initial size.
     * @param maxSize
     *            the maximum size.
     */
    public SWTResourceLRUCache(final int initialSize, final int maxSize) {
        super(initialSize, maxSize);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ext.base.collect.LRUCache#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(final java.util.Map.Entry<K, V> eldest) {
        if (super.removeEldestEntry(eldest)) {
            /*
             * if the entry will be removed then we dispose the resource
             */
            eldest.getValue().dispose();
            return true;
        }
        return false;
    }

}
