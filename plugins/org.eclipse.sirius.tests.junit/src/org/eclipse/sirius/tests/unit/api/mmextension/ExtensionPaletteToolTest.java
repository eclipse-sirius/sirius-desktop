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
package org.eclipse.sirius.tests.unit.api.mmextension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

public class ExtensionPaletteToolTest extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final List<String> modelerPathes = new ArrayList<String>();
        modelerPathes.add(MODELER_PATH);
        modelerPathes.add(MODELER_EXTENSION_PATH);
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, modelerPathes);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initViewpoint(DOCUMENTATION_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    public void testLayerToolInExtensionStyle() {
        EPackage ePackage = (EPackage) semanticModel;
        activateLayer(diagram, DOCUMENTATION_VIEWPOINT_NAME);
        assertTrue(applyNodeCreationTool("Doc Annotation", diagram, diagram));
        EAnnotation eAnnotation = ePackage.getEAnnotations().get(0);
        assertNotNull(eAnnotation);
    }

    @Override
    protected void tearDown() throws Exception {
        
        diagram = null;
        
        super.tearDown();
    }

}
