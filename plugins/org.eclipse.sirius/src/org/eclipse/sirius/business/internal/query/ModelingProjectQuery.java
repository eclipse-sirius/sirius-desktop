/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.internal.session.parser.RepresentationsFileSaxParser;
import org.eclipse.sirius.common.tools.api.util.WorkspaceUtil;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link ModelingProject} as a starting point.
 * 
 * @author lredor
 */
public class ModelingProjectQuery {

    /**
     * error message when no representation file found.
     */
    public static final String ZERO_REPRESENTATIONS_FILE_FOUND_IN = "Zero representations file found in \"";

    /**
     * error message when several representation files found.
     */
    public static final String A_MODELING_PROJECT_MUST_CONTAIN_ONLY_ONE = ". A modeling project must contain only one.";

    private final ModelingProject modelingProject;

    /**
     * Create a new query.
     * 
     * @param modelingProject
     *            the project to query.
     */
    public ModelingProjectQuery(ModelingProject modelingProject) {
        this.modelingProject = modelingProject;
    }

    /**
     * Retrieve the representations files in this project.
     * 
     * @return the representations files associated to this project, empty list
     *         if there is no representations file in this project
     */
    public List<IFile> getRepresentationFiles() {
        return WorkspaceUtil.getFilesFromWorkspace(Collections.singleton(modelingProject.getProject()), SiriusUtil.SESSION_RESOURCE_EXTENSION);
    }

    /**
     * Return an optional URI corresponding to the main representations file of
     * this project. It will be computed by a specific SaxParser that analyzes
     * representations files of this project to determine which is never
     * referenced.
     * 
     * @param monitor
     *            the monitor to be used for reporting progress and responding
     *            to cancellation. The monitor is never <code>null</code>
     * @return an optional URI corresponding to the main representations file of
     *         this project.
     * @throws IllegalArgumentException
     *             In case of zero or multiples main representations file in the
     *             references.
     */
    public URI computeMainRepresentationsFileURI(IProgressMonitor monitor) throws IllegalArgumentException {
        URI result = null;
        List<IFile> sessionFiles = getRepresentationFiles();
        monitor.beginTask("", sessionFiles.size() + 1); //$NON-NLS-1$
        Map<URI, Set<URI>> references = new HashMap<URI, Set<URI>>(sessionFiles.size());
        for (IFile sessionFile : sessionFiles) {
            final RepresentationsFileSaxParser sessionSaxParser = new RepresentationsFileSaxParser(sessionFile);
            sessionSaxParser.analyze(new SubProgressMonitor(monitor, 1));
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
        monitor.worked(1);

        if (notReferencedURIs.isEmpty()) {
            throw new IllegalArgumentException(ZERO_REPRESENTATIONS_FILE_FOUND_IN + modelingProject.getProject().getName() + "\". A modeling project must contain one.");
        } else if (notReferencedURIs.size() == 1) {
            result = notReferencedURIs.get(0);
        } else {
            throw new IllegalArgumentException("Found " + notReferencedURIs.size() + " main representations files (that means not referenced by another) in \""
                    + modelingProject.getProject().getName() + "\": " + getFragments(notReferencedURIs) + A_MODELING_PROJECT_MUST_CONTAIN_ONLY_ONE);
        }
        monitor.done();
        return result;
    }

    /**
     * Return a String representing the fragment of this URIs separated by a
     * comma.
     * 
     * @param uris
     *            the list of URIs
     * @return a String representing the fragment of this URIs separated by a
     *         comma.
     */
    private String getFragments(List<URI> uris) {
        StringBuffer result = new StringBuffer();
        for (Iterator<URI> iterator = uris.iterator(); iterator.hasNext(); /* */) {
            URI uri = iterator.next();
            if (!iterator.hasNext()) {
                // Replace the last comma by a and
                result.delete(result.length() - 2, result.length());
                result.append(" and ");
            }
            result.append(uri.lastSegment());
            if (iterator.hasNext()) {
                result.append(", "); //$NON-NLS-1$
            }
        }
        return result.toString();
    }
}
