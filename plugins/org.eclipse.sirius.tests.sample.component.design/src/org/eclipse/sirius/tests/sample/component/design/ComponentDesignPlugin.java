/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.tests.sample.component.design;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class ComponentDesignPlugin extends EclipseUIPlugin {
    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.sirius.tests.sample.component.design";

    // The shared instance
    public static ComponentDesignPlugin INSTANCE;

    private static Set<Viewpoint> viewpoints;

    /**
     * The constructor.
     */
    public ComponentDesignPlugin() {
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        INSTANCE = this;
        viewpoints = new HashSet<Viewpoint>();
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/component.odesign"));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        INSTANCE = null;
        if (viewpoints != null) {
            for (final Viewpoint viewpoint : viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            viewpoints.clear();
            viewpoints = null;
        }
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static ComponentDesignPlugin getDefault() {
        return INSTANCE;
    }
}
