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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * Ensures that the migration of a VSM referencing another VSM successfully
 * migrates both base and referencing VSMs.
 * 
 * <p>
 * Relevants tickets :
 * <ul>
 * <li>VP-1943 : VSM Migration pb since VP-1802</li>
 * <li>VP-1981 : JUnit for VSMMigration pb since VP-1802</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class ReferencedModelResourceMigrationTest extends SiriusDiagramTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/migration/do_not_migrate/VP-1981/";

    private static final String BASE_VSM_NAME = "base.odesign";

    private static final String REFERENCING_VSM_NAME = "referencing.odesign";

    private URI baseVSMURI;

    private URI referencingVSMURI;

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Copying base VSM into workspace
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR + BASE_VSM_NAME, TEMPORARY_PROJECT_NAME + "/" + BASE_VSM_NAME);
        baseVSMURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + BASE_VSM_NAME, true);

        // Copying referencing VSM into workspace
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR + REFERENCING_VSM_NAME, TEMPORARY_PROJECT_NAME + "/" + REFERENCING_VSM_NAME);
        referencingVSMURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + REFERENCING_VSM_NAME, true);

    }

    @Override
    protected void tearDown() throws Exception {
        baseVSMURI = null;
        referencingVSMURI = null;

        super.tearDown();
    }

    /**
     * Ensures that, when migrating a VSM "referencing.odesign" reusing mappings
     * defined in the "base" VSM, both VSMs are correctly migrated.
     */
    public void testMigrationOfBaseVSMIsNotPartialWhenMigratingReferencingVSM() {

        // Step 1 : migrating referencing VSM
        // ModelerResourceFileMigration modelerResourceFileMigration = new
        // ModelerResourceFileMigrationImpl(referencingVSMURI);
        // modelerResourceFileMigration.run(new NullProgressMonitor());
        // modelerResourceFileMigration.dispose();

        // Step 2 : loading the 2 VSMs in a resource set
        ResourceSet rs = new ResourceSetImpl();
        Resource baseVSMResource = rs.getResource(baseVSMURI, true);
        DiagramDescription baseRepresentation = (DiagramDescription) ((Group) baseVSMResource.getContents().get(0)).getOwnedViewpoints().iterator().next().getOwnedRepresentations().get(0);

        Resource referencingVSMResource = rs.getResource(referencingVSMURI, true);
        DiagramDescription referencingRepresentation = (DiagramDescription) ((Group) referencingVSMResource.getContents().get(0)).getOwnedViewpoints().iterator().next().getOwnedRepresentations()
                .get(0);

        // Step 3 : ensure that migration was successful
        // Step 3.1 : edge mappings should have been migrated on referencing VSM
        EdgeMapping edgeMapping = getEdgeMapping(referencingRepresentation.getDefaultLayer(), "edge");
        assertNotNull("Migrated Edge Mapping should have a style", edgeMapping.getStyle());
        assertNotNull("Migrated Edge Mapping should have a center label style description", edgeMapping.getStyle().getCenterLabelStyleDescription());

        // Step 3.2 : edge mappings should have been migrated on base VSM
        edgeMapping = getEdgeMapping(baseRepresentation.getDefaultLayer(), "edge");
        assertNotNull("Migrated Edge Mapping should have a style", edgeMapping.getStyle());
        assertNotNull("Migrated Edge Mapping should have a center label style description", edgeMapping.getStyle().getCenterLabelStyleDescription());

    }
}
