/*****************************************************************************
 * Copyright (c) 2025 CEA LIST.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Obeo - Initial API and implementation
 *****************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.GateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Gate;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Operand;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsNotDisplayed;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

/**
 * Abstract tests class for oblique messages in Sequence Diagram.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class AbstractObliqueMessagesSequenceTests extends AbstractSequenceDiagramTestCase {

    /**
     * Timeout for "waitUntil" is fixed to 1000 ms instead of 5000 ms.
     */
    private static final long TIMEOUT_ERROR = 1000;

    private static final String PATH = DATA_UNIT_DIR + "obliques/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Obliques tests";

    private static final String MODEL = "obliques.interactions";

    private static final String SESSION_FILE = "obliques.aird";

    private static final String TYPES_FILE = "types.ecore";

    /** Main part */
    protected SWTBotGefEditPart sequenceDiagramBot;

    /** Main interaction */
    protected Interaction interaction;

    /** Participants */
    protected ParticipantData participantAData;

    protected ParticipantData participantBData;

    protected ParticipantData participantCData;

    /** CombinedFragments */
    protected CombinedFragmentData combinedFragmentData;

    protected OperandData operandData;

    /** InteractionUses */
    protected InteractionUseData interactionUseData;

    /** Gates */
    protected GateData gate1Data;

    protected GateData gate2Data;

    protected GateData gate3Data;

    /** Executions */
    protected ExecutionData execution1Data;

    protected ExecutionData execution2Data;

    protected ExecutionData execution3Data;

    /** records */
    protected record ParticipantData(SWTBotGefEditPart bot, LifelineEditPart editPart, Participant semanticElement, Rectangle bounds) {
    }

    protected record CombinedFragmentData(SWTBotGefEditPart bot, CombinedFragmentEditPart editPart, CombinedFragment semanticElement, Rectangle bounds) {
    }

    protected record InteractionUseData(SWTBotGefEditPart bot, InteractionUseEditPart editPart, InteractionUse semanticElement, Rectangle bounds) {
    }

    protected record OperandData(SWTBotGefEditPart bot, OperandEditPart editPart, Operand semanticElement, Rectangle bounds) {
    }

    protected record GateData(SWTBotGefEditPart bot, GateEditPart editPart, Gate semanticElement, Rectangle bounds) {
    }

    protected record ExecutionData(SWTBotGefEditPart bot, ExecutionEditPart editPart, Execution semanticElement, Rectangle bounds) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.reveal(LIFELINE_A);

        maximizeEditor(editor);

        // main components
        sequenceDiagramBot = editor.mainEditPart();
        SequenceDiagramEditPart sdep = (SequenceDiagramEditPart) sequenceDiagramBot.part();
        SequenceDDiagram seqDiagram = (SequenceDDiagram) sdep.resolveSemanticElement();
        interaction = (Interaction) seqDiagram.getTarget();

        // InstanceRoles
        participantAData = initializeParticipantData("a");
        participantBData = initializeParticipantData("b");
        participantCData = initializeParticipantData("c");

        // CombinedFragments
        combinedFragmentData = initializeCombinedFragmentData(0);

        operandData = initializeOperandData(combinedFragmentData.bot, 0);

        // InteractionUses
        interactionUseData = initializeInteractionUseData(0);

        // Gates
        gate1Data = initializeGateData(combinedFragmentData.bot, "g1");
        gate2Data = initializeGateData(combinedFragmentData.bot, "g2");
        gate3Data = initializeGateData(interactionUseData.bot, "g3");

        // Executions
        execution1Data = initializeExecutionData("e1");
        execution2Data = initializeExecutionData("e2");
        execution3Data = initializeExecutionData("e3");
    }

    private ParticipantData initializeParticipantData(String participantName) {
        List<SWTBotGefEditPart> descendants = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(LifelineEditPart.class));
        SWTBotGefEditPart instanceRoleEditPartBot = findByName(descendants, participantName);
        LifelineEditPart instanceRoleEditPart = (LifelineEditPart) instanceRoleEditPartBot.part();
        Participant participant = (Participant) instanceRoleEditPart.resolveTargetSemanticElement();
        Rectangle instanceRoleEditPartBounds = editor.getBounds(instanceRoleEditPartBot);

        return new ParticipantData(instanceRoleEditPartBot, instanceRoleEditPart, participant, instanceRoleEditPartBounds);
    }

    private SWTBotGefEditPart findByName(List<SWTBotGefEditPart> descendants, String name) {
        for (SWTBotGefEditPart child : descendants) {
            GraphicalEditPart part = (GraphicalEditPart) child.part();
            DSemanticDecorator semanticDecorator = (DSemanticDecorator) part.resolveSemanticElement();
            EObject target = semanticDecorator.getTarget();
            EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
            if (target.eGet(feature).equals(name)) {
                return child;
            }
        }
        return null;
    }

    private CombinedFragmentData initializeCombinedFragmentData(int combinedFragmentIndex) {
        SWTBotGefEditPart combinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(combinedFragmentIndex);
        CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart) combinedFragmentBot.part();
        CombinedFragment combinedFragment = (CombinedFragment) combinedFragmentEditPart.resolveTargetSemanticElement();
        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragmentBot);

        return new CombinedFragmentData(combinedFragmentBot, combinedFragmentEditPart, combinedFragment, combinedFragmentBounds);
    }

    private OperandData initializeOperandData(SWTBotGefEditPart parentBot, int operandIndex) {
        SWTBotGefEditPart operandBot = parentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(operandIndex);
        OperandEditPart operandEditPart = (OperandEditPart) operandBot.part();
        Operand operand = (Operand) operandEditPart.resolveTargetSemanticElement();
        Rectangle operandBounds = editor.getBounds(operandBot);

        return new OperandData(operandBot, operandEditPart, operand, operandBounds);
    }

    private InteractionUseData initializeInteractionUseData(int interactionUseIndex) {
        SWTBotGefEditPart interactionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(interactionUseIndex);
        InteractionUseEditPart interactionUseEditPart = (InteractionUseEditPart) interactionUseBot.part();
        InteractionUse interactionUse = (InteractionUse) interactionUseEditPart.resolveTargetSemanticElement();
        Rectangle interactionUseBounds = editor.getBounds(interactionUseBot);

        return new InteractionUseData(interactionUseBot, interactionUseEditPart, interactionUse, interactionUseBounds);
    }

    private GateData initializeGateData(SWTBotGefEditPart parentBot, String gateName) {
        List<SWTBotGefEditPart> descendants = parentBot.descendants(IsInstanceOf.instanceOf(GateEditPart.class));
        SWTBotGefEditPart gateBot = findByName(descendants, gateName);
        GateEditPart gateEditPart = (GateEditPart) gateBot.part();
        Gate gate = (Gate) gateEditPart.resolveTargetSemanticElement();
        Rectangle gateBounds = editor.getBounds(gateBot);

        return new GateData(gateBot, gateEditPart, gate, gateBounds);
    }

    private ExecutionData initializeExecutionData(String executionName) {
        List<SWTBotGefEditPart> descendants = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        SWTBotGefEditPart executionBot = findByName(descendants, executionName);
        ExecutionEditPart executionEditPart = (ExecutionEditPart) executionBot.part();
        Execution execution = (Execution) executionEditPart.resolveTargetSemanticElement();
        Rectangle executionBounds = editor.getBounds(executionBot);

        return new ExecutionData(executionBot, executionEditPart, execution, executionBounds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        // Main part
        sequenceDiagramBot = null;

        interaction = null;

        // InstanceRoles
        participantAData = null;
        participantBData = null;
        participantCData = null;

        // CombinedFragments
        combinedFragmentData = null;

        operandData = null;

        // InteractionUses
        interactionUseData = null;

        // Gates
        gate1Data = null;
        gate2Data = null;
        gate3Data = null;

        // Executions
        execution1Data = null;
        execution2Data = null;
        execution3Data = null;

        super.tearDown();
    }

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

    protected void moveMessage() {
        moveMessage(15, false);
    }

    protected void moveMessage(int translateY, boolean shouldFail) {
        assertEquals("The diagram should have 1 message", 1, editor.getConnectionsEditPart().size());

        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);
        SequenceMessageEditPart messageEditPart = (SequenceMessageEditPart) swtBotGefConnectionEditPart.part();
        Point centerHandleMessagePosition = getHandleNearCenter(messageEditPart);

        Point dropPosition = centerHandleMessagePosition.getTranslated(0, translateY);
        editor.drag(centerHandleMessagePosition, dropPosition);
        // Check message move
        try {
            bot.waitUntil(new DefaultCondition() {
                @Override
                public boolean test() throws Exception {
                    return areClose(getHandleNearCenter(messageEditPart), dropPosition);
                }

                @Override
                public String getFailureMessage() {
                    return "The message has not been moved at the expected location";
                }
            }, TIMEOUT_ERROR);
            if (shouldFail) {
                fail("Expected the move to fail, but it succeeded");
            }
        } catch (TimeoutException e) {
            if (!shouldFail) {
                throw e;
            }
        }
    }

    protected void moveSourceHandleMessage() {
        moveSourceHandleMessage(-15, false);
    }

    protected void moveSourceHandleMessage(int translateY, boolean shouldFail) {
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);
        SequenceMessageEditPart messageEditPart = (SequenceMessageEditPart) swtBotGefConnectionEditPart.part();
        Point sourceHandleMessagePosition = getHandleNearSource(messageEditPart);

        Point dropPosition = sourceHandleMessagePosition.getTranslated(0, translateY);
        editor.drag(sourceHandleMessagePosition, dropPosition);
        // Check message move
        try {
            bot.waitUntil(new DefaultCondition() {
                @Override
                public boolean test() throws Exception {
                    return areClose(getHandleNearSource(messageEditPart), dropPosition);
                }

                @Override
                public String getFailureMessage() {
                    return "The message has not been moved at the expected location";
                }
            }, TIMEOUT_ERROR);
            if (shouldFail) {
                fail("Expected the move to fail, but it succeeded");
            }
        } catch (TimeoutException e) {
            if (!shouldFail) {
                throw e;
            }
        }
    }

    protected void moveTargetHandleMessage() {
        moveTargetHandleMessage(-15, false);
    }

    protected void moveTargetHandleMessage(int translateY, boolean shouldFail) {
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);
        SequenceMessageEditPart messageEditPart = (SequenceMessageEditPart) swtBotGefConnectionEditPart.part();
        Point targetHandleMessagePosition = getHandleNearTarget(messageEditPart);

        Point dropPosition = targetHandleMessagePosition.getTranslated(0, translateY);
        editor.drag(targetHandleMessagePosition, dropPosition);
        // Check message move
        try {
            bot.waitUntil(new DefaultCondition() {
                @Override
                public boolean test() throws Exception {
                    return areClose(getHandleNearTarget(messageEditPart), dropPosition);
                }

                @Override
                public String getFailureMessage() {
                    return "The message has not been moved at the expected location";
                }
            }, TIMEOUT_ERROR);
            if (shouldFail) {
                fail("Expected the move to fail, but it succeeded");
            }
        } catch (TimeoutException e) {
            if (!shouldFail) {
                throw e;
            }
        }
    }

    protected boolean areClose(Point p1, Point p2) {
        double epsilon = 2.0;
        return Math.abs(p1.preciseX() - p2.preciseX()) <= epsilon && Math.abs(p1.preciseY() - p2.preciseY()) <= epsilon;
    }

    private Point interpolateAlongEdge(Point source, Point target, double gap) {
        double x = source.x + gap * (target.x - source.x);
        double y = source.y + gap * (target.y - source.y);
        return new PrecisionPoint(x, y);
    }

    protected Point getHandleNearCenter(SequenceMessageEditPart messageEditPart) {
        PointList points = messageEditPart.getConnectionFigure().getPoints();
        Point source = points.getFirstPoint().getCopy();
        Point target = points.getLastPoint().getCopy();
        return interpolateAlongEdge(source, target, 0.60);
    }

    protected Point getHandleNearSource(SequenceMessageEditPart messageEditPart) {
        PointList points = messageEditPart.getConnectionFigure().getPoints();
        Point source = points.getFirstPoint().getCopy();
        Point target = points.getLastPoint().getCopy();
        return interpolateAlongEdge(source, target, 0.10);
    }

    protected Point getHandleNearTarget(SequenceMessageEditPart messageEditPart) {
        PointList points = messageEditPart.getConnectionFigure().getPoints();
        Point source = points.getFirstPoint().getCopy();
        Point target = points.getLastPoint().getCopy();
        return interpolateAlongEdge(source, target, 0.90);
    }

    protected void checkMessageHasBeenCreated() {
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return getSequenceDiagram().getAllMessages().size() == 1;
            }

            @Override
            public String getFailureMessage() {
                return "A message should have been created";
            }
        });
    }

    protected void checkMessageHasNotBeenCreated(String label) {
        ICondition checkEditPartIsNotDisplayed = new CheckEditPartIsNotDisplayed(label, editor);
        bot.waitUntil(checkEditPartIsNotDisplayed);
    }

}
