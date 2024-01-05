/*******************************************************************************
 * Copyright (c) 2018, 2024 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * Activator registering ELK layout algorithms to Sirius registry.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 */
public class DiagramElkPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton.
     */
    public static final DiagramElkPlugin INSTANCE = new DiagramElkPlugin();
    
    /**
     * Keep track of the shared singleton instance.
     */
    private static Implementation plugin;
    
    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.sirius.diagram.elk"; //$NON-NLS-1$

    /**
     * Create the instance.
     */
    public DiagramElkPlugin() {
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
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipseUIPlugin {
        /**
         * Creates an instance.
         */
        public Implementation() {
            plugin = this;
        }
    }
}
