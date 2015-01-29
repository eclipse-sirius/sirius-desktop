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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Tests that Drag and Drop tools are correctly defined in the context of
 * recursive Node Imports.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DragAndDropWithImportImportTest extends SiriusDiagramTestCase implements EcoreModeler {

    /**
     * Path of the folder containing all tests files.
     */
    private static final String TEST_FOLDER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/tc_VP-1144/";

    private static final String MODELER_EXTENSION2_PATH = TEST_FOLDER_PATH + "ecore_extension2.odesign";

    private static final String EXTENSION2_VIEWPOINTNAME = "Extension2";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final List<String> modelerPathes = new ArrayList<String>();
        modelerPathes.add(MODELER_PATH);
        modelerPathes.add(MODELER_EXTENSION_PATH);
        modelerPathes.add(MODELER_EXTENSION2_PATH);
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, modelerPathes);
    }

    /**
     * Checks that it is possible to define a new D&D tool on a Node mapping,
     * and that this definition has no edge effect on the imported mapping.
     */
    public void testNewDDToolOnNodeImport() {
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initViewpoint(DOCUMENTATION_VIEWPOINT_NAME);
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        // We first check that the DD Tool has correctly been defined
        diagram = (DDiagram) createRepresentation("Extension2WithDTools");
        checkDragAndDropToolsAreCorrectlyDefined(false, "Default", "ImportedEAttribute", "DDToolOnImportedEAttribute");
        // We then check that the imported node mapping has not been modified
        diagram = (DDiagram) getRepresentations("Entities").toArray()[0];
        checkDragAndDropToolsAreCorrectlyDefined(false, "Default", "EC EAttribute", "DDToolOnImportedEAttribute", false);
    }

    /**
     * Checks that it is possible to define a new D&D tool on a Node mapping and
     * extension Diagram, and that this definition has no edge effect on the
     * imported mapping.
     */
    public void testNewDDToolOnNodeImportAndDiagramExtension() {
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initViewpoint(DOCUMENTATION_VIEWPOINT_NAME);
        initViewpoint(EXTENSION2_VIEWPOINTNAME);

        diagram = (DDiagram) getRepresentations("Entities").toArray()[0];
        activateLayer(diagram, "ExtensionLayer");
        // We first check that the DD Tool has correctly been defined
        // diagram = (DDiagram)
        // getRepresentations("DiagramExtension").toArray()[0];
        checkDragAndDropToolsAreCorrectlyDefined(false, "ExtensionLayer", "ImportedEAttribute2", "DDToolOnImportedEAttribute2");

        // We then check that the imported node mapping has not been modified
        diagram = (DDiagram) getRepresentations("Entities").toArray()[0];
        checkDragAndDropToolsAreCorrectlyDefined(false, "Default", "EC EAttribute", "DDToolOnImportedEAttribute2", false);
    }

    /**
     * Checks that it is possible to define a new D&D tool on a container
     * mapping, and that this definition has no edge effect on the imported
     * mapping.
     */
    public void testNewDDToolOnContainerImport() {
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initViewpoint(DOCUMENTATION_VIEWPOINT_NAME);
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        // We first check that the DD Tool has correctly been defined
        diagram = (DDiagram) createRepresentation("Extension2WithDTools");
        checkDragAndDropToolsAreCorrectlyDefined(true, "Default", "ImportedEClass", "DDToolOnImportedEClass");
        // We then check that the imported node mapping has not been modified
        diagram = (DDiagram) getRepresentations("Entities").toArray()[0];
        checkDragAndDropToolsAreCorrectlyDefined(true, "Default", "EC EClass", "DDToolOnImportedEClass", false);
    }

    /**
     * Checks that it is possible to define a new D&D tool on a Node mapping and
     * extension Diagram, and that this definition has no edge effect on the
     * imported mapping.
     */
    public void testNewDDToolOnContainerImportAndDiagramExtension() {
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initViewpoint(DOCUMENTATION_VIEWPOINT_NAME);
        initViewpoint(EXTENSION2_VIEWPOINTNAME);

        // We first check that the DD Tool has correctly been defined
        diagram = (DDiagram) getRepresentations("Entities").toArray()[0];
        activateLayer(diagram, "ExtensionLayer");
        checkDragAndDropToolsAreCorrectlyDefined(true, "ExtensionLayer", "ImportedExternalEClass", "DDToolOnImportedExternalEClass");

        // We then check that the imported node mapping has not been modified
        diagram = (DDiagram) getRepresentations("Entities").toArray()[0];
        checkDragAndDropToolsAreCorrectlyDefined(true, "Default", "EC External EClass", "DDToolOnImportedExternalEClass", false);
    }

    /**
     * Checks that the Base Node Mapping (BNM) is associated to the correct Drop
     * Tool.
     */
    public void testDropTollOnBaseNodeMapping() {
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initViewpoint(DOCUMENTATION_VIEWPOINT_NAME);
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        diagram = (DDiagram) createRepresentation("Extension2Diagram");
        checkDragAndDropToolsAreCorrectlyDefined(false, "Default", "Node1", "DragAndDropOnNode1");
    }

    /**
     * Checks that a NodeMapping Import (NMI) defined on BNM is associated to
     * the correct Drop Tool.
     */
    public void testDropTollOnImportNodeMapping() {
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        diagram = (DDiagram) createRepresentation("Extension2DiagramWithImports");
        checkDragAndDropToolsAreCorrectlyDefined(false, "Default", "Node1Import1", "DragAndDropOnNode1");
    }

    /**
     * Checks that a NodeMapping Import defined on NMI is associated to the
     * correct Drop Tool.
     */
    public void testDropTollOnRecursiveImportNodeMapping() {
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        diagram = (DDiagram) createRepresentation("Extension2DiagramWithRecursiveImports");
        checkDragAndDropToolsAreCorrectlyDefined(false, "Default", "Node1Import2", "DragAndDropOnNode1");
    }

    /**
     * Checks that the Base Node Mapping (BCM) is associated to the correct Drop
     * Tool.
     */
    public void testDropTollOnBaseContainerMapping() {
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        diagram = (DDiagram) createRepresentation("Extension2Diagram");
        checkDragAndDropToolsAreCorrectlyDefined(true, "Default", "Container1", "DragAndDropOnContainer1");
    }

    /**
     * Checks that a ContainerMapping Import (CMI) defined on BCM is associated
     * to the correct Drop Tool.
     */
    public void testDropTollOnImportContainerMapping() {
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        diagram = (DDiagram) createRepresentation("Extension2DiagramWithImports");
        checkDragAndDropToolsAreCorrectlyDefined(true, "Default", "Container1Import1", "DragAndDropOnContainer1");
    }

    /**
     * Checks that a ContainerMapping Import defined on CMI is associated to the
     * correct Drop Tool.
     */
    public void testDropTollOnRecursiveImportContainerMapping() {
        initViewpoint(EXTENSION2_VIEWPOINTNAME);
        diagram = (DDiagram) createRepresentation("Extension2DiagramWithRecursiveImports");
        checkDragAndDropToolsAreCorrectlyDefined(true, "Default", "Container1Import2", "DragAndDropOnContainer1");
    }

    /**
     * Tests if the {@link DiagramElementMapping}g with the given name is
     * associated to a DropDescription with the given name.
     * 
     * @param isContainerMapping
     *            indicates if the DiagramElementMapping is a ContainerMapping
     *            or a NodeMapping
     * @param representationName
     *            the name of the representation in which the
     *            DiagramElementMapping has been defined
     * @param layerName
     *            the name of the layer in which the DiagramElementMapping has
     *            been defined
     * @param mappingName
     *            the name of the {@link DiagramElementMapping} to consider
     * @param expectedDragAndDropToolName
     *            the name of the drop description which should be associated to
     *            the considered DiagramElementMapping
     */
    protected void checkDragAndDropToolsAreCorrectlyDefined(boolean isContainerMapping, String layerName, String mappingName, String expectedDragAndDropToolName) {
        checkDragAndDropToolsAreCorrectlyDefined(isContainerMapping, layerName, mappingName, expectedDragAndDropToolName, true);
    }

    /**
     * Tests if the {@link DiagramElementMapping}g with the given name is
     * associated to a DropDescription with the given name.
     * 
     * @param isContainerMapping
     *            indicates if the DiagramElementMapping is a ContainerMapping
     *            or a NodeMapping
     * @param representationName
     *            the name of the representation in which the
     *            DiagramElementMapping has been defined
     * @param layerName
     *            the name of the layer in which the DiagramElementMapping has
     *            been defined
     * @param mappingName
     *            the name of the {@link DiagramElementMapping} to consider
     * @param expectedDragAndDropToolName
     *            the name of the drop description which should be associated to
     *            the considered DiagramElementMapping
     * @param isDToolExpected
     *            indicates if the a DD Tool should have been defined on the
     *            given mapping
     */
    protected void checkDragAndDropToolsAreCorrectlyDefined(boolean isContainerMapping, String layerName, String mappingName, String expectedDragAndDropToolName, boolean isDToolExpected) {
        assertNotNull(diagram);
        Layer layer = getLayer(diagram, layerName);
        assertNotNull(layer);
        DiagramElementMapping nodeMapping = null;
        if (isContainerMapping) {
            try {
                nodeMapping = getContainerMapping(layer, mappingName);
            } catch (NullPointerException e) {
                nodeMapping = layer.getContainerMappings().iterator().next();
            }
        } else {
            try {
                nodeMapping = getNodeMapping(layer, mappingName);
            } catch (NullPointerException e) {
                nodeMapping = layer.getNodeMappings().iterator().next();
            }

        }
        assertNotNull(nodeMapping);
        Collection<ContainerDropDescription> extensionDropTools = new DiagramElementMappingQuery(nodeMapping).getDropTools();

        // Check that the tool is present if and only if it is expected.
        boolean found = false;
        for (ContainerDropDescription ext : extensionDropTools) {
            if (isDToolExpected) {
                if (expectedDragAndDropToolName.equals(ext.getName())) {
                    found = true;
                    break;
                }
            } else {
                assertFalse(expectedDragAndDropToolName.equals(ext.getName()));
            }
        }
        if (isDToolExpected) {
            assertTrue(found);
        }
    }

}
