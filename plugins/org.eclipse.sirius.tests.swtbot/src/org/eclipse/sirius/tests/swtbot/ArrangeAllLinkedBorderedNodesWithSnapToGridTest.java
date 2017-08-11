/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Class test used to check if bordedNode are aligned on the grid by using "Linked Border Nodes" action.
 * 
 * @author jmallet
 */
public class ArrangeAllLinkedBorderedNodesWithSnapToGridTest extends ArrangeAllLinkedBorderedNodesLayoutStabilityTest {

    @Override
    protected void setUp() throws Exception {
        snapToGrid = true;
        super.setUp();

    }

    /**
     * Check that the port is arranged correctly.
     * 
     * @param containerName
     *            The container name
     * @param portName
     *            The port name
     * @param pinBorderedNodes
     *            true if the port must be pin during the validation of it
     */
    @Override
    protected void validatePositionOfPortOnContainer(String containerName, String portName, boolean pinBorderedNodes) { // ,
        super.validatePositionOfPortOnContainer(containerName, portName, pinBorderedNodes);

        SWTBotGefEditPart swtbotContainerEditPart = editor.getEditPart(containerName);
        assertNotNull("No container edit part found with this name", swtbotContainerEditPart);
        EditPart containerSquareEP = swtbotContainerEditPart.part();
        final AbstractBorderedShapeEditPart containerEP = (AbstractBorderedShapeEditPart) containerSquareEP.getParent();
        final AbstractDiagramBorderNodeEditPart portEP = findPortInContainer(containerEP, portName);
        checkBorderNodeAlignOnGrid(portEP, "port " + portName + " of the container " + containerName);
    }

    private void checkBorderNodeAlignOnGrid(AbstractDiagramBorderNodeEditPart portEP, String labelToDisplay) {
        if (portEP == null) {
            throw new IllegalArgumentException("The " + labelToDisplay + " must not be null.");
        }
        List<AbstractDiagramEdgeEditPart> edgesEP = listEdgesConnectedToPort(portEP);
        if (!edgesEP.isEmpty()) {
            boolean isLocationOK = false;
            Point location = GraphicalHelper.getAbsoluteBoundsIn100Percent(portEP, true).getLocation();
            isLocationOK = (location.x % GRID_STEP) == 0 || (location.y % GRID_STEP) == 0;
            if (!isLocationOK) {
                IGraphicalEditPart parentPart = (IGraphicalEditPart) portEP.getParent();
                Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart);
                isLocationOK = (location.x == parentBounds.x || location.x == (parentBounds.x + parentBounds.width))
                        || (location.y == parentBounds.y || location.y == (parentBounds.y + parentBounds.height));
            }
            if (!isLocationOK) {
                fail("The " + labelToDisplay + ": " + location + ", is not aligned on the grid (grid spacing = " + GRID_STEP + ").");
            }
        }
    }

}
