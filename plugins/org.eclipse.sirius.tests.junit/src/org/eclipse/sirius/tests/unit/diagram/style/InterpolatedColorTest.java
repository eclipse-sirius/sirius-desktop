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

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart.SquareFigure;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;

import com.google.common.collect.Iterables;

/**
 * 
 * @author smonnier
 */
public class InterpolatedColorTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket1176/test1176.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket1176/test1176.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/ticket1176/test1176.odesign";

    private static final String REPRESENTATION_DESC_NAME_A = "test_1176_A";

    private static final String REPRESENTATION_DESC_NAME_B = "test_1176_B";

    private static final String REPRESENTATION_DESC_NAME_C = "test_1176_C";

    private static final String REPRESENTATION_DESC_NAME_D = "test_1176_D";

    private static final RGBValues JAUNE = RGBValues.create(255, 255, 0);

    private static final RGBValues ROSE = RGBValues.create(255, 128, 255);

    private static final RGBValues BLEU = RGBValues.create(0, 128, 255);

    private static final RGBValues DEFAULT_GREEN = RGBValues.create(0, 255, 0);

    private static final RGBValues DEFAULT_RED = RGBValues.create(255, 0, 0);

    private static final HashMap<RGBValues, Integer> colorStepValue = new HashMap<RGBValues, Integer>();

    private DDiagram diagram;

    private DiagramEditor editor;

    private void initColors() {
        colorStepValue.put(BLEU, 1);

        colorStepValue.put(JAUNE, 5);

        colorStepValue.put(ROSE, 10);

        colorStepValue.put(DEFAULT_GREEN, 0);

        colorStepValue.put(DEFAULT_RED, 10);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        initColors();
    }

    /**
     * 
     * Validates an Interpolated Color using 3 Valid Color Steps (and many
     * invalid Color Steps).
     * 
     * @throws Exception
     */
    public void test3ValidColorSteps() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME_A).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        validateColor(0, BLEU, BLEU);
        validateColor(1, BLEU, BLEU);
        validateColor(2, BLEU, JAUNE);
        validateColor(3, BLEU, JAUNE);
        validateColor(4, BLEU, JAUNE);
        validateColor(5, JAUNE, JAUNE);
        validateColor(6, JAUNE, ROSE);
        validateColor(7, JAUNE, ROSE);
        validateColor(8, JAUNE, ROSE);
        validateColor(9, JAUNE, ROSE);
        validateColor(10, ROSE, ROSE);
        validateColor(15, ROSE, ROSE);
    }

    /**
     * 
     * Validates an Interpolated Color having 1 Valid Color Steps (and many
     * invalid Color Steps).
     * 
     * @throws Exception
     */
    public void test1ValidColorStep() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME_B).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        validateColor(0, JAUNE, JAUNE);
        validateColor(1, JAUNE, JAUNE);
        validateColor(2, JAUNE, JAUNE);
        validateColor(3, JAUNE, JAUNE);
        validateColor(4, JAUNE, JAUNE);
        validateColor(5, JAUNE, JAUNE);
        validateColor(6, JAUNE, JAUNE);
        validateColor(7, JAUNE, JAUNE);
        validateColor(8, JAUNE, JAUNE);
        validateColor(9, JAUNE, JAUNE);
        validateColor(10, JAUNE, JAUNE);
        validateColor(15, JAUNE, JAUNE);
    }

    /**
     * 
     * Validates an Interpolated Color having no valid Color Step and many
     * invalid Color Steps.
     * 
     * @throws Exception
     */
    public void testNoValidColorStepManyUndefinedColorSteps() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME_C).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        validateColor(0, DEFAULT_GREEN, DEFAULT_GREEN);
        validateColor(1, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(2, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(3, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(4, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(5, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(6, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(7, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(8, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(9, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(10, DEFAULT_RED, DEFAULT_RED);
        validateColor(15, DEFAULT_RED, DEFAULT_RED);
    }

    /**
     * 
     * Validates an Interpolated Color having no Color Step.
     * 
     * @throws Exception
     */
    public void testNoColorStep() throws Exception {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME_D).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        validateColor(0, DEFAULT_GREEN, DEFAULT_GREEN);
        validateColor(1, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(2, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(3, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(4, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(5, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(6, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(7, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(8, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(9, DEFAULT_GREEN, DEFAULT_RED);
        validateColor(10, DEFAULT_RED, DEFAULT_RED);
        validateColor(15, DEFAULT_RED, DEFAULT_RED);
    }

    private void validateColor(int value, RGBValues closestLowerFixedColor, RGBValues closestUpperFixedColor) {

        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, Integer.toString(value));
        assertTrue("No diagram element found with the label : " + Integer.toString(value), diagramElementsFromLabel != null && !diagramElementsFromLabel.isEmpty());

        IGraphicalEditPart editPart = getEditPart(diagramElementsFromLabel.get(0));
        assertTrue("No IGraphicalEditPart found with the label : " + Integer.toString(value), editPart != null && editPart.getFigure() != null);
        SquareFigure squareFigureChild = getSquareFigureChild(editPart.getFigure());
        assertNotNull("No Square Figure found for " + Integer.toString(value), squareFigureChild);
        Color backgroundColor = squareFigureChild.getBackgroundColor();
        RGBValues expectedRGBValues = expectedRGBValues(value, closestLowerFixedColor, closestUpperFixedColor);

        assertEquals("The red color does not match for " + Integer.toString(value), expectedRGBValues.getRed(), backgroundColor.getRed());
        assertEquals("The green color does not match for " + Integer.toString(value), expectedRGBValues.getGreen(), backgroundColor.getGreen());
        assertEquals("The blue color does not match for " + Integer.toString(value), expectedRGBValues.getBlue(), backgroundColor.getBlue());
    }

    /**
     * Finds the SquareFigure children that contains the Color informations.
     * 
     * @param figure
     * @return
     */
    private SquareFigure getSquareFigureChild(IFigure figure) {
        return getSquareFigureChild(figure, 0, 50);
    }

    private SquareFigure getSquareFigureChild(IFigure figure, int depth, int maxDepth) {
        if (depth >= maxDepth) {
            throw new IllegalStateException("Probable loop in figure hierachy: depth >= " + maxDepth + " at " + figure);
        }
        if (!(figure instanceof SquareFigure) && figure.getChildren() != null && !figure.getChildren().isEmpty()) {
            if (!(Iterables.isEmpty(Iterables.filter(figure.getChildren(), SquareFigure.class)))) {
                return Iterables.get((Iterables.filter(figure.getChildren(), SquareFigure.class)), 0);
            }
            for (IFigure childFigure : Iterables.filter(figure.getChildren(), IFigure.class)) {
                SquareFigure squareFigureChild = getSquareFigureChild(childFigure, depth + 1, maxDepth);
                if (squareFigureChild != null) {
                    return squareFigureChild;
                }
            }
        }

        return null;
    }

    /**
     * Calculates the interpolated color between closestLowerFixedColor and
     * closestUpperFixedColor with value.
     * 
     * @param value
     * @param closestLowerFixedColor
     * @param closestUpperFixedColor
     * @return
     */
    private RGBValues expectedRGBValues(int value, RGBValues closestLowerFixedColor, RGBValues closestUpperFixedColor) {
        if (closestLowerFixedColor.equals(closestUpperFixedColor)) {
            return closestLowerFixedColor;
        }
        int closestLowerBound = colorStepValue.get(closestLowerFixedColor);
        int closestUpperBound = colorStepValue.get(closestUpperFixedColor);

        final float scale = ((float) value - closestLowerBound) / (closestUpperBound - closestLowerBound);
        final int valRed = (int) (closestLowerFixedColor.getRed() + ((closestUpperFixedColor.getRed() - closestLowerFixedColor.getRed()) * scale));
        final int valGreen = (int) (closestLowerFixedColor.getGreen() + ((closestUpperFixedColor.getGreen() - closestLowerFixedColor.getGreen()) * scale));
        final int valBlue = (int) (closestLowerFixedColor.getBlue() + ((closestUpperFixedColor.getBlue() - closestLowerFixedColor.getBlue()) * scale));
        int r = VisualBindingManager.clamp(valRed, 0, 255);
        int g = VisualBindingManager.clamp(valGreen, 0, 255);
        int b = VisualBindingManager.clamp(valBlue, 0, 255);
        return RGBValues.create(r, g, b);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        colorStepValue.clear();
        diagram = null;
        editor = null;

        super.tearDown();
    }
}
