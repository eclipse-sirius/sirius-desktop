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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.collect.Lists;

/**
 * Tests Pin/Unpin, Show/Hide and Copy/Paste Layout actions are disabled on
 * Sequence diagram.
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
     * @return an execution, a state, a message, an interaction use, a combined
     *         fragment, a lost message, a found message.
     */
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
        pathsToTest.add(Lists.newArrayList("Participant b"));
        // Retrieve lifeline
        pathsToTest.add(Lists.newArrayList("Participant b", "Participant b"));
        // Retrieve end of life
        pathsToTest.add(Lists.newArrayList("Participant b", "Participant b", "Participant b"));
        // Retrieve execution
        pathsToTest.add(Lists.newArrayList("Participant b", "Participant b", "Execution e1"));
        // Retrieve state
        pathsToTest.add(Lists.newArrayList("Participant b", "Participant b", "State s1"));
        // Retrieve message
        pathsToTest.add(Lists.newArrayList("Call Message m1"));
        // Retrieve interactionUses
        pathsToTest.add(Lists.newArrayList("Interaction Use ref.1"));
        // Retrieve cominedFragments
        pathsToTest.add(Lists.newArrayList("Combined Fragment alt.1"));
        // Retrieve Lost messages
        pathsToTest.add(Lists.newArrayList("Create Participant Message m_create3"));
        // Retrieve found messages
        pathsToTest.add(Lists.newArrayList("Call Message m4"));

        return pathsToTest;
    }
}
