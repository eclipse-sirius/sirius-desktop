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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV690;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * Test that egde style and customFeatures are as expected after migration.
 * 
 * @author jdupont
 * 
 */
public class MigrationRoutingStyleEndUserOverrideTest extends SiriusDiagramTestCase {

    private String MODEL = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4180/My.ecore";

    private String AIRD = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4180/My.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(Collections.singletonList(MODEL), Collections.<String> emptyList(), AIRD);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(AIRD, true), true);

        // Check that the migration is needed.
        Version migration = DiagramRepresentationsFileMigrationParticipantV690.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that the edge style is correct and that the customFeatures is not
     * empty.
     */
    public void testMigration() {
        DDiagram diagram = (DDiagram) getRepresentations("Entities").toArray()[0];
        // Open the representation root package entities
        DiagramDocumentEditor editor = (DiagramDocumentEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        // Check that the reference between C4 and C1 is rectilinear in GMF
        // Style
        // Check that the reference between C4 and C1 is rectilinear (manathan)
        // in DEdge Style
        // Check that the customFeatures contains the feature routingStyle
        checkEdgeStyle(editor, Routing.RECTILINEAR_LITERAL, EdgeRouting.MANHATTAN_LITERAL, "C4", true);
        // Check that the customFeatures does not contain the feature
        // routingStyle
        checkEdgeStyle(editor, Routing.MANUAL_LITERAL, EdgeRouting.STRAIGHT_LITERAL, "C2", false);
        checkEdgeStyle(editor, Routing.TREE_LITERAL, EdgeRouting.TREE_LITERAL, "C3", false);
        checkEdgeStyle(editor, Routing.TREE_LITERAL, EdgeRouting.TREE_LITERAL, "C5", false);
    }

    private void checkEdgeStyle(DiagramDocumentEditor editor, Routing routingLiteral, EdgeRouting edgeRoutingLiteral, String sourceNodeName, boolean customFeature) {
        Iterable<IDiagramEdgeEditPart> connections = Iterables.filter(editor.getDiagramEditPart().getConnections(), IDiagramEdgeEditPart.class);
        boolean edgeFound = false;

        for (IDiagramEdgeEditPart connection : connections) {
            Edge edge = (Edge) connection.getModel();
            if (sourceNodeName.equals(((DNodeList) ((Node) edge.getSource()).getElement()).getName())) {
                edgeFound = true;
                DEdge dEdge = (DEdge) edge.getElement();
                final EdgeRouting edgeRouting = ((EdgeStyle) dEdge.getStyle()).getRoutingStyle();
                assertEquals("The DEdge routing style is incorrect.", edgeRoutingLiteral, edgeRouting);
                String routingStyleValue = DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName();
                if (customFeature) {
                    assertTrue("The customFeatures list should contain " + routingStyleValue, dEdge.getStyle().getCustomFeatures().contains(routingStyleValue));
                } else {
                    assertFalse("The customFeatures list should not contain " + routingStyleValue, dEdge.getStyle().getCustomFeatures().contains(routingStyleValue));
                }
                final RoutingStyle rstyle = (RoutingStyle) edge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
                assertEquals("The GMF routing style is incorrect.", routingLiteral, rstyle.getRouting());
                break;
            }
        }
        if (!edgeFound) {
            fail("There is no edge that has a source with name " + sourceNodeName);
        }
    }

}
