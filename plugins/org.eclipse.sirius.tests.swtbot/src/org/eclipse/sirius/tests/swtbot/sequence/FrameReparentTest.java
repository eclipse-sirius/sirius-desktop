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
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test drag of combined fragments in other combined fragment and on time line.
 * Test drag of interaction us in other interaction use and on time line.
 * 
 * @author jdupont
 */
public class FrameReparentTest extends AbstractDefaultModelSequenceTests {

    /**
     * Test drag combindedFragment on 2 life line to combined fragment on 1 life
     * line. This action is forbidden.
     */
    public void testDragCombinedFragmentToCombinedFragmentForbidden() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 200);
        Point pointB = new Point(positionXLifeLineB, 200);
        // Create first combined fragment on life line A & B
        SWTBotGefEditPart combinedFragmentAB = createCombinedFragmentWithResult(pointA, pointB);
        Rectangle combinedFragmentBoundsAB = editor.getBounds(combinedFragmentAB);
        assertEquals("the combined fragment position is wrong", pointA.y, combinedFragmentBoundsAB.getTop().y);
        // Create second combined fragment on life line B
        SWTBotGefEditPart combinedFragmentB = createCombinedFragmentWithResult(new Point(getLifelineScreenX(LIFELINE_B), 400));
        Rectangle combinedFragmentBoundsB = editor.getBounds(combinedFragmentB);
        assertEquals("the combined fragment position is wrong", 400, combinedFragmentBoundsB.getTop().y);
        // Drag Combined fragment AB to combined fragment B (it's forbidden)
        editor.drag(combinedFragmentAB, combinedFragmentBoundsB.getCenter().x, combinedFragmentBoundsB.getCenter().y);
        bot.sleep(500);
        combinedFragmentBoundsAB = editor.getBounds(combinedFragmentAB);
        assertEquals("the combined fragment must not be moved", pointB.y, combinedFragmentBoundsAB.getTop().y);
    }

    /**
     * Test drag combined fragment on 1 life line to combined fragment on 2 life
     * line. Test also drag combined fragment on 1 life line above the combined
     * fragment on 2 life line.
     */
    public void testDragCombinedFragmentToCombinedFragment() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 200);
        Point pointB = new Point(positionXLifeLineB, 200);

        // Create first combined fragment on life line A & B
        SWTBotGefEditPart combinedFragmentAB = createCombinedFragmentWithResult(pointA, pointB);
        Rectangle combinedFragmentBoundsAB = editor.getBounds(combinedFragmentAB);
        assertEquals("the combined fragment position is wrong", pointA.y, combinedFragmentBoundsAB.getTop().y);
        // Create second combined fragment on life line B
        SWTBotGefEditPart combinedFragmentB = createCombinedFragmentWithResult(new Point(getLifelineScreenX(LIFELINE_B), 400));
        Rectangle combinedFragmentBoundsB = editor.getBounds(combinedFragmentB);
        assertEquals("the combined fragment position is wrong", 400, combinedFragmentBoundsB.getTop().y);
        editor.select(combinedFragmentB);
        // Drag Combined fragmentB to combined fragment AB
        ICondition done = new OperationDoneCondition();
        editor.drag(combinedFragmentB, combinedFragmentBoundsAB.getCenter().x, combinedFragmentBoundsAB.getCenter().y);
        bot.waitUntil(done);
        combinedFragmentBoundsB = editor.getBounds(combinedFragmentB);
        assertEquals("the combined fragment must be moved in Combined fragment on life line A & B", combinedFragmentBoundsAB.getCenter().y, combinedFragmentBoundsB.getTop().y);
        // Drag Combined fragmentB above the combined fragmentAB
        done = new OperationDoneCondition();
        editor.drag(combinedFragmentB, combinedFragmentBoundsAB.getCenter().x, 150);
        bot.waitUntil(done);
        combinedFragmentBoundsB = editor.getBounds(combinedFragmentB);
        combinedFragmentBoundsAB = editor.getBounds(combinedFragmentAB);
        assertEquals("the combined fragment must be moved in Combined fragment on life line A & B", 150, combinedFragmentBoundsB.getTop().y);
        // Drag combined fragmentB to title of combined fragmentAB (this
        // operation is forbidden)
        editor.drag(combinedFragmentB, combinedFragmentBoundsAB.getCenter().x, combinedFragmentBoundsAB.getTop().y + 5);
        bot.sleep(500);
        combinedFragmentBoundsB = editor.getBounds(combinedFragmentB);
        assertEquals("the combined fragment must be moved in Combined fragment on life line A & B", 150, combinedFragmentBoundsB.getTop().y);
    }

    /**
     * Test drag interaction use on 2 life line to interaction use on 1 life
     * line and inversely. These actions are prohibited.
     */
    public void testDragInteractionUseToInteractionUseForbidden() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 200);
        Point pointB = new Point(positionXLifeLineB, 200);

        // Create first interaction use on life line A & B
        SWTBotGefEditPart interactionUseAB = createInteractionUseWithResult(pointA, pointB);
        Rectangle interactionUseBoundsAB = editor.getBounds(interactionUseAB);
        assertEquals("the interaction use position is wrong", pointA.y, interactionUseBoundsAB.getTop().y);

        // Create second interaction use on life line B
        SWTBotGefEditPart interactionUseB = createInteractionUseWithResult(new Point(getLifelineScreenX(LIFELINE_B), 400));
        Rectangle interactionUseBoundsB = editor.getBounds(interactionUseB);
        assertEquals("the interaction use position is wrong", 400, interactionUseBoundsB.getTop().y);

        // Drag interaction use AB to interaction use B (it's forbidden)
        editor.drag(interactionUseAB, interactionUseBoundsB.getCenter().x, interactionUseBoundsB.getCenter().y);
        bot.sleep(500);
        interactionUseBoundsAB = editor.getBounds(interactionUseAB);
        assertEquals("the interaction use must not be moved", pointB.y, interactionUseBoundsAB.getTop().y);

        // Drag interaction use B to interaction use AB (it's forbidden)
        editor.drag(interactionUseB, interactionUseBoundsAB.getCenter().x, interactionUseBoundsAB.getCenter().y);
        bot.sleep(500);
        interactionUseBoundsB = editor.getBounds(interactionUseB);
        assertEquals("the interaction use must not be moved", 400, interactionUseBoundsB.getTop().y);
    }

    /**
     * Test drag interaction use on 2 life line under interaction use on 1 life
     * line and inversely.
     */
    public void testDragInteractionUseToInteractionUse() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 200);
        Point pointB = new Point(positionXLifeLineB, 200);

        // Create first interaction use on life line A & B
        SWTBotGefEditPart interactionUseAB = createInteractionUseWithResult(pointA, pointB);
        Rectangle interactionUseBoundsAB = editor.getBounds(interactionUseAB);
        assertEquals("the interaction use position is wrong", pointA.y, interactionUseBoundsAB.getTop().y);

        // Create second interaction use on life line B
        SWTBotGefEditPart interactionUseB = createInteractionUseWithResult(new Point(getLifelineScreenX(LIFELINE_B), 400));
        Rectangle interactionUseBoundsB = editor.getBounds(interactionUseB);
        assertEquals("the interaction use position is wrong", 400, interactionUseBoundsB.getTop().y);

        // Drag interaction use AB under interaction use B
        ICondition done = new OperationDoneCondition();
        editor.drag(interactionUseAB, interactionUseBoundsB.getCenter().x, interactionUseBoundsB.getBottom().y + 20);
        bot.waitUntil(done);
        interactionUseBoundsAB = editor.getBounds(interactionUseAB);
        assertEquals("the interaction use on life line A & B must be under the other interaction use", interactionUseBoundsB.getBottom().y + 20, interactionUseBoundsAB.getTop().y);

        // Drag interaction use B above interaction use AB )
        done = new OperationDoneCondition();
        editor.drag(interactionUseB, interactionUseBoundsAB.getCenter().x, interactionUseBoundsAB.getBottom().y + 20);
        bot.waitUntil(done);
        interactionUseBoundsB = editor.getBounds(interactionUseB);
        interactionUseBoundsAB = editor.getBounds(interactionUseAB);
        assertEquals("the interaction use on life line B must be above the other interaction use", interactionUseBoundsAB.getBottom().y + 20, interactionUseBoundsB.getTop().y);
    }
}
