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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test complex authorized and no authorized drag in and out combined fragment.
 * 
 * @author jdupont
 */
public class ComplexInOutCombinedFragmentTest extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "complexInOutCombinedFragmentTest/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Lifelines";

    private static final String MODEL = "lifelines.interactions";

    private static final String SESSION_FILE = "lifelines.aird";

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    /**
     * Test action drag in out combined fragment
     * <p>
     * Step 1 : drag Sync call on life line A & B with Sync call on life line B
     * & A to combined fragment
     * <p>
     * Step 2 : drag Sync call on life line A & B with Sync call on life line B
     * & A at limit of the bottom of the combined fragment to test that the
     * combined fragment is resized
     * <p>
     * Step 3 : drag Sync call on life line A & B with Sync call of line line B
     * & A under the combined fragment.
     */
    public void testDragInOutCombinedFragment() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 275);
        Point pointB = new Point(positionXLifeLineB, 275);

        ICondition done = new OperationDoneCondition();
        SWTBotGefEditPart combinedFragment = createCombinedFragmentWithResult(pointA, pointB);
        bot.waitUntil(done);

        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragment);
        assertEquals("the combined fragment position  is wrong", 275, combinedFragmentBounds.getTop().y);
        Rectangle syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_A, 0);
        assertEquals("The execution position of e2 is not correct", 150, syncCallABExecutionBounds.getTop().y);
        // Drag execution of sync call on life line A To combined fragment
        ExecutionEditPart executionE2 = getExecutionEditPart(LIFELINE_A, 0);

        done = new OperationDoneCondition();
        editor.drag(getBotEditPart(executionE2), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getCenter().y);
        bot.waitUntil(done);

        syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_A, 0);
        assertEquals("the execution must be in combined fragment", combinedFragmentBounds.getCenter().y, syncCallABExecutionBounds.getTop().y);

        // Drag execution of sync call on life line A to combinedFragment's
        // bottom limit
        final int combinedFragmentBottom = combinedFragmentBounds.getCopy().getBottom().y;
        done = new OperationDoneCondition();
        editor.drag(getBotEditPart(executionE2), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getBottom().y - 3);
        bot.waitUntil(done);

        syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_A, 0);
        combinedFragmentBounds = editor.getBounds(combinedFragment);
        assertTrue("the execution must be in combined fragment", combinedFragmentBounds.getBottom().y > syncCallABExecutionBounds.getTop().y);
        assertTrue("the combined fragment must be resized", combinedFragmentBounds.getBottom().y > combinedFragmentBottom);

        // Drag execution of sync call on life line A under combined fragment
        done = new OperationDoneCondition();
        editor.drag(getBotEditPart(executionE2), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getBottom().y + 15);
        bot.waitUntil(done);

        syncCallABExecutionBounds = getExecutionLogicalBounds(LIFELINE_A, 0);
        assertEquals("the execution must be under combined fragment", combinedFragmentBounds.getBottom().y + 15, syncCallABExecutionBounds.getTop().y);
    }

    /**
     * Test action drag in out combined fragment forbidden.
     * <p>
     * Step1 : Drag the Sync call on life line B & C next to Combined fragment
     * (this action is forbidden)
     * <p>
     * Step 2 : Drag the Async call on life line B & C to combined fragment.
     * (This action is forbidden)
     * <p>
     * Step 3 : Drag the Sync call on life line A & B with Sync call on life
     * line B & A to combined fragment's title. (This action is forbidden).
     */
    public void testDragInOutCombinedFragmentForbidden() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 275);
        Point pointB = new Point(positionXLifeLineB, 275);

        ICondition done = new OperationDoneCondition();
        SWTBotGefEditPart combinedFragment = createCombinedFragmentWithResult(pointA, pointB);
        bot.waitUntil(done);

        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragment);
        assertEquals("the combined fragment position  is wrong", 275, combinedFragmentBounds.getTop().y);
        Rectangle syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_A, 0);
        assertEquals("The execution position of e2 is not correct", 150, syncCallABExecutionBounds.getTop().y);

        // Drag the Sync call on life line B & C next to Combined fragment (drag
        // before, center and after next combined fragment)
        ExecutionEditPart executionE1 = getExecutionEditPart(LIFELINE_C, 0);
        editor.drag(getBotEditPart(executionE1), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getTop().y - 10);
        syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_C, 0);
        assertEquals("the execution must not be moved", 130, syncCallABExecutionBounds.getTop().y);

        editor.drag(getBotEditPart(executionE1), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getCenter().y);
        syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_C, 0);
        assertEquals("the execution must not be moved", 130, syncCallABExecutionBounds.getTop().y);

        editor.drag(getBotEditPart(executionE1), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getBottom().y - 10);
        syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_C, 0);
        assertEquals("the execution must not be moved", 130, syncCallABExecutionBounds.getTop().y);

        // Drag the Async call on life line B & C to combined fragment
        ExecutionEditPart executionE4 = getExecutionEditPart(LIFELINE_B, 1);
        editor.drag(getBotEditPart(executionE4), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getCenter().y);
        syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertEquals("the execution must not be moved", 370, syncCallABExecutionBounds.getTop().y);

        // Drag the Sync call on life line A & B with Sync call on life line B &
        // A to combined fragment's title
        ExecutionEditPart executionE2 = getExecutionEditPart(LIFELINE_A, 0);
        editor.drag(getBotEditPart(executionE2), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getTop().y + 2);
        syncCallABExecutionBounds = getExecutionScreenBounds(LIFELINE_A, 0);
        assertEquals("the execution must not be moved", 150, syncCallABExecutionBounds.getTop().y);
    }
}
