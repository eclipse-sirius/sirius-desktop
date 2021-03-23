/**
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.business;

import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemContainsAtLeastOneChild;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemExpanded;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemSelected;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

/**
 * Object to manage graphical projects.
 * 
 * @author dlecan
 */
public class UIProject {

    private final String name;

    private final SWTWorkbenchBot bot;

    /**
     * Constructor.
     * 
     * @param pName
     *            Project name
     */
    public UIProject(final String pName) {
        bot = new SWTWorkbenchBot();
        name = pName;

    }

    /**
     * Open the project.
     */
    public void open() {
        SWTBot projectExplorerBot = bot.viewById(IModelExplorerView.ID).bot();
        SWTBotTree projectExplorerTree = projectExplorerBot.tree();
        projectExplorerTree.setFocus();
        SWTBotTreeItem treeItem = projectExplorerTree.getTreeItem(getName());
        treeItem.select();
        bot.waitUntil(new TreeItemSelected(treeItem));
        SWTBotUtils.clickContextMenu(treeItem, "Open Project");
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Delete resource.
     * 
     * @param resource
     *            Path to resource to delete (with / separator)
     * @return Current {@link UIProject}
     */
    public UIProject deleteResource(final UIResource resource) {
        SWTBotTreeItem treeItem = getProjectTreeItem();

        try {

            for (final String node : resource.getNodePath()) {
                treeItem = treeItem.expandNode(node);
            }

            treeItem.setFocus();
            treeItem.select().contextMenu("Delete").click();
            final SWTBotShell shell = bot.shell("Delete Resources");
            shell.activate();
            bot.button("OK").click();
            bot.waitUntil(Conditions.shellCloses(shell));
            bot.sleep(500);
        } catch (final TimeoutException e) {
            // do nothing
        }

        return this;
    }

    /**
     * Get the {@link SWTBotTreeItem} for the project.
     * 
     * @return the {@link SWTBotTreeItem} for the project
     */
    public SWTBotTreeItem getProjectTreeItem() {
        final SWTBot projectExplorerBot = bot.viewById(IModelExplorerView.ID).bot();

        final SWTBotTree projectExplorerTree = projectExplorerBot.tree();
        SWTBotTreeItem[] allItems = projectExplorerTree.getAllItems();
        SWTBotTreeItem treeItem = null;

        // find the root project tree item considering the fact that the project may be dirty
        String projectName = getName();
        String dirtyProjectName = "*" + projectName;
        for (SWTBotTreeItem swtBotTreeItem : allItems) {
            String text = swtBotTreeItem.getText();
            if (projectName.equals(text) || dirtyProjectName.equals(text)) {
                treeItem = swtBotTreeItem;
                break;
            }
        }
        if (treeItem == null) {
            throw new WidgetNotFoundException("The project " + getName() + " is not found");
        }

        TreeItemExpanded treeItemExpanded = new TreeItemExpanded(treeItem, getName());
        treeItem.expand();

        bot.waitUntil(treeItemExpanded);

        return treeItem;
    }

    /**
     * Select a UI resource.
     * 
     * @param uiResource
     *            UI resource to select.
     * @return Current {@link UIProject}.
     */
    public UIProject selectResource(final UIResource uiResource) {

        final SWTBotTreeItem treeItem = getUIItemFromResource(uiResource);
        treeItem.setFocus();
        treeItem.select();
        bot.waitUntil(new TreeItemSelected(treeItem));
        return this;
    }

    /**
     * Return ui tree item corresponding to a UI resource in a project.
     * 
     * @param uiResource
     *            UI resource to select.
     * @return UI tree item.
     */
    public SWTBotTreeItem getUIItemFromResource(final UIResource uiResource) {
        SWTBotTreeItem treeItem = getProjectTreeItem();

        for (final String node : uiResource.getNodePath()) {
            treeItem = treeItem.expandNode(node);
            bot.waitUntil(new TreeItemExpanded(treeItem, node));

        }
        // Wait that the parent contains at least one child (indeed sometimes,
        // the TreeItemExpanded is OK but the tree is not really expanded).
        bot.waitUntil(new TreeItemContainsAtLeastOneChild(treeItem));
        try {
            treeItem = treeItem.getNode(uiResource.getName());
        } catch (WidgetNotFoundException e) {
            // It should be here (just wait 2 seconds)
            bot.sleep(2000);
            treeItem = treeItem.getNode(uiResource.getName());
        }

        treeItem.setFocus();
        treeItem.select();
        bot.waitUntil(new TreeItemSelected(treeItem));
        return treeItem;
    }

    /**
     * Double-click on a resource.
     * 
     * @param uiResource
     *            Resource where to double-click.
     * @param contextualMenuLabel
     *            Contextual menu label.
     * @return Current {@link UIProject}.
     */
    public UIProject mouseRigthClickOnResource(final UIResource uiResource, String contextualMenuLabel) {
        final SWTBotTreeItem treeItem = getUIItemFromResource(uiResource);
        SWTBotUtils.clickContextMenu(treeItem, contextualMenuLabel);
        return this;
    }

    /**
     * Double-click on a resource.
     * 
     * @param uiResource
     *            Resource where to double-click.
     * @return Cuurent {@link UIProject}.
     */
    public UIProject mouseDoubleClickOnResource(final UIResource uiResource) {
        final SWTBotTreeItem treeItem = getUIItemFromResource(uiResource);
        treeItem.doubleClick();
        return this;
    }

    /**
     * Returns the name.
     * 
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Convert the current project to a Modeling Project using the configure contextual menu.
     */
    public void convertToModelingProject() {
        getProjectTreeItem().select();
        SWTBotUtils.clickContextMenu(getProjectTreeItem(), "Convert to Modeling Project");
    }

    /**
     * Save the Modeling Project.
     */
    public void save() {
        SWTBotTreeItem treeItem = getProjectTreeItem();
        SWTBotUtils.clickContextMenu(treeItem, "Save");
    }

    /**
     * Select the project.
     */
    public void select() {
        getProjectTreeItem().select();
    }
}
