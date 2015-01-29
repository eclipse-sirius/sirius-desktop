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
 * Refresh tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramRefreshTests extends SiriusDiagramTestCase implements EcoreModeler {

    protected static final int NUMBER_OF_CLASS = 100;

    protected static final int NUMBER_OF_SUBPACKAGE = 100;

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    public void testDefaultLayerRefresh() {
        defaultLayerRefreshWith(NUMBER_OF_CLASS);
    }

    protected void defaultLayerRefreshWith(final int numberOfClass) {

        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                for (int i = 0; i < numberOfClass; i++) {
                    final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                    eClass.setName("Class " + i);
                    ePackage.getEClassifiers().add(eClass);
                }
            }

        };

        executeCommand(cmd);
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", numberOfClass, diagram.getOwnedDiagramElements().size());

        cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {

                final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
                eReference.setName("reference");
                eReference.setEType((EClass) diagram.getOwnedDiagramElements().get(0).getTarget());
                ((EClass) diagram.getOwnedDiagramElements().get(1).getTarget()).getEStructuralFeatures().add(eReference);

                final EReference eComposition = EcoreFactory.eINSTANCE.createEReference();
                eComposition.setName("composition");
                eComposition.setContainment(true);
                eComposition.setEType((EClass) diagram.getOwnedDiagramElements().get(2).getTarget());
                ((EClass) diagram.getOwnedDiagramElements().get(3).getTarget()).getEStructuralFeatures().add(eComposition);
            }

        };

        executeCommand(cmd);
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of elements", numberOfClass + 2, diagram.getOwnedDiagramElements().size());
        assertEquals("The diagram do not contain the right number of edges", 2, diagram.getEdges().size());
    }

    public void testPackageLayerRefresh() {
        packageLayerRefresh(NUMBER_OF_SUBPACKAGE);
    }

    protected void packageLayerRefresh(final int numberOfPackage) {

        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                for (int i = 0; i < numberOfPackage; i++) {
                    EPackage eSubPackage = EcoreFactory.eINSTANCE.createEPackage();
                    eSubPackage.setName("Subpackage " + i);
                    ePackage.getESubpackages().add(eSubPackage);
                }
            }

        };

        executeCommand(cmd);
        activateLayer(diagram, EcoreModeler.LAYER_PACKAGE_NAME);
        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", numberOfPackage, diagram.getOwnedDiagramElements().size());

    }

    @Override
    protected void tearDown() throws Exception {

        diagram = null;

        super.tearDown();
    }
}
