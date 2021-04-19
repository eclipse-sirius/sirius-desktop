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
package org.eclipse.sirius.tests.swtbot;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This class is used to check that it is possible and that there is no exception when moving a Note and the
 * NoteAttachment has a single bendpoint.
 * 
 * @author gplouhinec
 *
 */
public class RectilinearNoteAttachmentWithOneBendpointTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_FILE = "NoteAttachmentWithOneBendpoint.ecore";

    private static final String SESSION_FILE = "NoteAttachmentWithOneBendpoint.aird";

    private static final String DATA_UNIT_DIR = "data/unit/noteAttachments/NoteAttachmentWithOneBendpoint/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE);
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
    }

    /**
     * Test that a NoteAttachment with only one bendpoint doesn't block the Note and that the NoteAttachment has a valid
     * number of bendpoints after moving the note.
     */
    @SuppressWarnings("rawtypes")
    public void testMovingNoteWithOneBendpointOnNoteAttachmentAtOpening() {
        SWTBotGefEditPart noteEP = editor.getEditPart("Text", SiriusNoteEditPart.class);
        editor.select(noteEP);

        AbstractGraphicalEditPart noteGraphicalEditPart = (AbstractGraphicalEditPart) noteEP.part();
        ConnectionEditPart attachmentEP = (ConnectionEditPart) noteGraphicalEditPart.getSourceConnections().get(0);
        List bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertEquals("Wrong number of bendpoints,", 1, bendpoints.size());

        final Point pointToDrag = editor.getAbsoluteCenter(noteGraphicalEditPart);
        final Point endpoint = new Point(300, 300);
        editor.drag(pointToDrag.x, pointToDrag.y, endpoint.x, endpoint.y);
        SWTBotUtils.waitAllUiEvents();

        bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertEquals("Wrong number of bendpoints,", 3, bendpoints.size());
    }
}
