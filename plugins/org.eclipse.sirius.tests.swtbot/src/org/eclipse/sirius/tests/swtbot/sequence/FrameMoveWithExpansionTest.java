/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class for syncCall
 * 
 * @author smonnier, edugueperoux
 */
public class FrameMoveWithExpansionTest extends AbstractDefaultModelSequenceTests {

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private SWTBotGefEditPart sequenceDiagramBot;

    private SWTBotGefEditPart firstCombinedFragmentBot;

    private SWTBotGefEditPart firstInteractionUseBot;

    private SWTBotGefEditPart e1Bot;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return DATA_UNIT_DIR + "frames/";
    }

    @Override
    protected String getSemanticModel() {
        return "frames_move_with_expansion.interactions";
    }

    @Override
    protected String getTypesSemanticModel() {
        return "types.ecore";
    }

    @Override
    protected String getSessionModel() {
        return "frames_move_with_expansion.aird";
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
        return Options.newSome("Sequence Diagram on Sample");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);
        arrangeAll();

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);

        sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();
        firstCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);
        e1Bot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
    }

    /**
     * 
     */
    public void test_move_combined_fragment_with_lifeline_expansion() {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;

        assertEquals(new Range(130, 250), ((CombinedFragmentEditPart) firstCombinedFragmentBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(180, 230), ((InteractionUseEditPart) firstInteractionUseBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(270, 320), ((ExecutionEditPart) e1Bot.part()).getISequenceEvent().getVerticalRange());

        // Select the Combined Fragment and move it down so as to require an
        // expansion of the lifeline
        editor.click(lifelineBPosition + 10, 130 + 5);
        CheckEditPartMoved cm = new CheckEditPartMoved(firstCombinedFragmentBot);
        editor.drag(lifelineBPosition + 10, 130 + 5, lifelineBPosition, 470 + 5);
        bot.waitUntil(cm);

        assertEquals(new Range(470, 470 + 120), ((CombinedFragmentEditPart) firstCombinedFragmentBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(180, 230), ((InteractionUseEditPart) firstInteractionUseBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(270, 320), ((ExecutionEditPart) e1Bot.part()).getISequenceEvent().getVerticalRange());

        validateOrdering(9);
    }

    /**
     * 
     */
    public void test_move_interaction_use_with_lifeline_expansion() {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;

        assertEquals(new Range(130, 250), ((CombinedFragmentEditPart) firstCombinedFragmentBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(180, 230), ((InteractionUseEditPart) firstInteractionUseBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(270, 320), ((ExecutionEditPart) e1Bot.part()).getISequenceEvent().getVerticalRange());

        // Select the InteractionUse and move it down so as to require an
        // expansion of the lifeline
        editor.click(lifelineAPosition + 10, 180 + 10);
        CheckEditPartMoved cm = new CheckEditPartMoved(firstInteractionUseBot);
        editor.drag(lifelineAPosition + 10, 180 + 10, lifelineAPosition, 490);
        bot.waitUntil(cm);
        assertEquals(new Range(130, 250), ((CombinedFragmentEditPart) firstCombinedFragmentBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(480, 480 + 50), ((InteractionUseEditPart) firstInteractionUseBot.part()).getISequenceEvent().getVerticalRange());
        assertEquals(new Range(270, 320), ((ExecutionEditPart) e1Bot.part()).getISequenceEvent().getVerticalRange());
        validateOrdering(9);
    }
}
