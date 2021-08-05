/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.text.MessageFormat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;

import com.google.common.base.Preconditions;

/**
 * Specific command to reload an analysis.
 * 
 * @author cnotot
 */
public class AnalysisResourceReloadedCommand extends RecordingCommand {

    private DAnalysisSessionImpl session;

    private Resource resource;

    private DAnalysis oldAnalysis;

    /**
     * Constructor.
     * 
     * @param domain
     *            TransactionalEditingDomain
     * @param session
     *            the session
     * @param analysisResource
     *            resource to reload
     */
    public AnalysisResourceReloadedCommand(DAnalysisSessionImpl session, TransactionalEditingDomain domain, Resource analysisResource) {
        super(domain, MessageFormat.format(Messages.AnalysisResourceReloadedCommand_label, analysisResource.getURI()));
        this.session = Preconditions.checkNotNull(session);
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
        removeOldAnalysis();
        addNewAnalysis();
        session = null;
        resource = null;
        oldAnalysis = null;
    }

    private void removeOldAnalysis() {
        if (oldAnalysis != null) {
            session.getAnalyses().remove(oldAnalysis);
            session.removeAdaptersOnAnalysis(oldAnalysis);
        }
    }

    private void addNewAnalysis() {
        DAnalysis analysis = (DAnalysis) resource.getContents().get(0);
        if (analysis != null) {
            session.getAnalyses().add(analysis);
            session.addAdaptersOnAnalysis(analysis);
        }
    }

}
