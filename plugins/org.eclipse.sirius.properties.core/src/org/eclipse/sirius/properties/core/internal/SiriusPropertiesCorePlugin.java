/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal;

import org.eclipse.eef.common.api.AbstractEEFEclipsePlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.osgi.framework.BundleContext;

/**
 * The plugin for <code>org.eclipse.sirius.properties.core</code>.
 * 
 * @author sbegaudeau
 */
public class SiriusPropertiesCorePlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.properties.core"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusPropertiesCorePlugin INSTANCE = new SiriusPropertiesCorePlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public SiriusPropertiesCorePlugin() {
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
    public static class Implementation extends AbstractEEFEclipsePlugin {

        /**
         * The constructor.
         */
        public Implementation() {
            super(PLUGIN_ID);
            SiriusPropertiesCorePlugin.plugin = this;
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
