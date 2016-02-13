/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.uml;

import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.DiagramWithChildrensCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.dnd.DndUtil;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Class to test port location after drag'n'drop of a parent of bordered nodes
 * (See VP-3095).
 * 
 * @author lredor
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class PortLocationAfterDragAndDropTest extends AbstractUmlDragAndDropTest {
    private final static String ROOT_MODEL_NAME = "<Model> root";

    private final static String COMPONENENT_TO_DRAG_PARENT_NAME = "AnotherParentComponent2";

    private final static String COMPONENENT_TO_DRAG_PARENT_NAME_WITH_PREFIX = "<Component> " + COMPONENENT_TO_DRAG_PARENT_NAME;

    private final static String COMPONENENT_TO_DRAG_ON_DIAGRAM_NAME = "ComponentWith3Ports";

    private final static String COMPONENENT_TO_DRAG_ON_DIAGRAM_NAME_WITH_PREFIX = "<Component> " + COMPONENENT_TO_DRAG_ON_DIAGRAM_NAME;

    private UIResource umlResource;

    private SWTBotTreeItem semanticResourceNode;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return "Component Diagram-DnDComponentFromModelExplorer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return "Component Diagram-DnDComponentFromModelExplorer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        umlResource = new UIResource(designerProject, UML2_MODEL);
        semanticResourceNode = localSession.getSemanticResourceNode(umlResource);
    }

    /**
     * This method drag'n'drops an element that have :
     * <UL>
     * <LI>3 "standard" ports and check that this ports are not all at {0, 0}
     * and that all the GMF constraints are different.</LI>
     * <LI>2 collapsed ports and check that this ports have a size of 1x1.</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem.
     */
    @Test
    public void testPortLocationFromParentDnDFromModelExplorerView() throws Exception {
        Assume.assumeFalse("Drag and drop from View does not work with Xvnc", DndUtil.isUsingXvnc());
        // DnD ComponentWith3Ports from the Model Explorer view to the diagram
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOT_MODEL_NAME).expandNode(COMPONENENT_TO_DRAG_PARENT_NAME_WITH_PREFIX)
                .getNode(COMPONENENT_TO_DRAG_ON_DIAGRAM_NAME_WITH_PREFIX);
        ecoreTreeItem.select();
        DndUtil util = new DndUtil(bot.getDisplay());
        util.dragAndDrop(ecoreTreeItem, editor.getCanvas());
        bot.waitUntil(new DiagramWithChildrensCondition(editor, 1));
        // Check the standard ports position
        // First port position
        SWTBotGefEditPart port1EditPart = editor.getEditPart("Port1", AbstractDiagramBorderNodeEditPart.class);
        Node port1Node = (Node) port1EditPart.part().getModel();
        assertNotNull("The first property has no corresponding GMF node", port1Node);
        assertTrue("Bad layout constraint type", port1Node.getLayoutConstraint() instanceof Bounds);
        final Bounds firstBounds = (Bounds) port1Node.getLayoutConstraint();
        // Second port position
        SWTBotGefEditPart port2EditPart = editor.getEditPart("Port2", AbstractDiagramBorderNodeEditPart.class);
        Node port2Node = (Node) port2EditPart.part().getModel();
        assertNotNull("The second property has no corresponding GMF node", port2Node);
        assertTrue("Bad layout constraint type", port2Node.getLayoutConstraint() instanceof Location);
        final Location secondLocation = (Location) port2Node.getLayoutConstraint();
        // Third port position
        SWTBotGefEditPart port3EditPart = editor.getEditPart("Port3", AbstractDiagramBorderNodeEditPart.class);
        Node port3Node = (Node) port3EditPart.part().getModel();
        assertNotNull("The third property has no corresponding GMF node", port3Node);
        assertTrue("Bad layout constraint type", port3Node.getLayoutConstraint() instanceof Location);
        final Location thirdLocation = (Location) port3Node.getLayoutConstraint();
        // Check that the constraint are not all equals to {0,0}
        assertFalse("All the layout constraints of the ports should not be in {0,0}",
                firstBounds.getX() == 0 && firstBounds.getY() == 0 && secondLocation.getX() == 0 && thirdLocation.getY() == 0 && thirdLocation.getX() == 0 && thirdLocation.getY() == 0);
        // Check that all the constraint are different
        assertTrue("All the layout constraints of the ports should be different", firstBounds.getX() != secondLocation.getX() || firstBounds.getY() != secondLocation.getY());
        assertTrue("All the layout constraints of the ports should be different", firstBounds.getX() != thirdLocation.getX() || firstBounds.getY() != thirdLocation.getY());
        assertTrue("All the layout constraints of the ports should be different", thirdLocation.getX() != secondLocation.getX() || thirdLocation.getY() != secondLocation.getY());

        // Check the collapsed ports position and size
        SWTBotGefEditPart collapsedPort1EditPart = editor.getEditPart("collapsePort1", AbstractDiagramBorderNodeEditPart.class);
        Node collapsedPort1Node = (Node) collapsedPort1EditPart.part().getModel();
        assertNotNull("The first collapsed property has no corresponding GMF node", collapsedPort1Node);
        assertTrue("Bad layout constraint type", collapsedPort1Node.getLayoutConstraint() instanceof Bounds);
        final Bounds firstCollapsedBounds = (Bounds) collapsedPort1Node.getLayoutConstraint();
        SWTBotGefEditPart collapsedPort2EditPart = editor.getEditPart("collapsePort2", AbstractDiagramBorderNodeEditPart.class);
        Node collapsedPort2Node = (Node) collapsedPort2EditPart.part().getModel();
        assertNotNull("The first collapsed property has no corresponding GMF node", collapsedPort2Node);
        assertTrue("Bad layout constraint type", collapsedPort2Node.getLayoutConstraint() instanceof Bounds);
        final Bounds secondCollapsedBounds = (Bounds) collapsedPort2Node.getLayoutConstraint();

        assertTrue("The size of the collapsed bordered nodes should be 1x1",
                firstCollapsedBounds.getWidth() == 1 && firstCollapsedBounds.getHeight() == 1 && secondCollapsedBounds.getWidth() == 1 && secondCollapsedBounds.getHeight() == 1);
        assertEquals("The space between two bordered nodes should be as if they are expanded", secondCollapsedBounds.getY() - firstCollapsedBounds.getY() - 1, firstBounds.getHeight());
    }
}
