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
package org.eclipse.sirius.tests.services.graphql.internal;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.osgi.framework.BundleContext;

/**
 * The plugin of the bundle.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLTestsPlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.tests.services.graphql"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusGraphQLTestsPlugin INSTANCE = new SiriusGraphQLTestsPlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    private SiriusGraphQLTestsPlugin() {
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
         * The constructor.
         */
        public Implementation() {
            super();
            SiriusGraphQLTestsPlugin.plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);
        }
    }

}
