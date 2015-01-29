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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.tools.api.command.view.RevealDDiagramElements;
import org.eclipse.sirius.diagram.tools.api.command.view.RevealDDiagramElementsLabel;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Ensures that hide/reveal edge and hide/reveal label on edge works correctly.
 * 
 * <p>
 * Tested parameters :
 * <ul>
 * <li>Edge hide/reveal</li>
 * <li>Edge label hide/reveal</li>
 * <li>Undo/Redo</li>
 * <li>Editor reopenning</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Relevant tickets :
 * <ul>
 * <li>VP-1771 : Edges toward Edges</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class EdgeOnEdgeHideRevealTest extends AbstractEdgeOnEdgeTest {

    private HideDDiagramElementAction hideAction;

    private HideDDiagramElementLabelAction hideLabelAction;

    private DiagramDocumentEditor diagramEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Step 1 creating Hide element and label actions
        hideAction = new HideDDiagramElementAction("Hide element");
        hideLabelAction = new HideDDiagramElementLabelAction("Hide label");
        assertTrue("The editor should be a DiagramDocumentEditor (but is a " + editor.getClass().getSimpleName() + ")", editor instanceof DiagramDocumentEditor);
        diagramEditor = (DiagramDocumentEditor) editor;
    }

    /**
     * Ensures that hide reveal on edge work as expected on edge from edge to
     * node.
     * 
     * @throws Exception
     */
    public void testEdgeHideRevealFromEdgeToNode() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to an
        // edge to hide
        EReference sourceReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation targetAnnotation = getAnnotationFromSource(sourceReference, "AnnotRef1");
        assertNotNull("Cannot find any EAnnotation with source " + sourceReference, targetAnnotation);

        hideRevealEdge(sourceReference, targetAnnotation, true, false);
    }

    /**
     * Ensures that hide reveal on edge work as expected on edge from node to
     * edge.
     * 
     * @throws Exception
     */
    public void testEdgeHideRevealFromNodeToEdge() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to an edge to hide
        EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation sourceAnnotation = getAnnotationFromSource(annotationReference, "AnnotRef1");
        assertNotNull("Cannot find any EAnnotation with source " + annotationReference, sourceAnnotation);
        EReference targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();

        hideRevealEdge(sourceAnnotation, targetReference, false, true);
    }

    /**
     * Ensures that hide reveal label on edge work as expected on edge from edge
     * to node.
     * 
     * @throws Exception
     */
    public void testEdgeLabelHideRevealFromEdgeToNode() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to an
        // edge to hide
        EReference sourceReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation targetAnnotation = getAnnotationFromSource(sourceReference, "AnnotRef1");
        assertNotNull("Cannot find any EAnnotation with source " + sourceReference, targetAnnotation);

        hideRevealEdgeLabel(sourceReference, targetAnnotation, true, false);
    }

    /**
     * Ensures that hide reveal label on edge work as expected on edge from node
     * to edge.
     * 
     * @throws Exception
     */
    public void testEdgeLabelHideRevealFromNodeToEdge() throws Exception {
        // Step 1 : getting the DiagramElement corresponding to an edge to hide
        EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation sourceAnnotation = getAnnotationFromSource(annotationReference, "AnnotRef1");
        assertNotNull("Cannot find any EAnnotation with source " + annotationReference, sourceAnnotation);
        EReference targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();

        hideRevealEdgeLabel(sourceAnnotation, targetReference, false, true);
    }

    /**
     * Hide the edge described by the given parameters. Test undo and redo after
     * hide action. Test Reveal action and undo and redo.
     * 
     * @param semanticEdgeSource
     *            the semantic source of the edge to hide
     * @param semanticEdgeTarget
     *            the semantic target of the edge to hide
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    private void hideRevealEdge(EObject semanticEdgeSource, EObject semanticEdgeTarget, boolean sourceShouldBeAnEge, boolean targetShouldBeAnEdge) throws Exception {
        // Step 1 : getting the edge
        DEdge dEdge = null;
        for (final DEdge edge : diagram.getEdges()) {
            DDiagramElement sourceNode = (DDiagramElement) edge.getSourceNode();
            DDiagramElement targetNode = (DDiagramElement) edge.getTargetNode();
            if (sourceNode.getTarget() == semanticEdgeSource && targetNode.getTarget() == semanticEdgeTarget) {
                if (sourceNode instanceof DNode && !sourceShouldBeAnEge || sourceShouldBeAnEge) {
                    if (targetNode instanceof DNode && !targetShouldBeAnEdge || targetShouldBeAnEdge) {
                        dEdge = edge;
                    }
                }

            }
        }
        assertNotNull("The edge to hide/reveal can not be null", dEdge);

        // Step 2 : hiding the edge
        IGraphicalEditPart elementEditPart = getEditPart(dEdge);
        assertNotNull("This element should be visible : " + dEdge, elementEditPart);
        diagramEditor.getDiagramGraphicalViewer().select(elementEditPart);
        hideAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the edge was hidden correctly
        checkForHiddenElements(diagram, dEdge);

        // Step 5 : Check Undo/Redo
        assertTrue("The Hide action couldn't be undone.", undo());
        checkForHiddenElements(diagram);
        assertTrue("The Hide action couldn't be redone.", redo());
        checkForHiddenElements(diagram, dEdge);

        // Step 6 : revealing the hidden element
        buildRevealAction(dEdge).run();
        TestsUtil.synchronizationWithUIThread();

        // Step 7 : ensure that all element are now revealed
        checkForHiddenElements(diagram);

        // Step 8 : Check Undo/Redo
        assertTrue("The Reveal action couldn't be undone.", undo());
        checkForHiddenElements(diagram, dEdge);
        assertTrue("The Reveal action couldn't be redone.", redo());
        checkForHiddenElements(diagram);

    }

    /**
     * hide and reveal the edge label described by the given parameters. Test
     * too the undo redo after this actions.
     * 
     * @param semanticEdgeSource
     *            the semantic source of the edge label to hide
     * @param semanticEdgeTarget
     *            the semantic target of the edge label to hide
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    private void hideRevealEdgeLabel(EObject semanticEdgeSource, EObject semanticEdgeTarget, boolean sourceShouldBeAnEge, boolean targetShouldBeAnEdge) throws Exception {
        // Step 1 : getting the edge
        DEdge dEdge = null;
        for (final DEdge edge : diagram.getEdges()) {
            DDiagramElement sourceNode = (DDiagramElement) edge.getSourceNode();
            DDiagramElement targetNode = (DDiagramElement) edge.getTargetNode();
            if (sourceNode.getTarget() == semanticEdgeSource && targetNode.getTarget() == semanticEdgeTarget) {
                if (sourceNode instanceof DNode && !sourceShouldBeAnEge || sourceShouldBeAnEge) {
                    if (targetNode instanceof DNode && !targetShouldBeAnEdge || targetShouldBeAnEdge) {
                        dEdge = edge;
                    }
                }

            }
        }
        assertNotNull("The edge to hide/reveal can not be null", dEdge);

        // Step 2 : hiding the edge label
        LabelEditPart labelEditPart = getLabelEditPart(dEdge);
        assertNotNull("This element should have a visible Label : " + dEdge, labelEditPart);
        diagramEditor.getDiagramGraphicalViewer().select(labelEditPart);
        hideLabelAction.run();
        TestsUtil.synchronizationWithUIThread();

        // Step 3 : ensure that the label was hidden correctly
        checkForHiddenLabels(diagram, dEdge);

        // Step 5 : Check Undo/Redo
        assertTrue("The Hide label action couldn't be undone.", undo());
        checkForHiddenLabels(diagram);
        assertTrue("The Hide label action couldn't be redone.", redo());
        checkForHiddenLabels(diagram, dEdge);

        // Step 6 : revealing the hidden labels
        buildRevealLabelAction(dEdge).run();
        TestsUtil.synchronizationWithUIThread();

        // Step 7 : ensure that all labels are now revealed
        checkForHiddenLabels(diagram);

        // Step 8 : Check Undo/Redo
        assertTrue("The reveal label action couldn't be undone.", undo());
        checkForHiddenLabels(diagram, dEdge);
        assertTrue("The reveal label action couldn't be redone.", redo());
        checkForHiddenLabels(diagram);
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
    private void checkForHiddenElements(final DDiagram diagram, DDiagramElement... elementsThatShouldHaveHidden) {

        // We first get all the elements that should have visible
        HashSet<DDiagramElement> allDiagramElements = Sets.newHashSet(diagram.getOwnedDiagramElements());
        for (DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            Iterator<DDiagramElement> filter = Iterables.filter(diagramElement.eContents(), DDiagramElement.class).iterator();
            while (filter.hasNext()) {
                allDiagramElements.add(filter.next());
            }
        }
        SetView<DDiagramElement> elementsThatShouldHaveVisible = Sets.difference(allDiagramElements, Sets.newHashSet(elementsThatShouldHaveHidden));

        // And ensure that all these elements have visible
        for (DDiagramElement elementThatShouldHaveVisible : elementsThatShouldHaveVisible) {

            assertNotNull("This element should not be hidden : " + elementThatShouldHaveVisible);
            assertTrue("This element should not be hidden : " + elementThatShouldHaveVisible, elementThatShouldHaveVisible.isVisible());
        }

        // Then we ensure that all elements that should be hidden are
        // effectively hidden
        for (DDiagramElement elementThatShouldHaveHidden : elementsThatShouldHaveHidden) {
            assertTrue("This element's label should be hidden : " + elementThatShouldHaveHidden, !elementThatShouldHaveHidden.isVisible());
        }
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

    /**
     * Builds an action that will reveal the element of the given
     * diagramElements.
     * 
     * @param diagramElementsToRevealFrom
     *            the diagram elements from must be revealed
     * @return an action that will reveal the diagramElements
     */
    private Action buildRevealAction(final DDiagramElement... diagramElementsToRevealFrom) {
        return new Action() {
            public void run() {
                Set<DDiagramElement> dDiagramElements = Sets.newHashSet(diagramElementsToRevealFrom);
                if (!dDiagramElements.isEmpty()) {
                    TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(dDiagramElements.iterator().next());
                    editingDomain.getCommandStack().execute(new RevealDDiagramElements(editingDomain, dDiagramElements));
                }
            }
        };
    }

}
