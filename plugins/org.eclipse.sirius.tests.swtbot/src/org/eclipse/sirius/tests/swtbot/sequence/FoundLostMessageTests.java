/*******************************************************************************
 * Copyright (c) 2017, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class for Found/Lost message creation
 * <ol>
 * <li>Test Found message creation</li>
 * <li>Test Found message creation with message re-ordering.</li>
 * <li>Test Found message move after creation.</li>
 * </ol>
 * 
 * @author jmallet
 */
public class FoundLostMessageTests extends AbstractLostFoundModelSequenceTests {

    /**
     * Edit part of the first participant.
     */
    private SWTBotGefEditPart instanceRoleABot;

    /**
     * Bounds of the editPart of the firstParticipant.
     */
    private Rectangle instanceRoleABounds;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.reveal(LIFELINE_1);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        // InstanceRoles
        instanceRoleABot = editor.getEditPart(LIFELINE_1);
        instanceRoleABounds = editor.getBounds(instanceRoleABot);
    }

    /**
     * Test create Found message.
     */
    public void test_Create_Found_Message_Creation() {
        Point centerM10ScreenPos = getSequenceMessageScreenCenteredPosition(TENTH_MESSAGE);
        Point centerM9ScreenPos = getSequenceMessageScreenCenteredPosition(NINETH_MESSAGE);

        // Creation of a message (m11 message)
        int yM10 = centerM10ScreenPos.y;
        int yM9 = centerM9ScreenPos.y;
        Point center = instanceRoleABounds.getCenter();
        Point newPosition = center.translate(0, (yM9 + yM10) / 2 - center.y);
        createMessage(InteractionsConstants.FOUND_READ_TOOL_ID, newPosition, newPosition);

        // Validates the position
        assertMessageVerticalPosition(ELEVENTH_MESSAGE, (yM9 + yM10) / 2);

        // create message with re-ordering (m12 message)
        newPosition = center.translate(0, (yM9 - 5) - center.y);
        createMessage(InteractionsConstants.FOUND_READ_TOOL_ID, newPosition, newPosition);

        // Check if there is an error in errorLog
        if (platformProblemsListener.doesAnErrorOccurs()) {
            fail("This Found read message creation should be done without error in errorlog.");
        }

        // Validates the position
        assertTrue("The m9 message must be moved of few points.", getSequenceMessageScreenCenteredPosition(NINETH_MESSAGE).y < centerM9ScreenPos.y + 20);

    }

    /**
     * Test move Found Message after its creation.
     */
    public void test_Move_Found_Message_Creation() {
        Point centerM10ScreenPos = getSequenceMessageScreenCenteredPosition(TENTH_MESSAGE);
        Point centerM9ScreenPos = getSequenceMessageScreenCenteredPosition(NINETH_MESSAGE);
        Point center = instanceRoleABounds.getCenter();

        // Creation of a message (m11 message)
        int yM10 = centerM10ScreenPos.y;
        int yM9 = centerM9ScreenPos.y;
        int yCenter = center.y;
        Point newPosition = center.translate(0, (yM9 + yM10) / 2 - yCenter);
        createMessage(InteractionsConstants.FOUND_READ_TOOL_ID, newPosition, newPosition);

        // compute new position of the created message
        Point centerM7ScreenPos = getSequenceMessageScreenCenteredPosition(SEVENTH_MESSAGE);
        int yM7 = centerM7ScreenPos.y;
        int newMoveVerticalPosition = (yCenter + yM7) / 2;

        // Move m9 message
        Point centerNewMessageScreenPos = getSequenceMessageScreenCenteredPosition(ELEVENTH_MESSAGE);
        editor.click(centerNewMessageScreenPos);
        editor.drag(centerNewMessageScreenPos, centerNewMessageScreenPos.x, newMoveVerticalPosition);

        // Validates the new position
        assertMessageVerticalPosition(ELEVENTH_MESSAGE, newMoveVerticalPosition);

        // validate that other positions are the same
        assertMessageVerticalPosition(SEVENTH_MESSAGE, centerM7ScreenPos.y);
        assertMessageVerticalPosition(TENTH_MESSAGE, centerM10ScreenPos.y);

    }

    @Override
    protected void tearDown() throws Exception {
        instanceRoleABot = null;

        instanceRoleABounds = null;

        super.tearDown();
    }
}
