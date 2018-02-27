/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.Collection;

import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Activator registering ELK layout algorithms to Sirius registry.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 */
public class DiagramElkPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.sirius.diagram.elk"; //$NON-NLS-1$

    // The shared instance
    private static DiagramElkPlugin plugin;

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        Collection<LayoutAlgorithmData> algorithmData = LayoutMetaDataService.getInstance().getAlgorithmData();
        for (LayoutAlgorithmData layoutAlgorithmData : algorithmData) {
            DiagramUIPlugin.getPlugin().addLayoutProvider(layoutAlgorithmData.getId(), layoutAlgorithmData.getName(), () -> new ELKLayoutNodeProvider());
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static DiagramElkPlugin getDefault() {
        return plugin;
    }

}
