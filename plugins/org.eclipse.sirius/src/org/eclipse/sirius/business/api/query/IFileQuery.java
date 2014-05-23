/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A class aggregating all the queries (read-only!) having a {@link IFile} as a
 * starting point.
 * 
 * @author lredor
 * 
 */
public class IFileQuery {

    private IFile file;

    /**
     * Constructor.
     * 
     * @param file
     *            the file to check
     */
    public IFileQuery(IFile file) {
        this.file = file;
    }

    /**
     * Check if this file is handled by an opened session, ie:
     * <UL>
     * <LI>a semantic resource of this session,</LI>
     * <LI>a referenced sub representations file,</LI>
     * <LI>a controlled resource.</LI>
     * </UL>
     * Tip: This method returns false for the main representations file of a
     * session.
     * 
     * @return true if this file is handled by an opened session, false
     *         otherwise.
     */
    public boolean isResourceHandledByOpenedSession() {
        boolean result = false;
        URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        for (Session session : Lists.newArrayList(SessionManager.INSTANCE.getSessions())) {
            if (session.isOpen() && fileURI != null) {

                Iterable<Resource> handledResources = Iterables.concat(session.getSemanticResources(), session.getReferencedSessionResources());
                if (session instanceof DAnalysisSessionEObject) {
                    handledResources = Iterables.concat(handledResources, ((DAnalysisSessionEObject) session).getControlledResources());
                }

                for (Iterator<Resource> iterator = handledResources.iterator(); iterator.hasNext() && !result; /* */) {
                    Resource res = iterator.next();
                    if (fileURI.equals(res.getURI())) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
