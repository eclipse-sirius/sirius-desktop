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
package org.eclipse.sirius.tools.api.command.semantic;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;

import com.google.common.collect.Lists;

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
     * Indicates whether cross referenced resources should be removed or not.
     */
    protected boolean removeCrossReferencedResources;

    /**
     * Indicates if the resource should be removed from controlled resources.
     */
    protected boolean removeFromControlledResources;

    private IProgressMonitor monitor;

    /**
     * Default Constructor.
     * 
     * @param session
     *            contextual {@link Session}
     * @param semanticResource
     *            Resource
     * @param removeFromControlledResources
     *            true if the given resource will removed from controlled
     *            resources
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of semantic
     *            resource removal
     */
    public RemoveSemanticResourceCommand(Session session, Resource semanticResource, boolean removeFromControlledResources, IProgressMonitor monitor) {
        super(session.getTransactionalEditingDomain(), "Remove model");
        this.semanticResource = semanticResource;
        this.session = session;
        this.removeFromControlledResources = removeFromControlledResources;
        this.monitor = monitor;
    }

    /**
     * Execute the command.
     */
    @Override
    protected void doExecute() {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        if (semanticResource != null && session != null && session.getSemanticResources().contains(semanticResource)) {
            session.removeSemanticResource(semanticResource, monitor);
        }
        if (removeFromControlledResources && session instanceof DAnalysisSessionEObject && ((DAnalysisSessionEObject) session).getControlledResources().contains(semanticResource)) {
            for (final EObject root : Lists.newArrayList(semanticResource.getContents())) {
                EcoreUtil.remove(root);
            }
            ((DAnalysisSessionEObject) session).getControlledResources().remove(semanticResource);
        }
    }

}
