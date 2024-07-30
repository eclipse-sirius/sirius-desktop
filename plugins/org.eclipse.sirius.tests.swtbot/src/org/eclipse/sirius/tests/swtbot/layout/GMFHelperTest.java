/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.tests.swtbot.layout;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests defined to ensure that GMF Helper returns correct absolute GMF bounds.
 * 
 * TODO: - Add tests for border nodes bounds - Add tests for nodes bounds (inside container)
 * 
 * @author lredor
 */
public class GMFHelperTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String FREE_FORM_CONTAINER_REPRESENTATION_NAME = "DiagramWithFreeFormContainers";

    private static final String LIST_CONTAINER_REPRESENTATION_NAME = "DiagramWithListContainers";

    private static final String HSTACK_CONTAINER_REPRESENTATION_NAME = "DiagramWithHStackContainers";

    private static final String VSTACK_CONTAINER_REPRESENTATION_NAME = "DiagramWithVStackContainers";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String ODESIGN_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/gmfHelper/";

    private static final String FILE_DIR = "/";

    /**
     * The name of a container that is empty.
     */
    private static final String EMPTY_CONTAINER_NAME = "p1";

    /**
     * The name of a container with children.
     */
    private static final String CONTAINER_WITH_CHILDREN_NAME = "p2";

    /**
     * The name of a container that is empty and in another container
     */
    private static final String EMPTY_CONTAINER_INSIDE_CONTAINER_NAME = "p22";


    /**
     * The name of an empty container with border node.
     */
    private static final String EMPTY_CONTAINER_WITH_BORDER_NODE_NAME = "p3";

    /**
     * The name of a container with children having border node.
     */
    private static final String CONTAINER_WITH_2_CHILDREN_HAVING_BORDER_NODE_NAME = "p4";

    /**
     * The name of an empty container with border node, inside a container.
     */
    private static final String EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_2_NAME = "p41";

    /**
     * The name of a container with children having border node.
     */
    private static final String CONTAINER_WITH_3_CHILDREN_HAVING_BORDER_NODE_NAME = "p5";

    /**
     * The name of an empty container with border node, inside a container.
     */
    private static final String EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_3_NAME = "p53";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        closeOutline();
    }

    /**
     * Method to open an editor. It should be called at the beginning of each test.
     * 
     * @param representationName
     *            The name of the representation to be opened.
     */
    private void openEditor(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationName, representationName, DDiagram.class, true);
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

    public void testFreeFormContainerBounds_1() {
        testFreeFormContainerBounds(EMPTY_CONTAINER_NAME, DNodeContainerEditPart.class);
    }

    public void testFreeFormContainerBounds_2() {
        testFreeFormContainerBounds(CONTAINER_WITH_CHILDREN_NAME, DNodeContainerEditPart.class);
    }

    public void testFreeFormContainerBounds_3() {
        testFreeFormContainerBounds(EMPTY_CONTAINER_INSIDE_CONTAINER_NAME, DNodeContainer2EditPart.class);
    }

    public void testFreeFormContainerBounds_4() {
        testFreeFormContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_NAME, DNodeContainerEditPart.class);
    }

    public void testFreeFormContainerBounds_5() {
        testFreeFormContainerBounds(CONTAINER_WITH_2_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeContainerEditPart.class);
        testFreeFormContainerBounds(CONTAINER_WITH_3_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeContainerEditPart.class);
    }

    public void testFreeFormContainerBounds_6() {
        testFreeFormContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_2_NAME, DNodeContainer2EditPart.class);
        testFreeFormContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_3_NAME, DNodeContainerEditPart.class);
    }


    public void testListContainerBounds_1() {
        testListContainerBounds(EMPTY_CONTAINER_NAME, DNodeListEditPart.class);
    }

    public void testListContainerBounds_2() {
        testListContainerBounds(CONTAINER_WITH_CHILDREN_NAME, DNodeListEditPart.class);
    }

    public void testListContainerBounds_3() {
        testListContainerBounds(EMPTY_CONTAINER_INSIDE_CONTAINER_NAME, DNodeListElementEditPart.class);
    }

    public void testListContainerBounds_4() {
        testListContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_NAME, DNodeListEditPart.class);
    }

    public void testListContainerBounds_5() {
        testListContainerBounds(CONTAINER_WITH_2_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeListEditPart.class);
        testListContainerBounds(CONTAINER_WITH_3_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeListEditPart.class);
    }

    public void testListContainerBounds_6() {
        testListContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_2_NAME, DNodeListElementEditPart.class);
        testListContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_3_NAME, DNodeListElementEditPart.class);
    }

    public void testHStackContainerBounds_1() {
        testHStackContainerBounds(EMPTY_CONTAINER_NAME, DNodeContainerEditPart.class);
    }

    public void testHStackContainerBounds_2() {
        testHStackContainerBounds(CONTAINER_WITH_CHILDREN_NAME, DNodeContainerEditPart.class);
    }

    public void testHStackContainerBounds_3() {
        testHStackContainerBounds(EMPTY_CONTAINER_INSIDE_CONTAINER_NAME, DNodeContainer2EditPart.class);
    }

    public void testHStackContainerBounds_4() {
        testHStackContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_NAME, DNodeContainerEditPart.class);
    }

    public void testHStackContainerBounds_5() {
        testHStackContainerBounds(CONTAINER_WITH_2_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeContainerEditPart.class);
        testHStackContainerBounds(CONTAINER_WITH_3_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeContainerEditPart.class);
    }

    public void testHStackContainerBounds_6() {
        testHStackContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_2_NAME, DNodeContainer2EditPart.class);
        testHStackContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_3_NAME, DNodeContainer2EditPart.class);
    }

    public void testVStackContainerBounds_1() {
        testVStackContainerBounds(EMPTY_CONTAINER_NAME, DNodeContainerEditPart.class);
    }

    public void testVStackContainerBounds_2() {
        testVStackContainerBounds(CONTAINER_WITH_CHILDREN_NAME, DNodeContainerEditPart.class);
    }

    public void testVStackContainerBounds_3() {
        testVStackContainerBounds(EMPTY_CONTAINER_INSIDE_CONTAINER_NAME, DNodeContainer2EditPart.class);
    }

    public void testVStackContainerBounds_4() {
        testVStackContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_NAME, DNodeContainerEditPart.class);
    }

    public void testVStackContainerBounds_5() {
        testVStackContainerBounds(CONTAINER_WITH_2_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeContainerEditPart.class);
        testVStackContainerBounds(CONTAINER_WITH_3_CHILDREN_HAVING_BORDER_NODE_NAME, DNodeContainerEditPart.class);
    }

    public void testVStackContainerBounds_6() {
        testVStackContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_2_NAME, DNodeContainer2EditPart.class);
        testVStackContainerBounds(EMPTY_CONTAINER_WITH_BORDER_NODE_INSIDE_CONTAINER_3_NAME, DNodeContainer2EditPart.class);
    }

    private void testFreeFormContainerBounds(String elementToCheckName, Class<? extends EditPart> expectedEditPartType) {
        testContainerBounds(FREE_FORM_CONTAINER_REPRESENTATION_NAME, elementToCheckName, expectedEditPartType);
    }

    private void testListContainerBounds(String elementToCheckName, Class<? extends EditPart> expectedEditPartType) {
        testContainerBounds(LIST_CONTAINER_REPRESENTATION_NAME, elementToCheckName, expectedEditPartType);
    }

    private void testHStackContainerBounds(String elementToCheckName, Class<? extends EditPart> expectedEditPartType) {
        testContainerBounds(HSTACK_CONTAINER_REPRESENTATION_NAME, elementToCheckName, expectedEditPartType);
    }

    private void testVStackContainerBounds(String elementToCheckName, Class<? extends EditPart> expectedEditPartType) {
        testContainerBounds(VSTACK_CONTAINER_REPRESENTATION_NAME, elementToCheckName, expectedEditPartType);
    }

    private void testContainerBounds(String representationName, String elementToCheckName, Class<? extends EditPart> expectedEditPartType) {
        openEditor(representationName);
        SWTBotGefEditPart swtBotEditPart = editor.getEditPart(elementToCheckName, expectedEditPartType);
        Rectangle draw2DAbsoluteBounds = editor.getAbsoluteBounds(swtBotEditPart);
        // It's strange, the above method does not return absolute bounds. To check in another commit. And the method
        // GraphicalHelper.getAbsoluteBoundsIn100Percent consider handleBounds and this is not what is expected here.
        ((GraphicalEditPart) swtBotEditPart.part()).getFigure().translateToAbsolute(draw2DAbsoluteBounds);
        Rectangle gmfAbsoluteBounds = GMFHelper.getAbsoluteBounds((Node) swtBotEditPart.part().getModel(), true, false, false, false);
        assertEquals("The GMF width of " + elementToCheckName + " in " + representationName + " does not correspond to the Draw2D one.", draw2DAbsoluteBounds.preciseWidth(),
                gmfAbsoluteBounds.preciseWidth());
        assertEquals("The GMF height of " + elementToCheckName + " in " + representationName + " does not correspond to the Draw2D one.", draw2DAbsoluteBounds.preciseHeight(),
                gmfAbsoluteBounds.preciseHeight());
        assertEquals("The GMF x coordinate of " + elementToCheckName + " in " + representationName + " does not correspond to the Draw2D one.", draw2DAbsoluteBounds.preciseX(),
                gmfAbsoluteBounds.preciseX());
        assertEquals("The GMF y coordinate of " + elementToCheckName + " in " + representationName + " does not correspond to the Draw2D one.", draw2DAbsoluteBounds.preciseY(),
                gmfAbsoluteBounds.preciseY());
    }
}