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
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test that :
 * <p>
 * - If the "Allow resizing Horizontally" is true, resize only the horizontally
 * size on bordered node creation and on node creation.
 * <p>
 * - If the "Allow resizing Vertically" is true, resize only the vertically size
 * on bordered node creation and on node creation.
 * <p>
 * - If the "Allow resizing Vertically and Horizontally" are true, resize works
 * on bordered node creation and on node creation.
 * <p>
 * - If the "Allow resizing Vertically and Horizontally" are false, resize not
 * works on bordered node creation and on node creation.
 * 
 * @author jdupont
 */
public class BorderedNodeResizeCreationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "new testBorderedCreationResize";

    private static final String REPRESENTATION_NAME = "testBorderedCreationResize";

    private static final String MODEL = "testBorderedCreationResize.ecore";

    private static final String SESSION_FILE = "testBorderedCreationResize.aird";

    private static final String ODESIGN_FILE = "testBorderedCreationResize.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/tools/creation/borderedNode/resize/";

    private static final String BORDERED_NODE_TOOL = "createBorderedResize";

    private static final String BORDERED_NODE_TOOL_VERTICALLY = "createBorderedResizeVertically";

    private static final String BORDERED_NODE_TOOL_HORIZONTALLY = "createBorderedResizeHorizontally";

    private static final String BORDERED_NODE_TOOL_NOT = "createBorderedResizeNot";

    private static final String NODE_TOOL = "createClassNodeResize";

    private static final String NODE_TOOL_VERTICALLY = "createClassNodeVertically";

    private static final String NODE_TOOL_HORIZONTALLY = "createClassNodeHorizontally";

    private static final String NODE_TOOL_NOT = "createClassNodeNot";

    private static final String CLASS_NAME = "Test";

    private static final String PACKAGE_NAME = "testPackage";

    private static final String BORDERED_NODE_NAME = "BorderedNode";

    private static final String BORDERED_NODE_NAME_VERTICALLY = "BorderedNodeVertically";

    private static final String BORDERED_NODE_NAME_HORIZONTALLY = "BorderedNodeHorizontally";

    private static final String BORDERED_NODE_NAME_NOT = "BorderedNodeNot";

    private static final String NODE_NAME = "Node";

    private static final String NODE_NAME_VERTICALLY = "NodeVertically";

    private static final String NODE_NAME_HORIZONTALLY = "NodeHorizontally";

    private static final String NODE_NAME_NOT = "NodeNot";

    /**
     * Aird resource session.
     */
    private UIResource sessionAirdResource;

    /**
     * Local session
     */
    private UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.setSnapToGrid(false);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        sessionAirdResource = null;
        localSession = null;
        editor = null;
    }

    /**
     * Test that on creation of bordered node, the resize is impossible if
     * "Allow resizing Horizontally" and "Allow resizing Vertically" are set to
     * false.
     */
    public void testNotResizedAllowOnBorderedNodeCreation() {
        createResizeBorderedNode(BORDERED_NODE_TOOL_NOT, BORDERED_NODE_NAME_NOT, 10, 10);
    }

    /**
     * Test that on creation of bordered node, the resize is possible if
     * "Allow resizing Horizontally" and "Allow resizing Vertically" are set to
     * true.
     */
    public void testResizedAllowOnBorderedNodeCreation() {
        createResizeBorderedNode(BORDERED_NODE_TOOL, BORDERED_NODE_NAME, 50, 50);
    }

    /**
     * Test that on creation of bordered node, the resize horizontally, is
     * allowed if "Allow resizing Horizontally" is set to true.
     */
    public void testResizedOnlyHorizontallyOnBorderedNodeCreation() {
        createResizeBorderedNode(BORDERED_NODE_TOOL_HORIZONTALLY, BORDERED_NODE_NAME_HORIZONTALLY, 50, 10);

    }

    /**
     * Test that on creation of bordered node, the resize vertically, is allowed
     * if "Allow resizing Vertically" is set to true.
     */
    public void testResizeOnlyVerticallyOnBorderedNodeCreation() {
        createResizeBorderedNode(BORDERED_NODE_TOOL_VERTICALLY, BORDERED_NODE_NAME_VERTICALLY, 10, 50);
    }

    /**
     * Test that on creation of node, the resize is impossible if
     * "Allow resizing Horizontally" and "Allow resizing Vertically" are set to
     * false.
     */
    public void testNotResizedAllowOnNodeCreation() {
        createResizeNode(NODE_TOOL_NOT, NODE_NAME_NOT, 30, 30);
    }

    /**
     * Test that on creation of node, the resize is possible if
     * "Allow resizing Horizontally" and "Allow resizing Vertically" are set to
     * true.
     */
    public void testResizedAllowOnNodeCreation() {
        createResizeNode(NODE_TOOL, NODE_NAME, 50, 50);
    }

    /**
     * Test that on creation of node, the resize horizontally, is allowed if
     * "Allow resizing Horizontally" is set to true.
     */
    public void testResizedOnlyHorizontallyOnNodeCreation() {
        createResizeNode(NODE_TOOL_HORIZONTALLY, NODE_NAME_HORIZONTALLY, 50, 30);

    }

    /**
     * Test that on creation of node, the resize vertically, is allowed if
     * "Allow resizing Vertically" is set to true.
     */
    public void testResizeOnlyVerticallyOnNodeCreation() {
        createResizeNode(NODE_TOOL_VERTICALLY, NODE_NAME_VERTICALLY, 30, 50);
    }

    /**
     * Create from tool a bordered node and try to resize during creation, when
     * it's possible.
     */
    private void createResizeBorderedNode(String createBorderedNodeToolName, String borderedNodeName, int width, int height) {
        // Retrieve the class (container) on create borderedNode
        SWTBotGefEditPart editPart = editor.getEditPart(CLASS_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(editPart);
        // Get the location of the Class
        Point classLocation = editor.getLocation(editPart);
        // create borderedNode
        createNode(createBorderedNodeToolName, classLocation.x, classLocation.y);
        // Check the the size is equals to expected result
        assertBorderedNodeSize(borderedNodeName, width, height);
    }

    /**
     * Create from tool a node and try to resize during creation, when it's
     * possible.
     */
    private void createResizeNode(String createNodeToolName, String nodeName, int width, int height) {
        // Retrieve the package (container) on create Node
        SWTBotGefEditPart editPart = editor.getEditPart(PACKAGE_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(editPart);
        // Get the location of the package
        Point classLocation = editor.getLocation(editPart);
        // create Node
        createNode(createNodeToolName, classLocation.x, classLocation.y);
        // Check the the size is equals to expected result
        assertNodeSize(nodeName, width, height);
    }

    /**
     * Create a new node using the defined Node Creation tool, at the given
     * position and try to resize when it's possible.
     * 
     * @param toolName
     *            the name of the tool to call
     * @param xOfNodeToCreate
     *            position of the node to create
     * @param yOfNodeToCreate
     *            position of the node to create
     */
    private void createNode(String toolName, int xOfNodeToCreate, int yOfNodeToCreate) {
        // Select the creation tool
        editor.activateTool(toolName);
        // Resize Node
        editor.drag(xOfNodeToCreate, yOfNodeToCreate, xOfNodeToCreate + 50, yOfNodeToCreate + 50);
    }

    /**
     * Check that the new GMF bordered node has a size expected.
     * 
     * @param borderedNodeLabel
     *            The label of the borderedNode to check.
     * @param width
     *            the width of borderedNode to check
     * @param height
     *            the height of borderedNode to check
     */
    private void assertBorderedNodeSize(String borderedNodeLabel, int width, int height) {
        SWTBotGefEditPart editPart = editor.getEditPart(borderedNodeLabel, AbstractDiagramBorderNodeEditPart.class);
        Size size = (Size) ((Node) editPart.part().getModel()).getLayoutConstraint();
        assertEquals("The borderedNode size should have " + width + " pixel width.", width, size.getWidth(), 1);
        assertEquals("The borderedNode size should have " + height + " pixel height.", height, size.getHeight(), 1);
    }

    /**
     * Check that the new GMF node has a size expected.
     * 
     * @param nodeLabel
     *            The label of the node to check.
     * @param width
     *            the width of node to check
     * @param height
     *            the height of node to check
     */
    private void assertNodeSize(String nodeLabel, int width, int height) {
        SWTBotGefEditPart editPart = editor.getEditPart(nodeLabel, AbstractDiagramNodeEditPart.class);
        Size size = (Size) ((Node) editPart.part().getModel()).getLayoutConstraint();
        assertEquals("The node size should have " + width + " pixel width.", width, size.getWidth(), 1);
        assertEquals("The node size should have " + height + " pixel height.", height, size.getHeight(), 1);
    }

}
