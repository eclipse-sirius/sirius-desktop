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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Tools tests for Entities diagram of ecore modeler : for operation testing.
 * 
 * @author nlepine
 */
public class ForToolTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/testFor.ecore";

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.junit/data/unit/tool/ecore.odesign";

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/testFor.aird";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
    }

    public void testForTools() {
        EPackage ePackage = (EPackage) semanticModel;
        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        EOperation operation = eClass.getEOperations().get(0);

        // add parameters
        applyDirectEditTool("Operation Name", diagram, getFirstDiagramElement(diagram, operation), "test (a EFloat, b EInt, c EByte) : EInt");
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
    }

    @Override
    protected void tearDown() throws Exception {
        
        diagram = null;
        
        super.tearDown();
    }

}
