/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.internal.migration.SnapBackDistantLabelsMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Test for {@link SnapBackDistantLabelsMigrationParticipant}.
 * 
 * @author lredor
 */
public class SnapBackDistantLabelsMigrationTest extends SiriusTestCase {

    private static final String UNEXPECTED_DATA_MESSAGE = "An expected diagram has not been found in data.";

    private static final String PATH = "data/unit/migration/do_not_migrate/edgeLabelMove/";

    private static final String SESSION_RESOURCE_NAME = "edgeLabelsMoveTest.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "edgeLabelsMoveTest.ecore";

    private static final String VSM_RESOURCE_NAME = "VSMForEdgeLabelsMoveTest.odesign";

    private Resource sessionResource;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, VSM_RESOURCE_NAME);
        URI sessionResourceURI = URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true);
        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        sessionResource = session.getSessionResource();

    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new SnapBackDistantLabelsMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the size of the diagrams are the same as non corrupted one after migration.
     */
    public void testDiagramSizeAfterMigration() {
        String suffix = "_corrupted";
        UnmodifiableIterator<Diagram> diagrams = Iterators.filter(sessionResource.getAllContents(), Diagram.class);
        Diagram diagram1 = null;
        Diagram corruptedDiagram1 = null;
        Diagram diagram2 = null;
        Diagram corruptedDiagram2 = null;
        Diagram diagram3 = null;
        Diagram corruptedDiagram3 = null;
        Diagram diagram4 = null;
        Diagram corruptedDiagram4 = null;

        while (diagrams.hasNext()) {
            Diagram diagram = diagrams.next();

            String diagramName = new DRepresentationQuery(((DDiagram) diagram.getElement()), session).getRepresentationDescriptor().getName();
            if (diagramName.startsWith("DiagWithNode")) {
                if (diagramName.endsWith(suffix)) {
                    corruptedDiagram1 = diagram;
                } else {
                    diagram1 = diagram;
                }
            } else if (diagramName.startsWith("DiagramWithSmall")) {
                if (diagramName.endsWith(suffix)) {
                    corruptedDiagram2 = diagram;
                } else {
                    diagram2 = diagram;
                }
            } else if (diagramName.startsWith("MyDiagram")) {
                if (diagramName.endsWith(suffix)) {
                    corruptedDiagram3 = diagram;
                } else {
                    diagram3 = diagram;
                }
            } else {
                if (diagramName.endsWith(suffix)) {
                    corruptedDiagram4 = diagram;
                } else {
                    diagram4 = diagram;
                }
            }
        }

        compareDiagram(corruptedDiagram1, diagram1);
        compareDiagram(corruptedDiagram2, diagram2);
        compareDiagram(corruptedDiagram3, diagram3);
        // This diag is a specific case with a node with huge coordinates.
        compareDiagram(corruptedDiagram4, diagram4);
    }

    /**
     * Compare a diagram that must be fixed during the migration, with another one.
     * 
     * @param corruptedDiagram
     *            Diagram that must be OK after migration
     * @param diagram
     *            Diagram to compare with
     */
    private void compareDiagram(Diagram corruptedDiagram, Diagram diagram) {
        assertNotNull(UNEXPECTED_DATA_MESSAGE, diagram);
        assertNotNull(UNEXPECTED_DATA_MESSAGE, corruptedDiagram);
        assertEquals("Corrupted diagram " + new DRepresentationQuery(((DDiagram) corruptedDiagram.getElement())).getRepresentationDescriptor().getName()
                + " should have the same bounding box as other diagram after migration.", calculateLocationBoundingBox(diagram), calculateLocationBoundingBox(corruptedDiagram));
    }

    /**
     * Calculates the bounding box around node children location of given diagram.
     * 
     * @param diagram
     *            given diagram
     * @return the diagram bounding box
     */
    public static final Rectangle calculateLocationBoundingBox(Diagram diagram) {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Node node : Iterables.filter(diagram.getChildren(), Node.class)) {
            LayoutConstraint constraint = node.getLayoutConstraint();
            if (constraint instanceof Location) {
                Location location = (Location) constraint;
                minX = Math.min(minX, location.getX());
                maxX = Math.max(maxX, location.getX());
                minY = Math.min(minY, location.getY());
                maxY = Math.max(maxY, location.getY());
            }
        }

        PrecisionRectangle rect = new PrecisionRectangle();
        rect.setPreciseWidth(maxX - minX);
        rect.setPreciseHeight(maxY - minY);
        rect.setPreciseX(minX);
        rect.setPreciseY(minY);
        return rect;
    }
}
