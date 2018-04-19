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
package org.eclipse.sirius.server.backend.internal.services.dashboard;

import static org.eclipse.sirius.server.backend.internal.SiriusServerResponse.STATUS_OK;

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
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.server.backend.internal.ISiriusServerService;
import org.eclipse.sirius.server.backend.internal.SiriusServerPath;
import org.eclipse.sirius.server.backend.internal.SiriusServerResponse;
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
     * Returns a stream of the modeling projects of the workspace.
     *
     * @return A stream of the modeling projects of the workspace
     */
    private Stream<ModelingProject> getModelingProjects() {
        IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        // @formatter:off
		return Arrays.stream(allProjects)
				.filter(ModelingProject::hasModelingProjectNature)
				.filter(IProject::isOpen)
				.map(project -> ModelingProject.asModelingProject(project).get());
		// @formatter:on
    }

    /**
     * Converts the given {@link ModelingProject} into a
     * {@link SiriusServerDashboardProjectDto}.
     *
     * @param modelingProject
     *            The {@link ModelingProject}
     * @return The {@link SiriusServerDashboardProjectDto}
     */
    private SiriusServerDashboardProjectDto convertToProject(ModelingProject modelingProject) {
        Session session = SiriusServerUtils.getSession(modelingProject);

        String name = modelingProject.getProject().getName();
        int semanticResourcesCount = session.getSemanticResources().size();
        // @formatter:off
		int representationsCount = DialectManager.INSTANCE.getAllRepresentationDescriptors(session).stream()
				.filter(descriptor -> !descriptor.getDescription().eIsProxy())
				.mapToInt(e -> 1)
				.sum();
		// @formatter:on

        return new SiriusServerDashboardProjectDto(name, semanticResourcesCount, representationsCount);
    }
}
