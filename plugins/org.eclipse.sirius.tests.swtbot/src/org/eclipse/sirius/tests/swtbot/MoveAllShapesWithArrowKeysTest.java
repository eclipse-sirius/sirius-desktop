/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckBoundsCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Check the move with the arrow keys.
 * 
 * @author Laurent Redor
 */
public class MoveAllShapesWithArrowKeysTest extends AbstractSiriusSwtBotGefTestCase {
    /**
     * 
     */
    private static final int GRID_STEP = 50;

    /**
     * Copied from {@link AbstractTool#MODIFIER_NO_SNAPPING}. It is not accessible here but is used in the overridden of
     * {@link #handleKeyDown(KeyEvent)}.<BR/>
     * Key modifier for ignoring snap while dragging. It's CTRL on Mac, and ALT on all other platforms.
     */
    public static final int MODIFIER_NO_SNAPPING;

    static {
        if (Platform.OS_MACOSX.equals(Platform.getOS())) {
            MODIFIER_NO_SNAPPING = SWT.CTRL;
        } else {
            MODIFIER_NO_SNAPPING = SWT.ALT;
        }
    }

    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private static final String MODELER_RESOURCE_NAME = "snapToAll.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/snap/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        // Close the outline view
        closeOutline();

        // Open the session
        UIResource sessionAirdResource = new UIResource(designerProject, SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        // Open the editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "snapToAllDiagram", "snapToAllDiagram", DDiagram.class, false, false);
        editor.setSnapToGrid(true, GRID_STEP, 2);
        editor.maximize();
    }

    @Override
    protected void tearDown() throws Exception {
        if (editor != null) {
            editor.zoom(ZoomLevel.ZOOM_100);
            editor.restore();
            editor.scrollTo(0, 0);
        }
        // Close the editor before opening the outline
        if (editor != null) {
            editor.close();
            SWTBotUtils.waitAllUiEvents();
        }

        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Move a container to the right with right arrow key a first time with {@link #MODIFIER_NO_SNAPPING} key press and
     * check the location is the expected one (ie one pixel to the right).<BR>
     * Move a container a second time without {@link #MODIFIER_NO_SNAPPING} key press and check the location is the
     * expected one (snap to the grid).<BR>
     */
    public void testMoveContainer() {
        moveInDirection("Container_p1", AbstractDiagramContainerEditPart.class, SWT.ARROW_RIGHT, false, false, true);
    }

    /**
     * Move a node to the bottom with down arrow key a first time with {@link #MODIFIER_NO_SNAPPING} key press and check
     * the location is the expected one (ie one pixel to the right).<BR>
     * Move a node a second time without {@link #MODIFIER_NO_SNAPPING} key press and check the location is the expected
     * one (snap to the grid).<BR>
     */
    public void testMoveNode() {
        moveInDirection("Node_p1", AbstractDiagramNodeEditPart.class, SWT.ARROW_DOWN, true, true, false);
    }

    /**
     * Move a node in container to the bottom with down arrow key a first time with {@link #MODIFIER_NO_SNAPPING} key
     * press and check the location is the expected one (ie one pixel to the right).<BR>
     * Move a node in container a second time without {@link #MODIFIER_NO_SNAPPING} key press and check the location is
     * the expected one (snap to the grid).<BR>
     * Move a node in container a second time without {@link #MODIFIER_NO_SNAPPING} key press and check the location is
     * the expected one (snap to the brother).<BR>
     */
    protected void testMoveNodeInContainer(ZoomLevel zoomLevel) {
        moveInDirection("NC_C1", AbstractDiagramNodeEditPart.class, SWT.ARROW_RIGHT, true, false, true, zoomLevel);

        // Third move with snap to shape (as a brother is near)
        SWTBotGefEditPart elementToMove = editor.getEditPart("NC_C1", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart brotherElement = editor.getEditPart("Container_subP1", AbstractDiagramContainerEditPart.class);
        final PrecisionRectangle originalBounds = (PrecisionRectangle) GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) elementToMove.part(), true);
        final PrecisionRectangle brotherBounds = (PrecisionRectangle) GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) brotherElement.part(), true);
        PrecisionRectangle expectedBounds = originalBounds.setPreciseX(brotherBounds.preciseX());

        ICondition condition = new CheckBoundsCondition((IGraphicalEditPart) elementToMove.part(), expectedBounds, true, true, true);
        SWTBotUtils.pressKeyboardKey(editor.getCanvas().widget, SWT.ARROW_RIGHT);
        bot.waitUntil(condition);

    }

    public void testMoveNodeInContainer() {
        testMoveNodeInContainer(ZoomLevel.ZOOM_100);

    }

    /**
     * Idem than above with zoom 125%
     */
    public void testMoveNodeInContainerWithZoom125() {
        testMoveNodeInContainer(ZoomLevel.ZOOM_125);
    }

    /**
     * Idem than above with zoom 200%
     */
    public void testMoveNodeInContainerWithZoom200() {
        testMoveNodeInContainer(ZoomLevel.ZOOM_200);
    }

    /**
     * Idem than above with zoom 50%
     */
    public void testMoveNodeInContainerWithZoom50() {
        testMoveNodeInContainer(ZoomLevel.ZOOM_50);
    }

    /**
     * Move a Note to the right with right arrow key a first time with {@link #MODIFIER_NO_SNAPPING} key press and check
     * the location is the expected one (ie one pixel to the right).<BR>
     * Move a Note a second time without {@link #MODIFIER_NO_SNAPPING} key press and check the location is the expected
     * one (snap to the grid).<BR>
     */
    public void testMoveNote() {
        moveInDirection("Note", NoteEditPart.class, SWT.ARROW_RIGHT, true);
    }

    /**
     * Move a border node of a node to the bottom with down arrow key a first time with {@link #MODIFIER_NO_SNAPPING}
     * key press and check the location is the expected one (ie one pixel to the right).<BR>
     * Move a border node of a node a second time without {@link #MODIFIER_NO_SNAPPING} key press and check the location
     * is the expected one (snap to the grid).<BR>
     */
    public void testMoveBorderNodeOnNode() {
        moveInDirection("BNN_C1", AbstractDiagramBorderNodeEditPart.class, SWT.ARROW_DOWN, true, true, false);
    }

    /**
     * Move a border node of a node in a container to the bottom with down arrow key a first time with
     * {@link #MODIFIER_NO_SNAPPING} key press and check the location is the expected one (ie one pixel to the
     * right).<BR>
     * Move a border node of a node in a container a second time without {@link #MODIFIER_NO_SNAPPING} key press and
     * check the location is the expected one (snap to the grid).<BR>
     */
    public void testMoveBorderNodeOnNodeInContainer() {
        moveInDirection("BNNC_att1", AbstractDiagramBorderNodeEditPart.class, SWT.ARROW_DOWN, true, true, false);
    }

    /**
     * Move a border node of a container to the bottom with down arrow key a first time with
     * {@link #MODIFIER_NO_SNAPPING} key press and check the location is the expected one (ie one pixel to the
     * right).<BR>
     * Move a border node of a container a second time without {@link #MODIFIER_NO_SNAPPING} key press and check the
     * location is the expected one (snap to the grid).<BR>
     */
    public void testMoveBorderNodeOnContainer() {
        moveInDirection("BNC_C1", AbstractDiagramBorderNodeEditPart.class, SWT.ARROW_DOWN, true, true, false);
    }

    /**
     * Move a border node of a container in a container to the bottom with down arrow key a first time with
     * {@link #MODIFIER_NO_SNAPPING} key press and check the location is the expected one (ie one pixel to the
     * right).<BR>
     * Move a border node of a container in a container a second time without {@link #MODIFIER_NO_SNAPPING} key press
     * and check the location is the expected one (snap to the grid).<BR>
     */
    public void testMoveBorderNodeOnContainerInContainer() {
        moveInDirection("BNC_C1Sub", AbstractDiagramBorderNodeEditPart.class, SWT.ARROW_DOWN, true, true, false);
    }

    /**
     * Move a border node of another border node to the top with up arrow key a first time with
     * {@link #MODIFIER_NO_SNAPPING} key press and check the location is the expected one (ie one pixel to the
     * right).<BR>
     * Move a border node of another border node a second time without {@link #MODIFIER_NO_SNAPPING} key press and check
     * the location is the expected one (snap to the grid).<BR>
     */
    public void testMoveBorderNodeOnBorderNode() {
        moveInDirection("BNBNC_att1", AbstractDiagramBorderNodeEditPart.class, SWT.ARROW_UP, true, true, false);
    }

    private void moveInDirection(String elementNameToMove, Class<? extends EditPart> expectedEditPartTypeOfMovedElement, int keyboardEventRepresentingArrowDirection, boolean isSnapSkippable) {
        moveInDirection(elementNameToMove, expectedEditPartTypeOfMovedElement, keyboardEventRepresentingArrowDirection, isSnapSkippable, false, false, ZoomLevel.ZOOM_100);
    }

    private void moveInDirection(String elementNameToMove, Class<? extends EditPart> expectedEditPartTypeOfMovedElement, int keyboardEventRepresentingArrowDirection, boolean isSnapSkippable, boolean skipX,
            boolean skipY) {
        moveInDirection(elementNameToMove, expectedEditPartTypeOfMovedElement, keyboardEventRepresentingArrowDirection, isSnapSkippable, skipX, skipY, ZoomLevel.ZOOM_100);
    }

    /**
     * Move element a first time without snap and check the location is the expected one (ie moved from one pixel).<BR>
     * Move element a second time with snap and check the location is the expected one (snap to the next grid step).<BR>
     */
    private void moveInDirection(String elementNameToMove, Class<? extends EditPart> expectedEditPartTypeOfMovedElement, int keyboardEventRepresentingArrowDirection, boolean isSnapSkippable, boolean skipX,
            boolean skipY,
            ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        // editor.scrollTo(0, 0);

        SWTBotGefEditPart elementToMove = editor.getEditPart(elementNameToMove, expectedEditPartTypeOfMovedElement);
        // Select the element to move
        editor.select(elementToMove);

        // Get the original bounds of the element to move
        final PrecisionRectangle originalBounds = (PrecisionRectangle) GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) elementToMove.part(), true);

        if (isSnapSkippable) {
            // First move without snap. It is sometimes KO because the "Alt+XXX arrow" is intercepted by another Eclipse
            // tool. It was the same before the new behavior implementation. So in this case, we only ignore it.
            ICondition condition = new CheckBoundsCondition((IGraphicalEditPart) elementToMove.part(), originalBounds.getTranslated(getMoveDeltaOfOnePixel(keyboardEventRepresentingArrowDirection)),
                    true, true, true);
            SWTBotUtils.pressKeyboardKey(editor.getCanvas().widget, MODIFIER_NO_SNAPPING, (char) SWT.None, keyboardEventRepresentingArrowDirection);
            bot.waitUntil(condition);

            // Move to initial location
            undo(localSession.getOpenedSession());
            // Scroll to 0, 0 is needed because the first move can cause a scroll of the diagram not reverted by the
            // Undo.
            editor.scrollTo(0, 0);
        }

        // Second move with snap
        // Use the same "bounds" used in SnapToAllDragEditPartsTracker to compute the expected delta, and so the
        // expected absolute bounds.
        IFigure figureToMove = ((GraphicalEditPart) elementToMove.part()).getFigure();
        PrecisionRectangle boundsToComputeMoveDelta = new PrecisionRectangle(figureToMove.getBounds());
        figureToMove.translateToAbsolute(boundsToComputeMoveDelta);
        editor.getDiagramEditPart().getFigure().translateToRelative(boundsToComputeMoveDelta);
        PrecisionRectangle expectedBounds = (PrecisionRectangle) originalBounds.getTranslated(getMoveDeltaToGrid(keyboardEventRepresentingArrowDirection, boundsToComputeMoveDelta, skipX, skipY));

        ICondition condition = new CheckBoundsCondition((IGraphicalEditPart) elementToMove.part(), expectedBounds, true, true, true);
        SWTBotUtils.pressKeyboardKey(editor.getCanvas().widget, keyboardEventRepresentingArrowDirection);
        bot.waitUntil(condition);
    }

    private PrecisionPoint getMoveDeltaOfOnePixel(int keyboardEventRepresentingArrowDirection) {
        PrecisionPoint result;
        if (keyboardEventRepresentingArrowDirection == SWT.ARROW_UP) {
            result = new PrecisionPoint(0, -1);
        } else if (keyboardEventRepresentingArrowDirection == SWT.ARROW_DOWN) {
            result = new PrecisionPoint(0, 1);
        } else if (keyboardEventRepresentingArrowDirection == SWT.ARROW_RIGHT) {
            result = new PrecisionPoint(1, 0);
        } else {
            result = new PrecisionPoint(-1, 0);
        }
        return result;
    }

    private PrecisionPoint getMoveDeltaToGrid(int keyboardEventRepresentingArrowDirection, PrecisionRectangle originalBounds, boolean skipX, boolean skipY) {
        PrecisionPoint result;
        if (keyboardEventRepresentingArrowDirection == SWT.ARROW_UP) {
            double leftCorrection = Math.IEEEremainder(originalBounds.preciseX(), GRID_STEP);
            double topCorrection = Math.IEEEremainder(originalBounds.preciseY(), GRID_STEP);
            if (topCorrection > 0) {
                topCorrection = -topCorrection;
            } else {
                topCorrection = GRID_STEP - topCorrection;
            }
            result = new PrecisionPoint(-leftCorrection, topCorrection);
        } else if (keyboardEventRepresentingArrowDirection == SWT.ARROW_DOWN) {
            double leftCorrection = Math.IEEEremainder(originalBounds.preciseX(), GRID_STEP);
            double topCorrection = Math.IEEEremainder(originalBounds.preciseY(), GRID_STEP);
            if (topCorrection > 0) {
                topCorrection = GRID_STEP - topCorrection;
            } else {
                topCorrection = -topCorrection;
            }
            result = new PrecisionPoint(-leftCorrection, topCorrection);
        } else if (keyboardEventRepresentingArrowDirection == SWT.ARROW_RIGHT) {
            double leftCorrection = Math.IEEEremainder(originalBounds.preciseX(), GRID_STEP);
            if (leftCorrection > 0) {
                leftCorrection = GRID_STEP - leftCorrection;
            } else {
                leftCorrection = -leftCorrection;
            }
            double topCorrection = Math.IEEEremainder(originalBounds.preciseY(), GRID_STEP);
            result = new PrecisionPoint(leftCorrection, -topCorrection);
        } else {
            double leftCorrection = Math.IEEEremainder(originalBounds.preciseX(), GRID_STEP);
            if (leftCorrection > 0) {
                leftCorrection = -leftCorrection;
            } else {
                leftCorrection = GRID_STEP - leftCorrection;
            }
            double topCorrection = Math.IEEEremainder(originalBounds.preciseY(), GRID_STEP);
            result = new PrecisionPoint(leftCorrection, -topCorrection);
        }
        if (skipX) {
            result.setX(0);
        }
        if (skipY) {
            result.setY(0);
        }
        return result;
    }
}
