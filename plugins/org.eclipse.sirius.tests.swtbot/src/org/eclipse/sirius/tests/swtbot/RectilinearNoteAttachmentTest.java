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
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.BendpointMovedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Ensures that NoteAttachment with Rectilinear style works correctly.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class RectilinearNoteAttachmentTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_FILE = "bugzilla_570518.ecore";

    private static final String SESSION_FILE = "bugzilla_570518.aird";

    private static final String DATA_UNIT_DIR = "data/unit/noteAttachments/bugzilla_570518/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE);
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", " package entities", DDiagram.class);
    }

    /**
     * Checks in this particular scenario that the bendpoints of the NoteAttachment remain consistent and that there is
     * not just one bendpoint left.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=570518"
     */
    @SuppressWarnings("rawtypes")
    public void testConsistentNumberBendpoints() {
        SWTBotGefEditPart note = editor.getEditPart("Text", SiriusNoteEditPart.class);
        editor.select(note);
        final Point pointToDrag = editor.getAbsoluteCenter((GraphicalEditPart) note.part());
        final Point endpoint = new Point(80, 155);

        SiriusNoteEditPart noteEP = (SiriusNoteEditPart) note.part();
        ConnectionEditPart attachmentEP = (ConnectionEditPart) noteEP.getSourceConnections().get(0);
        PointList points = attachmentEP.getConnectionFigure().getPoints();
        List bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertTrue("There must be 5 points.", points.size() == 5);
        assertTrue("There must be 5 bendpoints.", bendpoints.size() == 5);

        editor.drag(pointToDrag.x, pointToDrag.y, endpoint.x, endpoint.y);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new BendpointMovedCondition(attachmentEP, points.getPoint(0)).checkFirstBendpoint());

        points = attachmentEP.getConnectionFigure().getPoints();
        bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertTrue("There must be 5 points.", points.size() == 4);
        assertTrue("There must be 5 bendpoints.", bendpoints.size() == 4);

    }
}
