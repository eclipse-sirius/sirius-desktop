/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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
package org.eclipse.sirius.tests.services.graphql.internal;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.api.SiriusGraphQLInterpreter;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

/**
 * Tests of the project entity.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLProjectTests {

    /**
     * The name of the GraphQL operation.
     */
    private static final String OPERATION_NAME = "findProject"; //$NON-NLS-1$

    /**
     * The name of the project name variable.
     */
    private static final String PROJECT_NAME = "projectName"; //$NON-NLS-1$

    /**
     * The name of the sample project.
     */
    private static final String SAMPLE_PROJECT_NAME = "org.eclipse.sirius.sample"; //$NON-NLS-1$

    /**
     * The description of the sample project.
     */
    private static final String COMMENT = "SampleDescription"; //$NON-NLS-1$

    /**
     * Initialization of the unit tests.
     * 
     * <ul>
     * <li>Create an empty test project</li>
     * </ul>
     */
    @BeforeClass
    public static void beforeClass() {
        IProgressMonitor monitor = new NullProgressMonitor();

        try {
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(SAMPLE_PROJECT_NAME);
            if (project.exists()) {
                project.delete(true, monitor);
            }
            IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(SAMPLE_PROJECT_NAME);
            projectDescription.setComment(COMMENT);

            project.create(projectDescription, monitor);
            project.open(monitor);
        } catch (CoreException e) {
            IStatus status = new Status(IStatus.ERROR, SiriusGraphQLTestsPlugin.PLUGIN_ID, e.getMessage(), e);
            SiriusGraphQLTestsPlugin.getPlugin().log(status);
        }
    }

    /**
     * Evaluate the GraphQL query and compare it with the expected JSON result.
     * 
     * @param query
     *            The query
     * @param variables
     *            The variables
     * @param result
     *            The expected JSON result
     */
    private void evaluateQuery(String query, Map<String, Object> variables, String result) {
        ISiriusGraphQLQueryResult graphqQLResult = new SiriusGraphQLInterpreter().execute(query, variables, OPERATION_NAME, null);
        String json = new Gson().toJson(graphqQLResult.getData());
        String expected = result.replaceAll("\\s+", ""); //$NON-NLS-1$ //$NON-NLS-2$

        assertEquals(expected, json);
    }

    /**
     * Test the retrieval of the name of one project.
     */
    @Test
    public void testFindProjectNameByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectNameByName, variables, SiriusGraphQLTestsMessages.findProjectNameByName_resultSample);
    }

    /**
     * Test the retrieval of the path of one project.
     */
    @Test
    public void testFindProjectPathByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectPathByName, variables, SiriusGraphQLTestsMessages.findProjectPathByName_resultSample);
    }

    /**
     * Test the retrieval of the container of one project.
     */
    @Test
    public void testFindProjectContainerByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectContainerByName, variables, SiriusGraphQLTestsMessages.findProjectContainerByName_resultSample);
    }

    /**
     * Test the retrieval of the project of one project.
     */
    @Test
    public void testFindProjectProjectByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectProjectByName, variables, SiriusGraphQLTestsMessages.findProjectProjectByName_resultSample);
    }

    /**
     * Test the retrieval of the resources of one project.
     */
    @Test
    public void testFindProjectResourcesByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectResourcesByName, variables, SiriusGraphQLTestsMessages.findProjectResourcesByName_resultSample);
    }

    /**
     * Test the retrieval of the description of one project.
     */
    @Test
    public void testFindDescriptionResourcesByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectDescriptionByName, variables, SiriusGraphQLTestsMessages.findProjectDescriptionByName_resultSample);
    }

    /**
     * Test the retrieval of a resource of one project.
     */
    @Test
    public void testFindProjectResourceByPathByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectResourceByPathByName, variables, SiriusGraphQLTestsMessages.findProjectResourceByPathByName_resultSample);
    }

    /**
     * Test the retrieval of the activated viewpoints of one project.
     */
    @Test
    public void testFindProjectActivatedViewpointsByName() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROJECT_NAME, SAMPLE_PROJECT_NAME);

        this.evaluateQuery(SiriusGraphQLTestsMessages.findProjectActivatedViewpointsByName, variables, SiriusGraphQLTestsMessages.findProjectActivatedViewpointsByName_resultSample);
    }
}
