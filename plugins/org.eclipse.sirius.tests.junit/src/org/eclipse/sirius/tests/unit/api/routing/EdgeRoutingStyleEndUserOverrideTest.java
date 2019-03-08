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
package org.eclipse.sirius.tests.unit.api.routing;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramCorePreferences;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.collect.Iterables;

/**
 * Test that the end user can to override the routing style of all new edges.
 * 
 * @author jdupont
 * 
 */
public class EdgeRoutingStyleEndUserOverrideTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/vp-4180/My.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/vp-4180/My.odesign";

    private static final String REPRESENTATION_DESC_NAME = "VP-4180";

    private DiagramDocumentEditor editor;

    private DDiagram diagram;

    private EClass source;

    private EClass target;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

        activateViewpoint("VP-4180");

        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        editor = (DiagramDocumentEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        Resource semanticResource = session.getSemanticResources().iterator().next();
        source = (EClass) ((EPackage) semanticResource.getContents().get(0)).getEClassifier("NewEClass1");
        target = (EClass) ((EPackage) semanticResource.getContents().get(0)).getEClassifier("NewEClass2");
    }

    /**
     * Create a new edge with enabled user specific default values, routing
     * style set to Oblique and check that the routing style is Oblique (in GMF
     * style and DEdge style) and is custom (the customFeatures list contains
     * routingStyle).
     * <P/>
     * Reset style properties to default values and check nothing changes
     * <P/>
     * Disable user specific default values in preference
     * <P/>
     * Reset style properties to default values and check that the routing is
     * the VSM one
     */
    public void testCreateEdgeEnabledUserSpecificOblic() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * There is no edge that has a source with name NewEClass1 junit.framework.AssertionFailedError: There is no
             * edge that has a source with name NewEClass1 at
             * org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest.
             * checkRoutingStyleCustomStyle(EdgeRoutingStyleEndUserOverrideTest.java:228) at
             * org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest.
             * testCreateEdgeEnabledUserSpecificOblic(EdgeRoutingStyleEndUserOverrideTest.java:101)
             */
            return;
        }

        // Enabled user specific default values, routing style set to Oblique
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, true);
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_LINE_STYLE, SiriusDiagramCorePreferences.PREF_LINE_STYLE_DEFAULT_VALUE);

        // Create a new edge with tool createTree
        applyEdgeCreationTool("CreateTree", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));
        // Check that the routing style is Oblique (In GMF style and DEdge
        // style) and is custom (the customFeatures list contains routingStyle)
        checkRoutingStyleCustomStyle(editor, Routing.MANUAL_LITERAL, EdgeRouting.STRAIGHT_LITERAL, "NewEClass1", 2, true);
        // Reset style properties to default value
        Iterable<DEdge> edges = Iterables.filter(diagram.getDiagramElements(), DEdge.class);
        resetStylePropertiesToDefaultValues(edges.iterator().next(), diagram);
        // Check nothing changes. Check too the edge size does not changed
        checkRoutingStyleCustomStyle(editor, Routing.MANUAL_LITERAL, EdgeRouting.STRAIGHT_LITERAL, "NewEClass1", 2, true);
        // Disabled user specific default values in preference
        resetDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE);
        // Reset style properties to default values and check the routing style
        // is the VSM one (in GMF Style and DEge style)
        resetStylePropertiesToDefaultValues(edges.iterator().next(), diagram);
        checkRoutingStyleCustomStyle(editor, Routing.TREE_LITERAL, EdgeRouting.TREE_LITERAL, "NewEClass1", 2, false);
    }

    /**
     * Create a new edge with enabled user specific default values, routing
     * style set to Rectilinear and check that the routing style is Rectilinear
     * (in GMF style and DEdge style) and is custom (the customFeatures list
     * contains routingStyle).
     * <P/>
     * Reset style properties to default values and check nothing changes
     * <P/>
     * Disable user specific default values in preference
     * <P/>
     * Reset style properties to default values and check that the routing is
     * the VSM one
     */
    public void testCreateEdgeEnabledUserSpecificRectilinear() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * There is no edge that has a source with name NewEClass1 junit.framework.AssertionFailedError: There is no
             * edge that has a source with name NewEClass1 at
             * org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest.
             * checkRoutingStyleCustomStyle(EdgeRoutingStyleEndUserOverrideTest.java:228) at
             * org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest.
             * testCreateEdgeEnabledUserSpecificOblic(EdgeRoutingStyleEndUserOverrideTest.java:101)
             */
            return;
        }

        // Enabled user specific default values, routing style set to
        // Rectilinear
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, true);
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_LINE_STYLE, EdgeRouting.MANHATTAN);

        // Create a new edge with tool createTree
        applyEdgeCreationTool("CreateTree", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));
        // Check that the routing style is Rectilinear (In GMF style and DEdge
        // style) and is custom (the customFeatures list contains routingStyle)
        checkRoutingStyleCustomStyle(editor, Routing.RECTILINEAR_LITERAL, EdgeRouting.MANHATTAN_LITERAL, "NewEClass1", 2, true);
        // Reset style properties to default value
        Iterable<DEdge> edges = Iterables.filter(diagram.getDiagramElements(), DEdge.class);
        resetStylePropertiesToDefaultValues(edges.iterator().next(), diagram);
        // Check nothing changes. Check too the edge size does not changed
        checkRoutingStyleCustomStyle(editor, Routing.RECTILINEAR_LITERAL, EdgeRouting.MANHATTAN_LITERAL, "NewEClass1", 2, true);
        // Disabled user specific default values in preference
        resetDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE);
        // Reset style properties to default values and check the routing style
        // is the VSM one (in GMF Style and DEge style)
        resetStylePropertiesToDefaultValues(edges.iterator().next(), diagram);
        checkRoutingStyleCustomStyle(editor, Routing.TREE_LITERAL, EdgeRouting.TREE_LITERAL, "NewEClass1", 2, false);
    }

    /**
     * Create a new edge with enabled user specific default values, routing
     * style set to Tree and check that the routing style is Tree (in GMF style
     * and DEdge style) and is custom (the customFeatures list contains
     * routingStyle).
     * <P/>
     * Reset style properties to default values and check nothing changes
     * <P/>
     * Disable user specific default values in preference
     * <P/>
     * Reset style properties to default values and check that the routing is
     * the VSM one
     */
    public void testCreateEdgeEnabledUserSpecificTree() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * There is no edge that has a source with name NewEClass1 junit.framework.AssertionFailedError: There is no
             * edge that has a source with name NewEClass1 at
             * org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest.
             * checkRoutingStyleCustomStyle(EdgeRoutingStyleEndUserOverrideTest.java:228) at
             * org.eclipse.sirius.tests.unit.api.routing.EdgeRoutingStyleEndUserOverrideTest.
             * testCreateEdgeEnabledUserSpecificOblic(EdgeRoutingStyleEndUserOverrideTest.java:101)
             */
            return;
        }

        // Enabled user specific default values, routing style set to Tree
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE, true);
        changeDiagramPreference(SiriusDiagramCorePreferences.PREF_LINE_STYLE, EdgeRouting.TREE);

        // Create a new edge with tool createOblique
        applyEdgeCreationTool("CreateOblique", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));
        // Check that the routing style is Tree (In GMF style and DEdge
        // style) and is custom (the customFeatures list contains routingStyle)
        checkRoutingStyleCustomStyle(editor, Routing.TREE_LITERAL, EdgeRouting.TREE_LITERAL, "NewEClass1", 2, true);
        // Reset style properties to default value
        Iterable<DEdge> edges = Iterables.filter(diagram.getDiagramElements(), DEdge.class);
        resetStylePropertiesToDefaultValues(edges.iterator().next(), diagram);
        // Check nothing changes. Check too the edge size does not changed
        checkRoutingStyleCustomStyle(editor, Routing.TREE_LITERAL, EdgeRouting.TREE_LITERAL, "NewEClass1", 2, true);
        // Disabled user specific default values in preference
        resetDiagramPreference(SiriusDiagramCorePreferences.PREF_ENABLE_OVERRIDE);
        // Reset style properties to default values and check the routing style
        // is the VSM one (in GMF Style and DEge style)
        resetStylePropertiesToDefaultValues(edges.iterator().next(), diagram);
        checkRoutingStyleCustomStyle(editor, Routing.MANUAL_LITERAL, EdgeRouting.STRAIGHT_LITERAL, "NewEClass1", 2, false);
    }

    /**
     * Check the routing style in GMF style and DEdge style and is custom (The
     * customFeatures list contains routingStyle). Check too the size of the
     * edge.
     * 
     * @param editor
     *            the editor opened
     *            the opened editor
     * @param expectedGMFRoutingLiteral
     *            the expected routing style literal to check
     * @param expectedDEdgeRoutingLiteral
     *            the expected edge routing literal to check
     * @param sourceNodeName
     *            the edge's source node name
     * @param expectedEdgeSize
     *            the expected edge's size to check
     */
    private void checkRoutingStyleCustomStyle(DiagramDocumentEditor editor, Routing expectedGMFRoutingLiteral, EdgeRouting expectedDEdgeRoutingLiteral, String sourceNodeName,
            Integer expectedEdgeSize, boolean customFeature) {
        boolean edgeFound = false;
        TestsUtil.synchronizationWithUIThread();
        Iterable<IDiagramEdgeEditPart> connections = Iterables.filter(editor.getDiagramEditPart().getConnections(), IDiagramEdgeEditPart.class);

        for (IDiagramEdgeEditPart connection : connections) {
            Edge edge = (Edge) connection.getModel();
            if (sourceNodeName.equals(((DNode) ((Node) edge.getSource()).getElement()).getName())) {
                edgeFound = true;
                DEdge dEdge = (DEdge) edge.getElement();
                final EdgeRouting edgeRouting = ((EdgeStyle) dEdge.getStyle()).getRoutingStyle();
                assertEquals("The DEdge routing style is incorrect.", expectedDEdgeRoutingLiteral, edgeRouting);
                if (customFeature) {
                    String routingStyleValue = DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName();
                    assertTrue("The customFeatures list should contain " + routingStyleValue, dEdge.getStyle().getCustomFeatures().contains(routingStyleValue));
                }
                final RoutingStyle rstyle = (RoutingStyle) edge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
                assertEquals("The GMF routing Style is incorrect.", expectedGMFRoutingLiteral, rstyle.getRouting());
                assertEquals("The edge's size is wrong.", expectedEdgeSize, ((EdgeStyle) dEdge.getStyle()).getSize());
                break;
            }
        }
        if (!edgeFound) {
            fail("There is no edge that has a source with name " + sourceNodeName);
        }
    }
}
