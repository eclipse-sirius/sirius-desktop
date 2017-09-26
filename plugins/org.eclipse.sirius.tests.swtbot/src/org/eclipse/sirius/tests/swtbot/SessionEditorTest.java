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
package org.eclipse.sirius.tests.swtbot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.business.internal.resource.AirDResourceImpl;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.unit.airdeditor.SessionEditorTestPageProvider;
import org.eclipse.sirius.tests.unit.airdeditor.SessionEditorTestPageProvider.CommandSynchronization;
import org.eclipse.sirius.tests.unit.airdeditor.SessionEditorTestPageProvider.PageProviderExtension;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.SessionEditorPlugin;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry;
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
public class SessionEditorTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String NEW_LABEL_INVISIBLE = "newLabelInvisible";

    private static final String NEW_LABEL_VISIBLE = "newLabelVisible";

    private static final String UPDATED_PAGE_LABEL = "newLabel";

    private static final String PAGE2_TAB_LABEL = "page2";

    private static final String DEFAULT_PAGE_TAB_LABEL = "Overview";

    public static final String PATH = "/data/unit/layoutingMode/";

    private static final String SEMANTIC_MODEL_FILENAME = "vp2120.ecore";

    private static final String MODELER_MODEL_FILENAME = "vp2120.odesign";

    private static final String SESSION_PATH = "vp2120.aird";

    private static final String FILE_DIR = "/";

    private SessionEditor sessionEditor;

    private SessionEditorTestPageProvider sessionEditorTestPageProvider;

    private Map<String, AbstractSessionEditorPage> idToPageMap;

    private PageProviderRegistry pageRegistry;

    private List<PageProviderExtension> pageProviders;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, MODELER_MODEL_FILENAME, SESSION_PATH);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_PATH);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        RunnableWithResult<IEditorPart> result = new RunnableWithResult<IEditorPart>() {
            private IEditorPart resultEditor;

            @Override
            public void run() {
                URI uri = localSession.getOpenedSession().getSessionResource().getURI();
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

        idToPageMap = new HashMap<String, AbstractSessionEditorPage>();
        idToPageMap.put(SessionEditorPlugin.DEFAULT_PAGE_ID, (AbstractSessionEditorPage) sessionEditor.getPages().get(0));

        pageRegistry = SessionEditorPlugin.getPlugin().getPageRegistry();

        pageProviders = new ArrayList<PageProviderExtension>();

        sessionEditorTestPageProvider = new SessionEditorTestPageProvider(sessionEditor, idToPageMap, pageRegistry, pageProviders);

    }

    @Override
    protected void tearDown() throws Exception {
        sessionEditor.close(false);
        for (PageProviderExtension pageProviderExtension : pageProviders) {
            pageRegistry.removePageProvider(pageProviderExtension);
        }
        idToPageMap.clear();
        sessionEditor = null;
        sessionEditorTestPageProvider = null;
        pageRegistry = null;
        pageProviders = null;
        idToPageMap = null;
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
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            List<IFormPage> pagesOrdered = sessionEditor.getPages();
            CTabItem[] pageItems = getPageTabItems(sessionEditor);
            int i = 0;
            for (String expectedPageTabLAbel : orderedExpectedPages) {
                assertEquals("The test context is wrong", expectedPageTabLAbel, pageItems[i].getText());
                i++;
            }
            assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pageItems.length);
            assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pagesOrdered.size());
        });
    }

    /**
     * Asserts that computed pages order have the right order.
     * 
     * @param expectedTotalPagesNumber
     * @param orderedExpectedPages
     */
    protected void assertRightPageOrdering(int expectedTotalPagesNumber, String... orderedExpectedPages) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            List<IFormPage> pagesOrdered = sessionEditor.getPages();
            int i = 0;
            for (String expectedPageId : orderedExpectedPages) {
                assertEquals("The page order is wrong.", idToPageMap.get(expectedPageId), pagesOrdered.get(i));
                i++;
            }
            CTabItem[] pageItems = getPageTabItems(sessionEditor);
            assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pageItems.length);
            assertEquals("The number of page in the editor is wrong.", expectedTotalPagesNumber, pagesOrdered.size());
        });
    }

    /**
     * Test {@link PositioningKind#BEFORE} positioning:
     * 
     * There are the default page "Overview". We add a page before the default one.
     */
    public void testPageOrderingBefore() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
    }

    /**
     * Tests that when a page is placed after a page that does not exists, then it is added to the end.
     * 
     * We have the default page P1 and we add a page P2 before a page P3 that does not exist.
     * 
     * Result should be P1,P2.
     */
    public void testPageOrderingBeforeMissingPage() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.BEFORE, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
    }

    /**
     * Tests that when a page is placed after a page that does not exists, then it is added to the end.
     * 
     * We have the default page P1 and we add a page P2 after a page P3 that does not exist.
     * 
     * Result should be P1,P2.
     */
    public void testPageOrderingAfterMissingPage() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
    }

    /**
     * Tests that when a page is placed after a page that does not exists, then it is added to the end.
     * 
     * We have the default page P1 and we add a page P2 that replaces a page P3 that does not exist.
     * 
     * Result should be P1,P2.
     */
    public void testPageOrderingReplaceMissingPage() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.REPLACE, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
    }

    /**
     * Test {@link PositioningKind#AFTER} positioning:
     * 
     * There are the default page "Overview". We add a page after the default one.
     */
    public void testPageOrderingAfter() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
    }

    /**
     * Test {@link PositioningKind#REPLACE} positioning:
     * 
     * There are the default page "Overview". We add a page that replaces the default one.
     */
    public void testPageOrderingReplacement() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
        assertRightPageOrdering(1, SessionEditorTestPageProvider.PAGE2_ID);
    }

    /**
     * Test positioning when no positioning is provided by the added page:
     * 
     * There are the default page "Overview". We add a page without positioning information that is put at the most
     * right position.
     */
    public void testPageOrderingNoPositioning() {
        sessionEditorTestPageProvider.initOnePageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
        assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
    }

    /**
     * Test positioning when no positioning is provided by the added page:
     * 
     * There are the default page "Overview". We add two pages without positioning information that are put at the most
     * right position.
     */
    public void testMultiPageOrderingNoPositioning() {
        sessionEditorTestPageProvider.initTwoPageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, SessionEditorPlugin.DEFAULT_PAGE_ID,
                SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.BEFORE,
                SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE2_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.AFTER,
                SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.AFTER,
                SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE2_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.AFTER,
                SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.BEFORE,
                SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.REPLACE,
                SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(2, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.REPLACE,
                SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(1, SessionEditorTestPageProvider.PAGE3_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.BEFORE,
                SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.AFTER,
                SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorTestPageProvider.PAGE3_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.BEFORE,
                SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
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
        sessionEditorTestPageProvider.initTwoPageProvider(null, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.BEFORE,
                SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE3_ID);
        assertRightPageOrdering(3, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
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
        sessionEditorTestPageProvider.initThreePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, null,
                SessionEditorTestPageProvider.PAGE3_ID, PositioningKind.BEFORE, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE4_ID);
        assertRightPageOrdering(4, SessionEditorTestPageProvider.PAGE4_ID, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE2_ID, SessionEditorPlugin.DEFAULT_PAGE_ID);
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
        sessionEditorTestPageProvider.initThreePageProvider(null, null, SessionEditorTestPageProvider.PAGE2_ID, PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID,
                SessionEditorTestPageProvider.PAGE3_ID, PositioningKind.BEFORE, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorTestPageProvider.PAGE4_ID);
        assertRightPageOrdering(4, SessionEditorTestPageProvider.PAGE4_ID, SessionEditorTestPageProvider.PAGE3_ID, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);
    }

    /**
     * Test page positioning :
     * 
     * We add a page by adding a {@link PageProvider}. Then we remove the page provider from the registry. The page
     * should be removed.
     */
    public void testPageProviderRemoval() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);

        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            assertRightPageOrdering(2, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID);

            pageRegistry.removePageProvider(pageProviders.get(0));

            assertRightPageOrdering(1, SessionEditorPlugin.DEFAULT_PAGE_ID);
        });
    }

    /**
     * Test page positioning :
     * 
     * A page has its tab label updated each time the session's resource set is updated. This tests the rename is done
     * correctly.
     */
    public void testDynamicPageRenameUpdate() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().renameTab(UPDATED_PAGE_LABEL).build());
        }, null);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, UPDATED_PAGE_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, UPDATED_PAGE_LABEL);

    }

    /**
     * Test page positioning :
     * 
     * A page has its tab label set to newLabelVisible when the page is made visible and newLabelInvisible when it is
     * made not visible.
     */
    public void testDynamicPageRenameVisibilityUpdate() {
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, (isVisible) -> {
            if (isVisible) {
                return Optional.of(new PageUpdateCommandBuilder().renameTab(NEW_LABEL_VISIBLE).build());
            } else {
                return Optional.of(new PageUpdateCommandBuilder().renameTab(NEW_LABEL_INVISIBLE).build());
            }
        }, null, null);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, NEW_LABEL_VISIBLE);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, NEW_LABEL_VISIBLE);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, NEW_LABEL_INVISIBLE);

        setActivePage(0);

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().removePage().build());
        }, null);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().removePage().build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(1, DEFAULT_PAGE_TAB_LABEL);

        removeFirstResource();

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().removePage().build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        removeFirstResource();

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, null, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(1, PAGE2_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(1, PAGE2_TAB_LABEL);
    }

    /**
     * Removes the first resource of the session's resource set.
     */
    private void removeFirstResource() {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().remove(0);
        });
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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.REPLACE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, null, CommandSynchronization.VISIBILITY_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        setActivePage(1);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        setActivePage(1);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

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
        sessionEditorTestPageProvider.initOnePageProvider(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID, SessionEditorTestPageProvider.PAGE2_ID, (isVisible) -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.BEFORE, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, () -> {
            return Optional.of(new PageUpdateCommandBuilder().reorderPage(PositioningKind.AFTER, SessionEditorPlugin.DEFAULT_PAGE_ID).build());
        }, CommandSynchronization.BOTH_SYNCHRONIZATION);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        setActivePage(1);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);;

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);

        removeFirstResource();

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(0);

        assertRightPageOrderingByLabel(2, DEFAULT_PAGE_TAB_LABEL, PAGE2_TAB_LABEL);

        setActivePage(SessionEditorTestPageProvider.PAGE2_ID);

        assertRightPageOrderingByLabel(2, PAGE2_TAB_LABEL, DEFAULT_PAGE_TAB_LABEL);
    }

    /**
     * Change selected page with the one at the given index.
     */
    private void setActivePage(int pageIndex) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            sessionEditor.setActivePage(pageIndex);
        });
    }

    /**
     * Change selected page with the one with the given name.
     */
    private void setActivePage(String pageName) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            sessionEditor.setActivePage(pageName);
        });
    }

}
