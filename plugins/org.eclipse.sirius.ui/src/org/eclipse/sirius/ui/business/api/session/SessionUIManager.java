/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import java.util.Collection;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.internal.session.SessionUIManagerImpl;

/**
 * Manages UI session.
 * 
 * @author cbrun
 */
public interface SessionUIManager {
    /**
     * global session ui manager handler.
     */
    SessionUIManager INSTANCE = SessionUIManagerImpl.init();

    /**
     * return the ui editing session corresponding to the session.
     * 
     * @param session
     *            a model session.
     * @return the ui editing session corresponding to the session.
     */
    IEditingSession getUISession(Session session);

    /**
     * return the ui editing session corresponding to the session or create one
     * if it's not available.
     * 
     * @param session
     *            a model session.
     * @return the ui editing session corresponding to the session.
     */
    IEditingSession getOrCreateUISession(Session session);

    /**
     * Create a new ui session from an existing session.
     * 
     * @param session
     *            existing session.
     * @return the newly created UI session.
     */
    IEditingSession createUISession(Session session);

    /**
     * Remove a session from the manager.
     * 
     * @param uiSession
     *            session to remove.
     */
    void remove(IEditingSession uiSession);

    /**
     * Returns all UI sessions.
     * 
     * @return all ui sessions.
     */
    Collection<IEditingSession> getUISessions();

}
