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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;
import org.junit.Assert;

import com.google.common.base.Predicate;

/**
 * Ensures that edition tool works correctly when edit label of Edges on Edges.
 * <p>
 * Tested parameters :
 * <ul>
 * <li>Synchronized/Unsynchronized mode</li>
 * <li>Manual/Automatic refresh</li>
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
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien Dupont</a>
 */
public class EditEdgeOnEdgeTest extends AbstractEdgeOnEdgeTest {

    private static final String EDIT_LABEL = "RefEditedLabel";

    /**
     * A predicate that return true if the edge edit label modified the semantic
     * model has expected.
     */
    private Predicate<EPackage> edgeLabelEditedFromEdgeToNodeSemanticPredicate = new Predicate<EPackage>() {
        public boolean apply(EPackage semanticRoot) {
            EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
            return EDIT_LABEL.equals(annotationReference.getName());
        }
    };

    /**
     * A predicate that return true if the edge Deletion modified the semantic
     * model has expected.
     */
    private Predicate<EPackage> edgeLabelEditedFromNodeToEdgeSemanticPredicate = new Predicate<EPackage>() {
        public boolean apply(EPackage semanticRoot) {
            EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
            EAnnotation sourceAnnotation = getAnnotationFromSource(annotationReference, "A1");
            assertNotNull("Cannot find any EAnnotation with source " + annotationReference, sourceAnnotation);
            EReference targetReference = (EReference) sourceAnnotation.getReferences().iterator().next();
            return EDIT_LABEL.equals(targetReference.getName());
        }
    };

    /**
     * Ensures that edit label of an edge from an edge to a node mode works as
     * expected in manual refresh mode.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeEditLabeledFromEdgeToNodeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in automatic refresh mode.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized diagram.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized sourceMapping.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized targetMapping.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized mapping.
     */
    public void testEdgeLabelEditedFromEdgeToNodeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeLabelEditedFromEdgeToNode();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in manual refresh mode.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in automatic refresh mode.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized diagram.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that label edited an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized mapping.
     */
    public void testEdgeLabelEditedFromNodeToEdgeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeLabelEditedFromNodeToEdge();
    }

    /**
     * Ensures that the edge label edited modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning... When the source of
     * the Edge is a Node and its target an Edge.
     */
    public void genericTestEdgeLabelEditedFromNodeToEdge() {
        EReference targetReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation sourceAnnotation = getAnnotationFromSource(targetReference, "A1");
        assertNotNull("Cannot find any EAnnotation with source " + targetReference, sourceAnnotation);
        genericTestEdgeLabelEdited(sourceAnnotation, targetReference, "EAnnotToRefEdit", edgeLabelEditedFromNodeToEdgeSemanticPredicate, false, true);
    }

    /**
     * Ensures that the edge label edited modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning... When the source of
     * the Edge is an edge and its target a Node.
     */
    private void genericTestEdgeLabelEditedFromEdgeToNode() {
        EReference sourceReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation targetAnnotation = getAnnotationFromSource(sourceReference, "A1");
        assertNotNull("Cannot find any EAnnotation with source " + sourceReference, targetAnnotation);
        genericTestEdgeLabelEdited(sourceReference, targetAnnotation, "RefToEAnnotEdit", edgeLabelEditedFromEdgeToNodeSemanticPredicate, true, false);
    }

    /**
     * Ensures that the edge label edited modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning...
     * 
     * @param semanticEdgeSource
     *            the semantic source of the edge to create
     * @param semanticEdgeTarget
     *            the semantic target of the edge to create
     * @param edgeEditedLabelTool
     *            the name of the edge edit label tool to use
     * @param semanticPredicate
     *            the predicate that checks that semantic model has correctly
     *            been modified
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    public void genericTestEdgeLabelEdited(EObject semanticEdgeSource, EObject semanticEdgeTarget, String edgeEditedLabelTool, Predicate<EPackage> semanticPredicate, boolean sourceShouldBeAnEge,
            boolean targetShouldBeAnEdge) {

        // Step 1 : label edited an edge on edge
        // label should not exist before tool applying
        assertFalse("Invalid initial state", semanticPredicate.apply(semanticRoot));
        applyDirectEditTool(edgeEditedLabelTool, diagram, getFirstDiagramElement(diagram, semanticEdgeSource), "RefEditedLabel");

        // Step 2 : check that label has been edited
        checkEdgehHasBeenLabelEditedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 3 : testing undo/redo
        // Step 3.1 : Undo the edited of the edge
        session.getTransactionalEditingDomain().getCommandStack().undo();
        // -> semantic model should have been modified
        assertFalse("Undo failed", semanticPredicate.apply(semanticRoot));

        // Step 3.2 : Redo the lable edited of the edge
        session.getTransactionalEditingDomain().getCommandStack().redo();
        // -> semantic model should have been modified and edge should be
        // visible
        checkEdgehHasBeenLabelEditedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 4 : refreshing diagram
        refresh(diagram);
        checkEdgehHasBeenLabelEditedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 5 : Reopen editor
        closeAndReopenEditor();
        checkEdgehHasBeenLabelEditedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
    }

    /**
     * Ensures that an label edge with the given source and target has been
     * edited.
     * 
     * @param semanticSource
     *            the semantic source
     * @param semanticTarget
     *            the semantic target
     * @param predicate
     *            the predicate validating that semantic model has correctly
     *            been modified
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    private void checkEdgehHasBeenLabelEditedGraphicallyAndSemantically(EObject semanticSource, EObject semanticTarget, Predicate<EPackage> predicate, boolean sourceShouldBeAnEge,
            boolean targetShouldBeAnEdge) {
        // Step 1 :check that edge edit correctly modified the semantic
        // model
        assertTrue("Semantic Model was not correctly modified", predicate.apply(semanticRoot));

        // Step 2: check that edge edit is graphically correct
        // Step 2.1 : a DEdge should have been created
        DEdge edgeElement = null;
        for (final DEdge edge : diagram.getEdges()) {
            if ((((DDiagramElement) edge.getSourceNode()).getTarget() == semanticSource) && ((DDiagramElement) edge.getTargetNode()).getTarget() == semanticTarget) {
                edgeElement = edge;
            }
        }
        assertNotNull("Edge label should have been modified ", edgeElement);

        // Step 2.2 : an editpart should have been modified
        IDiagramEdgeEditPart gmfEP = (IDiagramEdgeEditPart) getEditPart(edgeElement, editor);
        assertNotNull("Edge should have been edited ", gmfEP);
        if (gmfEP.getSource() instanceof DEdgeEditPart) {
            if (((DEdgeEditPart) gmfEP.getSource()).getChildren().get(0) instanceof DEdgeNameEditPart) {
                Assert.assertTrue("The figure should be a SiriusWrapLabel.", ((DEdgeNameEditPart) ((DEdgeEditPart) gmfEP.getSource()).getChildren().get(0)).getFigure() instanceof SiriusWrapLabel);
                assertEquals("Edge should have been edited", true,
                        ((SiriusWrapLabel) ((DEdgeNameEditPart) ((DEdgeEditPart) gmfEP.getSource()).getChildren().get(0)).getFigure()).getText().contains(EDIT_LABEL));
            }
        }

        // Step 2.3 : checking the edited label edge
        DDiagramElement element = getFirstNodeElement(diagram, semanticSource);
        if (!(element instanceof Edge)) {
            element = getFirstEdgeElement(diagram, semanticSource);
        }
        if (targetShouldBeAnEdge) {
            if (element instanceof DEdge) {
                element = (DDiagramElement) ((DEdge) element).getTargetNode();
            }
        }

        assertTrue("Edge label not edited", element.getName().contains(EDIT_LABEL));

    }

}
