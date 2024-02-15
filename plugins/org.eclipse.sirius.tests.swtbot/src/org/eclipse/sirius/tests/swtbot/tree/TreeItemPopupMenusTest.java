/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests for ensuring that pop-up menu associated to TreeItemMappings are correctly integrated to the DTreeEditor.
 * 
 * @author alagarde
 */
public class TreeItemPopupMenusTest extends AbstractTreeSiriusSWTBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/tree/popupMenus/";

    private static final String MODEL_SPECIFICATION_FILE = "vp915.odesign";

    private static final String SESSION_FILE = "vp915.aird";

    private static final String MODEL_FILE = "vp915.ecore";

    private static final String FILE_DIR = "/";

    private static final String VIEWPOINT_NAME = "VP915";

    private static final String REPRESENTATION_NAME = "Tree With Contextual Menus";

    private static final String REPRESENTATION_INSTANCE_NAME = "contextMenu Tree";

    private static final String POP_UP_MENU_NAME = "Renamming...";

    private static final String OPERATION_ACTION_NAME = "from Operation";

    private static final String JAVA_ACTION_NAME = "MyJavaAction";

    private static final String JAVA_ACTION_CALL_NAME = "MyJavaAction-Call";

    private static final String TREE_ITEM_WITH_UNAPLICABLE_POPUP_CONTAINER_NAME = "NamedEntity";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_CONTAINER_NAME = "Employee";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_CALL_CONTAINER_NAME = "NamedEntity";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_NAME = "wage";

    private static final String TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME = "name";

    private static final String TREE_ITEM_WITH_UNAPLICABLE_POPUP_NAME = "wrongFeature";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * The Tree representation as UITreeRepresentation.
     */
    private UITreeRepresentation treeRepresentation;

    private SWTBotEditor editor;

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
     * Ensures that PopupMenu is not created if its precondition is not checked.
     */
    public void testPopupMenuWithFalsePrecondition() {
        // When getting the contextual menu of
        // "NamedEntity" Class -- "wrongFeature" attribute
        // The precondition value is false and the Pop-up Menu should
        // not be displayed
        boolean foundContextMenu = true;
        SWTBotTreeItem unaplicableNode = editor.bot().tree().getTreeItem(TREE_ITEM_WITH_UNAPLICABLE_POPUP_CONTAINER_NAME).expand().getNode(TREE_ITEM_WITH_UNAPLICABLE_POPUP_NAME);
        try {
            unaplicableNode.contextMenu(POP_UP_MENU_NAME);
        } catch (WidgetNotFoundException e) {
            foundContextMenu = false;
        }
        assertFalse("Pop-up Menu " + POP_UP_MENU_NAME + " should not be displayed on TreeITem " + TREE_ITEM_WITH_UNAPLICABLE_POPUP_NAME, foundContextMenu);

    }

    /**
     * Ensures that OperationActions with unchecked precondition are not displayed.
     */
    public void testPopupMenuWithOperationActionAndFalsePrecondition() {
        // When getting the contextual menu of
        // "NamedEntity" Class -- "name" attribute
        // The precondition value is false and the Operation Action action
        // should
        // not be displayed
        boolean foundContextMenu = true;
        final SWTBotTreeItem node = editor.bot().tree().getTreeItem(TREE_ITEM_WITH_JAVA_ACTION_CALL_CONTAINER_NAME).expand().getNode(TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME).select();
        // The test must be passed in the exception, so we deactivated the catch
        // of
        // "error log" view
        platformProblemsListener.setErrorCatchActive(false);
        try {
            node.contextMenu(OPERATION_ACTION_NAME);
        } catch (WidgetNotFoundException e) {
            foundContextMenu = false;
        }
        assertFalse("Operation Action " + OPERATION_ACTION_NAME + " should not be displayed on TreeITem " + TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME, foundContextMenu);
        platformProblemsListener.setErrorCatchActive(true);
    }

    /**
     * Ensures that ExternalJavaActions with unchecked precondition are not displayed.
     */
    public void testPopupMenuWithExternalJavaActionAndFalsePrecondition() {
        // When getting the contextual menu of
        // "NamedEntity" Class -- "name" attribute
        // The precondition value is false and the Java Action action should
        // not be displayed
        boolean foundContextMenu = true;
        final SWTBotTreeItem node = editor.bot().tree().getTreeItem(TREE_ITEM_WITH_JAVA_ACTION_CALL_CONTAINER_NAME).expand().getNode(TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME).select();
        // The test must be passed in the exception, so we deactivated the catch
        // of
        // "error log" view
        platformProblemsListener.setErrorCatchActive(false);
        try {
            node.contextMenu(JAVA_ACTION_NAME);
        } catch (WidgetNotFoundException e) {
            foundContextMenu = false;
        }
        assertFalse("Java Action " + JAVA_ACTION_NAME + " should not be displayed on TreeITem " + TREE_ITEM_WITH_JAVA_ACTION_CALL_NAME, foundContextMenu);
        platformProblemsListener.setErrorCatchActive(true);
    }

    /**
     * Ensures that ExternalJavaActionCalls with unchecked precondition are not displayed.
     */
    public void testPopupMenuWithExternalJavaActionCallAndFalsePrecondition() {
        // When getting the contextual menu of
        // "Employee" Class -- "wage" attribute
        // The precondition value is false and the Java Action Call action
        // should
        // not be displayed
        boolean foundContextMenu = true;
        final SWTBotTreeItem node = editor.bot().tree().getTreeItem(TREE_ITEM_WITH_JAVA_ACTION_CONTAINER_NAME).expand().getNode(TREE_ITEM_WITH_JAVA_ACTION_NAME).select();
        // The test must be passed in the exception, so we deactivated the catch
        // of
        // "error log" view
        platformProblemsListener.setErrorCatchActive(false);
        try {
            node.contextMenu(JAVA_ACTION_CALL_NAME);
        } catch (WidgetNotFoundException e) {
            foundContextMenu = false;
        }
        assertFalse("Java Action Call  " + JAVA_ACTION_CALL_NAME + " should not be displayed on TreeITem " + TREE_ITEM_WITH_JAVA_ACTION_NAME, foundContextMenu);
        platformProblemsListener.setErrorCatchActive(true);
    }

}
