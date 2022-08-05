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

import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.query.SiriusProjectDependencies;

/**
 * Provider able to give project dependencies.
 * 
 * @author lfasani
 */
public interface IProjectDependencyProvider {

    /**
     * Tells if this provider can handle the dependency computation.
     * 
     * @param project
     * @return
     */
    boolean canHandle(IProject project);

    /**
     * Get the direct dependencies of the {@link IProject}.
     */
    SiriusProjectDependencies getAllDependencies(IProject project);

    /**
     * Get the direct and transitive project dependencies of the {@link IProject} for resources(aird and semantic
     * resources) and only the direct project dependencies for image used by this project.
     */
    SiriusProjectDependencies getDirectDependencies(IProject project);

}
