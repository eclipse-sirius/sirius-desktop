/*******************************************************************************
 * Copyright (c) 2016, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart.SquareFigure;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.draw2d.figure.LozengeFigure;
import org.eclipse.sirius.ext.draw2d.figure.ODesignEllipseFigure;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

/**
 * Test container and node Copy-Paste layout and style from and to diagram with extension.
 * 
 * @author jmallet
 */
public class ContainerAndNodeCopyPasteFormatTest extends AbstractSiriusSwtBotGefTestCase {

    /** Label of LC2 representation on diagram. */
    private static final String LC2_LABEL = "LC2";

    /** Label of LC1 representation on diagram. */
    private static final String LC1_LABEL = "LC1";

    /**
     * Point used to paste Layout. This point does not reference any edit part.
     */
    private static final Point EMPTY_POINT = new Point(50, 500);

    /** Point used to select LC2 container. */
    private static final Point LC2_POINT = new Point(870, 100);

    /** LC2 Container bounds for diagram4. */
    private static final Rectangle LC2_CONTAINER_BOUNDS_DIAG4 = new Rectangle(600, 72, 291, 243);

    /** LC1 Container bounds for diagram4. */
    private static final Rectangle LC1_CONTAINER_BOUNDS_DIAG4 = new Rectangle(96, 96, 363, 255);

    /** LC2 Container bounds for diagram3. */
    private static final Rectangle LC2_CONTAINER_BOUNDS_DIAG3 = new Rectangle(600, 72, 291, 243);

    /** LC1 Container bounds for diagram3. */
    private static final Rectangle LC1_CONTAINER_BOUNDS_DIAG3 = new Rectangle(96, 96, 363, 255);

    /**
     * LC2 node Bounds for diagram using specific style to represent this node.
     */
    private static final Rectangle NEW_STYLE_LC2_NODE_BOUNDS = new Rectangle(830, 25, 400, 400);

    /** Initial bounds for LC1 square node. */
    private static final Rectangle INITIAL_LC1_SQUARE_NODE_BOUNDS = new Rectangle(0, 0, 30, 30);

    /** Initial bounds for LC2 square node. */
    private static final Rectangle INITIAL_LC2_SQUARE_NODE_BOUNDS = new Rectangle(90, 0, 30, 30);

    private static final Rectangle D_NODE_BOUNDS_CASE1 = new Rectangle(15, 34, 61, 61);

    private static final Rectangle E_NODE_BOUNDS_CASE1 = new Rectangle(95, 34, 61, 61);

    private static final Rectangle A_NODE_BOUNDS_CASE3 = new Rectangle(45, 44, 126, 51);

    private static final Rectangle B_NODE_BOUNDS_CASE3 = new Rectangle(45, 119, 126, 51);

    private static final Rectangle P11_CONTAINER_BOUNDS_CASE4 = new Rectangle(50, 50, 228, 203);

    private static final Rectangle A_NODE_BOUNDS_CASE5 = new Rectangle(120, 119, 151, 76);

    private static final Rectangle B_NODE_BOUNDS_CASE5 = new Rectangle(120, 219, 151, 76);

    private static final Rectangle P1_CONTAINER_BOUNDS_CASE5 = new Rectangle(100, 100, 403, 403);

    private static final Rectangle C1_NODE_BOUNDS_CASE6 = new Rectangle(45, 69, 76, 126);

    private static final Rectangle C2_NODE_BOUNDS_CASE6 = new Rectangle(220, 69, 76, 126);

    private static final Rectangle C3_NODE_BOUNDS_CASE6 = new Rectangle(45, 44, 76, 126);

    private static final String FILE_DIR = "/";

    private static final String VSM = "VP-3601.odesign";

    private static final String MODEL = "VP-3601.ecore";

    private static final String SESSION_FILE = "VP-3601.aird";

    private static final String THUX_IMAGE = "Tux.svg";

    private static final String DATA_UNIT_DIR = "data/unit/layout/borderedNodesCopyPastLayout/";

    private static final String REPRESENTATION_NAME3 = "rep3AdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME4 = "rep4AdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME_WITH_SQUARE = "repSquareAdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME_WITH_ELLIPSE = "repEllipseAdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME_WITH_DIAMOND = "repDiamondAdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME_WITH_IMAGE = "repImageAdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME_CASE1_SRC = "newDiagPasteLayoutCase1-Source";

    private static final String REPRESENTATION_NAME_CASE1_TGT1 = "newDiagPasteLayoutCase1-Target1";

    private static final String REPRESENTATION_NAME_CASE1_TGT2 = "newDiagPasteLayoutCase1-Target2";

    private static final String REPRESENTATION_NAME_CASE2_SRC = "newDiagPasteLayoutCase2-Source";

    private static final String REPRESENTATION_NAME_CASE2_TGT = "newDiagPasteLayoutCase2-Target";

    private static final String REPRESENTATION_NAME_CASE5_SRC = "newDiagPasteLayoutCase5-Source";

    private static final String REPRESENTATION_NAME_CASE5_TGT = "newDiagPasteLayoutCase5-Target";

    private static final String REPRESENTATION_NAME_CASE6_SRC = "newDiagPasteLayoutCase6-Source";

    private static final String REPRESENTATION_NAME_CASE6_TGT = "newDiagPasteLayoutCase6-Target";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Diagram";

    private static final String REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE = "DiagramWithSquareLCNode";

    private static final String REPRESENTATION_DESCRIPTION_NAME_ELLIPSE_LCNODE = "DiagramWithYellowEllipseLCNode";

    private static final String REPRESENTATION_DESCRIPTION_NAME_DIAMOND_LCNODE = "DiagramWithOrangeDiamondLCNode";

    private static final String REPRESENTATION_DESCRIPTION_NAME_IMAGE_LCNODE = "DiagramWithImageLCNode";

    private static final String REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1 = "DiagPasteLayoutCase1";

    private static final String REPRESENTATION_DESCRIPTION_NAME_BOUNDING_2 = "DiagPasteLayoutCase2";

    /**
     * Diagram used as source of the copy/paste.
     */
    private SWTBotSiriusDiagramEditor diagramEditorSrc;

    /**
     * Diagram used as target of the copy/paste.
     */
    private SWTBotSiriusDiagramEditor diagramEditorTgt;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM, THUX_IMAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        diagramEditorSrc = null;
        diagramEditorTgt = null;
        clearFormatDataManager();
        super.tearDown();
    }

    /**
     * Test that the paste layout puts the elements at the expected location. This test uses the copy-paste layout on
     * Diagram using the org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider
     * contributed in oes.tests.junit plug-in).
     * <ul>
     * <li>Check container locations before copy-paste on rep3</li>
     * <li>Run copy-paste from rep3 to rep4</li>
     * <li>Check container locations after copy-paste on rep4</li>
     * </ul>
     */
    public void testContainerCopyPasteLayoutOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME4, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);

        // Check LogicalFunction1 container locations before the copy layout.
        checkContainerBounds(diagramEditorSrc, LC1_LABEL, LC1_CONTAINER_BOUNDS_DIAG3, LC1_CONTAINER_BOUNDS_DIAG3);

        // Check LogicalFunction2 container locations before the copy layout.
        checkContainerBounds(diagramEditorSrc, LC2_LABEL, LC2_CONTAINER_BOUNDS_DIAG3, LC2_CONTAINER_BOUNDS_DIAG3);

        Option<Color> originalCopiedColor = getBackgroundColor(diagramEditorSrc, LC2_LABEL);

        // Copy LC1 layout
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        // Check LogicalFunction1 container locations before the paste layout.
        checkContainerBounds(diagramEditorTgt, LC1_LABEL, LC1_CONTAINER_BOUNDS_DIAG4, LC1_CONTAINER_BOUNDS_DIAG4);

        // Check LogicalFunction2 container locations before the paste layout.
        checkContainerBounds(diagramEditorTgt, LC2_LABEL, LC2_CONTAINER_BOUNDS_DIAG4, LC2_CONTAINER_BOUNDS_DIAG4);

        // Paste layout on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 container locations after the paste layout.
        checkContainerBounds(diagramEditorTgt, LC1_LABEL, LC2_CONTAINER_BOUNDS_DIAG3, LC2_CONTAINER_BOUNDS_DIAG3);

        // Check that style has not changed (current color is not the copied
        // color)
        Option<Color> currentColor = getBackgroundColor(diagramEditorTgt, LC1_LABEL);
        if (originalCopiedColor.some() && currentColor.some()) {
            assertFalse("The style must not be changed with a Copy Layout", originalCopiedColor.get().equals(currentColor.get()));
        }
    }

    private Option<Color> getBackgroundColor(SWTBotSiriusDiagramEditor editorToCheck, String label) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label, AbstractDiagramElementContainerEditPart.class);
        if (editPart != null) {
            AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) editPart.part();
            return Options.newSome(part.getFigure().getBackgroundColor());
        }
        return Options.newNone();
    }

    /**
     * Test that the paste layout puts the elements at the expected location. This test uses the copy-paste layout on
     * Diagram using the org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider
     * contributed in oes.tests.junit plug-in) Layout of container LC2 from a first diagram is applied on node LC1 from
     * a second diagram.
     * <ul>
     * <li>Check container locations before copy-paste on rep3</li>
     * <li>Run copy-paste from rep3 to rep5</li>
     * <li>Check container locations after copy-paste on rep5. Check node becomes container.</li>
     * </ul>
     */
    public void testContainerToNodeCopyPasteLayoutOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);

        // Check LogicalFunction1 container locations before the copy layout.
        checkContainerBounds(diagramEditorSrc, LC1_LABEL, LC1_CONTAINER_BOUNDS_DIAG3, LC1_CONTAINER_BOUNDS_DIAG3);

        // Check LogicalFunction2 container locations before the copy layout.
        Rectangle LC2Bounds = checkContainerBounds(diagramEditorSrc, LC2_LABEL, LC2_CONTAINER_BOUNDS_DIAG3, LC2_CONTAINER_BOUNDS_DIAG3);

        // Copy LC1 layout
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        // Check LogicalFunction1 node locations before the paste layout.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout.
        checkNodeBounds(diagramEditorTgt, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // Paste layout on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, LC2Bounds, LC2Bounds);
    }

    /**
     * Test that the paste style changes style on the expected figure. This test uses the copy-paste style on Diagram
     * using the org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider contributed
     * in oes.tests.junit plug-in). Style of node LC2 from a first diagram is applied on node LC1 from a second diagram.
     * <ul>
     * <li>Check node location and style before paste on rep5</li>
     * <li>Run copy-paste Style from rep6 to rep5</li>
     * <li>Check node locations and style after copy-paste on rep5. Check style node changes into yellow ellipse.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteEllipseStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_ELLIPSE_LCNODE, REPRESENTATION_NAME_WITH_ELLIPSE,
                DDiagram.class);

        // Copy LC1 style
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);
        diagramEditorTgt.show();

        // Check LogicalFunction1 node locations before the paste style.
        Rectangle LC1Bounds = checkNodeBounds(diagramEditorTgt, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste style.
        checkNodeBounds(diagramEditorTgt, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditorTgt, LC1_LABEL);

        // Paste style on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteStyleAction_text);

        // Check LogicalFunction1 node locations does not changed after the
        // paste layout.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, LC1Bounds, LC1Bounds);

        // Check style
        checkYellowEllipseStyle(diagramEditorTgt, LC1_LABEL);
    }

    /**
     * Test that the paste style changes style on the expected figure. This test uses the copy-paste style on Diagram
     * using the org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider contributed
     * in oes.tests.junit plug-in). Style of node LC2 from a first diagram is applied on node LC1 from a second diagram.
     * <ul>
     * <li>Check node location and style before paste on rep5</li>
     * <li>Run copy-paste Style from rep7 to rep5</li>
     * <li>Check node locations and style after copy-paste on rep5. Check style node changes into orange diamond.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteDiamondStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_DIAMOND_LCNODE, REPRESENTATION_NAME_WITH_DIAMOND,
                DDiagram.class);

        // Copy LC1 layout
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        // Check LogicalFunction1 node locations before the paste style.
        Rectangle LC1Bounds = checkNodeBounds(diagramEditorTgt, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste style.
        checkNodeBounds(diagramEditorTgt, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditorTgt, LC1_LABEL);

        // Paste layout on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteStyleAction_text);

        // Check LogicalFunction1 node locations does not change after the paste
        // style.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, LC1Bounds, LC1Bounds);

        // check style
        checkOrangeDiamondStyle(diagramEditorTgt, LC1_LABEL);
    }

    /**
     * Test that the paste style changes style on the expected figure. This test uses the copy-paste style on Diagram
     * using the org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider contributed
     * in oes.tests.junit plug-in). Style of node LC2 from a first diagram is applied on node LC1 from a second diagram.
     * <ul>
     * <li>Check node location and style before paste on rep5</li>
     * <li>Run copy-paste Style from rep8 to rep5</li>
     * <li>Check node locations and style after copy-paste on rep5. Check style node changes into workspace Image.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteWSImageStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_IMAGE_LCNODE, REPRESENTATION_NAME_WITH_IMAGE,
                DDiagram.class);

        // Copy LC1 Style
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);
        diagramEditorTgt.show();

        // Check LogicalFunction1 node locations before the paste style.
        Rectangle LC1Bounds = checkNodeBounds(diagramEditorTgt, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste style.
        checkNodeBounds(diagramEditorTgt, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditorTgt, LC1_LABEL);

        // Paste style on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteStyleAction_text);

        // Check LogicalFunction1 node locations does not change after the paste
        // style.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, LC1Bounds, LC1Bounds);

        // Check that the style has changed
        checkWSFigureStyle(diagramEditorTgt, LC1_LABEL);
    }

    /**
     * Test that the paste style and layout changes style on the expected figure and puts the elements at the expected
     * location. This test uses the copy-paste layout and style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider contributed in
     * oes.tests.junit plug-in). Style and layout of node LC2 from a first diagram is applied on node LC1 from a second
     * diagram.
     * <ul>
     * <li>Check node locations before copy-paste on rep3</li>
     * <li>Run copy-paste Style from rep6 to rep5</li>
     * <li>Check style node changes into yellow ellipse.</li>
     * <li>Check location changes.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteEllipseLayoutAndStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_ELLIPSE_LCNODE, REPRESENTATION_NAME_WITH_ELLIPSE,
                DDiagram.class);

        // Check LogicalFunction2 node locations before the copy layout and
        // style.
        Rectangle LC2Diag6Bounds = checkNodeBounds(diagramEditorSrc, LC2_LABEL, NEW_STYLE_LC2_NODE_BOUNDS, NEW_STYLE_LC2_NODE_BOUNDS);

        // Copy LC1 layout and Style
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        // Check LogicalFunction1 node locations before the paste layout and
        // style.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout and
        // style.
        Rectangle LC2Diag5Bounds = checkNodeBounds(diagramEditorTgt, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditorTgt, LC1_LABEL);

        // Paste layout on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteFormatAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        // By using diagram extension, it matches with LC2 bounds of diagram6
        // representation (LC2 annotation on LC1).
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, LC2Diag6Bounds, LC2Diag6Bounds);
        // Check LogicalFunction2 node locations does not change after the paste
        // layout (no annotation on LC2).
        checkNodeBounds(diagramEditorTgt, LC2_LABEL, LC2Diag5Bounds, LC2Diag5Bounds);

        // Check style
        checkYellowEllipseStyle(diagramEditorTgt, LC1_LABEL);
    }

    /**
     * Test that the paste style and layout changes style on the expected figure and puts the elements at the expected
     * location. This test uses the copy-paste layout and style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider contributed in
     * oes.tests.junit plug-in). Style and layout of node LC2 from a first diagram is applied on node LC1 from a second
     * diagram.
     * <ul>
     * <li>Check node locations before copy-paste on rep3</li>
     * <li>Run copy-paste Style from rep7 to rep5</li>
     * <li>Check style node changes into orange diamonds.</li>
     * <li>Check location changes.</li>
     * <li>Check label style changes.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteDiamondLayoutAndStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_DIAMOND_LCNODE, REPRESENTATION_NAME_WITH_DIAMOND,
                DDiagram.class);

        // Check LogicalFunction2 node locations before the copy layout and
        // style.
        Rectangle LC2Diag7Bounds = checkNodeBounds(diagramEditorSrc, LC2_LABEL, NEW_STYLE_LC2_NODE_BOUNDS, NEW_STYLE_LC2_NODE_BOUNDS);

        // Copy LC1 layout and style
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        // Check LogicalFunction1 node locations before the paste layout and
        // style.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout and
        // style.
        Rectangle LC2Diag5Bounds = checkNodeBounds(diagramEditorTgt, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditorTgt, LC1_LABEL);

        // Paste layout on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteFormatAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        // By using diagram extension, it matches with LC2 bounds of diagram7
        // representation (LC2 annotation on LC1).
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, LC2Diag7Bounds, LC2Diag7Bounds);
        // Check LogicalFunction2 node locations does not change after the paste
        // layout (no annotation on LC2).
        checkNodeBounds(diagramEditorTgt, LC2_LABEL, LC2Diag5Bounds, LC2Diag5Bounds);

        // Check style
        checkOrangeDiamondStyle(diagramEditorTgt, LC1_LABEL);
    }

    /**
     * Test that the paste style and layout changes style on the expected figure and puts the elements at the expected
     * location. This test uses the copy-paste layout and style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the sampleNameDataProvider contributed in
     * oes.tests.junit plug-in). Style and layout of node LC2 from a first diagram is applied on node LC1 from a second
     * diagram.
     * <ul>
     * <li>Check node locations before copy-paste on rep3</li>
     * <li>Run copy-paste Style from rep8 to rep5</li>
     * <li>Check style node changes into thux workspace image.</li>
     * <li>Check location changes.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteImageLayoutAndStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_IMAGE_LCNODE, REPRESENTATION_NAME_WITH_IMAGE,
                DDiagram.class);

        // Check LogicalFunction2 node locations before the copy layout and
        // style.
        Rectangle LC2Diag8Bounds = checkNodeBounds(diagramEditorSrc, LC2_LABEL, NEW_STYLE_LC2_NODE_BOUNDS, NEW_STYLE_LC2_NODE_BOUNDS);

        // Copy LC1 layout
        diagramEditorSrc.click(LC2_POINT);
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        // Check LogicalFunction1 node locations before the paste layout and
        // style.
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout and
        // style.
        Rectangle LC2Diag5Bounds = checkNodeBounds(diagramEditorTgt, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditorTgt, LC1_LABEL);

        // Paste layout on second representation
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteFormatAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        // By using diagram extension, it matches with LC2 bounds of diagram8
        // representation (LC2 annotation on LC1).
        checkNodeBounds(diagramEditorTgt, LC1_LABEL, LC2Diag8Bounds, LC2Diag8Bounds);
        // Check LogicalFunction2 node locations does not change after the paste
        // layout (no annotation on LC2).
        checkNodeBounds(diagramEditorTgt, LC2_LABEL, LC2Diag5Bounds, LC2Diag5Bounds);

        // Check style
        checkWSFigureStyle(diagramEditorTgt, LC1_LABEL);
    }

    /**
     * Test that the dialog appears if it should, and check the default value according to preference values. The test
     * also checks that the preference values are modified according to user selection in dialog.
     */
    public void testPasteModeDialogAndPreferenceValues() {
        // Change the promptPasteMode pref to override the default changed in setup (to retrieve a Sirius standard
        // behavior).
        final Boolean defaultPromptPasteMode = UIThreadRunnable.syncExec(new Result<Boolean>() {
            @Override
            public Boolean run() {
                IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
                boolean result = preferenceStore.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name());
                preferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), true);
                return result;
            }
        });
        try {
            // Open the required representation
            diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE1_SRC,
                    DDiagram.class);

            // Copy D layout from diagram (to enable the paste action)
            SWTBotGefEditPart nodeDEditPart = diagramEditorSrc.getEditPart("D", AbstractDiagramNodeEditPart.class);
            diagramEditorSrc.select(nodeDEditPart);
            bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeDEditPart.part()));
            diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

            // Paste format on diagram and cancel. This is just to check that dialog is asked on "Paste format" action,
            // the additional tests will be done on "Paste layout" action
            diagramEditorSrc.click(EMPTY_POINT);
            diagramEditorSrc.clickContextMenu(Messages.PasteFormatAction_text);
            bot.waitUntilWidgetAppears(Conditions.shellIsActive(Messages.SelectPasteModeDialog_title));
            SWTBotShell selectPasteModeDialog = bot.shell(Messages.SelectPasteModeDialog_title);
            selectPasteModeDialog.activate();
            // Click cancel
            SWTBotButton cancelButton = bot.button(IDialogConstants.CANCEL_LABEL);
            bot.waitUntilWidgetAppears(new ItemEnabledCondition(cancelButton));
            cancelButton.click();
            SWTBotUtils.waitAllUiEvents();

            // Paste layout on diagram
            diagramEditorSrc.click(EMPTY_POINT);
            diagramEditorSrc.clickContextMenu(Messages.PasteLayoutAction_text);

            // By default a dialog should appears to ask the Paste mode to the end-user, and the radio button "Absolute"
            // should be checked.
            bot.waitUntilWidgetAppears(Conditions.shellIsActive(Messages.SelectPasteModeDialog_title));
            selectPasteModeDialog = bot.shell(Messages.SelectPasteModeDialog_title);
            selectPasteModeDialog.activate();

            // Check radio value
            SWTBotRadio absoluteRadioButton = bot.radio(Messages.SelectPasteModeDialog_absoluteModeLabel);
            assertTrue("By default, Absolute mode should be selected.", absoluteRadioButton.isSelected()); //$NON-NLS-1$

            // Click cancel
            cancelButton = bot.button(IDialogConstants.CANCEL_LABEL);
            bot.waitUntilWidgetAppears(new ItemEnabledCondition(cancelButton));
            cancelButton.click();
            SWTBotUtils.waitAllUiEvents();

            // Change the preference value to have "Bounding mode" as default value
            changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name(), false);

            // Paste layout on diagram
            diagramEditorSrc.clickContextMenu(Messages.PasteLayoutAction_text);

            // Get dialog
            bot.waitUntilWidgetAppears(Conditions.shellIsActive(Messages.SelectPasteModeDialog_title));
            selectPasteModeDialog = bot.shell(Messages.SelectPasteModeDialog_title);
            selectPasteModeDialog.activate();
            // Check radio value
            absoluteRadioButton = bot.radio(Messages.SelectPasteModeDialog_absoluteModeLabel);
            assertFalse("After changing preference, the Absolute mode should not be selected.", absoluteRadioButton.isSelected()); //$NON-NLS-1$

            // Change value to "Absolute"
            absoluteRadioButton.click();

            // Click OK
            SWTBotButton pasteButton = bot.button(Messages.SelectPasteModeDialog_pasteButtonLabel);
            bot.waitUntilWidgetAppears(new ItemEnabledCondition(pasteButton));
            pasteButton.click();
            SWTBotUtils.waitAllUiEvents();

            // Check preference value (should be absolute as the last user choice)
            IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
            assertTrue("The preference for absolute mode must be set to true after the selection of the end-user in the dialog.", //$NON-NLS-1$
                    preferenceStore.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name()));

            // Change the preference value to not open the dialog
            changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), false);

            // Paste layout on diagram
            OperationDoneCondition doneCondition = new OperationDoneCondition();
            diagramEditorSrc.clickContextMenu(Messages.PasteLayoutAction_text);

            // No dialog appears
            try {
                bot.waitUntil(doneCondition);
            } catch (TimeoutException e) {
                fail("The paste action has not been executed, probably because a dialog appears."); //$NON-NLS-1$
            }
        } finally {
            UIThreadRunnable.syncExec(new VoidResult() {

                @Override
                public void run() {
                    IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
                    preferenceStore.setValue(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), defaultPromptPasteMode);
                }
            });
        }
    }

    /**
     * Test the copy paste layout with bounding box mode. This test corresponds to the case 1 of the specification.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case1() {
        // Enable the bounding box mode by default without popup.
        forceBoundingBoxPasteMode();
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE1_TGT1, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE1_SRC, DDiagram.class);

        // Check "D" and "E" locations before the copy layout.
        Rectangle dSourceBounds = checkNodeBounds(diagramEditorSrc, "D", D_NODE_BOUNDS_CASE1, D_NODE_BOUNDS_CASE1);
        Rectangle eSourceBounds = checkNodeBounds(diagramEditorSrc, "E", E_NODE_BOUNDS_CASE1, E_NODE_BOUNDS_CASE1);
        Dimension deltaBetweenDAndE = eSourceBounds.getLocation().getDifference(dSourceBounds.getLocation());

        // Copy D and E layout from source diagram
        SWTBotGefEditPart nodeDEditPart = diagramEditorSrc.getEditPart("D", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeEEditPart = diagramEditorSrc.getEditPart("E", AbstractDiagramNodeEditPart.class);
        diagramEditorSrc.select(nodeDEditPart, nodeEEditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeDEditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeEEditPart.part()));
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle absoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("D", AbstractDiagramNodeEditPart.class));

        // Paste layout on target diagram
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check "D" and "E" locations after the paste layout.
        Rectangle expectedBoundsForD = new Rectangle(absoluteBoundsBeforePaste.getLocation(), D_NODE_BOUNDS_CASE1.getSize());
        checkNodeBounds(diagramEditorTgt, "D", expectedBoundsForD, expectedBoundsForD);
        Rectangle expectedBoundsForE = expectedBoundsForD.getTranslated(deltaBetweenDAndE.width, deltaBetweenDAndE.height);
        checkNodeBounds(diagramEditorTgt, "E", expectedBoundsForE, expectedBoundsForE);
    }

    /**
     * Test the copy paste layout with bounding box mode. This test corresponds to the case 2 of the specification.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case2() {
        // Enable the bounding box mode by default without popup.
        forceBoundingBoxPasteMode();
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE1_TGT2, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE1_SRC, DDiagram.class);

        // Check "D" and "E" locations before the copy layout.
        Rectangle dSourceBounds = checkNodeBounds(diagramEditorSrc, "D", D_NODE_BOUNDS_CASE1, D_NODE_BOUNDS_CASE1);
        Rectangle eSourceBounds = checkNodeBounds(diagramEditorSrc, "E", E_NODE_BOUNDS_CASE1, E_NODE_BOUNDS_CASE1);
        Dimension deltaBetweenDAndE = eSourceBounds.getLocation().getDifference(dSourceBounds.getLocation());

        // Copy D and E layout from source diagram
        SWTBotGefEditPart nodeDEditPart = diagramEditorSrc.getEditPart("D", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeEEditPart = diagramEditorSrc.getEditPart("E", AbstractDiagramNodeEditPart.class);
        diagramEditorSrc.select(nodeDEditPart, nodeEEditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeDEditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeEEditPart.part()));
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle absoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("E", AbstractDiagramNodeEditPart.class));

        // Paste layout on target diagram
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check "D" and "E" locations after the paste layout.
        Rectangle expectedBoundsForD = new Rectangle(absoluteBoundsBeforePaste.getLocation(), D_NODE_BOUNDS_CASE1.getSize());
        checkNodeBounds(diagramEditorTgt, "D", expectedBoundsForD, expectedBoundsForD);
        Rectangle expectedBoundsForE = expectedBoundsForD.getTranslated(deltaBetweenDAndE.width, deltaBetweenDAndE.height);
        checkNodeBounds(diagramEditorTgt, "E", expectedBoundsForE, expectedBoundsForE);
    }

    /**
     * Test the copy paste layout with bounding box mode. This test corresponds to the case 3 of the specification.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case3() {
        // Enable the bounding box mode by default without popup.
        forceBoundingBoxPasteMode();
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE2_TGT, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE2_SRC, DDiagram.class);

        // Check "A" and "B" locations before the copy layout.
        Rectangle aSourceBounds = checkNodeBounds(diagramEditorSrc, "A", A_NODE_BOUNDS_CASE3, A_NODE_BOUNDS_CASE3);
        Rectangle bSourceBounds = checkNodeBounds(diagramEditorSrc, "B", B_NODE_BOUNDS_CASE3, B_NODE_BOUNDS_CASE3);
        Dimension deltaBetweenAAndB = bSourceBounds.getLocation().getDifference(aSourceBounds.getLocation());

        // Copy A and B layout from source diagram
        SWTBotGefEditPart nodeDEditPart = diagramEditorSrc.getEditPart("A", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeEEditPart = diagramEditorSrc.getEditPart("B", AbstractDiagramNodeEditPart.class);
        diagramEditorSrc.select(nodeDEditPart, nodeEEditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeDEditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeEEditPart.part()));
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle absoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("A", AbstractDiagramNodeEditPart.class));

        // Paste layout on target diagram
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check "A" and "B" locations after the paste layout.
        Rectangle expectedBoundsForA = new Rectangle(absoluteBoundsBeforePaste.getLocation(), A_NODE_BOUNDS_CASE3.getSize());
        checkNodeBounds(diagramEditorTgt, "A", expectedBoundsForA, expectedBoundsForA);
        Rectangle expectedBoundsForB = expectedBoundsForA.getTranslated(deltaBetweenAAndB.width, deltaBetweenAAndB.height);
        checkNodeBounds(diagramEditorTgt, "B", expectedBoundsForB, expectedBoundsForB);
    }

    /**
     * Test the copy paste layout with bounding box mode. This test corresponds to the case 4 of the specification.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case4() {
        // Enable the bounding box mode by default without popup.
        forceBoundingBoxPasteMode();
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE2_TGT, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE2_SRC, DDiagram.class);

        // Check "P11", "A" and "B" locations before the copy layout.
        checkContainerBounds(diagramEditorSrc, "P11", P11_CONTAINER_BOUNDS_CASE4, P11_CONTAINER_BOUNDS_CASE4);
        checkNodeBounds(diagramEditorSrc, "A", A_NODE_BOUNDS_CASE3, A_NODE_BOUNDS_CASE3);
        checkNodeBounds(diagramEditorSrc, "B", B_NODE_BOUNDS_CASE3, B_NODE_BOUNDS_CASE3);

        // Copy P11 layout from source diagram (and so "A" and "B" layout)
        SWTBotGefEditPart containerP11EditPart = diagramEditorSrc.getEditPart("P11", AbstractDiagramContainerEditPart.class);
        diagramEditorSrc.select(containerP11EditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, containerP11EditPart.part()));
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle absoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("P11", AbstractDiagramContainerEditPart.class));

        // Paste layout on target diagram
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check "P11" location after the paste layout.
        Rectangle expectedBoundsForP11 = new Rectangle(absoluteBoundsBeforePaste.getLocation(), P11_CONTAINER_BOUNDS_CASE4.getSize());
        checkContainerBounds(diagramEditorTgt, "P11", expectedBoundsForP11, expectedBoundsForP11);
        // Check "A" and "B" locations after the paste layout (same relative locations compared to source)
        checkNodeBounds(diagramEditorTgt, "A", A_NODE_BOUNDS_CASE3, A_NODE_BOUNDS_CASE3);
        checkNodeBounds(diagramEditorTgt, "B", B_NODE_BOUNDS_CASE3, B_NODE_BOUNDS_CASE3);
    }

    /**
     * Test the copy paste layout with bounding box mode. This test corresponds to the case 5 of the specification.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case5() {
        // Enable the bounding box mode by default without popup.
        forceBoundingBoxPasteMode();
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE5_TGT, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE5_SRC, DDiagram.class);

        // Check "P1", "A" and "B" locations before the copy layout.
        Rectangle p1SourceBounds = checkContainerBounds(diagramEditorSrc, "P1", P1_CONTAINER_BOUNDS_CASE5, P1_CONTAINER_BOUNDS_CASE5);
        Rectangle aSourceBounds = checkNodeBounds(diagramEditorSrc, "A", A_NODE_BOUNDS_CASE5, A_NODE_BOUNDS_CASE5);
        Rectangle bSourceBounds = checkNodeBounds(diagramEditorSrc, "B", B_NODE_BOUNDS_CASE5, B_NODE_BOUNDS_CASE5);
        Dimension deltaBetweenAAndB = bSourceBounds.getLocation().getDifference(aSourceBounds.getLocation());

        // Copy P1 layout from source diagram (and so "A" and "B" layout)
        SWTBotGefEditPart containerP1EditPart = diagramEditorSrc.getEditPart("P1", AbstractDiagramContainerEditPart.class);
        diagramEditorSrc.select(containerP1EditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, containerP1EditPart.part()));
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle p1AbsoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("P1", AbstractDiagramContainerEditPart.class));
        Rectangle aAbsoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("A", AbstractDiagramNodeEditPart.class));

        // Paste layout on target diagram
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check "P1" location after the paste layout.
        Rectangle expectedBoundsForP11 = new Rectangle(p1AbsoluteBoundsBeforePaste.getLocation(), P1_CONTAINER_BOUNDS_CASE5.getSize());
        checkContainerBounds(diagramEditorTgt, "P1", expectedBoundsForP11, expectedBoundsForP11);
        // Check "A" and "B" locations after the paste layout (same relative locations compared to source)
        Rectangle expectedBoundsForA = new Rectangle(aAbsoluteBoundsBeforePaste.getLocation(), A_NODE_BOUNDS_CASE5.getSize());
        checkNodeBounds(diagramEditorTgt, "A", expectedBoundsForA, expectedBoundsForA);
        Rectangle expectedBoundsForB = expectedBoundsForA.getTranslated(deltaBetweenAAndB.width, deltaBetweenAAndB.height);
        checkNodeBounds(diagramEditorTgt, "B", expectedBoundsForB, expectedBoundsForB);
    }

    /**
     * Test the copy paste layout with bounding box mode. This test corresponds to the case 6 of the specification.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case6() {
        testCopyPasteLayoutWithBoundingBoxMode_Case6(false);
    }

    /**
     * Test the copy paste layout with bounding box mode. This test corresponds to the case 7 of the specification.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case7() {
        testCopyPasteLayoutWithBoundingBoxMode_Case6(true);
    }

    /**
     * Test the copy paste layout with bounding box mode using date of Case6.
     */
    private void testCopyPasteLayoutWithBoundingBoxMode_Case6(boolean copyC3) {
        // Enable the bounding box mode by default without popup.
        forceBoundingBoxPasteMode();
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_2, REPRESENTATION_NAME_CASE6_TGT, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_2, REPRESENTATION_NAME_CASE6_SRC, DDiagram.class);

        // Check "C1", "C2", and "C3" locations before the copy layout.
        Rectangle c1SourceBounds = checkNodeBounds(diagramEditorSrc, "C1", C1_NODE_BOUNDS_CASE6, C1_NODE_BOUNDS_CASE6);
        Rectangle c2SourceBounds = checkNodeBounds(diagramEditorSrc, "C2", C2_NODE_BOUNDS_CASE6, C2_NODE_BOUNDS_CASE6);
        checkNodeBounds(diagramEditorSrc, "C3", C3_NODE_BOUNDS_CASE6, C3_NODE_BOUNDS_CASE6);
        Dimension deltaBetweenC1AndC2 = c2SourceBounds.getLocation().getDifference(c1SourceBounds.getLocation());
        Point c1AbsoluteLocationInSourceDiag = diagramEditorSrc.getAbsoluteLocation("C1", AbstractDiagramNodeEditPart.class);
        Point edgeLabelAbsoluteBoundsInSourceDiag = diagramEditorSrc.getAbsoluteLocation("refToC2", AbstractDiagramNameEditPart.class);
        Point edgeAbsoluteLocationInSourceDiag = diagramEditorSrc.getAbsoluteLocation("refToC2", AbstractDiagramEdgeEditPart.class, false);
        Dimension deltaBetweenC1AndEdgeLabelInAbsolute = edgeLabelAbsoluteBoundsInSourceDiag.getDifference(c1AbsoluteLocationInSourceDiag);
        Dimension deltaBetweenC1AndEdgeInAbsolute = edgeAbsoluteLocationInSourceDiag.getDifference(c1AbsoluteLocationInSourceDiag);

        // Copy C1, C2 (and C3) layout from source diagram
        SWTBotGefEditPart nodeC1EditPart = diagramEditorSrc.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeC2EditPart = diagramEditorSrc.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeC3EditPart = null;
        if (copyC3) {
            nodeC3EditPart = diagramEditorSrc.getEditPart("C3", AbstractDiagramNodeEditPart.class);
            diagramEditorSrc.select(nodeC1EditPart, nodeC2EditPart, nodeC3EditPart);
        } else {
            diagramEditorSrc.select(nodeC1EditPart, nodeC2EditPart);
        }
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeC1EditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeC2EditPart.part()));
        if (copyC3) {
            bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeC3EditPart.part()));
        }
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle c1AbsoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("C1", AbstractDiagramNodeEditPart.class));
        Rectangle c3AbsoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("C3", AbstractDiagramNodeEditPart.class));

        // Paste layout on target diagram
        diagramEditorTgt.click(EMPTY_POINT);
        diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check "C1" and "C2" locations after the paste layout (same relative locations compared to source)
        Rectangle expectedBoundsForC1 = new Rectangle(c1AbsoluteBoundsBeforePaste.getLocation().x(), c1AbsoluteBoundsBeforePaste.getLocation().y(), C1_NODE_BOUNDS_CASE6.width(),
                C1_NODE_BOUNDS_CASE6.height());
        checkNodeBounds(diagramEditorTgt, "C1", expectedBoundsForC1, expectedBoundsForC1);
        Rectangle expectedBoundsForC2 = expectedBoundsForC1.getTranslated(deltaBetweenC1AndC2.width, deltaBetweenC1AndC2.height);
        checkNodeBounds(diagramEditorTgt, "C2", expectedBoundsForC2, expectedBoundsForC2);
        // Check location of edge, between C1 and C2, and its label (if edge is OK, we consider that border nodes too).
        Rectangle expectedBoundsForLabel = expectedBoundsForC1.getTranslated(deltaBetweenC1AndEdgeLabelInAbsolute.width, deltaBetweenC1AndEdgeLabelInAbsolute.height);
        ((AbstractDiagramNodeEditPart) diagramEditorTgt.getEditPart("C1", AbstractDiagramNodeEditPart.class).part()).getFigure().translateToAbsolute(expectedBoundsForLabel);
        Point edgeLabelAbsoluteLocationAfterPaste = diagramEditorTgt.getAbsoluteLocation("refToC2", AbstractDiagramNameEditPart.class);
        assertEquals("Wrong Draw2D location for edge label refToC2", expectedBoundsForLabel.getLocation(), edgeLabelAbsoluteLocationAfterPaste);
        Rectangle expectedBoundsForEdge = expectedBoundsForC1.getTranslated(deltaBetweenC1AndEdgeInAbsolute.width, deltaBetweenC1AndEdgeInAbsolute.height);
        ((AbstractDiagramNodeEditPart) diagramEditorTgt.getEditPart("C1", AbstractDiagramNodeEditPart.class).part()).getFigure().translateToAbsolute(expectedBoundsForEdge);
        Point edgeAbsoluteLocationAfterPaste = diagramEditorTgt.getAbsoluteLocation("refToC2", AbstractDiagramEdgeEditPart.class);
        assertEquals("Wrong Draw2D location for edge label refToC2", expectedBoundsForEdge.getLocation(), edgeAbsoluteLocationAfterPaste);
        if (copyC3) {
            // Check C3 location after past (same location compared to before paste layout, because it is alone in its
            // group/in its container)
            Rectangle expectedBoundsForC3 = new Rectangle(c3AbsoluteBoundsBeforePaste.getLocation().x(), c3AbsoluteBoundsBeforePaste.getLocation().y(), C3_NODE_BOUNDS_CASE6.width(),
                    C3_NODE_BOUNDS_CASE6.height());
            checkNodeBounds(diagramEditorTgt, "C3", expectedBoundsForC3, expectedBoundsForC3);
        }
    }

    /**
     * Test using same scenario and data than {@link #testCopyPasteLayoutWithBoundingBoxMode_Case6()}, but instead of
     * selecting the diagram, 3 elements are selected to paste. This allows to check that only one dialog is displayed
     * and not one for each selected element.
     */
    public void testCopyPasteFormatWithBoundingBoxMode_Case6_withPasteOnMultiSelection() {
        testCopyPasteWithBoundingBoxMode_Case6_withPasteOnMultiSelection(true, false);
    }

    /**
     * Test using same scenario and data than {@link #testCopyPasteLayoutWithBoundingBoxMode_Case6()}, but instead of
     * selecting the diagram, 3 elements are selected to paste. This allows to check that only one dialog is displayed
     * and not one for each selected element.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case6_withPasteOnMultiSelection() {
        testCopyPasteWithBoundingBoxMode_Case6_withPasteOnMultiSelection(false, false);
    }

    /**
     * Test using same scenario and data than {@link #testCopyPasteLayoutWithBoundingBoxMode_Case6()}, but instead of
     * selecting the diagram, 5 elements are selected to paste (including 2 edges). This allows to check that only one
     * dialog is displayed and not one for each selected element. And also that there is no problem with edges
     * selection.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case6_withPasteOnMultiSelectionIncludingEdges() {
        testCopyPasteWithBoundingBoxMode_Case6_withPasteOnMultiSelection(false, true);
    }

    /**
     * Test using same scenario and data than {@link #testCopyPasteLayoutWithBoundingBoxMode_Case6()}, but instead of
     * selecting the diagram, 3 elements are selected to paste. This allows to check that only one dialog is displayed
     * and not one for each selected element.
     */
    protected void testCopyPasteWithBoundingBoxMode_Case6_withPasteOnMultiSelection(boolean pasteFormat, boolean selectEdgesBeforePaste) {
        // Enable the bounding box mode by default (but without popup).
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name(), false);
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), true);
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_2, REPRESENTATION_NAME_CASE6_TGT, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_2, REPRESENTATION_NAME_CASE6_SRC, DDiagram.class);

        // Copy C1, C2 and C3 layout from source diagram
        SWTBotGefEditPart nodeC1EditPart = diagramEditorSrc.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeC2EditPart = diagramEditorSrc.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeC3EditPart = diagramEditorSrc.getEditPart("C3", AbstractDiagramNodeEditPart.class);
        diagramEditorSrc.select(nodeC1EditPart, nodeC2EditPart, nodeC3EditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeC1EditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeC2EditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeC3EditPart.part()));
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle c1AbsoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("C1", AbstractDiagramNodeEditPart.class));

        // Paste layout on target diagram
        SWTBotGefEditPart targetNodeC1EditPart = diagramEditorTgt.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart targetNodeC2EditPart = diagramEditorTgt.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart targetNodeC3EditPart = diagramEditorTgt.getEditPart("C3", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart targetEdgeToC2EditPart = null;
        SWTBotGefEditPart targetEdgeToC3EditPart = null;
        if (selectEdgesBeforePaste) {
            targetEdgeToC2EditPart = diagramEditorTgt.getEditPart("refToC2", AbstractDiagramEdgeEditPart.class);
            targetEdgeToC3EditPart = diagramEditorTgt.getEditPart("refToC3", AbstractDiagramEdgeEditPart.class);
            diagramEditorTgt.select(targetNodeC1EditPart, targetNodeC2EditPart, targetNodeC3EditPart, targetEdgeToC2EditPart, targetEdgeToC3EditPart);
        } else {
            diagramEditorTgt.select(targetNodeC1EditPart, targetNodeC2EditPart, targetNodeC3EditPart);
        }
        bot.waitUntil(new CheckSelectedCondition(diagramEditorTgt, targetNodeC1EditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorTgt, targetNodeC2EditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorTgt, targetNodeC3EditPart.part()));
        if (selectEdgesBeforePaste) {
            bot.waitUntil(new CheckSelectedCondition(diagramEditorTgt, targetEdgeToC2EditPart.part()));
            bot.waitUntil(new CheckSelectedCondition(diagramEditorTgt, targetEdgeToC3EditPart.part()));
        }
        OperationDoneCondition doneCondition = new OperationDoneCondition();
        if (pasteFormat) {
            diagramEditorTgt.clickContextMenu(Messages.PasteFormatAction_text);
        } else {
            diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);
        }

        // A a dialog should appears to ask the Paste mode to the end-user.
        bot.waitUntilWidgetAppears(Conditions.shellIsActive(Messages.SelectPasteModeDialog_title));
        SWTBotShell selectPasteModeDialog = bot.shell(Messages.SelectPasteModeDialog_title);
        selectPasteModeDialog.activate();
        // Click OK
        SWTBotButton pasteButton = bot.button(Messages.SelectPasteModeDialog_pasteButtonLabel);
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(pasteButton));
        pasteButton.click();
        SWTBotUtils.waitAllUiEvents();

        // If other dialog is displayed the test will fail here.
        try {
            bot.waitUntil(doneCondition);
        } catch (TimeoutException e) {
            fail("The paste action has not been executed, probably because another dialog appears."); //$NON-NLS-1$
        }
    }

    /**
     * Same test than case1 but instead of selecting the diagram, 2 brothers are selected to paste format. This allows
     * to check that only one command is executed, and not one for each brother, during the paste format action.
     */
    public void testCopyPasteFormatWithBoundingBoxMode_Case1_withPasteOnBrothers() {
        testCopyPasteWithBoundingBoxMode_Case1_withPasteOnBrothers(true);
    }

    /**
     * Same test than case1 but instead of selecting the diagram, 2 brothers are selected to paste format. This allows
     * to check that only one command is executed, and not one for each brother, during the paste layout action.
     */
    public void testCopyPasteLayoutWithBoundingBoxMode_Case1_withPasteOnBrothers() {
        testCopyPasteWithBoundingBoxMode_Case1_withPasteOnBrothers(false);
    }

    /**
     * Same test than case1 but instead of selecting the diagram, 2 brothers are selected to paste format. This allows
     * to check that only one command is executed, and not one for each brother, during the paste action.
     */
    protected void testCopyPasteWithBoundingBoxMode_Case1_withPasteOnBrothers(boolean pasteFormat) {
        // Enable the bounding box mode by default without popup.
        forceBoundingBoxPasteMode();
        // Open the 2 required representations
        diagramEditorTgt = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE1_TGT1, DDiagram.class);
        diagramEditorSrc = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_BOUNDING_1, REPRESENTATION_NAME_CASE1_SRC, DDiagram.class);

        // Check "D" and "E" locations before the copy layout.
        Rectangle dSourceBounds = checkNodeBounds(diagramEditorSrc, "D", D_NODE_BOUNDS_CASE1, D_NODE_BOUNDS_CASE1);
        Rectangle eSourceBounds = checkNodeBounds(diagramEditorSrc, "E", E_NODE_BOUNDS_CASE1, E_NODE_BOUNDS_CASE1);
        Dimension deltaBetweenDAndE = eSourceBounds.getLocation().getDifference(dSourceBounds.getLocation());

        // Copy D and E layout from source diagram
        SWTBotGefEditPart nodeDEditPart = diagramEditorSrc.getEditPart("D", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart nodeEEditPart = diagramEditorSrc.getEditPart("E", AbstractDiagramNodeEditPart.class);
        diagramEditorSrc.select(nodeDEditPart, nodeEEditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeDEditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorSrc, nodeEEditPart.part()));
        diagramEditorSrc.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditorTgt.show();

        Rectangle absoluteBoundsBeforePaste = diagramEditorTgt.getAbsoluteBounds(diagramEditorTgt.getEditPart("D", AbstractDiagramNodeEditPart.class));

        // Paste layout on D and E
        SWTBotGefEditPart targetNodeDEditPart = diagramEditorTgt.getEditPart("D", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart targetNodeEEditPart = diagramEditorTgt.getEditPart("E", AbstractDiagramNodeEditPart.class);
        diagramEditorTgt.select(targetNodeDEditPart, targetNodeEEditPart);
        bot.waitUntil(new CheckSelectedCondition(diagramEditorTgt, targetNodeDEditPart.part()));
        bot.waitUntil(new CheckSelectedCondition(diagramEditorTgt, targetNodeEEditPart.part()));
        if (pasteFormat) {
            diagramEditorTgt.clickContextMenu(Messages.PasteFormatAction_text);
        } else {
            diagramEditorTgt.clickContextMenu(Messages.PasteLayoutAction_text);
        }

        // Check "D" and "E" locations after the paste layout.
        Rectangle expectedBoundsForD = new Rectangle(absoluteBoundsBeforePaste.getLocation(), D_NODE_BOUNDS_CASE1.getSize());
        checkNodeBounds(diagramEditorTgt, "D", expectedBoundsForD, expectedBoundsForD);
        Rectangle expectedBoundsForE = expectedBoundsForD.getTranslated(deltaBetweenDAndE.width, deltaBetweenDAndE.height);
        checkNodeBounds(diagramEditorTgt, "E", expectedBoundsForE, expectedBoundsForE);
    }

    /**
     * Change the boolean preferences concerning the Paste mode and store the old values. These values will be
     * automatically reset during tear down.
     *
     * WARNING : THIS METHOD MUST BE CALL ONLY ONCE PER TEST (set up + test)
     */
    private void forceBoundingBoxPasteMode() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PROMPT_PASTE_MODE.name(), false);
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_PASTE_MODE_ABSOLUTE.name(), false);
    }

    /**
     * Check that the container bounds (GMF and Draw2D) are as expected.
     * 
     * @param editorToCheck
     *            diagram editor to check
     * @param label
     *            Label of the container to check.
     * @param expectedGmfBounds
     *            The GMF expected bounds
     * @param expectedFigureBounds
     *            The draw2d expected bounds. If the x, y , width or height in this bounds is equal to -1, we don't
     *            check it. This is useful in case of size that depends on Font (with different result according to OS).
     */
    private Rectangle checkContainerBounds(SWTBotSiriusDiagramEditor editorToCheck, String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label, AbstractDiagramElementContainerEditPart.class);
        if (editPart != null) {
            AbstractDiagramElementContainerEditPart part = (AbstractDiagramElementContainerEditPart) editPart.part();
            return comparBoundsFromPart(label, expectedGmfBounds, expectedFigureBounds, part);
        }
        return null;
    }

    /**
     * Check that the node bounds (GMF and Draw2D) are as expected.
     * 
     * @param editorToCheck
     *            diagram editor to check
     * @param label
     *            Label of the node to check.
     * @param expectedGmfBounds
     *            The GMF expected bounds
     * @param expectedFigureBounds
     *            The draw2d expected bounds. If the x, y , width or height in this bounds is equal to -1, we don't
     *            check it. This is useful in case of size that depends on Font (with different result according to OS).
     */
    private Rectangle checkNodeBounds(SWTBotSiriusDiagramEditor editorToCheck, String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label, AbstractDiagramNodeEditPart.class);
        if (editPart != null) {
            AbstractDiagramNodeEditPart part = (AbstractDiagramNodeEditPart) editPart.part();
            return comparBoundsFromPart(label, expectedGmfBounds, expectedFigureBounds, part);
        }
        return null;
    }

    /**
     * Compare expected and obtained bounds (GMF and Draw2D) from an edit part.
     * 
     * @param label
     *            Label of the container to check.
     * @param expectedGmfBounds
     *            The GMF expected bounds
     * @param expectedFigureBounds
     *            The draw2d expected bounds. If the x, y , width or height in this bounds is equal to -1, we don't
     *            check it. This is useful in case of size that depends on Font (with different result according to OS).
     * @param part
     *            edit part to compare
     */
    private Rectangle comparBoundsFromPart(String label, Rectangle expectedGmfBounds, Rectangle expectedFigureBounds, AbstractBorderedShapeEditPart part) {
        IFigure mainFigure = part.getMainFigure();
        Rectangle bounds = mainFigure.getBounds();
        assertEquals("Wrong GMF bounds for " + label, expectedGmfBounds, getBounds((Node) part.getNotationView()));
        if (expectedFigureBounds.x() != -1 && expectedFigureBounds.y() != -1 && expectedFigureBounds.width() != -1 && expectedFigureBounds.height() != -1) {
            assertEquals("Wrong Draw2D bounds for " + label, expectedFigureBounds, bounds);
        } else {
            if (expectedFigureBounds.x() != -1) {
                assertEquals("Wrong Draw2D x for " + label, expectedFigureBounds.x(), bounds.x());
            }
            if (expectedFigureBounds.y() != -1) {
                assertEquals("Wrong Draw2D y for " + label, expectedFigureBounds.y(), bounds.y());
            }
            if (expectedFigureBounds.width() != -1) {
                assertEquals("Wrong Draw2D width for " + label, expectedFigureBounds.width(), bounds.width());
            }
            if (expectedFigureBounds.height() != -1) {
                assertEquals("Wrong Draw2D height for " + label, expectedFigureBounds.height(), bounds.height());
            }
        }
        return bounds;
    }

    private Rectangle getBounds(Node notationView) {
        Rectangle bounds = new Rectangle();
        LayoutConstraint layoutConstraint = notationView.getLayoutConstraint();
        if (layoutConstraint instanceof Location) {
            Location location = (Location) layoutConstraint;
            bounds.setLocation(location.getX(), location.getY());
        }
        if (layoutConstraint instanceof Size) {
            Size size = (Size) layoutConstraint;
            bounds.setSize(size.getWidth(), size.getHeight());
        }
        return bounds;
    }

    /**
     * Check that style of the node matches with gray square.
     * 
     * @param editorToCheck
     *            diagram editor to check
     * @param label
     *            Label of the node to check.
     */
    private void checkGraySquareStyle(SWTBotSiriusDiagramEditor editorToCheck, String label) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label, AbstractDiagramNodeEditPart.class);
        if (editPart != null) {
            AbstractDiagramNodeEditPart part = (AbstractDiagramNodeEditPart) editPart.part();
            Object shape = part.getStyleEditPart().getFigure().getChildren().get(0);
            assertTrue("The shape of figure for " + label + " must be a square.", shape instanceof SquareFigure);
            Color gray = new Color(null, 136, 136, 136);
            try {
                assertEquals("The color of figure for " + label + " must be gray.", gray, ((Shape) shape).getBackgroundColor());
            } finally {
                gray.dispose();
            }
        }
    }

    /**
     * Check that style of the node matches with orange diamond.
     * 
     * @param editorToCheck
     *            diagram editor to check
     * @param label
     *            Label of the node to check.
     */
    private void checkOrangeDiamondStyle(SWTBotSiriusDiagramEditor editorToCheck, String label) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label, AbstractDiagramNodeEditPart.class);
        if (editPart != null) {
            AbstractDiagramNodeEditPart part = (AbstractDiagramNodeEditPart) editPart.part();
            Object shape = part.getStyleEditPart().getFigure().getChildren().get(0);
            assertTrue("The shape of figure for " + label + " must be a diamond.", shape instanceof LozengeFigure);
            Color orange = new Color(null, 252, 175, 62);
            Color blue = new Color(null, 152, 168, 191);
            try {
                assertEquals("The color of figure for " + label + " must be orange.", orange, ((Shape) shape).getBackgroundColor());
                assertEquals("The color of the label for " + label + " must be blue.", blue, ((IFigure) ((Shape) shape).getChildren().get(0)).getForegroundColor());
            } finally {
                orange.dispose();
                blue.dispose();
            }
        }
    }

    /**
     * Check that style of the node matches with yellow ellipse.
     * 
     * @param editorToCheck
     *            diagram editor to check
     * @param label
     *            Label of the node to check.
     */
    private void checkYellowEllipseStyle(SWTBotSiriusDiagramEditor editorToCheck, String label) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label, AbstractDiagramNodeEditPart.class);
        if (editPart != null) {
            AbstractDiagramNodeEditPart part = (AbstractDiagramNodeEditPart) editPart.part();
            Object shape = part.getStyleEditPart().getFigure().getChildren().get(0);
            assertTrue("The shape of figure for " + label + " must be an ellipse.", shape instanceof ODesignEllipseFigure);
            Color yellow = new Color(null, 252, 233, 79);
            try {
                assertEquals("The color of figure for " + label + " must be yellow.", yellow, ((Shape) shape).getBackgroundColor());
            } finally {
                yellow.dispose();
            }
        }
    }

    /**
     * Check that style of the node matches with thux workspace image.
     * 
     * @param editorToCheck
     *            diagram editor to check
     * @param label
     *            Label of the node to check.
     */
    private void checkWSFigureStyle(SWTBotSiriusDiagramEditor editorToCheck, String label) {
        SWTBotGefEditPart editPart = editorToCheck.getEditPart(label, AbstractDiagramNodeEditPart.class);
        if (editPart != null) {
            AbstractDiagramNodeEditPart part = (AbstractDiagramNodeEditPart) editPart.part();
            Object shape = part.getStyleEditPart().getFigure().getChildren().get(0);
            assertTrue("The shape of figure " + label + " must be a workspace image.", shape instanceof SVGWorkspaceImageFigure);
            String[] uri = ((SVGWorkspaceImageFigure) shape).getURI().split("/");
            String imageName = uri[uri.length - 1];
            assertEquals("The workspace Image figure for " + label + " must reference Tux.svg image.", "Tux.svg", imageName);
        }
    }
}
