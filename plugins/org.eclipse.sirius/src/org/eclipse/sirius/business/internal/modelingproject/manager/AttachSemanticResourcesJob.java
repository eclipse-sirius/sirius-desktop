/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;

/**
 * A {@link Job} to attach semantic resource added to a modeling project.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AttachSemanticResourcesJob extends Job {

    private Map<Session, Set<URI>> semanticResourcesURIsToAttachPerSession;

    /**
     * Default constructor.
     * 
     * @param semanticResourcesURIsToAddPerSession
     *            {@link Map} associating to one {@link Session} all semantic resource {@link URI} to attach to it
     */
    public AttachSemanticResourcesJob(Map<Session, Set<URI>> semanticResourcesURIsToAddPerSession) {
        super(Messages.AttachSemanticResourcesJob_name);
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
            monitor.beginTask(Messages.AttachSemanticResourcesJob_name, 6 * semanticResourcesURIsToAttachPerSession.size());
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
