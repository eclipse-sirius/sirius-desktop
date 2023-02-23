/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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

import java.util.Optional;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;

/**
 * A class aggregating all the queries (read-only!) having a {@link Session} as a starting point.
 * 
 * @author lfasani
 */
public class SiriusSessionQuery {

    private final Session session;

    /**
     * Create a new query.
     * 
     * @param session
     *            the session to query.
     */
    public SiriusSessionQuery(Session session) {
        this.session = session;
    }

    /**
     * This method provides the name of the project associated to the session.<br/>
     * <li>If the project is local in the workspace the name is the {@link IProject} name.</li>
     * <li>If the project is shared elsewhere the name is retrieved from Session.getSharedMainDAnalysis URI().</li>
     */
    public String getSharedProjectName() {
        String projectName = ""; //$NON-NLS-1$
        Optional<URI> uriOpt = session.getSharedMainDAnalysis()//
                .map(EObject::eResource)//
                .map(Resource::getURI);
        if (uriOpt.isPresent()) {
            projectName = uriOpt//
                    .filter(URI::isPlatformResource)//
                    .map(uri -> {
                        return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
                    })//
                    .map(IFile::getProject)//
                    .map(IContainer::getName)//
                    .orElseGet(() -> {
                        return URI.decode(uriOpt.get().segment(0));
                    });
        }
        return projectName;
    }
}
