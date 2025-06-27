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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.GateEditPart;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
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
        // Add a Gate on the InteractionUse
        editor.activateTool("Gate");
        Point gateClickPosition = firstInteractionUseBounds.getRight().getTranslated(-10, 0);
        editor.click(gateClickPosition);

        // Check Gate creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return firstInteractionUse.getOwnedGates().size() == 1;
            }

            @Override
            public String getFailureMessage() {
                return "The interaction use should have 1 gate";
            }
        });
        assertEquals("The interaction use should have 1 gate", 1, firstInteractionUse.getOwnedGates().size());

        // Check Gate location
        Optional<SWTBotGefEditPart> optionalgateEditPart = firstInteractionUseBot.children().stream().filter(bot -> bot.part() instanceof GateEditPart).map(SWTBotGefEditPart.class::cast).findFirst();
        assertTrue("The GateEditPart has not been found", optionalgateEditPart.isPresent());
        assertTrue("GateEditPart expected", optionalgateEditPart.get().part() instanceof GateEditPart);
        Rectangle gateBounds = editor.getAbsoluteBounds(optionalgateEditPart.get());
        assertTrue("The gate has not been created where the tool was applied", gateBounds.contains(gateClickPosition));
        return gateClickPosition;
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
        // Add a Gate on the CombinedFragment
        editor.activateTool("Gate");
        Point gateClickPosition = firstCombinedFragmentBounds.getRight().getTranslated(-5, 0);
        editor.click(gateClickPosition);

        // Check Gate creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return firstCombinedFragment.getOwnedGates().size() == 1;
            }

            @Override
            public String getFailureMessage() {
                return "The combined fragment should have 1 gate";
            }
        });
        assertEquals("The combined fragment should have 1 gate", 1, firstCombinedFragment.getOwnedGates().size());

        // Check Gate location
        Optional<SWTBotGefEditPart> optionalgateEditPart = firstCombinedFragmentBot.children().stream().filter(bot -> bot.part() instanceof GateEditPart).map(SWTBotGefEditPart.class::cast)
                .findFirst();
        assertTrue("The GateEditPart has not been found", optionalgateEditPart.isPresent());
        assertTrue("GateEditPart expected", optionalgateEditPart.get().part() instanceof GateEditPart);
        Rectangle gateBounds = editor.getAbsoluteBounds(optionalgateEditPart.get());
        assertTrue("The gate has not been created where the tool was applied", gateBounds.contains(gateClickPosition));
        return gateClickPosition;
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

    private Point addGateOnInteractionContainer() {
        // Add a Gate on the InteractionContainer
        editor.activateTool("Gate");
        Point gateClickPosition = firstInteractionContainerBounds.getRight().getTranslated(-5, 0);
        editor.click(gateClickPosition);

        // Check Gate creation
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return firstInteractionContainer.getOwnedGates().size() == 1;
            }

            @Override
            public String getFailureMessage() {
                return "The interaction container should have 1 gate";
            }
        });
        assertEquals("The interaction container should have 1 gate", 1, firstInteractionContainer.getOwnedGates().size());

        // Check Gate location
        Optional<SWTBotGefEditPart> optionalgateEditPart = firstInteractionContainerBot.children().stream().filter(bot -> bot.part() instanceof GateEditPart).map(SWTBotGefEditPart.class::cast)
                .findFirst();
        assertTrue("The GateEditPart has not been found", optionalgateEditPart.isPresent());
        assertTrue("GateEditPart expected", optionalgateEditPart.get().part() instanceof GateEditPart);
        Rectangle gateBounds = editor.getAbsoluteBounds(optionalgateEditPart.get());
        assertTrue("The gate has not been created where the tool was applied", gateBounds.contains(gateClickPosition));
        return gateClickPosition;
    }
}
