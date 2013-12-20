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
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author pcdavid
 */
public class SequenceDiagramPlugin extends AbstractUIPlugin {
    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.eclipse.sirius.diagram.sequence.ui";

    /**
     * The shared instance.
     */
    private static SequenceDiagramPlugin plugin;

    /**
     * The constructor.
     */
    public SequenceDiagramPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static SequenceDiagramPlugin getDefault() {
        return plugin;
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
