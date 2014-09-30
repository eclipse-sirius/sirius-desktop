/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author Laurent Goubet <a
 *         href="mailto:laurent.goubet@obeo.fr">laurent.goubet@obeo.fr</a>
 */
public class SiriusTestsPlugin extends Plugin {
    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.tests.junit"; //$NON-NLS-1$

    /** This plug-in's shared instance. */
    private static SiriusTestsPlugin plugin;

    private static Set<Viewpoint> viewpoints;

    /**
     * Default constructor for the plugin.
     */
    public SiriusTestsPlugin() {
        plugin = this;
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static SiriusTestsPlugin getDefault() {
        return plugin;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        viewpoints = new HashSet<Viewpoint>();
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/componentization/componentization.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/componentization/componentization2.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/componentization/tools.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/layers/football.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/layers/footballExtension.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/migration/do_not_migrate/componentized_1a.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/migration/do_not_migrate/componentized_1b.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/table/unit/refresh/tables.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/tools/palette/extension/toolSection.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/tools/palette/extension/toolSection_extension.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/mappings/trac1926.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/data/unit/computelabel/testComputeLabelDiagram.odesign"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
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
