/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirResizableEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefFigureCanvas;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * Ensures that selecting several elements and then move the selected elements
 * with the "Ctrl" key pressed does not cause duplication of graphical elements
 * (which is GMF default behavior).
 * <p>
 * Related classes : <b>NoCopyDragEditPartsTrackerEx</b> .
 * </p>
 * 
 * @author alagarde
 */
public class DuplicationCausedBySelectionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String FILE_DIR = "/";

    private static final String DATA_UNIT_DIR = "data/unit/selection/duplication/";

    private static final String SESSION_NODES = "doremi-2440_nodes.aird";

    private static final String MODEL_NODES = "doremi-2440_nodes.ecore";

    private static final String SESSION_CONTAINERS = "doremi-2440_containers.aird";

    private static final String MODEL_CONTAINERS = "doremi-2440_containers.ecore";

    private static final String SESSION_EDGES = "doremi-2440_edges.aird";

    private static final String MODEL_EDGES = "doremi-2440_edges.ecore";

    private static final String SESSION_LISTS = "doremi-2440_lists.aird";

    private static final String MODEL_LISTS = "doremi-2440_lists.ecore";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String REPRESENTATION_NAME_FOR_NODES = "Nodes";

    private static final String ODESIGN_FOR_NODES = "doremi-2440_nodes.odesign";

    private static final String SESSION_BORDERED = "doremi-2440_bordered.aird";

    private static final String MODEL_BORDERED = "doremi-2440_bordered.ecore";

    private static final String REPRESENTATION_NAME_FOR_BORDERED = "Bordered";

    private static final String ODESIGN_FOR_BORDERED = "doremi-2440_bordered.odesign";

    private static final String MODEL_SEQUENCE = "doremi-2440_lifelines.interactions";

    private static final String MODEL_SEQUENCE2 = "doremi-2440_lifelines.ecore";

    private static final String SESSION_SEQUENCE = "doremi-2440_lifelines.aird";

    private static final String REPRESENTATION_NAME_FOR_SEQUENCE = "Sequence Diagram on Interaction";

    /**
     * The opened {@link UILocalSession}.
     */
    UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, ODESIGN_FOR_NODES, ODESIGN_FOR_BORDERED, MODEL_NODES, SESSION_NODES, MODEL_CONTAINERS, SESSION_CONTAINERS, MODEL_EDGES, SESSION_EDGES,
                MODEL_LISTS, SESSION_LISTS, MODEL_BORDERED, SESSION_BORDERED, MODEL_SEQUENCE, MODEL_SEQUENCE2, SESSION_SEQUENCE);
    }

    /**
     * Ensures that selecting Nodes and then try to move them with the "Ctrl"
     * key pressed does not cause duplication of graphical elements.
     */
    public void testSelectionDoesNotCauseDuplicationOnNodes() {
        // Step 1 : opening the representation that contains nodes
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_NODES);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_FOR_NODES, "nodes", DDiagram.class);

        checkElementsAreNotDuplicated(3, IDiagramNodeEditPart.class);

        // Step 2 : select elements and move them with "Ctrl" pressed, clicking
        // exactly on the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(true, IDiagramNodeEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, IDiagramNodeEditPart.class);

        // Step 2 : select elements and move them with "Ctrl" pressed, clicking
        // in the middle of the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(false, IDiagramNodeEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, IDiagramNodeEditPart.class);
    }

    /**
     * Ensures that selecting Containers and then try to move them with the
     * "Ctrl" key pressed does not cause duplication of graphical elements.
     */
    public void testSelectionDoesNotCauseDuplicationOnContainers() {
        // Step 1 : opening the representation that contains nodes
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_CONTAINERS);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, "containers", DDiagram.class);

        checkElementsAreNotDuplicated(3, IDiagramContainerEditPart.class);

        // Step 2 : select elements and move them with "Ctrl" pressed, clicking
        // exactly on the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(true, IDiagramContainerEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, IDiagramContainerEditPart.class);

        // Step 3 : select elements and move them with "Ctrl" pressed, clicking
        // in the middle of the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(false, IDiagramContainerEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, IDiagramContainerEditPart.class);
    }

    /**
     * Ensures that selecting NodeLists with 'Ctrl' and then try to move them
     * with the "Ctrl" key pressed does not cause duplication of graphical
     * elements.
     */
    public void testSelectionDoesNotCauseDuplicationOnNodeLists() {
        // Step 1 : opening the representation that contains node lists
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_LISTS);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, "lists", DDiagram.class);

        checkElementsAreNotDuplicated(3, DNodeListEditPart.class);

        // Step 2 : select elements and move them with "Ctrl" pressed, clicking
        // exactly on the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(true, DNodeListEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, DNodeListEditPart.class);

        // Step 3 : select elements and move them with "Ctrl" pressed, clicking
        // in the middle of the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(false, DNodeListEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, DNodeListEditPart.class);
    }

    /**
     * Ensures that selecting Nodes and Edges and then try to move them with the
     * "Ctrl" key pressed does not cause duplication of edges.
     */
    public void testSelectionDoesNotCauseDuplicationOnEdges() {
        // Step 1 : opening the representation that contains nodes
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_EDGES);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, "edges", DDiagram.class);

        checkElementsAreNotDuplicated(1, IDiagramEdgeEditPart.class);

        // Step 2 : select elements and move them with "Ctrl" pressed, clicking
        // exactly on the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(true, IDiagramEdgeEditPart.class, INodeEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(1, IDiagramEdgeEditPart.class);

        // Step 3 : select elements and move them with "Ctrl" pressed, clicking
        // in the middle of the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(false, IDiagramEdgeEditPart.class, INodeEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(1, IDiagramEdgeEditPart.class);
    }

    /**
     * Ensures that selecting Bordered Nodes with 'Ctrl' and then try to move
     * them with the "Ctrl" key pressed does not cause duplication these
     * bordered nodes inside their container.
     */
    public void testSelectionDoesNotCauseDuplicationOnBorderedNodes() {
        // Step 1 : opening the representation that contains nodes
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_BORDERED);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_FOR_BORDERED, "bordered", DDiagram.class);

        checkElementsAreNotDuplicated(1, IDiagramBorderNodeEditPart.class);

        // Step 2 : select elements and move them with "Ctrl" pressed, clicking
        // exactly on the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(false, IDiagramBorderNodeEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(1, IDiagramBorderNodeEditPart.class);

        // Step 3 : select elements and move them with "Ctrl" pressed, clicking
        // in the middle of the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(true, IDiagramBorderNodeEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(1, IDiagramBorderNodeEditPart.class);
    }

    /**
     * Ensures that selecting Lifelines and Instance Roles with 'Ctrl' and then
     * try to move them with the "Ctrl" key pressed does not cause duplication
     * of graphical elements.
     */
    public void testSelectionDoesNotCauseDuplicationOnLifelinesAndInstanceRoles() {

        // Step 1 : open the sequence diagram that contains lifelines
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_SEQUENCE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_FOR_SEQUENCE, "Sequence Diagram on Lifelines", DDiagram.class);

        checkElementsAreNotDuplicated(3, InstanceRoleEditPart.class);
        checkElementsAreNotDuplicated(3, LifelineEditPart.class);

        // Step 2 : select all the instance roles and move them with "Ctrl"
        // pressed, clicking exactly on the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(true, InstanceRoleEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, InstanceRoleEditPart.class);
        checkElementsAreNotDuplicated(3, LifelineEditPart.class);

        // Step 2 : select all the instance roles and move them with "Ctrl"
        // pressed, clicking in the middle of the element
        selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(false, InstanceRoleEditPart.class);
        // => ensure that no element has been duplicated
        checkElementsAreNotDuplicated(3, InstanceRoleEditPart.class);
        checkElementsAreNotDuplicated(3, LifelineEditPart.class);
    }

    /**
     * Selects all elements of the given type move them with the "Ctrl" key
     * pressed.
     * 
     * @param byClickingOnBorder
     *            indicates whether the user will click on the border of the
     *            element to move (arrows symbol) or in the middle of this
     *            element. This parameter has been added because the behavior is
     *            not the same (first solution required to change the
     *            {@link AirResizableEditPolicy}, the second the EditParts.
     * @param editPartTypes
     *            the {@link Class}es of the edit Parts to select
     */
    protected void selectElementsWithGivenTypeAndMoveThenWithCtrlKeyDown(boolean byClickingOnBorder, final Class<?>... editPartTypes) {
        // Step 1 : select the expected elements
        SWTBotUtils.waitAllUiEvents();
        List<SWTBotGefEditPart> editParts = editor.editParts(new BaseMatcher<EditPart>() {

            @Override
            public boolean matches(Object item) {
                boolean hasExpectedType = false;
                for (Class<?> editPartType : editPartTypes) {
                    if (editPartType.isInstance(item)) {
                        hasExpectedType = true;
                        break;
                    }
                }
                return hasExpectedType;
            }

            @Override
            public void describeTo(Description description) {

            }
        });
        editParts.addAll(editor.getConnectionsEditPart());
        editor.select(editParts);
        SWTBotUtils.waitAllUiEvents();

        // Step 2 : moving elements with "Ctrl" key down
        SWTBotGefFigureCanvas canvas = editor.getCanvas();
        SWTBotGefFigureCanvasForDuplicationTest canvasForTest = new SWTBotGefFigureCanvasForDuplicationTest(canvas);
        Rectangle bounds = editor.getBounds(editParts.iterator().next());
        int sourceX = bounds.x;
        int sourceY = bounds.y;
        int targetX = 0;
        int targetY = 0;
        if (!byClickingOnBorder) {
            sourceX++;
            targetX = 100;
            targetY = 100;
        }
        canvasForTest.mouseDrag(sourceX, sourceY + 5, targetX, targetY);
    }

    /**
     * Ensures that no graphical elements of the given type have been
     * duplicated, by checking that the number of Edit parts of the given type
     * is equals to the expected number.
     * 
     * @param expectedNumberOfElements
     *            the expected number of edit parts of the given type
     * @param editPartType
     *            the {@link Class} of the edit Parts to select
     */
    protected void checkElementsAreNotDuplicated(int expectedNumberOfElements, final Class<?> editPartType) {
        SWTBotUtils.waitAllUiEvents();
        // Step 1 : getting all edit Parts
        List<SWTBotGefEditPart> editParts = editor.editParts(new BaseMatcher<EditPart>() {

            @Override
            public boolean matches(Object item) {
                return editPartType.isInstance(item);
            }

            @Override
            public void describeTo(Description description) {

            }
        });
        editParts.addAll(editor.getConnectionsEditPart());

        // Step 2 : assert number of edit parts is equal to the expected number
        // of elements
        assertEquals("Wrong number of graphical elements (duplicated elements)", expectedNumberOfElements, editParts.size());

    }

    /**
     * Subclass of {@link SWTBotGefFigureCanvas} overriding mouseDrag() to press
     * on the "Ctrl" key when dragging elements.
     * 
     * @author alagarde
     */
    protected class SWTBotGefFigureCanvasForDuplicationTest extends SWTBotGefFigureCanvas {

        /**
         * Default constructor.
         * 
         * @param canvas
         *            the SWTBotGefFigureCanvas
         * @throws WidgetNotFoundException
         *             if widget cannot be found
         */
        public SWTBotGefFigureCanvasForDuplicationTest(SWTBotGefFigureCanvas canvas) throws WidgetNotFoundException {
            super((FigureCanvas) canvas.widget);
        }

        @Override
        public void mouseDrag(final int fromXPosition, final int fromYPosition, final int toXPosition, final int toYPosition) {
            UIThreadRunnable.asyncExec(new VoidResult() {
                @Override
                public void run() {
                    // Moving mouse to the start position
                    org.eclipse.swt.events.MouseEvent meMove = wrapMouseEvent(fromXPosition, fromYPosition, 0, 0, 0);
                    eventDispatcher.dispatchMouseMoved(meMove);
                    // Left-clicking at position
                    org.eclipse.swt.events.MouseEvent meDown = wrapMouseEvent(fromXPosition, fromYPosition, 1, SWT.BUTTON1, 1);
                    eventDispatcher.dispatchMousePressed(meDown);

                    // Holding left-button and moving mouse
                    org.eclipse.swt.events.MouseEvent meMoveTarget = wrapMouseEvent(toXPosition, toYPosition, 1, SWT.BUTTON1, 0);
                    eventDispatcher.dispatchMouseMoved(meMoveTarget);
                }
            });
            SWTBotUtils.waitAllUiEvents();
            UIThreadRunnable.asyncExec(new VoidResult() {
                @Override
                public void run() {
                    // Pressing on "Ctrl"
                    eventDispatcher.dispatchKeyPressed(keyEvent(SWT.CTRL));

                    // Releasing left-button while holding "Ctrl"
                    org.eclipse.swt.events.MouseEvent meUp = wrapMouseEvent(toXPosition, toYPosition, 1, SWT.CTRL & SWT.BUTTON1, 1);
                    eventDispatcher.dispatchMouseReleased(meUp);
                    // Releasing the "Ctrl" touched
                    eventDispatcher.dispatchKeyReleased(keyEvent(SWT.CTRL));
                }
            });
        }

        private KeyEvent keyEvent(int keyCode) {
            Event event = new Event();
            event.time = (int) System.currentTimeMillis();
            event.widget = widget;
            event.display = display;
            KeyEvent keyEvent = new KeyEvent(event);
            keyEvent.keyCode = keyCode;
            keyEvent.doit = true;
            return keyEvent;
        }

        private org.eclipse.swt.events.MouseEvent wrapMouseEvent(int x, int y, int button, int stateMask, int count) {
            return new org.eclipse.swt.events.MouseEvent(createMouseEvent(x, y, button, stateMask, count));
        }

    }
}
