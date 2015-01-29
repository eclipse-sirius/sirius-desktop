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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * Create view tool tests for Entities diagram of ecore modeler.
 * 
 * @author nlepine
 */
public class CreateNodeViewTest extends SiriusDiagramTestCase {

    private DDiagram diagram;

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/1907/1907.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/1907/1907.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/1907/ecore.odesign";

    private static final String ENTITIES_DESC_NAME = "Entities";//$NON-NLS-1$

    private static final String BORDERED_NODE_VIEW_DESC_NAME = "borderedNodeView";//$NON-NLS-1$

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    /**
     * Test creation view in package : must create edge view
     */
    public void testNodeCreationInPackage() {
        EPackage ePackage = (EPackage) semanticModel;
        EPackage p2 = (EPackage) findElementByName(ePackage, "p2");
        DDiagramElement nodeElement = getFirstDiagramElement(diagram, p2);
        boolean applyNodeCreationTool = applyNodeCreationTool("EClassCreationInPackage", diagram, nodeElement);
        assertTrue(applyNodeCreationTool);

        EClass c3 = (EClass) findElementByName(ePackage, "c3");
        DNode nodeElementC3 = getFirstNodeElement(diagram, c3);
        assertNotNull(nodeElementC3);
        DRepresentationElement representationElementC3 = getFirstRepresentationElement(diagram, c3);
        assertNotNull(representationElementC3);
        assertNotNull(getFirstEdgeElement(diagram, c3));

        EClass c4 = (EClass) findElementByName(ePackage, "c4");
        DNode nodeElementC4 = getFirstNodeElement(diagram, c4);
        assertNotNull(nodeElementC4);
        DRepresentationElement representationElementC4 = getFirstRepresentationElement(diagram, c4);
        assertNotNull(representationElementC4);
        assertNotNull(getFirstEdgeElement(diagram, c4));
    }

    /**
     * Test creation view in diagram : must create edge view
     */
    public void testNodeCreation() {
        EPackage ePackage = (EPackage) semanticModel;
        boolean applyNodeCreationTool = applyNodeCreationTool("EClass", diagram, diagram);
        assertTrue(applyNodeCreationTool);

        EClass c2 = (EClass) findElementByName(ePackage, "c2");
        DNode nodeElementC2 = getFirstNodeElement(diagram, c2);
        assertNotNull(nodeElementC2);
        DRepresentationElement representationElementC2 = getFirstRepresentationElement(diagram, c2);
        assertNotNull(representationElementC2);
        assertNotNull(getFirstEdgeElement(diagram, c2));

        EClass c1 = (EClass) findElementByName(ePackage, "c1");
        DNode nodeElementC1 = getFirstNodeElement(diagram, c1);
        assertNotNull(nodeElementC1);
        DRepresentationElement representationElementC1 = getFirstRepresentationElement(diagram, c1);
        assertNotNull(representationElementC1);
        assertNotNull(getFirstEdgeElement(diagram, c1));

    }

    /**
     * Call a tool that creates a view for a bordered node mapping on a List
     * then ensure that the created element is a {@link DNode} and not a
     * {@link DNodeListElement}. This test must be made in manual refresh mode.
     * This test is for the issue VP-3794.
     */
    public void testBorderedNodeViewOnListCreation() {
        // We should be in manual refresh for this test.
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        // Get the use case diagram
        diagram = (DDiagram) getRepresentations(BORDERED_NODE_VIEW_DESC_NAME).toArray()[0];
        // Get the DNodeList corresponding to the package on which to apply the
        // tool
        EPackage rootPackage = (EPackage) semanticModel;
        EPackage p2 = (EPackage) findElementByName(rootPackage, "p2");
        DDiagramElement nodeElement = getFirstDiagramElement(diagram, p2);
        // Apply the tool thath creates a view on the DNodeList
        boolean applyNodeCreationTool = applyNodeCreationTool("createWithCreateView", diagram, nodeElement);
        assertTrue("The tool has not been correctly called.", applyNodeCreationTool);

        // Ensure that a new class has been created (semantically).
        EClass newClass = (EClass) findElementByName(rootPackage, "NewClass");
        assertNotNull("A new class should be created with name NewClass.", newClass);
        // Ensure a DNode has been created for this new class.
        DNode nodeElementNewClass = getFirstNodeElement(diagram, newClass);
        assertNotNull("A DNode should be created for the new class.", nodeElementNewClass);
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

        super.tearDown();
    }
}
