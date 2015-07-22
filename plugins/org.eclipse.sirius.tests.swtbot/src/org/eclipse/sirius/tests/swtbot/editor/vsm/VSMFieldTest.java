/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.widget.ContextualMenuItemGetter;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotSpinner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Tests VSM field.
 * 
 * @author jdupont
 */
public class VSMFieldTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VSM = "vsm.odesign";

    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    private static final String DATA_UNIT_DIR = "data/unit/editor/vsm/";

    private static final String GROUP = "Group";

    private static final String GENERAL = "General";

    private static final String GRADIENT_TO_LEFT = "_UI_BackgroundStyle_GradientLeftToRight_literal";

    private static final String GRADIENT_TO_BOTTOM = "_UI_BackgroundStyle_GradientTopToBottom_literal";

    private static final String LIQUID_OBLIQUE = "_UI_BackgroundStyle_Liquid_literal";

    private static final String FLAT_CONTAINER_STYLE = "_UI_FlatContainerStyleDescription_type";

    private static final String PROPERTIES = "Properties";

    private static final String LABEL = "Label";

    private static final String CREATE_VIEW = "_UI_CreateView_type";

    private static final String CREATE_EDGE_VIEW = "_UI_CreateEdgeView_type";

    private static final String DELETE_VIEW = "_UI_DeleteView_type";

    private static final String TABLE = "Table";

    private static final String CROSS_TABLE = "CrossTable";

    private static final String TREE = "Tree";

    private static final String CREATE_LINE_TOOL = "_UI_CreateLineTool_type";

    private static final String CREATE_COLUMN = "_UI_CreateCrossColumnTool_type";

    private static final String DROP_TOOL = "_UI_TreeItemContainerDropTool_type";

    private static final String CREATE = "_UI_TreeItemCreationTool_type";

    private SWTBotText labelText;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    /**
     * Tests that the background style of Flat Container Style Description has 2
     * elements in the list.
     */
    public void testCheckFlatContainerBackgroundStyle() {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // Check background style element choice
        checkBackgroundStyleElement(odesignEditor);

    }

    /**
     * Tests that on a style in a diagram description, the label size minimal
     * value is 1.
     */
    public void testLabelSizeMinimalValueOnDiagramStyle() {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // Check background style element choice
        checkDiagramStyleLabelSize(odesignEditor);
    }

    /**
     * Tests that on a style in a table description, the label size minimal
     * value is 1.
     */
    public void testLabelSizeMinimalValueOnTableStyle() {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // Check background style element choice
        checkTableStyleLabelSize(odesignEditor);
    }

    /**
     * Tests that on a style in a table description, the label size minimal
     * value is 1.
     */
    public void testLabelSizeMinimalValueOnTreeStyle() {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // Check background style element choice
        checkTreeStyleLabelSize(odesignEditor);
    }

    /**
     * Test that for Create Line Tool, 'Create View' operation is not available
     * in menu.
     */
    public void testThatCreateViewNotPresentForTable() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
            org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find node with text: vsm.odesign
            at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem.java:334)
            at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:308)
            at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:346)
            at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.expandNode(SWTBotTreeItem.java:283)
            at org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper.openEditor(SWTBotCommonHelper.java:138)
            at org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase.openViewpointSpecificationModel(AbstractSiriusSwtBotGefTestCase.java:1553)
             */
            return;
        }
        String operationName = DiagramUIPlugin.getPlugin().getString(CREATE_VIEW);
        String createLineTool = TableUIPlugin.getPlugin().getString(CREATE_LINE_TOOL);

        checkMenuIsNotAvailable(TABLE, createLineTool, operationName);
    }

    /**
     * Test that for Create Line Tool and Create Column tool, 'Create View'
     * operation is not available in menu.
     */
    public void testThatCreateViewNotPresentForCrossTable() {
        String operationName = DiagramUIPlugin.getPlugin().getString(CREATE_VIEW);
        String createLineTool = TableUIPlugin.getPlugin().getString(CREATE_LINE_TOOL);
        String createColumn = TableUIPlugin.getPlugin().getString(CREATE_COLUMN);

        checkMenuIsNotAvailable(CROSS_TABLE, createLineTool, operationName);
        checkMenuIsNotAvailable(CROSS_TABLE, createColumn, operationName);
    }

    /**
     * Test that for Create tool and Drop Tool, 'Create View' operation is not
     * available in menu.
     */
    public void testThatCreateViewNotPresentForTree() {
        String operationName = DiagramUIPlugin.getPlugin().getString(CREATE_VIEW);
        String dropTool = TreeUIPlugin.getPlugin().getString(DROP_TOOL);
        String create = TreeUIPlugin.getPlugin().getString(CREATE);

        checkMenuIsNotAvailable(TREE, dropTool, operationName);
        checkMenuIsNotAvailable(TREE, create, operationName);
    }

    /**
     * Test that for Create Line Tool, 'Create Edge View' operation is not
     * available in menu.
     */
    public void testThatCreateEdgeViewNotPresentForTable() {
        String operationName = DiagramUIPlugin.getPlugin().getString(CREATE_EDGE_VIEW);
        String createLineTool = TableUIPlugin.getPlugin().getString(CREATE_LINE_TOOL);

        checkMenuIsNotAvailable(TABLE, createLineTool, operationName);
    }

    /**
     * Test that for Create Line Tool and Create Column tool, 'Create Edge View'
     * operation is not available in menu.
     */
    public void testThatCreateEdgeViewNotPresentForCrossTable() {
        String operationName = DiagramUIPlugin.getPlugin().getString(CREATE_EDGE_VIEW);
        String createLineTool = TableUIPlugin.getPlugin().getString(CREATE_LINE_TOOL);
        String createColumn = TableUIPlugin.getPlugin().getString(CREATE_COLUMN);

        checkMenuIsNotAvailable(CROSS_TABLE, createLineTool, operationName);
        checkMenuIsNotAvailable(CROSS_TABLE, createColumn, operationName);
    }

    /**
     * Test that for Create tool and Drop Tool, 'Create Edge View' operation is
     * not available in menu.
     */
    public void testThatCreateEdgeViewNotPresentForTree() {
        String operationName = DiagramUIPlugin.getPlugin().getString(CREATE_EDGE_VIEW);
        String dropTool = TreeUIPlugin.getPlugin().getString(DROP_TOOL);
        String create = TreeUIPlugin.getPlugin().getString(CREATE);

        checkMenuIsNotAvailable(TREE, dropTool, operationName);
        checkMenuIsNotAvailable(TREE, create, operationName);
    }

    /**
     * Test that for Create Line Tool, 'Delete View' operation is not available
     * in menu.
     */
    public void testThateDeleteViewNotPresentForTable() {
        String operationName = SiriusEditPlugin.getPlugin().getString(DELETE_VIEW);
        String createLineTool = TableUIPlugin.getPlugin().getString(CREATE_LINE_TOOL);

        checkMenuIsNotAvailable(TABLE, createLineTool, operationName);
    }

    /**
     * Test that for Create Line Tool and Create Column tool, 'Delete View'
     * operation is not available in menu.
     */
    public void testThateDeleteViewNotPresentForCrossTable() {
        String operationName = SiriusEditPlugin.getPlugin().getString(DELETE_VIEW);
        String createLineTool = TableUIPlugin.getPlugin().getString(CREATE_LINE_TOOL);
        String createColumn = TableUIPlugin.getPlugin().getString(CREATE_COLUMN);

        checkMenuIsNotAvailable(CROSS_TABLE, createLineTool, operationName);
        checkMenuIsNotAvailable(CROSS_TABLE, createColumn, operationName);
    }

    /**
     * Test that for Create tool and Drop Tool, 'Delete View' operation is not
     * available in menu.
     */
    public void testThatDeleteViewNotPresentForTree() {
        String operationName = SiriusEditPlugin.getPlugin().getString(DELETE_VIEW);
        String dropTool = TreeUIPlugin.getPlugin().getString(DROP_TOOL);
        String create = TreeUIPlugin.getPlugin().getString(CREATE);

        checkMenuIsNotAvailable(TREE, dropTool, operationName);
        checkMenuIsNotAvailable(TREE, create, operationName);
    }

    /**
     * Check that is possible to specify element with empty label expression.
     * 
     */
    public void testEmptyNodeLabels() {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        String gradient = DiagramUIPlugin.getPlugin().getString(FLAT_CONTAINER_STYLE);
        // expands the tree : Container style description
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("vsm").expandNode("Diagram").expandNode("Package").expandNode(gradient + " white to light_gray").select();
        // set the focus on the Properties view
        bot.viewByTitle(PROPERTIES).setFocus();
        // set the focus on the General tab
        SWTBotSiriusHelper.selectPropertyTabItem(LABEL);
        // get the label expression
        labelText = bot.viewByTitle(PROPERTIES).bot().text(0);
        // focus on label field
        labelText.setFocus();
        String oldLabelExpression = labelText.getText();
        // Set label to empty
        labelText.setText("");
        // set the focus on the General tab
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL);
        bot.saveAllEditors();
        // set the focus on the Label tab
        SWTBotSiriusHelper.selectPropertyTabItem(LABEL);
        assertEquals("The label expression should be empty", "", labelText.getText());
        closeAllEditors();
        // Open VSM
        odesignEditor = openViewpointSpecificationModel(VSM);
        // expands the tree : Container style description
        odesignEditor.bot().tree().expandNode(ODESIGN).expandNode(GROUP).expandNode("vsm").expandNode("Diagram").expandNode("Package").expandNode(gradient + " white to light_gray").select();
        // set the focus on the Properties view
        bot.viewByTitle(PROPERTIES).setFocus();
        // set the focus on the Label tab
        SWTBotSiriusHelper.selectPropertyTabItem(LABEL);
        // get the label expression
        labelText = bot.viewByTitle(PROPERTIES).bot().text(0);
        assertEquals("The label expression should be empty", "", labelText.getText());
        // set the old label expression
        labelText.setText(oldLabelExpression);
        bot.saveAllEditors();
    }

    /**
     * Check that the menu with operation name is not available for toolName
     * 
     * @param representationDescriptionName
     *            the representation Description name
     * @param toolName
     *            the tool name
     * @param operationName
     *            the operation name
     */
    private void checkMenuIsNotAvailable(String representationDescriptionName, String toolName, String operationName) {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // expands the tree
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("vsm").expandNode(representationDescriptionName).select(toolName);

        SWTBotUtils.waitAllUiEvents();

        // We only check that the operation name is not available in menu.
        Result<MenuItem> menuItemGetter = new ContextualMenuItemGetter(tree.widget, new String[] { operationName });
        final MenuItem navigationMenuItem = UIThreadRunnable.syncExec(menuItemGetter);
        boolean menuAvailable = navigationMenuItem != null;
        assertFalse("The provided menu " + operationName + " should not be available", menuAvailable);
    }

    /**
     * Check that the background style of an element is displayed properly
     * 
     * @param odesignEditor
     */
    private void checkBackgroundStyleElement(SWTBotVSMEditor odesignEditor) {
        // Retrieve properties value of background style choices
        String gradientLeftToRight = DiagramUIPlugin.getPlugin().getString(GRADIENT_TO_LEFT);
        assertNotNull("The properties '" + GRADIENT_TO_LEFT + "' should be available", gradientLeftToRight);
        String gradientTopToBottom = DiagramUIPlugin.getPlugin().getString(GRADIENT_TO_BOTTOM);
        assertNotNull("The properties '" + GRADIENT_TO_BOTTOM + "' should be available", gradientTopToBottom);
        String liquid_oblique = DiagramUIPlugin.getPlugin().getString(LIQUID_OBLIQUE);
        assertNotNull("The properties '" + LIQUID_OBLIQUE + "' should be available", liquid_oblique);
        String gradient = DiagramUIPlugin.getPlugin().getString(FLAT_CONTAINER_STYLE);

        // expands the tree : Container style description
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("vsm").expandNode("Diagram").expandNode("Package").expandNode(gradient + " white to light_gray").select();
        // set the focus on the Properties view
        bot.viewByTitle(PROPERTIES).setFocus();
        // set the focus on the General tab
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL);

        // check that background style field choices are correct
        SWTBotCCombo ccomboBox = bot.viewByTitle(PROPERTIES).bot().ccomboBox();
        assertEquals("BackgroundStyle must contain 3 elements", 3, ccomboBox.itemCount());
        assertEquals("Fist element must be  : " + gradientLeftToRight, gradientLeftToRight, ccomboBox.items()[0]);
        assertEquals("Second element must be  : " + liquid_oblique, liquid_oblique, ccomboBox.items()[1]);
        assertEquals("Third element must be : " + gradientTopToBottom, gradientTopToBottom, ccomboBox.items()[2]);

    }

    /**
     * Check that the label size of a style in a diagram description cannot be
     * set as 0.
     * 
     * @param odesignEditor
     */
    private void checkDiagramStyleLabelSize(SWTBotVSMEditor odesignEditor) {
        // Retrieve properties value of background style choices
        String gradientLeftToRight = DiagramUIPlugin.getPlugin().getString(GRADIENT_TO_LEFT);
        assertNotNull("The properties '" + GRADIENT_TO_LEFT + "' should be available", gradientLeftToRight);
        String gradientTopToBottom = DiagramUIPlugin.getPlugin().getString(GRADIENT_TO_BOTTOM);
        assertNotNull("The properties '" + GRADIENT_TO_BOTTOM + "' should be available", gradientTopToBottom);
        String liquid_oblique = DiagramUIPlugin.getPlugin().getString(LIQUID_OBLIQUE);
        assertNotNull("The properties '" + LIQUID_OBLIQUE + "' should be available", liquid_oblique);
        String gradient = DiagramUIPlugin.getPlugin().getString(FLAT_CONTAINER_STYLE);

        // expands the tree : Container style description
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("vsm").expandNode("Diagram").expandNode("Package").expandNode(gradient + " white to light_gray").select();
        processLabelSizeTests("8");
    }

    /**
     * Check that the label size of a style in a table description cannot be set
     * as 0.
     * 
     * @param odesignEditor
     */
    private void checkTableStyleLabelSize(SWTBotVSMEditor odesignEditor) {
        // expands the tree : Container style description
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("vsm").expandNode("Table").expandNode("Line").expandNode("Foreground 12").select();
        processLabelSizeTests("12");
    }

    /**
     * Check that the label size of a style in a table description cannot be set
     * as 0.
     * 
     * @param odesignEditor
     */
    private void checkTreeStyleLabelSize(SWTBotVSMEditor odesignEditor) {
        // expands the tree : Tree item style description
        SWTBotTree tree = odesignEditor.bot().tree();
        tree.expandNode(ODESIGN).expandNode(GROUP).expandNode("vsm").expandNode("Tree").expandNode("Tree").expandNode("feature:name").select();
        processLabelSizeTests("8");
    }

    /**
     * 
     */
    private void processLabelSizeTests(String initialValue) {
        // set the focus on the Properties view
        bot.viewByTitle(PROPERTIES).setFocus();
        // set the focus on the General tab
        SWTBotSiriusHelper.selectPropertyTabItem(LABEL);

        // check that background style field choices are correct
        SWTBotSpinner ccomboBox = bot.viewByTitle(PROPERTIES).bot().spinner();
        assertEquals("Initial value of the Label Size is expected to be " + initialValue, initialValue, ccomboBox.getText());
        ccomboBox.setSelection(3);
        assertEquals("Value of the Label Size is expected to have been changed to 3", "3", ccomboBox.getText());
        ccomboBox.setSelection(0);
        assertEquals("Value of the Label Size is expected to have been changed to 1, the minimal value", "1", ccomboBox.getText());
    }
}
