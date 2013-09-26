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
package org.eclipse.sirius.business.api.dialect.command;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Specific command to move the given representations.
 * 
 * @author mporhel
 * 
 */
public class MoveRepresentationCommand extends RecordingCommand {

    private Collection<DRepresentation> representations;

    private Session session;

    private DAnalysis targetAnalysis;

    /**
     * Specific command to move the given representations.
     * 
     * @param session
     *            the current session
     * @param targetAnalysis
     *            the target analysis
     * @param movableRepresentations
     *            the representations to move
     */
    public MoveRepresentationCommand(Session session, DAnalysis targetAnalysis, Collection<DRepresentation> movableRepresentations) {
        super(session.getTransactionalEditingDomain(), "Move representation to " + targetAnalysis.eResource().getURI().toString());
        this.representations = movableRepresentations;
        this.targetAnalysis = targetAnalysis;
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (representations == null || representations.isEmpty() || targetAnalysis == null || session == null) {
            return;
        }

        for (final DRepresentation representation : representations) {
            ((DAnalysisSession) session).moveRepresentation(targetAnalysis, representation);
        }
    }
}
