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
package org.eclipse.sirius.tests.unit.api.mappings;

import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * Tests for reuse mappings usage with filters
 * 
 * @author cnotot
 */
public class MappingImportAndFiltersTests extends SiriusDiagramTestCase {

    private String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/trac1926.ecore";

    private String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/mappings/trac1926.odesign";

    private String DEFAULT_VIEWPOINT_NAME = "trac1926";

    private String DIAGRAM_NAME = "D1";

    private DDiagram diagram;

    private final boolean DEFAULT_ISINHERITS_OPTION = true;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
        diagram = (DDiagram) createRepresentation(DIAGRAM_NAME);
    }

    public void testInit() {
        // No Layer and filter activated
        // => 2 nodes with an edge + a container, all in black color.
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        refresh(diagram);

        assertEquals(2, diagram.getNodes().size());
        assertEquals(1, diagram.getContainers().size());
        assertEquals(1, diagram.getEdges().size());

        checkColor(new RGB(0, 0, 0));
    }

    public void testOL2() throws Exception {
        // The layer in the diagram extension is activated.
        // => 2 nodes with an edge + a container, all in red color.
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        refresh(diagram);

        activateALayer(diagram, "OL2");

        assertEquals(2, diagram.getNodes().size());
        assertEquals(1, diagram.getContainers().size());
        assertEquals(1, diagram.getEdges().size());

        checkColor(new RGB(239, 41, 41));
    }

    public void testOL1() throws Exception {
        // The layer in the same diagram is activated.
        // => 2 nodes with an edge + a container, all in green color.
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        refresh(diagram);

        activateALayer(diagram, "OL1");

        assertEquals(2, diagram.getNodes().size());
        assertEquals(1, diagram.getContainers().size());
        assertEquals(1, diagram.getEdges().size());

        checkColor(new RGB(138, 226, 52));

    }

    private void testF3(boolean isInheritsAncestorFilters) throws Exception {
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        refresh(diagram);

        activateFilter(diagram, "F3");
        refresh(diagram);

        assertTrue(isVisible(diagram.getNodes()));
        assertTrue(isVisible(diagram.getEdges()));

        if (isInheritsAncestorFilters == DEFAULT_ISINHERITS_OPTION) {
            assertFalse(isVisible(diagram.getContainers()));
        } else {
            assertTrue(isVisible(diagram.getContainers()));
        }

    }

    public void testOL2F3WithInherit() throws Exception {
        // The layer in the diagram extension is activated.
        // The filter F3 is activated to hide Container mappings.
        // Option inheritsAncestorFilters = true in the odesign.
        // => 2 nodes with an edge all in red color but no container.
        testOL2();
        testF3(DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL2F3WithoutInherit() throws Exception {
        // The layer in the diagram extension is activated.
        // The filter F3 is activated to hide Container mappings.
        // Option inheritsAncestorFilters = false in the odesign.
        // => 2 nodes with an edge + a container, all in red color.

        switchInheritsAncestorFiltersOption("OL2", "MIE3", !DEFAULT_ISINHERITS_OPTION);

        testOL2();

        testF3(!DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL1F3WithInherit() throws Exception {

        // The layer in the same diagram is activated.
        // The filter F3 is activated to hide Container mappings.
        // Option inheritsAncestorFilters = true in the odesign.
        // => 2 nodes with an edge + a container, all in green color.

        testOL1();

        testF3(DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL1F3WithoutInherit() throws Exception {

        // The layer in the same diagram is activated.
        // The filter F3 is activated to hide Container mappings.
        // Option inheritsAncestorFilters = false in the odesign.
        // => 2 nodes with an edge, all in green color but no container.

        switchInheritsAncestorFiltersOption("OL1", "MI3", !DEFAULT_ISINHERITS_OPTION);

        testOL1();

        testF3(!DEFAULT_ISINHERITS_OPTION);
    }

    private void testF2(boolean isInheritsAncestorFilters) throws Exception {
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        refresh(diagram);

        activateFilter(diagram, "F2");
        refresh(diagram);

        assertTrue(isVisible(diagram.getNodes()));
        assertTrue(isVisible(diagram.getContainers()));

        if (isInheritsAncestorFilters == DEFAULT_ISINHERITS_OPTION) {
            assertFalse(isVisible(diagram.getEdges()));
        } else {
            assertTrue(isVisible(diagram.getEdges()));
        }

    }

    public void testOL2F2WithInherit() throws Exception {

        // The layer in the diagram extension is activated.
        // The filter F2 is activated to hide edges.
        // Option inheritsAncestorFilters = true in the odesign.
        // => 2 nodes and a container, all in red color but no edge.

        testOL2();

        testF2(DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL2F2WithoutInherit() throws Exception {

        // The layer in the diagram extension is activated.
        // The filter F2 is activated to hide edges.
        // Option inheritsAncestorFilters = false in the odesign.
        // => 2 nodes with an edge + a container, all in red color.

        switchInheritsAncestorFiltersOption("OL2", "MIE2", !DEFAULT_ISINHERITS_OPTION);

        testOL2();

        testF2(!DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL1F2WithInherit() throws Exception {

        // The layer in the same diagram is activated.
        // The filter F2 is activated to hide edges.
        // Option inheritsAncestorFilters = true in the odesign.
        // => 2 nodes and a container, all in green color but no edge.

        testOL1();

        testF2(DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL1F2WithoutInherit() throws Exception {

        // The layer in the same diagram is activated.
        // The filter F2 is activated to hide edges.
        // Option inheritsAncestorFilters = false in the odesign.
        // => 2 nodes with an edge + a container, all in green color.

        switchInheritsAncestorFiltersOption("OL1", "MI2", !DEFAULT_ISINHERITS_OPTION);

        testOL1();

        testF2(!DEFAULT_ISINHERITS_OPTION);
    }

    private void testF1(boolean isInheritsAncestorFilters) throws Exception {
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        refresh(diagram);

        activateFilter(diagram, "F1");
        refresh(diagram);

        assertTrue(isVisible(diagram.getContainers()));

        if (isInheritsAncestorFilters == DEFAULT_ISINHERITS_OPTION) {
            assertFalse(isVisible(diagram.getNodes()));
            assertFalse(isVisible(diagram.getEdges()));
        } else {
            assertTrue(isVisible(diagram.getNodes()));
            assertTrue(isVisible(diagram.getEdges()));
        }

    }

    public void testOL2F1WithInherit() throws Exception {

        // The layer in the diagram extension is activated.
        // The filter F1 is activated to hide nodes.
        // Option inheritsAncestorFilters = true in the odesign.
        // => A container in red color but no node and no edge.

        testOL2();

        testF1(DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL2F1WithoutInherit() throws Exception {

        // The layer in the diagram extension is activated.
        // The filter F1 is activated to hide nodes.
        // Option inheritsAncestorFilters = false in the odesign.
        // => 2 nodes with an edge + a container, all in red color.

        switchInheritsAncestorFiltersOption("OL2", "MIE1", !DEFAULT_ISINHERITS_OPTION);

        testOL2();

        testF1(!DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL1F1WithInherit() throws Exception {

        // The layer in the diagram extension is activated.
        // The filter F1 is activated to hide nodes.
        // Option inheritsAncestorFilters = true in the odesign.
        // => A container in green color but no node and no edge.

        testOL1();

        testF1(DEFAULT_ISINHERITS_OPTION);
    }

    public void testOL1F1WithoutInherit() throws Exception {

        // The layer in the diagram extension is activated.
        // The filter F1 is activated to hide nodes.
        // Option inheritsAncestorFilters = false in the odesign.
        // => 2 nodes with an edge + a container, all in green color.

        switchInheritsAncestorFiltersOption("OL1", "MI1", !DEFAULT_ISINHERITS_OPTION);

        testOL1();

        testF1(!DEFAULT_ISINHERITS_OPTION);
    }

    private void checkColor(RGB rgbRef) {

        Color colorRef = VisualBindingManager.getDefault().getColorFromRGB(rgbRef);

        checkColorNodes(colorRef);
        checkColorContainers(colorRef);
        checkColorEdges(colorRef);
    }

    private boolean isVisible(EList<? extends DDiagramElement> elements) {
        return DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, elements.get(0));
    }

    private void checkColorNodes(Color colorRef) {
        Iterator<DNode> nodes = diagram.getNodes().iterator();
        while (nodes.hasNext()) {
            DNode node = nodes.next();
            Style style = node.getStyle();
            if (style instanceof BundledImage) {
                RGBValues color = ((BundledImage) style).getColor();
                assertTrue("The expected color is not good.", isSameColor(color, colorRef));
            }
        }
    }

    private void checkColorContainers(Color colorRef) {
        Iterator<DEdge> edges = diagram.getEdges().iterator();
        while (edges.hasNext()) {
            DEdge edge = edges.next();
            Style style = edge.getStyle();
            if (style instanceof EdgeStyle) {
                RGBValues color = ((EdgeStyle) style).getStrokeColor();
                assertTrue("The expected color is not good.", isSameColor(color, colorRef));
            }
        }
    }

    private void checkColorEdges(Color colorRef) {
        Iterator<DDiagramElementContainer> containers = diagram.getContainers().iterator();
        while (containers.hasNext()) {
            DDiagramElementContainer container = containers.next();
            Style style = container.getStyle();
            if (style instanceof FlatContainerStyle) {
                RGBValues color = ((FlatContainerStyle) style).getBackgroundColor();
                assertTrue("The expected color is not good.", isSameColor(color, colorRef));
            }
        }
    }

    private boolean isSameColor(RGBValues color, Color colorRef) {
        return color.getBlue() == colorRef.getBlue() && color.getGreen() == colorRef.getGreen() && color.getRed() == colorRef.getRed();
    }

    private void activateALayer(final DDiagram diagram, final String name) throws Exception {
        assertTrue(activateLayer(diagram, name));
        refresh(diagram);
    }

    private void switchInheritsAncestorFiltersOption(final Layer layer, final String name, final boolean option) {
        final EObject mappingImport = getMappingImport(layer, name);
        if (mappingImport != null) {

            session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                /** {@inheritDoc} */
                @Override
                protected void doExecute() {
                    if (mappingImport instanceof AbstractMappingImport) {
                        ((AbstractMappingImport) mappingImport).setInheritsAncestorFilters(option);
                    } else if (mappingImport instanceof EdgeMappingImport) {
                        ((EdgeMappingImport) mappingImport).setInheritsAncestorFilters(option);
                    }
                }

            });

        }
    }

    private EObject getMappingImport(Layer layer, String name) {
        Iterator<EObject> it = layer.eContents().iterator();
        while (it.hasNext()) {
            EObject obj = it.next();
            if (obj instanceof AbstractMappingImport) {
                if (((RepresentationElementMapping) obj).getName().equals(name))
                    return obj;
            } else if (obj instanceof EdgeMappingImport) {
                if (((EdgeMappingImport) obj).getName().equals(name))
                    return obj;
            }
        }
        return null;
    }

    private void switchInheritsAncestorFiltersOption(String layerName, String mappingImportName, boolean option) {
        diagram = (DDiagram) getRepresentations(DIAGRAM_NAME).toArray()[0];
        refresh(diagram);

        switchInheritsAncestorFiltersOption(getLayer(diagram, layerName), mappingImportName, option);

        refresh(diagram);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
