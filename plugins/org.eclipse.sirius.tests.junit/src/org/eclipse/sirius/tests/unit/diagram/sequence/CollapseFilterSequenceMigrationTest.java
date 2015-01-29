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
package org.eclipse.sirius.tests.unit.diagram.sequence;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.sequence.ui.business.internal.migration.SequenceDiagramRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.ICollapseMode;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Ensures that collapse filter migration is correctly done for sequence
 * diagrams.
 * 
 * <p>
 * <li><b>Relevant tickets</b> : DOREMI-2897 & VP-3868</li>
 * </p>
 */
public class CollapseFilterSequenceMigrationTest extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = UNIT_DATA_ROOT + "migration/do_not_migrate/collapse/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram";

    private static final String SEMANTIC_RESOURCE_NAME = "collapse.interactions";

    private static final String SESSION_RESOURCE_NAME = "collapse.aird";

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return SiriusTestsPlugin.PLUGIN_ID + PATH;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected String getSemanticModel() {
        return SEMANTIC_RESOURCE_NAME;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected String getTypesSemanticModel() {
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected String getSessionModel() {
        return SESSION_RESOURCE_NAME;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(getPath() + getSessionModel(), true), true);

        // Check that the sequence migration is needed.
        Version collapseFilterMigration = SequenceDiagramRepresentationsFileMigrationParticipant.GMF_BOUNDS_MIGRATION_VERSION;
        assertTrue("The collapse filter migration must be required on test data.", loadedVersion == null || collapseFilterMigration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check the behavior of the collapse filter migration for sequence
     * diagrams.
     */
    public void testSequenceCollapseFilterMigrationDone() {
        // The load must migrate the data.

        // The data contains 5 collapsed executions && 1 indirectly collapsed
        // state.
        openSequenceDiagramOfType(REPRESENTATION_NAME, REPRESENTATION_TYPE);
        List<CollapseFilter> collapseFilters = Lists.newArrayList(Iterators.filter(sequenceDDiagram.eAllContents(), CollapseFilter.class));
        List<IndirectlyCollapseFilter> indirectCollapseFilters = Lists.newArrayList(Iterables.filter(collapseFilters, IndirectlyCollapseFilter.class));
        assertEquals("The model should contains 4 indirectly collapse filters.", 4, indirectCollapseFilters.size());
        assertEquals("The model should contains 9 collapse filters : 5 CollapseFilter and 4 IndirectlyCollapseFilter.", 9, collapseFilters.size());

        // Check the bounds migration.
        for (CollapseFilter filter : collapseFilters) {
            assertTrue("The collapse filter migration should have set the size values of the filters.", filter.getHeight() != 0);
            assertTrue("The collapse filter migration should have set the size values of the filters.", filter.getWidth() != 0);

            DNode collpasedNode = (DNode) filter.eContainer();
            Node gmfNode = Iterables.filter(new EObjectQuery(collpasedNode).getInverseReferences(NotationPackage.eINSTANCE.getView_Element()), Node.class).iterator().next();
            Size gmfSize = (Size) gmfNode.getLayoutConstraint();

            assertEquals("In sequence collapsed node should have a 0 width", ICollapseMode.COLLAPSED_DIMENSION.width, gmfSize.getWidth());
            assertEquals("In sequence collapse should have no effect on height", filter.getHeight(), gmfSize.getHeight());
        }
    }

    /**
     * Check the behavior of the collapse filter migration for sequence
     * diagrams.
     */
    public void testSequenceCollapseFilterMigrationDoneOnce() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME);

        URI copiedSession = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true);
        checkRepresentationFileMigrationStatus(copiedSession, true);

        Session session2 = null;
        // The load must migrate the data.
        try {
            session2 = SessionFactory.INSTANCE.createSession(copiedSession, new NullProgressMonitor());
            session2.open(new NullProgressMonitor());
        } catch (Exception e) {
            // TODO: handle exception
        }

        AirDResouceQuery query = new AirDResouceQuery((AirdResource) session2.getSessionResource());
        Option<DAnalysis> dAnalysis = query.getDAnalysis();

        // Check that the version attribute has not been set by the migration,
        // it will be set during save.
        assertTrue(dAnalysis.some());
        String version = dAnalysis.get().getVersion();
        assertTrue("The migration should still be marked as needed for next load.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // Save
        session2.save(new NullProgressMonitor());

        // Check that the version attribute is set during save.
        version = dAnalysis.get().getVersion();
        assertFalse("The migration should be done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // Check that the version attribute has been serialized.
        checkRepresentationFileMigrationStatus(session2.getSessionResource().getURI(), false);

        // close the session
        session2.close(new NullProgressMonitor());
    }
}
