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
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.condition.BendpointMovedCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Common behavior for Rectilinear NoteAttachment tests.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
@SuppressWarnings("restriction")
public class AbstractRectilinearNoteAttachmentTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Checks in this particular scenario that the bendpoints of the NoteAttachment remain consistent after moving the
     * Note and that there is not just one bendpoint left.
     * 
     * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=570518"
     * 
     * @param swtBotGefEditPart
     *            the swtBotGefEditPart corresponding to the Note, Representation Link or Text.
     */
    @SuppressWarnings("rawtypes")
    public void consistentNumberBendpoints(SWTBotGefEditPart swtBotGefEditPart) {
        editor.select(swtBotGefEditPart);
        final Point pointToDrag = editor.getAbsoluteCenter((GraphicalEditPart) swtBotGefEditPart.part());
        final Point endpoint = new Point(80, 155);

        AbstractGraphicalEditPart noteEP = (AbstractGraphicalEditPart) swtBotGefEditPart.part();
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

        assertTrue("There must be 4 points.", points.size() == 4);
        assertTrue("There must be 4 bendpoints.", bendpoints.size() == 4);
    }

    /**
     * Test that the "Remove Bend-points" command works correctly in this scenario before moving the element connected
     * to the NoteAttachment.
     */
    @SuppressWarnings("rawtypes")
    public void removeBendpointsOnNoteAttachmentBeforeMovingElement() {
        List<SWTBotGefConnectionEditPart> gefConnectionEditParts = editor.getConnectionsEditPart();
        SWTBotGefConnectionEditPart noteAttachmentSWTEP = getNoteAttachmentSWTBotGefEditPart(gefConnectionEditParts);
        noteAttachmentSWTEP.select();

        ConnectionEditPart attachmentEP = (ConnectionEditPart) noteAttachmentSWTEP.part();
        PointList points = attachmentEP.getConnectionFigure().getPoints();
        List bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();
        assertTrue("There must be 5 points.", points.size() == 5);
        assertTrue("There must be 5 bendpoints.", bendpoints.size() == 5);

        removeBendpoints();

        points = attachmentEP.getConnectionFigure().getPoints();
        bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertTrue("There must be 4 points.", points.size() == 4);
        assertTrue("There must be 4 bendpoints.", bendpoints.size() == 4);
    }

    /**
     * Test that the "Remove Bend-points" command works correctly in this scenario after moving the element connected to
     * the NoteAttachment.
     * 
     * @param swtBotGefEditPart
     *            the swtBotGefEditPart corresponding to the Note, Representation Link or Text.
     */
    @SuppressWarnings("rawtypes")
    public void removeBendpointsOnNoteAttachmentAfterMovingElement(SWTBotGefEditPart swtBotGefEditPart) {
        editor.select(swtBotGefEditPart);
        final Point pointToDrag = editor.getAbsoluteCenter((GraphicalEditPart) swtBotGefEditPart.part());
        final Point endpoint = new Point(80, 155);

        SWTBotGefConnectionEditPart noteAttachmentSWTEP = getNoteAttachmentSWTBotGefEditPart(editor.getConnectionsEditPart());
        ConnectionEditPart attachmentEP = (ConnectionEditPart) noteAttachmentSWTEP.part();
        PointList points = attachmentEP.getConnectionFigure().getPoints();

        editor.drag(pointToDrag.x, pointToDrag.y, endpoint.x, endpoint.y);
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new BendpointMovedCondition(attachmentEP, points.getPoint(0)).checkFirstBendpoint());

        points = attachmentEP.getConnectionFigure().getPoints();
        List bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertTrue("There must be 4 points.", points.size() == 4);
        assertTrue("There must be 4 bendpoints.", bendpoints.size() == 4);

        noteAttachmentSWTEP.select();

        removeBendpoints();

        points = attachmentEP.getConnectionFigure().getPoints();
        bendpoints = ((RelativeBendpoints) ((Edge) attachmentEP.getModel()).getBendpoints()).getPoints();

        assertTrue("There must be 3 points.", points.size() == 3);
        assertTrue("There must be 3 bendpoints.", bendpoints.size() == 3);
    }

    /**
     * Retrieves the NoteAttachment of the scenario.
     * 
     * @param gefConnectionEditParts
     *            all wrapped ConnectionsEditPart of the diagram (Edges and NoteAttachment)
     * @return the wrapped {@link NoteAttachmentEditPart}.
     */
    protected SWTBotGefConnectionEditPart getNoteAttachmentSWTBotGefEditPart(List<SWTBotGefConnectionEditPart> gefConnectionEditParts) {
        for (SWTBotGefConnectionEditPart swtEditPart : gefConnectionEditParts) {
            if (swtEditPart.part() instanceof NoteAttachmentEditPart) {
                return swtEditPart;
            }
        }
        return null;
    }

    /**
     * Uses the "Remove Bend-points" context menu.
     */
    protected void removeBendpoints() {
        editor.clickContextMenu("Remove Bend-points");
        SWTBotUtils.waitAllUiEvents();
    }
}
