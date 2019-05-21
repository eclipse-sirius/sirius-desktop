/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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

import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;

import com.google.common.collect.Iterables;

/**
 * Test class for return message management
 * 
 * @author smonnier
 */
public class SequenceReturnMessageTest extends AbstractDefaultModelSequenceTests {

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        closeOutline();
        super.onSetUpAfterOpeningDesignerPerspective();
    }

    @Override
    protected void tearDown() throws Exception {
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
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Deletion() throws Exception {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        editor.activateTool("Read");

        // Creation of a message
        editor.click(lifelineAPosition, 200);
        editor.click(lifelineBPosition, 250);

        int verticalPosition = getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE);

        // Validates the position
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 200, verticalPosition);

        editor.activateTool("Return");

        // Creation of a return message
        editor.click(lifelineAPosition, 300);
        editor.click(lifelineBPosition, 250);

        verticalPosition = getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE);

        // Validates the position of the return message
        assertEquals("The return message is not at the expected vertical position", 300, verticalPosition);

        // Deletion of the return message
        editor.click(getSequenceMessageScreenCenteredPosition(RETURN_MESSAGE));
        bot.menu("Edit").menu("Delete").click();

        SequenceMessageEditPart sequenceMessageEditPart = getFirstReturnMessage();
        assertNull(sequenceMessageEditPart);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Deletion_SyncCall() throws Exception {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, 200, lifelineBPosition, 250);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_SYNC_CALL, editor));

        int verticalPosition = getSequenceMessageScreenVerticalPosition(FIRST_SYNC_CALL);

        // Validates the position
        // TODO FIXME a Sync message is created a bit over where the user
        // clicked
        // assertEquals("The message named " + FIRST_SYNC_CALL +
        // " is not at the expected vertical position", 200, verticalPosition);

        // Validates a return message has been created after a read message
        assertNotNull("No return message have been found", getSequenceMessageEditPart(RETURN_MESSAGE));
        assertTrue("The return message is not after the invocation message", getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE) > verticalPosition);

        // Deletion of the return message
        editor.click(lifelineAPosition + 50, getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE));
        bot.menu("Edit").menu("Delete").click();

        SequenceMessageEditPart sequenceMessageEditPart = getFirstReturnMessage();
        assertNull(sequenceMessageEditPart);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Ordering() throws Exception {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of 3 message
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 300);
        editor.click(lifelineBPosition, 250);
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 250);
        editor.activateTool("Return");
        editor.click(lifelineAPosition, 250);
        editor.click(lifelineBPosition, 250);

        // Validates the graphical position
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertEquals("The return message is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE));
        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));

        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_ArrangeAll() throws Exception {
        maximizeEditor(editor);

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of 3 message
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 300);
        editor.click(lifelineBPosition, 250);
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 250);
        editor.activateTool("Return");
        editor.click(lifelineAPosition, 250);
        editor.click(lifelineBPosition, 250);

        // Validates the graphical position
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertEquals("The return message is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE));
        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));

        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);
        // Arrange All
        arrangeAll();
        bot.sleep(500);

        // Validates the graphical position
        int y = 130;
        assertMessageVerticalPosition(SECOND_MESSAGE, y);
        assertMessageVerticalPosition(RETURN_MESSAGE, y += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);
        assertMessageVerticalPosition(FIRST_MESSAGE, y += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);

        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Undo_Redo() throws Exception {
        maximizeEditor(editor);

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of 3 message
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 300);
        editor.click(lifelineBPosition, 250);
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 250);
        editor.activateTool("Return");
        editor.click(lifelineAPosition, 250);
        editor.click(lifelineBPosition, 250);

        // Validates the graphical position
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertEquals("The return message is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE));
        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));

        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);
        // Arrange All
        arrangeAll();
        bot.sleep(500);

        // Validates the graphical position
        int y = 130;
        assertMessageVerticalPosition(SECOND_MESSAGE, y);
        assertMessageVerticalPosition(RETURN_MESSAGE, y += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);
        assertMessageVerticalPosition(FIRST_MESSAGE, y += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);

        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

        // Undo Arrange All
        undo();

        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

        // Validates the graphical position
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertEquals("The return  message is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE));

        // Undo THIRD_MESSAGE creation
        undo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));

        assertNull("No return message should be found.", getSequenceMessageEditPart(RETURN_MESSAGE));

        // Undo SECOND_MESSAGE creation
        undo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));

        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNull("No return message should be found.", getSequenceMessageEditPart(RETURN_MESSAGE));

        // Undo FIRST_MESSAGE creation
        undo();

        assertNoEditPartWithLabel(FIRST_MESSAGE);
        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNull("No return message should be found.", getSequenceMessageEditPart(RETURN_MESSAGE));

        // Redo FIRST_MESSAGE creation
        redo();
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));

        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNull("No return message should be found.", getSequenceMessageEditPart(RETURN_MESSAGE));

        // Redo SECOND_MESSAGE creation
        redo();
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_MESSAGE));

        assertNull("No return message should be found.", getSequenceMessageEditPart(RETURN_MESSAGE));

        // Redo THIRD_MESSAGE creation
        redo();
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_MESSAGE));
        assertEquals("The return message is not at the expected vertical position", 250, getSequenceMessageVerticalPosition(RETURN_MESSAGE));

        // Redo Arrange All
        redo();
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 130, getSequenceMessageVerticalPosition(SECOND_MESSAGE));
        assertEquals("The return message is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(RETURN_MESSAGE));
        assertEquals("The return named " + FIRST_MESSAGE + " is not at the expected vertical position", 170, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Zoom() throws Exception {
        try {
            editor.zoom(ZoomLevel.ZOOM_50);
            // Reveal A to scroll to the left
            editor.reveal(LIFELINE_A);

            // Calculate the X position of the center of lifelines A and B
            int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
            int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

            // Creation of 3 message
            editor.activateTool("Read");
            editor.click(lifelineAPosition, 150);
            editor.click(lifelineBPosition, 125);
            editor.activateTool("Read");
            editor.click(lifelineAPosition, 75);
            editor.click(lifelineBPosition, 125);
            editor.activateTool("Return");
            editor.click(lifelineAPosition, 125);
            editor.click(lifelineBPosition, 125);

            // Validates the graphical position
            assertNotNull("The message named " + FIRST_MESSAGE + " has not been created", getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
            assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
            assertNotNull("The message named " + SECOND_MESSAGE + " has not been created", getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
            assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 75, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
            assertNotNull("The return message has not been created", getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE));
            assertEquals("The return message is not at the expected vertical position", 125, getSequenceMessageScreenVerticalPosition(RETURN_MESSAGE));
            SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));

            assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

            SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

            // Validates the semantic ordering equals the graphical ordering
            assertTrue("The semantic ordering does not match the graphical ordering",
                    Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));
        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }
}
