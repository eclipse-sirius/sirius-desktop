/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.util.Collection;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.swt.graphics.Image;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

/**
 * A {@link AbstractCachedSVGFigure} is a {@link SVGFigure} corresponding to a
 * svg image. {@link Image} are store in a map with soft values.
 *
 * @author mporhel
 */
public abstract class AbstractCachedSVGFigure extends SVGFigure {
    /**
     * Cache of pre-rendered images.
     */
    private static class ImageCache {
        /**
         * The rendered bitmaps, organized by key..
         */
        private final Cache<String, Image> images = CacheBuilder.newBuilder().softValues().build();

        /**
         * Get the image cached or create new one and cache it.
         *
         * @param key
         *            the key
         * @param clientArea
         *            the client area
         * @param graphics
         *            the graphical context
         * @return an image store in a cache
         */
        public Image getImage(SVGFigure fig, Rectangle clientArea, Graphics graphics) {
            String key = fig.getKey(graphics);
            Image result = images.getIfPresent(key);
            if (result == null) {
                result = render(fig, clientArea, graphics);
                if (result != null) {
                    images.put(key, result);
                }
            }
            return result;
        }

        /**
         * Remove all entries whose key begins with the given key. Remove from
         * the document map, the entries with the given keys to force to re-read
         * the file.
         *
         * @param documentKey
         *            the document key.
         * @return true of something was removed.
         */
        public boolean doRemoveFromCache(String documentKey) {
            if (!StringUtil.isEmpty(documentKey)) {
                boolean remove = false;
                Collection<String> keyToRemove = Lists.newArrayList();
                for (String key : images.asMap().keySet()) {
                    if (key.startsWith(documentKey)) {
                        keyToRemove.add(key);
                    }
                }

                for (String toRemove : keyToRemove) {
                    images.invalidate(toRemove);
                    remove = true;
                }
                return remove;
            }
            return false;
        }
    }

    /**
     * Cache to store bitmaps of rendered SVGs.
     */
    private static final ImageCache CACHE = new ImageCache();

    /**
     * Get the image cached or create new one and cache it.
     *
     * @param clientArea
     *            the client area
     * @param graphics
     *            the graphical context
     * @return an image store in a cache
     */
    @Override
    protected Image getImage(Rectangle clientArea, Graphics graphics) {
        return CACHE.getImage(this, clientArea, graphics);
    }

    /**
     * Remove all entries whose key begins with the given key. Remove from the
     * document map, the entries with the given keys to force to re-read the
     * file.
     *
     * @param documentKey
     *            the document key.
     * @return true of something was removed.
     */
    protected static boolean doRemoveFromCache(final String documentKey) {
        if (!StringUtil.isEmpty(documentKey)) {
            return CACHE.doRemoveFromCache(documentKey) || SVGFigure.documentsMap.remove(documentKey) != null;
        }
        return false;
    }

}
