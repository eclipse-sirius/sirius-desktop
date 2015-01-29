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
package org.eclipse.sirius.tests.unit.perf.scenario.modeler.ecore;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Layers performance tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramOpenPerfsTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagramFor100;

    private DDiagram diagramFor200;

    private DDiagram diagramFor400;

    private IEditorPart editorFor100;

    private IEditorPart editorFor200;

    private IEditorPart editorFor400;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

        TestsUtil.emptyEventsFromUIThread();

        EPackage ePackage = (EPackage) semanticModel;

        diagramFor100 = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        createClassesAndAnnotations(ePackage, 100);
        refresh(diagramFor100);
        TestsUtil.synchronizationWithUIThread();

        final DiagramDescription desc = diagramFor100.getDescription();

        createClassesAndAnnotations(ePackage, 200);
        diagramFor200 = createDDiagram(desc, "test for 200");
        refresh(diagramFor200);
        TestsUtil.synchronizationWithUIThread();

        createClassesAndAnnotations(ePackage, 400);
        diagramFor400 = createDDiagram(desc, "test for 400");
        refresh(diagramFor400);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOpen() throws Exception {
        editorFor100 = DialectUIManager.INSTANCE.openEditor(session, diagramFor100, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editorFor100, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOpenX2() throws Exception {
        editorFor200 = DialectUIManager.INSTANCE.openEditor(session, diagramFor200, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editorFor200, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOpenX4() throws Exception {
        editorFor400 = DialectUIManager.INSTANCE.openEditor(session, diagramFor400, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editorFor400, false);
        TestsUtil.synchronizationWithUIThread();
    }

    private void createClassesAndAnnotations(final EPackage ePackage, final int numberOfClass) {

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                for (int i = 0; i < numberOfClass; i++) {
                    final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
                    eClass.setName("Class " + i);
                    ePackage.getEClassifiers().add(eClass);
                    final EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
                    annotation.setSource("http://www.eclipse.org/emf/2002/GenModel");
                    annotation.getDetails().put("documentation", "doc contents");
                    eClass.getEAnnotations().add(annotation);
                }
            }

        };

        executeCommand(cmd);
    }

    private DDiagram createDDiagram(final DiagramDescription description, final String name) {
        CreateRepresentationCommand command = new CreateRepresentationCommand(session, description, semanticModel, name, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        return (DDiagram) command.getCreatedRepresentation();
    }
}
