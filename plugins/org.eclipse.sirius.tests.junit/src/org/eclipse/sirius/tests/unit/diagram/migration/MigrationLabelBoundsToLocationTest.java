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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV670;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * That test aims to check that label nodes with Bounds as layout constraint are
 * correctly migrated. <BR>
 * See
 * {@link org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV670#migrateLabelConstraintFromBoundsToLocation(List)}
 * for more details.
 * 
 * @author fbarbin
 */
public class MigrationLabelBoundsToLocationTest extends SiriusDiagramTestCase {

    private String DESCRIPTION = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-3823/VP-3823.odesign";

    private String MODEL = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-3823/VP-3823.ecore";

    private String AIRD = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-3823/VP-3823.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL, DESCRIPTION, AIRD);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(AIRD, true), true);

        // Check that the migration is needed.
        Version migration = DiagramRepresentationsFileMigrationParticipantV670.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that there is no Bounds layout constraint left for GMF Nodes of type
     * 5001, 5002, 5003 and 5010.
     */
    public void testLabelsHaveLocationInsteadOfBounds() {

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
        Iterator<EObject> iterator = diagram.eAllContents();
        while (iterator.hasNext()) {
            EObject element = iterator.next();
            if (element instanceof Node) {
                Node node = (Node) element;
                int typeInt = SiriusVisualIDRegistry.getVisualID(node.getType());
                // whatever the label position (border or node), the layout
                // constraint should always be a location and not a bounds.
                // 5001: a label of a Bordered node on a node.
                // 5002: a node label
                // 5003: a node label (where the node is in a container)
                // 5010: a bordered node label (where the bordered node is in
                // a container)
                if (typeInt == NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID || typeInt == NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID
                        || typeInt == NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID || typeInt == NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID) {

                    LayoutConstraint layoutConstraint = node.getLayoutConstraint();

                    assertFalse("The node " + node.getElement() + " has a layout constraint of type Bounds instead of Location.", layoutConstraint instanceof Bounds);
                }
            }
        }
    }
}
