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
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A {@link Job} to attach semantic resource added to a modeling project.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AttachSemanticResourcesJob extends Job {

    private static final String ACTION_NAME = "Attach semantic resources";

    private Map<Session, Set<URI>> semanticResourcesURIsToAttachPerSession;

    /**
     * Default constructor.
     * 
     * @param semanticResourcesURIsToAddPerSession
     *            {@link Map} associating to one {@link Session} all semantic
     *            resource {@link URI} to attach to it
     */
    public AttachSemanticResourcesJob(Map<Session, Set<URI>> semanticResourcesURIsToAddPerSession) {
        super(ACTION_NAME);
        this.semanticResourcesURIsToAttachPerSession = semanticResourcesURIsToAddPerSession;
    }

    /**
     * Overridden to attach semantic resource to a {@link Session}.
     * 
     * {@inheritDoc}
     */
    @Override
    public IStatus run(IProgressMonitor monitor) {
        try {
            monitor.beginTask(ACTION_NAME, 6 * semanticResourcesURIsToAttachPerSession.size());
            for (Entry<Session, Set<URI>> entry : semanticResourcesURIsToAttachPerSession.entrySet()) {
                Session session = entry.getKey();
                if (session != null && session.isOpen()) {
                    Set<URI> semanticResourcesURIsToAdd = entry.getValue();
                    CompoundCommand compoundCommand = new CompoundCommand();
                    for (URI semanticResourcesURI : semanticResourcesURIsToAdd) {
                        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourcesURI, new SubProgressMonitor(monitor, 5));
                        compoundCommand.append(addSemanticResourceCmd);
                    }
                    session.getTransactionalEditingDomain().getCommandStack().execute(compoundCommand);
                    monitor.worked(1);
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

    @Override
    public boolean belongsTo(Object family) {
        return AbstractRepresentationsFileJob.FAMILY.equals(family);
    }
}
