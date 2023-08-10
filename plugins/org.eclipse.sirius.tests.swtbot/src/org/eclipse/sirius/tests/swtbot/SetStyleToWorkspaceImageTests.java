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
package org.eclipse.sirius.tests.swtbot;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.image.GallerySelectable;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.IWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerRectangleFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AlphaDropShadowBorder;
import org.eclipse.sirius.tests.support.api.PluginVersionCompatibility;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckNbVisibleElementsInGallery;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckNbVisibleElementsInTree;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.WidgetIsDisabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ImageSelectionGalleryHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.osgi.framework.Version;

/**
 * Tests the style customization.
 * 
 * @author mporhel
 */
@SuppressWarnings("nls")
public class SetStyleToWorkspaceImageTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "tc2225.ecore";

    private static final String DESIGN_FILE = "tc2225.odesign";

    private static final String SESSION_FILE = "tc2225.aird";

    private static final String IMG_FILE = "aircraft.jpg";

    private static final String IMG_FILE1 = "aircraft1.JPG";

    private static final String IMG_SVG_FILE = "image.svg";

    private static final String DATA_UNIT_DIR = "data/unit/style/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "2225 package entities";

    private static final String REPRESENTATION_NAME = "Entities2225";

    private static final String DIALOG_TITLE = Messages.ImageSelectionDialog_title;

    private static String C1 = "c1";

    private static String A1 = "a1";

    private static final String C1_NODE = C1 + "Node";

    private static final String C1_CONTAINER = C1 + "Container";

    private static final String C1_LIST = C1 + "List";

    private static final String A1C1_NODE = A1 + C1_NODE;

    private static final String BUNDLE_IMAGE_SUFFIX = "_BI";

    private static final String A1C1_CONTAINER = A1 + C1_CONTAINER;

    private static final String A1C1_LIST = A1 + C1_LIST;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private String oldDefaultFontName;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // Set the default fontName to have tests on
        // "Reset style properties to default values" button works.
        oldDefaultFontName = changeDefaultFontName("Times New Roman");

        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, DESIGN_FILE, IMG_FILE, IMG_FILE1, IMG_SVG_FILE);
    }

    @Override
    protected void tearDown() throws Exception {
        // Reset the default fontName
        changeDefaultFontName(oldDefaultFontName);

        super.tearDown();
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleCancelFromAppearanceSection() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find widget. at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory .waitUntilWidgetAppears(SWTBotFactory.java:357) at
             * org.eclipse.sirius .tests.swtbot.support.api.editor.SWTBotSiriusHelper
             * .widget(SWTBotSiriusHelper.java:126) at org.eclipse.sirius.tests.swtbot
             * .support.api.editor.SWTBotSiriusHelper .selectPropertyTabItem(SWTBotSiriusHelper.java:99) at org.eclipse.
             * sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase
             * .getSectionButton(AbstractSiriusSwtBotGefTestCase.java:1232) at
             * org.eclipse.sirius.tests.swtbot.support.api. AbstractSiriusSwtBotGefTestCase
             * .getSetStyleToWorkspaceImageButtonFromAppearanceTab (AbstractSiriusSwtBotGefTestCase.java:1178) at
             * org.eclipse.sirius. tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase
             * .getSetStyleToWorkspaceImageButton (AbstractSiriusSwtBotGefTestCase.java:1126) at org.eclipse.sirius.
             * tests.swtbot.SetStyleToWorkspaceImageTests. testSetWkpImageStyleCancel
             * (SetStyleToWorkspaceImageTests.java:404) at
             * org.eclipse.sirius.tests.swtbot.SetStyleToWorkspaceImageTests.
             * testSetWkpImageStyleCancelFromAppearanceSection (SetStyleToWorkspaceImageTests.java:147) Caused by:
             * org.eclipse.swtbot.swt.finder.widgets.TimeoutException: Timeout after: 10000 ms.: Could not find widget
             * matching: (of type 'TabbedPropertyList')
             */
            return;
        }
        // Appearance tab tests
        testSetWkpImageStyleCancel(C1_NODE, AbstractDiagramNodeEditPart.class, false);
        testSetWkpImageStyleCancel(C1_LIST, AbstractDiagramListEditPart.class, false);
        testSetWkpImageStyleCancel(C1_CONTAINER, AbstractDiagramContainerEditPart.class, false);
        testSetWkpImageStyleCancel(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, false);
        testSetWkpImageStyleCancel(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleCancelFromTabbar() throws Exception {
        // Tabbar tests
        testSetWkpImageStyleCancel(C1_NODE, AbstractDiagramNodeEditPart.class, true);
        testSetWkpImageStyleCancel(C1_LIST, AbstractDiagramListEditPart.class, true);
        testSetWkpImageStyleCancel(C1_CONTAINER, AbstractDiagramContainerEditPart.class, true);
        testSetWkpImageStyleCancel(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, true);
        testSetWkpImageStyleCancel(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleDisabledOnListElementFromAppearanceSection() throws Exception {
        testSetWkpImageStyleDisabled(A1C1_LIST, AbstractDiagramNameEditPart.class, false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleDisabledOnListElementFromTabbar() throws Exception {
        testSetWkpImageStyleDisabled(A1C1_LIST, AbstractDiagramNameEditPart.class, true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnNodeFromTabbar() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(C1_NODE, AbstractDiagramNodeEditPart.class, true, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnListFromTabbar() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(C1_LIST, AbstractDiagramListEditPart.class, true, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnContainerFromTabbar() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(C1_CONTAINER, AbstractDiagramContainerEditPart.class, true, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnBorderedNodeFromTabbar() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, true, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnContainedNodeFromTabbar() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, true, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromJpgToSvgOnNodeFromTabbar() throws Exception {
        testChangeWkpImageStyle(C1_NODE, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(C1_NODE, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());

        testChangeWkpImageStyle(C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromJpgToSvgOnListFromTabbar() throws Exception {
        testChangeWkpImageStyle(C1_LIST, AbstractDiagramListEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(C1_LIST, AbstractDiagramListEditPart.class, true, getSvgImagePath());

        testChangeWkpImageStyle(C1_LIST + BUNDLE_IMAGE_SUFFIX, AbstractDiagramListEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(C1_LIST + BUNDLE_IMAGE_SUFFIX, AbstractDiagramListEditPart.class, true, getSvgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromJpgToSvgOnContainerFromTabbar() throws Exception {
        testChangeWkpImageStyle(C1_CONTAINER, AbstractDiagramContainerEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(C1_CONTAINER, AbstractDiagramContainerEditPart.class, true, getSvgImagePath());

        testChangeWkpImageStyle(C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramContainerEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramContainerEditPart.class, true, getSvgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromJpgToSvgOnBorderedNodeFromTabbar() throws Exception {
        testChangeWkpImageStyle(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, true, getSvgImagePath());

        testChangeWkpImageStyle(A1C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramBorderNodeEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(A1C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramBorderNodeEditPart.class, true, getSvgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromJpgToSvgOnContainedNodeFromTabbar() throws Exception {
        testChangeWkpImageStyle(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());

        testChangeWkpImageStyle(A1C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());
        testChangeWkpImageStyle(A1C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromSvgToJpgOnNodeFromTabbar() throws Exception {
        testChangeWkpImageStyle(C1_NODE, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(C1_NODE, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());

        testChangeWkpImageStyle(C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromSvgToJpgOnListFromTabbar() throws Exception {
        testChangeWkpImageStyle(C1_LIST, AbstractDiagramListEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(C1_LIST, AbstractDiagramListEditPart.class, true, getJpgImagePath());

        testChangeWkpImageStyle(C1_LIST + BUNDLE_IMAGE_SUFFIX, AbstractDiagramListEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(C1_LIST + BUNDLE_IMAGE_SUFFIX, AbstractDiagramListEditPart.class, true, getJpgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromSvgToJpgOnContainerFromTabbar() throws Exception {
        testChangeWkpImageStyle(C1_CONTAINER, AbstractDiagramContainerEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(C1_CONTAINER, AbstractDiagramContainerEditPart.class, true, getJpgImagePath());

        testChangeWkpImageStyle(C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramContainerEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramContainerEditPart.class, true, getJpgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromSvgToJpgOnBorderedNodeFromTabbar() throws Exception {
        testChangeWkpImageStyle(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, true, getJpgImagePath());

        testChangeWkpImageStyle(A1C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramBorderNodeEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(A1C1_NODE + BUNDLE_IMAGE_SUFFIX, AbstractDiagramBorderNodeEditPart.class, true, getJpgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeWkpImageStyleFromSvgToJpgOnContainedNodeFromTabbar() throws Exception {
        testChangeWkpImageStyle(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());

        testChangeWkpImageStyle(A1C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getSvgImagePath());
        testChangeWkpImageStyle(A1C1_CONTAINER + BUNDLE_IMAGE_SUFFIX, AbstractDiagramNodeEditPart.class, true, getJpgImagePath());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnNodeFromAppearanceSection() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(C1_NODE, AbstractDiagramNodeEditPart.class, false, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnListFromAppearanceSection() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(C1_LIST, AbstractDiagramListEditPart.class, false, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnContainerFromAppearanceSection() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(C1_CONTAINER, AbstractDiagramContainerEditPart.class, false, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnBorderNodeFromAppearanceSection() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class, false, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnContainedNodeFromAppearanceSection() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, false, getJpgImagePath(), false);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageStyleOnContainedNodeFromAppearanceSectionWithPluginImagePath() throws Exception {
        testSetWkpImageStyleApplicationAndCancel(A1C1_CONTAINER, AbstractDiagramNodeEditPart.class, false,
                "/org.eclipse.sirius.tests.junit/data/unit/migration/do_not_migrate/campaign/TestCampaign_10/image.bmp", true);
    }

    /**
     * Test "past workspace image from clipboard" functionality. TODO: test with many selection
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageFromClipboard() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());

        // send image to clipboard
        IFile imageFile = project.getFile(IMG_FILE);
        copyImageToClipboard(imageFile);

        // select element
        SWTBotGefEditPart botPart = selectAndCheckEditPart(C1_CONTAINER, AbstractDiagramContainerEditPart.class);
        IAbstractDiagramNodeEditPart part = (IAbstractDiagramNodeEditPart) botPart.part();
        checkCustom(part, false); // check initial style

        // paste image on element
        SWTBotToolbarButton pasteImageButton = bot.toolbarDropDownButtonWithTooltip(Messages.PasteImageAction_toolTipText);
        pasteImageButton.click();

        // check
        checkCustom(part, true);
        if (part.resolveDiagramElement().getStyle() instanceof WorkspaceImage style) {
            IFile wspImgFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(style.getWorkspacePath()));
            IFolder imagesFolder = project.getFolder("images");
            assertTrue("Image file of workspace image style doesn't exist", wspImgFile.exists());
            assertEquals("The image pasted from clipboard is not in `" + getProjectName() + "` project", getProjectName(), wspImgFile.getProject().getName());
            assertEquals("Workspace image file must be in wrong directory", imagesFolder, wspImgFile.getParent());
            assertTrue("Workspace image filename has the wrong format, expected yyyymmdd_hhmmss_xxxxxx.png, found: " + wspImgFile.getName(),
                    wspImgFile.getName().matches("^[0-9]{8}_[0-9]{6}_[0-9]{6}\\.png$"));
        } else {
            fail("The workspace image style is expected on the selected element after pasting image from clipboard");
        }
    }

    /**
     * Test "past workspace image from clipboard" functionality with multiple selection.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageFromClipboardToManyElement() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());

        // send image to clipboard
        IFile imageFile = project.getFile(IMG_FILE);
        copyImageToClipboard(imageFile);

        // select elements
        var selectionNames = new ArrayList<String>();
        var selectionTypes = new ArrayList<Class<? extends IGraphicalEditPart>>();
        
        selectionNames.add(C1_CONTAINER);
        selectionTypes.add(AbstractDiagramContainerEditPart.class);
        selectionNames.add(C1_LIST);
        selectionTypes.add(AbstractDiagramListEditPart.class);

        List<SWTBotGefEditPart> botParts = selectAndCheckManyEditPart(selectionNames, selectionTypes);
        List<IAbstractDiagramNodeEditPart> parts = botParts.stream().map(botPart -> {
            return (IAbstractDiagramNodeEditPart) botPart.part();
        }).toList();
        // check initial style
        for (IAbstractDiagramNodeEditPart part : parts) {
            checkCustom(part, false);
        }

        // paste image on element
        SWTBotToolbarButton pasteImageButton = bot.toolbarDropDownButtonWithTooltip(Messages.PasteImageAction_toolTipText);
        pasteImageButton.click();

        // check
        var wkpImagePath = new ArrayList<String>();
        for (IAbstractDiagramNodeEditPart part : parts) {
            checkCustom(part, true);
            if (part.resolveDiagramElement().getStyle() instanceof WorkspaceImage style) {
                wkpImagePath.add(style.getWorkspacePath());
            } else {
                fail("The workspace image style is expected on the selected element after pasting image from clipboard");
            }
        }

        // check that path is the same for all selected element
        wkpImagePath.stream().allMatch(path -> path.equals(wkpImagePath.get(0)));

        // check this path
        IFile wspImgFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wkpImagePath.get(0)));
        IFolder imagesFolder = project.getFolder("images");
        assertTrue("Image file of workspace image style doesn't exist", wspImgFile.exists());
        assertEquals("The image pasted from clipboard is not in `" + getProjectName() + "` project", getProjectName(), wspImgFile.getProject().getName());
        assertEquals("Workspace image file must be in wrong directory", imagesFolder, wspImgFile.getParent());
        assertTrue("Workspace image filename has the wrong format, expected yyyymmdd_hhmmss_xxxxxx.png, found: " + wspImgFile.getName(),
                wspImgFile.getName().matches("^[0-9]{8}_[0-9]{6}_[0-9]{6}\\.png$"));
    }

    /**
     * Test the button "past workspace image from clipboard" in toolbar and menu.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSetWkpImageFromClipboardButton() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        // clear clipboard
        clearClipboard();
        // paste format is default action
        SWTBotToolbarDropDownButton pasteFormatMenu;

        // check init state
        pasteFormatMenu = bot.toolbarDropDownButtonWithTooltip(Messages.PasteFormatAction_toolTipText_diagram);
        assertFalse("paste format menu must be disabled in toolbar without selection and without clipboard image", //
                pasteFormatMenu.isEnabled());
        assertFalse("paste format menu must be disabled in menu without selection and without clipboard image", //
                bot.menu("Edit").menu(Messages.PasteImageAction_text).isEnabled());

        // select element
        selectAndCheckEditPart(C1_CONTAINER, AbstractDiagramContainerEditPart.class);

        // check with selection but no image in clipboard
        pasteFormatMenu = bot.toolbarDropDownButtonWithTooltip(Messages.PasteFormatAction_toolTipText_diagramElements);
        assertFalse("paste format menu must be disabled in toolbar without clipboard image", //
                pasteFormatMenu.isEnabled());
        assertFalse("paste format menu must be disabled in menu without clipboard image", //
                bot.menu("Edit").menu(Messages.PasteImageAction_text).isEnabled());

        // copy image to Clipboard
        IFile imageFile = project.getFile(IMG_FILE);
        this.copyImageToClipboard(imageFile);

        // check with selection and image in clipboard
        pasteFormatMenu = bot.toolbarDropDownButtonWithTooltip(Messages.PasteImageAction_toolTipText);
        assertTrue("paste format menu must be enabled in toolbar with selection and clipboard image", //
                pasteFormatMenu.isEnabled());
        assertTrue("paste format menu must be enabled in menu with selection and clipboard image", //
                bot.menu("Edit").menu(Messages.PasteImageAction_text).isEnabled());

        // selection of an element that cannot have workspace image style
        selectAndCheckEditPart(A1C1_LIST, AbstractDiagramNameEditPart.class);

        // check with image in clipboard but not valid selection for worspace image
        pasteFormatMenu = bot.toolbarDropDownButtonWithTooltip(Messages.PasteFormatAction_toolTipText_diagramElements);
        assertFalse("paste format menu must be disabled in toolbar when selection cannot have workspace image style", //
                pasteFormatMenu.isEnabled());
        assertFalse("paste format menu must be disabled in menu when selection cannot have workspace image style", //
                bot.menu("Edit").menu(Messages.PasteImageAction_text).isEnabled());
    }

    /**
     * Test the filter function of the workspace selection dialog (VP-3520).
     */
    public void testTheFilterArea() {
        boolean afterEclipse3_5 = true;
        if (new PluginVersionCompatibility("org.eclipse.ui.navigator").compareTo(new Version("3.4.2.M20100120-0800")) <= 0) {
            afterEclipse3_5 = false;
        }

        // Select one edit part (to enable the button).
        selectAndCheckEditPart(A1C1_NODE, AbstractDiagramBorderNodeEditPart.class);
        // Get the button, of the tab Appearance of the properties view, to
        // change background image.
        AbstractSWTBot<? extends Widget> wkpImageButton = getSetStyleToWorkspaceImageButton(false, true);
        // Open the first dialog
        click(wkpImageButton);
        openSelectImageDialog();
        // Check that the filter area is here.
        try {
            bot.text();
        } catch (WidgetNotFoundException e) {
            fail("A text area should be displayed in the dialog to browse the workspace to select image.");
        }
        bot.waitUntil(new CheckNbVisibleElementsInTree(bot.tree(), 1, "The dialog should display only the project at starting."));
        // Check that nothing is displayed if there is no match
        expandIfNeeded(!afterEclipse3_5);
        bot.text().setText("noMatch");
        bot.waitUntil(new CheckNbVisibleElementsInTree(bot.tree(), 0, "The dialog should display nothing if the filter does not match with anything."));
        // Check that the contents of the representations file is filtered
        expandIfNeeded(!afterEclipse3_5);
        String fileName = "air*";
        bot.text().setText(fileName);
        // 1 element in tree and 2 in the gallery
        bot.waitUntil(new CheckNbVisibleElementsInTree(bot.tree(), 1, "The dialog should display all elements needed to access the file \"" + fileName + "\" (project/file)."));
        bot.tree().select(designerProject.getName());
        GallerySelectable gallery = ImageSelectionGalleryHelper.getGallery(bot);
        bot.waitUntil(new CheckNbVisibleElementsInGallery(gallery, 2, "There should be 2 visible elements in the gallery"));
        // Check that nothing is filtered if there is no filter
        expandIfNeeded(true);
        bot.text().setText("");
        // 1 element in tree and 3 in the gallery
        bot.waitUntil(new CheckNbVisibleElementsInTree(bot.tree(), 1, "The dialog should display a normal view if there is no filter."));
        bot.tree().select(designerProject.getName());
        gallery = ImageSelectionGalleryHelper.getGallery(bot);
        bot.waitUntil(new CheckNbVisibleElementsInGallery(gallery, 3, "There should be 3 visible elements in the gallery"));
    }

    /**
     * For Eclipse 3.5 (and before), we need a expand all on treeViewer because in Eclipse 3.5, the filter is only
     * applied on expanded elements.
     * 
     * @param expandNeeded
     *            true if the expandAll is needed
     */
    protected void expandIfNeeded(boolean expandNeeded) {
        if (expandNeeded) {
            // Check that nothing is filtered if there is no filter
            bot.text().setText("");
            bot.waitUntil(new CheckNbVisibleElementsInTree(bot.tree(), 1, "The dialog should display a normal view if there is no filter."));
            // Expand all (because in Eclipse 3.5, the filter only applied on
            // expand elements
            bot.tree().expandNode(getProjectName(), true);
        }
    }

    private void testSetWkpImageStyleCancel(String name, Class<? extends IGraphicalEditPart> type, boolean tabbar) throws Exception {
        startToListenErrorLog(true, true);

        SWTBotGefEditPart botPart = selectAndCheckEditPart(name, type);

        AbstractSWTBot<? extends Widget> wkpImageButton = getSetStyleToWorkspaceImageButton(tabbar, true);
        AbstractSWTBot<? extends Widget> cancelCustomButton = getResetStylePropertiesToDefaultValuesButton(tabbar, false);

        click(wkpImageButton);

        openSelectImageDialog();

        cancel(botPart);

        assertNotNull(cancelCustomButton);
        assertFalse(cancelCustomButton.isEnabled());

        assertFalse("An error occurs during this test.", doesAWarningOccurs() || doesAnErrorOccurs());
    }

    private void testSetWkpImageStyleDisabled(String name, Class<? extends IGraphicalEditPart> type, boolean tabbar) throws Exception {
        selectAndCheckEditPart(name, type);

        if (!tabbar) {
            // no appearance tab in properties view
            return;
        }

        getSetStyleToWorkspaceImageButton(tabbar, false);
        getResetStylePropertiesToDefaultValuesButton(tabbar, false);
    }

    private void testSetWkpImageStyleApplicationAndCancel(String name, Class<? extends IGraphicalEditPart> type, boolean tabbar, String imagePath, boolean isPluginImage) throws Exception {
        SWTBotGefEditPart botPart = selectAndCheckEditPart(name, type);
        IAbstractDiagramNodeEditPart part = (IAbstractDiagramNodeEditPart) botPart.part();

        Dimension oldGMFSize = getSize((Node) part.getNotationView());
        Dimension oldD2DSize = getSize(part.getFigure());

        if (oldGMFSize.height != -1) {
            assertEquals(oldGMFSize.width, oldD2DSize.width);
        }

        if (oldGMFSize.height != -1) {
            assertEquals(oldGMFSize.height, oldD2DSize.height);
        }

        AbstractSWTBot<? extends Widget> wkpImageButton = getSetStyleToWorkspaceImageButton(tabbar, true);
        AbstractSWTBot<? extends Widget> resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButton(tabbar, false);

        click(wkpImageButton);

        openSelectImageDialog();

        if (isPluginImage) {
            String defaultImage = getSvgImagePath();
            ImageSelectionGalleryHelper.selectWorkspaceImage(bot, defaultImage);

            SWTBot propertiesBot = bot.viewByTitle("Properties").bot();
            bot.viewByTitle("Properties").setFocus();
            SWTBotSiriusHelper.selectPropertyTabItem("Style", propertiesBot);
            SWTBotTree tree = propertiesBot.tree();
            SWTBotTreeItem treeItem = tree.expandNode("General").select().getNode("Workspace Path");
            treeItem.doubleClick();
            SWTBotText text = propertiesBot.text(defaultImage.replace('/', File.separatorChar));
            text.setText(imagePath);
            tree.select("General");
            SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesBot);
        } else {
            ImageSelectionGalleryHelper.selectWorkspaceImage(bot, imagePath);
        }

        if (tabbar) {
            editor.click(editor.mainEditPart());

            botPart = selectAndCheckEditPart(name, type);
            wkpImageButton = getSetStyleToWorkspaceImageButton(tabbar, true);
            resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButton(tabbar, true);
        }

        assertNotNull(resetStylePropertiesToDefaultValuesButton);
        assertTrue("Reset style button should be enabled.", resetStylePropertiesToDefaultValuesButton.isEnabled());

        checkCustom(part, true);

        Image image = WorkspaceImageFigure.flyWeightImage(imagePath);
        double ratio = (double) image.getBounds().width / image.getBounds().height;
        int newHeight = (int) (oldD2DSize.width / ratio);

        Dimension newGMFSize = getSize((Node) part.getNotationView());
        Dimension newD2DSize = getSize(part.getFigure());

        assertEquals("The GMF height should be kept.", oldGMFSize.height, newGMFSize.height);
        assertEquals("The GMF width should be kept.", oldGMFSize.width, newGMFSize.width);

        if (part instanceof IDiagramContainerEditPart || part instanceof IDiagramListEditPart) {
            // Auto-sized container are resized on set wkp image to get the
            // image size.
            assertEquals("The GMF width was and stays -1.", -1, oldGMFSize.width);
            assertEquals("The figure size should correspond to the image width.", image.getBounds().width, newD2DSize.width, 2);
            assertEquals("The figure size should correspond to the image width.", image.getBounds().height, newD2DSize.height, 2);
            assertTrue("The primary shape should be a ViewNodeContainerRectangleFigureDesc.",
                    ((AbstractDiagramElementContainerEditPart) part).getPrimaryShape() instanceof ViewNodeContainerRectangleFigureDesc);
            assertTrue("The background figure should be a IWorkspaceImageFigure.", ((AbstractDiagramElementContainerEditPart) part).getBackgroundFigure() instanceof IWorkspaceImageFigure);
            assertNull("The image figure should not have a drop shadow border.", ((AbstractDiagramElementContainerEditPart) part).getMainFigure().getBorder());
        } else {
            // Nodes keep their size and the height is modified to keep the
            // image ratio
            assertEquals("The node GMF width should not be impacted.", oldD2DSize.width, newD2DSize.width);
            assertEquals("The node figure should have the same ratio than the image.", newHeight, newD2DSize.height, 2);
        }

        click(resetStylePropertiesToDefaultValuesButton);

        if (tabbar) {
            editor.click(editor.mainEditPart());

            botPart = selectAndCheckEditPart(name, type);
            wkpImageButton = getSetStyleToWorkspaceImageButton(tabbar, true);
            resetStylePropertiesToDefaultValuesButton = getResetStylePropertiesToDefaultValuesButton(tabbar, false);
        }
        bot.waitUntil(new WidgetIsDisabledCondition(resetStylePropertiesToDefaultValuesButton));
        checkCustom(part, false);

        Dimension newGMFSize2 = getSize((Node) part.getNotationView());
        Dimension newD2DSize2 = getSize(part.getFigure());
        assertEquals(oldD2DSize.width, newD2DSize2.width);
        assertEquals(oldD2DSize.height, newD2DSize2.height);

        assertEquals(oldGMFSize.width, newGMFSize2.width);
        // assertEquals(oldGMFSize.height, newGMFSize2.height);

        if (part instanceof IDiagramContainerEditPart || part instanceof IDiagramListEditPart) {
            assertTrue("The primary shape should be a GradientRoundedRectangle.", ((AbstractDiagramElementContainerEditPart) part).getPrimaryShape() instanceof GradientRoundedRectangle);
            assertNull("The background figure should be null for a gradient style.", ((AbstractDiagramElementContainerEditPart) part).getBackgroundFigure());
            // Alpha drop shadow has been recreated.
            assertTrue("The drop shadow border should have been recreated.", ((AbstractDiagramElementContainerEditPart) part).getMainFigure().getBorder() instanceof AlphaDropShadowBorder);
        }
    }

    private void testChangeWkpImageStyle(String name, Class<? extends IGraphicalEditPart> type, boolean tabbar, String imagePath) throws Exception {
        SWTBotGefEditPart botPart = selectAndCheckEditPart(name, type);
        IAbstractDiagramNodeEditPart part = (IAbstractDiagramNodeEditPart) botPart.part();

        AbstractSWTBot<? extends Widget> wkpImageButton = getSetStyleToWorkspaceImageButton(tabbar, true);

        click(wkpImageButton);

        openSelectImageDialog();

        ImageSelectionGalleryHelper.selectWorkspaceImage(bot, designerProject.getName() + "/" + imagePath);

        if (tabbar) {
            editor.click(editor.mainEditPart());

            botPart = selectAndCheckEditPart(name, type);
            wkpImageButton = getSetStyleToWorkspaceImageButton(tabbar, true);
        }

        assertFalse("No message should be log in error log after a change of image:" + getErrorLoggersMessage(), doesAnErrorOccurs());
        checkCustom(part, true);
    }

    private Dimension getSize(Node gmfNode) {
        Size size = (Size) gmfNode.getLayoutConstraint();
        return new Dimension(size.getWidth(), size.getHeight());
    }

    private Dimension getSize(IFigure figure) {
        return new Dimension(figure.getSize());
    }

    private String getJpgImagePath() {
        return designerProject.getName() + "/" + IMG_FILE;
    }

    private String getSvgImagePath() {
        return designerProject.getName() + "/" + IMG_SVG_FILE;
    }

    private void cancel(SWTBotGefEditPart botPart) {
        bot.button("Cancel").click();
        checkCustom((IAbstractDiagramNodeEditPart) botPart.part(), false);
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends IGraphicalEditPart> type) {
        editor.setFocus();
        editor.reveal(name);

        SWTBotGefEditPart botPart = editor.getEditPart(name, type);
        assertNotNull("The requested edit part should not be null", botPart);

        CheckSelectedCondition cs = new CheckSelectedCondition(editor, name, type);
        editor.click(botPart);
        botPart.select();
        bot.waitUntil(cs);

        return botPart;
    }

    private List<SWTBotGefEditPart> selectAndCheckManyEditPart(List<String> names, List<Class<? extends IGraphicalEditPart>> types) throws IllegalArgumentException {
        // precondition
        if (names.size() != types.size()) {
            throw new IllegalArgumentException("Size of names and types must be equals");
        }

        var selectedEditPart = new ArrayList<SWTBotGefEditPart>();
        editor.setFocus();
        int selectionSize = names.size();
        for (int i = 0; i < selectionSize; ++i) {
            String name = names.get(i);
            Class<? extends IGraphicalEditPart> type = types.get(i);

            SWTBotGefEditPart botPart = editor.getEditPart(name, type);
            assertNotNull("The requested edit part should not be null", botPart);

            CheckSelectedCondition cs = new CheckSelectedCondition(editor, name, type);
            editor.clickWithKeys(name, SWT.SHIFT);
            bot.waitUntil(cs);

            selectedEditPart.add(botPart);
        }
        return selectedEditPart;
    }

    private void openSelectImageDialog() {
        bot.waitUntil(Conditions.shellIsActive(DIALOG_TITLE));

        SWTBotShell shell = bot.shell(DIALOG_TITLE);
        shell.setFocus();
    }

    private void checkCustom(IAbstractDiagramNodeEditPart part, boolean custom) {
        AbstractDNode node = (AbstractDNode) part.resolveDiagramElement();
        boolean isCustom = new DDiagramElementQuery(node).isCustomized();
        assertEquals(custom, isCustom);
    }

    private void copyImageToClipboard(IFile imageFile) throws Exception {
        ImageData[] data;
        try (InputStream imageStream = imageFile.getContents()) {
            data = new ImageData[] { new ImageData(imageStream) };
        }
        syncExec(() -> {
            Transfer[] imgTransfer = new Transfer[] { ImageTransfer.getInstance() };
            Clipboard clipboard = new Clipboard(Display.getCurrent());
            clipboard.setContents(data, imgTransfer);
            clipboard.dispose();
        });
    }

    private void clearClipboard() throws Exception {
        syncExec(() -> {
            Clipboard clipboard = new Clipboard(Display.getCurrent());
            clipboard.clearContents();
            clipboard.dispose();
        });
    }
}
