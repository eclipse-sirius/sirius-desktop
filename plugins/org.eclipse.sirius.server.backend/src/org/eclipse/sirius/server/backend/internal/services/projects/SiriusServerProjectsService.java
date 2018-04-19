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
package org.eclipse.sirius.server.backend.internal.services.projects;

import static org.eclipse.sirius.server.backend.internal.SiriusServerResponse.STATUS_BAD_REQUEST;
import static org.eclipse.sirius.server.backend.internal.SiriusServerResponse.STATUS_CREATED;
import static org.eclipse.sirius.server.backend.internal.SiriusServerResponse.STATUS_INTERNAL_SERVER_ERROR;
import static org.eclipse.sirius.server.backend.internal.SiriusServerResponse.STATUS_OK;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.server.backend.internal.ISiriusServerService;
import org.eclipse.sirius.server.backend.internal.SiriusServerMessages;
import org.eclipse.sirius.server.backend.internal.SiriusServerPath;
import org.eclipse.sirius.server.backend.internal.SiriusServerResponse;
import org.eclipse.sirius.server.backend.internal.utils.SiriusServerErrorDto;
import org.eclipse.sirius.server.backend.internal.utils.SiriusServerUtils;

/**
 * Service used to manipulate the list of projects.
 *
 * @author sbegaudeau
 */
@SiriusServerPath("/projects")
public class SiriusServerProjectsService implements ISiriusServerService {

    @Override
    public SiriusServerResponse doGet(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return new SiriusServerResponse(STATUS_OK, this.getModelingProjects());
    }

    /**
     * Returns all the modeling projects of the workspace.
     *
     * @return All the modeling projects of the workspace
     */
    private SiriusServerProjectsDto getModelingProjects() {
        IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        // @formatter:off
		List<SiriusServerProjectDto> modelingProjects = Arrays.stream(allProjects)
				.filter(ModelingProject::hasModelingProjectNature)
				.filter(IProject::isOpen)
				.map(project -> ModelingProject.asModelingProject(project).get())
				.map(this::convertToProject)
				.collect(Collectors.toList());
		// @formatter:on
        return new SiriusServerProjectsDto(modelingProjects);
    }

    /**
     * Converts the given modeling project into a
     * {@link SiriusServerProjectDto}.
     *
     * @param modelingProject
     *            The modeling project
     * @return The {@link SiriusServerProjectDto} created
     */
    private SiriusServerProjectDto convertToProject(ModelingProject modelingProject) {
        String name = modelingProject.getProject().getName();
        return new SiriusServerProjectDto(name);
    }

    @Override
    public SiriusServerResponse doPost(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        SiriusServerResponse response = null;
        try {
            Reader reader = new InputStreamReader(request.getInputStream(), SiriusServerUtils.UTF_8);
            SiriusServerNewProjectDto newProject = new Gson().fromJson(reader, SiriusServerNewProjectDto.class);

            String name = newProject.getName();
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
            if (project.exists()) {
                String message = MessageFormat.format(SiriusServerMessages.SiriusServerProjectsService_projectAlreadyExists, name);
                response = new SiriusServerResponse(STATUS_BAD_REQUEST, new SiriusServerErrorDto(message));
            } else {
                // TODO Improve this code once this issue is fixed: https://bugs.eclipse.org/bugs/show_bug.cgi?id=533931
                IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(name);
                projectDescription.setNatureIds(new String[] { ModelingProject.NATURE_ID });
                project.create(projectDescription, new NullProgressMonitor());
                project.open(new NullProgressMonitor());

                URI representationsURI = URI.createPlatformResourceURI(project.getFullPath().append(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME).toString(), true);
                SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(representationsURI, new NullProgressMonitor());
                sessionCreationOperation.execute();

                response = new SiriusServerResponse(STATUS_CREATED, new SiriusServerProjectDto(name));
            }
        } catch (@SuppressWarnings("unused") IOException | CoreException exception) {
            // We don't want to send back the message of the exception
            response = new SiriusServerResponse(STATUS_INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
