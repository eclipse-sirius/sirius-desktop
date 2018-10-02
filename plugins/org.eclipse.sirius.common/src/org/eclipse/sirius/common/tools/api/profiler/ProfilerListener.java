/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.profiler;

/**
 * Listen a profiler.
 * 
 * @author ymortier
 */
public interface ProfilerListener {

    /**
     * This method is invoked when a task begins.
     * 
     * @param event
     *            the event.
     */
    void taskStarted(ProfilerEvent event);

    /**
     * This method is invoked when a task ends.
     * 
     * @param event
     *            the event.
     */
    void taskStopped(ProfilerEvent event);

    /**
     * This method is invoked when the profiler is reinited.
     * 
     * @param event
     *            the event.
     */
    void profilerReinited(ProfilerEvent event);

}
