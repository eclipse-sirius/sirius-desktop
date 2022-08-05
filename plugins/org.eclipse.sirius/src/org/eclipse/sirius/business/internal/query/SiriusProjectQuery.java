/*******************************************************************************
 * Copyright (c) 2011, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.query;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.internal.session.parser.RepresentationsFileSaxParser;
import org.eclipse.sirius.common.tools.api.util.WorkspaceUtil;
import org.eclipse.sirius.tools.api.Messages;

/**
 * A class aggregating all the queries (read-only!) having a {@link ModelingProject} as a starting point.
 * 
 * @author lredor
 */
public class SiriusProjectQuery {

    /**
     * Error code when no representation file found.
     */
    public static final String ZERO_REPRESENTATIONS_FILE_FOUND_IN = "0"; //$NON-NLS-1$

    /**
     * Error code when several representation files found.
     */
    public static final String A_MODELING_PROJECT_MUST_CONTAIN_ONLY_ONE = "*"; //$NON-NLS-1$

    private final IProject project;

    /**
     * Create a new query.
     * 
     * @param modelingProject
     *            the project to query.
     */
    public SiriusProjectQuery(IProject project) {
        this.project = project;
    }

    /**
     * Retrieve the representations files in this project.
     * 
     * @return the representations files associated to this project, empty list if there is no representations file in
     *         this project
     */
    public List<IFile> getRepresentationFiles() {
        return WorkspaceUtil.getFilesFromWorkspace(Collections.singleton(project), SiriusUtil.SESSION_RESOURCE_EXTENSION);
    }

    /**
     * Return an optional URI corresponding to the main representations file of this project. It will be computed by a
     * specific SaxParser that analyzes representations files of this project to determine which is never referenced.
     * 
     * @param monitor
     *            the monitor to be used for reporting progress and responding to cancellation. The monitor is never
     *            <code>null</code>
     * @return an optional URI corresponding to the main representations file of this project.
     * @throws IllegalArgumentException
     *             In case of zero or multiples main representations file in the references.
     */
    public URI computeMainRepresentationsFileURI() throws IllegalArgumentException {
        URI result = null;
        List<URI> notReferencedURIs = getMainAirdURIs();

        if (notReferencedURIs.isEmpty()) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.ModelingProjectQuery_mustContainOneRepFileMsg, project.getName()), new Throwable(ZERO_REPRESENTATIONS_FILE_FOUND_IN));
        } else if (notReferencedURIs.size() == 1) {
            result = notReferencedURIs.get(0);
        } else {
            throw new IllegalArgumentException(
                    MessageFormat.format(Messages.ModelingProjectQuery_severalRepresentationsFiles, notReferencedURIs.size(), project.getName(), getFragments(notReferencedURIs)),
                    new Throwable(A_MODELING_PROJECT_MUST_CONTAIN_ONLY_ONE));
        }
        return result;
    }

    /**
     * Get the platform/resource URIs of all the aird contained in this project that are not referenced by any other
     * aird.
     */
    public List<URI> getMainAirdURIs() {
        List<IFile> sessionFiles = getRepresentationFiles();
        Map<URI, Set<URI>> references = new HashMap<URI, Set<URI>>(sessionFiles.size());
        for (IFile sessionFile : sessionFiles) {
            final RepresentationsFileSaxParser sessionSaxParser = new RepresentationsFileSaxParser(sessionFile);
            sessionSaxParser.analyze();
            references.put(sessionSaxParser.getRepresentationsFileURI(), sessionSaxParser.getReferencedAnalysis());
        }
        List<URI> notReferencedURIs = new ArrayList<URI>();
        List<URI> keys = new ArrayList<URI>(references.keySet());
        for (URI uri : references.keySet()) {
            boolean referenced = false;
            for (URI uri2 : keys) {
                if (references.get(uri2).contains(uri)) {
                    referenced = true;
                    break;
                }
            }
            if (!referenced) {
                notReferencedURIs.add(uri);
            }
        }
        return notReferencedURIs;
    }

    /**
     * Return a String representing the fragment of this URIs separated by a comma.
     * 
     * @param uris
     *            the list of URIs
     * @return a String representing the fragment of this URIs separated by a comma.
     */
    private String getFragments(List<URI> uris) {
        StringBuffer result = new StringBuffer();
        for (Iterator<URI> iterator = uris.iterator(); iterator.hasNext(); /* */) {
            URI uri = iterator.next();
            if (!iterator.hasNext()) {
                // Replace the last comma by a and
                result.delete(result.length() - 2, result.length());
                result.append(Messages.ModelingProjectQuery_and);
            }
            result.append(uri.lastSegment());
            if (iterator.hasNext()) {
                result.append(", "); //$NON-NLS-1$
            }
        }
        return result.toString();
    }
}
