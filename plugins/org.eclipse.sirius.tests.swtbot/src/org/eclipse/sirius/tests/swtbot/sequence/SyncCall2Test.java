/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberExecutionOnLifeline;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfChildren;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test class for syncCall
 * 
 * @author smonnier, mporhel
 */
public class SyncCall2Test extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "delete/2305/";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);
    }

    /**
     * Test of VP-1299. Moving a sync call out of its parent execution, with
     * auto expand. on its parent.
     */
    public void test_SyncCall_Insertion_Expand_Reparent() {
        // Parts
        ExecutionEditPart parentExec = getExecutionEditPart(LIFELINE_B, 0);
        ExecutionEditPart mainMovedExec = getExecutionEditPart(LIFELINE_B, 1);
        ExecutionEditPart movedChildExec1 = getExecutionEditPart(LIFELINE_B, 2);
        ExecutionEditPart movedChildExec2 = getExecutionEditPart(LIFELINE_B, 3);
        ExecutionEditPart movedChildExec3 = getExecutionEditPart(LIFELINE_B, 4);
        SequenceMessageEditPart m1 = getSequenceMessageEditPart(FIRST_MESSAGE_SYNC_CALL);
        SequenceMessageEditPart m2 = getReturnSyncCall(LIFELINE_B, 0);
        SequenceMessageEditPart m3 = getSequenceMessageEditPart(THIRD_MESSAGE_SYNC_CALL);
        SequenceMessageEditPart m4 = getReturnSyncCall(LIFELINE_B, 1);
        SequenceMessageEditPart m5 = getSequenceMessageEditPart(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B);
        SequenceMessageEditPart m6 = getReturnSyncCall(LIFELINE_B, 2);
        SequenceMessageEditPart m7 = getSequenceMessageEditPart(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B);
        SequenceMessageEditPart m8 = getReturnSyncCall(LIFELINE_B, 4);
        SequenceMessageEditPart m9 = getSequenceMessageEditPart(NINETH_MESSAGE_ON_LIFELINE_C);
        SequenceMessageEditPart m10 = getSequenceMessageEditPart(TENTH_MESSAGE);
        SequenceMessageEditPart m11 = getSequenceMessageEditPart(ELEVENTH_MESSAGE);
        SequenceMessageEditPart m12 = getSequenceMessageEditPart(TWELFTH_MESSAGE);
        SequenceMessageEditPart m13 = getSequenceMessageEditPart(THIRTEENTH_MESSAGE);
        SequenceMessageEditPart m14 = getSequenceMessageEditPart(FOURTEENTH_MESSAGE);
        SequenceMessageEditPart m15 = getSequenceMessageEditPart(FIFTEENTH_MESSAGE);
        SequenceMessageEditPart m16 = getSequenceMessageEditPart(SIXTEENTH_MESSAGE);

        // Bots
        SWTBotGefEditPart parentExecBot = getBotEditPart(parentExec);
        SWTBotGefEditPart mainMovedExecBot = getBotEditPart(mainMovedExec);

        // Initial bounds
        Rectangle parentExecBounds = getExecutionScreenBounds(parentExec);
        Rectangle mainMovedExecBounds = getExecutionScreenBounds(mainMovedExec);
        Rectangle movedChildExec1Bounds = getExecutionScreenBounds(movedChildExec1);
        Rectangle movedChildExec2Bounds = getExecutionScreenBounds(movedChildExec2);
        Rectangle movedChildExec3Bounds = getExecutionScreenBounds(movedChildExec3);
        int m9Y = getSequenceMessageScreenVerticalPosition(m9);
        int m10Y = getSequenceMessageScreenVerticalPosition(m10);
        int m11Y = getSequenceMessageScreenVerticalPosition(m11);
        int m12Y = getSequenceMessageScreenVerticalPosition(m12);
        int m13Y = getSequenceMessageScreenVerticalPosition(m13);
        int m14Y = getSequenceMessageScreenVerticalPosition(m14);
        int m15Y = getSequenceMessageScreenVerticalPosition(m15);
        int m16Y = getSequenceMessageScreenVerticalPosition(m16);

        validateOrdering(34);

        // Move the execution juste before its parent.
        Point targetPoint = parentExecBounds.getTop().getTranslated(new Dimension(0, -10));
        int dY = targetPoint.y - mainMovedExecBounds.y;
        // Reparent -> new editPart -> check on the parent which will be shift
        ICondition movedExec = new CheckNumberOfChildren(parentExecBot, ExecutionEditPart.class, 0);
        editor.drag(mainMovedExecBot, targetPoint);
        bot.waitUntil(movedExec);

        // ExpectedEffect : shift of the parent by the auto-expand, move of the
        // other parts

        parentExecBounds.y += mainMovedExecBounds.height;
        mainMovedExecBounds.y = targetPoint.y;
        movedChildExec1Bounds.y += dY;
        movedChildExec2Bounds.y += dY;
        movedChildExec3Bounds.y += dY;
        mainMovedExecBounds.x -= 15;
        movedChildExec1Bounds.x -= 15;
        movedChildExec2Bounds.x -= 15;
        movedChildExec3Bounds.x -= 15;
        m9Y += dY;
        m10Y += dY;
        m11Y += dY;
        m12Y += dY;
        m13Y += dY;
        m14Y += dY;
        m15Y += dY;
        m16Y += dY;

        // Check deactivation of reparented events
        assertFalse(mainMovedExec.isActive());
        assertFalse(movedChildExec1.isActive());
        assertFalse(movedChildExec2.isActive());
        assertFalse(movedChildExec3.isActive());

        // Get the new edit parts, the edit parts keep their previous index
        mainMovedExec = getExecutionEditPart(LIFELINE_B, 1);
        movedChildExec1 = getExecutionEditPart(LIFELINE_B, 2);
        movedChildExec2 = getExecutionEditPart(LIFELINE_B, 3);
        movedChildExec3 = getExecutionEditPart(LIFELINE_B, 4);

        // Check activation of reparented events
        assertTrue(mainMovedExec.isActive());
        assertTrue(movedChildExec1.isActive());
        assertTrue(movedChildExec2.isActive());
        assertTrue(movedChildExec3.isActive());

        // Check positions
        assertExecutionHasValidScreenBounds(parentExec, parentExecBounds);
        assertExecutionHasValidScreenBounds(mainMovedExec, mainMovedExecBounds);
        assertExecutionHasValidScreenBounds(movedChildExec1, movedChildExec1Bounds);
        assertExecutionHasValidScreenBounds(movedChildExec2, movedChildExec2Bounds);
        assertExecutionHasValidScreenBounds(movedChildExec3, movedChildExec3Bounds);
        assertMessageVerticalPosition(m1, parentExecBounds.y);
        assertMessageVerticalPosition(m2, parentExecBounds.bottom());
        assertMessageVerticalPosition(m3, mainMovedExecBounds.y);
        assertMessageVerticalPosition(m4, mainMovedExecBounds.bottom());
        assertMessageVerticalPosition(m5, movedChildExec1Bounds.y);
        assertMessageVerticalPosition(m6, movedChildExec1Bounds.bottom());
        assertMessageVerticalPosition(m7, movedChildExec3Bounds.y);
        assertMessageVerticalPosition(m8, movedChildExec3Bounds.bottom());
        assertMessageVerticalPosition(m9, m9Y);
        assertMessageVerticalPosition(m10, m10Y);
        assertMessageVerticalPosition(m11, m11Y);
        assertMessageVerticalPosition(m12, m12Y);
        assertMessageVerticalPosition(m13, m13Y);
        assertMessageVerticalPosition(m14, m14Y);
        assertMessageVerticalPosition(m15, m15Y);
        assertMessageVerticalPosition(m16, m16Y);
    }

    /**
     * Deleting a sync call have to reconnect its sub event
     * on its parent.
     * 
     */
    public void test_SyncCall_Delete_And_Reconnect_Sub_Event() {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        
        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);
        int lifelineCPosition = getLifelineScreenX(LIFELINE_C);

        Point firstExecutionScreenPosition = new Point(270, 156);
        Dimension firstExecutionDimension = new Dimension(LayoutConstants.DEFAULT_EXECUTION_WIDTH, 427);
        Point secondExecutionScreenPosition = new Point(285, 186);
        Dimension secondExecutionDimension = new Dimension(LayoutConstants.DEFAULT_EXECUTION_WIDTH, 380);
        Point thirdExecutionScreenPosition = new Point(300, 218);
        Dimension thirdExecutionDimension = new Dimension(LayoutConstants.DEFAULT_EXECUTION_WIDTH, 83);
        Point fourthExecutionScreenPosition = new Point(300, 371);
        Dimension fourthExecutionDimension = new Dimension(LayoutConstants.DEFAULT_EXECUTION_WIDTH, 182);
        Point fifthExecutionScreenPosition = new Point(315, 402);
        Dimension fifthExecutionDimension = new Dimension(LayoutConstants.DEFAULT_EXECUTION_WIDTH, 78);

        int m9ExpectedVerticalPosition = 248;
        int m10ExpectedVerticalPosition = 273;
        int m11ExpectedVerticalPosition = 325;
        int m12ExpectedVerticalPosition = 361;
        int m13ExpectedVerticalPosition = 433;
        int m14ExpectedVerticalPosition = 458;
        int m15ExpectedVerticalPosition = 503;
        int m16ExpectedVerticalPosition = 532;

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 2).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 2).y);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fourthExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 3).x);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 3).y);
        assertEquals("The execution index 4 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fifthExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 4).x);
        assertEquals("The execution index 4 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fifthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 4).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 2).getCopy().height);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 3).getCopy().height);
        assertEquals("The execution index 4 on lifeline " + LIFELINE_B + " has not the expected height", fifthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 4).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 2).getCopy().width);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 3).getCopy().width);
        assertEquals("The execution index 4 on lifeline " + LIFELINE_B + " has not the expected width", fifthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 4).getCopy().width);

        // Validate the positions of the messages
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y,
                getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_SYNC_CALL));
        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageLastBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL).x);
        assertEquals("The return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + getExecutionScreenDimension(LIFELINE_B, 1).height, getReturnSyncCallScreenPosition(LIFELINE_B, 1));
        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 1)).x);

        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y,
                getSequenceMessageScreenVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y
                + getExecutionScreenDimension(LIFELINE_B, 2).height, getReturnSyncCallScreenPosition(LIFELINE_B, 2));
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 2).x + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 2)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 2)).x);

        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 4).y,
                getSequenceMessageScreenVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 4).x
                + getExecutionScreenDimension(LIFELINE_B, 4).width, getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 4).y
                + getExecutionScreenDimension(LIFELINE_B, 4).height, getReturnSyncCallScreenPosition(LIFELINE_B, 4));
        assertEquals("The first bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 4).x + getExecutionScreenDimension(LIFELINE_B, 4).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 4)).x);
        assertEquals("The last bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 4)).x);

        assertEquals("The message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", m9ExpectedVerticalPosition,
                getSequenceMessageScreenVerticalPosition(NINETH_MESSAGE_ON_LIFELINE_C));
        assertEquals("The first bendpoint of the message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageFirstBendpointScreenPosition(NINETH_MESSAGE_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(NINETH_MESSAGE_ON_LIFELINE_C).x);

        assertEquals("The message named " + TENTH_MESSAGE + " is not at the expected vertical position", m10ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(TENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + TENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(TENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + TENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageLastBendpointScreenPosition(TENTH_MESSAGE).x);

        assertEquals("The message named " + ELEVENTH_MESSAGE + " is not at the expected vertical position", m11ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(ELEVENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + ELEVENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(ELEVENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + ELEVENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageLastBendpointScreenPosition(ELEVENTH_MESSAGE).x);

        assertEquals("The message named " + TWELFTH_MESSAGE + " is not at the expected vertical position", m12ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(TWELFTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + TWELFTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageFirstBendpointScreenPosition(TWELFTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + TWELFTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(TWELFTH_MESSAGE).x);

        assertEquals("The message named " + THIRTEENTH_MESSAGE + " is not at the expected vertical position", m13ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(THIRTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + THIRTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(THIRTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + THIRTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 4).x
                + getExecutionScreenDimension(LIFELINE_B, 4).width, getSequenceMessageLastBendpointScreenPosition(THIRTEENTH_MESSAGE).x);

        assertEquals("The message named " + FOURTEENTH_MESSAGE + " is not at the expected vertical position", m14ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(FOURTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + FOURTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 4).x
                + getExecutionScreenDimension(LIFELINE_B, 4).width, getSequenceMessageFirstBendpointScreenPosition(FOURTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + FOURTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(FOURTEENTH_MESSAGE).x);

        assertEquals("The message named " + FIFTEENTH_MESSAGE + " is not at the expected vertical position", m15ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(FIFTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + FIFTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + FIFTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 3).x
                + getExecutionScreenDimension(LIFELINE_B, 3).width, getSequenceMessageLastBendpointScreenPosition(FIFTEENTH_MESSAGE).x);

        assertEquals("The message named " + SIXTEENTH_MESSAGE + " is not at the expected vertical position", m16ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(SIXTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + SIXTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 3).x
                + getExecutionScreenDimension(LIFELINE_B, 3).width, getSequenceMessageFirstBendpointScreenPosition(SIXTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + SIXTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(SIXTEENTH_MESSAGE).x);

        validateOrdering(34);

        // Delete the first execution on the main execution, therefore the
        // second execution on lifeline B
        // First we delete its invocation message
        editor.click(getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE_SYNC_CALL));
        deleteSelectedElement();

        // Then we delete its return message
        editor.click(getSequenceMessageScreenCenteredPosition(getReturnSyncCall(LIFELINE_B, 1)));
        deleteSelectedElement();

        selectEditParts(getExecutionEditPart(LIFELINE_B, 1));

        CheckNumberExecutionOnLifeline checkExecutionIsDeleted = new CheckNumberExecutionOnLifeline(LIFELINE_B, 4, editor);
        deleteSelectedElement();
        bot.waitUntil(checkExecutionIsDeleted);

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 2).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 2).y);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fourthExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 3).x);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fifthExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 3).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        // assertEquals("The execution index 1 on lifeline " + LIFELINE_B +
        // " has not the expected height", secondExecutionDimension.height,
        // getExecutionScreenDimension(LIFELINE_B, 1).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 2).getCopy().height);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " has not the expected height", fifthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 3).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        // assertEquals("The execution index 1 on lifeline " + LIFELINE_B +
        // " has not the expected width", secondExecutionDimension.width,
        // getExecutionScreenDimension(LIFELINE_B, 1).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 2).getCopy().width);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " has not the expected width", fifthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 3).getCopy().width);

        // Validate the positions of the messages
        assertEquals("The message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL));
        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y,
                getSequenceMessageScreenVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 1).y
                + getExecutionScreenDimension(LIFELINE_B, 1).height, getReturnSyncCallScreenPosition(LIFELINE_B, 1));
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 1).x + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 1)).x);

        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 3).y,
                getSequenceMessageScreenVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 3).x
                + getExecutionScreenDimension(LIFELINE_B, 3).width, getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 3).y
                + getExecutionScreenDimension(LIFELINE_B, 3).height, getReturnSyncCallScreenPosition(LIFELINE_B, 3));
        assertEquals("The first bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 3).x + getExecutionScreenDimension(LIFELINE_B, 3).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 3)).x);
        assertEquals("The last bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 3)).x);

        assertEquals("The message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", m9ExpectedVerticalPosition,
                getSequenceMessageScreenVerticalPosition(NINETH_MESSAGE_ON_LIFELINE_C));
        assertEquals("The first bendpoint of the message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageFirstBendpointScreenPosition(NINETH_MESSAGE_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(NINETH_MESSAGE_ON_LIFELINE_C).x);

        assertEquals("The message named " + TENTH_MESSAGE + " is not at the expected vertical position", m10ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(TENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + TENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(TENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + TENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageLastBendpointScreenPosition(TENTH_MESSAGE).x);

        assertEquals("The message named " + ELEVENTH_MESSAGE + " is not at the expected vertical position", m11ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(ELEVENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + ELEVENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(ELEVENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + ELEVENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(ELEVENTH_MESSAGE).x);

        assertEquals("The message named " + TWELFTH_MESSAGE + " is not at the expected vertical position", m12ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(TWELFTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + TWELFTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(TWELFTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + TWELFTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(TWELFTH_MESSAGE).x);

        assertEquals("The message named " + THIRTEENTH_MESSAGE + " is not at the expected vertical position", m13ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(THIRTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + THIRTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(THIRTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + THIRTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 3).x
                + getExecutionScreenDimension(LIFELINE_B, 3).width, getSequenceMessageLastBendpointScreenPosition(THIRTEENTH_MESSAGE).x);

        assertEquals("The message named " + FOURTEENTH_MESSAGE + " is not at the expected vertical position", m14ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(FOURTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + FOURTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 3).x
                + getExecutionScreenDimension(LIFELINE_B, 3).width, getSequenceMessageFirstBendpointScreenPosition(FOURTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + FOURTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(FOURTEENTH_MESSAGE).x);

        assertEquals("The message named " + FIFTEENTH_MESSAGE + " is not at the expected vertical position", m15ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(FIFTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + FIFTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + FIFTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageLastBendpointScreenPosition(FIFTEENTH_MESSAGE).x);

        assertEquals("The message named " + SIXTEENTH_MESSAGE + " is not at the expected vertical position", m16ExpectedVerticalPosition, getSequenceMessageScreenVerticalPosition(SIXTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + SIXTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageFirstBendpointScreenPosition(SIXTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + SIXTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(SIXTEENTH_MESSAGE).x);

        validateOrdering(30);

        // Delete the first execution on the main execution, therefore the
        // second execution on lifeline B
        // First we delete its invocation message
        editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE_SYNC_CALL));
        deleteSelectedElement();

        // Then we delete its return message
        SequenceMessageEditPart returnSyncCall = getReturnSyncCall(LIFELINE_B, 0);
        editor.click(getSequenceMessageScreenCenteredPosition(returnSyncCall));
        editor.bot().waitUntil(new CheckSelectedCondition(editor, returnSyncCall));
        deleteSelectedElement();

        selectEditParts(getExecutionEditPart(LIFELINE_B, 0));

        checkExecutionIsDeleted = new CheckNumberExecutionOnLifeline(LIFELINE_B, 3, editor);
        deleteSelectedElement();
        bot.waitUntil(checkExecutionIsDeleted);

        // Validates the position of the execution
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionLogicalPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", thirdExecutionScreenPosition.y, getExecutionLogicalPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionLogicalPosition(LIFELINE_B, 1).x);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fourthExecutionScreenPosition.y, getExecutionLogicalPosition(LIFELINE_B, 1).y);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionLogicalPosition(LIFELINE_B, 2).x);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " is not at the expected vertical position", fifthExecutionScreenPosition.y, getExecutionLogicalPosition(LIFELINE_B, 2).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", thirdExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected height", fourthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().height);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " has not the expected height", fifthExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 2).getCopy().height);

        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected width", thirdExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 2 on lifeline " + LIFELINE_B + " has not the expected width", fourthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().width);
        assertEquals("The execution index 3 on lifeline " + LIFELINE_B + " has not the expected width", fifthExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 2).getCopy().width);


        assertEquals("The message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y,
                getSequenceMessageScreenVerticalPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The first bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The last bendpoint of the message named " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 0).y
                + getExecutionScreenDimension(LIFELINE_B, 0).height, getReturnSyncCallScreenPosition(LIFELINE_B, 0));
        assertEquals("The first bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 0).x + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y,
                getSequenceMessageScreenVerticalPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B));
        assertEquals("The first bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The last bendpoint of the message named " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageLastBendpointScreenPosition(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B).x);
        assertEquals("The return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected vertical position", getExecutionScreenPosition(LIFELINE_B, 2).y
                + getExecutionScreenDimension(LIFELINE_B, 2).height, getReturnSyncCallScreenPosition(LIFELINE_B, 2));
        assertEquals("The first bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position",
                getExecutionScreenPosition(LIFELINE_B, 2).x + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 2)).x);
        assertEquals("The last bendpoint of the return message linked to " + SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 2)).x);

        assertEquals("The message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", m9ExpectedVerticalPosition,
                getSequenceMessageVerticalPosition(NINETH_MESSAGE_ON_LIFELINE_C));
        assertEquals("The first bendpoint of the message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageFirstBendpointScreenPosition(NINETH_MESSAGE_ON_LIFELINE_C).x);
        assertEquals("The last bendpoint of the message named " + NINETH_MESSAGE_ON_LIFELINE_C + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(NINETH_MESSAGE_ON_LIFELINE_C).x);

        assertEquals("The message named " + TENTH_MESSAGE + " is not at the expected vertical position", m10ExpectedVerticalPosition, getSequenceMessageVerticalPosition(TENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + TENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(TENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + TENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x
                + getExecutionScreenDimension(LIFELINE_B, 0).width, getSequenceMessageLastBendpointScreenPosition(TENTH_MESSAGE).x);

        assertEquals("The message named " + ELEVENTH_MESSAGE + " is not at the expected vertical position", m11ExpectedVerticalPosition, getSequenceMessageVerticalPosition(ELEVENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + ELEVENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(ELEVENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + ELEVENTH_MESSAGE + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(ELEVENTH_MESSAGE).x);

        assertEquals("The message named " + TWELFTH_MESSAGE + " is not at the expected vertical position", m12ExpectedVerticalPosition, getSequenceMessageVerticalPosition(TWELFTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + TWELFTH_MESSAGE + " is not at the expected horizontal position", lifelineBPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(TWELFTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + TWELFTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(TWELFTH_MESSAGE).x);

        assertEquals("The message named " + THIRTEENTH_MESSAGE + " is not at the expected vertical position", m13ExpectedVerticalPosition, getSequenceMessageVerticalPosition(THIRTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + THIRTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(THIRTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + THIRTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageLastBendpointScreenPosition(THIRTEENTH_MESSAGE).x);

        assertEquals("The message named " + FOURTEENTH_MESSAGE + " is not at the expected vertical position", m14ExpectedVerticalPosition, getSequenceMessageVerticalPosition(FOURTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + FOURTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 2).x
                + getExecutionScreenDimension(LIFELINE_B, 2).width, getSequenceMessageFirstBendpointScreenPosition(FOURTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + FOURTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(FOURTEENTH_MESSAGE).x);

        assertEquals("The message named " + FIFTEENTH_MESSAGE + " is not at the expected vertical position", m15ExpectedVerticalPosition, getSequenceMessageVerticalPosition(FIFTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + FIFTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(FIFTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + FIFTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageLastBendpointScreenPosition(FIFTEENTH_MESSAGE).x);

        assertEquals("The message named " + SIXTEENTH_MESSAGE + " is not at the expected vertical position", m16ExpectedVerticalPosition, getSequenceMessageVerticalPosition(SIXTEENTH_MESSAGE));
        assertEquals("The first bendpoint of the message named " + SIXTEENTH_MESSAGE + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageFirstBendpointScreenPosition(SIXTEENTH_MESSAGE).x);
        assertEquals("The last bendpoint of the message named " + SIXTEENTH_MESSAGE + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(SIXTEENTH_MESSAGE).x);

        validateOrdering(26);
    }
}
