/*******************************************************************************
 * Copyright (c) 2008-2019 THALES GLOBAL SERVICES and others.
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

import java.util.Optional;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Class test for the new feature "centered edges". see bug #437528#17
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class CenteredEdgesRepairTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/centeredEdgeRepair/";

    private static final String SEMANTIC_MODEL = "testRepair.ecore";

    private static final String REPRESENTATION_MODEL = "testRepair.aird";

    private static final String MODELER = "testRepair.odesign";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "testReconnect";

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL, REPRESENTATION_MODEL, MODELER);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATION_MODEL);
    }

    /**
     * Ensure that the repair action refresh the centering property on the
     * edgeStyle and edge bendpoints if the edge centering property has been
     * changed in the VSM.
     */
    public void testRepairMove() {
        UIResource sessionAirdResource = new UIResource(designerProject, REPRESENTATION_MODEL);
        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource, repairActionLabel);
        SWTBotUtils.waitProgressMonitorClose(OpenRepresentationsFileJob.JOB_LABEL);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        openDiagram(REPRESENTATION_NAME);
        SWTBotGefConnectionEditPart swtBotGefEditPart1 = (SWTBotGefConnectionEditPart) editor.getEditPart("edge1", DEdgeEditPart.class);
        SWTBotGefConnectionEditPart swtBotGefEditPart2 = (SWTBotGefConnectionEditPart) editor.getEditPart("edge2", DEdgeEditPart.class);
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart1, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart1, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedSrcAnchor(swtBotGefEditPart2, new PrecisionPoint(0.5, 0.5));
        assertEdgeHasExpectedTgtAnchor(swtBotGefEditPart2, new PrecisionPoint(0.5, 0.5));
    }

    /**
     * Open diagram.
     */
    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);

    }

    private void assertEdgeHasExpectedTgtAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor) {
        SWTBotGefEditPart targetSwtBotGefEditPart = swtBotGefConnectionEditPart.target();
        ConnectionAnchor connectionAnchor = ((NodeEditPart) targetSwtBotGefEditPart.part()).getTargetConnectionAnchor(swtBotGefConnectionEditPart.part());
        assertEquals("Wrong edge target anchor", "(" + expectedAnchor.preciseX() + "," + expectedAnchor.preciseY() + ")", ((SlidableAnchor) connectionAnchor).getTerminal());
        Connection connection = (Connection) swtBotGefConnectionEditPart.part().getFigure();
        PointList pointList = connection.getPoints();
        Point lineOrigin = pointList.getPoint(pointList.size() - 2);
        Point realTargetConnection = pointList.getPoint(pointList.size() - 1);

        Point expectedLineTerminus = getProportionalPoint(getAbsoluteBounds((IGraphicalEditPart) targetSwtBotGefEditPart.part()), expectedAnchor);

        Optional<Point> option = GraphicalHelper.getIntersection(lineOrigin, expectedLineTerminus, (IGraphicalEditPart) targetSwtBotGefEditPart.part(), false);
        if (option.isPresent()) {
            assertConnectionEndPointEquals("Wrong edge target connection", option.get(), realTargetConnection);
        }

    }

    private void assertEdgeHasExpectedSrcAnchor(ConnectionEditPart connectionPart, NodeEditPart sourceEditPart, PrecisionPoint expectedAnchor) {

        ConnectionAnchor connectionAnchor = sourceEditPart.getSourceConnectionAnchor(connectionPart);

        assertEquals("Wrong edge source anchor", "(" + expectedAnchor.preciseX() + "," + expectedAnchor.preciseY() + ")", ((SlidableAnchor) connectionAnchor).getTerminal());

        Connection connection = (Connection) connectionPart.getFigure();
        PointList pointList = connection.getPoints();
        Point lineOrigin = pointList.getPoint(1);
        Point realSourceConnection = pointList.getPoint(0);

        Point expectedLineTerminus = getProportionalPoint(getAbsoluteBounds((IGraphicalEditPart) sourceEditPart), expectedAnchor);

        Optional<Point> option = GraphicalHelper.getIntersection(lineOrigin, expectedLineTerminus, (IGraphicalEditPart) sourceEditPart, false);
        if (option.isPresent()) {
            assertConnectionEndPointEquals("Wrong edge source connection", option.get(), realSourceConnection);
        }

    }

    private void assertEdgeHasExpectedSrcAnchor(SWTBotGefConnectionEditPart swtBotGefConnectionEditPart, PrecisionPoint expectedAnchor) {

        assertEdgeHasExpectedSrcAnchor((ConnectionEditPart) swtBotGefConnectionEditPart.part(), (NodeEditPart) swtBotGefConnectionEditPart.source().part(), expectedAnchor);

    }

    /**
     * Assert that the actual point is equal to the expected one with +/- 1
     * tolerance.
     * 
     * @param msg
     * @param expected
     * @param actual
     */
    private void assertConnectionEndPointEquals(String msg, Point expected, Point actual) {
        assertTrue(msg, actual.x() <= (expected.x() + 1) && actual.x() >= expected.x() - 1);
        assertTrue(msg, actual.y() <= (expected.y() + 1) && actual.y() >= expected.y() - 1);
    }

    private Rectangle getAbsoluteBounds(IGraphicalEditPart part) {
        IFigure figure = part.getFigure();
        Rectangle r = figure.getBounds().getCopy();
        figure.getParent().translateToAbsolute(r);
        return r;
    }

    private Point getProportionalPoint(Rectangle bounds, PrecisionPoint proportions) {
        Point result = bounds.getTopLeft().getCopy();
        long xOffest = Math.round(bounds.width * proportions.preciseX());
        long yOffset = Math.round(bounds.height * proportions.preciseY());
        result.translate(new Dimension((int) xOffest, (int) yOffset));
        return result;
    }
}
