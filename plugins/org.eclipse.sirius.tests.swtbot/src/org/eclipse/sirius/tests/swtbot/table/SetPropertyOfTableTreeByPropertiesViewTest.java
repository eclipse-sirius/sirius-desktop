/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.table;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionSavedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test setting property of a table editor by the properties view with an other editor tree open and setting property of
 * a tree editor by the properties view with an other table editor open. Test VP-1896.
 * 
 * 
 * @author jdupont
 */
public class SetPropertyOfTableTreeByPropertiesViewTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "1896";

    private static final String VSM_FILE = "1896.odesign";

    private static final String SESSION_FILE = "1896.aird";

    private static final String ECORE_FILE = "1896.ecore";

    private static final String FILE_DIR = "/";

    private static final String DATA_UNIT_DIR = "data/unit/table/setPropertyTable/vp-1896/";

    private static final String REPRESENTATION_TABLE_NAME = "Table";

    private static final String REPRESENTATION_TREE_NAME = "Tree";

    private static final String REPRESENTATION_TABLE_INSTANCE_NAME = "new Table";

    private static final String REPRESENTATION_TREE_INSTANCE_NAME = "new Tree";

    private static final String CLASS1 = "new EClass 1";

    private static final String CLASS1_RENAME = "new EClass 1 rename";

    private static final String PROPERTIES = "Properties";

    private static final String SEMANTIC = "Semantic";

    private static final String REFRESH_TABLE = "Force a refresh of the table";

    private static final String REFRESH_TREE = "Force a refresh of the tree";

    /**
     * Current table.
     */
    protected UITableRepresentation table;

    /**
     * Current tree.
     */
    protected UITreeRepresentation tree;

    /**
     * Test Properties view title.
     * <P>
     * Step 1 : open a table representations
     * <p>
     * Step 2 : select an element
     * <p>
     * Step 3 : check the properties view title is equals to the label of the semantic element ("equals" -> the title
     * comes from the item provider.
     */
    public void testTablePropertiesViewTitle() {
        // Step 1: open table
        openTableEditor();

        // Step 2: select a table element.
        table.getTable().select(0, 0);
        String cell = table.getTable().cell(0, 0);

        // Step 3: check the properties view title.
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        Option<String> title = SWTBotSiriusHelper.getPropertyItemTitle();
        assertTrue(title.some());
        assertEquals(cell, title.get());
    }

    /**
     * Test Properties view title.
     * <P>
     * Step 1 : open a tree representations
     * <p>
     * Step 2 : select an element
     * <p>
     * Step 3 : check the properties view title is equals to the label of the semantic element ("equals" -> the title
     * comes from the item provider.
     */
    public void testTreePropertiesViewTitle() {
        // Step 1: open tree
        openTreeEditor();
        openTableEditor();

        // Step 2: select a table element.
        tree.getTree().select(0, 0);
        String cell = tree.getTree().selection().get(0, 0);

        // Step 3: check the properties view title.
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        Option<String> title = SWTBotSiriusHelper.getPropertyItemTitle();
        assertTrue(title.some());
        assertEquals(cell, title.get());
    }

    /**
     * Test modify string field (name of semantic element) in property view of table.
     * <P>
     * Step 1 : open a tree and table representations
     * <p>
     * Step 2 : change name of semantic element
     * <p>
     * Step 3 : Verify that the tree and table representation element have name modified
     * <p>
     * Step 4 : Undo change; Verify that the tree and table representation have the name stating. Verify that the undo
     * command next, has nothing to do with renaming.
     */
    public void testModifyTablePropertyViewStringField() {
        // Step 1 : open tree and table
        openTreeEditor();
        openTableEditor();

        // Change the cell content.
        renameCellContent(CLASS1, CLASS1_RENAME);

        // The refresh is automatically do in runtime but with SWTBot it's
        // necessary to do a manual refresh
        table.getTable().select(0, 0);
        table.save();
        manualRefreshTable();

        // Verify that the tree element is rename too
        checkTreeRename(CLASS1_RENAME);

        // Check there is only one undo for the rename
        checkUndoRenameAction();
    }

    /**
     * Test modify string field (name of semantic element) in property view of tree.
     * <P>
     * Step 1 : open a tree and table representations
     * <p>
     * Step 2 : change name of semantic element
     * <p>
     * Step 3 : Verify that the tree and table representation element have name modified
     * <p>
     * Step 4 : Undo change; Verify that the tree and table representation have the name stating. Verify that the undo
     * command next, has nothing to do with renaming.
     */
    public void testModifyTreePropertyViewStringField() {
        // Step 1 : open tree and table
        openTableEditor();
        openTreeEditor();

        // Change the cell content.
        renameTreeContent(CLASS1, CLASS1_RENAME);

        // The refresh is automatically do in runtime but with SWTBot it's
        // necessary to do a manual refresh
        tree.getTree().select(0, 0);
        tree.save();
        manualRefreshTree();

        // Verify that the tree element is rename too
        checkTableRename(CLASS1_RENAME);

        // Check there is only one undo for the rename
        checkUndoRenameAction();
    }

    /**
     * Test modify boolean field (field to pass semantic element to abstract) in property view of table.
     * <P>
     * Step 1 : open a tree and table representations
     * <p>
     * Step 2 : change type abstract of semantic element
     * <p>
     * Step 3 : Verify that the tree and table representation have abstract type modified
     * <p>
     * Step 4 : Undo change; Verify that the tree and table representation have the abstract stating. Verify that the
     * undo command next, has nothing to do with change abstract.
     */
    public void testModifyTablePropertyViewBooleanField() {
        // Step 1 : open tree and table
        openTreeEditor();
        openTableEditor();

        // Change the cell content.
        changeTypeToAbstract(CLASS1, table.getTable());

        // The refresh is automatically do in runtime but with SWTBot it's
        // necessary to do a manual refresh
        table.getTable().select(0, 0);
        table.save();
        bot.waitUntil(new SessionSavedCondition(localSession.getOpenedSession()));

        manualRefreshTable();

        // Verify that the tree element is abstract too
        checkElementIsAbstract(CLASS1, tree.getTree(), true);

        // Check there is only one undo for the rename
        checkUndoAbstractAction();
    }

    /**
     * Test modify boolean field (field to pass semantic element to abstract) in property view of tree.
     * <P>
     * Step 1 : open a tree and table representations
     * <p>
     * Step 2 : change type abstract of semantic element
     * <p>
     * Step 3 : Verify that the tree and table representation have abstract type modified
     * <p>
     * Step 4 : Undo change; Verify that the tree and table representation have the abstract stating. Verify that the
     * undo command next, has nothing to do with change abstract.
     */
    public void testModifyTreePropertyViewBooleanField() {
        // Step 1 : open tree and table
        openTreeEditor();
        openTableEditor();

        // Change the cell content.
        changeTypeToAbstract(CLASS1, tree.getTree());

        // The refresh is automatically do in runtime but with SWTBot it's
        // necessary to do a manual refresh
        tree.getTree().select(0, 0);
        tree.save();
        manualRefreshTree();

        // Verify that the tree element is abstract too
        checkElementIsAbstract(CLASS1, table.getTable(), true);

        // Check there is only one undo for the rename
        checkUndoAbstractAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, getFilesUsedForTest());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Request an explicit refresh of the current table.
     */
    private void manualRefreshTable() {
        bot.toolbarButtonWithTooltip(REFRESH_TABLE).click();
    }

    /**
     * Request an explicit refresh of the current tree.
     */
    private void manualRefreshTree() {
        bot.toolbarButtonWithTooltip(REFRESH_TREE).click();
    }

    /**
     * Rename table line content
     * 
     * @param content
     *            , the content of line
     * @param contentRename
     *            , the new content of line
     * 
     */
    private void renameCellContent(String content, String contentRename) {
        // Verify that the cell correspond to expected
        assertEquals("The cell not correspond to expected", content, table.getTable().cell(0, 0));
        table.getTable().getTreeItem(content).select();
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(SEMANTIC, propertiesBot.bot());
        SWTBotTree tree = propertiesBot.bot().tree();
        tree.expandNode(content).select().getNode("Name").doubleClick();
        SWTBotText text = propertiesBot.bot().text();
        text.setText(contentRename);
    }

    /**
     * Rename tree content
     * 
     * @param content
     *            , the content of line
     * @param contentRename
     *            , the new content of line
     * 
     */
    private void renameTreeContent(String content, String contentRename) {
        // Verify that the cell correspond to expected
        assertEquals("The cell not correspond to expected", content, tree.getTree().getTreeItem(content).getText());
        tree.getTree().getTreeItem(content).select();
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(SEMANTIC, propertiesBot.bot());
        SWTBotTree tree = propertiesBot.bot().tree();
        tree.expandNode(content).select().getNode("Name").doubleClick();
        SWTBotText text = propertiesBot.bot().text();
        text.setText(contentRename);
    }

    /**
     * Change type to abstract from properties view.
     * 
     * @param content
     *            , the name of semantic element to change in abstract
     * @param swtBotTree
     *            the swtBotTree
     */
    private void changeTypeToAbstract(String content, SWTBotTree swtBotTree) {
        swtBotTree.getTreeItem(content).select();
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(SEMANTIC, propertiesBot.bot());
        SWTBotTree tree = propertiesBot.bot().tree();
        tree.expandNode(content).select().getNode("Abstract").doubleClick();
        if (TestsUtil.isPhotonPlatformOrLater()) {
            SWTBotCheckBox checkBox = propertiesBot.bot().checkBox(0);
            checkBox.select();
        } else {
            SWTBotCCombo comboBox = propertiesBot.bot().ccomboBox(0);
            comboBox.setSelection(1);
        }
    }

    /**
     * Verify that tree element was renamed.
     * 
     * @param contentRename
     *            , the new tree element naming
     */
    private void checkTreeRename(String contentRename) {
        boolean treeRename = false;
        tree.getTree().setFocus();
        manualRefreshTree();
        for (SWTBotTreeItem treeItem : tree.getTree().getAllItems()) {
            if (contentRename.equals(treeItem.getText())) {
                treeRename = true;
                break;
            }
        }
        if (!treeRename) {
            fail("The tree must be renamed");
        }
    }

    /**
     * Verify that table element was renamed.
     * 
     * @param contentRename
     *            , the new table element naming
     */
    private void checkTableRename(String contentRename) {
        boolean treeRename = false;
        table.getTable().setFocus();
        manualRefreshTable();
        for (SWTBotTreeItem treeItem : table.getTable().getAllItems()) {
            if (contentRename.equals(treeItem.getText())) {
                treeRename = true;
                break;
            }
        }
        if (!treeRename) {
            fail("The table must be renamed");
        }
    }

    /**
     * Verify that element is abstract.
     * 
     * @param element
     *            the element who must be abstract
     * @param swtBotTree
     *            swtBotTree
     * @param isAbstract
     *            boolean to know if element is abstract or not
     */
    private void checkElementIsAbstract(String element, SWTBotTree swtBotTree, boolean isAbstract) {
        swtBotTree.getTreeItem(element).select();
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(SEMANTIC, propertiesBot.bot());
        SWTBotTree tree = propertiesBot.bot().tree();
        tree.expandNode(element).select().getNode("Abstract").doubleClick();
        boolean value;
        if (TestsUtil.isPhotonPlatformOrLater()) {
            value = propertiesBot.bot().checkBox().isChecked();
        } else {
            value = Boolean.parseBoolean(propertiesBot.bot().ccomboBox().getText());
        }
        assertEquals("The value of abstract field must be true", isAbstract, value);

    }

    /**
     * Verify that the undo for rename is present only one time
     */
    private void checkUndoRenameAction() {
        // There is 2 undo refresh because there is 2 manual refresh launched
        undo("Refresh representation");
        undo("Refresh representation");
        undo("Set Name");
        try {
            // This is the old command that was call before correct VP-1896
            undo("Update value by properties view");
            fail("The Undo Update value by properties view should not be present");
        } catch (WidgetNotFoundException wnfe) {
            checkTreeRename(CLASS1);
            checkTableRename(CLASS1);
        }
    }

    /**
     * Verify that the undo for abstract is present only one time
     */
    private void checkUndoAbstractAction() {
        undo("Refresh representation");
        undo("Set Abstract");
        try {
            undo("Update value by properties view");
            fail("The Undo Update value by properties view should not be present");
        } catch (WidgetNotFoundException wnfe) {
            checkElementIsAbstract(CLASS1, tree.getTree(), false);
            checkElementIsAbstract(CLASS1, table.getTable(), false);
        }
    }

    /**
     * Open the table editor
     */
    private void openTableEditor() {
        table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_TABLE_NAME)
                .selectRepresentationInstance(REPRESENTATION_TABLE_INSTANCE_NAME, UITableRepresentation.class).open();
    }

    /**
     * Open the tree editor
     */
    private void openTreeEditor() {
        tree = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_TREE_NAME)
                .selectRepresentationInstance(REPRESENTATION_TREE_INSTANCE_NAME, UITreeRepresentation.class).open();
    }

    /**
     * Return files used in the current test.
     */
    String[] getFilesUsedForTest() {
        return new String[] { VSM_FILE, ECORE_FILE, SESSION_FILE };
    }

}
