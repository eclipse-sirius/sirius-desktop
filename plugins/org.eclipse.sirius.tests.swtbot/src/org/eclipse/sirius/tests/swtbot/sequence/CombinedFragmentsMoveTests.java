/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

import com.google.common.collect.Iterables;

/**
 * Test the validity of {@link CombinedFragment} and {@link Operand} moves.
 * 
 * @author <a href="mailto:steve.monnier@obeosoft.ca">Steve Monnier</a>
 */
public class CombinedFragmentsMoveTests extends AbstractSequenceDiagramTestCase {

    private static final String FILE_DIR = "/";

    private static final String DATA_UNIT_DIR = "/data/unit/sequence/";
    
    private static final String PATH = DATA_UNIT_DIR + "combinedFragments577045/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Interaction";

    private static final String REPRESENTATION_CF_MOVE1_NAME = "Sequence Diagram on CF_Move1";
    
    private static final String REPRESENTATION_CF_MOVE2_NAME = "Sequence Diagram on CF_Move2";
    
    private static final String REPRESENTATION_CF_MOVE3_NAME = "Sequence Diagram on CF_Move3"; 
    
    private static final String REPRESENTATION_CF_MOVE4_NAME = "Sequence Diagram on CF_Move4"; 

    private static final String MODEL = "577045.interactions";

    private static final String SESSION_FILE = "representations.aird";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return MODEL;
    }

    @Override
    protected String getTypesSemanticModel() {
        return null;
    }

    @Override
    protected String getSessionModel() {
        return SESSION_FILE;
    }

    /**
     * Not used here as each test opens a different representation.
     */
    @Override
    protected String getRepresentationId() {
        return null;
    }

    /**
     * Not used here as each test opens a different representation.
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return null;
    }


    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        if (getSemanticModel() != null) {
            copyFileToTestProject(Activator.PLUGIN_ID, getPath(), getSemanticModel());
        }
        if (getSessionModel() != null) {
            copyFileToTestProject(Activator.PLUGIN_ID, getPath(), getSessionModel());
        }
    }
    
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        changeDiagramPreference(SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name(), false);

        if (getSessionModel() != null) {
            sessionAirdResource = new UIResource(designerProject, FILE_DIR, getSessionModel());
            localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        }

    }
    
    /**
     * Check that it is not possible to move a combined fragment inside a combined fragment with less parent participants.
     */
    public void testCombinedFragmentMove1() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_CF_MOVE1_NAME, DDiagram.class, true, true);
        maximizeEditor(editor);
        
        SWTBotGefEditPart alt3CF = editor.getEditPart("alt.3").parent();
        Rectangle alt3CFBounds = editor.getBounds(alt3CF);
        SWTBotGefEditPart alt2CF = editor.getEditPart("alt.2").parent();
        Rectangle alt2CFBounds = editor.getBounds(alt2CF);

        // Select the combined Fragment alt.3 and try to move it in the middle of the small execution in alt.2
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(alt3CF);
        editor.drag(alt3CFBounds.getTop(), alt2CFBounds.getCenter());
        try {
            bot.waitUntil(checkMoved);
            fail("This drag and drop should not have been authorized.");
        } catch (TimeoutException te) {
            // Nothing to do, this is the expected case
        }
    }
    
    /**
     * Check that moving down a combined fragment without changing parent execution is possible.
     */
    public void testCombinedFragmentMove2() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_CF_MOVE2_NAME, DDiagram.class, true, true);
        maximizeEditor(editor);
        
        SWTBotGefEditPart alt2CF = editor.getEditPart("alt.2").parent();
        Rectangle alt2CFBounds = editor.getBounds(alt2CF);

        // Select the combined Fragment alt.2 and to move it further down on the same execution
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(alt2CF);
        editor.drag(alt2CFBounds.getTop(), alt2CFBounds.getTop().getTranslated(0, 20));
        bot.waitUntil(checkMoved);
        
        // Check that the combined fragment moved to the expected locations
        Rectangle alt2CFNewBounds = editor.getBounds(alt2CF);
        assertEquals("The combined fragment alt.2 is not at the expected Y locations", alt2CFBounds.getTop().getTranslated(0, 20).y, alt2CFNewBounds.getTop().y);
    }
    
    /**
     * Check that a combined fragment covering 3 lifelines can't be dragged into a combined fragment covering only one of these lifeline  
     */
    public void testCombinedFragmentMove3() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_CF_MOVE3_NAME, DDiagram.class, true, true);
        maximizeEditor(editor);

        SWTBotGefEditPart alt2 = editor.getEditPart("alt.2").parent();
        Rectangle alt2Bounds = editor.getBounds(alt2);
        SWTBotGefEditPart alt3 = editor.getEditPart("alt.3").parent();
        Rectangle alt3Bounds = editor.getBounds(alt3);
                
        // Extend it up by reducing the space of condition1
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(alt2);
        editor.drag(alt2Bounds.getTop(), alt3Bounds.getCenter());
        try {
            bot.waitUntil(checkMoved);
            fail("This drag and drop should not have been authorized.");
        } catch (TimeoutException te) {
            // Nothing to do, this is the expected case
        }
    }
    
    /**
     * Check that a combined fragment containing an execution can be moved together.  
     */
    public void testCombinedFragmentMove4() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_CF_MOVE4_NAME, DDiagram.class, true, true);
        maximizeEditor(editor);

        editor.reveal("alt.4");
        SWTBotGefEditPart alt4 = editor.getEditPart("alt.4").parent();
        Rectangle alt4Bounds = editor.getBounds(alt4);

        SWTBotGefEditPart sequenceDiagramBot = editor.mainEditPart();
        SequenceDiagramEditPart sequenceDiagramEditPart = (SequenceDiagramEditPart) sequenceDiagramBot.part();
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();
        Interaction interaction = (Interaction) sequenceDDiagram.getTarget();
        SWTBotGefEditPart instanceRole3Bot = editor.getEditPart("newParticipant3 : ");

        Execution e2 = interaction.getExecutions().get(1);
        Execution e3 = interaction.getExecutions().get(2);
        SWTBotGefEditPart e2Bot = instanceRole3Bot.parent().descendants(WithSemantic.withSemantic(e2)).get(0);
        SWTBotGefEditPart e3Bot = instanceRole3Bot.parent().descendants(WithSemantic.withSemantic(e3)).get(0);
        Rectangle e2Bounds = editor.getBounds(e2Bot);
        Rectangle e3Bounds = editor.getBounds(e3Bot);
        
        int yTranslation = -20;
        
        // Extend it up by reducing the space of condition1
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(alt4);
        editor.drag(alt4Bounds.getTopLeft(), alt4Bounds.getTopLeft().getTranslated(0, yTranslation));
        bot.waitUntil(checkMoved);
        
        // Check that the combined fragment moved to the expected locations and that the execution e3 does not have e2 as a parent anymore
        alt4 = editor.getEditPart("alt.4").parent();
        e2Bot = instanceRole3Bot.parent().descendants(WithSemantic.withSemantic(e2)).get(0);
        e3Bot = instanceRole3Bot.parent().descendants(WithSemantic.withSemantic(e3)).get(0);
        Rectangle alt4NewBounds = editor.getBounds(alt4);
        Rectangle e2NewBounds = editor.getBounds(e2Bot);
        Rectangle e3NewBounds = editor.getBounds(e3Bot);
        assertEquals("The combined fragment alt.2 is not at the expected Y location", alt4Bounds.getTop().getTranslated(0, yTranslation).y, alt4NewBounds.getTop().y);
        assertEquals("The excution e3 length should not have changed", e3Bounds.height, e3NewBounds.height);
        // I am not sure how we end up with this 500 round number as alt.4 Y coordinates is 457 
        assertEquals("The execution e3 is not at the expected Y locations", 500, e3NewBounds.getTop().y);
        assertEquals("The excution e2 is not at the expected Y location", e2Bounds.y + alt4Bounds.height, e2NewBounds.y);
        assertEquals("The excution e2 length should not have changed", e2Bounds.height, e2NewBounds.height);
        validateOrdering(18);
    }

}
