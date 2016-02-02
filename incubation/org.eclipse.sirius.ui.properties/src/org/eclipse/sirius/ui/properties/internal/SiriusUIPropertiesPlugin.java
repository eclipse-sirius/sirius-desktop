/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.osgi.framework.BundleContext;

/**
 * The plugin for <code>org.eclipse.sirius.ui.properties</code>.
 * 
 * @author pcdavid
 * @author sbegaudeau
 */
public class SiriusUIPropertiesPlugin extends EMFPlugin {
    /**
     * The identifier of the plugin.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.ui.properties";

    /**
     * The sole instance of the plugin.
     */
    public static final SiriusUIPropertiesPlugin INSTANCE = new SiriusUIPropertiesPlugin();

    /**
     * The sole instance of the bundle activator.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public SiriusUIPropertiesPlugin() {
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
         * The adapter is stateless, use a single instance that can be easily
         * unregistered when stopped.
         */
        private final SiriusSemanticAdapter adapterFactory = new SiriusSemanticAdapter();

        /**
         * The constructor.
         */
        public Implementation() {
            super();
            SiriusUIPropertiesPlugin.plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            IAdapterManager adapterManager = Platform.getAdapterManager();
            adapterManager.registerAdapters(adapterFactory, DSemanticDecorator.class);
            adapterManager.registerAdapters(adapterFactory, GraphicalEditPart.class);
            adapterManager.registerAdapters(adapterFactory, ConnectionEditPart.class);
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            IAdapterManager adapterManager = Platform.getAdapterManager();
            adapterManager.unregisterAdapters(adapterFactory);
        }

        /**
         * Logs the status.
         * 
         * @param severity
         *            The severity of the status
         * @param message
         *            The message to log or <code>null</code>. If the message is
         *            <code>null</code>, the message of the exception will be
         *            used instead
         * @param exception
         *            The exception to log
         */
        private void doLog(int severity, String message, Exception exception) {
            String messageToLog = message;
            if (messageToLog == null && exception != null) {
                messageToLog = exception.getMessage();
            }
            IStatus status = new Status(severity, PLUGIN_ID, messageToLog, exception);
            this.getLog().log(status);
        }

        /**
         * Logs an error with the exception and the given message.
         *
         * @param message
         *            The message
         * @param exception
         *            The exception
         */
        public void error(String message, Exception exception) {
            if (exception instanceof CoreException) {
                this.getLog().log(((CoreException) exception).getStatus());
            } else {
                this.doLog(IStatus.ERROR, message, exception);
            }
        }

        /**
         * Logs a warning with the exception and the given message.
         *
         * @param message
         *            The message
         * @param exception
         *            The exception
         */
        public void warning(String message, Exception exception) {
            if (exception instanceof CoreException) {
                this.getLog().log(((CoreException) exception).getStatus());
            } else {
                this.doLog(IStatus.WARNING, message, exception);
            }
        }

        /**
         * Logs an info with the exception and the given message.
         *
         * @param message
         *            The message
         * @param exception
         *            The exception
         */
        public void info(String message, Exception exception) {
            if (exception instanceof CoreException) {
                this.getLog().log(((CoreException) exception).getStatus());
            } else {
                this.doLog(IStatus.INFO, message, exception);
            }
        }

    }

}
