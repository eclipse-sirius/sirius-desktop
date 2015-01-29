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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Class to test the bug of the issue VP-1180 : Edge disappear after edge
 * creation (without forcerefresh).
 * 
 * @author fbarbin
 * 
 */
public class EntitiesDiagramSiriussConflictTest extends SiriusDiagramTestCase implements EcoreModeler {
    private DDiagram diagram;

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/tc1180/1180.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/tc1180/1180.aird";

    private static final String ARCHETYPE_CLASS_NAME = "ArchetypeClass1";

    private static final String ARCHETYPE_CLASS2_NAME = "ArchetypeClass2";

    private static final String CLASS_NAME = "Class1";

    private static final String CLASS2_NAME = "Class2";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
    }

    /**
     * Test edge creation between 2 existing Archetyped EClasses (with a node
     * mapping import).
     */
    public void testCreateEdgeBetween2Archetype() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        EClass class1 = (EClass) findElementByName((EPackage) semanticModel, ARCHETYPE_CLASS_NAME);
        assertNotNull(class1);
        DDiagramElement classNode = getFirstDiagramElement(diagram, class1);
        assertNotNull(classNode);

        EClass class2 = (EClass) findElementByName((EPackage) semanticModel, ARCHETYPE_CLASS2_NAME);
        assertNotNull(class2);
        DDiagramElement classNode2 = getFirstDiagramElement(diagram, class2);
        assertNotNull(classNode2);

        assertTrue(applyEdgeCreationTool("Reference", diagram, (EdgeTarget) classNode, (EdgeTarget) classNode2));

        assertNotNull("An edge should be created between this two classes.", findFirstDEdge(diagram));
    }

    /**
     * Test edge creation between 2 existing EClass (with classical mapping).
     */
    public void testCreateEdgeBetween2EClass() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        EClass class1 = (EClass) findElementByName((EPackage) semanticModel, CLASS_NAME);
        assertNotNull(class1);
        DDiagramElement classNode = getFirstDiagramElement(diagram, class1);
        assertNotNull(classNode);

        EClass class2 = (EClass) findElementByName((EPackage) semanticModel, CLASS2_NAME);
        assertNotNull(class2);
        DDiagramElement classNode2 = getFirstDiagramElement(diagram, class2);
        assertNotNull(classNode2);

        assertTrue(applyEdgeCreationTool("Reference", diagram, (EdgeTarget) classNode, (EdgeTarget) classNode2));

        assertNotNull("An edge should be created between this two classes.", findFirstDEdge(diagram));
    }

    /**
     * Test edge creation between an archetyped EClass (with a node mapping
     * import) and an EClass (with classical mapping).
     */
    public void testCreateEdgeBetweenAnArchetypeAndAnEClass() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        EClass class1 = (EClass) findElementByName((EPackage) semanticModel, ARCHETYPE_CLASS_NAME);
        assertNotNull(class1);
        DDiagramElement classNode = getFirstDiagramElement(diagram, class1);
        assertNotNull(classNode);

        EClass class2 = (EClass) findElementByName((EPackage) semanticModel, CLASS_NAME);
        assertNotNull(class2);
        DDiagramElement classNode2 = getFirstDiagramElement(diagram, class2);
        assertNotNull(classNode2);

        assertTrue(applyEdgeCreationTool("Reference", diagram, (EdgeTarget) classNode, (EdgeTarget) classNode2));

        assertNotNull("An edge should be created between this two classes.", findFirstDEdge(diagram));
    }

    private ENamedElement findElementByName(final ENamedElement root, final String name) {
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

    private DEdge findFirstDEdge(final DDiagram diagram) {
        for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
            if (diagramElement instanceof DEdge)
                return (DEdge) diagramElement;
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
