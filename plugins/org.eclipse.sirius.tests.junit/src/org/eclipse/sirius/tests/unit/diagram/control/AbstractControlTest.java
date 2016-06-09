/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.control;

import java.util.Set;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.collect.Sets;

/**
 * An abstract class for control/uncontrol tests.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractControlTest extends SiriusDiagramTestCase {

    /**
     * Control the semanticRoot into the semanticDest, control also
     * representations in representationsDest aird.
     * 
     * @param semanticRoot
     *            the semantic element to control.
     * @param semanticDest
     *            the URI of the resource in which to control the semantic
     *            element.
     * @param representations
     *            the set of representations to control in addition to the
     *            semantic element.
     * @param representationsDest
     *            the URI of the <code>.aird</code> resource in which to move
     *            the representations.
     * @throws Exception
     */
    protected void siriusControl(EObject semanticRoot, URI semanticDest, Set<DRepresentation> representations, URI representationsDest) throws Exception {
        Set<DRepresentationDescriptor> repDescriptors = Sets.newLinkedHashSet();
        for (DRepresentation dRepresentation : representations) {
            repDescriptors.add(new DRepresentationQuery(dRepresentation).getRepresentationDescriptor());
        }
        final Command siriusControlCommand = new SiriusControlCommand(semanticRoot, semanticDest, repDescriptors, representationsDest, true, new NullProgressMonitor());
        ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
            @Override
            public void run(IProgressMonitor monitor) throws CoreException {
                session.getTransactionalEditingDomain().getCommandStack().execute(siriusControlCommand);
            }
        }, new NullProgressMonitor());
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
    }
}
