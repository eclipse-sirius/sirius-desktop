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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Lists;

/**
 * Zoom tests for Entities diagram of ecore modeler.
 * 
 * @author dlecan
 */
public class EntitiesDiagramZoomTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String ZOOM_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/design/zoom.ecore";

    private static final String ZOOM_REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/design/zoom.aird";

    protected static final String FIRST_CLASS_NAME = "new EClass 1";

    protected static final int AFTER_ZOOM_AUTORIZED_DELTA = 2;

    protected static final int THEORICAL_LOCATION_AUTORIZED_DELTA = 3;

    private DDiagram diagram;

    private IEditorPart editorPart;

    private ZoomManager zoomManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(ZOOM_SEMANTIC_MODEL_PATH, MODELER_PATH, ZOOM_REPRESENTATIONS_PATH);
        diagram = (DDiagram) getRepresentations(EcoreModeler.ENTITIES_DESC_NAME).toArray()[0];

        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        zoomManager = (ZoomManager) ((DiagramEditor) editorPart).getDiagramEditPart().getViewer().getProperty(ZoomManager.class.toString());
    }

    /**
     * Test case. It checks that source point location of a specific edge does
     * not change after zoom in operation.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testZoomAndCheckEdgePosition() throws Exception {
        EPackage root = (EPackage) semanticModel;
        EClass clazz1 = (EClass) root.getEClassifier(FIRST_CLASS_NAME);

        assertNotNull("The semantic model is not valid.", clazz1);
        EdgeTarget dDiagClazz1 = (EdgeTarget) getFirstDiagramElement(diagram, clazz1);
        EList<DEdge> incomingEdges = dDiagClazz1.getIncomingEdges();

        DEdge dEdge = incomingEdges.iterator().next();

        /* various zoom in and out */
        zoomInAndCheck(dEdge);
        zoomInAndCheck(dEdge);
        zoomInAndCheck(dEdge);
        zoomOutAndCheck(dEdge);
        zoomOutAndCheck(dEdge);
        zoomOutAndCheck(dEdge);
        zoomInAndCheck(dEdge);
        zoomOutAndCheck(dEdge);

    }

    public void testZoomToPageIsAvailable() throws Exception {
        double zoom = getZoom(2);
        checkZoomLevelAvailable("Page");
        zoomManager.setZoomAsText("Page");
        assertEquals(zoom, zoomManager.getZoom(), 0.01);
    }

    public void testZoomToWidthIsAvailable() throws Exception {
        double zoom = getZoom(0);
        checkZoomLevelAvailable("Width");
        zoomManager.setZoomAsText("Width");
        assertEquals(zoom, zoomManager.getZoom(), 0.01);
    }

    public void testZoomToHeightIsAvailable() throws Exception {
        double zoom = getZoom(1);
        checkZoomLevelAvailable("Height");
        zoomManager.setZoomAsText("Height");
        assertEquals(zoom, zoomManager.getZoom(), 0.01);
    }

    /**
     * Provides the expected zoom value according to the given type.
     * 
     * @param type
     *            0 for get the expected zoom value for a zoom to width, 1 for a
     *            zoom to height and 2 for a zoom to page.
     * @return the expected zoom value.
     */
    private double getZoom(int type) {
        IGraphicalEditPart graphicalEditPart = getEditPart(diagram);
        IFigure figure = graphicalEditPart.getFigure();
        Dimension size = figure.getMinimumSize();
        Dimension available = zoomManager.getViewport().getClientArea().getSize();
        double zoomY = Math.min((double) available.height / (double) size.height, zoomManager.getMaxZoom());
        double zoomX = Math.min((double) available.width / (double) size.width, zoomManager.getMaxZoom());
        if (type == 0)
            return zoomX;
        if (type == 1)
            return zoomY;
        return Math.min(zoomX, zoomY);
    }

    private void checkZoomLevelAvailable(String zoomLevel) {
        List<String> levels = Lists.newArrayList(zoomManager.getZoomLevelsAsText());
        assertTrue("Zoom level '" + zoomLevel + "' is not available.", levels.contains(zoomLevel));
    }

    private void zoomInAndCheck(DEdge dEdge) {
        zoomAndCheck(dEdge, true);
    }

    private void zoomOutAndCheck(DEdge dEdge) {
        zoomAndCheck(dEdge, false);
    }

    private void zoomAndCheck(DEdge dEdge, boolean in) {
        Point locationBeforeZoom = getSourcePoint(dEdge);
        Point theoricalLocation = getEdgeAnchorTheoricalLocation(dEdge.getSourceNode());

        assertEquals("location should be the following ", theoricalLocation.x, locationBeforeZoom.x, THEORICAL_LOCATION_AUTORIZED_DELTA);
        assertEquals("location should be the following ", theoricalLocation.y, locationBeforeZoom.y, THEORICAL_LOCATION_AUTORIZED_DELTA);

        if (in)
            zoomManager.zoomIn();
        else
            zoomManager.zoomOut();

        Point locationAfterZoom = getSourcePoint(dEdge);
        assertEquals("Source location shouldn't have changed after zoom (x coordinate)", locationBeforeZoom.x, locationAfterZoom.x, AFTER_ZOOM_AUTORIZED_DELTA);
        assertEquals("Source location shouldn't have changed after zoom (y coordinate)", locationBeforeZoom.y, locationAfterZoom.y, AFTER_ZOOM_AUTORIZED_DELTA);
    }

    private Point getSourcePoint(final DEdge edge) {
        IGraphicalEditPart edgeEditPart = getEditPart(edge, editorPart);
        ViewEdgeFigure figure = (ViewEdgeFigure) edgeEditPart.getFigure();
        return figure.getStart();
    }

    private Point getEdgeAnchorTheoricalLocation(final EdgeTarget edgeTarget) {
        IGraphicalEditPart edgeEditPart = getEditPart((DDiagramElement) edgeTarget, editorPart);
        Point location = edgeEditPart.getFigure().getBounds().getLocation();
        Point center = edgeEditPart.getFigure().getBounds().getCenter();
        return new Point(center.x, location.y);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = null;
        editorPart = null;
        zoomManager = null;

        super.tearDown();
    }
}
