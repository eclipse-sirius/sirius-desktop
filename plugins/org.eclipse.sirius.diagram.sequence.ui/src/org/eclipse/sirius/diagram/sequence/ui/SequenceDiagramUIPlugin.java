/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author pcdavid
 */
public class SequenceDiagramUIPlugin extends EMFPlugin {
    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.diagram.sequence.ui"; //$NON-NLS-1$

    /**
     * Keep track of the singleton.
     */
    public static final SequenceDiagramUIPlugin INSTANCE = new SequenceDiagramUIPlugin();

    /**
     * Keep track of the singleton.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public SequenceDiagramUIPlugin() {
        super(new ResourceLocator[0]);
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
            super();
            plugin = this;
        }

        /**
         * Logs a warning.
         * 
         * @param message
         *            the message.
         * @param e
         *            the exception.
         */
        public void warning(String message, EvaluationException e) {
            IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, message, e);
            getLog().log(status);
        }

        /**
         * Logs an error.
         * 
         * @param message
         *            the message.
         * @param e
         *            the exception.
         */
        public void error(String message, EvaluationException e) {
            IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, e);
            getLog().log(status);
        }

    }

}
