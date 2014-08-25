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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Checks that the "remove from diagram" action removes notes and texts. Also
 * checks that is impossible to use "remove from model" action for notes and
 * texts.
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
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test that the note should be removed from contextual menu 'Delete from
     * Diagram'.
     * Disabled until bug #435363 is fixed.
     */
    public void _testRemoveNoteFromContextualMenuAction() {
        // Select note
        editor.click(NOTE);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(NOTE);
        SWTBotGefEditPart diagram = noteEditPart.parent().parent();
        assertEquals("The note " + NOTE + " should be present", noteEditPart.parent(), diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).get(0));
        editor.clickContextMenu(DELETE_FROM_DIAGRAM);
        // Check that the note has been removed
        assertTrue("The note " + NOTE + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).isEmpty());
    }

    /**
     * Test that the text should be removed from contextual menu 'Delete from
     * Diagram'.
     * Disabled until bug #435363 is fixed.
     */
    public void _testRemoveTextFromContextualMenuAction() {
        // Select text
        editor.click(TEXT);
        SWTBotGefEditPart textEditPart = editor.getEditPart(TEXT);
        SWTBotGefEditPart diagram = textEditPart.parent().parent();
        assertEquals("The Text " + TEXT + " should be present", textEditPart.parent(), diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).get(0));
        editor.clickContextMenu(DELETE_FROM_DIAGRAM);
        // Check that the text has been removed
        assertTrue("The Text " + TEXT + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).isEmpty());
    }

    /**
     * Test that the note should be removed with 'Delete from Diagram' action of
     * tabbar.
     */
    public void testRemoveNoteFromTabbarAction() {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }

        // Select note
        editor.click(NOTE);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(NOTE);
        SWTBotGefEditPart diagram = noteEditPart.parent().parent();
        assertEquals("The note " + NOTE + " should be present", noteEditPart.parent(), diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).get(0));
        editor.bot().toolbarButtonWithTooltip(DELETE_FROM_DIAGRAM).click();
        // Check that the note has been removed
        assertTrue("The note " + NOTE + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(NoteEditPart.class)).isEmpty());
    }

    /**
     * Test that the text should be removed with 'Delete from Diagram' action of
     * tabbar.
     */
    public void testRemoveTextFromTabbarAction() {
        // Not available in fixed tabbar
        if (!TestsUtil.isDynamicTabbar()) {
            return;
        }

        // Select text
        editor.click(TEXT);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(TEXT);
        SWTBotGefEditPart diagram = noteEditPart.parent().parent();
        assertEquals("The Text " + TEXT + " should be present", noteEditPart.parent(), diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).get(0));
        editor.bot().toolbarButtonWithTooltip(DELETE_FROM_DIAGRAM).click();
        // Check that the text has been removed
        assertTrue("The Text " + TEXT + " should be removed", diagram.descendants(IsInstanceOf.instanceOf(TextEditPart.class)).isEmpty());
    }
}
