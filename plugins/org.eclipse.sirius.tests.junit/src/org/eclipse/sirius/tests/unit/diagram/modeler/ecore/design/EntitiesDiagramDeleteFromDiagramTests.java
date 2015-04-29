/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.business.internal.command.ViewDeleteCommand;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ChangeSynchronizedDagramStatusCommand;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Check enablement of delete from diagram button for Entities diagram of ecore
 * modeler.
 * 
 * @author cnotot
 */
public class EntitiesDiagramDeleteFromDiagramTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        TestsUtil.emptyEventsFromUIThread();
    }

    public void testActionEnablementForActionBarButton() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        applyNodeCreationTool("Class", diagram, diagram);
        final EClass source = (EClass) ePackage.getEClassifiers().get(0);
        applyNodeCreationTool("Class", diagram, diagram);
        final EClass target = (EClass) ePackage.getEClassifiers().get(1);

        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        final EReference eReference = source.getEReferences().get(0);

        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editorPart;

        // Disable diagram synchronization
        final DDiagram diagram = (DDiagram) ((DDiagramEditor) diagramEditor).getRepresentation();
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram);
        editingDomain.getCommandStack().execute(new ChangeSynchronizedDagramStatusCommand(editingDomain, diagram));

        diagramEditor.getDiagramGraphicalViewer().deselectAll();
        TestsUtil.synchronizationWithUIThread();
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(getFirstDiagramElement(diagram, source)));
        TestsUtil.synchronizationWithUIThread();
        IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        if (!prefs.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name())) {
            /*
             * DeleteFromDiagramAction has been removed from the old-ui mode.
             * See Bug: 465836
             */
            assertTrue("The action should be enabled", isDeleteFromDiagramActionEnabled(diagramEditor));

            diagramEditor.getDiagramGraphicalViewer().deselectAll();
            TestsUtil.synchronizationWithUIThread();

            diagramEditor.getDiagramGraphicalViewer().select(getEditPart(getFirstDiagramElement(diagram, eReference)));
            TestsUtil.synchronizationWithUIThread();

            assertFalse("The action should be disabled", isDeleteFromDiagramActionEnabled(diagramEditor));
            DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        }
    }

    public void testDeleteNoteFromDiagram() {

        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editorPart;

        createNote(diagram, "");

        Diagram gmfDiagram = ((Diagram) diagramEditor.getDiagramDocument().getContent());

        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);

        deleteFromDiagram(getEditPart(note, diagramEditor));

        note = getNote(gmfDiagram.eContents());
        assertNull("The diagram should not own note", note);

        // Call Ctrl+Z
        diagramEditor.getDiagramEditDomain().getDiagramCommandStack().undo();
        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    public void testDeleteNoteFromModel() {

        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editorPart;

        createNote(diagram, "");

        Diagram gmfDiagram = ((Diagram) diagramEditor.getDiagramDocument().getContent());

        Node note = getNote(gmfDiagram.eContents());

        assertNotNull("The diagram should own a note", note);

        final EditPart editPart = getEditPart(note, diagramEditor);

        diagramEditor.getDiagramGraphicalViewer().select(editPart);
        TestsUtil.synchronizationWithUIThread();

        final DeleteCommand cmd = new ViewDeleteCommand(session.getTransactionalEditingDomain(), note);
        final CompositeCommand compoundCommand = new CompositeCommand(cmd.getLabel());
        compoundCommand.add(cmd);

        try {
            compoundCommand.execute(new NullProgressMonitor(), null);
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TestsUtil.synchronizationWithUIThread();

        note = getNote(gmfDiagram.eContents());
        assertNull("The diagram should not own note", note);

        // Call Ctrl+Z
        try {
            compoundCommand.undo(new NullProgressMonitor(), null);
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TestsUtil.synchronizationWithUIThread();

        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    private EditPart getEditPart(View gmfView, DiagramEditor diagramEditor) {
        EditPart result = null;
        if (gmfView != null) {
            final Map<?, ?> editPartRegistry = diagramEditor.getDiagramGraphicalViewer().getEditPartRegistry();
            final Object editPart = editPartRegistry.get(gmfView);
            if (editPart instanceof IGraphicalEditPart) {
                result = (IGraphicalEditPart) editPart;
            }
        }
        return result;
    }

    private Node getNote(EList<EObject> elements) {
        Iterator<EObject> it = elements.iterator();
        while (it.hasNext()) {
            EObject obj = it.next();
            if (obj instanceof Node && GMFNotationHelper.isNote((Node) obj)) {
                return (Node) obj;
            }
        }
        return null;
    }

    private boolean isDeleteFromDiagramActionEnabled(final DiagramDocumentEditor diagramEditor) {

        IContributionItem[] items;

        IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        /* preference name is internal, so access it in a standard way */
        if (prefs.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name())) {
            items = diagramEditor.getEditorSite().getActionBars().getToolBarManager().getItems();
        } else {
            items = ((DDiagramEditor) diagramEditor).getTabBarManager().getItems();
        }

        IAction foundAction = null;

        for (int i = 0; i < items.length && foundAction == null; i++) {
            if (items[i] instanceof ActionContributionItem && ((ActionContributionItem) items[i]).getAction() instanceof DeleteFromDiagramAction) {
                foundAction = ((ActionContributionItem) items[i]).getAction();
            }
        }

        assertNotNull("Action of type " + DeleteFromDiagramAction.class + " not found", foundAction);

        return foundAction.isEnabled();
    }

    @Override
    protected void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
