/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import com.google.common.collect.Iterables;

/**
 * Test class for message to self management
 * 
 * @author smonnier
 */
public class SequenceMessageToSelfTest extends AbstractDefaultModelSequenceTests {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);
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

        // editor.

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

        editor.activateTool("Read");

        // Creation of a return message
        editor.click(lifelineAPosition, 250);
        editor.click(lifelineAPosition, 350);

        bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_MESSAGE_TO_SELF, editor));

        verticalPosition = getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE_TO_SELF);

        // Validates the position of the return message
        assertEquals("The message to self start is not at the expected vertical position", 250, verticalPosition);
        assertEquals("The message to self end is not at the expected vertical position", 350, getSequenceMessageLastPointScreenVerticalPosition(SECOND_MESSAGE_TO_SELF));

        // Deletion of the return message

        // On a message to self, getSequenceMessageScreenCenteredPosition return
        // a position which corresponds to the label, but we want the edge
        // itself so we click 10 pixels higher.
        editor.click(getSequenceMessageScreenCenteredPosition(SECOND_MESSAGE_TO_SELF).getTranslated(0, -10));
        bot.menu("Edit").menu("Delete").click();

        try {
            editor.getEditPart(SECOND_MESSAGE_TO_SELF);
            fail(WidgetNotFoundException.class + " expected");

        } catch (final WidgetNotFoundException e) {
            // Expected, the edit part must not be found
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_With_Two_Clics_Same_Position() throws Exception {
        createMessageToSelfOnA(200, 200);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Minimum_Size() throws Exception {
        createMessageToSelfOnA(200, 201);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Return_To_The_Future() throws Exception {
        createMessageToSelfOnA(200, 150);
    }

    private void createMessageToSelfOnA(int firstClick, int secondClick) {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // editor.

        // Calculate the X position of the center of lifeline A
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);

        editor.activateTool("Read");

        // Creation of a message
        editor.click(lifelineAPosition, firstClick);
        editor.click(lifelineAPosition, secondClick);

        int messageToSelfBendpointVerticalGap = Math.max(LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP, secondClick - firstClick);
        Rectangle expectedBounds = new Rectangle(0, firstClick, 0, messageToSelfBendpointVerticalGap);

        assertMessageHasValidScreenBounds(getSequenceMessageEditPart(FIRST_MESSAGE_TO_SELF), expectedBounds, true, false);
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
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 180);
        editor.click(lifelineAPosition, 250);

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertEquals("The message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position", 180, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
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
    public void test_Edition() throws Exception {

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // editor.

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

        editor.activateTool("Read");

        // Creation of a return message
        editor.click(lifelineAPosition, 300);
        editor.click(lifelineAPosition, 350);

        bot.waitUntil(new CheckMessageEditPartIsDisplayed(SECOND_MESSAGE_TO_SELF, editor));

        editor.directEdgeEditTypeOnBorderNodesOnly(LIFELINE_A, LIFELINE_A, "message 2");

        String newMessageLabel = SECOND_MESSAGE_TO_SELF.replaceFirst("m2", "message 2");

        assertNotNull("There is no message with the label ", getSequenceMessageEditPart(newMessageLabel));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_ArrangeAll() throws Exception {
        // Go to the origin to avoid scroll bar. Indeed, there is sometimes,
        // without understanding why, scroll bar in this test, and this makes
        // fail the test.
        editor.scrollTo(0, 0);
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
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 180);
        editor.click(lifelineAPosition, 250);

        // Validates the graphical position
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertEquals("The message to self named " + THIRD_MESSAGE_TO_SELF + " is not starting at the expected vertical position", 180, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
        assertEquals("The message to self named " + THIRD_MESSAGE_TO_SELF + " is not ending at the expected vertical position", 250,
                getSequenceMessageLastPointScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
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

        // Validates the graphical position
        assertMessageVerticalPosition(SECOND_MESSAGE, getLogicalPosition(LIFELINE_A).y + getLogicalSize(LIFELINE_A).height + LayoutConstants.TIME_START_OFFSET);
        assertMessageVerticalPosition(FIRST_MESSAGE, getSequenceMessageLastPointScreenVerticalPosition(THIRD_MESSAGE_TO_SELF) + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);

        assertEquals("The message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position",
                getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE) + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF), 1);
        assertEquals("The message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position",
                getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF) + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP,
                getSequenceMessageLastPointScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));

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
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 180);
        editor.click(lifelineAPosition, 250);

        // Validates the graphical position
        assertMessageVerticalPosition(FIRST_MESSAGE, 300);
        assertMessageVerticalPosition(SECOND_MESSAGE, 150);
        assertEquals("The message to self named " + THIRD_MESSAGE_TO_SELF + " is not starting at the expected vertical position", 180, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
        assertEquals("The message to self named " + THIRD_MESSAGE_TO_SELF + " is not ending at the expected vertical position", 250,
                getSequenceMessageLastPointScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));

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

        // Validates the graphical position
        int expectedY = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + LayoutConstants.TIME_START_OFFSET;
        assertMessageVerticalPosition(SECOND_MESSAGE, expectedY);
        expectedY += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        assertEquals("The message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position", expectedY, getSequenceMessageVerticalPosition(THIRD_MESSAGE_TO_SELF));
        expectedY += LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP;
        assertEquals("The last point of message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position", expectedY,
                getSequenceMessageLastPointScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
        expectedY += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        assertMessageVerticalPosition(FIRST_MESSAGE, expectedY);

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
        assertEquals("The message named " + THIRD_MESSAGE_TO_SELF + " is not at the expected vertical position", 180, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));

        // Undo THIRD_MESSAGE creation
        undo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));

        assertNoEditPartWithLabel(THIRD_MESSAGE_TO_SELF);

        // Undo SECOND_MESSAGE creation
        undo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));

        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNoEditPartWithLabel(THIRD_MESSAGE_TO_SELF);

        // Undo FIRST_MESSAGE creation
        undo();

        assertNoEditPartWithLabel(FIRST_MESSAGE);
        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNoEditPartWithLabel(THIRD_MESSAGE_TO_SELF);

        // Redo FIRST_MESSAGE creation
        redo();
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));

        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNoEditPartWithLabel(THIRD_MESSAGE);

        // Redo SECOND_MESSAGE creation
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_MESSAGE));

        assertNoEditPartWithLabel(THIRD_MESSAGE);

        // Redo THIRD_MESSAGE creation
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_MESSAGE));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 180, getSequenceMessageVerticalPosition(THIRD_MESSAGE));

        // Redo Arrange All
        redo();
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 130, getSequenceMessageVerticalPosition(SECOND_MESSAGE));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(THIRD_MESSAGE));
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 180, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Zoom() throws Exception {
        editor.zoom(ZoomLevel.ZOOM_50);

        // Calculate the X position of the center of lifelines A and B

        int lifelineAPosition = editor.getLocation(LIFELINE_A, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_A).width / 2);
        int lifelineBPosition = editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2);

        // Creation of 3 message
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 125);
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 75);
        editor.click(lifelineBPosition, 125);
        editor.activateTool("Read");
        editor.click(lifelineAPosition, 90);
        editor.click(lifelineAPosition, 130);

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X / 2 - 10, LayoutConstants.LIFELINES_START_Y / 2 - 10);

        // Validates the graphical position
        assertNotNull("The message named " + FIRST_MESSAGE + " has not been created", getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE));
        assertNotNull("The message named " + SECOND_MESSAGE + " has not been created", getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 75, getSequenceMessageScreenVerticalPosition(SECOND_MESSAGE));
        assertNotNull("The message named " + THIRD_MESSAGE_TO_SELF + " has not been created", getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
        assertEquals("The message to self named " + THIRD_MESSAGE_TO_SELF + " is not starting at the expected vertical position", 90, getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
        assertEquals("The message to self named " + THIRD_MESSAGE_TO_SELF + " is not ending at the expected vertical position", 130,
                getSequenceMessageLastPointScreenVerticalPosition(THIRD_MESSAGE_TO_SELF));
        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));

        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));
    }

    /**
     * Test to check that it is possible to create a reflexive message
     * surrounded by an other one. Check horizontal layout.
     */
    public void test_Reflexive_Inclusion_Creation() {
        editor.maximize();
        arrangeAll();

        int lifelineBPosition = editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2);
        int baseReflexiveWidth = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP;
        int reflexiveHGap = LayoutConstants.MESSAGE_TO_SELF_HORIZONTAL_GAP;

        // Creation of 5 message
        int clic1 = 150;
        int clic2 = 290;
        createMessage("Read", LIFELINE_A, clic1, LIFELINE_A, clic2);

        Rectangle m1Bounds = new Rectangle(0, clic1, baseReflexiveWidth, clic2 - clic1);
        SWTBotGefEditPart m1Bot = editor.getEditPart(FIRST_MESSAGE).parent();
        m1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m1Bot.part(), m1Bounds, true, false);

        // Check B did not move
        int deltaB = lifelineBPosition - (editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2));
        assertEquals("Lifeline B should not move.", 0, deltaB);

        clic1 = 170;
        clic2 = 270;
        createMessage("Read", LIFELINE_A, clic1, LIFELINE_A, clic2);

        Rectangle m2Bounds = new Rectangle(0, clic1, baseReflexiveWidth, clic2 - clic1);
        SWTBotGefEditPart m2Bot = editor.getEditPart(SECOND_MESSAGE).parent();
        m2Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m2Bot.part(), m2Bounds, true, false);
        m1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m1Bot.part(), m1Bounds.getResized(reflexiveHGap, 0), true, false);

        // Check B should not move
        deltaB = -lifelineBPosition + editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2);
        assertEquals("Lifeline B should move.", 0, deltaB);

        clic1 = 190;
        clic2 = 250;
        createMessage("Read", LIFELINE_A, clic1, LIFELINE_A, clic2);

        Rectangle m3Bounds = new Rectangle(0, clic1, baseReflexiveWidth, clic2 - clic1);
        SWTBotGefEditPart m3Bot = editor.getEditPart(THIRD_MESSAGE).parent();
        m3Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m3Bot.part(), m3Bounds, true, false);
        m2Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m2Bot.part(), m2Bounds.getResized(reflexiveHGap, 0), true, false);
        m1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m1Bot.part(), m1Bounds.getResized(reflexiveHGap, 0), true, false);

        // Check B did not move
        deltaB = -lifelineBPosition + editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2);
        assertEquals("Lifeline B should move.", 0, deltaB);

        clic1 = 210;
        clic2 = 230;
        createMessage("Read", LIFELINE_A, clic1, LIFELINE_A, clic2);

        Rectangle m4Bounds = new Rectangle(0, clic1, baseReflexiveWidth, clic2 - clic1);
        SWTBotGefEditPart m4Bot = editor.getEditPart(FOURTH_MESSAGE).parent();
        m4Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m4Bot.part(), m4Bounds, true, false);
        m3Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m3Bot.part(), m3Bounds.getResized(reflexiveHGap, 0), true, false);
        m2Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m2Bot.part(), m2Bounds.getResized(reflexiveHGap, 0), true, false);
        m1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m1Bot.part(), m1Bounds.getResized(reflexiveHGap, 0), true, false);

        // Check B has moved
        deltaB = -lifelineBPosition + editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2);
        assertEquals("Lifeline B should move.", 10, deltaB);

        clic1 = 160;
        clic2 = 180;
        createMessage("Read", LIFELINE_A, clic1, LIFELINE_A, clic2);

        Rectangle m5Bounds = new Rectangle(0, clic1, baseReflexiveWidth, clic2 - clic1);
        SWTBotGefEditPart m5Bot = editor.getEditPart(FIFTH_MESSAGE).parent();

        m5Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m5Bot.part(), m5Bounds, true, false);
        m4Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m4Bot.part(), m4Bounds, true, false);
        m3Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m3Bot.part(), m3Bounds, true, false);
        m2Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m2Bot.part(), m2Bounds, true, false);
        m1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m1Bot.part(), m1Bounds, true, false);

        // Check B has moved
        deltaB = -lifelineBPosition + editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2);
        assertEquals("Lifeline B shoud not move after last step.", 10, deltaB);

        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));
        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();
        // Validates the semantic ordering equals the graphical ordering
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

    }

    /**
     * Test to check that it is possible to create a reflexive message
     * surrounded by an other one. Check horizontal layout.
     */
    public void test_Reflexive_Inclusion_Creation_On_Execution() {
        int lifelineAPosition = editor.getLocation(LIFELINE_A, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_A).width / 2);
        int lifelineBPosition = editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2);
        int baseReflexiveWidth = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP;
        int reflexiveHGap = LayoutConstants.MESSAGE_TO_SELF_HORIZONTAL_GAP;

        int startExec = 150;
        createExecution(lifelineAPosition, startExec);

        int clic1 = 160;
        int clic2 = 170;
        createMessage("Read", LIFELINE_A, clic1, LIFELINE_A, clic2);

        Rectangle m1Bounds = new Rectangle(0, clic1, baseReflexiveWidth, clic2 - clic1);
        SWTBotGefEditPart m1Bot = editor.getEditPart(FIRST_MESSAGE).parent();
        m1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m1Bot.part(), m1Bounds, true, false);

        // Check B did not move
        int deltaB = lifelineBPosition - (editor.getLocation(LIFELINE_B, IGraphicalEditPart.class).x + (getScreenSize(LIFELINE_B).width / 2));
        assertEquals("Lifeline B should not move.", 0, deltaB);

        clic1 = 130;
        clic2 = 250;
        createMessage("Read", LIFELINE_A, clic1, LIFELINE_A, clic2);

        Rectangle m2Bounds = new Rectangle(0, clic1, m1Bounds.width + reflexiveHGap + 5, clic2 - clic1);
        SWTBotGefEditPart m2Bot = editor.getEditPart(SECOND_MESSAGE).parent();
        m2Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m2Bot.part(), m2Bounds, true, false);
        m1Bounds = assertMessageHasValidScreenBounds((SequenceMessageEditPart) m1Bot.part(), m1Bounds, true, false);
    }

    /**
     * Test of DoReMi-2305. Deleting an execution have to reconnect its sub
     * reflexive messages on its parent.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Delete_Execution_And_Reconnect_Sub_Reflexive_Messages() throws Exception {
        localSession.close(false);

        String data_unit_dir_2305 = "data/unit/sequence/delete/2305/";
        String model_2305 = "reconnectReflectiveMessages.interactions";
        String session_file_2305 = "reconnectReflectiveMessages.aird";
        String representation_instance_name_2305 = "Sequence Diagram on Sample";

        // import model and open session
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, data_unit_dir_2305 + model_2305, getProjectName() + "/" + model_2305);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, data_unit_dir_2305 + session_file_2305, getProjectName() + "/" + session_file_2305);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, data_unit_dir_2305 + "types.ecore", getProjectName() + "/" + "types.ecore");

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, session_file_2305);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationId(), representation_instance_name_2305, DDiagram.class);

        maximizeEditor(editor);
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        Rectangle boundsE1 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, new Rectangle(0, 130, 0, 60), false);
        Rectangle boundsE2 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, new Rectangle(0, 210, 0, 100), false);
        Rectangle boundsE3 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, new Rectangle(0, 230, 0, 60), false);
        Rectangle boundsE4 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, new Rectangle(0, 330, 0, 130), false);
        Rectangle boundsE5 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 4, new Rectangle(0, 370, 0, 50), false);
        Rectangle boundsE6 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 5, new Rectangle(0, 480, 0, 352), false);
        Rectangle boundsE7 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 6, new Rectangle(0, 500, 0, 305), false);
        Rectangle boundsE8 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 7, new Rectangle(0, 603, 0, 50), false);

        Rectangle expectedBoundsM1 = new Rectangle(boundsE1.getRight().x, 150, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP, 170 - 150);
        Rectangle boundsM1 = assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE_TO_SELF, expectedBoundsM1, true, false);
        Rectangle expectedBoundsM2 = new Rectangle(boundsE3.getRight().x, 250, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP, 270 - 250);
        Rectangle boundsM2 = assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_TO_SELF, expectedBoundsM2, true, false);
        int parentRightXToOtherEndMessageDelta = 15;
        Rectangle expectedBoundsM5 = new Rectangle(0, 350, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP + parentRightXToOtherEndMessageDelta, 20);
        Rectangle boundsM5 = assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, expectedBoundsM5, true, false);
        Rectangle expectedBoundsM6 = new Rectangle(0, 420, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP, 440 - 420);
        Rectangle boundsM6 = assertReturnMessageHasValidScreenBounds(LIFELINE_A, 4, expectedBoundsM6, false, false);
        Rectangle expectedBoundsM7 = new Rectangle(0, 583, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP + parentRightXToOtherEndMessageDelta, 603 - 583);
        Rectangle boundsM7 = assertNamedMessageHasValidLogicalBounds(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, expectedBoundsM7, true, false);
        Rectangle expectedBoundsM8 = new Rectangle(0, 653, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP, 673 - 653);
        Rectangle boundsM8 = assertReturnMessageHasValidScreenBounds(LIFELINE_A, 7, expectedBoundsM8, false, false);
        int expectedResizeOfMessageToExecutionAfterDelete = boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width;
        int expectedResizeOfMessageToLifelineAfterDelete = boundsE1.width / 2 - 2;

        // Delete the execution corresponding to boundsE1
        editor.reveal(getExecutionEditPart(LIFELINE_A, 0));
        CheckSelectedCondition csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 0));
        editor.click(getExecutionScreenPosition(LIFELINE_A, 0));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM1.translate(-expectedResizeOfMessageToLifelineAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 4, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 5, boundsE7);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 6, boundsE8);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE_TO_SELF, boundsM1, true, false);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_TO_SELF, boundsM2, true, false);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, boundsM5, true, false);
        assertReturnMessageHasValidLogicalBounds(LIFELINE_A, 3, boundsM6, false, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, boundsM7, true, false);
        assertReturnMessageHasValidLogicalBounds(LIFELINE_A, 6, boundsM8, false, false);

        // Delete the execution corresponding to boundsE3
        editor.reveal(getExecutionEditPart(LIFELINE_A, 1));
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 1));
        editor.click(getExecutionScreenPosition(LIFELINE_A, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(5, 5);
        manualRefresh();

        // Expected bounds after the drag
        boundsM2.translate(-expectedResizeOfMessageToExecutionAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 4, boundsE7);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 5, boundsE8);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE_TO_SELF, boundsM1, true, false);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_TO_SELF, boundsM2, true, false);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, boundsM5, true, false);
        assertReturnMessageHasValidLogicalBounds(LIFELINE_A, 2, boundsM6, false, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_MESSAGE_SYNC_CALL_ON_LIFELINE_A, boundsM7, true, false);
        assertReturnMessageHasValidLogicalBounds(LIFELINE_A, 5, boundsM8, false, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        editor.zoom(ZoomLevel.ZOOM_100);
        super.tearDown();
    }
}
