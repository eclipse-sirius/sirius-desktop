/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * Sirius Diagram plug-in.
 * 
 * @was-generated
 */
public class DiagramPlugin extends Plugin {

    /** The id. */
    public static final String ID = "org.eclipse.sirius.diagram"; //$NON-NLS-1$

    /** The shared instance. */
    private static DiagramPlugin defaultPlugin;

    /**
     * Creates a new plugin.
     * 
     * @was-generated
     */
    public DiagramPlugin() {
        defaultPlugin = this;
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static DiagramPlugin getDefault() {
        return DiagramPlugin.defaultPlugin;
    }

    /**
     * Returns string from plug-in's resource bundle.
     * 
     * @param key
     *            the property name
     * @return the corresponding property value
     * @was-generated
     */
    public static String getString(String key) {
        return Platform.getResourceString(getDefault().getBundle(), "%" + key); //$NON-NLS-1$
    }

    /**
     * Logs an error.
     * 
     * @param error
     *            the error message.
     */
    public void logError(String error) {
        logError(error, null);
    }

    /**
     * Logs an error.
     * 
     * @param error
     *            the error message.
     * @param throwable
     *            the throwable.
     */
    public void logError(String error, Throwable throwable) {
        logMessage(error, throwable, IStatus.ERROR);
    }

    /**
     * Logs an info.
     * 
     * @param message
     *            the info message.
     */
    public void logInfo(String message) {
        logInfo(message, null);
    }

    /**
     * Logs an info.
     * 
     * @param message
     *            the info message.
     * @param throwable
     *            the throwable.
     */
    public void logInfo(String message, Throwable throwable) {
        logMessage(message, throwable, IStatus.INFO);
    }

    /**
     * Logs a warning.
     * 
     * @param message
     *            the warning message.
     */
    public void logWarning(String message) {
        logWarning(message, null);
    }

    /**
     * Logs a warning.
     * 
     * @param message
     *            the warning message.
     * @param throwable
     *            the throwable.
     */
    public void logWarning(String message, Throwable throwable) {
        logMessage(message, throwable, IStatus.WARNING);
    }

    private void logMessage(String message, Throwable throwable, int code) {
        String msg = message;
        if (message == null && throwable != null) {
            msg = throwable.getMessage();
        }

        getLog().log(new Status(code, DiagramPlugin.ID, IStatus.OK, msg, throwable));
        debug(msg, throwable);
    }

    /**
     * @was-generated
     */
    private void debug(String message, Throwable throwable) {
        if (!isDebugging()) {
            return;
        }
        // CHECKSTYLE:OFF
        if (message != null) {
            System.err.println(message);
        }
        if (throwable != null) {
            throwable.printStackTrace();
        }
        // CHECKSTYLE:ON
    }

}
