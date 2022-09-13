/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.action;

import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;

import com.google.common.base.Predicate;

/**
 * Ensures that deletion of Edges on Edges works correctly.
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
public class DeleteEdgeOnEdgeTest extends AbstractEdgeOnEdgeTest {

    /**
     * A predicate that return true if the edge Deletion modified the semantic
     * model has expected.
     */
    private Predicate<EPackage> edgeDeletionFromEdgeToNodeSemanticPredicate = new Predicate<EPackage>() {
        @Override
        public boolean apply(EPackage semanticRoot) {
            EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
            EAnnotation targetAnnotation = getAnnotationFromSource(annotationReference, "AnnotRef1");
            assertNotNull("Cannot find any EAnnotation with source " + annotationReference, targetAnnotation);
            return !annotationReference.getEAnnotations().contains(targetAnnotation);
        }
    };

    /**
     * A predicate that return true if the edge Deletion modified the semantic
     * model has expected.
     */
    private Predicate<EPackage> edgeDeletionFromNodeToEdgeSemanticPredicate = new Predicate<EPackage>() {
        @Override
        public boolean apply(EPackage semanticRoot) {
            EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
            EAnnotation sourceAnnotation = getAnnotationFromSource(annotationReference, "AnnotRef1");
            assertNotNull("Cannot find any EAnnotation with source " + annotationReference, sourceAnnotation);
            EReference targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
            return !sourceAnnotation.getReferences().contains(targetReference);
        }
    };

    private DDiagramElement odlSourceNodeOfDeletedEdge;

    private int oldNumberOfSourceConnectionsForOldSourceNodeOfDeletedEdge;

    /**
     * Ensures that deleting an edge from an edge to a node mode works as expected in manual refresh mode and with
     * unsynchronized targetMapping.
     */
    public void testEdgeDeletionFromEdgeToNodeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeDeletionFromEdgeToNode();
    }

    /**
     * Ensures that deleting an edge from an edge to a node mode works as expected in manual refresh mode and with
     * unsynchronized mapping.
     */
    public void testEdgeDeletionFromEdgeToNodeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeDeletionFromEdgeToNode();
    }

    /**
     * Ensures that deleting an edge from an edge to a node mode works as expected in automatic refresh mode.
     */
    public void testEdgeDeletionFromEdgeToNodeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeDeletionFromEdgeToNode();
    }

    /**
     * Ensures that deleting an edge from an edge to a node mode works as expected in automatic refresh mode with
     * unsynchronized diagram.
     */
    public void testEdgeDeletionFromEdgeToNodeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeDeletionFromEdgeToNode();
    }

    /**
     * Ensures that deleting an edge from an edge to a node mode works as expected in automatic refresh mode with
     * unsynchronized sourceMapping.
     */
    public void testEdgeDeletionFromEdgeToNodeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeDeletionFromEdgeToNode();
    }

    /**
     * Ensures that deleting an edge from an edge to a node mode works as expected in automatic refresh mode with
     * unsynchronized targetMapping.
     */
    public void testEdgeDeletionFromEdgeToNodeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeDeletionFromEdgeToNode();
    }

    /**
     * Ensures that deleting an edge from an edge to a node mode works as expected in automatic refresh mode with
     * unsynchronized mapping.
     */
    public void testEdgeDeletionFromEdgeToNodeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeDeletionFromEdgeToNode();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in manual refresh mode.
     */
    public void testEdgeDeletionFromNodeToEdgeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in manual refresh mode and with
     * unsynchronized diagram.
     */
    public void testEdgeDeletionFromNodeToEdgeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in manual refresh mode and with
     * unsynchronized sourceMapping.
     */
    public void testEdgeDeletionFromNodeToEdgeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in manual refresh mode and with
     * unsynchronized targetMapping.
     */
    public void testEdgeDeletionFromNodeToEdgeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in manual refresh mode and with
     * unsynchronized mapping.
     */
    public void testEdgeDeletionFromNodeToEdgeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in automatic refresh mode.
     */
    public void testEdgeDeletionFromNodeToEdgeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in automatic refresh mode and with
     * unsynchronized diagram.
     */
    public void testEdgeDeletionFromNodeToEdgeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in automatic refresh mode and with
     * unsynchronized sourceMapping.
     */
    public void testEdgeDeletionFromNodeToEdgeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in automatic refresh mode and with
     * unsynchronized targetMapping.
     */
    public void testEdgeDeletionFromNodeToEdgeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that deleting an edge from a node to an edge mode works as expected in automatic refresh mode and with
     * unsynchronized mapping.
     */
    public void testEdgeDeletionFromNodeToEdgeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeDeletionFromNodeToEdge();
    }

    /**
     * Ensures that the edge deletion modifies correctly the semantic and graphical model, with undo/redo, editor
     * reopenning... When the source of the Edge is a Node and its target an Edge.
     */
    public void genericTestEdgeDeletionFromNodeToEdge() {
        EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation sourceAnnotation = getAnnotationFromSource(annotationReference, "AnnotRef1");
        assertNotNull("Cannot find any EAnnotation with source " + annotationReference, sourceAnnotation);
        EReference targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();

        genericTestEdgeDeletion(sourceAnnotation, targetReference, edgeDeletionFromNodeToEdgeSemanticPredicate, false, true);
    }

    /**
     * Ensures that the edge Deletion modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning... When the source of
     * the Edge is an edge and its target a Node.
     */
    private void genericTestEdgeDeletionFromEdgeToNode() {
        EReference sourceReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation targetAnnotation = getAnnotationFromSource(sourceReference, "AnnotRef1");
        assertNotNull("Cannot find any EAnnotation with source " + sourceReference, targetAnnotation);

        genericTestEdgeDeletion(sourceReference, targetAnnotation, edgeDeletionFromEdgeToNodeSemanticPredicate, true, false);
    }

    /**
     * Ensures that the edge Deletion modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning...
     * 
     * @param semanticEdgeSource
     *            the semantic source of the edge to delete
     * @param semanticEdgeTarget
     *            the semantic target of the edge to delete
     * @param semanticPredicate
     *            the predicate that checks that semantic model has correctly
     *            been modified
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    public void genericTestEdgeDeletion(EObject semanticEdgeSource, EObject semanticEdgeTarget, Predicate<EPackage> semanticPredicate, boolean sourceShouldBeAnEge, boolean targetShouldBeAnEdge) {

        // Step 1 : delete an edge on edge
        // edge should not exist before tool applying
        assertFalse("Invalid initial state", semanticPredicate.apply(semanticRoot));
        deleteEdge(semanticEdgeSource, semanticEdgeTarget, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 2 : check that edge has been deleted
        checkEdgeHasBeenDeletedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 3 : testing undo/redo
        // Step 3.1 : Undo the Deletion of the edge
        session.getTransactionalEditingDomain().getCommandStack().undo();
        // -> semantic model should have been modified
        assertFalse("Undo failed", semanticPredicate.apply(semanticRoot));

        // Step 3.2 : Redo the Deletion of the edge
        session.getTransactionalEditingDomain().getCommandStack().redo();
        // -> semantic model should have been modified and edge should be
        // visible
        checkEdgeHasBeenDeletedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 4 : refreshing diagram
        refresh(diagram);
        checkEdgeHasBeenDeletedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 5 : Reopen editor
        closeAndReopenEditor();
        checkEdgeHasBeenDeletedGraphicallyAndSemantically(semanticEdgeSource, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
    }

    /**
     * Deletes the edge described by the given parameters.
     * 
     * @param semanticEdgeSource
     *            the semantic source of the edge to delete
     * @param semanticEdgeTarget
     *            the semantic target of the edge to delete been modified
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    private void deleteEdge(EObject semanticEdgeSource, EObject semanticEdgeTarget, boolean sourceShouldBeAnEge, boolean targetShouldBeAnEdge) {
        // Step 1 : getting the DEdge (from node to edge)
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
        assertNotNull(dEdge);

        // Step 2 : getting the edit part corresponding to the label of this
        // dedge
        odlSourceNodeOfDeletedEdge = (DDiagramElement) dEdge.getSourceNode();
        IGraphicalEditPart parent = getEditPart(odlSourceNodeOfDeletedEdge);
        oldNumberOfSourceConnectionsForOldSourceNodeOfDeletedEdge = parent.getSourceConnections().size();
        final IGraphicalEditPart edgeEditPart = getEditPart(dEdge);
        final IGraphicalEditPart listNamePart = getEdgeNameEditPart(edgeEditPart);
        assertNotNull("No EdgeNameEditPart instance found", listNamePart);

        // Step 3 : deleting the label edit part from diagram
        delete(edgeEditPart);
    }

    /**
     * Ensures that an edge with the given source and target has been deleted.
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
    private void checkEdgeHasBeenDeletedGraphicallyAndSemantically(EObject semanticSource, EObject semanticTarget, Predicate<EPackage> predicate, boolean sourceShouldBeAnEge,
            boolean targetShouldBeAnEdge) {
        // Step 1 :check that edge Deletion correctly modified the semantic
        // model
        assertTrue("Semantic Model was not correctly modified", predicate.apply(semanticRoot));

        // Step 2: check that edge Deletion is graphically correct
        // Step 2.1 : a DEdge should have been deleted
        DEdge edgeElement = null;
        for (final DEdge edge : diagram.getEdges()) {
            if ((((DDiagramElement) edge.getSourceNode()).getTarget() == semanticSource) && ((DDiagramElement) edge.getTargetNode()).getTarget() == semanticTarget) {
                edgeElement = edge;
            }
        }
        assertNull("DEdge should have been deleted ", edgeElement);

        // Step 2.2 : edit parts should also have been deleted
        IGraphicalEditPart parent = getEditPart(odlSourceNodeOfDeletedEdge);
        assertEquals("EditParts should have been deleted", oldNumberOfSourceConnectionsForOldSourceNodeOfDeletedEdge - 1, parent.getSourceConnections().size());

    }

    private IGraphicalEditPart getEdgeNameEditPart(final IGraphicalEditPart parent) {
        List<EditPart> childrenEditParts = parent.getChildren();
        for (final EditPart child : childrenEditParts) {
            if (child instanceof DEdgeNameEditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }
}
