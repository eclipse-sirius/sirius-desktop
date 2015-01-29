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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckLifelineResize;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNoteAttachement;
import org.eclipse.sirius.tests.swtbot.sequence.condition.ConnectionEditPartChangedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.collect.Iterables;

/**
 * Test note attachment to sequence nodes.
 */
@SuppressWarnings("restriction")
public class NoteAttachmentTest extends AbstractDefaultModelSequenceTests {

    private LifelineEditPart lep;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.reveal(LIFELINE_A);

        lep = getLifelineEditPart(LIFELINE_A);
    }

    /**
     * Test attach note to lifeline.
     */
    public void testAttachNoteToLifeline() {
        createNoteAndAttachment(200, false);
    }

    /**
     * Test attach lifeline to note.
     */
    public void testAttachLifelineToNote() {
        createNoteAndAttachment(200, true);
    }

    private void createNoteAndAttachment(int y1, boolean attachmentFromLifeline) {
        createNote(200, 200, "Test");
        CheckNoteAttachement cna = new CheckNoteAttachement(lep, attachmentFromLifeline);
        if (attachmentFromLifeline) {
            attachLifelineToNote(210, 210, LIFELINE_A, y1);
        } else {
            attachNoteToLifeline(210, 210, LIFELINE_A, y1);
        }
        bot.waitUntil(cna);

        int lepSourceConnections = attachmentFromLifeline ? 1 : 0;
        int lepTargetConnections = attachmentFromLifeline ? 0 : 1;
        Object attachmentPart = attachmentFromLifeline ? lep.getSourceConnections().get(0) : lep.getTargetConnections().get(0);

        assertEquals(lepSourceConnections, lep.getSourceConnections().size());
        assertEquals(lepTargetConnections, lep.getTargetConnections().size());
        assertTrue(attachmentPart instanceof NoteAttachmentEditPart);

    }

    /**
     * Test move note attachment target bendpoint on lifeline.
     */
    public void testMoveNoteAttachmentTargetPointOnLifeline() {
        int x = getLifelineEditPart(LIFELINE_A).getFigure().getBounds().right();
        int y1 = 190;
        int y2 = 250;

        createNoteAndAttachment(y1, false);

        NoteAttachmentEditPart naep = (NoteAttachmentEditPart) lep.getTargetConnections().get(0);
        Point lastPoint = naep.getConnectionFigure().getPoints().getLastPoint().getCopy();
        GraphicalHelper.logical2screen(lastPoint, lep);
        assertEquals(x, lastPoint.x);
        assertEquals(y1, lastPoint.y, 1);

        ICondition condition = new ConnectionEditPartChangedCondition(naep);
        editor.drag(lastPoint.x, lastPoint.y, lastPoint.x - 1, y2);
        bot.waitUntil(condition);

        lastPoint = naep.getConnectionFigure().getPoints().getLastPoint().getCopy();
        GraphicalHelper.logical2screen(lastPoint, lep);
        assertEquals(x, lastPoint.x, 1);
        // marge d'erreur : anchor positition et point de réference :
        // intersection figure
        assertEquals(y2, lastPoint.y, 2);
    }

    /**
     * Test move note attachment source bendpoint on lifeline.
     */
    public void testMoveNoteAttachmentSourcePointOnLifeline() {
        int x = getLifelineEditPart(LIFELINE_A).getFigure().getBounds().right();
        int y1 = 190;
        int y2 = 250;

        createNoteAndAttachment(y1, true);

        NoteAttachmentEditPart naep = (NoteAttachmentEditPart) lep.getSourceConnections().get(0);
        Point lastPoint = naep.getConnectionFigure().getPoints().getFirstPoint().getCopy();
        GraphicalHelper.logical2screen(lastPoint, lep);
        assertEquals(x, lastPoint.x);
        assertEquals(y1, lastPoint.y, 1);

        ICondition condition = new ConnectionEditPartChangedCondition(naep);
        editor.drag(lastPoint.x, lastPoint.y, lastPoint.x - 1, y2);
        bot.waitUntil(condition);

        lastPoint = naep.getConnectionFigure().getPoints().getFirstPoint().getCopy();
        GraphicalHelper.logical2screen(lastPoint, lep);
        assertEquals(x, lastPoint.x, 1);
        // marge d'erreur : anchor positition et point de réference :
        // intersection figure
        assertEquals(y2, lastPoint.y, 2);
    }

    /**
     * Test source note attachment stability when resizing down the lifeline.
     */
    public void testAttachmentSourceIsStableWhenLifelineIsExtended() {
        int x = getLifelineEditPart(LIFELINE_A).getFigure().getBounds().right();
        int y1 = 190;

        maximizeEditor(editor);

        createNoteAndAttachment(y1, true);

        NoteAttachmentEditPart naep = (NoteAttachmentEditPart) lep.getSourceConnections().get(0);
        Point lastPoint = naep.getConnectionFigure().getPoints().getFirstPoint();
        assertEquals(x, lastPoint.x);
        assertEquals(y1, lastPoint.y, 1);

        EndOfLifeEditPart eol = Iterables.getOnlyElement(Iterables.filter(getLifelineEditPart(LIFELINE_A).getChildren(), EndOfLifeEditPart.class));
        Point center = eol.getFigure().getBounds().getCenter();
        ICondition condition = new OperationDoneCondition();
        editor.drag(center, center.getTranslated(new Dimension(0, 20)));
        bot.waitUntil(condition);
        SWTBotUtils.waitAllUiEvents();

        lastPoint = naep.getConnectionFigure().getPoints().getFirstPoint();
        assertEquals(x, lastPoint.x);
        assertEquals(y1, lastPoint.y, 1);
    }

    /**
     * Test target note attachment stability when resizing down the lifeline.
     */
    public void testAttachmentTargetIsStableWhenLifelineIsExtended() {
        int x = getLifelineEditPart(LIFELINE_A).getFigure().getBounds().right();
        int y1 = 190;

        maximizeEditor(editor);

        createNoteAndAttachment(y1, false);

        NoteAttachmentEditPart naep = (NoteAttachmentEditPart) lep.getTargetConnections().get(0);
        Point lastPoint = naep.getConnectionFigure().getPoints().getLastPoint();
        assertEquals(x, lastPoint.x);
        assertEquals(y1, lastPoint.y, 1);

        EndOfLifeEditPart eol = Iterables.getOnlyElement(Iterables.filter(getLifelineEditPart(LIFELINE_A).getChildren(), EndOfLifeEditPart.class));
        Point center = eol.getFigure().getBounds().getCenter();
        ICondition condition = new OperationDoneCondition();
        editor.drag(center, center.getTranslated(new Dimension(0, 20)));
        bot.waitUntil(condition);
        SWTBotUtils.waitAllUiEvents();

        lastPoint = naep.getConnectionFigure().getPoints().getLastPoint();
        assertEquals(x, lastPoint.x);
        assertEquals(y1, lastPoint.y, 1);
    }

    /**
     * Test source note attachment stability when resizing up the lifeline.
     */
    public void testAttachmentSourceStaysInRangeWhenLifelineIsReduced() {
        int x = getLifelineEditPart(LIFELINE_A).getFigure().getBounds().right();

        maximizeEditor(editor);

        // Expand the lifeline so that it can be reduced later.
        EndOfLifeEditPart eol = Iterables.getOnlyElement(Iterables.filter(getLifelineEditPart(LIFELINE_A).getChildren(), EndOfLifeEditPart.class));
        Point center = eol.getFigure().getBounds().getCenter();
        ICondition condition = new OperationDoneCondition();
        editor.drag(center, center.getTranslated(new Dimension(0, 50)));
        bot.waitUntil(condition);
        SWTBotUtils.waitAllUiEvents();

        int y = eol.getFigure().getBounds().getTop().y - 10;
        createNoteAndAttachment(y, true);

        NoteAttachmentEditPart naep = (NoteAttachmentEditPart) lep.getSourceConnections().get(0);
        Point lastPoint = naep.getConnectionFigure().getPoints().getFirstPoint();
        assertEquals(x, lastPoint.x);
        // marge d'erreur : anchor positition et point de réference :
        // intersection figure
        assertEquals(y, lastPoint.y, 20);

        // Reduce the lifeline back to original size
        center = eol.getFigure().getBounds().getCenter();
        condition = new OperationDoneCondition();
        editor.drag(center, center.getTranslated(new Dimension(0, -50)));
        bot.waitUntil(condition);
        SWTBotUtils.waitAllUiEvents();

        lastPoint = naep.getConnectionFigure().getPoints().getFirstPoint();
        assertEquals(x, lastPoint.x);
        // marge d'erreur : anchor positition et point de réference :
        // intersection figure
        assertEquals(eol.getFigure().getBounds().getTop().y, lastPoint.y, 20);
    }

    /**
     * Test target note attachment stability when resizing up the lifeline.
     */
    public void testAttachmentTargetStaysInRangeWhenLifelineIsReduced() {
        int x = getLifelineEditPart(LIFELINE_A).getFigure().getBounds().right();

        maximizeEditor(editor);

        // Expand the lifeline so that it can be reduced later.
        EndOfLifeEditPart eol = Iterables.getOnlyElement(Iterables.filter(getLifelineEditPart(LIFELINE_A).getChildren(), EndOfLifeEditPart.class));
        Point center = eol.getFigure().getBounds().getCenter();
        ICondition condition = new OperationDoneCondition();
        editor.drag(center, center.getTranslated(new Dimension(0, 50)));
        bot.waitUntil(condition);
        SWTBotUtils.waitAllUiEvents();

        createNote(200, 200, "Test");
        int y = eol.getFigure().getBounds().getTop().y - 10;
        CheckNoteAttachement cna = new CheckNoteAttachement(lep, false);
        attachNoteToLifeline(210, 210, LIFELINE_A, eol.getFigure().getBounds().getTop().y - 10);
        bot.waitUntil(cna);

        assertEquals(0, lep.getSourceConnections().size());
        assertEquals(1, lep.getTargetConnections().size());
        assertTrue(lep.getTargetConnections().get(0) instanceof NoteAttachmentEditPart);

        NoteAttachmentEditPart naep = (NoteAttachmentEditPart) lep.getTargetConnections().get(0);
        Point lastPoint = naep.getConnectionFigure().getPoints().getLastPoint();
        assertEquals(x, lastPoint.x);
        // marge d'erreur : anchor positition et point de réference :
        // intersection figure
        assertEquals(y, lastPoint.y, 20);

        // Reduce the lifeline back to original size
        center = eol.getFigure().getBounds().getCenter();
        CheckLifelineResize clr = new CheckLifelineResize(getLifelineEditPart(LIFELINE_A), new Point(0, -50));
        editor.drag(center, center.getTranslated(new Dimension(0, -50)));
        bot.waitUntil(clr);

        lastPoint = naep.getConnectionFigure().getPoints().getLastPoint();
        assertEquals(x, lastPoint.x);
        // marge d'erreur : anchor positition et point de réference :
        // intersection figure
        assertEquals(eol.getFigure().getBounds().getTop().y, lastPoint.y, 20);
    }

    private void createNote(int x, int y, String text) {
        editor.activateTool("Note");
        editor.click(x, y);
        editor.directEditType(text);
        editor.click(1, 1);
    }

    private void attachNoteToLifeline(int noteX, int noteY, String lifelineName, int lifelineY) {
        int lifelineX = getLifelineScreenX(lifelineName);
        editor.activateTool("Note Attachment");
        editor.click(noteX, noteY);
        editor.click(lifelineX, lifelineY);
    }

    private void attachLifelineToNote(int noteX, int noteY, String lifelineName, int lifelineY) {
        int lifelineX = getLifelineScreenX(lifelineName);
        editor.activateTool("Note Attachment");
        editor.click(lifelineX, lifelineY);
        editor.click(noteX, noteY);
    }
}
