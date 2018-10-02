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
package org.eclipse.sirius.server.backend.internal.services.dashboard;

import java.util.List;

/**
 * The DTO used to return the state of the dashboard to the client.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerDashboardDto {
    private int projectsCount;

    private int viewpointsCount;

    private List<SiriusServerDashboardProjectDto> projects;

    private int metamodelsCount;

    /**
     * The constructor.
     *
     * @param projectsCount
     *            The number of projects
     * @param viewpointsCount
     *            The number of viewpoints
     * @param metamodelsCount
     *            The number of metamodels
     * @param projects
     *            The projects
     */
    public SiriusServerDashboardDto(int projectsCount, int viewpointsCount, int metamodelsCount, List<SiriusServerDashboardProjectDto> projects) {
        this.projectsCount = projectsCount;
        this.viewpointsCount = viewpointsCount;
        this.metamodelsCount = metamodelsCount;
        this.projects = projects;
    }

    public int getProjectsCount() {
        return this.projectsCount;
    }

    public int getViewpointsCount() {
        return this.viewpointsCount;
    }

    public int getMetamodelsCount() {
        return this.metamodelsCount;
    }

    public List<SiriusServerDashboardProjectDto> getProjects() {
        return this.projects;
    }
}
