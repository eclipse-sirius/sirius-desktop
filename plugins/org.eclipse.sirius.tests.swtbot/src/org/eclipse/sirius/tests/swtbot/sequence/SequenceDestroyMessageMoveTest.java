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

import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class for creation message management
 * 
 * @author smonnier
 */
public class SequenceDestroyMessageMoveTest extends AbstractDefaultModelSequenceTests {

    private SWTBotGefEditPart sequenceDiagramBot;

    private Interaction interaction;

    private SWTBotGefEditPart instanceRoleABot;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        maximizeEditor(editor);

        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();

        sequenceDiagramBot = editor.mainEditPart();
        SequenceDiagramEditPart sequenceDiagramEditPart = (SequenceDiagramEditPart) sequenceDiagramBot.part();
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();
        interaction = (Interaction) sequenceDDiagram.getTarget();

        // InstanceRoles
        instanceRoleABot = editor.getEditPart(LIFELINE_A);
    }

    /**
     * Test method.
     */
    public void test_Move() {
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, LIFELINE_A, 400, LIFELINE_B, 400);

        interaction.getMessages().get(0);
        SWTBotGefEditPart lifelineABot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart destroyMessageBot = lifelineABot.sourceConnections().get(0);
        destroyMessageBot.target();

        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 300, LIFELINE_C, 300);
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 150, LIFELINE_B, 150);

        // Validate positions
        // Validates the position
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 400);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 300);
        assertMessageVerticalPosition(THIRD_MESSAGE, 150);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // move up the destroy message between messages
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).y - 170);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 230);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 300);
        assertMessageVerticalPosition(THIRD_MESSAGE, 150);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // move down the destroy message back where it was
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).y + 170);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 400);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 300);
        assertMessageVerticalPosition(THIRD_MESSAGE, 150);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // Validate it is not possible to move the destroy message after
        // lifeline A
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).y + 200);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 400);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 300);
        assertMessageVerticalPosition(THIRD_MESSAGE, 150);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // Validate it is not possible to move the destroy message before a
        // message on lifeline B
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE).y - 30);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 400);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 300);
        assertMessageVerticalPosition(THIRD_MESSAGE, 150);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);
    }

    /**
     * Test method.
     */
    public void test_Move_Zoom() {
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, LIFELINE_A, 200, LIFELINE_B, 200);

        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 150, LIFELINE_C, 150);
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 75, LIFELINE_B, 75);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 200);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 75);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // move up the destroy message between messages
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).y - 85);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 115);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 75);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // move down the destroy message back where it was
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).y + 85);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 200);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 75);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // Validate it is not possible to move the destroy message after
        // lifeline A
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE).y + 100);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 200);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 75);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);

        // Validate it is not possible to move the destroy message before a
        // message on lifeline B
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_DESTROY_MESSAGE), getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE).x,
                getSequenceMessageScreenCenteredPosition(THIRD_MESSAGE).y - 15);

        editor.click(0, 0);

        // Validate positions
        assertMessageVerticalPosition(FIRST_DESTROY_MESSAGE, 200);
        assertMessageVerticalPosition(SECOND_MESSAGE_ON_LIFELINE_C, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 75);
        validateOrdering(6);
        validateSequenceMessageCenteredOnTarget(FIRST_DESTROY_MESSAGE);
    }

    @Override
    protected void tearDown() throws Exception {
        editor.zoom(ZoomLevel.ZOOM_100);
        super.tearDown();
    }
}
