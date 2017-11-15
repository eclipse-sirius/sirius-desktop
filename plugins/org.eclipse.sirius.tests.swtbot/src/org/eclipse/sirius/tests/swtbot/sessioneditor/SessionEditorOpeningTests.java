/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sessioneditor;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotPerspective;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Tests that the session editor is opened in various situation.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SessionEditorOpeningTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_FILE = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/edgeCreation/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE);
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Tests that the aird editor is opened when the aird file is double clicked from Model explorer view and when the
     * project has not the modeling nature.
     */
    public void testOpenSessionEditorByDoubleClick() {
        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        modelExplorerView.setFocus();
        SWTBot packageExplorerViewBot = modelExplorerView.bot();

        SWTBotTreeItem expandedNode = packageExplorerViewBot.tree().expandNode("DesignerTestProject", true);
        SWTBotTreeItem node = expandedNode.getNode("My.aird");
        node.doubleClick();

        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor() != null;
            }

            @Override
            public String getFailureMessage() {
                return "Wrong Active editor is : " + PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor().getEditorSite().getId();
            }
        });
        assertEquals("The session editor has not opened.", "org.eclipse.sirius.ui.editor.session",
                PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor().getEditorSite().getId());
    }

    /**
     * Tests that the aird editor is opened when the aird file is double clicked from Package explorer view and when the
     * project has not the modeling nature.
     */
    public void testOpenSessionEditorByDoubleClick2() {
        SWTBotPerspective javaPerspective = bot.perspectiveByLabel("Java");
        javaPerspective.activate();
        SWTBotView packageExplorerView = bot.viewById("org.eclipse.jdt.ui.PackageExplorer");
        packageExplorerView.setFocus();
        SWTBot packageExplorerViewBot = packageExplorerView.bot();

        assertSessionEditorOpened(packageExplorerViewBot);
    }

    private void assertSessionEditorOpened(SWTBot packageExplorerViewBot) {
        SWTBotTreeItem expandedNode = packageExplorerViewBot.tree().expandNode("DesignerTestProject", true);
        SWTBotTreeItem node = expandedNode.getNode("My.aird");
        node.doubleClick();

        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor() != null;
            }

            @Override
            public String getFailureMessage() {
                return "Wrong Active editor is : " + PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor().getEditorSite().getId();
            }
        });
        assertEquals("The session editor has not opened.", "org.eclipse.sirius.ui.editor.session",
                PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor().getEditorSite().getId());
    }

    /**
     * Tests that the aird editor is opened when the aird file is double clicked from Model explorer view and when the
     * project has the modeling nature.
     */
    public void testOpenSessionEditorByDoubleClick3() {
        designerProject.convertToModelingProject();

        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        modelExplorerView.setFocus();
        SWTBot packageExplorerViewBot = modelExplorerView.bot();

        assertSessionEditorOpened(packageExplorerViewBot);
    }

    /**
     * Tests that the aird editor is opened when the aird file is double clicked from Package explorer view and when the
     * project has the modeling nature.
     */
    public void testOpenSessionEditorByDoubleClick4() {
        designerProject.convertToModelingProject();

        SWTBotPerspective javaPerspective = bot.perspectiveByLabel("Java");
        javaPerspective.activate();
        SWTBotView packageExplorerView = bot.viewById("org.eclipse.jdt.ui.PackageExplorer");
        packageExplorerView.setFocus();
        SWTBot packageExplorerViewBot = packageExplorerView.bot();

        assertSessionEditorOpened(packageExplorerViewBot);
    }

    /**
     * Tests that the aird editor is opened when the aird file is double clicked from Project explorer view and when the
     * project has the modeling nature.
     */
    public void testOpenSessionEditorByDoubleClick5() {
        designerProject.convertToModelingProject();

        Display.getDefault().syncExec(() -> {
            try {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.navigator.ProjectExplorer");
            } catch (PartInitException e) {
                e.printStackTrace();
            }
        });

        SWTBotView packageExplorerView = bot.viewById("org.eclipse.ui.navigator.ProjectExplorer");
        packageExplorerView.setFocus();
        SWTBot packageExplorerViewBot = packageExplorerView.bot();

        assertSessionEditorOpened(packageExplorerViewBot);
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        super.tearDown();
    }
}
