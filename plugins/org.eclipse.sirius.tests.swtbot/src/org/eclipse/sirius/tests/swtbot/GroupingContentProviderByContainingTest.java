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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * We test the grouping content provider in its containing feature mode. (It
 * will group children by them containment feature)
 * <p>
 * Test that :
 * <p>
 * The GroupingContentProvider handles the grouping item when it needed in model
 * explorer.
 * <p>
 * The GroupingContentProvider handles the grouping item when it needed in
 * EObjectPaneBasedSelectionWizardPage.
 * <p>
 * The GroupingContentProvider the grouping item when it needed in
 * EObjectSelectionWizardPage.
 */
public class GroupingContentProviderByContainingTest extends AbstractGroupingContentProviderTest {

    /**
     * Expected children size without group.
     */
    private static final int DEFAULT_SIZE = 8;

    /**
     * Expected children size when group by containing.
     */
    private static final int GROUPED_SIZE = 4;

    /**
     * Test that grouping items does not appear in the wizard page when
     * PREF_GROUP_ENABLE at true, but each containing feature size <=
     * groupTrigger
     */
    public void testPaneBasedSelectionWizardPage_GroupEnabled_NotAppear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getPaneBasedSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items does not appear in the wizard page when
     * PREF_GROUP_ENABLE at true.
     */
    public void testPaneBasedSelectionWizardPage_GroupEnabled_Appear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, GROUPED_SIZE - 1);

        SWTBotTreeItem[] items = getPaneBasedSelectionWizardTreeitems();
        assertEquals(GROUPED_SIZE, items.length);
        assertOnTreeItem(items, true);
    }

    /**
     * Test that grouping items does not appear in the pane based selection
     * wizard when PREF_GROUP_ENABLE at true and group by containing feature,
     * but each containing feature size <= groupTrigger
     */
    public void testPaneBasedSelectionWizardPage_GroupEnabled2_NotAppear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getPaneBasedSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items appears in the pane based selection wizard when
     * PREF_GROUP_ENABLE at true and group by containing feature.
     */
    public void testPaneBasedSelectionWizardPage_GroupEnabled2_Appear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, GROUPED_SIZE - 1);

        SWTBotTreeItem[] items = getPaneBasedSelectionWizardTreeitems();
        assertEquals(GROUPED_SIZE, items.length);
        assertOnTreeItem(items, true);
    }

    /**
     * Test that grouping items does not appear in the pane based selection
     * wizard when PREF_GROUP_ENABLE at false
     */
    public void testPaneBasedSelectionWizardPage_GroupDisabled_NotAppear() {
        setCommonUIPrefAt(false, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getPaneBasedSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items does not appear in the selection wizard when
     * PREF_GROUP_ENABLE at true, but each containing feature size <=
     * groupTrigger
     */
    public void testSelectionWizardPage_GroupEnabled_NotAppear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items appears in the selection wizard when
     * PREF_GROUP_ENABLE at true.
     */
    public void testSelectionWizardPage_GroupEnabled_Appear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, GROUPED_SIZE - 1);

        SWTBotTreeItem[] items = getSelectionWizardTreeitems();
        assertEquals(GROUPED_SIZE, items.length);
        assertOnTreeItem(items, true);
    }

    /**
     * Test that grouping items does not appear in the selection wizard when
     * PREF_GROUP_ENABLE at true and group by containing feature, but each
     * containing feature size <= groupTrigger
     */
    public void testSelectionWizardPage_GroupEnabled2_NotAppear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items appears in the selection wizard when
     * PREF_GROUP_ENABLE at true and group by containing feature.
     */
    public void testSelectionWizardPage_GroupEnabled2_Appear() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, GROUPED_SIZE - 1);

        SWTBotTreeItem[] items = getSelectionWizardTreeitems();
        assertEquals(GROUPED_SIZE, items.length);
        assertOnTreeItem(items, true);
    }

    /**
     * Test that grouping items does not appear in the selection wizard when
     * PREF_GROUP_ENABLE at false
     */
    public void testSelectionWizardPage_GroupDisabled_NotAppear() {
        setCommonUIPrefAt(false, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items does not appear in the model explorer when
     * PREF_GROUP_ENABLE at true, but each containing feature size <=
     * groupTrigger
     */
    public void testModelExplorer_GroupEnabled_NotAppear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = expandModelExplorerTree();
        /*
         * We add one for the diagram tree item
         */
        assertEquals(DEFAULT_SIZE + 1, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items appears in the model explorer when
     * PREF_GROUP_ENABLE at true.
     */
    public void testModelExplorer_GroupEnabled_Appear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, GROUPED_SIZE - 1);

        SWTBotTreeItem[] items = expandModelExplorerTree();
        /*
         * We add one for the diagram tree item
         */
        assertEquals(GROUPED_SIZE + 1, items.length);
        assertOnTreeItem(new SWTBotTreeItem[] { items[0] }, false);
        assertOnTreeItem(new SWTBotTreeItem[] { items[1], items[2], items[3], items[4] }, true);
    }

    /**
     * Test that grouping items does not appear in the model explorer when
     * PREF_GROUP_ENABLE at true and group by containing feature, but each
     * containing feature size <= groupTrigger
     */
    public void testModelExplorer_GroupEnabled2_NotAppear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = expandModelExplorerTree();
        /*
         * We add one for the diagram tree item
         */
        assertEquals(DEFAULT_SIZE + 1, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items appears in the model explorer when
     * PREF_GROUP_ENABLE at true and group by containing feature.
     */
    public void testModelExplorer_GroupEnabled2_Appear() {
        setCommonUIPrefAt(true, true, GROUPED_SIZE - 1, GROUPED_SIZE - 1);

        SWTBotTreeItem[] items = expandModelExplorerTree();
        /*
         * We add one for the diagram tree item
         */
        assertEquals(GROUPED_SIZE + 1, items.length);
        assertOnTreeItem(new SWTBotTreeItem[] { items[0] }, false);
        assertOnTreeItem(new SWTBotTreeItem[] { items[1], items[2], items[3], items[4] }, true);
    }

    /**
     * Test that grouping items does not appear in the model explorer when
     * PREF_GROUP_ENABLE at false
     */
    public void testModelExplorer_GroupDisabled_NotAppear() {
        setCommonUIPrefAt(false, true, GROUPED_SIZE - 1, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = expandModelExplorerTree();
        /*
         * We add one for the diagram tree item
         */
        assertEquals(DEFAULT_SIZE + 1, items.length);
        assertOnTreeItem(items, false);
    }
}
