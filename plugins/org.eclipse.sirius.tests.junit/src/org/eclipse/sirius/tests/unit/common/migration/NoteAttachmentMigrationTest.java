/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.migration;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.migration.NoteAttachmentWithoutSourceOrTargetMigrationParticipant;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Ensure that NoteAttachment without source or target has been removed.<br />
 * See {@link NoteAttachmentWithoutSourceOrTargetMigrationParticipant} for more
 * details.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class NoteAttachmentMigrationTest extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/migration/do_not_migrate/noteAttachment/";

    private static final String REPRESENTATIONS_FILE_NAME = "My.aird";

    private static final String MODEL_FILE_NAME = "My.ecore";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = NoteAttachmentWithoutSourceOrTargetMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the NoteAttachment without source or target have been removed.
     */
    public void testNoteAttachmentCorrectlyRemoved() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        assertNotNull("Analysis is not retrieved.", analysis);

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We can now check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(analysis);
    }

    private void checkMigrationEffect(DAnalysis analysis) {
        Iterator<EObject> noteAttachmentWithoutSourceOrTarget = null;
        for (DView dView : analysis.getOwnedViews()) {
            for (DDiagram dDiagram : Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DDiagram.class)) {
                DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                    Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                    noteAttachmentWithoutSourceOrTarget = Iterators.filter(gmfDiagram.eAllContents(), Predicates.and(Predicates.instanceOf(Connector.class), new Predicate<EObject>() {
                        @Override
                        public boolean apply(EObject input) {
                            Connector connector = (Connector) input;
                            if (ViewType.NOTEATTACHMENT.equals(connector.getType())) {
                                return connector.getSource() == null || connector.getTarget() == null;
                            }
                            return false;
                        }
                    }));
                }
            }
        }
        assertTrue("It should not have NoteAttachment without source or target in this representations file.",
                noteAttachmentWithoutSourceOrTarget == null || !noteAttachmentWithoutSourceOrTarget.hasNext());
    }
}
