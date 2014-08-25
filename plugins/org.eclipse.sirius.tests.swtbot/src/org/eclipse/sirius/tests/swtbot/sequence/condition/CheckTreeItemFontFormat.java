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
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * 
 * @author smonnier
 */
public class CheckTreeItemFontFormat extends DefaultCondition {

    private SWTBotTreeItem treeItem;

    /**
     * The text of the treeItem.
     */
    private String text;

    private int expectedFontFormat;

    /**
     * Default Constructor
     * 
     * @param treeItem
     *            the treeItem under investigation.
     * @param expectedFontFormat
     *            the expected {@link FontFormat} for the treeItem
     * 
     */
    public CheckTreeItemFontFormat(SWTBotTreeItem treeItem, int expectedFontFormat) {
        this.treeItem = treeItem;
        this.expectedFontFormat = expectedFontFormat;
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() throws Exception {
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            public void run() {
                treeItem.widget.getDisplay().syncExec(new Runnable() {
                    public void run() {
                        text = treeItem.widget.getText();
                        setResult(SWTBotUtils.getWidgetFormat(treeItem.widget).getValue() == expectedFontFormat);
                    }
                });
            }
        };
        treeItem.widget.getDisplay().syncExec(runnable);
        return ((Boolean) runnable.getResult()).booleanValue();
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return "The FontFormat of widget" + text + "is not " + FontFormat.get(expectedFontFormat);
    }

}
