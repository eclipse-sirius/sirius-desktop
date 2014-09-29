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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

public class ExternalJavaActionTest extends SiriusDiagramTestCase implements ExternalJavaActionModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(EcoreModeler.DESIGN_VIEWPOINT_NAME);
        TestsUtil.synchronizationWithUIThread();
        diagram = (DDiagram) getRepresentations(ENTITIES_REPRESENTATION_NAME).iterator().next();
    }

    @SuppressWarnings("unchecked")
    public void testUniversalToolInvokingJavaAction() throws Exception {
        refresh(diagram);
        TestsUtil.synchronizationWithUIThread();

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        assertTrue(StubJavaAction.CALL_ARGUMENTS.isEmpty());
        assertTrue(applyNodeCreationTool("StubAction", diagram, getFirstDiagramElement(diagram, eClass)));
        assertEquals("The stub action was not invoked", 1, StubJavaAction.CALL_ARGUMENTS.size());
        List<Object> args = (List<Object>) StubJavaAction.CALL_ARGUMENTS.get(0);

        Collection<? extends EObject> selections = (Collection<? extends EObject>) args.get(0);
        assertEquals("The target element was not passed as the selection for the stub action", 1, selections.size());
        EObject selection = selections.iterator().next();
        assertSame("The selection passed was not the right element", eClass, selection);

        Map<String, Object> parameters = (Map<String, Object>) args.get(1);
        assertEquals(2, parameters.size());
        Object eClassParam = parameters.get("eClass");
        Object viewParam = parameters.get("view");
        assertSame("The External Java Action Parameter 'eClass' was not passed properly to the stub action", eClass, eClassParam);
        assertTrue("The External Java Action Parameter 'view' was not passed properly to the stub action", (viewParam instanceof DNodeList) && ((DNodeList) viewParam).getTarget() == eClass);
    }

    public void testDeleteWithSemanticRemoveFromJavaAction() throws Exception {
        assertFalse(getRepresentations(CLASS_REPRESENTATION_NAME).isEmpty());

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        StubDeleteJavaAction.resetCalled();
        assertFalse(StubDeleteJavaAction.hasBeenCalled());
        StubDeleteJavaAction.doASemanticRemove();
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, diagram.getOwnedDiagramElements().size());
        delete(getEditPart(getFirstDiagramElement(diagram, eClass)));
        TestsUtil.synchronizationWithUIThread();

        assertEquals(0, diagram.getOwnedDiagramElements().size());
        assertTrue(StubDeleteJavaAction.hasBeenCalled());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // This work was done by DeleteDiagramElementsTriggerOperation which has
        // been removed, but a similar behavior could be added in the future.
        // assertTrue(getRepresentations(CLASS_REPRESENTATION_NAME).isEmpty());
    }

    public void testDeleteWithSemanticDeleteFromJavaAction() throws Exception {
        assertFalse(getRepresentations(CLASS_REPRESENTATION_NAME).isEmpty());

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        StubDeleteJavaAction.resetCalled();
        assertFalse(StubDeleteJavaAction.hasBeenCalled());
        StubDeleteJavaAction.doASemanticDelete();
        TestsUtil.synchronizationWithUIThread();

        assertEquals(1, diagram.getOwnedDiagramElements().size());
        delete(getEditPart(getFirstDiagramElement(diagram, eClass)));
        TestsUtil.synchronizationWithUIThread();

        assertEquals(0, diagram.getOwnedDiagramElements().size());
        assertTrue(StubDeleteJavaAction.hasBeenCalled());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // This work was done by DeleteDiagramElementsTriggerOperation which has
        // been removed, but a similar behavior could be added in the future.
        // assertTrue(getRepresentations(CLASS_REPRESENTATION_NAME).isEmpty());
    }

    public void testDeleteOtherThanSelectionFromJavaAction() throws Exception {
        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        assertEquals(2, eClass.getEAllAttributes().size());
        final EAttribute eAttribute = eClass.getEAllAttributes().get(0);

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DNodeList list = (DNodeList) diagram.getOwnedDiagramElements().get(0);
        assertEquals(2, list.getOwnedElements().size());

        IGraphicalEditPart originalEditPart = getEditPart(getFirstDiagramElement(diagram, eAttribute));

        delete(originalEditPart);
        TestsUtil.synchronizationWithUIThread();

        assertTrue(StubDeleteOtherThanSelectionJavaAction.hasBeenCalled());

        /* the semantic and the diagram element have been deleted */
        assertEquals(1, eClass.getEAllAttributes().size());

        // This work was done by DeleteDiagramElementsTriggerOperation which has
        // been removed, but a similar behavior could be added in the future.
        // assertEquals(1, list.getOwnedElements().size());

        /*
         * the selected element should not have been deleted or recreated (by
         * canonical edit policy)
         */
        assertSame(originalEditPart, getEditPart(getFirstDiagramElement(diagram, eAttribute)));

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test the java external action and action call with precondition. Also
     * test that the variable $views is not accessible in the precondition
     * 
     * @throws Exception
     */
    public void testJavaActionWithPrecondition() throws Exception {
        refresh(diagram);
        TestsUtil.synchronizationWithUIThread();

        final EPackage ePackage = (EPackage) semanticModel;
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        assertTrue(applyNodeCreationTool("StubAction", diagram, getFirstDiagramElement(diagram, eClass)));
        assertEquals("The stub action was not invoked", 1, StubJavaAction.CALL_ARGUMENTS.size());
        TestsUtil.synchronizationWithUIThread();

        // with the precondition, the action is not called, the number of
        // invokes stay the same
        assertTrue(applyNodeCreationTool("StubActionWithPrecondition", diagram, getFirstDiagramElement(diagram, eClass)));
        assertEquals("The stub action was not invoked", 1, StubJavaAction.CALL_ARGUMENTS.size());
        TestsUtil.synchronizationWithUIThread();

        assertTrue(applyNodeCreationTool("CallStubAction", diagram, getFirstDiagramElement(diagram, eClass)));
        assertEquals("The stub action was not invoked", 2, StubJavaAction.CALL_ARGUMENTS.size());
        TestsUtil.synchronizationWithUIThread();

        // with the precondition, the action is not called, the number of
        // invokes stay the same
        assertTrue(applyNodeCreationTool("CallStubActionWithPrecondition", diagram, getFirstDiagramElement(diagram, eClass)));
        assertEquals("The stub action was not invoked", 2, StubJavaAction.CALL_ARGUMENTS.size());
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {

        diagram = null;
        StubJavaAction.CALL_ARGUMENTS.clear();
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
