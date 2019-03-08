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
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.collect.Iterables;

/**
 * TTest that the GMF Routing Style changed when Conditional Style is applied.
 * 
 * @author jdupont
 * 
 */
public class EdgeRoutingStyleEdgeConditionalStyleTest extends SiriusDiagramTestCase {

    private static final String DATA_UNIT_PROJECT_MODELING = "data/unit/style/VP-3372/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3372.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/" + DATA_UNIT_PROJECT_MODELING + "VP-3372.odesign";

    private static final String VIEWPOINT_NAME = "VP-3372";

    private static final String REPRESENTATION_DESC_NAME = "VP-3372 diagram";

    private static final String CLASS_NAME = "c4";

    private static final String CLASS_NAME_AFTER_RENAME = "c1";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_PROJECT_MODELING, SEMANTIC_RESOURCE_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, MODELER_PATH);
    }

    /**
     * Test the tree routing style is applied when conditional style is applied.
     */
    public void testTreeRoutingStyleIsAppliedWithConditionalStyle() {
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

        activateViewpoint(VIEWPOINT_NAME);

        DDiagram diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, semanticModel);
        DiagramDocumentEditor editor = (DiagramDocumentEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check by default the routing style is Manual
        checkRoutingStyle(editor, Routing.MANUAL_LITERAL, CLASS_NAME);

        // Rename the source node named c4 to c1
        Resource semanticResource = session.getSemanticResources().iterator().next();
        EClass c4 = (EClass) ((EPackage) semanticResource.getContents().get(0)).getEClassifier(CLASS_NAME);

        Command enableConditionalStyleCmd = SetCommand.create(session.getTransactionalEditingDomain(), c4, EcorePackage.Literals.ENAMED_ELEMENT__NAME, CLASS_NAME_AFTER_RENAME);
        session.getTransactionalEditingDomain().getCommandStack().execute(enableConditionalStyleCmd);

        TestsUtil.synchronizationWithUIThread();

        // Launch a refresh
        refresh(diagram);
        // Check after rename the routing style have changed and there is a
        // conditional style applied on edge
        checkRoutingStyle(editor, Routing.TREE_LITERAL, CLASS_NAME_AFTER_RENAME);
        checkLabel("CONDITIONAL_STYLE_APPLIED", CLASS_NAME_AFTER_RENAME, diagram);
    }

    /**
     * Check the routing style of the first edge with source that has name
     * <code>sourceNodeName</code>.
     * 
     * @param editor
     *            the opened editor
     * @param expectedRoutingStyle
     *            the expected routing style
     * @param sourceNodeName
     *            the edge's source node name
     */
    private void checkRoutingStyle(DiagramDocumentEditor editor, Routing expectedRoutingStyle, String sourceNodeName) {
        Iterable<IDiagramEdgeEditPart> connections = Iterables.filter(editor.getDiagramEditPart().getConnections(), IDiagramEdgeEditPart.class);

        boolean edgeFound = false;
        for (IDiagramEdgeEditPart connection : connections) {
            Edge edge = (Edge) connection.getModel();
            if (sourceNodeName.equals(((DNode) ((Node) edge.getSource()).getElement()).getName())) {
                edgeFound = true;
                final RoutingStyle rstyle = (RoutingStyle) edge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
                assertEquals("The routing style is incorrect", expectedRoutingStyle, rstyle.getRouting());
                break;
            }
        }
        if (!edgeFound) {
            fail("There is no edge that has a source with name " + sourceNodeName);
        }
    }

    /**
     * Check the label of the first edge with source that has name
     * <code>sourceNodeName</code>.
     * 
     * @param expectedLabelExpression
     *            the expected edge's label expression
     * @param sourceNodeName
     *            the edge's source node name
     * @param diagram
     *            the diagram containing the edge
     */
    private void checkLabel(String expectedLabelExpression, String sourceNodeName, DDiagram diagram) {
        Iterable<DEdge> edges = Iterables.filter(diagram.getDiagramElements(), DEdge.class);

        boolean edgeFound = false;
        for (final DEdge edge : edges) {
            if (sourceNodeName.equals(((DNode) edge.getSourceNode()).getName())) {
                edgeFound = true;
                assertEquals("The conditional style should be applied, the label is not the expected one.", expectedLabelExpression, edge.getName());
                break;
            }
        }
        if (!edgeFound) {
            fail("There is no edge that has a source with name " + sourceNodeName);
        }
    }
}
