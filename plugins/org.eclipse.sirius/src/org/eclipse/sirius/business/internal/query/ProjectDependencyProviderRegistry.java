/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.query;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.common.tools.api.resource.IFileGetter;

/**
 * Registry for providers able to give project dependencies.
 * 
 * @author lfasani
 * 
 */
public final class ProjectDependencyProviderRegistry {

    /**
     * The sole instance.
     */
    private static ProjectDependencyProviderRegistry instance;

    private Set<IProjectDependencyProvider> projectDependencyProviders = new LinkedHashSet<IProjectDependencyProvider>();

    private ProjectDependencyProviderRegistry() {
    }

    /**
     * Gets the sole instance.
     * 
     * @return the sole instance
     */
    public static ProjectDependencyProviderRegistry getDefault() {
        if (ProjectDependencyProviderRegistry.instance == null) {
            ProjectDependencyProviderRegistry.instance = new ProjectDependencyProviderRegistry();
        }
        return ProjectDependencyProviderRegistry.instance;
    }

    /**
     * Register a {@link IFileGetter}.
     * 
     * @param fileGetter
     *            the {@link IFileGetter} to register
     */
    public void registerProjectDependencyProvider(IProjectDependencyProvider projectDependencyProvider) {
        this.projectDependencyProviders.add(projectDependencyProvider);
    }

    public Set<IProjectDependencyProvider> getProjectDependencyProviders() {
        return projectDependencyProviders;
    }
}
