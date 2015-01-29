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
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealAllElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.junit.Assert;

/**
 * 
 * Ensures that hide/reveal, undo/redo and delete actions on diagram elements
 * generate the expected behavior on the Notes related to this elements.
 * 
 * This test concerns one note attached to two packages. Tests are done when
 * Remove/hide note when the annotated element is removed/hidden preference is
 * enabled and disabled.
 * 
 * @author <a href="mailto:loredana.chituc@obeo.fr">Loredana Chituc</a>
 */
public class NoteBehaviorOnHideRevealDeleteManyElementsTest extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/note/tc_VP-2121_many/";

    private static final String SEMANTIC_MODEL_PATH = PATH + "tc_VP-2121.ecore";

    private static final String REPRESENTATION_MODEL_PATH = PATH + "tc_VP-2121.aird";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private static final int NB_GMF_ELEMENTS = 6;

    private EPackage ePackage;

    private HideDDiagramElementAction hideAction;

    private RevealAllElementsAction revealAction;

    private DDiagram diagram;

    private DiagramDocumentEditor diagramEditor;

    private DiagramEditor editor;

    private Diagram gmfDiagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        TestsUtil.emptyEventsFromUIThread();

        hideAction = new HideDDiagramElementAction();
        revealAction = new RevealAllElementsAction();

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        diagramEditor = (DiagramDocumentEditor) editor;
        gmfDiagram = ((Diagram) diagramEditor.getDiagramDocument().getContent());

        ePackage = (EPackage) semanticModel;
    }

    /**
     * Tests that the note attached to two packages is deleted only when the
     * both packages are deleted.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is enabled.
     */
    public void testDeleteElementsWithOneNotePrefTrue() {
        assertEquals("There should be six gmf diagram elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());

        // Step 1: getting the diagram elements and the edit parts corresponding
        // to P1 and P2
        EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        DDiagramElement diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        IGraphicalEditPart p1EditPart = getEditPart(diagramElementP1);

        EPackage eSubPackageP2 = ePackage.getESubpackages().get(1);
        DDiagramElement diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        IGraphicalEditPart p2EditPart = getEditPart(diagramElementP2);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note!", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: deletes only P1
        deleteFromDiagram(p1EditPart);
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that P1 was deleted but not the note because the
        // latter is attached to P1 and P2
        assertNull("The package should be deleted!", p1EditPart.getParent());
        assertEquals("There should be four gmf diagram element!", 4, getNbOfGmfElements());

        // Step 4: ensures that the attached note was not deleted and it is
        // visible
        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 5: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 6: ensures that the package reappeared
        assertEquals("There should be six gmf diagram elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());
        assertTrue("The package should be visible", diagram.getContainers().get(0).isVisible());

        // Step 7: getting the diagram elements and the edit parts corresponding
        // to P1 and P2
        eSubPackageP1 = ePackage.getESubpackages().get(0);
        diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        p1EditPart = getEditPart(diagramElementP1);

        eSubPackageP2 = ePackage.getESubpackages().get(1);
        diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        p2EditPart = getEditPart(diagramElementP2);

        // Step 7.1: deletes P1 and P2
        deleteFromDiagram(p1EditPart);
        TestsUtil.synchronizationWithUIThread();
        deleteFromDiagram(p2EditPart);
        TestsUtil.synchronizationWithUIThread();

        // Step 8: asserts that P1 and P2 were deleted
        assertNull("P1 should be deleted!", p1EditPart.getParent());
        assertNull("P2 should be deleted!", p2EditPart.getParent());

        Assert.assertTrue("the DDiagram should not contains any DDiagramElement", diagram.getDiagramElements().isEmpty());

        Diagram gmfDiagram = ((Diagram) diagramEditor.getDiagramDocument().getContent());

        assertEquals("There should be only one gmf diagram element!", 1, getNbOfGmfElements());

        // Step 9: ensures that the attached note was also deleted
        note = getNote(gmfDiagram.eContents());
        assertNull("The diagram should not own note!", note);

        // Step 10: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 10.1: ensures that P2 and the note reappeared
        assertEquals("There should be four gmf elements!", 4, getNbOfGmfElements());
        assertTrue("P2 should be visible", diagram.getContainers().get(0).isVisible());
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 11: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 11.1: ensures that P1 reappeared and the note is still visible
        assertEquals("There should be six gmf elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());
        assertTrue("P1 should be visible", diagram.getContainers().get(1).isVisible());
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    /**
     * Tests that the note attached to two packages is not deleted when the
     * packages are deleted.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is disabled.
     */

    public void testDeleteElementsWithOneNotePrefFalse() {
        // Step 0: Uncheck the preference
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), false);
        assertEquals("There should be six gmf diagram elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());

        // Step 1: getting the diagram elements and the edit parts corresponding
        // to P1 and P2
        final EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        final DDiagramElement diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        IGraphicalEditPart p1EditPart = getEditPart(diagramElementP1);

        final EPackage eSubPackageP2 = ePackage.getESubpackages().get(1);
        final DDiagramElement diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        IGraphicalEditPart p2EditPart = getEditPart(diagramElementP2);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note!", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: deletes only P1
        deleteFromDiagram(p1EditPart);
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that P1 was deleted
        assertNull("The package should be deleted!", p1EditPart.getParent());
        assertEquals("There should be four gmf diagram element!", 4, getNbOfGmfElements());

        // Step 4: ensures that the attached note was not deleted and it's
        // visible
        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 5: deletes P2
        deleteFromDiagram(p2EditPart);
        TestsUtil.synchronizationWithUIThread();

        // Step 6: asserts that P2 was deleted
        assertNull("P2 should be deleted!", p2EditPart.getParent());
        assertEquals("There should be two gmf diagram element: one related to the diagram and one to the note!", 2, getNbOfGmfElements());

        // Step 7: ensures that the attached note was not deleted
        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note!", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 8: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 8.1: ensures that P2 reappeared
        assertTrue("P2 should be visible", diagram.getContainers().get(0).isVisible());
        assertEquals("There should be four gmf elements!", 4, getNbOfGmfElements());
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 9: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 9.1: ensures that P1 reappeared and the note is still visible
        assertTrue("P1 should be visible !", diagram.getContainers().get(1).isVisible());
        assertEquals("There should be six gmf elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    /**
     * Tests that the note attached to two packages is hidden/revealed only when
     * the both packages are hidden/revealed.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is enabled.
     */
    public void testHideElementsWithOneNotePrefTrue() {
        // Step 0: Check the preference
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);

        EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        DDiagramElement diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        IGraphicalEditPart p1EditPart = getEditPart(diagramElementP1);

        EPackage eSubPackageP2 = ePackage.getESubpackages().get(1);
        DDiagramElement diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        IGraphicalEditPart p2EditPart = getEditPart(diagramElementP2);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note!", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: hiding only P1
        diagramEditor.getDiagramGraphicalViewer().select(p1EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that the element was hidden
        assertFalse("The package P1 should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 4: ensures that the attached note was not hidden because it's
        // also attached to P2
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 5: hiding also P2
        diagramEditor.getDiagramGraphicalViewer().select(p2EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 6: asserts that the element was hidden
        assertFalse("P2 should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 7: ensures that the attached note was also hidden
        note = getNote(gmfDiagram.eContents());
        assertFalse("The note should be hidden!", note.isVisible());

        // Step 8: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 8.1: ensures that P2 and the attached note are revealed
        assertTrue("The package P2 should be visible", diagram.getContainers().get(1).isVisible());
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 9: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 9.1: ensures that P1 is revealed
        assertTrue("The package P1 should be visible", diagram.getContainers().get(0).isVisible());
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 10.1: getting the diagram elements and the edit parts
        // corresponding
        // to P1 and P2
        eSubPackageP1 = ePackage.getESubpackages().get(0);
        diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        p1EditPart = getEditPart(diagramElementP1);

        eSubPackageP2 = ePackage.getESubpackages().get(1);
        diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        p2EditPart = getEditPart(diagramElementP2);

        // Step 10.1: hiding P1 and P2
        diagramEditor.getDiagramGraphicalViewer().select(p1EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();
        diagramEditor.getDiagramGraphicalViewer().select(p2EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 11: asserts that the elements were correctly hidden
        assertFalse("P1 should be hidden!", diagram.getContainers().get(0).isVisible());
        assertFalse("P2 should be hidden!", diagram.getContainers().get(1).isVisible());

        // Step 12: ensures that the attached note was also hidden
        note = getNote(gmfDiagram.eContents());
        assertFalse("The note should be hidden!", note.isVisible());

        // Step 13: reveals P1 and P2
        revealAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 14: asserts that the packages are visible
        assertTrue("The package should be visible!", diagram.getContainers().get(0).isVisible());
        assertTrue("The package should be visible!", diagram.getContainers().get(1).isVisible());

        // Step 15: ensures that the attached note is visible
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    /**
     * Tests that the note attached to two package is not hidden/revealed when
     * the packages are hidden/revealed.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is disabled.
     */
    public void testHideElementsWithOneNotePrefFalse() {
        // Step 0: Uncheck the preference
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), false);

        // Step 1: getting the diagram elements and the edit parts corresponding
        // to P1 and P2
        EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        DDiagramElement diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        IGraphicalEditPart p1EditPart = getEditPart(diagramElementP1);

        EPackage eSubPackageP2 = ePackage.getESubpackages().get(1);
        DDiagramElement diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        IGraphicalEditPart p2EditPart = getEditPart(diagramElementP2);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: hiding only P1
        diagramEditor.getDiagramGraphicalViewer().select(p1EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that the element was hidden
        assertFalse("The package P1 should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 4: ensures that the attached note was not hidden
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be hidden!", note.isVisible());

        // Step 5: hiding also P2
        diagramEditor.getDiagramGraphicalViewer().select(p2EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 6: asserts that the element was hidden
        assertFalse("P2 should be hidden!", diagram.getContainers().get(1).isVisible());

        // Step 7: ensures that the attached note was not hidden
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 8: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 8.1: ensures that P2 and the attached note are revealed
        assertTrue("The package P2 should be visible", diagram.getContainers().get(1).isVisible());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 9: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 9.1: ensures that P1 is revealed
        assertTrue("The package P1 should be visible", diagram.getContainers().get(0).isVisible());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 10: getting the diagram elements and the edit parts
        // corresponding
        // to P1 and P2
        eSubPackageP1 = ePackage.getESubpackages().get(0);
        diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        p1EditPart = getEditPart(diagramElementP1);

        eSubPackageP2 = ePackage.getESubpackages().get(1);
        diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        p2EditPart = getEditPart(diagramElementP2);

        // Step 10.1: hiding P1 and P2
        diagramEditor.getDiagramGraphicalViewer().select(p1EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();
        diagramEditor.getDiagramGraphicalViewer().select(p2EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 11: asserts that the elements were correctly hidden
        assertFalse("P1 should be hidden!", diagram.getContainers().get(0).isVisible());
        assertFalse("P2 should be hidden!", diagram.getContainers().get(1).isVisible());

        // Step 12: ensures that the attached note was not hidden
        assertTrue("The note should be visible!", note.isVisible());

        // Step 13: reveals P1 and P2
        revealAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 14: asserts that the packages are visible
        assertTrue("The package should be visible!", diagram.getContainers().get(0).isVisible());
        assertTrue("The package should be visible!", diagram.getContainers().get(1).isVisible());

        // Step 15: ensures that the attached note is visible
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    /**
     * Tests that the note attached to two packages, P1 & P2, is hidden when:
     * <ul>
     * <li>hide P1;</li>
     * <li>delete P2.</li>
     * </ul>
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is enabled.
     */
    public void testHideNoteOnElementDeletionPrefTrue() {
        // Step 0: Check the preference
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);

        EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        DDiagramElement diagramElementP1 = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP1);
        IGraphicalEditPart p1EditPart = getEditPart(diagramElementP1);

        EPackage eSubPackageP2 = ePackage.getESubpackages().get(1);
        DDiagramElement diagramElementP2 = getFirstDiagramElement(diagram, eSubPackageP2);
        assertNotNull("The package should have a corresponding diagram element", diagramElementP2);
        IGraphicalEditPart p2EditPart = getEditPart(diagramElementP2);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: hiding only P1
        diagramEditor.getDiagramGraphicalViewer().select(p1EditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that the element was hidden
        assertFalse("The package P1 should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 4: ensures that the attached note was not hidden because it's
        // also attached to P2
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should not be hidden!", note.isVisible());

        // Step 5: deletes P2
        deleteFromDiagram(p2EditPart);
        TestsUtil.synchronizationWithUIThread();

        // Step 6: asserts that P2 was deleted
        assertNull("P2 should be deleted!", p2EditPart.getParent());
        assertEquals("There should be the gmf diagram element, P1 and the note attachement!", 4, getNbOfGmfElements());

        // Step 9: ensures that the attached note was not deleted but just
        // hidden
        assertNotNull("The diagram should own a note!", note);
        assertFalse("The note should be hidden!", note.isVisible());

        // Step 10: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 10.1: ensures that P2 and the note reappeared
        assertEquals("There should be six gmf elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());
        assertTrue("P2 should be visible", diagram.getContainers().get(1).isVisible());
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 11: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 11.1: ensures that P1 reappeared and the note is still visible
        assertTrue("P1 should be visible", diagram.getContainers().get(0).isVisible());
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);

    }

    // Returns a note
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

    // Returns the number of the gmf elements related to the diagram, notes,
    // containers and connectors (even when we delete a note the latter is still
    // counted)
    private int getNbOfGmfElements() {
        Diagram gmfDiagram = ((Diagram) diagramEditor.getDiagramDocument().getContent());
        return gmfDiagram.eContents().size();
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
