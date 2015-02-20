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
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;

import com.google.common.base.Predicate;

/**
 * Ensures that creation tool works correctly when creating Edges on Edges.
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
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class CreateEdgeOnEdgeTest extends AbstractEdgeOnEdgeTest {

    /**
     * A predicate that return true if the edge creation modified the semantic
     * model has expected.
     */
    private Predicate<EPackage> edgeCreationFromEdgeToNodeSemanticPredicate = new Predicate<EPackage>() {
        public boolean apply(EPackage semanticRoot) {
            EReference sourceReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
            EAnnotation targetAnnotation = getAnnotationFromSource(sourceReference, "myAnnotation");
            assertNotNull("Cannot find any EAnnotation with source " + sourceReference, targetAnnotation);
            return sourceReference.getEAnnotations().contains(targetAnnotation);
        }
    };

    /**
     * A predicate that return true if the edge creation modified the semantic
     * model has expected.
     */
    private Predicate<EPackage> edgeCreationFromNodeToEdgeSemanticPredicate = new Predicate<EPackage>() {
        public boolean apply(EPackage semanticRoot) {
            EReference targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
            EAnnotation sourceAnnotation = getAnnotationFromSource(targetReference, "myAnnotation");
            assertNotNull("Cannot find any EAnnotation with source " + targetReference, sourceAnnotation);
            return sourceAnnotation.getReferences().contains(targetReference);
        }
    };

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in manual refresh mode.
     */
    public void testEdgeCreationFromEdgeToNodeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeCreationFromEdgeToNodeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeCreationFromEdgeToNodeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeCreationFromEdgeToNodeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeCreationFromEdgeToNodeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in automatic refresh mode.
     */
    public void testEdgeCreationFromEdgeToNodeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized diagram.
     */
    public void testEdgeCreationFromEdgeToNodeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized sourceMapping.
     */
    public void testEdgeCreationFromEdgeToNodeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized targetMapping.
     */
    public void testEdgeCreationFromEdgeToNodeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized mapping.
     */
    public void testEdgeCreationFromEdgeToNodeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeCreationFromEdgeToNode();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in manual refresh mode.
     */
    public void testEdgeCreationFromNodeToEdgeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeCreationFromNodeToEdgeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeCreationFromNodeToEdgeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeCreationFromNodeToEdgeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeCreationFromNodeToEdgeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in automatic refresh mode.
     */
    public void testEdgeCreationFromNodeToEdgeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized diagram.
     */
    public void testEdgeCreationFromNodeToEdgeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeCreationFromNodeToEdgeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeCreationFromNodeToEdgeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that creating an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized mapping.
     */
    public void testEdgeCreationFromNodeToEdgeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeCreationFromNodeToEdge();
    }

    /**
     * Ensures that the edge creation modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning... When the source of
     * the Edge is a Node and its target an Edge.
     */
    public void genericTestEdgeCreationFromNodeToEdge() {
        EReference targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
        EAnnotation sourceAnnotation = getAnnotationFromSource(targetReference, "myAnnotation");
        assertNotNull("Cannot find any EAnnotation with source " + targetReference, sourceAnnotation);
        genericTestEdgeCreation(sourceAnnotation, targetReference, "AnnotToRef", edgeCreationFromNodeToEdgeSemanticPredicate, false, true);
    }

    /**
     * Ensures that the edge creation modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning... When the source of
     * the Edge is an edge and its target a Node.
     */
    private void genericTestEdgeCreationFromEdgeToNode() {
        EReference sourceReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
        EAnnotation targetAnnotation = getAnnotationFromSource(sourceReference, "myAnnotation");
        assertNotNull("Cannot find any EAnnotation with source " + sourceReference, targetAnnotation);
        genericTestEdgeCreation(sourceReference, targetAnnotation, "RefToEAnnot", edgeCreationFromEdgeToNodeSemanticPredicate, true, false);
    }

    /**
     * Ensures that the edge creation modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning...
     * 
     * @param semanticEdgeSource
     *            the semantic source of the edge to create
     * @param semanticEdgeTarget
     *            the semantic target of the edge to create
     * @param edgeCreationToolName
     *            the name of the edge creation tool to use
     * @param semanticPredicate
     *            the predicate that checks that semantic model has correctly
     *            been modified
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    public void genericTestEdgeCreation(EObject semanticEdgeSource, EObject semanticEdgeTarget, String edgeCreationToolName, Predicate<EPackage> semanticPredicate, boolean sourceShouldBeAnEge,
            boolean targetShouldBeAnEdge) {

        // Step 1 : create an edge on edge
        // edge should not exist before tool applying
        assertFalse("Invalid initial state", semanticPredicate.apply(semanticRoot));
        applyEdgeCreationTool(edgeCreationToolName, diagram, (EdgeTarget) getFirstDiagramElement(diagram, semanticEdgeSource), (EdgeTarget) getFirstDiagramElement(diagram, semanticEdgeTarget));

        // Step 2 : check that edge has been created
        checkEdgehHasBeenCreatedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 3 : testing undo/redo
        // Step 3.1 : Undo the creation of the edge
        session.getTransactionalEditingDomain().getCommandStack().undo();
        // -> semantic model should have been modified
        assertFalse("Undo failed", semanticPredicate.apply(semanticRoot));

        // Step 3.2 : Redo the creation of the edge
        session.getTransactionalEditingDomain().getCommandStack().redo();
        // -> semantic model should have been modified and edge should be
        // visible
        checkEdgehHasBeenCreatedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 4 : refreshing diagram
        refresh(diagram);
        checkEdgehHasBeenCreatedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 5 : Reopen editor
        closeAndReopenEditor();
        checkEdgehHasBeenCreatedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
    }

    /**
     * Ensures that an edge with the given source and target has been created.
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
    private void checkEdgehHasBeenCreatedGraphicallyAndSemantically(EObject semanticSource, EObject semanticTarget, Predicate<EPackage> predicate, boolean sourceShouldBeAnEge,
            boolean targetShouldBeAnEdge) {
        // Step 1 :check that edge creation correctly modified the semantic
        // model
        assertTrue("Semantic Model was not correctly modified", predicate.apply(semanticRoot));

        // Step 2: check that edge creation is graphically correct
        // Step 2.1 : a DEdge should have been created
        DEdge edgeElement = null;
        for (final DEdge edge : diagram.getEdges()) {
            if ((((DDiagramElement) edge.getSourceNode()).getTarget() == semanticSource) && ((DDiagramElement) edge.getTargetNode()).getTarget() == semanticTarget) {
                edgeElement = edge;
            }
        }
        assertNotNull("Edge should have been created ", edgeElement);

        // Step 2.2 : an editpart should have been created
        IDiagramEdgeEditPart gmfEP = (IDiagramEdgeEditPart) getEditPart(edgeElement, editor);
        assertNotNull("Edge should have been created ", gmfEP);
        assertNotNull("Edge should have been created ", gmfEP.getPolylineConnectionFigure());

        // Step 2.3 : checking the created edge's source and target
        // Step 2.3.1 : checking the created edge's source
        DDiagramElement sourceElement = getFirstNodeElement(diagram, semanticSource);
        if (!(sourceElement instanceof Edge) && sourceShouldBeAnEge) {
            sourceElement = getFirstEdgeElement(diagram, semanticSource);
        }
        assertEquals("Wrong Edge source", sourceElement, ((Edge) gmfEP.getModel()).getSource().getElement());

        // Step 2.3.2 : checking the created edge's target
        DDiagramElement targetElement = getFirstNodeElement(diagram, semanticTarget);
        if (!(targetElement instanceof Edge) && targetShouldBeAnEdge) {
            targetElement = getFirstEdgeElement(diagram, semanticTarget);
        }
        assertEquals("Wrong Edge target", targetElement, ((Edge) gmfEP.getModel()).getTarget().getElement());

    }

}
