/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.editor.SpecificEditorInputTranformer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IMemento;

/**
 * Specific editor input for specific editor providing model path, viewpoint URI
 * and representation description name.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class SessionSpecificEditorInput extends SessionEditorInput {

    private static final String VIEWPOINT_URI = "VIEWPOINT_URI"; //$NON-NLS-1$

    private static final String REPRESENTATION_DESCRIPTION_NAME = "REPRESENTATION_DESCRIPTION_NAME"; //$NON-NLS-1$

    private static final String SEMANTIC_MODEL_PATH = "SEMANTIC_MODEL_PATH"; //$NON-NLS-1$

    private IPath mySemanticModelPath;

    private URI mySiriusURI;

    private String myRepresentationDescriptionName;

    /**
     * Create a new SessionEditorInput with a memento.
     * 
     * @param memento
     *            a bit of information kept by the platform.
     */
    public SessionSpecificEditorInput(final IMemento memento) {
        super(memento);
    }

    /**
     * Create a new SessionSpecificEditorInput with the current session and ui
     * session.
     * 
     * @param uri
     *            element URI.
     * @param name
     *            name of the editor.
     * @param session
     *            the current session.
     */
    public SessionSpecificEditorInput(URI uri, String name, Session session) {
        super(uri, name, session);
    }

    /**
     * Init the editor input.
     * 
     * @param semanticModelPath
     *            the semantic model path
     * @param viewpointURI
     *            the viewpoint URI
     * @param representationDescriptionName
     *            the representation description name
     */
    public void init(final IPath semanticModelPath, final URI viewpointURI, final String representationDescriptionName) {
        this.mySemanticModelPath = semanticModelPath;
        this.mySiriusURI = viewpointURI;
        this.myRepresentationDescriptionName = representationDescriptionName;
    }

    @Override
    public void saveState(IMemento memento) {
        super.saveState(memento);
        if (mySiriusURI != null) {
            memento.putString(VIEWPOINT_URI, mySiriusURI.toString());
        }
        if (myRepresentationDescriptionName != null) {
            memento.putString(REPRESENTATION_DESCRIPTION_NAME, myRepresentationDescriptionName);
        }
        if (mySemanticModelPath != null) {
            memento.putString(SEMANTIC_MODEL_PATH, mySemanticModelPath.toString());
        }
    }

    @Override
    protected void loadState(IMemento memento) {
        super.loadState(memento);
        restoreValuesFromMemento(memento);
    }

    @Override
    protected Session getSession(URI sessionModelURI) {
        final URI uriWithoutFragment = sessionModelURI.trimFragment();
        return createSessionFromURIAndMemento(uriWithoutFragment);
    }

    private Session createSessionFromURIAndMemento(final URI uri) {
        Session session = null;
        SpecificEditorInputTranformer tranformer = new SpecificEditorInputTranformer();
        tranformer.init(mySiriusURI, myRepresentationDescriptionName);
        try {
            session = SessionManager.INSTANCE.getExistingSession(uri);
            if (session == null) {
                final DRepresentation representation = tranformer.createSessionAndRepresentation(mySemanticModelPath, uri.toString());
                session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
            }
        } catch (final IOException exception) {
            SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, "Failing of EditorInput transformation.", exception));
        } catch (CoreException exception) {
            SiriusEditPlugin.getPlugin().getLog().log(exception.getStatus());
        }
        return session;
    }

    private void restoreValuesFromMemento(final IMemento memento) {
        mySemanticModelPath = new Path(memento.getString(SEMANTIC_MODEL_PATH));
        mySiriusURI = URI.createURI(memento.getString(VIEWPOINT_URI));
        myRepresentationDescriptionName = memento.getString(REPRESENTATION_DESCRIPTION_NAME);
    }
}
