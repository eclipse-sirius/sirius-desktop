/*******************************************************************************
 * Copyright (c) 2016, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.modelingproject.marker;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * A {@link WorkspaceJob} to update markers for a invalid project.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class InvalidModelingProjectMarkerUpdaterJob extends WorkspaceJob {

    private IProject project;

    private String message;

    /**
     * Default constructor.
     * 
     * @param project
     *            the project for which update markers to mark this project as invalid
     * @param message
     *            the message to display in marker
     */
    public InvalidModelingProjectMarkerUpdaterJob(IProject project, String message) {
        super(Messages.InvalidModelingProjectMarkerUpdaterJob_updateMarkers);
        this.project = project;
        this.message = message;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
        // Clean existing marker if exists
        try {
            project.deleteMarkers(ModelingMarker.MARKER_TYPE, false, IResource.DEPTH_ZERO);
        } catch (final CoreException ce) {
            SiriusPlugin.getDefault().getLog().log(ce.getStatus());
        }
        // Add a marker on this project
        try {
            final IMarker marker = project.createMarker(ModelingMarker.MARKER_TYPE);
            marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
            marker.setAttribute(IMarker.MESSAGE, message);
        } catch (final CoreException ce) {
            SiriusPlugin.getDefault().getLog().log(ce.getStatus());
        }
        return Status.OK_STATUS;
    }

}
