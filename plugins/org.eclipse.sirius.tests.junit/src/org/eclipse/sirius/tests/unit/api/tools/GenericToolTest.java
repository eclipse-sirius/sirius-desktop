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

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

public class GenericToolTest extends SiriusDiagramTestCase {
    
    private static final String REFERENCE_NAME = "abc";

    private static final String ATTRIBUTE_NAME = "b";

    private static final String CLASS_NAME = "C1";

    private static final String TOOL_NAME = "Rename+A";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/tc1909/My.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/tc1909/1909.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/tc1909/My.odesign";

    private static final String REPRESENTATION_DESC_NAME = "Test";

    private DDiagram diagram;

    private EPackage rootPkg;

    private EClass class1;

    private EAttribute attr1;

    private EReference ref;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);

        rootPkg = (EPackage) semanticModel;
        class1 = (EClass) findElementByName(rootPkg, CLASS_NAME);
        assertNotNull(class1);
        attr1 = (EAttribute) findElementByName(rootPkg, ATTRIBUTE_NAME);
        assertNotNull(attr1);
        ref = (EReference) findElementByName(rootPkg, REFERENCE_NAME);
        assertNotNull(ref);
    }

    public void testGenericTool() throws Exception {
        DDiagramElement classNode = getFirstDiagramElement(diagram, class1);
        assertNotNull(classNode);
        assertTrue(class1.getName().equals(CLASS_NAME));
        applyNodeCreationTool(TOOL_NAME, diagram, classNode);
        assertTrue(class1.getName().equals(CLASS_NAME + "A"));

        DDiagramElement attrNode = getFirstDiagramElement(diagram, attr1);
        assertNotNull(attrNode);
        assertTrue(attr1.getName().equals(ATTRIBUTE_NAME));
        applyNodeCreationTool(TOOL_NAME, diagram, attrNode);
        assertTrue(attr1.getName().equals(ATTRIBUTE_NAME + "A"));

        DDiagramElement refEdge = getFirstDiagramElement(diagram, ref);
        assertNotNull(refEdge);
        assertTrue(ref.getName().equals(REFERENCE_NAME));
        applyNodeCreationTool(TOOL_NAME, diagram, refEdge);
        assertTrue(ref.getName().equals(REFERENCE_NAME + "A"));
    }

    public ENamedElement findElementByName(final ENamedElement root, final String name) {
        if (root.getName().equals(name)) {
            return root;
        } else {
            final TreeIterator<EObject> iter = root.eAllContents();
            while (iter.hasNext()) {
                final EObject obj = iter.next();
                if (obj instanceof ENamedElement && ((ENamedElement) obj).getName().equals(name)) {
                    return (ENamedElement) obj;
                }
            }
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        
        diagram = null;
        rootPkg = null;
        class1 = null;
        attr1 = null;
        ref = null;
        
        super.tearDown();
    }

}
