/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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
package org.eclipse.sirius.server.internal;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.sirius.ext.ide.api.AbstractRegistryEventListener;
import org.eclipse.sirius.ext.ide.api.DescriptorRegistryEventListener;
import org.eclipse.sirius.ext.ide.api.IItemDescriptor;
import org.eclipse.sirius.ext.ide.api.IItemRegistry;
import org.eclipse.sirius.ext.ide.api.ItemRegistry;
import org.eclipse.sirius.server.api.ISiriusServerConfigurator;
import org.eclipse.sirius.server.api.ISiriusServerEndpointConfigurationProvider;
import org.eclipse.sirius.server.api.ISiriusServerService;
import org.osgi.framework.BundleContext;

/**
 * Plugin class for the bundle.
 *
 * @author sbegaudeau
 */
public class SiriusServerPlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.server"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusServerPlugin INSTANCE = new SiriusServerPlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public SiriusServerPlugin() {
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
         * The name of the sirius server configurator extension point.
         */
        private static final String SIRIUS_SERVER_CONFIGURATOR = "siriusServerConfigurator"; //$NON-NLS-1$

        /**
         * The name of the sirius server service extension point.
         */
        private static final String SIRIUS_SERVER_SERVICE = "siriusServerService"; //$NON-NLS-1$

        /**
         * The name of the sirius server endpoint configuration provider
         * extension point.
         */
        private static final String SIRIUS_SERVER_ENDPOINT_CONFIGURATION_PROVIDER = "siriusServerEndpointConfigurationProvider"; //$NON-NLS-1$

        /**
         * The {@link IItemRegistry} used to retrieve the configurators.
         */
        private IItemRegistry<ISiriusServerConfigurator> configuratorRegistry;

        /**
         * The extension registry listener for the configurator.
         */
        private AbstractRegistryEventListener configuratorListener;

        /**
         * The {@link IItemRegistry} used to retrieve the services.
         */
        private IItemRegistry<ISiriusServerService> serviceRegistry;

        /**
         * The extension registry listener for the service.
         */
        private AbstractRegistryEventListener serviceListener;

        /**
         * The {@link IItemRegistry} used to retrieve the endpoint configuration
         * providers.
         */
        private IItemRegistry<ISiriusServerEndpointConfigurationProvider> endpointConfigurationProviderRegistry;

        /**
         * The extension registry listener for the endpoint configuration
         * providers.
         */
        private AbstractRegistryEventListener endpointConfigurationProviderListener;

        /**
         * The server manager.
         */
        private SiriusServerManager serverManager = new SiriusServerManager();

        /**
         * The constructor.
         */
        public Implementation() {
            super();
            SiriusServerPlugin.plugin = this;
        }

        /**
         * {@inheritDoc}
         *
         * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
         */
        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);

            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

            // Sirius server configurator extension point
            this.configuratorRegistry = new ItemRegistry<>();
            this.configuratorListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, SIRIUS_SERVER_CONFIGURATOR, this.configuratorRegistry);
            extensionRegistry.addListener(this.configuratorListener, PLUGIN_ID + '.' + SIRIUS_SERVER_CONFIGURATOR);
            this.configuratorListener.readRegistry(extensionRegistry);

            // Sirius server service extension point
            this.serviceRegistry = new ItemRegistry<>();
            this.serviceListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, SIRIUS_SERVER_SERVICE, this.serviceRegistry);
            extensionRegistry.addListener(this.serviceListener, PLUGIN_ID + '.' + SIRIUS_SERVER_SERVICE);
            this.serviceListener.readRegistry(extensionRegistry);

            // Sirius server endpoint configuration provider extension point
            this.endpointConfigurationProviderRegistry = new ItemRegistry<>();
            this.endpointConfigurationProviderListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, SIRIUS_SERVER_ENDPOINT_CONFIGURATION_PROVIDER, this.endpointConfigurationProviderRegistry);
            extensionRegistry.addListener(this.endpointConfigurationProviderListener, PLUGIN_ID + '.' + SIRIUS_SERVER_ENDPOINT_CONFIGURATION_PROVIDER);
            this.endpointConfigurationProviderListener.readRegistry(extensionRegistry);

            this.serverManager.start();
        }

        /**
         * {@inheritDoc}
         *
         * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
         */
        @Override
        public void stop(BundleContext context) throws Exception {
            this.serverManager.stop();

            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
            extensionRegistry.removeListener(this.configuratorListener);
            extensionRegistry.removeListener(this.serviceListener);
            extensionRegistry.removeListener(this.endpointConfigurationProviderListener);

            this.serviceListener = null;
            this.serviceRegistry = null;
            this.configuratorListener = null;
            this.configuratorRegistry = null;
            this.endpointConfigurationProviderListener = null;
            this.endpointConfigurationProviderRegistry = null;

            super.stop(context);
        }

        /**
         * Returns the list of the {@link ISiriusServerConfigurator}.
         *
         * @return The list of the {@link ISiriusServerConfigurator}
         */
        public List<ISiriusServerConfigurator> getSiriusServerConfigurators() {
            // @formatter:off
			return this.configuratorRegistry.getItemDescriptors().stream()
					.map(IItemDescriptor::getItem)
					.collect(Collectors.toList());
			// @formatter:on
        }

        /**
         * Returns the list of the {@link ISiriusServerService}.
         *
         * @return The list of the {@link ISiriusServerService}
         */
        public List<ISiriusServerService> getSiriusServerServices() {
            // @formatter:off
            return this.serviceRegistry.getItemDescriptors().stream()
                    .map(IItemDescriptor::getItem)
                    .collect(Collectors.toList());
            // @formatter:off
        }

        /**
         * Returns the list of the {@link ISiriusServerEndpointConfigurationProvider}.
         *
         * @return The list of the {@link ISiriusServerEndpointConfigurationProvider}
         */
        public List<ISiriusServerEndpointConfigurationProvider> getEndpointConfigurationProviders() {
            // @formatter:off
            return this.endpointConfigurationProviderRegistry.getItemDescriptors().stream()
                    .map(IItemDescriptor::getItem)
                    .collect(Collectors.toList());
            // @formatter:off
        }

        /**
         * Returns the URI on which the server is listening.
         *
         * @return the URI on which the server is listening.
         */
        public URI getServerURI() {
            return serverManager.getURI();
        }
    }
}
