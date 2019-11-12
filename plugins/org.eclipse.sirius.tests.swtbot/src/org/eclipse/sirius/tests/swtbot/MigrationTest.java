/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo.
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
package org.eclipse.sirius.tests.swtbot;

import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * This class tests various functionalities of the {@link SessionEditor}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class MigrationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/migration/userSave/";

    private static final String SEMANTIC_MODEL_FILENAME = "My.ecore";

    private static final String MODELER_MODEL_FILENAME = "My.odesign";

    private static final String SESSION_FILE_NAME = "My.aird";

    private SessionEditor sessionEditor;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        changeSiriusCommonPreference(CommonPreferencesConstants.PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION, true);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, MODELER_MODEL_FILENAME, SESSION_FILE_NAME);

    }

    @Override
    protected void tearDown() throws Exception {
        if (sessionEditor != null) {
            sessionEditor.close(false);
            sessionEditor = null;
        }
        super.tearDown();

    }

    private enum Saving {
        OK, NO
    }

    private void testMigrationFromDoubleClick(Saving airdSaving, Saving VSMSaving) {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * This test fails on some IC server because of a problem of rendering HTML in the aird editor. A popup
             * appears with title "Internal Error" and message
             * "An SWT error has occurred. You are recommended to exit the workbench...". So the test fails. In
             * org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointsSelectionGraphicalHandler.createBrowser(
             * Composite) it seems that this case should be correctly handled but it is not the case.
             */
            return;
        }
        SWTBotView modelExplorerView = bot.viewById("org.eclipse.sirius.ui.tools.views.model.explorer");
        SWTBotTreeItem project = modelExplorerView.bot().tree().getTreeItem("DesignerTestProject");
        project.expand();
        SWTBotTreeItem node = project.getNode("My.aird").expand();
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                try {

                    node.select().doubleClick();
                    bot.shell("Save");
                    return true;
                } catch (WidgetNotFoundException e) {
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The dialog asking user to save has not opened.";
            }
        });
        SWTBotShell shell = bot.shell("Save");

        if (Saving.OK == airdSaving) {
            shell.bot().button("Yes").click();
        } else {
            if (Saving.NO == airdSaving) {
                shell.bot().button("No").click();
            } else {
                shell.bot().button("Cancel").click();
            }
        }
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return bot.editors().size() > 0;
            }

            @Override
            public String getFailureMessage() {
                return "Session editor did not opened.";
            }
        });
        SWTBotEditor editorBot;
        DAnalysis dAnalysis = getDAnalysis();

        if (Saving.OK == airdSaving) {
            assertNotEquals("Aird Migration should have been done.", "8.0.0", dAnalysis.getVersion());
        } else {
            assertEquals("Aird Migration should have not been done.", "8.0.0", dAnalysis.getVersion());
        }
        SWTBotTreeItem nodeDesign = project.getNode("My.odesign");
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                try {
                    nodeDesign.select().doubleClick();
                    bot.shell("Save");
                    return true;
                } catch (WidgetNotFoundException e) {
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The dialog asking user to save has not opened.";
            }
        });
        shell = bot.shell("Save");

        if (Saving.OK == airdSaving) {
            shell.bot().button("Yes").click();
        } else {
            if (Saving.NO == airdSaving) {
                shell.bot().button("No").click();
            } else {
                shell.bot().button("Cancel").click();
            }
        }
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return bot.editors().size() > 1;
            }

            @Override
            public String getFailureMessage() {
                return "VSM editor did not opened.";
            }
        });
        editorBot = bot.editors().get(1);
        CustomSiriusEditor customSiriusEditor = (CustomSiriusEditor) editorBot.getReference().getEditor(false);
        Resource resource = customSiriusEditor.getEditingDomain().getResourceSet().getResources().get(0);
        Group group = (Group) resource.getContents().get(0);

        if (Saving.OK == airdSaving) {
            assertNotEquals("VSM migration should have been done.", "8.0.0", group.getVersion());
        } else {
            assertEquals("VSM migration should have not been done.", "8.0.0", group.getVersion());
        }

    }

    private DAnalysis getDAnalysis() {
        SWTBotEditor editorBot = bot.editors().get(0);
        sessionEditor = (SessionEditor) editorBot.getReference().getEditor(false);
        Session session = sessionEditor.getSession();
        Resource sessionResource = session.getSessionResource();
        DAnalysis dAnalysis = (DAnalysis) sessionResource.getContents().get(0);
        return dAnalysis;
    }

    private void testMigrationFromModelingProjectConversion(Saving airdSaving, Saving VSMSaving) {
        Display.getDefault().asyncExec(() -> {
            WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
                @Override
                protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                    ModelingProjectManager.INSTANCE.convertToModelingProject(ResourcesPlugin.getWorkspace().getRoot().getProject(TEMP_PROJECT_NAME), new NullProgressMonitor());
                }
            };
            try {
                op.run(new NullProgressMonitor());
            } catch (InvocationTargetException | InterruptedException e1) {
            }
        });

        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                try {
                    bot.shell("Save");
                    return true;
                } catch (WidgetNotFoundException e) {
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The dialog asking user to save has not opened.";
            }
        });
        SWTBotShell shell = bot.shell("Save");

        if (Saving.OK == airdSaving) {
            shell.bot().button("Yes").click();
        } else {
            if (Saving.NO == airdSaving) {
                shell.bot().button("No").click();
            } else {
                shell.bot().button("Cancel").click();
            }
        }
        Session session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(TEMP_PROJECT_NAME + "/My.aird", true), new NullProgressMonitor());
        Resource sessionResource = session.getSessionResource();
        DAnalysis dAnalysis = (DAnalysis) sessionResource.getContents().get(0);

        if (Saving.OK == airdSaving) {
            assertNotEquals("VSM migration should have been done.", "8.0.0", dAnalysis.getVersion());
        } else {
            assertEquals("VSM migration should have not been done.", "8.0.0", dAnalysis.getVersion());
        }

    }

    /**
     * Test that when the preference {@link CommonPreferencesConstants#PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION} is
     * used then a dialog asks user to save aird and VSM after their migration when a double click is done on the files.
     * Also test than when the user choose the yes option, then the saving is done.
     */
    public void testMigrationSavingFromAirdAndVSMDoubleClick() {
        testMigrationFromDoubleClick(Saving.OK, Saving.OK);
    }

    /**
     * Test that when the preference {@link CommonPreferencesConstants#PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION} is
     * used then a dialog asks user to save aird and VSM after their migration when a double click is done on the files.
     * Also test than when the user choose the No option, then the saving is not done.
     */
    public void testMigrationNoSavingFromAirdAndVSMDoubleClick() {
        testMigrationFromDoubleClick(Saving.NO, Saving.NO);
    }

    /**
     * Test that when the preference {@link CommonPreferencesConstants#PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION} is
     * used then a dialog asks user to save aird after their migration when the project is turned into a modeling one.
     * Also test than when the user choose the yes option, then the saving is done.
     */
    public void testMigrationSavingFromAirdAndVSMOpenProject() {
        testMigrationFromModelingProjectConversion(Saving.OK, Saving.OK);
    }

    /**
     * Test that when the preference {@link CommonPreferencesConstants#PREF_ASK_TO_SAVE_RESOURCE_AFTER_MIGRATION} is
     * used then a dialog ask user to save aird after their migration when the project is turned into a modeling one.
     * Also test than when the user choose the No option, then the saving is not done.
     */
    public void testMigrationNoSavingFromAirdAndVSMOpenProject() {
        testMigrationFromModelingProjectConversion(Saving.NO, Saving.NO);
    }

}
