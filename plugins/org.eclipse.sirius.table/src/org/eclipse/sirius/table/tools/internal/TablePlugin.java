/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.tools.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * Table plug-in.
 * 
 * @author mchauvin
 */
public class TablePlugin extends Plugin {

    /** The shared instance */
    private static TablePlugin plugin;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static TablePlugin getDefault() {
        return plugin;
    }

    /**
     * Logs an error in the error log.
     * 
     * @param message
     *            the message to log (optional).
     * @param e
     *            the exception (optional).
     */
    public void error(final String message, final Exception e) {
        String msgToDisplay = message;
        if (message == null && e != null) {
            msgToDisplay = e.getMessage();
        }
        if (e instanceof CoreException) {
            this.getLog().log(((CoreException) e).getStatus());
        } else {
            final IStatus status = new Status(IStatus.ERROR, this.getBundle().getSymbolicName(), msgToDisplay, e);
            this.getLog().log(status);
        }
    }

}
