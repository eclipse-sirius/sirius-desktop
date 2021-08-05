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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.Messages;

/**
 * Specific command do remove semantic resources from the given session.
 * 
 * @author mporhel
 * 
 *         since@ 4.0
 */
public class RemoveSemanticResourceCommand extends RecordingCommand {

    /**
     * The contextual {@link Session}.
     */
    protected Session session;

    /**
     * The semantic Resource to remove from the contextual {@link Session}.
     */
    protected Resource semanticResource;

    /**
     * Indicates if the referencing resources are also to remove.
     */
    private boolean removeReferencingResources;

    private IProgressMonitor monitor;

    /**
     * Default Constructor.
     * 
     * @param session
     *            contextual {@link Session}
     * @param semanticResource
     *            resource to remove
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of semantic resource removal
     * @param removeReferencingResources
     *            indicates if the referencing resources are also to remove
     */
    public RemoveSemanticResourceCommand(Session session, Resource semanticResource, IProgressMonitor monitor, boolean removeReferencingResources) {
        super(session.getTransactionalEditingDomain(), Messages.RemoveSemanticResourceCommand_label);
        this.semanticResource = semanticResource;
        this.session = session;
        this.monitor = monitor;
        this.removeReferencingResources = removeReferencingResources;
    }

    /**
     * Execute the command.
     */
    @Override
    protected void doExecute() {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        session.removeSemanticResource(semanticResource, monitor, removeReferencingResources);
    }

}
