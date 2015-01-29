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
package org.eclipse.sirius.tests.unit.diagram.filter;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;

/**
 * Ensures that filter of Edges on Edges works correctly.
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
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class FilterEdgeOnEdgeTest extends AbstractEdgeOnEdgeTest {

    private static final String FILTER_NODE_TO_EDGE = "Hide EAnnotToRef";

    private static final String FILTER_EDGE_TO_NODE = "Hide RefToEAnnot";

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in manual refresh mode.
     */
    public void testEdgeFilterFromEdgeToNodeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeFilterFromEdgeToNodeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeFilterFromEdgeToNodeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeFilterFromEdgeToNodeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeFilterFromEdgeToNodeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in automatic refresh mode.
     */
    public void testEdgeFilterFromEdgeToNodeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized diagram.
     */
    public void testEdgeFilterFromEdgeToNodeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized sourceMapping.
     */
    public void testEdgeFilterFromEdgeToNodeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized targetMapping.
     */
    public void testEdgeFilterFromEdgeToNodeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized mapping.
     */
    public void testEdgeFilterFromEdgeToNodeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeFilterFromEdgeToNode();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in manual refresh mode.
     */
    public void testEdgeFilterFromNodeToEdgeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeFilterFromNodeToEdgeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeFilterFromNodeToEdgeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeFilterFromNodeToEdgeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeFilterFromNodeToEdgeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in automatic refresh mode.
     */
    public void testEdgeFilterFromNodeToEdgeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in automatic refresh mode and with unsynchronized diagram.
     */
    public void testEdgeFilterFromNodeToEdgeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in automatic refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeFilterFromNodeToEdgeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : unsynchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnotToRef TC1");

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in automatic refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeFilterFromNodeToEdgeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : unsynchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EAnnotToRef TC1");

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that filter an edge from a node to an edge mode works as expected
     * in automatic refresh mode and with unsynchronized mapping.
     */
    public void testEdgeFilterFromNodeToEdgeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EAnnot TC1 (source node mapping) : synchronized
        // EMa TC1 (target edge mapping) : synchronized
        // EAnnotToRef TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "EMa TC1");

        genericTestEdgeFilterFromNodeToEdge();
    }

    /**
     * Ensures that the edge Filter modifies correctly the graphical model, with
     * undo/redo, editor reopenning... When the source of the Edge is a Node and
     * its target an Edge.
     */
    public void genericTestEdgeFilterFromNodeToEdge() {
        genericTestEdgeFilter(false, true);
    }

    /**
     * Ensures that the edge Filter modifies correctly the graphical model, with
     * undo/redo, editor reopenning... When the source of the Edge is an edge
     * and its target a Node.
     */
    private void genericTestEdgeFilterFromEdgeToNode() {
        genericTestEdgeFilter(true, false);
    }

    /**
     * Ensures that the edge Filter modifies correctly the graphical model, with
     * undo/redo, editor reopenning...
     * 
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    public void genericTestEdgeFilter(boolean sourceShouldBeAnEge, boolean targetShouldBeAnEdge) {

        // Step 1 : Check that the edges are visible before hidden
        if (sourceShouldBeAnEge) {
            for (final DEdge edge : diagram.getEdges()) {
                if (edge.getSourceNode() instanceof DEdge && edge.getTargetNode() instanceof DNode && ((DNode) edge.getTargetNode()).getTarget() instanceof EAnnotation) {
                    assertTrue("The Ref to EAnnot should be visible", edge.isVisible());
                }
            }
        } else if (targetShouldBeAnEdge) {
            for (final DEdge edge : diagram.getEdges()) {
                if (edge.getTargetNode() instanceof DEdge && edge.getSourceNode() instanceof DNode && ((DNode) edge.getSourceNode()).getTarget() instanceof EAnnotation) {
                    assertTrue("The EAnnot to Ref should be visible", edge.isVisible());
                }
            }
        }

        // Step 2 : Filter edges

        filterEdge(sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 2 : check that edge has been filtered
        checkEdgeHasBeenFilteredGraphically(sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 3 : testing undo/redo
        // Step 3.1 : Undo the Filter of the edge
        session.getTransactionalEditingDomain().getCommandStack().undo();

        if (sourceShouldBeAnEge) {
            for (final DEdge edge : diagram.getEdges()) {
                if (edge.getSourceNode() instanceof DEdge && edge.getTargetNode() instanceof DNode && ((DNode) edge.getTargetNode()).getTarget() instanceof EAnnotation) {
                    assertTrue("The Ref to EAnnot should be visible", edge.isVisible());
                }
            }
        } else if (targetShouldBeAnEdge) {
            for (final DEdge edge : diagram.getEdges()) {
                if (edge.getTargetNode() instanceof DEdge && edge.getSourceNode() instanceof DNode && ((DNode) edge.getSourceNode()).getTarget() instanceof EAnnotation) {
                    assertTrue("The EAnnot to Ref should be visible", edge.isVisible());
                }
            }
        }

        // Step 3.2 : Redo the Filter of the edge
        session.getTransactionalEditingDomain().getCommandStack().redo();
        // -> semantic model should have been modified and edge should be
        // visible
        checkEdgeHasBeenFilteredGraphically(sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 4 : refreshing diagram
        refresh(diagram);
        checkEdgeHasBeenFilteredGraphically(sourceShouldBeAnEge, targetShouldBeAnEdge);

        // Step 5 : Reopen editor
        closeAndReopenEditor();
        checkEdgeHasBeenFilteredGraphically(sourceShouldBeAnEge, targetShouldBeAnEdge);
    }

    /**
     * Filtered the edge described by the given parameters.
     * 
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    private void filterEdge(boolean sourceShouldBeAnEge, boolean targetShouldBeAnEdge) {
        // Step 1 : getting the DEdge (from edge to node)
        if (sourceShouldBeAnEge) {
            // Step 2 : activate the filter
            activateFilter(diagram, FILTER_EDGE_TO_NODE);
            TestsUtil.emptyEventsFromUIThread();
            // Step 1 : getting the DEdge (from node to edge)
        } else if (targetShouldBeAnEdge) {
            // Step 2 : activate the filter
            activateFilter(diagram, FILTER_NODE_TO_EDGE);
            TestsUtil.emptyEventsFromUIThread();
        }
    }

    /**
     * Ensures that an edge with the given source and target has been filtered.
     * 
     * @param sourceShouldBeAnEge
     *            indicates if the edge's source should be an edge
     * 
     * @param targetShouldBeAnEdge
     *            indicates if the edge's target should be an edge
     */
    private void checkEdgeHasBeenFilteredGraphically(boolean sourceShouldBeAnEge, boolean targetShouldBeAnEdge) {
        if (sourceShouldBeAnEge) {
            for (final DEdge edge : diagram.getEdges()) {
                if (edge.getSourceNode() instanceof DEdge && edge.getTargetNode() instanceof DNode && ((DNode) edge.getTargetNode()).getTarget() instanceof EAnnotation) {
                    assertFalse("The Ref to EAnnot should be visible", edge.isVisible());
                }
            }
        } else if (targetShouldBeAnEdge) {
            for (final DEdge edge : diagram.getEdges()) {
                if (edge.getTargetNode() instanceof DEdge && edge.getSourceNode() instanceof DNode && ((DNode) edge.getSourceNode()).getTarget() instanceof EAnnotation) {
                    assertFalse("The Ref to EAnnot should be visible", edge.isVisible());
                }
            }
        }
    }
}
