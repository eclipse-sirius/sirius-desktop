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
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This class helps to wait that a tree item is expanded.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TreeItemExpanded extends DefaultCondition {
    private final TreeItem widget;

    private final String name;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            The {@link SWTBotTreeItem} to wait to be expanded
     * @param name
     *            The name of the tree item
     */
    public TreeItemExpanded(final SWTBotTreeItem treeItem, String name) {
        this.widget = treeItem.widget;
        this.name = name;
    }

    @Override
    public String getFailureMessage() {
        return "tree item with text " + name + " is not expanded";
    }

    @Override
    public boolean test() throws Exception {
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            @Override
            public void run() {
                widget.getDisplay().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        setResult(widget.getExpanded());
                    }
                });
            }
        };
        widget.getDisplay().syncExec(runnable);
        return runnable.getResult().booleanValue();
    }

}
