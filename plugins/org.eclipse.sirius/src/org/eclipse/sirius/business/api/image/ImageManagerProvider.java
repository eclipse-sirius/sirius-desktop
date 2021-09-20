/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.image;

import org.eclipse.sirius.business.internal.image.ImageManagerForWorkspaceResource;

/**
 * This provider is intended to provide a {@link ImageManager}.
 * 
 * @author lfasani
 */
public final class ImageManagerProvider {
    private static ImageManager imageManager = new ImageManagerForWorkspaceResource();

    private ImageManagerProvider() {
    }

    /**
     * Set the imageManager that will be used.</br>
     * Sirius sets its own ImageManager and this method is intended to be called by third part application code so that
     * the ImageManager provided by Sirius can be overridden.
     * 
     * @param imageManagerToSet
     *            the image manager
     */
    public static void setImageManager(ImageManager imageManagerToSet) {
        imageManager = imageManagerToSet;
    }

    /**
     * Get the instance of {@link ImageManager}.
     * 
     * @return the instance of {@link ImageManager}.
     */
    public static ImageManager getImageManager() {
        return imageManager;
    }
}
