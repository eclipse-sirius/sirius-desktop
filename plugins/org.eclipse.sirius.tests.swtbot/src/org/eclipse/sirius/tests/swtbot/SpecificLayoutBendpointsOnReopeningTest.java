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

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test bendpoints on reopening of Ordered Tree Layout, Composite Layout with
 * "Top to Bottom" and Composite Layout with "Bottom to Top".
 * 
 * @See VP-2932 for original problem.
 * 
 * @author jdupont
 */
public class SpecificLayoutBendpointsOnReopeningTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_NAME_BREAKDOWN = "new Breakdown";

    private static final String REPRESENTATION_NAME_BREAKDOWN_ABOVE = "new BreakdownAboveEdge";

    private static final String REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM = "new BreakdownCompositeTopToBottom";

    private static final String REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE = "new BreakdownCompositeTopToBottomBelowEdge";

    private static final String REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP = "new BreakdownCompositeBottomToTop";

    private static final String REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE = "new BreakdownCompositeBottomToTopAboveEdge";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Breakdown";

    private static final String REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM = "BreakdownCompositeTopToBottom";

    private static final String REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP = "BreakdownCompositeBottomToTop";

    private static final String MODEL = "breakdown.ecore";

    private static final String SESSION_FILE = "breakdown.aird";

    private static final String VSM_FILE = "breakDownEcore.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/specific_layout/";

    private static final String FILE_DIR = "/";

    private static final String NODE_NAME_ROOT1 = "root1";

    private static final String NODE_NAME_ROOT2 = "root2";

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
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Test Tree Layout DragNDrop root2 from bottom to bottom. root1 As target,
     * root2 as source. root2 is bellow edge before and after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to bottom and
     * to right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropSourceRoot2BottomtoBottomEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 220, 100, 220, 47);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 235/230
        editor.drag(root2EditPart, 235, 230);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 255, 230, 255, 100, 220, 100, 220, 47);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 255, 230, 255, 100, 220, 100, 220, 47);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout DragNDrop root2 from bottom to top. root1 As target,
     * root2 as source. root2 is below edge before move and above edge after
     * move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to top and to
     * left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropSourceRoot2BottomToTopEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 220, 100, 220, 47);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 100/5
        editor.drag(root2EditPart, 100, 5);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 45, 120, 100, 220, 100, 220, 47);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 45, 120, 100, 220, 100, 220, 47);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout DragNDrop root2 from top to bottom. root1 As target,
     * root2 as source. root2 is above edge before move and below edge after
     * move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to bottom and
     * to right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropSourceRoot2TopToBottomEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 305, 175, 305, 120);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 350/230
        editor.drag(root2EditPart, 350, 230);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 385, 230, 385, 175, 305, 175, 305, 120);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 385, 230, 385, 175, 305, 175, 305, 120);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout DragNDrop root2 from top to top. root1 As target, root2
     * as source. root2 is above edge before move and above edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to top and to
     * left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropSourceRoot2TopToTopEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 305, 175, 305, 120);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 50/5
        editor.drag(root2EditPart, 50, 5);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 85, 75, 85, 175, 305, 175, 305, 120);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 85, 75, 85, 175, 305, 175, 305, 120);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout DragNDrop root1 from top to top. root1 As target, root2
     * as source. root1 is above edge before and after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to top and to
     * right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropTargetRoot1ToptoTopEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 220, 100, 220, 47);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 270/30
        editor.drag(root1EditPart, 270, 30);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 290, 100, 290, 72);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 290, 100, 290, 72);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout DragNDrop root1 from top to bottom. root1 As target,
     * root2 as source. root1 is above edge before move and below edge after
     * move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to bottom and
     * to right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropTargetRoot1TopToBottomEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 220, 100, 220, 47);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 240/170
        editor.drag(root1EditPart, 240, 170);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 260, 100, 260, 170);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 100, 260, 100, 260, 170);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout DragNDrop root1 from top to bottom. root1 As target,
     * root2 as source. root1 is above edge before move and below edge after
     * move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to bottom and
     * to right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropTargetRoot1TopToBottomEdgeBellow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 305, 175, 305, 120);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 300/240
        editor.drag(root1EditPart, 300, 240);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 335, 175, 335, 240);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 335, 175, 335, 240);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout DragNDrop root1 from top to top. root1 As target, root2
     * as source. root1 is above edge before move and above edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to top and to
     * right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testTreeLayoutDragNDropTargetRoot1TopToTopEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 305, 175, 305, 120);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 320/5
        editor.drag(root1EditPart, 320, 5);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 355, 175, 355, 75);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BREAKDOWN_ABOVE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 185, 120, 185, 175, 355, 175, 355, 75);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout TopToBottom DragNDrop root1 from bottom to bottom.
     * root1 As target, root2 as source. root1 is bellow edge before and after
     * move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to bottom and
     * to right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBottomDragNDropTargetRoot1BottomtoBottomEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 340, 215, 340, 255);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 400/350
        editor.drag(root1EditPart, 400, 350);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 435, 215, 435, 350);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 435, 215, 435, 350);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout TopToBottom DragNDrop root1 from bottom to top.
     * root1 As target, root2 as source. root1 is below edge before move and
     * above edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to top and to
     * right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBottomDragNDropTargetRoot1BottomToTopEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 340, 215, 340, 255);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 375/100
        editor.drag(root1EditPart, 375, 100);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 410, 215, 410, 170);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 410, 215, 410, 170);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout TopToBottom DragNDrop root2 from top to top. root1
     * As target, root2 as source. root2 is above edge before and after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to top and to
     * left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBottomDragNDropSourceRoot2ToptoTopEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 340, 215, 340, 255);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 50/50
        editor.drag(root2EditPart, 50, 50);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 85, 120, 85, 215, 340, 215, 340, 255);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 85, 120, 85, 215, 340, 215, 340, 255);
        // Close editor
        editor.close();
    }

    /**
     * Test Tree Layout TopToBottom DragNDrop root2 from top to bottom. root1 As
     * target, root2 as source. root2 is above edge before move and below edge
     * after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to bottom and
     * to left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBottomDragNDropSourceRoot2TopToBottomEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 170, 170, 170, 215, 340, 215, 340, 255);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 85/255
        editor.drag(root2EditPart, 85, 255);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 215, 340, 215, 340, 255);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 215, 340, 215, 340, 255);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout TopToBottom DragNDrop root1 from top to bottom.
     * root1 As target, root2 as source. root2 is below edge before move and
     * below edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to bottom and
     * to left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBottomDragNDropTargetRoot1TopToBottomEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 340, 210, 340, 255);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 230/325
        editor.drag(root1EditPart, 230, 325);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 265, 210, 265, 325);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 265, 210, 265, 325);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout TopToBottom DragNDrop root1 from top to top. root1
     * As target, root2 as source. root1 is below edge before move and above
     * edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to top and to
     * left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBottomDragNDropTargetRoot1TopToTopEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 340, 210, 340, 255);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 230/100
        editor.drag(root1EditPart, 230, 100);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 265, 210, 265, 170);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 265, 210, 265, 170);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout TopToBottom DragNDrop root2 from top to bottom.
     * root1 As target, root2 as source. root2 is below edge before move and
     * below edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to bottom and
     * to left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBottomDragNDropSourceRoot2TopToBottomEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 340, 210, 340, 255);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop to 30/350
        editor.drag(root2EditPart, 30, 350);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 65, 350, 65, 210, 340, 210, 340, 255);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 65, 350, 65, 210, 340, 210, 340, 255);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout TopToBottom DragNDrop root2 from top to top. root1
     * As target, root2 as source. root2 is below edge before move and above
     * edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to top and to
     * left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutTopToBotttomDragNDropSourceRoot2TopToTopEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 120, 255, 120, 210, 340, 210, 340, 255);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 50/100
        editor.drag(root2EditPart, 50, 100);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 85, 170, 85, 210, 340, 210, 340, 255);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_TOP_TO_BOTTOM, REPRESENTATION_NAME_BREAKDOWN_TOP_TO_BOTTOM_BELOW_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 85, 170, 85, 210, 340, 210, 340, 255);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout BottomToTop DragNDrop root2 from bottom to bottom.
     * root1 As target, root2 as source. root2 is bellow edge before and after
     * move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to bottom and
     * to right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomToTopDragNDropSourceRoot2BottomtoBottomEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 250, 145, 250, 100);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 515/275
        editor.drag(root2EditPart, 515, 275);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 550, 275, 550, 145, 250, 145, 250, 100);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 550, 275, 550, 145, 250, 145, 250, 100);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout BottomToTop DragNDrop root2 from bottom to top.
     * root1 As target, root2 as source. root2 is below edge before move and
     * above edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to top and to
     * right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomToTopDragNDropSourcetRoot2BottomToTopEdgeAbove() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 250, 145, 250, 100);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 500/30
        editor.drag(root2EditPart, 500, 30);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 535, 100, 535, 145, 250, 145, 250, 100);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 535, 100, 535, 145, 250, 145, 250, 100);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout BottomToTop DragNDrop root2 from top to bottom.
     * root1 As target, root2 as source. root1 is above edge before move and
     * below edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to bottom and
     * to right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomToTopDragNDropSourceRoot2TopToBottomEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 190, 195, 190, 150);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 440/270
        editor.drag(root2EditPart, 440, 270);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 475, 270, 475, 195, 190, 195, 190, 150);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 475, 270, 475, 195, 190, 195, 190, 150);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout DragNDrop root2 from top to top. root1 As target,
     * root2 as source. root2 is above edge before move and above edge after
     * move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root2 and Drag and Drop node root2 to top and to
     * right</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomToTopDragNDropSourceRoot2TopToTopEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 190, 195, 190, 150);
        // Select node root2
        editor.getSelectableEditPart(NODE_NAME_ROOT2).select();
        SWTBotGefEditPart root2EditPart = editor.getEditPart(NODE_NAME_ROOT2, AbstractDiagramNodeEditPart.class);
        // Drag and drop root2 to 450/35
        editor.drag(root2EditPart, 450, 35);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 485, 105, 485, 195, 190, 195, 190, 150);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 485, 105, 485, 195, 190, 195, 190, 150);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout BottomToTop DragNDrop root1 from top to top. root1
     * As target, root2 as source. root1 is above edge before and after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to top and to
     * left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomToTopDragNDropTargetRoot1ToptoTopEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 250, 145, 250, 100);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 110/5
        editor.drag(root1EditPart, 110, 5);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 145, 145, 145, 75);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 145, 145, 145, 75);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout BottomToTop DragNDrop root1 from top to bottom.
     * root1 As target, root2 as source. root1 is above edge before move and
     * below edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to bottom and
     * to left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomToTopDragNDropTargetRoot1TopToBottomEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class,
                true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 250, 145, 250, 100);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 45/200
        editor.drag(root1EditPart, 45, 200);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 80, 145, 80, 200);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 450, 200, 450, 145, 80, 145, 80, 200);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout BottomToTop DragNDrop root1 from top to bottom.
     * root1 As target, root2 as source. root1 is above edge before move and
     * below edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to bottom and
     * to left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomtoTopDragNDropTargetRoot1TopToBottomEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 190, 195, 190, 150);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 65/230
        editor.drag(root1EditPart, 65, 230);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 100, 195, 100, 230);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 100, 195, 100, 230);
        // Close editor
        editor.close();
    }

    /**
     * Test Composite Layout BottomToTop DragNDrop root1 from top to top. root1
     * As target, root2 as source. root1 is above edge before move and above
     * edge after move.
     * <ol>
     * <li>Step 1 : Check edge position</li>
     * <li>Step 2 : Select node root1 and Drag and Drop node root1 to top and to
     * left</li>
     * <li>Step 3 : Check edge position</li>
     * <li>Step 4 : Close editor and reopen editor</li>
     * <li>Step 5 : Check edge position</li>
     * </ol>
     */
    public void testCompositeLayoutBottomToTopDragNDropTargetRoot1TopToTopEdgeBelow() {
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE,
                DDiagram.class, true);
        // Retrieve edge point location.
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 190, 195, 190, 150);
        // Select node root1
        editor.getSelectableEditPart(NODE_NAME_ROOT1).select();
        SWTBotGefEditPart root1EditPart = editor.getEditPart(NODE_NAME_ROOT1, AbstractDiagramNodeEditPart.class);
        // Drag and drop root1 to 75/20
        editor.drag(root1EditPart, 75, 20);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 110, 195, 110, 90);
        // Save and close representation
        editor.saveAndClose();
        // Open editor
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOTTOM_TO_TOP, REPRESENTATION_NAME_BREAKDOWN_BOTTOM_TO_TOP_ABOVE_EDGE, DDiagram.class, true);
        // Retrieve edge point location
        checkPositionEdge(editor, NODE_NAME_ROOT2, NODE_NAME_ROOT1, 390, 150, 390, 195, 110, 195, 110, 90);
        // Close editor
        editor.close();
    }

    private void checkPositionEdge(SWTBotSiriusDiagramEditor editor, String source, String target, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3) {
        PointList edgePoints = edgePoints(editor, source, target);
        // This points correspond to edge bendpoints. Point0 correspond to
        // source and point3 correspond to target.
        Point point0 = new Point(x0, y0);
        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);
        Point point3 = new Point(x3, y3);
        assertEquals("The point must be have coordinates x :" + point0.x + " and y : " + point0.y, point0, edgePoints.getPoint(0));
        assertEquals("The point must be have coordinates x :" + point1.x + " and y : " + point1.y, point1, edgePoints.getPoint(1));
        assertEquals("The point must be have coordinates x :" + point2.x + " and y : " + point2.y, point2, edgePoints.getPoint(2));
        assertEquals("The point must be have coordinates x :" + point3.x + " and y : " + point3.y, point3, edgePoints.getPoint(3));
    }

    private PointList edgePoints(SWTBotSiriusDiagramEditor editor, String sourceEditPartName, String targetEditPartName) {

        ConnectionEditPart connectionEditPart = getConnectionEditPart(editor, sourceEditPartName, targetEditPartName);
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        return ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();

    }

    private ConnectionEditPart getConnectionEditPart(SWTBotSiriusDiagramEditor editor, String sourceEditPartName, String targetEditPartName) {
        assertNotNull("There is no connection between " + sourceEditPartName + " and " + targetEditPartName, getConnectionEditPartList(editor, sourceEditPartName, targetEditPartName));
        assertEquals("There are more or less than 1 connection between " + sourceEditPartName + " and " + targetEditPartName, 1,
                getConnectionEditPartList(editor, sourceEditPartName, targetEditPartName).size());
        return getConnectionEditPartList(editor, sourceEditPartName, targetEditPartName).get(0).part();
    }

    private List<SWTBotGefConnectionEditPart> getConnectionEditPartList(SWTBotSiriusDiagramEditor editor, String sourceEditPart, String targetEditPart) {
        return editor.getConnectionEditPart(editor.getEditPart(sourceEditPart, AbstractDiagramNodeEditPart.class), editor.getEditPart(targetEditPart, AbstractDiagramNodeEditPart.class));
    }

}
