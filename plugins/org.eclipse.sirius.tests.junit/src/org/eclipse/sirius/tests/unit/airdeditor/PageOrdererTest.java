/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.airdeditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.DefaultSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;

/**
 * Tests the component {@link PageRegistry}. To succeed, the plugin org.eclipse.sirius.ui.debug must not be loaded.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class PageOrdererTest extends AbstractSessionEditorTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sessionEditor = new SessionEditor();
    }

    /**
     * Asserts that computed pages order have the right order.
     * 
     * @param expectedTotalPagesNumber
     * @param orderedExpectedPages
     */
    protected void assertRightPageOrdering(int expectedTotalPagesNumber, String... orderedExpectedPages) {
        List<AbstractSessionEditorPage> pagesOrdered = pageRegistry.getPagesOrdered(sessionEditor, null, new ArrayList<>(), null);
        Optional<AbstractSessionEditorPage> defaultPage = pagesOrdered.stream().filter(DefaultSessionEditorPage.class::isInstance).findFirst();
        if (defaultPage.isPresent()) {
            idToPageMap.put(DefaultSessionEditorPage.PAGE_ID, defaultPage.get());
        }
        int i = 0;
        for (String expectedPageId : orderedExpectedPages) {
            assertEquals("The page order is wrong.", idToPageMap.get(expectedPageId), pagesOrdered.get(i));
            i++;
        }
        assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pagesOrdered.size());
    }

    /**
     * Test {@link PositioningKind#BEFORE} positioning:
     * 
     * There are the default page "Overview". We add a page before the default one.
     */
    public void testPageOrderingBefore() {
        initOnePageProvider(PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(2, PAGE2_ID, DefaultSessionEditorPage.PAGE_ID);
    }

    /**
     * Tests that when a page is placed after a page that does not exists, then it is added to the end.
     * 
     * We have the default page P1 and we add a page P2 before a page P3 that does not exist.
     * 
     * Result should be P1,P2.
     */
    public void testPageOrderingBeforeMissingPage() {
        initOnePageProvider(PositioningKind.BEFORE, PAGE3_ID, PAGE2_ID);
        assertRightPageOrdering(2, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }

    /**
     * Tests that when a page is placed after a page that does not exists, then it is added to the end.
     * 
     * We have the default page P1 and we add a page P2 after a page P3 that does not exist.
     * 
     * Result should be P1,P2.
     */
    public void testPageOrderingAfterMissingPage() {
        initOnePageProvider(PositioningKind.AFTER, PAGE3_ID, PAGE2_ID);
        assertRightPageOrdering(2, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }

    /**
     * Tests that when a page is placed after a page that does not exists, then it is added to the end.
     * 
     * We have the default page P1 and we add a page P2 that replaces a page P3 that does not exist.
     * 
     * Result should be P1,P2.
     */
    public void testPageOrderingReplaceMissingPage() {
        initOnePageProvider(PositioningKind.REPLACE, PAGE3_ID, PAGE2_ID);
        assertRightPageOrdering(2, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }

    /**
     * Test {@link PositioningKind#AFTER} positioning:
     * 
     * There are the default page "Overview". We add a page after the default one.
     */
    public void testPageOrderingAfter() {
        initOnePageProvider(PositioningKind.AFTER, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(2, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }

    /**
     * Test {@link PositioningKind#REPLACE} positioning:
     * 
     * There are the default page "Overview". We add a page that replaces the default one.
     */
    public void testPageOrderingReplacement() {
        initOnePageProvider(PositioningKind.REPLACE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(1, PAGE2_ID);
    }

    /**
     * Test positioning when no positioning is provided by the added page:
     * 
     * There are the default page "Overview". We add a page without positioning information that is put at the most
     * right position.
     */
    public void testPageOrderingNoPositioning() {
        initOnePageProvider(null, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(2, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }

    /**
     * Test positioning when no positioning is provided by the added page:
     * 
     * There are the default page "Overview". We add two pages without positioning information that are put at the most
     * right position.
     */
    public void testMultiPageOrderingNoPositioning() {
        initTwoPageProvider(null, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, null, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PAGE3_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview".
     * 
     * One page P2 is placed after the default one P1.
     * 
     * Another page P3 is provided and is placed before P2.
     * 
     * The page order should be P1, P3, P2.
     */
    public void testMultiPageOrderingBeforeConflict() {
        initTwoPageProvider(PositioningKind.AFTER, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID, PAGE2_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview".
     * 
     * One page P2 is placed before the default one P1.
     * 
     * Another page P3 is provided and is placed after P2.
     * 
     * The page order should be P2, P3, P1.
     */
    public void testMultiPageOrderingAfterFirstPage() {
        initTwoPageProvider(PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.AFTER, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE2_ID, PAGE3_ID, DefaultSessionEditorPage.PAGE_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is placed after the default one P1.
     * 
     * Another page P3 is provided and is placed after P1 also.
     * 
     * The page order should be P1,P3,P2.
     */
    public void testMultiPageOrderingAfterConflict() {
        initTwoPageProvider(PositioningKind.AFTER, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.AFTER, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID, PAGE2_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is placed before the default one P1.
     * 
     * Another page P3 is placed after P2.
     * 
     * The page order should be P1,P3,P2.
     */
    public void testMultiPageOrderingAfterConflict2() {
        initTwoPageProvider(PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.AFTER, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE2_ID, PAGE3_ID, DefaultSessionEditorPage.PAGE_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is placed before the default one P1.
     * 
     * Another page P3 is provided and is placed before P1 also.
     * 
     * The page order should be P2,P3,P1.
     */
    public void testMultiPageOrderingBeforeConflict2() {
        initTwoPageProvider(PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE2_ID, PAGE3_ID, DefaultSessionEditorPage.PAGE_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 replaces the default one P1.
     * 
     * Another page P3 replaces P1 also.
     * 
     * The page order should be P2,P3 because P2 replace P1 and P3 has no P1 to replace after P2 replacement.
     */
    public void testMultiPageOrderingReplaceConflict() {
        initTwoPageProvider(PositioningKind.REPLACE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.REPLACE, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(2, PAGE2_ID, PAGE3_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 replaces the default one P1.
     * 
     * One page P3 if after P2.
     * 
     * Another page P4 is after P1.
     * 
     * There is a conflict because P3 should be after P2 that replaced P1 and P4 is also after P2.
     * 
     * The page order should be P2,P3 and P4 because the pages attached to the page replacing another one have priority
     * over pages attached to the replaced page.
     */
    public void testMultiPageOrderingReplaceConflict2() {
        initThreePageProvider(PositioningKind.AFTER, PAGE2_ID, PAGE3_ID, PositioningKind.AFTER, DefaultSessionEditorPage.PAGE_ID, PAGE4_ID, PositioningKind.REPLACE, DefaultSessionEditorPage.PAGE_ID,
                PAGE2_ID);
        assertRightPageOrdering(3, PAGE2_ID, PAGE3_ID, PAGE4_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 replaces the default one P1.
     * 
     * Another page P3 replaces P2.
     * 
     * The page order should be P3 only.
     */
    public void testMultiPageOrderingReplace() {
        initTwoPageProvider(PositioningKind.REPLACE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.REPLACE, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(1, PAGE3_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is placed before the default one P1.
     * 
     * Another page P3 is placed before P2.
     * 
     * The page order should be P3,P2,P1.
     */
    public void testMultiPageOrderingBefore() {
        initTwoPageProvider(PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE3_ID, PAGE2_ID, DefaultSessionEditorPage.PAGE_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is placed after the default one P1.
     * 
     * Another page P3 is placed after P2.
     * 
     * The page order should be P1, P2, P3.
     */
    public void testMultiPageOrderingAfter() {
        initTwoPageProvider(PositioningKind.AFTER, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.AFTER, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PAGE3_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 has no positioning information.
     * 
     * Another page P3 is placed before P1.
     * 
     * The page order should be P3, P1, P2.
     */
    public void testMultiPageOrderingNoPositioningAndBefore() {
        initTwoPageProvider(null, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE3_ID, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is placed after P3.
     * 
     * Another page P3 is placed before P1.
     * 
     * The page order should be P3, P1, P2.
     */
    public void testMultiPageOrderingConflict() {
        initTwoPageProvider(null, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE3_ID, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is placed before P1
     * 
     * Another page P3 is not placed.
     * 
     * Another page P4 is placed before P3.
     * 
     * We have two independent cluster {P4, P3} and {P2, P1}.
     * 
     * The page order should be P4, P3, P2, P1.
     */
    public void testMultiPageOrderingClustering() {
        initThreePageProvider(PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID, null, null, PAGE3_ID, PositioningKind.BEFORE, PAGE3_ID, PAGE4_ID);
        assertRightPageOrdering(4, PAGE4_ID, PAGE3_ID, PAGE2_ID, DefaultSessionEditorPage.PAGE_ID);
    }

    /**
     * Test page positioning :
     * 
     * There are the default page "Overview" P1.
     * 
     * One page P2 is not placed
     * 
     * Another page P3 is placed before P1.
     * 
     * Another page P4 is placed before P3.
     * 
     * We have two independent cluster {P4, P3, P1} and {P2}.
     * 
     * The page order should be P4, P3, P1, P2.
     */
    public void testMultiPageOrderingClustering2() {
        initThreePageProvider(null, null, PAGE2_ID, PositioningKind.BEFORE, DefaultSessionEditorPage.PAGE_ID, PAGE3_ID, PositioningKind.BEFORE, PAGE3_ID, PAGE4_ID);
        assertRightPageOrdering(4, PAGE4_ID, PAGE3_ID, DefaultSessionEditorPage.PAGE_ID, PAGE2_ID);
    }
}
