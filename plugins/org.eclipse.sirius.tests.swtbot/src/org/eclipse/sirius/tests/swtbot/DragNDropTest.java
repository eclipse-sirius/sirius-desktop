/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.DiagramWithChildrensCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Test class which checks drag&drop from Model Content to the diagram.
 * 
 * @author lchituc
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DragNDropTest extends AbstractSiriusSwtBotGefTestCase {

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private static final String REPRESENTATION_INSTANCE_2BLANK = "new TC1041 representation 2 Blank";

    private static final String REPRESENTATION_INSTANCE_5BLANK = "new TC1041 representation 5 Blank";

    private static final String TEST_NO_NPE_RAISED_DURING_DND = "Test no NPE raised during DND";

    private static final String REPRESENTATION_INSTANCE_6BLANK = "new TC1041 representation 6 Blank";

    private static final String REPRESENTATION_INSTANCE_7BLANK = "new TC1041 representation 7 Blank";

    private static final String REPRESENTATION_NAME_2 = "TC1041 representation 2 Blank";

    private static final String REPRESENTATION_NAME_3 = "TC1041 representation 3";

    private static final String REPRESENTATION_NAME_5 = "TC1041 representation 5 Blank";

    private static final String REPRESENTATION_NAME_6 = "TC1041 representation 6 Blank";

    private static final String REPRESENTATION_NAME_7 = "TC1041 representation 7 Blank";

    private static final String MODEL = "tc1041.ecore";

    private static final String SESSION_FILE = "tc1041.aird";

    private static final String VSM_FILE = "tc1041.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/tc-1041/";

    private static final String FILE_DIR = "/";

    private static final String CONTAINER_TO_DRAG_P1 = "P1";

    private static final String CONTAINER_TO_DRAG_P2 = "P2";

    private static final String CONTAINER_TO_DRAG_P3 = "P2.1";

    private static final String CLASS_TO_DRAG_C1 = "C1";

    private static final String ROOTPACKAGE_NAME = "Package";

    private UIResource ecoreEcoreResource;

    private SWTBotTreeItem semanticResourceNode;

    /**
     * True if SnapToGrid is activated on editor, false otherwise.
     */
    protected boolean snapToGrid;

    /**
     * Step used for the grid spacing.
     */
    protected static final int GRID_STEP = 20;

    /**
     * A selection listener to detect if the diagram is selected during the drag'n'drop.
     * 
     * @author lredor
     */
    private class NoDiagramSelectionListener implements ISelectionListener {

        String errorMessage = null;

        /**
         * Default constructor.
         */
        public NoDiagramSelectionListener() {
        }

        @Override
        public void selectionChanged(IWorkbenchPart part, ISelection selection) {
            if (errorMessage == null) {
                if (selection instanceof StructuredSelection) {
                    StructuredSelection structuredSelection = (StructuredSelection) selection;
                    if (structuredSelection.size() != 1) {
                        errorMessage = "Only one element must be selected during the drag'n'drop from a DDiagramContainer to another one (but was <" + structuredSelection.size() + ">.";
                    } else if (structuredSelection.getFirstElement() instanceof DDiagramEditPart) {
                        errorMessage = "The diagram must never be selected during the drag'n'drop from a DDiagramContainer to another one.";
                    }
                } else {
                    errorMessage = "The selection must be StructuredSelection during the drag'n'drop from a DDiagramContainer to another one.";
                }
            }
        }

        public Optional<String> getError() {
            return Optional.ofNullable(errorMessage);
        }

    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        ecoreEcoreResource = new UIResource(designerProject, MODEL);
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
    }

    /**
     * Open "TC1041 representation 2 Blank" diagram.
     */
    private void openRepresentation2() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_2, REPRESENTATION_INSTANCE_2BLANK, DDiagram.class);
        if (snapToGrid) {
            editor.setSnapToGrid(true, GRID_STEP, 2);
        } else {
            editor.setSnapToGrid(false);
        }
    }

    /**
     * Open "TC1041 representation 5 Blank" diagram.
     */
    private void openRepresentation5() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_5, REPRESENTATION_INSTANCE_5BLANK, DDiagram.class);
        if (snapToGrid) {
            editor.setSnapToGrid(true, GRID_STEP, 2);
        } else {
            editor.setSnapToGrid(false);
        }
    }

    /**
     * Open "Test no NPE raised during DND" diagram.
     */
    private void openRepresentation3() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_3, TEST_NO_NPE_RAISED_DURING_DND, DDiagram.class, !snapToGrid);
    }

    /**
     * Open "TC1041 representation 6 Blank" diagram.
     */
    private void openRepresentation6() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_6, REPRESENTATION_INSTANCE_6BLANK, DDiagram.class);
        if (snapToGrid) {
            editor.setSnapToGrid(true, GRID_STEP, 2);
        } else {
            editor.setSnapToGrid(false);
        }
    }

    /**
     * Open "TC1041 representation 7 Blank" diagram.
     */
    private void openRepresentation7() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_7, REPRESENTATION_INSTANCE_7BLANK, DDiagram.class);
        if (snapToGrid) {
            editor.setSnapToGrid(true, GRID_STEP, 2);
        } else {
            editor.setSnapToGrid(false);
        }
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P1(EPackage) from the Model Content
     *             view to the diagram. This test is done on a "TC1041
     *             representation 2 Blank" diagram.
     */
    @Test
    public void test_DnDPackageFromMC2DiagramBlank2() throws Exception {

        openRepresentation2();

        startToListenErrorLog(true, true);

        // DnD P1(EPackage) from the Model Content view to the diagram
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).getNode(CONTAINER_TO_DRAG_P1);
        ecoreTreeItem.dragAndDrop(editor.getCanvas());

        bot.waitUntil(new DiagramWithChildrensCondition(editor, 1));
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

        IGraphicalEditPart p1EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P1, DNodeContainerEditPart.class).part();
        checkEditPartLocation(p1EditPart);
    }

    /**
     * Test the drag&drop of C1(EClass) from P1(EPackage) to P2(EPackage). The test is done on the "Test no NPE raised
     * during DND" diagram. P2 has been expanded vertically in order to have its center out of the editor display. This
     * test has been created in order to test the feedback in this particular case.<BR>
     * This test is OK on some configuration even without the fix (for example Oxygen and the corresponding TP).
     * 
     * @throws Exception
     *             In case of problem
     */
    @Test
    public void test_DnDNoNPEDuringFeedback_Diagram3() throws Exception {

        openRepresentation3();
        boolean errorCatchPreviouslyEnabled = isErrorCatchActive();
        try {
            // In the diagram, DnD C1(EClass) from P1(EPackage) to P2(EPackage)
            SWTBotGefEditPart eClassBorderNodeEditPart = editor.getEditPart(CLASS_TO_DRAG_C1).parent();
            SWTBotGefEditPart targetEPackageNodeEditPart = editor.getEditPart(CONTAINER_TO_DRAG_P2, AbstractDiagramNodeEditPart.class);

            Point sourceLocation = editor.getBounds(eClassBorderNodeEditPart).getLocation();

            Rectangle endBounds = ((GraphicalEditPart) targetEPackageNodeEditPart.part()).getFigure().getBounds();
            Point endLocation = endBounds.getCenter();

            // Find the center of the visible part of P2
            IFigure rootFigure = ((AbstractGraphicalEditPart) targetEPackageNodeEditPart.part().getRoot()).getFigure();
            if (!rootFigure.getBounds().contains(endLocation)) {
                Rectangle intersection = rootFigure.getBounds().intersect(endBounds);
                if (!intersection.isEmpty()) {
                    endLocation = intersection.getCenter();
                }
            }
            // Activate the error catch to detect the potential NPE fixed by the
            // previous commit
            setErrorCatchActive(true);
            eClassBorderNodeEditPart.click();
            SWTBotUtils.waitAllUiEvents();
            // Add a selection listener to detect wrong diagram selection during drag'n'drop
            NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(sourceLocation, endLocation);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }
            SWTBotUtils.waitAllUiEvents();

        } finally {
            setErrorCatchActive(errorCatchPreviouslyEnabled);
        }

    }

    /**
     * Test that a move of a label on border, when the cursor is on the border of the label, ie a cursor with four
     * arrows, doesn't trigger a drag'n'drop of its parent.
     * 
     * @throws Exception
     *             In case of problem
     */
    @Test
    public void test_MoveLabelOnBorderWith4ArrowsIcon() throws Exception {

        openRepresentation3();
        boolean errorCatchPreviouslyEnabled = isErrorCatchActive();
        try {
            int nbDiagramChildrenBeforeMove = editor.mainEditPart().children().size();
            // In the diagram, move label of C1(EClass) of 50 pixels
            SWTBotGefEditPart labelEClassBorderNodeEditPart = editor.getEditPart(CLASS_TO_DRAG_C1);
            Point sourceLocation = editor.getBounds(labelEClassBorderNodeEditPart).getLocation().getTranslated(10, 0);
            Point endLocation = sourceLocation.getTranslated(0, 50);

            // Activate the error catch to detect the potential NPE fixed by the
            // previous commit
            setErrorCatchActive(true);
            labelEClassBorderNodeEditPart.click();
            SWTBotUtils.waitAllUiEvents();
            // Add a selection listener to detect wrong diagram selection during drag'n'drop
            NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(sourceLocation, endLocation);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }
            SWTBotUtils.waitAllUiEvents();
            // Asserts that C1 graphical element has not been created on the diagram (ie that a drag'n'drop does not
            // occur)
            assertEquals("It seems that a drag'n'drop wrongly occurs after a move of a label, wrong number of diagram children.", nbDiagramChildrenBeforeMove, editor.mainEditPart().children().size());
            assertFalse("The label has not been moved.", sourceLocation.equals(editor.getBounds(labelEClassBorderNodeEditPart).getLocation().getTranslated(10, 0)));
        } finally {
            setErrorCatchActive(errorCatchPreviouslyEnabled);
        }

    }

    /**
     * @throws Exception
     *             Test the drag&drop of P2 from the Model Content view to P1 previously created. This test is done on a
     *             "TC1041 representation 2 Blank" diagram.
     */
    @Test
    public void test_DnDPackageFromMC2ContainerBlank2() throws Exception {
        test_DnDPackageFromMC2DiagramBlank2();

        startToListenErrorLog(true, true);

        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point p1Location = editor.getBounds(p1Bot).getLocation();

        // DnD P2(EPackage) from the Model Content view to P1
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem1 = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).getNode(CONTAINER_TO_DRAG_P2);
        ecoreTreeItem1.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(p1Location.x + 25, p1Location.y + 25));
        bot.waitUntil(new DiagramWithChildrensCondition(editor, 1));

        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

        IGraphicalEditPart p2EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P2, DNodeContainerEditPart.class).part();
        checkEditPartLocation(p2EditPart);
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P2 from the Model Content view to P1 previously created. This test is done on a
     *             "TC1041 representation 2 Blank" diagram.
     */
    @Test
    public void test_DnDPackageFromMC2ContainerBlank2_zoom200() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        test_DnDPackageFromMC2DiagramBlank2();

        try {
            editor.zoom(ZoomLevel.ZOOM_200);

            startToListenErrorLog(true, true);

            // Get the location of P1
            SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();

            editor.reveal(p1Bot.part());
            Point targetLocation = editor.getBounds(p1Bot).getCenter();

            // DnD P2(EPackage) from the Model Content view to P1
            final SWTBotTreeItem ecoreTreeItem1 = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).getNode(CONTAINER_TO_DRAG_P2);
            ecoreTreeItem1.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(targetLocation.x, targetLocation.y));
            bot.waitUntil(new DiagramWithChildrensCondition(editor, 1));
            bot.waitUntil(new CheckNumberOfDescendants(p1Bot, AbstractDiagramNodeEditPart.class, 1));

            // Get the location of P2
            SWTBotGefEditPart p2Bot = editor.getEditPart(CONTAINER_TO_DRAG_P2).parent();
            Point p2Location = editor.getBounds(p2Bot).getTopLeft();

            if (!snapToGrid) {
                assertEquals(targetLocation.x, p2Location.x);
                assertEquals(targetLocation.y, p2Location.y);
            }

            assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

            IGraphicalEditPart p2EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P2, DNodeContainerEditPart.class).part();
            checkEditPartLocation(p2EditPart);
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
            editor.click(10, 10);
        }
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P2 from P1 to the diagram. This test is done on a "TC1041 representation 2
     *             Blank" diagram.
     */
    @Test
    public void test_DnDNodeFromContainer2DiagramBlank2() throws Exception {
        test_DnDPackageFromMC2ContainerBlank2();

        List<SWTBotGefEditPart> allEditParts = editor.mainEditPart().children();

        assertEquals("Bad number of elements!", 1, allEditParts.size());

        startToListenErrorLog(true, true);

        // DnD P2 from P1 to the diagram
        // I had to move P2.1 because in the previous Dnd it was moved out
        // of P1 but not to POINT_ON_DIAGRAM coordinates. As a matter of
        // fact there is a layout manager bug.

        // Get the location of P2
        SWTBotGefEditPart p2Bot = editor.getEditPart(CONTAINER_TO_DRAG_P2).parent();

        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point targetLocation = editor.getBounds(p1Bot).getTopRight().getTranslated(10, 0);

        // Add a selection listener to detect wrong diagram selection during drag'n'drop
        NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
        EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
        try {
            editor.drag(p2Bot, targetLocation);
            allEditParts = editor.mainEditPart().children();

            assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
            assertEquals("Bad number of elements!", 2, allEditParts.size());

            IGraphicalEditPart p2EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P2, DNodeContainerEditPart.class).part();
            // TODO remove this condition once #521802 is fixed.
            if (!snapToGrid) {
                checkEditPartLocation(p2EditPart);
            }
        } finally {
            removeSelectionListenerAndCheckIt(selectionListener);
        }
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P2 from the diagram to P1. This test is done on a "TC1041 representation 2
     *             Blank" diagram.
     */
    @Test
    public void test_DnDContainerFromDiagram2ContainerBlank2() throws Exception {
        test_DnDNodeFromContainer2DiagramBlank2();

        startToListenErrorLog(true, true);

        SWTBotGefEditPart sourceSwtBotPart = editor.getEditPart(CONTAINER_TO_DRAG_P2, AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart targetSwtBotPart = editor.getEditPart(CONTAINER_TO_DRAG_P1, AbstractDiagramContainerEditPart.class);

        final IGraphicalEditPart targetPart = (IGraphicalEditPart) targetSwtBotPart.part();
        Point targetCenter = targetPart.getFigure().getBounds().getCenter();

        // Add a selection listener to detect wrong diagram selection during drag'n'drop
        NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
        EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
        try {
            // DnD P2 from the diagram to P1
            editor.drag(sourceSwtBotPart, targetCenter);
    
            assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    
            IGraphicalEditPart p2EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P2, DNodeContainerEditPart.class).part();
            checkEditPartLocation(p2EditPart);
        } finally {
            removeSelectionListenerAndCheckIt(selectionListener);
        }
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P2 from the diagram to P1. This test is done using a "TC1041 representation 2
     *             Blank" diagram.
     */
    @Test
    public void test_DnDContainerFromContainer2ContainerBlank2() throws Exception {
        test_DnDContainerFromDiagram2ContainerBlank2();

        startToListenErrorLog(true, true);

        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point targetLocation = editor.getBounds(p1Bot).getLeft().getTranslated(-200, 0);

        // DnD P2.1(EPackage) from the Model Content view to the diagram
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).expandNode(CONTAINER_TO_DRAG_P1).expandNode(CONTAINER_TO_DRAG_P2).getNode(CONTAINER_TO_DRAG_P3);
        ecoreTreeItem.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(targetLocation.x, targetLocation.y));

        bot.waitUntil(new DiagramWithChildrensCondition(editor, 2));
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

        IGraphicalEditPart p3EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P3, DNodeContainerEditPart.class).part();
        checkEditPartLocation(p3EditPart);

        // Get the location of P2
        SWTBotGefEditPart p2Bot = editor.getEditPart(CONTAINER_TO_DRAG_P2).parent();
        Point p2Location = editor.getBounds(p2Bot).getCenter();

        SWTBotGefEditPart targetSwtBotPart = editor.getEditPart(CONTAINER_TO_DRAG_P3, AbstractDiagramContainerEditPart.class);
        final IGraphicalEditPart targetPart = (IGraphicalEditPart) targetSwtBotPart.part();
        Point targetCenter = targetPart.getFigure().getBounds().getCenter();

        // Click on p2 (to be already selected. Otherwise, there is "unexpected" selection thrown during the
        // drag'n'drop because of SWTBot method).
        editor.click(p2Location);
        // Add a selection listener to detect wrong diagram selection during drag'n'drop
        NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
        EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
        try {
            // DnD P2 from P1 to P2.1
            editor.drag(p2Location, targetCenter);
    
            assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    
            IGraphicalEditPart p2EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P2, DNodeContainerEditPart.class).part();
            checkEditPartLocation(p2EditPart);
        } finally {
            removeSelectionListenerAndCheckIt(selectionListener);
        }
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P2 from the diagram to P1. This test is done using a "TC1041 representation 2
     *             Blank" diagram.
     */
    @Test
    public void test_DnDContainerFromContainer2ContainerBlank2_zoom200() throws Exception {
        test_DnDContainerFromDiagram2ContainerBlank2();

        try {
            editor.click(10, 10);
            editor.zoom(ZoomLevel.ZOOM_200);
            startToListenErrorLog(true, true);

            editor.reveal(CONTAINER_TO_DRAG_P1);

            // Get the location of P1
            SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
            Point targetLocation = editor.getBounds(p1Bot).getLeft().getTranslated(-200, 0);

            // DnD P2.1(EPackage) from the Model Content view to the diagram
            semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
            final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).expandNode(CONTAINER_TO_DRAG_P1).expandNode(CONTAINER_TO_DRAG_P2).getNode(CONTAINER_TO_DRAG_P3);
            ecoreTreeItem.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(targetLocation.x, targetLocation.y));
            bot.waitUntil(new CheckNumberOfDescendants(p1Bot, AbstractDiagramNodeEditPart.class, 1));

            bot.waitUntil(new DiagramWithChildrensCondition(editor, 2));
            assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

            IGraphicalEditPart p3EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P3, DNodeContainerEditPart.class).part();
            checkEditPartLocation(p3EditPart);

            SWTBotGefEditPart p21Bot = editor.getEditPart(CONTAINER_TO_DRAG_P3, AbstractDiagramContainerEditPart.class);
            final IGraphicalEditPart targetPart = (IGraphicalEditPart) p21Bot.part();
            editor.reveal(targetPart);

            // Get the location of P2
            SWTBotGefEditPart p2Bot = editor.getEditPart(CONTAINER_TO_DRAG_P2).parent();
            Point p2Location = editor.getBounds(p2Bot).getCenter();

            Point targetCenter = editor.getBounds(p21Bot).getCenter();

            // Click on p2 (to be already selected. Otherwise, there is "unexpected" selection thrown during the
            // drag'n'drop because of SWTBot method).
            editor.click(p2Location);
            // Add a selection listener to detect wrong diagram selection during drag'n'drop
            NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                // DnD P2 from P1 to P2.1
                editor.drag(p2Location, targetCenter);
    
                // Get the location of P2
                p2Bot = editor.getEditPart(CONTAINER_TO_DRAG_P2).parent();
                p2Location = editor.getBounds(p2Bot).getCenter();
                bot.waitUntil(new CheckNumberOfDescendants(p1Bot, AbstractDiagramNodeEditPart.class, 0));
                bot.waitUntil(new CheckNumberOfDescendants(p21Bot, AbstractDiagramNodeEditPart.class, 1));
    
                // If the snap is activated, the location will not be the expected one since the view will be snapped on the
                // grid.
                // The snap will be check in checkEditPartLocation method.
                if (!snapToGrid) {
                    assertEquals(targetCenter.x, p2Location.x);
                    assertEquals(targetCenter.y, p2Location.y);
                }
    
                assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    
                IGraphicalEditPart p2EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P2, DNodeContainerEditPart.class).part();
                checkEditPartLocation(p2EditPart);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
            editor.click(10, 10);
        }
    }

    /**
     * @throws Exception
     *             Asserts that the DnD of an EClass (C1) from the Model Content view to the diagram is not allowed
     *             using "TC1041 representation 2 Blank" diagram.
     */
    @Test
    public void test_DnDEClassFromMC2DiagramBlank2() throws Exception {

        openRepresentation2();

        startToListenErrorLog(true, true);

        // DnD C1(EClass) from the Model Content view to P2. This
        // move shall not be allowed. Asserts that no error message is
        // generated
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).expandNode(CONTAINER_TO_DRAG_P1).getNode(CLASS_TO_DRAG_C1);
        ecoreTreeItem.dragAndDrop(editor.getCanvas());

        // Asserts that C1 graphical element was not created on the diagram
        // and no error
        // message was generated
        List<SWTBotGefEditPart> allEditParts = editor.mainEditPart().children();

        assertEquals("Bad number of elements!", 0, allEditParts.size());
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    }

    /**
     * @throws Exception
     *             Asserts that the DnD of an EClass(C1) from the Model Content view inside a container (P1) is not
     *             allowed using "TC1041 representation 2 Blank" diagram.
     */
    @Test
    public void test_DnDEClassMC2ContainerBlank2() throws Exception {

        test_DnDPackageFromMC2DiagramBlank2();

        startToListenErrorLog(true, true);

        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point p1Location = editor.getBounds(p1Bot).getLocation();

        // DnD C1(EClass) from the Model Content view to P2. This
        // move shall not be allowed. Asserts that no error message is
        // generated
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).expandNode(CONTAINER_TO_DRAG_P1).getNode(CLASS_TO_DRAG_C1);
        ecoreTreeItem.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(p1Location.x + 25, p1Location.y + 25));

        // Asserts that C1 was not created as child of P1 and no error
        // message was generated
        SWTBotGefEditPart targetSwtBotPart = editor.getEditPart(CONTAINER_TO_DRAG_P1, AbstractDiagramContainerEditPart.class);
        Iterable<AbstractDiagramNodeEditPart> filter = Iterables.filter(((CompartmentEditPart) targetSwtBotPart.part().getChildren().get(1)).getChildren(), AbstractDiagramNodeEditPart.class);

        assertEquals("Bad number of elements", 0, Sets.newLinkedHashSet(filter).size());
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P1(EPackage) from the Model Content view to the diagram. This test is done
     *             using a "TC1041 representation 5 Blank" diagram.
     */
    @Test
    public void test_DnDPackageFromMC2DiagramBlank5() throws Exception {

        openRepresentation5();

        startToListenErrorLog(true, true);

        // DnD P1(EPackage) from the Model Content view to the diagram
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).getNode(CONTAINER_TO_DRAG_P1);
        ecoreTreeItem.dragAndDrop(editor.getCanvas());

        bot.waitUntil(new DiagramWithChildrensCondition(editor, 1));
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

        IGraphicalEditPart p1EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P1, DNodeContainerEditPart.class).part();
        checkEditPartLocation(p1EditPart);
    }

    /**
     * @throws Exception
     *             Test the drag&drop of C1 from the Model Content view to P1 previously created. This test is done
     *             using a "TC1041 representation 5 Blank" diagram.
     */
    @Test
    public void test_DnDClassFromMC2ContainerBlank5() throws Exception {
        test_DnDPackageFromMC2DiagramBlank5();

        startToListenErrorLog(true, true);

        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point p1Location = editor.getBounds(p1Bot).getLocation();

        // DnD P2(EPackage) from the Model Content view to P1
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).expandNode(CONTAINER_TO_DRAG_P1).getNode(CLASS_TO_DRAG_C1);
        ecoreTreeItem.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(p1Location.x + 25, p1Location.y + 25));
        bot.waitUntil(new DiagramWithChildrensCondition(editor, 1));

        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

        IGraphicalEditPart c1EditPart = (IGraphicalEditPart) editor.getEditPart(CLASS_TO_DRAG_C1, DNodeContainerEditPart.class).part();
        checkEditPartLocation(c1EditPart);
    }

    /**
     * @throws Exception
     *             Test the drag&drop of C1 from P1 to P2 . This test is done using a "TC1041 representation 5 Blank"
     *             diagram.
     */
    @Test
    public void test_DnDClassFromContainer2ContainerBlank5() throws Exception {
        test_DnDClassFromMC2ContainerBlank5();

        startToListenErrorLog(true, true);
        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point targetLocation = editor.getBounds(p1Bot).getLeft().getTranslated(-200, 0);

        // DnD P2(EPackage) from the Model Content view to the diagram
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).getNode(CONTAINER_TO_DRAG_P2);
        ecoreTreeItem.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(targetLocation.x, targetLocation.y));
        bot.waitUntil(new DiagramWithChildrensCondition(editor, 2));
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

        IGraphicalEditPart p2EditPart = (IGraphicalEditPart) editor.getEditPart(CONTAINER_TO_DRAG_P2, DNodeContainerEditPart.class).part();
        checkEditPartLocation(p2EditPart);

        // Get the location of C1
        SWTBotGefEditPart c1Bot = editor.getEditPart(CLASS_TO_DRAG_C1).parent();
        Point c1Location = editor.getBounds(c1Bot).getLocation();

        // Get the center of P2 (the target)
        SWTBotGefEditPart targetSwtBotPart = editor.getEditPart(CONTAINER_TO_DRAG_P2, AbstractDiagramContainerEditPart.class);
        final IGraphicalEditPart targetPart = (IGraphicalEditPart) targetSwtBotPart.part();
        Point targetCenter = targetPart.getFigure().getBounds().getCenter();

        // Click on C1 (to be already selected. Otherwise, there is "unexpected" selection thrown during the
        // drag'n'drop because of SWTBot method).
        editor.click(c1Location);
        // Add a selection listener to detect wrong diagram selection during drag'n'drop
        NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
        EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
        try {
            // DnD C1 from P1 to P2
            editor.drag(c1Location, targetCenter);

            assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());

            IGraphicalEditPart c1EditPart = (IGraphicalEditPart) editor.getEditPart(CLASS_TO_DRAG_C1, DNodeContainerEditPart.class).part();
            checkEditPartLocation(c1EditPart);
        } finally {
            removeSelectionListenerAndCheckIt(selectionListener);
        }
    }

    /**
     * @throws Exception
     *             Test the drag&drop of C1(EClass) from the Model Content view to the diagram. This move should not be
     *             allowed and no error message should be generated.
     */
    @Test
    public void test_DnDClassFromMC2DiagramBlank5() throws Exception {
        openRepresentation5();

        startToListenErrorLog(true, true);

        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        // DnD P1(EPackage) from the Model Content view to the diagram
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).expandNode(CONTAINER_TO_DRAG_P1).getNode(CLASS_TO_DRAG_C1);
        ecoreTreeItem.dragAndDrop(editor.getCanvas());

        // Asserts that C1 graphical element was not created on the diagram
        // and no error message was generated
        List<SWTBotGefEditPart> allEditParts = editor.mainEditPart().children();

        assertEquals("Bad number of elements!", 0, allEditParts.size());
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    }

    /**
     * Test the drag&drop of C1(EClass) from the Model Content view to the diagram. This move should be allowed (as it
     * is in a transient layer enabled by default) and no error message should be generated.
     * 
     * @throws Exception
     *             In case of problem
     */
    @Test
    public void test_DnDClassFromMC2DiagramBlank6FromATransientLayerTool() throws Exception {

        openRepresentation6();

        startToListenErrorLog(true, true);

        // DnD P1(EPackage) from the Model Content view to the diagram
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).expandNode(CONTAINER_TO_DRAG_P1).getNode(CLASS_TO_DRAG_C1);
        ecoreTreeItem.dragAndDrop(editor.getCanvas());

        // Asserts that C1 graphical element was created on the diagram and
        // no error message was generated
        List<SWTBotGefEditPart> allEditParts = editor.mainEditPart().children();

        assertEquals("Bad number of elements!", 1, allEditParts.size());
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    }

    /**
     * @throws Exception
     *             Test the drag&drop of P2 from the Model Content view to P1 previously created. This move should not
     *             be allowed and no error message should be generated.
     */
    @Test
    public void test_DnDPackageFromMC2ContainerBlank5() throws Exception {

        test_DnDPackageFromMC2DiagramBlank5();

        startToListenErrorLog(true, true);

        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point p1Location = editor.getBounds(p1Bot).getLocation();

        // DnD P2(EPackage) from the Model Content view to P1
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).getNode(CONTAINER_TO_DRAG_P2);
        ecoreTreeItem.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(p1Location.x + 25, p1Location.y + 25));

        // Asserts that P2 was not created as child of P1 and no error
        // message was generated
        SWTBotGefEditPart targetSwtBotPart = editor.getEditPart(CONTAINER_TO_DRAG_P1, AbstractDiagramContainerEditPart.class);
        Iterable<AbstractDiagramNodeEditPart> filter = Iterables.filter(((CompartmentEditPart) targetSwtBotPart.part().getChildren().get(1)).getChildren(), AbstractDiagramNodeEditPart.class);

        assertEquals("Bad number of elements", 0, Sets.newLinkedHashSet(filter).size());
        assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
    }

    /**
     * Test that the selection change behavior is OK even after a DnD tool that has an unexpected behavior, ie the drag
     * element is no longer here after the execution of the DnD tool.
     */
    @Test
    public void test_SelectionChangeBehaviorAfterADnDWithUnexpectedBehavior() throws Exception {

        openRepresentation7();

        startToListenErrorLog(true, true);

        // Access to property view
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();

        // Access to tab General
        SWTBotSiriusHelper.selectPropertyTabItem("General", propertiesView.bot());

        // Check that "Package" item is displayed in the General tab of Properties view
        propertiesView.bot().tree(0).getTreeItem("Package");

        // Get the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart(CONTAINER_TO_DRAG_P1).parent();
        Point p1Location = editor.getBounds(p1Bot).getLocation();

        // DnD P2(EPackage) from the Model Content view to P1
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem1 = semanticResourceNode.expandNode(ROOTPACKAGE_NAME).getNode(CONTAINER_TO_DRAG_P2);
        ecoreTreeItem1.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(p1Location.x + 25, p1Location.y + 25));
        bot.waitUntil(new DiagramWithChildrensCondition(editor, 1));

        assertFalse("An error message has been logged!", doesAWarningOccurs() || doesAnErrorOccurs());

        // Get the location of P2
        SWTBotGefEditPart p2Bot = editor.getEditPart(CONTAINER_TO_DRAG_P2).parent();

        Point targetLocation = editor.getBounds(p1Bot).getTopRight().getTranslated(10, 0);

        // Add a selection listener to detect wrong diagram selection during drag'n'drop
        NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
        EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
        try {
            editor.drag(p2Bot, targetLocation);
            List<SWTBotGefEditPart> allEditParts = editor.mainEditPart().children();

            assertFalse("An error message was generated !", doesAWarningOccurs() || doesAnErrorOccurs());
            // Check that the drag element P2 no longer exists
            assertEquals("Bad number of elements!", 1, allEditParts.size());

            // Select another element to check that notifications are thrown, Properties view is used to check this.
            editor.select(p1Bot);
            try {
                // Check that "P1" item is displayed in the General tab of Properties view
                propertiesView.bot().tree(0).getTreeItem("P1");
            } catch (WidgetNotFoundException e) {
                fail("The properties view should be refreshed and the name of the selected package P1 should be dislayed in it.");
            }
        } finally {
            removeSelectionListenerAndCheckIt(selectionListener);
        }
    }

    @Override
    @After
    public void tearDown() throws Exception {
        if (editor != null) {
            editor.close();
        }
        SWTBotUtils.waitAllUiEvents();
        localSession.close(false);
        super.tearDown();
    }

    /**
     * Check if EditPart is on Diagram.
     * 
     * @param editPart
     *            the edit part to check
     */
    protected void checkEditPartLocation(IGraphicalEditPart editPart) {
        assertNotNull("No container edit part found with this name", editPart);
    }

    /**
     * Check if an error message has been thrown by the selection listener and remove it from the active page.
     * 
     * @param selectionListener
     *            The selection listener to remove
     */
    private void removeSelectionListenerAndCheckIt(NoDiagramSelectionListener selectionListener) {
        EclipseUIUtil.getActivePage().removeSelectionListener(selectionListener);
        if (selectionListener.getError().isPresent()) {
            fail(selectionListener.getError().get());
        }
    }
}
