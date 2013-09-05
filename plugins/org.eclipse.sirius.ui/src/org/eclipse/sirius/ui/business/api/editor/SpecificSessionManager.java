/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.editor;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;

/**
 * Manage the opening and closing of session cleanly for a specific editor.
 * 
 * @author mchauvin
 * @since 2.5
 */
public class SpecificSessionManager {

    /** The included session, private as subclasses does not need to access it. */
    private Session session;

    /**
     * Create a new instance.
     */
    public SpecificSessionManager() {
    }

    /**
     * Close editing session and session.
     */
    public void closeSession() {
        final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        if (uiSession != null) {
            uiSession.close();
        }
        session.close(new NullProgressMonitor());
        session = null;
    }

    /**
     * Get the session managed. Take care, with great power comes great
     * responsibilities.
     * 
     * @return the wrapped session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Create a session silently for the specific editor. Must be called in a
     * transactional EMF Command.
     * 
     * @param semanticModelPath
     *            the semantic model path
     * @param analysisFilenameURI
     *            the analysis filename uri
     * @throws IOException
     *             if semantic model file could be loaded
     */
    public void createSession(final IPath semanticModelPath, final String analysisFilenameURI) throws IOException {

        Session createdSession;
        try {
            createdSession = SessionFactory.INSTANCE.createSession(URI.createURI(analysisFilenameURI), new NullProgressMonitor());
        } catch (CoreException e) {
            return;
        }
        URI semanticResourceURI = URI.createURI(semanticModelPath.toPortableString(), true);
        createdSession.addSemanticResource(semanticResourceURI, new NullProgressMonitor());

        if (createdSession != null) {
            final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(createdSession);
            uiSession.open();
        }
    }
}
