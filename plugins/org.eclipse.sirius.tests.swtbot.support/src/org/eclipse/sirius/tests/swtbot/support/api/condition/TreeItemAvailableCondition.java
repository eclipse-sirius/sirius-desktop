/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Condition on tree item availability status.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class TreeItemAvailableCondition extends DefaultCondition {

    private SWTBotTree tree;

    private SWTBotTreeItem treeItem;

    private final String treeItemName;

    private final boolean expectedToBeFound;

    /**
     * Default constructor.
     * 
     * @param tree
     *            the parent tree
     * @param treeItemName
     *            the tree item name that we are looking for availability
     * @param expectedToBeFound
     *            the expected availability status
     */
    public TreeItemAvailableCondition(SWTBotTree tree, String treeItemName, boolean expectedToBeFound) {
        this.tree = tree;
        this.treeItemName = treeItemName;
        this.expectedToBeFound = expectedToBeFound;
    }

    /**
     * Default constructor.
     * 
     * @param treeItem
     *            the parent tree item
     * @param treeItemName
     *            the tree item name that we are looking for availability
     * @param expectedToBeFound
     *            the expected availability status
     */
    public TreeItemAvailableCondition(SWTBotTreeItem treeItem, String treeItemName, boolean expectedToBeFound) {
        this.treeItem = treeItem;
        this.treeItemName = treeItemName;
        this.expectedToBeFound = expectedToBeFound;
    }

    @Override
    public String getFailureMessage() {
        return "The tree item " + treeItemName + " has not been found";
    }

    @Override
    public boolean test() throws Exception {
        try {
            if (tree != null) {
                tree.getTreeItem(treeItemName);
            } else {
                treeItem.getNode(treeItemName);
            }
            return expectedToBeFound;
        } catch (WidgetNotFoundException e) {
            return !expectedToBeFound;
        }
    }

}
