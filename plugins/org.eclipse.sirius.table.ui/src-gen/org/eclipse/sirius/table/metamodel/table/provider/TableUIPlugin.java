/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.provider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.viewpoint.description.audit.provider.AuditItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.Maps;

/**
 * This is the central singleton for the Table edit plugin. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public final class TableUIPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    public static final TableUIPlugin INSTANCE = new TableUIPlugin();

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    private static Implementation plugin;

    public static final String ID = "org.eclipse.sirius.table.ui"; //$NON-NLS-1$

    /**
     * A map associating an ImageDescriptor witht the corresponding Image.
     */
    private static Map<ImageDescriptor, Image> descriptorsToImages = Maps.newHashMap();

    /**
     * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TableUIPlugin() {
        super(new ResourceLocator[] { EcoreEditPlugin.INSTANCE, SiriusEditPlugin.INSTANCE, });
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
        return TableUIPlugin.plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin() {
        return TableUIPlugin.plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static class Implementation extends EclipsePlugin {

        private ComposedAdapterFactory adapterFactory;

        /**
         * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        public Implementation() {
            TableUIPlugin.plugin = this;
        }

        /**
         * @not-generated : recreate adapter factory if destroyed..
         */
        public AdapterFactory getItemProvidersAdapterFactory() {
            if (adapterFactory == null) {
                adapterFactory = createAdapterFactory();
            }
            return adapterFactory;
        }

        /**
         * @not-generated
         */
        public ComposedAdapterFactory createAdapterFactory() {
            List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
            factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
            fillItemProviderFactories(factories);
            return new ComposedAdapterFactory(factories);
        }

        /**
         * @not-generated
         */
        protected void fillItemProviderFactories(List<AdapterFactory> factories) {
            factories.add(new ViewpointItemProviderAdapterFactory());
            factories.add(new DescriptionItemProviderAdapterFactory());
            factories.add(new ToolItemProviderAdapterFactory());
            factories.add(new AuditItemProviderAdapterFactory());
            factories.add(new EcoreItemProviderAdapterFactory());
            factories.add(new ResourceItemProviderAdapterFactory());
            factories.add(new ReflectiveItemProviderAdapterFactory());
            factories.add(new org.eclipse.sirius.table.metamodel.table.description.provider.DescriptionItemProviderAdapterFactory());
            factories.add(new TableItemProviderAdapterFactory());
        }

        /**
         * Logs an error in the error log.
         *
         * @param message
         *            the message to log (optionnal).
         * @param e
         *            the exception (optionnal).
         */
        public void error(String message, Exception e) {
            if (message == null && e != null) {
                message = e.getMessage();
            }
            if (e instanceof CoreException) {
                this.getLog().log(((CoreException) e).getStatus());
            } else {
                IStatus status = new Status(IStatus.ERROR, this.getBundle().getSymbolicName(), message, e);
                this.getLog().log(status);
            }
        }

        /**
         * Returns the image corresponding to the given ImageDescriptor.
         *
         * @param desc
         *            an image descriptor.
         * @return an Image instance corresponding to the given ImageDescriptor
         */
        public static Image getImage(ImageDescriptor desc) {
            if (!TableUIPlugin.descriptorsToImages.containsKey(desc)) {
                TableUIPlugin.descriptorsToImages.put(desc, desc.createImage());
            }
            return TableUIPlugin.descriptorsToImages.get(desc);
        }

        /**
         * Respects images residing in any plug-in. If path is relative, then
         * this bundle is looked up for the image, otherwise, for absolute path,
         * first segment is taken as id of plug-in with image
         *
         * @not-generated
         * @param path
         *            the path to image, either absolute (with plug-in id as
         *            first segment), or relative for bundled images
         * @return the image descriptor
         */
        public static ImageDescriptor findImageDescriptor(String path) {
            final IPath p = new Path(path);

            // Step 1 : trying to get the image descriptor from Workspace
            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IWorkspaceRoot root = workspace.getRoot();
            IFile candidateImage = root.getFile(p);
            // If the path is a valid path from the workspace's root
            if (candidateImage.exists()) {
                URL url;
                try {
                    // We try to create an image descriptor for the given path
                    url = new URL("platform:/resource" + p.makeAbsolute().toString()); //$NON-NLS-1$
                    return ImageDescriptor.createFromURL(url);
                } catch (MalformedURLException e) {
                    // nothing to do, we will try to create the Image Descriptor
                    // from plugins
                }
            }
            // Step 2 : try to create the Image Descriptor from plugins
            if (p.isAbsolute() && p.segmentCount() > 1) {
                return AbstractUIPlugin.imageDescriptorFromPlugin(p.segment(0), p.removeFirstSegments(1).makeAbsolute().toString());
            } else {
                return Implementation.getBundledImageDescriptor(p.makeAbsolute().toString());
            }
        }

        /**
         * Returns an image descriptor for the image file at the given plug-in
         * relative path.
         *
         * @not-generated
         * @param path
         *            the path
         * @return the image descriptor
         */
        public static ImageDescriptor getBundledImageDescriptor(String path) {
            return AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, path);
        }

    }

}
