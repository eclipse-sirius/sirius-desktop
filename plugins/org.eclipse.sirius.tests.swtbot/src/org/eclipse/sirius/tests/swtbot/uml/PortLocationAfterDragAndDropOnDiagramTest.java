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
package org.eclipse.sirius.tests.swtbot.uml;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Class to test port location after Drag and Drop on the same container (See
 * VP-3472)
 * 
 * @author MVenisse
 */
public class PortLocationAfterDragAndDropOnDiagramTest extends AbstractUmlDragAndDropTest {

    /**
     * The four bordered nodes to drag and drop
     */
    private static final String PORT1_TO_DND = "Port1";

    private static final String PORT2_TO_DND = "Port2";

    private static final String PORT3_TO_DND = "Port3";

    private static final String PORT4_TO_DND = "Port4";

    private static final String COLLAPSED_PORT1_TO_DND = "collapsePort1";

    private static final String COLLAPSED_PORT2_TO_DND = "collapsePort2";

    private static final String COLLAPSED_PORT3_TO_DND = "collapsePort3";

    private static final String REPRESENTATION_NAME_TO_OPEN = "nodeWithBorderedNodes";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Component Diagram-DnDComponentFromModelExplorer";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return REPRESENTATION_NAME_TO_OPEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return REPRESENTATION_DESCRIPTION_NAME;
    }

    /**
     * This test selects the seven bordered nodes (standard "port1" to "port4"
     * and collapsed port from "collapsePort1" to "collapsePort3"), move them at
     * the same time vertically. <BR>
     * First move : +34 pixels on the y axe <BR>
     * Second move : -50 pixels on the y axe
     * 
     * This test check if the spaces between the ports don't change.
     * 
     * @throws Exception
     *             test exception
     */
    public void testPortsLocationsAfterDragAndDropOnSameContainer() throws Exception {

        // Get the ports edit parts
        SWTBotGefEditPart editPartPort1 = editor.getEditPart(PORT1_TO_DND, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart editPartPort2 = editor.getEditPart(PORT2_TO_DND, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart editPartPort3 = editor.getEditPart(PORT3_TO_DND, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart editPartPort4 = editor.getEditPart(PORT4_TO_DND, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart editPartCollapsedPort1 = editor.getEditPart(COLLAPSED_PORT1_TO_DND, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart editPartCollapsedPort2 = editor.getEditPart(COLLAPSED_PORT2_TO_DND, AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart editPartCollapsedPort3 = editor.getEditPart(COLLAPSED_PORT3_TO_DND, AbstractDiagramBorderNodeEditPart.class);

        // Compute the vertical spaces between each ports
        int beforeP1ToP2 = editor.getBounds(editPartPort2).y - editor.getBounds(editPartPort1).y;
        int beforeP2ToP3 = editor.getBounds(editPartPort3).y - editor.getBounds(editPartPort2).y;
        int beforeP3ToP4 = editor.getBounds(editPartPort4).y - editor.getBounds(editPartPort3).y;
        int beforeP4ToCP1 = editor.getBounds(editPartCollapsedPort1).y - editor.getBounds(editPartPort4).y;
        int beforeCP1ToCP2 = editor.getBounds(editPartCollapsedPort2).y - editor.getBounds(editPartCollapsedPort1).y;
        int beforeCP2ToCP3 = editor.getBounds(editPartCollapsedPort3).y - editor.getBounds(editPartCollapsedPort2).y;

        CheckEditPartMoved checkEditPartPort1Move = new CheckEditPartMoved(editPartPort1);

        // Select the seven ports at the same time
        Collection<SWTBotGefEditPart> editPartsToSelect = new ArrayList<SWTBotGefEditPart>();
        editPartsToSelect.add(editPartPort1);
        editPartsToSelect.add(editPartPort2);
        editPartsToSelect.add(editPartPort3);
        editPartsToSelect.add(editPartPort4);
        editPartsToSelect.add(editPartCollapsedPort1);
        editPartsToSelect.add(editPartCollapsedPort2);
        editPartsToSelect.add(editPartCollapsedPort3);
        editor.select(editPartsToSelect);

        bot.waitUntil(new CheckSelectedCondition(editor, COLLAPSED_PORT3_TO_DND, AbstractDiagramBorderNodeEditPart.class));

        // Drag and drop the seven port vertically (+34 pixels on the y axe)
        dragSelection(editPartPort1, 34);

        bot.waitUntil(checkEditPartPort1Move);

        // Check if the spaces before and after the drag and drop are the same
        assertSameVerticalSpace(PORT1_TO_DND, PORT2_TO_DND, editPartPort1, editPartPort2, beforeP1ToP2);
        assertSameVerticalSpace(PORT2_TO_DND, PORT3_TO_DND, editPartPort2, editPartPort3, beforeP2ToP3);
        assertSameVerticalSpace(PORT3_TO_DND, PORT4_TO_DND, editPartPort3, editPartPort4, beforeP3ToP4);
        assertSameVerticalSpace(PORT4_TO_DND, COLLAPSED_PORT1_TO_DND, editPartPort4, editPartCollapsedPort1, beforeP4ToCP1);
        assertSameVerticalSpace(COLLAPSED_PORT1_TO_DND, COLLAPSED_PORT2_TO_DND, editPartCollapsedPort1, editPartCollapsedPort2, beforeCP1ToCP2);
        assertSameVerticalSpace(COLLAPSED_PORT2_TO_DND, COLLAPSED_PORT3_TO_DND, editPartCollapsedPort2, editPartCollapsedPort3, beforeCP2ToCP3);

        checkEditPartPort1Move = new CheckEditPartMoved(editPartPort1);

        // Drag and drop the four port vertically (-50 pixels on the y axe)
        dragSelection(editPartPort1, -50);

        bot.waitUntil(checkEditPartPort1Move);

        // Check if the spaces before and after the drag and drop are the same
        assertSameVerticalSpace(PORT1_TO_DND, PORT2_TO_DND, editPartPort1, editPartPort2, beforeP1ToP2);
        assertSameVerticalSpace(PORT2_TO_DND, PORT3_TO_DND, editPartPort2, editPartPort3, beforeP2ToP3);
        assertSameVerticalSpace(PORT3_TO_DND, PORT4_TO_DND, editPartPort3, editPartPort4, beforeP3ToP4);
        assertSameVerticalSpace(PORT4_TO_DND, COLLAPSED_PORT1_TO_DND, editPartPort4, editPartCollapsedPort1, beforeP4ToCP1);
        assertSameVerticalSpace(COLLAPSED_PORT1_TO_DND, COLLAPSED_PORT2_TO_DND, editPartCollapsedPort1, editPartCollapsedPort2, beforeCP1ToCP2);
        assertSameVerticalSpace(COLLAPSED_PORT2_TO_DND, COLLAPSED_PORT3_TO_DND, editPartCollapsedPort2, editPartCollapsedPort3, beforeCP2ToCP3);
    }

    /**
     * Drag the selected elements.
     * 
     * @param refPart
     *            the reference part to compute the movement
     * @param shift
     *            the space before and after drag
     */
    private void dragSelection(SWTBotGefEditPart refPart, int shift) {
        Rectangle bounds = editor.getBounds(refPart);
        Point beginPoint = new Point(bounds.getCenter());
        Point endPoint = new Point(beginPoint.x, beginPoint.y + shift);
        editor.drag(beginPoint, endPoint);
    }

    /**
     * @param firstPortName
     *            The name of the first port (to display in error message in
     *            case of problem)
     * @param secondPortName
     *            The name of the second port (to display in error message in
     *            case of problem)
     * @param firstEditPartPort
     *            The edit part corresponding to the first port
     * @param secondEditPartPort
     *            The edit part corresponding to the second port
     * @param expectedSpace
     *            The expected space between first and second port
     */
    private void assertSameVerticalSpace(String firstPortName, String secondPortName, SWTBotGefEditPart firstEditPartPort, SWTBotGefEditPart secondEditPartPort, int expectedSpace) {
        int currentSpace = editor.getBounds(secondEditPartPort).y - editor.getBounds(firstEditPartPort).y;
        assertEquals("The vertical space between " + firstPortName + " and " + secondPortName + " is not the same after the move", expectedSpace, currentSpace);
    }

}
