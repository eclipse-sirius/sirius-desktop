/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.geometry.PointAround;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.hamcrest.core.IsNot;
import org.junit.Assert;

/**
 * Common class for all Uml drag and drop tests.
 * 
 * @author dlecan
 */
public abstract class AbstractUmlDragAndDropTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * temporary delta.
     */
    // Eyh, developer, raise a warning to the project leader if this delta
    // exceeds 10 pixels
    protected static final int TEMPORARY_DELTA = 0;

    private static final String TEMP_PROJECT_NAME = "DragAndDropTestProject";

    /**
     * The name of the semantic file.
     */
    protected static final String UML2_MODEL = "uml2.uml";

    private static final String SESSION_FILE = "uml2.aird";

    private static final String VSM_FILE = "uml2.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private String oldFont;

    @Override
    protected String getProjectName() {
        return TEMP_PROJECT_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, UML2_MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        oldFont = changeDefaultFontName("Comic Sans MS");
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = openAndGetEditor(getRepresentationDescriptionName(), getRepresentationNameToOpen());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
    }

    /**
     * Get representation name to open in setup.
     * 
     * @return Representation name to open
     */
    protected abstract String getRepresentationNameToOpen();

    /**
     * Get representation description name where to find the representation to
     * open in setup.
     * 
     * @return Representation description name
     */
    protected abstract String getRepresentationDescriptionName();

    /**
     * Open the editor with <code>representationName</code> and
     * <code>representationDescriptionName</code>, and set the zoom to 100%
     * 
     * @param representationDescriptionName
     *            The representation description name to open
     * @param representationName
     *            The representation name to open
     * @return the opened editor.
     */
    protected SWTBotSiriusDiagramEditor openAndGetEditor(final String representationDescriptionName, final String representationName) {
        final SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationDescriptionName, representationName, DDiagram.class);
        editor.zoomDefault();
        editor.setSnapToGrid(false);
        return editor;
    }

    /**
     * Check position and size.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     * @param translation
     *            Translation
     * @param delta
     *            Delta
     */
    protected void checkNewCoordinates(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds, final Dimension translation, final int delta) {
        checkPositionChanged(elementNameToDnD, originalBounds, newBounds);
        checkNewSize(elementNameToDnD, originalBounds, newBounds);
        checkNewPosition(elementNameToDnD, originalBounds, newBounds, translation, delta);
    }

    /**
     * Check position and size.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     * @param translation
     *            Translation
     */
    protected void checkNewCoordinates(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds, final Dimension translation) {
        // delta = 1 because of int to double conversions, operations, ...
        checkNewCoordinates(elementNameToDnD, originalBounds, newBounds, translation, 1);
    }

    /**
     * Check size.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     */
    protected void checkNewSize(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds) {
        assertThat(elementNameToDnD + " size has changed", newBounds.getSize(), equalTo(originalBounds.getSize()));
    }

    /**
     * Check position has changed.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     */
    protected void checkPositionChanged(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds) {
        assertThat(elementNameToDnD + " position hasn't change", newBounds.getLocation(), not(equalTo(originalBounds.getLocation())));
    }

    /**
     * Check position.
     * 
     * @param elementNameToDnD
     *            Name of element to drag and drop.
     * @param originalBounds
     *            Original bounds of element.
     * @param newBounds
     *            New bounds of element.
     * @param translation
     *            Translation
     * @param delta
     *            Delta
     */
    protected void checkNewPosition(final String elementNameToDnD, final Rectangle originalBounds, final Rectangle newBounds, final Dimension translation, final int delta) {
        final int localDelta = TEMPORARY_DELTA;
        GraphicTestsSupportHelp.assertEquals(String.format("Translation of %s is wrong (delta : %s)", elementNameToDnD, localDelta), originalBounds.getLocation().translate(translation),
                newBounds.getLocation(), localDelta);
    }

    /**
     * Check constant gap between 2 containers.
     * 
     * @param elementName1ToDnD
     *            Element 1 to drag and drop.
     * @param originalTopLeftPoint1
     *            Original top left point of element 1
     * @param newTopLeftPoint1
     *            New top left point of element 1
     * @param elementName2ToDnD
     *            Element 2 to drag and drop.
     * @param originalTopLeftPoint2
     *            Original top left point of element 2
     * @param newTopLeftPoint2
     *            New top left point of element 2
     */
    protected void checkConstantGapBetween(final String elementName1ToDnD, final Point originalTopLeftPoint1, final Point newTopLeftPoint1, final String elementName2ToDnD,
            final Point originalTopLeftPoint2, final Point newTopLeftPoint2) {
        final double originalDistance = originalTopLeftPoint1.getDistance(originalTopLeftPoint2);
        final double newDistance = newTopLeftPoint1.getDistance(newTopLeftPoint2);

        assertEquals("Distance between " + elementName1ToDnD + " and " + elementName2ToDnD + "has changed", newDistance, originalDistance, 1);
    }

    /**
     * Get bounds of specified element.
     * 
     * @param label
     *            Element to find.
     * @return Bounds of element.
     */
    protected Rectangle getEditPartBounds(final String label) {
        return getEditPartBounds(getGraphicalEditPart(label));
    }

    /**
     * Get bounds of specified element.
     * 
     * @param part
     *            Element to find.
     * @return Bounds of element.
     */
    protected Rectangle getEditPartBounds(final GraphicalEditPart part) {
        final Rectangle originalRelativeBounds = part.getFigure().getBounds().getCopy();
        part.getFigure().translateToAbsolute(originalRelativeBounds);
        // Modified by reference, but it works on a copy
        return originalRelativeBounds;
    }

    /**
     * Get edit part of specified element.
     * 
     * @param label
     *            Element to find.
     * @return SWTBot Edit part
     */
    protected SWTBotGefEditPart getUmlEditPart(final String label) {
        final SWTBotGefEditPart editPart = editor.getEditPart(label);
        return editPart.parent();
    }

    /**
     * Get edit part of specified element.
     * 
     * @param label
     *            Element to find.
     * @return SWTBot Edit part
     */
    protected GraphicalEditPart getGraphicalEditPart(final String label) {
        return (GraphicalEditPart) getUmlEditPart(label).part();
    }

    /**
     * Check that the location of the bordered node with <code>name1</code> in
     * the editor <code>editor1</code> has the same location as the bordered
     * node <code>name2</code> in the editor <code>editor2</code>.
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
    protected void assertSameLocation(SWTBotSiriusDiagramEditor editor1, String name1, SWTBotSiriusDiagramEditor editor2, String name2, String message) {
        Point expected = editor1.getAbsoluteLocation(name1, AbstractDiagramBorderNodeEditPart.class);
        Point actual = editor2.getAbsoluteLocation(name2, AbstractDiagramBorderNodeEditPart.class);
        assertEquals(MessageFormat.format(message, name1, name2), expected, actual);
    }

    /**
     * Get copy of bendpoints position for the first edge found connected to the
     * edit part with <code>editPartName</code> as name.
     * 
     * @param editPartName
     *            name of the edit part to search
     * @param expectedEditPartType
     *            The type of the expected edit part
     * 
     * @return copy of bendpoints position or null if there is no edge
     */
    protected PointList getBendpoints(String editPartName, final Class<? extends EditPart> expectedEditPartType) {
        ConnectionEditPart connectionEditPart = getEdge(editPartName, expectedEditPartType);
        if (connectionEditPart == null)
            return null; // no edge defined
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        return ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
    }

    /**
     * Get the points list computed from GMF bendpoints according to source side
     * for the first edge found connected to the edit part with
     * <code>editPartName</code> as name.
     * 
     * @param editPartName
     *            name of the edit part to search
     * @param expectedEditPartType
     *            The type of the expected edit part
     * 
     * @return List of points or null if there is no edge
     */
    protected List<Point> getGMFBendpointsFromSource(String editPartName, final Class<? extends EditPart> expectedEditPartType) {
        ConnectionEditPart connectionEditPart = getEdge(editPartName, expectedEditPartType);
        if (connectionEditPart == null)
            return null; // no edge defined
        return GMFHelper.getPointsFromSource(connectionEditPart);
    }

    /**
     * Get the first edge found connected to the edit part with
     * <code>editPartName</code> as name.
     * 
     * @param editPartName
     *            name of the edit part to search
     * @param expectedEditPartType
     *            The type of the expected edit part
     * @return the edge connected to the edit part or null if there is no edge.
     */
    protected ConnectionEditPart getEdge(String editPartName, final Class<? extends EditPart> expectedEditPartType) {
        SWTBotGefEditPart editPart = editor.getEditPart(editPartName, expectedEditPartType);
        List<SWTBotGefConnectionEditPart> sourceConnections = editPart.sourceConnections();

        ConnectionEditPart connectionEditPart = null;
        if (!sourceConnections.isEmpty()) {
            connectionEditPart = sourceConnections.get(0).part();
        } else {
            List<SWTBotGefConnectionEditPart> targetConnections = editPart.targetConnections();

            if (!targetConnections.isEmpty()) {
                connectionEditPart = targetConnections.get(0).part();
            }
        }

        return connectionEditPart;
    }

    /**
     * Check the stability of the first edge found connected to the edit part
     * with <code>editPartName</code> as name.
     * 
     * @param editPartName
     *            name of the edit part to search
     * @param originalPoints
     *            original draw2d points
     * @param originalGmfPointsFromSource
     *            original GMF points computed from source
     */
    protected void checkEdgeStability(String editPartName, final Class<? extends EditPart> expectedEditPartType, PointList originalPoints, List<Point> originalGmfPointsFromSource) {
        ConnectionEditPart connectionEditPart = getEdge(editPartName, expectedEditPartType);

        // get new bendpoints
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList newPoints = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        Assert.assertEquals("The number of bendpoints should be the same", originalPoints.size(), newPoints.size());

        List<Point> newGMFBendpointsFromSource = GMFHelper.getPointsFromSource(connectionEditPart);

        // get routing style
        assertTrue(connectionEditPart.getModel() instanceof Edge);
        EObject element = ((Edge) connectionEditPart.getModel()).getElement();
        assertTrue(element instanceof DEdge);
        DEdge dedge = (DEdge) element;
        EdgeRouting edgeRouting = ((EdgeStyle) dedge.getStyle()).getRoutingStyle();

        // if edgeRouting == EdgeRouting.STRAIGHT then the first or the last
        // point has moved

        // if edgeRouting == EdgeRouting.MANHATTAN then the two first or
        // the two last points have moved

        Point originalFirstPoint = originalPoints.getFirstPoint();
        Point newFirstPoint = newPoints.getFirstPoint();
        if (originalFirstPoint.equals(newFirstPoint)) {
            // EdgeRouting.STRAIGHT: the last point has moved
            // EdgeRouting.MANHATTAN: the two last points have moved
            int end = edgeRouting == EdgeRouting.STRAIGHT_LITERAL ? originalPoints.size() - 1 : originalPoints.size() - 2;

            // unmoved points
            for (int i = 1; i < end; i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertEquals("The two points at index " + i + " should be equal", originalPoint, newPoint);
                Assert.assertEquals("The two GMF points at index " + i + " should be equal", originalGmfPointsFromSource.get(i), newGMFBendpointsFromSource.get(i));
                assertThat("The two GMF points at index " + i + " should be equal", newGMFBendpointsFromSource.get(i), PointAround.around(originalGmfPointsFromSource.get(i), 1));
            }

            // moved points
            for (int i = end; i < originalPoints.size(); i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertNotEquals("The two points at index " + i + " should be different", originalPoint, newPoint);
                assertThat("The two GMF points at index " + i + " should be different", newGMFBendpointsFromSource.get(i), IsNot.not(PointAround.around(originalGmfPointsFromSource.get(i), 1)));
            }
        } else {
            // EdgeRouting.STRAIGHT: the first point has moved
            // EdgeRouting.MANHATTAN: the two first points have moved
            int begin = edgeRouting == EdgeRouting.STRAIGHT_LITERAL ? 1 : 2;

            // moved points
            for (int i = 1; i < begin; i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertNotEquals("The two points at index " + i + " should be different", originalPoint, newPoint);
                assertThat("The two GMF points at index " + i + " should be different", newGMFBendpointsFromSource.get(i), IsNot.not(PointAround.around(originalGmfPointsFromSource.get(i), 1)));
            }

            // unmoved points
            for (int i = begin; i < originalPoints.size(); i++) {
                Point originalPoint = originalPoints.getPoint(i);
                Point newPoint = newPoints.getPoint(i);
                Assert.assertEquals("The two points at index " + i + " should be equal", originalPoint, newPoint);
                assertThat("The two GMF points at index " + i + " should be equal", newGMFBendpointsFromSource.get(i), PointAround.around(originalGmfPointsFromSource.get(i), 1));
            }
        }
        if (isEquals(originalPoints, originalGmfPointsFromSource) && !isEquals(newPoints, newGMFBendpointsFromSource)) {
            fail("The draw2d and GMF lists of points should be the same: \n" + toString(newPoints) + "\n" + toString(newGMFBendpointsFromSource));
        }
    }

    /**
     * @param newGMFBendpointsFromSource
     * @return
     */
    private String toString(List<Point> newGMFBendpointsFromSource) {
        StringBuffer result = new StringBuffer("[");
        for (int i = 0; i < newGMFBendpointsFromSource.size(); i++) {
            Point point = newGMFBendpointsFromSource.get(i);
            result.append("(").append(point.x).append(", ").append(point.y).append(")");
            if (i < newGMFBendpointsFromSource.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     * @param newPoints
     * @return
     */
    private String toString(PointList newPoints) {
        StringBuffer result = new StringBuffer("[");
        for (int i = 0; i < newPoints.size(); i++) {
            Point point = newPoints.getPoint(i);
            result.append("(").append(point.x).append(", ").append(point.y).append(")");
            if (i < newPoints.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     * Compare a list of draw2d point and a list of GMF points.
     * 
     * @param draw2dPoints
     *            List of draw2d points
     * @param gmfPoints
     *            List of GMF points
     * @return true if the lists are the same.
     */
    private boolean isEquals(PointList draw2dPoints, List<Point> gmfPoints) {
        boolean isEquals = true;
        if (draw2dPoints.size() != gmfPoints.size()) {
            isEquals = false;
        }
        for (int i = 0; i < draw2dPoints.size() && isEquals; i++) {
            Point d2dPoint = draw2dPoints.getPoint(i);
            Point gmfPoint = gmfPoints.get(i);
            if (!((d2dPoint.x == gmfPoint.x || d2dPoint.x == gmfPoint.x + 1) || (d2dPoint.y == gmfPoint.y || d2dPoint.y == gmfPoint.y + 1))) {
                isEquals = false;
            }
        }
        return isEquals;
    }
}
