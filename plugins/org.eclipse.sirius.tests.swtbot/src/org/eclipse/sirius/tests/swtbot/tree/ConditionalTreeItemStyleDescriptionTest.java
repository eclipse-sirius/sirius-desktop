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
package org.eclipse.sirius.tests.swtbot.tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;

/**
 * Test condtional tree item description. Test automatic refresh and manual refresh. Test undo/redo after each test type
 * Test opening and closing model or editor to verify that all changes are effective
 * 
 * @author jdupont
 */
public class ConditionalTreeItemStyleDescriptionTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /**
     * Sirius Specific Model.
     */
    private static final String VSM = "ecore.odesign";

    /**
     * 
     */
    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/tree/";

    /**
     * Session file.
     */
    private static final String SESSION_FILE = "tree.aird";

    /**
     * UML File.
     */
    private static final String ECORE_FILE = "tree.ecore";

    /**
     * File directory.
     */
    private static final String FILE_DIR = "/";

    /**
     * Sirius Group.
     */
    private static final String GROUP = "Ecore Editing Workbench V4.6";

    /**
     * Properties view tab General.
     */
    private static final String GENERAL = "General";

    /**
     * Sirius name.
     */
    private static final String VIEWPOINT_NAME = "Design";

    /**
     * Representation name.
     */
    private static final String REPRESENTATION_NAME = "Tree";

    /**
     * Semantic model instance.
     */
    private static final String REPRESENTATION_INSTANCE_NAME = "new Tree";

    /**
     * Contextual menu undo.
     */
    private static final String UNDO = "Undo Refresh representation";

    /**
     * Contextual menu redo.
     */
    private static final String REDO = "Redo Refresh representation";

    /**
     * Element name from semantic model
     */
    private static final String ELEMENT_NAME = "TestTailleNomSup13";

    /**
     * Current diagram.
     */
    protected UITreeRepresentation treeRepresentation;

    /**
     * Current editor.
     */
    protected SWTBotEditor editor;

    /**
     * Session.
     */
    private UIResource sessionAirdResource;

    /**
     * Local Session.
     */
    private UILocalSession localSession;

    /**
     * Light orange color value RGB.
     */
    private Color lightOrange = VisualBindingManager.getDefault().getColorFromName(MAPCOLORVALUE.get(14));

    /**
     * Light green color value RGB.
     */
    private Color lightGreen = VisualBindingManager.getDefault().getColorFromName(MAPCOLORVALUE.get(13));

    /**
     * The label color value.
     */
    private Color labelColor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM, SESSION_FILE, ECORE_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * Test automatic refresh when tree editor is open when modify odesign.
     */
    @Test
    public void testAutomaticRefreshWithEditorOpen() {
        disableAutoRefresh();
        enableAutoRefresh();
        disableRefreshOnOpeningRepresentation();
        enableRefreshOnOpeningRepresentation();

        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        // the session is already open and opening odesign
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        modifyVSM(odesignEditor);

        bot.sleep(500);

        // Test editor refresh
        refreshEditorTest();

        // Test undo and redo on refreh
        undoRedoRefresh();

        localSession.close(true);
    }

    /**
     * Test automatic refresh when session is open before modify odesign.
     */
    @Test
    public void testAutomaticRefreshWithSessionOpen() {
        disableAutoRefresh();
        enableAutoRefresh();
        disableRefreshOnOpeningRepresentation();
        enableRefreshOnOpeningRepresentation();

        updateVSMandOpenEditor();

        // Test editor refresh
        refreshEditorTest();
        SWTBotUtils.waitAllUiEvents();

        // Test undo and redo on refreh
        undoRedoRefresh();

        // close session
        localSession.close(true);
    }

    /**
     * Test automatic refresh when session is close during modify odesign.
     */
    @Test
    public void testAutomaticRefreshWithSessionClose() {
        disableAutoRefresh();
        enableAutoRefresh();
        disableRefreshOnOpeningRepresentation();
        enableRefreshOnOpeningRepresentation();

        // the session is already open and opening odesign
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        // the session is close and the odesign is open
        if (localSession.isDirty()) {
            localSession.close(true);
        } else {
            localSession.closeNoDirty();
        }

        // Modify VSM
        modifyVSM(odesignEditor);

        // Wait for the refresh of the vsm is over.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Open session
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        // Test editor refresh
        refreshEditorTest();

        // Test undo and redo on refreh
        undoRedoRefresh();

        localSession.close(true);
    }

    /**
     * Test manual refresh.
     */
    @Test
    public void testStyleDescriptionManualRefresh() {
        disableAutoRefresh();
        disableRefreshOnOpeningRepresentation();

        updateVSMandOpenEditor();
        SWTBotUtils.waitAllUiEvents();

        editor = treeRepresentation.getEditor();

        editor.save();
        SWTBotUtils.waitProgressMonitorClose("Progress Information");

        TreeItem widgetTestTailleNomSup13 = null;
        String treeItemName = null;

        if (editor.bot().tree().getAllItems().length > 0) {
            treeItemName = editor.bot().tree().getAllItems()[1].getText();
        }
        if (treeItemName != null) {
            widgetTestTailleNomSup13 = editor.bot().tree().getTreeItem(treeItemName).widget;
        }

        Color colorBackground = null;
        Color labelColor = null;

        // Retrieve show icon, label size, label expression, label alignment,
        // label format, color background, label color of 'new EClass 1'
        if (widgetTestTailleNomSup13 != null) {
            colorBackground = getWidgetBackgroundColor(widgetTestTailleNomSup13);
            labelColor = getLabelColor(widgetTestTailleNomSup13);
        }

        Color lightYellow = VisualBindingManager.getDefault().getColorFromName(MAPCOLORVALUE.get(17));
        Color lightGreen = VisualBindingManager.getDefault().getColorFromName(MAPCOLORVALUE.get(13));

        assertThat(colorBackground, equalTo(lightYellow));
        assertThat(labelColor, equalTo(lightGreen));

        // Manual refreh with click context menu
        editor.bot().tree().contextMenu(REFRESH_TREE).click();
        SWTBotUtils.waitProgressMonitorClose("Progress Information");

        refreshEditorTest();

        localSession.closeNoDirty();

    }

    /**
     * Undo and redo on refresh action.
     */
    private void undoRedoRefresh() {
        TreeItem widgetTestTailleNomSup13 = null;
        String treeItemName = null;

        if (editor.bot().tree().getAllItems().length > 0) {
            for (SWTBotTreeItem treeItem : editor.bot().tree().getAllItems()) {
                treeItemName = treeItem.getText();
                if (ELEMENT_NAME.equals(treeItemName)) {
                    break;
                }
            }
        }
        if (treeItemName != null) {
            widgetTestTailleNomSup13 = editor.bot().tree().getTreeItem(treeItemName).widget;
        }

        labelColor = getLabelColor(widgetTestTailleNomSup13);
        assertThat(labelColor, equalTo(lightOrange));

        widgetTestTailleNomSup13 = null;
        treeItemName = null;
        labelColor = null;

        SWTBotUtils.waitAllUiEvents();

        // Undo
        editor.setFocus();
        SWTBotUtils.waitAllUiEvents();
        // SWTBotMenu menu3 = bot.menu("Edit");
        SWTBotMenu menu = SWTBotSiriusHelper.menu(editor.bot(), "Edit");
        SWTBotMenu menu2 = menu.menu(UNDO);
        menu2.click();

        if (editor.bot().tree().getAllItems().length > 0) {
            for (SWTBotTreeItem treeItem : editor.bot().tree().getAllItems()) {
                treeItemName = treeItem.getText();
                if (ELEMENT_NAME.equals(treeItemName)) {
                    break;
                }
            }
        }
        if (treeItemName != null) {
            widgetTestTailleNomSup13 = editor.bot().tree().getTreeItem(treeItemName).widget;
        }

        labelColor = getLabelColor(widgetTestTailleNomSup13);
        assertThat(labelColor, equalTo(lightGreen));

        widgetTestTailleNomSup13 = null;
        treeItemName = null;
        labelColor = null;

        // SWTBotUtils.waitAllUiEvents();

        // ReDo
        SWTBotSiriusHelper.menu(bot, "Edit").menu(REDO).click();
        SWTBotUtils.waitAllUiEvents();

        if (editor.bot().tree().getAllItems().length > 0) {
            for (SWTBotTreeItem treeItem : editor.bot().tree().getAllItems()) {
                treeItemName = treeItem.getText();
                if (ELEMENT_NAME.equals(treeItemName)) {
                    break;
                }
            }
        }
        if (treeItemName != null) {
            widgetTestTailleNomSup13 = editor.bot().tree().getTreeItem(treeItemName).widget;
        }

        labelColor = getLabelColor(widgetTestTailleNomSup13);
        assertThat(labelColor, equalTo(lightOrange));
    }

    /**
     * 
     */
    private void updateVSMandOpenEditor() {
        // the session is already open and opening odesign
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);

        // Modify VSM
        modifyVSM(odesignEditor);

        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);
    }

    /**
     * Modify all fields in properties view of tree item style description in viewpoint specific model (.odesign).
     * 
     * @param odesignEditor
     *            the odesign editor.
     */
    private void modifyVSM(SWTBotVSMEditor odesignEditor) {
        // expand the tree : Tree Item Style Description 8
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("Design").expandNode("Tree").expandNode("Class").expandNode("Conditional Style").select();

        // accesses to property view
        SWTBotView propertiesView = bot.viewByTitle(PROPERTIES);
        propertiesView.setFocus();

        // accesses to tab General
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL, propertiesView.bot());
        changeAndTestPropertyTabGeneral("aql:self.name.size() > 13");
        SWTBotSiriusHelper.selectPropertyTabItem("All", propertiesView.bot());

        odesignEditor.bot().tree().setFocus();
        // Save odesign
        saveViewpointSpecificationModel(VSM);

    }

    /**
     * Verify that editor changes made in the odesign.
     */
    private void refreshEditorTest() {

        editor = treeRepresentation.getEditor();

        editor.save();
        SWTBotUtils.waitProgressMonitorClose("Progress Information");

        TreeItem widgetTestTailleNomSup13 = null;
        String treeItemName = null;

        if (editor.bot().tree().getAllItems().length > 0) {
            for (SWTBotTreeItem treeItem : editor.bot().tree().getAllItems()) {
                treeItemName = treeItem.getText();
                if (ELEMENT_NAME.equals(treeItemName)) {
                    break;
                }
            }
        }
        if (treeItemName != null) {
            widgetTestTailleNomSup13 = editor.bot().tree().getTreeItem(treeItemName).widget;
        }

        Color colorBackground = null;
        Color labelColor = null;

        // Retrieve show icon, label size, label expression, label alignment,
        // label format, color background, label color of 'new EClass 1'
        if (widgetTestTailleNomSup13 != null) {
            colorBackground = getWidgetBackgroundColor(widgetTestTailleNomSup13);
            labelColor = getLabelColor(widgetTestTailleNomSup13);
        }

        Color black = VisualBindingManager.getDefault().getColorFromName(MAPCOLORVALUE.get(1));
        Color lightOrange = VisualBindingManager.getDefault().getColorFromName(MAPCOLORVALUE.get(14));

        assertThat(colorBackground, equalTo(black));
        assertThat(labelColor, equalTo(lightOrange));
    }

    /**
     * Retrieve, test, change and test all fields in General tab.
     */
    private void changeAndTestPropertyTabGeneral(String newValue) {
        // Retrieve predicate expression
        assertThat(bot.viewByTitle(PROPERTIES).bot().text(0).getText(), equalTo(""));
        // Set predicate expression
        bot.viewByTitle(PROPERTIES).bot().text(0).setFocus();
        bot.viewByTitle(PROPERTIES).bot().text(0).setText(newValue);
        assertThat(bot.viewByTitle(PROPERTIES).bot().text(0).getText(), equalTo(newValue));
    }
}
