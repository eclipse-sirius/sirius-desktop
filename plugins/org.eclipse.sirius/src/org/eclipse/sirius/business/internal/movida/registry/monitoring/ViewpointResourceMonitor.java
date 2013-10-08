/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida.registry.monitoring;

/**
 * A monitor that can discover resources which may contain Sirius
 * definitions, and detect changes in those resources. Concrete implementations
 * are specialized in specific sources of such resources (i.e. the workspace or
 * plug-ins).
 * <p>
 * When created, a monitor is initially inactive. It must be configured by
 * setting the {{@link #setListener(ViewpointResourceListener) listener} to
 * notify before it can be {{@link #start() started}.
 * <p>
 * Notifications may be sent to the listener synchronously right during the call
 * to {@link #start()} or asynchronously, depending on the implementation.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public interface ViewpointResourceMonitor {
    /**
     * The listener to notify when a change is detected. May be
     * <code>null</code>, but any change detected while there is no valid
     * listener is lost.
     * 
     * @param listener
     *            the listener to notify.
     */
    void setListener(ViewpointResourceListener listener);

    /**
     * Starts the monitor if it is not already running. Does nothing if it was
     * already running. Re-starting a monitor which was previously
     * {@link #stop() stopped} has the same effect as starting a new instance
     * from scratch; in particular, it will re-discover all the existing
     * resources as if they were just added.
     */
    void start();

    /**
     * Stops the monitor if it is running. Does nothing if its was already
     * stopped.
     */
    void stop();

    /**
     * Tests whether the monitor is actively monitoring its source.
     * 
     * @return <code>true</code> if this monitor is running.
     */
    boolean isRunning();
}
