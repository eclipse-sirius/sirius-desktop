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
 * Element with tree item.
 * 
 * @author dlecan
 */
public abstract class AbstractUIElementWithTreeItem {

    private final SWTBotTreeItem treeItem;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            Tree item for this element.
     */
    public AbstractUIElementWithTreeItem(final SWTBotTreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * Returns the treeItem.
     * 
     * @return the treeItem
     */
    public SWTBotTreeItem getTreeItem() {
        return treeItem;
    }
}
