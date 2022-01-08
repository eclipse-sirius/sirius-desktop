/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.session;

import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Listener for SessionManager easier to leverage.
 * 
 * @author cbrun
 */
public interface SessionManagerListener {

    /** The extension point ID. */
    String ID = "org.eclipse.sirius.sessionManagerListener"; //$NON-NLS-1$

    /** The class attribute. */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Called when a new session has been added in the manager.
     * 
     * @param newSession
     *            the new session.
     */
    void notifyAddSession(Session newSession);

    /**
     * Called when a session has been removed from the manager.
     * 
     * @param removedSession
     *            the old session.
     */
    void notifyRemoveSession(Session removedSession);

    /**
     * Invoked when a viewpoint is selected.
     * 
     * @param selectedSirius
     *            the selected viewpoint.
     */
    void viewpointSelected(Viewpoint selectedSirius);

    /**
     * Invoked when a viewpoint is deselected.
     * 
     * @param deselectedSirius
     *            the deselected viewpoint.
     */
    void viewpointDeselected(Viewpoint deselectedSirius);

    /**
     * A session handled with the manager notify its clients about a change.
     * 
     * @param updated
     *            the session that changed.
     * @param notification
     *            the kind of notification received from the session, value
     *            coming from {@link SessionListener}
     */
    void notify(Session updated, int notification);

    /**
     * An empty implementation of SessionManagerListener for convenience.
     */
    class Stub implements SessionManagerListener {
        @Override
        public void notify(final Session updated, final int notification) {
        }

        @Override
        public void notifyAddSession(final Session newSession) {
        }

        @Override
        public void notifyRemoveSession(final Session removedSession) {
        }

        @Override
        public void viewpointDeselected(final Viewpoint deselectedSirius) {
        }

        @Override
        public void viewpointSelected(final Viewpoint selectedSirius) {
        }

    }
}
