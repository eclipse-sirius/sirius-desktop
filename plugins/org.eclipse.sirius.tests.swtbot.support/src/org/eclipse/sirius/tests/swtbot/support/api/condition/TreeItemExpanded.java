/**
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES
 * This program and the accompanying materials
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

import java.text.MessageFormat;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This class helps to wait that a tree item is expanded (or collapsed.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TreeItemExpanded extends DefaultCondition {
    private final TreeItem widget;

    private final String name;

    private boolean expandedCheck = true;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            The {@link SWTBotTreeItem} to wait to be expanded
     * @param name
     *            The name of the tree item to check
     */
    public TreeItemExpanded(final SWTBotTreeItem treeItem, String name) {
        this.widget = treeItem.widget;
        this.name = name;
    }

    /**
     * Constructor to have the inverse condition (collapsed condition).
     * 
     * @param treeItem
     *            The {@link SWTBotTreeItem} to wait to be expanded
     * @param name
     *            The name of the tree item to check
     * @param expandedCheck
     *            true to check the expanded status, false to check the collapsed status.
     */
    public TreeItemExpanded(final SWTBotTreeItem treeItem, String name, boolean expandedCheck) {
        this.widget = treeItem.widget;
        this.name = name;
        this.expandedCheck = expandedCheck;
    }

    /**
     * Return the failure message returned when this condition fails.
     * 
     * @param expandedCheck
     *            true to check the expanded status, false to check the collapsed status.
     * @param name
     *            The name of the tree item to check
     * @return The failure message returned when this condition fails.
     */
    public static String getFailureMessage(boolean expandedCheck, String name) {
        String failureMessage = "Tree item with text {0} is not {1}"; //$NON-NLS-1$
        if (expandedCheck) {
            return MessageFormat.format(failureMessage, name, "expanded"); //$NON-NLS-1$
        } else {
            return MessageFormat.format(failureMessage, name, "collapsed"); //$NON-NLS-1$
        }
    }

    @Override
    public String getFailureMessage() {
        return getFailureMessage(expandedCheck, name);
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
        if (expandedCheck) {
            return runnable.getResult().booleanValue();
        } else {
            return !runnable.getResult().booleanValue();
        }
    }

}
