/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.navigator;

import java.util.Collection;

import org.eclipse.swt.graphics.Image;

import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.CommonItem;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;

/**
 * An interface to surround a group of tree items.
 * 
 * @author hmarchadour
 * 
 */
public class GroupingItem implements CommonItem, ItemDecorator {

    /**
     * The starting offset of this group
     */
    private final int fOffset;

    /**
     * o The stored parent
     */
    private Object parent;

    /**
     * Stored children
     */
    private Collection<Object> children;

    private Image imageCache;

    /**
     * The constructor.
     * 
     * @param offset
     *            The starting offset of this group
     * @param parent
     *            The parent
     * @param children
     *            Children
     */
    public GroupingItem(int offset, Object parent, Collection<Object> children) {
        fOffset = offset;
        this.parent = parent;
        this.children = children;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.CommonItem#getChildren()
     */
    public Collection<?> getChildren() {
        return children;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.CommonItem#getParent()
     */
    public Object getParent() {
        return parent;
    }

    /**
     * The <code>GroupingItem</code> text process.
     * 
     * @return Grouping item text
     */
    public String getText() {
        int childrenSize = 0;
        if (children != null && children.size() > 0) {
            childrenSize = children.size() - 1;
        }
        return "[" + fOffset + ".." + (fOffset + childrenSize) + "]";
    }

    /**
     * The <code>GroupingItem</code> image process.
     * 
     * @return Grouping item image
     */
    public Image getImage() {
        if (imageCache == null) {
            imageCache = SiriusTransPlugin.INSTANCE.getBundledImage(SiriusTransPlugin.PLUGIN_ID + "/icons/ArrayPartition.gif");
        }
        return imageCache;
    }

}
