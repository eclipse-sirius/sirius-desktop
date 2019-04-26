/*******************************************************************************
 * Copyright (c) 2019 Obeo
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
package org.eclipse.sirius.diagram.elk.debug;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * The actual implementation of the Eclipse <b>Plugin</b>.
 * 
 * @author Laurent Redor
 */
public class DiagramElkDebugPlugin extends EMFPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.sirius.diagram.elk.debug"; //$NON-NLS-1$

    /**
     * Keep track of the singleton.
     */
    public static final DiagramElkDebugPlugin INSTANCE = new DiagramElkDebugPlugin();

    /**
     * Keep track of the singleton.
     */
    private static Implementation plugin;

    /**
     * Create the instance.
     */
    public DiagramElkDebugPlugin() {
        super(new ResourceLocator[0]);
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return DiagramElkDebugPlugin.plugin;
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
