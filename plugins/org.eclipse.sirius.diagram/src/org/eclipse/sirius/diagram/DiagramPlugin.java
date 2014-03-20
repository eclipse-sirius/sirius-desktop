/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
import org.eclipse.core.runtime.Status;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

//CHECKSTYLE:OFF
/**
 * @was-generated NOT
 */
public class DiagramPlugin extends AbstractUIPlugin {

    /**
     * @was-generated
     */
    public static final String ID = "org.eclipse.sirius.diagram"; //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final PreferencesHint DIAGRAM_PREFERENCES_HINT = new PreferencesHint(ID);

    /**
     * @was-generated
     */
    private static DiagramPlugin instance;

    /**
     * @was-generated
     */

    /**
     * @was-generated
     */
    public DiagramPlugin() {
    }

    /**
     * @was-generated
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
    }

    /**
     * @was-generated
     */
    public void stop(BundleContext context) throws Exception {
        instance = null;
    }

    /**
     * @was-generated
     */
    public static DiagramPlugin getInstance() {
        return instance;
    }

    /**
     * Returns string from plug-in's resource bundle
     * 
     * @was-generated
     */
    public static String getString(String key) {
        return Platform.getResourceString(getInstance().getBundle(), "%" + key); //$NON-NLS-1$
    }

    /**
     * @was-generated
     */
    public void logError(String error) {
        logError(error, null);
    }

    /**
     * @was-generated
     */
    public void logError(String error, Throwable throwable) {
        if (error == null && throwable != null) {
            error = throwable.getMessage();
        }
        getLog().log(new Status(IStatus.ERROR, DiagramPlugin.ID, IStatus.OK, error, throwable));
        debug(error, throwable);
    }

    /**
     * @was-generated
     */
    public void logInfo(String message) {
        logInfo(message, null);
    }

    /**
     * @was-generated
     */
    public void logInfo(String message, Throwable throwable) {
        if (message == null && throwable != null) {
            message = throwable.getMessage();
        }
        getLog().log(new Status(IStatus.INFO, DiagramPlugin.ID, IStatus.OK, message, throwable));
        debug(message, throwable);
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
        if (message == null && throwable != null) {
            message = throwable.getMessage();
        }
        getLog().log(new Status(IStatus.WARNING, DiagramPlugin.ID, IStatus.OK, message, throwable));
        debug(message, throwable);
    }

    /**
     * @was-generated
     */
    private void debug(String message, Throwable throwable) {
        if (!isDebugging()) {
            return;
        }
        if (message != null) {
            System.err.println(message);
        }
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
    // CHECKSTYLE:ON
}
