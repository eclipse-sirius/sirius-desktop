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
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This class helps to wait that a tree item is expanded AND its children are
 * visible.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TreeItemContainsAtLeastOneChild extends DefaultCondition {
    private final SWTBotTreeItem treeItem;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            The treeItem to wait to be expanded and contains at least one
     *            visible child
     */
    public TreeItemContainsAtLeastOneChild(SWTBotTreeItem treeItem) {
        this.treeItem = treeItem;
    }

    @Override
    public String getFailureMessage() {
        return "tree item does not contains child";
    }

    @Override
    public boolean test() throws Exception {
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            @Override
            public void run() {
                treeItem.widget.getDisplay().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        setResult(treeItem.getItems().length > 0);
                    }
                });
            }
        };
        treeItem.widget.getDisplay().syncExec(runnable);
        return ((Boolean) runnable.getResult()).booleanValue();
    }

}
