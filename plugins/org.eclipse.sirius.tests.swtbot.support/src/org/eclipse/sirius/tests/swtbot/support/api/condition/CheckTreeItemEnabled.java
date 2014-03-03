/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Condition testing the a treeItem is enabled.
 * 
 * @author smonnier
 */
public class CheckTreeItemEnabled extends DefaultCondition {

    private final SWTBotTreeItem treeItem;

    /**
     * Default Constructor.
     * 
     * @param treeItem
     *            the treeItem under investigation.
     * 
     */
    public CheckTreeItemEnabled(SWTBotTreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return treeItem.isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "The FontFormat of widget";
    }

}
