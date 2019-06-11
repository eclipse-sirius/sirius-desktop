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
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;

/**
 * Tests defined to ensure that DNodeList are created at expected locations.
 * 
 * @author alagarde
 */
public class DNodeListCreationTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * The name to associate to the DNodeList to Create.
     */
    private static final String NEW_NODE_NAME = "NewEClass1";

    /**
     * 
     * The name to associate to the DNodeList to Create if a nodelist has
     * already been created.
     */
    private static final String NEW_NODE_NAME_IF_EXISTS = "NewEClass2";

    /**
     * The name of the DNodeList Creation Tool to use within this test.
     */
    private static final String NODE_CREATION_TOOL_NAME = "Class";

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    // We use the semantic model and session defined for NoteCreationTest,
    // as use-cases are really close
    private static final String MODEL = "2083.ecore";

    private static final String SESSION_FILE = "2083.aird";

    private static final String DATA_UNIT_DIR = "data/unit/tools/creation/note/";

    private static final String FILE_DIR = "/";

    private static final String P1_PACKAGE_NAME = "p1";

    private static final String P2_PACKAGE_NAME = "p2";

    private static final String P3_PACKAGE_NAME = "p3";

    private static final String P4_PACKAGE_NAME = "p4";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
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

        // Disable snap to grid
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
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Ensures that a nodeList created in a Diagram (zoom level : 100%) has the
     * expected location.
     */
    public void testDNodeListCreationInDiagramWithoutScroll() {
        testDNodeListCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Ensures that a nodeList created in a Diagram (zoom level : 50%) has the
     * expected location.
     */
    public void testDNodeListCreationInDiagramWithoutScrollAndChangeZoom() {
        testDNodeListCreationInDiagramWithoutScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Ensures that a nodeList created in a Diagram has the expected location.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     */
    private void testDNodeListCreationInDiagramWithoutScroll(ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
        SWTBotUtils.waitAllUiEvents();
        // Get the insertion location for the DNodeList to create
        Point location = new Point(2, 2);
        createDNodeList(location.getScaled(zoomLevel.getAmount()).x, location.getScaled(zoomLevel.getAmount()).y);
        // Check the location of the created DNodeList
        assertDNodeListAtLocation(NEW_NODE_NAME, location);
    }

    /**
     * Ensures that a nodeList created in a Diagram with Scroll (zoom level :
     * 100%) has the expected location.
     */
    public void testDNodeListCreationInDiagramWithScroll() {
        testDNodeListCreationInDiagramWithScroll(ZoomLevel.ZOOM_100);
    }

    /**
     * Ensures that a nodeList created in a Diagram with Scroll (zoom level :
     * 50%) has the expected location.
     */
    public void testDNodeListCreationInDiagramWithScrollAndChangeZoom() {
        testDNodeListCreationInDiagramWithScroll(ZoomLevel.ZOOM_50);
    }

    /**
     * Ensures that a nodeList created in a Diagram (with Scroll) has the
     * expected location.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     */
    private void testDNodeListCreationInDiagramWithScroll(ZoomLevel zoomLevel) {
        editor.zoom(zoomLevel);
        // Reveal p2 (to scroll in the diagram)
        editor.select(editor.getSelectableEditPart(P2_PACKAGE_NAME));
        editor.reveal(editor.getEditPart(P2_PACKAGE_NAME).part());
        // Get the location of p2 package (relative the part visible on the
        // screen)
        Point p2Location = editor.getLocation(P2_PACKAGE_NAME, NodeEditPart.class);
        // Get the absolute location of p2 package from origin (0, 0)
        Point p2AbsoluteLocation = editor.getAbsoluteLocation(P2_PACKAGE_NAME, NodeEditPart.class);
        // Get the insertion location for the DNodeList (use the relative
        // coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(-4, -4);
        Point location = p2Location.getTranslated(delta.getScaled(zoomLevel.getAmount()));

        createDNodeList(location.x, location.y);
        // Check the location of the dNodeList (with the absolute coordinate)
        assertDNodeListAtLocation(NEW_NODE_NAME, p2AbsoluteLocation.getTranslated(delta));
    }

    /**
     * Ensures that a DNodeList created inside a container (without Scroll) has
     * the expected position.
     * 
     */
    public void testDNodeListCreationInContainerWithoutScroll() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_100, "p1");
    }

    /**
     * Ensures that a DNodeList created inside a container (without Scroll, zoom
     * : 50%) has the expected position.
     * 
     */
    public void testDNodeListCreationInContainerWithoutScrollAndChangeZoom() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_50, P1_PACKAGE_NAME);
    }

    /**
     * Ensures that a DNodeList created inside a container (which location
     * implies to scroll on the diagram) has the expected position.
     */
    public void testDNodeListCreationInContainerWithScrollInDiagram() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_100, P2_PACKAGE_NAME);
    }

    /**
     * Ensures that a DNodeList created inside a container (which location
     * implies to scroll on the diagram) has the expected position (zoom : 50%).
     */
    public void testDNodeListCreationInContainerWithScrollInDiagramAndChangeZoom() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_50, P2_PACKAGE_NAME, NEW_NODE_NAME_IF_EXISTS);
    }

    /**
     * Creates a new DNodeList inside the container with the given name, and
     * check that it is created at the expected position.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param packageToRevealName
     *            the name of the package to reveal before inserting the note.
     */
    private void testDNodeListCreationInContainer(ZoomLevel zoomLevel, String packageToRevealName) {
        testDNodeListCreationInContainer(zoomLevel, packageToRevealName, NEW_NODE_NAME);
    }

    /**
     * Ensures that a DNodeList created inside a container (this container
     * having been reduced by user and therefore having a scroll bar) has the
     * expected position.
     */
    public void testDNodeListCreationInContainerWithScrollInContainer() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_100, P3_PACKAGE_NAME, NEW_NODE_NAME_IF_EXISTS);
    }

    /**
     * Ensures that a DNodeList created inside a container (this container
     * having been reduced by user and therefore having a scroll bar) has the
     * expected position (zoom : 50%).
     */
    public void testNoteCreationInContainerWithScrollInContainerAndChangeZoom() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_50, P3_PACKAGE_NAME, NEW_NODE_NAME_IF_EXISTS);
    }

    /**
     * Ensures that a DNodeList created inside a container (this container
     * having been reduced by user and therefore having a scroll bar), which
     * location implies to scroll on the diagram, has the expected position.
     */
    public void testNoteCreationInContainerWithScrollInContainerAndDiagram() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_100, P4_PACKAGE_NAME, NEW_NODE_NAME);
    }

    /**
     * Ensures that a DNodeList created inside a container (this container
     * having been reduced by user and therefore having a scroll bar), which
     * location implies to scroll on the diagram, has the expected position
     * (zoom : 50%).
     */
    public void testNoteCreationInContainerWithScrollInContainerAndDiagramAndChangeZoom() {
        testDNodeListCreationInContainer(ZoomLevel.ZOOM_50, P4_PACKAGE_NAME, NEW_NODE_NAME_IF_EXISTS);
    }

    /**
     * Creates a new DNodeList inside the container with the given name, and
     * check that it is created at the expected position.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param packageToRevealName
     *            the name of the package to reveal before inserting the note
     * @param newClassName
     *            the expected name of the newly created Class
     */
    private void testDNodeListCreationInContainer(ZoomLevel zoomLevel, String packageNameToReveal, String newClassName) {
        editor.zoom(zoomLevel);
        // Reveal the package (and eventually scroll in the diagram)
        editor.reveal(packageNameToReveal);
        // Get the location of the package (relative the part visible on the
        // screen)
        Point packageLocation = editor.getLocation(packageNameToReveal, NodeEditPart.class);
        // Get the absolute location of the package from origin (0, 0)
        Point packageAbsoluteLocation = editor.getAbsoluteLocation(packageNameToReveal, NodeEditPart.class);
        // Get the insertion location for the note (use the relative coordinate,
        // that's what is send to the request in reality)
        Point delta = new Point(30, 30);
        Point location = packageLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));
        createDNodeList(location.x, location.y);
        // Check the location of the note (with the absolute coordinate)
        assertDNodeListAtLocation(newClassName, packageAbsoluteLocation.getTranslated(delta));
    }

    /**
     * Create a new nodeList using the defined DNodeList Creation tool, at the
     * given position.
     * 
     * @param xOfDNodeListToCreate
     *            position of the note to create
     * @param yOfDNodeListToCreate
     *            position of the note to create
     */
    private void createDNodeList(int xOfDNodeListToCreate, int yOfDNodeListToCreate) {
        // Select the Class tool and
        editor.activateTool(NODE_CREATION_TOOL_NAME);
        // Click in the editor (the coordinates use to
        // click is with the zoom level) -> DNodeList is created
        editor.click(xOfDNodeListToCreate, yOfDNodeListToCreate);
    }

    /**
     * Check that the created DNodeList is near the expected location. We don't
     * check precisely the location because the zoom can have round effect on
     * the location.
     * 
     * @param nodeListLabel
     *            the name of the DNodeList to check
     * @param expectedLocation
     *            the expected absolute position of the created DNodeList within
     *            the graphical viewer
     */
    private void assertDNodeListAtLocation(String nodeListLabel, Point expectedLocation) {
        Point nodeListLocation = editor.getAbsoluteLocation(nodeListLabel, NodeEditPart.class);
        assertEquals("The DNodeList has been created at wrong location.", adaptLocation(expectedLocation), nodeListLocation);
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
