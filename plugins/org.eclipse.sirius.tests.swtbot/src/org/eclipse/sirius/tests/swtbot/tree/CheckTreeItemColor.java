/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Condition testing that a treeItem has a specific foreground color.
 * 
 * @author lredor
 */
public class CheckTreeItemColor extends DefaultCondition {

    private final TreeItem treeItem;

    private Color expectedColor;

    /**
     * Default Constructor.
     * 
     * @param treeItem
     *            the treeItem under investigation.
     * @param expectedColor
     *            The expected foreground color
     * 
     */
    public CheckTreeItemColor(TreeItem treeItem, Color expectedColor) {
        this.treeItem = treeItem;
        this.expectedColor = expectedColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return expectedColor.equals(UIThreadRunnable.syncExec(new Result<Color>() {
            public Color run() {
                return VisualBindingManager.getDefault().getColorFromRGBValues(((DTreeItem) treeItem.getData()).getOwnedStyle().getLabelColor());
            }
        }));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "The foreground color is not the expected one.";
    }

}
