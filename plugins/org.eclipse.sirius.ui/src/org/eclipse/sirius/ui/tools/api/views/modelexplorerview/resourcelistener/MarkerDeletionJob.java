/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.internal.modelingproject.marker.ModelingMarker;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * A {@link WorkspaceJob} to delete markers of a {@link IResource}.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class MarkerDeletionJob extends WorkspaceJob {

    private IResource resource;

    /**
     * Default constructor.
     *
     * @param resource
     *            the {@link IResource} for which to delete markers
     */
    public MarkerDeletionJob(IResource resource) {
        super(Messages.MarkerDeletionJob_name);
        this.resource = resource;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
        try {
            resource.deleteMarkers(ModelingMarker.MARKER_TYPE, false, IResource.DEPTH_ZERO);
        } catch (final CoreException e) {
            SiriusPlugin.getDefault().getLog().log(e.getStatus());
        }
        return Status.OK_STATUS;
    }
}
