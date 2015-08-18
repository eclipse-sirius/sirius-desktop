/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.session.factory.UISessionFactory;
import org.eclipse.sirius.ui.business.internal.session.factory.UISessionFactoryService;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Default session ui manager implementation.
 * 
 * @author cbrun
 * 
 */
public final class SessionUIManagerImpl extends SessionManagerListener.Stub implements SessionUIManager {

    private final Map<Session, IEditingSession> sessionToUISession = new HashMap<Session, IEditingSession>();

    private SessionUIManagerImpl() {
        SessionManager.INSTANCE.addSessionsListener(this);

        // Init UI sessions corresponding to already opened sessions.
        for (Session alreadyManagedSession : SessionManager.INSTANCE.getSessions()) {
            if (alreadyManagedSession.isOpen()) {
                createAndOpenUiSession(alreadyManagedSession);
            }
        }
    }

    /**
     * return a new default session ui manager.
     * 
     * @return a new default session ui manager.
     */
    public static SessionUIManager init() {
        return new SessionUIManagerImpl();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public IEditingSession createUISession(final Session session) {
        UISessionFactory uiSessionFactory = UISessionFactoryService.INSTANCE.getUISessionFactory();
        IEditingSession editingSession = uiSessionFactory.createUISession(session);
        sessionToUISession.put(session, editingSession);
        return editingSession;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public IEditingSession getUISession(final Session session) {
        final IEditingSession editingSession = sessionToUISession.get(session);
        return editingSession;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void remove(final IEditingSession uiSession) {
        final Collection<Session> toRemove = new ArrayList<Session>();
        for (final Map.Entry<Session, IEditingSession> entry : sessionToUISession.entrySet()) {
            if (entry.getValue() == uiSession) {
                toRemove.add(entry.getKey());
            }
        }
        for (final Session session : toRemove) {
            sessionToUISession.remove(session);
        }
    }

    public Collection<IEditingSession> getUISessions() {
        return sessionToUISession.values();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
     */
    @Override
    public void notifyAddSession(final Session newSession) {
        cleanUISessions();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
     */
    @Override
    public void notifyRemoveSession(final Session removedSession) {
        cleanUISessions();
    }

    @Override
    public void notify(Session updated, int notification) {
        super.notify(updated, notification);

        switch (notification) {
        case SessionListener.OPENED:
            createAndOpenUiSession(updated);
            break;
        case SessionListener.CLOSING:
            IEditingSession uiSession = getUISession(updated);
            if (uiSession != null) {
                uiSession.close(false);
            }
            break;
        default:
            // do nothing as we will be notified in other way
            break;
        }
        cleanUISessions();
    }

    /**
     * Cleans all sessions.
     */
    protected void cleanUISessions() {
        final List<Session> sessionToRemove = new LinkedList<Session>();
        for (final Map.Entry<Session, IEditingSession> mapping : new ArrayList<Map.Entry<Session, IEditingSession>>(sessionToUISession.entrySet())) {
            if (mapping.getKey() == null || !mapping.getKey().isOpen()) {
                sessionToRemove.add(mapping.getKey());
                // Disposes the UI Session.
                mapping.getValue().close();
            }
        }
        for (final Session session : sessionToRemove) {
            this.sessionToUISession.remove(session);
        }
    }

    private void createAndOpenUiSession(final Session openedSession) {
        final IEditingSession uiSession = getOrCreateUISession(openedSession);
        if (uiSession != null) {
            uiSession.open();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.session.SessionUIManager#findUISession(org.eclipse.ui.IEditorInput)
     */
    public IEditingSession findUISession(final IEditorInput editorInput) {
        IEditingSession session = null;
        for (final IEditingSession editingSession : this.getUISessions()) {
            if (editingSession.isSessionFor(editorInput)) {
                session = editingSession;
            }
        }
        if (session == null) {
            keyFromInput(editorInput);
        }
        return session;
    }

    /**
     * Returns a key with the editor input.
     * 
     * @param input
     *            the input.
     * @return a key with the editor input.
     */
    private String keyFromInput(final IEditorInput input) {
        final String separator = "/"; //$NON-NLS-1$
        String result = null;
        if (input instanceof URIEditorInput) {
            final URI uri = ((URIEditorInput) input).getURI();
            final URI deresolved = uri.deresolve(URI.createURI("platform:/resource/")); //$NON-NLS-1$
            final String workspacePAth = separator + deresolved.path();
            final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePAth));
            if (file != null && file.isAccessible() && file.exists()) {
                String filePath = file.getLocation().toString();
                if (filePath.startsWith(separator)) {
                    filePath = filePath.substring(1);
                }
                result = filePath;
            }
            if (result == null) {
                String path = uri.path();
                /*
                 * On windows we get some "/C:" uri ! remove that leading "/"
                 */
                if (path.startsWith(separator)) {
                    path = path.substring(1);
                }
                result = path;
            }
        }
        if (result == null && input instanceof IPathEditorInput) {
            result = ((IPathEditorInput) input).getPath().toString();
        }
        if (result == null && input instanceof FileEditorInput) {
            final java.net.URI uri = ((FileEditorInput) input).getURI();
            String path = uri.getPath();
            /*
             * On windows we get some "/C:" uri ! remove that leading "/"
             */
            if (path.startsWith(separator)) {
                path = path.substring(1);
            }
            result = path;
        }
        if (result == null) {
            result = input.toString();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.viewpoint.description.Viewpoint)
     */
    @Override
    public void viewpointDeselected(final Viewpoint deselectedSirius) {
        // does nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.viewpoint.description.Viewpoint)
     */
    @Override
    public void viewpointSelected(final Viewpoint selectedSirius) {
        // does nothing.
    }

    /**
     * 
     * {@inheritDoc}
     */
    public IEditingSession getOrCreateUISession(final Session session) {
        IEditingSession uiSession = getUISession(session);
        if (uiSession == null) {
            uiSession = createUISession(session);
        }
        return uiSession;
    }

}
