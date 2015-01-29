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
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.ui.business.internal.migration.SequenceDiagramRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * Ensures that Sequence Message flag reset migration is correctly done for
 * sequence diagrams.
 * 
 * <p>
 * <li><b>Relevant tickets</b> : VP-4808</li>
 * </p>
 */
public class SequenceMessageFlagResetMigrationTest extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = UNIT_DATA_ROOT + "migration/do_not_migrate/message_flag/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram";

    private static final String SEMANTIC_RESOURCE_NAME = "message_flag.interactions";

    private static final String SESSION_RESOURCE_NAME = "message_flag.aird";

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
        Version messageFlagMigration = SequenceDiagramRepresentationsFileMigrationParticipant.MESSAGE_FLAG_MIGRATION_VERSION;
        assertTrue("The message flag reset migration must be required on test data.", loadedVersion == null || messageFlagMigration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check the behavior of the message flag reset migration for sequence
     * diagrams.
     */
    public void testSequenceMessageFlagMigrationDone() {
        // The load must migrate the data.
        assertEquals("The session should not be dirty on opening after the migration.", SessionStatus.SYNC, session.getStatus());

        // The data contains 6 messages (sync call, return, creation,
        // destruction, simple message).
        Option<SequenceDDiagram> seqDiag = getSequenceDDiagramOfType(REPRESENTATION_NAME, REPRESENTATION_TYPE);
        assertTrue("Sequence diagram " + REPRESENTATION_NAME + " not found in " + getPath() + getSessionModel(), seqDiag.some());
        List<DEdge> edges = seqDiag.get().getEdges();
        assertEquals("The diagram should contain 6 edges.", 6, edges.size());
        for (DEdge edge : edges) {
            // Check the flag migration.
            Iterable<AbsoluteBoundsFilter> flags = Iterables.filter(edge.getGraphicalFilters(), AbsoluteBoundsFilter.class);
            assertEquals("Each Sequence Message must have one and only one flag.", 1, Iterables.size(flags));
            AbsoluteBoundsFilter absBoundsFlag = Iterables.getFirst(flags, null);
            assertEquals("Flag on Sequence Message should have the default x value.", DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_X().getDefaultValue(), absBoundsFlag.getX());
            assertEquals("Flag on Sequence Message should have the default width value.", DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Width().getDefaultValue(), absBoundsFlag.getWidth());
        }

        openSequenceDiagramOfType(REPRESENTATION_NAME, REPRESENTATION_TYPE);

        // The layout done on opening should change the diagram and make the
        // session dirty
        assertEquals("The session should not be dirty after the migrated Sequence diagram opening.", SessionStatus.SYNC, session.getStatus());
    }

    /**
     * Check the behavior of the message flag reset migration for sequence
     * diagrams.
     */
    public void testSequenceMessageFlagMigrationDoneOnce() {
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
