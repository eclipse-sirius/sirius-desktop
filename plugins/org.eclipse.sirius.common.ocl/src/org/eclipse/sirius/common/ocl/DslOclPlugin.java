/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ocl;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author ymortier
 */
public class DslOclPlugin extends EMFPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.common.ocl";

    /**
     * Keep track of the singleton.
     */
    public static final DslOclPlugin INSTANCE = new DslOclPlugin();

    /**
     * Keep track of the singleton.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public DslOclPlugin() {
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
    public static class Implementation extends EclipsePlugin {
        /**
         * Creates an instance.
         */
        public Implementation() {
            plugin = this;
        }
        
        /**
         * Log a an error in the error log view.
         * 
         * @param message
         *            the message.
         * @param t
         *            the optional exception.
         */
        public void error(final String message, final Throwable t) {
            final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
            Platform.getLog(this.getBundle()).log(status);
        }
    }
}
