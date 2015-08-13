/*******************************************************************************
 * Copyright (c) 2015 Obeo
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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test first point or last point location of edges on figure with
 * {@link org.eclipse.sirius.diagram.ui.tools.internal.figure.AlphaBasedSlidableImageAnchor}
 * . There locations should not be on the bounding box but on the figure itself
 * (non transparent area).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class EdgeOnFigureWithAlphaAnchorTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/edgeOnFigureWithAlphaAnchor/";

    private static final String MODELER_RESOURCE_NAME = "alphaAnchor.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "alphaAnchor.ecore";

    private static final String SESSION_RESOURCE_NAME = "alphaAnchor.aird";

    private static final String SVG_ACTOR_NAME = "actor.svg";

    private static final String SVG_TRANSPARENT_SQUARE_NAME = "transparentSquare.svg";

    private static final String SVG_USE_CASE_NAME = "use_case.svg";

    private static final String PNG_COW_NAME = "cow.png";

    private static final String PNG_CIRCLES_NAME = "twoCircles.png";

    private static final String PNG_CIRCLE_WITH_BLANK_NAME = "circleWithBlank.png";

    private static final String REPRESENTATION_INSTANCE_NAME = "new MyDiagram";

    private static final String REPRESENTATION_NAME = "MyDiagram";

    private static final String ECLASS_TRIANGLE = "triangle";

    private static final String ECLASS_TRANSPARENT_SQUARE = "transparentSquare";

    private static final String ECLASS_NOTE = "note";

    private static final String ECLASS_ACTOR = "actor";

    private static final String ECLASS_USE_CASE = "useCase";

    private static final String ECLASS_SQUARE = "square";

    private static final String ECLASS_ELLIPSE = "C2";

    private static final String ECLASS_COW = "cow";

    private static final String ECLASS_CIRCLES = "circles";

    private static final String ECLASS_CIRCLE_WITH_BLANK = "circleWithBlank";

    private static final String ECLASS_A = "a";

    private static final String EREFERENCE_TO_NOTE = "toNote";

    private static final String EREFERENCE_TO_NOTE2 = "toNote2";

    private static final String EREFERENCE_TO_TRANSPARENT = "toTransparent";

    private static final String EREFERENCE_USES = "uses";

    private static final String EREFERENCE_COW_USES = "cowUses";

    private static final String EREFERENCE_CIRCLES_USES = "circlesUses";

    private static final String EREFERENCE_TO_C2 = "toC2";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME, SVG_ACTOR_NAME, SVG_TRANSPARENT_SQUARE_NAME, SVG_USE_CASE_NAME,
                PNG_COW_NAME, PNG_CIRCLES_NAME, PNG_CIRCLE_WITH_BLANK_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class, true);
    }

    /**
     * Test that the extremity is not on the bounding box of the figure but on
     * the figure itself.
     */
    public void testExtremityLocationOfExistingEdges() {
        checkEdgeExtremitiesLocation(EREFERENCE_TO_TRANSPARENT, ECLASS_TRIANGLE, ECLASS_TRANSPARENT_SQUARE, false, true);
        checkEdgeExtremitiesLocation(EREFERENCE_TO_NOTE, ECLASS_TRIANGLE, ECLASS_NOTE, false, true);
        checkEdgeExtremitiesLocation(EREFERENCE_USES, ECLASS_ACTOR, ECLASS_USE_CASE, false, false);
        checkEdgeExtremitiesLocation(EREFERENCE_TO_C2, ECLASS_SQUARE, ECLASS_ELLIPSE, true, true);
        checkEdgeExtremitiesLocation(EREFERENCE_COW_USES, ECLASS_COW, ECLASS_USE_CASE, false, false);
        checkEdgeExtremitiesLocation(EREFERENCE_CIRCLES_USES, ECLASS_CIRCLES, ECLASS_USE_CASE, false, false);
        checkEdgeExtremitiesLocation(EREFERENCE_TO_NOTE2, ECLASS_CIRCLE_WITH_BLANK, ECLASS_NOTE, false, true);
    }

    /**
     * Test drag edge source to a figure with alpha.
     */
    public void testDragSource() {
        SWTBotGefEditPart referenceEditPartBot = editor.getEditPart(EREFERENCE_TO_C2, AbstractConnectionEditPart.class);
        PointList newEReference1PointList = getPointList((AbstractConnectionEditPart) referenceEditPartBot.part());

        // Point to move
        Point originalPoint = newEReference1PointList.getFirstPoint();
        // Target point
        Rectangle newSourceBounds = editor.getBounds(editor.getEditPart(ECLASS_ACTOR, AbstractDiagramNodeEditPart.class));
        Point targetPoint = newSourceBounds.getCenter();

        // Select the edge and move it
        editor.select(referenceEditPartBot);
        editor.drag(originalPoint, targetPoint);

        // Check that the edge has been moved
        referenceEditPartBot = editor.getEditPart(EREFERENCE_TO_NOTE, AbstractConnectionEditPart.class);
        assertNotEquals("The first point of edge should be different", originalPoint, getPointList((AbstractConnectionEditPart) referenceEditPartBot.part()).getFirstPoint());
        // Check edge extremities
        checkEdgeExtremitiesLocation(EREFERENCE_TO_C2, ECLASS_ACTOR, ECLASS_ELLIPSE, false, true);
    }

    /**
     * Test drag edge target to figure with alpha.
     */
    public void testDragTarget() {
        SWTBotGefEditPart referenceEditPartBot = editor.getEditPart(EREFERENCE_TO_NOTE, AbstractConnectionEditPart.class);
        PointList newEReference1PointList = getPointList((AbstractConnectionEditPart) referenceEditPartBot.part());

        // Point to move
        Point originalPoint = newEReference1PointList.getLastPoint();
        // Target point
        Rectangle newSourceBounds = editor.getBounds(editor.getEditPart(ECLASS_ACTOR, AbstractDiagramNodeEditPart.class));
        Point targetPoint = newSourceBounds.getCenter();

        // Select the edge and move it
        editor.select(referenceEditPartBot);
        editor.drag(originalPoint, targetPoint);

        // Check that the edge has been moved
        referenceEditPartBot = editor.getEditPart(EREFERENCE_TO_NOTE, AbstractConnectionEditPart.class);
        assertNotEquals("The last point of edge should be different", originalPoint, getPointList((AbstractConnectionEditPart) referenceEditPartBot.part()).getLastPoint());
        // Check edge extremities
        checkEdgeExtremitiesLocation(EREFERENCE_TO_NOTE, ECLASS_TRIANGLE, ECLASS_ACTOR, false, false);
    }

    /**
     * Test oblique edge creation.
     */
    public void testCreateObliqueEdge() {
        String newReferenceName = "newReference";
        createEdge(ECLASS_ACTOR, ECLASS_USE_CASE, "Reference", newReferenceName);
        // Check edge extremities of this new edge
        checkEdgeExtremitiesLocation(newReferenceName, ECLASS_ACTOR, ECLASS_USE_CASE, false, false);
    }

    /**
     * Test rectilinear edge creation.
     */
    public void testCreateRectlinearEdge() {
        String newReferenceName = "rectilinear";
        createEdge(ECLASS_ACTOR, ECLASS_USE_CASE, "ReferenceRectilinear", newReferenceName);
        // Check edge extremities of this new edge
        checkEdgeExtremitiesLocation(newReferenceName, ECLASS_ACTOR, ECLASS_USE_CASE, false, false);
    }

    /**
     * Test oblique edge creation on node with border image (to check that the
     * border image is not considered to compute the anchor of the node).
     */
    public void testCreateEdgeOnNodeWithBorderImage() {
        String newReferenceName = "newReference";
        createEdge(ECLASS_A, ECLASS_SQUARE, "Reference", newReferenceName);
        // Check edge extremities of this new edge
        checkEdgeExtremitiesLocation(newReferenceName, ECLASS_A, ECLASS_SQUARE, true, true);
    }

    /**
     * Test drag edge source from a figure with alpha to another one. This test
     * is not enabled because of known bug that does not directly concerns this
     * issue (bugzilla 461200).
     */
    public void _testDragSourceFail() {
        SWTBotGefEditPart referenceEditPartBot = editor.getEditPart(EREFERENCE_TO_NOTE, AbstractConnectionEditPart.class);
        PointList newEReference1PointList = getPointList((AbstractConnectionEditPart) referenceEditPartBot.part());

        // Point to move
        Point originalPoint = newEReference1PointList.getFirstPoint();
        // Target point
        Rectangle newSourceBounds = editor.getBounds(editor.getEditPart(ECLASS_ACTOR, AbstractDiagramNodeEditPart.class));
        Point targetPoint = newSourceBounds.getCenter().getTranslated(-50, 0);

        // Select the edge and move it
        editor.select(referenceEditPartBot);
        editor.drag(originalPoint, targetPoint);

        // Check that the edge has been moved
        referenceEditPartBot = editor.getEditPart(EREFERENCE_TO_NOTE, AbstractConnectionEditPart.class);
        assertNotEquals("The first point of edge should be different", originalPoint, getPointList((AbstractConnectionEditPart) referenceEditPartBot.part()).getFirstPoint());
        // Check edge extremities
        // TODO: Bug: Even if the target point is not the center (see above
        // getTranslated method), the center is finally used
        checkEdgeExtremitiesLocation(EREFERENCE_TO_NOTE, ECLASS_ACTOR, ECLASS_NOTE, false, true);
    }

    /**
     * Test move source of an edge on the same source figure. This test is not
     * enabled because it does not currently work with SWTBot. The move with
     * SWTBOT does not provide feedback and so does not make change.
     */
    public void _testMoveSourceOnFigureWithAlpha() {
        SWTBotGefEditPart referenceEditPartBot = editor.getEditPart(EREFERENCE_TO_NOTE, AbstractConnectionEditPart.class);
        PointList newEReference1PointList = getPointList((AbstractConnectionEditPart) referenceEditPartBot.part());

        // Select the edge
        editor.select(referenceEditPartBot);

        // Move the source bendpoint
        Point originalPoint = newEReference1PointList.getFirstPoint();
        editor.drag(originalPoint, originalPoint.x + 20, originalPoint.y - 20);

        // Check that the edge has been moved
        assertNotEquals("The first point of edge should be different", originalPoint, getPointList((AbstractConnectionEditPart) referenceEditPartBot.part()).getFirstPoint());
        // Check edge extremities
        checkEdgeExtremitiesLocation(EREFERENCE_TO_NOTE, ECLASS_TRIANGLE, ECLASS_NOTE, false, true);
    }

    /**
     * Test move target of an edge on the same target figure. This test is not
     * enabled because it does not currently work with SWTBot. The move with
     * SWTBOT does not provide feedback and so does not make change.
     */
    public void _testMoveTargetOnFigureWithAlpha() {
        SWTBotGefEditPart referenceEditPartBot = editor.getEditPart(EREFERENCE_USES, AbstractConnectionEditPart.class);
        PointList newEReference1PointList = getPointList((AbstractConnectionEditPart) referenceEditPartBot.part());

        // Select the edge
        editor.select(referenceEditPartBot);

        // Move the target bendpoint
        Point originalPoint = newEReference1PointList.getLastPoint();
        editor.drag(originalPoint, originalPoint.x + 70, originalPoint.y);

        // Check that the edge has been moved
        assertNotEquals("The last point of edge should be different", originalPoint, getPointList((AbstractConnectionEditPart) referenceEditPartBot.part()).getLastPoint());
        // Check edge extremities
        checkEdgeExtremitiesLocation(EREFERENCE_USES, ECLASS_ACTOR, ECLASS_USE_CASE, false, false);
    }

    private void checkEdgeExtremitiesLocation(String referenceName, String sourceNodeName, String targetNodeName, boolean onBoundingBoxSourceExpected, boolean onBoundingBoxTargetExpected) {
        SWTBotGefEditPart refEdgeEditPartBot = editor.getEditPart(referenceName, AbstractConnectionEditPart.class);
        PointList refPointList = getPointList((AbstractConnectionEditPart) refEdgeEditPartBot.part());

        SWTBotGefEditPart sourceEditPartBot = editor.getEditPart(sourceNodeName, AbstractDiagramNodeEditPart.class);
        Rectangle sourceBounds = editor.getBounds(sourceEditPartBot);

        SWTBotGefEditPart targetEditPartBot = editor.getEditPart(targetNodeName, AbstractDiagramNodeEditPart.class);
        Rectangle targetBounds = editor.getBounds(targetEditPartBot);

        checkEdgeExtremityLocation(sourceBounds, refPointList.getFirstPoint(), onBoundingBoxSourceExpected, "Starting", referenceName);
        checkEdgeExtremityLocation(targetBounds, refPointList.getLastPoint(), onBoundingBoxTargetExpected, "Ending", referenceName);
    }

    private void checkEdgeExtremityLocation(Rectangle bounds, Point extremity, boolean onBoundingBoxExpected, String messagePrefix, String referenceName) {
        PointList boundingBoxLine = PointListUtilities.createPointsFromRect(bounds);
        boolean extremityIsOnBoundingBox = boundingBoxLine.polylineContainsPoint(extremity.x(), extremity.y(), 0);
        assertTrue(getBoundingBoxMessage(messagePrefix, referenceName, onBoundingBoxExpected),
                (onBoundingBoxExpected && extremityIsOnBoundingBox) || (!onBoundingBoxExpected && !extremityIsOnBoundingBox));

        if (!onBoundingBoxExpected) {
            assertTrue(getInsideMessage(messagePrefix, referenceName), bounds.contains(extremity));
        }
    }

    private String getInsideMessage(String prefix, String referenceName) {
        return prefix + " point of \"" + referenceName + "\" should be inside the figure";
    }

    private String getBoundingBoxMessage(String prefix, String referenceName, boolean onBoundingBoxExpected) {
        if (onBoundingBoxExpected) {
            return prefix + " point of \"" + referenceName + "\" should be on the bounding box of the figure";
        } else {
            return prefix + " point of \"" + referenceName + "\" should not be on the bounding box of the figure";
        }
    }

    private PointList getPointList(AbstractConnectionEditPart abstractDiagramEdgeEditPart) {
        PointList pointList = null;
        Connection connectionFigure = abstractDiagramEdgeEditPart.getConnectionFigure();
        pointList = connectionFigure.getPoints();
        return pointList;
    }

    private void createEdge(String sourceNodeName, String targetNodeName, String toolName, String newReferenceName) {
        SWTBotGefEditPart sourceEditPartBot = editor.getEditPart(sourceNodeName, AbstractDiagramNodeEditPart.class);
        Rectangle sourceBounds = editor.getBounds(sourceEditPartBot);
        SWTBotGefEditPart targetEditPartBot = editor.getEditPart(targetNodeName, AbstractDiagramNodeEditPart.class);
        Rectangle targetBounds = editor.getBounds(targetEditPartBot);

        // Create the bracketEdge on bracketEdge
        editor.activateTool(toolName);
        Point firstClick = sourceBounds.getCenter();
        Point lastClick = targetBounds.getCenter();
        editor.click(firstClick);
        editor.click(lastClick);
    }

    @Override
    protected void tearDown() throws Exception {
        editor.click(0, 0);
        editor.close();
        super.tearDown();
    }
}
