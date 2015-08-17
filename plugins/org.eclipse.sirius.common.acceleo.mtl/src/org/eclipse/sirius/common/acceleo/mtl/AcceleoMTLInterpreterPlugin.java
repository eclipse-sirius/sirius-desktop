/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.extension.AcceleoRegistryListener;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.extension.ImportHandlerRegistry;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mporhel
 */
public class AcceleoMTLInterpreterPlugin extends EMFPlugin {
    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.common.acceleo.mtl"; //$NON-NLS-1$

    /** Keep track of the singleton.. */
    public static final AcceleoMTLInterpreterPlugin INSTANCE = new AcceleoMTLInterpreterPlugin();

    /** This plug-in's shared instance. */
    private static Implementation plugin;

    /**
     * Default constructor for the plugin.
     */
    public AcceleoMTLInterpreterPlugin() {
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
    public static Implementation getDefault() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipsePlugin {
        /**
         * The registry listener that will be used for extensions to the acceleo
         * interpreter.
         */
        private final AcceleoRegistryListener registryListener = new AcceleoRegistryListener();

        /**
         * Creates an instance.
         */
        public Implementation() {
            plugin = this;
        }

        @Override
        public void start(final BundleContext context) throws Exception {
            super.start(context);
            Platform.getExtensionRegistry().addListener(registryListener, AcceleoRegistryListener.IMPORT_HANDLER_EXTENSION_POINT);
            registryListener.parseInitialContributions();
        }

        @Override
        public void stop(final BundleContext context) throws Exception {
            super.stop(context);
            Platform.getExtensionRegistry().removeListener(registryListener);
            ImportHandlerRegistry.clearRegistry();
        }
    }
}
