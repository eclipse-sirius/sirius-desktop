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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;

/**
 * Tests the space addition to lifeline or message with the CTRL+SHIFT+Mouse drag.
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

    private static final int BIG_SPACE_TO_REMOVE = 217;

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
     * Verifies that vertical space insertion after the first execution on the line do add spaces between the last
     * message and the end of the line.
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
     * Verifies that vertical space insertion at the level of the first execution do add the space in the execution.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceInsertionInExecution() throws Exception {

        maximizeEditor(editor);
        // Reveal B to scroll to the left
        editor.reveal(LIFELINE_B);

        int lifelineAHeight = getLifelineLength(LIFELINE_B);
        Rectangle e1 = getExecutionScreenBounds(LIFELINE_B, 0).getCopy();
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
     * Verifies that vertical space insertion before life lines do add spaces between start of lines and the first
     * message on the line.
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
     * Verifies that vertical space removal after the first execution on the line do remove spaces between the last
     * message and the end of the line.
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
     * Verifies that vertical space removal at the level of the first execution do remove the space in the execution.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testVerticalSpaceRemovalInExecution() throws Exception {

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
     * Verifies that vertical space removal before life lines do remove spaces between start of lines and the first
     * message on the line.
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
     * Verifies that vertical space removal when the drag area cuts the limits of more than one element do remove space
     * to the lifeline after last element.
     * 
     * Verifies that vertical space removal does not remove anything when the drag area cuts the limits of more than one
     * element when no space is available after last element of lifeline.
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
}
