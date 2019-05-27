/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorReference;

/**
 * Test the service implementation navigation from VSM service call in interpreted expressions.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 * 
 */
public class ServiceNavigationTest extends AbstractContentAssistTest {

    private final class JavaEditorOpenedCondition implements ICondition {
        private boolean javaEditorOpened;

        /**
         * @param javaEditorOpened
         *            true if the Java editor should be opened at some point. False it should never be opened.
         */
        public JavaEditorOpenedCondition(boolean javaEditorOpened) {
            this.javaEditorOpened = javaEditorOpened;
        }

        @Override
        public boolean test() throws Exception {
            SWTBotEditor activeEditor = bot.activeEditor();
            IEditorReference reference = activeEditor.getReference();
            return (javaEditorOpened && "org.eclipse.jdt.ui.CompilationUnitEditor".equals(reference.getId()))
                    || (!javaEditorOpened && !"org.eclipse.jdt.ui.CompilationUnitEditor".equals(reference.getId()));
        }

        @Override
        public String getFailureMessage() {
            return "Java editor has not been opened";
        }
    }

    private static final String BASIC_SERVICE_JAVA = "BasicService.java";

    private static final String BASIC_SERVICE_JAVA2 = "BasicService2.java";

    private static final String PATH = "data/unit/editor/vsm/completion/";

    private static final String VSM_PROJECT_NAME = "org.eclipse.sirius.test.design";

    private static final String VSM = "test2.odesign";

    /**
     * 
     */
    private static final String TESTED_ODESIGN_NAME = "new" + VSM;

    private SWTBotView propertiesBot;

    private SWTBotTreeItem diagramRepresentationDescription;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        final IWorkspaceDescription description = ResourcesPlugin.getWorkspace().getDescription();
        description.setAutoBuilding(false);
        ResourcesPlugin.getWorkspace().setDescription(description);
        // Wait the end of the current build and/or refresh.
        waitJobsBuildOrRefresh();
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        setErrorCatchActive(false);

        // Load the target platform, if not already done, to allow compilation
        // of the java services
        TestsUtil.setTargetPlatform(Activator.PLUGIN_ID);
    }

    /**
     * Initialize the context by copying new resources and waiting all build process.
     * 
     * @exception InterruptedException
     *                if this thread is interrupted while waiting
     * @exception OperationCanceledException
     *                if the progress monitor is canceled while waiting
     * @exception CoreException
     *                In case of problem during setting workspace description to disable auto build.
     * @throws CommandException
     */
    private void initContext(List<String> nodes, boolean selectPropertyField) throws InterruptedException, OperationCanceledException, CoreException, CommandException {
        designerPerspectives.openSiriusPerspective();

        // Copy the sample ecore model for type completion
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, "test.ecore");

        // Create VSM Project.
        IProject viewpointSpecificationProject = ViewpointSpecificationProjectCreationTest.createViewpointSpecificationProject(bot, VSM_PROJECT_NAME, VSM);
        // Wait the end of the current build and/or refresh.
        viewpointSpecificationProject.refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());
        waitJobsBuildOrRefresh();

        // Define the imports in the VSM and add Ecore dependency
        String MANIFEST_MF = "MANIFEST.MF";
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + MANIFEST_MF, VSM_PROJECT_NAME + "/META-INF/" + MANIFEST_MF);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + VSM, VSM_PROJECT_NAME + "/description/" + TESTED_ODESIGN_NAME);

        // We open the new design used by the test. We avoid replacing the
        // already existing one because sometime the refresh is not correctly
        // done and tests can fail.
        SWTBotView modelExplorerView = bot.viewById(ModelExplorerView.ID);
        modelExplorerView.setFocus();
        SWTBotTreeItem projectNode = modelExplorerView.bot().tree().expandNode("org.eclipse.sirius.test.design");
        SWTBotTreeItem descriptionNode = projectNode.expandNode("description");

        viewpointSpecificationProject.refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());
        waitJobsBuildOrRefresh();

        SWTBotTreeItem designNode = descriptionNode.getNode(TESTED_ODESIGN_NAME);
        designNode.doubleClick();

        waitJobsBuildOrRefresh();

        // Copy the Java Service file
        char pathSeparator = '/';
        String package_name = VSM_PROJECT_NAME;
        package_name = package_name.replace('.', pathSeparator);
        String dest = VSM_PROJECT_NAME + "/src/" + package_name + pathSeparator;
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + BASIC_SERVICE_JAVA, dest + BASIC_SERVICE_JAVA);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + BASIC_SERVICE_JAVA2, dest + BASIC_SERVICE_JAVA2);

        waitJobsBuildOrRefresh();

        // Launch a manual build and wait the end of the workspace build
        ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
        waitJobsBuildOrRefresh();

        bot.activeEditor().setFocus();
        SWTBotTreeItem lastExpandNode = bot.activeEditor().bot().tree().expandNode("platform:/resource/" + VSM_PROJECT_NAME + "/description/" + TESTED_ODESIGN_NAME);
        for (String node : nodes) {
            lastExpandNode = lastExpandNode.expandNode(node);
        }
        diagramRepresentationDescription = lastExpandNode;
        lastExpandNode.select();

        if (selectPropertyField) {
            propertiesBot = bot.viewByTitle("Properties");
            propertiesBot.setFocus();
            SWTBotSiriusHelper.selectPropertyTabItem("General", propertiesBot.bot());
        }
    }

    /**
     * There is problem on linux with this test so we are waiting build or refresh jobs by joining them.
     */
    private void waitJobsBuildOrRefresh() throws InterruptedException, OperationCanceledException {
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_REFRESH, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_REFRESH, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the service interpreter.</li>
     * <li>The cursor is at the starting position</li>
     * <li>The service called is present in two different classes</li>
     * <li>The service from which the navigation is done is the first one in the wizard.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testServiceNavigationWithSameServiceInDifferentClasses() throws Exception {
        List<String> expectedItemLabels = new ArrayList<>();
        expectedItemLabels.add("BasicService - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        expectedItemLabels.add("BasicService2 - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        testNavigation("service:sampleService()", 2, expectedItemLabels, 0, 0);
    }

    /**
     * Tests that double clicking a Java service declaration will open corresponding Java editor.
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testServiceNavigationFromJavaExtensionDefinition() throws Exception {
        initContext(Arrays.asList("test", "VP"), false);
        SWTBotTreeItem javaExtensionItem = diagramRepresentationDescription.expandNode("org.eclipse.sirius.test.design.BasicService").select();
        javaExtensionItem.doubleClick();
        TestsUtil.waitUntil(new JavaEditorOpenedCondition(true));
    }

    /**
     * Tests that double clicking a Java service declaration pointing at an unknown class opens a dialog with an error
     * message.
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testServiceNavigationFromJavaExtensionDefinitionError() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        initContext(Arrays.asList("test", "VP"), false);

        SWTBotTreeItem javaExtensionItem = diagramRepresentationDescription.expandNode("org.eclipse.sirius.test.design.BasicService").select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("General", propertiesBot.bot());
        SWTBotText text = propertiesBot.bot().text(0);

        text.setFocus();
        text.setText("wrong service");
        javaExtensionItem.doubleClick();

        String message = bot.activeShell().getText();
        assertEquals("An error dialog should have opened.", "Service class opening failure", message);

    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the service interpreter.</li>
     * <li>The cursor is at the starting position</li>
     * <li>The service called is present in two different classes</li>
     * <li>The service from which the navigation is done is the second one in the wizard.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testServiceNavigationWithSameServiceInDifferentClasses2() throws Exception {
        List<String> expectedItemLabels = new ArrayList<>();
        expectedItemLabels.add("BasicService - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        expectedItemLabels.add("BasicService2 - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        testNavigation("service:sampleService()", 2, expectedItemLabels, 1, 0);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the service interpreter.</li>
     * <li>The cursor is at the 12 index position</li>
     * <li>The service called is present in one class</li>
     * <li>The service is automatically opened in JAVA editor.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testSingleServiceNavigation() throws Exception {
        testNavigation("service:sampleService(self)", 1, null, -1, 12);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the AQL interpreter.</li>
     * <li>The cursor is at the starting position</li>
     * <li>No service should be detected at the cursor position. So nothing should be done.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testServiceNavigationWithAqlInterpreterFirstPosition() throws Exception {
        testNavigation("aql:self.sampleService() and self.sampleService(self)->", 0, null, -1, 0);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the AQL interpreter.</li>
     * <li>The cursor is at the last position</li>
     * <li>No service should be detected at the cursor position. So nothing should be done.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testServiceNavigationWithAqlInterpreterLastPosition() throws Exception {
        testNavigation("aql:self.sampleService() and self.sampleService(self)->", 0, null, -1, 55);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the AQL interpreter.</li>
     * <li>The cursor is at the 27 index position</li>
     * <li>No service should be detected at the cursor position. So nothing should be done.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testServiceNavigationWithAqlInterpreterMiddlePosition() throws Exception {
        testNavigation("aql:self.sampleService() and self.sampleService(self)->", 0, null, -1, 27);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the AQL interpreter.</li>
     * <li>The cursor is at the 13 index position</li>
     * <li>The service call at cursor position is present in two different classes</li>
     * <li>The service from which the navigation is done is the second one in the wizard.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testDualServiceNavigationWithAqlInterpreter() throws Exception {
        List<String> expectedItemLabels = new ArrayList<>();
        expectedItemLabels.add("BasicService - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        expectedItemLabels.add("BasicService2 - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        testNavigation("aql:self.sampleService() and self.sampleService(self)->", 2, expectedItemLabels, 1, 13);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the AQL interpreter.</li>
     * <li>The cursor is at the 9 index position</li>
     * <li>The service call at cursor position is present in two different classes</li>
     * <li>The service from which the navigation is done is the second one in the wizard.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testDualServiceNavigationWithAqlInterpreter2() throws Exception {
        List<String> expectedItemLabels = new ArrayList<>();
        expectedItemLabels.add("BasicService - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        expectedItemLabels.add("BasicService2 - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        testNavigation("aql:self.sampleService() and self.sampleService(self)->", 2, expectedItemLabels, 1, 9);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the AQL interpreter.</li>
     * <li>The cursor is at the 23 index position</li>
     * <li>The service call at cursor position is present in two different classes</li>
     * <li>The service from which the navigation is done is the second one in the wizard.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testDualServiceNavigationWithAqlInterpreter3() throws Exception {
        List<String> expectedItemLabels = new ArrayList<>();
        expectedItemLabels.add("BasicService - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        expectedItemLabels.add("BasicService2 - org.eclipse.sirius.test.design - org.eclipse.sirius.test.design/src");
        testNavigation("aql:self.sampleService() and self.sampleService(self)->", 2, expectedItemLabels, 1, 23);
    }

    /**
     * Check that Java service navigation from F3 key and a VSM expression works in the following context:
     * <ul>
     * <li>The expression calls the AQL interpreter.</li>
     * <li>The cursor is at the 38 index position</li>
     * <li>The service call at cursor position is present in one class</li>
     * <li>The service is automatically opened in JAVA editor.</li>
     * </ul>
     * 
     * @exception Exception
     *                if a problem occurs.
     */
    public void testSingleServiceNavigationWithAqlInterpreter() throws Exception {
        testNavigation("aql:self.sampleService() and self.sampleService(self)->", 1, null, -1, 38);
    }

    /**
     * Tests that Java service navigation from VSM expression is the expected one.
     * 
     * @param vsmExpression
     *            the VSM expression used to test Java service navigation.
     * @param matchingJavaServiceNumber
     *            the number of Java service that should be proposed for navigation.
     * @param expectedItemLabels
     *            The Java service item's labels in their wizard's order.
     * @param javaServiceIndex
     *            the index of the Java service item to open with Java editor when in Java service navigation wizard.
     * @param cursorPosition
     *            the cursor position in the VSM expression before triggering navigation with F3 key.
     * 
     * @throws InterruptedException
     *             if a problem occurs.
     * @throws CoreException
     *             if a problem occurs.
     * @throws CommandException
     *             if a problem occurs.
     */
    protected void testNavigation(String vsmExpression, int matchingJavaServiceNumber, List<String> expectedItemLabels, int javaServiceIndex, int cursorPosition)
            throws InterruptedException, CoreException, CommandException {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        initContext(Arrays.asList("test", "VP", "Diag"), true);

        // Init the Precondition Expression
        SWTBotText precondition = propertiesBot.bot().text(3);
        precondition.setFocus();
        precondition.setText(vsmExpression);
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return vsmExpression.equals(precondition.getText());
            }

            @Override
            public String getFailureMessage() {
                return null;
            }

        });
        Display.getDefault().asyncExec(() -> {
            // bot.pressShortcut does not work
            precondition.widget.setSelection(cursorPosition);
            Event event = new Event();
            event.keyCode = SWT.F3;
            precondition.widget.notifyListeners(SWT.KeyDown, event);
        });
        if (matchingJavaServiceNumber > 0) {
            if (matchingJavaServiceNumber > 1) {
                TestsUtil.waitUntil(new ICondition() {

                    @Override
                    public boolean test() throws Exception {

                        SWTBotShell shell = null;
                        try {
                            shell = bot.shell("Service navigation");
                        } catch (Exception e) {
                            // do nothing
                        }
                        return shell != null;
                    }

                    @Override
                    public String getFailureMessage() {
                        return "Service navigation wizard did not open.";
                    }
                }, 10000);
                SWTBotShell shell = bot.shell("Service navigation");
                SWTBot wizardBot = shell.bot();
                SWTBotTable table = wizardBot.table(0);
                TestsUtil.waitUntil(new ICondition() {

                    @Override
                    public boolean test() throws Exception {
                        return table.rowCount() > 0;
                    }

                    @Override
                    public String getFailureMessage() {
                        return "The type navigation wizard was not filled with target Java services.";
                    }
                }, 15000);

                assertEquals("Wrong number of matching Java service implementations.", matchingJavaServiceNumber, table.rowCount());

                if (expectedItemLabels != null) {
                    for (int i = 0; i < expectedItemLabels.size(); i++) {
                        SWTBotTableItem tableItem = table.getTableItem(i);
                        assertEquals("Unknown service: " + tableItem.getText(), expectedItemLabels.get(i), tableItem.getText());

                    }
                }
                if (javaServiceIndex >= 0) {
                    table.select(javaServiceIndex);
                    wizardBot.button(TestsUtil.isPhotonPlatformOrLater() ? "Open" : "OK").click();
                }
            }
            TestsUtil.waitUntil(new JavaEditorOpenedCondition(true));

        }

        if (matchingJavaServiceNumber == 0) {
            TestsUtil.waitUntil(new JavaEditorOpenedCondition(false));
        }

    }
}
