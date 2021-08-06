/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.model.tools.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.model.business.internal.migration.IMigrationHandler;

/**
 * Sirius plug-in.
 * 
 * @author ymortier
 */
public final class SiriusModelPlugin extends EMFPlugin {
    /**
     * Tell whether Eclipse is running or not.
     */
    public static final boolean IS_ECLIPSE_RUNNING;

    static {
        boolean result = false;
        // CHECKSTYLE:OFF
        try {
            result = Platform.isRunning();
        } catch (final Throwable exception) {
            // Assume that we aren't running.
        }
        // CHECKSTYLE:ON
        IS_ECLIPSE_RUNNING = result;
    }

    /** The id. */
    public static final String ID = "org.eclipse.sirius.model"; //$NON-NLS-1$

    /** Keep track of the singleton.. */
    public static final SiriusModelPlugin INSTANCE = new SiriusModelPlugin();

    private static Implementation plugin;

    /**
     * Creates the instance.
     */
    public SiriusModelPlugin() {
        super(new ResourceLocator[] { EcorePlugin.INSTANCE, SiriusModelPlugin.INSTANCE, });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getDefault() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipsePlugin {

        /**
         * Handler for migration.
         */
        private IMigrationHandler migrationHandler;

        /**
         * Creates an instance.
         */
        public Implementation() {
            super();
            plugin = this;
        }

        /**
         * Get the migration handler used for Description resource creation.
         *
         * @return the migration handler used for Description resource creation.
         */
        public IMigrationHandler getMigrationHandler() {
            return migrationHandler;
        }

        /**
         * Set the migration handler used for Description resource creation.
         * 
         * @param handler
         *            the migration handler.
         */
        public void setMigrationHandler(IMigrationHandler handler) {
            migrationHandler = handler;
        }

        /**
         * Logs an error in the error log.
         * 
         * @param message
         *            the message to log (optional).
         * @param e
         *            the exception (optional).
         */
        public void error(String message, final Throwable e) {
            log(message, e, IStatus.ERROR);
        }

        /**
         * Logs a warning in the error log.
         * 
         * @param message
         *            the message to log (optional).
         * @param e
         *            the exception (optional).
         */
        public void warning(String message, final Exception e) {
            log(message, e, IStatus.WARNING);
        }

        /**
         * Logs an info in the error log.
         * 
         * @param message
         *            the message to log (optional).
         * @param e
         *            the exception (optional).
         */
        public void info(String message, final Throwable e) {
            log(message, e, IStatus.INFO);
        }

        private void log(String message, final Throwable e, int severity) {
            String messageToDisplay = message;
            if (messageToDisplay == null && e != null) {
                messageToDisplay = e.getMessage();
            }
            if (e instanceof CoreException) {
                getLog().log(((CoreException) e).getStatus());
            } else {
                IStatus status = new Status(severity, getDefault().getSymbolicName(), messageToDisplay, e);
                getLog().log(status);
            }
        }

    }

}
