/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.control;

import java.io.File;
import java.text.Collator;
import java.util.Collections;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.ui.ide.undo.CopyResourcesOperation;

/**
 * Tests behaviors when model (semantic and session) is controlled outside the
 * current Eclipse.
 * 
 * @author mporhel
 */
public class ExternalControlTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/control/external/";

    private static final String BASE = "base/";

    private static final String CONTROLLED = "controlled/";

    private static final String MAIN_SEMANTIC_MODEL_FILENAME = "main.ecore";

    private static final String MAIN_SESSION_MODEL_FILENAME = "main.aird";

    private static final String MAIN_SESSION_MODEL_FILENAME_2 = "rmain.aird";

    private static final String FRAGMENT_SEMANTIC_MODEL_FILENAME = "fragment.ecore";

    private static final String FRAGMENT_SESSION_MODEL_FILENAME = "fragment.aird";

    private boolean autoBuild;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        autoBuild = ResourcesPlugin.getPlugin().getPluginPreferences().getBoolean(ResourcesPlugin.PREF_AUTO_BUILDING);

        ResourcesPlugin.getPlugin().getPluginPreferences().setValue(ResourcesPlugin.PREF_AUTO_BUILDING, false);
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH + BASE, MAIN_SEMANTIC_MODEL_FILENAME, MAIN_SESSION_MODEL_FILENAME, MAIN_SESSION_MODEL_FILENAME_2);
        copyFiles(SiriusTestsPlugin.PLUGIN_ID, PATH + CONTROLLED, TEMPORARY_PROJECT_NAME + File.separator + CONTROLLED, FRAGMENT_SEMANTIC_MODEL_FILENAME, FRAGMENT_SESSION_MODEL_FILENAME,
                MAIN_SEMANTIC_MODEL_FILENAME, MAIN_SESSION_MODEL_FILENAME, MAIN_SESSION_MODEL_FILENAME_2);

    }

    @Override
    protected void tearDown() throws Exception {
        ResourcesPlugin.getPlugin().getPluginPreferences().setValue(ResourcesPlugin.PREF_AUTO_BUILDING, autoBuild);
        super.tearDown();
    }

    /**
     * This test check that the reload is properly done when a representations
     * file is modified outside the session when the session file is located
     * after the semantic file. After an external control followed by a
     * representation creation in the referenced aird, the new representation
     * should be displayed in the model explorer view and the adapters must be
     * added on the new referenced analysis.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testReloadAfterExternalControlAndNewRepresentationCreation() throws Exception {
        String sessionFileName = MAIN_SESSION_MODEL_FILENAME_2;
        String semanticFileName = MAIN_SEMANTIC_MODEL_FILENAME;
        assertTrue("Session file name should be after the semantic model in the project children list.", Collator.getInstance().compare(sessionFileName, semanticFileName) > 0);

        doTestReloadAfterExternalControlAndNewRepresentationCreation(sessionFileName, semanticFileName);
    }

    /**
     * This test check that the reload is properly done when a representations
     * file is modified outside the session when the session file is located
     * after the semantic file. After an external control followed by a
     * representation creation in the referenced aird, the new representation
     * should be displayed in the model explorer view and the adapters must be
     * added on the new referenced analysis.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testReloadAfterExternalControlAndNewRepresentationCreation2() throws Exception {
        String sessionFileName = MAIN_SESSION_MODEL_FILENAME;
        String semanticFileName = MAIN_SEMANTIC_MODEL_FILENAME;
        assertTrue("Session file name should be before the semantic model in the project children list.", Collator.getInstance().compare(sessionFileName, semanticFileName) < 0);
        doTestReloadAfterExternalControlAndNewRepresentationCreation(sessionFileName, semanticFileName);
    }

    private void doTestReloadAfterExternalControlAndNewRepresentationCreation(String sessionFileName, String semanticFileName) throws Exception, CoreException, InterruptedException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        root.refreshLocal(IResource.DEPTH_INFINITE, null);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + semanticFileName), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + sessionFileName);

        assertEquals("The session should not be dirty before the external modification.", SessionStatus.SYNC, session.getStatus());
        assertEquals("The session should contains 2 representations before the external modification.", 2, DialectManager.INSTANCE.getAllRepresentations(session).size());
        assertEquals("There should be only 1 session resource before the external modification.", 1, ((DAnalysisSessionImpl) session).getAllSessionResources().size());
        assertEquals("There should be only 1 semantic resource before the external modification.", 1, session.getSemanticResources().size());
        assertEquals("There should not be any controlled resource before the external modification.", 0, ((DAnalysisSessionImpl) session).getControlledResources().size());

        final DAnalysisSessionImpl analysisSession = (DAnalysisSessionImpl) session;
        ICondition done = new ICondition() {

            @Override
            public boolean test() throws Exception {
                return 2 == analysisSession.allAnalyses().size();
            }

            @Override
            public String getFailureMessage() {
                return "We should get 2 session resources after the copy";
            }
        };

        // Replace files per externally modified files and refresh once ater the
        // copy.
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        IFolder folder = project.getFolder(CONTROLLED);
        CopyResourcesOperation copy = new CopyResourcesOperation(folder.members(), project.getFullPath(), "copy");
        copy.execute(new NullProgressMonitor(), null);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        TestsUtil.waitUntil(done);

        assertEquals("The session should not be dirty after external modification.", SessionStatus.SYNC, session.getStatus());
        assertEquals("The session should contains 3 representations after the external modification, check the adapters on the session resources", 3,
                DialectManager.INSTANCE.getAllRepresentations(session).size());
        assertEquals("There should be 2 session files after the external modifications", 2, ((DAnalysisSessionImpl) session).getAllSessionResources().size());
        assertEquals("There should be 1 session resource after the external modification, the second semantic resource is a fragment, check the controlled resources detection.", 1, session
                .getSemanticResources().size());
        assertEquals("There should be 1 controlled resource after the external modification, check the controlled resources detection.", 1, ((DAnalysisSessionImpl) session).getControlledResources()
                .size());
    }
}
