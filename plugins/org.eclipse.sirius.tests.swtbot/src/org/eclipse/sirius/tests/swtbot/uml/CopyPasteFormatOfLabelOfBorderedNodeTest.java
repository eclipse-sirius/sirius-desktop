/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.uml;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This test check that a pasted Node corresponding to the label of a bordered
 * node has {@link Location} as constraint (and not {@link Bounds}).
 * 
 * @See VP-3292.
 * @author lredor
 */
public class CopyPasteFormatOfLabelOfBorderedNodeTest extends AbstractUmlDragAndDropTest {
    @Override
    protected void tearDown() throws Exception {
        clearFormatDataManager();
        super.tearDown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return "DnD Component Diagram";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return "Component Diagram";
    }

    /**
     * This test check that the pasted location of bordered nodes is the same as
     * the copied location if the location is already occupied by another port
     * that is also pasted.@See VP-3099.<BR>
     * Also Check that only the layout of the label of border node is paste when
     * launching Paste Layout action.
     */
    public void testCopyPasteLayout() {
        Color red = new Color(null, 227, 164, 156);
        try {
            final EditPart srcLabelEditPart = editor.getEditPart("DropPort", AbstractDiagramNameEditPart.class).part();
            assertTrue("The model of the DropPort editPart in source diagram should be a Node", srcLabelEditPart.getModel() instanceof Node);
            assertTrue("The constraint of the Node of the label \"DropPort\" should be a Location.", ((Node) srcLabelEditPart.getModel()).getLayoutConstraint() instanceof Location);
            Point sourceLabelLocation = new Point(((Location) ((Node) srcLabelEditPart.getModel()).getLayoutConstraint()).getX(),
                    ((Location) ((Node) srcLabelEditPart.getModel()).getLayoutConstraint()).getY());
            checkBorderNodeLabelColor(srcLabelEditPart, red, "Red");
            // Launch copy layout
            editor.clickContextMenu(Messages.CopyFormatAction_text);
            // Open the second editor to paste in
            final SWTBotSiriusDiagramEditor editorForPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(),
                    "Component Diagram - Paste to", DDiagram.class);
            // Launch paste layout
            editorForPaste.clickContextMenu(Messages.PasteLayoutAction_text);
            // Check that the layout constraint of the Label is a Location (and
            // not
            // a Bounds)
            final SWTBotGefEditPart editPartBot = editorForPaste.getEditPart("DropPort", AbstractDiagramNameEditPart.class);
            assertTrue("The model of the DropPort editPart should be a Node", editPartBot.part().getModel() instanceof Node);
            assertTrue("The constraint of the Node of the label \"DropPort\" should be a Location (and not a Bounds).",
                    !(((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Bounds) && ((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Location);
            // Check that the label location has changed
            Point currentPoint = new Point(((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getX(),
                    ((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getY());
            assertEquals("The location should be the same as the source after the paste layout", sourceLabelLocation, currentPoint);
            // Check that the color has not changed
            checkBorderNodeLabelColor(editPartBot.part(), ColorConstants.black, "Black");
        } finally {
            red.dispose();
        }
    }

    /**
     * Check that only the style of the label of border node is paste when
     * launching Paste Style action.
     */
    public void testCopyPasteStyle() {
        Color red = new Color(null, 227, 164, 156);
        try {
            final EditPart srcLabelEditPart = editor.getEditPart("DropPort", AbstractDiagramNameEditPart.class).part();
            checkBorderNodeLabelColor(srcLabelEditPart, red, "Red");
            // Launch copy layout
            editor.clickContextMenu(Messages.CopyFormatAction_text);
            // Open the second editor to paste in
            final SWTBotSiriusDiagramEditor editorForPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(),
                    "Component Diagram - Paste to", DDiagram.class);
            final SWTBotGefEditPart editPartBot = editorForPaste.getEditPart("DropPort", AbstractDiagramNameEditPart.class);
            assertTrue("The model of the DropPort editPart should be a Node", editPartBot.part().getModel() instanceof Node);
            assertTrue("The constraint of the Node of the label \"DropPort\" should be a Location (and not a Bounds).",
                    !(((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Bounds) && ((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Location);
            Point targetLabelLocation = new Point(((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getX(),
                    ((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getY());
            // Launch paste layout
            editorForPaste.clickContextMenu(Messages.PasteStyleAction_text);
            // Check that the layout constraint of the Label is a Location (and
            // not a Bounds)
            assertTrue("The model of the DropPort editPart should be a Node", editPartBot.part().getModel() instanceof Node);
            assertTrue("The constraint of the Node of the label \"DropPort\" should be a Location (and not a Bounds).",
                    !(((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Bounds) && ((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Location);
            // Check that the label location has not changed
            Point currentPoint = new Point(((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getX(),
                    ((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getY());
            assertEquals("The location should unchanged after the paste style", targetLabelLocation, currentPoint);
            // Check that the color has changed
            checkBorderNodeLabelColor(editPartBot.part(), red, "Red");
        } finally {
            red.dispose();
        }

    }

    /**
     * Check that layout and style of the label of border node is paste when
     * launching Paste Format action.
     */
    public void testCopyPasteFormat() {
        Color red = new Color(null, 227, 164, 156);
        try {
            final EditPart srcLabelEditPart = editor.getEditPart("DropPort", AbstractDiagramNameEditPart.class).part();
            assertTrue("The model of the DropPort editPart in source diagram should be a Node", srcLabelEditPart.getModel() instanceof Node);
            assertTrue("The constraint of the Node of the label \"DropPort\" should be a Location.", ((Node) srcLabelEditPart.getModel()).getLayoutConstraint() instanceof Location);
            Point sourceLabelLocation = new Point(((Location) ((Node) srcLabelEditPart.getModel()).getLayoutConstraint()).getX(),
                    ((Location) ((Node) srcLabelEditPart.getModel()).getLayoutConstraint()).getY());
            checkBorderNodeLabelColor(srcLabelEditPart, red, "Red");
            // Launch copy layout
            editor.clickContextMenu(Messages.CopyFormatAction_text);
            // Open the second editor to paste in
            final SWTBotSiriusDiagramEditor editorForPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(),
                    "Component Diagram - Paste to", DDiagram.class);
            // Launch paste layout
            editorForPaste.clickContextMenu(Messages.PasteFormatAction_text);
            // Check that the layout constraint of the Label is a Location (and
            // not
            // a Bounds)
            final SWTBotGefEditPart editPartBot = editorForPaste.getEditPart("DropPort", AbstractDiagramNameEditPart.class);
            assertTrue("The model of the DropPort editPart should be a Node", editPartBot.part().getModel() instanceof Node);
            assertTrue("The constraint of the Node of the label \"DropPort\" should be a Location (and not a Bounds).",
                    !(((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Bounds) && ((Node) editPartBot.part().getModel()).getLayoutConstraint() instanceof Location);
            // Check that the label location has changed
            Point currentPoint = new Point(((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getX(),
                    ((Location) ((Node) editPartBot.part().getModel()).getLayoutConstraint()).getY());
            assertEquals("The location should be the same as the source after the paste layout", sourceLabelLocation, currentPoint);
            // Check that the color has changed
            checkBorderNodeLabelColor(editPartBot.part(), red, "Red");
        } finally {
            red.dispose();
        }

    }

    private void checkBorderNodeLabelColor(EditPart srcLabelEditPart, Color color, String nameColor) {
        if (srcLabelEditPart instanceof DNodeNameEditPart) {
            DNodeNameEditPart nameEditPart = (DNodeNameEditPart) srcLabelEditPart;
            // // Check color
            assertEquals("The color of the label must be " + nameColor, color, nameEditPart.getFigure().getForegroundColor());
        }
    }
}
