/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Utility class to extract session from selection.
 * 
 * It check the
 * 
 * @author mporhel
 * 
 */
public final class FileSessionFinder {

    private FileSessionFinder() {
    }

    /**
     * Retrieve the open sessions in the given list by looking for
     * {@link Session}s and/or *.aird {@link IFile}s (only main aird for non
     * modeling projects) or transient session with semantic files in selection.
     * 
     * @param listObject
     *            list of objects
     * @return list of session associated to selection
     */
    public static List<Session> getSelectedSessions(final Collection<?> listObject) {
        final List<Session> selectedSessions = Lists.newArrayList();

        if (listObject == null) {
            return selectedSessions;
        }

        for (final Object selected : listObject) {
            if (selected instanceof Session) {
                selectedSessions.add((Session) selected);
            } else if (selected instanceof IFile) {
                Collection<Session> fileSessions = getSessionFromFile((IFile) selected, false);
                if (fileSessions != null && !fileSessions.isEmpty()) {
                    selectedSessions.addAll(fileSessions);
                }
            }
        }
        return selectedSessions;
    }

    /**
     * Retrieve the open sessions related to the given file list.
     * 
     * @param files
     *            list of files
     * @return list of session associated to the given files.
     */
    public static List<Session> getRelatedSessions(final Collection<IFile> files) {
        final List<Session> selectedSessions = Lists.newArrayList();

        if (files == null) {
            return selectedSessions;
        }

        for (final IFile file : files) {
            if (file != null) {
                Collection<Session> fileSessions = getSessionFromFile(file, true);
                if (fileSessions != null && !fileSessions.isEmpty()) {
                    selectedSessions.addAll(fileSessions);
                }
            }
        }
        return selectedSessions;
    }

    /**
     * Get session linked to an aird or transient session linked to its semantic
     * resource.
     */
    private static Collection<Session> getSessionFromFile(IFile file, boolean lookFromSemanticAndControlled) {
        Collection<Session> sessions = Sets.newLinkedHashSet();
        boolean lookForTransientSession = !SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(file.getFileExtension());
        URI fileURI = getFileUri(file);
        for (Session session : SessionManager.INSTANCE.getSessions()) {
            if (checkedSession(session, file, fileURI, lookForTransientSession, lookFromSemanticAndControlled)) {
                sessions.add(session);
            }
        }
        return sessions;
    }

    private static boolean checkedSession(Session session, IFile file, URI fileURI, boolean lookForTransientSession, boolean lookFromSemantic) {
        boolean foundSession = false;
        Resource sessionResource = session.getSessionResource();

        if (session.isOpen() && sessionResource != null) {
            if (lookFromSemantic || lookForTransientSession && new URIQuery(sessionResource.getURI()).isInMemoryURI()) {
                Iterable<Resource> resourceToCheck = session.getSemanticResources();
                if (session instanceof DAnalysisSessionEObject && lookFromSemantic) {
                    resourceToCheck = Iterables.concat(session.getSemanticResources(), ((DAnalysisSessionEObject) session).getControlledResources());
                }

                foundSession = checkSession(fileURI, resourceToCheck);
            } else {
                foundSession = sessionResource.getURI().equals(fileURI);
                if (!foundSession && ModelingProject.hasModelingProjectNature(file.getProject())) {
                    foundSession = checkSession(fileURI, session.getAllSessionResources());
                }
            }
        }
        return foundSession;
    }

    private static boolean checkSession(URI fileURI, Iterable<Resource> resourceToCheck) {
        boolean foundSession = false;
        for (Resource res : resourceToCheck) {
            if (res.getURI().equals(fileURI)) {
                foundSession = true;
                break;
            }
        }
        return foundSession;
    }

    private static URI getFileUri(IFile file) {
        return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
    }
}
