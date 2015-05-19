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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.collect.Lists;

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

    private List<FontFormat> expectedFontFormat;

    /**
     * Default Constructor
     * 
     * @param treeItem
     *            the treeItem under investigation.
     * @param expectedFontFormat
     *            the expected {@link FontFormat} for the treeItem
     * 
     */
    public CheckTreeItemFontFormat(SWTBotTreeItem treeItem, List<FontFormat> expectedFontFormat) {
        this.treeItem = treeItem;
        this.expectedFontFormat = expectedFontFormat == null ? Lists.<FontFormat> newArrayList() : expectedFontFormat;
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
                        setResult(SWTBotUtils.getWidgetFormat(treeItem.widget).equals(expectedFontFormat));
                    }
                });
            }
        };
        treeItem.widget.getDisplay().syncExec(runnable);
        return runnable.getResult().booleanValue();
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return "The FontFormat of widget" + text + "is not " + getFontFormatLiterals(expectedFontFormat);
    }

    private List<String> getFontFormatLiterals(List<FontFormat> fontFormat) {
        List<String> expected = new ArrayList<String>();
        for (FontFormat style : fontFormat) {
            expected.add(style.getName());
        }
        return expected;
    }

}
