/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * Extender plug-in.
 * 
 * @author mchauvin
 */
public class ExtenderPlugin extends Plugin {

    private static final String ID = "org.eclipse.sirius.ecore.extender"; //$NON-NLS-1$ 

    private static ExtenderPlugin instance;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        instance = this;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        instance = null;
        super.stop(context);
    }

    /**
     * Get the plugin instance.
     * 
     * @return the plugin instance
     */
    public static ExtenderPlugin getInstance() {
        return instance;
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
     *            a low-level exception, or <code>null</code> if not applicable
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
     *            a low-level exception, or <code>null</code> if not applicable
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
     *            a low-level exception, or <code>null</code> if not applicable
     */
    public void logWarning(final String message, final Throwable throwable) {
        String warning = message;
        if (message == null && throwable != null) {
            warning = throwable.getMessage();
        }
        getLog().log(new Status(IStatus.WARNING, ID, IStatus.OK, warning, throwable));
    }

}
