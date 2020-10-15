/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.diagram.sequence.business.internal.util;

/**
 * Sequence cache helper.
 * 
 * @author nlepine
 * 
 */
public final class CacheHelper {

    private static boolean dragTrackercacheEnabled;

    /**
     * Avoid instantiation.
     */
    private CacheHelper() {
        // Do nothing.
    }

    /**
     * Return if drag tracker cache is enabled.
     * 
     * @return if drag tracker cache is enabled.
     */
    public static boolean isDragTrackerCacheEnabled() {
        return dragTrackercacheEnabled;
    }

    /**
     * Set if drag tracker cache is enabled.
     * 
     * @param enabled
     *            boolean
     */
    public static void setDragTrackerCacheEnabled(boolean enabled) {
        CacheHelper.dragTrackercacheEnabled = enabled;
    }

    /**
     * Clear drag tracker caches.
     */
    public static void clearDragTrackerCaches() {
    }

    /**
     * Clear and disable all caches.
     */
    public static void clearCaches() {
        CacheHelper.setDragTrackerCacheEnabled(false);
        CacheHelper.clearDragTrackerCaches();
    }

    /**
     * Init and enable all caches.
     */
    public static void initCaches() {
        clearCaches();
        CacheHelper.setDragTrackerCacheEnabled(true);
    }

}
