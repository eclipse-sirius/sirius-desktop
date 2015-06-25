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
package org.eclipse.sirius.tests.swtbot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test on model like in the APP_TEMOIN.
 * 
 * @author smonnier
 */
public class ArrangeAllLinkedBorderedNodesLayoutStabilityAppTemoinTest extends AbstractArrangeAllTest implements PositionConstants {

    private static final String REPRESENTATION_INSTANCE_NAME_ENTITIES = "UseCase3 - root package entities";

    private static final String REPRESENTATION_INSTANCE_NAME_ENTITIES2 = "UseCase1 - root package entities2";

    private static final String REPRESENTATION_INSTANCE_NAME_ENTITIES_PINNED = "UseCase2 - root package entities2 pinned";

    private static final String REPRESENTATION_NAME_ENTITIES = "Entities";

    private static final String REPRESENTATION_NAME_ENTITIES2 = "Entities2";

    private static final String VIEWPOINT_NAME = "Test case for ticket #1957";

    private static final String MODEL = "APP_TEMOIN.ecore";

    private static final String SESSION_FILE = "APP_TEMOIN.aird";

    private static final String VSM_FILE = "tc1957.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/borderedNodes/";

    private static final String FILE_DIR = "/";

    private boolean ARE_PINNED_PORTS = false;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME_ENTITIES2, REPRESENTATION_INSTANCE_NAME_ENTITIES2, DDiagram.class);
        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();
    }

    @Override
    protected void tearDown() throws Exception {
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        super.tearDown();
    }

    /**
     * Normal arrange all with diagram like in APP_TEMOIN model.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderedNodesCase1() throws Exception {
        // activate the "Arrange Linked Bordered Nodes" action
        editor.clickContextMenu("Arrange All");

        validatePositions();
    }

    /**
     * Arrange all in zoom 200 with diagram like in APP_TEMOIN model.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderedNodesCase1Zoom200() throws Exception {
        editor.zoom(ZoomLevel.ZOOM_200);
        // activate the "Arrange Linked Bordered Nodes" action
        editor.clickContextMenu("Arrange All");
        validatePositions();
        editor.zoom(ZoomLevel.ZOOM_100);
    }

    /**
     * Arrange all in zoom 50 with diagram like in APP_TEMOIN model.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderedNodesCase1Zoom50() throws Exception {
        editor.zoom(ZoomLevel.ZOOM_50);
        // activate the "Arrange Linked Bordered Nodes" action
        editor.clickContextMenu("Arrange All");
        validatePositions();
        editor.zoom(ZoomLevel.ZOOM_100);
    }

    /**
     * Test arrange all with all bordered nodes pinned with diagram like in
     * APP_TEMOIN model.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderedNodesCase1Pinned() throws Exception {
        // Select another diagram
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME_ENTITIES2, REPRESENTATION_INSTANCE_NAME_ENTITIES_PINNED, DDiagram.class);
        // validate position before Arrange
        validatePositions(false);
        editor.clickContextMenu("Arrange All");
        // validate position after Arrange
        validatePositions(false);
        // activate the "Arrange Linked Bordered Nodes" action
        // editor.clickContextMenu("Arrange All");
    }

    /**
     * Normal arrange all with another diagram.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testArrangeLinkedBorderedNodesCase2() throws Exception {
        // Select another diagram
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME_ENTITIES, REPRESENTATION_INSTANCE_NAME_ENTITIES, DDiagram.class);

        // activate the "Arrange Linked Bordered Nodes" action
        editor.clickContextMenu("Arrange All");

        validatePositions();
    }

    /**
     * Validate the positions of all bordered nodes.
     */
    private void validatePositions() {
        // package Pakage1
        validatePositionOfPortOnContainer("Class13", "ref12");
        validatePositionOfPortOnContainer("Class13", "ref1");
        validatePositionOfPortOnContainer("Class13", "ref4");

        validatePositionOfPortOnContainer("Class11", "ref2");
        validatePositionOfPortOnContainer("Class11", "ref3");

        validatePositionOfPortOnContainer("Class12", "AAAAout");

        // Package4
        validatePositionOfPortOnContainer("Class41", "ref4");
        validatePositionOfPortOnContainer("Class41", "ref5");

        // Package Package2
        validatePositionOfPortOnContainer("Package2", "Package2");

        validatePositionOfPortOnContainer("Class22", "ref7");
        validatePositionOfPortOnContainer("Class22", "ref31");

        validatePositionOfPortOnContainer("Class21", "AAAAout");

        validatePositionOfPortOnContainer("Class24", "ref1");
        validatePositionOfPortOnContainer("Class24", "ref9");

        validatePositionOfPortOnContainer("Class23", "ref12");
        validatePositionOfPortOnContainer("Class23", "ref8");

        validatePositionOfPortOnContainer("Class25", "ref7");
        validatePositionOfPortOnContainer("Class25", "ref31");
        validatePositionOfPortOnContainer("Class25", "ref5");
        validatePositionOfPortOnContainer("Class25", "ref8");
        validatePositionOfPortOnContainer("Class25", "ref9");

        validatePositionOfPortOnContainer("Class22", "ref7");
        validatePositionOfPortOnContainer("Class22", "ref31");

        validatePositionOfPortOnContainer("Class28", "ref1");
        validatePositionOfPortOnContainer("Class28", "ref31");
        validatePositionOfPortOnContainer("Class28", "ref6");
        validatePositionOfPortOnContainer("Class28", "ref11");

        validatePositionOfPortOnContainer("Class26", "withoutType2");
        validatePositionOfPortOnContainer("Class26", "ref2");
        validatePositionOfPortOnContainer("Class26", "ref3");
        validatePositionOfPortOnContainer("Class26", "ref31");
        validatePositionOfPortOnContainer("Class26", "ref1");
        validatePositionOfPortOnContainer("Class26", "ref10");

        validatePositionOfPortOnContainer("Class27", "ref1");

        // Package3
        validatePositionOfPortOnContainer("Class32", "ref31");
        validatePositionOfPortOnContainer("Class32", "ref6");

        validatePositionOfPortOnContainer("Class31", "withoutType");
    }

    /**
     * @param pinPorts
     *            true is the port must be pin during the validation
     */
    private void validatePositions(boolean pinPorts) {
        // package Package1
        validatePositionOfPortOnContainer("Class13", "ref12", EAST, pinPorts);
        validatePositionOfPortOnContainer("Class13", "ref1", EAST, pinPorts);
        validatePositionOfPortOnContainer("Class13", "ref4", EAST, pinPorts);

        validatePositionOfPortOnContainer("Class11", "ref2", EAST, pinPorts);
        validatePositionOfPortOnContainer("Class11", "ref3", EAST, pinPorts);

        validatePositionOfPortOnContainer("Class12", "AAAAout", EAST, pinPorts);

        // Package Package2
        validatePositionOfPortOnContainer("Package2", "Package2", WEST, pinPorts);

        validatePositionOfPortOnContainer("Class22", "ref7", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class22", "ref31", EAST, pinPorts);

        validatePositionOfPortOnContainer("Class25", "ref7", NORTH, pinPorts);
        validatePositionOfPortOnContainer("Class25", "ref31", EAST, pinPorts);
        validatePositionOfPortOnContainer("Class25", "ref5", SOUTH, pinPorts);
        validatePositionOfPortOnContainer("Class25", "ref8", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class25", "ref9", WEST, pinPorts);

        validatePositionOfPortOnContainer("Class23", "ref12", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class23", "ref8", EAST, pinPorts);

        validatePositionOfPortOnContainer("Class24", "ref1", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class24", "ref9", EAST, pinPorts);

        validatePositionOfPortOnContainer("Class26", "withoutType2", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class26", "ref2", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class26", "ref3", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class26", "ref31", EAST, pinPorts);
        validatePositionOfPortOnContainer("Class26", "ref1", EAST, pinPorts);
        validatePositionOfPortOnContainer("Class26", "ref10", EAST, pinPorts);

        validatePositionOfPortOnContainer("Class27", "ref1", SOUTH, pinPorts);

        validatePositionOfPortOnContainer("Class28", "ref1", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class28", "ref31", NORTH, pinPorts);
        validatePositionOfPortOnContainer("Class28", "ref6", EAST, pinPorts);
        validatePositionOfPortOnContainer("Class28", "ref11", WEST, pinPorts);

        validatePositionOfPortOnContainer("Class29", "ref10", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class29", "ref11", EAST, pinPorts);

        validatePositionOfPortOnContainer("Class21", "AAAAout", WEST, pinPorts);

        // Package4
        validatePositionOfPortOnContainer("Class41", "ref4", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class41", "ref5", NORTH, pinPorts);

        // Package3
        validatePositionOfPortOnContainer("Class32", "ref31", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class32", "ref6", WEST, pinPorts);

        validatePositionOfPortOnContainer("Class31", "withoutType", WEST, pinPorts);

        validatePositionOfPortOnContainer("Class24", "ref1", WEST, pinPorts);
        validatePositionOfPortOnContainer("Class24", "ref9", EAST, pinPorts);
    }

    /**
     * Check that the port is arranged correctly.
     * 
     * @param containerName
     *            The container name
     * @param portName
     *            The port name
     */
    private void validatePositionOfPortOnContainer(String containerName, String portName) { // ,
        // int
        // position){

        SWTBotGefEditPart swtbotContainerEditPart = editor.getEditPart(containerName);

        assertNotNull("No container edit part found with the name \"" + containerName + "\"", swtbotContainerEditPart);

        EditPart containerSquareEP = swtbotContainerEditPart.part();

        assertTrue("The parent edit part of the container label is not a AbstractBorderedShapeEditPart", containerSquareEP.getParent() instanceof AbstractBorderedShapeEditPart);
        final AbstractBorderedShapeEditPart containerEP = (AbstractBorderedShapeEditPart) containerSquareEP.getParent();
        final AbstractDiagramBorderNodeEditPart portEP = findPortInContainer(containerEP, portName);

        assertNotNull("No port edit part found with the name \"" + portName + "\"", portEP);

        if (ARE_PINNED_PORTS) {
            pinDiagramElement((DDiagramElement) ((Node) portEP.getModel()).getElement());
        }

        boolean validateEdgeFromPortDoesNotCrossParentContainer = validateEdgeFromPortDoesNotCrossParentContainer(containerEP, portEP);
        assertTrue("The port " + portName + " has an edge that cross the parent container " + containerName, validateEdgeFromPortDoesNotCrossParentContainer);

        // boolean validatePositionOfPortOnContainer =
        // validatePositionOfPortOnContainer(containerEP, portEP, position);
        //
        // assertTrue("The port "+portName+" is not in the expected position relatively to the container "+containerName,
        // validatePositionOfPortOnContainer);
    }

    /**
     * Check that the port is on the expected side.
     * 
     * @param containerName
     *            The container name
     * @param portName
     *            The port name
     * @param position
     *            The expected side.
     * @param pinPorts
     *            true is the port must be pin during the validation
     */
    private void validatePositionOfPortOnContainer(String containerName, String portName, int position, boolean pinPorts) {

        SWTBotGefEditPart swtbotContainerEditPart = editor.getEditPart(containerName);

        assertNotNull("No container edit part found with this name", swtbotContainerEditPart);

        EditPart containerSquareEP = swtbotContainerEditPart.part();

        assertTrue("The parent edit part of the container label is not a AbstractBorderedShapeEditPart", containerSquareEP.getParent() instanceof AbstractBorderedShapeEditPart);
        final AbstractBorderedShapeEditPart containerEP = (AbstractBorderedShapeEditPart) containerSquareEP.getParent();
        final AbstractDiagramBorderNodeEditPart portEP = findPortInContainer(containerEP, portName);

        assertNotNull("No port edit part found with this name", portEP);

        if (pinPorts) {
            pinDiagramElement((DDiagramElement) ((Node) portEP.getModel()).getElement());
        }

        boolean validatePositionOfPortOnContainer = validatePositionOfPortOnContainer(containerEP, portEP, position);
        assertTrue("The port " + portName + " is not in the expected position relatively to the container " + containerName, validatePositionOfPortOnContainer);
    }

    private boolean validateEdgeFromPortDoesNotCrossParentContainer(final AbstractBorderedShapeEditPart containerEP, final AbstractDiagramBorderNodeEditPart portEP) {
        Rectangle containerBounds = containerEP.getFigure().getBounds();
        List<AbstractDiagramEdgeEditPart> edgesEP = listEdgesConnectedToPort(portEP);
        if (!edgesEP.isEmpty()) {
            for (AbstractDiagramEdgeEditPart edgeEP : edgesEP) {
                int north = findNorth(edgeEP);
                int south = findSouth(edgeEP);
                int west = findWest(edgeEP);
                int east = findEast(edgeEP);
                Rectangle edgeBounds = new Rectangle(north, west, south - north, east - west);
                if (edgeBounds.intersects(containerBounds))
                    return false;
            }
        }
        return true;
    }

    private int findNorth(final AbstractDiagramEdgeEditPart edgeEP) {
        int north = Integer.MAX_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            if (point.y < north)
                north = point.y;
        }
        return north;
    }

    private int findSouth(final AbstractDiagramEdgeEditPart edgeEP) {
        int south = Integer.MIN_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            if (point.y > south)
                south = point.y;
        }
        return south;
    }

    private int findWest(final AbstractDiagramEdgeEditPart edgeEP) {
        int west = Integer.MAX_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            if (point.x < west)
                west = point.x;
        }
        return west;
    }

    private int findEast(final AbstractDiagramEdgeEditPart edgeEP) {
        int west = Integer.MIN_VALUE;
        PointList points = edgeEP.getPolylineConnectionFigure().getPoints().getCopy();
        for (int i = 0; i < points.size(); i++) {
            Point point = points.getPoint(i);
            if (point.x > west)
                west = point.x;
        }
        return west;
    }

    private List<AbstractDiagramEdgeEditPart> listEdgesConnectedToPort(final AbstractDiagramBorderNodeEditPart portEP) {
        ArrayList<AbstractDiagramEdgeEditPart> linkedPorts = new ArrayList<AbstractDiagramEdgeEditPart>();
        Iterator<?> targetIterator = portEP.getTargetConnections().iterator();
        while (targetIterator.hasNext()) {
            Object object = (Object) targetIterator.next();
            if (object instanceof AbstractDiagramEdgeEditPart) {
                AbstractDiagramEdgeEditPart edgeEP = (AbstractDiagramEdgeEditPart) object;
                linkedPorts.add(edgeEP);
            }
        }

        Iterator<?> sourceIterator = portEP.getSourceConnections().iterator();
        while (sourceIterator.hasNext()) {
            Object object = (Object) sourceIterator.next();
            if (object instanceof AbstractDiagramEdgeEditPart) {
                AbstractDiagramEdgeEditPart edgeEP = (AbstractDiagramEdgeEditPart) object;
                linkedPorts.add(edgeEP);
            }
        }

        return linkedPorts;
    }

    private boolean validatePositionOfPortOnContainer(AbstractBorderedShapeEditPart containerEP, AbstractDiagramBorderNodeEditPart portEP, int position) {
        Rectangle boundsPort = portEP.getFigure().getBounds();
        Rectangle boundsContainer = containerEP.getFigure().getBounds();
        switch (position) {
        case NORTH:
            return Math.abs(boundsContainer.y - boundsPort.y) < IBorderItemOffsets.DEFAULT_OFFSET.height;

        case EAST:
            return Math.abs(boundsContainer.x + boundsContainer.width - boundsPort.x - boundsPort.width) < IBorderItemOffsets.DEFAULT_OFFSET.width;

        case SOUTH:
            return Math.abs(boundsContainer.y + boundsContainer.height - boundsPort.y - boundsPort.height) < IBorderItemOffsets.DEFAULT_OFFSET.height;

        case WEST:
            return Math.abs(boundsContainer.x - boundsPort.x) < IBorderItemOffsets.DEFAULT_OFFSET.width;

        default:
            fail("The position should be one among North/East/South/West.");
            break;
        }
        return false;
    }

    private AbstractDiagramBorderNodeEditPart findPortInContainer(AbstractBorderedShapeEditPart containerEP, String portName) {
        Iterator<?> iterator = containerEP.getChildren().iterator();
        while (iterator.hasNext()) {
            Object object = (Object) iterator.next();
            if (object instanceof AbstractDiagramBorderNodeEditPart) {
                AbstractDiagramBorderNodeEditPart portEP = (AbstractDiagramBorderNodeEditPart) object;
                Object portModel = portEP.getModel();
                if (portModel instanceof Node) {
                    Node portNode = (Node) portModel;
                    EObject portElement = portNode.getElement();
                    if (portElement instanceof DDiagramElement) {
                        DDiagramElement portDDiagramElement = (DDiagramElement) portElement;
                        Iterator<EObject> semanticElementsIterator = portDDiagramElement.getSemanticElements().iterator();
                        while (semanticElementsIterator.hasNext()) {
                            EObject eObject = (EObject) semanticElementsIterator.next();
                            if (eObject instanceof ENamedElement) {
                                ENamedElement ref = (ENamedElement) eObject;
                                if (ref.getName().equals(portName)) {
                                    return portEP;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}
