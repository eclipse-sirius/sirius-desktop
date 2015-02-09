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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

import com.google.common.collect.Iterables;

/**
 * Test class for creation message management
 * <ol>
 * <li>Test create message creation</li>
 * <ol>
 * <li>Test create message creation on empty diagram</li>
 * <li>Test create message creation on empty diagram</li>
 * </ol>
 * <li>Test create message move</li> </ol>
 * 
 * @author smonnier
 */
public class CreateMessageTests extends AbstractDefaultModelSequenceTests {

    private SWTBotGefEditPart instanceRoleABot;

    private SWTBotGefEditPart instanceRoleBBot;

    private SWTBotGefEditPart instanceRoleCBot;

    private Rectangle instanceRoleABounds;

    private Rectangle instanceRoleBBounds;

    private Rectangle instanceRoleCBounds;

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
        instanceRoleCBot = editor.getEditPart(LIFELINE_C);

        instanceRoleABounds = editor.getBounds(instanceRoleABot);
        instanceRoleBBounds = editor.getBounds(instanceRoleBBot);
        instanceRoleCBounds = editor.getBounds(instanceRoleCBot);

    }

    /**
     * Test method.
     */
    public void test_Create_Message_Creation() {
        // Creation of a message
        Point start = instanceRoleABounds.getCenter().translate(0, 200);
        Point end = instanceRoleBBounds.getCenter().translate(0, 200);
        createMessage(InteractionsConstants.READ_TOOL_ID, start, end);

        SWTBotGefEditPart lifelineABot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart readMessageBot = lifelineABot.sourceConnections().get(0);
        Rectangle readMessageBounds = editor.getBounds(readMessageBot);

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, start.y);

        // Try to add a create message on invalid range
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start.getTranslated(0, 50), instanceRoleBBounds.getCenter());
        assertNoEditPartWithLabel(SECOND_CREATE_MESSAGE);

        // Creation of a create message
        int dy = 100;
        Point startOfCreateMessage = instanceRoleABounds.getCenter().translate(0, dy);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfCreateMessage, instanceRoleBBounds.getCenter());
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_CREATE_MESSAGE, editor));

        SWTBotGefConnectionEditPart createMessageBot = instanceRoleBBot.parent().targetConnections().get(0);

        // Validates the position of the return message
        assertMessageVerticalPosition(FIRST_MESSAGE, readMessageBounds.y);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, startOfCreateMessage.y);

        validateOrdering();

        // Deletion of the create message
        createMessageBot.select();
        deleteSelectedElement();

        assertNoEditPartWithLabel(SECOND_CREATE_MESSAGE);

        // Validates the position of the lifeline is back to normal
        assertEquals("The lifeline " + LIFELINE_A + " is not at the expected vertical position", instanceRoleABounds, editor.getBounds(instanceRoleABot));
        assertEquals("The lifeline " + LIFELINE_B + " is not at the expected vertical position", instanceRoleBBounds, editor.getBounds(instanceRoleBBot));

    }

    /**
     * Test that create message creation from LIFELINE_B to LIFELINE_A while
     * there exists already a create message from LIFELINE_A to LIFELINE_B is
     * not possible.
     */
    public void test_Simple_Cyclic_Creation_NotPossible() {
        Point start = instanceRoleABounds.getCenter().translate(0, 100);
        Point end = instanceRoleBBounds.getCenter();// .translate(0, 100);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);

        SWTBotGefConnectionEditPart createMessageOfBBot = instanceRoleBBot.parent().targetConnections().get(0);
        Rectangle createMessageOfBBounds = editor.getBounds(createMessageOfBBot);
        Rectangle newInstanceRoleBBounds = editor.getBounds(instanceRoleBBot);

        Rectangle expectedCreateMessageOfBBounds = new Rectangle(start.getTranslated(2, 0), new Dimension(instanceRoleBBounds.x - (start.x + 1), 1));
        Assert.assertEquals(expectedCreateMessageOfBBounds, createMessageOfBBounds);
        int dy = start.y - (instanceRoleBBounds.y + instanceRoleBBounds.height / 2);
        Assert.assertEquals(instanceRoleBBounds.getTranslated(0, dy), newInstanceRoleBBounds);

        start = newInstanceRoleBBounds.getCenter().translate(0, 100);
        end = instanceRoleABounds.getCenter();// .translate(0, 100);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);

        Assert.assertEquals(instanceRoleABounds, editor.getBounds(instanceRoleABot));
        Assert.assertEquals(instanceRoleBBounds.getTranslated(0, dy), editor.getBounds(instanceRoleBBot));
        Assert.assertEquals(expectedCreateMessageOfBBounds, editor.getBounds(createMessageOfBBot));
    }

    /**
     * Test that create message creation from LIFELINE_C to LIFELINE_A while
     * there exists already a first create message from LIFELINE_A to LIFELINE_B
     * and a second from LIFELINE_B to LIFELINE_C is not possible.
     */
    public void test_Complex_Cyclic_Creation_NotPossible() {
        // Create first create message
        Point start = instanceRoleABounds.getCenter().translate(0, 100);
        Point end = instanceRoleBBounds.getCenter();// .translate(0, 100);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);

        SWTBotGefConnectionEditPart createMessageOfBBot = instanceRoleBBot.parent().targetConnections().get(0);
        Rectangle createMessageOfBBounds = editor.getBounds(createMessageOfBBot);
        Rectangle newInstanceRoleBBounds = editor.getBounds(instanceRoleBBot);

        Rectangle expectedCreateMessageOfBBounds = new Rectangle(start.getTranslated(2, 0), new Dimension(instanceRoleBBounds.x - (start.x + 1), 1));
        Assert.assertEquals(expectedCreateMessageOfBBounds, createMessageOfBBounds);
        int dyOfB = start.y - (instanceRoleBBounds.y + instanceRoleBBounds.height / 2);
        Assert.assertEquals(instanceRoleBBounds.getTranslated(0, dyOfB), newInstanceRoleBBounds);

        // Create second create message
        start = newInstanceRoleBBounds.getCenter().translate(0, 100);
        end = instanceRoleCBounds.getCenter();// .translate(0, 100);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);

        SWTBotGefConnectionEditPart createMessageOfCBot = instanceRoleCBot.parent().targetConnections().get(0);
        Rectangle createMessageOfCBounds = editor.getBounds(createMessageOfCBot);
        Rectangle newInstanceRoleCBounds = editor.getBounds(instanceRoleCBot);

        Rectangle expectedCreateMessageOfCBounds = new Rectangle(start.getTranslated(2, 0), new Dimension(instanceRoleCBounds.x - (start.x + 1), 1));
        Assert.assertEquals(expectedCreateMessageOfCBounds, createMessageOfCBounds);
        int dyOfC = start.y - (instanceRoleCBounds.y + instanceRoleCBounds.height / 2);
        Assert.assertEquals(instanceRoleCBounds.getTranslated(0, dyOfC), newInstanceRoleCBounds);

        // Test
        start = newInstanceRoleCBounds.getCenter().translate(0, 100);
        end = instanceRoleABounds.getCenter();// .translate(0, 100);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);

        Assert.assertEquals(instanceRoleABounds, editor.getBounds(instanceRoleABot));
        Assert.assertEquals(instanceRoleBBounds.getTranslated(0, dyOfB), editor.getBounds(instanceRoleBBot));
        Assert.assertEquals(instanceRoleCBounds.getTranslated(0, dyOfC), editor.getBounds(instanceRoleCBot));

        Assert.assertEquals(expectedCreateMessageOfBBounds, editor.getBounds(createMessageOfBBot));
        Assert.assertEquals(expectedCreateMessageOfCBounds, editor.getBounds(createMessageOfCBot));
    }

    /**
     * Test method.
     */
    public void test_CascadingCreateMessages() {
        // Creation of a message
        Point startOfFirstReadMessage = instanceRoleABounds.getCenter().translate(0, 200);
        Point endOfFirstReadMessage = instanceRoleBBounds.getCenter().translate(0, 200);
        createMessage(InteractionsConstants.READ_TOOL_ID, startOfFirstReadMessage, endOfFirstReadMessage);

        // Validates the position
        assertMessageVerticalPosition(FIRST_MESSAGE, startOfFirstReadMessage.y);

        // Try to add a create message on invalid range
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfFirstReadMessage.getTranslated(0, 50), instanceRoleBBounds.getCenter());
        assertNoEditPartWithLabel(SECOND_CREATE_MESSAGE);

        // Creation of a create message
        Point startOfFirstCreateMessage = instanceRoleABounds.getCenter().translate(0, 100);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfFirstCreateMessage, instanceRoleBBounds.getCenter());
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_CREATE_MESSAGE, editor));

        // Validates the position of the return message
        assertMessageVerticalPosition(FIRST_MESSAGE, startOfFirstReadMessage.y);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, startOfFirstCreateMessage.y);

        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Add a new participant to have cascading create message
        createParticipant(lifelineBPosition + getScreenSize(LIFELINE_B).width / 2 + LayoutConstants.LIFELINES_MIN_X_GAP / 2, 75);

        // Creation of a second create message
        Point newLifelinePos = getScreenPosition(NEW_LIFELINE);
        Point startOfSecondCreateMessage = instanceRoleBBounds.getCenter().translate(0, 225);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfSecondCreateMessage, newLifelinePos);

        // Validates the position of the create message
        assertMessageVerticalPosition(FIRST_MESSAGE, startOfFirstReadMessage.y);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, startOfFirstCreateMessage.y);
        assertMessageVerticalPosition(THIRD_CREATE_MESSAGE, startOfSecondCreateMessage.y);
    }

    /**
     * Test reflexive read message move from ends points is not possible.
     * 
     * See VP-1340.
     */
    public void test_reflexiveReadMessage_endPoints_move_notPossible() {
        // Creation of a message
        Point startOfFirstReadMessage = instanceRoleABounds.getCenter().translate(0, 40);
        Point endOfFirstReadMessage = instanceRoleBBounds.getCenter();
        ICondition condition = new OperationDoneCondition();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfFirstReadMessage, endOfFirstReadMessage);
        bot.waitUntil(condition);

        Point source = instanceRoleBBounds.getCenter().translate(0, 150);
        Point target = source.getTranslated(0, 10);
        condition = new OperationDoneCondition();
        createMessage(InteractionsConstants.READ_TOOL_ID, source, target);
        bot.waitUntil(condition);

        SWTBotGefEditPart lifelineBBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart reflexiveReadMessageBot = lifelineBBot.sourceConnections().get(0);
        Rectangle reflexiveReadMessageBounds = editor.getBounds(reflexiveReadMessageBot);

        AbstractConnectionEditPart reflexiveReadMessageEditPart = (AbstractConnectionEditPart) reflexiveReadMessageBot.part();
        Point firstPoint = reflexiveReadMessageEditPart.getConnectionFigure().getPoints().getFirstPoint();
        int dy = -10;
        reflexiveReadMessageBot.select();
        editor.drag(firstPoint, firstPoint.getTranslated(0, dy));
        bot.sleep(500);

        Assert.assertEquals(reflexiveReadMessageBounds, editor.getBounds(reflexiveReadMessageBot));

        Point lastPoint = reflexiveReadMessageEditPart.getConnectionFigure().getPoints().getFirstPoint();
        dy = 10;
        reflexiveReadMessageBot.select();
        editor.drag(lastPoint, lastPoint.getTranslated(0, dy));
        bot.sleep(500);

        Assert.assertEquals(reflexiveReadMessageBounds, editor.getBounds(reflexiveReadMessageBot));

    }

    /**
     * Test method.
     */
    public void test_Edition() {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        Point lifelineBPos = getScreenPosition(LIFELINE_B);

        editor.activateTool("Create");

        // Creation of a create message
        createMessage(InteractionsConstants.CREATE_TOOL_ID, lifelineAPosition, 150, lifelineBPos.x, lifelineBPos.y);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_CREATE_MESSAGE, editor));

        editor.directEdgeEditTypeExtendedToBorderNodes(LIFELINE_A, LIFELINE_B, "create message 1");

        String newMessageLabel = FIRST_CREATE_MESSAGE.replaceFirst("m_create1", "create message 1");
        assertNotNull("There is no message with the label ", getSequenceMessageEditPart(newMessageLabel));
    }

    /**
     * Test method.
     */
    public void test_ArrangeAll() {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of 3 message
        createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 400, lifelineBPosition, 400);

        // Creation of a create message
        createMessage(InteractionsConstants.CREATE_TOOL_ID, lifelineAPosition, 150, getScreenPosition(LIFELINE_B).x, getScreenPosition(LIFELINE_B).y);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_CREATE_MESSAGE, editor));

        createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 250, lifelineAPosition, 310);

        // Validates the graphical position
        assertMessageVerticalPosition(FIRST_MESSAGE, 400);
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, 150);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_TO_SELF, new Rectangle(lifelineAPosition, 250, 0, 310 - 250), true);

        validateOrdering();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);

        // Arrange All
        arrangeAll();

        // TODO FIXME Find why with swtbot we need to add a message after
        lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // arrange all to be refreshed properly
        createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 400, lifelineBPosition, 400);

        // Validates the graphical position
        int expectedCreateMessagePosition = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + LayoutConstants.TIME_START_OFFSET;
        assertMessageVerticalPosition(SECOND_CREATE_MESSAGE, expectedCreateMessagePosition);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_TO_SELF, new Rectangle(lifelineAPosition, expectedCreateMessagePosition += LayoutConstants.TIME_START_OFFSET
                + getScreenSize(LIFELINE_B).height / 2, 0, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP), true);
        assertMessageVerticalPosition(FIRST_MESSAGE, expectedCreateMessagePosition += LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);

        validateOrdering();
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

            // Creation of 3 message
            editor.activateTool(InteractionsConstants.READ_TOOL_ID);
            editor.click(lifelineAPosition, 175);
            editor.click(lifelineBPosition, 175);
            editor.activateTool(InteractionsConstants.CREATE_TOOL_ID);

            // Creation of a create message
            editor.click(lifelineAPosition, 75);
            editor.click(getScreenPosition(LIFELINE_B).x, getScreenPosition(LIFELINE_B).y);

            bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_CREATE_MESSAGE, editor));

            editor.activateTool(InteractionsConstants.READ_TOOL_ID);
            editor.click(lifelineAPosition, 125);
            editor.click(lifelineAPosition, 155);

            // Validates the graphical position
            assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 175, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
            // TODO FIXME Find why the create message is created 2 or 3 pixels
            // up
            assertTrue("The return message is not at the expected vertical position", Math.abs(getSequenceMessageScreenVerticalPosition(SECOND_CREATE_MESSAGE) - 75) < 5);
            assertEquals("The message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position", 125, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
            SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));

            assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

            SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

            // Validates the semantic ordering equals the graphical ordering
            assertTrue("The semantic ordering does not match the graphical ordering",
                    Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

            // Click on the diagram to unfocus the created element
            editor.click(getScreenPosition(LIFELINE_A).x - 5, getScreenPosition(LIFELINE_A).y - 5);
            // Arrange All
            arrangeAll();

            // TODO FIXME Find why with swtbot we need to add a message after
            // arrange all to be refreshed properly
            int lifelineCPosition = getLifelineScreenX(LIFELINE_C);

            editor.activateTool(InteractionsConstants.READ_TOOL_ID);
            editor.click(lifelineBPosition, 225);
            editor.click(lifelineCPosition, 225);

            editor.click(getScreenPosition(LIFELINE_A).x - 5, getScreenPosition(LIFELINE_A).y - 5);
            arrangeAll();

            // Validates the graphical position
            int expectedCreateMessagePosition = (int) (getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + LayoutConstants.TIME_START_OFFSET * zoom50.getAmount());
            assertEquals("The message named " + SECOND_CREATE_MESSAGE + " is not at the expected vertical position", expectedCreateMessagePosition,
                    getSequenceMessageScreenVerticalPosition(SECOND_CREATE_MESSAGE));
            int expectedThirdMessagePosition = (int) (getSequenceMessageScreenVerticalPosition(SECOND_CREATE_MESSAGE) + getScreenSize(LIFELINE_B).height / 2 + LayoutConstants.TIME_START_OFFSET
                    * ZoomLevel.ZOOM_50.getAmount());
            assertEquals("The message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position", expectedThirdMessagePosition,
                    getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF), 1);

            // Validates the semantic ordering equals the graphical ordering
            assertTrue("The semantic ordering does not match the graphical ordering",
                    Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));
        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     */
    // DISABLED becauseof move of message in swtbot not possible
    public void _test_Move() {

        // Try to add a create message
        Point startOfFirstCreateMessage = instanceRoleABounds.getBottom().translate(0, 50);
        Point endOfFirstCreateMessage = instanceRoleBBounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfFirstCreateMessage, endOfFirstCreateMessage);

        SWTBotGefEditPart lifelineABot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart createMessageBot = lifelineABot.sourceConnections().get(0);
        Rectangle createMessageBounds = editor.getBounds(createMessageBot);

        validateSequenceMessageCenteredOnTarget(FIRST_CREATE_MESSAGE);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 250, LIFELINE_C, 250);
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 400, LIFELINE_B, 400);

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 400, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);

        // move down the create message between messages
        Point messageCenter = getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE);
        createMessageBot.select();
        editor.drag(createMessageBounds.getCenter(), createMessageBounds.getCenter().translate(0, 150));

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 400, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_CREATE_MESSAGE);

        // move up the create message back where it was
        messageCenter = getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE);
        editor.drag(messageCenter, messageCenter.getTranslated(0, -150));

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 400, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_CREATE_MESSAGE);

        // Validate it is not possible to move the create message before
        // lifeline A
        int previousCreateMessageVerticalPosition = getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE).y;
        messageCenter = getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE);
        editor.drag(messageCenter, messageCenter.getTranslated(0, -100));

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position",
                Math.max(previousCreateMessageVerticalPosition - 100, getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + LayoutConstants.EXECUTION_CHILDREN_MARGIN),
                getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 400, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_CREATE_MESSAGE);

        // Validate it is not possible to move the create message after the last
        // lifeline B
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE), getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE).y + 30);

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position",
                Math.max(previousCreateMessageVerticalPosition - 50, getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + LayoutConstants.EXECUTION_CHILDREN_MARGIN),
                getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 250, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 400, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_CREATE_MESSAGE);
    }

    /**
     * Test method.
     */
    // DISABLED becauseof move of message in swtbot not possible
    public void _test_Move_Zoom() {
        editor.zoom(ZoomLevel.ZOOM_50);

        editor.activateTool("Create");

        // Try to add a create message
        editor.click(getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2, 75);
        editor.click(getScreenPosition(LIFELINE_B).x, getScreenPosition(LIFELINE_B).y);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 125, LIFELINE_C, 125);
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 200, LIFELINE_B, 200);

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position", 75, getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 125, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 200, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);

        // move down the create message between messages
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE).y + 75);

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 125, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 200, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);

        // move up the create message back where it was
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_CREATE_MESSAGE).y - 75);

        // Validate positions
        assertEquals("The message named " + FIRST_CREATE_MESSAGE + " is not at the expected vertical position", 75, getSequenceMessageScreenVerticalPosition(FIRST_CREATE_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE_ON_LIFELINE_C + " is not at the expected vertical position", 125, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 200, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE));
        validateOrdering(6);
    }

    @Override
    protected void tearDown() throws Exception {
        instanceRoleABot = null;
        instanceRoleBBot = null;
        instanceRoleCBot = null;

        instanceRoleABounds = null;
        instanceRoleBBounds = null;
        instanceRoleCBounds = null;

        super.tearDown();
    }
}
