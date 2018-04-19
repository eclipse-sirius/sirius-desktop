/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.backend.internal.utils;

import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Utility class.
 *
 * @author sbegaudeau
 */
public final class SiriusServerUtils {

    /**
     * The UTF-8 encoding.
     */
    public static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusServerUtils() {
        // prevent instantiation
    }

    /**
     * Returns the session of the given modeling project or open a new one and
     * return it.
     *
     * @param modelingProject
     *            The modeling project
     * @return The session
     */
    public static Session getSession(ModelingProject modelingProject) {
        Optional<Session> optionalSession = Optional.ofNullable(modelingProject.getSession());
        Session session = optionalSession.orElseGet(() -> {
            // FIXME SBE: proper management of optionals once Sirius has
            // switched to Java optional
            URI sessionResourceURI = modelingProject.getMainRepresentationsFileURI(new NullProgressMonitor()).get();
            return SessionManager.INSTANCE.openSession(sessionResourceURI, new NullProgressMonitor(), SiriusPlugin.getDefault().getUiCallback());
        });
        return session;
    }

}
