/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
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

import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Class to availability of a context menu on a {@link SWTBotTreeItem}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class CheckContextMenuAvailableCondition extends DefaultCondition {

    private final SWTBotTreeItem treeItem;

    private final String menuName;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            {@link SWTBotTreeItem} to check context menu existence
     * 
     * @param menuName
     *            name of the expected context menu
     * 
     */
    public CheckContextMenuAvailableCondition(SWTBotTreeItem treeItem, String menuName) {
        this.treeItem = treeItem;
        this.menuName = menuName;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swtbot.swt.finder.waits.ICondition#test()
     */
    @Override
    public boolean test() throws Exception {
        return SWTBotUtils.hasContextMenu(treeItem, menuName);
    }

    @Override
    public String getFailureMessage() {
        return "The SWTBotTreeItem named '" + treeItem.getText() + " has no context menu named " + menuName;
    }

}
