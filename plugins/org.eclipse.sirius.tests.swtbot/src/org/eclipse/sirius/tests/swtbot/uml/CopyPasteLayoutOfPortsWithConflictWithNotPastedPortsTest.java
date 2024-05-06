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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.text.MessageFormat;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This test check that the pasted location of bordered nodes is near the copied
 * location if the location is already occupied by another port that is not
 * pasted.
 * 
 * @See VP-3099.
 * @author lredor
 */
public class CopyPasteLayoutOfPortsWithConflictWithNotPastedPortsTest extends AbstractUmlDragAndDropTest {
    /**
     * The maximum distance allowed between copied and pasted position (10 for
     * the size of port already at the same location + 1 for the space used in
     * BorderItemLocator).
     */
    private static final double MAX_POSITION_DELTA = 11;

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
        return "conflictsWithNotPasteElements-copy";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return "Component Diagram-DnDComponentAndPortFromModelExplorer";
    }

    /**
     * This test check that the pasted location of bordered nodes is near the
     * copied location if the location is already occupied by another port that
     * is not pasted.
     */
    public void testCopyPaste() {
        // Launch copy layout
        editor.clickContextMenu(Messages.CopyFormatAction_text);
        // Open the second editor to paste in
        final SWTBotSiriusDiagramEditor editorForPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(),
                "conflictsWithNotPasteElements-paste", DDiagram.class);
        // Check that the location of ports to paste is the same as other ports
        String errorMessage = "The copied {0} location should have the same location of the {1} current location in diagram to paste.";
        assertSameLocation(editor, "Port1", editorForPaste, "Port7", errorMessage);
        assertSameLocation(editor, "Port2", editorForPaste, "Port6", errorMessage);
        assertSameLocation(editor, "Port3", editorForPaste, "Port8", errorMessage);
        assertSameLocation(editor, "Port4", editorForPaste, "Port5", errorMessage);
        // Launch paste layout
        editorForPaste.clickContextMenu(Messages.PasteLayoutAction_text);
        // Check that the location of pasted ports is near to the original
        // copied location. A delta of 11 is tolerated (because they will be
        // shifted under or above the existing ports).
        // assertNearLocation(editor, originalPort, editor2, newPort, delta)
        errorMessage = "The pasted {1} location should have a location near the copied {0} location.";
        assertNearLocation(editor, "Port1", editorForPaste, "Port1", errorMessage);
        assertNearLocation(editor, "Port2", editorForPaste, "Port2", errorMessage);
        assertNearLocation(editor, "Port3", editorForPaste, "Port3", errorMessage);
        assertNearLocation(editor, "Port4", editorForPaste, "Port4", errorMessage);
    }

    /**
     * This test check that the pasted location of bordered nodes is correctly
     * computed according to the existing collapsed node in the paste diagram
     * that is in conflict with the copied layout.
     */
    public void testCopyPasteWithCollapseInPaste() {
        editor.close();
        SWTBotUtils.waitAllUiEvents();

        editor = openAndGetEditor(getRepresentationDescriptionName(), "collapseWithConflicts1-copy");

        // Launch copy layout
        editor.clickContextMenu(Messages.CopyFormatAction_text);
        // Open the second editor to paste in
        final SWTBotSiriusDiagramEditor editorForPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(),
                "collapseWithConflicts1-paste", DDiagram.class);
        // Check that the location of ports to paste is the same as other ports
        String errorMessage = "The copied {0} location should have the same location of the {1} current location in diagram to paste.";
        assertSameLocation(editor, "Port1", editorForPaste, "Port1", errorMessage);
        // Check that the location of port to paste includes one of the existing
        // other ports
        String errorMessage2 = "The copied {0} bounds should contains the location of the {1} in diagram to paste.";
        assertLocationIsIncludedInBounds(editorForPaste, "Port2", editor, "Port1", errorMessage2);
        // Launch paste layout
        editorForPaste.clickContextMenu(Messages.PasteLayoutAction_text);
        // Check that the location of pasted ports is near to the original
        // copied location. A delta of 11 is tolerated (because they will be
        // shifted under or above the existing ports).
        errorMessage = "The pasted {1} location should have a location near the copied {0} location.";
        assertNearLocation(editorForPaste, "Port1", editorForPaste, "Port2", errorMessage);

        SWTBotGefEditPart firstEditPart = editorForPaste.getEditPart("Port1", AbstractDiagramBorderNodeEditPart.class);
        Bounds b1 = (Bounds) ((Node) ((GraphicalEditPart) firstEditPart.part()).getModel()).getLayoutConstraint();
        SWTBotGefEditPart secondEditPart = editorForPaste.getEditPart("Port2", AbstractDiagramBorderNodeEditPart.class);
        Option<Bounds> b2 = new NodeQuery(((Node) ((GraphicalEditPart) secondEditPart.part()).getModel())).getExtendedBounds();

        assertJustUnder(b1, b2.get());
    }

    /**
     * This test check that the pasted location of collapsed bordered nodes is
     * correctly computed according to the existing expanded node in the paste
     * diagram that is in conflict with the copied layout.
     */
    public void testCopyPasteWithCollapseInCopy() {
        editor.close();
        SWTBotUtils.waitAllUiEvents();

        editor = openAndGetEditor(getRepresentationDescriptionName(), "collapseWithConflicts2-copy");

        // Launch copy layout
        editor.clickContextMenu(Messages.CopyFormatAction_text);
        // Open the second editor to paste in
        final SWTBotSiriusDiagramEditor editorForPaste = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationDescriptionName(),
                "collapseWithConflicts2-paste", DDiagram.class);
        // Check that the location of port to paste is included in one of the
        // existing other ports
        String errorMessage2 = "The copied {0} location should be contained in the bounds of the {1} in diagram to paste.";
        assertLocationIsIncludedInBounds(editor, "Port2", editorForPaste, "Port1", errorMessage2);
        // Launch paste layout
        editorForPaste.clickContextMenu(Messages.PasteLayoutAction_text);
        // Check that the location of pasted ports is near to the original
        // copied location. A delta of 11 is tolerated (because they will be
        // shifted under or above the existing ports).

        SWTBotGefEditPart firstEditPart = editorForPaste.getEditPart("Port1", AbstractDiagramBorderNodeEditPart.class);
        Bounds b1 = (Bounds) ((Node) ((GraphicalEditPart) firstEditPart.part()).getModel()).getLayoutConstraint();
        SWTBotGefEditPart secondEditPart = editorForPaste.getEditPart("Port2", AbstractDiagramBorderNodeEditPart.class);
        Option<Bounds> b2 = new NodeQuery(((Node) ((GraphicalEditPart) secondEditPart.part()).getModel())).getExtendedBounds();

        assertJustUnder(b2.get(), b1);
    }

    /**
     * Check that the location of the bordered node <code>name2</code> in the
     * editor <code>editor2</code> is near to the original copied location (the
     * bordered node with <code>name1</code> in the editor <code>editor1</code>
     * ). A delta of 11 is tolerated (because they will be shifted under or
     * above the existing ports).
     * 
     * @param editor1
     *            The first editor
     * @param name1
     *            The name of the bordered node, in the first editor, to compare
     * @param editor2
     *            The second editor
     * @param name2
     *            The name of the bordered node, in the second editor, to
     *            compare
     * @param message
     *            The error message in case of difference
     */
    protected void assertNearLocation(SWTBotSiriusDiagramEditor editor1, String name1, SWTBotSiriusDiagramEditor editor2, String name2, String message) {
        Point expected = editor1.getAbsoluteLocation(name1, AbstractDiagramBorderNodeEditPart.class);
        Point actual = editor2.getAbsoluteLocation(name2, AbstractDiagramBorderNodeEditPart.class);
        assertThat(MessageFormat.format(message, name1, name2), new Double(expected.getDistance(actual)), lessThanOrEqualTo(MAX_POSITION_DELTA));
    }

    /**
     * Check that the location of the bordered node <code>name1</code> in the
     * editor <code>editor1</code> is included in the bounds of the bordered
     * node with <code>name2</code> in the editor <code>editor2</code> ). A
     * delta of 11 is tolerated (because they will be shifted under or above the
     * existing ports).
     * 
     * @param editor1
     *            The first editor
     * @param name1
     *            The name of the bordered node, in the first editor, to compare
     * @param editor2
     *            The second editor
     * @param name2
     *            The name of the bordered node, in the second editor, to
     *            compare
     * @param message
     *            The error message in case of difference
     */
    protected void assertLocationIsIncludedInBounds(SWTBotSiriusDiagramEditor editor1, String name1, SWTBotSiriusDiagramEditor editor2, String name2, String message) {
        Point location = editor1.getAbsoluteLocation(name1, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart swtBotGefEditPart = editor2.getEditPart(name2, AbstractDiagramBorderNodeEditPart.class);
        Rectangle expectedIncludedBounds = editor2.getAbsoluteBounds(swtBotGefEditPart);
        assertTrue(MessageFormat.format(message, name2, name1), expectedIncludedBounds.contains(location));
    }

    /**
     * Check that the location of the first bounds is just under (one pixel) the
     * second bounds.
     * 
     * @param bounds1
     *            The first bounds (that should be just under the second one)
     * @param bounds2
     *            The second bounds
     */
    protected void assertJustUnder(Bounds bounds1, Bounds bounds2) {
        assertEquals("The GMF constraint (x axis) should be the same.", bounds2.getX(), bounds1.getX());
        assertEquals("The GMF constraint (y axis) of first bounds should be just under the second one.", bounds2.getY() + bounds2.getHeight() + 1, bounds1.getY());
    }
}
