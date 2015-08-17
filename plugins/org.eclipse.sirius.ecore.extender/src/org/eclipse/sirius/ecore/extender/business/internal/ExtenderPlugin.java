/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * Extender plug-in.
 * 
 * @author mchauvin
 */
public class ExtenderPlugin extends EMFPlugin {

    /** The id. */
    public static final String ID = "org.eclipse.sirius.ecore.extender"; //$NON-NLS-1$ 

    /**
     * Keep track of the singleton.
     */
    public static final ExtenderPlugin INSTANCE = new ExtenderPlugin();

    private static Implementation plugin;

    /**
     * Create the instance.
     */
    public ExtenderPlugin() {
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
         * Log an error.
         * 
         * @param error
         *            the error message to log
         */
        public void logError(final String error) {
            logError(error, null);
        }

        /**
         * Log an error.
         * 
         * @param message
         *            the error message to log
         * @param throwable
         *            a low-level exception, or <code>null</code> if not
         *            applicable
         */
        public void logError(final String message, final Throwable throwable) {
            String err = message;
            if (message == null && throwable != null) {
                err = throwable.getMessage();
            }
            getLog().log(new Status(IStatus.ERROR, ID, IStatus.OK, err, throwable));
        }

        /**
         * Log an info.
         * 
         * @param message
         *            the info message
         */
        public void logInfo(final String message) {
            logInfo(message, null);
        }

        /**
         * Log an info.
         * 
         * @param message
         *            the info message
         * @param throwable
         *            a low-level exception, or <code>null</code> if not
         *            applicable
         */
        public void logInfo(final String message, final Throwable throwable) {
            String info = message;
            if (message == null && throwable != null) {
                info = throwable.getMessage();
            }
            getLog().log(new Status(IStatus.INFO, ID, IStatus.OK, info, throwable));
        }

        /**
         * Log a warning.
         * 
         * @param message
         *            the warning message.
         */
        public void logWarning(final String message) {
            logWarning(message, null);
        }

        /**
         * Log a warning.
         * 
         * @param message
         *            the warning message.
         * @param throwable
         *            a low-level exception, or <code>null</code> if not
         *            applicable
         */
        public void logWarning(final String message, final Throwable throwable) {
            String warning = message;
            if (message == null && throwable != null) {
                warning = throwable.getMessage();
            }
            getLog().log(new Status(IStatus.WARNING, ID, IStatus.OK, warning, throwable));
        }

    }

}
