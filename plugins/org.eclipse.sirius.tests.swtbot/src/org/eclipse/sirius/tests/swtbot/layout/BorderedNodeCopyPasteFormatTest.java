/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.handler.ChangeFilterActivation;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test Bordered nodes Copy-Paste layout. (see VP-3601)
 * 
 * @author fbarbin
 */
public class BorderedNodeCopyPasteFormatTest extends AbstractSiriusSwtBotGefTestCase {

    /** a1 border Node location on second diagram with extension. */
    private static final Point A1_SECOND_DIAG_GMF_LOCATION = new Point(48, -2);

    /** a1 border Node GMF location on second diagram with extension. */
    private static final Point A1_SECOND_DIAG_LOCATION = new Point(103, 28);

    /** a1 border Node location on first diagram. */
    private static final Point B1_FIRST_DIAG_GMF_LOCATION = new Point(1, -2);

    /** a1 border Node GMF location on first diagram. */
    private static final Point B1_FIRST_DIAG_LOCATION = new Point(56, 28);

    /** First point used to copy format. */
    private static final Point COPY_POINT = new Point(610, 100);

    /** Second point used to paste format. */
    private static final Point PASTE_POINT = new Point(400, 400);

    private static final String FILE_DIR = "/";

    private static final String VSM = "VP-3601.odesign";

    private static final String MODEL = "VP-3601.ecore";

    private static final String SESSION_FILE = "VP-3601.aird";

    private static final String DATA_UNIT_DIR = "data/unit/layout/borderedNodesCopyPastLayout/";

    private static final String REPRESENTATION_NAME1 = "rep1";

    private static final String REPRESENTATION_NAME2 = "rep2";

    private static final String REPRESENTATION_NAME3 = "rep3AdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME4 = "rep4AdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Diagram";

    private static final String FILTER_NAME_INCORRECT = "The filter name is not correct";

    /** Diagram on first representation. */
    private SWTBotSiriusDiagramEditor diagramEditor1;

    /** Diagram on second representation. */
    private SWTBotSiriusDiagramEditor diagramEditor2;

    /** Diagram on third representation with extension. */
    private SWTBotSiriusDiagramEditor diagramEditor3;

    /** Diagram on second representation with extension. */
    private SWTBotSiriusDiagramEditor diagramEditor4;

    private ArrayList<Point> expectedAList, expectedBList, expectedAList_GMF, expectedBList_GMF;

    private ArrayList<Point> expectedCollapsedNodeList, expectedNonCollapsedNodeList, expectedCollapsedNodeList_GMF, expectedNonCollapsedNodeList_GMF;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Set expected bordered nodes locations for first container and second
        // container.
        expectedAList = new ArrayList<Point>();
        expectedAList.add(0, new Point(53, 30));
        expectedAList.add(1, new Point(253, 114));
        expectedAList.add(2, new Point(53, 114));
        expectedAList.add(3, new Point(53, 186));
        expectedAList.add(4, new Point(253, 186));
        expectedAList.add(5, new Point(247, 28));

        expectedAList_GMF = new ArrayList<Point>();
        expectedAList_GMF.add(0, new Point(-2, 0));
        expectedAList_GMF.add(1, new Point(198, 84));
        expectedAList_GMF.add(2, new Point(-2, 84));
        expectedAList_GMF.add(3, new Point(-2, 156));
        expectedAList_GMF.add(4, new Point(198, 156));
        expectedAList_GMF.add(5, new Point(192, -2));

        expectedBList = new ArrayList<Point>();
        expectedBList.add(0, B1_FIRST_DIAG_LOCATION);
        expectedBList.add(1, new Point(53, 90));
        expectedBList.add(2, new Point(140, 28));
        expectedBList.add(3, new Point(140, 155));
        expectedBList.add(4, new Point(229, 78));
        expectedBList.add(5, new Point(56, 155));
        expectedBList.add(6, new Point(229, 150));
        expectedBList.add(7, new Point(229, 30));

        expectedBList_GMF = new ArrayList<Point>();
        expectedBList_GMF.add(0, B1_FIRST_DIAG_GMF_LOCATION);
        expectedBList_GMF.add(1, new Point(-2, 60));
        expectedBList_GMF.add(2, new Point(85, -2));
        expectedBList_GMF.add(3, new Point(85, 125));
        expectedBList_GMF.add(4, new Point(174, 48));
        expectedBList_GMF.add(5, new Point(1, 125));
        expectedBList_GMF.add(6, new Point(174, 120));
        expectedBList_GMF.add(7, new Point(174, 0));

        // expected node coordinates concerned by collapse filter (before
        // collapse activation)
        expectedNonCollapsedNodeList = new ArrayList<Point>();
        expectedNonCollapsedNodeList.add(0, new Point(253, 66));
        expectedNonCollapsedNodeList.add(1, new Point(53, 114));

        expectedNonCollapsedNodeList_GMF = new ArrayList<Point>();
        expectedNonCollapsedNodeList_GMF.add(0, new Point(198, 36));
        expectedNonCollapsedNodeList_GMF.add(1, new Point(-2, 84));

        // expected node coordinates concerned by collapse filter (after
        // collapse activation)
        expectedCollapsedNodeList = new ArrayList<Point>();
        expectedCollapsedNodeList.add(0, new Point(259, 70));
        expectedCollapsedNodeList.add(1, new Point(56, 118));

        expectedCollapsedNodeList_GMF = new ArrayList<Point>();
        expectedCollapsedNodeList_GMF.add(0, new Point(204, 40));
        expectedCollapsedNodeList_GMF.add(1, new Point(1, 88));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        diagramEditor1 = null;
        diagramEditor2 = null;
        diagramEditor3 = null;
        diagramEditor4 = null;
        expectedBList = null;
        expectedAList = null;
        expectedNonCollapsedNodeList = null;
        expectedCollapsedNodeList = null;
        clearFormatDataManager();
        super.tearDown();
    }

    /**
     * Test that the paste layout puts the elements at the expected location.
     * This test uses the copy-paste layout on Container.
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Run copy-paste from rep1 to rep2</li>
     * <li>Check bordered node locations after copy-paste on rep2 (also check on
     * one style attribute that there is non style change)</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayout() {
        // Open the 2 required representations
        diagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);
        // Check the color before copy layout
        Color yellow = new Color(null, 225, 225, 135);
        Color lightBlue = new Color(null, 194, 239, 255);
        try {
            checkBorderNodeColor("r", diagramEditor1, yellow, "Yellow", 1);
            checkBorderNodeColor("r", diagramEditor2, lightBlue, "Light Blue", 1);
            // Check LogicalFunction2 bordered nodes locations before the copy
            // layout.
            checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

            // Check borderedNode before collapse
            checkExtendedRBorderedNodes(diagramEditor1);
            DRepresentation representation = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1);

            // collapse r1 and r2 bordered nodes by activating filter "test"
            activateFilter((DDiagram) representation, "test");

            SWTBotUtils.waitAllUiEvents();
            // Check borderedNode after collapse
            checkCollapsedRBorderedNodes(diagramEditor1);

            // Copy LC1 layout
            diagramEditor1.show();
            diagramEditor1.click(new Point(100, 100));
            diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

            // Paste layout on second representation
            diagramEditor2.show();
            diagramEditor2.click(new Point(500, 250));
            diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

            // Copy LC2 layout
            diagramEditor1.show();
            diagramEditor1.click(new Point(604, 76));
            diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

            // Paste layout on second representation
            diagramEditor2.show();
            diagramEditor2.click(new Point(500, 250));
            diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

            // Check LogicalFunction1 bordered nodes locations after the paste
            // layout.
            checkFirstContainerABorderedNodes(diagramEditor2, expectedAList, expectedAList_GMF);

            // Check LogicalFunction2 bordered nodes locations after the paste
            // layout.
            checkSecondContainerABorderedNodes(diagramEditor2, expectedBList, expectedBList_GMF);

            // Check the color after paste layout (no change expected)
            checkBorderNodeColor("r", diagramEditor2, lightBlue, "Light Blue", 1);

            // Check borderedNode before collapse
            checkExtendedRBorderedNodes(diagramEditor2);
            representation = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2);

            // collapse r1 and r2 bordered nodes by activating filter "test"
            activateFilter((DDiagram) representation, "test");

            SWTBotUtils.waitAllUiEvents();
            // Check borderedNode after collapse
            checkCollapsedRBorderedNodes(diagramEditor2);
        } finally {
            yellow.dispose();
            lightBlue.dispose();
        }
    }

    /**
     * Test that the paste layout puts the elements at the expected location.
     * This test uses the copy-paste layout on Diagram.
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Run copy-paste from rep1 to rep2</li>
     * <li>Check bordered node locations after copy-paste on rep2</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutOnDiagram() {
        // Open the 2 required representations
        diagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor1);
        DRepresentation representation = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1);

        // collapse r1 and r2 bordered nodes by activating filter "test"
        activateFilter((DDiagram) representation, "test");

        SWTBotUtils.waitAllUiEvents();
        // Check borderedNode after collapse
        checkCollapsedRBorderedNodes(diagramEditor1);

        // Copy LC1 layout
        diagramEditor1.show();
        diagramEditor1.click(new Point(1, 1));
        diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

        // Paste layout on second representation
        diagramEditor2.show();
        diagramEditor2.click(new Point(100, 400));
        diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor2, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations after the paste
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor2, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor2);
        representation = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2);

        // collapse r1 and r2 bordered nodes by activating filter "test"
        activateFilter((DDiagram) representation, "test");

        SWTBotUtils.waitAllUiEvents();
        // Check borderedNode after collapse
        checkCollapsedRBorderedNodes(diagramEditor2);

    }

    /**
     * Test that the paste layout puts the elements at the expected location.
     * This test uses the copy-paste layout after "Select All".
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Run copy-paste from rep1 to rep2</li>
     * <li>Check bordered node locations after copy-paste on rep2</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutWithSelectAll() {
        // Open the 2 required representations
        diagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor1);
        DRepresentation representation = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1);

        // collapse r1 and r2 bordered nodes by activating filter "test"
        activateFilter((DDiagram) representation, "test");

        SWTBotUtils.waitAllUiEvents();
        // Check borderedNode after collapse
        checkCollapsedRBorderedNodes(diagramEditor1);

        // Copy LC1 layout
        diagramEditor1.show();
        diagramEditor1.click(new Point(1, 1));

        diagramEditor1.bot().toolbarDropDownButtonWithTooltip("Select &All").click();

        diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

        // Paste layout on second representation
        diagramEditor2.show();
        diagramEditor2.click(new Point(100, 400));
        diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor2, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations after the paste
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor2, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor2);
        representation = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2);

        // collapse r1 and r2 bordered nodes by activating filter "test"
        activateFilter((DDiagram) representation, "test");

        SWTBotUtils.waitAllUiEvents();
        // Check borderedNode after collapse
        checkCollapsedRBorderedNodes(diagramEditor2);

    }

    /**
     * Test that the paste layout puts the elements at the expected location,
     * even if the bordered nodes are hidden before. This test uses the
     * copy-paste layout after "Select All".
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Activate hide filter on "b" bordered node on rep2</li>
     * <li>Run copy-paste from rep1 to rep2</li>
     * <li>Deactivate the hide filter</li>
     * <li>Check bordered node locations after copy-paste on rep2</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutWithHiddenTargetNodes() {
        // Open the 2 required representations
        diagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor1);

        // Copy LC1 layout
        diagramEditor1.click(new Point(1, 1));

        diagramEditor1.bot().toolbarDropDownButtonWithTooltip("Select &All").click();

        diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

        // activate hide filter on representation 2

        final DRepresentation representation2 = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2);

        diagramEditor2.show();
        SWTBotUtils.waitAllUiEvents();
        // hide b bordered nodes by activating filter "testHide"
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                activateFilter((DDiagram) representation2, "testHide");
            }
        });
        SWTBotUtils.waitAllUiEvents();
        // TODO FBA check that nodes are correctly hidden

        // Paste layout on second representation
        diagramEditor2.click(new Point(100, 400));
        diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

        // reveal b bordered nodes by deactivating filter "testHide"
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                deactivateFilter((DDiagram) representation2, "testHide");
            }
        });
        SWTBotUtils.waitAllUiEvents();

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor2, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations after the paste
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor2, expectedBList, expectedBList_GMF);

        checkExtendedRBorderedNodes(diagramEditor2);

    }

    /**
     * Test that the paste layout puts the elements at the expected location,
     * even if the bordered nodes are collapsed before. This test uses the
     * copy-paste layout after "Select All".
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Activate collapse filter on "b" bordered node on rep2</li>
     * <li>Run copy-paste from rep1 to rep2</li>
     * <li>Deactivate the collapse filter</li>
     * <li>Check bordered node locations after copy-paste on rep2</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutWithCollapsedTargetNodes() {
        // Open the 2 required representations
        diagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor1);

        // Copy LC1 layout
        diagramEditor1.click(new Point(1, 1));

        diagramEditor1.bot().toolbarDropDownButtonWithTooltip("Select &All").click();

        diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

        final DRepresentation representation2 = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2);

        diagramEditor2.show();
        SWTBotUtils.waitAllUiEvents();

        // collapse "b" bordered nodes

        activateFilter((DDiagram) representation2, "testCollapseAtt");

        SWTBotUtils.waitAllUiEvents();

        // Paste layout on second representation
        diagramEditor2.click(new Point(100, 400));
        diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

        // extend bordered nodes by deactivating filter "testHide"
        deactivateFilter((DDiagram) representation2, "testCollapseAtt");

        SWTBotUtils.waitAllUiEvents();

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor2, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations after the paste
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor2, expectedBList, expectedBList_GMF);

        checkExtendedRBorderedNodes(diagramEditor2);

    }

    /**
     * Test that the copy-paste layout puts the elements at the expected
     * location if the bordered nodes are collapsed on the source diagram and
     * extended on the target diagram. This test uses the copy-paste layout
     * after "Select All".
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Activate collapse filter on "b" bordered node on rep1</li>
     * <li>Run copy-paste from rep1 to rep2</li>
     * <li>Check bordered node locations after copy-paste on rep2</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutWithCollapsedSourceNodes() {
        // Open the 2 required representations
        diagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor1);

        final DRepresentation representation1 = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1);
        // collapse "b" bordered nodes
        activateFilter((DDiagram) representation1, "testCollapseAtt");
        SWTBotUtils.waitAllUiEvents();

        // Copy LC1 layout
        diagramEditor1.click(new Point(1, 1));

        diagramEditor1.bot().toolbarDropDownButtonWithTooltip("Select &All").click();

        diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor2.show();
        SWTBotUtils.waitAllUiEvents();

        // Paste layout on second representation
        diagramEditor2.click(new Point(100, 400));
        diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

        SWTBotUtils.waitAllUiEvents();

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor2, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations after the paste
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor2, expectedBList, expectedBList_GMF);

        checkExtendedRBorderedNodes(diagramEditor2);

    }

    /**
     * Test that the copy-paste layout puts the elements at the expected
     * location if the bordered nodes are collapsed on the source diagram and
     * extended on the target diagram. This test uses the copy-paste layout
     * after "Select All".
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Activate collapse filter on "b" bordered node on rep1</li>
     * <li>Activate collapse filter on "b" bordered node on rep2</li>
     * <li>Run copy-paste from rep1 to rep2</li>
     * <li>Deactivate collapse filter on rep2</li>
     * <li>Check bordered node locations after copy-paste on rep2</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutWithCollapsedSourceAndTargetNodes() {
        // Open the 2 required representations
        diagramEditor2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor1);

        final DRepresentation representation1 = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1);
        // collapse "b" bordered nodes
        activateFilter((DDiagram) representation1, "testCollapseAtt");
        SWTBotUtils.waitAllUiEvents();

        // Copy LC1 layout
        diagramEditor1.click(new Point(1, 1));

        diagramEditor1.bot().toolbarDropDownButtonWithTooltip("Select &All").click();

        diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor2.show();

        final DRepresentation representation2 = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2);

        diagramEditor2.show();
        SWTBotUtils.waitAllUiEvents();

        // collapse "b" bordered nodes
        activateFilter((DDiagram) representation2, "testCollapseAtt");

        SWTBotUtils.waitAllUiEvents();

        // Paste layout on second representation
        diagramEditor2.click(new Point(100, 400));
        diagramEditor2.clickContextMenu(Messages.PasteLayoutAction_text);

        SWTBotUtils.waitAllUiEvents();
        // extend bordered nodes by deactivating filter "testHide"
        deactivateFilter((DDiagram) representation2, "testCollapseAtt");

        SWTBotUtils.waitAllUiEvents();

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor2, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations after the paste
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor2, expectedBList, expectedBList_GMF);

        checkExtendedRBorderedNodes(diagramEditor2);

    }

    /**
     * Test that the paste layout puts the elements at the expected location.
     * This test uses the copy-paste layout on same Diagram.
     * <ul>
     * <li>Check bordered node locations before copy-paste on rep1</li>
     * <li>Run copy-paste from rep1 to rep1</li>
     * <li>Check bordered node locations after copy-paste on rep1</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutOnSameDiagram() {
        // Open the required representation
        diagramEditor1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode before collapse
        checkExtendedRBorderedNodes(diagramEditor1);
        DRepresentation representation = getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1);

        // collapse r1 and r2 bordered nodes by activating filter "test"
        activateFilter((DDiagram) representation, "test");

        SWTBotUtils.waitAllUiEvents();
        // Check borderedNode after collapse
        checkCollapsedRBorderedNodes(diagramEditor1);

        // Copy LC1 layout
        diagramEditor1.show();
        diagramEditor1.click(new Point(1, 1));
        diagramEditor1.clickContextMenu(Messages.CopyFormatAction_text);

        // Paste layout on same representation
        diagramEditor1.click(new Point(1, 1));
        diagramEditor1.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor1, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations after the paste
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor1, expectedBList, expectedBList_GMF);

        // Check borderedNode after collapse
        checkCollapsedRBorderedNodes(diagramEditor1);
    }

    private void checkSecondContainerABorderedNodes(SWTBotSiriusDiagramEditor editorToCheck, ArrayList<Point> expectedList, ArrayList<Point> expectedList_GMF) {
        for (int i = 1; i <= 8; i++) {
            checkBorderNode("b", editorToCheck, expectedList.get(i - 1), expectedList_GMF.get(i - 1), i);
        }
    }

    private void checkBorderNode(String label, SWTBotSiriusDiagramEditor editorToCheck, Point expectedPoint, Point expectedPoint_GMF, int i) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label + i, AbstractDiagramBorderNodeEditPart.class);
        if (editPart != null) {
            AbstractDiagramBorderNodeEditPart part = (AbstractDiagramBorderNodeEditPart) editPart.part();
            // Check draw2d location
            Point location = part.getFigure().getBounds().getTopLeft();
            assertEquals("Wrong expected location for bordered node " + label + i, expectedPoint, location);
            // Check GMF location
            Point locationGMF = new Point(((Location) ((Node) part.getModel()).getLayoutConstraint()).getX(), ((Location) ((Node) part.getModel()).getLayoutConstraint()).getY());
            assertEquals("Wrong expected GMF location for bordered node " + label + i, expectedPoint_GMF, locationGMF);
        }
    }

    private void checkBorderNodeColor(String label, SWTBotSiriusDiagramEditor editorToCheck, Color color, String nameColor, int i) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label + i, AbstractDiagramBorderNodeEditPart.class);
        if (editPart != null) {
            AbstractDiagramBorderNodeEditPart part = (AbstractDiagramBorderNodeEditPart) editPart.part();
            // Check color
            Object shape = part.getStyleEditPart().getFigure().getChildren().get(0);
            assertEquals("The color of figure for " + label + i + " must be " + nameColor, color, ((Shape) shape).getBackgroundColor());
        }
    }

    private void checkFirstContainerABorderedNodes(SWTBotSiriusDiagramEditor editorToCheck, ArrayList<Point> expectedList, ArrayList<Point> expectedList_GMF) {
        for (int i = 1; i <= 6; i++) {
            checkBorderNode("a", editorToCheck, expectedList.get(i - 1), expectedList_GMF.get(i - 1), i);
        }
    }

    private void checkCollapsedRBorderedNodes(SWTBotSiriusDiagramEditor editorToCheck) {
        for (int i = 1; i <= 2; i++) {

            SWTBotGefEditPart editPart = editorToCheck.getEditPart("r" + i, AbstractDiagramBorderNodeEditPart.class);
            if (editPart != null) {
                AbstractDiagramBorderNodeEditPart part = (AbstractDiagramBorderNodeEditPart) editPart.part();
                // Check draw2d
                Point location = part.getFigure().getBounds().getTopLeft();
                Dimension dimension = part.getFigure().getBounds().getSize();
                assertEquals("Wrong expected location for bordered node r" + i + " after collapse filter activation", expectedCollapsedNodeList.get(i - 1), location);
                assertEquals("Wrong expected dimension for bordered node r" + i + " after collapse filter activation", new Dimension(1, 1), dimension);
                // Check GMF
                Bounds bounds = (Bounds) ((Node) part.getModel()).getLayoutConstraint();
                Point locationGMF = new Point(bounds.getX(), bounds.getY());
                Dimension dimensionGMF = new Dimension(bounds.getWidth(), bounds.getHeight());
                assertEquals("Wrong expected GMF location for bordered node r" + i + " after collapse filter activation", expectedCollapsedNodeList_GMF.get(i - 1), locationGMF);
                assertEquals("Wrong expected GMF dimension for bordered node r" + i + " after collapse filter activation", new Dimension(1, 1), dimensionGMF);
            }
        }
    }

    private void checkExtendedRBorderedNodes(SWTBotSiriusDiagramEditor editorToCheck) {
        for (int i = 1; i <= 2; i++) {
            SWTBotGefEditPart editPart = editorToCheck.getEditPart("r" + i, AbstractDiagramBorderNodeEditPart.class);
            if (editPart != null) {
                AbstractDiagramBorderNodeEditPart part = (AbstractDiagramBorderNodeEditPart) editPart.part();
                // Check draw2d
                Point location = part.getFigure().getBounds().getTopLeft();
                Dimension dimension = part.getFigure().getBounds().getSize();
                assertEquals("Wrong expected location for collapsed bordered node r" + i + " before collapse filter activation", expectedNonCollapsedNodeList.get(i - 1), location);
                assertEquals("Wrong expected dimension for collapsed bordered node r" + i + " before collapse filter activation", new Dimension(10, 10), dimension);
                // Check GMF
                Bounds bounds = (Bounds) ((Node) part.getModel()).getLayoutConstraint();
                Point locationGMF = new Point(bounds.getX(), bounds.getY());
                Dimension dimensionGMF = new Dimension(bounds.getWidth(), bounds.getHeight());
                assertEquals("Wrong expected GMF location for collapsed bordered node r" + i + " before collapse filter activation", expectedNonCollapsedNodeList_GMF.get(i - 1), locationGMF);
                assertEquals("Wrong expected GMF dimension for collapsed bordered node r" + i + " before collapse filter activation", new Dimension(10, 10), dimensionGMF);
            }
        }
    }

    /**
     * Test that the paste layout puts the elements at the expected location.
     * This test uses the copy-paste layout on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in).
     * <ul>
     * <li>Check container locations before copy-paste on rep3</li>
     * <li>Run copy-paste from rep3 to rep4</li>
     * <li>Check border node location after copy-paste on rep4</li>
     * </ul>
     */
    public void testBorderedNodeCopyPasteLayoutOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor4 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME4, DDiagram.class);
        diagramEditor3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);
        // Check LogicalFunction1 bordered nodes locations before the copy
        // layout.
        checkFirstContainerABorderedNodes(diagramEditor3, expectedAList, expectedAList_GMF);

        // Check LogicalFunction2 bordered nodes locations before the copy
        // layout.
        checkSecondContainerABorderedNodes(diagramEditor3, expectedBList, expectedBList_GMF);

        // Copy LC1 layout
        diagramEditor3.click(COPY_POINT);
        diagramEditor3.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor4.show();
        // Paste layout on second representation
        diagramEditor4.click(PASTE_POINT);
        diagramEditor4.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 bordered nodes locations after the paste
        // layout.
        checkBorderNode("a", diagramEditor4, B1_FIRST_DIAG_LOCATION, B1_FIRST_DIAG_GMF_LOCATION, 1);
    }

    /**
     * Test that the paste style changes style on the expected figure. This test
     * uses the copy-paste style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in). Style of
     * border node a1 from a first diagram is applied on border node LC2 from a
     * second diagram.
     * <ul>
     * <li>Run copy-paste Style from rep7 to rep5</li>
     * <li>Check node locations and style after copy-paste on rep5. Check style
     * node changes into yellow.</li>
     * </ul>
     */
    public void testBorderNodeCopyPasteStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor4 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME4, DDiagram.class);
        diagramEditor3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);

        diagramEditor3.show();
        // Copy LC1 layout
        diagramEditor3.click(COPY_POINT);
        diagramEditor3.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor4.show();
        // Check a1 bordered nodes locations before the paste style.
        checkBorderNode("a", diagramEditor4, A1_SECOND_DIAG_LOCATION, A1_SECOND_DIAG_GMF_LOCATION, 1);

        // Paste style on second representation
        diagramEditor4.click(PASTE_POINT);
        diagramEditor4.clickContextMenu(Messages.PasteStyleAction_text);

        // Check a1 bordered nodes locations does not change after the paste
        // layout.
        checkBorderNode("a", diagramEditor4, A1_SECOND_DIAG_LOCATION, A1_SECOND_DIAG_GMF_LOCATION, 1);

        // Check border node color
        Color yellow = new Color(null, 252, 233, 79);
        try {
            checkBorderNodeColor("a", diagramEditor4, yellow, "Yellow", 1);
        } finally {
            yellow.dispose();
        }
    }

    /**
     * Activate a filter.
     * 
     * @param diagram
     *            the diagram
     * @param filterName
     *            the filter name
     * @return <code>true</code> if the activation could be made,
     *         <code>false</code> otherwise
     */
    private final boolean activateFilter(final DDiagram diagram, final String filterName) {
        return changeFilterActivation(diagram, filterName, true);
    }

    /**
     * Deactivate a filter.
     * 
     * @param diagram
     *            the diagram
     * @param filterName
     *            the filter name
     * @return <code>true</code> if the deactivation could be made,
     *         <code>false</code> otherwise
     */
    private final boolean deactivateFilter(final DDiagram diagram, final String filterName) {
        return changeFilterActivation(diagram, filterName, false);
    }

    /**
     * Activate a filter.
     * 
     * @param diagram
     *            the diagram
     * @param filterName
     *            the filter name
     * @param value
     * @return <code>true</code> if the activation changing could be made,
     *         <code>false</code> otherwise
     */
    private final boolean changeFilterActivation(final DDiagram diagram, final String filterName, boolean value) {
        final FilterDescription filter = getFilter(diagram, filterName);
        if (filter != null) {
            return setFilterActivation(diagram, filter, value);
        }
        throw new IllegalArgumentException(FILTER_NAME_INCORRECT);
    }

    /**
     * Searches the given {@link DDiagram} for a filter of the given name and
     * returns it.
     * 
     * @param diagram
     *            The diagram to search for a tool.
     * @param filterName
     *            The name of the searched filter.
     * @return The retrieved filter, or <code>null</code> if it cannot be found.
     */
    private final FilterDescription getFilter(final DDiagram diagram, final String filterName) {
        final Collection<FilterDescription> filters = diagram.getDescription().getFilters();

        for (final FilterDescription filter : filters) {
            if (filter.getName().equals(filterName)) {
                return filter;
            }
        }
        return null;
    }

    private boolean setFilterActivation(final DDiagram diagram, final FilterDescription filter, final boolean visible) {
        final Runnable change = new ChangeFilterActivation((IDiagramWorkbenchPart) EclipseUIUtil.getActiveEditor(), diagram, filter, visible);
        change.run();
        return true;
    }
}
