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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;

/**
 * 
 * @author smonnier, edugueperoux
 */
public class ExecutionMessageReconnectionTests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "delete/2305/";

    private static final String REPRESENTATION_ID = "Sequence Diagram on Sample";

    private static final String MODEL = "reconnectMessages.interactions";

    private static final String SESSION_FILE = "reconnectMessages.aird";

    private static final String TYPES_FILE = "types.ecore";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getSemanticModel() {
        return MODEL;
    }

    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    protected String getSessionModel() {
        return SESSION_FILE;
    }

    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_ID);
    }

    private Rectangle boundsE1;

    private Rectangle boundsE2;

    private Rectangle boundsE3;

    private Rectangle boundsE4;

    private Rectangle boundsE5;

    private Rectangle boundsE6;

    private Rectangle boundsE7;

    private Rectangle boundsE8;

    private Rectangle boundsE9;

    private Rectangle boundsE10;

    private Rectangle boundsE11;

    private Rectangle boundsE12;

    private Rectangle boundsM1;

    private Rectangle boundsM2;

    private Rectangle boundsM3;

    private Rectangle boundsM4;

    private Rectangle boundsM5;

    private Rectangle boundsM6;

    private Rectangle boundsM7;

    private Rectangle boundsM8;

    private Rectangle boundsM9;

    private Rectangle boundsM10;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        maximizeEditor(editor);
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        testConsistency();
    }

    /**
     * Tests diagram consistency.
     */
    public void testConsistency() {
        boundsE1 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, new Rectangle(0, 130, 0, 380), false);
        boundsE2 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, new Rectangle(0, 540, 0, 440), false);
        boundsE3 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, new Rectangle(0, 1010, 0, 210), false);
        boundsE4 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, new Rectangle(0, 1040, 0, 150), false);
        boundsE5 = assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, new Rectangle(0, 745, 0, 90), false);
        boundsE6 = assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, new Rectangle(0, 1100, 0, 60), false);
        boundsE7 = assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, new Rectangle(0, 190, 0, 85), false);
        boundsE8 = assertExecutionHasValidLogicalBounds(LIFELINE_C, 1, new Rectangle(0, 865, 0, 85), false);
        boundsE9 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, new Rectangle(0, 305, 0, 145), false);
        boundsE10 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, new Rectangle(0, 335, 0, 85), false);
        boundsE11 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, new Rectangle(0, 570, 0, 145), false);
        boundsE12 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 3, new Rectangle(0, 600, 0, 85), false);

        boundsM1 = assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, new Rectangle(new Point(0, 160), new Point(0, 160)), true, false);
        boundsM2 = assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, new Rectangle(new Point(0, 479), new Point(0, 479)), false, false);
        boundsM3 = assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, new Rectangle(new Point(0, 775), new Point(0, 775)), false, false);
        boundsM4 = assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, new Rectangle(new Point(0, 805), new Point(0, 805)), true, false);
        boundsM5 = assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, new Rectangle(new Point(0, 1070), new Point(0, 1070)), true, false);
        boundsM6 = assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, new Rectangle(new Point(0, 1130), new Point(0, 1130)), false, false);
        boundsM7 = assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, new Rectangle(new Point(0, 220), new Point(0, 220)), true, false);
        boundsM8 = assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, new Rectangle(new Point(0, 920), new Point(0, 920)), true, false);
        boundsM9 = assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, new Rectangle(new Point(0, 655), new Point(0, 655)), true, false);
        boundsM10 = assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, new Rectangle(new Point(0, 365), new Point(0, 365)), true, false);
    }

    /**
     * Deleting an execution have to reconnect its sub
     * executions and destroy messages on its parent.
     * 
     */
    public void test_Delete_Execution_And_Reconnect_Sub_Executions_And_Destroy_Messages() {

        int expectedResizeOfMessageToExecutionAfterDelete = boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width;
        int expectedResizeOfMessageToLifelineAfterDelete = boundsE1.width / 2 - 2;

        // Delete the execution corresponding to boundsE8
        editor.reveal(getExecutionEditPart(LIFELINE_C, 1));
        CheckSelectedCondition csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_C, 1));
        editor.click(getExecutionScreenPosition(LIFELINE_C, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM8.translate(-expectedResizeOfMessageToLifelineAfterDelete, 0);
        boundsM8.resize(expectedResizeOfMessageToLifelineAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, boundsE7);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, boundsE9);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, boundsE10);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, boundsE11);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 3, boundsE12);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, boundsM1, true);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, boundsM2, false);
        assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, boundsM3, false);
        assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, boundsM4, true);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, boundsM5, true);
        assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, boundsM6, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, boundsM7, true);
        assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, boundsM8, true);
        assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, boundsM9, true);
        assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, boundsM10, true);

        // Delete the execution corresponding to boundsE12
        editor.reveal(getExecutionEditPart(NEW_LIFELINE_5, 3));
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(NEW_LIFELINE_5, 3));
        editor.click(getExecutionScreenPosition(NEW_LIFELINE_5, 3));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM9.translate(-expectedResizeOfMessageToExecutionAfterDelete, 0);
        boundsM9.resize(expectedResizeOfMessageToExecutionAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, boundsE7);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, boundsE9);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, boundsE10);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, boundsE11);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, boundsM1, true);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, boundsM2, false);
        assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, boundsM3, false);
        assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, boundsM4, true);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, boundsM5, true);
        assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, boundsM6, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, boundsM7, true);
        assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, boundsM8, true);
        assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, boundsM9, true);
        assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, boundsM10, true);
    }

    /**
     * Deleting an execution have to reconnect its sub
     * executions and create messages on its parent.
     * 
     */
    public void test_Delete_Execution_And_Reconnect_Sub_Executions_And_Create_Messages() {

        int expectedResizeOfMessageToExecutionAfterDelete = boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width;
        int expectedResizeOfMessageToLifelineAfterDelete = boundsE1.width / 2 - 2;

        // Delete the execution corresponding to boundsE7
        CheckSelectedCondition csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_C, 0));
        editor.click(getExecutionScreenPosition(LIFELINE_C, 0));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM7.translate(-expectedResizeOfMessageToLifelineAfterDelete, 0);
        boundsM7.resize(expectedResizeOfMessageToLifelineAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, boundsE8);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, boundsE9);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, boundsE10);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, boundsE11);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 3, boundsE12);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, boundsM1, true);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, boundsM2, false);
        assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, boundsM3, false);
        assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, boundsM4, true);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, boundsM5, true);
        assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, boundsM6, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, boundsM7, true);
        assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, boundsM8, true);
        assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, boundsM9, true);
        assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, boundsM10, true);

        // Delete the execution corresponding to boundsE10
        editor.reveal(getExecutionEditPart(NEW_LIFELINE_5, 1));
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(NEW_LIFELINE_5, 1));
        editor.click(getExecutionScreenPosition(NEW_LIFELINE_5, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM10.translate(-expectedResizeOfMessageToExecutionAfterDelete, 0);
        boundsM10.resize(expectedResizeOfMessageToExecutionAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, boundsE8);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, boundsE9);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, boundsE11);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, boundsE12);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, boundsM1, true);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, boundsM2, false);
        assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, boundsM3, false);
        assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, boundsM4, true);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, boundsM5, true);
        assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, boundsM6, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, boundsM7, true);
        assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, boundsM8, true);
        assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, boundsM9, true);
        assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, boundsM10, true);
    }

    /**
     * Deleting an execution have to reconnect its sub
     * executions and simple messages on its parent.
     * 
     */
    public void test_Delete_Execution_And_Reconnect_Sub_Executions_And_Simple_Messages() {
        Rectangle boundsE1 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, new Rectangle(0, 130, 0, 380), false);
        Rectangle boundsE2 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, new Rectangle(0, 540, 0, 440), false);
        Rectangle boundsE3 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, new Rectangle(0, 1010, 0, 210), false);
        Rectangle boundsE4 = assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, new Rectangle(0, 1040, 0, 150), false);
        Rectangle boundsE5 = assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, new Rectangle(0, 745, 0, 90), false);
        Rectangle boundsE6 = assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, new Rectangle(0, 1100, 0, 60), false);
        Rectangle boundsE7 = assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, new Rectangle(0, 190, 0, 85), false);
        Rectangle boundsE8 = assertExecutionHasValidLogicalBounds(LIFELINE_C, 1, new Rectangle(0, 865, 0, 85), false);
        Rectangle boundsE9 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, new Rectangle(0, 305, 0, 145), false);
        Rectangle boundsE10 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, new Rectangle(0, 335, 0, 85), false);
        Rectangle boundsE11 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, new Rectangle(0, 570, 0, 145), false);
        Rectangle boundsE12 = assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 3, new Rectangle(0, 600, 0, 85), false);

        Rectangle boundsM1 = assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, new Rectangle(new Point(0, 160), new Point(0, 160)), true, false);
        Rectangle boundsM2 = assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, new Rectangle(new Point(0, 479), new Point(0, 479)), false, false);
        Rectangle boundsM3 = assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, new Rectangle(new Point(0, 775), new Point(0, 775)), false, false);
        Rectangle boundsM4 = assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, new Rectangle(new Point(0, 805), new Point(0, 805)), true, false);
        Rectangle boundsM5 = assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, new Rectangle(new Point(0, 1070), new Point(0, 1070)), true, false);
        Rectangle boundsM6 = assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, new Rectangle(new Point(0, 1130), new Point(0, 1130)), false, false);
        Rectangle boundsM7 = assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, new Rectangle(new Point(0, 220), new Point(0, 220)), true, false);
        Rectangle boundsM8 = assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, new Rectangle(new Point(0, 920), new Point(0, 920)), true, false);
        Rectangle boundsM9 = assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, new Rectangle(new Point(0, 655), new Point(0, 655)), true, false);
        Rectangle boundsM10 = assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, new Rectangle(new Point(0, 365), new Point(0, 365)), true, false);
        int expectedTranslationAfterDelete = -(boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width);
        int expectedResizeOfMessageToExecutionAfterDelete = boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width;
        int expectedResizeOfMessageToLifelineAfterDelete = boundsE1.width / 2 - 2;

        // Delete the execution corresponding to boundsE8
        editor.reveal(getExecutionEditPart(LIFELINE_C, 1));
        CheckSelectedCondition csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_C, 1));
        editor.click(getExecutionScreenPosition(LIFELINE_C, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM8.translate(-expectedResizeOfMessageToLifelineAfterDelete, 0);
        boundsM8.resize(expectedResizeOfMessageToLifelineAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, boundsE7);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, boundsE9);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, boundsE10);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, boundsE11);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 3, boundsE12);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, boundsM1, true);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, boundsM2, false);
        assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, boundsM3, false);
        assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, boundsM4, true);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, boundsM5, true);
        assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, boundsM6, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, boundsM7, true);
        assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, boundsM8, true);
        assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, boundsM9, true);
        assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, boundsM10, true);

        // Delete the execution corresponding to boundsE12
        editor.reveal(getExecutionEditPart(NEW_LIFELINE_5, 3));
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(NEW_LIFELINE_5, 3));
        editor.click(getExecutionScreenPosition(NEW_LIFELINE_5, 3));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM9.translate(-expectedResizeOfMessageToExecutionAfterDelete, 0);
        boundsM9.resize(expectedResizeOfMessageToExecutionAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE2);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 3, boundsE4);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, boundsE5);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, boundsE6);
        assertExecutionHasValidLogicalBounds(LIFELINE_C, 0, boundsE7);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 0, boundsE9);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 1, boundsE10);
        assertExecutionHasValidLogicalBounds(NEW_LIFELINE_5, 2, boundsE11);

        assertNamedMessageHasValidLogicalBounds(FIRST_MESSAGE, boundsM1, true);
        assertNamedMessageHasValidLogicalBounds(SECOND_MESSAGE_ON_LIFELINE_A, boundsM2, false);
        assertNamedMessageHasValidLogicalBounds(THIRD_MESSAGE_ON_LIFELINE_A, boundsM3, false);
        assertNamedMessageHasValidLogicalBounds(FOURTH_MESSAGE_ON_LIFELINE_B, boundsM4, true);
        assertNamedMessageHasValidLogicalBounds(FIFTH_MESSAGE, boundsM5, true);
        assertNamedMessageHasValidLogicalBounds(SIXTH_MESSAGE, boundsM6, false);
        assertNamedMessageHasValidLogicalBounds(SEVENTH_CREATE_MESSAGE, boundsM7, true);
        assertNamedMessageHasValidLogicalBounds(EIGHTH_DESTROY_MESSAGE, boundsM8, true);
        assertNamedMessageHasValidLogicalBounds(NINETH_DESTROY_MESSAGE, boundsM9, true);
        assertNamedMessageHasValidLogicalBounds(TENTH_CREATE_MESSAGE, boundsM10, true);
    }
}
