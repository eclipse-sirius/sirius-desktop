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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.diagram.AlignmentKind;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.ContainerShape;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.GaugeSection;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.CustomStyleDescription;
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.description.style.util.StyleSwitch;
import org.eclipse.sirius.diagram.ui.business.internal.query.CustomizableQuery;
import org.eclipse.sirius.diagram.util.DiagramSwitch;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

/**
 * Class to test the StyleHelper class.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StyleHelperTest extends TestCase {

    private static final String IMG_WORKSPACE_PATH = "imgWorkspacePath";

    private static final SystemColor COLOR_FIRST;

    private static final SystemColor COLOR_SECOND;

    private static final SystemColor BORDER_COLOR_FIRST;

    private static final SystemColor BORDER_COLOR_SECOND;

    private static final SystemColor LABEL_COLOR_FIRST;

    private static final SystemColor LABEL_COLOR_SECOND;

    private static final SystemColor BACKGROUND_COLOR_FIRST;

    private static final SystemColor BACKGROUND_COLOR_SECOND;

    private static final SystemColor FOREGROUND_COLOR_FIRST;

    private static final SystemColor FOREGROUND_COLOR_SECOND;

    private static final SystemColor STROKE_COLOR_FIRST;

    private static final SystemColor STROKE_COLOR_SECOND;

    private static final RGBValues CUSTOM_COLOR;

    static {
        COLOR_FIRST = DescriptionFactory.eINSTANCE.createSystemColor();
        COLOR_FIRST.setName("Color first value");
        COLOR_FIRST.setBlue(0);
        COLOR_FIRST.setGreen(10);
        COLOR_FIRST.setRed(20);

        COLOR_SECOND = DescriptionFactory.eINSTANCE.createSystemColor();
        COLOR_SECOND.setName("color second value");
        COLOR_SECOND.setBlue(10);
        COLOR_SECOND.setGreen(20);
        COLOR_SECOND.setRed(30);

        BORDER_COLOR_FIRST = DescriptionFactory.eINSTANCE.createSystemColor();
        BORDER_COLOR_FIRST.setName("BorderColor first value");
        BORDER_COLOR_FIRST.setBlue(20);
        BORDER_COLOR_FIRST.setGreen(30);
        BORDER_COLOR_FIRST.setRed(40);

        BORDER_COLOR_SECOND = DescriptionFactory.eINSTANCE.createSystemColor();
        BORDER_COLOR_SECOND.setName("BorderColor second value");
        BORDER_COLOR_SECOND.setBlue(30);
        BORDER_COLOR_SECOND.setGreen(40);
        BORDER_COLOR_SECOND.setRed(50);

        LABEL_COLOR_FIRST = DescriptionFactory.eINSTANCE.createSystemColor();
        LABEL_COLOR_FIRST.setName("LabelColor first value");
        LABEL_COLOR_FIRST.setBlue(20);
        LABEL_COLOR_FIRST.setGreen(30);
        LABEL_COLOR_FIRST.setRed(40);

        LABEL_COLOR_SECOND = DescriptionFactory.eINSTANCE.createSystemColor();
        LABEL_COLOR_SECOND.setName("LabelColor second value");
        LABEL_COLOR_SECOND.setBlue(30);
        LABEL_COLOR_SECOND.setGreen(40);
        LABEL_COLOR_SECOND.setRed(50);

        BACKGROUND_COLOR_FIRST = DescriptionFactory.eINSTANCE.createSystemColor();
        BACKGROUND_COLOR_FIRST.setName("Fground color first value");
        BACKGROUND_COLOR_FIRST.setBlue(40);
        BACKGROUND_COLOR_FIRST.setGreen(50);
        BACKGROUND_COLOR_FIRST.setRed(60);

        BACKGROUND_COLOR_SECOND = DescriptionFactory.eINSTANCE.createSystemColor();
        BACKGROUND_COLOR_SECOND.setName("Background color second value");
        BACKGROUND_COLOR_SECOND.setBlue(50);
        BACKGROUND_COLOR_SECOND.setGreen(60);
        BACKGROUND_COLOR_SECOND.setRed(70);

        FOREGROUND_COLOR_FIRST = DescriptionFactory.eINSTANCE.createSystemColor();
        FOREGROUND_COLOR_FIRST.setName("Foreground color first value");
        FOREGROUND_COLOR_FIRST.setBlue(60);
        FOREGROUND_COLOR_FIRST.setGreen(70);
        FOREGROUND_COLOR_FIRST.setRed(80);

        FOREGROUND_COLOR_SECOND = DescriptionFactory.eINSTANCE.createSystemColor();
        FOREGROUND_COLOR_SECOND.setName("Foreground color second value");
        FOREGROUND_COLOR_SECOND.setBlue(70);
        FOREGROUND_COLOR_SECOND.setGreen(80);
        FOREGROUND_COLOR_SECOND.setRed(90);

        STROKE_COLOR_FIRST = DescriptionFactory.eINSTANCE.createSystemColor();
        STROKE_COLOR_FIRST.setName("Stroke color first value");
        STROKE_COLOR_FIRST.setBlue(80);
        STROKE_COLOR_FIRST.setGreen(90);
        STROKE_COLOR_FIRST.setRed(100);

        STROKE_COLOR_SECOND = DescriptionFactory.eINSTANCE.createSystemColor();
        STROKE_COLOR_SECOND.setName("Stroke color second value");
        STROKE_COLOR_SECOND.setBlue(90);
        STROKE_COLOR_SECOND.setGreen(100);
        STROKE_COLOR_SECOND.setRed(110);

        CUSTOM_COLOR = RGBValues.create(33, 33, 33);
    }

    private StyleHelper styleHelper;

    @Override
    protected void setUp() {
        this.styleHelper = new StyleHelper(CompoundInterpreter.INSTANCE);
    }

    @Override
    protected void tearDown() throws Exception {
        styleHelper = null;
        super.tearDown();
    }

    /**
     * Test the creation of an edge style from an edge description style with
     * the first possible value of each property.
     */
    public void testCreateEdgeStyleWithFirstValues() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        EdgeStyle edgeStyle = styleHelper.createEdgeStyle(edgeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);
    }

    /**
     * Test the creation of an edge style from an edge description style with
     * the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateEdgeStyleGenericWithFirstValues() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(edgeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof EdgeStyle);
        new CheckStyleCreationForStyleDescSwitch(edgeStyleDesc).doSwitch((EdgeStyle) style);
    }

    /**
     * Test the creation of an edge style from an edge description style with
     * the second possible value of each property. If one property can't have a
     * second possible value, the same of the first test is used.
     */
    public void testCreateEdgeStyleWithSecondValues() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        EdgeStyle edgeStyle = styleHelper.createEdgeStyle(edgeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);
    }

    /**
     * Test the creation of an edge style from a bracket edge description style
     * with the first possible value of each property.
     */
    public void testCreateBracketEdgeStyleWithFirstValues() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createBracketEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        EdgeStyle edgeStyle = styleHelper.createEdgeStyle(edgeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);
    }

    /**
     * Test the creation of an edge style from a bracket edge description style
     * with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateBracketEdgeStyleGenericWithFirstValues() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createBracketEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(edgeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof EdgeStyle);
        new CheckStyleCreationForStyleDescSwitch(edgeStyleDesc).doSwitch((EdgeStyle) style);
    }

    /**
     * Test the creation of an edge style from a bracket edge description style
     * with the second possible value of each property. If one property can't
     * have a second possible value, the same of the first test is used.
     */
    public void testCreateBracketEdgeStyleWithSecondValues() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createBracketEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        EdgeStyle edgeStyle = styleHelper.createEdgeStyle(edgeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);
    }

    /**
     * Test the creation of a FLAT container style from a container description
     * style with the first possible value of each property.
     */
    public void testCreateContainerStyle_FlatWithFirstValues() {
        FlatContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the creation of a FLAT container style from a container description
     * style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateContainerStyleGeneric_FlatWithFirstValues() {
        FlatContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(containerStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof ContainerStyle);
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch((ContainerStyle) style);
    }

    /**
     * Test the creation of a FLAT container style from a container description
     * style with the second possible value of each property. If one property
     * can't have a second possible value, the same of the first test is used.
     */
    public void testCreateContainerStyle_FlatWithSecondValues() {
        FlatContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the creation of a SHAPE container style from a container description
     * style with the first possible value of each property.
     */
    public void testCreateContainerStyle_ShapeWithFirstValues() {
        ShapeContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the creation of a SHAPE container style from a container description
     * style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateContainerGenericStyle_ShapeWithFirstValues() {
        ShapeContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(containerStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof ContainerStyle);
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch((ContainerStyle) style);
    }

    /**
     * Test the creation of a SHAPE container style from a container description
     * style with the second possible value of each property. If one property
     * can't have a second possible value, the same of the first test is used.
     */
    public void testCreateContainerStyle_ShapeWithSecondValues() {
        ShapeContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the creation of a WORKSAPCE_IMAGE container style from a container
     * description style with the first possible value of each property.
     */
    public void testCreateContainerStyle_WorkspaceImageWithFirstValues() {
        WorkspaceImageDescription containerStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the creation of a WORKSAPCE_IMAGE container style from a container
     * description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateContainerStyleGeneric_WorkspaceImageWithFirstValues() {
        WorkspaceImageDescription containerStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(containerStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof ContainerStyle);
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch((ContainerStyle) style);
    }

    /**
     * Test the creation of a WORKSAPCE_IMAGE container style from a container
     * description style with the second possible value of each property. If one
     * property can't have a second possible value, the same of the first test
     * is used.
     */
    public void testCreateContainerStyle_WorkspaceImageWithSecondValues() {
        WorkspaceImageDescription containerStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the creation of a BundledImageDescription node style from a node
     * description style with the first possible value of each property.
     */
    public void testCreateNodeStyle_BundledImageDescriptionWithFirstValues() {
        BundledImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createBundledImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a BundledImageDescription node style from a node
     * description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_BundledImageDescriptionWithFirstValues() {
        BundledImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createBundledImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a BundledImageDescription node style from a node
     * description style with the second possible value of each property. If one
     * property can't have a second possible value, the same of the first test
     * is used.
     */
    public void testCreateNodeStyle_BundledImageDescriptionWithSecondValues() {
        BundledImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createBundledImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a BundledImageDescription node style from a node
     * description style with the first possible value of each property.
     */
    public void testCreateNodeStyle_CustomStyleDescriptionWithFirstValues() {
        CustomStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createCustomStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a CustomStyleDescription node style from a node
     * description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_CustomStyleDescriptionWithFirstValues() {
        CustomStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createCustomStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a CustomStyleDescription node style from a node
     * description style with the second possible value of each property. If one
     * property can't have a second possible value, the same of the first test
     * is used.
     */
    public void testCreateNodeStyle_CustomStyleDescriptionWithSecondValues() {
        CustomStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createCustomStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a DotDescription node style from a node description
     * style with the first possible value of each property.
     */
    public void testCreateNodeStyle_DotDescriptionWithFirstValues() {
        DotDescription nodeStyleDesc = StyleFactory.eINSTANCE.createDotDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a DotDescription node style from a node description
     * style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_DotDescriptionWithFirstValues() {
        DotDescription nodeStyleDesc = StyleFactory.eINSTANCE.createDotDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a DotDescription node style from a node description
     * style with the second possible value of each property. If one property
     * can't have a second possible value, the same of the first test is used.
     */
    public void testCreateNodeStyle_DotDescriptionWithSecondValues() {
        DotDescription nodeStyleDesc = StyleFactory.eINSTANCE.createDotDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a EllipseNodeDescription node style from a node
     * description style with the first possible value of each property.
     */
    public void testCreateNodeStyle_EllipseNodeDescriptionWithFirstValues() {
        EllipseNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createEllipseNodeDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a EllipseNodeDescription node style from a node
     * description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_EllipseNodeDescriptionWithFirstValues() {
        EllipseNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createEllipseNodeDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a EllipseNodeDescription node style from a node
     * description style with the second possible value of each property. If one
     * property can't have a second possible value, the same of the first test
     * is used.
     */
    public void testCreateNodeStyle_EllipseNodeDescriptionWithSecondValues() {
        EllipseNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createEllipseNodeDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a GaugeCompositeStyleDescription node style from a
     * node description style with the first possible value of each property.
     */
    public void testCreateNodeStyle_GaugeCompositeStyleDescriptionWithFirstValues() {
        GaugeCompositeStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a GaugeCompositeStyleDescription node style from a
     * node description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_GaugeCompositeStyleDescriptionWithFirstValues() {
        GaugeCompositeStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a GaugeCompositeStyleDescription node style from a
     * node description style with the second possible value of each property.
     * If one property can't have a second possible value, the same of the first
     * test is used.
     */
    public void testCreateNodeStyle_GaugeCompositeStyleDescriptionWithSecondValues() {
        GaugeCompositeStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a LozengeNodeDescription node style from a node
     * description style with the first possible value of each property.
     */
    public void testCreateNodeStyle_LozengeNodeDescriptionWithFirstValues() {
        LozengeNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createLozengeNodeDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a LozengeNodeDescription node style from a node
     * description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_LozengeNodeDescriptionWithFirstValues() {
        LozengeNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createLozengeNodeDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a LozengeNodeDescription node style from a node
     * description style with the second possible value of each property. If one
     * property can't have a second possible value, the same of the first test
     * is used.
     */
    public void testCreateNodeStyle_LozengeNodeDescriptionWithSecondValues() {
        LozengeNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createLozengeNodeDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a NoteDescription node style from a node description
     * style with the first possible value of each property.
     */
    public void testCreateNodeStyle_NoteDescriptionWithFirstValues() {
        NoteDescription nodeStyleDesc = StyleFactory.eINSTANCE.createNoteDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a NoteDescription node style from a node description
     * style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_NoteDescriptionWithFirstValues() {
        NoteDescription nodeStyleDesc = StyleFactory.eINSTANCE.createNoteDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a NoteDescription node style from a node description
     * style with the second possible value of each property. If one property
     * can't have a second possible value, the same of the first test is used.
     */
    public void testCreateNodeStyle_NoteDescriptionWithSecondValues() {
        NoteDescription nodeStyleDesc = StyleFactory.eINSTANCE.createNoteDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a SquareDescription node style from a node
     * description style with the first possible value of each property.
     */
    public void testCreateNodeStyle_SquareDescriptionWithFirstValues() {
        SquareDescription nodeStyleDesc = StyleFactory.eINSTANCE.createSquareDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a SquareDescription node style from a node
     * description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_SquareDescriptionWithFirstValues() {
        SquareDescription nodeStyleDesc = StyleFactory.eINSTANCE.createSquareDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a SquareDescription node style from a node
     * description style with the second possible value of each property. If one
     * property can't have a second possible value, the same of the first test
     * is used.
     */
    public void testCreateNodeStyle_SquareDescriptionWithSecondValues() {
        SquareDescription nodeStyleDesc = StyleFactory.eINSTANCE.createSquareDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a WorkspaceImageDescription node style from a node
     * description style with the first possible value of each property.
     */
    public void testCreateNodeStyle_WorkspaceImageDescriptionWithFirstValues() {
        WorkspaceImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the creation of a WorkspaceImageDescription node style from a node
     * description style with the first possible value of each property.<BR>
     * This test is made with the method which return a generic type.
     */
    public void testCreateNodeStyleGeneric_WorkspaceImageDescriptionWithFirstValues() {
        WorkspaceImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        Style style = styleHelper.createStyle(nodeStyleDesc);
        // Check the style
        assertTrue("The style is not of expected type.", style instanceof NodeStyle);
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch((NodeStyle) style);
    }

    /**
     * Test the creation of a WorkspaceImageDescription node style from a node
     * description style with the second possible value of each property. If one
     * property can't have a second possible value, the same of the first test
     * is used.
     */
    public void testCreateNodeStyle_WorkspaceImageDescriptionWithSecondValues() {
        WorkspaceImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Check the style
        new CheckStyleCreationForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleContainerStyle_Flat() {
        FlatContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Change the color of the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(containerStyleDesc);
        // Refresh the style
        styleHelper.refreshStyle(containerStyle);
        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleContainerStyle_Shape() {
        ShapeContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Change the color of the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(containerStyleDesc);
        // Refresh the style
        styleHelper.refreshStyle(containerStyle);
        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleContainerStyle_WorkspaceImage() {
        WorkspaceImageDescription containerStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Change the color of the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(containerStyleDesc);
        // Refresh the style
        styleHelper.refreshStyle(containerStyle);
        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleEdgeStyle() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        EdgeStyle edgeStyle = styleHelper.createEdgeStyle(edgeStyleDesc);

        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);

        // Change the color of the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(edgeStyleDesc);
        // Refresh the style
        styleHelper.refreshStyle(edgeStyle);
        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);
    }

    /**
     * Test the that when we add a BeginLabelStyleDescription &
     * EndLabelStyleDescription to a EdgeStyleDescription, the StyleHelper
     * create the corresponding BeginLabelStyle & EndLabelStyle.
     * 
     * see VP-2048.
     */
    public void testRefreshStyleEdgeStyleForLabels() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);

        // Create the style from the style description
        EdgeStyle edgeStyle = styleHelper.createEdgeStyle(edgeStyleDesc);

        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);

        // Update the EdgeStyleDescription with the label style for begin & end
        // style.
        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // ReInitialize the style description with the new begin & end label
        // styles
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);

        // Refresh the style
        styleHelper.refreshStyle(edgeStyle);
        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_BundledImageDescription() {
        BundledImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createBundledImageDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_CustomStyleDescription() {
        CustomStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createCustomStyleDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_DotDescription() {
        DotDescription nodeStyleDesc = StyleFactory.eINSTANCE.createDotDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_EllipseNodeDescription() {
        EllipseNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createEllipseNodeDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_GaugeCompositeStyleDescription() {
        GaugeCompositeStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_LozengeNodeDescription() {
        LozengeNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createLozengeNodeDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_NoteDescription() {
        NoteDescription nodeStyleDesc = StyleFactory.eINSTANCE.createNoteDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_SquareDescription() {
        SquareDescription nodeStyleDesc = StyleFactory.eINSTANCE.createSquareDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleNodeStyle_WorkspaceImageDescription() {
        WorkspaceImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        testRefreshStyleNodeStyle(nodeStyleDesc);
    }

    private void testRefreshStyleNodeStyle(NodeStyleDescription nodeStyleDesc) {
        // Initialize the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Change the color of the style description
        new InitializeStyleDescriptionWithSecondValuesSwitch().doSwitch(nodeStyleDesc);
        // Refresh the style
        styleHelper.refreshStyle(nodeStyle);
        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for Square style.
     */
    public void testRefreshStyleNodeStyle_SquareDescription_WithCustomFeatures() {
        SquareDescription nodeStyleDesc = StyleFactory.eINSTANCE.createSquareDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for WorkspaceImage
     * style.
     */
    public void testRefreshStyleNodeStyle_WorkspaceImageDescription_WithCustomFeatures() {
        WorkspaceImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for Note style.
     */
    public void testRefreshStyleNodeStyle_NoteDescription_WithCustomFeatures() {
        NoteDescription nodeStyleDesc = StyleFactory.eINSTANCE.createNoteDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for GaugeComposite
     * style.
     */
    public void testRefreshStyleNodeStyle_GaugeCompositeStyleDescription_WithCustomFeatures() {
        GaugeCompositeStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createGaugeCompositeStyleDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for Ellipse style.
     */
    public void testRefreshStyleNodeStyle_EllipseNodeDescription_WithCustomFeatures() {
        EllipseNodeDescription nodeStyleDesc = StyleFactory.eINSTANCE.createEllipseNodeDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for Dot style.
     */
    public void testRefreshStyleNodeStyle_DotDescription_WithCustomFeatures() {
        DotDescription nodeStyleDesc = StyleFactory.eINSTANCE.createDotDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for CustomStyle
     * style.
     */
    public void testRefreshStyleNodeStyle_CustomStyleDescription_WithCustomFeatures() {
        CustomStyleDescription nodeStyleDesc = StyleFactory.eINSTANCE.createCustomStyleDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for BundleImage
     * style.
     */
    public void testRefreshStyleNodeStyle_BundleImageDescription_WithCustomFeatures() {
        BundledImageDescription nodeStyleDesc = StyleFactory.eINSTANCE.createBundledImageDescription();
        testRefreshStyleNodeStyle_WithCustomFeatures(nodeStyleDesc);
    }

    /**
     * Test the refreshStyle method.
     */
    public void testRefreshStyleEdgeStyle_WithCustomFeatures() {
        EdgeStyleDescription edgeStyleDesc = StyleFactory.eINSTANCE.createEdgeStyleDescription();

        edgeStyleDesc.setBeginLabelStyleDescription(StyleFactory.eINSTANCE.createBeginLabelStyleDescription());
        edgeStyleDesc.setCenterLabelStyleDescription(StyleFactory.eINSTANCE.createCenterLabelStyleDescription());
        edgeStyleDesc.setEndLabelStyleDescription(StyleFactory.eINSTANCE.createEndLabelStyleDescription());

        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(edgeStyleDesc);
        // Create the style from the style description
        EdgeStyle edgeStyle = styleHelper.createEdgeStyle(edgeStyleDesc);

        // Check the style
        new CheckStyleRefreshForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);

        // Modify the style and put custom feature in it
        for (String featureName : new CustomizableQuery(edgeStyle).getCustomizableFeatureNames()) {
            edgeStyle.getCustomFeatures().add(featureName);
            // Doesn't considers GMF features here because we test only
            // StyleHelper
            if (edgeStyle.eClass().getEStructuralFeature(featureName) != null) {
                new ModifyStyleWithCustomColorsSwitch(edgeStyleDesc).doSwitch(edgeStyle);
                // Refresh the style
                styleHelper.refreshStyle(edgeStyle);
                // Check the style
                new CheckStyleRefreshWithCustomForStyleDescSwitch(edgeStyleDesc).doSwitch(edgeStyle);
            }
        }
    }

    private void testRefreshStyleNodeStyle_WithCustomFeatures(NodeStyleDescription nodeStyleDesc) {
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(nodeStyleDesc);
        // Create the style from the style description
        NodeStyle nodeStyle = styleHelper.createNodeStyle(nodeStyleDesc);
        // Refresh the style
        styleHelper.refreshStyle(nodeStyle);
        // Modify the style and put custom feature in it
        for (String featureName : new CustomizableQuery(nodeStyle).getCustomizableFeatureNames()) {
            nodeStyle.getCustomFeatures().add(featureName);
            // Doesn't considers GMF features here because we test only
            // StyleHelper
            if (nodeStyle.eClass().getEStructuralFeature(featureName) != null) {
                new ModifyStyleWithCustomColorsSwitch(nodeStyleDesc).doSwitch(nodeStyle);
                // Refresh the style
                styleHelper.refreshStyle(nodeStyle);
                // Check the style
                new CheckStyleRefreshWithCustomForStyleDescSwitch(nodeStyleDesc).doSwitch(nodeStyle);
            }
        }
    }

    /**
     * Test the refreshStyle method with customizedFeatures for WorkspaceImage
     * style.
     */
    public void testRefreshStyleContainerStyle_WorkspaceImageDescription_WithCustomFeatures() {
        WorkspaceImageDescription containerStyleDesc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        testRefreshStyleContainerStyle_WithCustomFeatures(containerStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for ShapeContainer
     * style.
     */
    public void testRefreshStyleContainerStyle_ShapeContainerStyleDescription_WithCustomFeatures() {
        ShapeContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        testRefreshStyleContainerStyle_WithCustomFeatures(containerStyleDesc);
    }

    /**
     * Test the refreshStyle method with customizedFeatures for FlatContainer
     * style.
     */
    public void testRefreshStyleContainerStyle_FlatContainerStyleDescription_WithCustomFeatures() {
        FlatContainerStyleDescription containerStyleDesc = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        testRefreshStyleContainerStyle_WithCustomFeatures(containerStyleDesc);
    }

    private void testRefreshStyleContainerStyle_WithCustomFeatures(ContainerStyleDescription containerStyleDesc) {
        // Initialize the style description
        new InitializeStyleDescriptionWithFirstValuesSwitch().doSwitch(containerStyleDesc);
        // Create the style from the style description
        ContainerStyle containerStyle = styleHelper.createContainerStyle(containerStyleDesc);
        // Refresh the style
        styleHelper.refreshStyle(containerStyle);
        // Modify the style and put custom feature in it
        for (String featureName : new CustomizableQuery(containerStyle).getCustomizableFeatureNames()) {
            containerStyle.getCustomFeatures().add(featureName);
            // Doesn't considers GMF features here because we test only
            // StyleHelper
            if (containerStyle.eClass().getEStructuralFeature(featureName) != null) {
                new ModifyStyleWithCustomColorsSwitch(containerStyleDesc).doSwitch(containerStyle);
                // Refresh the style
                styleHelper.refreshStyle(containerStyle);
                // Check the style
                new CheckStyleRefreshWithCustomForStyleDescSwitch(containerStyleDesc).doSwitch(containerStyle);
            }
        }
    }

    /**
     * return true if both values are equals (same RGB color values).
     * 
     * @param message
     *            the error message
     * @param expectedValue
     *            the first value.
     * @param newValues
     *            the second value.
     */
    private static void assertSameRGB(final String message, final ColorDescription expectedValue, final RGBValues newValues) {
        if (expectedValue instanceof FixedColor) {
            if (expectedValue != null && newValues != null && ((FixedColor) expectedValue).getBlue() == newValues.getBlue() && ((FixedColor) expectedValue).getRed() == newValues.getRed()
                    && ((FixedColor) expectedValue).getGreen() == newValues.getGreen())
                return;
            failNotEquals(message, expectedValue, newValues);
        } else {
            assertEquals(message, expectedValue, newValues);
        }
    }

    /**
     * Switch to initialize a style description with the first possible value of
     * each property. Initialize the style description with the second possible
     * value of each property. If one property can't have a second possible
     * value, the same of the first test is used.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     * 
     */
    private static final class InitializeStyleDescriptionWithFirstValuesSwitch extends StyleSwitch<Object> {
        /**
         * Default constructor.
         * 
         */
        private InitializeStyleDescriptionWithFirstValuesSwitch() {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseBundledImageDescription(org.eclipse.sirius.diagram.description.style.BundledImageDescription)
         */
        @Override
        public Object caseBundledImageDescription(BundledImageDescription object) {
            object.setColor(COLOR_FIRST);
            object.setShape(BundledImageShape.DOT_LITERAL);
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseBundledImageDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseCustomStyleDescription(org.eclipse.sirius.diagram.description.style.CustomStyleDescription)
         */
        @Override
        public Object caseCustomStyleDescription(CustomStyleDescription object) {
            object.setId("id");
            return super.caseCustomStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseDotDescription(org.eclipse.sirius.diagram.description.style.DotDescription)
         */
        @Override
        public Object caseDotDescription(DotDescription object) {
            object.setBackgroundColor(BACKGROUND_COLOR_FIRST);
            object.setStrokeSizeComputationExpression("4");
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseDotDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseEllipseNodeDescription(org.eclipse.sirius.diagram.description.style.EllipseNodeDescription)
         */
        @Override
        public Object caseEllipseNodeDescription(EllipseNodeDescription object) {
            object.setColor(COLOR_FIRST);
            object.setHorizontalDiameterComputationExpression("5");
            object.setVerticalDiameterComputationExpression("6");
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseEllipseNodeDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseGaugeCompositeStyleDescription(org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription)
         */
        @Override
        public Object caseGaugeCompositeStyleDescription(GaugeCompositeStyleDescription object) {
            object.setAlignment(AlignmentKind.HORIZONTAL_LITERAL);
            object.getSections().clear();
            GaugeSectionDescription gaugeSectionDescription = StyleFactory.eINSTANCE.createGaugeSectionDescription();
            gaugeSectionDescription.setBackgroundColor(BACKGROUND_COLOR_FIRST);
            gaugeSectionDescription.setForegroundColor(FOREGROUND_COLOR_FIRST);
            gaugeSectionDescription.setLabel("GaugeSectionLabel1");
            gaugeSectionDescription.setMinValueExpression("0");
            gaugeSectionDescription.setMaxValueExpression("10");
            gaugeSectionDescription.setValueExpression("5");
            object.getSections().add(gaugeSectionDescription);
            gaugeSectionDescription = StyleFactory.eINSTANCE.createGaugeSectionDescription();
            gaugeSectionDescription.setBackgroundColor(BACKGROUND_COLOR_FIRST);
            gaugeSectionDescription.setForegroundColor(FOREGROUND_COLOR_FIRST);
            gaugeSectionDescription.setLabel("GaugeSectionLabel2");
            gaugeSectionDescription.setMinValueExpression("10");
            gaugeSectionDescription.setMaxValueExpression("20");
            gaugeSectionDescription.setValueExpression("15");
            object.getSections().add(gaugeSectionDescription);
            return super.caseGaugeCompositeStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseLozengeNodeDescription(org.eclipse.sirius.diagram.description.style.LozengeNodeDescription)
         */
        @Override
        public Object caseLozengeNodeDescription(LozengeNodeDescription object) {
            object.setColor(COLOR_FIRST);
            object.setHeightComputationExpression("5");
            object.setWidthComputationExpression("6");
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseLozengeNodeDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseNoteDescription(org.eclipse.sirius.diagram.description.style.NoteDescription)
         */
        @Override
        public Object caseNoteDescription(NoteDescription object) {
            object.setColor(COLOR_FIRST);
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseNoteDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseSquareDescription(org.eclipse.sirius.diagram.description.style.SquareDescription)
         */
        @Override
        public Object caseSquareDescription(SquareDescription object) {
            object.setColor(COLOR_FIRST);
            object.setHeight(5);
            object.setWidth(6);
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseSquareDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseRoundedCornerStyleDescription(org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription)
         */
        @Override
        public Object caseRoundedCornerStyleDescription(RoundedCornerStyleDescription object) {
            object.setArcHeight(5);
            object.setArcWidth(6);
            return super.caseRoundedCornerStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseBorderedStyleDescription(org.eclipse.sirius.diagram.description.style.BorderedStyleDescription)
         */
        @Override
        public Object caseBorderedStyleDescription(BorderedStyleDescription object) {
            object.setBorderColor(BORDER_COLOR_FIRST);
            object.setBorderSizeComputationExpression("7");
            return super.caseBorderedStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseLabelStyleDescription(org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription)
         */
        @Override
        public Object caseLabelStyleDescription(LabelStyleDescription object) {
            object.setLabelAlignment(LabelAlignment.CENTER);
            object.setLabelExpression("LabelExpression");
            FontFormatHelper.setFontFormat(object.getLabelFormat(), FontFormat.BOLD_LITERAL);
            object.setLabelSize(6);
            object.setLabelColor(LABEL_COLOR_FIRST);
            object.setShowIcon(true);
            object.setIconPath(IMG_WORKSPACE_PATH);
            return super.caseLabelStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseContainerStyleDescription(org.eclipse.sirius.diagram.description.style.ContainerStyleDescription)
         */
        @Override
        public Object caseContainerStyleDescription(ContainerStyleDescription object) {
            object.setRoundedCorner(true);
            object.setLabelColor(LABEL_COLOR_FIRST);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseTooltipStyleDescription(org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription)
         */
        @Override
        public Object caseTooltipStyleDescription(TooltipStyleDescription object) {
            object.setTooltipExpression("TooltipExpression");
            return super.caseTooltipStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseFlatContainerStyleDescription(org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription)
         */
        @Override
        public Object caseFlatContainerStyleDescription(FlatContainerStyleDescription object) {
            object.setBackgroundColor(BACKGROUND_COLOR_FIRST);
            object.setBackgroundStyle(BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL);
            object.setForegroundColor(FOREGROUND_COLOR_FIRST);
            return super.caseFlatContainerStyleDescription(object);

        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseShapeContainerStyleDescription(org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription)
         */
        @Override
        public Object caseShapeContainerStyleDescription(ShapeContainerStyleDescription object) {
            object.setBackgroundColor(BACKGROUND_COLOR_FIRST);
            object.setShape(ContainerShape.PARALLELOGRAM_LITERAL);
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseShapeContainerStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseNodeStyleDescription(org.eclipse.sirius.diagram.description.style.NodeStyleDescription)
         */
        @Override
        public Object caseNodeStyleDescription(NodeStyleDescription object) {
            object.setLabelPosition(LabelPosition.BORDER_LITERAL);
            object.setResizeKind(ResizeKind.EAST_WEST_LITERAL);
            object.setSizeComputationExpression("2");
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseNodeStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseWorkspaceImageDescription(org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription)
         */
        @Override
        public Object caseWorkspaceImageDescription(WorkspaceImageDescription object) {
            object.setWorkspacePath(IMG_WORKSPACE_PATH);
            object.setLabelColor(LABEL_COLOR_FIRST);
            return super.caseWorkspaceImageDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseEdgeStyleDescription(org.eclipse.sirius.diagram.description.style.EdgeStyleDescription)
         */
        @Override
        public Object caseEdgeStyleDescription(EdgeStyleDescription object) {
            object.setFoldingStyle(FoldingStyle.NONE_LITERAL);
            object.setLineStyle(LineStyle.DASH_DOT_LITERAL);
            object.setRoutingStyle(EdgeRouting.MANHATTAN_LITERAL);
            object.setSizeComputationExpression("5");
            object.setSourceArrow(EdgeArrows.DIAMOND_LITERAL);
            object.setStrokeColor(STROKE_COLOR_FIRST);
            if (object.getBeginLabelStyleDescription() != null) {
                object.getBeginLabelStyleDescription().setLabelColor(LABEL_COLOR_FIRST);
                object.getBeginLabelStyleDescription().setLabelExpression("begin first LabelExpression");
                FontFormatHelper.setFontFormat(object.getBeginLabelStyleDescription().getLabelFormat(), FontFormat.BOLD_LITERAL);
                object.getBeginLabelStyleDescription().setLabelSize(5);
            }
            if (object.getCenterLabelStyleDescription() != null) {
                object.getCenterLabelStyleDescription().setLabelColor(LABEL_COLOR_FIRST);
                object.getCenterLabelStyleDescription().setLabelExpression("center first LabelExpression");
                FontFormatHelper.setFontFormat(object.getCenterLabelStyleDescription().getLabelFormat(), FontFormat.BOLD_LITERAL);
                object.getCenterLabelStyleDescription().setLabelSize(5);
            }
            if (object.getEndLabelStyleDescription() != null) {
                object.getEndLabelStyleDescription().setLabelColor(LABEL_COLOR_FIRST);
                object.getEndLabelStyleDescription().setLabelExpression("end first LabelExpression");
                FontFormatHelper.setFontFormat(object.getEndLabelStyleDescription().getLabelFormat(), FontFormat.BOLD_LITERAL);
                object.getEndLabelStyleDescription().setLabelSize(5);
            }
            object.setTargetArrow(EdgeArrows.DIAMOND_LITERAL);
            return super.caseEdgeStyleDescription(object);
        }
    }

    /**
     * Switch to initialize a style description with the second possible value
     * of each property. If one property can't have a second possible value, the
     * first one is used.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static final class InitializeStyleDescriptionWithSecondValuesSwitch extends StyleSwitch<Object> {
        /**
         * Default constructor.
         * 
         */
        private InitializeStyleDescriptionWithSecondValuesSwitch() {
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseBundledImageDescription(org.eclipse.sirius.diagram.description.style.BundledImageDescription)
         */
        @Override
        public Object caseBundledImageDescription(BundledImageDescription object) {
            object.setColor(COLOR_SECOND);
            object.setLabelColor(LABEL_COLOR_SECOND);
            object.setShape(BundledImageShape.RING_LITERAL);
            return super.caseBundledImageDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseCustomStyleDescription(org.eclipse.sirius.diagram.description.style.CustomStyleDescription)
         */
        @Override
        public Object caseCustomStyleDescription(CustomStyleDescription object) {
            object.setId("id");
            return super.caseCustomStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseDotDescription(org.eclipse.sirius.diagram.description.style.DotDescription)
         */
        @Override
        public Object caseDotDescription(DotDescription object) {
            object.setBackgroundColor(BACKGROUND_COLOR_SECOND);
            object.setLabelColor(LABEL_COLOR_SECOND);
            object.setStrokeSizeComputationExpression("4");
            return super.caseDotDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseEllipseNodeDescription(org.eclipse.sirius.diagram.description.style.EllipseNodeDescription)
         */
        @Override
        public Object caseEllipseNodeDescription(EllipseNodeDescription object) {
            object.setColor(COLOR_SECOND);
            object.setLabelColor(LABEL_COLOR_SECOND);
            object.setHorizontalDiameterComputationExpression("5");
            object.setVerticalDiameterComputationExpression("6");
            return super.caseEllipseNodeDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseGaugeCompositeStyleDescription(org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription)
         */
        @Override
        public Object caseGaugeCompositeStyleDescription(GaugeCompositeStyleDescription object) {
            object.setAlignment(AlignmentKind.SQUARE_LITERAL);
            object.getSections().clear();
            GaugeSectionDescription gaugeSectionDescription = StyleFactory.eINSTANCE.createGaugeSectionDescription();
            gaugeSectionDescription.setBackgroundColor(BACKGROUND_COLOR_SECOND);
            gaugeSectionDescription.setForegroundColor(FOREGROUND_COLOR_SECOND);
            gaugeSectionDescription.setLabel("GaugeSectionLabel3");
            gaugeSectionDescription.setMinValueExpression("20");
            gaugeSectionDescription.setMaxValueExpression("30");
            gaugeSectionDescription.setValueExpression("25");
            object.getSections().add(gaugeSectionDescription);
            gaugeSectionDescription = StyleFactory.eINSTANCE.createGaugeSectionDescription();
            gaugeSectionDescription.setBackgroundColor(BACKGROUND_COLOR_SECOND);
            gaugeSectionDescription.setForegroundColor(FOREGROUND_COLOR_SECOND);
            gaugeSectionDescription.setLabel("GaugeSectionLabel4");
            gaugeSectionDescription.setMinValueExpression("30");
            gaugeSectionDescription.setMaxValueExpression("40");
            gaugeSectionDescription.setValueExpression("35");
            object.getSections().add(gaugeSectionDescription);
            gaugeSectionDescription = StyleFactory.eINSTANCE.createGaugeSectionDescription();
            gaugeSectionDescription.setBackgroundColor(BACKGROUND_COLOR_SECOND);
            gaugeSectionDescription.setForegroundColor(FOREGROUND_COLOR_SECOND);
            gaugeSectionDescription.setLabel("GaugeSectionLabel5");
            gaugeSectionDescription.setMinValueExpression("40");
            gaugeSectionDescription.setMaxValueExpression("50");
            gaugeSectionDescription.setValueExpression("45");
            object.getSections().add(gaugeSectionDescription);
            return super.caseGaugeCompositeStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseLozengeNodeDescription(org.eclipse.sirius.diagram.description.style.LozengeNodeDescription)
         */
        @Override
        public Object caseLozengeNodeDescription(LozengeNodeDescription object) {
            object.setColor(COLOR_SECOND);
            object.setLabelColor(LABEL_COLOR_SECOND);
            object.setHeightComputationExpression("5");
            object.setWidthComputationExpression("6");
            return super.caseLozengeNodeDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseNoteDescription(org.eclipse.sirius.diagram.description.style.NoteDescription)
         */
        @Override
        public Object caseNoteDescription(NoteDescription object) {
            object.setColor(COLOR_SECOND);
            object.setLabelColor(LABEL_COLOR_SECOND);
            return super.caseNoteDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseSquareDescription(org.eclipse.sirius.diagram.description.style.SquareDescription)
         */
        @Override
        public Object caseSquareDescription(SquareDescription object) {
            object.setColor(COLOR_SECOND);
            object.setHeight(7);
            object.setWidth(8);
            object.setLabelColor(LABEL_COLOR_SECOND);
            return super.caseSquareDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseRoundedCornerStyleDescription(org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription)
         */
        @Override
        public Object caseRoundedCornerStyleDescription(RoundedCornerStyleDescription object) {
            object.setArcHeight(5);
            object.setArcWidth(6);
            return super.caseRoundedCornerStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseBorderedStyleDescription(org.eclipse.sirius.diagram.description.style.BorderedStyleDescription)
         */
        @Override
        public Object caseBorderedStyleDescription(BorderedStyleDescription object) {
            object.setBorderColor(BORDER_COLOR_SECOND);
            object.setBorderSizeComputationExpression("7");
            return super.caseBorderedStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseLabelStyleDescription(org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription)
         */
        @Override
        public Object caseLabelStyleDescription(LabelStyleDescription object) {
            object.setLabelAlignment(LabelAlignment.LEFT);
            object.setLabelExpression("LabelExpression");
            FontFormatHelper.setFontFormat(object.getLabelFormat(), FontFormat.ITALIC_LITERAL);
            object.setLabelColor(LABEL_COLOR_SECOND);
            object.setLabelSize(6);
            object.setShowIcon(false);
            object.setIconPath(null);
            return super.caseLabelStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseContainerStyleDescription(org.eclipse.sirius.diagram.description.style.ContainerStyleDescription)
         */
        @Override
        public Object caseContainerStyleDescription(ContainerStyleDescription object) {
            object.setRoundedCorner(false);
            object.setLabelColor(LABEL_COLOR_SECOND);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseTooltipStyleDescription(org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription)
         */
        @Override
        public Object caseTooltipStyleDescription(TooltipStyleDescription object) {
            object.setTooltipExpression("TooltipExpression");
            return super.caseTooltipStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseFlatContainerStyleDescription(org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription)
         */
        @Override
        public Object caseFlatContainerStyleDescription(FlatContainerStyleDescription object) {
            object.setBackgroundColor(BACKGROUND_COLOR_SECOND);
            object.setBackgroundStyle(BackgroundStyle.GRADIENT_TOP_TO_BOTTOM_LITERAL);
            object.setForegroundColor(FOREGROUND_COLOR_SECOND);
            object.setLabelColor(LABEL_COLOR_SECOND);
            return super.caseFlatContainerStyleDescription(object);

        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseShapeContainerStyleDescription(org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription)
         */
        @Override
        public Object caseShapeContainerStyleDescription(ShapeContainerStyleDescription object) {
            object.setBackgroundColor(BACKGROUND_COLOR_SECOND);
            object.setShape(ContainerShape.PARALLELOGRAM_LITERAL);
            object.setLabelColor(LABEL_COLOR_SECOND);
            return super.caseShapeContainerStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseNodeStyleDescription(org.eclipse.sirius.diagram.description.style.NodeStyleDescription)
         */
        @Override
        public Object caseNodeStyleDescription(NodeStyleDescription object) {
            object.setLabelPosition(LabelPosition.NODE_LITERAL);
            object.setLabelColor(LABEL_COLOR_SECOND);
            object.setResizeKind(ResizeKind.NONE_LITERAL);
            object.setSizeComputationExpression("2");
            return super.caseNodeStyleDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseWorkspaceImageDescription(org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription)
         */
        @Override
        public Object caseWorkspaceImageDescription(WorkspaceImageDescription object) {
            object.setWorkspacePath(IMG_WORKSPACE_PATH);
            object.setLabelColor(LABEL_COLOR_SECOND);
            return super.caseWorkspaceImageDescription(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.description.style.util.StyleSwitch#caseEdgeStyleDescription(org.eclipse.sirius.diagram.description.style.EdgeStyleDescription)
         */
        @Override
        public Object caseEdgeStyleDescription(EdgeStyleDescription object) {
            object.setFoldingStyle(FoldingStyle.SOURCE_LITERAL);
            object.setLineStyle(LineStyle.DASH_LITERAL);
            object.setRoutingStyle(EdgeRouting.STRAIGHT_LITERAL);
            object.setSizeComputationExpression("5");
            object.setSourceArrow(EdgeArrows.FILL_DIAMOND_LITERAL);
            object.setStrokeColor(STROKE_COLOR_SECOND);
            if (object.getBeginLabelStyleDescription() != null) {
                object.getBeginLabelStyleDescription().setLabelColor(LABEL_COLOR_SECOND);
                object.getBeginLabelStyleDescription().setLabelExpression("begin second LabelExpression");
                FontFormatHelper.setFontFormat(object.getBeginLabelStyleDescription().getLabelFormat(), FontFormat.ITALIC_LITERAL);
                object.getBeginLabelStyleDescription().setLabelSize(6);
            }
            if (object.getCenterLabelStyleDescription() != null) {
                object.getCenterLabelStyleDescription().setLabelColor(LABEL_COLOR_SECOND);
                object.getCenterLabelStyleDescription().setLabelExpression("center second LabelExpression");
                FontFormatHelper.setFontFormat(object.getCenterLabelStyleDescription().getLabelFormat(), FontFormat.ITALIC_LITERAL);
                object.getCenterLabelStyleDescription().setLabelSize(6);
            }
            if (object.getEndLabelStyleDescription() != null) {
                object.getEndLabelStyleDescription().setLabelColor(LABEL_COLOR_SECOND);
                object.getEndLabelStyleDescription().setLabelExpression("end second LabelExpression");
                FontFormatHelper.setFontFormat(object.getEndLabelStyleDescription().getLabelFormat(), FontFormat.ITALIC_LITERAL);
                object.getEndLabelStyleDescription().setLabelSize(6);
            }
            object.setTargetArrow(EdgeArrows.FILL_DIAMOND_LITERAL);
            return super.caseEdgeStyleDescription(object);
        }
    }

    /**
     * Switch to modify all colors of a style with custom color.
     * 
     * @author lredor
     */
    private static final class ModifyStyleWithCustomColorsSwitch extends CheckStyleCreationForStyleDescSwitch {
        /**
         * Default constructor.
         * 
         * @param styleDesc
         *            the style description to use for check, should not be
         *            <code>null</code>.
         */
        private ModifyStyleWithCustomColorsSwitch(final StyleDescription styleDesc) {
            super(styleDesc);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseBorderedStyle(org.eclipse.sirius.viewpoint.BorderedStyle)
         */
        @Override
        public Object caseBorderedStyle(BorderedStyle object) {
            Object result = super.caseBorderedStyle(object);
            object.setBorderColor((StyleHelperTest.CUSTOM_COLOR));
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseBundledImage(org.eclipse.sirius.viewpoint.BundledImage)
         */
        @Override
        public Object caseBundledImage(BundledImage object) {
            Object result = super.caseBundledImage(object);
            object.setColor(StyleHelperTest.CUSTOM_COLOR);
            object.setBorderColor(StyleHelperTest.CUSTOM_COLOR);
            object.setLabelColor(StyleHelperTest.CUSTOM_COLOR);
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseDot(org.eclipse.sirius.viewpoint.Dot)
         */
        @Override
        public Object caseDot(Dot object) {
            Object result = super.caseDot(object);
            object.setBackgroundColor(StyleHelperTest.CUSTOM_COLOR);
            object.setLabelColor(StyleHelperTest.CUSTOM_COLOR);
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseEllipse(org.eclipse.sirius.viewpoint.Ellipse)
         */
        @Override
        public Object caseEllipse(Ellipse object) {
            Object result = super.caseEllipse(object);
            object.setColor((StyleHelperTest.CUSTOM_COLOR));
            object.setLabelColor((StyleHelperTest.CUSTOM_COLOR));
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseGaugeCompositeStyle(org.eclipse.sirius.viewpoint.GaugeCompositeStyle)
         */
        @Override
        public Object caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            Object result = super.caseGaugeCompositeStyle(object);
            for (int i = 0; i < object.getSections().size(); i++) {
                GaugeSection section = object.getSections().get(i);
                section.setBackgroundColor((StyleHelperTest.CUSTOM_COLOR));
                object.setLabelColor((StyleHelperTest.CUSTOM_COLOR));
                section.setForegroundColor((StyleHelperTest.CUSTOM_COLOR));
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseLozenge(org.eclipse.sirius.viewpoint.Lozenge)
         */
        @Override
        public Object caseLozenge(Lozenge object) {
            Object result = super.caseLozenge(object);
            object.setColor((StyleHelperTest.CUSTOM_COLOR));
            object.setLabelColor((StyleHelperTest.CUSTOM_COLOR));
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseNote(org.eclipse.sirius.viewpoint.Note)
         */
        @Override
        public Object caseNote(Note object) {
            Object result = super.caseNote(object);
            object.setLabelColor((StyleHelperTest.CUSTOM_COLOR));
            object.setColor((StyleHelperTest.CUSTOM_COLOR));
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseSquare(org.eclipse.sirius.viewpoint.Square)
         */
        @Override
        public Object caseSquare(Square object) {
            Object result = super.caseSquare(object);
            object.setColor((StyleHelperTest.CUSTOM_COLOR));
            object.setLabelColor((StyleHelperTest.CUSTOM_COLOR));
            return result;
        }

        @Override
        public Object caseStyle(Style object) {
            Object result = super.caseStyle(object);
            EStructuralFeature featureToCustomize = DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR;
            object.getCustomFeatures().add(featureToCustomize.getName());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseFlatContainerStyle(org.eclipse.sirius.viewpoint.FlatContainerStyle)
         */
        @Override
        public Object caseFlatContainerStyle(FlatContainerStyle object) {
            Object result = super.caseFlatContainerStyle(object);
            object.setBackgroundColor((StyleHelperTest.CUSTOM_COLOR));
            object.setForegroundColor((StyleHelperTest.CUSTOM_COLOR));
            object.setLabelColor((StyleHelperTest.CUSTOM_COLOR));
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseShapeContainerStyle(org.eclipse.sirius.viewpoint.ShapeContainerStyle)
         */
        @Override
        public Object caseShapeContainerStyle(ShapeContainerStyle object) {
            Object result = super.caseShapeContainerStyle(object);
            object.setBackgroundColor((StyleHelperTest.CUSTOM_COLOR));
            object.setLabelColor((StyleHelperTest.CUSTOM_COLOR));
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseEdgeStyle(org.eclipse.sirius.viewpoint.EdgeStyle)
         */
        @Override
        public Object caseEdgeStyle(EdgeStyle object) {
            Object result = super.caseEdgeStyle(object);
            object.setStrokeColor((StyleHelperTest.CUSTOM_COLOR));
            object.getCenterLabelStyle().setLabelColor((StyleHelperTest.CUSTOM_COLOR));
            object.getCenterLabelStyle().setShowIcon(!object.getCenterLabelStyle().isShowIcon());
            object.getCenterLabelStyle().setIconPath(IMG_WORKSPACE_PATH + IMG_WORKSPACE_PATH);
            return result;
        }
    }

    private static final class CheckStyleRefreshForStyleDescSwitch extends CheckStyleCreationForStyleDescSwitch {
        /**
         * Default constructor.
         * 
         * @param styleDesc
         *            the style description to use for check, should not be
         *            <code>null</code>.
         */
        private CheckStyleRefreshForStyleDescSwitch(final StyleDescription styleDesc) {
            super(styleDesc);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseBorderedStyle(org.eclipse.sirius.viewpoint.BorderedStyle)
         */
        @Override
        public Object caseBorderedStyle(BorderedStyle object) {
            Object result = super.caseBorderedStyle(object);
            if (hasContext(object)) {
                assertEquals("Bad border size", Integer.getInteger(((BorderedStyleDescription) getStyleDesc()).getBorderSizeComputationExpression()), object.getBorderSize());
            }
            StyleHelperTest.assertSameRGB("Bad border color", ((BorderedStyleDescription) getStyleDesc()).getBorderColor(), object.getBorderColor());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseLabelStyle(org.eclipse.sirius.viewpoint.LabelStyle)
         */
        @Override
        public Object caseLabelStyle(LabelStyle object) {
            Object result = super.caseLabelStyle(object);
            StyleHelperTest.assertSameRGB("Bad label color", ((LabelStyleDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            return result;
        }

        /**
         * Check if the style has a context, i.e. the style is contained in a
         * diagram element.<BR>
         * This method allow to check some properties only if the style has
         * context.
         * 
         * @param style
         *            The style to check
         * @return true if the style has context.
         */
        private boolean hasContext(Style style) {
            return style.eContainer() != null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseBundledImage(org.eclipse.sirius.viewpoint.BundledImage)
         */
        @Override
        public Object caseBundledImage(BundledImage object) {
            Object result = super.caseBundledImage(object);
            StyleHelperTest.assertSameRGB("Bad color", ((BundledImageDescription) getStyleDesc()).getColor(), object.getColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((BundledImageDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseDot(org.eclipse.sirius.viewpoint.Dot)
         */
        @Override
        public Object caseDot(Dot object) {
            Object result = super.caseDot(object);
            StyleHelperTest.assertSameRGB("Bad background color", ((DotDescription) getStyleDesc()).getBackgroundColor(), object.getBackgroundColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((DotDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseEllipse(org.eclipse.sirius.viewpoint.Ellipse)
         */
        @Override
        public Object caseEllipse(Ellipse object) {
            Object result = super.caseEllipse(object);
            StyleHelperTest.assertSameRGB("Bad color", ((EllipseNodeDescription) getStyleDesc()).getColor(), object.getColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((EllipseNodeDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            if (hasContext(object)) {
                assertEquals("Bad horizontal diameter", Integer.getInteger(((EllipseNodeDescription) getStyleDesc()).getHorizontalDiameterComputationExpression()), object.getHorizontalDiameter());
                assertEquals("Bad vertical diameter", Integer.getInteger(((EllipseNodeDescription) getStyleDesc()).getVerticalDiameterComputationExpression()), object.getVerticalDiameter());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseGaugeCompositeStyle(org.eclipse.sirius.viewpoint.GaugeCompositeStyle)
         */
        @Override
        public Object caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            Object result = super.caseGaugeCompositeStyle(object);
            for (int i = 0; i < object.getSections().size(); i++) {
                GaugeSection section = object.getSections().get(i);
                if (hasContext(object)) {
                    StyleHelperTest.assertSameRGB("Bad background color", ((GaugeCompositeStyleDescription) getStyleDesc()).getSections().get(i).getBackgroundColor(), section.getBackgroundColor());
                    StyleHelperTest.assertSameRGB("Bad foreground color", ((GaugeCompositeStyleDescription) getStyleDesc()).getSections().get(i).getForegroundColor(), section.getForegroundColor());
                    StyleHelperTest.assertSameRGB("Bad label color", ((GaugeCompositeStyleDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
                }
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseLozenge(org.eclipse.sirius.viewpoint.Lozenge)
         */
        @Override
        public Object caseLozenge(Lozenge object) {
            Object result = super.caseLozenge(object);
            StyleHelperTest.assertSameRGB("Bad color", ((LozengeNodeDescription) getStyleDesc()).getColor(), object.getColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((LozengeNodeDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            if (hasContext(object)) {
                assertEquals("Bad height", ((LozengeNodeDescription) getStyleDesc()).getHeightComputationExpression(), object.getHeight());
                assertEquals("Bad width", ((LozengeNodeDescription) getStyleDesc()).getWidthComputationExpression(), object.getWidth());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseNote(org.eclipse.sirius.viewpoint.Note)
         */
        @Override
        public Object caseNote(Note object) {
            Object result = super.caseNote(object);
            StyleHelperTest.assertSameRGB("Bad color", ((NoteDescription) getStyleDesc()).getColor(), object.getColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((NoteDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseSquare(org.eclipse.sirius.viewpoint.Square)
         */
        @Override
        public Object caseSquare(Square object) {
            Object result = super.caseSquare(object);
            StyleHelperTest.assertSameRGB("Bad color", ((SquareDescription) getStyleDesc()).getColor(), object.getColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((SquareDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseFlatContainerStyle(org.eclipse.sirius.viewpoint.FlatContainerStyle)
         */
        @Override
        public Object caseFlatContainerStyle(FlatContainerStyle object) {
            Object result = super.caseFlatContainerStyle(object);
            StyleHelperTest.assertSameRGB("Bad background color", ((FlatContainerStyleDescription) getStyleDesc()).getBackgroundColor(), object.getBackgroundColor());
            StyleHelperTest.assertSameRGB("Bad foreground color", ((FlatContainerStyleDescription) getStyleDesc()).getForegroundColor(), object.getForegroundColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((FlatContainerStyleDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseShapeContainerStyle(org.eclipse.sirius.viewpoint.ShapeContainerStyle)
         */
        @Override
        public Object caseShapeContainerStyle(ShapeContainerStyle object) {
            Object result = super.caseShapeContainerStyle(object);
            StyleHelperTest.assertSameRGB("Bad background color", ((ShapeContainerStyleDescription) getStyleDesc()).getBackgroundColor(), object.getBackgroundColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((ShapeContainerStyleDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseEdgeStyle(org.eclipse.sirius.viewpoint.EdgeStyle)
         */
        @Override
        public Object caseEdgeStyle(EdgeStyle object) {
            Object result = super.caseEdgeStyle(object);
            EdgeStyleDescription edgeStyleDescription = (EdgeStyleDescription) getStyleDesc();
            StyleHelperTest.assertSameRGB("Bad stroke color", edgeStyleDescription.getStrokeColor(), object.getStrokeColor());

            if (edgeStyleDescription.getBeginLabelStyleDescription() != null) {
                StyleHelperTest.assertNotNull(object.getBeginLabelStyle());
                StyleHelperTest.assertSameRGB("Bad begin label color", edgeStyleDescription.getBeginLabelStyleDescription().getLabelColor(), object.getBeginLabelStyle().getLabelColor());
                StyleHelperTest.assertEquals("Bad begin label size", edgeStyleDescription.getBeginLabelStyleDescription().getLabelSize(), object.getBeginLabelStyle().getLabelSize());
                StyleHelperTest.assertEquals("Bad begin label format", edgeStyleDescription.getBeginLabelStyleDescription().getLabelFormat(), object.getBeginLabelStyle().getLabelFormat());
                StyleHelperTest.assertEquals("Bad begin show icon", edgeStyleDescription.getBeginLabelStyleDescription().isShowIcon(), object.getBeginLabelStyle().isShowIcon());
                StyleHelperTest.assertEquals("Bad begin icon path", edgeStyleDescription.getBeginLabelStyleDescription().getIconPath(), object.getBeginLabelStyle().getIconPath());
            } else {
                StyleHelperTest.assertNull(object.getBeginLabelStyle());
            }
            if (edgeStyleDescription.getCenterLabelStyleDescription() != null) {
                StyleHelperTest.assertNotNull(object.getCenterLabelStyle());
                StyleHelperTest.assertSameRGB("Bad center label color", edgeStyleDescription.getCenterLabelStyleDescription().getLabelColor(), object.getCenterLabelStyle().getLabelColor());
                StyleHelperTest.assertEquals("Bad center label size", edgeStyleDescription.getCenterLabelStyleDescription().getLabelSize(), object.getCenterLabelStyle().getLabelSize());
                StyleHelperTest.assertEquals("Bad center label format", edgeStyleDescription.getCenterLabelStyleDescription().getLabelFormat(), object.getCenterLabelStyle().getLabelFormat());
                StyleHelperTest.assertEquals("Bad center show icon", edgeStyleDescription.getCenterLabelStyleDescription().isShowIcon(), object.getCenterLabelStyle().isShowIcon());
                StyleHelperTest.assertEquals("Bad center icon path", edgeStyleDescription.getCenterLabelStyleDescription().getIconPath(), object.getCenterLabelStyle().getIconPath());
            } else {
                StyleHelperTest.assertNull(object.getCenterLabelStyle());
            }
            if (edgeStyleDescription.getEndLabelStyleDescription() != null) {
                StyleHelperTest.assertNotNull(object.getEndLabelStyle());
                StyleHelperTest.assertSameRGB("Bad end label color", edgeStyleDescription.getEndLabelStyleDescription().getLabelColor(), object.getEndLabelStyle().getLabelColor());
                StyleHelperTest.assertEquals("Bad end label size", edgeStyleDescription.getEndLabelStyleDescription().getLabelSize(), object.getEndLabelStyle().getLabelSize());
                StyleHelperTest.assertEquals("Bad end label format", edgeStyleDescription.getEndLabelStyleDescription().getLabelFormat(), object.getEndLabelStyle().getLabelFormat());
                StyleHelperTest.assertEquals("Bad end show icon", edgeStyleDescription.getEndLabelStyleDescription().isShowIcon(), object.getEndLabelStyle().isShowIcon());
                StyleHelperTest.assertEquals("Bad end icon path", edgeStyleDescription.getEndLabelStyleDescription().getIconPath(), object.getEndLabelStyle().getIconPath());
            } else {
                StyleHelperTest.assertNull(object.getEndLabelStyle());
            }
            return result;
        }
    }

    private static final class CheckStyleRefreshWithCustomForStyleDescSwitch extends CheckStyleCreationForStyleDescSwitch {
        /**
         * Default constructor.
         * 
         * @param styleDesc
         *            the style description to use for check, should not be
         *            <code>null</code>.
         */
        private CheckStyleRefreshWithCustomForStyleDescSwitch(final StyleDescription styleDesc) {
            super(styleDesc);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseBorderedStyle(org.eclipse.sirius.viewpoint.BorderedStyle)
         */
        @Override
        public Object caseBorderedStyle(BorderedStyle object) {
            Object result = super.caseBorderedStyle(object);
            if (hasContext(object)) {
                assertEquals("Bad border size", Integer.getInteger(((BorderedStyleDescription) getStyleDesc()).getBorderSizeComputationExpression()), object.getBorderSize());
            }
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad border color", CUSTOM_COLOR, object.getBorderColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad border color", ((BorderedStyleDescription) getStyleDesc()).getBorderColor(), object.getBorderColor());
            }
            return result;
        }

        /**
         * Check if the style has a context, i.e. the style is contained in a
         * diagram element.<BR>
         * This method allow to check some properties only if the style has
         * context.
         * 
         * @param style
         *            The style to check
         * @return true if the style has context.
         */
        private boolean hasContext(Style style) {
            return style.eContainer() != null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseBundledImage(org.eclipse.sirius.viewpoint.BundledImage)
         */
        @Override
        public Object caseBundledImage(BundledImage object) {
            Object result = super.caseBundledImage(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.BUNDLED_IMAGE__COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad color", (StyleHelperTest.CUSTOM_COLOR), object.getColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad color", ((BundledImageDescription) getStyleDesc()).getColor(), object.getColor());
            }
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad color", (StyleHelperTest.CUSTOM_COLOR), object.getBorderColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad color", ((BundledImageDescription) getStyleDesc()).getBorderColor(), object.getBorderColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((BundledImageDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseDot(org.eclipse.sirius.viewpoint.Dot)
         */
        @Override
        public Object caseDot(Dot object) {
            Object result = super.caseDot(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.DOT__BACKGROUND_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad background color", (StyleHelperTest.CUSTOM_COLOR), object.getBackgroundColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad background color", ((DotDescription) getStyleDesc()).getBackgroundColor(), object.getBackgroundColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((DotDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseEllipse(org.eclipse.sirius.viewpoint.Ellipse)
         */
        @Override
        public Object caseEllipse(Ellipse object) {
            Object result = super.caseEllipse(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.ELLIPSE__COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad color", (StyleHelperTest.CUSTOM_COLOR), object.getColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad color", ((EllipseNodeDescription) getStyleDesc()).getColor(), object.getColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((EllipseNodeDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            if (hasContext(object)) {
                assertEquals("Bad horizontal diameter", Integer.getInteger(((EllipseNodeDescription) getStyleDesc()).getHorizontalDiameterComputationExpression()), object.getHorizontalDiameter());
                assertEquals("Bad vertical diameter", Integer.getInteger(((EllipseNodeDescription) getStyleDesc()).getVerticalDiameterComputationExpression()), object.getVerticalDiameter());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseGaugeCompositeStyle(org.eclipse.sirius.viewpoint.GaugeCompositeStyle)
         */
        @Override
        public Object caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            Object result = super.caseGaugeCompositeStyle(object);
            for (int i = 0; i < object.getSections().size(); i++) {
                GaugeSection section = object.getSections().get(i);
                GaugeSectionDescription gaugeSectionDescription = ((GaugeCompositeStyleDescription) getStyleDesc()).getSections().get(i);
                if (hasContext(object)) {
                    if (section.getCustomFeatures().contains(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR.getName())) {
                        SiriusAssert.assertSameRGB("Bad background color", (StyleHelperTest.CUSTOM_COLOR), section.getBackgroundColor());
                    } else {
                        StyleHelperTest.assertSameRGB("Bad background color", gaugeSectionDescription.getBackgroundColor(), section.getBackgroundColor());
                    }
                    if (section.getCustomFeatures().contains(DiagramPackage.Literals.GAUGE_SECTION__FOREGROUND_COLOR.getName())) {
                        SiriusAssert.assertSameRGB("Bad foreground color", (StyleHelperTest.CUSTOM_COLOR), section.getForegroundColor());
                    } else {
                        StyleHelperTest.assertSameRGB("Bad foreground color", gaugeSectionDescription.getForegroundColor(), section.getForegroundColor());
                    }
                }
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((GaugeCompositeStyleDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseLozenge(org.eclipse.sirius.viewpoint.Lozenge)
         */
        @Override
        public Object caseLozenge(Lozenge object) {
            Object result = super.caseLozenge(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.LOZENGE__COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad color", (StyleHelperTest.CUSTOM_COLOR), object.getColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad color", ((LozengeNodeDescription) getStyleDesc()).getColor(), object.getColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((LozengeNodeDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            if (hasContext(object)) {
                assertEquals("Bad height", ((LozengeNodeDescription) getStyleDesc()).getHeightComputationExpression(), object.getHeight());
                assertEquals("Bad width", ((LozengeNodeDescription) getStyleDesc()).getWidthComputationExpression(), object.getWidth());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseNote(org.eclipse.sirius.viewpoint.Note)
         */
        @Override
        public Object caseNote(Note object) {
            Object result = super.caseNote(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.NOTE__COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad color", (StyleHelperTest.CUSTOM_COLOR), object.getColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad color", ((NoteDescription) getStyleDesc()).getColor(), object.getColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((NoteDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseSquare(org.eclipse.sirius.viewpoint.Square)
         */
        @Override
        public Object caseSquare(Square object) {
            Object result = super.caseSquare(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.SQUARE__COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad color", (StyleHelperTest.CUSTOM_COLOR), object.getColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad color", ((SquareDescription) getStyleDesc()).getColor(), object.getColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((SquareDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseFlatContainerStyle(org.eclipse.sirius.viewpoint.FlatContainerStyle)
         */
        @Override
        public Object caseFlatContainerStyle(FlatContainerStyle object) {
            Object result = super.caseFlatContainerStyle(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad background color", (StyleHelperTest.CUSTOM_COLOR), object.getBackgroundColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad background color", ((FlatContainerStyleDescription) getStyleDesc()).getBackgroundColor(), object.getBackgroundColor());
            }
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad foreground color", (StyleHelperTest.CUSTOM_COLOR), object.getForegroundColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad foreground color", ((FlatContainerStyleDescription) getStyleDesc()).getForegroundColor(), object.getForegroundColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((FlatContainerStyleDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseShapeContainerStyle(org.eclipse.sirius.viewpoint.ShapeContainerStyle)
         */
        @Override
        public Object caseShapeContainerStyle(ShapeContainerStyle object) {
            Object result = super.caseShapeContainerStyle(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad background color", (StyleHelperTest.CUSTOM_COLOR), object.getBackgroundColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad background color", ((ShapeContainerStyleDescription) getStyleDesc()).getBackgroundColor(), object.getBackgroundColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getLabelColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad label color", ((ShapeContainerStyleDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            }
            return result;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest.CheckStyleCreationForStyleDescSwitch#caseEdgeStyle(org.eclipse.sirius.viewpoint.EdgeStyle)
         */
        @Override
        public Object caseEdgeStyle(EdgeStyle object) {
            Object result = super.caseEdgeStyle(object);
            if (object.getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__STROKE_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad stroke color", (StyleHelperTest.CUSTOM_COLOR), object.getStrokeColor());
            } else {
                StyleHelperTest.assertSameRGB("Bad stroke color", ((EdgeStyleDescription) getStyleDesc()).getStrokeColor(), object.getStrokeColor());
            }
            if (object.getCustomFeatures().contains(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName())) {
                SiriusAssert.assertSameRGB("Bad label color", (StyleHelperTest.CUSTOM_COLOR), object.getCenterLabelStyle().getLabelColor());
            } else {
                StyleHelperTest
                        .assertSameRGB("Bad label color", ((EdgeStyleDescription) getStyleDesc()).getCenterLabelStyleDescription().getLabelColor(), object.getCenterLabelStyle().getLabelColor());
            }
            return result;
        }
    }

    /**
     * Switch to check the style for a given style description.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static class CheckStyleCreationForStyleDescSwitch extends DiagramSwitch<Object> {
        /**
         * The style description to use for check.
         */
        private final StyleDescription styleDesc;

        /**
         * Default constructor.
         * 
         * @param styleDesc
         *            the style description to use for check, should not be
         *            <code>null</code>.
         */
        private CheckStyleCreationForStyleDescSwitch(final StyleDescription styleDesc) {
            this.styleDesc = styleDesc;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseStyle(org.eclipse.sirius.viewpoint.Style)
         */
        @Override
        public Object caseStyle(Style object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof StyleDescription);
            assertEquals("Bad description", getStyleDesc(), object.getDescription());
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseBorderedStyle(org.eclipse.sirius.viewpoint.BorderedStyle)
         */
        @Override
        public Object caseBorderedStyle(BorderedStyle object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof BorderedStyleDescription);
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseLabelStyle(org.eclipse.sirius.viewpoint.LabelStyle)
         */
        @Override
        public Object caseLabelStyle(LabelStyle object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof LabelStyleDescription);
            assertEquals("Bad label alignment", ((LabelStyleDescription) getStyleDesc()).getLabelAlignment(), object.getLabelAlignment());
            assertEquals("Bad label format", ((LabelStyleDescription) getStyleDesc()).getLabelFormat(), object.getLabelFormat());
            assertEquals("Bad label size", ((LabelStyleDescription) getStyleDesc()).getLabelSize(), object.getLabelSize());
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseNodeStyle(org.eclipse.sirius.viewpoint.NodeStyle)
         */
        @Override
        public Object caseNodeStyle(NodeStyle object) {
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseBundledImage(org.eclipse.sirius.viewpoint.BundledImage)
         */
        @Override
        public Object caseBundledImage(BundledImage object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof BundledImageDescription);
            assertEquals("Bad bundled image shape", ((BundledImageDescription) getStyleDesc()).getShape(), object.getShape());
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseCustomStyle(org.eclipse.sirius.viewpoint.CustomStyle)
         */
        @Override
        public Object caseCustomStyle(CustomStyle object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof CustomStyleDescription);
            assertEquals("Bad id", ((CustomStyleDescription) getStyleDesc()).getId(), object.getId());
            return super.caseCustomStyle(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseDot(org.eclipse.sirius.viewpoint.Dot)
         */
        @Override
        public Object caseDot(Dot object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof DotDescription);
            assertEquals("Bad stroke size computation", ((DotDescription) getStyleDesc()).getStrokeSizeComputationExpression(), object.getStrokeSizeComputationExpression());
            return super.caseDot(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseEllipse(org.eclipse.sirius.viewpoint.Ellipse)
         */
        @Override
        public Object caseEllipse(Ellipse object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof EllipseNodeDescription);
            // The height and width are not checked because in this test the
            // Style has not DDiagramElement parent so the expression can not be
            // evaluated.
            return super.caseEllipse(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseGaugeCompositeStyle(org.eclipse.sirius.viewpoint.GaugeCompositeStyle)
         */
        @Override
        public Object caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof GaugeCompositeStyleDescription);
            assertEquals("Bad alignment", ((GaugeCompositeStyleDescription) getStyleDesc()).getAlignment(), object.getAlignment());
            assertEquals("Bad number of sections", ((GaugeCompositeStyleDescription) getStyleDesc()).getSections().size(), object.getSections().size());
            for (int i = 0; i < object.getSections().size(); i++) {
                GaugeSection section = object.getSections().get(i);
                assertEquals("Bad label", ((GaugeCompositeStyleDescription) getStyleDesc()).getSections().get(i).getLabel(), section.getLabel());
                assertEquals("Bad max value", Integer.getInteger(((GaugeCompositeStyleDescription) getStyleDesc()).getSections().get(i).getMaxValueExpression()), section.getMax());
                assertEquals("Bad min value", Integer.getInteger(((GaugeCompositeStyleDescription) getStyleDesc()).getSections().get(i).getMinValueExpression()), section.getMin());
                assertEquals("Bad value", Integer.getInteger(((GaugeCompositeStyleDescription) getStyleDesc()).getSections().get(i).getValueExpression()), section.getValue());
            }
            return super.caseGaugeCompositeStyle(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseLozenge(org.eclipse.sirius.viewpoint.Lozenge)
         */
        @Override
        public Object caseLozenge(Lozenge object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof LozengeNodeDescription);
            StyleHelperTest.assertSameRGB("Bad color", ((LozengeNodeDescription) getStyleDesc()).getColor(), object.getColor());
            StyleHelperTest.assertSameRGB("Bad label color", ((LozengeNodeDescription) getStyleDesc()).getLabelColor(), object.getLabelColor());
            // The height and width are not checked because in this test the
            // Style has not DDiagramElement parent so the expression can not be
            // evaluated.
            return super.caseLozenge(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseNote(org.eclipse.sirius.viewpoint.Note)
         */
        @Override
        public Object caseNote(Note object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof NoteDescription);
            return super.caseNote(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseSquare(org.eclipse.sirius.viewpoint.Square)
         */
        @Override
        public Object caseSquare(Square object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof SquareDescription);
            assertEquals("Bad height", ((SquareDescription) getStyleDesc()).getHeight(), object.getHeight());
            assertEquals("Bad width", ((SquareDescription) getStyleDesc()).getWidth(), object.getWidth());
            return super.caseSquare(object);
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseFlatContainerStyle(org.eclipse.sirius.viewpoint.FlatContainerStyle)
         */
        @Override
        public Object caseFlatContainerStyle(FlatContainerStyle object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof FlatContainerStyleDescription);
            assertEquals("Bad background style", ((FlatContainerStyleDescription) getStyleDesc()).getBackgroundStyle(), object.getBackgroundStyle());
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseShapeContainerStyle(org.eclipse.sirius.viewpoint.ShapeContainerStyle)
         */
        @Override
        public Object caseShapeContainerStyle(ShapeContainerStyle object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof ShapeContainerStyleDescription);
            assertEquals("Bad shape", ((ShapeContainerStyleDescription) getStyleDesc()).getShape(), object.getShape());
            return null;

        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseWorkspaceImage(org.eclipse.sirius.viewpoint.WorkspaceImage)
         */
        @Override
        public Object caseWorkspaceImage(WorkspaceImage object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof WorkspaceImageDescription);
            assertEquals("Bad image path", ((WorkspaceImageDescription) getStyleDesc()).getWorkspacePath(), object.getWorkspacePath());
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.util.DiagramSwitch#caseEdgeStyle(org.eclipse.sirius.viewpoint.EdgeStyle)
         */
        @Override
        public Object caseEdgeStyle(EdgeStyle object) {
            assertTrue("Bad kind of StyleDescription for this style", getStyleDesc() instanceof EdgeStyleDescription);
            assertEquals("Bad folding style", ((EdgeStyleDescription) getStyleDesc()).getFoldingStyle(), object.getFoldingStyle());
            assertEquals("Bad line style", ((EdgeStyleDescription) getStyleDesc()).getLineStyle(), object.getLineStyle());
            assertEquals("Bad source arrow", ((EdgeStyleDescription) getStyleDesc()).getSourceArrow(), object.getSourceArrow());
            assertEquals("Bad target arrow", ((EdgeStyleDescription) getStyleDesc()).getTargetArrow(), object.getTargetArrow());
            assertEquals("Bad routing style", ((EdgeStyleDescription) getStyleDesc()).getRoutingStyle(), object.getRoutingStyle());
            return super.caseEdgeStyle(object);
        }

        @Override
        public Object caseBracketEdgeStyle(BracketEdgeStyle object) {
            return super.caseBracketEdgeStyle(object);
        }

        protected StyleDescription getStyleDesc() {
            return styleDesc;
        }
    }

}
