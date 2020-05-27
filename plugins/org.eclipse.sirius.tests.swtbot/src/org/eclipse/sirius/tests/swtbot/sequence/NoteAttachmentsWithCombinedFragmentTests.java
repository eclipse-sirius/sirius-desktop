/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.notation.DescriptionStyle;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNoteAttachement;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Tests concerning Note Attachment with Combined Fragment and Operand (in particular visibility if them).
 * 
 * @author Laurent Redor
 */
@SuppressWarnings("restriction")
public class NoteAttachmentsWithCombinedFragmentTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Create notes, note attachments and check visibility of note attachments (Notes are the source of the note
     * attachments).
     */
    public void testCreationOfNoteAttachmentFromNoteToOperandAndCombinedFragment() {
        testCreationOfNoteAttachment(true);
    }

    /**
     * Create notes, note attachments and check visibility of note attachments (Notes are the target of the note
     * attachments).
     */
    public void testCreationOfNoteAttachmentFromOperandAndCombinedFragmentToNote() {
        testCreationOfNoteAttachment(false);
    }

    /**
     * Check that note attachments are always visible after reducing the size of a combined fragment (there are one note
     * attachment on the combined fragment and one on the operand).
     */
    public void testNoteAttachmentVisibilityAfterReducingCombinedFragmentSize() {
        testNoteAttachmentVisibilityAfterCombinedFragmentResize(-10);
    }

    /**
     * Check that note attachments are always visible after increasing the size of a combined fragment (there are one
     * note attachment on the combined fragment and one on the operand).
     */
    public void testNoteAttachmentVisibilityAfterIncreasingCombinedFragmentSize() {
        testNoteAttachmentVisibilityAfterCombinedFragmentResize(10);
    }

    /**
     * Check that note attachments are always visible after a reducing the size of an operand (there are one note
     * attachment on the combined fragment and one on the operand).
     */
    public void testNoteAttachmentVisibilityAfterReducingOperandSize() {
        testNoteAttachmentVisibilityAfterOperandResize(firstOperandOfFirstCombinedFragmentBot, firstOperandOfFirstCombinedFragmentBounds, -10, false);
    }

    /**
     * Check that note attachments are always visible after a increasing the size of an operand (there are one note
     * attachment on the combined fragment and one on the operand).
     */
    public void testNoteAttachmentVisibilityAfterIncreasingLastOperandSize() {
        testNoteAttachmentVisibilityAfterOperandResize(secondOperandOfFirstCombinedFragmentBot, secondOperandOfFirstCombinedFragmentBounds, 10, true);
    }

    /**
     * Create notes, note attachments and check visibility of note attachments.
     * 
     * @param attachmentFromNote
     *            true to create the note attachment from Note to other edit part, false otherwise.
     */
    protected void testCreationOfNoteAttachment(boolean attachmentFromNote) {
        // Make opt 2 visible
        editor.reveal(secondCombinedFragmentEditPart);
        // Create notes, note attachments and check visibility of Note attachments
        NoteAttachmentEditPart noteAttachment1 = createNoteAndAttachment(400, 225, "Note1", firstOperandOfFirstCombinedFragmentBot, PositionConstants.MIDDLE, PositionConstants.CENTER,
                attachmentFromNote);
        NoteAttachmentEditPart noteAttachment2 = createNoteAndAttachment(400, 300, "Note2", secondOperandOfFirstCombinedFragmentBot, PositionConstants.MIDDLE, PositionConstants.CENTER,
                attachmentFromNote);
        checkVisibility(attachmentFromNote, noteAttachment2);
        NoteAttachmentEditPart noteAttachment3 = createNoteAndAttachment(400, 375, "Note3", firstCombinedFragmentBot, PositionConstants.MIDDLE, PositionConstants.TOP, attachmentFromNote);
        checkVisibility(attachmentFromNote, noteAttachment1, noteAttachment2);
        createNoteAndAttachment(400, 450, "Note4", secondOperandOfFirstCombinedFragmentBot, PositionConstants.MIDDLE, PositionConstants.CENTER, attachmentFromNote);
        checkVisibility(attachmentFromNote, noteAttachment1, noteAttachment2, noteAttachment3);
    }

    /**
     * Check that note attachments are always visible after a resize of combined fragment (there are one note attachment
     * on the combined fragment and one on the operand).
     * 
     * @param delta
     *            the delta to resize (negative value to reduce, positive value to increase)
     */
    protected void testNoteAttachmentVisibilityAfterCombinedFragmentResize(int delta) {
        // Make opt 2 visible
        editor.reveal(secondCombinedFragmentEditPart);
        // Create notes, note attachments and check visibility of Note attachments
        NoteAttachmentEditPart noteAttachment1 = createNoteAndAttachment(400, 200, "Note1", firstCombinedFragmentBot, PositionConstants.RIGHT, PositionConstants.TOP, true);
        NoteAttachmentEditPart noteAttachment2 = createNoteAndAttachment(400, 300, "Note2", firstOperandOfFirstCombinedFragmentBot, PositionConstants.RIGHT, PositionConstants.CENTER, true);
        // Resize the combined fragment on top
        resizeCombinedFragmentFromTop(firstCombinedFragmentBot, firstCombinedFragmentBounds, delta);
        checkVisibility(true, noteAttachment1, noteAttachment2);
        undo("Combined Fragme...mposite Command");
        SWTBotUtils.waitAllUiEvents();
        checkVisibility(true, noteAttachment1, noteAttachment2);
    }

    /**
     * Check that note attachments are always visible after a resize of operand (there are one note attachment on the
     * combined fragment and one on the operand).
     * 
     * @param operandBot
     *            The operand to resize
     * @param operandBounds
     *            The current operand bounds
     * @param isLastOperand
     *            true if the operand to resize is the last of the combined fragment, false otherwise
     * @param delta
     *            the delta to resize (negative value to reduce, positive value to increase)
     */
    protected void testNoteAttachmentVisibilityAfterOperandResize(SWTBotGefEditPart operandBot, Rectangle operandBounds, int delta, boolean isLastOperand) {
        // Make opt 2 visible
        editor.reveal(secondCombinedFragmentEditPart);
        // Create notes, note attachments and check visibility of Note attachments
        NoteAttachmentEditPart noteAttachment1 = createNoteAndAttachment(400, 200, "Note1", firstCombinedFragmentBot, PositionConstants.RIGHT, PositionConstants.TOP, true);
        NoteAttachmentEditPart noteAttachment2 = createNoteAndAttachment(400, 300, "Note2", firstOperandOfFirstCombinedFragmentBot, PositionConstants.RIGHT, PositionConstants.CENTER, true);
        // Resize the operand on bottom
        resizeOperandFromBottom(operandBot, operandBounds, delta);
        checkVisibility(true, noteAttachment1, noteAttachment2);
        if (isLastOperand) {
            undo("Combined Fragme...mposite Command");
        } else {
            undo("Operand Resize Composite Command");
        }
        SWTBotUtils.waitAllUiEvents();
        checkVisibility(true, noteAttachment1, noteAttachment2);
    }

    private void resizeCombinedFragmentFromTop(SWTBotGefEditPart combinedFragmentBot, Rectangle combinedFragmentBounds, int delta) {
        editor.click(1, 1);
        editor.click(combinedFragmentBounds.getTop());
        bot.waitUntil(new CheckSelectedCondition(editor, combinedFragmentBot.part()));

        CheckEditPartResized checkResize = new CheckEditPartResized(combinedFragmentBot);
        editor.drag(combinedFragmentBounds.getTop(), combinedFragmentBounds.getTop().getTranslated(0, -delta));
        bot.waitUntil(checkResize);
        Rectangle newBounds = editor.getBounds(combinedFragmentBot);
        Assert.assertEquals(combinedFragmentBounds.getTranslated(0, -delta).getResized(0, delta), newBounds);
        SWTBotUtils.waitAllUiEvents();
    }

    private void resizeOperandFromBottom(SWTBotGefEditPart operandBot, Rectangle operandBounds, int delta) {
        editor.click(1, 1);
        editor.click(operandBounds.getTop());
        bot.waitUntil(new CheckSelectedCondition(editor, operandBot.part()));

        CheckEditPartResized checkResize = new CheckEditPartResized(operandBot);
        System.out.println(operandBounds.getBottom());
        editor.drag(operandBounds.getBottom(), operandBounds.getBottom().getTranslated(0, delta));
        bot.waitUntil(checkResize);
        Assert.assertEquals(operandBounds.getResized(0, delta), editor.getBounds(operandBot));
        SWTBotUtils.waitAllUiEvents();
    }

    private void createNote(int x, int y, String text) {
        editor.activateTool("Note");
        editor.click(x, y);
        editor.directEditType(text);
        editor.click(1, 1);
    }

    /**
     * @param noteEditPart
     *            the source or target note
     * @param editPart
     *            the target or source edit part
     * @param verticalLocation
     *            One of {@link PositionConstants#TOP}, {@link PositionConstants#CENTER} or
     *            {@link PositionConstants#BOTTOM}
     * @param horizontalLocation
     *            One of {@link PositionConstants#LEFT}, {@link PositionConstants#MIDDLE} or
     *            {@link PositionConstants#RIGHT}
     * @param fromNoteToEditPart
     *            true if the note attachment is from note to edit part, false otherwise
     */
    private void attachNoteToEditPart(SWTBotGefEditPart noteEditPart, SWTBotGefEditPart editPart, int horizontalLocation, int verticalLocation, boolean fromNoteToEditPart) {
        Rectangle noteBounds = editor.getBounds(noteEditPart);
        Rectangle editPartBounds = editor.getBounds(editPart);
        // Compute location of the "anchor" in the edit part
        int xLocation = editPartBounds.getCenter().x();
        if (horizontalLocation == PositionConstants.RIGHT) {
            xLocation = editPartBounds.getRight().x() - 10;
        } else if (horizontalLocation == PositionConstants.LEFT) {
            xLocation = editPartBounds.getLeft().x() + 10;
        }
        int yLocation = editPartBounds.getCenter().y();
        if (verticalLocation == PositionConstants.TOP) {
            yLocation = editPartBounds.getTop().y + 10;
        } else if (verticalLocation == PositionConstants.BOTTOM) {
            yLocation = editPartBounds.getBottom().y - 10;
        }
        // Create the note attachment
        editor.activateTool("Note Attachment");
        if (fromNoteToEditPart) {
            editor.click(noteBounds.getCenter().x(), noteBounds.getCenter().y());
            editor.click(xLocation, yLocation);
        } else {
            editor.click(xLocation, yLocation);
            editor.click(noteBounds.getCenter().x(), noteBounds.getCenter().y());
        }
    }

    private NoteAttachmentEditPart createNoteAndAttachment(int xLocation, int yLocation, String label, SWTBotGefEditPart sourceOrTarget, int horizontalLocation, int verticalLocation,
            boolean attachmentFromNote) {
        AbstractGraphicalEditPart sourceOrTargetEditPart = (AbstractGraphicalEditPart) sourceOrTarget.part();

        int nbSourceConnectionsExpected = sourceOrTargetEditPart.getSourceConnections().size();
        int nbTargetConnectionsExpected = sourceOrTargetEditPart.getTargetConnections().size();

        createNote(xLocation, yLocation, label);
        SWTBotGefEditPart noteEditPart = editor.getEditPart(label, SiriusNoteEditPart.class);

        CheckNoteAttachement cna = new CheckNoteAttachement(sourceOrTargetEditPart, attachmentFromNote);
        // The 250, 230 coordinates are about the note center. The idea is creating a note attachment without source or
        // target anchor.
        attachNoteToEditPart(noteEditPart, sourceOrTarget, horizontalLocation, verticalLocation, attachmentFromNote);
        if (attachmentFromNote) {
            nbTargetConnectionsExpected++;
        } else {
            nbSourceConnectionsExpected++;
        }
        bot.waitUntil(cna);

        assertEquals(nbSourceConnectionsExpected, sourceOrTargetEditPart.getSourceConnections().size());
        assertEquals(nbTargetConnectionsExpected, sourceOrTargetEditPart.getTargetConnections().size());

        Object attachmentEditPart = attachmentFromNote ? sourceOrTargetEditPart.getTargetConnections().get(nbTargetConnectionsExpected - 1)
                : sourceOrTargetEditPart.getSourceConnections().get(nbSourceConnectionsExpected - 1);
        assertTrue(attachmentEditPart instanceof NoteAttachmentEditPart);
        SWTBotUtils.waitAllUiEvents();
        // Check that the figure of the note attachment is visible
        checkVisibility(attachmentFromNote, (NoteAttachmentEditPart) attachmentEditPart);
        return (NoteAttachmentEditPart) attachmentEditPart;
    }

    private void checkVisibility(boolean isNoteOnSourceSide, NoteAttachmentEditPart... noteAttachmentEditParts) {
        for (NoteAttachmentEditPart noteAttachmentEditPart : noteAttachmentEditParts) {
            String noteLabel;
            if (isNoteOnSourceSide) {
                noteLabel = ((DescriptionStyle) noteAttachmentEditPart.getSource().getModel()).getDescription();
            } else {
                noteLabel = ((DescriptionStyle) noteAttachmentEditPart.getTarget().getModel()).getDescription();
            }
            assertTrue("The figure of the note attachment of Note \"" + noteLabel + "\" should be visible.", noteAttachmentEditPart.getFigure().isVisible());
        }
    }

    /**
     * Check that the created Note is at the expected location.
     * 
     * @param getAbsoluteLocation
     *            the name of the Note to check
     * @param expectedLocation
     *            the expected absolute position of the created Note within the graphical viewer
     */
    private void assertNoteAtLocation(String noteLabel, Point expectedLocation) {
        Point noteLocation = editor.getAbsoluteLocation(noteLabel, SiriusNoteEditPart.class);
        assertEquals("The Note has been created at wrong location.", expectedLocation, noteLocation);
    }
}
