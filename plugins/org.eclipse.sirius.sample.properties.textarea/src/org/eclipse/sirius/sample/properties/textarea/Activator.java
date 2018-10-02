/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.sample.properties.textarea;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.sirius.sample.properties.textarea";

    // The shared instance
    private static Activator plugin;

    private static Set<Viewpoint> viewpoints;

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        Activator.plugin = this;
        Activator.viewpoints = new HashSet<Viewpoint>();
        Activator.viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(Activator.PLUGIN_ID + "/description/project.odesign"));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        Activator.plugin = null;
        if (Activator.viewpoints != null) {
            for (final Viewpoint viewpoint : Activator.viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            Activator.viewpoints.clear();
            Activator.viewpoints = null;
        }
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return Activator.plugin;
    }
}
