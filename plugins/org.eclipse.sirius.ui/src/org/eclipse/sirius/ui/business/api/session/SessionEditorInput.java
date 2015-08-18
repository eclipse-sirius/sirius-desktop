/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import java.lang.ref.WeakReference;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IMemento;

/**
 * Specific URI editor input providing the session data.
 * 
 * @author cbrun
 */
public class SessionEditorInput extends URIEditorInput {

    /**
     * Constant to store the uri of the main resource of the session. We can't
     * use the URI that is already store in the URIEditorInput because it can be
     * different in case of fragmentation.
     */
    private static final String SESSION_RESOURCE_URI = "SESSION_RESOURCE_URI"; //$NON-NLS-1$

    /**
     * Default editor name
     * 
     * @since 0.9.0
     */
    private static final String DEFAULT_EDITOR_NAME = "Default_Representation_Editor";

    private WeakReference<Session> sessionRef;

    /**
     * add a name field to override the {@link URIEditorInput} one's with
     * possibility to update it.
     */
    private String name;

    private URI sessionResourceURI;

    private WeakReference<EObject> inputRef;

    /**
     * Create a new SessionEditorInput with the current session and ui session.
     * 
     * @param uri
     *            element URI.
     * @param name
     *            name of the editor.
     * @param session
     *            the current session.
     */
    public SessionEditorInput(final URI uri, final String name, final Session session) {
        super(uri, name);
        this.name = name;
        this.sessionRef = new WeakReference<Session>(session);
        if (session.getSessionResource() != null) {
            this.sessionResourceURI = session.getSessionResource().getURI();
        }
    }

    /**
     * Create a new SessionEditorInput with a memento.
     * 
     * @param memento
     *            a bit of information kept by the platform.
     * @since 0.9.0
     */
    public SessionEditorInput(final IMemento memento) {
        super(memento);
    }

    /**
     * return the model editing session.
     * 
     * @return the model editing session.
     */
    public Session getSession() {
        return getSession(true);
    }

    /**
     * Return the model editing session.
     * 
     * @param restore
     *            true to restore the session if it is not instantiated or
     *            closed.
     * @return the model editing session.
     */
    public Session getSession(boolean restore) {
        Session session = sessionRef != null ? sessionRef.get() : null;
        // Avoid to create a new session if the default editor name is used: we
        // do not known yet for which representation the input is, like
        // in the GotoMarker case for example.
        if (session == null || (!session.isOpen() && !DEFAULT_EDITOR_NAME.equals(name))) {
            URI sessionModelURI = getURI().trimFragment();
            if (sessionResourceURI != null) {
                sessionModelURI = sessionResourceURI;
            }
            session = getSession(sessionModelURI, restore);
            if (session != null) {
                this.sessionRef = new WeakReference<Session>(session);
            }
        }
        return session;
    }

    /**
     * Get the input of this editor input.
     * 
     * @return the input of this editor input
     */
    public EObject getInput() {
        return getInput(true);
    }

    /**
     * Get the input of this editor input.
     * 
     * @param restore
     *            true to restore the input and associated session if they are
     *            not instantiated
     * @return the input of this editor input
     */
    private EObject getInput(boolean restore) {
        EObject input = inputRef != null ? inputRef.get() : null;
        if (input == null) {
            Session session = getSession(restore);
            if (session != null && session.isOpen() && getURI().hasFragment()) {
                input = session.getTransactionalEditingDomain().getResourceSet().getEObject(getURI(), false);
                if (input != null) {
                    inputRef = new WeakReference<EObject>(input);
                }
            }
        }
        return input;
    }

    @Override
    public String getName() {
        return name == null ? super.getName() : name;
    }

    /**
     * 
     * @param string
     */
    void setName(final String string) {
        this.name = string;
    }

    @Override
    public void saveState(final IMemento memento) {
        super.saveState(memento);
        memento.putString(NAME_TAG, getName());
        memento.putString(CLASS_TAG, getClass().getName());
        if (sessionResourceURI != null) {
            memento.putString(SESSION_RESOURCE_URI, sessionResourceURI.toString());
        }
    }

    @Override
    protected void loadState(final IMemento memento) {
        super.loadState(memento);
        setName(memento.getString(NAME_TAG));
        final String sessionResourceURIString = memento.getString(SESSION_RESOURCE_URI);
        if (sessionResourceURIString != null) {
            sessionResourceURI = URI.createURI(sessionResourceURIString);
            Session newSession = getSession(sessionResourceURI);
            if (newSession != null) {
                this.sessionRef = new WeakReference<Session>(newSession);
            }
        }
    }

    /**
     * Get the session.
     * 
     * @param sessionModelURI
     *            the Session Resource URI
     * @return the session if it can be found, <code>null</code> otherwise
     * 
     * @since 0.9.0
     */
    protected Session getSession(URI sessionModelURI) {
        return getSession(sessionModelURI, true);
    }

    /**
     * Get the session.
     * 
     * @param sessionModelURI
     *            the Session Resource URI
     * @param restore
     *            true to restore the session if it is not instantiated
     * @return the session if it can be found, <code>null</code> otherwise
     * 
     * @since 0.9.0
     */
    private Session getSession(URI sessionModelURI, boolean restore) {
        Session sessionFromURI;
        try {
            sessionFromURI = SessionManager.INSTANCE.getExistingSession(sessionModelURI);

            // A session adds and removes itself from the session manager during
            // open()/close()
            // If restore, we try to create a new one and open it only in this
            // case: the session lifecycle is not safe enough to try to open a
            // previously closed session.
            if (sessionFromURI == null && restore) {
                sessionFromURI = SessionManager.INSTANCE.getSession(sessionModelURI, new NullProgressMonitor());
                if (sessionFromURI != null && !sessionFromURI.isOpen()) {
                    sessionFromURI.open(new NullProgressMonitor());
                }
            }

            if (sessionFromURI != null && sessionFromURI.isOpen()) {
                // The SessionUIManager creates and open an IEditingSession when
                // a session is added to the SessionManager. This
                // IEditingSession is closed and removed from the ui manager
                // when the corresponding session is removed from the session
                // manager (closed).
                IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(sessionFromURI);
                if (uiSession == null && restore) {
                    uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(sessionFromURI);
                }
                if (uiSession != null && !uiSession.isOpen()) {
                    uiSession.open();
                }
            }
        } catch (IllegalStateException e) {
            sessionFromURI = null;
            // Silent catch: can happen if failing to retrieve the session from
            // its URI
        } catch (OperationCanceledException e) {
            sessionFromURI = null;
            // Silent catch: can happen if failing to retrieve the session from
            // its URI
        }
        return sessionFromURI;
    }

    @Override
    protected String getBundleSymbolicName() {
        return SiriusEditPlugin.getPlugin().getSymbolicName();
    }

    @Override
    public String getFactoryId() {
        return SessionEditorInputFactory.ID;
    }

    /**
     * Create a new input from an Analysis uri.
     * 
     * @param sessionResourceURI
     *            a session Resource URI.
     * @return a new SessionEditorinput.
     * 
     * @since 0.9.0
     */
    public static SessionEditorInput create(final URI sessionResourceURI) {
        Session session;
        try {
            session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        } catch (CoreException e) {
            return null;
        }
        return new SessionEditorInput(sessionResourceURI, DEFAULT_EDITOR_NAME, session);
    }

    @Override
    public String getToolTipText() {
        return getURI().trimFragment().toString() + "/" + getName(); //$NON-NLS-1$
    }

    /**
     * To avoid memory leak, the session is not kept during the closing of the
     * corresponding editor. Indeed, editorInput is kept by
     * {@link org.eclipse.ui.INavigationHistory} and
     * org.eclipse.ui.internal.EditorHistory. This method must not be called by
     * client, it is automatically called by the dispose of
     * {@link DDiagramEditor}.
     * 
     * @deprecated since a {@link org.eclipse.ui.IEditorInput} can be reused by
     *             several instances of {@link org.eclipse.ui.IEditorPart}
     *             through the navigation history view.
     */
    @Deprecated
    public void dispose() {
    }

    /**
     * Overridden to test input existence in a generic way.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean exists() {
        boolean exists = super.exists();
        if (!exists) {
            EObject input = getInput(false);
            if (input != null) {
                Resource resource = input.eResource();
                if (resource != null && resource.getResourceSet() != null) {
                    exists = resource.getResourceSet().getURIConverter().exists(resource.getURI(), null);
                }
            }
        }
        return exists;
    }

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
        Object a = super.getAdapter(adapter);
        if (IFile.class == adapter && a == null) {
            if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE) {
                Session inputSession = getSession();
                if (inputSession != null && inputSession.isOpen()) {
                    a = EclipseUtil.getAdatper(adapter, inputSession.getSessionResource().getURI());
                }
            }
        }
        return a;
    }

    @Override
    public int hashCode() {
        EObject input = getInput(false);
        if (input != null) {
            return input.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = super.equals(o);
        if (equals && o instanceof SessionEditorInput) {
            EObject input = getInput(false);
            if (input != null) {
                SessionEditorInput otherSessionEditorInput = (SessionEditorInput) o;
                return input.equals(otherSessionEditorInput.getInput(false));
            }
        }
        return equals;
    }
}
