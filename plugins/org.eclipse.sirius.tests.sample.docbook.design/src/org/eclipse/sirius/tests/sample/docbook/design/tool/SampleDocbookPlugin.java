/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.docbook.design.tool;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.BundleContext;

public class SampleDocbookPlugin extends Plugin {
    /** The plugin id. */
    public static final String PLUGIN_ID = "org.eclipse.sirius.tests.sample.docbook.design";

    // The shared instance
    private static SampleDocbookPlugin plugin;

    private static Set<Viewpoint> viewpoints;

    /**
     * The constructor.
     */
    public SampleDocbookPlugin() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        viewpoints = new HashSet<Viewpoint>();
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/model/docbook.odesign"));
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/model/docbook_extended.odesign"));

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        if (viewpoints != null) {
            for (final Viewpoint viewpoint : viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            viewpoints.clear();
            viewpoints = null;
        }
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static SampleDocbookPlugin getDefault() {
        return plugin;
    }

    /**
     * Logs the given message and throwable as an error.
     * 
     * @param message
     *            the message.
     * @param t
     *            the exception.
     */
    public void error(final String message, final Throwable t) {
        final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
        this.getLog().log(status);
    }

    /**
     * Logs the given message and throwable as a warning.
     * 
     * @param message
     *            the message.
     * @param t
     *            the exception.
     */
    public void warning(final String message, final Throwable t) {
        final IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, message, t);
        this.getLog().log(status);
    }

    /**
     * Logs the given message as an information.
     * 
     * @param message
     *            the message.
     */
    public void info(final String message) {
        final IStatus status = new Status(IStatus.INFO, PLUGIN_ID, message);
        this.getLog().log(status);
    }
}
