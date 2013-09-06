/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephane Fournier (Thales) <stephane.fournier@thalesgroup.com> - Initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.plugin;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Base class for plug-ins that integrate with the Eclipse platform UI.<br>
 * 
 * @author sfournier
 */
public abstract class AbstractUIActivator extends AbstractUIPlugin {
    private static final String ICONS_PATH = "icons/"; //$NON-NLS-1$

    /**
     * Get an image descriptor for given key.<br>
     * Images must be located in 'plug-in folder'/icons
     * 
     * @param key
     *            the key must be the file name of the related image.
     * @return an {@link ImageDescriptor} or null if not found
     */
    public ImageDescriptor getImageDescriptor(String key) {
        ImageRegistry imageRegistry = getImageRegistry();
        ImageDescriptor imageDescriptor = imageRegistry.getDescriptor(key);

        if (null == imageDescriptor) {
            imageDescriptor = createImageDescriptor(key);
            imageRegistry.put(key, imageDescriptor);
        }
        return imageDescriptor;
    }

    /**
     * Get an image for given key.<br>
     * Images must be located in 'plug-in folder'/icons
     * 
     * @param key
     *            the key must be the file name of the related image.
     * @return an {@link Image} or null if not found
     */
    public Image getImage(String key) {
        ImageRegistry imageRegistry = getImageRegistry();

        Image image = imageRegistry.get(key);
        if (null == image) {
            // Create an image descriptor for given id.
            ImageDescriptor imageDescriptor = createImageDescriptor(key);
            // Store the (id, imageDescriptor) rather than (id,image)
            // because with storing (id,image) the getDescriptor method will
            // return null in later usage
            // this way, everything is correctly initialized.
            imageRegistry.put(key, imageDescriptor);

            // Everything is all right at this step, let's get the real image
            image = imageRegistry.get(key);
        }
        return image;
    }

    /**
     * Create an image descriptor for given key.<br>
     * Images must be located in 'plug-in folder'/icons
     * 
     * @param key
     *            the key must be the file name of the related image.
     * @return an {@link ImageDescriptor} or null if error occurred
     */
    protected ImageDescriptor createImageDescriptor(String key) {
        ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(getPluginId(), ICONS_PATH + key);
        return imageDescriptor;
    }

    /**
     * Get the plug-in ID according to MANISFEST.MF definition.
     * 
     * @return a String containing the plug-in ID.
     */
    public String getPluginId() {
        return getBundle().getSymbolicName();
    }

    /**
     * Log a message in the Eclipse log file.
     * 
     * @param severity
     *            one of <code>IStatus.OK</code>, <code>IStatus.ERROR</code>,
     *            <code>IStatus.INFO</code>, <code>IStatus.WARNING</code>, or
     *            <code>IStatus.CANCEL</code>
     * @param message
     *            a human-readable message, localized to the current locale
     * @param exception
     *            a low-level exception, or <code>null</code> if not applicable
     */
    public void log(int severity, String message, Throwable exception) {
        getLog().log(new Status(severity, getPluginId(), message, exception));
    }
}
