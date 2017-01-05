/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
public class ExecutionLinkedMessageReconnectionTests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "delete/2305/";

    private static final String REPRESENTATION_ID = "Sequence Diagram on Sample";

    private static final String MODEL = "reconnectLinkedMessages.interactions";

    private static final String SESSION_FILE = "reconnectLinkedMessages.aird";

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        maximizeEditor(editor);
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

    }

    /**
     * Test of DoReMi-2305. Deleting an execution have to reconnect its sub
     * executions and messages on its parent.
     * 
     */
    public void test_Delete_Execution_And_Reconnect_Sub_Executions_And_Messages() {
        Rectangle boundsE1 = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 130, 0, 90), false);
        Rectangle boundsE2 = assertExecutionHasValidScreenBounds(LIFELINE_A, 1, new Rectangle(0, 250, 0, 150), false);
        Rectangle boundsE3 = assertExecutionHasValidScreenBounds(LIFELINE_A, 2, new Rectangle(0, 280, 0, 90), false);
        Rectangle boundsE4 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, 160, 0, 30), false);
        Rectangle boundsE5 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, 310, 0, 30), false);
        Rectangle boundsE6 = assertExecutionHasValidScreenBounds(LIFELINE_C, 0, new Rectangle(0, 430, 0, 90), false);
        Rectangle boundsE7 = assertExecutionHasValidScreenBounds(LIFELINE_C, 1, new Rectangle(0, 550, 0, 150), false);
        Rectangle boundsE8 = assertExecutionHasValidScreenBounds(LIFELINE_C, 2, new Rectangle(0, 580, 0, 90), false);
        Rectangle boundsE9 = assertExecutionHasValidScreenBounds(NEW_LIFELINE, 0, new Rectangle(0, 460, 0, 30), false);
        Rectangle boundsE10 = assertExecutionHasValidScreenBounds(NEW_LIFELINE, 1, new Rectangle(0, 610, 0, 30), false);

        Rectangle boundsM1 = assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, new Rectangle(new Point(0, 160), new Point(0, 160)), true, false);
        Rectangle boundsM2 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(new Point(0, 190), new Point(0, 190)), false, false);
        Rectangle boundsM3 = assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, new Rectangle(new Point(0, 310), new Point(0, 310)), true, false);
        Rectangle boundsM4 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(new Point(0, 340), new Point(0, 340)), false, false);
        Rectangle boundsM5 = assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, new Rectangle(new Point(0, 460), new Point(0, 460)), true, false);
        Rectangle boundsM6 = assertNamedMessageHasValidScreenBounds(SIXTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, new Rectangle(new Point(0, 610), new Point(0, 610)), true, false);
        int expectedTranslationAfterDelete = -(boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width);
        int expectedResizeOfMessageToExecutionAfterDelete = boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width;
        int expectedResizeOfMessageToLifelineAfterDelete = boundsE1.width / 2 - 2;

        // Delete the execution corresponding to boundsE1
        CheckSelectedCondition csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 0));
        editor.click(getExecutionScreenPosition(LIFELINE_A, 0));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM1.translate(-expectedResizeOfMessageToLifelineAfterDelete, 0);
        boundsM1.resize(expectedResizeOfMessageToLifelineAfterDelete, 0);
        boundsM2.translate(-expectedResizeOfMessageToLifelineAfterDelete, 0);
        boundsM2.resize(expectedResizeOfMessageToLifelineAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsE3);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE4);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE5);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, boundsE6);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 1, boundsE7);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 2, boundsE8);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 0, boundsE9);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 1, boundsE10);

        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, true);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, true);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM5, true);
        assertNamedMessageHasValidScreenBounds(SIXTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM6, true);

        // Delete the execution corresponding to boundsE3
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 1));
        editor.click(getExecutionScreenPosition(LIFELINE_A, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM3.translate(-expectedResizeOfMessageToExecutionAfterDelete, 0);
        boundsM3.resize(expectedResizeOfMessageToExecutionAfterDelete, 0);
        boundsM4.translate(-expectedResizeOfMessageToExecutionAfterDelete, 0);
        boundsM4.resize(expectedResizeOfMessageToExecutionAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE4);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE5);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, boundsE6);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 1, boundsE7);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 2, boundsE8);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 0, boundsE9);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 1, boundsE10);

        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, true);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, true);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM5, true);
        assertNamedMessageHasValidScreenBounds(SIXTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM6, true);

        // Delete the execution corresponding to boundsE6
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_C, 0));
        editor.click(getExecutionScreenPosition(LIFELINE_C, 0));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM5.translate(-expectedResizeOfMessageToLifelineAfterDelete, 0);
        boundsM5.resize(expectedResizeOfMessageToLifelineAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE4);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE5);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, boundsE7);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 1, boundsE8);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 0, boundsE9);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 1, boundsE10);

        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, true);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, true);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM5, true);
        assertNamedMessageHasValidScreenBounds(SIXTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM6, true);

        // Delete the execution corresponding to boundsE8
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_C, 1));
        editor.click(getExecutionScreenPosition(LIFELINE_C, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        editor.click(0, 0);
        manualRefresh();

        // Expected bounds after the drag
        boundsM6.translate(-expectedResizeOfMessageToExecutionAfterDelete, 0);
        boundsM6.resize(expectedResizeOfMessageToExecutionAfterDelete, 0);

        // Scroll to (0, 0) to avoid side effect of the scroll bars in case of
        // small screen or higher toolbar in some environment.
        editor.scrollTo(0, 0);

        // Validate bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE4);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE5);
        assertExecutionHasValidScreenBounds(LIFELINE_C, 0, boundsE7);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 0, boundsE9);
        assertExecutionHasValidScreenBounds(NEW_LIFELINE, 1, boundsE10);

        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, true);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, true);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM5, true);
        assertNamedMessageHasValidScreenBounds(SIXTH_MESSAGE_SYNC_CALL_ON_NEW_LIFELINE, boundsM6, true);
    }

}
