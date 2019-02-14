/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ExportAsImageHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Validate the export of diagram as image.
 * 
 * @author smonnier
 */
public class ExportDiagramsAsImagesAndHtmlTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME1 = "diagram1";

    private static final String REPRESENTATION_INSTANCE_NAME2 = "diagram2";

    private static final String REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION = "aaaa package entities";

    private static final String EXPORT_DIAGRAM_AS_IMAGE = "Export representation as image file";

    private static final String EXPORT_DIAGRAMS_AS_IMAGE = "Export representations as image file";

    private static final String MODEL = "tc2260.ecore";

    private static final String MODEL_ROOT_ELEMENT_NAME = "root";

    private static final String MODEL_FOR_TRANSIENT_SESSION = "vp867.ecore";

    private static final String MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME = "aaaa";

    private static final String MODEL_AIRD_FOR_TRANSIENT_SESSION = "vp867.aird";

    private static final String SESSION_FILE = "tc2260.aird";

    private static final String DATA_UNIT_DIR = "data/unit/export/ticket2260/";

    private static final String DATA_UNIT_DIR_FOR_TRANSIENT_SESSION = "data/unit/export/vp867/";

    private static final String FILE_DIR = "/";

    private UILocalSession localSession;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        new ExportAsImageHelper().resetDialogSettings();
    }

    /**
     * Validate the functionality "Export representations as images" on an aird
     * file for JPG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsJPGFromProjectExplorerView() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
        exportAsImageFromProjectExplorerView("JPG");
        valideExportResult("jpg", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the functionality "Export representations as images" on a
     * representations file for JPG format in a path that containing dot (for
     * example in a folder named "a.b"). VP-2928
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsJPGFromProjectExplorerViewWithPathContainingDot() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
        // Export the representations in a folder named "a.b" (folder will be
        // created in the project)
        exportAsImageFromProjectExplorerView("JPG", "a.b", null);
        valideExportResultWithFolder("jpg", "a.b", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the fails of functionality "Export representations as images" on
     * a representations file for JPG format with an inexisting folder
     * destination. VP-2929 Check that the "folder check rules" are called at
     * the opening of the dialog.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsJPGFromProjectExplorerViewWithAnInvalidPathAtCreationOfPopup() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
        // Export in a new folder
        exportAsImageFromProjectExplorerView("JPG", "newFolder", null);
        SWTBotUtils.waitAllUiEvents();
        // Delete the previous memorized folder ("newFolder")
        IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getProject(designerProject.getName()).getFolder("newFolder");
        try {
            folder.delete(true, new NullProgressMonitor());
        } catch (CoreException e) {
            fail("The folder \"" + folder.getLocation().toOSString() + "\" could not be deleted : " + e.getMessage());
        }
        // Refresh the project to be sure that the folder is no longer available in the Project Explorer
        folder.getParent().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
        // Launch an export that must fails because the previous saved folder
        // (used by default in the export dialog) does not exist.
        exportAsImageFromProjectExplorerView("JPG", null, "Folder does not exist");
    }

    /**
     * Validate the functionality "Export representations as images" on an aird
     * file for PNG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsPNGFromProjectExplorerView() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        exportAsImageFromProjectExplorerView("PNG");
        valideExportResult("png", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the functionality "Export representations as images" on an aird
     * file for SVG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsSVGFromProjectExplorerView() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        exportAsImageFromProjectExplorerView("SVG");
        valideExportResult("svg", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the functionality "Export representations as images" on the root
     * of a semantic model for JPG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsJPGFromModelContentView() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        exportAsImageFromModelContentView("JPG", MODEL_ROOT_ELEMENT_NAME);
        valideExportResult("jpg", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the functionality "Export representations as images" on the root
     * of a semantic model for PNG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsPNGFromModelContentView() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        exportAsImageFromModelContentView("PNG", MODEL_ROOT_ELEMENT_NAME);
        valideExportResult("png", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the functionality "Export representations as images" on the root
     * of a semantic model for SVG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsSVGFromModelContentView() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        exportAsImageFromModelContentView("SVG", MODEL_ROOT_ELEMENT_NAME);
        valideExportResult("svg", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the functionality "Export diagram as image" from tab bar for JPG
     * export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsJPGFromTabBar() throws Exception {
        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        exportAsImageFromEditorTabBar("JPG");
        valideExportResult("jpg", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    // There is a problem with the text update in the combo file path.
    /**
     * Validate the functionality "Export diagram as image" from tab bar for
     * file without extension, with extension not authorized, with extension
     * change from image format with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void _testExportDiagramsOfTransientSessionFromTabBar() throws Exception {
        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        exportAsImageFromEditorTabBar();
        valideExportResult();
    }

    /**
     * Validate the functionality "Export diagram as image" from tab bar for PNG
     * export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsPNGFromTabBar() throws Exception {
        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        exportAsImageFromEditorTabBar("PNG");
        valideExportResult("png", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export diagram as image" from tab bar for SVG
     * export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsSVGFromTabBar() throws Exception {
        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        exportAsImageFromEditorTabBar("SVG");
        valideExportResult("svg", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export representations as images" on the root
     * of a semantic model for JPG export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsJPGFromModelContentView() throws Exception {

        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource ecoreResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        ecoreResource.getProject().mouseRigthClickOnResource(ecoreResource, "Sirius Ecore Editor");

        SWTBotUtils.waitAllUiEvents();
        localSession = designerPerspective.getOpenedSession(new UIResource(designerProject, FILE_DIR, MODEL_AIRD_FOR_TRANSIENT_SESSION));
        SWTBotUtils.waitAllUiEvents();

        exportAsImageFromModelContentView("JPG", MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME);
        valideExportResult("jpg", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export representations as images" on the root
     * of a semantic model for PNG export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsPNGFromModelContentView() throws Exception {

        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        SWTBotUtils.waitAllUiEvents();
        localSession = designerPerspective.getOpenedSession(new UIResource(designerProject, FILE_DIR, MODEL_AIRD_FOR_TRANSIENT_SESSION));
        SWTBotUtils.waitAllUiEvents();

        exportAsImageFromModelContentView("PNG", MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME);
        valideExportResult("png", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export representations as images" on the root
     * of a semantic model for SVG export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsSVGFromModelContentView() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.widgets.TimeoutException: Timeout
             * after: 10000 ms.: tree item with text DesignerTestProject is not
             * expanded at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(
             * SWTBotFactory.java:407) at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(
             * SWTBotFactory.java:381) at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(
             * SWTBotFactory.java:369) at
             * org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.
             * getProjectTreeItem(UIProject.java:108) at
             * org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.
             * getUIItemFromResource(UIProject.java:137) at
             * org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.
             * mouseRigthClickOnResource(UIProject.java:171) at
             * org.eclipse.sirius.tests.swtbot.ExportDiagramsAsImagesAndHtmlTest
             * .testExportDiagramsOfTransientSessionAsSVGFromModelContentView(
             * ExportDiagramsAsImagesAndHtmlTest.java:371)
             */
            return;
        }

        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        localSession = designerPerspective.getOpenedSession(new UIResource(designerProject, FILE_DIR, MODEL_AIRD_FOR_TRANSIENT_SESSION));

        exportAsImageFromModelContentView("SVG", MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME);
        valideExportResult("svg", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    private void exportAsImageFromProjectExplorerView(String imageExtension) throws Exception {
        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotUtils.waitAllUiEvents();
        SWTBotTreeItem expandedProjectTreeItem = tree.expandNode(designerProject.getName());
        SWTBotTreeItem airdFileTreeItem = expandedProjectTreeItem.getNode(0);
        String airdFilename = SESSION_FILE;
        SWTBotUtils.waitAllUiEvents();
        expandedProjectTreeItem.select(airdFilename);
        SWTBotUtils.clickContextMenu(airdFileTreeItem, Messages.ExportRepresentationsAction_label);

        bot.waitUntil(Conditions.shellIsActive(EXPORT_DIAGRAMS_AS_IMAGE));

        assertThat("The dialog is not correct for the export", bot.activeShell().getText(), equalTo(EXPORT_DIAGRAMS_AS_IMAGE));

        assertNotNull(bot.comboBoxWithLabel("To directory"));

        bot.comboBox(1).setSelection(imageExtension);
        bot.checkBox("Export to HTML").select();
        bot.button("OK").click();
    }

    /**
     * Launch the export with specific parameters.
     * 
     * @param imageExtension
     *            The extension to choose in the export dialog
     * @param newDestinationFolderName
     *            The name of the folder to add to the default folder used for
     *            destination (this folder will be created)
     * @param errorMessageToCheck
     *            null if no error message to check, a message if the export
     *            dialog must display an error message. If the message is not
     *            null, a test is done to check if it is displayed and the
     *            Cancel button is then check.
     * @throws Exception
     */
    private void exportAsImageFromProjectExplorerView(String imageExtension, String newDestinationFolderName, String errorMessageToCheck) throws Exception {
        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotUtils.waitAllUiEvents();
        SWTBotTreeItem expandedProjectTreeItem = tree.expandNode(designerProject.getName());
        SWTBotTreeItem airdFileTreeItem = expandedProjectTreeItem.getNode(0);
        String airdFilename = SESSION_FILE;
        SWTBotUtils.waitAllUiEvents();
        expandedProjectTreeItem.select(airdFilename);
        SWTBotUtils.clickContextMenu(airdFileTreeItem, Messages.ExportRepresentationsAction_label);

        bot.waitUntil(Conditions.shellIsActive(EXPORT_DIAGRAMS_AS_IMAGE));

        assertThat("The dialog is not correct for the export", bot.activeShell().getText(), equalTo(EXPORT_DIAGRAMS_AS_IMAGE));

        assertNotNull(bot.comboBoxWithLabel("To directory"));

        if (newDestinationFolderName != null) {
            File newFolder = new File(bot.comboBox(0).getText() + File.separator + newDestinationFolderName);
            newFolder.mkdir();
            bot.comboBox(0).setText(newFolder.getAbsolutePath());
        }
        bot.comboBox(1).setSelection(imageExtension);
        bot.checkBox("Export to HTML").select();
        if (errorMessageToCheck != null) {
            try {
                bot.label(errorMessageToCheck);
            } catch (WidgetNotFoundException e) {
                fail("The error message \"" + errorMessageToCheck + "\" is expected in the export dialog but is not displayed");
            }
            bot.button("Cancel").click();
        } else {
            bot.button("OK").click();
        }
    }

    private void exportAsImageFromModelContentView(String imageExtension, String rootElementName) throws Exception {
        if (localSession == null)
            fail();
        SWTBotTreeItem semanticRoot = localSession.getSemanticResourceNode(new UIResource(designerProject, FILE_DIR, MODEL)).select(rootElementName);
        SWTBotUtils.clickContextMenu(semanticRoot, Messages.ExportRepresentationsAction_label);

        int nbRepsToExport = DialectManager.INSTANCE.getAllRepresentations(localSession.getOpenedSession()).size();

        if (nbRepsToExport == 1) {
            bot.waitUntil(Conditions.shellIsActive(EXPORT_DIAGRAM_AS_IMAGE));
            assertThat("The dialog is not correct for the export", bot.activeShell().getText(), equalTo(EXPORT_DIAGRAM_AS_IMAGE));
            assertNotNull(bot.comboBoxWithLabel("To file"));
        } else {
            bot.waitUntil(Conditions.shellIsActive(EXPORT_DIAGRAMS_AS_IMAGE));
            assertThat("The dialog is not correct for the export", bot.activeShell().getText(), equalTo(EXPORT_DIAGRAMS_AS_IMAGE));
            assertNotNull(bot.comboBoxWithLabel("To directory"));
        }

        bot.comboBox(1).setSelection(imageExtension);
        bot.checkBox("Export to HTML").select();
        bot.button("OK").click();
    }

    private void exportAsImageFromEditorTabBar(String imageExtension) throws Exception {
        // Wait all UI events to ensure that the tabbar is correctly
        // refreshed.
        SWTBotUtils.waitAllUiEvents();

        bot.activeEditor().bot().toolbarButtonWithTooltip("Export diagram as image").click();

        bot.waitUntil(Conditions.shellIsActive(EXPORT_DIAGRAM_AS_IMAGE));

        assertThat("The dialog is not correct for the export", bot.activeShell().getText(), equalTo(EXPORT_DIAGRAM_AS_IMAGE));

        assertNotNull(bot.comboBoxWithLabel("To file"));

        bot.comboBox(1).setSelection(imageExtension);
        bot.checkBox("Export to HTML").select();
        bot.button("OK").click();
    }

    private void exportAsImageFromEditorTabBar() throws Exception {
        // Wait all UI events to ensure that the tabbar is correctly
        // refreshed.
        SWTBotUtils.waitAllUiEvents();

        bot.activeEditor().bot().toolbarButtonWithTooltip("Export diagram as image").click();

        bot.waitUntil(Conditions.shellIsActive(EXPORT_DIAGRAM_AS_IMAGE));

        assertThat("The dialog is not correct for the export", bot.activeShell().getText(), equalTo(EXPORT_DIAGRAM_AS_IMAGE));

        assertNotNull(bot.comboBoxWithLabel("To file"));

        StringBuffer filePath = new StringBuffer(bot.comboBoxWithLabel("To file").getText());

        filePath.append("/export.png");

        bot.comboBoxWithLabel("To file").getText();

        bot.comboBoxWithLabel("To file").setText(filePath.toString());

        bot.button(0).setFocus();

        assertThat("The image format is not correct", bot.comboBox(1).getText(), equalTo("PNG"));

        IPath path = new Path(filePath.toString());

        path = path.removeFileExtension();

        path = path.addFileExtension("txt");

        bot.comboBoxWithLabel("To file").setText(path.toString());

        bot.button(0).setFocus();

        bot.comboBox(1).setSelection("JPG");

        // This part not working
        // if (!path.toString().contains(".txt.jpg")) {
        // fail("the update file path does not correct");
        // }

        bot.checkBox("Export to HTML").select();

        bot.button("OK").click();
    }

    private void valideExportResult(final String imageExtension, final String... expectedFileNames) throws Exception {
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        SWTBotUtils.waitAllUiEvents();
        valideExportResultWithFolder(imageExtension, null, expectedFileNames);
    }

    private void valideExportResultWithFolder(final String imageExtension, String newFolderName, final String... expectedFileNames) throws Exception {
        File destinationFolder;
        if (newFolderName != null) {
            destinationFolder = new File(ResourcesPlugin.getWorkspace().getRoot().getProject(designerProject.getName()).getLocation().toOSString() + File.separator + newFolderName);
        } else {
            destinationFolder = new File(ResourcesPlugin.getWorkspace().getRoot().getProject(designerProject.getName()).getLocation().toOSString());
        }
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                File filesWithExpectedExtension[] = destinationFolder.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        boolean result = false;
                        for (String filename : expectedFileNames) {
                            result = result || name.equals(filename + "_0_0." + imageExtension);
                        }
                        return result;
                    }
                });
                return expectedFileNames.length == filesWithExpectedExtension.length;
            }

            @Override
            public String getFailureMessage() {
                return "Wrong number of file created with the \"" + imageExtension + "\" extension.";
            }
        });

        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                File filesWithHTMLExtension[] = destinationFolder.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        boolean result = false;
                        for (String filename : expectedFileNames) {
                            result = result || name.equals(filename + ".html");
                        }
                        return result;
                    }
                });
                return expectedFileNames.length == filesWithHTMLExtension.length;
            }

            @Override
            public String getFailureMessage() {
                return "Wrong number of file created with the \"html\" extension.";
            }
        });
    }

    private void valideExportResult() throws Exception {
        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();

        List<String> nodes = tree.expandNode(designerProject.getName()).getNodes();
        assertTrue(nodes.contains("export_0_0.txt.jpg"));
        assertTrue(nodes.contains("export.txt.html"));

    }
}
