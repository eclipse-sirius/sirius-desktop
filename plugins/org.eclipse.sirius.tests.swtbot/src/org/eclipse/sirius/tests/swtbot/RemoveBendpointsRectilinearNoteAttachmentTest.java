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

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;

/**
 * Ensures that the "Remove Bend-points" command works correctly on a NoteAttachment with a Rectilinear style and
 * doesn't raise an exception in this scenario.
 * 
 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=571214">Bug 571214</a>
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class RemoveBendpointsRectilinearNoteAttachmentTest extends AbstractRectilinearNoteAttachmentTest {

    private static final String MODEL_FILE = "bugzilla_571214.ecore";

    private static final String SESSION_FILE = "bugzilla_571214.aird";

    private static final String DATA_UNIT_DIR = "data/unit/noteAttachments/bugzilla_571214/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE);
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
    }

    /**
     * @see AbstractRectilinearNoteAttachmentTest#removeBendpointsOnNoteAttachmentBeforeMovingElement()
     */
    @SuppressWarnings("rawtypes")
    public void testRemoveBendpointsOnNoteAttachment() {
        List<SWTBotGefConnectionEditPart> gefConnectionEditParts = editor.getConnectionsEditPart();
        SWTBotGefConnectionEditPart noteAttachmentSWTEP = getNoteAttachmentSWTBotGefEditPart(gefConnectionEditParts);
        noteAttachmentSWTEP.select();

        ConnectionEditPart attachmentEP = (ConnectionEditPart) noteAttachmentSWTEP.part();
        List bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();
        assertTrue("There must be 6 bendpoints.", bendpoints.size() == 6);

        removeBendpoints();

        bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertTrue("There must be 4 bendpoints.", bendpoints.size() == 4);
    }
}
