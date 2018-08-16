/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.internal;

import java.util.Optional;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.sirius.ext.ide.api.AbstractRegistryEventListener;
import org.eclipse.sirius.ext.ide.api.DescriptorRegistryEventListener;
import org.eclipse.sirius.ext.ide.api.IItemDescriptor;
import org.eclipse.sirius.ext.ide.api.IItemRegistry;
import org.eclipse.sirius.ext.ide.api.ItemRegistry;
import org.eclipse.sirius.services.diagram.api.ISiriusDiagramImagePathProvider;
import org.osgi.framework.BundleContext;

/**
 * The plugin class of the Sirius diagram component.
 *
 * @author sbegaudeau
 */
public final class SiriusDiagramPlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.services.diagram"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusDiagramPlugin INSTANCE = new SiriusDiagramPlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    private SiriusDiagramPlugin() {
        super(new ResourceLocator[0]);
    }

    @Override
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
     * The bundle activator.
     *
     * @author sbegaudeau
     */
    public static class Implementation extends EclipsePlugin {

        /**
         * The name of the sirius diagram image path provider extension point.
         */
        private static final String SIRIUS_DIAGRAM_IMAGE_PATH_PROVIDER = "siriusDiagramImagePathProvider"; //$NON-NLS-1$

        /**
         * The {@link IItemRegistry} used to retrieve the image path providers.
         */
        private IItemRegistry<ISiriusDiagramImagePathProvider> imagePathProviderRegistry;

        /**
         * The extension registry listener for the image path provider.
         */
        private AbstractRegistryEventListener imagePathProviderListener;

        /**
         * The constructor.
         */
        public Implementation() {
            super();
            SiriusDiagramPlugin.plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);

            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

            this.imagePathProviderRegistry = new ItemRegistry<>();
            this.imagePathProviderListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, SIRIUS_DIAGRAM_IMAGE_PATH_PROVIDER, this.imagePathProviderRegistry);
            extensionRegistry.addListener(this.imagePathProviderListener);
            this.imagePathProviderListener.readRegistry(extensionRegistry);
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);

            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
            extensionRegistry.removeListener(this.imagePathProviderListener);
            this.imagePathProviderListener = null;
            this.imagePathProviderRegistry = null;
        }

        /**
         * Returns the image path provider.
         * 
         * @return The image path provider
         */
        public Optional<ISiriusDiagramImagePathProvider> getImagePathProvider() {
            // @formatter:off
            return this.imagePathProviderRegistry.getItemDescriptors().stream()
                    .map(IItemDescriptor::getItem)
                    .findFirst();
            // @formatter:on
        }
    }
}
