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
package org.eclipse.sirius.tests.unit.api.diagramintegrity;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Tests creation command based on entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class RetrieveEditPartFromSemanticElementTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    private IEditorPart editor;

    private EClass eClass;

    private EClass eClass2;

    private IDiagramDialectGraphicalViewer viewer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        final EPackage ePackage = (EPackage) semanticModel;
        eClass = createEClass(ePackage);
        eClass2 = createEClass(ePackage);
        createConnection();
        refresh(diagram);
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        viewer = (IDiagramDialectGraphicalViewer) editor.getAdapter(GraphicalViewer.class);
    }

    public void testRegister() {
        final EditPart testPart = new AbstractGraphicalEditPart() {
            @Override
            protected IFigure createFigure() {
                return null;
            }

            @Override
            protected void createEditPolicies() {
            }
        };
        List<EditPart> partsBefore = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertFalse(partsBefore.contains(testPart));

        viewer.registerEditPartForSemanticElement(eClass, testPart);

        List<EditPart> partsAfter = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertTrue(partsAfter.contains(testPart));
    }

    public void testUnregister() {

        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        final IGraphicalEditPart editPart = getEditPart(diagramElement);

        List<EditPart> partsBefore = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertTrue(partsBefore.contains(editPart));

        viewer.unregisterEditPartForSemanticElement(eClass, editPart);

        List<EditPart> partsAfter = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertFalse(partsAfter.contains(editPart));
    }

    public void testUnsynchronizedDeleteAndUnregister() {

        // Step 1 : Unsynchronized the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                diagram.setSynchronized(false);
            }
        });

        // Step 2 : Delete the edge on eClass
        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        final IGraphicalEditPart editPart = getEditPart(diagramElement);

        List<EditPart> partsBefore = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertTrue(partsBefore.contains(editPart));

        deleteFromDiagram(editPart);

        List<EditPart> partsAfter = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertFalse(partsAfter.contains(editPart));

        // Step 3 : delete the the list part
        final DDiagramElement dde2 = getFirstDiagramElement(diagram, eClass);
        assertFalse("The inital test data should have two lists and an edge.", diagramElement == dde2);
        final IGraphicalEditPart ep2 = getEditPart(dde2);
        partsBefore = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertTrue(partsBefore.contains(ep2));

        deleteFromDiagram(ep2);

        partsAfter = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertFalse(partsAfter.contains(ep2));
        assertTrue(partsAfter.isEmpty());
    }

    public void testDeleteAndUnregister() {
        // Step 1 : Delete the edge on eClass
        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        final IGraphicalEditPart editPart = getEditPart(diagramElement);

        List<EditPart> partsBefore = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertTrue(partsBefore.contains(editPart));

        delete(editPart);

        List<EditPart> partsAfter = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertFalse(partsAfter.contains(editPart));

        // Step2 : delete the the list part
        final DDiagramElement dde2 = getFirstDiagramElement(diagram, eClass);
        assertFalse("The inital test data should have two lists and an edge.", diagramElement == dde2);
        final IGraphicalEditPart ep2 = getEditPart(dde2);
        partsBefore = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertTrue(partsBefore.contains(ep2));

        delete(ep2);

        partsAfter = viewer.findEditPartsForElement(eClass, EditPart.class);
        assertFalse(partsAfter.contains(ep2));
        assertTrue(partsAfter.isEmpty());
    }

    public void testFind() {
        List<EditPart> parts = viewer.findEditPartsForElement(eClass, EditPart.class);

        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eClass);
        final IGraphicalEditPart editPart = getEditPart(diagramElement);

        assertTrue(parts.contains(editPart));

        final DDiagramElement diagramElement2 = getFirstDiagramElement(diagram, eClass2);
        final IGraphicalEditPart editPart2 = getEditPart(diagramElement2);

        assertFalse(parts.contains(editPart2));

        List<ConnectionEditPart> partsForEdge = viewer.findEditPartsForElement(eClass, ConnectionEditPart.class);
        final DEdge dEdge = getFirstEdgeElement(diagram, eClass);
        final IGraphicalEditPart edgePart = getEditPart(dEdge);
        assertTrue(partsForEdge.contains(edgePart));
    }

    private EClass createEClass(final EPackage ePackage) {
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                ePackage.getEClassifiers().add(eClass);
            }
        });
        return eClass;
    }

    private void createConnection() {

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                eClass.getESuperTypes().add(eClass2);
            }
        });
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = null;
        editor = null;
        eClass = null;
        eClass2 = null;
        viewer = null;

        super.tearDown();
    }

}
