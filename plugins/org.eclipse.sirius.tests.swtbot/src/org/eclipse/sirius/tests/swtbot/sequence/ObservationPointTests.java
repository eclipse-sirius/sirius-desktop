/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ObservationPoint;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ObservationPointEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.Direction;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckToolIsActivated;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * This class tests the ObservationPoint concept of Sequence dagrams.
 * 
 * An observation point is a node with an ObservationPointMapping and a semantic target which belongs to the semantic
 * global ordering. On the interaction sample, it is displayed when the "Constraint" layer is active. It allows to
 * create/display constraints as edges with a bracket style.
 * 
 * @author mporhel
 */
public class ObservationPointTests extends AbstractDefaultModelSequenceTests {

    private final Predicate<DDiagramElement> isVisible = new Predicate<DDiagramElement>() {
        @Override
        public boolean apply(DDiagramElement input) {
            return input.isVisible();
        }
    };

    private final Predicate<DDiagramElement> isVisibleObsPoint = Predicates.and(ObservationPoint.viewpointElementPredicate(), isVisible);

    private final Predicate<DDiagramElement> isNonVisibleObsPoint = Predicates.and(ObservationPoint.viewpointElementPredicate(), Predicates.not(isVisible));

    /**
     * Test the activation of the observation layer. It should trigger the creation of observation points. They should
     * be placed by the sequence layout.
     */
    public void test_ObservationPointLayer_Activation() {
        doTest_ObservationPoint_Layer_Activation(ZoomLevel.ZOOM_100.getAmount());
    }

    /**
     * Test the activation of the observation layer. It should trigger the creation of observation points. They should
     * be placed by the sequence layout.
     */
    public void test_ObservationPointLayer_Activation_Zoom50() {
        try {
            ZoomLevel zoom50 = ZoomLevel.ZOOM_50;
            editor.zoom(zoom50);

            doTest_ObservationPoint_Layer_Activation(zoom50.getAmount());

        } finally {
            editor.click(0, 0);
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    private void doTest_ObservationPoint_Layer_Activation(double zoomLevel) {
        doCreate_ExecA0_ExecA1(200, zoomLevel);
        DDiagram diagram = (DDiagram) ((DialectEditor) editor.getReference().getEditor(false)).getRepresentation();

        assertEquals("There should be no observation point before the layer activation.", 0, getObservationPoints().size());
        assertTrue("Observation layer was not activated.", diagram.getActivatedLayers().size() == 1);

        // Activate Observation layer
        changeAndCheckObservationLayerActivation(true, 4);

        check_ExecA0_ExecA1_ObservationPoints();

        // Deactivate Observation layer
        changeAndCheckObservationLayerActivation(false, 4);

    }

    private void doCreate_ExecA0_ExecA1(int yScreenExecA0, double zoom) {
        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        // Creation of an execution
        ICondition done = new OperationDoneCondition();
        createExecution(lifelineAPosition, yScreenExecA0);
        bot.waitUntil(done);

        // Validates the position of the execution
        Rectangle execA0ScreenBounds = assertExecutionHasValidScreenBounds(LIFELINE_A, 0,
                new Rectangle(0, yScreenExecA0, LayoutConstants.DEFAULT_EXECUTION_WIDTH, (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);

        // Creation of an execution
        int yExecA1 = execA0ScreenBounds.getCenter().y;
        done = new OperationDoneCondition();
        createExecution(lifelineAPosition, yExecA1);
        bot.waitUntil(done);

        // Expected effect
        int executionA0HeightDelta = (int) (4 * LayoutConstants.EXECUTION_CHILDREN_MARGIN * zoom);
        execA0ScreenBounds.resize(0, executionA0HeightDelta);

        // Validates the position of the second execution
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, execA0ScreenBounds, false);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, new Rectangle(0, yExecA1, 0, (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
    }

    private void check_ExecA0_ExecA1_ObservationPoints() {
        // Check ObservationPoint positions
        List<ObservationPointEditPart> createdObsPoint = getSortedObservationParts();

        // First observation point -> first exec's top
        // Fourth observation point -> first exec's bottom
        checkCenterPosition(getExecutionEditPart(LIFELINE_A, 0), Arrays.asList(createdObsPoint.get(0), createdObsPoint.get(3)));

        // Second observation point -> second exec's top
        // Third observation point -> second exec's bottom
        checkCenterPosition(getExecutionEditPart(LIFELINE_A, 1), Arrays.asList(createdObsPoint.get(1), createdObsPoint.get(2)));
    }

    /**
     * Test the creation of events once when the observation layer is active. Each creation should trigger the creation
     * of observation points. They should be placed by the sequence layout.
     */
    public void test_Creation_With_Activated_Layer() {
        // Activate Observation layer
        changeAndCheckObservationLayerActivation(true, 0);
        assertEquals("There should be no observation point before the execution creation.", 0, getObservationPoints().size());

        // Create Executions
        doCreate_ExecA0_ExecA1(200, ZoomLevel.ZOOM_100.getAmount());

        check_ExecA0_ExecA1_ObservationPoints();

        // Deactivate Observation layer
        changeAndCheckObservationLayerActivation(false, 4);
    }

    /**
     * Test method.
     */
    public void test_Move_Forbidden() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create an execution
        ICondition done = new OperationDoneCondition();
        createExecution(getLifelineScreenX(LIFELINE_A), 200);
        bot.waitUntil(done);

        // try to move the first observation point.
        SWTBotGefEditPart observationPoint = getObservationPoints().get(0);
        Rectangle beforeDrag = getBounds((IGraphicalEditPart) observationPoint.part());
        ICondition selected = new CheckSelectedCondition(editor, observationPoint.part());
        editor.drag(observationPoint, new Point(getLifelineScreenX(LIFELINE_B), 250));
        bot.waitUntil(selected);

        Rectangle afterDrag = getBounds((IGraphicalEditPart) observationPoint.part());
        assertEquals("The observation point should not move", beforeDrag, afterDrag);
    }

    /**
     * Test method.
     */
    public void test_Constraint_Creation() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create an execution
        ICondition done = new OperationDoneCondition();
        createExecution(getLifelineScreenX(LIFELINE_A), 200);
        bot.waitUntil(done);
        // deselect the created execution
        editor.click(0, 0);

        // Maximize editor
        editor.maximize();

        // Create a constraint
        List<SWTBotGefEditPart> observationPoints = getObservationPoints();
        done = new CheckToolIsActivated(editor, "Constraint");
        editor.activateTool("Constraint");
        bot.waitUntil(done);
        done = new OperationDoneCondition();
        editor.click(editor.getBounds(observationPoints.get(0)).getCenter().getCopy());
        editor.click(editor.getBounds(observationPoints.get(1)).getCenter().getCopy());
        bot.waitUntil(done);

        List<SWTBotGefConnectionEditPart> constraints = editor.getConnectionEditPart(observationPoints.get(0), observationPoints.get(1));
        assertEquals("A constraint should exists", 1, constraints.size());
        BracketConnectionQuery query = new BracketConnectionQuery((Connection) constraints.get(0).part().getFigure());
        // init general direction
        query.getPointListFromConstraint();
        assertEquals("Dafault direction for sequence constraint should be LEFT", Direction.LEFT, query.getGeneralDirection());
    }

    /**
     * Test method.
     */
    public void test_Extra_Mapping_Constraint_Creation() {

        assertEquals("", DescriptionPackage.eINSTANCE.getDiagramElementMapping(), ToolPackage.eINSTANCE.getEdgeCreationDescription_ExtraSourceMappings().getEReferenceType());
        assertEquals("", DescriptionPackage.eINSTANCE.getDiagramElementMapping(), ToolPackage.eINSTANCE.getEdgeCreationDescription_ExtraTargetMappings().getEReferenceType());

        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create an Sync call message
        ICondition done = new OperationDoneCondition();
        Point start = new Point(getLifelineScreenX(LIFELINE_A), 200);
        Point end = new Point(getLifelineScreenX(LIFELINE_B), 200);
        createSyncCall(start, end);
        bot.waitUntil(done);
        // deselect the created execution
        editor.click(0, 0);

        // Create a constraint
        List<SWTBotGefEditPart> observationPoints = getObservationPoints();
        done = new CheckToolIsActivated(editor, "Constraint");
        editor.activateTool("Constraint");
        bot.waitUntil(done);
        done = new OperationDoneCondition();
        SequenceMessageEditPart messageEditPart = getSequenceMessageEditPart("m1");
        SequenceMessageEditPart returnMessageEditPart = getReturnSyncCall(LIFELINE_B, 0);
        editor.click(messageEditPart.getFigure().getBounds().getCenter().getCopy().x, 200);
        editor.click(returnMessageEditPart.getFigure().getBounds().getCenter().getCopy().x, 250);
        bot.waitUntil(done);

        List<SWTBotGefConnectionEditPart> constraints = editor.getConnectionEditPart(observationPoints.get(1), observationPoints.get(2));
        assertEquals("A constraint should exists", 1, constraints.size());
        BracketConnectionQuery query = new BracketConnectionQuery((Connection) constraints.get(0).part().getFigure());
        // init general direction
        query.getPointListFromConstraint();
        assertEquals("Dafault direction for sequence constraint should be LEFT", Direction.LEFT, query.getGeneralDirection());
    }

    /**
     * Test method.
     */
    public void test_Event_Move() {
        editor.maximize();
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create an execution
        ICondition done = new OperationDoneCondition();
        createExecution(getLifelineScreenX(LIFELINE_A), 200);
        bot.waitUntil(done);
        // deselect the created execution
        editor.click(0, 0);

        // Check ObservationPoint positions
        List<ObservationPointEditPart> createdObsPoint = getSortedObservationParts();
        assertEquals("Wrong observation points number", 2, createdObsPoint.size());
        checkCenterPosition(getExecutionEditPart(LIFELINE_A, 0), createdObsPoint);

        // Move the execution
        SWTBotGefEditPart e1 = editor.mainEditPart().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e1.select();
        done = new OperationDoneCondition();
        editor.drag(editor.getBounds(e1).getTopLeft().getCopy(), getLifelineScreenX(LIFELINE_A), 250);
        bot.waitUntil(done);

        // Check observation positions
        assertEquals("Wrong observation points number", 2, createdObsPoint.size());
        checkCenterPosition(getExecutionEditPart(LIFELINE_A, 0), createdObsPoint);
        // To be sure
        assertEquals("Check the observation point position", 250, getCenter(createdObsPoint.get(0)).y);

    }

    /**
     * Test method.
     */
    public void test_Observed_Message() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a simple message
        ICondition done = new OperationDoneCondition();
        createMessage("Read", getLifelineScreenX(LIFELINE_A), 200, getLifelineScreenX(LIFELINE_B), 200);
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition(getSequenceMessageEditPart(FIRST_MESSAGE), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Reflexive_Message() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a simple message
        ICondition done = new OperationDoneCondition();
        createMessage("Read", getLifelineScreenX(LIFELINE_A), 200, getLifelineScreenX(LIFELINE_A), 250);
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition(getSequenceMessageEditPart(FIRST_MESSAGE), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Message_Right_To_Left() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);
        editor.reveal(LIFELINE_A);

        // create a simple message
        ICondition done = new OperationDoneCondition();
        createMessage("Read", getLifelineScreenX(LIFELINE_B), 200, getLifelineScreenX(LIFELINE_A), 200);
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition(getSequenceMessageEditPart(FIRST_MESSAGE), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Creation_Message() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a creation message
        ICondition done = new OperationDoneCondition();
        createMessage("Create", getLifelineScreenX(LIFELINE_A), 200, getLifelineScreenX(LIFELINE_B), 200);
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition(getSequenceMessageEditPart(FIRST_CREATE_MESSAGE_ON_LIFELINE_A), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Destruction_Message() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a destruction message
        ICondition done = new OperationDoneCondition();
        createMessage("Destroy", getLifelineScreenX(LIFELINE_A), 200, getLifelineScreenX(LIFELINE_B), 200);
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition(getSequenceMessageEditPart(FIRST_DESTROY_MESSAGE), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_State() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a state
        ICondition done = new OperationDoneCondition();
        SWTBotGefEditPart state = createStateWithResult(getLifelineScreenX(LIFELINE_A), 200).get();
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition((ISequenceEventEditPart) state.part(), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Punctual_State() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a punctual state
        ICondition done = new OperationDoneCondition();
        SWTBotGefEditPart pState = createPunctualStateWithResult(getLifelineScreenX(LIFELINE_A), 200).get();
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition((ISequenceEventEditPart) pState.part(), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Interaction_Use() {
        editor.maximize();

        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create an InteractionUse
        ICondition done = new OperationDoneCondition();
        SWTBotGefEditPart iu = createInteractionUseWithResult(new Point(getLifelineScreenX(LIFELINE_A), 200));
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition((ISequenceEventEditPart) iu.part(), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Combined_Fragment() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a combined fragment
        ICondition done = new OperationDoneCondition();
        SWTBotGefEditPart cf = createCombinedFragmentWithResult(new Point(getLifelineScreenX(LIFELINE_A), 200));
        bot.waitUntil(done);
        done = new OperationDoneCondition();
        createCombinedFragmentOperand(editor.getBounds(cf).getCenter().getTranslated(5, 5));
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition((ISequenceEventEditPart) cf.part(), getSortedObservationParts());
    }

    /**
     * Test method.
     */
    public void test_Observed_Sync_Call() {
        // activate observation layer.
        changeAndCheckObservationLayerActivation(true, 0);

        // create a sync call
        ICondition done = new OperationDoneCondition();
        createSyncCall(new Point(getLifelineScreenX(LIFELINE_A), 200), new Point(getLifelineScreenX(LIFELINE_B), 200));
        bot.waitUntil(done);

        // Check ObservationPoint positions
        checkCenterPosition(getExecutionEditPart(LIFELINE_B, 0), getSortedObservationParts());
    }

    private void checkCenterPosition(ISequenceEventEditPart observedPart, List<ObservationPointEditPart> obeps) {
        Rectangle observedBounds = getBounds(observedPart);
        String observedName = ((DRepresentationElement) observedPart.getISequenceEvent().getNotationView().getElement()).getName();

        if (observedPart instanceof SequenceMessageEditPart) {
            assertEquals(2, obeps.size());
            Message msg = (Message) observedPart.getISequenceEvent();

            Point srcCenter;
            Point tgtCenter;

            Rectangle logicalBounds = msg.getProperLogicalBounds().getCopy();
            if (msg.isReflective()) {
                srcCenter = logicalBounds.getTopRight().getCopy();
                tgtCenter = logicalBounds.getBottomRight().getCopy();
            } else {
                srcCenter = logicalBounds.getLeft().getCopy();
                tgtCenter = logicalBounds.getRight().getCopy();
            }

            assertEquals(observedName + " have an observation point with a wrong center position.", srcCenter, getCenter(obeps.get(0)));
            assertEquals(observedName + " have an observation point with a wrong center position.", tgtCenter, getCenter(obeps.get(1)));
        } else if (observedPart instanceof StateEditPart && observedPart.getISequenceEvent().isLogicallyInstantaneous()) {
            // punctual state
            assertEquals(1, obeps.size());
            assertEquals(observedName + " have an observation point with a wrong center position.", observedBounds.getLeft(), getCenter(obeps.get(0)));
        } else if (observedPart instanceof CombinedFragmentEditPart) {
            CombinedFragment cf = (CombinedFragment) observedPart.getISequenceEvent();
            int nbOperands = cf.getOperands().size();
            assertEquals(2 + nbOperands, obeps.size());

            // Combined fragment top left is the first observed event end.
            assertEquals(observedName + " have an observation point with a wrong center position.", cf.getProperLogicalBounds().getTopLeft().getCopy(), getCenter(obeps.get(0)));
            for (int i = 0; i < cf.getOperands().size(); i++) {
                Operand operand = cf.getOperand(i).get();
                String opName = ((DRepresentationElement) operand.getNotationView().getElement()).getName();
                // Each operand top left should have its own observed event end.
                assertEquals(opName + " have an observation point with a wrong center position.", operand.getProperLogicalBounds().getTopLeft().getCopy(), getCenter(obeps.get(i + 1)));
            }
            // Combined fragment bottom left is the last observed event end.
            assertEquals(observedName + " have an observation point with a wrong center position.", cf.getProperLogicalBounds().getBottomLeft().getCopy(), getCenter(obeps.get(nbOperands + 1)));
        } else {
            // interactions uses, states and executions
            Point top = observedBounds.getTop().getCopy();
            Point bottom = observedBounds.getBottom().getCopy();

            if (obeps.size() == 4 && observedPart instanceof ExecutionEditPart) {
                // sync call case
                Execution exec = (Execution) observedPart.getISequenceEvent();
                Message startMessage = exec.getStartMessage().get();
                Message endMessage = exec.getEndMessage().get();
                Point send = startMessage.getProperLogicalBounds().getLeft();
                Point receive = endMessage.getProperLogicalBounds().getRight();

                assertEquals(observedName + " have an observation point with a wrong center position.", send, getCenter(obeps.get(0)));
                assertEquals(observedName + " have an observation point with a wrong center position.", top, getCenter(obeps.get(1)));
                assertEquals(observedName + " have an observation point with a wrong center position.", bottom, getCenter(obeps.get(2)));
                assertEquals(observedName + " have an observation point with a wrong center position.", receive, getCenter(obeps.get(3)));
            } else {
                assertEquals(2, obeps.size());
                if (observedPart instanceof InteractionUseEditPart) {
                    top = observedBounds.getTopLeft().getCopy();
                    bottom = observedBounds.getBottomLeft().getCopy();
                }

                assertEquals(observedName + " have an observation point with a wrong center position.", top, getCenter(obeps.get(0)));
                assertEquals(observedName + " have an observation point with a wrong center position.", bottom, getCenter(obeps.get(1)));
            }
        }
    }

    /**
     * Try to change the observation layer status.
     * 
     * @param activationExpected
     *            true if the method should activate the observation layer, false otherwise
     * @param expectedObservationPoints
     *            the number of expected observation points, if true, they should be visible, if false they should
     *            exists on the viewpoint side, but the should be no edit part.
     * 
     */
    private void changeAndCheckObservationLayerActivation(boolean activationExpected, int expectedObservationPoints) {
        DDiagram diagram = (DDiagram) ((DialectEditor) editor.getReference().getEditor(false)).getRepresentation();
        int prevActivatedLayers = diagram.getActivatedLayers().size();

        changeDurationLayerActivation();

        // Check activation/deactivation
        int postActivatedLayers = diagram.getActivatedLayers().size();
        int delta = postActivatedLayers - prevActivatedLayers;
        if (activationExpected) {
            assertTrue("Observation layer was not activated.", delta == 1);
            assertEquals("Check the created observation points.", expectedObservationPoints, getObservationPoints().size());
            assertEquals("Check the created observation points.", expectedObservationPoints,
                    Iterators.size(Iterators.filter(Iterators.filter(diagram.eAllContents(), DDiagramElement.class), isVisibleObsPoint)));

        } else {
            assertTrue("Observation layer was not deactivated.", delta == -1);
            assertEquals("The observation points should be visible.", 0, getObservationPoints().size());
            assertEquals("The observation points should be visible.", expectedObservationPoints,
                    Iterators.size(Iterators.filter(Iterators.filter(diagram.eAllContents(), DDiagramElement.class), isNonVisibleObsPoint)));
        }
    }

    private List<ObservationPointEditPart> getSortedObservationParts() {
        Function<SWTBotGefEditPart, EditPart> getPart = new Function<SWTBotGefEditPart, EditPart>() {
            @Override
            public EditPart apply(SWTBotGefEditPart input) {
                return input.part();
            }
        };

        List<ObservationPointEditPart> createdObsPoint = Lists.newArrayList(Iterables.filter(Iterables.transform(getObservationPoints(), getPart), ObservationPointEditPart.class));
        // Sort the observation point regarding their horizontal position.

        Collections.sort(createdObsPoint, new Comparator<ObservationPointEditPart>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public int compare(ObservationPointEditPart o1, ObservationPointEditPart o2) {
                int y1 = o1.getFigure().getBounds().y;
                int y2 = o2.getFigure().getBounds().y;

                if (y1 == y2) {
                    EventEnd ee1 = o1.getObservationPoint().getObservedEventEnd().get();
                    EventEnd ee2 = o2.getObservationPoint().getObservedEventEnd().get();

                    boolean start1 = false;
                    boolean cee1 = false;
                    boolean end2 = false;
                    boolean cee2 = false;
                    if (ee1 instanceof SingleEventEnd) {
                        start1 = ((SingleEventEnd) ee1).isStart();
                    } else {
                        cee1 = true;
                    }

                    if (ee2 instanceof SingleEventEnd) {
                        end2 = !((SingleEventEnd) ee2).isStart();
                    } else {
                        cee2 = true;
                    }

                    return start1 && end2 || start1 && cee2 || cee1 && end2 ? -1 : !end2 && !start1 || !end2 && cee1 || cee2 && !start1 ? 0 : -1;
                }
                return y1 - y2;
            }
        });
        return createdObsPoint;
    }

    private Point getCenter(ObservationPointEditPart part) {
        return getBounds(part).getCenter();
    }

    private Rectangle getBounds(IGraphicalEditPart part) {
        return part.getFigure().getBounds().getCopy();
    }
}
