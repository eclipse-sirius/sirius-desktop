/*******************************************************************************
 * Copyright (c) 2018, 2020 Obeo.
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
package org.eclipse.sirius.server.backend.internal.services.project;

import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_INTERNAL_SERVER_ERROR;
import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_NOT_FOUND;
import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_OK;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.server.api.ISiriusServerService;
import org.eclipse.sirius.server.api.SiriusServerPath;
import org.eclipse.sirius.server.api.SiriusServerResponse;
import org.eclipse.sirius.server.backend.internal.SiriusServerBackendPlugin;
import org.eclipse.sirius.server.backend.internal.expressions.SiriusBackendInterpreter;
import org.eclipse.sirius.server.backend.internal.services.workflow.WorkflowHelper;
import org.eclipse.sirius.server.backend.internal.utils.SiriusServerUtils;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.workflow.ActivityDescription;
import org.eclipse.sirius.workflow.PageDescription;
import org.eclipse.sirius.workflow.SectionDescription;

/**
 * Service used to manipulate a specific project.
 *
 * @author sbegaudeau
 */
@SiriusServerPath("/projects/{projectName}")
public class SiriusServerProjectService implements ISiriusServerService {

    /**
     * The name of the self variable.
     */
    private static final String SELF = "self"; //$NON-NLS-1$

    /**
     * The name of the variable used to capture the name of the project.
     */
    private static final Object PROJECT_NAME = "projectName"; //$NON-NLS-1$

    @Override
    public SiriusServerResponse doGet(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        Optional<String> optionalProjectName = Optional.ofNullable(variables.get(PROJECT_NAME));
        Optional<ModelingProject> optionalModelingProject = optionalProjectName.flatMap(this::findModelingProjectByName);
        Optional<SiriusServerProjectDto> optionalProject = optionalModelingProject.map(this::getProjectFromModelingProject);

        return optionalProject.map(project -> new SiriusServerResponse(STATUS_OK, project)).orElseGet(() -> new SiriusServerResponse(STATUS_NOT_FOUND));
    }

    /**
     * Finds the modeling project with the given name.
     *
     * @param projectName
     *            The name of the project
     * @return An optional with the modeling project or an empty optional if it
     *         could not be found
     */
    private Optional<ModelingProject> findModelingProjectByName(String projectName) {
        Optional<IProject> optionalProject = Optional.ofNullable(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName));
        // @formatter:off
		return optionalProject.filter(ModelingProject::hasModelingProjectNature)
				.filter(IProject::isOpen)
				.map(iProject -> ModelingProject.asModelingProject(iProject).get()); // FIXME Sirius Optional removal!
		// @formatter:on
    }

    /**
     * Converts the given modeling project into a project to be returned by the
     * service.
     *
     * @param modelingProject
     *            The modeling project
     * @return The project to be returned by the service
     */
    private SiriusServerProjectDto getProjectFromModelingProject(ModelingProject modelingProject) {
        Session session = SiriusServerUtils.getSession(modelingProject);

        String projectName = modelingProject.getProject().getName();
        String description = SiriusServerUtils.getProjectDescription(modelingProject.getProject());
        List<AbstractSiriusServerRepresentationDto> representations = this.getRepresentations(session);
        List<SiriusServerSemanticResourceDto> semanticResources = this.getSemanticResources(modelingProject.getProject(), session);
        List<SiriusServerPageDto> pages = this.getPages(modelingProject, session);
        List<SiriusServerSectionDto> currentPageSections = this.getFirstPageSections(session);
        return new SiriusServerProjectDto(projectName, description, representations, semanticResources, pages, currentPageSections);
    }

    /**
     * Returns the list of workflow page from the given session.
     *
     * @param modelingProject
     *            The modeling project
     * @param session
     *            The session
     * @return The list of workflow page from the given session
     */
    private List<SiriusServerPageDto> getPages(ModelingProject modelingProject, Session session) {
        return WorkflowHelper.on(session).getPageDescriptions().map(page -> {
            DAnalysis self = ((DAnalysisSession) session).allAnalyses().stream().findFirst().orElse(null);
            Map<String, Object> variables = new HashMap<>();
            variables.put(SELF, self);
            IEvaluationResult result = new SiriusBackendInterpreter(session).evaluateExpression(variables, page.getTitleExpression());

            String identifier = page.getName();
            String name = result.asString();
            return new SiriusServerPageDto(identifier, name);
        }).collect(Collectors.toList());
    }

    /**
     * Returns the list of the sections of the current page.
     *
     * @param session
     *            The session
     * @return The list of the sections of the current page
     */
    private List<SiriusServerSectionDto> getFirstPageSections(Session session) {
        Optional<PageDescription> optionalPageDescription = WorkflowHelper.on(session).getPageDescriptions().findFirst();
        List<SectionDescription> sectionDescriptions = optionalPageDescription.map(PageDescription::getSections).orElseGet(BasicEList::new);

        // @formatter:off
        return sectionDescriptions.stream()
                .map(sectionDescription -> this.convertSection(session, sectionDescription))
                .collect(Collectors.toList());
        // @formatter:on
    }

    /**
     * Converts the given {@link SectionDescription}.
     *
     * @param session
     *            The Sirius session
     * @param sectionDescription
     *            The section description
     * @return The section DTO
     */
    private SiriusServerSectionDto convertSection(Session session, SectionDescription sectionDescription) {
        String sectionIdentifier = sectionDescription.getName();

        DAnalysis self = ((DAnalysisSession) session).allAnalyses().stream().findFirst().orElse(null);
        Map<String, Object> variables = new HashMap<>();
        variables.put(SELF, self);
        IEvaluationResult result = new SiriusBackendInterpreter(session).evaluateExpression(variables, sectionDescription.getTitleExpression());
        String sectionName = result.asString();

        // @formatter:off
        List<SiriusServerActivityDto> activities = sectionDescription.getActivities().stream()
                .map(activityDescription -> this.convertActivity(session, activityDescription))
                .collect(Collectors.toList());
        // @formatter:on

        return new SiriusServerSectionDto(sectionIdentifier, sectionName, activities);
    }

    /**
     * Converts the given {@link ActivityDescription}.
     *
     * @param session
     *            The Sirius session
     * @param activityDescription
     *            The activity description
     * @return The activity DTO
     */
    private SiriusServerActivityDto convertActivity(Session session, ActivityDescription activityDescription) {
        String activityIdentifier = activityDescription.getName();

        DAnalysis self = ((DAnalysisSession) session).allAnalyses().stream().findFirst().orElse(null);
        Map<String, Object> variables = new HashMap<>();
        variables.put(SELF, self);
        IEvaluationResult result = new SiriusBackendInterpreter(session).evaluateExpression(variables, activityDescription.getLabelExpression());
        String activityName = result.asString();

        return new SiriusServerActivityDto(activityIdentifier, activityName);
    }

    /**
     * Returns the list of representations from the given session.
     *
     * @param session
     *            The session
     * @return The list of representations from the given session
     */
    private List<AbstractSiriusServerRepresentationDto> getRepresentations(Session session) {
        Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        // @formatter:off
		return representationDescriptors.stream()
			.filter(descriptor -> !descriptor.getDescription().eIsProxy())
			.map(this::convertToRepresentation)
			.collect(Collectors.toList());
		// @formatter:on
    }

    /**
     * Converts the given {@link DRepresentationDescriptor} into an
     * {@link AbstractSiriusServerRepresentationDto}.
     *
     * @param descriptor
     *            The descriptor
     * @return The {@link AbstractSiriusServerRepresentationDto}
     */
    private AbstractSiriusServerRepresentationDto convertToRepresentation(DRepresentationDescriptor descriptor) {
        String name = descriptor.getName();

        RepresentationDescription description = descriptor.getDescription();
        String descriptionName = description.getName();

        // @formatter:off
		String viewpointName = Optional.of(description.eContainer())
				.filter(Viewpoint.class::isInstance)
				.map(Viewpoint.class::cast)
				.map(Viewpoint::getName)
				.orElse(""); //$NON-NLS-1$
		// @formatter:on

        AbstractSiriusServerRepresentationDto representation = null;
        if (description instanceof DiagramDescription) {
            representation = new SiriusServerDiagramDto(viewpointName, descriptionName, name);
        } else if (description instanceof TableDescription) {
            representation = new SiriusServerTableDto(viewpointName, descriptionName, name);
        } else if (description instanceof TreeDescription) {
            representation = new SiriusServerTreeDto(viewpointName, descriptionName, name);
        }

        return representation;
    }

    /**
     * Returns the list of semantic resources from the given session.
     *
     * @param session
     *            The Sirius session
     * @return The list of semantic resources from the given session
     */
    private List<SiriusServerSemanticResourceDto> getSemanticResources(IProject project, Session session) {
        Collection<Resource> semanticResources = session.getSemanticResources();
        // @formatter:off
		return semanticResources.stream()
				.map(Resource::getURI)
				.filter(URI::isPlatformResource)
				.map(uri -> {
					String platformString = uri.toPlatformString(true);
					return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString));
				})
				.filter(iFile -> iFile.getProject().equals(project))
				.map(this::convertToSemanticResource)
				.collect(Collectors.toList());
		// @formatter:on
    }

    /**
     * Converts the given IFile into a {@link SiriusServerSemanticResourceDto}.
     *
     * @param iFile
     *            The semantic resource
     * @return A {@link SiriusServerSemanticResourceDto}.
     */
    private SiriusServerSemanticResourceDto convertToSemanticResource(IFile iFile) {
        String name = iFile.getName();
        String path = iFile.getProjectRelativePath().toString();

        long size = 0;

        File file = iFile.getLocation().toFile();
        try {
            size = Files.size(file.toPath());
        } catch (IOException exception) {
            IStatus status = new Status(IStatus.ERROR, SiriusServerBackendPlugin.PLUGIN_ID, exception.getMessage(), exception);
            SiriusServerBackendPlugin.getPlugin().log(status);
        }

        String sizeLabel = this.getSizeLabel(size);

        return new SiriusServerSemanticResourceDto(path, name, sizeLabel);
    }

    /**
     * Returns a label displaying the given size in KB or MB.
     *
     * @param size
     *            The size
     * @return The label
     */
    private String getSizeLabel(long size) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00"); //$NON-NLS-1$

        double kb = 1024d;
        double mb = kb * 1024d;

        if (size > mb) {
            return decimalFormat.format(size / mb) + "MB"; //$NON-NLS-1$
        }
        return decimalFormat.format(size / kb) + "KB"; //$NON-NLS-1$
    }

    @Override
    public SiriusServerResponse doPut(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        SiriusServerResponse response = null;
        try {
            Reader reader = new InputStreamReader(request.getInputStream(), SiriusServerUtils.UTF_8);
            SiriusServerUpdateProjectDescriptionDto updateProjectDescription = new Gson().fromJson(reader, SiriusServerUpdateProjectDescriptionDto.class);

            String projectName = variables.get(PROJECT_NAME);
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
            if (project.exists()) {
                IProjectDescription description = project.getDescription();
                description.setComment(updateProjectDescription.getDescription());
                project.setDescription(description, new NullProgressMonitor());
                response = new SiriusServerResponse(STATUS_OK, new SiriusServerProjectDescriptionUpdatedDto(description.getComment()));
            } else {
                response = new SiriusServerResponse(STATUS_NOT_FOUND);
            }
        } catch (@SuppressWarnings("unused") IOException | CoreException exception) {
            // We don't want to send back the message of the exception
            response = new SiriusServerResponse(STATUS_INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
