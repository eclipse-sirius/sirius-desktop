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
package org.eclipse.sirius.tests.unit.diagram.action;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramDropTests;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class DeleteFromDiagramActionTests extends EntitiesDiagramDropTests {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
    }

    public void testActionEnablementForDragAndDroppedElements() {
        TestsUtil.synchronizationWithUIThread();

        final EPackage root = (EPackage) semanticModel;
        final EPackage child = root.getESubpackages().iterator().next();

        final DDiagram rootdiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, root).iterator().next();
        final DDiagram childdiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, child).iterator().next();

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain(), "Remove the package from diagram") {
            @Override
            protected void doExecute() {
                rootdiagram.getOwnedDiagramElements().remove(0);
            }
        };
        executeCommand(cmd);

        deactivateLayer(rootdiagram, LAYER_PACKAGE_NAME);
        deactivateLayer(childdiagram, LAYER_PACKAGE_NAME);

        dropSemantic(child.getEClassifier("Child"), rootdiagram, null);

        refresh(rootdiagram);

        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, rootdiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("editor is not of the right type", editor instanceof DiagramDocumentEditor);

        final DiagramEditorBrowser browser = new DiagramEditorBrowser((DiagramDocumentEditor) editor);

        final IGraphicalEditPart childEditPart = browser.findEditPart(new MatchBySemanticElement(child.getEClassifier("Child"))).next();
        final IGraphicalEditPart rootEditPart = browser.findEditPart(new MatchBySemanticElement(root.getEClassifier("Root"))).next();

        final StructuredSelection onlyDroppedIsSelected = new StructuredSelection(childEditPart);
        final StructuredSelection onlyRootIsSelected = new StructuredSelection(rootEditPart);
        final StructuredSelection rootAndDroppedareSelected = new StructuredSelection(Lists.newArrayList(childEditPart, rootEditPart));

        final DeleteFromDiagramAction actionDelegate = new DeleteFromDiagramAction();

        final Action mockAction = new Action() {};

        actionDelegate.selectionChanged(mockAction, onlyDroppedIsSelected);
        assertTrue("As the edit part has been dropped (create = false) then the delete from diagram should be enabled", mockAction.isEnabled());

        actionDelegate.selectionChanged(mockAction, onlyRootIsSelected);
        assertFalse("As the edit part has not been dropped (create = true) then the delete from diagram should be disabled", mockAction.isEnabled());

        actionDelegate.selectionChanged(mockAction, rootAndDroppedareSelected);
        assertFalse("As at least one edit part has not been dropped (create = true) then the delete from diagram should be disabled", mockAction.isEnabled());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test label deletion (delete from diagram) of an edge. Verify bug VP-1027
     * 
     * Test should be reactivated when VP-3084 will be corrected.
     * 
     * 
     * @throws Exception
     */
    public void _testDeleteEdgeLabel() throws Exception {

        EPackage ePackage = (EPackage) semanticModel;
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, ePackage).iterator().next();
        DDiagramEditor editor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        applyNodeCreationTool(TOOL_CREATION_CLASS_NAME, diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        final EReference eReference = Iterables.filter(((EClass) ePackage.getEClassifiers().get(0)).getEStructuralFeatures(), EReference.class).iterator().next();

        final IGraphicalEditPart edge = getEditPart(getFirstDiagramElement(diagram, eReference));
        final IGraphicalEditPart listNamePart = getEdgeNameEditPart(edge);
        assertNotNull("No EdgeNameEditPart instance found", listNamePart);

        deleteFromDiagram(listNamePart);

        IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not deleted", parent.getSourceConnections().size() == 0);

        session.getTransactionalEditingDomain().getCommandStack().undo();

        parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not reinserted", parent.getSourceConnections().size() > 0);

        session.getTransactionalEditingDomain().getCommandStack().redo();

        parent = getEditPart(getFirstDiagramElement(diagram, eClass));

        assertTrue("Edge label was not deleted", parent.getSourceConnections().size() == 0);
        DialectUIManager.INSTANCE.closeEditor(editor, false);
    }

    private IGraphicalEditPart getEdgeNameEditPart(final IGraphicalEditPart parent) {
        for (final Object child : parent.getChildren()) {
            if (child instanceof DEdgeNameEditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        TestsUtil.emptyEventsFromUIThread();
    }

}
