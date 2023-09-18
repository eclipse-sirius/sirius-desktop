/*******************************************************************************
 * Copyright (c) 2016, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests the method
 * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
 * . The returned edit part can be either the edge one or the source and target
 * ones. It depends on the position of the mouse. If the mouse is over the node
 * expanded until 20px or over the minimum selection zone around a small node
 * and the edge box has a big enough selectable zone, then the node's part must
 * be return. Otherwise, the edge part must be returned.
 * 
 * The location given by {@link SWTBotSiriusDiagramEditor} are integer. The
 * method tested uses precise. So when testing the selection limits between edge
 * and nodes, it is possible to have a -1 or +1 regarding the case.
 * 
 * @author pguilet
 */
public class EdgeSelectionTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Number of pixels to remove or add from a mouse position to click on the
     * last pixel of a node with a computed expanded zone in the side the edge
     * is attached to it.
     */
    private static final int LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE = 7;

    /**
     * Number of pixels to remove or add from a mouse position to click on the
     * first pixel of the edge after the last pixel of a node with a computed
     * expanded zone.
     */
    private static final int LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE = 8;

    private static final String FILE_DIR = "/";

    private static final String DIAGRAM_INSTANCE_NAME = "diagram_3358";

    private static final String DIAGRAM_DESCRIPTION = "diagram_3358";

    private static final String MODEL_FILE = "3358.ecore";

    private static final String SESSION_FILE = "3358.aird";

    private static final String VSM_FILE = "3358.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeSelection/";

    private SWTBotSiriusDiagramEditor editor;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

    }

    @Override
    protected void tearDown() throws Exception {
        // Restore the default 100% zoom level
        if (editor != null) {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
        super.tearDown();
    }

    private void openDiagram(String descriptionName, String instanceName, ZoomLevel zoomLevel) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), descriptionName, instanceName, DDiagram.class);
        editor.zoom(zoomLevel);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed horizontally. The edge
     * has the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the last pixel of the expanded zone over the
     * edge that is attached to the source node on the left side. The returned
     * part must be the source node.
     */
    public void testSimpleCaseHorizontallyNodeSelectionEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C2");
        Point sourceNodePartLocation = editor.getLocation(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());
        editor.click(new Point(sourceNodePartLocation.x - LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE, sourceNodePartLocation.y));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed horizontally. The edge
     * has the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the first pixel after the last pixel of the
     * expanded node zone over the edge. The returned part must be the edge one.
     */
    public void testSimpleCaseHorizontallyEdgeSelectionEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C2");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Point sourceNodePartLocation = editor.getLocation(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(sourceNodePartLocation.x - LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE, sourceNodePartLocation.y));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed horizontally. The edge
     * has not the minimum size of 20px to be considered as selectable whereas
     * we have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the last pixel of the source node figure over
     * the edge attached to it on the left side. The returned part must be the
     * source node.
     */
    public void testSimpleCaseHorizontallyNodeSelectionEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C3");
        Point sourceNodePartLocation = editor.getLocation(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());
        // we add +1 because the absolute location has a number behind ','
        editor.click(new Point(sourceNodePartLocation.x + 1, sourceNodePartLocation.y + 1));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed horizontally. The edge
     * has not the minimum size of 20px to be considered as selectable whereas
     * we have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the first pixel after the last pixel of the
     * source node's left side. The returned part must be the edge one because
     * we don't take in consideration the expanded nodes size in this situation
     * to have a selectable edge.
     */
    public void testSimpleCaseHorizontallyEdgeSelectionEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C3");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Point sourceNodePartLocation = editor.getLocation(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(sourceNodePartLocation.x - 1, sourceNodePartLocation.y));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case style *-----* : where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed vertically. The edge
     * has the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the last pixel of the expanded zone over the
     * edge that is attached to the target node on the right side. The returned
     * part must be the target node.
     */
    public void testSimpleCaseHorizontallyTargetNodeSelectionEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C1");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        editor.click(new Point((targetNodePartLocation.x + targetNodePartLocation.width) + LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE, targetNodePartLocation.y));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed horizontally. The edge
     * has the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the first pixel after the last pixel of the
     * expanded target node zone over the edge on the right side of the node.
     * The returned part must be the edge one.
     */
    public void testSimpleCaseHorizontallyEdgeSelectionOnTargetNodeEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C1");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point((targetNodePartLocation.x + targetNodePartLocation.width) + LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE, targetNodePartLocation.y));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed horizontally. The edge
     * has not the minimum size of 20px to be considered as selectable whereas
     * we have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the last pixel of the target node figure over
     * the edge attached to it on the right side. The returned part must be the
     * target node.
     */
    public void testSimpleCaseHorizontallyTargetNodeSelectionEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C4");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        editor.click(new Point((targetNodePartLocation.x + targetNodePartLocation.width) - 1, targetNodePartLocation.y + 1));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the right. The edge is displayed horizontally. The edge
     * has not the minimum size of 20px to be considered as selectable whereas
     * we have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the first pixel after the last pixel of the
     * target node's right side. The returned part must be the edge one because
     * we don't take in consideration the expanded nodes size in this situation
     * to have a selectable edge.
     */
    public void testSimpleCaseHorizontallyEdgeSelectionOnTargetNodeEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C4");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point((targetNodePartLocation.x + targetNodePartLocation.width) + 1, targetNodePartLocation.y));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * the minimum size of 20px to be considered as selectable whereas we have
     * virtually expanded the node size from 5px to 20px to compute this area.
     * Zoom is 75%, edge is in a container with scroll and main screen is also
     * scrolled. We click on the last pixel of the expanded zone over the edge
     * that is attached to the source node on the bottom side. The returned part
     * must be the source node.
     */
    public void testSimpleCaseVerticallyNodeSelectionEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C6");
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());
        editor.click(new Point(sourceNodePartLocation.x, (sourceNodePartLocation.y + sourceNodePartLocation.height) + LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * the minimum size of 20px to be considered as selectable whereas we have
     * virtually expanded the node size from 5px to 20px to compute this area.
     * Zoom is 75%, edge is in a container with scroll and main screen is also
     * scrolled. We click on the first pixel after the last pixel of the
     * expanded node zone over the edge at the bottom side. The returned part
     * must be the edge one.
     */
    public void testSimpleCaseVerticallyEdgeSelectionEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C6");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(sourceNodePartLocation.x, (sourceNodePartLocation.y + sourceNodePartLocation.height) + LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * not the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the last pixel of the source node figure over
     * the edge attached to it on the bottom side. The returned part must be the
     * source node.
     */
    public void testSimpleCaseVerticallyNodeSelectionEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C6");
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());
        // we add +1 because the absolute location has a number behind ','
        editor.click(new Point(sourceNodePartLocation.x + 1, (sourceNodePartLocation.y + sourceNodePartLocation.height)));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * not the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the first pixel after the last pixel of the
     * source node's bottom side. The returned part must be the edge one because
     * we don't take in consideration the expanded nodes size in this situation
     * to have a selectable edge.
     */
    public void testSimpleCaseVerticallyEdgeSelectionEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C9");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(sourceNodePartLocation.x, (sourceNodePartLocation.y + sourceNodePartLocation.height) + 1));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * the minimum size of 20px to be considered as selectable whereas we have
     * virtually expanded the node size from 5px to 20px to compute this area.
     * Zoom is 75%, edge is in a container with scroll and main screen is also
     * scrolled. We click on the last pixel of the expanded zone over the edge
     * that is attached to the target node on the top side. The returned part
     * must be the target node.
     */
    public void testSimpleCaseVerticallyTargetNodeSelectionEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C7");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        editor.click(new Point(targetNodePartLocation.x, targetNodePartLocation.y - LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * the minimum size of 20px to be considered as selectable whereas we have
     * virtually expanded the node size from 5px to 20px to compute this area.
     * Zoom is 75%, edge is in a container with scroll and main screen is also
     * scrolled. We click on the first pixel after the last pixel of the
     * expanded target node zone over the edge on the top side of the node. The
     * returned part must be the edge one.
     */
    public void testSimpleCaseVerticallyEdgeSelectionOnTargetNodeEdgeSelectableZonePresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C7");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(targetNodePartLocation.x, targetNodePartLocation.y - LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * not the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the last pixel of the target node figure over
     * the edge attached to it on the top side. The returned part must be the
     * target node.
     */
    public void testSimpleCaseVerticallyTargetNodeSelectionEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C8");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        // we add +1 because the absolute location has a number behind ','
        editor.click(new Point(targetNodePartLocation.x + 1, targetNodePartLocation.y));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * Source node is on the top. The edge is displayed vertically. The edge has
     * not the minimum size of 20px to be considered as selectable whereas we
     * have virtually expanded the node size from 5px to 20px to compute this
     * area. Zoom is 75%, edge is in a container with scroll and main screen is
     * also scrolled. We click on the first pixel after the last pixel of the
     * target node's at top side position. The returned part must be the edge
     * one because we don't take in consideration the expanded nodes size in
     * this situation to have a selectable edge.
     */
    public void testSimpleCaseVerticallyEdgeSelectionOnTargetNodeEdgeSelectableZoneNotPresent() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C8");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(targetNodePartLocation.x, targetNodePartLocation.y - 1));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the right. Nodes are images of 5px. Source node
     * is on the top. The edge is displayed vertically. Zoom is 75%, edge is in
     * a container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the right position of the union box of
     * the two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the target node on the right side. The returned part must be
     * the target node.
     */
    public void testTargetNodeSelectionWithExtraSelectableZoneAtRightPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C13");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        editor.click(new Point(targetNodePartLocation.x + targetNodePartLocation.width + LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE, targetNodePartLocation.y));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the right. Nodes are images of 5px. Source node
     * is on the top. The edge is displayed vertically. Zoom is 75%, edge is in
     * a container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the right position of the union box of
     * the two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the source node on the right side. The returned part must be
     * the source node.
     */
    public void testSourceNodeSelectionWithExtraSelectableZoneAtRightPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C12");
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());

        editor.click(new Point(sourceNodePartLocation.x + sourceNodePartLocation.width + LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE, sourceNodePartLocation.y));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the right. Nodes are images of 5px. Source node
     * is on the top. The edge is displayed vertically. Zoom is 75%, edge is in
     * a container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the right position of the union box of
     * the two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the source node on the right side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToSourceNodeWithExtraSelectableZoneAtRightPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C12");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        // we add +1 because the absolute location has a number behind ','
        editor.click(new Point(sourceNodePartLocation.x + sourceNodePartLocation.width + LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE, sourceNodePartLocation.y));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the right. Nodes are images of 5px. Source node
     * is on the top. The edge is displayed vertically. Zoom is 75%, edge is in
     * a container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the right position of the union box of
     * the two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the target node on the right side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToTargetNodeWithExtraSelectableZoneAtRightPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C13");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(targetNodePartLocation.x + targetNodePartLocation.width + LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE, targetNodePartLocation.y));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the left. Nodes are images of 5px. Source node is
     * on the top. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the left position of the union box of
     * the two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the target node on the left side. The returned part must be
     * the target node.
     */
    public void testTargetNodeSelectionWithExtraSelectableZoneAtLeftPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C16");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        editor.click(new Point(targetNodePartLocation.x - LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE, targetNodePartLocation.y));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the left. Nodes are images of 5px. Source node is
     * on the top. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the left position of the union box of
     * the two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the source node on the left side. The returned part must be
     * the source node.
     */
    public void testSourceNodeSelectionWithExtraSelectableZoneAtLeftPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C17");
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());

        editor.click(new Point(sourceNodePartLocation.x - LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE, sourceNodePartLocation.y + 2));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the left. Nodes are images of 5px. Source node is
     * on the top. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the left position of the union box of
     * the two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the source node on the left side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToSourceNodeWithExtraSelectableZoneAtLeftPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C17");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(sourceNodePartLocation.x - LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE, sourceNodePartLocation.y));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is vertically aligned to the source one and the top of
     * the edge is oriented to the left. Nodes are images of 5px. Source node is
     * on the top. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the left position of the union box of
     * the two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the target node on the left side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToTargetNodeWithExtraSelectableZoneAtLeftPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C16");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(targetNodePartLocation.x - LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE, targetNodePartLocation.y - 1));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the top position of the union box of the
     * two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the target node on the top side. The returned part must be
     * the target node.
     */
    public void testTargetNodeSelectionWithExtraSelectableZoneAtTopPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C10");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        editor.click(new Point(targetNodePartLocation.x, targetNodePartLocation.y - LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the top position of the union box of the
     * two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the source node on the down side. The returned part must be
     * the source node.
     */
    public void testSourceNodeSelectionWithExtraSelectableZoneAtTopPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C11");
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());
        editor.click(new Point(sourceNodePartLocation.x, sourceNodePartLocation.y - LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the top position of the union box of the
     * two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the source node on the top side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToSourceNodeWithExtraSelectableZoneAtTopPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C11");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);

        editor.click(new Point(sourceNodePartLocation.x, sourceNodePartLocation.y - LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the top position of the union box of the
     * two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the target node on the down side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToTargetNodeWithExtraSelectableZoneAtTopPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C10");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(targetNodePartLocation.x, targetNodePartLocation.y - LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the bottom position of the union box of
     * the two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the target node on the bottom side. The returned part must be
     * the target node.
     */
    public void testTargetNodeSelectionWithExtraSelectableZoneAtBottomPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart targetNodePart = editor.getEditPart("C15");
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, targetNodePart.part().getParent());
        editor.click(new Point(targetNodePartLocation.x, targetNodePartLocation.y + targetNodePartLocation.height + LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the bottom position of the union box of
     * the two nodes.
     * 
     * We click on the last pixel of the expanded zone over the edge that is
     * attached to the source node on the bottom side. The returned part must be
     * the source node.
     */
    public void testSourceNodeSelectionWithExtraSelectableZoneAtBottomPosition() {

        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C14");
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());
        editor.click(new Point(sourceNodePartLocation.x, sourceNodePartLocation.y + sourceNodePartLocation.height + LENGTH_TO_REACH_LAST_PIXEL_OF_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the bottom position of the union box of
     * the two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the source node on the bottom side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToSourceNodeWithExtraSelectableZoneAtBottomPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C14");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Rectangle sourceNodePartLocation = editor.getBounds(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);

        editor.click(new Point(sourceNodePartLocation.x + 2, sourceNodePartLocation.y + sourceNodePartLocation.height + LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with the following case : ._|¨|_.
     * 
     * The target node is horizontally aligned to the source one and the top of
     * the edge is oriented to the top. Nodes are images of 5px. Source node is
     * on the right. The edge is displayed vertically. Zoom is 75%, edge is in a
     * container with scroll and main screen is also scrolled.
     * 
     * The edge has not the minimum size of 20px to be considered as selectable
     * whereas we have virtually expanded the node size from 5px to 20px to
     * compute this area.
     * 
     * But we have a selectable area at the bottom position of the union box of
     * the two nodes.
     * 
     * We click on the pixel after the last pixel of the expanded zone over the
     * edge that is attached to the target node on the bottom side. The returned
     * part must be the edge.
     */
    public void testEdgeSelectionNextToTargetNodeWithExtraSelectableZoneAtBottomPosition() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_75);

        SWTBotGefEditPart targetNodePart = editor.getEditPart("C15");
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) targetNodePart.part().getParent()).getTargetConnections().get(0);
        Rectangle targetNodePartLocation = editor.getBounds(targetNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(targetNodePartLocation.x, targetNodePartLocation.y + targetNodePartLocation.height + LENGTH_TO_REACH_FIRST_EDGE_PIXEL_AFTER_EXPANDED_NODE));
        bot.waitUntil(checkc1Selected);

    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * 
     * Source node is on the right. The edge is displayed horizontally.
     * 
     * The edge has the minimum size of 20px to be considered as selectable.
     * area. Edge is in a container with scroll and main screen is also
     * scrolled.
     * 
     * Zoom is 400% making the node image to have a size of 20px that means no
     * expanded zone is computed during selection.
     * 
     * We click on the first pixel on the left side of the zoomed source node.
     * 
     * The selecting element must be the source node.
     */
    public void testExpansionZoneWithZoomedImagesEdgeSelection() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_400);
        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C2");
        editor.reveal(sourceNodePart.part());
        Point sourceNodePartLocation = editor.getLocation(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, sourceNodePart.part().getParent());
        editor.click(new Point(sourceNodePartLocation.x + 1, sourceNodePartLocation.y + 1));
        bot.waitUntil(checkc1Selected);
    }

    /**
     * 
     * Tests the edge selection(
     * {@link AbstractDiagramEdgeEditPart#getTargetEditPart(org.eclipse.gef.Request)}
     * ) with a simple case : *----------* where the nodes are images of 5px.
     * 
     * Source node is on the right. The edge is displayed horizontally.
     * 
     * The edge has the minimum size of 20px to be considered as selectable.
     * area. Edge is in a container with scroll and main screen is also
     * scrolled.
     * 
     * Zoom is 400% making the node image to have a size of 20px that means no
     * expanded zone is computed during selection.
     * 
     * We click on the pixel after the last pixel of the zoomed source node (at
     * left).
     * 
     * The selecting element must be the edge.
     */
    public void testExpansionZoneWithZoomedImagesSourceNodeSelection() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_400);

        SWTBotGefEditPart sourceNodePart = editor.getEditPart("C2");
        editor.reveal(sourceNodePart.part());
        AbstractDiagramEdgeEditPart edgePart = (AbstractDiagramEdgeEditPart) ((DNode4EditPart) sourceNodePart.part().getParent()).getSourceConnections().get(0);
        Point sourceNodePartLocation = editor.getLocation(sourceNodePart);

        CheckSelectedCondition checkc1Selected = new CheckSelectedCondition(editor, edgePart);
        editor.click(new Point(sourceNodePartLocation.x - 1, sourceNodePartLocation.y + 1));
        bot.waitUntil(checkc1Selected);
    }

}
