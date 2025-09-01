/*****************************************************************************
 * Copyright (c) 2025 CEA LIST.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Obeo - Initial API and implementation
 *****************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Gate;
import org.eclipse.sirius.sample.interactions.Participant;

/**
 * Tests oblique messages creation and move in Sequence Diagram between {@link Participant}, {@link Execution} and
 * {@link Gate}.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class ObliqueMessagesTests extends AbstractObliqueMessagesSequenceTests {

    public void testAsyncObliqueMessage_betweenLifelines() {
        Point sourceMessage = participantAData.bounds().getTop().getTranslated(0, 20);
        Point targetMessage = participantBData.bounds().getTop().getTranslated(0, 40);
        createAsyncCallOblique(sourceMessage, targetMessage);
        checkMessageHasBeenCreated();
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testAsyncObliqueMessage_betweenExecutions() {
        Point sourceMessage = execution1Data.bounds().getCenter();
        Point targetMessage = execution2Data.bounds().getCenter();
        createAsyncCallOblique(sourceMessage, targetMessage);
        checkMessageHasBeenCreated();
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testAsyncObliqueMessage_betweenGates() {
        Point sourceMessage = gate2Data.bounds().getCenter();
        Point targetMessage = gate1Data.bounds().getCenter();
        createAsyncCallOblique(sourceMessage, targetMessage);
        checkMessageHasNotBeenCreated("oblique_m1");
    }

    public void testAsyncObliqueMessage_fromExecutionToLifeline() {
        Point sourceMessage = execution1Data.bounds().getCenter();
        Point targetMessage = participantAData.bounds().getCenter();
        createAsyncCallOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testAsyncObliqueMessage_fromExecutionToGate() {
        Point sourceMessage = execution3Data.bounds().getCenter();
        Point targetMessage = gate1Data.bounds().getCenter();
        createAsyncCallOblique(sourceMessage, targetMessage);
        checkMessageHasNotBeenCreated("oblique_m1");
    }

    public void testAsyncObliqueMessage_fromLifelineToExecution() {
        Point sourceMessage = participantBData.bounds().getTop().getTranslated(0, 20);
        Point targetMessage = execution1Data.bounds().getCenter();
        createAsyncCallOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testAsyncObliqueMessage_fromLifelineToGate() {
        Point sourceMessage = new Point(participantAData.bounds().getCenter().x, operandData.bounds().getTop().y + 5);
        Point targetMessage = gate1Data.bounds().getCenter();
        createAsyncCallOblique(sourceMessage, targetMessage);
        checkMessageHasNotBeenCreated("oblique_m1");
    }

    public void testAsyncObliqueMessage_fromGateToLifeline() {
        Point sourceMessage = gate1Data.bounds().getCenter();
        Point targetMessage = new Point(participantAData.bounds().getBottom().x, combinedFragmentData.bounds().getBottom().y - 5);
        createAsyncCallOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testAsyncObliqueMessage_fromGateToExecution() {
        Point sourceMessage = gate2Data.bounds().getCenter();
        Point targetMessage = execution3Data.bounds().getCenter();
        createAsyncCallOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_betweenLifelines() {
        Point sourceMessage = participantAData.bounds().getTop().getTranslated(0, 20);
        Point targetMessage = participantBData.bounds().getTop().getTranslated(0, 40);
        createWriteOblique(sourceMessage, targetMessage);
        checkMessageHasBeenCreated();
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_betweenExecutions() {
        Point sourceMessage = execution1Data.bounds().getCenter();
        Point targetMessage = execution2Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        checkMessageHasBeenCreated();
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_betweenGates() {
        Point sourceMessage = gate2Data.bounds().getCenter();
        Point targetMessage = gate1Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_betweenGates_InteractionUse() {
        Point sourceMessage = gate3Data.bounds().getCenter();
        Point targetMessage = gate1Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage(60, true);
        moveSourceHandleMessage(-60, true);
        moveTargetHandleMessage(60, true);
    }

    public void testCreateInvalidWriteObliqueMessage_betweenGates_InteractionUse() {
        Point sourceMessage = gate1Data.bounds().getCenter();
        Point targetMessage = gate3Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        assertEquals("The diagram should have 0 message", 0, editor.getConnectionsEditPart().size());
    }

    public void testWriteObliqueMessage_fromExecutionToLifeline() {
        Point sourceMessage = execution1Data.bounds().getCenter();
        Point targetMessage = participantAData.bounds().getCenter().getTranslated(0, 10);
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_fromExecutionToGate() {
        Point sourceMessage = execution3Data.bounds().getCenter();
        Point targetMessage = gate1Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_fromLifelineToExecution() {
        Point sourceMessage = participantBData.bounds().getTop().getTranslated(0, 20);
        Point targetMessage = execution1Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_fromLifelineToGate() {
        Point sourceMessage = new Point(participantAData.bounds().getCenter().x, operandData.bounds().getTop().y + 5);
        Point targetMessage = gate1Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_fromGateToLifeline() {
        Point sourceMessage = gate2Data.bounds().getCenter();
        Point targetMessage = new Point(participantBData.bounds().getBottom().x, operandData.bounds().getBottom().y - 20);
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }

    public void testWriteObliqueMessage_fromGateToExecution() {
        Point sourceMessage = gate2Data.bounds().getCenter();
        Point targetMessage = execution3Data.bounds().getCenter();
        createWriteOblique(sourceMessage, targetMessage);
        moveMessage();
        moveSourceHandleMessage();
        moveTargetHandleMessage();
    }
}
