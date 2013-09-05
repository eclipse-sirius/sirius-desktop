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
package org.eclipse.sirius.ui.tools.internal.operations;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.google.common.collect.Sets;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;

/**
 * Operation to add a semantic resource to a session.
 * 
 * @author mchauvin
 */
public class SemanticResourceAdditionOperation implements IRunnableWithProgress {

    private Collection<Session> sessions;

    private Collection<URI> uris;

    private Collection<Object> results;

    /**
     * Creates a new instance.
     * 
     * @param sessions
     *            the sessions
     * @param uris
     *            the uris
     */
    public SemanticResourceAdditionOperation(final Collection<Session> sessions, final Collection<URI> uris) {
        this.sessions = sessions;
        this.uris = uris;
    }

    /**
     * {@inheritDoc}
     */
    public void run(IProgressMonitor monitor) throws java.lang.reflect.InvocationTargetException, InterruptedException {
        try {
            monitor.beginTask("Semantic resource addition", uris.size() * sessions.size());
            results = Sets.newLinkedHashSet();
            for (Session session : sessions) {
                TransactionalEditingDomain transDomain = session.getTransactionalEditingDomain();
                CompoundCommand command = new CompoundCommand();
                for (URI semanticModelURI : uris) {
                    RecordingCommand cmd = new AddSemanticResourceCommand(session, semanticModelURI, new SubProgressMonitor(monitor, 1));
                    command.append(cmd);
                }
                transDomain.getCommandStack().execute(command);
                results.addAll(command.getResult());
            }
        } finally {
            monitor.done();
        }
    }

    public Collection<Object> getResults() {
        return results;
    }
}
