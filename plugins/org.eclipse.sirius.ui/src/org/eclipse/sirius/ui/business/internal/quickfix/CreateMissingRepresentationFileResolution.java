/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.ui.business.internal.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;

/**
 * This class extends {@link IMarkerResolutionGenerator} to provide a quickfix concerning error markers on modeling
 * project without representations file. This quickfix only create the missing representations file.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class CreateMissingRepresentationFileResolution implements IMarkerResolutionGenerator {

    @Override
    public IMarkerResolution[] getResolutions(IMarker marker) {
        WorkbenchMarkerResolution workbenchMarkerResolution = new WorkbenchMarkerResolution() {

            @Override
            public void run(IMarker marker) {
                if (marker.getResource() instanceof IProject) {
                    try {
                        IProject project = (IProject) marker.getResource();
                        ModelingProjectManager.INSTANCE.removeModelingNature(project, new NullProgressMonitor());
                        ModelingProjectManager.INSTANCE.convertToModelingProject(project, new NullProgressMonitor());
                    } catch (CoreException e) {
                        SiriusEditPlugin.getPlugin().getLog().log(e.getStatus());
                    }
                }
            }

            @Override
            public String getLabel() {
                return Messages.CreateMissingRepresentationFileResolution_label;
            }

            @Override
            public Image getImage() {
                return null;
            }

            @Override
            public String getDescription() {
                return Messages.CreateMissingRepresentationFileResolution_description;
            }

            @Override
            public IMarker[] findOtherMarkers(IMarker[] markers) {
                return new IMarker[0];
            }
        };
        return new IMarkerResolution[] { workbenchMarkerResolution };
    }

}
