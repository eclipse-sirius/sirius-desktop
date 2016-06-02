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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV690;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * That test aims as checking that GMF node(s) with the attribute "visible" set
 * to false are correctly set to true when the corresponding DDiagramElement is
 * visible. <BR>
 * see
 * {@link DiagramRepresentationsFileMigrationParticipantV690#migrateVisibilityInconsistenciesBetweenGMFNodeAndDDiagramElement(List)}
 * for more details.
 * 
 * @author fbarbin
 */
public class MigrationInconsistentGMFVisibilityTest extends SiriusDiagramTestCase {

    private String DESCRIPTION = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4184/My.odesign";

    private String MODEL = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4184/My.ecore";

    private String AIRD = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4184/My.aird";

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
        Version migration = DiagramRepresentationsFileMigrationParticipantV690.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that the GMF node visibility is the same than the DDiagramElement
     * visibility after migration.
     */
    public void testGMFViewpointVisibilityConsistency() {

        DView view = session.getOwnedViews().iterator().next();
        DDiagram ddiagram = (DDiagram) new DViewQuery(view).getLoadedRepresentations().get(0);
        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(ddiagram);
        Option<Diagram> option = query.getAssociatedGMFDiagram();
        if (option.some()) {
            Iterator<EObject> iterator = Iterators.filter(option.get().eAllContents(), new Predicate<EObject>() {
                @Override
                public boolean apply(EObject arg0) {
                    if (arg0 instanceof Node) {
                        int typeInt = SiriusVisualIDRegistry.getVisualID(((Node) arg0).getType());
                        return typeInt == DNode4EditPart.VISUAL_ID;
                    }
                    return false;
                }
            });

            while (iterator.hasNext()) {
                Node node = (Node) iterator.next();
                assertTrue("The GMF Node visibility and the DDiagramElement visibility should be consistent", node.isVisible() == ((DDiagramElement) node.getElement()).isVisible());
            }
        } else {
            fail("No GMF diagram found");
        }
    }
}
