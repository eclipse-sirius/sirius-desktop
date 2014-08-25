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
public class GroupingContentProviderTest extends AbstractGroupingContentProviderTest {

    /**
     * Expected children size without group.
     */
    private static final int DEFAULT_SIZE = 8;

    /**
     * Expected children size when group by hierarchy.
     */
    private static final int GROUPED_SIZE = 2;

    /**
     * Test that grouping items appears in the wizard page when
     * PREF_GROUP_ENABLE at true
     */
    public void testPaneBasedSelectionWizardPage_GroupEnabled() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
            org.eclipse.swtbot.swt.finder.widgets.TimeoutException: Timeout after: 10000 ms.: The expected number of session was not reached.
            at org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(SWTBotFactory.java:407)
            at org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(SWTBotFactory.java:381)
            at org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(SWTBotFactory.java:369)
            at org.eclipse.sirius.tests.swtbot.support.api.business.UIPerspective.openSessionFromFile(UIPerspective.java:263)
            at org.eclipse.sirius.tests.swtbot.support.api.business.UIPerspective.openSessionFromFile(UIPerspective.java:241)
            at org.eclipse.sirius.tests.swtbot.AbstractGroupingContentProviderTest.onSetUpAfterOpeningDesignerPerspective(AbstractGroupingContentProviderTest.java:80)
            at org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase.setUp(AbstractSiriusSwtBotGefTestCase.java:289)
            */
            return;
        }
        setCommonUIPrefAt(true, false, DEFAULT_SIZE / 2, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getPaneBasedSelectionWizardTreeitems();
        assertEquals(GROUPED_SIZE, items.length);
        assertOnTreeItem(items, true);
    }

    /**
     * Test that grouping items does not appear in the pane based selection
     * wizard when PREF_GROUP_ENABLE at false
     */
    public void testPaneBasedSelectionWizardPage_GroupDisabled() {
        setCommonUIPrefAt(false, false, DEFAULT_SIZE / 2, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getPaneBasedSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items appears in the selection wizard when
     * PREF_GROUP_ENABLE at true
     */
    public void testSelectionWizardPage_GroupEnabled() {
        setCommonUIPrefAt(true, false, DEFAULT_SIZE / 2, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getSelectionWizardTreeitems();
        assertEquals(GROUPED_SIZE, items.length);
        assertOnTreeItem(items, true);
    }

    /**
     * Test that grouping items does not appear in the selection wizard when
     * PREF_GROUP_ENABLE at false
     */
    public void testSelectionWizardPage_GroupDisabled() {
        setCommonUIPrefAt(false, false, DEFAULT_SIZE / 2, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = getSelectionWizardTreeitems();
        assertEquals(DEFAULT_SIZE, items.length);
        assertOnTreeItem(items, false);
    }

    /**
     * Test that grouping items appears in the model explorer when
     * PREF_GROUP_ENABLE at true
     */
    public void testModelExplorer_GroupEnabled() {
        setCommonUIPrefAt(true, false, DEFAULT_SIZE / 2, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = expandModelExplorerTree();
        /*
         * We add one for the diagram tree item
         */
        assertEquals(GROUPED_SIZE + 1, items.length);
        assertOnTreeItem(items, true);
    }

    /**
     * Test that grouping items does not appear in the model explorer when
     * PREF_GROUP_ENABLE at false
     */
    public void testModelExplorer_GroupDisabled() {
        setCommonUIPrefAt(false, false, DEFAULT_SIZE / 2, DEFAULT_SIZE - 1);

        SWTBotTreeItem[] items = expandModelExplorerTree();
        /*
         * We add one for the diagram tree item
         */
        assertEquals(DEFAULT_SIZE + 1, items.length);
        assertOnTreeItem(items, false);
    }
}
