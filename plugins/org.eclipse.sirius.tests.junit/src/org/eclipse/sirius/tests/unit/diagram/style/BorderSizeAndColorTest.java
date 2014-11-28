/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.style;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;

import com.google.common.collect.Iterables;

/**
 * 
 * @author smonnier
 */
public class BorderSizeAndColorTest extends SiriusDiagramTestCase {

    /**
     * The id of the outline view.
     */
    private static final String OUTLINE_VIEW_ID = "org.eclipse.ui.views.ContentOutline";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2229/2229.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2229/2229.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket2229/2229.odesign";

    private static final String REPRESENTATION_DESC_NAME = "tc2229";

    private static final int EXPECTED_BORDER_SIZE = 4;

    private static final RGBValues EXPECTED_BORDER_COLOR = RGBValues.create(138, 226, 52);;

    private DDiagram diagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Close outline to improve performance (this test is very long if the
        // outline is open)
        EclipseUIUtil.hideView(OUTLINE_VIEW_ID);

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);

        // Reopen the Outline (for the rest of suite)
        EclipseUIUtil.showView(OUTLINE_VIEW_ID);

        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }

    /**
     * 
     * Validates the border size of a Node mapping with Square description.
     * 
     * @throws Exception
     */
    public void testNodeMappingSquareBorderSize() throws Exception {
        validateNodeStyleBorderSize("Square");
        validateFigureBorderSize("Square");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Lozenge description.
     * 
     * @throws Exception
     */
    public void testNodeMappingLozengeBorderSize() throws Exception {
        validateNodeStyleBorderSize("Lozenge");
        validateFigureBorderSize("Lozenge");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Ellipse description.
     * 
     * @throws Exception
     */
    public void testNodeMappingEllipseBorderSize() throws Exception {
        validateNodeStyleBorderSize("Ellipse");
        validateFigureBorderSize("Ellipse");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Bundle Image Square
     * description.
     * 
     * @throws Exception
     */
    public void testNodeMappingBundleImageSquareBorderSize() throws Exception {
        validateNodeStyleBorderSize("Bundle_Image_Square");
        // For the moment, there is no border on a figure corresponding to
        // "Bundle Images" description
        // validateFigureBorderSize("Bundle_Image_Square");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Bundle Image Stroke
     * description.
     * 
     * @throws Exception
     */
    public void testNodeMappingBundleImageStrokeBorderSize() throws Exception {
        validateNodeStyleBorderSize("Bundle_Image_Stroke");
        // For the moment, there is no border on a figure corresponding to
        // "Bundle Images" description
        // validateFigureBorderSize("Bundle_Image_Stroke");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Bundle Image Triangle
     * description.
     * 
     * @throws Exception
     */
    public void testNodeMappingBundleImageTriangleBorderSize() throws Exception {
        validateNodeStyleBorderSize("Bundle_Image_Triangle");
        // For the moment, there is no border on a figure corresponding to
        // "Bundle Images" description
        // validateFigureBorderSize("Bundle_Image_Triangle");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Bundle Image Dot
     * description.
     * 
     * @throws Exception
     */
    public void testNodeMappingBundleImageDotBorderSize() throws Exception {
        validateNodeStyleBorderSize("Bundle_Image_Dot");
        // For the moment, there is no border on a figure corresponding to
        // "Bundle Images" description
        // validateFigureBorderSize("Bundle_Image_Dot");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Bundle Image Ring
     * description.
     * 
     * @throws Exception
     */
    public void testNodeMappingBundleImageRingBorderSize() throws Exception {
        validateNodeStyleBorderSize("Bundle_Image_Ring");
        // For the moment, there is no border on a figure corresponding to
        // "Bundle Images" description
        // validateFigureBorderSize("Bundle_Image_Ring");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Note description.
     * 
     * @throws Exception
     */
    public void testNodeMappingNoteBorderSize() throws Exception {
        validateNodeStyleBorderSize("Note");
        // For the moment, there is no border on a figure corresponding to
        // "Note" description
        // validateFigureBorderSize("Note");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Dot description.
     * 
     * @throws Exception
     */
    public void testNodeMappingDotBorderSize() throws Exception {
        validateNodeStyleBorderSize("Dot");
        validateFigureBorderSize("Dot");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Gauge description.
     * 
     * @throws Exception
     */
    public void testNodeMappingGaugeBorderSize() throws Exception {
        validateNodeStyleBorderSize("Gauge");
        // For the moment, there is no border on a figure corresponding to "Dot"
        // description
        // validateFigureBorderSize("Gauge");
    }

    /**
     * 
     * Validates the border size of a Node mapping with Workspace Image
     * description.
     * 
     * @throws Exception
     */
    public void testNodeMappingWorkspaceImageBorderSize() throws Exception {
        validateNodeStyleBorderSize("Workspace_Image");
        // For the moment, there is no border on a figure corresponding to
        // "Workspace Images" description
        // validateFigureBorderSize("Workspace_Image");
    }

    /**
     * 
     * Validates the border size of a Container mapping with Flat container
     * description.
     * 
     * @throws Exception
     */
    public void testContainerMappingFlatBorderSize() throws Exception {
        validateNodeStyleBorderSize("Flat_Container");
        validateFigureBorderSize("Flat_Container");
    }

    /**
     * 
     * Validates the border size of a Container mapping with Shape container
     * description.
     * 
     * @throws Exception
     */
    public void testContainerMappingShapeBorderSize() throws Exception {
        validateNodeStyleBorderSize("Shape_Container");
        // For the moment, there is no border on a figure corresponding to
        // "Shape container" description
        // validateFigureBorderSize("Shape_Container");
    }

    /**
     * 
     * Validates the border size of a Container mapping with Workspace Image
     * container description.
     * 
     * @throws Exception
     */
    public void testContainerMappingWorkspaceImageBorderSize() throws Exception {
        validateNodeStyleBorderSize("Workspace_Image_Container");
        validateFigureBorderSize("Workspace_Image_Container");
    }

    private void validateNodeStyleBorderSize(String descriptionType) throws Exception {

        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, descriptionType + "_new EClass 1");

        AbstractDNode node = Iterables.getOnlyElement(Iterables.filter(diagramElementsFromLabel, AbstractDNode.class));

        assertTrue(node.getStyle() instanceof BorderedStyle);
        BorderedStyle borderedStyle = (BorderedStyle) node.getStyle();

        // Validate border size and color
        assertEquals((int) EXPECTED_BORDER_SIZE, (int) borderedStyle.getBorderSize());
        assertEquals((int) EXPECTED_BORDER_COLOR.getRed(), (int) borderedStyle.getBorderColor().getRed());
        assertEquals((int) EXPECTED_BORDER_COLOR.getGreen(), (int) borderedStyle.getBorderColor().getGreen());
        assertEquals((int) EXPECTED_BORDER_COLOR.getBlue(), (int) borderedStyle.getBorderColor().getBlue());
    }

    private void validateFigureBorderSize(String descriptionType) throws Exception {
        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, descriptionType + "_new EClass 1");

        AbstractDNode node = Iterables.getOnlyElement(Iterables.filter(diagramElementsFromLabel, AbstractDNode.class));
        IGraphicalEditPart editPart = getEditPart(node);
        IFigure figure = editPart.getFigure();
        assertTrue(validateHavingChildFigureWithBorderInformation(figure));
    }

    private Boolean validateHavingChildFigureWithBorderInformation(IFigure figure) {
        if (figure instanceof Shape) {
            Shape shape = (Shape) figure;
            if (validateShapeBorderInformation(shape)) {
                return true;
            }
        }
        Iterator<?> iterator = figure.getChildren().iterator();
        boolean result = false;
        while (iterator.hasNext() && !result) {
            Object object = (Object) iterator.next();
            if (object instanceof IFigure) {
                IFigure childFigure = (IFigure) object;
                result = validateHavingChildFigureWithBorderInformation(childFigure);
            }
        }
        return result;
    }

    private Boolean validateShapeBorderInformation(Shape shape) {
        Color foregroundColor = shape.getForegroundColor();
        return shape.getLineWidth() == EXPECTED_BORDER_SIZE && foregroundColor.getRed() == EXPECTED_BORDER_COLOR.getRed() && foregroundColor.getGreen() == EXPECTED_BORDER_COLOR.getGreen()
                && foregroundColor.getBlue() == EXPECTED_BORDER_COLOR.getBlue();
    }

}
