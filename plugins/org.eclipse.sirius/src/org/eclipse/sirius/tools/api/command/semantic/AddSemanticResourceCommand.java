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
package org.eclipse.sirius.tools.api.command.semantic;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.Messages;

/**
 * Specific command to add semantic resources to the given sessions.
 * 
 * @author mporhel
 * 
 * @since 0.9.0
 */
public class AddSemanticResourceCommand extends RecordingCommand {

    /**
     * The contextual {@link Session}.
     */
    protected Session session;

    /**
     * The URI of the semantic Resource to add to the set of semantic Resources referenced by the contextual
     * {@link Session}.
     */
    protected URI semanticResourceURI;

    /**
     * a {@link IProgressMonitor} to show progression of semantic resource addition.
     */
    protected IProgressMonitor monitor;

    private Collection<Session> result = new LinkedHashSet<>();

    /**
     * Specific command to add semantic resources to the given sessions.
     * 
     * @param session
     *            the session to update.
     * @param semanticResourceURI
     *            the {@link URI} of semantic model resource to add to the given session.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of semantic resource addition
     */
    public AddSemanticResourceCommand(Session session, URI semanticResourceURI, IProgressMonitor monitor) {
        super(session.getTransactionalEditingDomain(), Messages.AddSemanticResourceCommand_label);
        this.session = session;
        this.semanticResourceURI = semanticResourceURI;
        this.monitor = monitor;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        // Step 1 : add the semantic resource to the given session
        session.addSemanticResource(semanticResourceURI, monitor);

        // Step 2 : if the semantic resource addition is successful
        Resource addedResource = session.getTransactionalEditingDomain().getResourceSet().getResource(semanticResourceURI, false);
        if (addedResource != null) {
            // We return the session as a result
            result.add(session);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
     */
    @Override
    public Collection<?> getResult() {
        return result;
    }

}
