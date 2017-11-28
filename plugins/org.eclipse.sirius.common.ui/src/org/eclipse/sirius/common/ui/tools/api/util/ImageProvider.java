/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.util;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * Provider for images.
 * 
 * @author <a href="mailto:florian.barbin@obeo.fr">Florian Barbin</a>
 */
public final class ImageProvider {
    /**
     * Private constructor to avoid instantiation.
     */
    private ImageProvider() {
    }

    /**
     * Get the <code>ImageDescriptor</code> from a path.
     * 
     * @param path
     *            The path of the image (path relative to the workspace or to
     *            the plugins).
     * @return an ImageDescriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        final File imageFile = FileProvider.getDefault().getFile(new Path(path));
        if (imageFile != null && imageFile.exists() && imageFile.canRead()) {
            try {
                return ImageDescriptor.createFromURL(imageFile.toURI().toURL());
            } catch (MalformedURLException e) {
                // do nothing
            }
        }
        return ImageDescriptor.getMissingImageDescriptor();
    }

    /**
     * Get the Image corresponding to this path.
     * 
     * @param path
     *            The path of the image (path relative to the workspace or to
     *            the plugins).
     * @return An image
     */
    public static Image getImageFromPath(final String path) {
        return SiriusTransPlugin.INSTANCE.getBundledImage(path);
    }

}
