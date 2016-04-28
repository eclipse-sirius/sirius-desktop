/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.tests.support.api.ImageEquality;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This class helps to wait that a tree item has an expected image.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TreeItemWithImageCondition extends DefaultCondition {
    private final TreeItem widget;

    private final Image expectedImage;

    private final String failureMessage;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            The {@link SWTBotTreeItem} to wait
     * @param expectedImage
     *            The expectedImage for this tree item
     * @param failureMessage
     *            the failure message when the test fails.
     */
    public TreeItemWithImageCondition(final SWTBotTreeItem treeItem, Image expectedImage, String failureMessage) {
        this.widget = treeItem.widget;
        this.expectedImage = expectedImage;
        this.failureMessage = failureMessage;
    }

    @Override
    public String getFailureMessage() {
        return failureMessage;
    }

    @Override
    public boolean test() throws Exception {
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            @Override
            public void run() {
                widget.getDisplay().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        Image treeItemImageDisplay = widget.getImage();
                        setResult(ImageEquality.areEqualImages(expectedImage, treeItemImageDisplay));
                    }
                });
            }
        };
        widget.getDisplay().syncExec(runnable);
        return runnable.getResult().booleanValue();
    }

}
