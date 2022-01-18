/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

/**
 * Marker resolution generator looking for Marker related to broken images.
 * 
 * @author lfasani
 * 
 */
public class ImageMarkerResolutionGenerator implements IMarkerResolutionGenerator {
    @Override
    public IMarkerResolution[] getResolutions(IMarker marker) {
        final boolean isImagePathMarker = marker.getAttribute(NavigationMarkerConstants.MARKER_IMAGE, false);
        if (isImagePathMarker) {
            IMarkerResolution[] resolutions = new IMarkerResolution[] { new ImageMarkerResolution() };
            return resolutions;
        }
        return new IMarkerResolution[] {};
    }

}
