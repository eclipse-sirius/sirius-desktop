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

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealAllElementsAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;

/**
 * Hide/Reveal tests for Entities diagram of ecore modeler.
 * 
 * @author cnotot
 */
public class EntitiesDiagramHideRevealTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private static final int NB_ELTS = 3;

    // EClass1 is hidden, but one edge too. So 2 elements are hidden
    private static final int NB_HIDDEN_ELTS = 2;

    private static final int NB_VISIBLE_ELTS = NB_ELTS - NB_HIDDEN_ELTS;

    private DDiagram diagram;

    private DiagramDocumentEditor diagramEditor;

    private IEditorPart editorPart;

    private DDiagramElement class1DiagramElement;

    private HideDDiagramElementAction hideAction;

    private RevealAllElementsAction revealAction;

    private IGraphicalEditPart diagramEditPart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String ecoreDestination = TEMPORARY_PROJECT_NAME + "/test.ecore";
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_SEMANTIC_MODEL_PROJECT_RELATIVE_PATH, ecoreDestination);
        genericSetUp(ecoreDestination, MODELER_PATH);

        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];

        // Action creations
        hideAction = new HideDDiagramElementAction();

        revealAction = new RevealAllElementsAction();

        // Semantic element creations
        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());

        applyNodeCreationTool("Class", diagram, diagram);
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);

        applyNodeCreationTool("Class", diagram, diagram);
        final EClass eClass2 = (EClass) ePackage.getEClassifiers().get(1);

        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) getFirstDiagramElement(diagram, eClass), (EdgeTarget) getFirstDiagramElement(diagram, eClass2));

        // Get the diagram for the root of this model.
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME, ePackage).iterator().next();

        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue("We should have a DiagramDocumentEditor", editorPart instanceof DiagramDocumentEditor);
        diagramEditor = (DiagramDocumentEditor) editorPart;

        diagramEditPart = diagramEditor.getDiagramEditPart();

        assertEquals("Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("Bad number of visible diagram elements.", NB_ELTS, getNbVisibleDiagramElements(diagram));
        assertEquals("Bad number of visible diagram elements.", NB_ELTS, getNbVisibleGMFDiagramElements(diagram));

        class1DiagramElement = getFirstDiagramElement(diagram, eClass);
        assertNotNull("The Class 1 has no corresponding diagramElement", class1DiagramElement);
    }

    /**
     * Test hide action alone.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideAction() throws Exception {
        // assertNull("The decorator should not be found",
        // findNotificationEditPart(diagramEditPart));
        // Hide EClass1. One edge will be hidden too
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(class1DiagramElement));
        TestsUtil.synchronizationWithUIThread();

        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleGMFDiagramElements(diagram));

        // checkNotificationIsVisible(findNotificationEditPart(diagramEditPart));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test hide then reveal actions.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideThenRevealActions() throws Exception {
        // assertNull("The decorator should not be found",
        // findNotificationEditPart(diagramEditPart));
        // Hide EClass1
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(class1DiagramElement));
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        diagramEditor.getDiagramGraphicalViewer().select(diagramEditPart);
        revealAction.run();
        TestsUtil.synchronizationWithUIThread();

        assertEquals("[REVEAL] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[REVEAL] Bad number of visible diagram elements.", NB_ELTS, getNbVisibleDiagramElements(diagram));
        assertEquals("[REVEAL] Bad number of visible diagram elements.", NB_ELTS, getNbVisibleGMFDiagramElements(diagram));

        // assertNull("The decorator should not be found",
        // findNotificationEditPart(diagramEditPart));

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Ensures that it is possible to :
     * <ul>
     * <li>Hide a Node (and by side-effect an Edge)</li>
     * <li>Make a copy of the diagram</li>
     * <li>Close the diagram</li>
     * <li>Open the cloned diagram</li>
     * <li>Reveal the Node (and by side-effect an Edge) in this cloned diagram
     * and check the visibility</li>.
     * </ul>
     * 
     * @throws Exception
     */
    // The following tests do not use the hide and reveal, as it is impossible
    // to select hidden edit views in the editor for now on (no Edit Part is
    // created if the view is not visible)
    public void testRevealNodeAfterHavingClonedDiagram() throws Exception {
        // Step 1 : hide Node -> attached Edge should be hidden
        changeDiagramElementVisiblity(class1DiagramElement, false);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleGMFDiagramElements(diagram));

        // Step 2 : clone the diagram
        DDiagram newDiagram = copyDiagram("Copy of original diagram", diagram);
        TestsUtil.synchronizationWithUIThread();
        assertThereIsOnlyOneGmfDiagramForEachRepresentation(newDiagram);

        // Step 3 : open the editor
        editorPart = DialectUIManager.INSTANCE.openEditor(session, newDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = (DiagramDocumentEditor) editorPart;
        diagramEditPart = diagramEditor.getDiagramEditPart();
        TestsUtil.synchronizationWithUIThread();

        // Step 4 : reveal the node -> attached Edge should be visible
        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model should not be empty here.", !ePackage.getEClassifiers().isEmpty());
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        class1DiagramElement = getFirstDiagramElement(newDiagram, eClass);
        assertNotNull("The Class 1 has no corresponding diagramElement", class1DiagramElement);

        changeDiagramElementVisiblity(class1DiagramElement, true);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, newDiagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleDiagramElements(newDiagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleGMFDiagramElements(newDiagram));
    }

    private DDiagram copyDiagram(final String name, final DDiagram diagramToCopy) {
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagramToCopy);
        RecordingCommand copyCommand = new RecordingCommand(domain) {

            private DRepresentation representation;

            @Override
            protected void doExecute() {
                representation = DialectManager.INSTANCE.copyRepresentation(diagramToCopy, name, session, null);
            }

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
             */
            @Override
            public Collection<?> getResult() {
                Collection<DDiagram> result = new HashSet<DDiagram>();
                result.add((DDiagram) representation);
                return result;
            }
        };
        domain.getCommandStack().execute(copyCommand);
        DDiagram copy = (DDiagram) copyCommand.getResult().iterator().next();
        return copy;
    }

    public void testRevealNodeAfterHavingClosedDiagram() throws Exception {
        // Step 1 : hide Node -> attached Edge should be hidden
        changeDiagramElementVisiblity(class1DiagramElement, false);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleGMFDiagramElements(diagram));

        // Step 2 : close the editor
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : open the editor
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = (DiagramDocumentEditor) editorPart;
        diagramEditPart = diagramEditor.getDiagramEditPart();
        TestsUtil.synchronizationWithUIThread();

        // Step 4 : reveal the node -> attached Edge should be visible
        changeDiagramElementVisiblity(class1DiagramElement, true);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleGMFDiagramElements(diagram));

    }

    /**
     * Ensures that it is possible to :
     * <ul>
     * <li>Hide an Edge</li>
     * <li>Close the diagram</li>
     * <li>Open the diagram</li>
     * <li>Reveal the Edge</li>.
     * </ul>
     * 
     * @throws Exception
     */
    public void testRevealEdgeAfterHavingClosedDiagram() throws Exception {
        // Step 1 : hide Edge
        DEdge edge = Iterables.filter(class1DiagramElement.getParentDiagram().getOwnedDiagramElements(), DEdge.class).iterator().next();
        changeDiagramElementVisiblity(edge, false);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleGMFDiagramElements(diagram));

        // Step 2 : close the editor
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : open the editor
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = (DiagramDocumentEditor) editorPart;
        diagramEditPart = diagramEditor.getDiagramEditPart();
        TestsUtil.synchronizationWithUIThread();

        // Step 4 : reveal the Edge
        changeDiagramElementVisiblity(edge, true);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleGMFDiagramElements(diagram));
    }

    /**
     * Ensures that it is possible to :
     * <ul>
     * <li>Hide an Edge</li>
     * <li>Hide its Source Node</li>
     * <li>Close the diagram</li>
     * <li>Open the diagram</li>
     * <li>Reveal the Node : edge should still be hidden</li>
     * </ul>
     * 
     * @throws Exception
     */
    public void testRevealNodeAfterHavingClosedDiagramAndHideEdge() throws Exception {
        // Step 1 : hide Edge
        DEdge edge = Iterables.filter(class1DiagramElement.getParentDiagram().getOwnedDiagramElements(), DEdge.class).iterator().next();
        changeDiagramElementVisiblity(edge, false);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleDiagramElements(diagram));

        // Step 2 : hide Node
        changeDiagramElementVisiblity(class1DiagramElement, false);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleGMFDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleGMFDiagramElements(diagram));

        // Step 3 : close the editor
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editorPart, true);
        TestsUtil.synchronizationWithUIThread();

        // Step 4 : open the editor
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue("We should have a DiagramDocumentEditor", editorPart instanceof DiagramDocumentEditor);
        diagramEditor = (DiagramDocumentEditor) editorPart;
        diagramEditPart = diagramEditor.getDiagramEditPart();

        // Step 5 : reveal the node -> attached Edge should not be visible
        changeDiagramElementVisiblity(class1DiagramElement, true);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleGMFDiagramElements(diagram));
    }

    /**
     * Ensures that it is possible to :
     * <ul>
     * <li>Hide an Edge</li>
     * <li>Hide its Source Node</li>
     * <li>Close the diagram</li>
     * <li>Open the diagram</li>
     * <li>Reveal the Edge (should not actually reveal the Edge)</li>
     * <li>Reveal the Node (should also reveal the Edge)</li>
     * </ul>
     * 
     * @throws Exception
     */
    public void testRevealEdgeAfterHavingClosedDiagramAndReveleadNode() throws Exception {
        // Step 1 : hide Edge
        DEdge edge = Iterables.filter(class1DiagramElement.getParentDiagram().getOwnedDiagramElements(), DEdge.class).iterator().next();
        changeDiagramElementVisiblity(edge, false);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 1, getNbVisibleGMFDiagramElements(diagram));

        // Step 2 : hide Node
        changeDiagramElementVisiblity(class1DiagramElement, false);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleGMFDiagramElements(diagram));

        // Step 3 : close the editor
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editorPart, true);
        TestsUtil.synchronizationWithUIThread();

        // Step 4 : open the editor
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = (DiagramDocumentEditor) editorPart;
        diagramEditPart = diagramEditor.getDiagramEditPart();

        // Step 5 : reveal the Edge -> should still not be visible as its source
        // is hidden
        changeDiagramElementVisiblity(edge, true);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS, getNbVisibleGMFDiagramElements(diagram));

        // Step 6 : reveal the node -> attached Edge should be visible
        changeDiagramElementVisiblity(class1DiagramElement, true);
        assertEquals("[HIDE] Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleDiagramElements(diagram));
        assertEquals("[HIDE] Bad number of visible diagram elements.", NB_VISIBLE_ELTS + 2, getNbVisibleGMFDiagramElements(diagram));
    }

    /**
     * Changes the visibility of the given {@link DDiagramElement} in the
     * diagram.
     * 
     * @param element
     *            the {@link DDiagramElement} to reveal
     * @param newVisibility
     *            the new visibility for this element
     */
    protected void changeDiagramElementVisiblity(final DDiagramElement element, final boolean newVisibility) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                if (newVisibility) {
                    HideFilterHelper.INSTANCE.reveal(element);
                } else {
                    HideFilterHelper.INSTANCE.hide(element);
                }
            }
        });
        TestsUtil.synchronizationWithUIThread();
    }

    private int getNbVisibleDiagramElements(final DDiagram diagram) {
        int nbVisibleDiagramElements = 0;
        for (final DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            if (DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, diagramElement)) {
                nbVisibleDiagramElements++;
            }
        }
        return nbVisibleDiagramElements;
    }

    private int getNbVisibleGMFDiagramElements(final DDiagram diagram) {
        int nbVisibleDiagramElements = 0;
        for (final DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            if (getGmfViewAndAssertOnlyOne(diagramElement, View.class, session).isVisible()) {
                nbVisibleDiagramElements++;
            }
        }
        return nbVisibleDiagramElements;
    }

    private void assertThereIsOnlyOneGmfDiagramForEachRepresentation(DDiagram diagram) {
        Collection<EObject> inverseReferences = new EObjectQuery(diagram).getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
        assertEquals("We should have only one GMF diagram for each viewpoint diagram.", 1, inverseReferences.size());
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        diagramEditor = null;
        editorPart = null;
        class1DiagramElement = null;
        hideAction = null;
        revealAction = null;
        diagramEditPart = null;
        session.close(new NullProgressMonitor());
        session = null;
        super.tearDown();
    }
}
