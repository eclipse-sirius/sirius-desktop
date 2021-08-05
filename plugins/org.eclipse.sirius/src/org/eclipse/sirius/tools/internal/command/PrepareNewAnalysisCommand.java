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
package org.eclipse.sirius.tools.internal.command;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Specific command to prepare the given analysis. It will be added to the content of the given resource.
 * 
 * @author mporhel
 * 
 */
public class PrepareNewAnalysisCommand extends RecordingCommand {

    private DAnalysis slaveAnalysis;

    private Resource resource;

    private Session session;

    /**
     * Specific command to prepare the new analysis.
     * 
     * @param domain
     *            the editing domain.
     * @param resource
     *            the current resource.
     * @param newAnalysis
     *            the new analysis.
     * @param session
     *            the current session.
     */
    public PrepareNewAnalysisCommand(TransactionalEditingDomain domain, Resource resource, DAnalysis newAnalysis, Session session) {
        super(domain, Messages.PrepareNewAnalysisCommand_label);
        this.slaveAnalysis = newAnalysis;
        this.resource = resource;
        this.session = session;
    }

    @Override
    protected void doExecute() {
        if (slaveAnalysis != null && resource != null) {
            resource.getContents().add(slaveAnalysis);
        }
        if (slaveAnalysis != null && session instanceof DAnalysisSession) {
            ((DAnalysisSession) session).addReferencedAnalysis(slaveAnalysis);
            for (final Resource semResource : session.getSemanticResources()) {
                if (!semResource.getContents().isEmpty()) {
                    slaveAnalysis.getSemanticResources().add(new ResourceDescriptor(semResource.getURI()));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}
