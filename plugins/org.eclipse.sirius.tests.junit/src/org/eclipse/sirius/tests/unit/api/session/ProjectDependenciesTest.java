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
package org.eclipse.sirius.tests.unit.api.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.query.SiriusProjectDependencies;
import org.eclipse.sirius.business.api.query.SiriusProjectDependencyQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Test for the project dependencies.
 * 
 * @author lfasani
 */
public class ProjectDependenciesTest extends SiriusDiagramTestCase implements EcoreModeler {

    public static final String FOLDER_PATH = "/data/unit/project/dependencies/";//$NON-NLS-1$

    public static final String PROJECT1 = "project1Ecore";//$NON-NLS-1$

    public static final String PROJECT2 = "project2Ecore";//$NON-NLS-1$

    public static final String PROJECT3 = "project3Ecore";//$NON-NLS-1$

    public static final String AIRD = "project1Ecore.aird";//$NON-NLS-1$

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyAllFiles(SiriusTestsPlugin.PLUGIN_ID, FOLDER_PATH + PROJECT1, PROJECT1);
        copyAllFiles(SiriusTestsPlugin.PLUGIN_ID, FOLDER_PATH + PROJECT2, PROJECT2);
        copyAllFiles(SiriusTestsPlugin.PLUGIN_ID, FOLDER_PATH + PROJECT3, PROJECT3);

        genericSetUp(Collections.<URI> emptyList(), Collections.<URI> emptyList(), true, toURI(PROJECT1 + "/" + AIRD, ResourceURIType.RESOURCE_PLATFORM_URI)); //$NON-NLS-1$

        TestsUtil.emptyEventsFromUIThread();
    }

    public void testDependenciesWithNotAnalysedProject() {
        EclipseTestsSupportHelper.INSTANCE.deleteProject(PROJECT2);
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT1);

        // check getAllDependencies
        SiriusProjectDependencies allDependencies = new SiriusProjectDependencyQuery(project).getAllDependencies();

        List<String> generalProjectDirectDependencies = new ArrayList<>(allDependencies.getGeneralProjectDirectDependencies());
        assertTrue(generalProjectDirectDependencies.isEmpty());

        List<String> imageProjectDirectDependencies = new ArrayList<>(allDependencies.getImageProjectsDirectDependencies());
        assertEquals(imageProjectDirectDependencies.size(), 1);
        assertEquals(imageProjectDirectDependencies.get(0), PROJECT3);

        List<String> transitiveDependencies = new ArrayList<>(allDependencies.getGeneralProjectTransitiveDependencies());
        assertTrue(transitiveDependencies.isEmpty());

        List<String> notAnalysedDependencies = new ArrayList<>(allDependencies.getNotAnalysedGeneralProjectDependencies());
        assertEquals(notAnalysedDependencies.size(), 1);
        assertEquals(notAnalysedDependencies.get(0), PROJECT2);
    }

    public void testDependenciesOnOpenedSession() {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT1);

        getAndCheckDependencies(project);
    }

    public void testDependenciesOnClosedSession() {
        session.close(new NullProgressMonitor());

        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT1);

        getAndCheckDependencies(project);
    }

    private void getAndCheckDependencies(IProject project) {
        // PROJECT1 has a general dep on PROJECT2
        // PROJECT1 has a image dep on PROJECT3
        // PROJECT2 has a general dep on PROJECT2

        // check getDirectDependencies
        SiriusProjectDependencies directDependencies = new SiriusProjectDependencyQuery(project).getDirectDependencies();

        List<String> generalProjectDirectDependencies = new ArrayList<>(directDependencies.getGeneralProjectDirectDependencies());
        assertEquals(generalProjectDirectDependencies.size(), 1);
        assertEquals(generalProjectDirectDependencies.get(0), PROJECT2);

        List<String> imageProjectDirectDependencies = new ArrayList<>(directDependencies.getImageProjectsDirectDependencies());
        assertEquals(imageProjectDirectDependencies.size(), 1);
        assertEquals(imageProjectDirectDependencies.get(0), PROJECT3);

        List<String> transitiveDependencies = new ArrayList<>(directDependencies.getGeneralProjectTransitiveDependencies());
        assertTrue(transitiveDependencies.isEmpty());

        assertTrue(directDependencies.getNotAnalysedGeneralProjectDependencies().isEmpty());

        // check getAllDependencies
        SiriusProjectDependencies allDependencies = new SiriusProjectDependencyQuery(project).getAllDependencies();

        generalProjectDirectDependencies = new ArrayList<>(allDependencies.getGeneralProjectDirectDependencies());
        assertEquals(generalProjectDirectDependencies.size(), 1);
        assertEquals(generalProjectDirectDependencies.get(0), PROJECT2);

        imageProjectDirectDependencies = new ArrayList<>(allDependencies.getImageProjectsDirectDependencies());
        assertEquals(imageProjectDirectDependencies.get(0), PROJECT3);

        transitiveDependencies = new ArrayList<>(allDependencies.getGeneralProjectTransitiveDependencies());
        assertEquals(transitiveDependencies.size(), 1);
        assertEquals(transitiveDependencies.get(0), PROJECT3);

        assertTrue(allDependencies.getNotAnalysedGeneralProjectDependencies().isEmpty());
    }
}
