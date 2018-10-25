/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

/**
 * Tests Pin/Unpin, Show/Hide and Copy/Paste Layout actions are disabled on Sequence diagram.
 * 
 * Test VP-3087 and VP-3088.
 * 
 * 
 * @author jdupont
 */
public class ActionDisabledOnSequenceDiagramTest extends AbstractActionDisabledOnSequenceDiagramTest {

    private static final String PATH = DATA_UNIT_DIR + "actionsDisabled/";

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart sequenceDiagramBot;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B).parent();
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A).parent();
        sequenceDiagramBot = instanceRoleEditPartABot.parent();
    }

    /**
     * {@inheritDoc}
     * 
     * @return an execution, a state, a message, an interaction use, a combined fragment, a lost message, a found
     *         message.
     */
    @Override
    protected Collection<SWTBotGefEditPart> getEditPartsToCheckDisabledActionsOn() {
        Collection<SWTBotGefEditPart> partsToTest = new ArrayList<>();
        // Retrieve instance role (do not retrieve the lifeline : it is not
        // selectable).
        partsToTest.add(instanceRoleEditPartBBot);
        // Retrieve end of life
        partsToTest.add(instanceRoleEditPartBBot.descendants(IsInstanceOf.instanceOf(EndOfLifeEditPart.class)).get(0));
        // Retrieve execution
        partsToTest.add(instanceRoleEditPartBBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0));
        // Retrieve state
        partsToTest.add(instanceRoleEditPartBBot.descendants(IsInstanceOf.instanceOf(StateEditPart.class)).get(0));
        // Retrieve message
        partsToTest.add(editor.getEditPart("m1").parent());
        // Retrieve interactionUses
        partsToTest.add(sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0));
        // Retrieve cominedFragments
        partsToTest.add(sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0));
        // Retrieve Lost messages
        partsToTest.add(editor.getEditPart("m_create3").parent());
        // Retrieve found messages
        partsToTest.add(editor.getEditPart("m6").parent());
        return partsToTest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<List<String>> getElementPathsToCheckNoEffectInWizard() {
        Collection<List<String>> pathsToTest = new ArrayList<>();
        // Retrieve instance role
        pathsToTest.add(Arrays.asList("Participant b"));
        // Retrieve lifeline
        pathsToTest.add(Arrays.asList("Participant b", "Participant b"));
        // Retrieve end of life
        pathsToTest.add(Arrays.asList("Participant b", "Participant b", "Participant b"));
        // Retrieve execution
        pathsToTest.add(Arrays.asList("Participant b", "Participant b", "Execution e1"));
        // Retrieve state
        pathsToTest.add(Arrays.asList("Participant b", "Participant b", "State s1"));
        // Retrieve message
        pathsToTest.add(Arrays.asList("Call Message m1"));
        // Retrieve interactionUses
        pathsToTest.add(Arrays.asList("Interaction Use ref.1"));
        // Retrieve cominedFragments
        pathsToTest.add(Arrays.asList("Combined Fragment alt.1"));
        // Retrieve Lost messages
        pathsToTest.add(Arrays.asList("Create Participant Message m_create3"));
        // Retrieve found messages
        pathsToTest.add(Arrays.asList("Call Message m4"));

        return pathsToTest;
    }

    /**
     * Tests that the edit mode dropdown menu is not visible for sequence diagrams
     */
    public void testEditModeDropDownMenuNotVisible() {
        try {
            editor.bot().toolbarDropDownButtonWithTooltip("Change Diagram edition mode");
            fail("Menu should not be visible.");
        } catch (WidgetNotFoundException e) {
        }
    }
}
