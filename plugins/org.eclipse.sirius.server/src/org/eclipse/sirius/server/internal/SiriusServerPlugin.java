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
package org.eclipse.sirius.server.internal;

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
         * The name of the extention point.
         */
        private static final String SIRIUS_SERVER_CONFIGURATOR = "siriusServerConfigurator"; //$NON-NLS-1$

        /**
         * The {@link IItemRegistry} used to retrieve the configurators.
         */
        private IItemRegistry<ISiriusServerConfigurator> siriusServerConfiguratorRegistry;

        /**
         * The extension registry listener for the configurator.
         */
        private AbstractRegistryEventListener siriusServerConfiguratorListener;

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
            this.siriusServerConfiguratorRegistry = new ItemRegistry<>();
            this.siriusServerConfiguratorListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, SIRIUS_SERVER_CONFIGURATOR, this.siriusServerConfiguratorRegistry);
            extensionRegistry.addListener(this.siriusServerConfiguratorListener, PLUGIN_ID + '.' + SIRIUS_SERVER_CONFIGURATOR);
            this.siriusServerConfiguratorListener.readRegistry(extensionRegistry);

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
            extensionRegistry.removeListener(this.siriusServerConfiguratorListener);

            this.siriusServerConfiguratorListener = null;
            this.siriusServerConfiguratorRegistry = null;

            super.stop(context);
        }

        /**
         * Returns the list of the {@link ISiriusServerConfigurator}.
         * 
         * @return The list of the {@link ISiriusServerConfigurator}
         */
        public List<ISiriusServerConfigurator> getSiriusServerConfigurators() {
            // @formatter:off
			return this.siriusServerConfiguratorRegistry.getItemDescriptors().stream()
					.map(IItemDescriptor::getItem)
					.collect(Collectors.toList());
			// @formatter:on
        }
    }
}
