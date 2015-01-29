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
package org.eclipse.sirius.tests.unit.diagram.layers;

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.api.mappings.edgeonedge.AbstractEdgeOnEdgeTest;

/**
 * Ensures that layer of Edges on Edges works correctly.
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
public class LayerEdgeOnEdgeTest extends AbstractEdgeOnEdgeTest {

    private static final String LAYER_EDGE = "Imbricated EdgeMapping";

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in manual refresh mode.
     */
    public void testEdgeLayerFromEdgeToNodeInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized diagram.
     */
    public void testEdgeLayerFromEdgeToNodeInManualRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        unsynchronizeDiagram(diagram);

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized sourceMapping.
     */
    public void testEdgeLayerFromEdgeToNodeInManualRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized targetMapping.
     */
    public void testEdgeLayerFromEdgeToNodeInManualRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in manual refresh mode and with unsynchronized mapping.
     */
    public void testEdgeLayerFromEdgeToNodeInManualRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in automatic refresh mode.
     */
    public void testEdgeLayerFromEdgeToNodeInAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized diagram.
     */
    public void testEdgeLayerFromEdgeToNodeInAutoRefreshUnsynchronizedDiagram() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        unsynchronizeDiagram(diagram);

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized sourceMapping.
     */
    public void testEdgeLayerFromEdgeToNodeInAutoRefreshUnsynchronizedSourceMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : unsynchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EAnnot TC1", "RefToEAnnot TC1");

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized targetMapping.
     */
    public void testEdgeLayerFromEdgeToNodeInAutoRefreshUnsynchronizedTargetMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : unsynchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : synchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "RefToEAnnot TC1");

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that Layer an edge from an edge to a node mode works as expected
     * in automatic refresh mode with unsynchronized mapping.
     */
    public void testEdgeLayerFromEdgeToNodeInAutoRefreshUnsynchronizedEdgeOnEdgeMapping() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // EMa TC1 (source edge mapping) : synchronized
        // EAnnot TC1 (target node mapping) : synchronized
        // RefToEAnnot TC1 (edge on edge mapping ) : unsynchronized
        unsynchronizeAllMappingsExcept("EMa TC1", "EAnnot TC1");

        genericTestEdgeLayerFromEdgeToNode();
    }

    /**
     * Ensures that the edge Layer modifies correctly the semantic and graphical
     * model, with undo/redo, editor reopenning... When the source of the Edge
     * is an edge and its target a Node.
     */
    private void genericTestEdgeLayerFromEdgeToNode() {
        genericTestEdgeLayer();
    }

    /**
     * Ensures that the edge Layer modifies correctly the graphical model, with
     * undo/redo, editor reopenning...
     */
    public void genericTestEdgeLayer() {

        // Step 1 : Check that the edges are visible before hidden

        for (final DEdge edge : diagram.getEdges()) {
            if (edge.getSourceNode() instanceof DEdge && edge.getTargetNode() instanceof DNode && "C1".equals(((DNode) edge.getTargetNode()).getName())) {
                assertTrue("The imbrecated edge mapping should be visible", edge.isVisible());
            }
        }

        // Step 2 : Layer edges

        desactiveLayerEdge();
        // Step 3 : check that edge has been deleted
        checkEdgeHasBeenActivateLayerGraphically();

        // Step 4 : testing undo/redo
        // Step 4.1 : Undo the Layer of the edge
        session.getTransactionalEditingDomain().getCommandStack().undo();

        for (final DEdge edge : diagram.getEdges()) {
            if (edge.getSourceNode() instanceof DEdge && edge.getTargetNode() instanceof DNode && "C1".equals(((DNode) edge.getTargetNode()).getName())) {
                assertTrue("The imbrecated edge mapping should be visible", edge.isVisible());
            }
        }

        // Step 4.2 : Redo the Layer of the edge
        session.getTransactionalEditingDomain().getCommandStack().redo();
        // -> semantic model should have been modified and edge should be
        // visible
        checkEdgeHasBeenActivateLayerGraphically();

        // Step 5 : refreshing diagram
        refresh(diagram);
        checkEdgeHasBeenActivateLayerGraphically();

        // Step 6 : Reopen editor
        closeAndReopenEditor();
        checkEdgeHasBeenActivateLayerGraphically();
    }

    /**
     * Layered the edge described by the given parameters.
     * 
     */
    private void desactiveLayerEdge() {
        // Step 1 : activate the Layer
        deactivateLayer(diagram, LAYER_EDGE);
        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Ensures that an edge with the given source and target has been Layered.
     * 
     */
    private void checkEdgeHasBeenActivateLayerGraphically() {

        for (final DEdge edge : diagram.getEdges()) {
            if (edge.getSourceNode() instanceof DEdge && edge.getTargetNode() instanceof DNode && "C1".equals(((DNode) edge.getTargetNode()).getName())) {
                assertFalse("The Ref to EAnnot should be visible", edge.isVisible());
            }
        }

    }
}
