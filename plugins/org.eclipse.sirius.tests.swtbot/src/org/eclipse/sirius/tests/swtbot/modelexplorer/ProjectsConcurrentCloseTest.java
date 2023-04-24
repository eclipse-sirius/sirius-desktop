/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.table.CandidateMappingManager;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIProject;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OpenedSessionCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.internal.session.EditingSession;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test that we do not get an error when closing several selected projects.
 *
 * @author mporhel
 */
public class ProjectsConcurrentCloseTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_FILE = "rep1.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String DATA_UNIT_DIR = "data/unit/closingprojects/";

    private SWTBot modelExplorerViewBot;

    private UILocalSession localSession2;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // First Project
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE);

        // Second Project
        // Creation
        designerPerspective.createProject(getProject2Name());
        // Copy files
        copyFileToProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, getProject2Name(), MODEL_FILE, SESSION_FILE);
    }

    /**
     * Get the project name to create.
     *
     * @return Project name.
     */
    protected String getProject2Name() {
        return getProjectName() + "2";
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        SWTBotUtils.waitAllUiEvents();

        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        modelExplorerView.setFocus();
        modelExplorerViewBot = modelExplorerView.bot();

        prepareModelingProject(getProjectName());
        bot.waitUntil(new OpenedSessionCondition(1));

        prepareModelingProject(getProject2Name());
        bot.waitUntil(new OpenedSessionCondition(2));

        // Activate Design Viewpoint
        List<Session> sessions = new ArrayList<Session>(SessionManager.INSTANCE.getSessions());
        localSession = new UILocalSession(UIResource.createFromResource(sessions.get(0).getSessionResource()));
        localSession2 = new UILocalSession(UIResource.createFromResource(sessions.get(1).getSessionResource()));
    }

    private void prepareModelingProject(final String projectName) throws InterruptedException {
        final UIProject uiProject = new UIProject(projectName);
        uiProject.select();

        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return SWTBotUtils.hasContextMenu(uiProject.getProjectTreeItem(), "Convert to Modeling Project");
            }

            @Override
            public String getFailureMessage() {
                return "Convert menu was not available on " + projectName;
            }

        });
        uiProject.convertToModelingProject();
        SWTBotUtils.waitAllUiEvents();
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        SWTBotUtils.waitProgressMonitorClose("Operation in progress...");
    }

    /**
     * This test verifies that two projects can be closed 'concurrently': user
     * select several projects and right click "Close Project".
     *
     * No {@link ConcurrentModificationException} should occur (one was
     * previously triggered in {@link EditingSession}).
     */
    public void testProjectConcurrentClose() {
        assertEquals("Both modeling projects sessions should be opened.", 2, SessionManager.INSTANCE.getSessions().size());

        modelExplorerViewBot.tree().select(getProjectName(), getProject2Name());
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return modelExplorerViewBot.tree().selectionCount() == 2;
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Two projects should be selected.";
            }
        });
        String closeProjects = "Close Project";
        if (TestsUtil.isPhotonPlatformOrLater()) {

            closeProjects = "Close Projects";
        }
        SWTBotUtils.clickContextMenu(modelExplorerViewBot.tree(), closeProjects);

        SWTBotUtils.waitAllUiEvents();

        bot.waitUntil(new OpenedSessionCondition(0));
        assertFalse(getErrorLoggersMessage(), doesAnErrorOccurs());
    }

    /**
     * This test verifies that two projects with opened diagrams can be closed
     * 'concurrently': user select several projects and right click
     * "Close Project".
     *
     * No {@link ConcurrentModificationException} should occur (one was
     * previously triggered in {@link CandidateMappingManager}).
     */
    public void testProjectConcurrentCloseWithOpenedDiagrams() {
        // open a diagram per session.
        openRepresentation(localSession.getOpenedSession(), "Entities", "p package entities", DDiagram.class);
        openRepresentation(localSession2.getOpenedSession(), "Entities", "p package entities", DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        // close projects
        testProjectConcurrentClose();

    }
}
