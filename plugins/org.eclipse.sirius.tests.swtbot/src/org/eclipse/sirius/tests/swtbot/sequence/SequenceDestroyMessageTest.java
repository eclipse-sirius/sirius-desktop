/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsNotDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Test class for creation message management
 * <ol>
 * <li>Creation</li>
 * <li>Deletion</li>
 * <li>Move</li>
 * <li>Undo</li>
 * <li>Redo</li>
 * <li>Zoom</li>
 * </ol>
 * 
 * @author smonnier
 */
public class SequenceDestroyMessageTest extends AbstractDefaultModelSequenceTests {

    private SWTBotGefEditPart instanceRoleABot;

    private SWTBotGefEditPart instanceRoleBBot;

    private Rectangle instanceRoleABounds;

    private Rectangle instanceRoleBBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        // InstanceRoles
        instanceRoleABot = editor.getEditPart(LIFELINE_A);
        instanceRoleBBot = editor.getEditPart(LIFELINE_B);

        instanceRoleABounds = editor.getBounds(instanceRoleABot);
        instanceRoleBBounds = editor.getBounds(instanceRoleBBot);

    }

    /**
     * Test method.
     */
    public void test_Creation() {
        doCommonCreate();

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of a destroy message
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineAPosition, 300, lifelineBPosition, 225);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(THIRD_DESTROY_MESSAGE, editor));

        // Validates the position of the return message
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);
        assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, 300);

        // Validates the position of the End of life node and the lifeline
        // length
        assertEndOfLifeIsValid(THIRD_DESTROY_MESSAGE, LIFELINE_B);
    }

    /**
     * Test destroy message creation from above the source lifeline of the
     * existing destroy message to the existing endOfLife, target of the
     * existing destroy message. This should be impossible.
     * 
     */
    public void _test_destroyMessageCreation_when_oneAlreadyExists() {
        Point sourceOfFirstDestroy = instanceRoleABounds.getCenter().translate(0, 200);
        Point targetOfFirstDestroy = instanceRoleBBounds.getCenter().translate(0, 200);
        ICondition condition = new OperationDoneCondition();
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, sourceOfFirstDestroy, targetOfFirstDestroy);
        bot.waitUntil(condition);

        SWTBotGefEditPart lifelineABot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart firstDestroyMessageBot = lifelineABot.sourceConnections().get(0);
        SWTBotGefEditPart firstEndOfLifeBot = firstDestroyMessageBot.target();

        Rectangle expectedDestroyMessageBounds = new Rectangle(sourceOfFirstDestroy.x + 2, sourceOfFirstDestroy.y, targetOfFirstDestroy.x - 26 - sourceOfFirstDestroy.x, 1);
        Assert.assertEquals(expectedDestroyMessageBounds, editor.getBounds(firstDestroyMessageBot));
        Assert.assertEquals(targetOfFirstDestroy, editor.getBounds(firstEndOfLifeBot).getCenter());

        Point sourceOfSecondDestroy = instanceRoleABounds.getCenter().translate(0, 100);
        Point targetOfSecondDestroy = editor.getBounds(firstEndOfLifeBot).getCenter();
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, sourceOfSecondDestroy, targetOfSecondDestroy);

        Assert.assertEquals("There should be only one end of life.", 1, instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(EndOfLifeEditPart.class)).size());
        Assert.assertEquals("There should be only one destroy message.", 1, lifelineABot.sourceConnections().size());

    }

    /**
     * Test that reflexive destroy message creation is possible and is correctly
     * displayed.
     */
    public void _test_ReflexiveDestroyMessageCreation() {
        Point sourceOfFirstDestroy = instanceRoleABounds.getCenter().translate(0, 200);
        Point targetOfFirstDestroy = instanceRoleABounds.getCenter().translate(0, 300);
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, sourceOfFirstDestroy, targetOfFirstDestroy);

        SWTBotGefEditPart lifelineABot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart firstDestroyMessageBot = lifelineABot.sourceConnections().get(0);
        SWTBotGefEditPart firstEndOfLifeBot = firstDestroyMessageBot.target();

        Rectangle firstDestroyMessageBounds = editor.getBounds(firstDestroyMessageBot);
        Assert.assertEquals(sourceOfFirstDestroy.y, firstDestroyMessageBounds.y);
        Assert.assertEquals(targetOfFirstDestroy.y, firstDestroyMessageBounds.getBottom().y);
        Assert.assertEquals(targetOfFirstDestroy.y, editor.getBounds(firstEndOfLifeBot).getCenter().y);
    }

    /**
     * Test method.
     */
    public void test_Deletion() {
        test_Creation();

        // Select the destroy message
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        editor.click(lifelineAPosition + 25, getSequenceMessageScreenVerticalPosition(THIRD_DESTROY_MESSAGE));

        // Deletion of the destroy message
        deleteSelectedElement();
        assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);

        // Validates the lenght of the lifeline is back to normal
        assertEquals("The lifeline " + LIFELINE_B + " does not have the expected length", 320, getLifelineLength(LIFELINE_B));
    }

    /**
     * Test method.
     */
    public void test_Ordering() {
        test_Creation();

        validateOrdering();
    }

    /**
     * Test method.
     */
    public void test_Edition() {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of a create message
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineAPosition, 150, lifelineBPosition, 150);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_DESTROY_MESSAGE, editor));

        editor.directEdgeEditTypeExtendedToBorderNodes(LIFELINE_A, LIFELINE_B, "destroy message 1");
        String newMessageLabel = FIRST_DESTROY_MESSAGE.replaceFirst("m_destroy1", "destroy message 1");
        assertNotNull("There is no message with the label ", getSequenceMessageEditPart(newMessageLabel));
    }

    /**
     * Test method.
     */
    public void test_CascadingDestroyMessages() {
    	
        doCreate2();

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);

        ICondition checkEditPartIsNotDisplayed = new CheckEditPartIsNotDisplayed("m_destroy4", editor);
        // Select the destroy message
        editor.select(FOURTH_DESTROY_MESSAGE);

        // Deletion of the destroy message
        deleteSelectedElement();
        bot.waitUntil(checkEditPartIsNotDisplayed);
        
        assertNoEditPartWithLabel(FOURTH_DESTROY_MESSAGE);

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);
        assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, 400);

        // Select the destroy message
        // editor.select(THIRD_DESTROY_MESSAGE);
        editor.click(lifelineAPosition + 25, getSequenceMessageScreenVerticalPosition(THIRD_DESTROY_MESSAGE));

        // Deletion of the destroy message
        deleteSelectedElement();
        assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);

        // Validates the lenght of the lifeline is back to normal
        assertEquals("The lifeline " + LIFELINE_B + " does not have the expected length", 320, getLifelineLength(LIFELINE_B));
        assertEquals("The lifeline " + NEW_LIFELINE + " does not have the expected length", getLifelineLength(LIFELINE_A), getLifelineLength(LIFELINE_C));
    }

    /**
     * Test method.
     */
    public void test_ArrangeAll() {
        doCreate2();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);
        // Arrange All
        arrangeAll();

        // Validates the graphical position
        int expectedMessagePosition = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + LayoutConstants.TIME_START_OFFSET;
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, expectedMessagePosition);

        expectedMessagePosition += getScreenSize(LIFELINE_B).height / 2 + LayoutConstants.TIME_START_OFFSET;
        assertMessageVerticalPosition(FIRST_MESSAGE, expectedMessagePosition);

        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(FOURTH_DESTROY_MESSAGE);
        Rectangle endOfLifeEditPartBounds = ((EndOfLifeEditPart) sequenceMessageEditPart.getTarget()).getFigure().getBounds();
        expectedMessagePosition += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + endOfLifeEditPartBounds.height / 2;
        assertMessageVerticalPosition(FOURTH_DESTROY_MESSAGE, expectedMessagePosition);

        sequenceMessageEditPart = getSequenceMessageEditPart(THIRD_DESTROY_MESSAGE);
        endOfLifeEditPartBounds = ((EndOfLifeEditPart) sequenceMessageEditPart.getTarget()).getFigure().getBounds();
        expectedMessagePosition += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + endOfLifeEditPartBounds.height / 2;
        assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, expectedMessagePosition);

        assertEndOfLifeIsValid(FOURTH_DESTROY_MESSAGE, LIFELINE_C);
        assertEndOfLifeIsValid(THIRD_DESTROY_MESSAGE, LIFELINE_B);

        // Validates the semantic ordering equals the graphical ordering
        validateOrdering();
    }

    /**
     * Test method.
     */
    public void test_Undo_Redo() {
        doCreate2();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);
        // Arrange All
        arrangeAll();
        
        editor.click(0, 0);

        // Undo Arrange All
        undo();

        // Undo THIRD_MESSAGE creation
        undo();

        // Undo destroy message creation
        undo();

        // Undo create message creation
        undo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));

        assertNoEditPartWithLabel(SECOND_CREATE_MESSAGE);
        assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);

        // Undo FIRST_MESSAGE creation
        undo();

        assertNoEditPartWithLabel(FIRST_MESSAGE);
        assertNoEditPartWithLabel(SECOND_CREATE_MESSAGE);
        assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);

        // Redo FIRST_MESSAGE creation
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 250, getSequenceMessageVerticalPosition(FIRST_MESSAGE));

        assertNoEditPartWithLabel(SECOND_CREATE_MESSAGE);
        assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);
        assertNoEditPartWithLabel(FOURTH_DESTROY_MESSAGE);

        // Redo SECOND_MESSAGE creation
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 250, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_CREATE_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_CREATE_MESSAGE));

        assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);
        assertNoEditPartWithLabel(FOURTH_DESTROY_MESSAGE);

        // Redo THIRD_MESSAGE destruction
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 250, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_CREATE_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_CREATE_MESSAGE));
        assertEquals("The message named " + THIRD_DESTROY_MESSAGE + " is not at the expected vertical position", 400, getSequenceMessageVerticalPosition(THIRD_DESTROY_MESSAGE));

        assertNoEditPartWithLabel(FOURTH_DESTROY_MESSAGE);
        
        // Redo FOURTH_MESSAGE destruction
        redo();
        
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 250, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_CREATE_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_CREATE_MESSAGE));
       assertEquals("The message named " + THIRD_DESTROY_MESSAGE + " is not at the expected vertical position", 400, getSequenceMessageVerticalPosition(THIRD_DESTROY_MESSAGE));
       assertEquals("The message named " + FOURTH_DESTROY_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FOURTH_DESTROY_MESSAGE));
       
        
        // Redo Arrange All
        redo();
        
        assertEquals("The message named " + SECOND_CREATE_MESSAGE + " is not at the expected vertical position", 130, getSequenceMessageVerticalPosition(SECOND_CREATE_MESSAGE));
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 185, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + FOURTH_DESTROY_MESSAGE + " is not at the expected vertical position", 230, getSequenceMessageVerticalPosition(FOURTH_DESTROY_MESSAGE));
        assertEquals("The message named " + THIRD_DESTROY_MESSAGE + " is not at the expected vertical position", 275, getSequenceMessageVerticalPosition(THIRD_DESTROY_MESSAGE));
    }

    /**
     * Test method.
     */
    public void test_Zoom() {
        try {
            ZoomLevel zoom50 = ZoomLevel.ZOOM_50;
            editor.zoom(ZoomLevel.ZOOM_50);
            // Reveal A to scroll to the left
            editor.reveal(LIFELINE_A);

            // Calculate the X position of the center of lifelines A and B

            int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
            int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

            // Creation of a message
            createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 125, lifelineBPosition, 125);

            // Validates the position of the return message
            assertMessageVerticalPosition(FIRST_MESSAGE, 125);

            // Creation of a create message
            createMessage(InteractionsConstants.CREATE_TOOL_ID, lifelineAPosition, 75, getScreenPosition(LIFELINE_B).x, getScreenPosition(LIFELINE_B).y);
            bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_CREATE_MESSAGE, editor));

            // Validates the position of the return message
            assertMessageVerticalPosition(FIRST_MESSAGE, 125);
            assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 75);

            // Try to add a create message on invalid range
            createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineAPosition, 75, lifelineBPosition, 125);
            assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);

            // Validates the position of the return message
            assertMessageVerticalPosition(FIRST_MESSAGE, 125);
            assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 75);

            // Creation of a destroy message
            createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineAPosition, 200, lifelineBPosition, 110);
            bot.waitUntil(new CheckMessageEditPartIsDisplayed(THIRD_DESTROY_MESSAGE, editor));

            // Validates the position of the return message
            assertMessageVerticalPosition(FIRST_MESSAGE, 125);
            assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 75);
            assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, 200);

            int newlifelinePosition = getLifelineScreenX(LIFELINE_C);

            // Creation of a second create message
            createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineBPosition, 150, newlifelinePosition, 175);

            // Validates the position of the return message
            assertMessageVerticalPosition(FIRST_MESSAGE, 125);
            assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 75);
            assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, 200);
            assertMessageVerticalPosition(FOURTH_DESTROY_MESSAGE, 150);

            // Validates the position of the End of life node and the lifeline
            // length
            assertEndOfLifeIsValid(THIRD_DESTROY_MESSAGE, LIFELINE_B);

            // Validates the position of the End of life node and the lifeline
            // length
            assertEndOfLifeIsValid(FOURTH_DESTROY_MESSAGE, LIFELINE_C);

            // Validates the semantic ordering equals the graphical ordering
            validateOrdering();

            // Click on the diagram to unfocus the created element
            editor.click(0, 0);
            // Arrange All
            arrangeAll();

            // Validates the graphical position
            int expectedMessagePosition = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + (int) (zoom50.getAmount() * LayoutConstants.TIME_START_OFFSET);
            assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, expectedMessagePosition);

            expectedMessagePosition += getScreenSize(LIFELINE_B).height / 2 + (int) (zoom50.getAmount() * LayoutConstants.TIME_START_OFFSET);
            assertMessageVerticalPosition(FIRST_MESSAGE, expectedMessagePosition);

            SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(FOURTH_DESTROY_MESSAGE);
            Rectangle endOfLifeEditPartBounds = ((EndOfLifeEditPart) sequenceMessageEditPart.getTarget()).getFigure().getBounds();
            expectedMessagePosition += (int) (zoom50.getAmount() * (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + endOfLifeEditPartBounds.height / 2));
            assertMessageVerticalPosition(FOURTH_DESTROY_MESSAGE, expectedMessagePosition);

            sequenceMessageEditPart = getSequenceMessageEditPart(THIRD_DESTROY_MESSAGE);
            endOfLifeEditPartBounds = ((EndOfLifeEditPart) sequenceMessageEditPart.getTarget()).getFigure().getBounds();
            expectedMessagePosition += (int) (zoom50.getAmount() * (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + endOfLifeEditPartBounds.height / 2));
            assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, expectedMessagePosition);

            assertEndOfLifeIsValid(FOURTH_DESTROY_MESSAGE, LIFELINE_C);
            assertEndOfLifeIsValid(THIRD_DESTROY_MESSAGE, LIFELINE_B);

            // Validates the semantic ordering equals the graphical ordering
            validateOrdering();
        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    private void doCommonCreate() {
        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of a message
        createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 250, lifelineBPosition, 250);

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);

        // Creation of a create message
        createMessage(InteractionsConstants.CREATE_TOOL_ID, lifelineAPosition, 150, getScreenPosition(LIFELINE_B).x, getScreenPosition(LIFELINE_B).y);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_CREATE_MESSAGE, editor));

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);

        // Try to add a destroy message on invalid range
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineAPosition, 150, lifelineBPosition, 250);
        assertNoEditPartWithLabel(THIRD_DESTROY_MESSAGE);

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);

    }

    private void doCreate2() {
        doCommonCreate();

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);
        int lifelineCPosition = getLifelineScreenX(LIFELINE_C);

        // Creation of a destroy message
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineAPosition, 400, lifelineBPosition, 225);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(THIRD_DESTROY_MESSAGE, editor));

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);
        assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, 400);

        // Creation of a second create message
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, lifelineBPosition, 300, lifelineCPosition, 350);

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, 250);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);
        assertMessageVerticalPosition(FOURTH_DESTROY_MESSAGE, 300);
        assertMessageVerticalPosition(THIRD_DESTROY_MESSAGE, 400);

        // Validates the position of the End of life nodes and the lifeline
        // length
        assertEndOfLifeIsValid(THIRD_DESTROY_MESSAGE, LIFELINE_B);
        assertEndOfLifeIsValid(FOURTH_DESTROY_MESSAGE, LIFELINE_C);

        // Validates the semantic ordering equals the graphical ordering
        validateOrdering();

    }

    private void assertEndOfLifeIsValid(String destructionMessageName, String lifelineName) {
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart(destructionMessageName);
        assertTrue("The destroy message " + destructionMessageName + " does not target an EndOfLifeEditPart", sequenceMessageEditPart.getTarget() instanceof EndOfLifeEditPart);

        validateSequenceMessageCenteredOnTarget(destructionMessageName);

        assertEquals("The lifeline " + lifelineName + " does not have the expected length", ((EndOfLifeEditPart) sequenceMessageEditPart.getTarget()).getFigure().getBounds().y
                - (getLogicalPosition(lifelineName).y + getLogicalSize(lifelineName).height), getLifelineLength(lifelineName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        instanceRoleABot = null;
        instanceRoleBBot = null;

        instanceRoleABounds = null;
        instanceRoleBBounds = null;

        super.tearDown();
    }
}
