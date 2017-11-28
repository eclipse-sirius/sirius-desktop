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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.CustomStyleDescription;
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;

import junit.framework.TestCase;

/**
 * Test the default color associated with every style. You might launch this
 * test as pure JUnit.
 * 
 * @author cbrun
 * 
 */
public class DefaultColorsTest extends TestCase {

    VisualBindingManager colorManager;

    ColorFactory colorFactory;

    private Resource res;

    private StyleHelper styleHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.styleHelper = new StyleHelper(CompoundInterpreter.INSTANCE);
        colorManager = new VisualBindingManager();
        colorManager.init(10, 10);
        colorFactory = new ColorFactory(colorManager);
        ResourceSet set = new ResourceSetImpl();

        res = set.createResource(URI.createURI("http://test.xmi"));

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        colorManager.dispose();
        res.getContents().clear();
    }

    public void testWorkspaceImageDefaultColor() throws Exception {
        WorkspaceImageDescription desc = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        res.getContents().add(desc);
        WorkspaceImage style = (WorkspaceImage) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testCustomStyleDefaultColor() throws Exception {
        CustomStyleDescription desc = StyleFactory.eINSTANCE.createCustomStyleDescription();
        res.getContents().add(desc);
        CustomStyle style = (CustomStyle) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testBundledImageDefaultColor() throws Exception {
        BundledImageDescription desc = StyleFactory.eINSTANCE.createBundledImageDescription();
        res.getContents().add(desc);
        BundledImage style = (BundledImage) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.black(), colorFactory.create(style.getColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testDotBackgroundColor() throws Exception {
        DotDescription desc = StyleFactory.eINSTANCE.createDotDescription();
        res.getContents().add(desc);
        Dot style = (Dot) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.gray(), colorFactory.create(style.getBackgroundColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testEdgeStrokeColor() throws Exception {
        EdgeStyleDescription desc = StyleFactory.eINSTANCE.createEdgeStyleDescription();
        CenterLabelStyleDescription centerLabelStyleDescription = StyleFactory.eINSTANCE.createCenterLabelStyleDescription();
        desc.setCenterLabelStyleDescription(centerLabelStyleDescription);
        res.getContents().add(desc);
        EdgeStyle style = (EdgeStyle) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.gray(), colorFactory.create(style.getStrokeColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getCenterLabelStyle().getLabelColor()));
    }

    public void testEllipseColor() throws Exception {
        EllipseNodeDescription desc = StyleFactory.eINSTANCE.createEllipseNodeDescription();
        res.getContents().add(desc);
        Ellipse style = (Ellipse) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.gray(), colorFactory.create(style.getColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testFlatContainerColors() throws Exception {
        FlatContainerStyleDescription desc = StyleFactory.eINSTANCE.createFlatContainerStyleDescription();
        res.getContents().add(desc);
        FlatContainerStyle style = (FlatContainerStyle) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.white(), colorFactory.create(style.getBackgroundColor()));
        assertEquals(colorFactory.light_gray(), colorFactory.create(style.getForegroundColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testLozengeColor() throws Exception {
        LozengeNodeDescription desc = StyleFactory.eINSTANCE.createLozengeNodeDescription();
        res.getContents().add(desc);
        Lozenge style = (Lozenge) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.gray(), colorFactory.create(style.getColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testNoteColor() throws Exception {
        NoteDescription desc = StyleFactory.eINSTANCE.createNoteDescription();
        res.getContents().add(desc);
        Note style = (Note) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.yellow(), colorFactory.create(style.getColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testSquareColor() throws Exception {
        SquareDescription desc = StyleFactory.eINSTANCE.createSquareDescription();
        res.getContents().add(desc);
        Square style = (Square) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.gray(), colorFactory.create(style.getColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

    public void testShapeContainerColor() throws Exception {
        ShapeContainerStyleDescription desc = StyleFactory.eINSTANCE.createShapeContainerStyleDescription();
        res.getContents().add(desc);
        ShapeContainerStyle style = (ShapeContainerStyle) styleHelper.createStyle(desc);
        styleHelper.refreshStyle(style);
        assertEquals(colorFactory.light_gray(), colorFactory.create(style.getBackgroundColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getBorderColor()));
        assertEquals(colorFactory.black(), colorFactory.create(style.getLabelColor()));
    }

}
