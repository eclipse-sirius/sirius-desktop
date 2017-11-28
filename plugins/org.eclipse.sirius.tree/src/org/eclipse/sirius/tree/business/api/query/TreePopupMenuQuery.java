/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.api.query;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef;

/**
 * 
 * A query to retrieve elements from a TreePopupMenu.
 * 
 * @author cbrun
 * 
 */
public class TreePopupMenuQuery {

    private TreePopupMenu menu;

    /**
     * Create the query.
     * 
     * @param menu
     *            the EObject to query.
     */
    public TreePopupMenuQuery(TreePopupMenu menu) {
        this.menu = menu;
    }

    /**
     * return all the {@link MenuItemDescription} associated with the menu
     * flattening the referenced ones.
     * 
     * @return all the {@link MenuItemDescription} associated with the menu
     *         flattening the referenced ones.
     */
    public Iterable<MenuItemDescription> getMenuItems() {
        List<MenuItemDescription> items = new ArrayList<>();
        for (MenuItemOrRef absMenuItem : menu.getMenuItemDescriptions()) {
            if (absMenuItem instanceof MenuItemDescription) {
                items.add((MenuItemDescription) absMenuItem);
            } else if (absMenuItem instanceof MenuItemDescriptionReference) {
                MenuItemDescriptionReference ref = (MenuItemDescriptionReference) absMenuItem;
                if (ref.getItem() != null && ref.getItem().eResource() != null) {
                    items.add(ref.getItem());
                }
            }
        }
        return items;
    }
}
