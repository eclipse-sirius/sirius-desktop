/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
public interface SessionManagerListener2 extends SessionManagerListener {

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
     * An empty implementation of SessionManagerListener2 for convenience.
     */
    public class Stub implements SessionManagerListener2 {

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener2#notify(org.eclipse.sirius.business.api.session.Session,
         *      int)
         */
        public void notify(final Session updated, final int notification) {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
         */
        public void notifyAddSession(final Session newSession) {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
         */
        public void notifyRemoveSession(final Session removedSession) {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
         */
        public void notifyUpdatedSession(final Session updated) {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.viewpoint.description.Viewpoint)
         */
        public void viewpointDeselected(final Viewpoint deselectedSirius) {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.viewpoint.description.Viewpoint)
         */
        public void viewpointSelected(final Viewpoint selectedSirius) {
        }

    }
}
