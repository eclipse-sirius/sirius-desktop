/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.ui.properties.internal;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * The plugin class of the bundle.
 * 
 * @author sbegaudeau
 */
public class SiriusUiPropertiesTestsEMFPlugin extends EMFPlugin {

    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.tests.ui.properties"; //$NON-NLS-1$

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusUiPropertiesTestsEMFPlugin INSTANCE = new SiriusUiPropertiesTestsEMFPlugin();

    /**
     * The OSGi related implementation of the plugin.
     */
    private static SiriusUiPropertiesTestsEcorePlugin plugin;

    /**
     * The constructor.
     */
    public SiriusUiPropertiesTestsEMFPlugin() {
        super(new ResourceLocator[0]);
    }

    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the sole implementation of the plugin.
     * 
     * @return The sole implementation of the plugin
     */
    public static SiriusUiPropertiesTestsEcorePlugin getPlugin() {
        return plugin;
    }

    /**
     * This class is used as the bundle activator of the plugin.
     * 
     * @author sbegaudeau
     */
    public static class SiriusUiPropertiesTestsEcorePlugin extends EclipseUIPlugin {
        /**
         * The constructor.
         */
        public SiriusUiPropertiesTestsEcorePlugin() {
            super();
            SiriusUiPropertiesTestsEMFPlugin.plugin = this;
        }
    }

}
