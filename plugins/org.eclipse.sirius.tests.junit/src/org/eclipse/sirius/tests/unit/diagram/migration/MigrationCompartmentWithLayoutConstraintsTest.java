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
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV680;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * Test that compartments do not have layout constraints after the migration.
 * See
 * {@link DiagramRepresentationsFileMigrationParticipantV680#migrateCompartmentsWithLayoutConstraints(List)}
 * for more details.
 * 
 * @author fbarbin
 * 
 */
public class MigrationCompartmentWithLayoutConstraintsTest extends SiriusDiagramTestCase {
    private String VSM = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4195/vp-4195.odesign";

    private String MODEL = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4195/vp4195.ecore";

    private String AIRD = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-4195/vp4195.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL, VSM, AIRD);
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
     * Test that there is no layout constraints left for GMF Node represent by
     * {@link DNodeContainerViewNodeContainerCompartment2EditPart#VISUAL_ID} or
     * {@link DNodeContainerViewNodeContainerCompartmentEditPart#VISUAL_ID} id.
     */
    public void testCompartmentsWithLayoutConstraints() {

        List<Diagram> diagrams = getGMFDiagrams();

        for (Diagram diagram : diagrams) {
            checkCompartmentWithLayoutConstraint(diagram);
        }

    }

    private List<Diagram> getGMFDiagrams() {
        List<Diagram> diagrams = new ArrayList<Diagram>();
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

        return diagrams;
    }

    private void checkCompartmentWithLayoutConstraint(Diagram diagram) {
        Iterator<EObject> iterator = Iterators.filter(diagram.eAllContents(), new IsCompartmentPredicate());
        while (iterator.hasNext()) {
            EObject node = iterator.next();
            assertTrue("The compartment " + node + " shouldn't have a layout constraint.", ((Node) node).getLayoutConstraint() == null);

        }
    }

    /**
     * A predicate that checks if the element represents a compartment.
     * 
     * @author fbarbin
     * 
     */
    private class IsCompartmentPredicate implements Predicate<EObject> {

        @Override
        public boolean apply(EObject arg0) {
            if (arg0 instanceof Node) {
                int id = SiriusVisualIDRegistry.getVisualID(((Node) arg0).getType());
                return id == DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID || id == DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID;
            }
            return false;
        }

    }
}
