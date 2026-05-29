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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;

/**
 * Tests defined to ensure that Node are created at expected locations.
 * 
 * @author alagarde
 */
public class NodeCreationTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * The name to associate to the Node to Create.
     */
    private static final String NEW_NODE_NAME = "new Class";

    /**
     * The name of the Node Creation Tool to use within this test.
     */
    private static final String NODE_CREATION_TOOL_NAME = "Class";

    private static final String REPRESENTATION_INSTANCE_NAME = "vp-1174 test diagram";

    private static final String REPRESENTATION_NAME = "vp-1174Diagram";

    private static final String MODEL = "vp-1174.ecore";

    private static final String SESSION_FILE = "vp-1174.aird";

    private static final String ODESIGN_FILE = "vp-1174.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/tools/creation/node/";

    private static final String FILE_DIR = "/";

    private static final String PACKAGE_1_NAME = "P1";

    private static final String PACKAGE_2_NAME = "P2";

    private static final String PACKAGE_3_NAME = "P3";

    private static final String PACKAGE_4_NAME = "P4";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        closeOutline();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        editor.setSnapToGrid(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Restore the default zoom level
        editor.click(1, 1); // Set the focus on the diagram
        editor.zoom(ZoomLevel.ZOOM_100);
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        SWTBotUtils.waitAllUiEvents();
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Ensures that a node created in a Diagram (zoom level : 100%) has the
     * expected location.
     */
    public void testNodeCreationInDiagramWithoutScroll() {
        testNodeCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Ensures that a node created in a Diagram (zoom level : 50%) has the
     * expected location.
     */
    public void testNodeCreationInDiagramWithoutScrollAndChangeZoom() {
        testNodeCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Ensures that a node created in a Diagram has the expected location.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     */
    private void testNodeCreationInDiagramWithoutScroll(ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        SWTBotUtils.waitAllUiEvents();
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        SWTBotUtils.waitAllUiEvents();
        // Get the insertion location for the Node to create
        Point location = new Point(2, 2);

        createNode(location.getScaled(zoomLevel.getAmount()).x, location.getScaled(zoomLevel.getAmount()).y);
        // Check the location of the created Node
        assertNodeAtLocation(NEW_NODE_NAME, location);
    }

    /**
     * Ensures that a node created in a Diagram with Scroll (zoom level : 100%)
     * has the expected location.
     */
    public void testNodeCreationInDiagramWithScroll() {
        testNodeCreationInDiagramWithScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Ensures that a node created in a Diagram with Scroll (zoom level : 50%)
     * has the expected location.
     */
    public void testNodeCreationInDiagramWithScrollAndChangeZoom() {
        testNodeCreationInDiagramWithScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Ensures that a node created in a Diagram (with Scroll) has the expected
     * location.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     */
    private void testNodeCreationInDiagramWithScroll(ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        // Reveal p2 (to scroll in the diagram)
        editor.select(editor.getSelectableEditPart(PACKAGE_3_NAME));
        editor.reveal(editor.getEditPart(PACKAGE_3_NAME).part());
        // Get the location of p2 package (relative the part visible on the
        // screen)
        Point p2Location = editor.getLocation(PACKAGE_3_NAME, DNodeContainerEditPart.class);
        // Get the absolute location of p2 package from origin (0, 0)
        Point p2AbsoluteLocation = editor.getAbsoluteLocation(PACKAGE_3_NAME, DNodeContainerEditPart.class);
        // Get the insertion location for the Node (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(-2, -2);
        Point location = p2Location.getTranslated(delta.getScaled(zoomLevel.getAmount()));

        createNode(location.x, location.y);
        // Check the location of the node (with the absolute coordinate)
        assertNodeAtLocation(NEW_NODE_NAME, p2AbsoluteLocation.getTranslated(delta));
    }

    /**
     * Ensures that a Node created inside a container (without Scroll) has the
     * expected position.
     * 
     */
    public void testNodeCreationInContainerWithoutScroll() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_100, PACKAGE_1_NAME);
    }

    /**
     * Ensures that a Node created inside a container (without Scroll, zoom :
     * 50%) has the expected position.
     * 
     */
    public void testNodeCreationInContainerWithoutScrollAndChangeZoom() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_50, PACKAGE_1_NAME);
    }

    /**
     * Ensures that a Node created inside a container (which location implies to
     * scroll on the diagram) has the expected position.
     */
    public void testNodeCreationInContainerWithScrollInDiagram() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_100, PACKAGE_4_NAME);
    }

    /**
     * Ensures that a Node created inside a container (which location implies to
     * scroll on the diagram) has the expected position.
     */
    public void testNodeCreationInContainerWithScrollInDiagramAndChangeZoom() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_50, PACKAGE_4_NAME);
    }

    /**
     * Creates a new Node inside the container with the given name, and check
     * that it is created at the expected position.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param packageToRevealName
     *            the name of the package to reveal before inserting the node.
     */
    private void testNodeCreationInContainer(ZoomLevel zoomLevel, String packageToRevealName) {
        testNodeCreationInContainer(zoomLevel, packageToRevealName, NEW_NODE_NAME);
    }

    /**
     * Ensures that a Node created inside a container (this container having
     * been reduced by user and therefore having a scroll bar) has the expected
     * position.
     */
    public void testNodeCreationInContainerWithScrollInContainer() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_100, PACKAGE_2_NAME, NEW_NODE_NAME);
    }

    /**
     * Ensures that a Node created inside a container (this container having
     * been reduced by user and therefore having a scroll bar) has the expected
     * position (zoom : 50%).
     */
    public void testNodeCreationInContainerWithScrollInContainerAndChangeZoom() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_50, PACKAGE_2_NAME, NEW_NODE_NAME);
    }

    /**
     * Ensures that a Node created inside a container (this container having
     * been reduced by user and therefore having a scroll bar), which location
     * implies to scroll on the diagram, has the expected position.
     */
    public void testNodeCreationInContainerWithScrollInContainerAndDiagram() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_100, PACKAGE_3_NAME, NEW_NODE_NAME);
    }

    /**
     * Ensures that a Node created inside a container (this container having
     * been reduced by user and therefore having a scroll bar), which location
     * implies to scroll on the diagram, has the expected position (zoom : 50%).
     */
    public void testNodeCreationInContainerWithScrollInContainerAndDiagramAndChangeZoom() {
        testNodeCreationInContainer(ZoomLevel.ZOOM_50, PACKAGE_3_NAME, NEW_NODE_NAME);
    }

    /**
     * Creates a new Node inside the container with the given name, and check
     * that it is created at the expected position.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param packageToRevealName
     *            the name of the package to reveal before inserting the node
     * @param newClassName
     *            the expected name of the newly created Class
     */
    private void testNodeCreationInContainer(ZoomLevel zoomLevel, String packageNameToReveal, String newClassName) {
        editor.zoom(zoomLevel);
        // Reveal the package (and eventually scroll in the diagram)
        editor.reveal(packageNameToReveal);
        // Get the location of the package (relative the part visible on the
        // screen)
        Point packageLocation = editor.getLocation(packageNameToReveal, DNodeContainerEditPart.class);
        // Get the absolute location of the package from origin (0, 0)
        Point packageAbsoluteLocation = editor.getAbsoluteLocation(packageNameToReveal, DNodeContainerEditPart.class);
        // Get the insertion location for the node (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(32, 32);
        Point location = packageLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));
        createNode(location.x, location.y);
        // Check the location of the node (with the absolute coordinate)
        assertNodeAtLocation(newClassName, packageAbsoluteLocation.getTranslated(delta));
    }

    /**
     * Create a new node using the defined Node Creation tool, at the given
     * position.
     * 
     * @param xOfNodeToCreate
     *            position of the node to create
     * @param yOfNodeToCreate
     *            position of the node to create
     */
    private void createNode(int xOfNodeToCreate, int yOfNodeToCreate) {
        // Select the Class tool and
        editor.activateTool(NODE_CREATION_TOOL_NAME);
        // Click in the editor (the coordinates use to
        // click is with the zoom level) -> Node is created
        editor.click(xOfNodeToCreate, yOfNodeToCreate);
    }

    /**
     * Check that the created Node is near the expected location. We don't check
     * precisely the location because the zoom can have round effect on the
     * location.
     * 
     * @param nodeLabel
     *            the name of the Node to check
     * @param expectedLocation
     *            the expected absolute position of the created Node within the
     *            graphical viewer
     */
    private void assertNodeAtLocation(String nodeLabel, Point expectedLocation) {
        Point nodeLocation = editor.getAbsoluteLocation(nodeLabel, NodeEditPart.class);
        assertEquals("The Node has been created at wrong location.", adaptLocation(expectedLocation), nodeLocation);
    }

    /**
     * Possibility to adapt the expected location according to some parameters
     * (snap to grid, ...).
     * 
     * @param expectedLocation
     *            The initial expected location
     * @return The adapted location
     */
    protected Point adaptLocation(Point expectedLocation) {
        return expectedLocation;
    }
}
