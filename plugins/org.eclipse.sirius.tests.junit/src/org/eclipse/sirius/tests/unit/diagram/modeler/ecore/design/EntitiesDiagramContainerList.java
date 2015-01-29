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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * tests for the order of a list in a container in an ecore modeler. #862
 * 
 * @author cnotot
 */
public class EntitiesDiagramContainerList extends SiriusDiagramTestCase implements EcoreModeler {

    /**
     * NUMBER_OF_ATTRIBUTES. Has to be superior or equal to 2.
     */
    protected static final int NUMBER_OF_ATTRIBUTES = 3;

    private DDiagram diagram;

    /**
     * (non-Javadoc).
     * 
     * @see junit.framework.TestCase#setUp()
     * @throws Exception
     *             on error.
     */
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    /**
     * testInsertNewElement.
     */
    public void testInsertNewElement() {

        EClass eClass = initCommon();

        insertNewAttribute(eClass);

        assertEquals("The diagram does not contain the right number of attributes", NUMBER_OF_ATTRIBUTES + 1, getListNodes(eClass).size());

        List<EObject> semanticList = getSemanticList(getListNodes(eClass));

        // One refresh is supposed to be enough to have the list element order
        // of the graphical container matching the order of the list returned by the semantic candidate expression.
        assertTrue("The list in the graphical container is not the same than the one of the semantic model.", eClass.getEStructuralFeatures().equals(semanticList));

        /*
         * Let's refresh again and make sure everything is still in order.
         */
        refresh(diagram);

        semanticList = getSemanticList(getListNodes(eClass));

        assertTrue("The list in the graphical container is not the same than the one of the semantic model.", eClass.getEStructuralFeatures().equals(semanticList));

    }

    /**
     * testMoveElement.
     */
    public void testMoveElement() {

        EClass eClass = initCommon();

        moveAttribute(eClass);

        assertEquals("The diagram does not contain the right number of attributes", NUMBER_OF_ATTRIBUTES, getListNodes(eClass).size());

        List<EObject> semanticList = getSemanticList(getListNodes(eClass));

        assertTrue("The list in the graphical container is not the same than the one of the semantic model.", eClass.getEStructuralFeatures().equals(semanticList));

    }

    private EClass initCommon() {
        final EPackage ePackage = (EPackage) semanticModel;

        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        initSemanticModel(ePackage);

        EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        List<DNodeListElement> listNodes = getListNodes(eClass);

        assertEquals("The diagram do not contain the right number of attributes", NUMBER_OF_ATTRIBUTES, listNodes.size());

        return eClass;
    }

    private List<DNodeListElement> getListNodes(EClass eClass) {
        DDiagramElement graphicalEClass = getFirstDiagramElement(diagram, eClass);
        assertTrue("The diagram element is not a container", graphicalEClass instanceof DNodeList);
        DNodeList container = (DNodeList) graphicalEClass;
        return container.getOwnedElements();
    }

    private void insertNewAttribute(final EClass eClass) {
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {

                final EAttribute eAtt = EcoreFactory.eINSTANCE.createEAttribute();
                eAtt.setName("newAtt");

                eClass.getEStructuralFeatures().add(NUMBER_OF_ATTRIBUTES - 1, eAtt);

            }

        };

        executeCommand(cmd);
        refresh(diagram);
    }

    private void moveAttribute(final EClass eClass) {
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {

                eClass.getEStructuralFeatures().move(NUMBER_OF_ATTRIBUTES - 2, NUMBER_OF_ATTRIBUTES - 1);

            }

        };

        executeCommand(cmd);
        refresh(diagram);
    }

    private void initSemanticModel(final EPackage ePackage) {
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {

                final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                eClass.setName("Class");
                ePackage.getEClassifiers().add(eClass);

                for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++) {
                    final EAttribute eAtt = EcoreFactory.eINSTANCE.createEAttribute();
                    eAtt.setName("Att" + i);
                    eClass.getEStructuralFeatures().add(eAtt);
                }

            }

        };

        executeCommand(cmd);
        refresh(diagram);
    }

    private List<EObject> getSemanticList(List<?> diagramElements) {
        List<EObject> semanticList = new ArrayList<EObject>();
        for (int i = 0; i < diagramElements.size(); i++) {
            DDiagramElement node = (DDiagramElement) diagramElements.get(i);
            EObject obj = node.getTarget();
            semanticList.add(obj);
        }
        return semanticList;
    }
}
