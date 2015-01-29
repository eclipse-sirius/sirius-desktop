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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.quality;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Layers tests for Relations diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class RelationsDiagramLayersTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    private EClass eClass;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(QUALITY_VIEWPOINT_NAME);

        final EPackage ePackage = (EPackage) semanticModel;
        eClass = EcoreFactory.eINSTANCE.createEClass();
        executeCommand(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                ePackage.getEClassifiers().add(eClass);
            }
        });
        diagram = (DDiagram) createRepresentation(RELATIONS_DESC_NAME, eClass);
        assertNotNull(diagram);
    }

    public void testDefaultLayerVisibility() {

        final EPackage ePackage = (EPackage) semanticModel;

        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                final EClass myClass = EcoreFactory.eINSTANCE.createEClass();
                ePackage.getEClassifiers().add(myClass);
            }

        };

        executeCommand(cmd);
        refresh(diagram);
        /* we should not see other class the class on the diagram */
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());
        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, eClass)));
    }

    public void testUsedbyLayerVisibility() {

        final EPackage ePackage = (EPackage) semanticModel;

        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                final EClass myClass = EcoreFactory.eINSTANCE.createEClass();
                final EReference eRef = EcoreFactory.eINSTANCE.createEReference();
                eRef.setEType(eClass);
                myClass.getEStructuralFeatures().add(eRef);
                final EPackage eSubPackage = EcoreFactory.eINSTANCE.createEPackage();
                eSubPackage.getEClassifiers().add(myClass);
                ePackage.getESubpackages().add(eSubPackage);

                final EClass inheritClass = EcoreFactory.eINSTANCE.createEClass();
                inheritClass.getESuperTypes().add(eClass);
                final EPackage eSubPackage2 = EcoreFactory.eINSTANCE.createEPackage();
                eSubPackage2.getEClassifiers().add(inheritClass);
                ePackage.getESubpackages().add(eSubPackage2);

            }

        };

        executeCommand(cmd);

        refresh(diagram);
        /* we should not see other class the class on the diagram */
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());
        assertTrue(activateLayer(diagram, "Used by"));
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 1 + 2 /*
                                                                                    * 2
                                                                                    * classes
                                                                                    */+ 2 /*
                                                                                           * 2
                                                                                           * edges
                                                                                           */, diagram.getOwnedDiagramElements().size());
        assertEquals("The diagram do not contain the right number of edges", 2, diagram.getEdges().size());

        final EClass referencerClass = (EClass) ePackage.getESubpackages().get(0).getEClassifiers().get(0);
        final EClass inheritClass = (EClass) ePackage.getESubpackages().get(1).getEClassifiers().get(0);

        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, referencerClass)));
        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, inheritClass)));

        assertTrue(deactivateLayer(diagram, "Used by"));

        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, referencerClass)));
        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, inheritClass)));
    }

    public void testUsesLayerVisibility() {
        final EPackage ePackage = (EPackage) semanticModel;

        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                final EClass myClass = EcoreFactory.eINSTANCE.createEClass();
                final EReference eRef = EcoreFactory.eINSTANCE.createEReference();
                eRef.setEType(myClass);
                eClass.getEStructuralFeatures().add(eRef);
                final EPackage eSubPackage = EcoreFactory.eINSTANCE.createEPackage();
                eSubPackage.getEClassifiers().add(myClass);
                ePackage.getESubpackages().add(eSubPackage);

                final EClass inheritClass = EcoreFactory.eINSTANCE.createEClass();
                eClass.getESuperTypes().add(inheritClass);
                final EPackage eSubPackage2 = EcoreFactory.eINSTANCE.createEPackage();
                eSubPackage2.getEClassifiers().add(inheritClass);
                ePackage.getESubpackages().add(eSubPackage2);

            }

        };

        executeCommand(cmd);

        refresh(diagram);
        /* we should not see other class the class on the diagram */
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());
        assertTrue(activateLayer(diagram, "Uses"));
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 1 + 2 /*
                                                                                    * 2
                                                                                    * classes
                                                                                    */+ 2 /*
                                                                                           * 2
                                                                                           * edges
                                                                                           */, diagram.getOwnedDiagramElements().size());
        assertEquals("The diagram do not contain the right number of edges", 2, diagram.getEdges().size());

        final EClass referencedClass = (EClass) ePackage.getESubpackages().get(0).getEClassifiers().get(0);
        final EClass superClass = (EClass) ePackage.getESubpackages().get(1).getEClassifiers().get(0);

        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, referencedClass)));
        assertTrue(isVisible(diagram, getFirstDiagramElement(diagram, superClass)));

        assertTrue(deactivateLayer(diagram, "Uses"));

        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, referencedClass)));
        assertFalse(isVisible(diagram, getFirstDiagramElement(diagram, superClass)));

    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        eClass = null;
        super.tearDown();
    }
}
