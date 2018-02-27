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
package org.eclipse.sirius.tests.xtext;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusXtextTestsPlugin extends Plugin {
    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.tests.junit.xtext"; //$NON-NLS-1$

    /** This plug-in's shared instance. */
    private static SiriusXtextTestsPlugin plugin;

    private static Set<Viewpoint> viewpoints;

    /**
     * Default constructor for the plugin.
     */
    public SiriusXtextTestsPlugin() {
        plugin = this;
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static SiriusXtextTestsPlugin getDefault() {
        return plugin;
    }

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        viewpoints = new HashSet<Viewpoint>();
        // viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID +
        // "/data/unit/componentization/componentization.odesign"));
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        if (viewpoints != null) {
            for (Viewpoint viewpoint : viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            viewpoints.clear();
            viewpoints = null;
        }
        super.stop(context);
    }

}
