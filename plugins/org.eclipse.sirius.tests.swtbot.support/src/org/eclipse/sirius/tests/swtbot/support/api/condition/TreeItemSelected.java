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
 * This class helps to wait that a tree item is selected.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TreeItemSelected extends DefaultCondition {
    private final SWTBotTreeItem item;

    /**
     * Constructor.
     * 
     * @param item
     *            The item to wait to be selected
     */
    public TreeItemSelected(final SWTBotTreeItem item) {
        this.item = item;
    }

    @Override
    public String getFailureMessage() {
        return "tree item with text " + item.getText() + " is not selected";
    }

    @Override
    public boolean test() throws Exception {
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            @Override
            public void run() {
                item.display.syncExec(new Runnable() {
                    @Override
                    public void run() {
                        setResult(item.isSelected());
                    }
                });
            }
        };
        item.display.syncExec(runnable);
        boolean result = runnable.getResult().booleanValue();
        if (!result) {
            item.select();
        }
        return result;
    }

}
