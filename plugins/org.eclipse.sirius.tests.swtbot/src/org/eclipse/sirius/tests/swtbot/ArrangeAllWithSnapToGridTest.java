/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Tests for the automatic arrangement feature with snapToGrid.
 * 
 * @author jmallet
 */
public class ArrangeAllWithSnapToGridTest extends ArrangeAllTest {

    /**
     * Step used for the grid spacing.
     */
    private static final int GRID_STEP = 50;

    @Override
    protected void setUp() throws Exception {
        snapToGrid = true;
        changeDiagramUIPreference(IPreferenceConstants.PREF_RULER_UNITS, RulerProvider.UNIT_PIXELS);
        changeDiagramUIPreference(IPreferenceConstants.PREF_GRID_SPACING, GRID_STEP);
        super.setUp();

    }

    /**
     * Method used to test if Container node is align on the grid after arrangeAll.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testContainerSnapWithArrange() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession.newDiagramRepresentation("new Top/Bottom Container Ports And Edges", "Top/Bottom Container Ports And Edges").on(mainEPackage)
                .withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();
        SWTBotUtils.waitAllUiEvents();
        IGraphicalEditPart LF6 = (IGraphicalEditPart) editor.getEditPart("LF6", DNodeContainer2EditPart.class).part();
        checkLocationAlignOnGrid(LF6, "container LF6");
        IGraphicalEditPart LF7 = (IGraphicalEditPart) editor.getEditPart("LF7", DNodeContainer2EditPart.class).part();
        checkLocationAlignOnGrid(LF7, "container LF7");
    }

    /**
     * Method used to test if borderNode on Container is align on the grid after arrangeAll.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBorderNodeSnapWithArrange() throws Exception {
        final UIDiagramRepresentation diagramRepresentation = localSession.newDiagramRepresentation("new Top/Bottom Container Ports And Edges", "Top/Bottom Container Ports And Edges").on(mainEPackage)
                .withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();

        // check container of borderNode
        IGraphicalEditPart LF1 = (IGraphicalEditPart) editor.getEditPart("LF1", DNodeContainerEditPart.class).part();
        checkLocationAlignOnGrid(LF1, "container LF1");

        // check borderNodes
        checkBorderNodesLocationAligned(LF1);
    }

    /**
     * Check that BorderNode of a given container are aligned on the grid.
     * 
     * @param containerEP
     *            edit part container which contains borderNode.
     */
    private void checkBorderNodesLocationAligned(IGraphicalEditPart containerEP) {
        Iterator<?> iterator = containerEP.getChildren().iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof AbstractDiagramBorderNodeEditPart) {
                AbstractDiagramBorderNodeEditPart portEP = (AbstractDiagramBorderNodeEditPart) object;
                Object portModel = portEP.getModel();
                if (portModel instanceof Node) {
                    Node portNode = (Node) portModel;
                    EObject portElement = portNode.getElement();
                    if (portElement instanceof DDiagramElement) {
                        DDiagramElement portDDiagramElement = (DDiagramElement) portElement;
                        String portName = portDDiagramElement.getName();
                        checkLocationAlignOnGrid(portEP, "borderNode " + portName);
                    }
                }
            }
        }
    }

    /**
     * Check that a diagram element is aligned on the grid.
     * 
     * @param editPart
     *            edit part of the diagram element to check
     * @param elementNameToDisplay
     *            The name of the element displayed in case of error.
     */
    private void checkLocationAlignOnGrid(IGraphicalEditPart editPart, String elementNameToDisplay) {
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return editPart.getFigure().getBounds().x != 0 || editPart.getFigure().getBounds().y != 0;
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "The " + elementNameToDisplay + " coordinates are {0, 0}. It seems that the arrange all did not occur.";
            }
        });
        Point location = editPart.getFigure().getBounds().getTopLeft();
        editPart.getFigure().translateToAbsolute(location);
        boolean isLocationOK = (location.x % GRID_STEP) == 0 || (location.y % GRID_STEP) == 0;
        if (!isLocationOK) {
            IGraphicalEditPart parentPart = (IGraphicalEditPart) editPart.getParent();
            Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart);
            isLocationOK = (location.x == parentBounds.x || location.x == (parentBounds.x + parentBounds.width))
                    || (location.y == parentBounds.y || location.y == (parentBounds.y + parentBounds.height));
        }
        if (!isLocationOK) {
            fail("For " + elementNameToDisplay + ", the x or y coordinate of the top left corner should be on the grid (grid spacing = " + GRID_STEP + "), but was: " + location + ".");
        }
    }

}
