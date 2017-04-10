/*******************************************************************************
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.session;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.internal.session.SessionManagerImpl;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;

/**
 * The {@link SessionManager} is responsible for a set of sessions.
 * 
 * @author cbrun
 */
public interface SessionManager {
    /**
     * Default session manager instance.
     */
    SessionManager INSTANCE = SessionManagerImpl.init();

    /**
     * Add a new listener for the session manager.
     * 
     * @param listener
     *            new listener to add.
     */
    void addSessionsListener(SessionManagerListener listener);

    /**
     * Remove the given listener.
     * 
     * @param listener
     *            listener to remove.
     */
    void removeSessionsListener(SessionManagerListener listener);

    /**
     * return the sessions currently active.
     * 
     * @return the sessions currently active.
     */
    Collection<Session> getSessions();

    /**
     * Add a new session.<BR>
     * This method should not be called by client. It must be automatically called by
     * {@link Session#open(IProgressMonitor))}.
     * 
     * @param newSession
     *            session to add.
     */
    void add(Session newSession);

    /**
     * Remove an existing session.<BR>
     * This method should not be called by client. It must be automatically called by
     * {@link Session#close(IProgressMonitor)}.
     * 
     * @param removedSession
     *            session to remove.
     */
    void remove(Session removedSession);

    /**
     * Notify the session manager listeners of a Representation creation.
     * 
     * @param session
     *            the session in which creation occurs
     */
    void notifyRepresentationCreated(Session session);

    /**
     * Notify the session manager listeners of a Representation renamed.
     * 
     * @param session
     *            the session in which rename occurs
     * @since 0.9.0
     */
    void notifyRepresentationRenamed(Session session);

    /**
     * Notify the session manager listeners of a Representation deletion.
     * 
     * @param session
     *            the session in which deletion occurs
     */
    void notifyRepresentationDeleted(Session session);

    /**
     * Try to return the session corresponding to a semantic {@link EObject}.
     * 
     * @param any
     *            semantic EObject.
     * @return the corresponding session.
     */
    Session getSession(EObject any);

    /**
     * Try to return the session corresponding to a semantic {@link Resource}.
     * 
     * @param semanticResource
     *            a semantic Resource.
     * @return the corresponding session.
     */
    Session getSession(Resource semanticResource);

    /**
     * Return the {@link Session} whose Resource's URI correspond to sessionResourceURI. If the Resource has already
     * been loaded returns the existing Session else try to load it.
     * 
     * @param sessionResourceURI
     *            a session Resource {@link URI}
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Session getting, especially if there is a resource
     *            loading
     * @return the corresponding session if exist.
     */
    Session getSession(URI sessionResourceURI, IProgressMonitor monitor);

    /**
     * Return the {@link Session} whose Resource's URI correspond to sessionResourceURI. If the Resource has already
     * been loaded returns the existing Session else return null.
     * 
     * @param sessionResourceURI
     *            a session Resource {@link URI}
     * @return the corresponding session if exist.
     * 
     * @since 0.9.0
     */
    Session getExistingSession(URI sessionResourceURI);

    /**
     * Try to open a session. If there is a version mismatch, the user may be asked if he wants to open the session
     * anyway.
     * 
     * @param sessionResourceURI
     *            a session Resource {@link URI}
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Session getting, especially if there is a resource
     *            loading
     * @param uiCallback
     *            used to let the user choose if he wants to open the session anyway in case of version mismatch
     * @return the opened session
     */
    Session openSession(URI sessionResourceURI, IProgressMonitor monitor, UICallBack uiCallback);

}
