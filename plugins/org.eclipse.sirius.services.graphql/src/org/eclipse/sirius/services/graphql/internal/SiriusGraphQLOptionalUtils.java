/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.graphql.internal;

import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Utility functions to manipulate optionals.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLOptionalUtils {

    /**
     * The constructor.
     */
    private SiriusGraphQLOptionalUtils() {
        // Prevent instantiation
    }

    /**
     * Returns an optional containing the project computed from the given object or an empty optional if it could not be
     * found.
     * 
     * @param object
     *            The name of the project as an Object
     * @return An optional with the project found or an empty optional
     */
    public static Optional<IProject> projectFromName(Object object) {
        // @formatter:off
        return Optional.ofNullable(object)
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(ResourcesPlugin.getWorkspace().getRoot()::getProject);
        // @formatter:on
    }

    /**
     * Returns an optional containing the viewpoint computed from the given object or an empty optional if it could not
     * be found.
     * 
     * @param object
     *            The identifier of the viewpoint as an Object
     * @return An optional with the viewpoint found or an empty optional
     */
    public static Optional<Viewpoint> viewpointFromIdentifier(Object object) {
        // @formatter:off
        return Optional.ofNullable(object)
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .flatMap(viewpointIdentifier -> {
                    return ViewpointRegistry.getInstance().getViewpoints().stream()
                            .filter(viewpoint -> viewpointIdentifier.equals(viewpoint.getName()))
                            .findFirst();
                });
        // @formatter:on
    }
}
