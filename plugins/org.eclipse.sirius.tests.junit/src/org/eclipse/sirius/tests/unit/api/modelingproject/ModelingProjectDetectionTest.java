/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.tests.unit.api.modelingproject;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * Tests that Modeling Projects are properly detected and opened even if they
 * have non-standard structure (i.e. structure that can be difficult or
 * impossible to obtain manually but that can occur programmatically).
 * 
 * @author pcdavid
 */
public class ModelingProjectDetectionTest extends SiriusTestCase {

    private static final String FIXTURE_ROOT = "data/unit/project/MultiAirdNoViewpoints";

    private static final String[] FIXTURE_FILES = { "My_.aird", "My_.ecore", "My.ecore", "representations.aird" };

    /**
     * Tests that a modeling project which contains multiple (fragmented) aird
     * files but no ownedViews is still properly analyzed and opened.
     * <p>
     * This used to cause a misdetection of the project structure in the SAX
     * parser, causing this error: "Found 2 main representations files (that
     * means not referenced by another) in "DesignerTestProject":
     * representations.airdand My_.aird. A modeling project must contain only
     * one."
     * 
     * @throws Exception
     *             if an unexpected error occurs during the test.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=526258"
     */
    public void test_modeling_project_with_multiple_aird_and_no_viewpoints() throws Exception {
        // Create a Modeling project from the fixture files.
        WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
            @Override
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                for (String file : FIXTURE_FILES) {
                    EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, FIXTURE_ROOT + "/" + file, TEMPORARY_PROJECT_NAME + "/" + file);
                }
                ModelingProjectManager.INSTANCE.convertToModelingProject(ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME), new NullProgressMonitor());
            }
        };
        op.run(new NullProgressMonitor());
        // Check the session was correctly opened, including the referenced
        // analysis
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/representations.aird", true), new NullProgressMonitor());
        assertNotNull(session);
        assertTrue("The session was not automatically opened", session.isOpen());
        assertEquals("The aird fragment was not correctly detected", 2, session.getAllSessionResources().size());
        assertEquals("The sample data project should not have any ownedView (see #526258)", 0, session.getOwnedViews().size());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
