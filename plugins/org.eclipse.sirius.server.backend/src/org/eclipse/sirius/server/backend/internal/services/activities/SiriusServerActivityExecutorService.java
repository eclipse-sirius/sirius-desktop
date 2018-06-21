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
package org.eclipse.sirius.server.backend.internal.services.activities;

import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_NOT_FOUND;
import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_OK;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.server.api.ISiriusServerService;
import org.eclipse.sirius.server.api.SiriusServerPath;
import org.eclipse.sirius.server.api.SiriusServerResponse;
import org.eclipse.sirius.server.backend.internal.services.workflow.SiriusToolServices;
import org.eclipse.sirius.server.backend.internal.services.workflow.WorkflowHelper;
import org.eclipse.sirius.server.backend.internal.utils.SiriusServerUtils;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.workflow.ActivityDescription;

/**
 * The service used to execute a specific activity of a workflow.
 *
 * @author sbegaudeau
 */
@SiriusServerPath("/projects/{projectName}/pages/{pageIdentifier}/sections/{sectionIdentifier}/activities/{activityIdentifier}/execute")
public class SiriusServerActivityExecutorService implements ISiriusServerService {

    /**
     * The name of the variable used to capture the name of the project.
     */
    private static final Object PROJECT_NAME = "projectName"; //$NON-NLS-1$

    /**
     * The name of the variable used to capture the identifier of the page.
     */
    private static final Object PAGE_IDENTIFIER = "pageIdentifier"; //$NON-NLS-1$

    /**
     * The name of the variable used to capture the identifier of the section.
     */
    private static final Object SECTION_IDENTIFIER = "sectionIdentifier"; //$NON-NLS-1$

    /**
     * The name of the variable used to capture the identifier of the activity.
     */
    private static final Object ACTIVITY_IDENTIFIER = "activityIdentifier"; //$NON-NLS-1$

    @Override
    public SiriusServerResponse doPost(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        String projectName = variables.get(PROJECT_NAME);
        String pageId = variables.get(PAGE_IDENTIFIER);
        String sectionId = variables.get(SECTION_IDENTIFIER);
        String activityId = variables.get(ACTIVITY_IDENTIFIER);

        // @formatter:off
        Optional<IProject> optionalProject = Optional.ofNullable(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName));
        Optional<ModelingProject> optionalModelingProject = optionalProject.filter(ModelingProject::hasModelingProjectNature)
                .filter(IProject::isOpen)
                .map(iProject -> ModelingProject.asModelingProject(iProject).get()); // FIXME Sirius Optional removal!
        // @formatter:on
        if (optionalModelingProject.isPresent()) {
            ModelingProject modelingProject = optionalModelingProject.get();
            Session session = SiriusServerUtils.getSession(modelingProject);

            WorkflowHelper.on(session).findActivityById(pageId, sectionId, activityId).ifPresent(activity -> {
                executeActivity(session, activity);
            });

            return new SiriusServerResponse(STATUS_OK);
        }

        return new SiriusServerResponse(STATUS_NOT_FOUND);
    }

    private void executeActivity(Session session, ActivityDescription activity) {
        URI taskURI = EcoreUtil.getURI(activity.getOperation());
        TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
        ted.getCommandStack().execute(new RecordingCommand(ted) {
            @Override
            protected void doExecute() {
                DAnalysis self = ((DAnalysisSessionImpl) session).getAnalyses().get(0);
                new SiriusToolServices().executeOperation(self, taskURI.toString());
            }
        });
    }
}
