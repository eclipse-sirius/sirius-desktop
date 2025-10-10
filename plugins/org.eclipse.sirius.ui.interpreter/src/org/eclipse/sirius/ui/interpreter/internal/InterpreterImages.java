/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.PlatformUI;

/**
 * This utility class provides the images for the interpreter plugin.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class InterpreterImages {
    /** Base URL for all "icon" images. This will be initialized to this bundle's "icons" folder. */
    private static final URL BASE_URL;

    /** The image registry containing <code>Image</code>s and the <code>ImageDescriptor</code>s. */
    private static ImageRegistry imageRegistry;

    static {
        BASE_URL = InterpreterPlugin.getDefault().getBundle().getEntry(IInterpreterConstants.INTERPRETER_ICONS);
    }

    /**
     * Utility classes don't need a default constructor.
     */
    private InterpreterImages() {
        // Hides default constructor
    }

    /**
     * Returns the image descriptor associated with the given key in this registry.
     * 
     * @param key
     *            Key of the image descriptor we seek.
     * @return The image descriptor associated with the given key in this registry.
     */
    public static ImageDescriptor getImageDescriptor(String key) {
        return getImageRegistry().getDescriptor(key);
    }

    /**
     * Provides access to the image registry.
     * 
     * @return The image registry.
     */
    public static ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            initializeRegistry();
        }
        return imageRegistry;
    }

    /**
     * Creates the given image and puts it in the registry for latter use.
     * 
     * @param key
     *            Key that will allow us to retrieve the image in the registry.
     * @param path
     *            Path to the image that is to be put into the registry.
     */
    private static void createImage(String key, String path) {
        ImageDescriptor image = ImageDescriptor.getMissingImageDescriptor();
        if (BASE_URL == null) {
            // FIXME log this, will have a "missing image" descriptor.
        } else {
            try {
                image = ImageDescriptor.createFromURL(new URL(BASE_URL, path));
            } catch (MalformedURLException e) {
                // FIXME log this, will have a "missing image" descriptor.
            }
        }
        imageRegistry.put(key, image);
    }

    /**
     * Initializes the image registry with all needed images.
     */
    private static void initializeRegistry() {
        imageRegistry = new ImageRegistry(PlatformUI.getWorkbench().getDisplay());

        createImage(IInterpreterConstants.CLEAR_ACTION_ICON, IInterpreterConstants.CLEAR_ACTION_ICON);
        createImage(IInterpreterConstants.DELETE_ACTION_DISABLED_ICON, IInterpreterConstants.DELETE_ACTION_DISABLED_ICON);
        createImage(IInterpreterConstants.DELETE_ACTION_ICON, IInterpreterConstants.DELETE_ACTION_ICON);
        createImage(IInterpreterConstants.EVALUATE_ACTION_ICON, IInterpreterConstants.EVALUATE_ACTION_ICON);
        createImage(IInterpreterConstants.SORT_ACTION_ICON, IInterpreterConstants.SORT_ACTION_ICON);
        createImage(IInterpreterConstants.INTERPRETER_VIEW_DEFAULT_ICON, IInterpreterConstants.INTERPRETER_VIEW_DEFAULT_ICON);
        createImage(IInterpreterConstants.LINK_WITH_EDITOR_CONTEXT_ACTION_ICON, IInterpreterConstants.LINK_WITH_EDITOR_CONTEXT_ACTION_ICON);
        createImage(IInterpreterConstants.LINK_WITH_EDITOR_CONTEXT_ACTION_DISABLED_ICON, IInterpreterConstants.LINK_WITH_EDITOR_CONTEXT_ACTION_DISABLED_ICON);
        createImage(IInterpreterConstants.REALTIME_TOGGLE_ICON, IInterpreterConstants.REALTIME_TOGGLE_ICON);
        createImage(IInterpreterConstants.VARIABLE_VISIBILITY_TOGGLE_ICON, IInterpreterConstants.VARIABLE_VISIBILITY_TOGGLE_ICON);
        createImage(IInterpreterConstants.STEP_BY_STEP_VISIBILITY_TOGGLE_ICON, IInterpreterConstants.STEP_BY_STEP_VISIBILITY_TOGGLE_ICON);
    }
}
