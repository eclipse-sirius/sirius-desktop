/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Verifies that Note (and Text) attachments, which are not handled
 * directly/only by Sirius but inherited from GMF, work correctly.
 */
@SuppressWarnings("restriction")
public class NoteAttachmentTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL_FILE = "MyEcore.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String DATA_UNIT_DIR = "data/unit/noteAttachments/Bugzilla-527391/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE);
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
    }

    /**
     * Checks that Note attachments can be selected.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=527391"
     */
    public void test_NoteAttachments_can_be_selected() {
        List<NoteAttachmentEditPart> notes = editor.getConnectionsEditPart().stream().map(SWTBotGefConnectionEditPart::part).filter(NoteAttachmentEditPart.class::isInstance)
                .map(NoteAttachmentEditPart.class::cast).collect(Collectors.toList());
        assertEquals("The test diagram should contain 2 note attachments", 2, notes.size());
        Optional<NoteAttachmentEditPart> attachmentToNote = notes.stream().filter(n -> n.getTarget() instanceof NoteEditPart).findFirst();
        assertTrue("Could not find the note attachment edit part", attachmentToNote.isPresent());
        Optional<NoteAttachmentEditPart> attachmentToText = notes.stream().filter(n -> n.getSource() instanceof TextEditPart).findFirst();
        assertTrue("Could not find the text attachment edit part", attachmentToText.isPresent());
        // Make sure the diagram's background is selected first.
        editor.click(new Point(10, 10));
        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                ISelection sel = editor.getSelection();
                return (sel instanceof StructuredSelection) && ((StructuredSelection) sel).getFirstElement() == editor.getDiagramEditPart();
            }
            
            @Override
            public String getFailureMessage() {
                return "The diagram was not selected";
            }
        });
        // Select the note attachment
        editor.click(new Point(417, 103));
        bot.waitUntil(new CheckSelectedCondition(editor, attachmentToNote.get()));
        // Select the text attachment
        editor.click(new Point(433, 211));
        bot.waitUntil(new CheckSelectedCondition(editor, attachmentToText.get()));

    }
}
