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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemTextCondition;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test the refresh when modifying the values in property view
 * 
 * @author nlepine
 */
public class RefreshWithPropertiesViewTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /**
     * 
     */
    private static final String NAME = "Name";

    /**
     * 
     */
    private static final String NEW_E_CLASS_2 = "new EClass 2";

    /**
     * 
     */
    private static final String NEW_E_CLASS_1 = "new EClass 1";

    /**
     * Sirius Specific Model.
     */
    private static final String VSM = "ecore.odesign";

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
     * Test modifying the properties view refresh the tree in manual refresh.
     */
    public void testStyleDescriptionManualRefresh() {
        disableAutoRefresh();
        changePropertiesView();
    }

    /**
     * open the editor and change the property view
     */
    private void changePropertiesView() {
        // Open editor
        treeRepresentation = openEditor(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME);

        editor = treeRepresentation.getEditor();

        editor.save();

        editor.setFocus();

        // expand the tree : Tree Item Style Description 8
        SWTBotTree tree = editor.bot().tree();
        SWTBotTreeItem item = tree.expandNode(NEW_E_CLASS_1).select();

        checkpropertiesview(item);

        // undo redo
        editor.setFocus();
        undo("Set Name");
        bot.waitUntil(new TreeItemTextCondition(item, NEW_E_CLASS_1));

        redo("Set Name");
        bot.waitUntil(new TreeItemTextCondition(item, NEW_E_CLASS_2));

        localSession.save();
        localSession.closeNoDirty();
    }

    /**
     * @param item
     */
    private void checkpropertiesview(SWTBotTreeItem item) {
        TreeItem widgetEnum = item.widget;
        assertNotNull("The tree item for the class is null", widgetEnum);

        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotTree tree2 = propertiesViewBot.tree();
        propertiesView.setFocus();

        /* Change the name of the package by properties */
        SWTBotTreeItem packageName = tree2.getTreeItem(NEW_E_CLASS_1).getNode(NAME);
        packageName.select();
        packageName.click();
        propertiesViewBot.text().setText(NEW_E_CLASS_2);
        SWTBotTreeItem treeItem = treeRepresentation.getTree().getAllItems()[0];
        treeRepresentation.getTree().getTreeItem(treeItem.getText()).select().click();
        tree2 = propertiesViewBot.tree();
        packageName = tree2.getTreeItem(NEW_E_CLASS_2).getNode(NAME);
        packageName.click();
        bot.waitUntil(new TreeItemTextCondition(item, NEW_E_CLASS_2));
    }

    /**
     * Test modifying the properties view refresh the tree in manual refresh.
     */
    public void testStyleDescriptionAutoRefresh() {
        disableAutoRefresh();
        enableAutoRefresh();
        changePropertiesView();
    }

}
