/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.business.internal.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.internal.metamodel.helper.TreeModelHelper;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeVariable;

/**
 * Utility methods to handle Tree models.
 * 
 * @author nlepine
 * 
 */
public final class TreeHelper {

    private TreeHelper() {

    }

    /**
     * return the {@link DTable} containing the element if there is one.
     * 
     * @param element
     *            any table element.
     * @return the {@link DTable} containing the element if there is one.
     */
    public static DTree getTree(final EObject element) {
        EObject container = element;
        while (container != null) {
            if (container instanceof DTree) {
                return (DTree) container;
            }
            container = container.eContainer();
        }
        return null;
    }

    /**
     * Get the expanded lines of this table.
     * 
     * @param tree
     *            The table
     * @return a list of DLine which are not collapsed
     */
    public static List<DTreeItem> getExpandedItems(final DTree tree) {
        final List<DTreeItem> result = new ArrayList<DTreeItem>();
        final EList<DTreeItem> items = tree.getOwnedTreeItems();
        for (final DTreeItem item : items) {
            if (item.isExpanded()) {
                result.add(item);
            }
            result.addAll(TreeHelper.getExpandedItems(item));
        }
        return result;
    }

    /**
     * Get the expanded lines of this line.
     * 
     * @param parentItem
     *            The parent line
     * @return a list of DLine which are not collapsed
     */
    public static List<DTreeItem> getExpandedItems(final DTreeItem parentItem) {
        final List<DTreeItem> result = new ArrayList<DTreeItem>();
        final EList<DTreeItem> items = parentItem.getOwnedTreeItems();
        for (final DTreeItem item : items) {
            if (item.isExpanded()) {
                result.add(item);
            }
            result.addAll(TreeHelper.getExpandedItems(item));
        }
        return result;
    }

    /**
     * Get the variable with the name in this tool.
     * 
     * @param tool
     *            The tool in which search the variable
     * @param name
     *            The variable name
     * @return The corresponding variable or null if not found
     */
    public static TreeVariable getVariable(final TreeItemTool tool, final String name) {
        return TreeModelHelper.getVariable(tool, name);
    }

}
