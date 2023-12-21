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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test InstanceRoleResizableEditPolicy for move and resize commands.
 * 
 * @author edugueperoux
 */
public class InstanceRoleResizableEditPolicyTests extends AbstractDefaultModelSequenceTests {

    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private SWTBotGefEditPart instanceRoleEditPartCBot;

    private Rectangle instanceRoleEditPartABounds;

    private Rectangle instanceRoleEditPartBBounds;

    private Rectangle instanceRoleEditPartCBounds;

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

        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
        instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);

        instanceRoleEditPartABounds = editor.getBounds(instanceRoleEditPartABot);
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand : test that draging
     * LIFELINE_B to (20,0) delta shift correctly LIFELINE_B and LIFELINE_C
     */
    public void testInstanceRoleMoveSimpleToRight() {
        // drag LIFELINE_B to (20,0) delta
        editor.drag(instanceRoleEditPartBBot, instanceRoleEditPartBBounds.x + 20, origin.y);
        assertEquals(instanceRoleEditPartABounds.getLocation(), editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getLocation().getTranslated(20, 0), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getLocation().getTranslated(0, 0), editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand : test that drag of
     * LIFELINE_A in top left margin is not allowed
     */
    public void testInstanceRoleMoveSimpleOnLeftMargin() {
        // drag LIFELINE_A to (-2,-2) delta to test
        // LayoutConstants.LIFELINES_START margin constraints
        editor.drag(instanceRoleEditPartABot, origin.x - 2, origin.y - 2);
        assertEquals(origin, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand : drag LIFELINE_A to
     * (30,0) delta to test if LIFELINE_B and LIFELINE_C are correctly shifted
     * when LIFELINE_A move to right.
     */
    public void testInstanceRoleMoveAToShiftBAndC() {
        editor.drag(instanceRoleEditPartBBot, instanceRoleEditPartBBounds.x + 20, origin.y);

        // drag LIFELINE_A to (30,0) delta to test if LIFELINE_B and LIFELINE_C
        // are correctly shifted when LIFELINE_A move to right
        int delta = 30;
        editor.drag(instanceRoleEditPartABot, origin.x + delta, origin.y);
        assertEquals(origin.getTranslated(delta, 0), editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getLocation().getTranslated(delta - 10, 0), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getLocation().getTranslated(0, 0), editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand.
     */
    public void _testInstanceRoleMoveDOnLeftToShiftLeftMostABAndC() {
        // Create a fourteenth InstanceRoleEditPart's figure named D
        String LIFELINE_D = "d";
        final SWTBotGefEditPart instanceRoleEditPartDBot = createParticipant(LIFELINE_D, instanceRoleEditPartCBounds.getRight().x + LayoutConstants.LIFELINES_MIN_X_GAP * 2,
                LayoutConstants.LIFELINES_START_Y);
        LIFELINE_D += " : ";
        Rectangle instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);
        editor.drag(instanceRoleEditPartABot, origin.x + 30, origin.y);
        editor.drag(instanceRoleEditPartABot, origin.x + 20, origin.y);

        // drag LIFELINE_D to the left to test if LIFELINE_A, LIFELINE_B and
        // LIFELINE_C are correctly shifted to leftmost
        int delta = LayoutConstants.LIFELINES_MIN_X_GAP + 20;
        editor.drag(instanceRoleEditPartDBot, instanceRoleEditPartDBounds.x - delta, LayoutConstants.LIFELINES_START_Y - 10);
        assertEquals(origin.getTranslated(70, 50), editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getLocation(), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getLocation().getTranslated(330, 50), editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getLocation().getTranslated(LayoutConstants.LIFELINES_MIN_X_GAP + instanceRoleEditPartDBounds.width - 20, 0),
                editor.getLocation(LIFELINE_D, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand.
     */
    // WARNING this test checks constraints for InstanceRole but don't concerns
    // InstanceRoleResizableEditPolicy but the CreationTool associated to the
    // InstanceRole
    public void testInstanceRoleCreateEBetweenBAndC() {
        // Create a fourteenth InstanceRoleEditPart's figure named D
        String LIFELINE_D = "d";
        SWTBotGefEditPart instanceRoleEditPartDBot = createParticipant(LIFELINE_D, instanceRoleEditPartCBounds.getRight().x, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_D += " : ";

        // Create a fourteenth InstanceRoleEditPart's figure named E and dropped
        // between LIFELINE_B and LIFELINE_C
        String LIFELINE_E = "e";
        createParticipant("e", instanceRoleEditPartCBounds.x - LayoutConstants.LIFELINES_MIN_X_GAP / 2, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_E += " : ";

        assertEquals(origin, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getLocation(), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        Point instanceRoleEditPartELocation = instanceRoleEditPartBBounds.getTopRight().getTranslated(LayoutConstants.LIFELINES_MIN_X_GAP + 15, 0);
        Rectangle instanceRoleEditPartEBounds = editor.getBounds(instanceRoleEditPartDBot);
        assertEquals(instanceRoleEditPartELocation, editor.getLocation(LIFELINE_E, InstanceRoleEditPart.class));
        Point instanceRoleEditPartCLocation = instanceRoleEditPartELocation.getTranslated(instanceRoleEditPartEBounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartCLocation, editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));
        Point instanceRoleEditPartDLocation = instanceRoleEditPartCLocation.getTranslated(instanceRoleEditPartCBounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartDLocation, editor.getLocation(LIFELINE_D, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand.
     */
    public void testInstanceRoleCreateEBetweenBAndC2() {
        // Create a fourteenth InstanceRoleEditPart's figure named D
        String LIFELINE_D = "d";
        SWTBotGefEditPart instanceRoleEditPartDBot = createParticipant(LIFELINE_D, instanceRoleEditPartCBounds.getRight().x, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_D += " : ";
        Rectangle instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);

        // Create a fourteenth InstanceRoleEditPart's figure named E and drop it
        // between LIFELINE_B and LIFELINE_C
        String LIFELINE_E = "e";
        SWTBotGefEditPart instanceRoleEditPartEBot = createParticipant(LIFELINE_E, instanceRoleEditPartDBounds.getTopRight().x, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_E += " : ";
        Rectangle instanceRoleEditPartEBounds = editor.getBounds(instanceRoleEditPartEBot);

        // drag LIFELINE_E between LIFELINE_B and LIFELINE_C
        editor.drag(instanceRoleEditPartEBot, instanceRoleEditPartCBounds.x - LayoutConstants.LIFELINES_MIN_X_GAP / 2, LayoutConstants.LIFELINES_START_Y);

        assertEquals(origin, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getLocation(), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        Point instanceRoleEditPartELocation = instanceRoleEditPartBBounds.getTopRight().getTranslated(LayoutConstants.LIFELINES_MIN_X_GAP + 15, 0);
        assertEquals(instanceRoleEditPartELocation, editor.getLocation(LIFELINE_E, InstanceRoleEditPart.class));
        Point instanceRoleEditPartCLocation = instanceRoleEditPartELocation.getTranslated(instanceRoleEditPartEBounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartCLocation, editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));
        Point instanceRoleEditPartDLocation = instanceRoleEditPartCLocation.getTranslated(instanceRoleEditPartCBounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartDLocation, editor.getLocation(LIFELINE_D, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand.
     */
    public void testInstanceRoleMoveWithCreateMessage() {
        // Create a fourteenth InstanceRoleEditPart's figure named D
        String LIFELINE_D = "d";
        SWTBotGefEditPart instanceRoleEditPartDBot = createParticipant(LIFELINE_D, instanceRoleEditPartCBounds.getRight().x, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_D += " : ";
        Rectangle instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);

        // Create a fourteenth InstanceRoleEditPart's figure named E
        String LIFELINE_E = "e";
        SWTBotGefEditPart instanceRoleEditPartEBot = createParticipant("e", instanceRoleEditPartDBounds.getTopRight().x, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_E += " : ";
        Rectangle instanceRoleEditPartEBounds = editor.getBounds(instanceRoleEditPartEBot);

        // test drag a InstanceRoleEditPart's figure referenced from a Create
        // message
        int delta = 10;
        ICondition condition = new CheckEditPartMoved(instanceRoleEditPartDBot);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, LIFELINE_B, instanceRoleEditPartBBounds.getBottom().y + delta, LIFELINE_D, instanceRoleEditPartDBounds.y + 10);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(condition);

        assertEquals(instanceRoleEditPartABounds.getLocation(), editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getLocation(), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getLocation(), editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));
        // if layout is done pack mode then use
        // LayoutConstants.TIME_START_OFFSET
        assertEquals(instanceRoleEditPartBBounds.getBottom().y + LayoutConstants.TIME_START_OFFSET /* delta */- instanceRoleEditPartDBounds.height / 2
                - (LayoutConstants.TIME_START_OFFSET - LayoutConstants.TIME_START_MIN_OFFSET), editor.getLocation(LIFELINE_D, InstanceRoleEditPart.class).y);
        assertEquals(instanceRoleEditPartEBounds.getLocation(), editor.getLocation(LIFELINE_E, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand.
     */
    // TODO : VP-1036
    public void _testInstanceRoleMoveMultiple1() {
        // Create a fourteenth InstanceRoleEditPart's figure named D
        String LIFELINE_D = "d";
        SWTBotGefEditPart instanceRoleEditPartDBot = createParticipant(LIFELINE_D, instanceRoleEditPartCBounds.getRight().x, LayoutConstants.LIFELINES_START_Y);
        LIFELINE_D += " : ";
        Rectangle instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);

        // Create a fourteenth InstanceRoleEditPart's figure named E
        String LIFELINE_E = "e";
        createParticipant("e", instanceRoleEditPartDBounds.getTopRight().x, LayoutConstants.LIFELINES_START_Y);
        LIFELINE_E += " : ";

        // Multiple moving of LIFELINE_A and LIFELINE_B on LIFELINE_D to have :
        // LIFELINE_C, LIFELINE_A, LIFELINE_D, LIFELINE_B and LIFELINE_E order
        editor.select(instanceRoleEditPartABot, instanceRoleEditPartBBot);
        editor.drag(instanceRoleEditPartABot, instanceRoleEditPartDBounds.x - LayoutConstants.LIFELINES_MIN_X_GAP / 2, origin.y);

        assertEquals(instanceRoleEditPartCBounds.getLocation(), editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));
        Point instanceRoleEditPartAFutureLocation = instanceRoleEditPartCBounds.getTopRight().getTranslated(LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartAFutureLocation, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        Point instanceRoleEditPartDFutureLocation = instanceRoleEditPartAFutureLocation.getTranslated(instanceRoleEditPartABounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartDFutureLocation, editor.getLocation(LIFELINE_D, InstanceRoleEditPart.class));
        Point instanceRoleEditPartBFutureLocation = instanceRoleEditPartDFutureLocation.getTranslated(instanceRoleEditPartDBounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartBFutureLocation, editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        Point instanceRoleEditPartEFutureLocation = instanceRoleEditPartBFutureLocation.getTranslated(instanceRoleEditPartBBounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartEFutureLocation, editor.getLocation(LIFELINE_E, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand.
     */
    public void testInstanceRoleMoveMultiple2() {
        // Create a fourteenth InstanceRoleEditPart's figure named D
        String LIFELINE_D = "d";
        SWTBotGefEditPart instanceRoleEditPartDBot = createParticipant(LIFELINE_D, instanceRoleEditPartCBounds.getRight().x, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_D += " : ";
        Rectangle instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);

        // Create a fourteenth InstanceRoleEditPart's figure named E
        String LIFELINE_E = "e";
        createParticipant("e", instanceRoleEditPartDBounds.getTopRight().x, LayoutConstants.LIFELINES_START_Y);
        SWTBotUtils.waitAllUiEvents();
        LIFELINE_E += " : ";

        // Multiple moving of LIFELINE_A and LIFELINE_C on LIFELINE_D to have :
        // LIFELINE_B, LIFELINE_A, LIFELINE_D, LIFELINE_E and LIFELINE_C order
        editor.select(instanceRoleEditPartABot.parent(), instanceRoleEditPartCBot.parent());
        editor.drag(instanceRoleEditPartABot.parent(), instanceRoleEditPartCBounds.getTopRight().x, origin.y);
        SWTBotUtils.waitAllUiEvents();

        assertEquals(instanceRoleEditPartBBounds.getLocation(), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        Point instanceRoleEditPartAFutureLocation = instanceRoleEditPartCBounds.getTopRight();
        assertEquals(instanceRoleEditPartAFutureLocation, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        Point instanceRoleEditPartDFutureLocation = instanceRoleEditPartAFutureLocation.getTranslated(instanceRoleEditPartABounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartDFutureLocation, editor.getLocation(LIFELINE_D, InstanceRoleEditPart.class));
        Point instanceRoleEditPartEFutureLocation = instanceRoleEditPartDFutureLocation.getTranslated(instanceRoleEditPartDBounds.width + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartEFutureLocation, editor.getLocation(LIFELINE_E, InstanceRoleEditPart.class));
        Point instanceRoleEditPartCFutureLocation = instanceRoleEditPartEFutureLocation.getTranslated(getScreenSize(LIFELINE_E).width + LayoutConstants.LIFELINES_MIN_PACKED_X_GAP + 10, 0);
        assertEquals(instanceRoleEditPartCFutureLocation, editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));
    }

    /**
     * Test InstanceRoleResizableEditPolicy#getResizeCommand.
     * 
     */
    public void testInstanceRoleResizeOnLeftMargin() {
        // Test if resize to left of LIFELINE_A doesn't change bounds because of
        // left margin constraint
        instanceRoleEditPartABot.resize(PositionConstants.EAST, instanceRoleEditPartABounds.width + 10, instanceRoleEditPartABounds.height);
        assertEquals(origin, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartABounds.getSize(), editor.getDimension(LIFELINE_A, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getResizeCommand.
     * 
     */
    public void testInstanceRoleResizeOnTopMargin() {
        // Test if resize to top of LIFELINE_A doesn't change bounds
        instanceRoleEditPartABot.resize(PositionConstants.NORTH, instanceRoleEditPartABounds.width, instanceRoleEditPartABounds.height + 10);
        assertEquals(origin, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartABounds.getSize(), editor.getDimension(LIFELINE_A, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getResizeCommand.
     */
    public void testInstanceRoleResizeOfCOnLeftLimit() {
        // Test if resize of LIFELINE_C to left limit shift correclty LIFELINE_A
        // and LIFELINE_B to the leftmost
        int deltaOfA = 30;
        ICondition condition = new CheckEditPartMoved(instanceRoleEditPartABot);
        editor.drag(instanceRoleEditPartABot, origin.x + deltaOfA, origin.y);
        bot.waitUntil(condition);
        int deltaOfC = 30;
        condition = new CheckEditPartMoved(instanceRoleEditPartCBot);
        editor.drag(instanceRoleEditPartCBot, instanceRoleEditPartCBounds.getLocation().x + deltaOfA + deltaOfC, origin.y);
        bot.waitUntil(condition);

        instanceRoleEditPartCBot.resize(PositionConstants.EAST, instanceRoleEditPartCBounds.width + deltaOfA + deltaOfC, instanceRoleEditPartABounds.height);

        assertEquals(instanceRoleEditPartABounds.getLocation().getTranslated(30, 0), editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getLocation().getTranslated(10, 0), editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getLocation(), editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartABounds.getSize(), editor.getDimension(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getSize(), editor.getDimension(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getSize().getExpanded(deltaOfA + deltaOfC, 0), editor.getDimension(LIFELINE_C, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getResizeCommand.
     */
    public void testInstanceRoleResizeOfAOnRight() {
        // Test if resize to right of LIFELINE_A resize correctly
        int delta = 10;
        instanceRoleEditPartABot.parent().select();
        instanceRoleEditPartABot.parent().resize(PositionConstants.WEST, instanceRoleEditPartABounds.width + delta, instanceRoleEditPartABounds.height);
        assertEquals(origin, editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartABounds.getSize().getExpanded(delta, 0), editor.getDimension(LIFELINE_A, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getResizeCommand.
     */
    public void testInstanceRoleResizeOfAOverB() {
        // Test if resize LIFELINE_A over LIFELINE_B shift correctly all
        // InstanceRoles at the right, and conserve the gap between B and C
        int deltaX_B_C = instanceRoleEditPartBBounds.getLocation().getDifference(instanceRoleEditPartCBounds.getLocation()).width;
        int delta = 2 * LayoutConstants.LIFELINES_MIN_X_GAP + instanceRoleEditPartBBounds.width;
        instanceRoleEditPartABot.parent().select();
        instanceRoleEditPartABot.resize(PositionConstants.WEST, instanceRoleEditPartABounds.width + delta, instanceRoleEditPartABounds.height);

        assertEquals(instanceRoleEditPartABounds.getLocation(), editor.getLocation(LIFELINE_A, InstanceRoleEditPart.class));
        Point instanceRoleEditPartBFutureLocation = instanceRoleEditPartABounds.getLocation().getTranslated(instanceRoleEditPartABounds.width + delta + LayoutConstants.LIFELINES_MIN_X_GAP, 0);
        assertEquals(instanceRoleEditPartBFutureLocation, editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(deltaX_B_C, editor.getLocation(LIFELINE_B, InstanceRoleEditPart.class).getDifference(editor.getLocation(LIFELINE_C, InstanceRoleEditPart.class)).width);

        assertEquals(instanceRoleEditPartABounds.getSize().getExpanded(delta, 0), editor.getDimension(LIFELINE_A, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartBBounds.getSize(), editor.getDimension(LIFELINE_B, InstanceRoleEditPart.class));
        assertEquals(instanceRoleEditPartCBounds.getSize(), editor.getDimension(LIFELINE_C, InstanceRoleEditPart.class));

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getResizeCommand.
     * 
     * @throws Exception
     *             exception
     */
    public void testInstanceRoleResizeOnCreateMessage() throws Exception {
        // resize of LIFELINE_B in SOUTH to test if create message is overlapped
        createMessage(InteractionsConstants.CREATE_TOOL_ID, LIFELINE_A, instanceRoleEditPartABounds.getBottom().y + 10, LIFELINE_C, instanceRoleEditPartCBounds.y + 10);
        SequenceMessageEditPart sequenceMessageEditPart = getSequenceMessageEditPart("m_create1");
        instanceRoleEditPartBBot.resize(PositionConstants.SOUTH, instanceRoleEditPartBBounds.width, instanceRoleEditPartBBounds.height + 100);
        assertTrue(sequenceMessageEditPart.getConnectionFigure().getBounds().getCenter().y > instanceRoleEditPartBBounds.y + editor.getDimension(LIFELINE_B, InstanceRoleEditPart.class).height);

    }

    /**
     * Test InstanceRoleResizableEditPolicy#getResizeCommand : VP-1088
     */
    public void testInstanceRoleResizeMultiple() {
        // Test multiple selection resize of LIFELINE_A and LIFELINE_B in
        // direction of bottom left corner
        editor.select(instanceRoleEditPartABot.parent(), instanceRoleEditPartBBot.parent());
        Dimension sizeDelta = new Dimension(20, 20);

        ICondition condition = new CheckEditPartResized(instanceRoleEditPartABot);
        instanceRoleEditPartABot.resize(PositionConstants.SOUTH_WEST, instanceRoleEditPartBBounds.width + sizeDelta.width, instanceRoleEditPartBBounds.height + sizeDelta.height);
        bot.waitUntil(condition);

        assertEquals(instanceRoleEditPartABounds.getResized(sizeDelta), editor.getBounds(instanceRoleEditPartABot));
        assertEquals(instanceRoleEditPartBBounds.getResized(sizeDelta), editor.getBounds(instanceRoleEditPartBBot));
        assertEquals(instanceRoleEditPartCBounds, editor.getBounds(instanceRoleEditPartCBot));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        if (editor != null) {
            restoreEditor(editor);
        } else {
            maximizeEditor();
        }

        instanceRoleEditPartABot = null;
        instanceRoleEditPartBBot = null;
        instanceRoleEditPartCBot = null;

        instanceRoleEditPartABounds = null;
        instanceRoleEditPartBBounds = null;
        instanceRoleEditPartCBounds = null;

        super.tearDown();
    }
}
