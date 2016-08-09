/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Checks that the "remove from diagram" action removes notes and texts (and
 * associated NoteAttachment). Also checks that is impossible to use
 * "remove from model" action for notes and texts.
 * 
 * @author jdupont
 */
public class RemoveNoteTextTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/tools/remove/notesText/";

    private static final String FILE_DIR = "/";

    private static final String NOTE = "Note1";

    private static final String TEXT = "Text1";

    private static final String DELETE_FROM_DIAGRAM = "Delete from Diagram";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test that the note should be removed from contextual menu 'Delete from
     * Diagram'.
     */
    public void testRemoveNoteFromContextualMenuAction() {
        // Select note
        editor.click(NOTE);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(NOTE, NoteEditPart.class);
        SWTBotGefEditPart diagram = noteEditPart.parent();
        assertEquals("The note " + NOTE + " should be present", noteEditPart, diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).get(0));
        editor.clickContextMenu(DELETE_FROM_DIAGRAM);
        // Check that the note has been removed
        assertTrue("The note " + NOTE + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).isEmpty());
    }

    /**
     * Test that the note attachment should be removed from contextual menu
     * 'Delete from Diagram'.
     */
    public void testRemoveNoteAttachmentFromContextualMenuAction() {
        // Select note
        editor.click(NOTE);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(NOTE, NoteEditPart.class);
        SWTBotGefConnectionEditPart noteAttachment = noteEditPart.sourceConnections().get(0);
        noteAttachment.select();
        assertEquals("The note attachment should be present", noteAttachment, noteEditPart.sourceConnections().iterator().next());
        assertTrue("The note attachment should be present", noteAttachment.part() instanceof NoteAttachmentEditPart);
        editor.clickContextMenu(DELETE_FROM_DIAGRAM);
        // Check that the note attachment has been removed
        assertTrue("The note attachment should be removed", noteEditPart.sourceConnections().isEmpty());
    }

    /**
     * Test that the text should be removed from contextual menu 'Delete from
     * Diagram'.
     */
    public void testRemoveTextFromContextualMenuAction() {
        // Select text
        editor.click(TEXT);
        SWTBotGefEditPart textEditPart = editor.getEditPart(TEXT, TextEditPart.class);
        SWTBotGefEditPart diagram = textEditPart.parent();
        assertEquals("The Text " + TEXT + " should be present", textEditPart, diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).get(0));
        editor.clickContextMenu(DELETE_FROM_DIAGRAM);
        // Check that the text has been removed
        assertTrue("The Text " + TEXT + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).isEmpty());
    }

    /**
     * Test that the note should be removed with 'Delete from Diagram' action of
     * tabbar.
     */
    public void testRemoveNoteFromTabbarAction() {
        // Select note
        editor.click(NOTE);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(NOTE, NoteEditPart.class);
        SWTBotGefEditPart diagram = noteEditPart.parent();
        SWTBotGefConnectionEditPart noteAttachmentPart = noteEditPart.sourceConnections().get(0);
        View noteAttachment = (View) noteAttachmentPart.part().getModel();
        assertEquals("The note " + NOTE + " should be present", noteEditPart, diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).get(0));
        editor.bot().toolbarButtonWithTooltip(DELETE_FROM_DIAGRAM).click();
        // Check that the note has been removed
        assertTrue("The note " + NOTE + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).isEmpty());
        // Check that the corresponding note attachment has also been removed
        assertNull("The corresponding note attachment should be removed", noteAttachment.eContainer());
    }

    /**
     * Test that the note attachment should be removed with 'Delete from
     * Diagram' action of tabbar.
     */
    public void testRemoveNoteAttachmentFromTabbarAction() {
        // Select note
        editor.click(NOTE);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(NOTE, NoteEditPart.class);
        SWTBotGefConnectionEditPart noteAttachment = noteEditPart.sourceConnections().get(0);
        noteAttachment.select();
        assertEquals("The note attachment should be present", noteAttachment, noteEditPart.sourceConnections().iterator().next());
        assertTrue("The note attachment should be present", noteAttachment.part() instanceof NoteAttachmentEditPart);
        editor.bot().toolbarButtonWithTooltip(DELETE_FROM_DIAGRAM).click();
        // Check that the note attachment has been removed
        assertTrue("The note attachment should be removed", noteEditPart.sourceConnections().isEmpty());
    }

    /**
     * Test that the text should be removed with 'Delete from Diagram' action of
     * tabbar.
     */
    public void testRemoveTextFromTabbarAction() {
        // Select text
        editor.click(TEXT);
        SWTBotGefEditPart textEditPart = editor.getEditPart(TEXT, TextEditPart.class);
        SWTBotGefConnectionEditPart noteAttachmentPart = textEditPart.targetConnections().get(0);
        View noteAttachment = (View) noteAttachmentPart.part().getModel();
        SWTBotGefEditPart diagram = textEditPart.parent();
        assertEquals("The Text " + TEXT + " should be present", textEditPart, diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).get(0));
        editor.bot().toolbarButtonWithTooltip(DELETE_FROM_DIAGRAM).click();
        // Check that the text has been removed
        assertTrue("The Text " + TEXT + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).isEmpty());
        // Check that the corresponding note attachment has also been removed
        assertNull("The corresponding note attachment should be removed", noteAttachment.eContainer());
    }
}
