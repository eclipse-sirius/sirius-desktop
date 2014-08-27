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
 * Tools tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class DependenciesDiagramRefreshTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final int NUMBER_OF_PACKAGE = 100;

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(QUALITY_VIEWPOINT_NAME);
        diagram = (DDiagram) createRepresentation(DEPENDENCIES_DESC_NAME);
    }
    
    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

    public void testDefaultLayerRefresh() {

        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                for (int i = 0; i < NUMBER_OF_PACKAGE; i++) {
                    final EPackage eSubPackage = EcoreFactory.eINSTANCE.createEPackage();
                    eSubPackage.setName("Package " + i);
                    ePackage.getESubpackages().add(eSubPackage);
                }
            }

        };

        executeCommand(cmd);
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", NUMBER_OF_PACKAGE + 1, diagram.getOwnedDiagramElements().size());

        cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {

                final EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();

                final EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();

                final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
                eReference.setName("reference");
                eReference.setEType(eClass2);
                eClass1.getEStructuralFeatures().add(eReference);

                final EPackage ePackage1 = (EPackage) diagram.getOwnedDiagramElements().get(0).getTarget();
                ePackage1.getEClassifiers().add(eClass1);
                final EPackage ePackage2 = (EPackage) diagram.getOwnedDiagramElements().get(1).getTarget();
                ePackage2.getEClassifiers().add(eClass2);
            }

        };

        assertTrue(executeCommand(cmd));
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of edges", 1, diagram.getEdges().size());
        assertEquals("The diagram do not contain the right number of elements", NUMBER_OF_PACKAGE + 1 + 1, diagram.getOwnedDiagramElements().size());
    }

    public void testContentLayerRefresh() {

        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 1, diagram.getOwnedDiagramElements().size());

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                for (int i = 0; i < NUMBER_OF_PACKAGE; i++) {
                    final EPackage eSubPackage = EcoreFactory.eINSTANCE.createEPackage();
                    eSubPackage.setName("Package " + i);
                    ePackage.getESubpackages().add(eSubPackage);
                    final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                    eClass.setName("class " + i);
                    eSubPackage.getEClassifiers().add(eClass);
                }
            }

        };

        executeCommand(cmd);
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", NUMBER_OF_PACKAGE + 1, diagram.getOwnedDiagramElements().size());

        assertTrue(activateLayer(diagram, "Content"));
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", NUMBER_OF_PACKAGE * 2 + 1, diagram.getDiagramElements().size());

    }

}
