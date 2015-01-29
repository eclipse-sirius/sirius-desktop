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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test Combined fragment drag on existing sequence events.
 * 
 * Test VP-2466.
 * 
 * 
 * @author jdupont
 */
public class CombineFragmentEncompassingExistingSequenceEventsTest extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "combineFragmentEncompassingExistingSequenceEvents/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Lifelines";

    private static final String MODEL = "lifelines.interactions";

    private static final String SESSION_FILE = "lifelines.aird";

    private static final String TYPES_FILE = "types.ecore";

    private SWTBotGefEditPart instanceRoleEditPartBBot;

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
     * {@inheritDoc}
     * 
     * @throws Exception
     *             if exception thrown
     */
    @Override
    public void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
    }

    /**
     * Test creating Combine Fragment encompassing existing sequence events. The
     * combine fragment should not encompassing existing sequence events because
     * the message is on the 2 life line and the combined fragment creation is
     * only on one life line.
     */
    public void testCombinedFragmentNotEncompassingDragOnExistingSequenceEvents() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) - 40;
        int positionXLifeLineB2 = getLifelineScreenX(LIFELINE_B) + 40;
        Point pointB = new Point(positionXLifeLineB, 115);
        Point pointB2 = new Point(positionXLifeLineB2, 335);
        Rectangle executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        SWTBotGefEditPart sequenceDiagramBot = instanceRoleEditPartBBot.parent().parent();
        SWTBotGefEditPart combinedFragmentAlt1 = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        Rectangle combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, executionE1Bounds);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, executionE2Bounds);
        assertEquals("The combined fragment Alt1 is not properly placed", 170, combinedFragmentAlt1Bounds.y);
        // Create a new combined fragment on the existing sequenceEvents (The 2
        // execution and the combinedFragmentAlt1)
        SWTBotGefEditPart combinedFragmentAlt2 = createCombinedFragmentWithResult(pointB, pointB2);
        Rectangle combinedFragmentAlt2Bounds = editor.getBounds(combinedFragmentAlt2);
        // Check that the combined fragment not encompassing existing sequence
        // events
        assertEquals("The combined fragment Alt2 is not properly placed", 115, combinedFragmentAlt2Bounds.y);
        assertEquals("The combined fragment Alt2 is not properly placed", 336, combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height);

        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertEquals("The combined fragment Alt1 is not properly placed", 381, combinedFragmentAlt1Bounds.y);

        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y > 335);
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y > 335);
    }

    /**
     * Test creating Combine Fragment encompassing existing sequence events.
     * Create combine fragment on sequence events and the combined fragment
     * encompassing existing sequence events. Second step is to drag combined
     * fragment to verify the combined fragment encompassing existing sequence
     * events.
     */
    public void testCombinedFragmentEncompassingDragOnExistingSequenceEvents() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 40;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 40;
        Point pointA = new Point(positionXLifeLineA, 115);
        Point pointB = new Point(positionXLifeLineB, 335);
        Rectangle executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        SWTBotGefEditPart sequenceDiagramBot = instanceRoleEditPartBBot.parent().parent();
        SWTBotGefEditPart combinedFragmentAlt1 = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        Rectangle combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, executionE1Bounds);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, executionE2Bounds);
        assertEquals("The combined fragment Alt1 is not properly placed", 170, combinedFragmentAlt1Bounds.y);
        // Create a new combined fragment on the existing sequenceEvents (The 2
        // execution and the combinedFragmentAlt1)
        SWTBotGefEditPart combinedFragmentAlt2 = createCombinedFragmentWithResult(pointA, pointB);
        Rectangle combinedFragmentAlt2Bounds = editor.getBounds(combinedFragmentAlt2);
        // Check that the combined fragment encompassing existing sequence
        // events
        assertEquals("The combined fragment Alt2 is not properly placed", 115, combinedFragmentAlt2Bounds.y);
        assertEquals("The combined fragment Alt2 is not properly placed", 336, combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height);

        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertEquals("The combined fragment Alt1 is not properly placed", 190, combinedFragmentAlt1Bounds.y);

        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));

        // Drag combined fragment and check that the all sequence events are
        // moved.
        combinedFragmentAlt2.select();
        editor.drag(combinedFragmentAlt2, combinedFragmentAlt2Bounds.x, combinedFragmentAlt2Bounds.y + 100);
        bot.sleep(500);
        combinedFragmentAlt2Bounds = editor.getBounds(combinedFragmentAlt2);
        assertEquals("The combined fragment Alt2 is not properly placed", 215, combinedFragmentAlt2Bounds.y);
        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertEquals("The combined fragment Alt1 is not properly placed", 290, combinedFragmentAlt1Bounds.y);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
    }

    /**
     * Test creating Combine Fragment encompassing existing sequence events when
     * there is a scroll on diagram. Create combine fragment on sequence events
     * and the combined fragment encompassing existing sequence events. Second
     * step is to drag combined fragment to verify the combined fragment
     * encompassing existing sequence events.
     */
    public void testCombinedFragmentEncompassingDragOnExistingSequenceEventsWithScroll() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        // Move life line b to right
        LifelineEditPart lifeLineB = getLifelineEditPart(LIFELINE_B);
        editor.drag(getBotEditPart(lifeLineB), 1000, 0);
        // Zoom to 1.25
        editor.zoom(ZoomLevel.ZOOM_125);
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 40;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 40;
        Point pointA = new Point(positionXLifeLineA, (int) (115 * 1.25));
        Point pointB = new Point(positionXLifeLineB, (int) (335 * 1.25));
        Rectangle executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        SWTBotGefEditPart sequenceDiagramBot = instanceRoleEditPartBBot.parent().parent();
        SWTBotGefEditPart combinedFragmentAlt1 = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        Rectangle combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertEquals("The combined fragment Alt1 is not properly placed", (int) (170 * 1.25), combinedFragmentAlt1Bounds.y);
        // Create a new combined fragment on the existing sequenceEvents (The 2
        // execution and the combinedFragmentAlt1)
        SWTBotGefEditPart combinedFragmentAlt2 = createCombinedFragmentWithResult(pointA, pointB);
        Rectangle combinedFragmentAlt2Bounds = editor.getBounds(combinedFragmentAlt2);
        // Check that the combined fragment encompassing existing sequence
        // events
        assertEquals("The combined fragment Alt2 is not properly placed", (int) (115 * 1.25), combinedFragmentAlt2Bounds.y, 1);
        assertEquals("The combined fragment Alt2 is not properly placed", 488, combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height);

        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertEquals("The combined fragment Alt1 is not properly placed", (int) (190 * 1.25), combinedFragmentAlt1Bounds.y, 1);

        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        // Drag combined fragment and check that the all sequence events are
        // moved.
        combinedFragmentAlt2.select();
        drag(combinedFragmentAlt2Bounds.x, combinedFragmentAlt2Bounds.y, combinedFragmentAlt2Bounds.x, combinedFragmentAlt2Bounds.y + 100);
        bot.sleep(500);
        combinedFragmentAlt2Bounds = editor.getBounds(combinedFragmentAlt2);
        assertEquals("The combined fragment Alt2 is not properly placed", 245, combinedFragmentAlt2Bounds.y, 1);
        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertEquals("The combined fragment Alt1 is not properly placed", 338, combinedFragmentAlt1Bounds.y);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
    }

    /**
     * Test creating Combine Fragment encompassing existing CombinedFragment
     * after delete and undo this. The combined fragment deleted contains
     * sequence events.
     * <p>
     * Step 1 : Create combine fragment encompassing existing sequence events
     * <p>
     * Step 2 : Delete combine fragment
     * <p>
     * Step 3 : Undo Delete
     * <p>
     * Step 4 : Create a new combine fragment encompassing existing combined
     * fragment
     */
    public void _testCombinedFragmentEncompassingDragOnExistingCombinedFragmentAfeterDelete() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 40;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 40;
        Point pointA = new Point(positionXLifeLineA, 115);
        Point pointB = new Point(positionXLifeLineB, 335);
        Rectangle executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        SWTBotGefEditPart sequenceDiagramBot = instanceRoleEditPartBBot.parent().parent();
        SWTBotGefEditPart combinedFragmentAlt1 = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        Rectangle combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 0, executionE1Bounds);
        assertExecutionHasValidLogicalBounds(LIFELINE_B, 1, executionE2Bounds);
        assertEquals("The combined fragment Alt1 is not properly placed", 170, combinedFragmentAlt1Bounds.y);
        // Create a new combined fragment on the existing sequenceEvents (The 2
        // execution and the combinedFragmentAlt1)
        SWTBotGefEditPart combinedFragmentAlt2 = createCombinedFragmentWithResult(pointA, pointB);
        Rectangle combinedFragmentAlt2Bounds = editor.getBounds(combinedFragmentAlt2);
        // Check that the combined fragment encompassing existing sequence
        // events
        assertEquals("The combined fragment Alt2 is not properly placed", 115, combinedFragmentAlt2Bounds.y);
        assertEquals("The combined fragment Alt2 is not properly placed", 336, combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height);

        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertEquals("The combined fragment Alt1 is not properly placed", 190, combinedFragmentAlt1Bounds.y);

        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));

        // Delete combined fragment
        combinedFragmentAlt2.select();
        editor.clickContextMenu("Delete from Model");

        // Check that all elements are deleted
        assertNull(getExecutionEditPart(LIFELINE_B, 0));

        // Undo delete
        undo("Delete from Model");

        // Check that all elements are restored
        assertEquals("The combined fragment Alt2 is not properly placed", 115, combinedFragmentAlt2Bounds.y);
        assertEquals("The combined fragment Alt2 is not properly placed", 336, combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height);
        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertEquals("The combined fragment Alt1 is not properly placed", 190, combinedFragmentAlt1Bounds.y);

        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));

        // Create a new combine fragment encompassing the combined fragment
        Point pointA2 = new Point(positionXLifeLineA, 100);
        Point pointB2 = new Point(positionXLifeLineB, 350);
        SWTBotGefEditPart combinedFragmentAlt3 = createCombinedFragmentWithResult(pointA2, pointB2);
        Rectangle combinedFragmentAlt3Bounds = editor.getBounds(combinedFragmentAlt3);

        // Check that the combined fragment encompassing existing sequence
        // events
        assertEquals("The combined fragment Alt3 is not properly placed", 100, combinedFragmentAlt3Bounds.y, 1);
        assertEquals("The combined fragment Alt3 is not properly placed", 350, combinedFragmentAlt3Bounds.y + combinedFragmentAlt3Bounds.height, 1);

        combinedFragmentAlt1Bounds = editor.getBounds(combinedFragmentAlt1);
        assertEquals("The combined fragment Alt1 is not properly placed", 190, combinedFragmentAlt1Bounds.y);

        executionE1Bounds = getExecutionScreenBounds(LIFELINE_B, 0);
        executionE2Bounds = getExecutionScreenBounds(LIFELINE_B, 1);
        assertTrue("The execution e1 should not be in the combine fragment Alt2", executionE1Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
        assertTrue("The execution e2 should not be in the combine fragment Alt2", executionE2Bounds.y < (combinedFragmentAlt2Bounds.y + combinedFragmentAlt2Bounds.height));
    }

    /**
     * Drag and drop the specified edit part to the specified location.
     * 
     * @param xPosition
     *            the x relative position to start drag
     * @param yPosition
     *            the y relative postion to start drag
     * @param toXPosition
     *            the x relative location
     * @param toYPosition
     *            the y relative location
     */
    public void drag(final int xPosition, final int yPosition, final int toXPosition, final int toYPosition) {
        /*
         * We should increment drag location to avoid a resize. 7 comes from
         * SquareHandle#DEFAULT_HANDLE_SIZE and we divided by 2 as
         * AbstractHandle#getAccessibleLocation do that by default
         */
        int offset = 7 / 2 + 1;
        editor.drag(xPosition + 1, yPosition + 1, toXPosition + offset, toYPosition + offset);
    }

    private SWTBotGefEditPart getBotEditPart(LifelineEditPart parentExec) {
        return editor.getEditPart(parentExec.getFigure().getBounds().getCopy().getCenter(), LifelineEditPart.class);
    }

}
