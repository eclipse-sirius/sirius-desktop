/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;

import com.google.common.base.Predicate;

/**
 * Ensures that reconnection tool works correctly when do reconnection Edges on
 * Edges.
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
 * 
 */
public class ReconnectionEdgeOnEdgeTest extends AbstractEdgeOnEdgeTest {

    /**
     * A predicate that return true if the edge reconnection modified the
     * semantic model has expected.
     */
    private Predicate<EPackage> edgeReconnectionFromEdgeToNodeSemanticPredicate = new Predicate<EPackage>() {
        public boolean apply(EPackage semanticRoot) {
            boolean result = false;

            EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
            EAnnotation targetAnnotation = getAnnotationFromSource(annotationReference, "A1");

            if (annotationReference.getEAnnotation("A1") != null) {
                result = annotationReference.getEAnnotation("A1").equals(targetAnnotation);
            }

            return result;
        }
    };

    /**
     * A predicate that return true if the edge reconnection modified the
     * semantic model has expected.
     */
    private Predicate<EPackage> edgeReconnectionFromNodeToEdgeSemanticPredicate = new Predicate<EPackage>() {
        public boolean apply(EPackage semanticRoot) {

            EReference annotationReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
            EReference targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
            EAnnotation sourceAnnotation = getAnnotationFromSource(annotationReference, "A1");

            return sourceAnnotation.getReferences().iterator().next().equals(targetReference);

        }
    };

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in manual refresh mode.
     */
    public void testEdgeReconnectionFromEdgeToNodeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeReconnectionFromEdgeToNodeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeReconncetionFromEdgeToNodeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnect an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeReconnectionFromEdgeToNodeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeReconnectionFromEdgeToNodeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in automatic refresh mode.
     */
    public void testEdgeReconnectionFromEdgeToNodeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized diagram.
     */
    public void testEdgeReconnectionFromEdgeToNodeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized sourceMapping.
     */
    public void testEdgeReconnectionFromEdgeToNodeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized targetMapping.
     */
    public void testEdgeReconnectionFromEdgeToNodeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from an edge to a node mode works as
     * expected in automatic refresh mode with unsynchronized mapping.
     */
    public void testEdgeReconnectionFromEdgeToNodeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeReconnectionFromEdgeToNode();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in manual refresh mode.
     */
    public void testEdgeReconnectionFromNodeToEdgeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeReconnectionFromNodeToEdgeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeReconnectionFromNodeToEdgeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeReconnectionFromNodeToEdgeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeReconnectionFromNodeToEdgeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in automatic refresh mode.
     */
    public void testEdgeReconnectionFromNodeToEdgeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized diagram.
     */
    public void testEdgeReconnectionFromNodeToEdgeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeReconnectionFromNodeToEdgeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeReconnectionFromNodeToEdgeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that reconnection an edge from a node to an edge mode works as
     * expected in automatic refresh mode and with unsynchronized mapping.
     */
    public void testEdgeReconnectionFromNodeToEdgeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeReconnectionFromNodeToEdge();
    }

    /**
     * Ensures that the edge reconnection modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning... When the source of
     * the Edge is a Node and its target an Edge.
     */
    public void genericTestEdgeReconnectionFromNodeToEdge() {
        EReference targetReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation sourceAnnotation = getAnnotationFromSource(targetReference, "A1");
        genericTestEdgeReconnection(sourceAnnotation, targetReference, "Reconnect EAnnotToRef", edgeReconnectionFromNodeToEdgeSemanticPredicate, false, true);
    }

    /**
     * Ensures that the edge reconnection modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning... When the source of
     * the Edge is an edge and its target a Node.
     */
    private void genericTestEdgeReconnectionFromEdgeToNode() {
        EReference sourceReference = ((EClass) semanticRoot.getEClassifier("C0")).getEReferences().iterator().next();
        EAnnotation targetAnnotation = getAnnotationFromSource(sourceReference, "A1");
        genericTestEdgeReconnection(sourceReference, targetAnnotation, "Reconnect RefToEAnnot", edgeReconnectionFromEdgeToNodeSemanticPredicate, true, false);
    }

    /**
     * Ensures that the edge reconnection modifies correctly the semantic and
     * graphical model, with undo/redo, editor reopenning...
     * 
     * @param semanticEdgeSource
     *            the semantic source of the edge to reconnect
     * @param semanticEdgeTarget
     *            the semantic target of the edge to reconnect
     * @param edgeReconnectToolName
     *            the name of the edge creation tool to use
     * @param semanticPredicate
     *            the predicate that checks that semantic model has correctly
     *            been modified
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    public void genericTestEdgeReconnection(EObject semanticEdgeSource, EObject semanticEdgeTarget, String edgeReconnectToolName, Predicate<EPackage> semanticPredicate, boolean sourceShouldBeAnEge,
            boolean targetShouldBeAnEdge) {

        // Step 1 : reconnect an edge on edge
        // edge should not exist before tool applying
        assertFalse("Invalid initial state", semanticPredicate.apply(semanticRoot));
        DEdge edge = null;
        for (DEdge dEdge : diagram.getEdges()) {
            if (edgeReconnectToolName.contains(dEdge.getName())) {
                if (sourceShouldBeAnEge && dEdge.getSourceNode() instanceof DEdge && semanticEdgeSource instanceof EReferenceImpl
                        && ((DEdge) dEdge.getSourceNode()).getName().contains(((EReferenceImpl) semanticEdgeSource).getName()) && dEdge.getTargetNode() instanceof DNode
                        && semanticEdgeTarget instanceof EAnnotation && ((DNode) dEdge.getTargetNode()).getName().equals(((EAnnotation) semanticEdgeTarget).getSource())) {
                    edge = dEdge;
                    break;
                } else if (targetShouldBeAnEdge && dEdge.getTargetNode() instanceof DEdge && semanticEdgeTarget instanceof EReferenceImpl
                        && ((DEdge) dEdge.getTargetNode()).getName().contains(((EReferenceImpl) semanticEdgeTarget).getName()) && dEdge.getSourceNode() instanceof DNode
                        && semanticEdgeSource instanceof EAnnotation && ((DNode) dEdge.getSourceNode()).getName().equals(((EAnnotation) semanticEdgeSource).getSource())) {
                    edge = dEdge;
                    break;
                }
            }
        }

        assertNotNull("The edge to reconnect can not be null", edge);

        EReference sourceReference = null;
        EReference targetReference = null;
        IDiagramEdgeEditPart gmfEP;
        if (sourceShouldBeAnEge) {
            sourceReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
            DDiagramElement firstDiagramElement = getFirstDiagramElement(diagram, sourceReference);
            assertTrue("The first diagram editor should be a DEdge (but is a " + firstDiagramElement.getClass().getSimpleName() + ")", firstDiagramElement instanceof DEdge);
            applyEdgeReconnectionTool(edgeReconnectToolName, diagram, edge, (EdgeTarget) getFirstDiagramElement(diagram, semanticEdgeSource),
                    (EdgeTarget) ((DEdge) getFirstDiagramElement(diagram, sourceReference)));
            // Step 2 : check that edge has been reconnected
            gmfEP = checkEdgehHasBeenReconnectedGraphicallyAndSemantically(sourceReference, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        } else {
            targetReference = ((EClass) semanticRoot.getEClassifier("C2")).getEReferences().iterator().next();
            applyEdgeReconnectionTool(edgeReconnectToolName, diagram, edge, (EdgeTarget) getFirstDiagramElement(diagram, semanticEdgeTarget),
                    (EdgeTarget) getFirstDiagramElement(diagram, targetReference));
            // Step 2 : check that edge has been reconnected
            gmfEP = checkEdgehHasBeenReconnectedGraphicallyAndSemantically(semanticEdgeSource, targetReference, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        }
        // Check that the edge is selected after reconnection
        TestsUtil.synchronizationWithUIThread();
        assertTrue("The edge should be selected after the reconnection.", gmfEP.getSelected() != EditPart.SELECTED_NONE);

        // Step 3 : testing undo/redo
        // Step 3.1 : Undo the reconnect of the edge
        session.getTransactionalEditingDomain().getCommandStack().undo();
        // -> semantic model should have been modified
        assertFalse("Undo failed", semanticPredicate.apply(semanticRoot));

        // Step 3.2 : Redo the reconnect of the edge
        session.getTransactionalEditingDomain().getCommandStack().redo();
        // -> semantic model should have been modified and edge should be
        // visible
        if (sourceShouldBeAnEge) {
            checkEdgehHasBeenReconnectedGraphicallyAndSemantically(sourceReference, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        } else {
            checkEdgehHasBeenReconnectedGraphicallyAndSemantically(semanticEdgeSource, targetReference, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        }

        // Step 4 : refreshing diagram
        refresh(diagram);
        if (sourceShouldBeAnEge) {
            checkEdgehHasBeenReconnectedGraphicallyAndSemantically(sourceReference, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        } else {
            checkEdgehHasBeenReconnectedGraphicallyAndSemantically(semanticEdgeSource, targetReference, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        }

        // Step 5 : Reopen editor
        closeAndReopenEditor();
        if (sourceShouldBeAnEge) {
            checkEdgehHasBeenReconnectedGraphicallyAndSemantically(sourceReference, semanticEdgeTarget, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        } else {
            checkEdgehHasBeenReconnectedGraphicallyAndSemantically(semanticEdgeSource, targetReference, semanticPredicate, sourceShouldBeAnEge, targetShouldBeAnEdge);
        }
    }

    /**
     * Ensures that an edge with the given source and target has been reconnect.
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
    private IDiagramEdgeEditPart checkEdgehHasBeenReconnectedGraphicallyAndSemantically(EObject semanticSource, EObject semanticTarget, Predicate<EPackage> predicate, boolean sourceShouldBeAnEge,
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
                break;
            }
        }
        assertNotNull("The edge to reconnect can not be null ", edgeElement);

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
        return gmfEP;
    }

}
