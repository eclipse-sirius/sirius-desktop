/*******************************************************************************
 * Copyright (c) 2022 Obeo.
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

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.DefaultPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors.StandalonePermissionProviderDescriptor;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemExpanded;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.tree.ui.tools.internal.editor.SiriusDTreeCellNavigationStrategy;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.IntResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

/**
 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=549352 .
 * 
 * @author Laurent Redor</a>
 */
public class TreeExpandFoldWithKeyTests extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/navigation/";//$NON-NLS-1$

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "tree.odesign";//$NON-NLS-1$

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "tree.aird";//$NON-NLS-1$

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "tree.ecore";//$NON-NLS-1$

    private SWTBotEditor treeEditorBot;

    private UITreeRepresentation treeRepresentation;

    private DTree dTree;

    private PermissionProviderDescriptor permissionProviderDescriptor;

    private ReadOnlyPermissionAuthority readOnlyPermissionAuthority;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, SESSION_RESOURCE_FILE, SEMANTIC_RESOURCE_FILE);

        initCustomPermissionAuthority();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE);//$NON-NLS-1$
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();

        treeRepresentation = openEditor(localSession, EcoreModeler.DESIGN_VIEWPOINT_NAME, "Tree", "new Tree");//$NON-NLS-1$ //$NON-NLS-2$
        treeEditorBot = treeRepresentation.getEditor();
        DTreeEditor dTreeEditor = (DTreeEditor) treeEditorBot.getReference().getEditor(false);
        dTree = (DTree) dTreeEditor.getRepresentation();

    }

    @Override
    protected void tearDown() throws Exception {
        PermissionService.removeExtension(permissionProviderDescriptor);
        permissionProviderDescriptor = null;
        readOnlyPermissionAuthority = null;

        treeEditorBot.close();
        treeEditorBot = null;
        treeRepresentation = null;
        dTree = null;

        super.tearDown();
    }

    /**
     * Test expand and collapse of a tree item with the right/left arrow keys.
     */
    public void testExpandAndCollapseWithKeyboard() {
        DTreeItem p4DTreeItem = dTree.getOwnedTreeItems().get(3);
        String p4Name = "\"" + p4DTreeItem.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        SWTBotTreeItem p4SwtBotTreeItem = treeEditorBot.bot().tree().getAllItems()[3];
        p4SwtBotTreeItem.select();

        assertFalse("Wrong initial expanded status for graphical TreeItem " + p4Name + ", before expanding with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong initial expanded status for Sirius DTreeItem " + p4Name + ", before expanding with keyboard.", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of graphical TreeItem " + p4Name + ", before expanding with keyboard.", 1, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of Sirius DTreeItem " + p4Name + ", before expanding with keyboard.", 1, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$


        expandWithArrowKey(treeRepresentation.getTree(), p4SwtBotTreeItem, p4Name);

        assertTrue("Wrong expanded status for graphical TreeItem " + p4Name + " after expanding with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertTrue("Wrong expanded status for Sirius DTreeItem " + p4Name + " after expanding with keyboard", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + p4Name + " after expanding with keyboard.", 2, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + p4Name + " after expanding with keyboard.", 2, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$

        collapseWithArrowKey(treeRepresentation.getTree(), p4SwtBotTreeItem, p4Name);

        assertFalse("Wrong expanded status for graphical TreeItem " + p4Name + " after collapsing with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong expanded status for Sirius DTreeItem " + p4Name + " after collapsing with keyboard", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + p4Name + " after collapsing with keyboard.", 2, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + p4Name + " after collapsing with keyboard.", 2, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Test expand all and collapse all of a tree item with the right/left arrow keys + Shift key pressed.
     */
    public void testExpandAndCollapseAllWithKeyboard() {
        testExpandAndCollapseAllWithKeyboard(false);
    }

    /**
     * Test expand all and collapse all of a tree item with the right/left arrow keys + Shift key pressed.
     */
    public void testExpandAndCollapseAllWithKeyboardWithSpecificDepthLimit() {
        testExpandAndCollapseAllWithKeyboard(true);
    }

    /**
     * Test expand all and collapse all of a tree item with the right/left arrow keys + Shift key pressed.
     * 
     * @param applySpecificDepthLimit
     *            True to apply a specific depth limit or false to have the default depth limit of {@link SiriusDTreeCellNavigationStrategy}.
     */
    private void testExpandAndCollapseAllWithKeyboard(boolean applySpecificDepthLimit) {
        if (applySpecificDepthLimit) {
            SiriusDTreeCellNavigationStrategy.setExpandDepthLimit(2);
        }
        DTreeItem p4DTreeItem = dTree.getOwnedTreeItems().get(3);
        String p4Name = "\"" + p4DTreeItem.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        SWTBotTreeItem p4SwtBotTreeItem = treeEditorBot.bot().tree().getAllItems()[3];
        p4SwtBotTreeItem.select();

        assertFalse("Wrong initial expanded status for graphical TreeItem " + p4Name + ", before expanding with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong initial expanded status for Sirius DTreeItem " + p4Name + ", before expanding with keyboard.", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of graphical TreeItem " + p4Name + ", before expanding with keyboard.", 1, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of Sirius DTreeItem " + p4Name + ", before expanding with keyboard.", 1, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$

        expandAllWithArrowKey(treeRepresentation.getTree(), p4SwtBotTreeItem, p4Name, SWTBotPreferences.TIMEOUT);

        assertTrue("Wrong expanded status for graphical TreeItem " + p4Name + " after expanding with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertTrue("Wrong expanded status for Sirius DTreeItem " + p4Name + " after expanding with keyboard", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + p4Name + " after expanding with keyboard.", 2, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + p4Name + " after expanding with keyboard.", 2, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$
        String eClass1P4Name = "EClass 1 P4";//$NON-NLS-1$
        SWTBotTreeItem eClass1P4SwtBotTreeItem = p4SwtBotTreeItem.getItems()[0];
        DTreeItem eClass1P4DTreeItem = p4DTreeItem.getOwnedTreeItems().get(0);
        assertTrue("Wrong expanded status for graphical TreeItem " + eClass1P4Name + " after expanding with keyboard.", isExpanded(eClass1P4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertTrue("Wrong expanded status for Sirius DTreeItem " + eClass1P4Name + " after expanding with keyboard", eClass1P4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + eClass1P4Name + " after expanding with keyboard.", 1, getItemCount(eClass1P4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + eClass1P4Name + " after expanding with keyboard.", 1, eClass1P4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$
        String eClass1P4CategorieName = "EClass 1 P4 Categorie";//$NON-NLS-1$
        SWTBotTreeItem eClass1P4CategorieSwtBotTreeItem = eClass1P4SwtBotTreeItem.getItems()[0];
        DTreeItem eClass1P4CategorieDTreeItem = eClass1P4DTreeItem.getOwnedTreeItems().get(0);
        assertEquals("Wrong expanded status for graphical TreeItem " + eClass1P4CategorieName + " after expanding with keyboard.", !applySpecificDepthLimit, //$NON-NLS-1$ //$NON-NLS-2$
                isExpanded(eClass1P4CategorieSwtBotTreeItem.widget));
        assertEquals("Wrong expanded status for Sirius DTreeItem " + eClass1P4CategorieName + " after expanding with keyboard", !applySpecificDepthLimit, eClass1P4CategorieDTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + eClass1P4CategorieName + " after expanding with keyboard.", 1, getItemCount(eClass1P4CategorieSwtBotTreeItem.widget)); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + eClass1P4CategorieName + " after expanding with keyboard.", 1, eClass1P4CategorieDTreeItem.getOwnedTreeItems().size()); //$NON-NLS-1$ //$NON-NLS-2$

        collapseAllWithArrowKey(treeRepresentation.getTree(), p4SwtBotTreeItem, p4Name, SWTBotPreferences.TIMEOUT);

        assertFalse("Wrong expanded status for graphical TreeItem " + p4Name + " after collapsing with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong expanded status for Sirius DTreeItem " + p4Name + " after collapsing with keyboard", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + p4Name + " after collapsing with keyboard.", 2, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + p4Name + " after collapsing with keyboard.", 2, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$

        assertFalse("Wrong expanded status for graphical TreeItem " + eClass1P4Name + " after collapsing with keyboard.", isExpanded(eClass1P4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong expanded status for Sirius DTreeItem " + eClass1P4Name + " after collapsing with keyboard", eClass1P4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + eClass1P4Name + " after collapsing with keyboard.", 1, getItemCount(eClass1P4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + eClass1P4Name + " after collapsing with keyboard.", 1, eClass1P4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$

        assertFalse("Wrong expanded status for graphical TreeItem " + eClass1P4CategorieName + " after collapsing with keyboard.", isExpanded(eClass1P4CategorieSwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong expanded status for Sirius DTreeItem " + eClass1P4CategorieName + " after collapsing with keyboard", eClass1P4CategorieDTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + eClass1P4CategorieName + " after collapsing with keyboard.", 1, getItemCount(eClass1P4CategorieSwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + eClass1P4CategorieName + " after collapsing with keyboard.", 1, eClass1P4CategorieDTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Test expand and collapse of a tree item with the right/left arrow keys when the permission authority forbid them.
     * 
     * @throws TimeoutException
     */
    public void testExpandAndCollapseWithKeyboardWhenPermissionAuthorityForbidThem() {
        readOnlyPermissionAuthority.activate();

        DTreeItem p4DTreeItem = dTree.getOwnedTreeItems().get(3);
        String p4Name = "\"" + p4DTreeItem.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        SWTBotTreeItem p4SwtBotTreeItem = treeEditorBot.bot().tree().getAllItems()[3];
        p4SwtBotTreeItem.select();

        assertFalse("Wrong initial expanded status for graphical TreeItem " + p4Name + ", before expanding with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong initial expanded status for Sirius DTreeItem " + p4Name + ", before expanding with keyboard.", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of graphical TreeItem " + p4Name + ", before expanding with keyboard.", 1, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of Sirius DTreeItem " + p4Name + ", before expanding with keyboard.", 1, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$

        try {
            expandWithArrowKey(treeRepresentation.getTree(), p4SwtBotTreeItem, p4Name, 2000);
        } catch (TimeoutException e) {
            if (!e.getMessage().contains(TreeItemExpanded.getFailureMessage(true, p4Name))) {
                // Throw the TimeoutException only if the message is not the expected one. Indeed, with the read only
                // permission authority activated, it is expected that the expand is not possible.
                throw e;
            }
        }

        assertFalse("Wrong expanded status for graphical TreeItem " + p4Name + " after expanding with keyboard.", isExpanded(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertFalse("Wrong expanded status for Sirius DTreeItem " + p4Name + " after expanding with keyboard", p4DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + p4Name + " after expanding with keyboard.", 1, getItemCount(p4SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + p4Name + " after expanding with keyboard.", 1, p4DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$

        DTreeItem p3DTreeItem = dTree.getOwnedTreeItems().get(2);
        String p3Name = "\"" + p3DTreeItem.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$
        SWTBotTreeItem p3SwtBotTreeItem = treeEditorBot.bot().tree().getAllItems()[2];
        p3SwtBotTreeItem.select();

        assertTrue("Wrong initial expanded status for graphical TreeItem " + p3Name + ", before collapsing with keyboard.", isExpanded(p3SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertTrue("Wrong initial expanded status for Sirius DTreeItem " + p3Name + ", before collapsing with keyboard.", p3DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of graphical TreeItem " + p3Name + ", before collapsing with keyboard.", 2, getItemCount(p3SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong initial children number of Sirius DTreeItem " + p3Name + ", before collapsing with keyboard.", 2, p3DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$

        try {
            collapseWithArrowKey(treeRepresentation.getTree(), p3SwtBotTreeItem, p3Name, 2000);
        } catch (TimeoutException e) {
            if (!e.getMessage().contains(TreeItemExpanded.getFailureMessage(false, p3Name))) {
                // Throw the TimeoutException only if the message is not the expected one. Indeed, with the read only
                // permission authority activated, it is expected that the expand is not possible.
                throw e;
            }
        }
        assertTrue("Wrong expanded status for graphical TreeItem " + p3Name + ", after collapsing with keyboard.", isExpanded(p3SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertTrue("Wrong expanded status for Sirius DTreeItem " + p3Name + ", after collapsing with keyboard.", p3DTreeItem.isExpanded());//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of graphical TreeItem " + p3Name + ", after collapsing with keyboard.", 2, getItemCount(p3SwtBotTreeItem.widget));//$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wrong children number of Sirius DTreeItem " + p3Name + ", after collapsing with keyboard.", 2, p3DTreeItem.getOwnedTreeItems().size());//$NON-NLS-1$ //$NON-NLS-2$
    }

    private boolean isExpanded(final TreeItem treeItem) {
        return syncExec(new BoolResult() {
            @Override
            public Boolean run() {
                return treeItem.getExpanded();
            }
        });
    }

    private int getItemCount(final TreeItem treeItem) {
        return syncExec(new IntResult() {

            @Override
            public Integer run() {
                return treeItem.getItemCount();
            }
        });
    }

    private void expandWithArrowKey(final SWTBotTree swtBotTree, final SWTBotTreeItem treeItemToExpand, final String treeItemToExpandName) {
        expandWithArrowKey(swtBotTree, treeItemToExpand, treeItemToExpandName, SWTBotPreferences.TIMEOUT);
    }

    private void expandWithArrowKey(final SWTBotTree swtBotTree, final SWTBotTreeItem treeItemToExpand, final String treeItemToExpandName, long timeout) {
        ICondition condition = new TreeItemExpanded(treeItemToExpand, treeItemToExpandName);
        SWTBotUtils.pressKeyboardKey(swtBotTree.widget, SWT.ARROW_RIGHT);
        bot.waitUntil(condition, timeout);
    }

    private void expandAllWithArrowKey(final SWTBotTree swtBotTree, final SWTBotTreeItem treeItemToExpand, final String treeItemToExpandName, long timeout) {
        ICondition condition = new TreeItemExpanded(treeItemToExpand, treeItemToExpandName);
        SWTBotUtils.pressKeyboardKey(swtBotTree.widget, SWT.SHIFT, (char) SWT.None, SWT.ARROW_RIGHT);
        bot.waitUntil(condition, timeout);
    }

    private void collapseWithArrowKey(final SWTBotTree swtBotTree, final SWTBotTreeItem treeItemToExpand, final String treeItemToExpandName) {
        collapseWithArrowKey(swtBotTree, treeItemToExpand, treeItemToExpandName, SWTBotPreferences.TIMEOUT);
    }

    private void collapseWithArrowKey(final SWTBotTree swtBotTree, final SWTBotTreeItem treeItemToExpand, final String treeItemToExpandName, long timeout) {
        ICondition condition = new TreeItemExpanded(treeItemToExpand, treeItemToExpandName, false);
        SWTBotUtils.pressKeyboardKey(swtBotTree.widget, SWT.ARROW_LEFT);
        bot.waitUntil(condition, timeout);
    }

    private void collapseAllWithArrowKey(final SWTBotTree swtBotTree, final SWTBotTreeItem treeItemToExpand, final String treeItemToExpandName, long timeout) {
        ICondition condition = new TreeItemExpanded(treeItemToExpand, treeItemToExpandName, false);
        SWTBotUtils.pressKeyboardKey(swtBotTree.widget, SWT.SHIFT, (char) SWT.None, SWT.ARROW_LEFT);
        bot.waitUntil(condition, timeout);
    }

    /**
     * Init Sirius with a {@link ReadOnlyPermissionAuthority}, disabled by default.
     */
    private void initCustomPermissionAuthority() {
        readOnlyPermissionAuthority = new ReadOnlyPermissionAuthority();
        IPermissionProvider permissionProvider = new DefaultPermissionProvider(readOnlyPermissionAuthority);
        permissionProviderDescriptor = new StandalonePermissionProviderDescriptor("org.eclipse.sirius.tree.tests.forbiddenPermissionAuthorityProvider", ExtenderConstants.HIGHEST_PRIORITY, //$NON-NLS-1$
                permissionProvider);
        PermissionService.addExtension(permissionProviderDescriptor);
    }
}
