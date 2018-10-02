/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.internal;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mchauvin
 */
public class SiriusTestsSupportPlugin extends Plugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.tests.support";

    /** The shared instance */
    private static SiriusTestsSupportPlugin plugin;

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        SiriusTestsSupportPlugin.plugin = this;
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        SiriusTestsSupportPlugin.plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static SiriusTestsSupportPlugin getDefault() {
        return SiriusTestsSupportPlugin.plugin;
    }
}
