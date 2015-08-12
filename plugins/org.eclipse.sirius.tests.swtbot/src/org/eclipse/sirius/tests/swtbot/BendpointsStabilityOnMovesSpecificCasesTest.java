/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This class is complementary to {@link BendpointsStabilityOnMovesTest} but
 * with specific cases detected after.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class BendpointsStabilityOnMovesSpecificCasesTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PROPERTIES_VIEW_ID = "org.eclipse.ui.views.PropertySheet";

    private static final String DATA_UNIT_DIR = "data/unit/bendpointsStability/otherSpecificCases/";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DIAGRAM_DESCRIPTION_NAME = "Entities";

    boolean isOutlineViewOpened;

    boolean isPropertiesViewOpened;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        // Close outline & property view (to improve test performances)
        final IWorkbenchPage currentPage = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0];
        final IViewReference[] viewReferences = currentPage.getViewReferences();
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < viewReferences.length; i++) {
                    if ("org.eclipse.ui.views.ContentOutline".equals(viewReferences[i].getId())) {
                        isOutlineViewOpened = true;
                        currentPage.hideView(viewReferences[i]);
                    } else if (PROPERTIES_VIEW_ID.equals(viewReferences[i].getId())) {
                        isPropertiesViewOpened = true;
                        currentPage.hideView(viewReferences[i]);
                    }
                }
            }
        });
    }

    @Override
    protected void tearDown() throws Exception {
        if (isOutlineViewOpened) {
            designerViews.openOutlineView();
        }
        if (isPropertiesViewOpened) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    try {
                        PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0].showView(PROPERTIES_VIEW_ID);
                    } catch (PartInitException e) {
                        fail("Could not reopen property view during teardown : " + e.getMessage());
                    }
                }
            });
        }
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency.
     */
    public void testLastPointConsistency() {
        // Step 1: open the testing diagram editor
        SWTBotSiriusDiagramEditor diagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESCRIPTION_NAME, "rectilinearCase1", DSemanticDiagram.class,
                true, true);
        try {
            String nodeToMoveName = "C2";
            diagramEditor.reveal(nodeToMoveName);
            // Step 2: store the previous bendpoints
            SWTBotGefEditPart editPartToMove = diagramEditor.getEditPart(nodeToMoveName, IAbstractDiagramNodeEditPart.class);
            SWTBotGefConnectionEditPart connectionEditPart = editPartToMove.targetConnections().get(0);
            PointList previousPoints = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();

            // Step 3: Drag node
            Point moveDelta = new Point(-20, 50);
            Point initialLocation = diagramEditor.getBounds(editPartToMove).getCenter();
            Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);
            ICondition editPartMovedCondition = new CheckEditPartMoved(editPartToMove);
            diagramEditor.drag(initialLocation, targetLocation);
            bot.waitUntil(editPartMovedCondition);
            assertEquals("Drag as failed: selection should be the same before and after drag.", editPartToMove, diagramEditor.selectedEditParts().get(0));
            // Step 4: Check bendpoints
            compareActualBendpointsWithExpected(diagramEditor, connectionEditPart, previousPoints, moveDelta, false);
        } finally {
            diagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency.
     */
    public void testFirstPointConsistency() {
        // Step 1: open the testing diagram editor
        SWTBotSiriusDiagramEditor diagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESCRIPTION_NAME, "rectilinearCase1", DSemanticDiagram.class,
                true, true);
        try {
            String nodeToMoveName = "C1";
            diagramEditor.reveal(nodeToMoveName);
            // Step 2: store the previous bendpoints
            SWTBotGefEditPart editPartToMove = diagramEditor.getEditPart(nodeToMoveName, IAbstractDiagramNodeEditPart.class);
            SWTBotGefConnectionEditPart connectionEditPart = editPartToMove.sourceConnections().get(0);
            PointList previousPoints = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();

            // Step 3: Drag node
            Point moveDelta = new Point(20, 20);
            Point initialLocation = diagramEditor.getBounds(editPartToMove).getCenter();
            Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);
            ICondition editPartMovedCondition = new CheckEditPartMoved(editPartToMove);
            diagramEditor.drag(initialLocation, targetLocation);
            bot.waitUntil(editPartMovedCondition);
            assertEquals("Drag as failed: selection should be the same before and after drag.", editPartToMove, diagramEditor.selectedEditParts().get(0));
            // Step 4: Check bendpoints
            compareActualBendpointsWithExpected(diagramEditor, connectionEditPart, previousPoints, moveDelta, true);
        } finally {
            diagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    // Check the first or last benpoint of the connection.
    private void compareActualBendpointsWithExpected(SWTBotSiriusDiagramEditor diagramEditor, SWTBotGefConnectionEditPart connectionEditPart, PointList expectedBendPoints, Point moveDelta,
            boolean firstPoint) {
        PointList actualBendPoints = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();
        List<Point> newGMFBendpointsFromSource = GMFHelper.getPointsFromSource(connectionEditPart.part());
        Point expectedDraw2dPoint;
        Point actualDraw2dPoint;
        Point actualGmfPoint;
        if (firstPoint) {
            expectedDraw2dPoint = expectedBendPoints.getFirstPoint();
            actualDraw2dPoint = actualBendPoints.getFirstPoint();
            actualGmfPoint = newGMFBendpointsFromSource.get(0);
        } else {
            expectedDraw2dPoint = expectedBendPoints.getLastPoint();
            actualDraw2dPoint = actualBendPoints.getLastPoint();
            actualGmfPoint = newGMFBendpointsFromSource.get(newGMFBendpointsFromSource.size() - 1);
        }
        // The first (or last) bendpoint should have moved.
        String messagePrefix = "First";
        if (!firstPoint) {
            messagePrefix = "Last";
        }
        assertNotEquals(messagePrefix + " point should have moved", expectedDraw2dPoint, actualDraw2dPoint);
        assertEquals(messagePrefix + " point should have moved at the expected location", expectedDraw2dPoint.getTranslated(moveDelta), actualDraw2dPoint);
        // The draw2d and GMF point should be the same.
        assertEquals(messagePrefix + " draw2d and GMF points should be the same", actualDraw2dPoint, actualGmfPoint);
    }
}
