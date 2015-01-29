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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.tools.api.command.view.RevealDDiagramElementsLabel;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealAllElementsAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Ensures that Hide/Reveal actions on Label have the expected behavior.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class LabelHideRevealTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/hide-reveal/tc-2330/tc2330.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/hide-reveal/tc-2330/tc2330.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tool/hide-reveal/tc-2330/tc2330.odesign";

    private static final String REPRESENTATION_DESC_NAME = "2330 Diagram";

    private static final int NB_ELTS = 11;

    private DDiagram diagram;

    private DiagramDocumentEditor diagramEditor;

    private IEditorPart editorPart;

    private HideDDiagramElementLabelAction hideAction;

    private RevealAllElementsAction revealAllAction;

    /**
     * @{inheritDoc
     */
    @Override
    protected void setUp() throws Exception {
        // Step 1 : intialize the session and the representation
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];

        // Step 2 : creating Hide and Reveal actions
        hideAction = new HideDDiagramElementLabelAction("Hide Label");
        revealAllAction = new RevealAllElementsAction();

        // Step 3 : open an editor on the representation
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        diagramEditor = (DiagramDocumentEditor) editorPart;

        // Check that all elements are visible
        assertEquals("Bad number of diagram elements.", NB_ELTS, diagram.getOwnedDiagramElements().size());
        assertEquals("Bad number of visible diagram elements.", NB_ELTS, getNbVisibleDiagramElements(diagram));
    }

    public void testHideRevealOnSingleBorderedNode() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to a
        // bordered node label to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EPackage eSubPackage = ePackage.getESubpackages().get(0);
        final EClass eClass1 = (EClass) eSubPackage.getEClassifiers().get(0);
        DDiagramElement labelToHide = getFirstDiagramElement(diagram, eClass1);
        assertNotNull(eClass1.getName() + " has no corresponding diagramElement", labelToHide);

        // Step 2 : hiding the bordered node label
        LabelEditPart labelEditPart = getLabelEditPart(labelToHide);
        assertNotNull("This element should have a visible Label : " + labelToHide, labelEditPart);
        diagramEditor.getDiagramGraphicalViewer().select(labelEditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHide);

        // Step 5 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram, labelToHide);

        // Step 6 : revealing the hidden labels
        buildRevealLabelAction(labelToHide).run();
        TestsUtil.synchronizationWithUIThread();

        // Step 7 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);

        // Step 8 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram, labelToHide);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram);
    }

    /**
     * Builds an action that will reveal the labels of the given
     * diagramElements.
     * 
     * @param diagramElementsToRevealLabelFrom
     *            the diagram elements from which label must be revealed
     * @return an action that will reveal the labels of the given
     *         diagramElements
     */
    private Action buildRevealLabelAction(final DDiagramElement... diagramElementsToRevealLabelFrom) {
        return new Action() {
            public void run() {
                Set<DDiagramElement> dDiagramElements = Sets.newHashSet(diagramElementsToRevealLabelFrom);
                if (!dDiagramElements.isEmpty()) {
                    TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(dDiagramElements.iterator().next());
                    editingDomain.getCommandStack().execute(new RevealDDiagramElementsLabel(editingDomain, dDiagramElements));
                }
            }
        };
    }

    public void testHideRevealOnMultipleBorderedNodes() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to 2
        // bordered node label to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EPackage eSubPackage = ePackage.getESubpackages().get(0);
        final EClass eClass1 = (EClass) eSubPackage.getEClassifiers().get(0);
        DDiagramElement labelToHide1 = getFirstDiagramElement(diagram, eClass1);

        final EPackage eSubPackage2 = ePackage.getESubpackages().get(1);
        final EClass eClass2 = (EClass) eSubPackage2.getEClassifiers().get(0);
        DDiagramElement labelToHide2 = getFirstDiagramElement(diagram, eClass2);

        assertNotNull(eClass1.getName() + " has no corresponding diagramElement", labelToHide1);
        assertNotNull(eClass2.getName() + " has no corresponding diagramElement", labelToHide2);

        // Step 2 : hiding 2 bordered node labels
        hideLabelForElements(labelToHide1, labelToHide2);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2);

        // Step 4 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2);

        // Step 5 : revealing one of the 2 hidden labels
        buildRevealLabelAction(labelToHide1).run();
        TestsUtil.synchronizationWithUIThread();
        checkForHiddenLabelElements(diagram, labelToHide2);

        // Step 6 : revealing the last hidden label
        buildRevealLabelAction(labelToHide2).run();
        TestsUtil.synchronizationWithUIThread();
        checkForHiddenLabelElements(diagram);

        // Step 7 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram, labelToHide2);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram);
    }

    public void testHideRevealOnSingleNode() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to a
        // node label to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EEnum eEnum1 = (EEnum) ePackage.getEClassifiers().get(0);
        DDiagramElement labelToHide1 = getFirstDiagramElement(diagram, eEnum1);
        assertNotNull(eEnum1.getName() + " has no corresponding diagramElement", labelToHide1);

        // Step 2 : hiding 1 node labels
        hideLabelForElements(labelToHide1);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHide1);

        // Step 4 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram, labelToHide1);

        // Step 5 : revealing the hidden labels
        buildRevealLabelAction(labelToHide1).run();
        TestsUtil.synchronizationWithUIThread();

        // Step 6 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);

        // Step 7 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram, labelToHide1);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram);
    }

    public void testHideRevealOnMultipleNodes() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to a
        // node label to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EEnum eEnum1 = (EEnum) ePackage.getEClassifiers().get(0);
        DDiagramElement labelToHide1 = getFirstDiagramElement(diagram, eEnum1);
        assertNotNull(eEnum1.getName() + " has no corresponding diagramElement", labelToHide1);

        final EEnum eEnum2 = (EEnum) ePackage.getEClassifiers().get(1);
        DDiagramElement labelToHide2 = getFirstDiagramElement(diagram, eEnum2);
        assertNotNull(eEnum1.getName() + " has no corresponding diagramElement", labelToHide2);

        // Step 2 : hiding 1 node labels
        hideLabelForElements(labelToHide1, labelToHide2);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2);

        // Step 4 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2);

        // Step 5 : revealing the hidden labels
        buildRevealLabelAction(labelToHide1, labelToHide2).run();
        TestsUtil.synchronizationWithUIThread();

        // Step 6 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);

        // Step 7 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram);
    }

    public void testHideRevealOnMultiplesNodeAndBorderedNodes() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to a
        // node label to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EEnum eEnum1 = (EEnum) ePackage.getEClassifiers().get(0);
        DDiagramElement labelToHide1 = getFirstDiagramElement(diagram, eEnum1);
        assertNotNull(eEnum1.getName() + " has no corresponding diagramElement", labelToHide1);

        final EEnum eEnum2 = (EEnum) ePackage.getEClassifiers().get(1);
        DDiagramElement labelToHide2 = getFirstDiagramElement(diagram, eEnum2);
        assertNotNull(eEnum1.getName() + " has no corresponding diagramElement", labelToHide2);

        final EPackage eSubPackage = ePackage.getESubpackages().get(0);
        final EClass eClass1 = (EClass) eSubPackage.getEClassifiers().get(0);
        DDiagramElement labelToHide3 = getFirstDiagramElement(diagram, eClass1);

        final EPackage eSubPackage2 = ePackage.getESubpackages().get(1);
        final EClass eClass2 = (EClass) eSubPackage2.getEClassifiers().get(0);
        DDiagramElement labelToHide4 = getFirstDiagramElement(diagram, eClass2);

        assertNotNull(eClass1.getName() + " has no corresponding diagramElement", labelToHide3);
        assertNotNull(eClass2.getName() + " has no corresponding diagramElement", labelToHide4);

        // Step 2 : hiding 1 node labels
        hideLabelForElements(labelToHide1, labelToHide2, labelToHide3, labelToHide4);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2, labelToHide3, labelToHide4);

        // Step 4 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2, labelToHide3, labelToHide4);

        // Step 5 : revealing the hidden labels
        buildRevealLabelAction(labelToHide1, labelToHide4).run();
        TestsUtil.synchronizationWithUIThread();
        checkForHiddenLabelElements(diagram, labelToHide3, labelToHide2);

        buildRevealLabelAction(labelToHide2, labelToHide3).run();
        TestsUtil.synchronizationWithUIThread();
        checkForHiddenLabelElements(diagram);

        // Step 6 : Check Undo/Redo
        assertTrue("This action couldn't be undone.", undo());
        checkForHiddenLabelElements(diagram, labelToHide2, labelToHide3);
        assertTrue("This action couldn't be redone.", redo());
        checkForHiddenLabelElements(diagram);
    }

    /**
     * Ensures that the revealAllAction reveals also the hidden labels. <BR>
     * TODO : Check that the reveal action is not used...
     */
    public void _testRevealAllAction() {
        // Step 1 : getting the DiagramElement corresponding to a
        // node label to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EEnum eEnum1 = (EEnum) ePackage.getEClassifiers().get(0);
        DDiagramElement labelToHide1 = getFirstDiagramElement(diagram, eEnum1);
        assertNotNull(eEnum1.getName() + " has no corresponding diagramElement", labelToHide1);

        final EEnum eEnum2 = (EEnum) ePackage.getEClassifiers().get(1);
        DDiagramElement labelToHide2 = getFirstDiagramElement(diagram, eEnum2);
        assertNotNull(eEnum1.getName() + " has no corresponding diagramElement", labelToHide2);

        // Step 2 : hiding 1 node labels
        hideLabelForElements(labelToHide1, labelToHide2);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHide1, labelToHide2);

        // Step 4 : revealing the hidden labels
        revealAllAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 5 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);
    }

    public void testHideRevealLabelOnSingleEdge() throws Exception {
        // Step 1 : Get the DiagramElement corresponding to a
        // edge label to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EPackage p1 = (EPackage) ePackage.getESubpackages().get(0);
        assertEquals("Bad input data", "p1", p1.getName());
        final EClass classA = (EClass) p1.getEClassifier("A");
        final EStructuralFeature reference = classA.getEStructuralFeature("toB");
        DDiagramElement labelToHideEdge = getFirstDiagramElement(diagram, reference);
        assertNotNull(reference.getName() + " has no corresponding diagramElement", labelToHideEdge);

        // Step 2 : hiding 1 edge label
        hideLabelForElements(labelToHideEdge);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHideEdge);

        // Step 4 : undo
        assertTrue("The hidden of the label of the edge couldn't be undone.", undo());

        // Step 5 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);

        // Step 6 : hide and then revealing the hidden labels
        hideLabelForElements(labelToHideEdge);
        buildRevealLabelAction(labelToHideEdge).run();
        TestsUtil.synchronizationWithUIThread();

        // Step 7 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);

        // Step 8 : undo
        assertTrue("The reveal of the label of the edge couldn't be undone.", undo());

        // Step 9 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, labelToHideEdge);

    }

    public void testHideRevealLabelOnMultipeEdges() throws Exception {
        // Step 1 : Get the DiagramElement corresponding to 2
        // edge labels to hide
        final EPackage ePackage = (EPackage) semanticModel;
        final EPackage p1 = (EPackage) ePackage.getESubpackages().get(0);
        assertEquals("Bad input data", "p1", p1.getName());
        final EClass classA = (EClass) p1.getEClassifier("A");
        final EStructuralFeature reference1 = classA.getEStructuralFeature("toB");
        DDiagramElement label1ToHideEdge = getFirstDiagramElement(diagram, reference1);
        assertNotNull(reference1.getName() + " has no corresponding diagramElement", label1ToHideEdge);

        final EClass classI = (EClass) p1.getEClassifier("I");
        final EStructuralFeature reference2 = classI.getEStructuralFeature("toJ");
        DDiagramElement label2ToHideEdge = getFirstDiagramElement(diagram, reference2);
        assertNotNull(reference2.getName() + " has no corresponding diagramElement", label2ToHideEdge);

        // Step 2 : hiding 2 edge labels
        hideLabelForElements(label1ToHideEdge, label2ToHideEdge);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the labels was hidden correctly
        checkForHiddenLabelElements(diagram, label1ToHideEdge, label2ToHideEdge);

        // Step 4 : undo
        assertTrue("The hidden of the label of the edge couldn't be undone.", undo());

        // Step 5 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);

        // Step 6 : hide and then revealing the hidden labels
        hideLabelForElements(label1ToHideEdge, label2ToHideEdge);
        buildRevealLabelAction(label1ToHideEdge, label2ToHideEdge).run();
        TestsUtil.synchronizationWithUIThread();

        // Step 7 : ensure that all labels are now revealed
        checkForHiddenLabelElements(diagram);

        // Step 8 : undo
        assertTrue("The reveal of the label of the edge couldn't be undone.", undo());

        // Step 9 : ensure that the label was hidden correctly
        checkForHiddenLabelElements(diagram, label1ToHideEdge, label2ToHideEdge);

    }

    public void testHideRevealLabelOnMultipeElements() {
        // Step 1 : Getting the DiagramElement corresponding to one edge label
        // and one package.
        final EPackage ePackage = (EPackage) semanticModel;
        final EPackage p1 = (EPackage) ePackage.getESubpackages().get(0);
        assertEquals("Bad input data", "p1", p1.getName());
        DDiagramElement aPackageContainer = getFirstDiagramElement(diagram, p1);
        final EClass classA = (EClass) p1.getEClassifier("A");
        final EStructuralFeature reference1 = classA.getEStructuralFeature("toB");
        DDiagramElement label1ToHideEdge = getFirstDiagramElement(diagram, reference1);
        assertNotNull(reference1.getName() + " has no corresponding diagramElement", label1ToHideEdge);

        // Step 2 : Select the two elements
        selectElements(true, label1ToHideEdge, aPackageContainer);
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : Build the contextual menu to get the action
        MenuManager menuManager = diagramEditor.getDiagramGraphicalViewer().getContextMenu();
        assertTrue("The menu manager of this diagram must be a IMenuListener.", menuManager instanceof IMenuListener);

        ((IMenuListener) menuManager).menuAboutToShow(menuManager);

        IContributionItem hide = diagramEditor.getDiagramGraphicalViewer().getContextMenu()
                .findUsingPath(ActionIds.MENU_FILTERS + "/" + ActionIds.MENU_CONNECTION_LABEL + "/" + ActionIds.ACTION_HIDE_CONNECTION_LABELS);
        assertFalse("The menu hide must not be available when edges are selected with other thing.", hide instanceof ActionContributionItem);
    }

    /**
     * Hides the label for all given {@link DDiagramElement}s. Also checks that
     * the given elements own a label.
     * 
     * @param diagramElementsToHide
     *            the {@link DDiagramElement} containing a label that must be
     *            hidden
     */
    protected void hideLabelForElements(DDiagramElement... diagramElementsToHide) {
        selectElements(true, diagramElementsToHide);
        hideAction.run();
    }

    /**
     * @param checkVisibilityOfLabel
     *            Checks that the given elements own a label
     * @param diagramElementsToHide
     */
    private void selectElements(boolean checkVisibilityOfLabel, DDiagramElement... diagramElementsToHide) {
        boolean firstSelection = true;
        for (DDiagramElement diagramElement : diagramElementsToHide) {
            IGraphicalEditPart labelEditPart = getEditPart(diagramElement);
            if (checkVisibilityOfLabel) {
                assertNotNull("This element should have a visible Label : " + diagramElement, labelEditPart);
                assertTrue("This element's label figure should be visible : " + diagramElement, labelEditPart.getFigure().isVisible());
            }
            if (firstSelection) {
                diagramEditor.getDiagramGraphicalViewer().select(labelEditPart);
                firstSelection = false;
            } else {
                diagramEditor.getDiagramGraphicalViewer().appendSelection(labelEditPart);
            }
        }
    }

    /**
     * Checks that all elements that should be hidden are effectively hidden in
     * the given diagram, and that there is no other hidden element.
     * 
     * @param diagram
     *            the diagram to test
     * @param elementsThatShouldHaveHiddenLabel
     *            the list of elements that should be hidden in the given
     *            diagram
     */
    private void checkForHiddenLabelElements(final DDiagram diagram, DDiagramElement... elementsThatShouldHaveHiddenLabel) {

        // We first get all the elements that should have visible labels
        HashSet<DDiagramElement> allDiagramElements = Sets.newHashSet(diagram.getOwnedDiagramElements());
        for (DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            Iterator<DDiagramElement> filter = Iterables.filter(diagramElement.eContents(), DDiagramElement.class).iterator();
            while (filter.hasNext()) {
                allDiagramElements.add(filter.next());
            }
        }
        SetView<DDiagramElement> elementsThatShouldHaveVisibleLabel = Sets.difference(allDiagramElements, Sets.newHashSet(elementsThatShouldHaveHiddenLabel));

        // And ensure that all these elements have visible labels
        for (DDiagramElement elementThatShouldHaveVisibleLabel : elementsThatShouldHaveVisibleLabel) {

            LabelEditPart labelEditPart = getLabelEditPart(elementThatShouldHaveVisibleLabel);
            assertNotNull("This element's label should not be hidden : " + elementThatShouldHaveVisibleLabel, labelEditPart);
            assertTrue("This element's label should not be hidden : " + elementThatShouldHaveVisibleLabel, labelEditPart.getFigure().isVisible());
        }

        // Then we ensure that all elements that should be hidden are
        // effectively hidden
        for (DDiagramElement elementThatShouldHaveHiddenLabel : elementsThatShouldHaveHiddenLabel) {
            LabelEditPart labelEditPart = getLabelEditPart(elementThatShouldHaveHiddenLabel);
            assertTrue("This element's label should be hidden : " + elementThatShouldHaveHiddenLabel, labelEditPart == null || !labelEditPart.getFigure().isVisible());
        }
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

    /**
     * @{inheritDoc
     */
    @Override
    protected void tearDown() throws Exception {
        // close the editor
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        super.tearDown();
    }
}
