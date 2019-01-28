/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.common.api;

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
import org.osgi.framework.BundleContext;

/**
 * The plugin of the Sirius GraphQL Common plugin.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLCommonPlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.services.graphql.common"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusGraphQLCommonPlugin INSTANCE = new SiriusGraphQLCommonPlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    private SiriusGraphQLCommonPlugin() {
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
         * The name of the sirius GraphQL schema customizer extension point.
         */
        private static final String SIRIUS_GRAPHQL_SCHEMA_CUSTOMIZER = "siriusGraphqlSchemaCustomizer"; //$NON-NLS-1$

        /**
         * The {@link IItemRegistry} used to retrieve the customizers.
         */
        private IItemRegistry<ISiriusGraphQLSchemaCustomizer> customizerRegistry;

        /**
         * The extension registry listener for the customizers.
         */
        private AbstractRegistryEventListener customizerListener;

        /**
         * The constructor.
         */
        public Implementation() {
            super();
            SiriusGraphQLCommonPlugin.plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);

            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

            this.customizerRegistry = new ItemRegistry<>();
            this.customizerListener = new DescriptorRegistryEventListener<>(PLUGIN_ID, SIRIUS_GRAPHQL_SCHEMA_CUSTOMIZER, this.customizerRegistry);
            extensionRegistry.addListener(this.customizerListener, PLUGIN_ID + '.' + SIRIUS_GRAPHQL_SCHEMA_CUSTOMIZER);
            this.customizerListener.readRegistry(extensionRegistry);

        }

        @Override
        public void stop(BundleContext context) throws Exception {
            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
            extensionRegistry.removeListener(this.customizerListener);

            this.customizerListener = null;
            this.customizerRegistry = null;

            super.stop(context);
        }

        /**
         * Returns the list of the {@link ISiriusGraphQLSchemaCustomizer}.
         * 
         * @return The list of the {@link ISiriusGraphQLSchemaCustomizer}.
         */
        public List<ISiriusGraphQLSchemaCustomizer> getGraphQLSchemaCustomizers() {
            // @formatter:off
            return this.customizerRegistry.getItemDescriptors().stream()
                    .map(IItemDescriptor::getItem)
                    .collect(Collectors.toList());
            // @formatter:on
        }
    }
}
