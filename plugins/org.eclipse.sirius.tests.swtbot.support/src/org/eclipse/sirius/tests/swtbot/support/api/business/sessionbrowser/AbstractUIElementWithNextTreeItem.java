/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * UI element which can handle a next element in the tree.
 * 
 * @author dlecan
 */
public abstract class AbstractUIElementWithNextTreeItem extends AbstractUIElementWithTreeItem {

    /**
     * Constructor.
     * 
     * @param treeItem
     *            Tree item for this element.
     */
    public AbstractUIElementWithNextTreeItem(final SWTBotTreeItem treeItem) {
        super(treeItem);
    }

    /**
     * Return the next node. Return <code>null</code> if internal tree item is
     * <code>null</code>.
     * 
     * @param nodeLabel
     *            Node label.
     * @return Next node.
     */
    protected SWTBotTreeItem getNextNode(final String nodeLabel) {
        if (getTreeItem() != null) {
            return getTreeItem().expandNode(nodeLabel);
        }
        return null;
    }

}
