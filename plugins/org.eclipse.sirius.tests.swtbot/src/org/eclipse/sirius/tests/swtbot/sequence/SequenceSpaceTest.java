/*******************************************************************************
* Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests the space insertion or removal to lifeline or message with the
 * CTRL+SHIFT+Mouse drag.
 * 
 * @author pguilet
 *
 */
public class SequenceSpaceTest extends AbstractDefaultModelSequenceTests {

    private static final int SPACE_TO_ADD = 100;

    private static final int SPACE_TO_REMOVE = 10;

    private static final String REPRESENTATION_INSTANCE_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "2256.interactions";

    private static final String SESSION_FILE = "2256.aird";

    private static final String DATA_UNIT_DIR = "data/unit/sequence/spaces/";

    private static final int BIG_SPACE_TO_REMOVE = 212;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, getTypesSemanticModel());
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationId(), REPRESENTATION_INSTANCE_NAME, DDiagram.class, true, true);
    }

    /**
     * Verifies that vertical space insertion after the first execution on the
     * line do add spaces between the last message and the end of the line.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionAfterExecution() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int e1ReturnMessageVerticalPosition = getSequenceMessageVerticalPosition(RETURN_MESSAGE);

        editor.dragWithKeys(1, e1ReturnMessageVerticalPosition + 20, 1, 20 + e1ReturnMessageVerticalPosition + SPACE_TO_ADD, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int e1ReturnMessageVerticalPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        assertEquals("The execution should not have been resized.", e1ReturnMessageVerticalPosition, e1ReturnMessageVerticalPositionAfterDrag);
        assertEquals("Wrong lifeling height: no space has been added at the end of the lifeline.", lifelineAHeight + SPACE_TO_ADD, lifelineAHeightAfterDrag, 1);
        assertEquals("Vertical space should not have been added before the first execution", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));

    }

    /**
     * Verifies that vertical space insertion at the level of the first
     * execution do add the space in the execution when triggered from canvas.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionInExecutionFromCanvas() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        org.eclipse.draw2d.geometry.Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int firstExecutionVerticalPosition = getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL);
        int firstExecutionReturnPosition = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, firstExecutionVerticalPosition);

        editor.dragWithKeys(1, firstExecutionVerticalPosition + 10, 1, firstExecutionVerticalPosition + 10 + SPACE_TO_ADD, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        int executionReturnPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("Wrong lifeling height: space is expected to be added.", lifelineAHeight + SPACE_TO_ADD, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The end of the first execution is not at the expected location.", firstExecutionReturnPosition + SPACE_TO_ADD, executionReturnPositionAfterDrag);

    }

    /**
     * Verifies that vertical space insertion at the level of the first
     * execution do add the space in the execution when triggered from inside
     * the execution.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionInExecutionFromExecution() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        org.eclipse.draw2d.geometry.Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int firstExecutionVerticalPosition = getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL);
        int firstExecutionReturnPosition = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        SequenceMessageEditPart endExecutionVerticalPartBeforeMove = (SequenceMessageEditPart) ((GraphicalEditPart) getSequenceMessageEditPart(FIRST_MESSAGE_SYNC_CALL).getTarget())
                .getSourceConnections().get(0);
        int endExecutionHorizontalPositionBeforeMove = getSequenceMessageScreenHorizontalPosition(endExecutionVerticalPartBeforeMove);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, firstExecutionVerticalPosition);

        editor.dragWithKeys(endExecutionHorizontalPositionBeforeMove + 3, firstExecutionVerticalPosition + 10, endExecutionHorizontalPositionBeforeMove + 3,
                firstExecutionVerticalPosition + 10 + SPACE_TO_ADD, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        int executionReturnPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("Wrong lifeling height: space is expected to be added.", lifelineAHeight + SPACE_TO_ADD, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The end of the first execution is not at the expected location.", firstExecutionReturnPosition + SPACE_TO_ADD, executionReturnPositionAfterDrag);

    }

    /**
     * Verifies that vertical space insertion before life lines do add spaces
     * between start of lines and the first message on the line.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionBeforeLine() throws Exception {
        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int firstExecutionVerticalPosition = getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, firstExecutionVerticalPosition);

        editor.dragWithKeys(1, 1, 1, SPACE_TO_ADD + 1, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        assertEquals("Wrong lifeling height: space is expected to be added.", lifelineAHeight + SPACE_TO_ADD, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y + SPACE_TO_ADD, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
    }

    /**
     * Verifies that vertical space removal after the first execution on the
     * line do remove spaces between the last message and the end of the line.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalAfterExecution() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int e1ReturnMessageVerticalPosition = getSequenceMessageVerticalPosition(RETURN_MESSAGE);

        editor.dragWithKeys(1, 20 + e1ReturnMessageVerticalPosition + SPACE_TO_ADD, 1, e1ReturnMessageVerticalPosition + 20, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int e1ReturnMessageVerticalPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        assertEquals("The execution should not have been resized.", e1ReturnMessageVerticalPosition, e1ReturnMessageVerticalPositionAfterDrag);
        assertEquals("Wrong lifeling height: space is expected to be removed at the end of the lifeline.", lifelineAHeight - SPACE_TO_ADD, lifelineAHeightAfterDrag, 1);
        assertEquals("Vertical space should not have been removed before the first execution", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));

    }

    /**
     * Verifies that vertical space removal at the level of the first execution
     * do remove the space in the execution and triggered from canvas.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalInExecutionFromCanvas() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int firstExecutionVerticalPosition = getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL);
        int firstExecutionReturnPosition = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, firstExecutionVerticalPosition);

        editor.dragWithKeys(1, firstExecutionVerticalPosition + 10 + SPACE_TO_REMOVE, 1, firstExecutionVerticalPosition + 10, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        int executionReturnPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("Wrong lifeling height: space is expected to be removed", lifelineAHeight - SPACE_TO_REMOVE, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The first execution has not been resized.", firstExecutionReturnPosition - SPACE_TO_REMOVE, executionReturnPositionAfterDrag);

    }

    /**
     * Verifies that vertical space removal at the level of the first execution
     * do remove the space in the execution and triggered from execution.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalInExecutionFromExecution() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int firstExecutionVerticalPosition = getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL);
        int firstExecutionReturnPosition = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        SequenceMessageEditPart endExecutionVerticalPartBeforeMove = (SequenceMessageEditPart) ((GraphicalEditPart) getSequenceMessageEditPart(FIRST_MESSAGE_SYNC_CALL).getTarget())
                .getSourceConnections().get(0);
        int endExecutionHorizontalPositionBeforeMove = getSequenceMessageScreenHorizontalPosition(endExecutionVerticalPartBeforeMove);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, firstExecutionVerticalPosition);

        editor.dragWithKeys(endExecutionHorizontalPositionBeforeMove + 3, firstExecutionVerticalPosition + 10 + SPACE_TO_REMOVE, endExecutionHorizontalPositionBeforeMove + 3,
                firstExecutionVerticalPosition + 10, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        int executionReturnPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("Wrong lifeling height: space is expected to be removed", lifelineAHeight - SPACE_TO_REMOVE, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The first execution has not been resized.", firstExecutionReturnPosition - SPACE_TO_REMOVE, executionReturnPositionAfterDrag);

    }

    /**
     * Verifies that vertical space removal before life lines do remove spaces
     * between start of lines and the first message on the line.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalBeforeLine() throws Exception {
        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int firstExecutionVerticalPosition = getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, firstExecutionVerticalPosition);

        editor.dragWithKeys(1, SPACE_TO_REMOVE + 1, 1, 1, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        assertEquals("Wrong lifeling height: space is expected to be removed", lifelineAHeight - SPACE_TO_REMOVE, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y - SPACE_TO_REMOVE, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
    }

    /**
     * Verifies that vertical space removal when the drag area cuts the limits
     * of more than one element do remove space to the lifeline after last
     * element.
     * 
     * Verifies that vertical space removal does not remove anything when the
     * drag area cuts the limits of more than one element when no space is
     * available after last element of lifeline.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalWithManyCrossing() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
        int e3StartVerticalPosition = getSequenceMessageVerticalPosition("m5");
        int e1StartVerticalPosition = getSequenceMessageVerticalPosition("m1");
        int firstExecutionReturnPosition = getSequenceMessageVerticalPosition(RETURN_MESSAGE);

        // drag with space available after last element on the lifeline
        editor.dragWithKeys(1, e3StartVerticalPosition + 10, 1, e3StartVerticalPosition - BIG_SPACE_TO_REMOVE, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        int executionReturnPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("Wrong lifeling height: space is expected to be removed", lifelineAHeight - BIG_SPACE_TO_REMOVE - 10, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The first execution has not been resized.", firstExecutionReturnPosition, executionReturnPositionAfterDrag);

        lifelineAHeight = getLifelineLength(LIFELINE_B);
        // drag with no space available after last element on the lifeline
        editor.dragWithKeys(1, e3StartVerticalPosition + 10, 1, e1StartVerticalPosition - 10, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        executionReturnPositionAfterDrag = getSequenceMessageVerticalPosition(RETURN_MESSAGE);
        assertEquals("Wrong lifeling height: space is expected to be added.", lifelineAHeight, lifelineAHeightAfterDrag);
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", e1.y, getSequenceMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The first execution should not have been resized.", firstExecutionReturnPosition, executionReturnPositionAfterDrag);

    }

    /**
     * Verifies that vertical space insertion in an operand condition in a
     * combined fragment is OK.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionInOperandCondition() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        int startExecutionVerticalPosition = getSequenceMessageVerticalPosition("m9");
        SequenceMessageEditPart endExecutionVerticalPartBeforeMove = (SequenceMessageEditPart) ((GraphicalEditPart) getSequenceMessageEditPart("m9").getTarget()).getSourceConnections().get(0);
        int endExecutionVerticalPositionBeforeMove = getSequenceMessageScreenVerticalPosition(endExecutionVerticalPartBeforeMove);
        editor.dragWithKeys(50, startExecutionVerticalPosition + 10, 50, startExecutionVerticalPosition + 10 + SPACE_TO_ADD, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        SequenceMessageEditPart endExecutionVerticalPartAfterMove = (SequenceMessageEditPart) ((GraphicalEditPart) getSequenceMessageEditPart("m9").getTarget()).getSourceConnections().get(0);
        int endExecutionVerticalPositionAfterMove = getSequenceMessageScreenVerticalPosition(endExecutionVerticalPartAfterMove);

        assertEquals("Wrong lifeling height: space is expected to be added.", lifelineAHeight + SPACE_TO_ADD, lifelineAHeightAfterDrag);
        assertEquals("The message named m9 has not its return message at the expected vertical position", endExecutionVerticalPositionBeforeMove + SPACE_TO_ADD, endExecutionVerticalPositionAfterMove);
    }

    /**
     * Verifies that vertical space insertion in a combined fragment is OK.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionInCombinedFragment() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        int startExecutionVerticalPosition = getSequenceMessageVerticalPosition("m9");
        SequenceMessageEditPart endExecutionVerticalPartBeforeMove = (SequenceMessageEditPart) ((GraphicalEditPart) getSequenceMessageEditPart("m9").getTarget()).getSourceConnections().get(0);
        int endExecutionVerticalPositionBeforeMove = getSequenceMessageScreenVerticalPosition(endExecutionVerticalPartBeforeMove);
        editor.dragWithKeys(50, startExecutionVerticalPosition - 35, 50, startExecutionVerticalPosition - 35 + SPACE_TO_ADD, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        SequenceMessageEditPart endExecutionVerticalPartAfterMove = (SequenceMessageEditPart) ((GraphicalEditPart) getSequenceMessageEditPart("m9").getTarget()).getSourceConnections().get(0);
        int endExecutionVerticalPositionAfterMove = getSequenceMessageScreenVerticalPosition(endExecutionVerticalPartAfterMove);

        assertEquals("Wrong lifeling height: space is expected to be added.", lifelineAHeight + SPACE_TO_ADD, lifelineAHeightAfterDrag);
        assertEquals("The message named m9 has not its return message at the expected vertical position", endExecutionVerticalPositionBeforeMove + SPACE_TO_ADD, endExecutionVerticalPositionAfterMove,
                2);
    }

    /**
     * Verifies that vertical space removal in an operand condition in a
     * combined fragment is OK.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalInOperandCondition() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        int startExecutionVerticalPosition = getSequenceMessageVerticalPosition("m9");
        SequenceMessageEditPart endExecutionVerticalPartBeforeMove = (SequenceMessageEditPart) ((GraphicalEditPart) getSequenceMessageEditPart("m9").getTarget()).getSourceConnections().get(0);
        int endExecutionVerticalPositionBeforeMove = getSequenceMessageScreenVerticalPosition(endExecutionVerticalPartBeforeMove);

        // drag with space available after last element on the lifeline
        editor.dragWithKeys(50, endExecutionVerticalPositionBeforeMove - 1, 50, endExecutionVerticalPositionBeforeMove - SPACE_TO_REMOVE, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        int endExecutionVerticalPositionAfterMove = getSequenceMessageScreenVerticalPosition(endExecutionVerticalPartBeforeMove);
        assertEquals("Wrong lifeling height: space is expected to be removed", lifelineAHeight - SPACE_TO_REMOVE, lifelineAHeightAfterDrag, 1);
        assertEquals("The message named m9 is not at the expected vertical position", startExecutionVerticalPosition, getSequenceMessageVerticalPosition("m9"));
        assertEquals("The first execution has not been resized.", endExecutionVerticalPositionBeforeMove - SPACE_TO_REMOVE, endExecutionVerticalPositionAfterMove);
    }

    private int getSequenceMessageScreenHorizontalPosition(SequenceMessageEditPart sequenceMessageEditPart) {
        Point location = sequenceMessageEditPart.getConnectionFigure().getPoints().getFirstPoint().getCopy();
        GraphicalHelper.logical2screen(location, sequenceMessageEditPart);
        return location.x;
    }

    /**
     * Verifies that vertical space removal in a combined fragment header is OK.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalInCombinedFragment() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);
        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        int startExecutionVerticalPosition = getSequenceMessageVerticalPosition("m9");

        editor.dragWithKeys(50, startExecutionVerticalPosition - 35, 50, startExecutionVerticalPosition - 35 - SPACE_TO_REMOVE, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        int lifelineAHeightAfterDrag = getLifelineLength(LIFELINE_B);
        int startExecutionVerticalPositionAfterMove = getSequenceMessageVerticalPosition("m9");
        assertEquals("Wrong lifeling height: space is expected to be removed", lifelineAHeight - SPACE_TO_REMOVE, lifelineAHeightAfterDrag, 1);
        assertEquals("The message named m9 is not at the expected vertical position", startExecutionVerticalPosition - SPACE_TO_REMOVE, startExecutionVerticalPositionAfterMove);
    }

    /**
     * Verifies that vertical space insertion in a State is OK.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionInState() throws Exception {

        maximizeEditor(editor);
        // Reveal state S1 to scroll on it
        editor.reveal("s1");
        SWTBotGefEditPart editPart = editor.getEditPart("s1", StateEditPart.class);
        StateEditPart s1 = (StateEditPart) editPart.part();
        Rectangle s1Bounds = getStateScreenBounds(s1);

        int xLocation = s1Bounds.getCenter().x;
        int yFromLocation = s1Bounds.getCenter().y;
        int yToLocation = yFromLocation + SPACE_TO_ADD;

        editor.dragWithKeys(xLocation, yFromLocation, xLocation, yToLocation, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        Rectangle s1BoundsAfterMove = getStateScreenBounds(s1);

        assertEquals("Wrong State height: space is expected to be added.", s1Bounds.height() + SPACE_TO_ADD, s1BoundsAfterMove.height());
    }

    /**
     * Verifies that vertical space removal in a State is OK.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalInState() throws Exception {

        maximizeEditor(editor);
        // Reveal state S1 to scroll on it
        editor.reveal("s1");
        SWTBotGefEditPart editPart = editor.getEditPart("s1", StateEditPart.class);
        StateEditPart s1 = (StateEditPart) editPart.part();
        Rectangle s1Bounds = getStateScreenBounds(s1);

        int xLocation = s1Bounds.getCenter().x;
        int yFromLocation = s1Bounds.getCenter().y;
        int yToLocation = yFromLocation - SPACE_TO_REMOVE;

        editor.dragWithKeys(xLocation, yFromLocation, xLocation, yToLocation, new AtomicBoolean(true), SWT.CTRL, SWT.SHIFT);
        SWTBotUtils.waitAllUiEvents();

        Rectangle s1BoundsAfterMove = getStateScreenBounds(s1);

        assertEquals("Wrong State height: space is expected to be removed.", s1Bounds.height() - SPACE_TO_REMOVE, s1BoundsAfterMove.height());
    }
}
