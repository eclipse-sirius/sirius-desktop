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
package org.eclipse.sirius.business.internal.session.danalysis;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;

/**
 * Specific command to reload an analysis.
 * 
 * @author cnotot
 */
public class ReloadRepresentationsFileCmd extends RecordingCommand {

    private Session session;

    private Resource resource;

    private DAnalysis oldAnalysis;

    /**
     * Constructor.
     * 
     * @param domain
     *            TransactionalEditingDomain
     * @param session
     *            the session
     * @param name
     *            command name
     * @param analysisResource
     *            resource to reload
     */
    public ReloadRepresentationsFileCmd(final Session session, TransactionalEditingDomain domain, String name, Resource analysisResource) {
        super(domain, name);
        this.session = session;
        this.resource = analysisResource;
        EList<EObject> contents = analysisResource.getContents();
        if (contents.isEmpty()) {
            this.oldAnalysis = null;
        } else {
            this.oldAnalysis = (DAnalysis) contents.get(0);
        }
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public boolean canRedo() {
        return false;
    }

    @Override
    protected void doExecute() {
        DAnalysisSession analysisSession = getAnalysisSession();
        DAnalysisSessionEObject analysisSessionEObject = getAnalysisSessionEObject();

        removeOldAnalysis(analysisSession, analysisSessionEObject);
        addNewAnalysis(analysisSession, analysisSessionEObject);
        session = null;
        resource = null;
        oldAnalysis = null;
    }

    private void addNewAnalysis(DAnalysisSession analysisSession, DAnalysisSessionEObject analysisSessionEObject) {
        if (resource != null) {
            final DAnalysis analysis = (DAnalysis) resource.getContents().get(0);
            if (analysis != null) {
                if (analysisSessionEObject != null) {
                    analysisSessionEObject.getAnalyses().add(analysis);
                }
                if (analysisSession != null) {
                    analysisSession.addAdaptersOnAnalysis(analysis);
                }
            }
        }
    }

    private void removeOldAnalysis(DAnalysisSession analysisSession, DAnalysisSessionEObject analysisSessionEObject) {
        if (oldAnalysis != null) {
            if (analysisSessionEObject != null) {
                analysisSessionEObject.getAnalyses().remove(oldAnalysis);
            }
            if (analysisSession != null) {
                analysisSession.removeAdaptersOnAnalysis(oldAnalysis);
            }
        }
    }

    private DAnalysisSessionEObject getAnalysisSessionEObject() {
        DAnalysisSessionEObject analysisSessionEObject = null;
        if (session instanceof DAnalysisSessionEObject) {
            analysisSessionEObject = (DAnalysisSessionEObject) session;
        }
        return analysisSessionEObject;
    }

    private DAnalysisSession getAnalysisSession() {
        DAnalysisSession analysisSession = null;
        if (session instanceof DAnalysisSession) {
            analysisSession = (DAnalysisSession) session;
        }
        return analysisSession;
    }

}
