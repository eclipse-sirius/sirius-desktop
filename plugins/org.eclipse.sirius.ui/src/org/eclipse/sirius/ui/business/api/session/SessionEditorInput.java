/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.business.api.session;

import java.lang.ref.WeakReference;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IMemento;

/**
 * Specific URI editor input providing the session data.
 *
 * @author cbrun
 */
public class SessionEditorInput extends URIEditorInput {

    /**
     * Constant to store the uri of the main resource of the session. We can't use the URI that is already store in the
     * URIEditorInput because it can be different in case of fragmentation.
     */
    private static final String SESSION_RESOURCE_URI = "SESSION_RESOURCE_URI"; //$NON-NLS-1$

    private static final String REP_DESC_URI = "REP_DESC_URI"; //$NON-NLS-1$

    private WeakReference<Session> sessionRef;

    /**
     * add a name field to override the {@link URIEditorInput} one's with possibility to update it.
     */
    private String name;

    private URI sessionResourceURI;

    private WeakReference<EObject> inputRef;

    private IStatus status;

    private URI repDescURI;

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
     * Create a new SessionEditorInput with the current session and ui session.
     *
     * @param uri
     *            element URI.
     * @param repDescURI
     *            The URI of the {@link DRepresentationDescriptor}. This URI is Used for loading the
     *            {@link DRepresentation} from the {@link DRepresentationDescriptor#getRepresentation()}. Can be null.
     * @param name
     *            name of the editor.
     * @param session
     *            the current session.
     */
    public SessionEditorInput(URI uri, URI repDescURI, String name, Session session) {
        this(uri, name, session);
        this.repDescURI = repDescURI;

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
     *            true to restore the session if it is not instantiated or closed.
     * @return the model editing session.
     */
    public Session getSession(boolean restore) {
        Session session = sessionRef != null ? sessionRef.get() : null;
        // Avoid to create a new session if the default editor name is used: we
        // do not known yet for which representation the input is, like
        // in the GotoMarker case for example.
        if (session == null || (!session.isOpen() && !Messages.SessionEditorInput_defaultEditorName.equals(name))) {
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
     *            true to restore the input and associated session if they are not instantiated
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

    void setName(final String string) {
        this.name = string;
    }

    public URI getRepDescUri() {
        return repDescURI;
    }

    public void setRepDescURI(URI repDescURI) {
        this.repDescURI = repDescURI;
    }

    @Override
    public void saveState(final IMemento memento) {
        super.saveState(memento);
        memento.putString(URIEditorInput.URI_TAG, getURI().toString());
        memento.putString(URIEditorInput.NAME_TAG, getName());
        memento.putString(URIEditorInput.CLASS_TAG, getClass().getName());
        if (sessionResourceURI != null) {
            memento.putString(SessionEditorInput.SESSION_RESOURCE_URI, sessionResourceURI.toString());
        }
        if (repDescURI != null) {
            memento.putString(SessionEditorInput.REP_DESC_URI, repDescURI.toString());
        }
    }

    @Override
    protected void loadState(final IMemento memento) {
        super.loadState(memento);
        setName(memento.getString(URIEditorInput.NAME_TAG));
        final String sessionResourceURIString = memento.getString(SessionEditorInput.SESSION_RESOURCE_URI);
        final String repDescURIString = memento.getString(SessionEditorInput.REP_DESC_URI);
        if (sessionResourceURIString != null) {
            sessionResourceURI = URI.createURI(sessionResourceURIString);
            Session newSession = getSession(sessionResourceURI);
            if (newSession != null) {
                this.sessionRef = new WeakReference<Session>(newSession);
            }
        }
        if (repDescURIString != null) {
            repDescURI = URI.createURI(repDescURIString);
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
                status = Status.OK_STATUS;
            }
            sessionFromURI = openSession(sessionModelURI, restore, sessionFromURI);
        } catch (OperationCanceledException e) {
            sessionFromURI = null;
            status = new Status(IStatus.CANCEL, SiriusEditPlugin.ID, e.getLocalizedMessage(), e); // $NON-NLS-1$
            // Silent catch: can happen if failing to retrieve the session from
            // its URI
            // CHECKSTYLE:OFF
        } catch (RuntimeException e) {
            // CHECKSTYLE:ON
            sessionFromURI = null;
            status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e); // $NON-NLS-1$
            // Silent catch: can happen if failing to retrieve the session from
            // its URI
        }
        return sessionFromURI;
    }

    private static Session openSession(URI sessionModelURI, boolean restore, Session sessionFromURI) {
        Session session = sessionFromURI;
        if (session == null && restore) {
            session = SessionManager.INSTANCE.openSession(sessionModelURI, new NullProgressMonitor(), SiriusEditPlugin.getPlugin().getUiCallback(), true);
        }

        if (session != null && session.isOpen()) {
            // The SessionUIManager creates and open an IEditingSession when
            // a session is added to the SessionManager. This
            // IEditingSession is closed and removed from the ui manager
            // when the corresponding session is removed from the session
            // manager (closed).
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            if (uiSession == null && restore) {
                uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
            }
            if (uiSession != null && !uiSession.isOpen()) {
                uiSession.open();
            }
        }
        return session;
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
        // Use the existing session if there is one
        Session session = SessionManager.INSTANCE.getExistingSession(sessionResourceURI);
        if (session == null) {
            session = SessionEditorInput.openSession(sessionResourceURI, true, null);
        }
        return new SessionEditorInput(sessionResourceURI, Messages.SessionEditorInput_defaultEditorName, session);
    }

    @Override
    public String getToolTipText() {
        return getURI().trimFragment().toString() + "/" + getName(); //$NON-NLS-1$
    }

    /**
     * To avoid memory leak, the session is not kept during the closing of the corresponding editor. Indeed, editorInput
     * is kept by {@link org.eclipse.ui.INavigationHistory} and org.eclipse.ui.internal.EditorHistory. This method must
     * not be called by client, it is automatically called by the dispose of {@link DDiagramEditor}.
     *
     * @deprecated since a {@link org.eclipse.ui.IEditorInput} can be reused by several instances of
     *             {@link org.eclipse.ui.IEditorPart} through the navigation history view.
     */
    @Deprecated
    public void dispose() {
    }

    @Override
    public URI getURI() {
        EObject input = inputRef != null ? inputRef.get() : null;
        if (input != null) {
            try {
                return EcoreUtil.getURI(input);
                // TODO: remove this try/catch WORK-AROUND when 392720 will be
                // fixed
            } catch (IllegalStateException e) {

            }
        }
        return super.getURI();
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
            if (input != null && !input.eIsProxy()) {
                Resource resource = input.eResource();
                if (resource != null && resource.getResourceSet() != null) {
                    exists = resource.getResourceSet().getURIConverter().exists(resource.getURI(), null);
                }
            }
        }
        return exists;
    }

    /**
     * Get the status of the session opening from this {@link SessionEditorInput}.
     * 
     * <ul>
     * <li>A status with severity {@link IStatus#CANCEL} is returned for a session opening canceled through
     * {@link OperationCanceledException}.</li>
     * <li>A status with severity {@link IStatus#ERROR} is returned for a session opening failing because of another
     * {@link RuntimeException}.</li>
     * <li>Otherwise a status with severity {@link IStatus#OK} is returned.</li>
     * </ul>
     * 
     * @return the status of the session opening, null is never returned
     */
    public IStatus getStatus() {
        if (status == null) {
            status = Status.OK_STATUS;
        }
        return status;
    }

    @Override
    public Object getAdapter(Class adapter) {
        Object a = super.getAdapter(adapter);
        if (IFile.class == adapter && a == null) {
            if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE) {
                Session inputSession = getSession(false);
                if (inputSession != null && inputSession.isOpen()) {
                    a = EclipseUtil.getAdapter(adapter, inputSession.getSessionResource().getURI());
                } else if (sessionResourceURI != null) {
                    a = EclipseUtil.getAdapter(adapter, sessionResourceURI);
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
        boolean equals = this == o || o instanceof SessionEditorInput && getURI().equals(((SessionEditorInput) o).getURI());
        if (equals && o instanceof SessionEditorInput) {
            SessionEditorInput otherSessionEditorInput = (SessionEditorInput) o;
            IStatus otherStatus = otherSessionEditorInput.getStatus();
            if (getStatus() != otherStatus) {
                equals = false;
            } else {
                EObject input = getInput(false);
                if (input != null) {
                    equals = input.equals(otherSessionEditorInput.getInput(false));
                }
            }
        }
        return equals;
    }
}
