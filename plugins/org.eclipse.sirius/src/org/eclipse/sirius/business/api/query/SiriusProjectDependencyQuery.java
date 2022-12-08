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
package org.eclipse.sirius.business.api.query;

import java.util.LinkedHashSet;
import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.internal.query.ProjectDependencyProviderRegistry;

/**
 * Allows to get project dependencies of a given Sirius project.<br/>
 * The given project can be a local file project or a project connected to an other repository.
 * 
 * @author Laurent Fasani
 */
public class SiriusProjectDependencyQuery {

    private IProject project;

    /**
     * Default constructor.
     */
    public SiriusProjectDependencyQuery(IProject project) {
        this.project = project;
    }

    /**
     * Get the direct and transitive project dependencies of the {@link IProject} for resources(aird and semantic
     * resources) and only the direct project dependencies for image used by this project.<br/>
     * For local projects, it will use SaxParser to analyze the referenced analysis and semantic elements
     * recursively<br/>
     * For local projects, it will use SaxParser to analyze the DAnnotation containing the project used for images<br/>
     * 
     * @return null if something went wrong.
     */
    public SiriusProjectDependencies getAllDependencies() {
        // @formatter:off
        Optional<SiriusProjectDependencies> dependencies = ProjectDependencyProviderRegistry.getDefault().getProjectDependencyProviders().stream()
                .filter(provider -> provider.canHandle(project))
                .findFirst()
                .map(provider -> provider.getAllDependencies(project));
        // @formatter:on

        return dependencies.orElse(null);
    }

    /**
     * Get the direct dependencies of the {@link IProject}.
     */
    public SiriusProjectDependencies getDirectDependencies() {

        // @formatter:off
        Optional<SiriusProjectDependencies> dependencies = ProjectDependencyProviderRegistry.getDefault().getProjectDependencyProviders().stream()
                .filter(provider -> provider.canHandle(project))
                .findFirst()
                .map(provider -> provider.getDirectDependencies(project));
        // @formatter:on

        return dependencies.orElseGet(() -> new SiriusProjectDependencies(new LinkedHashSet<>(), new LinkedHashSet<>(), new LinkedHashSet<>(), new LinkedHashSet<>()));

    }
}
