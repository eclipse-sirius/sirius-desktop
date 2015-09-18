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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.ImageCache;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

/**
 * A {@link AbstractCachedSVGFigure} is a {@link SVGFigure} corresponding to a
 * svg image. {@link Image} are store in a map with soft values.
 *
 * @author mporhel
 */
public abstract class AbstractCachedSVGFigure extends SVGFigure {
    /**
     * Cache to store bitmaps of rendered SVGs.
     */
    private static final ImageCache CACHE = new ImageCache();

    private String getContextKey(Graphics graphics) {
        // CHECKSTYLE:OFF
        int aaText = SWT.DEFAULT;
        try {
            aaText = graphics.getTextAntialias();
        } catch (Exception e) {
            // not supported
        }
        // CHECKSTYLE:ON

        StringBuilder result = new StringBuilder();
        result.append(aaText);
        result.append(AbstractCachedSVGFigure.SEPARATOR);
        Rectangle r = getClientArea();
        result.append(r.width);
        result.append(AbstractCachedSVGFigure.SEPARATOR);
        result.append(r.height);

        return result.toString();
    }

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
        return getImage(getKey() + getContextKey(graphics), clientArea, graphics);
    }

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
    protected Image getImage(String key, Rectangle clientArea, Graphics graphics) {
        Image result = AbstractCachedSVGFigure.CACHE.getIfPresent(key);
        if (result == null) {
            result = render(this, clientArea, graphics);
            if (result != null) {
                AbstractCachedSVGFigure.CACHE.put(key, result);
            }
        }
        return result;
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
