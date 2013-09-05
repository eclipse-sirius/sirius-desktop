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
package org.eclipse.sirius.business.api.session;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.sirius.business.internal.session.SessionManagerImpl;

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
     * This method should not be called by client. It must be automatically
     * called by {@link Session#open(IProgressMonitor))}.
     * 
     * @param newSession
     *            session to add.
     */
    void add(Session newSession);

    /**
     * Remove an existing session.<BR>
     * This method should not be called by client. It must be automatically
     * called by {@link Session#close()}.
     * 
     * @param removedSession
     *            session to remove.
     */
    void remove(Session removedSession);

    /**
     * Add a new resource in the session notifying all the manager listeners.
     * 
     * @param session
     *            session in which we'll add the resource.
     * @param newResource
     *            resource to add.
     * 
     * @deprecated since 4.0 add
     *             {@link Session#addSemanticResource(URI, org.eclipse.core.runtime.IProgressMonitor)}
     *             instead to add semantic Resource.
     */
    void addResource(Session session, Resource newResource);

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
     * @since 2.13
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
     * Try to return the session corresponding to an {@link EObject}.
     * 
     * @param any
     *            semantic EObject.
     * @return the corresponding session.
     */
    Session getSession(EObject any);

    /**
     * Try to return the session corresponding to an {@link EObject}.
     * 
     * @param semanticResource
     *            a semantic Resource.
     * @return the corresponding session.
     */
    Session getSession(Resource semanticResource);

    /**
     * Return the {@link Session} whose Resource's URI correspond to
     * sessionResourceURI. If the Resource has already been loaded returns the
     * existing Session else try to load it.
     * 
     * @param sessionResourceURI
     *            a session Resource {@link URI}
     * @return the corresponding session if exist.
     * 
     * @since 4.0
     * @deprecated use {@link SessionManager#getSession(URI, IProgressMonitor)}
     *             instead
     */
    Session getSession(URI sessionResourceURI);

    /**
     * Return the {@link Session} whose Resource's URI correspond to
     * sessionResourceURI. If the Resource has already been loaded returns the
     * existing Session else try to load it.
     * 
     * @param sessionResourceURI
     *            a session Resource {@link URI}
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Session
     *            getting, especially if there is a resource loading
     * @return the corresponding session if exist.
     */
    Session getSession(URI sessionResourceURI, IProgressMonitor monitor);

    /**
     * Return the {@link Session} whose Resource's URI correspond to
     * sessionResourceURI. If the Resource has already been loaded returns the
     * existing Session else return null.
     * 
     * @param sessionResourceURI
     *            a session Resource {@link URI}
     * @return the corresponding session if exist.
     * 
     * @since 4.0
     */
    Session getExistingSession(URI sessionResourceURI);

}
