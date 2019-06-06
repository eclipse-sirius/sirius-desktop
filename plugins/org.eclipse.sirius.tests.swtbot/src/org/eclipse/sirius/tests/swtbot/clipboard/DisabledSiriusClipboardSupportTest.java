/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.clipboard;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.NoteEditPartMatcher;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests that when the Sirius clipboard support is disabled via
 * {@link SiriusDiagramPreferencesKeys#PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE}, we
 * can only copy GMF notes on diagram to paste them on any diagram
 * representation.
 * 
 * See VP-2401.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DisabledSiriusClipboardSupportTest extends AbstractClipboardSupportTest {

    private UIResource sessionAirdResourceBis;

    private SWTBotGefEditPart pastableClassBot;

    private SWTBotGefEditPart class2Bot;

    private SWTBotGefEditPart nodeleteClassBot;

    private SWTBotGefConnectionEditPart inheritanceConnectionBot;

    private SWTBotGefEditPart aloneNoteBot;

    private SWTBotGefEditPart noteLinkedToAnotherNoteBot;

    private SWTBotGefEditPart noteLinkedToSemanticElementBot;

    private SWTBotGefEditPart diagramEditPartBotOfEditor;

    private SWTBotGefEditPart diagramEditPartBotOfEditor2;

    private Rectangle aloneNoteBounds;

    private Rectangle noteLinkedToAnotherNoteBounds;

    private Rectangle noteLinkedToSemanticElementBounds;

    private List<SWTBotGefEditPart> noteEditPartBots;

    private int nbOfNote;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, MODEL_BIS, SESSION_FILE, SESSION_FILE_BIS, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        localSession.selectView();
        localSession.getRootSessionTreeItem().select();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE, DDiagram.class);

        sessionAirdResourceBis = new UIResource(designerProject, FILE_DIR, SESSION_FILE_BIS);
        localSessionBis = designerPerspective.openSessionFromFile(sessionAirdResourceBis);
        localSession.selectView();
        localSession.getRootSessionTreeItem().select();

        editor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), CUSTOM_DESCRIPTION, REPRESENTATION_WITH_CUSTOM_PASTE_BIS, DDiagram.class);
        editor.show();

        diagramEditPartBotOfEditor = editor.rootEditPart().children().get(0);
        diagramEditPartBotOfEditor2 = editor2.rootEditPart().children().get(0);

        // DDiagramElement EditPart bots
        pastableClassBot = editor.getEditPart("pastable_Class").parent();
        class2Bot = editor.getEditPart("Class2").parent();
        nodeleteClassBot = editor.getEditPart("nodelete_Class").parent();
        inheritanceConnectionBot = class2Bot.targetConnections().get(0);

        // Note EditPart bots
        aloneNoteBot = editor.getEditPart("NoteAlone").parent();
        noteLinkedToAnotherNoteBot = editor.getEditPart("NoteLinkedToAnotherNote").parent();
        noteLinkedToSemanticElementBot = editor.getEditPart("NoteLinkedToSemanticElement").parent();

        aloneNoteBounds = editor.getBounds(aloneNoteBot);
        noteLinkedToAnotherNoteBounds = editor.getBounds(noteLinkedToAnotherNoteBot);
        noteLinkedToSemanticElementBounds = editor.getBounds(noteLinkedToSemanticElementBot);

        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        nbOfNote = noteEditPartBots.size();
    }

    /**
     * Test that when
     * {@link SiriusDiagramPreferencesKeys#PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE}
     * preference set to true, the clipboard support is not available on
     * DDiagramElement's views but yet on Notes.
     */
    public void testDDiagramElementSelection() {

        pastableClassBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, pastableClassBot.part()));
        bot.waitUntil(new CheckSelectedCondition(editor, pastableClassBot.part()));
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(false, false, false);

        class2Bot.select();
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(false, false, false);

        nodeleteClassBot.select();
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(false, false, false);

        editor.select(pastableClassBot, class2Bot, nodeleteClassBot);
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(false, false, false);

        inheritanceConnectionBot.select();
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(false, false, false);
    }

    /**
     * Test that when
     * {@link SiriusDiagramPreferencesKeys#PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE}
     * preference set to true, the clipboard support is available on on Notes.
     * 
     */
    public void testNoteSelection() {
        aloneNoteBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, aloneNoteBot.part()));
        bot.waitUntil(new CheckSelectedCondition(editor, aloneNoteBot.part()));
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(true, true, false);

        noteLinkedToAnotherNoteBot.select();
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(true, true, false);

        noteLinkedToSemanticElementBot.select();
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(true, true, false);

        editor.select(aloneNoteBot, noteLinkedToAnotherNoteBot, noteLinkedToSemanticElementBot);
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(true, true, false);

        editor.select(aloneNoteBot, noteLinkedToAnotherNoteBot, noteLinkedToSemanticElementBot);
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(true, true, false);

        editor.select(aloneNoteBot, pastableClassBot);
        assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(false, false, false);

    }

    /**
     * Test that copy of note on a same diagram.
     */
    public void testNoteCopyPasteInASameDiagram() {

        // 1. Do a copy/paste
        aloneNoteBot.select();
        copySelection(editor);

        diagramEditPartBotOfEditor.select();
        pasteInSelection(editor);

        // 2. Assert that a new Note has been created
        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("It should be have a new Note created", nbOfNote + 1, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteCopyBot = noteEditPartBots.get(nbOfNote);
        Rectangle aloneNoteCopyBounds = editor.getBounds(aloneNoteCopyBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteCopyBounds);

        // 3. Test a second paste
        pasteInSelection(editor);

        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("It should be have a new Note created", nbOfNote + 2, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteSecondCopyBot = noteEditPartBots.get(nbOfNote + 1);
        Rectangle aloneNoteSecondCopyBounds = editor.getBounds(aloneNoteSecondCopyBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteSecondCopyBounds);

    }

    /**
     * Test that cut of note on a same diagram.
     */
    public void testNoteCutPasteInASameDiagram() {
        // 1. Do a cut/paste
        noteLinkedToSemanticElementBot.select();
        cutSelection(editor);

        diagramEditPartBotOfEditor.select();
        pasteInSelection(editor);

        // 2. Assert that the number of note is unchanged
        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("With a cut the number of Note should be unchanged", nbOfNote, noteEditPartBots.size());

        SWTBotGefEditPart noteLinkedToSemanticElementCopyBot = noteEditPartBots.get(nbOfNote - 1);
        Rectangle noteLinkedToSemanticElementCopyBounds = editor.getBounds(noteLinkedToSemanticElementCopyBot);

        assertEquals("The note copy should be located near to the original location", noteLinkedToSemanticElementBounds.getTranslated(10, 10), noteLinkedToSemanticElementCopyBounds);

    }

    /**
     * Test that copy of note on a same diagram with a multiple selection.
     */
    public void testNoteCopyPasteInASameDiagramWithAMultipleSelection() {
        // 1. Test copy/paste of a multiple selection
        editor.select(noteLinkedToSemanticElementBot, aloneNoteBot, noteLinkedToAnotherNoteBot);
        copySelection(editor);

        diagramEditPartBotOfEditor.select();
        pasteInSelection(editor);

        // 2. Assert
        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("It should be have three new Notes created", nbOfNote + 3, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteCopyOfMultipleSelectionBot = noteEditPartBots.get(nbOfNote);
        SWTBotGefEditPart noteLinkedToAnotherNoteCopyOfMultipleSelectionBot = noteEditPartBots.get(nbOfNote + 1);
        SWTBotGefEditPart noteLinkedToSemanticElementCopyOfMultipleSelectionBot = noteEditPartBots.get(nbOfNote + 2);

        Rectangle aloneNoteCopyOfMultipleSelectionBounds = editor.getBounds(aloneNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds = editor.getBounds(noteLinkedToAnotherNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToSemanticElementCopyOfMultipleSelectionBounds = editor.getBounds(noteLinkedToSemanticElementCopyOfMultipleSelectionBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToAnotherNoteBounds.getTranslated(10, 10), noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToSemanticElementBounds.getTranslated(10, 10), noteLinkedToSemanticElementCopyOfMultipleSelectionBounds);
    }

    /**
     * Test that cut of note on a same diagram with a multiple selection.
     */
    public void testNoteCutPasteInASameDiagramWithAMultipleSelection() {
        // 1. Test cut/paste of a multiple selection
        editor.select(aloneNoteBot, noteLinkedToAnotherNoteBot, noteLinkedToSemanticElementBot);
        cutSelection(editor);

        diagramEditPartBotOfEditor.select();
        pasteInSelection(editor);

        // 2. Check
        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("With a cut the number of Note should be unchanged", nbOfNote, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteCopyOfMultipleSelectionBot = editor.getEditPart("NoteAlone").parent();
        SWTBotGefEditPart noteLinkedToAnotherNoteCopyOfMultipleSelectionBot = editor.getEditPart("NoteLinkedToAnotherNote").parent();
        SWTBotGefEditPart noteLinkedToSemanticElementCopyOfMultipleSelectionBot = editor.getEditPart("NoteLinkedToSemanticElement").parent();

        Rectangle aloneNoteCopyOfMultipleSelectionBounds = editor.getBounds(aloneNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds = editor.getBounds(noteLinkedToAnotherNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToSemanticElementCopyOfMultipleSelectionBounds = editor.getBounds(noteLinkedToSemanticElementCopyOfMultipleSelectionBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToAnotherNoteBounds.getTranslated(10, 10), noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToSemanticElementBounds.getTranslated(10, 10), noteLinkedToSemanticElementCopyOfMultipleSelectionBounds);
    }

    /**
     * Test that copy of note on different diagram of different session.
     */
    public void testNoteCopyPasteInDifferentDiagramOfDifferentSession() {

        // 1. Do a copy/paste
        aloneNoteBot.select();
        copySelection(editor);

        editor2.show();
        diagramEditPartBotOfEditor2.select();
        pasteInSelection(editor);

        // 2. Assert that a new Note has been created
        noteEditPartBots = diagramEditPartBotOfEditor2.descendants(new NoteEditPartMatcher());
        assertEquals("It should be have a new Note created", 1, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteCopyBot = noteEditPartBots.get(0);
        Rectangle aloneNoteCopyBounds = editor2.getBounds(aloneNoteCopyBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteCopyBounds);

        // 3. Test a second paste
        pasteInSelection(editor);

        noteEditPartBots = diagramEditPartBotOfEditor2.descendants(new NoteEditPartMatcher());
        assertEquals("It should be have a new Note created", 2, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteSecondCopyBot = noteEditPartBots.get(1);
        Rectangle aloneNoteSecondCopyBounds = editor2.getBounds(aloneNoteSecondCopyBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteSecondCopyBounds);

    }

    /**
     * Test that cut of note on different diagram of different session.
     */
    public void testNoteCutPasteInDifferentDiagramOfDifferentSession() {
        // 1. Do a cut/paste
        noteLinkedToSemanticElementBot.select();
        cutSelection(editor);

        editor2.show();
        diagramEditPartBotOfEditor2.select();
        pasteInSelection(editor2);

        // 2. Assert that the number of note is unchanged
        noteEditPartBots = diagramEditPartBotOfEditor2.descendants(new NoteEditPartMatcher());
        assertEquals("With a cut the number of Note should be unchanged", 1, noteEditPartBots.size());

        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("With a cut the number of Note should be unchanged", nbOfNote - 1, noteEditPartBots.size());

        SWTBotGefEditPart noteLinkedToSemanticElementCopyBot = editor2.getEditPart("NoteLinkedToSemanticElement");
        Rectangle noteLinkedToSemanticElementCopyBounds = editor2.getBounds(noteLinkedToSemanticElementCopyBot);

        // TODO : find why bounds change between diagram of different session on
        // cut
        assertEquals("The note copy should be located near to the original location", noteLinkedToSemanticElementBounds.getTranslated(16, 16).resize(-21, -12), noteLinkedToSemanticElementCopyBounds);

    }

    /**
     * Test that copy of note on different diagram of different session with a
     * multiple selection.
     */
    public void testNoteCopyPasteInDifferentDiagramOfDifferentSessionWithAMultipleSelection() {
        // 1. Test copy/paste of a multiple selection
        editor.select(noteLinkedToSemanticElementBot, aloneNoteBot, noteLinkedToAnotherNoteBot);
        copySelection(editor);

        editor2.show();
        diagramEditPartBotOfEditor2.select();
        pasteInSelection(editor2);

        // 2. Assert
        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("The number of Note on the diagram of the first session should be unchanged", nbOfNote, noteEditPartBots.size());

        noteEditPartBots = diagramEditPartBotOfEditor2.descendants(new NoteEditPartMatcher());
        assertEquals("They should be 3 new Notes on the diagram of the second session", 3, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteCopyOfMultipleSelectionBot = editor2.getEditPart("NoteAlone").parent();
        SWTBotGefEditPart noteLinkedToAnotherNoteCopyOfMultipleSelectionBot = editor2.getEditPart("NoteLinkedToAnotherNote").parent();
        SWTBotGefEditPart noteLinkedToSemanticElementCopyOfMultipleSelectionBot = editor2.getEditPart("NoteLinkedToSemanticElement").parent();

        Rectangle aloneNoteCopyOfMultipleSelectionBounds = editor.getBounds(aloneNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds = editor.getBounds(noteLinkedToAnotherNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToSemanticElementCopyOfMultipleSelectionBounds = editor.getBounds(noteLinkedToSemanticElementCopyOfMultipleSelectionBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToAnotherNoteBounds.getTranslated(10, 10), noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToSemanticElementBounds.getTranslated(10, 10), noteLinkedToSemanticElementCopyOfMultipleSelectionBounds);
    }

    /**
     * Test that cut of note on different diagram of different session with a
     * multiple selection.
     */
    public void testNoteCutPasteInDifferentDiagramOfDifferentSessionWithAMultipleSelection() {
        // 1. Test cut/paste of a multiple selection
        editor.select(aloneNoteBot, noteLinkedToAnotherNoteBot, noteLinkedToSemanticElementBot);
        cutSelection(editor);

        editor2.show();
        diagramEditPartBotOfEditor2.select();
        pasteInSelection(editor2);

        noteEditPartBots = diagramEditPartBotOfEditor2.descendants(new NoteEditPartMatcher());
        assertEquals("3 new Note should be pasted on the diagram of the second session", 3, noteEditPartBots.size());

        noteEditPartBots = diagramEditPartBotOfEditor.descendants(new NoteEditPartMatcher());
        assertEquals("The diagram of the first session shouldn't contains Note now", 0, noteEditPartBots.size());

        SWTBotGefEditPart aloneNoteCopyOfMultipleSelectionBot = editor2.getEditPart("NoteAlone").parent();
        SWTBotGefEditPart noteLinkedToAnotherNoteCopyOfMultipleSelectionBot = editor2.getEditPart("NoteLinkedToAnotherNote").parent();
        SWTBotGefEditPart noteLinkedToSemanticElementCopyOfMultipleSelectionBot = editor2.getEditPart("NoteLinkedToSemanticElement").parent();

        Rectangle aloneNoteCopyOfMultipleSelectionBounds = editor2.getBounds(aloneNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds = editor2.getBounds(noteLinkedToAnotherNoteCopyOfMultipleSelectionBot);
        Rectangle noteLinkedToSemanticElementCopyOfMultipleSelectionBounds = editor2.getBounds(noteLinkedToSemanticElementCopyOfMultipleSelectionBot);

        assertEquals("The note copy should be located near to the original location", aloneNoteBounds.getTranslated(10, 10), aloneNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToAnotherNoteBounds.getTranslated(10, 10), noteLinkedToAnotherNoteCopyOfMultipleSelectionBounds);
        assertEquals("The note copy should be located near to the original location", noteLinkedToSemanticElementBounds.getTranslated(10, 10), noteLinkedToSemanticElementCopyOfMultipleSelectionBounds);
    }
}
