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
public class ExecutionReconnectionTests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "delete/2305/";

    private static final String REPRESENTATION_ID = "Sequence Diagram on Sample";

    private static final String MODEL = "reconnectExecutions.interactions";

    private static final String SESSION_FILE = "reconnectExecutions.aird";

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
     * Deleting an execution have to reconnect its sub executions on its parent.
     * 
     */
    public void test_Delete_Execution_And_Reconnect_Sub_Executions() {

        Rectangle boundsE0 = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 130, 0, 250), false);
        Rectangle boundsE1 = assertExecutionHasValidScreenBounds(LIFELINE_A, 1, new Rectangle(0, 160, 0, 50), false);
        Rectangle boundsE2 = assertExecutionHasValidScreenBounds(LIFELINE_A, 2, new Rectangle(0, 240, 0, 110), false);
        Rectangle boundsE3 = assertExecutionHasValidScreenBounds(LIFELINE_A, 3, new Rectangle(0, 270, 0, 50), false);
        Rectangle boundsE4 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, 410, 0, 348), false);
        Rectangle boundsE5 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, 440, 0, 226), false);
        Rectangle boundsE6 = assertExecutionHasValidScreenBounds(LIFELINE_B, 2, new Rectangle(0, 596, 0, 50), false);
        int expectedTranslationAfterDelete = -(boundsE0.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width);

        // Delete the execution corresponding to boundsE0
        CheckSelectedCondition csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 0));
        editor.click(getExecutionScreenPosition(LIFELINE_A, 0));
        bot.waitUntil(csc);
        deleteSelectedElement();

        // Expected bounds after the drag
        boundsE1.translate(expectedTranslationAfterDelete, 0);
        boundsE2.translate(expectedTranslationAfterDelete, 0);
        boundsE3.translate(expectedTranslationAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 2, boundsE3);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE4);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE5);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 2, boundsE6);

        // Delete the execution corresponding to boundsE2
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 1));
        editor.click(getExecutionScreenPosition(LIFELINE_A, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        // Expected bounds after the drag
        boundsE3.translate(expectedTranslationAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsE3);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE4);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE5);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 2, boundsE6);

        // Delete the execution corresponding to boundsE5
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_B, 1));
        editor.click(getExecutionScreenPosition(LIFELINE_B, 1));
        bot.waitUntil(csc);
        deleteSelectedElement();

        // Expected bounds after the drag
        boundsE6.translate(expectedTranslationAfterDelete, 0);

        // Scroll to (0, 0) to avoid side effect of the scroll bars in case of
        // small screen or higher toolbar in some environment.
        editor.scrollTo(0, 0);

        // Validate bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsE3);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE4);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE6);

        // Delete the execution corresponding to boundsE4
        csc = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_B, 0));
        editor.click(getExecutionScreenPosition(LIFELINE_B, 0));
        bot.waitUntil(csc);
        deleteSelectedElement();

        // Expected bounds after the drag
        boundsE6.translate(expectedTranslationAfterDelete, 0);

        // Validate bounds
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 0, boundsE1);
        assertExecutionHasValidLogicalBounds(LIFELINE_A, 1, boundsE3);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, boundsE6);
    }

}
