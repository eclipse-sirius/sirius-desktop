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
package org.eclipse.sirius.tests.swtbot.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class HideRevealTableLinesTest extends AbstractHideRevealTableElementsTest<SWTBotTree> {

    private static final String DIALOG_TITLE = "Hide/Show table lines";

    private static final String CONTEXTUAL_MENU_HIDE_ELEMENT = "Hide/Show lines...";
    
    @Override
    public void testCheckAllElementsAreInDialog() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
                org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find node with text: Models
                at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem.java:334)
                at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:308)
                at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:346)
                at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.expandNode(SWTBotTreeItem.java:283)
                at org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.getUIItemFromResource(UIProject.java:140)
                at org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.selectResource(UIProject.java:122)
                at org.eclipse.sirius.tests.swtbot.support.api.business.UIPerspective.openSessionCreationWizardFromSemanticResource(UIPerspective.java:188)
                at org.eclipse.sirius.tests.swtbot.table.AbstractHideRevealTableElementsTest.onSetUpAfterOpeningDesignerPerspective(AbstractHideRevealTableElementsTest.java:125)
            */
            return;
        }
        super.testCheckAllElementsAreInDialog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getElementCount() {
        return getAllTreeItems(treeTable.widget).size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> getElementsHeader() {
        return UIThreadRunnable.syncExec(new Result<List<String>>() {
            public List<String> run() {
                return Lists.newArrayList(Collections2.transform(getAllTreeItems(treeTable.widget), new Function<TreeItem, String>() {
                    public String apply(final TreeItem from) {
                        return from.getText();
                    }
                }));
            }
        });
    }

    private List<TreeItem> getAllTreeItems(final Tree tree) {
        return UIThreadRunnable.syncExec(new Result<List<TreeItem>>() {
            public List<TreeItem> run() {
                final TreeItem[] items = tree.getItems();
                return getVisibleChildrenCount(items);
            }

            private List<TreeItem> getVisibleChildrenCount(final TreeItem[] items) {
                final List<TreeItem> result = new ArrayList<>();
                for (final TreeItem item : items) {
                    result.add(item);
                    result.addAll(getVisibleChildrenCount(item.getItems()));

                }
                return result;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DialogTable<SWTBotTree> getInnerTable(final SWTBot parent) {
        final SWTBotTree hideElementTree = parent.tree();
        return new DialogTable<SWTBotTree>() {

            /**
             * {@inheritDoc}
             */
            public String getTableItem(final int index) {
                return UIThreadRunnable.syncExec(new Result<String>() {
                    public String run() {
                        return getAllTreeItems(hideElementTree.widget).get(index).getText();
                    }
                });
            }

            /**
             * 
             * {@inheritDoc}
             */
            public int elementCount() {
                return getAllTreeItems(hideElementTree.widget).size();
            }

            /**
             * {@inheritDoc}
             */
            public SWTBotTree getDialogTable() {
                return hideElementTree;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getVisibleElementCount() {
        return treeTable.visibleRowCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String uncheckElement(final DialogTable<SWTBotTree> dialogTable, final int relativeElementIndex) {
        return uncheckAllElements(dialogTable, new int[] { relativeElementIndex }).get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getContextualMenuLabel() {
        return CONTEXTUAL_MENU_HIDE_ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDialogTitle() {
        return DIALOG_TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> uncheckAllElements(final DialogTable<SWTBotTree> dialogTable, final int[] relativeElementIndex) {
        final SWTBotTree swtBotTree = dialogTable.getDialogTable();

        final List<String> result = new ArrayList<>();

        final List<TreeItem> allTreeItems = getAllTreeItems(swtBotTree.widget);

        for (final int i : relativeElementIndex) {
            final SWTBotTreeItem swtBotTreeItem = new SWTBotTreeItem(allTreeItems.get(i));
            swtBotTreeItem.uncheck();
            result.add(swtBotTreeItem.getText());
        }

        return result;
    }
}
