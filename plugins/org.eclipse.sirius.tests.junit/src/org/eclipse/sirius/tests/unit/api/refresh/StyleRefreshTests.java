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
package org.eclipse.sirius.tests.unit.api.refresh;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.junit.Assert;

/**
 * Refresh style tests for Entities diagram of ecore modeler.
 * 
 * @author nlepine
 */
public class StyleRefreshTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String PATH = "/data/unit/refresh/style/";

    private static final String SEMANTIC_MODEL_NAME = "test861.ecore";

    private static final String MODELER_MODEL_NAME = "ecore.odesign";

    private static final String REPRESENTATIONS_MODEL_NAME = "test861.aird";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_NAME;

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_MODEL_NAME;

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + REPRESENTATIONS_MODEL_NAME;

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private DDiagram diagram;

    private static final RGBValues CUSTOM_COLOR = RGBValues.create(33, 33, 33);


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(MODELER_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SEMANTIC_MODEL_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(REPRESENTATIONS_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_MODEL_NAME);
        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_MODEL_NAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
    }

    public void testShapeContainerStyle() {
        Layer layer = getLayer(diagram, "Default");
        ContainerMapping containerMapping = getContainerMapping(layer, "EC EClass");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertNull(currentDescription);

        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        DDiagramElement element = getFirstDiagramElement(diagram, eClass);
        assertNotNull(element);

        ContainerStyleDescription description = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof ShapeContainerStyleDescription);
        assertTrue(element.getStyle() instanceof ShapeContainerStyle);
        assertEquals(containerMapping.getStyle(), description);

    }

    public void testCustomShapeContainerStyle() {
        Layer layer = getLayer(diagram, "Default");
        ContainerMapping containerMapping = getContainerMapping(layer, "EC EClass");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertNull(currentDescription);

        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        assertNotNull(diagramElement);

        // First style description with a blue background color
        ShapeContainerStyleDescription styleDescription = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        Assert.assertNotSame("Border and background default color should be different", styleDescription.getBackgroundColor(), styleDescription.getBorderColor());
        SystemColor blue = EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("blue1");
        blue.setBlue(200);
        blue.setGreen(0);
        blue.setRed(0);
        styleDescription.setBackgroundColor(blue);
        setDescriptionStyle(containerMapping, styleDescription);
        refresh(diagram);
        assertEquals(diagramElement.getStyle().getDescription(), styleDescription);

        /* mark style as custom */
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        ShapeContainerStyle shapeContainerStyle = (ShapeContainerStyle) diagramElement.getStyle();
        Command setCustomStyleCmd = AddCommand.create(domain, shapeContainerStyle, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES,
                DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR.getName());
        domain.getCommandStack().execute(setCustomStyleCmd);

        // Second style description with a red background color
        ShapeContainerStyleDescription newStyleDescription = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        SystemColor red = EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("red1");
        red.setBlue(0);
        red.setGreen(0);
        red.setRed(200);
        newStyleDescription.setBackgroundColor(red);
        setDescriptionStyle(containerMapping, newStyleDescription);

        refresh(diagram);
        /*
         * style description should be the second one but the background color
         * of the style should be the background color of the first style
         * description
         */
        assertEquals(diagramElement.getStyle().getDescription(), newStyleDescription);
        RGBValues currentBackgroundColor = ((ShapeContainerStyle) diagramElement.getStyle()).getBackgroundColor();
        assertEquals("The background color is not the expected one", blue.getBlue(), currentBackgroundColor.getBlue());
        assertEquals("The background color is not the expected one", blue.getGreen(), currentBackgroundColor.getGreen());
        assertEquals("The background color is not the expected one", blue.getRed(), currentBackgroundColor.getRed());

    }

    public void testWorkspaceImageContainerStyle() {
        Layer layer = getLayer(diagram, "Default");
        ContainerMapping containerMapping = getContainerMapping(layer, "EC EClass");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertNull(currentDescription);

        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        DDiagramElement element = getFirstDiagramElement(diagram, eClass);
        assertNotNull(element);

        ContainerStyleDescription description = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof WorkspaceImageDescription);
        assertTrue(element.getStyle() instanceof WorkspaceImage);
        assertEquals(containerMapping.getStyle(), description);

    }

    public void testFlatContainerStyle() {
        Layer layer = getLayer(diagram, "Default");
        ContainerMapping containerMapping = getContainerMapping(layer, "EC EClass");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertNull(currentDescription);

        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        DDiagramElement element = getFirstDiagramElement(diagram, eClass);
        assertNotNull(element);

        ContainerStyleDescription description = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        setDescriptionStyle(containerMapping, description);
        refresh(diagram);
        assertTrue(containerMapping.getStyle() instanceof FlatContainerStyleDescription);
        assertTrue(element.getStyle() instanceof FlatContainerStyle);
        assertEquals(containerMapping.getStyle(), description);

    }

    public void testCustomColorShapeContainerStyle() {
        Layer layer = getLayer(diagram, "Default");
        ContainerMapping containerMapping = getContainerMapping(layer, "EC EClass");
        ContainerStyleDescription currentDescription = containerMapping.getStyle();
        assertNull(currentDescription);

        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        assertNotNull(diagramElement);

        ContainerStyleDescription styleDescription = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        setDescriptionStyle(containerMapping, styleDescription);
        refresh(diagram);
        assertEquals(diagramElement.getStyle().getDescription(), styleDescription);

        // mark style as custom and change the background color
        setCustomBackgroundColor((ShapeContainerStyle) diagramElement.getStyle());

        refresh(diagram);
        /* style description should be the old one */
        assertEquals(diagramElement.getStyle().getDescription(), styleDescription);
        SiriusAssert.assertSameRGB("Bad background color", CUSTOM_COLOR, ((ShapeContainerStyle) diagramElement.getStyle()).getBackgroundColor());

        /* style description should be the old one */
        assertEquals(diagramElement.getStyle().getDescription(), styleDescription);
        SiriusAssert.assertSameRGB("Bad background color", CUSTOM_COLOR, ((ShapeContainerStyle) diagramElement.getStyle()).getBackgroundColor());
    }

    private void setDescriptionStyle(final ContainerMapping containerMapping, final ContainerStyleDescription style) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command setStyleCmd = SetCommand.create(domain, containerMapping, DescriptionPackage.Literals.CONTAINER_MAPPING__STYLE, style);
        domain.getCommandStack().execute(setStyleCmd);
    }

    private void setCustomBackgroundColor(final ShapeContainerStyle style) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CompoundCommand compoundCommand = new CompoundCommand();

        RGBValues rgbValues = StyleRefreshTests.CUSTOM_COLOR;
        Command setBackgroundColorCmd = SetCommand.create(domain, style, DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, rgbValues);
        compoundCommand.append(setBackgroundColorCmd);
        Command setCustomStyleCmd = AddCommand.create(domain, style, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES,
                DiagramPackage.Literals.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR.getName());
        compoundCommand.append(setCustomStyleCmd);
        domain.getCommandStack().execute(compoundCommand);
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

}
