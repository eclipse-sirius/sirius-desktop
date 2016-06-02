/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV680;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterators;

/**
 * That test aims to check that edge label nodes with Location as layout
 * constraint are correctly migrated. <BR>
 * See
 * {@link org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV680#migrateEdgeLabelLocationToBounds(List)}
 * for more details.
 * 
 * @author mporhel
 */
public class MigrationEdgeLabelLocationToBoundsTest extends SiriusDiagramTestCase {

    private String MODEL = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4169/VP-4169.ecore";

    private String AIRD = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4169/VP-4169.aird";

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
        Version migration = DiagramRepresentationsFileMigrationParticipantV680.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that there is no Location layout constraint left for edge label GMF
     * Nodes which are edge labels.
     */
    public void testEdgeLabelsHaveLocationInsteadOfBounds() {

        List<Diagram> diagrams = new ArrayList<Diagram>();
        // retrieve all gmf diagram
        for (DView view : session.getOwnedViews()) {

            for (DRepresentation representation : new DViewQuery(view).getLoadedRepresentations()) {
                if (representation instanceof DDiagram) {
                    DDiagramGraphicalQuery query = new DDiagramGraphicalQuery((DDiagram) representation);
                    Option<Diagram> option = query.getAssociatedGMFDiagram();
                    if (option.some()) {
                        diagrams.add(option.get());
                    }
                }
            }
        }

        for (Diagram diagram : diagrams) {
            checkLabelWithBounds(diagram);
        }

    }

    private void checkLabelWithBounds(Diagram diagram) {
        Iterator<Node> iterator = Iterators.filter(diagram.eAllContents(), Node.class);
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (new ViewQuery(node).isForEdgeNameEditPart()) {
                LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                assertTrue("The label node " + node.getElement() + " should have a Bounds layout constraint.", layoutConstraint instanceof Bounds);
            }
        }
    }
}
