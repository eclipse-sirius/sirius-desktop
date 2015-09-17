/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure.svg;

import java.util.Set;

import org.eclipse.swt.graphics.Image;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Cache of pre-rendered images.
 * 
 * @author pcdavid
 */
public class ImageCache {
    /**
     * The rendered bitmaps, organized by key..
     */
    private final Cache<String, Image> images = CacheBuilder.newBuilder().softValues().build();

    /**
     * Returns the bitmap associated to the specified key, or <code>null</code>
     * if there is none.
     * 
     * @param key
     *            the image key.
     * @return the bitmap associated to the specified key.
     */
    public Image getIfPresent(String key) {
        return images.getIfPresent(key);
    }

    /**
     * Returns the set of keys for which we have cached bitmaps.
     * 
     * @return the set of keys for which we have cached bitmaps.
     */
    public Set<String> keySet() {
        return images.asMap().keySet();
    }

    /**
     * Associate a rendered bitmap to a key.
     * 
     * @param key
     *            the key.
     * @param img
     *            the rendered bitmap.
     */
    public void put(String key, Image img) {
        images.put(key, img);
    }

    /**
     * Remove the bitmap associated to the specified key from the cache, if any.
     * 
     * @param key
     *            the key.
     */
    public void invalidate(String key) {
        images.invalidate(key);
    }
}
