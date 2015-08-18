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
package org.eclipse.sirius.common.ui;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderRegistryListener;
import org.eclipse.sirius.common.ui.tools.internal.util.ISaveDialogExtensionRegistryListener;

/**
 * This is the central singleton for the SystemInterfaces edit plugin. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @author cbrun
 */
public final class SiriusTransPlugin extends EMFPlugin {

    /**
     * The icons root directory.
     */
    public static final String PREFIX_ROOT = "icons/"; //$NON-NLS-1$

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.sirius.common.ui"; //$NON-NLS-1$

    /**
     * Singleton instance.
     */
    public static final SiriusTransPlugin INSTANCE = new SiriusTransPlugin();

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     */
    private static Implementation plugin;

    private ImageRegistry imageRegistry;

    /**
     * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     */
    public SiriusTransPlugin() {
        super(new ResourceLocator[] {});
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the singleton instance.
     * @generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getBundledImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * Creates the image descriptor from the filename given.
     * 
     * @param imageName
     *            the full filename of the image
     * @return the new image descriptor
     */
    public static ImageDescriptor create(final String imageName) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(SiriusTransPlugin.PLUGIN_ID, PREFIX_ROOT + imageName);
    }

    /**
     * Returns an image for the image file. If path is relative, then this
     * bundle is looked up for the image, otherwise, for absolute path, first
     * segment is taken as id of plug-in with image. Client do not need to
     * dispose this image. Images will be disposed automatically.
     * 
     * @generated
     * @param path
     *            the path
     * @return image instance
     */
    public Image getBundledImage(final String path) {
        Image image = getImageRegistry().get(path);
        if (image == null) {
            final File imageFile = FileProvider.getDefault().getFile(new Path(path));
            if (imageFile != null && imageFile.exists() && imageFile.canRead()) {
                try {
                    ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(imageFile.toURI().toURL());
                    getImageRegistry().put(path, imageDescriptor);
                    image = getImageRegistry().get(path);

                } catch (MalformedURLException e) {
                    // do nothing
                }
            }
        }
        return image;
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
     * Returns a new image registry for this plug-in. The registry will be used
     * to manage images which are frequently used by the plug-in.
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
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the singleton instance.
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * Log a an error in the error log view.
     * 
     * @param message
     *            the message.
     * @param t
     *            the optional exception.
     */
    public void error(final String message, final Throwable t) {
        final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
        if (getPluginLogger() != null) {
            getPluginLogger().log(status);
        }
    }

    /**
     * Log an info in the error log view.
     * 
     * @param message
     *            the message.
     * @param t
     *            the optional exception.
     */
    public void info(final String message, final Throwable t) {
        final IStatus status = new Status(IStatus.INFO, PLUGIN_ID, message, t);
        if (getPluginLogger() != null) {
            getPluginLogger().log(status);
        }
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated-not
     */
    public static class Implementation extends EclipseUIPlugin {

        private ISaveDialogExtensionRegistryListener registryListener;

        /**
         * The registry listener that will be used to listen to
         * propertyContributorLabelProviderDelegate extension changes.
         */
        private LabelProviderProviderRegistryListener labelProviderProviderRegistryListener;

        private PreferenceChangeListener preferenceChangeListener;

        /**
         * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
            preferenceChangeListener = new PreferenceChangeListener();
        }

        private void initProfiler() {
            final boolean profiling = this.getPreferenceStore().getBoolean(CommonPreferencesConstants.PREF_TRACE_ON);
            DslCommonPlugin.PROFILER.setActive(profiling);
        }

        /**
         * Log a an error in the error log view.
         * 
         * @param message
         *            the message.
         * @param t
         *            the optional exception.
         */
        public void error(final String message, final Throwable t) {
            final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
            getLog().log(status);
        }

        /**
         * Log a an error in the error log view.
         * 
         * @param message
         *            the message.
         * @param t
         *            the optional exception.
         */
        public void warning(final String message, final Throwable t) {
            final IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, message, t);
            getLog().log(status);
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);

            initProfiler();
            getPreferenceStore().addPropertyChangeListener(preferenceChangeListener);
            registryListener = new ISaveDialogExtensionRegistryListener();
            registryListener.init();
            labelProviderProviderRegistryListener = new LabelProviderProviderRegistryListener();
            labelProviderProviderRegistryListener.init();
        }

        /**
         * 
         * {@inheritDoc}
         * 
         * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
         * @generated-not
         */
        @Override
        public void stop(BundleContext context) throws Exception {
            getPreferenceStore().removePropertyChangeListener(preferenceChangeListener);
            registryListener.dispose();
            registryListener = null;
            labelProviderProviderRegistryListener.dispose();
            labelProviderProviderRegistryListener = null;
            super.stop(context);
        }

        /**
         * Listens preferences.
         * 
         * @author ymortier
         */
        private class PreferenceChangeListener implements IPropertyChangeListener {

            public void propertyChange(final PropertyChangeEvent event) {
                final boolean profiling = getPreferenceStore().getBoolean(CommonPreferencesConstants.PREF_TRACE_ON);
                DslCommonPlugin.PROFILER.setActive(profiling);
            }
        }

    }
}
