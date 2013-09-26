/*******************************************************************************
 * Copyright (c) 2008, 2008 THALES GLOBAL SERVICES.
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
 * Listener for SessionManager, see {@link SessionManagerListener2}.
 * 
 * @author cbrun
 */
@Deprecated
public interface SessionManagerListener {

    /** The extension point ID. */
    String ID = "org.eclipse.sirius.sessionManagerListener";

    /** The class attribute. */
    String CLASS_ATTRIBUTE = "class";

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
     * A session handled with the manager has been updated.
     * 
     * @param updated
     *            the session that changed.
     * @see SessionManagerListener2#notify(Session, int)
     */
    @Deprecated
    void notifyUpdatedSession(Session updated);

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
}
