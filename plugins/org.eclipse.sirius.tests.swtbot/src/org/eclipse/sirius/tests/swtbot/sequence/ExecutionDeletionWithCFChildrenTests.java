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

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Tests execution deletion when some reparent or reconnection are needed in
 * case of indirect childhood through Combined Fragments.
 * 
 * VP-1269.
 * 
 * @author mporhel
 */
public class ExecutionDeletionWithCFChildrenTests extends AbstractSequenceDiagramTestCase {
    private static final String EXEC_D2 = "execD2";

    private static final String EXEC_D1 = "execD1";

    private static final String EXEC_C2 = "execC2";

    private static final String EXEC_C1 = "execC1";

    private static final String EXEC_B2 = "execB2";

    private static final String EXEC_B1 = "execB1";

    private static final String EXEC_A4 = "execA4";

    private static final String EXEC_A3 = "execA3";

    private static final String EXEC_A2 = "execA2";

    private static final String EXEC_A1 = "execA1";

    private static final String PATH = DATA_UNIT_DIR + "delete/VP-1296/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample #1";

    private static final String MODEL = "combinedFragments.interactions";

    private static final String SESSION_FILE = "combinedFragments.aird";

    private static final String TYPES_FILE = "types.ecore";

    private static final String CF_1 = "cf1";

    private static final String CF_2 = "cf2";

    private static final String OP_1 = "[op1]";

    private static final String OP_2 = "[op2]";

    private static final String OP_3 = "[op3]";

    private static final String m1 = "m1";

    private static final String m2 = "m2";

    private static final String m3 = "m3";

    private static final String m4 = "m4";

    private static final String m5 = "m5";

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
     */
    @Override
    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    // Fragments
    private Rectangle cf1Bounds;

    private Rectangle cf2Bounds;

    private Rectangle op1Bounds;

    private Rectangle op2Bounds;

    private Rectangle op3Bounds;

    // Execs
    private Rectangle execA1Bounds;

    private Rectangle execA2Bounds;

    private Rectangle execA3Bounds;

    private Rectangle execA4Bounds;

    private Rectangle execB1Bounds;

    private Rectangle execB2Bounds;

    private Rectangle execC1Bounds;

    private Rectangle execC2Bounds;

    private Rectangle execD1Bounds;

    private Rectangle execD2Bounds;

    private int m1y;

    private int m2y;

    private int m3y;

    private int m4y;

    private int m5y;

    private SWTBotGefEditPart lifelineA;

    private SWTBotGefEditPart lifelineB;

    private SWTBotGefEditPart lifelineC;

    private SWTBotGefEditPart lifelineD;

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

        lifelineA = getLifelineBot(LIFELINE_A);
        lifelineB = getLifelineBot(LIFELINE_B);
        lifelineC = getLifelineBot(LIFELINE_C);
        lifelineD = getLifelineBot(LIFELINE_D);

        // Bounds
        cf1Bounds = editor.getBounds(editor.getEditPart(CF_1));
        op1Bounds = editor.getBounds(editor.getEditPart(OP_1));
        op2Bounds = editor.getBounds(editor.getEditPart(OP_2));

        cf2Bounds = editor.getBounds(editor.getEditPart(CF_2));
        op3Bounds = editor.getBounds(editor.getEditPart(OP_3));

        execA1Bounds = editor.getBounds(getExecutionBot(EXEC_A1, lifelineA));
        execA2Bounds = editor.getBounds(getExecutionBot(EXEC_A2, lifelineA));
        execA3Bounds = editor.getBounds(getExecutionBot(EXEC_A3, lifelineA));
        execA4Bounds = editor.getBounds(getExecutionBot(EXEC_A4, lifelineA));
        execB1Bounds = editor.getBounds(getExecutionBot(EXEC_B1, lifelineB));
        execB2Bounds = editor.getBounds(getExecutionBot(EXEC_B2, lifelineB));
        execC1Bounds = editor.getBounds(getExecutionBot(EXEC_C1, lifelineC));
        execC2Bounds = editor.getBounds(getExecutionBot(EXEC_C2, lifelineC));
        execD1Bounds = editor.getBounds(getExecutionBot(EXEC_D1, lifelineD));
        execD2Bounds = editor.getBounds(getExecutionBot(EXEC_D2, lifelineD));

        m1y = editor.getBounds(editor.getEditPart(m1)).y;
        m2y = editor.getBounds(editor.getEditPart(m2)).y;
        m3y = editor.getBounds(editor.getEditPart(m3)).y;
        m4y = editor.getBounds(editor.getEditPart(m4)).y;
        m5y = editor.getBounds(editor.getEditPart(m5)).y;
    }

    private SWTBotGefEditPart getLifelineBot(final String label) {
        Matcher<EditPart> matcher = new BaseMatcher<EditPart>() {
            public boolean matches(Object item) {
                return item instanceof LifelineEditPart;
            }

            public void describeTo(Description description) {
                description.appendText("the lifeline edit part targeting the lifeline named " + label);
            }
        };

        List<SWTBotGefEditPart> descendants = editor.getEditPart(label).parent().descendants(matcher);
        return descendants.isEmpty() ? null : descendants.get(0);
    }

    private SWTBotGefEditPart getExecutionBot(final String label, SWTBotGefEditPart lifeline) {
        Matcher<EditPart> matcher = new BaseMatcher<EditPart>() {
            public boolean matches(Object item) {
                if (item instanceof ExecutionEditPart) {
                    EObject execution = ((ExecutionEditPart) item).resolveTargetSemanticElement();
                    return execution instanceof Execution && label.equals(((Execution) execution).getName());
                }
                return false;
            }

            public void describeTo(Description description) {
                description.appendText("the execution edit part targeting the execution named " + label);
            }
        };

        List<SWTBotGefEditPart> descendants = lifeline.descendants(matcher);
        return descendants.isEmpty() ? null : descendants.get(0);
    }

    /**
     * @param m12
     * @param lifelineB2
     * @param executionBot
     */
    private void checkConnection(String label, SWTBotGefEditPart sourceBot, SWTBotGefEditPart targetBot) {
        assertFalse(sourceBot.sourceConnections().isEmpty());
        assertFalse(targetBot.targetConnections().isEmpty());

        SWTBotGefConnectionEditPart message = (SWTBotGefConnectionEditPart) editor.getEditPart(label).parent();
        assertEquals(sourceBot, message.source());
        assertEquals(targetBot, message.target());
    }

    private void checkParent(SWTBotGefEditPart bot, SWTBotGefEditPart expectedParentBot) {
        assertNotNull(bot);
        assertNotNull(expectedParentBot);
        assertEquals(expectedParentBot, bot.parent());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testParentExecutionDeletionReparentToExecution() {

        // execA1 deletion
        editor.click(getExecutionBot(EXEC_A1, lifelineA));
        ICondition done = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(done);

        assertNull(getExecutionBot(EXEC_A1, lifelineA));
        checkParent(getExecutionBot(EXEC_A2, lifelineA), lifelineA);
        checkParent(getExecutionBot(EXEC_A3, lifelineA), lifelineA);
        checkDiagramStability();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testGrandParentExecutionDeletionReparentToLifeline() {
        checkConnection(m4, getExecutionBot(EXEC_B1, lifelineB), getExecutionBot(EXEC_A2, lifelineA));

        // execB1 deletion
        editor.click(getExecutionBot(EXEC_B1, lifelineB));
        ICondition done = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(done);

        assertNull(getExecutionBot(EXEC_B1, lifelineB));
        checkConnection(m4, lifelineB, getExecutionBot(EXEC_A2, lifelineA));
        checkParent(getExecutionBot(EXEC_B2, lifelineB), lifelineB);
        checkDiagramStability();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testGrandParentExecutionDeletionReparentToExecutionInFrame() {
        // execA3 deletion
        editor.click(getExecutionBot(EXEC_A3, lifelineA));
        ICondition done = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(done);

        assertNull(getExecutionBot(EXEC_A3, lifelineA));
        checkParent(getExecutionBot(EXEC_A4, lifelineA), getExecutionBot(EXEC_A1, lifelineA));
        checkDiagramStability();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testGrandParentExecutionDeletionReparentToLifelineInFrame() {
        checkConnection(m1, getExecutionBot(EXEC_C2, lifelineC), getExecutionBot(EXEC_D2, lifelineD));
        checkConnection(m2, getExecutionBot(EXEC_D1, lifelineD), getExecutionBot(EXEC_C2, lifelineC));

        // execD1 deletion
        editor.click(getExecutionBot(EXEC_D1, lifelineD));
        ICondition done = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(done);

        assertNull(getExecutionBot(EXEC_D1, lifelineD));
        checkConnection(m1, getExecutionBot(EXEC_C2, lifelineC), getExecutionBot(EXEC_D2, lifelineD));
        checkConnection(m2, lifelineD, getExecutionBot(EXEC_C2, lifelineC));
        checkDiagramStability();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExecutionDeletionParentReconnection() {
        // execA2 deletion
        editor.click(getExecutionBot(EXEC_A2, lifelineA));
        ICondition done = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(done);

        assertNull(getExecutionBot(EXEC_A2, lifelineA));
        checkDiagramStability();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testExecutionDeletionLifelineReconnection() {
        checkConnection(m1, getExecutionBot(EXEC_C2, lifelineC), getExecutionBot(EXEC_D2, lifelineD));
        checkConnection(m2, getExecutionBot(EXEC_D1, lifelineD), getExecutionBot(EXEC_C2, lifelineC));

        // execC2 deletion
        editor.click(getExecutionBot(EXEC_C2, lifelineC));
        ICondition done = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(done);

        assertNull(getExecutionBot(EXEC_C2, lifelineC));
        checkConnection(m1, lifelineC, getExecutionBot(EXEC_D2, lifelineD));
        checkConnection(m2, getExecutionBot(EXEC_D1, lifelineD), lifelineC);
        checkDiagramStability();
    }

    private void checkDiagramStability() {
        editor.scrollTo(0, 0);

        checkStability(editor.getEditPart(CF_1), cf1Bounds);
        checkStability(editor.getEditPart(OP_1), op1Bounds);
        checkStability(editor.getEditPart(OP_2), op2Bounds);

        checkStability(editor.getEditPart(CF_2), cf2Bounds);
        checkStability(editor.getEditPart(OP_3), op3Bounds);

        checkStability(getExecutionBot(EXEC_A1, lifelineA), execA1Bounds);
        checkStability(getExecutionBot(EXEC_A2, lifelineA), execA2Bounds);
        checkStability(getExecutionBot(EXEC_A3, lifelineA), execA3Bounds);
        checkStability(getExecutionBot(EXEC_A4, lifelineA), execA4Bounds);
        checkStability(getExecutionBot(EXEC_B1, lifelineB), execB1Bounds);
        checkStability(getExecutionBot(EXEC_B2, lifelineB), execB2Bounds);
        checkStability(getExecutionBot(EXEC_C1, lifelineC), execC1Bounds);
        checkStability(getExecutionBot(EXEC_C2, lifelineC), execC2Bounds);
        checkStability(getExecutionBot(EXEC_D1, lifelineD), execD1Bounds);
        checkStability(getExecutionBot(EXEC_D2, lifelineD), execD2Bounds);

        checkStability(editor.getEditPart(m1), new Rectangle(0, m1y, 0, 0));
        checkStability(editor.getEditPart(m2), new Rectangle(0, m2y, 0, 0));
        checkStability(editor.getEditPart(m3), new Rectangle(0, m3y, 0, 0));
        checkStability(editor.getEditPart(m4), new Rectangle(0, m4y, 0, 0));
        checkStability(editor.getEditPart(m5), new Rectangle(0, m5y, 0, 0));
    }

    /**
     * Tests bounds of the part with the given name, if exists.
     * 
     * @param name
     *            the name
     * @param expectedBounds
     *            the expected bounds (logical)
     */
    private void checkStability(SWTBotGefEditPart bot, Rectangle expectedBounds) {
        if (bot != null) {
            if (bot.part() instanceof SequenceMessageEditPart) {
                assertMessageVerticalPosition((SequenceMessageEditPart) bot.part(), expectedBounds.y);
            } else if (bot.part() instanceof ISequenceEventEditPart) {
                assertSequenceNodeHasValidBounds((ISequenceEventEditPart) bot.part(), "", expectedBounds, false, true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        lifelineA = null;
        lifelineB = null;
        lifelineC = null;
        lifelineD = null;

        // CFCs
        cf1Bounds = null;
        cf2Bounds = null;
        op1Bounds = null;
        op2Bounds = null;
        op3Bounds = null;

        // Execs
        execA1Bounds = null;
        execA2Bounds = null;
        execA3Bounds = null;
        execA4Bounds = null;
        execB1Bounds = null;
        execB2Bounds = null;
        execC1Bounds = null;
        execC2Bounds = null;
        execD1Bounds = null;
        execD2Bounds = null;

        super.tearDown();
    }
}
