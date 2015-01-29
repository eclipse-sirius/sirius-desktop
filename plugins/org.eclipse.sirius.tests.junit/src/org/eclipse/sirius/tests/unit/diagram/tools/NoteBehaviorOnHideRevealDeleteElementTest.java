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

/**
 * 
 * Ensures that hide/reveal, undo/redo and delete actions on diagram elements
 * generate the expected behavior on the Notes related to this elements.
 * 
 * This test concerns one note attached to one package. Tests are done when
 * Remove/hide note when the annotated element is removed/hidden preference is
 * enabled and disabled.
 * 
 * @author <a href="mailto:loredana.chituc@obeo.fr">Loredana Chituc</a>
 */
public class NoteBehaviorOnHideRevealDeleteElementTest extends SiriusDiagramTestCase implements EcoreModeler {
    private static final String PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/note/tc_VP-2121/";

    private static final String SEMANTIC_MODEL_PATH = PATH + "tc_VP-2121.ecore";

    private static final String REPRESENTATION_MODEL_PATH = PATH + "tc_VP-2121.aird";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private static final int NB_GMF_ELEMENTS = 4;

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

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        // Action creations
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
     * Tests that the note attached to a package is also deleted when the
     * package is deleted.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is enabled.
     * 
     */
    public void testDeleteElementWithOneNotePrefTrue() {
        assertEquals("There should be four gmf elements: the diagram gmf element, the package, the note and the note attachement!", NB_GMF_ELEMENTS, getNbOfGmfElements());

        // Step 1: getting the diagram element and the edit part corresponding
        // to P1
        final EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElement);
        IGraphicalEditPart elementEditPart = getEditPart(diagramElement);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note!", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: deletes P1
        deleteFromDiagram(elementEditPart);
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that the element is deleted
        assertNull("The package should be deleted!", elementEditPart.getParent());
        assertEquals("There should be only the gmf diagram element!", 1, getNbOfGmfElements());

        // Step 4: ensures that the attached note is deleted
        note = getNote(gmfDiagram.eContents());
        assertNull("The diagram should not own note!", note);

        // Step 5: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 6: ensures that the package and the note reappeared
        assertEquals("There should be four gmf elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());
        assertTrue("The package should be visible!", diagram.getContainers().get(0).isVisible());
        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note!", note);
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    /**
     * Tests that the note attached to a package is not deleted when the package
     * is deleted.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is disabled.
     */

    public void testDeleteElementWithOneNotePrefFalse() {
        // Step 0: Uncheck the preference
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), false);
        assertEquals("There should be only four diagram element!", NB_GMF_ELEMENTS, getNbOfGmfElements());

        // Step 1: getting the diagram element and the edit part corresponding
        // to P1
        final EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        final DDiagramElement diagramElement = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element", diagramElement);
        IGraphicalEditPart elementEditPart = getEditPart(diagramElement);

        // Step 1.1 : asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note!", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: deletes P1
        deleteFromDiagram(elementEditPart);
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that the element is deleted
        assertNull("The package should be deleted!", elementEditPart.getParent());
        assertEquals("There should be only the gmf diagram element and the note!", 2, getNbOfGmfElements());

        // Step 4: ensures that the attached note was not deleted and it is
        // visible
        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should not own note", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 4: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 6 : ensures that the package reappeared
        assertEquals("There should be four elements!", NB_GMF_ELEMENTS, getNbOfGmfElements());
        assertTrue("The package should be visible", diagram.getContainers().get(0).isVisible());
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    /**
     * Tests that the note attached to a package is also hidden/revealed when
     * the package is hidden/revealed.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is enabled.
     */
    public void testHideElementWithOneNotePrefTrue() {
        // Step 0: Check the preference
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), true);

        // Step 1: getting the diagram element and the edit part corresponding
        // to P1
        EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element!", diagramElement);
        IGraphicalEditPart elementEditPart = getEditPart(diagramElement);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: hiding P1
        diagramEditor.getDiagramGraphicalViewer().select(elementEditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that the element is hidden
        assertFalse("The package should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 4: ensures that the attached note is also hidden
        note = getNote(gmfDiagram.eContents());
        assertFalse("The note should be hidden!", note.isVisible());

        // Step 5: reveals P1
        revealAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 6: asserts that the element is revealed
        assertTrue("The package should be visible!", diagram.getContainers().get(0).isVisible());

        // Step 7: ensures that the attached note is revealed
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 8: hiding P1
        eSubPackageP1 = ePackage.getESubpackages().get(0);
        diagramElement = getFirstDiagramElement(diagram, eSubPackageP1);
        elementEditPart = getEditPart(diagramElement);
        diagramEditor.getDiagramGraphicalViewer().select(elementEditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 9: asserts that the element is hidden
        assertFalse("The package should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 10: ensures that the attached note is hidden
        note = getNote(gmfDiagram.eContents());
        assertFalse("The note should be hidden!", note.isVisible());

        // Step 11: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 12: ensures that the package and the attached note are revealed
        assertTrue("The package should be visible", diagram.getContainers().get(0).isVisible());
        note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        assertTrue("The note should be visible!", note.isVisible());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    /**
     * Tests that the note attached to a package is not hidden/revealed when the
     * package is hidden/revealed.
     * 
     * The Remove/hide note when the annotated element is removed/hidden
     * preference is disabled.
     * 
     */
    public void testHideElementWithOneNotePrefFalse() {
        // Step 0: Uncheck the preference
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name(), false);

        EPackage eSubPackageP1 = ePackage.getESubpackages().get(0);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, eSubPackageP1);
        assertNotNull("The package should have a corresponding diagram element!", diagramElement);
        IGraphicalEditPart elementEditPart = getEditPart(diagramElement);

        // Step 1.1: asserts that there is a note and it is visible
        Node note = getNote(gmfDiagram.eContents());
        assertNotNull("The diagram should own a note", note);
        assertTrue("The note should be visible!", note.isVisible());

        // Step 2: hiding P1
        diagramEditor.getDiagramGraphicalViewer().select(elementEditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3: asserts that the element is hidden
        assertFalse("The package should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 4: ensures that the attached note is STILL VISIBLE
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 5: reveals P1
        revealAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 6: asserts that the element is revealed
        assertTrue("The package should be visible!", diagram.getContainers().get(0).isVisible());

        // Step 7: ensures that the attached note is visible
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 8: hiding P1
        eSubPackageP1 = ePackage.getESubpackages().get(0);
        diagramElement = getFirstDiagramElement(diagram, eSubPackageP1);
        elementEditPart = getEditPart(diagramElement);
        diagramEditor.getDiagramGraphicalViewer().select(elementEditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 9: asserts that the element is hidden
        assertFalse("The package should be hidden!", diagram.getContainers().get(0).isVisible());

        // Step 10: ensures that the attached note was not hidden
        note = getNote(gmfDiagram.eContents());
        assertTrue("The note should be visible!", note.isVisible());

        // Step 11: calls Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        // Step 12: ensures that the package is revealed
        assertTrue("The package should be visible!", diagram.getContainers().get(0).isVisible());
        assertNotNull("The diagram should own a note!", note);
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
