/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.business.api.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Utility methods to handle Tree models.
 * 
 * @author jdupont
 */
public final class TreeUIHelper {

    private TreeUIHelper() {

    }

    /**
     * Add elements to a tree descriptor.
     * 
     * @param expected
     *            tree descriptor to update.
     * @param str
     *            data to add.
     */
    public static void addLineToTree(final List<String> expected, final String str) {
        expected.add(str);
    }

    /**
     * Export the given tree to HTML format.
     * 
     * Note that returned HTML will display also children of collapsed
     * {@link DTreeItem} then a full refresh could be needed, see
     * {@link org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand#RefreshRepresentationsCommand(org.eclipse.emf.transaction.TransactionalEditingDomain, boolean, org.eclipse.core.runtime.IProgressMonitor, org.eclipse.sirius.viewpoint.DRepresentation...)}
     * or yet
     * {@link org.eclipse.sirius.business.api.dialect.DialectServices#refresh(org.eclipse.sirius.viewpoint.DRepresentation, boolean, org.eclipse.core.runtime.IProgressMonitor)}
     * with boolean at true to do a full refresh.
     * 
     * If you are in manual refresh and don't want to refresh whole
     * {@link org.eclipse.sirius.tree.DTree}, you can directly call
     * {@link org.eclipse.sirius.tree.business.api.interaction.DTreeItemUserInteraction}
     * on the specific {@link DTreeItem} to have its direct children refreshed.
     * 
     * @param tree
     *            tree to export.
     * @return a string with the HTML tree.
     */
    public static String toContentHTMl(final Tree tree) {
        return TreeUIHelper.toHTML(TreeUIHelper.toTreeDescriptor(tree));
    }

    /**
     * Transform a graphical tree to a tree descriptor.
     * 
     * Note that returned strings will display also children of collapsed
     * {@link DTreeItem} then a full refresh could be needed, see
     * {@link org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand#RefreshRepresentationsCommand(org.eclipse.emf.transaction.TransactionalEditingDomain, boolean, org.eclipse.core.runtime.IProgressMonitor, org.eclipse.sirius.viewpoint.DRepresentation...)}
     * or yet
     * {@link org.eclipse.sirius.business.api.dialect.DialectServices#refresh(org.eclipse.sirius.viewpoint.DRepresentation, boolean, org.eclipse.core.runtime.IProgressMonitor)}
     * with boolean at true to do a full refresh.
     * 
     * If you are in manual refresh and don't want to refresh whole
     * {@link org.eclipse.sirius.tree.DTree}, you can directly call
     * {@link org.eclipse.sirius.tree.business.api.interaction.DTreeItemUserInteraction}
     * on the specific {@link DTreeItem} to have its direct children refreshed.
     * 
     * @param tree
     *            tree to transform.
     * @return a list of element representing the tree.
     */
    public static List<String> toTreeDescriptor(final Tree tree) {
        final List<String> expected = new ArrayList<String>();
        for (final TreeItem treeItem : tree.getItems()) {
            expected.add(treeItem.getText());
            if (treeItem.getData() instanceof DTreeItem) {
                for (String str : TreeUIHelper.addLineToDescriptor(((DTreeItem) treeItem.getData()).getOwnedTreeItems())) {
                    expected.add(str);
                }
            }
        }
        return expected;
    }

    private static List<String> addLineToDescriptor(EList<DTreeItem> treeItemSpecs) {
        final List<String> expected = new ArrayList<String>();
        for (final DTreeItem dTreeItem : treeItemSpecs) {
            expected.add(dTreeItem.getName());
            if (!dTreeItem.getOwnedTreeItems().isEmpty()) {
                for (String str : TreeUIHelper.addLineToDescriptor(dTreeItem.getOwnedTreeItems())) {
                    expected.add(str);
                }
                // addLineToDescriptor(dTreeItem.getOwnedTreeItems());
            }
        }
        return expected;

    }

    /**
     * Transform a tree descriptor to an HTML representation.
     * 
     * @param descriptor
     *            the tree descriptor.
     * @return an HTML tree.
     */
    public static String toHTML(final List<String> descriptor) {
        final StringBuffer result = new StringBuffer();
        result.append("<table>\n"); //$NON-NLS-1$
        for (String line : descriptor) {
            result.append("<tr>"); //$NON-NLS-1$
            result.append("<td>"); //$NON-NLS-1$
            result.append(line);
            result.append("</td>"); //$NON-NLS-1$
            result.append("</tr>\n"); //$NON-NLS-1$

        }
        result.append("</table>"); //$NON-NLS-1$
        return result.toString();
    }

}
