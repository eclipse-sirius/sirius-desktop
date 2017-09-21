/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.multipageeditor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.business.internal.resource.AirDResourceImpl;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.airdeditor.AbstractSessionEditorTest;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.SessionEditorPlugin;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;
import org.eclipse.sirius.ui.editor.api.pages.PageUpdateCommandBuilder;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * This class tests the {@link SessionEditor} page extensibility functionality from the UI point of view.
 * 
 * Warning: the plugin org.eclipse.sirius.ui.debug must be not in runtime so the tests can work.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SessionEditorTest extends AbstractSessionEditorTest {

    private static final String NEW_LABEL_INVISIBLE = "newLabelInvisible";

    private static final String NEW_LABEL_VISIBLE = "newLabelVisible";

    private static final String UPDATED_PAGE_LABEL = "newLabel";

    private static final String PAGE2_TAB_LABEL = "page2";

    private static final String DEFAULT_PAGE_TAB_LABEL = "Overview";

    public static final String PATH = "/data/unit/layoutingMode/";

    private static final String SEMANTIC_MODEL_FILENAME = "vp2120.ecore";

    private static final String MODELER_MODEL_FILENAME = "vp2120.odesign";

    private static final String SESSION_PATH = "vp2120.aird";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, MODELER_MODEL_FILENAME, SESSION_PATH);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_PATH);
        RunnableWithResult<IEditorPart> result = new RunnableWithResult<IEditorPart>() {
            private IEditorPart resultEditor;

            @Override
            public void run() {
                URI uri = session.getSessionResource().getURI();
                try {
                    resultEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                            .openEditor(new FileEditorInput(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)))), SessionEditor.EDITOR_ID);
                } catch (PartInitException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public IEditorPart getResult() {
                return resultEditor;
            }

            @Override
            public void setStatus(IStatus status) {

            }

            @Override
            public IStatus getStatus() {
                return null;
            }
        };
        PlatformUI.getWorkbench().getDisplay().syncExec(result);
        sessionEditor = (SessionEditor) result.getResult();
        assertEquals("There should be only the default page after initialization.", 1, sessionEditor.getPages().size());
        idToPageMap.put(SessionEditorPlugin.DEFAULT_PAGE_ID, (AbstractSessionEditorPage) sessionEditor.getPages().get(0));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.sirius.tests.unit.airdeditor.AbstractSessionEditorTest#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        sessionEditor.close(false);
        super.tearDown();

    }

    private CTabItem[] getPageTabItems(SessionEditor sessionEditor) {
        Field f;
        try {
            f = MultiPageEditorPart.class.getDeclaredField("container");

            f.setAccessible(true);
            CTabFolder tabFolder = (CTabFolder) f.get(sessionEditor);
            return tabFolder.getItems();
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Asserts that computed pages order have the right order by testing tab labels.
     * 
     * @param expectedTotalPagesNumber
     * @param orderedExpectedPages
     */
    protected void assertRightPageOrderingByLabel(int expectedTotalPagesNumber, String... orderedExpectedPages) {
        List<IFormPage> pagesOrdered = sessionEditor.getPages();
        CTabItem[] pageItems = getPageTabItems(sessionEditor);
        int i = 0;
        for (String expectedPageTabLAbel : orderedExpectedPages) {
            assertEquals("The test context is wrong", expectedPageTabLAbel, pageItems[i].getText());
            i++;
        }
        assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pageItems.length);
        assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pagesOrdered.size());
    }

    /**
     * Asserts that computed pages order have the right order.
     * 
     * @param expectedTotalPagesNumber
     * @param orderedExpectedPages
     */
    protected void assertRightPageOrdering(int expectedTotalPagesNumber, String... orderedExpectedPages) {
        List<IFormPage> pagesOrdered = sessionEditor.getPages();
        int i = 0;
        for (String expectedPageId : orderedExpectedPages) {
            assertEquals("The page order is wrong.", idToPageMap.get(expectedPageId), pagesOrdered.get(i));
            i++;
        }
        CTabItem[] pageItems = getPageTabItems(sessionEditor);
        assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pageItems.length);
        assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pagesOrdered.size());
    }

    /**
     * Test {@link PositioningKind#BEFORE} positioning:
     * 
     * There are the default page "Overview". We add a page before the default one.
     */
    public void testPageOrderingBefore() {
        initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(2, PAGE2_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
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
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
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
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
    }

    /**
     * Test {@link PositioningKind#AFTER} positioning:
     * 
     * There are the default page "Overview". We add a page after the default one.
     */
    public void testPageOrderingAfter() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
    }

    /**
     * Test {@link PositioningKind#REPLACE} positioning:
     * 
     * There are the default page "Overview". We add a page that replaces the default one.
     */
    public void testPageOrderingReplacement() {
        initOnePageProvider(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(1, PAGE2_ID);
    }

    /**
     * Test positioning when no positioning is provided by the added page:
     * 
     * There are the default page "Overview". We add a page without positioning information that is put at the most
     * right position.
     */
    public void testPageOrderingNoPositioning() {
        initOnePageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
    }

    /**
     * Test positioning when no positioning is provided by the added page:
     * 
     * There are the default page "Overview". We add two pages without positioning information that are put at the most
     * right position.
     */
    public void testMultiPageOrderingNoPositioning() {
        initTwoPageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PAGE3_ID);
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
        initTwoPageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID, PAGE2_ID);
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
        initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.AFTER, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE2_ID, PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        initTwoPageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID, PAGE2_ID);
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
        initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.AFTER, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE2_ID, PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE2_ID, PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        initTwoPageProvider(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(2, PAGE2_ID, PAGE3_ID);
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
        initTwoPageProvider(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.REPLACE, PAGE2_ID, PAGE3_ID);
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
        initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE3_ID, PAGE2_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        initTwoPageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.AFTER, PAGE2_ID, PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PAGE3_ID);
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
        initTwoPageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
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
        initTwoPageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID);
        assertRightPageOrdering(3, PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
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
        initThreePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, null, PAGE3_ID, PositioningKind.BEFORE, PAGE3_ID, PAGE4_ID);
        assertRightPageOrdering(4, PAGE4_ID, PAGE3_ID, PAGE2_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        initThreePageProvider(null, null, PAGE2_ID, PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE3_ID, PositioningKind.BEFORE, PAGE3_ID, PAGE4_ID);
        assertRightPageOrdering(4, PAGE4_ID, PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
    }

    /**
     * Test page positioning :
     * 
     * We add a page by adding a {@link PageProvider}. Then we remove the page provider from the registry. The page
     * should be removed.
     */
    public void testPageProviderRemoval() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID);

        pageRegistry.removePageProvider(pageProviders.get(0));

        assertRightPageOrdering(1, SessionEditorPlugin.DEFAULT_PAGE_ID);
    }

    /**
     * Test page positioning :
     * 
     * A page has its tab label updated each time the session's resource set is updated. This tests the rename is done
     * correctly.
     */
    public void testDynamicPageRenameUpdate() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().renameTab(UPDATED_PAGE_LABEL).build());
        }, null);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, UPDATED_PAGE_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, UPDATED_PAGE_LABEL);

    }

    /**
     * Test page positioning :
     * 
     * A page has its tab label set to newLabelVisible when the page is made visible and newLabelInvisible when it is
     * made not visible.
     */
    public void testDynamicPageRenameVisibilityUpdate() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, (isVisible) -> {
            if (isVisible) {
                return Optional.of(new PageUpdateCommandBuilder().renameTab(NEW_LABEL_VISIBLE).build());
            } else {
                return Optional.of(new PageUpdateCommandBuilder().renameTab(NEW_LABEL_INVISIBLE).build());
            }
        }, null, null);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, NEW_LABEL_VISIBLE);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, NEW_LABEL_VISIBLE);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, NEW_LABEL_INVISIBLE);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, NEW_LABEL_INVISIBLE);
    }

    /**
     * Test page positioning :
     * 
     * A page is removed when any change occurs from session's resource set. But the page provider has no condition
     * preventing its initialization when called. The page visibility is not synchronized between page and page
     * provider.
     * 
     * So just after the removal, the page should be added again.
     */
    public void testDynamicPageRemovalDesynchroUpdate() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().removePage().build());
        }, null);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * A page is removed when the {@link AirDResourceImpl} resource is removed. The page provider and the page method
     * {@link AbstractSessionEditorPage#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)} are
     * synchronized. I.e if the page is removed dynamically, the page provider update will not provide the page again
     * The page removal condition is the the exact contrary of the page providing condition.
     */
    public void testDynamicPageRemovalUpdate() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().removePage().build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(1, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(1, DEFAULT_PAGE_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * A page is removed when the {@link AirDResourceImpl} resource is removed and the page is visible. The page
     * provider and the page method {@link AbstractSessionEditorPage#pageChanged(boolean)} are synchronized. I.e if the
     * page is removed dynamically, the page provider update will not provide the page again The page removal condition
     * is the the exact contrary of the page providing condition.
     */
    public void testDynamicPageRemovalVisibilityUpdate() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().removePage().build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(1, DEFAULT_PAGE_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * A page is reordered before the default one when the {@link AirDResourceImpl} resource is removed. The method
     * {@link AbstractSessionEditorPage#getPositioning()} is synchronized with the method
     * {@link AbstractSessionEditorPage#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)} doing
     * the removal. So the page reorder information when called from a resource set changed are the same than the page
     * positioning from methods {@link AbstractSessionEditorPage#getPositioning()} and
     * {@link AbstractSessionEditorPage#getLocationId()}
     */
    public void testDynamicPageReorderUpdate() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * The default page is reordered after another page when the {@link AirDResourceImpl} resource is removed. The
     * method {@link AbstractSessionEditorPage#getPositioning()} is synchronized with the method
     * {@link AbstractSessionEditorPage#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)} doing
     * the removal. So the page reorder information when called from a resource set changed are the same than the page
     * positioning from methods {@link AbstractSessionEditorPage#getPositioning()} and
     * {@link AbstractSessionEditorPage#getLocationId()}
     */
    public void testDynamicPageReorderUpdate2() {
        initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * The default page is reordered after another page when the {@link AirDResourceImpl} resource is removed. The
     * method {@link AbstractSessionEditorPage#getPositioning()} is synchronized with the method
     * {@link AbstractSessionEditorPage#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)} doing
     * the removal. So the page reorder information when called from a resource set changed are the same than the page
     * positioning from methods {@link AbstractSessionEditorPage#getPositioning()} and
     * {@link AbstractSessionEditorPage#getLocationId()}
     */
    public void testDynamicPageReorderUpdate3() {
        initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(1, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(1, PAGE2_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * The default page is replaced by another page when the {@link AirDResourceImpl} resource is removed and the page
     * gains focus. This tests the method {@link AbstractSessionEditorPage#pageChanged(boolean)}. The page reorder
     * information when called from the previous method are the same than the page positioning from methods
     * {@link AbstractSessionEditorPage#getPositioning()} and {@link AbstractSessionEditorPage#getLocationId()}
     */
    public void testDynamicPageReorderVisibilityUpdate() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(1, PAGE2_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * The default page is reordered before another page when the {@link AirDResourceImpl} resource is removed and the
     * page gains focus. This tests the method {@link AbstractSessionEditorPage#pageChanged(boolean)}. The page reorder
     * information when called from the previous method are the same than the page positioning from methods
     * {@link AbstractSessionEditorPage#getPositioning()} and {@link AbstractSessionEditorPage#getLocationId()}
     */
    public void testDynamicPageReorderVisibilityUpdate2() {
        initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * The default page is reordered after another page when the {@link AirDResourceImpl} resource is removed and the
     * page gains focus. This tests the method {@link AbstractSessionEditorPage#pageChanged(boolean)}. The page reorder
     * information when called from the previous method are the same than the page positioning from methods
     * {@link AbstractSessionEditorPage#getPositioning()} and {@link AbstractSessionEditorPage#getLocationId()}.
     */
    public void testDynamicPageReorderVisibilityUpdate3() {
        initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.setActivePage(1);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.setActivePage(1);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);
    }

    /**
     * Test page positioning :
     * 
     * The methods
     * {@link AbstractSessionEditorPage#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)} and
     * {@link AbstractSessionEditorPage#pageChanged(boolean)} reorder the page after and before the default one
     * respectively when {@link AirDResourceImpl} is removed and when the page is set visible.
     */
    public void testBothDynamicPageUpdate() {
        initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.BOTH_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.setActivePage(1);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);;

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        sessionEditor.setActivePage(PAGE2_ID);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);
    }

}
