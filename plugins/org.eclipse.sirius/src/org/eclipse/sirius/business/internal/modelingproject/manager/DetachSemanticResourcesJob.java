/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.modelingproject.manager;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A {@link Job} to detach semantic resource removed to a modeling project.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DetachSemanticResourcesJob extends Job {

    private static final String ACTION_NAME = "Detach semantic resources";

    private Map<Session, Set<URI>> semanticResourcesURIsToDetachPerSession;

    /**
     * Default constructor.
     * 
     * @param semanticResourcesURIsToDetachPerSession
     *            {@link Map} associating to one {@link Session} all semantic
     *            resource {@link URI} to attach to it
     */
    public DetachSemanticResourcesJob(Map<Session, Set<URI>> semanticResourcesURIsToDetachPerSession) {
        super(ACTION_NAME);
        this.semanticResourcesURIsToDetachPerSession = semanticResourcesURIsToDetachPerSession;
    }

    /**
     * Overridden to detach semantic resource from a {@link Session}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        try {
            monitor.beginTask(ACTION_NAME, 2);
            for (final Session session : semanticResourcesURIsToDetachPerSession.keySet()) {
                if (session != null && session.isOpen()) {
                    TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                    Set<URI> semanticResourcesURIsToDetach = semanticResourcesURIsToDetachPerSession.get(session);
                    CompoundCommand compoundCommand = new CompoundCommand();
                    for (final URI semanticResourcesURI : semanticResourcesURIsToDetach) {
                        // Use a read transaction to avoid concurrent access
                        // exception on ResourceSet.getResource();
                        RunnableWithResult<Resource> resourceGetter = new RunnableWithResult.Impl<Resource>() {

                            public void run() {
                                Resource semanticResource = session.getTransactionalEditingDomain().getResourceSet().getResource(semanticResourcesURI, false);
                                setResult(semanticResource);
                            }
                        };
                        TransactionUtil.runExclusive(domain, resourceGetter);

                        Command removeSemanticResourceCmd = new RemoveSemanticResourceCommand(session, resourceGetter.getResult(), true, new SubProgressMonitor(monitor, 1));
                        compoundCommand.append(removeSemanticResourceCmd);
                    }
                    monitor.worked(1);
                    session.getTransactionalEditingDomain().getCommandStack().execute(compoundCommand);
                }
            }
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } finally {
            monitor.done();
        }
        return Status.OK_STATUS;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
     */
    @Override
    public boolean belongsTo(Object family) {
        return AbstractRepresentationsFileJob.FAMILY.equals(family);
    }

}
