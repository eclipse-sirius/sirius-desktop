/*******************************************************************************
 * Copyright (c) 2016, 2022 THALES GLOBAL SERVICES.
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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart.SquareFigure;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.draw2d.figure.LozengeFigure;
import org.eclipse.sirius.ext.draw2d.figure.ODesignEllipseFigure;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test container and node Copy-Paste layout and style from and to diagram with
 * extension.
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

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Diagram";

    private static final String REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE = "DiagramWithSquareLCNode";

    private static final String REPRESENTATION_DESCRIPTION_NAME_ELLIPSE_LCNODE = "DiagramWithYellowEllipseLCNode";

    private static final String REPRESENTATION_DESCRIPTION_NAME_DIAMOND_LCNODE = "DiagramWithOrangeDiamondLCNode";

    private static final String REPRESENTATION_DESCRIPTION_NAME_IMAGE_LCNODE = "DiagramWithImageLCNode";

    /**
     * Diagram on third representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ).
     */
    private SWTBotSiriusDiagramEditor diagramEditor3;

    /**
     * Diagram on second representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ).
     */
    private SWTBotSiriusDiagramEditor diagramEditor4;

    /**
     * Diagram on fifth representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ), package are represented by gray square.
     */
    private SWTBotSiriusDiagramEditor diagramEditor5;

    /**
     * Diagram on fifth representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ), package are represented by orange diamond.
     */
    private SWTBotSiriusDiagramEditor diagramEditor6;

    /**
     * Diagram on fifth representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ), package are represented by yellow ellipse.
     */
    private SWTBotSiriusDiagramEditor diagramEditor7;

    /**
     * Diagram on fifth representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ), package are represented by workspace image.
     */
    private SWTBotSiriusDiagramEditor diagramEditor8;

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
        diagramEditor3 = null;
        diagramEditor4 = null;
        diagramEditor5 = null;
        diagramEditor6 = null;
        diagramEditor7 = null;
        diagramEditor8 = null;
        super.tearDown();
    }

    /**
     * Test that the paste layout puts the elements at the expected location.
     * This test uses the copy-paste layout on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in).
     * <ul>
     * <li>Check container locations before copy-paste on rep3</li>
     * <li>Run copy-paste from rep3 to rep4</li>
     * <li>Check container locations after copy-paste on rep4</li>
     * </ul>
     */
    public void testContainerCopyPasteLayoutOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor4 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME4, DDiagram.class);
        diagramEditor3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);

        // Check LogicalFunction1 container locations before the copy layout.
        checkContainerBounds(diagramEditor3, LC1_LABEL, LC1_CONTAINER_BOUNDS_DIAG3, LC1_CONTAINER_BOUNDS_DIAG3);

        // Check LogicalFunction2 container locations before the copy layout.
        checkContainerBounds(diagramEditor3, LC2_LABEL, LC2_CONTAINER_BOUNDS_DIAG3, LC2_CONTAINER_BOUNDS_DIAG3);

        Option<Color> originalCopiedColor = getBackgroundColor(diagramEditor3, LC2_LABEL);

        // Copy LC1 layout
        diagramEditor3.click(LC2_POINT);
        diagramEditor3.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor4.show();

        // Check LogicalFunction1 container locations before the paste layout.
        checkContainerBounds(diagramEditor4, LC1_LABEL, LC1_CONTAINER_BOUNDS_DIAG4, LC1_CONTAINER_BOUNDS_DIAG4);

        // Check LogicalFunction2 container locations before the paste layout.
        checkContainerBounds(diagramEditor4, LC2_LABEL, LC2_CONTAINER_BOUNDS_DIAG4, LC2_CONTAINER_BOUNDS_DIAG4);

        // Paste layout on second representation
        diagramEditor4.click(EMPTY_POINT);
        diagramEditor4.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 container locations after the paste layout.
        checkContainerBounds(diagramEditor4, LC1_LABEL, LC2_CONTAINER_BOUNDS_DIAG3, LC2_CONTAINER_BOUNDS_DIAG3);

        // Check that style has not changed (current color is not the copied
        // color)
        Option<Color> currentColor = getBackgroundColor(diagramEditor4, LC1_LABEL);
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
     * Test that the paste layout puts the elements at the expected location.
     * This test uses the copy-paste layout on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in) Layout of
     * container LC2 from a first diagram is applied on node LC1 from a second
     * diagram.
     * <ul>
     * <li>Check container locations before copy-paste on rep3</li>
     * <li>Run copy-paste from rep3 to rep5</li>
     * <li>Check container locations after copy-paste on rep5. Check node
     * becomes container.</li>
     * </ul>
     */
    public void testContainerToNodeCopyPasteLayoutOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor5 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditor3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);

        // Check LogicalFunction1 container locations before the copy layout.
        checkContainerBounds(diagramEditor3, LC1_LABEL, LC1_CONTAINER_BOUNDS_DIAG3, LC1_CONTAINER_BOUNDS_DIAG3);

        // Check LogicalFunction2 container locations before the copy layout.
        Rectangle LC2Bounds = checkContainerBounds(diagramEditor3, LC2_LABEL, LC2_CONTAINER_BOUNDS_DIAG3, LC2_CONTAINER_BOUNDS_DIAG3);

        // Copy LC1 layout
        diagramEditor3.click(LC2_POINT);
        diagramEditor3.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor5.show();

        // Check LogicalFunction1 node locations before the paste layout.
        checkNodeBounds(diagramEditor5, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout.
        checkNodeBounds(diagramEditor5, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // Paste layout on second representation
        diagramEditor5.click(EMPTY_POINT);
        diagramEditor5.clickContextMenu(Messages.PasteLayoutAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        checkNodeBounds(diagramEditor5, LC1_LABEL, LC2Bounds, LC2Bounds);
    }

    /**
     * Test that the paste style changes style on the expected figure. This test
     * uses the copy-paste style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in). Style of
     * node LC2 from a first diagram is applied on node LC1 from a second
     * diagram.
     * <ul>
     * <li>Check node location and style before paste on rep5</li>
     * <li>Run copy-paste Style from rep6 to rep5</li>
     * <li>Check node locations and style after copy-paste on rep5. Check style
     * node changes into yellow ellipse.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteEllipseStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor5 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditor6 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_ELLIPSE_LCNODE, REPRESENTATION_NAME_WITH_ELLIPSE,
                DDiagram.class);

        // Copy LC1 style
        diagramEditor6.click(LC2_POINT);
        diagramEditor6.clickContextMenu(Messages.CopyFormatAction_text);
        diagramEditor5.show();

        // Check LogicalFunction1 node locations before the paste style.
        Rectangle LC1Bounds = checkNodeBounds(diagramEditor5, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste style.
        checkNodeBounds(diagramEditor5, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditor5, LC1_LABEL);

        // Paste style on second representation
        diagramEditor5.click(EMPTY_POINT);
        diagramEditor5.clickContextMenu(Messages.PasteStyleAction_text);

        // Check LogicalFunction1 node locations does not changed after the
        // paste layout.
        checkNodeBounds(diagramEditor5, LC1_LABEL, LC1Bounds, LC1Bounds);

        // Check style
        checkYellowEllipseStyle(diagramEditor5, LC1_LABEL);
    }

    /**
     * Test that the paste style changes style on the expected figure. This test
     * uses the copy-paste style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in). Style of
     * node LC2 from a first diagram is applied on node LC1 from a second
     * diagram.
     * <ul>
     * <li>Check node location and style before paste on rep5</li>
     * <li>Run copy-paste Style from rep7 to rep5</li>
     * <li>Check node locations and style after copy-paste on rep5. Check style
     * node changes into orange diamond.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteDiamondStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor5 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditor7 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_DIAMOND_LCNODE, REPRESENTATION_NAME_WITH_DIAMOND,
                DDiagram.class);

        // Copy LC1 layout
        diagramEditor7.click(LC2_POINT);
        diagramEditor7.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor5.show();

        // Check LogicalFunction1 node locations before the paste style.
        Rectangle LC1Bounds = checkNodeBounds(diagramEditor5, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste style.
        checkNodeBounds(diagramEditor5, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditor5, LC1_LABEL);

        // Paste layout on second representation
        diagramEditor5.click(EMPTY_POINT);
        diagramEditor5.clickContextMenu(Messages.PasteStyleAction_text);

        // Check LogicalFunction1 node locations does not change after the paste
        // style.
        checkNodeBounds(diagramEditor5, LC1_LABEL, LC1Bounds, LC1Bounds);

        // check style
        checkOrangeDiamondStyle(diagramEditor5, LC1_LABEL);
    }

    /**
     * Test that the paste style changes style on the expected figure. This test
     * uses the copy-paste style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in). Style of
     * node LC2 from a first diagram is applied on node LC1 from a second
     * diagram.
     * <ul>
     * <li>Check node location and style before paste on rep5</li>
     * <li>Run copy-paste Style from rep8 to rep5</li>
     * <li>Check node locations and style after copy-paste on rep5. Check style
     * node changes into workspace Image.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteWSImageStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor5 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditor8 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_IMAGE_LCNODE, REPRESENTATION_NAME_WITH_IMAGE, DDiagram.class);

        // Copy LC1 Style
        diagramEditor8.click(LC2_POINT);
        diagramEditor8.clickContextMenu(Messages.CopyFormatAction_text);
        diagramEditor5.show();

        // Check LogicalFunction1 node locations before the paste style.
        Rectangle LC1Bounds = checkNodeBounds(diagramEditor5, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste style.
        checkNodeBounds(diagramEditor5, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditor5, LC1_LABEL);

        // Paste style on second representation
        diagramEditor5.click(EMPTY_POINT);
        diagramEditor5.clickContextMenu(Messages.PasteStyleAction_text);

        // Check LogicalFunction1 node locations does not change after the paste
        // style.
        checkNodeBounds(diagramEditor5, LC1_LABEL, LC1Bounds, LC1Bounds);

        // Check that the style has changed
        checkWSFigureStyle(diagramEditor5, LC1_LABEL);
    }

    /**
     * Test that the paste style and layout changes style on the expected figure
     * and puts the elements at the expected location. This test uses the
     * copy-paste layout and style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in). Style and
     * layout of node LC2 from a first diagram is applied on node LC1 from a
     * second diagram.
     * <ul>
     * <li>Check node locations before copy-paste on rep3</li>
     * <li>Run copy-paste Style from rep6 to rep5</li>
     * <li>Check style node changes into yellow ellipse.</li>
     * <li>Check location changes.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteEllipseLayoutAndStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor5 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditor6 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_ELLIPSE_LCNODE, REPRESENTATION_NAME_WITH_ELLIPSE,
                DDiagram.class);

        // Check LogicalFunction2 node locations before the copy layout and
        // style.
        Rectangle LC2Diag6Bounds = checkNodeBounds(diagramEditor6, LC2_LABEL, NEW_STYLE_LC2_NODE_BOUNDS, NEW_STYLE_LC2_NODE_BOUNDS);

        // Copy LC1 layout and Style
        diagramEditor6.click(LC2_POINT);
        diagramEditor6.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor5.show();

        // Check LogicalFunction1 node locations before the paste layout and
        // style.
        checkNodeBounds(diagramEditor5, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout and
        // style.
        Rectangle LC2Diag5Bounds = checkNodeBounds(diagramEditor5, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditor5, LC1_LABEL);

        // Paste layout on second representation
        diagramEditor5.click(EMPTY_POINT);
        diagramEditor5.clickContextMenu(Messages.PasteFormatAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        // By using diagram extension, it matches with LC2 bounds of diagram6
        // representation (LC2 annotation on LC1).
        checkNodeBounds(diagramEditor5, LC1_LABEL, LC2Diag6Bounds, LC2Diag6Bounds);
        // Check LogicalFunction2 node locations does not change after the paste
        // layout (no annotation on LC2).
        checkNodeBounds(diagramEditor5, LC2_LABEL, LC2Diag5Bounds, LC2Diag5Bounds);

        // Check style
        checkYellowEllipseStyle(diagramEditor5, LC1_LABEL);
    }

    /**
     * Test that the paste style and layout changes style on the expected figure
     * and puts the elements at the expected location. This test uses the
     * copy-paste layout and style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in). Style and
     * layout of node LC2 from a first diagram is applied on node LC1 from a
     * second diagram.
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
        diagramEditor5 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditor7 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_DIAMOND_LCNODE, REPRESENTATION_NAME_WITH_DIAMOND,
                DDiagram.class);

        // Check LogicalFunction2 node locations before the copy layout and
        // style.
        Rectangle LC2Diag7Bounds = checkNodeBounds(diagramEditor7, LC2_LABEL, NEW_STYLE_LC2_NODE_BOUNDS, NEW_STYLE_LC2_NODE_BOUNDS);

        // Copy LC1 layout and style
        diagramEditor7.click(LC2_POINT);
        diagramEditor7.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor5.show();

        // Check LogicalFunction1 node locations before the paste layout and
        // style.
        checkNodeBounds(diagramEditor5, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout and
        // style.
        Rectangle LC2Diag5Bounds = checkNodeBounds(diagramEditor5, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditor5, LC1_LABEL);

        // Paste layout on second representation
        diagramEditor5.click(EMPTY_POINT);
        diagramEditor5.clickContextMenu(Messages.PasteFormatAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        // By using diagram extension, it matches with LC2 bounds of diagram7
        // representation (LC2 annotation on LC1).
        checkNodeBounds(diagramEditor5, LC1_LABEL, LC2Diag7Bounds, LC2Diag7Bounds);
        // Check LogicalFunction2 node locations does not change after the paste
        // layout (no annotation on LC2).
        checkNodeBounds(diagramEditor5, LC2_LABEL, LC2Diag5Bounds, LC2Diag5Bounds);

        // Check style
        checkOrangeDiamondStyle(diagramEditor5, LC1_LABEL);
    }

    /**
     * Test that the paste style and layout changes style on the expected figure
     * and puts the elements at the expected location. This test uses the
     * copy-paste layout and style on Diagram using the
     * org.eclipse.sirius.diagram.ui.layoutDataManager extension-point (the
     * sampleNameDataProvider contributed in oes.tests.junit plug-in). Style and
     * layout of node LC2 from a first diagram is applied on node LC1 from a
     * second diagram.
     * <ul>
     * <li>Check node locations before copy-paste on rep3</li>
     * <li>Run copy-paste Style from rep8 to rep5</li>
     * <li>Check style node changes into thux workspace image.</li>
     * <li>Check location changes.</li>
     * </ul>
     */
    public void testNodeToNodeCopyPasteImageLayoutAndStyleOnDiagramWithExtension() {
        // Open the 2 required representations
        diagramEditor5 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_SQUARE_LCNODE, REPRESENTATION_NAME_WITH_SQUARE,
                DDiagram.class);
        diagramEditor8 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME_IMAGE_LCNODE, REPRESENTATION_NAME_WITH_IMAGE, DDiagram.class);

        // Check LogicalFunction2 node locations before the copy layout and
        // style.
        Rectangle LC2Diag8Bounds = checkNodeBounds(diagramEditor8, LC2_LABEL, NEW_STYLE_LC2_NODE_BOUNDS, NEW_STYLE_LC2_NODE_BOUNDS);

        // Copy LC1 layout
        diagramEditor8.click(LC2_POINT);
        diagramEditor8.clickContextMenu(Messages.CopyFormatAction_text);

        diagramEditor5.show();

        // Check LogicalFunction1 node locations before the paste layout and
        // style.
        checkNodeBounds(diagramEditor5, LC1_LABEL, INITIAL_LC1_SQUARE_NODE_BOUNDS, INITIAL_LC1_SQUARE_NODE_BOUNDS);

        // Check LogicalFunction2 node locations before the paste layout and
        // style.
        Rectangle LC2Diag5Bounds = checkNodeBounds(diagramEditor5, LC2_LABEL, INITIAL_LC2_SQUARE_NODE_BOUNDS, INITIAL_LC2_SQUARE_NODE_BOUNDS);

        // check LogicalFunction1 node style before the paste style.
        checkGraySquareStyle(diagramEditor5, LC1_LABEL);

        // Paste layout on second representation
        diagramEditor5.click(EMPTY_POINT);
        diagramEditor5.clickContextMenu(Messages.PasteFormatAction_text);

        // Check LogicalFunction1 node locations after the paste layout.
        // By using diagram extension, it matches with LC2 bounds of diagram8
        // representation (LC2 annotation on LC1).
        checkNodeBounds(diagramEditor5, LC1_LABEL, LC2Diag8Bounds, LC2Diag8Bounds);
        // Check LogicalFunction2 node locations does not change after the paste
        // layout (no annotation on LC2).
        checkNodeBounds(diagramEditor5, LC2_LABEL, LC2Diag5Bounds, LC2Diag5Bounds);

        // Check style
        checkWSFigureStyle(diagramEditor5, LC1_LABEL);
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
     *            The draw2d expected bounds. If the x, y , width or height in
     *            this bounds is equal to -1, we don't check it. This is useful
     *            in case of size that depends on Font (with different result
     *            according to OS).
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
     *            The draw2d expected bounds. If the x, y , width or height in
     *            this bounds is equal to -1, we don't check it. This is useful
     *            in case of size that depends on Font (with different result
     *            according to OS).
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
     *            The draw2d expected bounds. If the x, y , width or height in
     *            this bounds is equal to -1, we don't check it. This is useful
     *            in case of size that depends on Font (with different result
     *            according to OS).
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
