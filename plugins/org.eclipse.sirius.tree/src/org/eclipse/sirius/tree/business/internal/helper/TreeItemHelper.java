/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.helper;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.description.TreeItemMapping;

/**
 * Class providing utility methods for handling DTreeItems. For example,
 * provides facilities for creating a DTreeItem from a TreeItemMapping and a
 * semantic element.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class TreeItemHelper {

    private TreeItemHelper() {

    }

    /**
     * Creates a {@link DTreeItem} from the given {@link TreeItemMapping},
     * having the given modelElement has semantic target.
     * 
     * @param treeItemMapping
     *            The {@link TreeItemMapping} to use for creating the
     *            {@link DTreeItem}
     * @param modelElement
     *            the model element to associate with the new {@link DTreeItem}
     * @return a {@link DTreeItem} created from the given
     *         {@link TreeItemMapping}, having the given modelElement has
     *         semantic target
     */
    public static DTreeItem createTreeItemFromMapping(TreeItemMapping treeItemMapping, EObject modelElement) {
        final DTreeItem newItem = TreeFactory.eINSTANCE.createDTreeItem();
        newItem.setTarget(modelElement);
        newItem.setActualMapping(treeItemMapping);

        // TODO if use show need for more than this simple creating (like style
        // updates for example), should be completed

        return newItem;
    }

    /**
     * Returns a DTreeItem referencing the given semantic element, if any
     * exists.
     * 
     * @param semanticElement
     *            the semantic element
     * @return a DTreeItem referencing the given semantic element, or
     *         Options.none if no such TreeItem can be found.
     */
    public static Option<DTreeItem> getTreeItem(EObject semanticElement) {
        Option<DTreeItem> treeItem = Options.newNone();
        Iterable<DTreeItem> filter = Iterables.filter(semanticElement.eCrossReferences(), DTreeItem.class);
        if (filter.iterator().hasNext()) {
            treeItem = Options.newSome(filter.iterator().next());
        }
        return treeItem;
    }
}
