/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.internal.schema.mutation;

import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Utility functions to manipulate optionals.
 * 
 * @author sbegaudeau
 */
public final class WorkspaceOptionalUtils {
    /**
     * The constructor.
     */
    private WorkspaceOptionalUtils() {
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
}
