/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
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

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This class helps to wait that a tree item is renamed.
 * 
 * @author mporhel
 */
public class TreeItemTextCondition extends DefaultCondition {

    private final SWTBotTreeItem treeItem;

    private final String expectedName;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            the item to check
     * @param expectedName
     *            the expected new name.
     */
    public TreeItemTextCondition(SWTBotTreeItem treeItem, String expectedName) {
        this.treeItem = treeItem;
        this.expectedName = expectedName;
    }

    @Override
    public boolean test() throws Exception {
        return expectedName.equals(treeItem.getText());
    }

    @Override
    public String getFailureMessage() {
        return "tree item with text " + treeItem.getText() + " was not renamed into " + expectedName;
    }

}
