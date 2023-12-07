/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Tools tests for Entities diagram of ecore modeler.
 * 
 * @author nlepine
 */
public class EntitiesDiagramDirectEditToolOnOperationTests extends SiriusDiagramTestCase  implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/directEdit/testOperation.ecore";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/directEdit/testOperation.aird";

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);
        diagram = (DDiagram) getRepresentations(EcoreModeler.ENTITIES_DESC_NAME, semanticModel).toArray()[0];
    }

    public void testDirectEditOnOperation() {
        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        EOperation operation = eClass.getEOperations().get(0);
        // rename the operation name
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test");
        assertEquals("test", operation.getName());
        assertNull(operation.getEType());
        assertTrue(operation.getEParameters().isEmpty());

        // rename the return type
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), ": EInt");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(operation.getEParameters().isEmpty());

        // add a parameter
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test (a EFloat)");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(!operation.getEParameters().isEmpty());
        assertEquals(operation.getEParameters().get(0).getName(), "a");
        assertEquals(operation.getEParameters().get(0).getEType(), EcorePackage.eINSTANCE.getEFloat());

        // remove a parameter
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test ()");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(operation.getEParameters().isEmpty());

        // add parameters
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test (a EFloat, b EInt, c EByte)");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(!operation.getEParameters().isEmpty());
        assertTrue(operation.getEParameters().size() == 3);
        assertEquals(operation.getEParameters().get(0).getName(), "a");
        assertEquals(operation.getEParameters().get(0).getEType(), EcorePackage.eINSTANCE.getEFloat());
        assertEquals(operation.getEParameters().get(1).getName(), "b");
        assertEquals(operation.getEParameters().get(1).getEType(), EcorePackage.eINSTANCE.getEInt());
        assertEquals(operation.getEParameters().get(2).getName(), "c");
        assertEquals(operation.getEParameters().get(2).getEType(), EcorePackage.eINSTANCE.getEByte());

        // update parameters names
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test (x, y, z)");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(!operation.getEParameters().isEmpty());
        assertTrue(operation.getEParameters().size() == 3);
        assertEquals(operation.getEParameters().get(0).getName(), "x");
        assertEquals(operation.getEParameters().get(0).getEType(), EcorePackage.eINSTANCE.getEFloat());
        assertEquals(operation.getEParameters().get(1).getName(), "y");
        assertEquals(operation.getEParameters().get(1).getEType(), EcorePackage.eINSTANCE.getEInt());
        assertEquals(operation.getEParameters().get(2).getName(), "z");
        assertEquals(operation.getEParameters().get(2).getEType(), EcorePackage.eINSTANCE.getEByte());

        // update parameters names and types
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test (x EInt, y EBoolean, z EInt)");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(!operation.getEParameters().isEmpty());
        assertTrue(operation.getEParameters().size() == 3);
        assertEquals(operation.getEParameters().get(0).getName(), "x");
        assertEquals(operation.getEParameters().get(0).getEType(), EcorePackage.eINSTANCE.getEInt());
        assertEquals(operation.getEParameters().get(1).getName(), "y");
        assertEquals(operation.getEParameters().get(1).getEType(), EcorePackage.eINSTANCE.getEBoolean());
        assertEquals(operation.getEParameters().get(2).getName(), "z");
        assertEquals(operation.getEParameters().get(2).getEType(), EcorePackage.eINSTANCE.getEInt());

        // remove parameters
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test (x EInt)");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(!operation.getEParameters().isEmpty());
        assertTrue(operation.getEParameters().size() == 1);
        assertEquals(operation.getEParameters().get(0).getName(), "x");
        assertEquals(operation.getEParameters().get(0).getEType(), EcorePackage.eINSTANCE.getEInt());

        // test with only parameters
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "(x EInt, y EBoolean, z EInt)");
        assertEquals("test", operation.getName());
        assertEquals(operation.getEType(), EcorePackage.eINSTANCE.getEInt());
        assertTrue(!operation.getEParameters().isEmpty());
        assertTrue(operation.getEParameters().size() == 3);
        assertEquals(operation.getEParameters().get(0).getName(), "x");
        assertEquals(operation.getEParameters().get(0).getEType(), EcorePackage.eINSTANCE.getEInt());
        assertEquals(operation.getEParameters().get(1).getName(), "y");
        assertEquals(operation.getEParameters().get(1).getEType(), EcorePackage.eINSTANCE.getEBoolean());
        assertEquals(operation.getEParameters().get(2).getName(), "z");
        assertEquals(operation.getEParameters().get(2).getEType(), EcorePackage.eINSTANCE.getEInt());

    }

    public void testDirectEditOnEPackage() throws Exception {
        assertTrue(applyNodeCreationTool("Package", diagram, diagram));
        final EPackage eSubPackage = ((EPackage) ((DSemanticDecorator) diagram).getTarget()).getESubpackages().get(0);
        assertNotNull(eSubPackage);
        applyDirectEditTool("Edit Name", diagram, getFirstDiagramElement(diagram, eSubPackage), "my name is ?");
        assertEquals("my name is ?", eSubPackage.getName());
    }

}
