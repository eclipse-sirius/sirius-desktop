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

import org.eclipse.sirius.common.acceleo.mtl.business.internal.extension.AcceleoRegistryListener;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.extension.ImportHandlerRegistry;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mporhel
 */
public class AcceleoMTLInterpreterPlugin extends Plugin {
    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.common.acceleo.mtl"; //$NON-NLS-1$

    /** This plug-in's shared instance. */
    private static AcceleoMTLInterpreterPlugin plugin;

    /**
     * The registry listener that will be used for extensions to the acceleo
     * interpreter.
     */
    private final AcceleoRegistryListener registryListener = new AcceleoRegistryListener();

    /**
     * Default constructor for the plugin.
     */
    public AcceleoMTLInterpreterPlugin() {
        // Empty implementation
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static AcceleoMTLInterpreterPlugin getDefault() {
        return plugin;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        Platform.getExtensionRegistry().addListener(registryListener, AcceleoRegistryListener.IMPORT_HANDLER_EXTENSION_POINT);
        registryListener.parseInitialContributions();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
        Platform.getExtensionRegistry().removeListener(registryListener);
        ImportHandlerRegistry.clearRegistry();
    }
}
