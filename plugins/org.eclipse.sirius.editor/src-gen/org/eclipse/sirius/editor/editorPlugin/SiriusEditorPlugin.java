/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.editorPlugin;

// Start of user code imports

import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.sirius.editor.tools.api.ecore.WorkspaceEPackageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import com.google.common.collect.Sets;

// End of user code imports

/**
 * This is the central singleton for the Viewpoint editor plugin.
 */
public final class SiriusEditorPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton.
     */
    public static final SiriusEditorPlugin INSTANCE = new SiriusEditorPlugin();

    /** the plugin ID **/
    public static final String PLUGIN_ID = "org.eclipse.sirius.editor";

    /** Full path of the help icon. */
    public static final String ICONS_PREFERENCES_HELP = "icons/full/help.gif"; //$NON-NLS-1$

    /**
     * The registry for all graphic images; <code>null</code> if not yet
     * initialized.
     */
    private ImageRegistry imageRegistry = null;

    /**
     * Keep track of the singleton.
     */
    private static Implementation plugin;

    private static Set<String> siriusRuntimeBundles = Sets.newLinkedHashSet();

    // Start of user code fields

    private static WorkspaceEPackageRegistry workspaceEPackageRegistry;

    // End of user code fields

    /**
     * Create the instance.
     */
    public SiriusEditorPlugin() {
        super(new ResourceLocator[] {});
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * Returns the image registry for this UI plug-in.
     * <p>
     * The image registry contains the images used by this plug-in that are very
     * frequently used and so need to be globally shared within the plug-in.
     * Since many OSs have a severe limit on the number of images that can be in
     * memory at any given time, a plug-in should only keep a small number of
     * images in their registry.
     * <p>
     * Subclasses should reimplement <code>initializeImageRegistry</code> if
     * they have custom graphic images to load.
     * </p>
     * <p>
     * Subclasses may override this method but are not expected to.
     * </p>
     * 
     * @return the image registry
     */
    public ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            imageRegistry = createImageRegistry();
        }
        return imageRegistry;
    }

    /**
     * Returns a new image registry for this plugin-in. The registry will be
     * used to manage images which are frequently used by the plugin-in.
     * <p>
     * The default implementation of this method creates an empty registry.
     * Subclasses may override this method if needed.
     * </p>
     * 
     * @return ImageRegistry the resulting registry.
     * @see #getImageRegistry
     */
    protected ImageRegistry createImageRegistry() {

        // If we are in the UI Thread use that
        if (Display.getCurrent() != null) {
            return new ImageRegistry(Display.getCurrent());
        }

        if (PlatformUI.isWorkbenchRunning()) {
            return new ImageRegistry(PlatformUI.getWorkbench().getDisplay());
        }

        // Invalid thread access if it is not the UI Thread
        // and the workbench is not created.
        throw new SWTError(SWT.ERROR_THREAD_INVALID_ACCESS);
    }

    /**
     * Returns an image for the image file at the given plug-in relative path.
     * Client do not need to dispose this image. Images will be disposed
     * automatically.
     * 
     * @param path
     *            the path
     * @return image instance
     */
    public Image getBundledImage(String path) {
        Image image = getImageRegistry().get(path);
        if (image == null) {
            getImageRegistry().put(path, getBundledImageDescriptor(path));
            image = getImageRegistry().get(path);
        }
        return image;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getBundledImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * return a set containing all the symbolic names of bundles which are part
     * of the Sirius runtime and are currently installed in the platform.
     * 
     * @return a set containing all the symbolic names of bundles which are part
     *         of the Sirius runtime and are currently installed in the
     *         platform.
     */
    public static Set<String> getSiriusRuntimeBundles() {
        return siriusRuntimeBundles;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipseUIPlugin {
        /**
         * Creates an instance.
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }

        // Start of user code Implementation specifics
        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            workspaceEPackageRegistry = new WorkspaceEPackageRegistry(true);
            for (Bundle bnd : context.getBundles()) {
                String name = bnd.getSymbolicName();
                if (name != null && name.startsWith("org.eclipse.sirius")) {
                    if (name.indexOf("sample") == -1 && name.indexOf("tests") == -1) {
                        siriusRuntimeBundles.add(name);
                    }
                }
            }
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);
            if (EMFPlugin.IS_ECLIPSE_RUNNING) {
                workspaceEPackageRegistry.dispose(ResourcesPlugin.getWorkspace());
            }
            workspaceEPackageRegistry = null;
        }

        /**
         * Get a {@link Registry} which aggregate EPackage from EMF registry and
         * EPackage from workspace.
         * 
         * @return a {@link Registry} which aggregate EPackage from EMF registry
         *         and EPackage from workspace
         */
        public Registry getWorkspaceEPackageRegistry() {
            if (!workspaceEPackageRegistry.isListeningWorkspace() && EMFPlugin.IS_ECLIPSE_RUNNING) {
                workspaceEPackageRegistry.init(ResourcesPlugin.getWorkspace());
            }
            return workspaceEPackageRegistry;
        }

        // End of user code Implementation specifics
    }
}
