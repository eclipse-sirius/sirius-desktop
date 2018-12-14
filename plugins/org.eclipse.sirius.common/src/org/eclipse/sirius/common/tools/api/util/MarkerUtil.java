/*******************************************************************************
 * Copyright (c) 2012-2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.api.util;

import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.sirius.common.tools.DslCommonPlugin;

/**
 * This class provides utility methods that aid in the creation of
 * {@link IResource} markers ({@link IMarker}).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class MarkerUtil {

    /**
     * Avoid instantiation.
     */
    protected MarkerUtil() {
        // Do nothing
    }

    /**
     * Creates a marker with the specified type on this resource. Marker type
     * ids are the id of an extension installed in the
     * <code>org.eclipse.core.resources.markers</code> extension point. The
     * specified type string must not be <code>null</code>.
     * 
     * @param resource
     *            An IResource for which a marker must be added
     * @param message
     *            The message of the marker we want to display in the Problem
     *            View.
     * @param severity
     *            The severity of the marker
     * @param type
     *            the type of the marker to create
     * @return an optional Marker (none if problem during creation).
     */
    public static Optional<IMarker> addMarkerFor(final IResource resource, final String message, final int severity, String type) {
        try {
            if (resource != null) {
                final IMarker marker = resource.createMarker(type);
                marker.setAttribute(IMarker.SEVERITY, severity);
                marker.setAttribute(IMarker.MESSAGE, message);
                return Optional.of(marker);
            }
        } catch (final CoreException e) {
            DslCommonPlugin.getDefault().getLog().log(e.getStatus());
        }
        return Optional.empty();
    }

    /**
     * Deletes all markers on this resource of the given type.
     * 
     * @param resource
     *            An IResource for which this markers must be removed
     * @param type
     *            the type of the marker to create
     */
    public static void removeMarkerFor(IResource resource, String type) {
        try {
            if (resource != null && resource.exists()) {
                resource.deleteMarkers(type, false, IResource.DEPTH_ZERO);
            }
        } catch (final CoreException e) {
            DslCommonPlugin.getDefault().getLog().log(e.getStatus());
        }
    }

    /**
     * Sets the attribute of this IMarker with the given name. <BR>
     * 
     * @see IMarker#setAttribute(String, Object)
     * 
     * @param marker
     *            The concerned marker
     * @param attributeName
     *            the name of the attribute
     * @param value
     *            the value, or <code>null</code> if the attribute is to be
     *            undefined
     */
    public static void setAttribute(IMarker marker, String attributeName, Object value) {
        try {
            if (marker != null) {
                marker.setAttribute(attributeName, value);
            }
        } catch (final CoreException e) {
            DslCommonPlugin.getDefault().getLog().log(e.getStatus());
        }

    }
}
