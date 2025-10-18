/*******************************************************************************
 * Copyright (c) 2025 CEA LIST.
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
/**
 * 
 */
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.GateEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Test class for creation message management.
 * 
 * @author smonnier
 */
public class GatesTests extends AbstractGatesSequenceTests {

    public void testGateOnInteractionUse_readmessage() {
        Point gateClickPosition = addGateOnInteractionUse();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnInteractionUseGate(messageCount);

        // Check that users can't move the gate-connected message outside of the parent range (no resize)
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        Point messageMoveTargetPosition = firstInteractionUseBounds.getBottom().getTranslated(0, 20);
        editor.drag(bounds.getCenter(), messageMoveTargetPosition);

        // Check message did not move
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == bounds.y;
            }

            @Override
            public String getFailureMessage() {
                return "The message should not have been moved";
            }
        });
    }

    public void testGateOnInteractionUse_createmessage() {
        Point gateClickPosition = addGateOnInteractionUse();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Create");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnInteractionUseGate(messageCount);
    }

    public void testGateOnInteractionUse_destroymessage() {
        Point gateClickPosition = addGateOnInteractionUse();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Destroy");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        // Check message creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return getSequenceDiagram().getAllMessages().size() == messageCount + 1;
            }

            @Override
            public String getFailureMessage() {
                return "A message should have been created";
            }
        });
        assertEquals("The diagram should have 1 message", 1, editor.getConnectionsEditPart().size());
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);

        // Move the message just before the end of the interaction use
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        Point messageMoveTargetPosition = firstInteractionUseBounds.getBottom().getTranslated(0, -5);
        editor.select(swtBotGefConnectionEditPart);
        editor.drag(bounds.getCenter(), messageMoveTargetPosition);

        // Check message move
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                // FIXME Somehow, with a destruction message the message is moved 5px further that expected
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == messageMoveTargetPosition.y + 5;
            }

            @Override
            public String getFailureMessage() {
                return "The message has not been moved at the expected location";
            }
        });
    }

    public void testGateOnInteractionUse_obliquemessage() {
        Point gateClickPosition = addGateOnInteractionUse();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Write_Oblique");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnInteractionUseGate(messageCount);
    }

    private void validateMoveMessageOnInteractionUseGate(int messageCount) {
        // Check message creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return getSequenceDiagram().getAllMessages().size() == messageCount + 1;
            }

            @Override
            public String getFailureMessage() {
                return "A message should have been created";
            }
        });
        assertEquals("The diagram should have 1 message", 1, editor.getConnectionsEditPart().size());
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);

        // Move the message just before the end of the interaction use
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        Point messageMoveTargetPosition = firstInteractionUseBounds.getBottom().getTranslated(0, -5);
        editor.drag(bounds.getCenter(), messageMoveTargetPosition);

        // Check message move
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == messageMoveTargetPosition.y;
            }

            @Override
            public String getFailureMessage() {
                return "The message has not been moved at the expected location";
            }
        });
    }

    private Point addGateOnInteractionUse() {
        return addGateOnInteractionUse(firstInteractionUseBounds.getRight().getTranslated(-10, 0));
    }

    private Point addGateOnInteractionUse(Point targetLocation) {
        // Add a Gate on the InteractionUse
        editor.activateTool("Gate");
        int expectedGateCount = firstInteractionUse.getOwnedGates().size() + 1;
        editor.click(targetLocation);

        // Check Gate creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return firstInteractionUse.getOwnedGates().size() == expectedGateCount;
            }

            @Override
            public String getFailureMessage() {
                return "The combined fragment should have "+ expectedGateCount +" gate(s)";
            }
        });
        assertEquals("The interaction use should have "+ expectedGateCount +" gate(s)", expectedGateCount, firstInteractionUse.getOwnedGates().size());

        // Check Gate location
        SWTBotGefEditPart optionalgateEditPart = firstInteractionUseBot.children().stream().filter(bot -> bot.part() instanceof GateEditPart).map(SWTBotGefEditPart.class::cast)
                .collect(Collectors.toList()).get(expectedGateCount-1);
        assertTrue("GateEditPart expected", optionalgateEditPart.part() instanceof GateEditPart);
        Rectangle gateBounds = editor.getAbsoluteBounds(optionalgateEditPart);
        assertTrue("The gate has not been created where the tool was applied", gateBounds.contains(targetLocation));
        return targetLocation;
    }

    public void testGateOnCombinedFragment_readmessage() {
        Point gateClickPosition = addGateOnCombinedFragment();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnCombinedFragmentGate(messageCount);

        // Check that users can't move the gate-connected message outside of the parent range (no resize)
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        Point messageMoveTargetPosition = firstInteractionUseBounds.getBottom().getTranslated(0, 20);
        editor.drag(bounds.getCenter(), messageMoveTargetPosition);

        // Check message did not move
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == bounds.y;
            }

            @Override
            public String getFailureMessage() {
                return "The message should not have been moved";
            }
        });
    }

    public void testGateOnCombinedFragment_createmessage() {
        Point gateClickPosition = addGateOnCombinedFragment();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Create");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnCombinedFragmentGate(messageCount);
    }

    public void testGateOnCombinedFragment_destroymessage() {
        Point gateClickPosition = addGateOnCombinedFragment();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Destroy");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnCombinedFragmentGate(messageCount);
    }

    public void testGateOnCombinedFragment_obliquemessage() {
        Point gateClickPosition = addGateOnCombinedFragment();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Write_Oblique");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnCombinedFragmentGate(messageCount);
    }

    private void validateMoveMessageOnCombinedFragmentGate(int messageCount) {
        // Check message creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return getSequenceDiagram().getAllMessages().size() == messageCount + 1;
            }

            @Override
            public String getFailureMessage() {
                return "A message should have been created";
            }
        });
        assertEquals("The diagram should have 1 message", 1, editor.getConnectionsEditPart().size());
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);

        // Move the message just before the end of the combined fragment
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        Point messageMoveTargetPosition = firstCombinedFragmentBounds.getBottom().getTranslated(0, -5);
        editor.drag(bounds.getCenter(), messageMoveTargetPosition);

        // Check message move
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == messageMoveTargetPosition.y;
            }

            @Override
            public String getFailureMessage() {
                return "The message has not been moved at the expected location";
            }
        });
    }

    private Point addGateOnCombinedFragment() {
        return addGateOnCombinedFragment(firstCombinedFragmentBounds.getRight().getTranslated(-5, 0));
    }


    private Point addGateOnCombinedFragment(Point targetLocation) {
        // Add a Gate on the CombinedFragment
        editor.activateTool("Gate");
        int expectedGateCount = firstCombinedFragment.getOwnedGates().size() + 1;
        editor.click(targetLocation);

        // Check Gate creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return firstCombinedFragment.getOwnedGates().size() == expectedGateCount;
            }

            @Override
            public String getFailureMessage() {
                return "The combined fragment should have "+ expectedGateCount +" gate(s)";
            }
        });
        assertEquals("The combined fragment should have "+ expectedGateCount +" gate(s)", expectedGateCount, firstCombinedFragment.getOwnedGates().size());

        // Check Gate location
        SWTBotGefEditPart optionalgateEditPart = firstCombinedFragmentBot.children().stream().filter(bot -> bot.part() instanceof GateEditPart).map(SWTBotGefEditPart.class::cast)
                .collect(Collectors.toList()).get(expectedGateCount-1);
        assertTrue("GateEditPart expected", optionalgateEditPart.part() instanceof GateEditPart);
        Rectangle gateBounds = editor.getAbsoluteBounds(optionalgateEditPart);
        assertTrue("The gate has not been created where the tool was applied", gateBounds.contains(targetLocation));
        return targetLocation;
    }

    public void testGateOnInteractionContainer_readmessage() {
        Point gateClickPosition = addGateOnInteractionContainer();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnInteractionContainerGate(messageCount);

        // Check that users can't move the gate-connected message outside of the parent range (no resize)
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        Point messageMoveTargetPosition = firstInteractionContainerBounds.getBottom().getTranslated(0, 20);
        editor.drag(bounds.getCenter(), messageMoveTargetPosition);

        // Check message did not move
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == bounds.y;
            }

            @Override
            public String getFailureMessage() {
                return "The message should not have been moved";
            }
        });
    }

    public void testGateOnInteractionContainer_createmessage() {
        Point gateClickPosition = addGateOnInteractionContainer();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Create");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnInteractionContainerGate(messageCount);
    }

    public void testGateOnInteractionContainer_destroymessage() {
        Point gateClickPosition = addGateOnInteractionContainer();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Destroy");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y);

        validateMoveMessageOnInteractionContainerGate(messageCount);
    }

    public void testGateOnInteractionContainer_obliquemessage() {
        Point gateClickPosition = addGateOnInteractionContainer();

        // Add message from gate to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Write_Oblique");
        editor.click(gateClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gateClickPosition.y + 50);

        validateMoveMessageOnInteractionContainerGate(messageCount);
    }

    private void validateMoveMessageOnInteractionContainerGate(int messageCount) {
        // Check message creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return getSequenceDiagram().getAllMessages().size() == messageCount + 1;
            }

            @Override
            public String getFailureMessage() {
                return "A message should have been created";
            }
        });
        assertEquals("The diagram should have 1 message", 1, editor.getConnectionsEditPart().size());
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);

        // Move the message 50px higher
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        Point messageMoveTargetPosition = firstInteractionContainerBounds.getRight().getTranslated(0, 100);
        editor.drag(bounds.getCenter(), messageMoveTargetPosition);

        // Check message move
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == messageMoveTargetPosition.y;
            }

            @Override
            public String getFailureMessage() {
                return "The message has not been moved at the expected location";
            }
        });
    }

    /**
     * Validates that it is forbidden to move a message connected to a gate to the location of a sibling gate.
     * 
     * @param messageCount number of expected message
     * @param moveToTargetLocation target location where the message should not be able to move at
     */
    private void validateMoveMessageOnSiblingGateForbidden(int messageCount, Point moveToTargetLocation) {
        // Check message creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return getSequenceDiagram().getAllMessages().size() == messageCount;
            }

            @Override
            public String getFailureMessage() {
                return "A message should have been created";
            }
        });
        assertEquals("The diagram should have 1 message", 1, editor.getConnectionsEditPart().size());
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);

        // Move the message to target location
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        editor.drag(bounds.getCenter(), moveToTargetLocation);
        // The move should be forbidden so we call SWTBotUtils.waitAllUiEvents() to wait for the drag to be processed and cancelled 
        SWTBotUtils.waitAllUiEvents();

        // Check message move
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == bounds.getCenter().y;
            }

            @Override
            public String getFailureMessage() {
                return "The message should not have been moved";
            }
        });
    }

    /**
     * Validates that it is forbidden to move a message connected to a gate to the location of a sibling gate.
     * 
     * @param messageCount number of expected message
     * @param moveToTargetLocation target location where the message should not be able to move at
     */
    private void validateMoveMessageAtSameHeightAsOppositeSiblingGateAllowed(int messageCount, Point moveToTargetLocation) {
        // Check message creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return getSequenceDiagram().getAllMessages().size() == messageCount;
            }

            @Override
            public String getFailureMessage() {
                return "A message should have been created";
            }
        });
        assertEquals("The diagram should have 1 message", 1, editor.getConnectionsEditPart().size());
        SWTBotGefConnectionEditPart swtBotGefConnectionEditPart = editor.getConnectionsEditPart().get(0);

        // Move the message to target location
        Rectangle bounds = editor.getBounds(swtBotGefConnectionEditPart);
        editor.drag(bounds.getCenter(), new Point(bounds.getCenter().x, moveToTargetLocation.y));

        // Check message move
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return editor.getBounds(swtBotGefConnectionEditPart).getCenter().y == moveToTargetLocation.y;
            }

            @Override
            public String getFailureMessage() {
                return "The message has not been moved at the expected location";
            }
        });
    }

    private Point addGateOnInteractionContainer() {
        return addGateOnInteractionContainer(firstInteractionContainerBounds.getRight().getTranslated(-5, 0));
    }

    private Point addGateOnInteractionContainer(Point targetLocation) {
        // Add a Gate on the InteractionContainer
        editor.activateTool("Gate");
        int expectedGateCount = firstInteractionContainer.getOwnedGates().size() + 1;
        editor.click(targetLocation);

        // Check Gate creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return firstInteractionContainer.getOwnedGates().size() == 1;
            }

            @Override
            public String getFailureMessage() {
                return "The interaction container should have "+ expectedGateCount +" gate(s)";
            }
        });
        assertEquals("The interaction container should have "+ expectedGateCount +" gate(s)", 1, firstInteractionContainer.getOwnedGates().size());

        // Check Gate location
        SWTBotGefEditPart optionalgateEditPart = firstInteractionContainerBot.children().stream().filter(bot -> bot.part() instanceof GateEditPart).map(SWTBotGefEditPart.class::cast)
                .collect(Collectors.toList()).get(expectedGateCount-1);
        assertTrue("GateEditPart expected", optionalgateEditPart.part() instanceof GateEditPart);
        Rectangle gateBounds = editor.getAbsoluteBounds(optionalgateEditPart);
        assertTrue("The gate has not been created where the tool was applied", gateBounds.contains(targetLocation));
        return targetLocation;
    }

    public void testMoveMessageWithGateOnSiblingGate_CombinedFragment_Source() {
        // Create 2 gates on the same combined fragment
        Point gate1ClickPosition = addGateOnCombinedFragment();
        Point gate2ClickPosition = addGateOnCombinedFragment(firstCombinedFragmentBounds.getBottomRight().getTranslated(-5, -25));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gate1ClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageOnSiblingGateForbidden(messageCount+1, gate2ClickPosition);
    }

    public void testMoveMessageWithGateOnSiblingGate_CombinedFragment_Source_OppositeSide() {
        // Create 2 gates on the same combined fragment
        Point gate1ClickPosition = addGateOnCombinedFragment();
        Point gate2ClickPosition = addGateOnCombinedFragment(firstCombinedFragmentBounds.getBottomLeft().getTranslated(5, -25));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gate1ClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageAtSameHeightAsOppositeSiblingGateAllowed(messageCount+1, gate2ClickPosition);
    }
    
    public void testMoveMessageWithGateOnSiblingGate_CombinedFragment_Target() {
        // Create 2 gates on the same combined fragment
        Point gate1ClickPosition = addGateOnCombinedFragment();
        Point gate2ClickPosition = addGateOnCombinedFragment(firstCombinedFragmentBounds.getBottomRight().getTranslated(-5, -25));
        
        // Add message from lifeline D to gate1
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);
        editor.click(gate1ClickPosition);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageOnSiblingGateForbidden(messageCount+1, gate2ClickPosition);
    }
    
    public void testMoveMessageWithGateOnSiblingGate_CombinedFragment_Target_OppositeSide() {
        // Create 2 gates on the same combined fragment
        Point gate1ClickPosition = addGateOnCombinedFragment();
        Point gate2ClickPosition = addGateOnCombinedFragment(firstCombinedFragmentBounds.getBottomLeft().getTranslated(5, -25));
        
        // Add message from lifeline D to gate1
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);
        editor.click(gate1ClickPosition);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageAtSameHeightAsOppositeSiblingGateAllowed(messageCount+1, gate2ClickPosition);
    }

    public void testMoveMessageWithGateOnSiblingGate_InteractionUse_Source() {
        // Resize the InteractionUse
        editor.select(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getBottom(), firstInteractionUseBounds.getBottom().getTranslated(0, 100));
        SWTBotUtils.waitAllUiEvents();
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);
        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionUse();
        Point gate2ClickPosition = addGateOnInteractionUse(firstInteractionUseBounds.getBottomRight().getTranslated(-5, -25));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gate1ClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageOnSiblingGateForbidden(messageCount+1, gate2ClickPosition);
    }

    public void testMoveMessageWithGateOnSiblingGate_InteractionUse_Source_OppositeSide() {
        // Resize the InteractionUse
        editor.select(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getBottom(), firstInteractionUseBounds.getBottom().getTranslated(0, 100));
        SWTBotUtils.waitAllUiEvents();
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);
        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionUse();
        Point gate2ClickPosition = addGateOnInteractionUse(firstInteractionUseBounds.getBottomLeft().getTranslated(5, -25));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gate1ClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageAtSameHeightAsOppositeSiblingGateAllowed(messageCount+1, gate2ClickPosition);
    }

    public void testMoveMessageWithGateOnSiblingGate_InteractionUse_Target() {
        // Resize the InteractionUse
        editor.select(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getBottom(), firstInteractionUseBounds.getBottom().getTranslated(0, 100));
        SWTBotUtils.waitAllUiEvents();
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);
        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionUse();
        Point gate2ClickPosition = addGateOnInteractionUse(firstInteractionUseBounds.getBottomRight().getTranslated(-5, -25));
        
        // Add message from lifeline D to gate1
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);
        editor.click(gate1ClickPosition);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageOnSiblingGateForbidden(messageCount+1, gate2ClickPosition);
    }

    public void testMoveMessageWithGateOnSiblingGate_InteractionUse_Target_OppositeSide() {
        // Resize the InteractionUse
        editor.select(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getBottom(), firstInteractionUseBounds.getBottom().getTranslated(0, 100));
        SWTBotUtils.waitAllUiEvents();
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);
        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionUse();
        Point gate2ClickPosition = addGateOnInteractionUse(firstInteractionUseBounds.getBottomLeft().getTranslated(5, -25));
        
        // Add message from lifeline D to gate1
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);
        editor.click(gate1ClickPosition);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageAtSameHeightAsOppositeSiblingGateAllowed(messageCount+1, gate2ClickPosition);
    }
    
    public void testMoveMessageWithGateOnSiblingGate_InteractionContainer_Source() {
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionContainer();
        Point gate2ClickPosition = addGateOnInteractionContainer(firstInteractionContainerBounds.getRight().getTranslated(-5, 50));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gate1ClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageOnSiblingGateForbidden(messageCount+1, gate2ClickPosition);
    }
    
    public void testMoveMessageWithGateOnSiblingGate_InteractionContainer_Source_OppositeSide() {
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionContainer();
        Point gate2ClickPosition = addGateOnInteractionContainer(firstInteractionContainerBounds.getLeft().getTranslated(5, 50));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(gate1ClickPosition);
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageAtSameHeightAsOppositeSiblingGateAllowed(messageCount+1, gate2ClickPosition);
    }
    
    public void testMoveMessageWithGateOnSiblingGate_InteractionContainer_Target() {
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionContainer();
        Point gate2ClickPosition = addGateOnInteractionContainer(firstInteractionContainerBounds.getRight().getTranslated(-5, 50));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);
        editor.click(gate1ClickPosition);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageOnSiblingGateForbidden(messageCount+1, gate2ClickPosition);
    }
    
    public void testMoveMessageWithGateOnSiblingGate_InteractionContainer_Target_OppositeSide() {
        // Create 2 gates on the same Interaction Use
        Point gate1ClickPosition = addGateOnInteractionContainer();
        Point gate2ClickPosition = addGateOnInteractionContainer(firstInteractionContainerBounds.getLeft().getTranslated(5, 50));
        
        // Add message from gate1 to lifeline D
        int messageCount = getSequenceDiagram().getAllMessages().size();
        editor.activateTool("Read");
        editor.click(instanceRoleEditPartDBounds.getCenter().x, gate1ClickPosition.y);
        editor.click(gate1ClickPosition);

        // Check that the message can't be moved to the location of the other gate
        validateMoveMessageAtSameHeightAsOppositeSiblingGateAllowed(messageCount+1, gate2ClickPosition);
    }
}
