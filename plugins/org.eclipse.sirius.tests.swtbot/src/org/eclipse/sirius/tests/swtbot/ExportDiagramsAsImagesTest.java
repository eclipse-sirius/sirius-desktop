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

import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckTreeItemEnabled;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ExportAsImageHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Validate the export of diagram as image.
 * 
 * @author smonnier
 */
public class ExportDiagramsAsImagesTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME1 = "diagram1";

    private static final String REPRESENTATION_INSTANCE_NAME2 = "diagram2";

    private static final String REPRESENTATION_INSTANCE_NAME3 = "diag\\ram/num3";

    private static final String REPRESENTATION_INSTANCE_NAME3_RENAMED = "diag_ram_num3";

    private static final String REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION = "aaaa package entities";

    private static final String REPRESENTATION_INSTANCE_BIG = "root package entities";

    private static final String MODEL = "tc2260.ecore";

    private static final String MODEL_ROOT_ELEMENT_NAME = "root";

    private static final String MODEL_FOR_TRANSIENT_SESSION = "vp867.ecore";

    private static final String BIG_MODEL = "My.ecore";

    private static final String MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME = "aaaa";

    private static final String MODEL_AIRD_FOR_TRANSIENT_SESSION = "vp867.aird";

    private static final String SESSION_FILE = "tc2260.aird";

    private static final String BIG_SESSION = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/export/ticket2260/";

    private static final String DATA_UNIT_DIR_FOR_BIG_MODEL = "data/unit/export/bigModel/";

    private static final String DATA_UNIT_DIR_FOR_TRANSIENT_SESSION = "data/unit/export/vp867/";

    private static final String FILE_DIR = "/";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

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
        localSession = null;
        super.tearDown();
        new ExportAsImageHelper().resetDialogSettings();
    }

    /**
     * Validate the functionality "Export representations as images" on an aird file for JPG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportAsJPGFromProjectExplorerView() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        exportAsImageFromProjectExplorerView("JPG");
        valideExportResult("jpg", REPRESENTATION_INSTANCE_NAME1, REPRESENTATION_INSTANCE_NAME2);
    }

    /**
     * Validate the functionality "Export representations as images" on a big aird file for JPG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportBigRepresesntationAsJPGFromProjectExplorerView() throws Exception {
        try {
            setErrorCatchActive(false);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_MODEL, getProjectName() + "/" + BIG_MODEL);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_SESSION, getProjectName() + "/" + BIG_SESSION);
            final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, BIG_SESSION);
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
            exportBigAsImageFromProjectExplorerView("JPG");
            valideExportResult("jpg", REPRESENTATION_INSTANCE_BIG);
        } finally {
            setErrorCatchActive(true);
        }
    }

    /**
     * Validate the functionality "Export representations as images" on a big aird file for PNG export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportBigRepresesntationAsPNGFromProjectExplorerView() throws Exception {
        try {
            setErrorCatchActive(false);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_MODEL, getProjectName() + "/" + BIG_MODEL);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_SESSION, getProjectName() + "/" + BIG_SESSION);
            final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, BIG_SESSION);
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
            exportBigAsImageFromProjectExplorerView("PNG");
            valideExportResult("png", REPRESENTATION_INSTANCE_BIG);
        } finally {
            setErrorCatchActive(true);
        }
    }

    /**
     * Validate the functionality "Export representations as images" on a big aird file for SVG export. Since VP-3958,
     * we export correctly big SVG image.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportBigRepresesntationAsSVGFromProjectExplorerView() throws Exception {
        try {
            setErrorCatchActive(true);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_MODEL, getProjectName() + "/" + BIG_MODEL);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_SESSION, getProjectName() + "/" + BIG_SESSION);
            final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, BIG_SESSION);
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
            exportBigAsImageFromProjectExplorerView("SVG");
            valideExportResult("svg", REPRESENTATION_INSTANCE_BIG);
        } finally {
            setErrorCatchActive(true);
        }
    }

    /**
     * Validate the functionality "Export diagram as images" from tab bar for JPG export, with a big representation
     * (size > 50 000 000 pixels).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportBigRepresesntationAsJPGFromTabbar() throws Exception {
        try {
            setErrorCatchActive(false);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_MODEL, getProjectName() + "/" + BIG_MODEL);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_SESSION, getProjectName() + "/" + BIG_SESSION);
            final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, BIG_SESSION);
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
            // SWTBotTreeItem semanticResourceNode =
            // localSession.getSemanticResourceNode(ecoreEcoreResource);
            // SWTBotTreeItem ecoreTreeItem =
            // semanticResourceNode.getNode("ecore");
            // localSession.getLocalSessionBrowser().perCategory().selectSirius(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME)
            // .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME,
            // UIDiagramRepresentation.class).open();
            openDiagramEditor();
            exportAsImageFromEditorTabBar("JPG");
            valideExportResult("jpg", REPRESENTATION_INSTANCE_BIG);
        } finally {
            setErrorCatchActive(true);
        }
    }

    /**
     * Validate the functionality "Export diagram as images" from tab bar for PNG export, with a big representation
     * (size > 50 000 000 pixels).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportBigRepresesntationAsPNGFromTabbar() throws Exception {
        try {
            setErrorCatchActive(false);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_MODEL, getProjectName() + "/" + BIG_MODEL);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_SESSION, getProjectName() + "/" + BIG_SESSION);
            final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, BIG_SESSION);
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
            openDiagramEditor();
            exportAsImageFromEditorTabBar("PNG");
            valideExportResult("png", REPRESENTATION_INSTANCE_BIG);
        } finally {
            setErrorCatchActive(true);
        }
    }

    /**
     * Validate the functionality "Export diagram as images" from tab bar for SVG export, with a big representation
     * (size > 50 000 000 pixels). Since VP-3958, we export correctly big SVG image.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportBigRepresesntationAsSVGFromTabbar() throws Exception {
        try {
            setErrorCatchActive(true);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_MODEL, getProjectName() + "/" + BIG_MODEL);
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_BIG_MODEL + BIG_SESSION, getProjectName() + "/" + BIG_SESSION);
            final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, BIG_SESSION);
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
            openDiagramEditor();
            exportAsImageFromEditorTabBar("SVG");
            valideExportResult("svg", REPRESENTATION_INSTANCE_BIG);
        } finally {
            setErrorCatchActive(true);
        }
    }

    /**
     * Validate the functionality "Export representations as images" on an aird file for PNG export.
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
     * Validate the functionality "Export representations as images" on an aird file for SVG export.
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
     * Validate the functionality "Export representations as images" on the root of a semantic model for JPG export.
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
     * Validate the functionality "Export representations as images" on the root of a semantic model for PNG export.
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
     * Validate the functionality "Export representations as images" on the root of a semantic model for SVG export.
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
     * Validate the functionality "Export diagram as image" from tab bar for JPG export, with a transient session.
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

    /**
     * Validate the functionality "Export diagram as image" from tab bar for PNG export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsPNGFromTabBar() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find node with text:
             * vp867.ecore at org.eclipse.swtbot.swt .finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem.java:334) at
             * org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode( SWTBotTreeItem.java:308) at
             * org.eclipse.swtbot.swt.finder.widgets. SWTBotTreeItem.getNode(SWTBotTreeItem.java:346) at
             * org.eclipse.sirius .tests.swtbot.support.api.business.UIProject. getUIItemFromResource
             * (UIProject.java:152) at org.eclipse.sirius.tests.swtbot.support.api
             * .business.UIProject.mouseRigthClickOnResource(UIProject.java:171) at
             * org.eclipse.sirius.tests.swtbot.ExportDiagramsAsImagesTest.
             * testExportDiagramsOfTransientSessionAsPNGFromTabBar (ExportDiagramsAsImagesTest.java:372)
             */
            return;
        }

        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        exportAsImageFromEditorTabBar("PNG");
        valideExportResult("png", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export diagram as image" from tab bar for SVG export, with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsSVGFromTabBar() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find node with text:
             * vp867.ecore at org.eclipse.swtbot.swt .finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem.java:334) at
             * org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode( SWTBotTreeItem.java:308) at
             * org.eclipse.swtbot.swt.finder.widgets. SWTBotTreeItem.getNode(SWTBotTreeItem.java:346) at
             * org.eclipse.sirius .tests.swtbot.support.api.business.UIProject. getUIItemFromResource
             * (UIProject.java:152) at org.eclipse.sirius.tests.swtbot.support.api
             * .business.UIProject.mouseRigthClickOnResource(UIProject.java:171) at
             * org.eclipse.sirius.tests.swtbot.ExportDiagramsAsImagesAndHtmlTest
             * .testExportDiagramsOfTransientSessionAsSVGFromTabBar( ExportDiagramsAsImagesAndHtmlTest.java:306)
             */
            return;
        }

        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        exportAsImageFromEditorTabBar("SVG");
        valideExportResult("svg", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export representations as images" on the root of a semantic model for JPG export,
     * with a transient session.
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

        exportAsImageTranscientSessionFromModelContentView("JPG", MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME);
        valideExportResult("jpg", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export representations as images" on the root of a semantic model for PNG export,
     * with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsPNGFromModelContentView() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.widgets.TimeoutException: Timeout after: 10000 ms.: tree item with text
             * DesignerTestProject is not expanded at org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(
             * SWTBotFactory.java:407) at org.eclipse.swtbot.swt.finder.SWTBotFactory .waitUntil(SWTBotFactory.java:381)
             * at org.eclipse.swtbot.swt.finder .SWTBotFactory.waitUntil(SWTBotFactory.java:369) at org.eclipse.sirius
             * .tests.swtbot.support.api.business.UIProject.getProjectTreeItem (UIProject.java:108) at
             * org.eclipse.sirius.tests.swtbot.support.api .business.UIProject.getUIItemFromResource(UIProject.java:137)
             * at org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.
             * mouseRigthClickOnResource(UIProject.java:171) at
             * org.eclipse.sirius.tests.swtbot.ExportDiagramsAsImagesTest.
             * testExportDiagramsOfTransientSessionAsPNGFromModelContentView (ExportDiagramsAsImagesTest.java:463)
             */
            return;
        }

        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        localSession = designerPerspective.getOpenedSession(new UIResource(designerProject, FILE_DIR, MODEL_AIRD_FOR_TRANSIENT_SESSION));

        exportAsImageTranscientSessionFromModelContentView("PNG", MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME);
        valideExportResult("png", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate the functionality "Export representations as images" on the root of a semantic model for SVG export,
     * with a transient session.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramsOfTransientSessionAsSVGFromModelContentView() throws Exception {

        /* Open a transient session on an ecore file */
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR_FOR_TRANSIENT_SESSION + MODEL_FOR_TRANSIENT_SESSION, getProjectName() + "/" + MODEL_FOR_TRANSIENT_SESSION);

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, MODEL_FOR_TRANSIENT_SESSION);
        sessionAirdResource.getProject().mouseRigthClickOnResource(sessionAirdResource, "Sirius Ecore Editor");

        localSession = designerPerspective.getOpenedSession(new UIResource(designerProject, FILE_DIR, MODEL_AIRD_FOR_TRANSIENT_SESSION));

        exportAsImageTranscientSessionFromModelContentView("SVG", MODEL_FOR_TRANSIENT_SESSION_ROOT_ELEMENT_NAME);
        valideExportResult("svg", REPRESENTATION_INSTANCE_NAME_FOR_TRANSIENT_SESSION);
    }

    /**
     * Validate that file separators in representation name are correctly replaced during the diagram export.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExportDiagramWithFileSeparator() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        bot.waitUntil(new CheckTreeItemEnabled(localSession.getLocalSessionBrowser().getTreeItem()));
        UIDiagramRepresentation uiDiagramRepresentation = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME3, UIDiagramRepresentation.class);
        uiDiagramRepresentation.open();
        SWTBotUtils.clickContextMenu(uiDiagramRepresentation.getTreeItem(), Messages.ExportRepresentationsAction_label);

        SWTBot exportBot = SWTBotSiriusHelper.getShellBot(Messages.ExportOneRepresentationAsImageDialog_dialogTitle);
        exportBot.comboBox(1).setSelection("SVG");

        exportBot.button("OK").click();
        // exportAsImageFromEditorTabBar("SVG");
        valideExportResult("svg", REPRESENTATION_INSTANCE_NAME3_RENAMED);
    }

    private void exportAsImageFromProjectExplorerView(String imageExtension) throws Exception {
        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotTreeItem airdFile = tree.expandNode(designerProject.getName()).select(SESSION_FILE);
        SWTBotUtils.clickContextMenu(airdFile, Messages.ExportRepresentationsAction_label);

        SWTBot exportBot = SWTBotSiriusHelper.getShellBot(Messages.ExportSeveralRepresentationsAsImagesDialog_dialogTitle);
        exportBot.comboBox(1).setSelection(imageExtension);
        exportBot.button("OK").click();
        SWTBotUtils.waitAllUiEvents();
        SWTBotUtils.waitAllUiEvents();
    }

    private void exportBigAsImageFromProjectExplorerView(String imageExtension) throws Exception {
        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotTreeItem airdFile = tree.expandNode(designerProject.getName()).select(BIG_SESSION);
        SWTBotUtils.clickContextMenu(airdFile, Messages.ExportRepresentationsAction_label);
        SWTBotUtils.waitAllUiEvents();
        SWTBot exportBot = SWTBotSiriusHelper.getShellBot(Messages.ExportSeveralRepresentationsAsImagesDialog_dialogTitle);
        exportBot.comboBox(1).setSelection(imageExtension);
        exportBot.button("OK").click();
        SWTBotUtils.waitAllUiEvents();
        // The session should still be opened after having performed an export as image.
        Session opennedSession = localSession.getOpenedSession();
        assertNotNull("The session should be opened", opennedSession);
        assertTrue("The session should be opened", opennedSession.isOpen());
    }

    private void exportAsImageFromModelContentView(String imageExtension, String rootElementName) throws Exception {
        if (localSession == null)
            fail();
        SWTBotTreeItem semanticRoot = localSession.getSemanticResourceNode(new UIResource(designerProject, FILE_DIR, MODEL)).select(rootElementName);
        SWTBotUtils.clickContextMenu(semanticRoot, Messages.ExportRepresentationsAction_label);

        SWTBot exportBot = SWTBotSiriusHelper.getShellBot(Messages.ExportSeveralRepresentationsAsImagesDialog_dialogTitle);
        exportBot.comboBox(1).setSelection(imageExtension);
        exportBot.button("OK").click();
    }

    private void exportAsImageTranscientSessionFromModelContentView(String imageExtension, String rootElementName) throws Exception {
        if (localSession == null)
            fail();
        SWTBotTreeItem semanticRoot = localSession.getSemanticResourceNode(new UIResource(designerProject, FILE_DIR, MODEL)).select(rootElementName);
        SWTBotUtils.clickContextMenu(semanticRoot, Messages.ExportRepresentationsAction_label);

        SWTBot exportBot = SWTBotSiriusHelper.getShellBot(Messages.ExportSeveralRepresentationsAsImagesDialog_dialogTitle);
        exportBot.comboBox(1).setSelection(imageExtension);
        exportBot.button("OK").click();
    }

    private void exportAsImageFromEditorTabBar(String imageExtension) throws Exception {

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();

        bot.activeEditor().bot().toolbarButtonWithTooltip("Export diagram as image").click();

        SWTBot exportBot = SWTBotSiriusHelper.getShellBot(Messages.ExportOneRepresentationAsImageDialog_dialogTitle);
        exportBot.comboBox(1).setSelection(imageExtension);
        exportBot.button("OK").click();
    }

    private void valideExportResult(String imageExtension, final String... expectedFileNames) throws Exception {
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        SWTBotUtils.waitAllUiEvents();
        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        List<String> nodes = tree.expandNode(designerProject.getName()).getNodes();
        for (String filename : expectedFileNames) {
            assertTrue("The project " + designerProject.getName() + " does not contain " + filename + "." + imageExtension + " in treeViewer : " + nodes,
                    nodes.contains(filename + "." + imageExtension));
        }
    }

    /**
     * Open the diagram editor
     */
    private void openDiagramEditor() {
        bot.waitUntil(new CheckTreeItemEnabled(localSession.getLocalSessionBrowser().getTreeItem()));
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

}
