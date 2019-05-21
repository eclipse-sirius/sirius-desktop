/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNoOpenedSessionInModelContentView;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;

/**
 * Tests for the "pin elements" feature.
 * 
 * @author pcdavid
 */
public class PinnedElementsOnTreeDiagramTest extends AbstractPinnedElementsTest {

    private static final String VIEWPOINT_NAME = "Tests Cases for ticket #1924 (pin/unpin on tree diagram)";

    private static final String MODEL = "model/tc1924.ecore";

    private static final String SESSION_FILE = "model/tc1924.aird";

    private static final String VSM_FILE = "description/tc1924.odesign";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        closeOutline();
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, "tc1924.aird");
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testMovingElementsSetsPinnedIfPreferenceEnabled() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), true);

        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_all_unpinned");
        final IDiagramElementEditPart class1 = (IDiagramElementEditPart) editor.getEditPart("C1", IDiagramElementEditPart.class).part();
        assertThat(class1, not(isPinnedMatcher()));
        Point origin = editor.getLocation("C1", IDiagramElementEditPart.class).getTranslated(1, 1);
        Point target = origin.getTranslated(30, 30);
        editor.drag(origin.x, origin.y, target.x, target.y);
        bot.waitUntil(waitForPinned(class1));
        assertThat("Moved element should pinned if AUTO_PIN_ON_MOVE is enabled.", class1, isPinnedMatcher());
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testMovingElementsDoesNotSetsPinnedIfPreferenceDisabled() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name(), false);

        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_all_unpinned");
        final IDiagramElementEditPart class1 = (IDiagramElementEditPart) editor.getEditPart("C1", IDiagramElementEditPart.class).part();
        assertThat(class1, not(isPinnedMatcher()));
        Point origin = editor.getLocation("C1", IDiagramElementEditPart.class).getTranslated(1, 1);
        Point target = origin.getTranslated(30, 30);
        editor.drag(origin.x, origin.y, target.x, target.y);
        bot.waitUntil(waitForNotPinned(class1));
        assertThat("Moved element should not be pinned if AUTO_PIN_ON_MOVE is disabled.", class1, not(isPinnedMatcher()));
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testPinElementContextualMenuAction() throws Exception {
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_all_unpinned");
        final IDiagramElementEditPart class1 = (IDiagramElementEditPart) editor.getEditPart("C1", IDiagramElementEditPart.class).part();
        assertThat(class1, not(isPinnedMatcher()));
        editor.getEditPart("C1", IDiagramElementEditPart.class).select();
        editor.clickContextMenu("Pin selected elements");
        bot.waitUntil(waitForPinned(class1));
        assertThat(class1, isPinnedMatcher());
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testPinnedAttributeIsPersistent() throws Exception {
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_all_unpinned");
        IDiagramElementEditPart class1 = (IDiagramElementEditPart) editor.getEditPart("C1", IDiagramElementEditPart.class).part();
        assertThat(class1, not(isPinnedMatcher()));
        editor.getEditPart("C1", IDiagramElementEditPart.class).select();
        editor.clickContextMenu("Pin selected elements");
        bot.waitUntil(waitForPinned(class1));
        assertThat(class1, isPinnedMatcher());
        localSession.close(true);

        bot.waitUntil(new CheckNoOpenedSessionInModelContentView(bot, SESSION_FILE));

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_all_unpinned");
        class1 = (IDiagramElementEditPart) editor.getEditPart("C1", IDiagramElementEditPart.class).part();
        assertThat(class1, isPinnedMatcher());
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_All_Unpinned() throws Exception {
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_all_unpinned");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_All_Pinned() throws Exception {
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_all_pinned");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertNoBoundChanged(initialBounds, finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Some_Pinned_No_Overlaps() throws Exception {
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_some_pinned_no_overlaps");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Some_Pinned_Solvable_Overlaps() throws Exception {
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_some_pinned_solvable_overlaps");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Some_Pinned_Unsolvable_Overlaps() throws Exception {
        openDiagram(VIEWPOINT_NAME, "SimpleTreeDiagram", "simpleTreeDiagram_some_pinned_unsolvable_overlaps");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Recursive_All_Unpinned() throws Exception {
        openDiagram(VIEWPOINT_NAME, "RecursiveTreeDiagram", "Recursive_all_unpinned");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Recursive_All_Pinned() throws Exception {
        openDiagram(VIEWPOINT_NAME, "RecursiveTreeDiagram", "Recursive_all_pinned");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertNoBoundChanged(initialBounds, finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Recursive_Some_Pinned_No_Overlaps() throws Exception {
        openDiagram(VIEWPOINT_NAME, "RecursiveTreeDiagram", "Recursive_some_pinned_no_overlaps");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Recursive_Some_Pinned_Solvable_Overlaps() throws Exception {
        openDiagram(VIEWPOINT_NAME, "RecursiveTreeDiagram", "Recursive_some_pinned_solvable_overlaps");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    /**
     * @throws Exception
     *             if an error occurred.
     */
    public void testArrange_Recursive_Some_Pinned_Unsolvable_Overlaps() throws Exception {
        openDiagram(VIEWPOINT_NAME, "RecursiveTreeDiagram", "Recursive_some_pinned_unsolvable_overlaps");
        final Map<IGraphicalEditPart, Rectangle> initialBounds = saveBounds();
        arrangeAll();
        final Map<IGraphicalEditPart, Rectangle> finalBounds = saveBounds();
        assertSomeBoundsChanged(initialBounds, finalBounds);
    }
}
