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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.documentation;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

public class EntitiesDiagramTooltipsRefreshTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    public void testTooltipsComputedOnNewElements() {
        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        final EClass[] classHolder = new EClass[1];
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                eClass.setName("MyClass");
                ePackage.getEClassifiers().add(eClass);
                classHolder[0] = eClass;
            }
        };
        executeCommand(cmd);
        refresh(diagram);
        DDiagramElement diagElement = getDiagramElementsFromLabel(diagram, "MyClass").get(0);
        assertEquals(ePackage.getName() + "." + classHolder[0].getName(), diagElement.getTooltipText());

        final EOperation[] operationHolder = new EOperation[1];
        cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                final EOperation op = EcoreFactory.eINSTANCE.createEOperation();
                op.setName("theOperation");
                op.setEType(EcorePackage.eINSTANCE.getEBoolean());
                classHolder[0].getEOperations().add(op);
                operationHolder[0] = op;
            }
        };
        executeCommand(cmd);
        refresh(diagram);
        diagElement = getDiagramElementsFromLabel(diagram, operationHolder[0].getName()).get(0);
        assertEquals(operationHolder[0].getName() + "() : " + operationHolder[0].getEType().getName(), diagElement.getTooltipText());
    }

    public void testTooltipsRefreshedOnSemanticChanges() {
        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        // 1. Create a new EClass
        final EClass[] classHolder = new EClass[1];
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                eClass.setName("MyClass");
                ePackage.getEClassifiers().add(eClass);
                classHolder[0] = eClass;
            }
        };
        executeCommand(cmd);
        refresh(diagram);

        // 2. Check its initial tooltip text
        DDiagramElement diagElement = getDiagramElementsFromLabel(diagram, classHolder[0].getName()).get(0);
        assertEquals(ePackage.getName() + "." + classHolder[0].getName(), diagElement.getTooltipText());

        // 3. Changes the EClass's name
        cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                classHolder[0].setName("NewName");
            }
        };
        executeCommand(cmd);
        refresh(diagram);

        // 4. Check that the tooltip has been refreshed
        diagElement = getDiagramElementsFromLabel(diagram, classHolder[0].getName()).get(0);
        assertEquals(ePackage.getName() + "." + classHolder[0].getName(), diagElement.getTooltipText());
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }
}
