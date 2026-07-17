/*******************************************************************************
 * Copyright (c) 2010, 2026 THALES GLOBAL SERVICES.
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

import java.util.function.Function;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.NodePositionHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests defined to ensure that bordered nodes are created at expected locations
 * (collapsed or not, with zoom or not, with scroll bar or not).
 * 
 * Typo: All test methods starting with testBNC_* is for
 * testBorderedNodeCreation_*.
 * 
 * @author lredor
 */
public class BorderedNodeCreationTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * The name of a new bordered node created on class.
     */
    private static final String NEW_BORDERED_NODE_ON_CLASS_NAME = "new Attribute";

    /**
     * The name of a new bordered node created on package.
     */
    private static final String NEW_BORDERED_NODE_ON_PACKAGE_NAME = "new Enum";

    /**
     * The name of a new collapsed bordered node created on class or on package.
     */
    private static final String NEW_COLLAPSED_BORDERED_NODE_NAME = "newCollapse";

    /**
     * The name of the tool that create a bordered node on a class.
     */
    private static final String BORDERED_NODE_CREATION_ON_CLASS_TOOL_NAME = "Attribute";

    /**
     * The name of the tool that create a bordered node on a package.
     */
    protected static final String BORDERED_NODE_CREATION_ON_PACKAGE_TOOL_NAME = "Enum";

    /**
     * The name of the tool that create a collapsed bordered node on a class (if
     * the corresponding filter is enabled in the use case).
     */
    private static final String COLLASPED_BORDERED_NODE_CREATION_ON_CLASS_TOOL_NAME = "CollapsedAttribute";

    /**
     * The name of the tool that create a collapsed bordered node on a package
     * (if the corresponding filter is enabled in the use case).
     */
    private static final String COLLASPED_BORDERED_NODE_CREATION_ON_PACKAGE_TOOL_NAME = "CollapsedEnum";

    private static final String REPRESENTATION_INSTANCE_NAME = "new BorderedNodeDiagram";

    private static final String REPRESENTATION_NAME = "BorderedNodeDiagram";

    private static final String MODEL = "borderedNode.ecore";

    private static final String SESSION_FILE = "borderedNode.aird";

    private static final String ODESIGN_FILE = "borderedNode.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/tools/creation/borderedNode/";

    private static final String FILE_DIR = "/";

    /** Name if the Class one */
    protected static final String C1_NAME = "C1";

    private static final String C3_NAME = "C3";

    private static final String PACKAGE_1_NAME = "P1";

    private static final String PACKAGE_2_NAME = "P2";

    private static final String PACKAGE_3_NAME = "P3";

    /** Name of the fourth package */
    protected static final String PACKAGE_4_NAME = "P4";

    private static final String PACKAGE_6_NAME = "P6ForVStack";

    private static final String CLASS_1_NAME = "Class1";

    private static final String CLASS_2_NAME = "Class2";

    private static final String CLASS_3_NAME = "Class3";

    private static final String CLASS_4_NAME = "Class4";

    public static final int COLLAPSED_SIZE = 1;

    public static final int EXPANDED_SIZE = 10;

    /**
     * If true, the creation will be made near the bordered node named
     * "elementToRevealName + collapse" (this bordered node should exist), 1
     * pixel to the left of this last. If false the creation will be made at 8
     * pixel of the top-left corner of the container.
     */
    protected boolean nearCollapsedBorderedNode = false;

    /**
     * If true the tool to create collapsed bordered node is used, if false, the
     * tool to create "normal" bordered node is used.
     */
    protected boolean createCollapsedBorderedNode;

    /**
     * If true the tool to create the border node is named "BorderClassOnVStack".
     */
    protected boolean createBorderedNodeOnVStack;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // IPreferenceStore preferenceStore = (IPreferenceStore)
        // getPreferencesHint().getPreferenceStore();
        // preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM,
        // false);
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        // changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(),
        // true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        closeOutline();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Go to the origin to avoid scroll bar
        editor.scrollTo(0, 0);
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
     * Change the environment of tests of this class.
     * 
     * @param nearCollapsedBorderedNode
     *            If true, the creation will be made near the bordered node
     *            named "elementToRevealName + collapse" (this bordered node
     *            should exist), 1 pixel to the left of this last. If false the
     *            creation will be made at 8 pixel of the top-left corner of the
     *            container.
     */
    protected void setNearCollapsedBorderedNode(boolean nearCollapsedBorderedNode) {
        this.nearCollapsedBorderedNode = nearCollapsedBorderedNode;
    }

    /**
     * Change the environment of tests of this class.
     * 
     * @param createCollapsedBorderedNode
     *            If true the tool to create collapsed bordered node is used, if
     *            false, the tool to create "normal" bordered node is used.
     */
    protected void setCreateCollapsedBorderedNode(boolean createCollapsedBorderedNode) {
        this.createCollapsedBorderedNode = createCollapsedBorderedNode;
    }

    /**
     * Ensures that a bordered node created on a Node (zoom level : 100%) has
     * the expected location.
     */
    public void testBNC_OnNodeWithoutScroll() {
        testBNC_OnNode(ZoomLevel.ZOOM_100, C1_NAME);
    }

    /**
     * Ensures that a bordered node created on a Node (zoom level : 50%) has the
     * expected location.
     */
    public void testBNC_OnNodeWithoutScrollAndChangeZoom() {
        testBNC_OnNode(ZoomLevel.ZOOM_50, C1_NAME);
    }

    /**
     * Ensures that a bordered node created on a Node with Scroll (zoom level :
     * 100%) has the expected location.
     */
    public void testBNC_OnNodeWithScroll() {
        testBNC_OnNode(ZoomLevel.ZOOM_100, C3_NAME);
    }

    /**
     * Ensures that a bordered node created on a Node with Scroll (zoom level :
     * 200%) has the expected location.
     */
    public void testBNC_OnNodeWithScrollAndChangeZoom() {
        testBNC_OnNode(ZoomLevel.ZOOM_200, C3_NAME);
    }

    /**
     * Ensures that a bordered node created on a Node (with Scroll) has the
     * expected location.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param classToRevealName
     *            the name of the class on which the bordered node will be
     *            created, this class is revealed before creation.
     */
    protected void testBNC_OnNode(ZoomLevel zoomLevel, String classToRevealName) {
        testBNC_OnNode(zoomLevel, classToRevealName, true);
    }

    /**
     * Ensures that a bordered node created on a Node (with Scroll) has the
     * expected location.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param classToRevealName
     *            the name of the class on which the bordered node will be
     *            created, this class is revealed before creation.
     * @param eastAndSouthCases
     *            true if the east and south cases must be tested, false
     *            otherwise (if the node is in the container the reveal does not
     *            reveal east and south sides so it is not possible to created
     *            border nodes on these sides).
     */
    protected void testBNC_OnNode(ZoomLevel zoomLevel, String classToRevealName, boolean eastAndSouthCases) {
        editor.zoom(zoomLevel);

        SWTBotGefEditPart classEditPart = editor.getEditPart(classToRevealName, AbstractDiagramNodeEditPart.class);

        // Reveal the class (and eventually scroll in the diagram)
        editor.select(classEditPart);
        editor.reveal(classEditPart.part());
        // Get the location of the class (relative the part visible on the
        // screen)
        Point classLocation = editor.getLocation(classEditPart);
        // Get the absolute location of the class from origin (0, 0)
        Point classAbsoluteLocation = editor.getAbsoluteLocation((GraphicalEditPart) classEditPart.part());
        // Get the insertion location for the bordered node (use the relative
        // coordinate, that's what is send to the request in reality)
        Point creationLocation;
        Point absoluteExpectedLocation = null;
        if (nearCollapsedBorderedNode) {
            // Search the location of the collapsed bordered node
            SWTBotGefEditPart editPart = editor.getEditPart(classToRevealName + "collapse", AbstractDiagramBorderNodeEditPart.class);
            Point collapseBorderedNodeLocation = editor.getLocation(editPart);
            // Translate this location to have 2 pixels at right of the
            // collapsed bordered node
            creationLocation = collapseBorderedNodeLocation.getTranslated(2, 0);
            // The creation at this place is not allowed so a shift should be
            // made to create the bordered node after the expanded state of the
            // collapsed bordered node.
            Option<Bounds> optionalBounds = new NodeQuery((Node) editPart.part().getModel()).getExtendedBounds();
            if (optionalBounds.some()) {
                Point delta = new Point(optionalBounds.get().getX() + optionalBounds.get().getWidth() + 1, optionalBounds.get().getY());
                absoluteExpectedLocation = classAbsoluteLocation.getTranslated(delta);
            } else {
                fail("Problem during getting expanded bounds of the collapse bordered node.");
            }
            testBNC_OnNodeFinalCheck(classEditPart, classAbsoluteLocation, creationLocation, absoluteExpectedLocation);
        } else {
            // To increase code coverage the 4 corners are tested
            Rectangle classBounds = editor.getAbsoluteBounds(classEditPart);

            // Try to locate the bordered node at the top-left corner of the
            // class (on north side)
            Point delta = new Point(8, 0);
            // We compute the location according the the class location, the
            // zoom factor and an insets to be sure to be in the class and not
            // just above.
            creationLocation = classLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()).translate(0, 1));
            // The expected location is in absolute coordinate the delta is
            // translate with -2 in y axis corresponding to the shift of the
            // bordered node make by BorderItemLocator
            // (IBorderItemOffsets.DEFAULT_OFFSET - size of the node).
            absoluteExpectedLocation = classAbsoluteLocation.getTranslated(delta.translate(0, -2));
            testBNC_OnNodeFinalCheck(classEditPart, classAbsoluteLocation, creationLocation, absoluteExpectedLocation);
            undo(getBorderedNodeCreationOnClassToolName());

            if (eastAndSouthCases) {
                // Try to locate the bordered node to the top-right corner of
                // the class (on east side)
                delta = new Point(classBounds.width - 1, 2);
                // We compute the location according the the class location and
                // the zoom factor
                creationLocation = classLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));
                // The expected location is in absolute coordinate the delta is
                // translate with -7 in y axis corresponding to the shift of the
                // bordered node make by BorderItemLocator
                // (IBorderItemOffsets.DEFAULT_OFFSET - size of the node).
                absoluteExpectedLocation = classAbsoluteLocation.getTranslated(delta.translate(-7, 0));
                testBNC_OnNodeFinalCheck(classEditPart, classAbsoluteLocation, creationLocation, absoluteExpectedLocation);
                undo(getBorderedNodeCreationOnClassToolName());

                // Try to locate the bordered node to the bottom-left corner of
                // the class (on south side)
                delta = new Point(8, classBounds.height - 4);
                // We compute the location according the the class location, the
                // zoom factor and an insets to be sure to be in the class and
                // not just above.
                creationLocation = classLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()).translate(0, 1));
                // The expected location is in absolute coordinate the delta is
                // translate with -2 in y axis corresponding to the shift of the
                // bordered node make by BorderItemLocator
                // (IBorderItemOffsets.DEFAULT_OFFSET - size of the node).
                absoluteExpectedLocation = classAbsoluteLocation.getTranslated(delta.translate(0, -4));
                testBNC_OnNodeFinalCheck(classEditPart, classAbsoluteLocation, creationLocation, absoluteExpectedLocation);
                undo(getBorderedNodeCreationOnClassToolName());
            }

            // Try to locate the bordered node to the top-left corner of the
            // class (on west side)
            delta = new Point(2, 2);
            // We compute the location according the the class location, the
            // zoom factor and an insets to be sure to be in the class and not
            // just above.
            creationLocation = classLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()));
            // The expected location is in absolute coordinate the delta is
            // translate with -2 in y axis corresponding to the shift of the
            // bordered node make by BorderItemLocator
            // (IBorderItemOffsets.DEFAULT_OFFSET - size of the node).
            absoluteExpectedLocation = classAbsoluteLocation.getTranslated(delta.translate(-4, 0));
            testBNC_OnNodeFinalCheck(classEditPart, classAbsoluteLocation, creationLocation, absoluteExpectedLocation);
        }

    }

    private void testBNC_OnNodeFinalCheck(SWTBotGefEditPart classEditPart, Point classAbsoluteLocation, Point creationLocation, Point absoluteExpectedLocation) {
        absoluteExpectedLocation = adaptExpectedLocation(((GraphicalEditPart) classEditPart.part()).getFigure(), classAbsoluteLocation, absoluteExpectedLocation);

        createBorderedNode(getBorderedNodeCreationOnClassToolName(), creationLocation.x, creationLocation.y);
        // Check the location of the bordered node (with the absolute
        // coordinate)
        assertBorderedNodeAtLocation(getNewBorderedNodeOnClassName(), absoluteExpectedLocation, classAbsoluteLocation, creationLocation, ((GraphicalEditPart) classEditPart.part()),
                nearCollapsedBorderedNode);
        if (createCollapsedBorderedNode) {
            // Check the size of the new GMF node.
            assertBorderedNodeSize(getNewBorderedNodeOnPackageName());
        }
    }

    /**
     * @param parentFigure
     * @param parentAbsoluteLocation
     * @param absoluteExpectedLocation
     * @return
     */
    protected Point adaptExpectedLocation(IFigure parentFigure, Point parentAbsoluteLocation, Point absoluteExpectedLocation) {
        if (createCollapsedBorderedNode) {
            // Adapt the expected location to collapsed one.
            absoluteExpectedLocation = PortLayoutHelper
                    .getCollapseCandidateLocation(new Dimension(COLLAPSED_SIZE, COLLAPSED_SIZE), new Rectangle(absoluteExpectedLocation.x, absoluteExpectedLocation.y, EXPANDED_SIZE, EXPANDED_SIZE),
                    new Rectangle(parentAbsoluteLocation, parentFigure.getSize())).getTopLeft();
        }
        return absoluteExpectedLocation;
    }

    /**
     * Ensures that a bordered node created on a container (without Scroll) has
     * the expected position.
     * 
     */
    public void testBNC_OnContainerWithoutScroll() {
        testBNC_OnContainer(ZoomLevel.ZOOM_100, PACKAGE_1_NAME);
    }

    /**
     * Ensures that a bordered node created on a container (without Scroll, zoom
     * : 50%) has the expected position.
     * 
     */
    public void testBNC_OnContainerWithoutScrollAndChangeZoom() {
        testBNC_OnContainer(ZoomLevel.ZOOM_50, PACKAGE_1_NAME);
    }

    /**
     * Ensures that a bordered node created on a container (which location
     * implies to scroll on the diagram) has the expected position.
     */
    public void testBNC_OnContainerWithScrollInDiagram() {
        testBNC_OnContainer(ZoomLevel.ZOOM_100, PACKAGE_4_NAME);
    }

    /**
     * Ensures that a bordered node created on a container (which location
     * implies to scroll on the diagram) has the expected position.
     */
    public void testBNC_OnContainerWithScrollInDiagramAndChangeZoom() {
        testBNC_OnContainer(ZoomLevel.ZOOM_200, PACKAGE_4_NAME);
    }

    /**
     * Creates a new bordered node on the container with the given name, and
     * check that it is created at the expected position.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param packageToRevealName
     *            the name of the package to reveal before inserting the
     *            bordered node.
     */
    protected void testBNC_OnContainer(ZoomLevel zoomLevel, String packageToRevealName) {
        testBNC_OnContainer(zoomLevel, packageToRevealName, null);
    }

    /**
     * Creates a new bordered node on the container with the given name, and
     * check that it is created at the expected position.
     * 
     * @param zoomLevel
     *            the zoomLevel to set on the editor
     * @param packageToRevealName
     *            the name of the package to reveal before inserting the
     *            bordered node.
     * @param classToRevealName
     *            the name of the class to reveal after revealing the package,
     *            null if there is no class to reveal
     */
    private void testBNC_OnContainer(ZoomLevel zoomLevel, String packageToRevealName, String classToRevealName) {
        editor.zoom(zoomLevel);

        SWTBotGefEditPart editPart = editor.getEditPart(packageToRevealName, AbstractDiagramContainerEditPart.class);
        // Reveal the package (and eventually scroll in the diagram)
        editor.select(editPart);
        editor.reveal(editPart.part());
        // Reveal the class (and eventually scroll in the package)
        if (classToRevealName != null) {
            editor.reveal(classToRevealName);
        }
        // Get the location of the package (relative the part visible on the
        // screen)
        Point packageLocation = editor.getLocation(editPart);
        // Get the absolute location of the package from origin (0, 0)
        Point packageAbsoluteLocation = editor.getAbsoluteLocation((GraphicalEditPart) editPart.part());
        // Get the insertion location for the bordered node (use the relative
        // coordinate, that's what is send to the request in reality)
        Point location;
        Point expectedLocation = null;
        if (nearCollapsedBorderedNode) {
            // Search the location of the collapsed bordered node
            SWTBotGefEditPart collapsedEditPart = editor.getEditPart(packageToRevealName + "collapse", AbstractDiagramBorderNodeEditPart.class);
            Point collapseBorderedNodeLocation = editor.getLocation(collapsedEditPart);
            // Translate this location to have 2 pixels at right of the
            // collapsed bordered node.
            location = collapseBorderedNodeLocation.getTranslated(2, 0);
            // The creation at this place is not allowed so a shift should be
            // made to create the bordered node after the expanded state of the
            // collapsed bordered node.
            Option<Bounds> optionalBounds = new NodeQuery((Node) collapsedEditPart.part().getModel()).getExtendedBounds();
            if (optionalBounds.some()) {
                Point delta = new Point(optionalBounds.get().getX() + optionalBounds.get().getWidth() + 1, optionalBounds.get().getY());
                expectedLocation = packageAbsoluteLocation.getTranslated(delta);
            } else {
                fail("Problem during getting expanded bounds of the collapse bordered node.");
            }
        } else {
            // Try to locate the bordered node on the left border, at 8 pixels from the top-left
            // corner of the package
            Point delta = new Point(0, 8);
            if (createBorderedNodeOnVStack) {
                // For a VStack container try to locate the bordered node a little bit lower to be under the "title"
                // area.
                delta = delta.getTranslated(0, 22);
            }
            // We compute the location according the the package location, the
            // zoom factor and an insets to be sure to be in the package and not
            // just nearby.
            location = packageLocation.getTranslated(delta.getScaled(zoomLevel.getAmount()).translate(1, 0));
            // The expected location is in absolute coordinate the delta is
            // translate with -2 in y axis corresponding to the shift of the
            // bordered node make by BorderItemLocator
            // (IBorderItemOffsets.DEFAULT_OFFSET - size of the node).
            expectedLocation = packageAbsoluteLocation.getTranslated(delta.translate(-2, 0));
        }
        expectedLocation = adaptExpectedLocation(((GraphicalEditPart) editPart.part()).getFigure(), packageAbsoluteLocation, expectedLocation);

        createBorderedNode(getBorderedNodeCreationOnPackageToolName(), location.x, location.y);
        // Check the location of the bordered node (with the absolute
        // coordinate)
        assertBorderedNodeAtLocation(getNewBorderedNodeOnPackageName(), expectedLocation, packageAbsoluteLocation, (GraphicalEditPart) editPart.part(), nearCollapsedBorderedNode);
        if (createCollapsedBorderedNode) {
            // Check the size of the new GMF node.
            assertBorderedNodeSize(getNewBorderedNodeOnPackageName());
        }
    }

    /**
     * Ensures that a bordered node created on a container (this container
     * having been reduced by user and therefore having a scroll bar) has the
     * expected position.
     */
    public void testBNC_OnContainerWithScrollInContainer() {
        testBNC_OnContainer(ZoomLevel.ZOOM_100, PACKAGE_2_NAME, CLASS_2_NAME);
    }

    /**
     * Ensures that a bordered node created on a container (this container
     * having been reduced by user and therefore having a scroll bar) has the
     * expected position (zoom : 200%).
     */
    public void testBNC_OnContainerWithScrollInContainerAndChangeZoom() {
        testBNC_OnContainer(ZoomLevel.ZOOM_200, PACKAGE_2_NAME, CLASS_2_NAME);
    }

    /**
     * Ensures that a bordered node created on a container (this container
     * having been reduced by user and therefore having a scroll bar), which
     * location implies to scroll on the diagram, has the expected position.
     */
    public void testBNC_OnContainerWithScrollInContainerAndDiagram() {
        testBNC_OnContainer(ZoomLevel.ZOOM_100, PACKAGE_3_NAME, CLASS_3_NAME);
    }

    /**
     * Ensures that a bordered node created on a container (this container
     * having been reduced by user and therefore having a scroll bar), which
     * location implies to scroll on the diagram, has the expected position
     * (zoom : 200%).
     */
    public void testBNC_OnContainerWithScrollInContainerAndDiagramAndChangeZoom() {
        testBNC_OnContainer(ZoomLevel.ZOOM_200, PACKAGE_3_NAME, CLASS_3_NAME);
    }

    /**
     * Create a new bordered node using the defined Bordered Node Creation tool,
     * at the given position.
     * 
     * @param toolName
     *            the name of the tool to call
     * @param xOfNodeToCreate
     *            position of the note to create
     * @param yOfNodeToCreate
     *            position of the note to create
     */
    protected void createBorderedNode(String toolName, int xOfNodeToCreate, int yOfNodeToCreate) {
        // Select the Class tool and
        editor.activateTool(toolName);
        // Click in the editor (the coordinates use to
        // click is with the zoom level) -> Node is created
        editor.click(xOfNodeToCreate, yOfNodeToCreate);
    }

    /**
     * Check that the created Bordered Node is near the expected location. We
     * don't check precisely the location because the zoom can have round effect
     * on the location.
     * 
     * @param borderedNodeLabel
     *            the name of the Bordered Node to check
     * @param expectedLocation
     *            the expected absolute position of the created Bordered Node
     *            within the graphical viewer
     * @param parentLocation
     *            the absolute location of the parent figure
     * @param creationLocation
     *            The relative creation location used to create the border node
     *            or null
     * @param parentFigure
     *            the parent figure
     * @param nearCollapsedBorderedNode
     *            If true, the creation will be made near the bordered node
     *            named "elementToRevealName + collapse" (this bordered node
     *            should exist), 1 pixel to the left of this last. If false the
     *            creation will be made at 8 pixel of the top-left corner of the
     *            container.
     */
    private void assertBorderedNodeAtLocation(String borderedNodeLabel, Point expectedLocation, Point parentLocation, IGraphicalEditPart parentPart, boolean nearCollapsedBorderedNode) {
        assertBorderedNodeAtLocation(borderedNodeLabel, expectedLocation, parentLocation, null, parentPart, nearCollapsedBorderedNode);
    }

    /**
     * Check that the created Bordered Node is near the expected location. We
     * don't check precisely the location because the zoom can have round effect
     * on the location.
     * 
     * @param borderedNodeLabel
     *            the name of the Bordered Node to check
     * @param expectedLocation
     *            the expected absolute position of the created Bordered Node
     *            within the graphical viewer
     * @param parentLocation
     *            the absolute location of the parent figure
     * @param creationLocation
     *            The relative creation location used to create the border node
     *            or null
     * @param parentPart
     *            the parent edit part
     * @param nearCollapsedBorderedNode
     *            If true, the creation will be made near the bordered node
     *            named "elementToRevealName + collapse" (this bordered node
     *            should exist), 1 pixel to the left of this last. If false the
     *            creation will be made at 8 pixel of the top-left corner of the
     *            container.
     */
    protected void assertBorderedNodeAtLocation(String borderedNodeLabel, Point expectedLocation, Point parentLocation, Point creationLocation, IGraphicalEditPart parentPart,
            boolean nearCollapsedBorderedNode) {
        IGraphicalEditPart borderNodePart = (IGraphicalEditPart) editor.getEditPart(borderedNodeLabel, AbstractDiagramBorderNodeEditPart.class).part();
        Point nodeLocation = editor.getAbsoluteLocation(borderNodePart);
        String errorMessage = "the BorderedNode has been created at wrong location.";
        if (nearCollapsedBorderedNode) {
            errorMessage = "the BorderedNode has been created at wrong location (for near collapsed bordered node case).";
        }
        // Check GMF
        Node borderNode = (Node) borderNodePart.getModel();
        Point gmfNodeLocation = GMFHelper.getAbsoluteLocation(borderNode, true, false);
        assertSameLocation("For GMF, " + errorMessage, borderNode, gmfNodeLocation, borderNodePart, expectedLocation, parentLocation, creationLocation, parentPart);
        // Check Draw2d
        assertSameLocation("For Draw2d, " + errorMessage, borderNode, nodeLocation, borderNodePart, expectedLocation, parentLocation, creationLocation, parentPart);
    }

    /**
     * Check that the new GMF node has a size of 1x1.
     * 
     * @param borderedNodeLabel
     *            The label of the bordered node to check.
     */
    private void assertBorderedNodeSize(String borderedNodeLabel) {
        SWTBotGefEditPart editPart = editor.getEditPart(borderedNodeLabel, AbstractDiagramBorderNodeEditPart.class);
        Size size = (Size) ((Node) editPart.part().getModel()).getLayoutConstraint();
        assertEquals("The port size should have 1 pixel width.", COLLAPSED_SIZE, size.getWidth());
        assertEquals("The port size should have 1 pixel height.", COLLAPSED_SIZE, size.getHeight());
    }

    /**
     * Ensures that a bordered node created on a vertical stack region (zoom level: 100%) has the expected location.
     */
    public void testBNC_OnVStackContainer() {
        createBorderedNodeOnVStack = true;
        try {
            testBNC_OnContainer(ZoomLevel.ZOOM_100, PACKAGE_6_NAME);
        } finally {
            createBorderedNodeOnVStack = false;
        }
    }

    /**
     * Ensures that a bordered node created on a Node in Container (zoom level :
     * 100%) has the expected location.
     */
    public void testBNC_OnNodeInContainer() {
        testBNC_OnNode(ZoomLevel.ZOOM_100, CLASS_1_NAME);
    }

    /**
     * Ensures that a bordered node created on a Node in Container (zoom level :
     * 50%) has the expected location.
     */
    public void testBNC_OnNodeInContainerAndChangeZoom() {
        testBNC_OnNode(ZoomLevel.ZOOM_50, CLASS_1_NAME);
    }

    /**
     * Ensures that a bordered node created on a Node in Container with scroll
     * (zoom level : 100%) has the expected location.
     */
    public void testBNC_OnNodeInContainerWithScroll() {
        testBNC_OnNode(ZoomLevel.ZOOM_100, CLASS_2_NAME, false);
    }

    /**
     * Ensures that a bordered node created on a Node in Container with scroll
     * (zoom level : 50%) has the expected location.
     */
    public void testBNC_OnNodeInContainerWithScrollAndChangeZoom() {
        testBNC_OnNode(ZoomLevel.ZOOM_50, CLASS_2_NAME, false);
    }

    /**
     * Ensures that a bordered node created on a Node in Container with scroll
     * (which location implies to scroll on the diagram) (zoom level : 100%) has
     * the expected location.
     */
    public void testBNC_OnNodeInContainerWithScrollWithScrollInDiagram() {
        testBNC_OnNode(ZoomLevel.ZOOM_100, CLASS_4_NAME, false);
    }

    /**
     * Ensures that a bordered node created on a Node in Container with scroll
     * (which location implies to scroll on the diagram) (zoom level : 200%) has
     * the expected location.
     */
    public void testBNC_OnNodeInContainerWithScrollWithScrollInDiagramAndChangeZoom() {
        testBNC_OnNode(ZoomLevel.ZOOM_200, CLASS_4_NAME, false);
    }

    /**
     * Returns the name of the tool to use according to
     * <code>createCollapsedBorderedNode</code> status.
     * 
     * @return the name of the tool to use.
     */
    protected String getBorderedNodeCreationOnClassToolName() {
        if (createCollapsedBorderedNode) {
            return COLLASPED_BORDERED_NODE_CREATION_ON_CLASS_TOOL_NAME;
        } else {
            return BORDERED_NODE_CREATION_ON_CLASS_TOOL_NAME;
        }
    }

    /**
     * Returns the name of the tool to use according to
     * <code>createCollapsedBorderedNode</code> status.
     * 
     * @return the name of the tool to use.
     */
    protected String getBorderedNodeCreationOnPackageToolName() {
        if (createBorderedNodeOnVStack) {
            if (createCollapsedBorderedNode) {
                return "CollapsedBorderClassOnVStack";
            } else {
                return "BorderClassOnVStack";
            }
        } else {
            if (createCollapsedBorderedNode) {
                return COLLASPED_BORDERED_NODE_CREATION_ON_PACKAGE_TOOL_NAME;
            } else {
                return BORDERED_NODE_CREATION_ON_PACKAGE_TOOL_NAME;
            }
        }
    }

    /**
     * Returns the name of the new bordered node created on class according to
     * <code>createCollapsedBorderedNode</code> status.
     * 
     * @return the name of the new bordered node.
     */
    protected String getNewBorderedNodeOnClassName() {
        if (createCollapsedBorderedNode) {
            return NEW_COLLAPSED_BORDERED_NODE_NAME;
        } else {
            return NEW_BORDERED_NODE_ON_CLASS_NAME;
        }
    }

    /**
     * Returns the name of the tool to use according to
     * <code>createCollapsedBorderedNode</code> status.
     * 
     * @return the name of the tool to use.
     */
    protected String getNewBorderedNodeOnPackageName() {
        if (createCollapsedBorderedNode) {
            return NEW_COLLAPSED_BORDERED_NODE_NAME;
        } else {
            return NEW_BORDERED_NODE_ON_PACKAGE_NAME;
        }
    }

    /**
     * Possibility to adapt the expected location according to some parameters (snap to grid, ...).
     *
     * @param errorMessage
     *            the assertion message
     * @param borderNode
     *            the border node
     * @param nodeLocation
     *            The actual node location (in absolute coordinates)
     * @param borderNodePart
     *            the border node edit part
     * @param expectedLocation
     *            The expected location (in absolute coordinates)
     * @param parentLocation
     *            The parent location (in absolute coordinates)
     * @param creationLocation
     *            The relative creation location used to create the border node or null
     * @param parentPart
     *            the parent edit part
     */
    protected void assertSameLocation(String errorMessage, Node borderNode, Point nodeLocation, IGraphicalEditPart borderNodePart, Point expectedLocation, Point parentLocation, Point creationLocation,
            IGraphicalEditPart parentPart) {
        assertEquals(errorMessage, expectedLocation, nodeLocation);
    }

    /**
     * Checks that the center of the free axis of a border node is on the grid. The other axis is constrained by its
     * container.
     *
     * @param errorMessage
     *            the assertion message
     * @param borderNode
     *            the border node
     * @param nodeLocation
     *            the actual node location
     * @param borderNodePart
     *            the border node edit part
     * @param parentLocation
     *            the parent location
     * @param parentPart
     *            the parent edit part
     * @param snapToLocation
     *            the snap-to-grid location expected for the free axis
     * @param gridSpacing
     *            the grid spacing
     * @param centerOffset
     *            offset to apply before checking the center, for collapsed bordered nodes
     */
    protected void assertBorderNodeCenteredOnGrid(String errorMessage, Node borderNode, Point nodeLocation, IGraphicalEditPart borderNodePart, Point parentLocation,
            IGraphicalEditPart parentPart, Point snapToLocation, int gridSpacing, Point centerOffset) {
        Dimension nodeSize = getBorderNodeSize(borderNode, borderNodePart);
        Point logicalNodeLocation = getLogicalNodeLocationForSnap(borderNode, nodeLocation, borderNodePart);
        Point logicalParentLocation = getLogicalParentLocationForSnap(borderNode, parentLocation, parentPart);
        int side = getBorderNodeSide(logicalNodeLocation, nodeSize, logicalParentLocation, parentPart);
        assertBorderNodeCenteredOnGrid(errorMessage, borderNode, nodeLocation, borderNodePart, snapToLocation, gridSpacing, centerOffset, side);
    }

    /**
     * Checks that the center of the free axis of a border node is on the grid. The other axis is constrained by its
     * container.
     *
     * @param errorMessage
     *            the assertion message
     * @param borderNode
     *            the border node
     * @param nodeLocation
     *            the actual node location
     * @param borderNodePart
     *            the border node edit part
     * @param snapToLocation
     *            the snap-to-grid location expected for the free axis
     * @param gridSpacing
     *            the grid spacing
     * @param centerOffset
     *            offset to apply before checking the center, for collapsed bordered nodes
     * @param side
     *            the side on which the bordered node is located
     */
    protected void assertBorderNodeCenteredOnGrid(String errorMessage, Node borderNode, Point nodeLocation, IGraphicalEditPart borderNodePart, Point snapToLocation, int gridSpacing,
            Point centerOffset, int side) {
        Dimension nodeSize = getBorderNodeSize(borderNode, borderNodePart);
        Point logicalNodeLocation = getLogicalNodeLocationForSnap(borderNode, nodeLocation, borderNodePart);
        Rectangle parentBounds = getLogicalParentBoundsForSnap(borderNode, borderNodePart);
        Point effectiveCenterOffset = getEffectiveCollapsedCenterOffset(nodeSize, side, centerOffset);
        if ((side == PositionConstants.WEST || side == PositionConstants.EAST) && !NodePositionHelper.canResizeHeight(borderNode)) {
            int centerY = logicalNodeLocation.y + effectiveCenterOffset.y + nodeSize.height / 2;
            Point center = new Point(logicalNodeLocation.x + centerOffset.x + nodeSize.width / 2, centerY);
            Point snappedCenter = editor.adaptLocationToSnap(center);
            if (isClampedOnFreeAxis(logicalNodeLocation, parentBounds, nodeSize, effectiveCenterOffset, snappedCenter, pt -> pt.y)) {
                // In this case, it is expected to not have a border node centered.
                return;
            }
            assertEquals(errorMessage + " The border node vertical center should be on the grid. location=" + nodeLocation + ", size=" + nodeSize + ", centerOffset=" + centerOffset + ", side="
                    + side + ", logicalLocation=" + logicalNodeLocation + ", centerY=" + centerY + ", snappedCenter=" + snappedCenter + ", previousSnapToLocation=" + snapToLocation
                    + ", gridSpacing=" + gridSpacing + ", constraint=" + borderNode.getLayoutConstraint() + ", element=" + borderNode.getElement() + ".", snappedCenter.y, centerY);
        } else if ((side == PositionConstants.NORTH || side == PositionConstants.SOUTH) && !NodePositionHelper.canResizeWidth(borderNode)) {
            int centerX = logicalNodeLocation.x + effectiveCenterOffset.x + nodeSize.width / 2;
            Point center = new Point(centerX, logicalNodeLocation.y + centerOffset.y + nodeSize.height / 2);
            Point snappedCenter = editor.adaptLocationToSnap(center);
            if (isClampedOnFreeAxis(logicalNodeLocation, parentBounds, nodeSize, effectiveCenterOffset, snappedCenter, pt -> pt.x)) {
                // In this case, it is expected to not have a border node centered.
                return;
            }
            assertEquals(errorMessage + " The border node horizontal center should be on the grid. location=" + nodeLocation + ", size=" + nodeSize + ", centerOffset=" + centerOffset + ", side="
                    + side + ", logicalLocation=" + logicalNodeLocation + ", centerX=" + centerX + ", snappedCenter=" + snappedCenter + ", previousSnapToLocation=" + snapToLocation
                    + ", gridSpacing=" + gridSpacing + ", constraint=" + borderNode.getLayoutConstraint() + ", element=" + borderNode.getElement() + ".", snappedCenter.x, centerX);
        }
    }

    /**
     * Returns true when a WEST/EAST border node can not have its vertical center aligned on the grid, or reciprocally
     * when a NORTH/SOUTH border node can not have its horizontal center aligned on the grid because the corresponding
     * location would be outside of the parent bounds.
     * <p>
     * In this situation the locator keeps the border node on the closest authorized vertical bound instead of
     * preserving the snapped center. For collapsed border nodes, the effective bounds include the collapse offsets used
     * by the locator.
     * </p>
     *
     * @param nodeLocation
     *            the actual logical location of the border node
     * @param parentBounds
     *            the logical bounds of the parent border
     * @param nodeSize
     *            the logical size of the border node
     * @param centerOffset
     *            the offset to apply to the node location to compute the effective center
     * @param snappedCenterY
     *            the expected snapped vertical center
     * @return true if the node is clamped on the top or bottom effective bound because the snapped top-left location
     *         would be outside of the parent bounds, false otherwise.
     */
    private boolean isClampedOnFreeAxis(Point nodeLocation, Rectangle parentBounds, Dimension nodeSize, Point centerOffset, Point snappedCenter, Function<Point, Integer> axis) {
        // A collapsed bordered node of size 1 is centered inside its expanded bounds.
        // With integer coordinates, centering 1px in 10px gives (10 - 1) / 2 = 4,
        // leaving 4px before the collapsed node and 5px after it.
        int shiftForCollapsed = (EXPANDED_SIZE - COLLAPSED_SIZE) / 2;
        int oppositeShiftForCollapsed = EXPANDED_SIZE - shiftForCollapsed;
        var topleft = parentBounds.getTopLeft();
        var opposite = parentBounds.getBottomRight();
        var dim = new Point(nodeSize.width, nodeSize.height);
        int minCoordinate = isCollapsedSize(nodeSize) ? axis.apply(topleft) + shiftForCollapsed : axis.apply(topleft);
        int maxCoordinate = isCollapsedSize(nodeSize) ? axis.apply(opposite) - oppositeShiftForCollapsed : axis.apply(opposite) - axis.apply(dim);
        int snappedCoordinate = axis.apply(snappedCenter) - axis.apply(centerOffset) - axis.apply(dim) / 2;
        return (axis.apply(nodeLocation) == minCoordinate && snappedCoordinate < minCoordinate) || (axis.apply(nodeLocation) == maxCoordinate && snappedCoordinate > maxCoordinate);
    }

    private Point getEffectiveCollapsedCenterOffset(Dimension nodeSize, int side, Point centerOffset) {
        Point result = centerOffset;
        if (isCollapsedSize(nodeSize)) {
            result = centerOffset.getCopy();
            if (side == PositionConstants.WEST || side == PositionConstants.EAST) {
                result.y = 1;
            } else if (side == PositionConstants.NORTH || side == PositionConstants.SOUTH) {
                result.x = 1;
            }
        }
        return result;
    }

    private boolean isCollapsedSize(Dimension nodeSize) {
        return nodeSize.width == COLLAPSED_SIZE && nodeSize.height == COLLAPSED_SIZE;
    }

    private Point getLogicalNodeLocationForSnap(Node borderNode, Point nodeLocation, IGraphicalEditPart borderNodePart) {
        if (borderNode.eContainer() instanceof Node parentNode && borderNodePart.getParent() instanceof IGraphicalEditPart parentPart) {
            Point borderNodeGMFLocation = GMFHelper.getAbsoluteLocation(borderNode, true, false);
            if (borderNodeGMFLocation.equals(nodeLocation)) {
                return nodeLocation;
            }
            Point parentGMFLocation = GMFHelper.getAbsoluteLocation(parentNode, true, false);
            Point parentDraw2DLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart, true).getLocation();
            return nodeLocation.getTranslated(parentGMFLocation.getDifference(parentDraw2DLocation));
        }
        return nodeLocation;
    }

    private Point getLogicalParentLocationForSnap(Node borderNode, Point parentLocation, IGraphicalEditPart parentPart) {
        if (borderNode.eContainer() instanceof Node parentNode) {
            return GMFHelper.getAbsoluteLocation(parentNode, true, false);
        }
        return parentLocation;
    }

    private Rectangle getLogicalParentBoundsForSnap(Node borderNode, IGraphicalEditPart borderNodePart) {
        if (borderNode.eContainer() instanceof Node parentNode && borderNodePart.getParent() instanceof IGraphicalEditPart parentPart) {
            Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart, true);
            Point parentGMFLocation = GMFHelper.getAbsoluteLocation(parentNode, true, false);
            parentBounds.setLocation(parentGMFLocation);
            return parentBounds;
        }
        return GraphicalHelper.getAbsoluteBoundsIn100Percent((IGraphicalEditPart) borderNodePart.getParent(), true);
    }

    protected Dimension getBorderNodeSize(Node borderNode, IGraphicalEditPart borderNodePart) {
        Dimension nodeSize = borderNodePart.getFigure().getSize().getCopy();
        if (borderNode.getLayoutConstraint() instanceof Size size && size.getWidth() > 0 && size.getHeight() > 0) {
            nodeSize = new Dimension(size.getWidth(), size.getHeight());
        }
        return nodeSize;
    }

    protected int getBorderNodeSide(Point nodeLocation, Dimension nodeSize, Point parentLocation, IGraphicalEditPart parentPart) {
        Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart, true);
        parentBounds.setLocation(parentLocation);
        return CanonicalDBorderItemLocator.findClosestSideOfParent(new Rectangle(nodeLocation, nodeSize), parentBounds);
    }
}
