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
package org.eclipse.sirius.properties.defaultrules.internal;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * The plugin for <code>org.eclipse.sirius.properties.core</code>.
 * 
 * @author sbegaudeau
 */
public class SiriusPropertiesDefaultRulesPlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.properties.defaultrules"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusPropertiesDefaultRulesPlugin INSTANCE = new SiriusPropertiesDefaultRulesPlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public SiriusPropertiesDefaultRulesPlugin() {
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
    public static class Implementation extends EclipsePlugin {
        /**
         * The constructor.
         */
        public Implementation() {
            SiriusPropertiesDefaultRulesPlugin.plugin = this;
        }
    }
}
