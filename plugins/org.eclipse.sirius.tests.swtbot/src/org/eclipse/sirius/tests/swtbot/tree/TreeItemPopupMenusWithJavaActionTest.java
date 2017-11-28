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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import junit.framework.AssertionFailedError;

/**
 * Tests for ensuring that pop-up menu associated to TreeItemMappings are
 * correctly integrated to the DTreeEditor.
 * 
 * @author alagarde
 */
public class TreeItemPopupMenusWithJavaActionTest extends AbstractTreeSiriusSWTBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/tree/popupMenus/";

    private static final String MODEL_SPECIFICATION_FILE = "vp915.odesign";

    private static final String SESSION_FILE = "vp915.aird";

    private static final String MODEL_FILE = "vp915.ecore";

    private static final String FILE_DIR = "/";

    private static final String VIEWPOINT_NAME = "VP915";

    private static final String REPRESENTATION_NAME = "Tree With Contextual Menus";

    private static final String REPRESENTATION_INSTANCE_NAME = "contextMenu Tree";

    private static final String OPERATION_ACTION_IMAGE_PATH = "/org.eclipse.sirius.sample.ecore.design/icons/full/obj16/DynamicInstance.gif";

    private static final String JAVA_ACTION_IMAGE_PATH = "/org.eclipse.sirius.sample.ecore.design/icons/full/obj16/Role.gif";

    private static final String JAVA_ACTION_CALL_IMAGE_PATH = "/org.eclipse.sirius.sample.ecore.design/icons/full/obj16/Thing.gif";

    private static final String OPERATION_ACTION_NAME = "from Operation";

    private static final String JAVA_ACTION_NAME = "MyJavaAction";

    private static final String JAVA_ACTION_CALL_NAME = "MyJavaAction-Call";

    private static final String TREE_ITEM_WITH_OPERATION_ACTION_CONTAINER_NAME = "Employee";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_CONTAINER_NAME = "Employee";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_CALL_CONTAINER_NAME = "NamedEntity";

    private static final String TREE_ITEM_WITH_OPERATION_ACTION_NAME = "wage";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_NAME = "wage";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME = "name";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * The Tree representation as UITreeRepresentation.
     */
    private UITreeRepresentation treeRepresentation;

    private SWTBotEditor editor;

    private Image operationActionImage;

    private Image javaActionImage;

    private Image javaActionCallImage;

    private long swtBotTimeOutOldValue;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // Decreasing SWTBot's timeout to enhance performances
        swtBotTimeOutOldValue = SWTBotPreferences.TIMEOUT;
        SWTBotPreferences.TIMEOUT = 1000;
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_SPECIFICATION_FILE, SESSION_FILE, MODEL_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        editor = treeRepresentation.getEditor();

        operationActionImage = TreeUIPlugin.getImage(TreeUIPlugin.findImageDescriptor(OPERATION_ACTION_IMAGE_PATH));
        javaActionImage = TreeUIPlugin.getImage(TreeUIPlugin.findImageDescriptor(JAVA_ACTION_IMAGE_PATH));
        javaActionCallImage = TreeUIPlugin.getImage(TreeUIPlugin.findImageDescriptor(JAVA_ACTION_CALL_IMAGE_PATH));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        SWTBotPreferences.TIMEOUT = swtBotTimeOutOldValue;
    }

    /**
     * Ensures that OperationActions defined inside a PopupMenu are correctly
     * integrated to the editor.
     * <p>
     * Also checks that OperationActions with unchecked precondition are not
     * displayed
     * </p>
     */
    public void testPopupMenuWithOperationAction() {
        // Step 1 : getting the contextual menu of the
        // "Employee" Class -- "wage" attribute

        SWTBotTreeItem node = editor.bot().tree().getTreeItem(TREE_ITEM_WITH_OPERATION_ACTION_CONTAINER_NAME).expand().getNode(TREE_ITEM_WITH_OPERATION_ACTION_NAME);
        String oldNodeName = node.getText();
        SWTBotMenu contextMenu = node.contextMenu(OPERATION_ACTION_NAME);

        // Step 2 : ensure that the right image is displayed
        checkContextMenuIcon(contextMenu, operationActionImage);
        SWTBotUtils.clickContextMenu(node, OPERATION_ACTION_NAME);

        SWTBotUtils.waitAllUiEvents();
        // Step 2 : ensuring that the operation has correctly been applied
        assertTrue("Operation Action was not correctly applied on " + TREE_ITEM_WITH_OPERATION_ACTION_NAME, node.getText().equals(oldNodeName + "-RENAMMED"));

        // Undo test
        bot.menu("Edit").menu("Undo " + OPERATION_ACTION_NAME + " on " + TREE_ITEM_WITH_OPERATION_ACTION_NAME).click();
        assertFalse("Operation Action was not correctly undone on " + TREE_ITEM_WITH_OPERATION_ACTION_NAME, node.getText().equals(oldNodeName + "-RENAMMED"));
    }

    /**
     * Ensures that ExternalJavaActions defined inside a PopupMenu are correctly
     * integrated to the editor.
     */
    public void testPopupMenuWithExternalJavaAction() {
        // Step 1
        // Step 1 : getting the contextual menu of the
        // "Employee" Class -- "wage" attribute

        SWTBotTreeItem node = editor.bot().tree().getTreeItem(TREE_ITEM_WITH_JAVA_ACTION_CONTAINER_NAME).expand().getNode(TREE_ITEM_WITH_JAVA_ACTION_NAME);
        String oldNodeName = node.getText();
        SWTBotMenu contextMenu = node.contextMenu(JAVA_ACTION_NAME);

        // Step 2 : ensure that the right image is displayed
        checkContextMenuIcon(contextMenu, javaActionImage);
        SWTBotUtils.clickContextMenu(node, JAVA_ACTION_NAME);

        SWTBotUtils.waitAllUiEvents();
        // Step 2 : ensuring that the operation has correctly been applied
        assertTrue("Java Action was not correctly applied on " + TREE_ITEM_WITH_JAVA_ACTION_NAME, node.getText().equals(oldNodeName + "_RENAMMED"));

        // Undo Test
        bot.menu("Edit").menu("Undo " + TREE_ITEM_WITH_JAVA_ACTION_NAME).click();
        assertFalse("Java Action was not correctly undone on " + TREE_ITEM_WITH_JAVA_ACTION_NAME, node.getText().equals(oldNodeName + "_RENAMMED"));

    }

    /**
     * Ensures that ExternalJavaActionCalls defined inside a PopupMenu are
     * correctly integrated to the editor.
     */
    public void testPopupMenuWithExternalJavaCallAction() {
        // Step 1
        // Step 1 : getting the contextual menu of the
        // "Employee" Class -- "wage" attribute

        SWTBotTreeItem node = editor.bot().tree().getTreeItem(TREE_ITEM_WITH_JAVA_ACTION_CALL_CONTAINER_NAME).expand().getNode(TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME);
        String oldNodeName = node.getText();
        SWTBotMenu contextMenu = node.contextMenu(JAVA_ACTION_CALL_NAME);

        // Step 2 : ensure that the right image is displayed
        checkContextMenuIcon(contextMenu, javaActionCallImage);
        SWTBotUtils.clickContextMenu(node, JAVA_ACTION_CALL_NAME);

        SWTBotUtils.waitAllUiEvents();
        // Step 2 : ensuring that the operation has correctly been applied
        assertTrue("Java Action was not correctly applied on " + TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME, node.getText().equals(oldNodeName + "_RENAMMED"));

        // Undo Test
        bot.menu("Edit").menu("Undo " + TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME).click();
        assertFalse("Java Action was not correctly undone on " + TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME, node.getText().equals(oldNodeName + "_RENAMMED"));

    }

    /**
     * Ensures that the given contextMenu has the excepted icon.
     * 
     * @param contextMenu
     *            the context menu to test
     * @param expectedIcon
     *            the expected icon
     */
    private void checkContextMenuIcon(final SWTBotMenu contextMenu, final Image expectedIcon) {
        try {
            contextMenu.display.syncExec(new Runnable() {
                public void run() {
                    assertEquals("Wrong Image for the Operation Action", expectedIcon.type, contextMenu.widget.getImage().type);
                }
            });
        } catch (SWTException e) {
            throw new AssertionFailedError(e.getMessage());
        }

    }
}
