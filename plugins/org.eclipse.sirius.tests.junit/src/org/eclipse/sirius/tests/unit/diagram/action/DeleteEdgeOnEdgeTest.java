/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.helper.task.DeleteEObjectTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

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
     * Ensures that deleting a node used as target by an edge from a node to an edge, with "successive edge on egde
     * cases" works as expected in manual refresh mode and with unsynchronized diagram:<BR/>
     * First case with a simple case.
     */
    public void testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram1_diagram() {
        testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram1(true);
    }

    /**
     * Ensures that deleting a node used as target by an edge from a node to an edge, with "successive edge on egde
     * cases" works as expected in manual refresh mode and with unsynchronized diagram:<BR/>
     * First case with a simple case.
     */
    public void testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram1_model() {
        testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram1(false);
    }

    /**
     * Ensures that deleting a node used as target by an edge from a node to an edge, with "successive edge on egde
     * cases" works as expected in manual refresh mode and with unsynchronized diagram:<BR/>
     * First case with a simple case.
     */
    private void testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram1(boolean deleteFromDiagram) {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        // Open a specific diagram
        DDiagram diagram2 = (DDiagram) getRepresentations("bugzilla580691_TC1").toArray()[0]; //$NON-NLS-1$
        assertNotNull(diagram2);
        IEditorPart editor2 = DialectUIManager.INSTANCE.openEditor(session, diagram2, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        try {
            unsynchronizeDiagram(diagram2);

            int nbEdgesBeforeDelete = diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum();

            // Step 1 : delete an edge on edge
            // edge should not exist before tool applying
            EClass classC0 = null;
            try {
                classC0 = (EClass) semanticRoot.getESubpackages().get(0).getEClassifiers().get(2);
            } catch (Exception e) {
                fail("Impossible de get the semantic EObject corresponding to the class C0:" + e.getMessage()); //$NON-NLS-1$
            }

            DDiagramElement ddeC0 = getFirstDiagramElement(diagram2, classC0);
            if (deleteFromDiagram) {
                // Check the number of DeleteEObjectTask in case of a "Delete from Diagram" command (to check in
                // improvement
                // made at the same time that a fix)
                Command cdm = getCommandFactory().buildDeleteFromDiagramCommand(ddeC0);
                // Count the number of tasks in this command (one for DNode and one for each DEdge, ie 4)
                assertEquals("Wrong number of DeleteEObjectTask.", 5, getNbDeleteTasksInCommand(cdm)); //$NON-NLS-1$
            } else {
                // Execute a "Delete from Model" command
                delete(getEditPart(ddeC0));
                TestsUtil.synchronizationWithUIThread();

                // Step 2 : check number of edges reachable through nodes (outgoingEdges and incomingEdges)
                assertEquals("Wrong number of edges, DEdge, after the deletion.", 0, //$NON-NLS-1$
                        diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum());

                // Step 3 : testing undo/redo
                // Step 3.1 : Undo the Deletion of the edge
                session.getTransactionalEditingDomain().getCommandStack().undo();
                assertEquals("Undo deletion failed, wrong number of edges (DEdge).", nbEdgesBeforeDelete, //$NON-NLS-1$
                        diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum());

                // Step 3.2 : Redo the Deletion of the edge
                session.getTransactionalEditingDomain().getCommandStack().redo();
                assertEquals("Wrong number of edges, DEdge, after the redo of deletion.", 0, //$NON-NLS-1$
                        diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum());

                // Step 4 : refreshing diagram
                refresh(diagram2);
                assertEquals("Wrong number of edges, DEdge, after refresh.", 0, //$NON-NLS-1$
                        diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum());
            }
        } finally {
            DialectUIManager.INSTANCE.closeEditor(editor2, false);
            TestsUtil.emptyEventsFromUIThread();
        }
    }

    /**
     * Ensures that deleting a node used as target by an edge from a node to an edge, with "successive edge on egde
     * cases" works as expected in manual refresh mode and with unsynchronized diagram:<BR/>
     * Second case with a more complex case: The deleted object, indirectly deletes sub nodes, with as consequence, the
     * deletion of the same edge "several times", two times to be more precise. This allows to check the number of
     * DeleteEObjectTask in case of "Delete from Diagram" case. For the case of "Delete from Model", it is not possible
     * to count them.
     */
    public void testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram2_diagram() {
        testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram2(true);
    }

    /**
     * Ensures that deleting a node used as target by an edge from a node to an edge, with "successive edge on egde
     * cases" works as expected in manual refresh mode and with unsynchronized diagram:<BR/>
     * Second case with a more complex case: The deleted object, indirectly deletes sub nodes, with as consequence, the
     * deletion of the same edge "several times", two times to be more precise. This allows to check the number of
     * DeleteEObjectTask in case of "Delete from Diagram" case. For the case of "Delete from Model", it is not possible
     * to count them.
     */
    public void testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram2_model() {
        testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram2(false);
    }

    /**
     * Ensures that deleting a node used as target by an edge from a node to an edge, with "successive edge on egde
     * cases" works as expected in manual refresh mode and with unsynchronized diagram:<BR/>
     * Second case with a more complex case: The deleted object, indirectly deletes sub nodes, with as consequence, the
     * deletion of the same edge "several times", two times to be more precise. This allows to check the number of
     * DeleteEObjectTask in case of "Delete from Diagram" case. For the case of "Delete from Model", it is not possible
     * to count them.
     */
    private void testIndirectEdgeDeletionFromNodeToEdgeWithSuccessiveEgdeOnEdgesInManualRefreshUnsynchronizedDiagram2(boolean deleteFromDiagram) {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        // Open a specific diagram
        DDiagram diagram2 = (DDiagram) getRepresentations("bugzilla580691_TC2").toArray()[0]; //$NON-NLS-1$
        assertNotNull(diagram2);
        IEditorPart editor2 = DialectUIManager.INSTANCE.openEditor(session, diagram2, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        try {
            unsynchronizeDiagram(diagram2);

            int nbEdgesBeforeDelete = diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum();

            // Step 1 : delete an edge on edge
            // edge should not exist before tool applying
            EPackage packageP1 = null;
            try {
                packageP1 = semanticRoot.getESubpackages().get(1).getESubpackages().get(0);
            } catch (Exception e) {
                fail("Impossible de get the semantic EObject corresponding to the package p1:" + e.getMessage()); //$NON-NLS-1$
            }

            DDiagramElement ddeP1 = getFirstDiagramElement(diagram2, packageP1);
            if (deleteFromDiagram) {
                // Check the number of DeleteEObjectTask in case of a "Delete from Diagram" command (to check in
                // improvement
                // made at the same time that a fix)
                Command cdm = getCommandFactory().buildDeleteFromDiagramCommand(ddeP1);
                // Count the number of tasks in this command (one for DNode and one for each DEdge, ie 4)
                assertEquals("Wrong number of DeleteEObjectTask.", 8, getNbDeleteTasksInCommand(cdm)); //$NON-NLS-1$
            } else {
            // Execute a "Delete from Model" command
            delete(getEditPart(ddeP1));
            TestsUtil.synchronizationWithUIThread();

            // Step 2 : check number of edges reachable through nodes (outgoingEdges and incomingEdges)
            assertEquals("Wrong number of edges, DEdge, after the deletion.", 0, //$NON-NLS-1$
                    diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum());

            // Step 3 : testing undo/redo
            // Step 3.1 : Undo the Deletion of the edge
            session.getTransactionalEditingDomain().getCommandStack().undo();
            assertEquals("Undo deletion failed, wrong number of edges (DEdge).", nbEdgesBeforeDelete, //$NON-NLS-1$
                    diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum());

            // Step 3.2 : Redo the Deletion of the edge
            session.getTransactionalEditingDomain().getCommandStack().redo();
            assertEquals("Wrong number of edges, DEdge, after the redo of deletion.", 0, //$NON-NLS-1$
                    diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum());

            // Step 4 : refreshing diagram
            refresh(diagram2);
            assertEquals("Wrong number of edges, DEdge, after refresh.", 0, diagram2.getNodes().stream().mapToInt(dNode -> dNode.getIncomingEdges().size() + dNode.getOutgoingEdges().size()).sum()); //$NON-NLS-1$
        }
        } finally {
            DialectUIManager.INSTANCE.closeEditor(editor2, false);
            TestsUtil.emptyEventsFromUIThread();
        }
    }

    private int getNbDeleteTasksInCommand(Command cmd) {
        int nbsuBTasks = 0;
        if (cmd instanceof SiriusCommand) {
            for (ICommandTask task : ((SiriusCommand) cmd).getTasks()) {
                if (task instanceof DeleteEObjectTask) {
                    nbsuBTasks++;
                }
            }
        }
        return nbsuBTasks;
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
        var childrenEditParts = parent.getChildren();
        for (var child : childrenEditParts) {
            if (child instanceof DEdgeNameEditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }
}
