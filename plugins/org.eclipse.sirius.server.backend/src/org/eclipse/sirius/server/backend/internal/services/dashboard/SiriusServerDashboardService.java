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

import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_OK;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.server.api.ISiriusServerService;
import org.eclipse.sirius.server.api.SiriusServerPath;
import org.eclipse.sirius.server.api.SiriusServerResponse;
import org.eclipse.sirius.server.backend.internal.utils.SiriusServerUtils;

/**
 * Service used to interact with the dashboard.
 *
 * @author sbegaudeau
 */
@SiriusServerPath("/dashboard")
public class SiriusServerDashboardService implements ISiriusServerService {

    /**
     * The number of projects to be displayed in the dashboard.
     */
    private static final int DASHBOARD_PROJECT_COUNT = 7;

    @Override
    public SiriusServerResponse doGet(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return new SiriusServerResponse(STATUS_OK, this.getDashboard());
    }

    /**
     * Returns the dashboard.
     *
     * @return The dashboard
     */
    private SiriusServerDashboardDto getDashboard() {
        int projectsCount = Long.valueOf(this.getModelingProjects().count()).intValue();
        int viewpointsCount = ViewpointRegistry.getInstance().getViewpoints().size();
        int metamodelsCount = EPackage.Registry.INSTANCE.size();

        // @formatter:off
		List<SiriusServerDashboardProjectDto> projects = this.getModelingProjects()
				.limit(DASHBOARD_PROJECT_COUNT)
				.map(this::convertToProject)
				.collect(Collectors.toList());
		// @formatter:on

        return new SiriusServerDashboardDto(projectsCount, viewpointsCount, metamodelsCount, projects);
    }

    /**
     * Returns a stream of the projects with the modeling project nature in the
     * workspace.
     *
     * @return A stream of the projects with the modeling project nature in the
     *         workspace
     */
    private Stream<IProject> getModelingProjects() {
        IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        // @formatter:off
		return Arrays.stream(allProjects)
				.filter(ModelingProject::hasModelingProjectNature)
				.filter(IProject::isOpen);
		// @formatter:on
    }

    /**
     * Converts the given project into a
     * {@link SiriusServerDashboardProjectDto}.
     *
     * @param iProject
     *            A project with the modeling project nature
     * @return The {@link SiriusServerDashboardProjectDto}
     */
    private SiriusServerDashboardProjectDto convertToProject(IProject iProject) {
        String name = iProject.getName();
        String description = SiriusServerUtils.getProjectDescription(iProject);

        return new SiriusServerDashboardProjectDto(name, description);
    }
}
