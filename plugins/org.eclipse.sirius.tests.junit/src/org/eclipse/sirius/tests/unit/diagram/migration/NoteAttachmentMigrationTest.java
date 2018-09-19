/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.migration.IMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileResourceHandler;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.migration.NoteAttachmentWithoutSourceOrTargetMigrationParticipant;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.internal.migration.RepairGMFbendpointsMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Ensure that NoteAttachment without source or target has been removed.<br />
 * See {@link NoteAttachmentWithoutSourceOrTargetMigrationParticipant} for more details.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class NoteAttachmentMigrationTest extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/migration/do_not_migrate/noteAttachment/";

    private static final String MODEL_FILE_NAME = "My.ecore";

    private static final String REPRESENTATIONS_FILE_NAME = "My.aird";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = NoteAttachmentWithoutSourceOrTargetMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the NoteAttachment without source or target have been removed when
     * DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant is triggered before
     * NoteAttachmentWithoutSourceOrTargetMigrationParticipant.
     * 
     * Also test that running {@link RepairGMFbendpointsMigrationParticipant} on an edge without source does not provoke
     * any exception.
     */
    public void testNoteAttachmentCorrectlyRemovedAndBendpointRepairing() {
        Comparator<? super IMigrationParticipant> c = (o1, o2) -> o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
        checkMigration(c);
    }

    /**
     * Handler not doing any post load actions.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public static class RepresentationsFileResourceHandlerWithoutPostLoad extends RepresentationsFileResourceHandler {
        public RepresentationsFileResourceHandlerWithoutPostLoad(String loadedVersion) {
            super(loadedVersion);
        }

        @Override
        public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
            // doNothing
        }
    }

    /**
     * Check that the NoteAttachment without source or target have been removed when
     * NoteAttachmentWithoutSourceOrTargetMigrationParticipant is triggered before
     * DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant.
     */
    public void testNoteAttachmentCorrectlyRemoved2() {
        Comparator<? super IMigrationParticipant> c = (o1, o2) -> o2.getClass().getSimpleName().compareTo(o1.getClass().getSimpleName());
        checkMigration(c);
    }

    /**
     * Execute migration and check that note attachments are fixed when the migration is done. Standard migration
     * mechanism with random participant execution is disabled. Instead we execute these in a specified order.
     * 
     * @param c
     *            a comparator allowing to order the migration participants execution.
     */
    private void checkMigration(Comparator<? super IMigrationParticipant> c) {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();

        List<IMigrationParticipant> delegatesParticipants = new ArrayList<IMigrationParticipant>();
        IConfigurationElement[] config = EclipseUtil.getConfigurationElementsFor("org.eclipse.sirius.migrationParticipant"); //$NON-NLS-1$
        for (IConfigurationElement configurationElement : config) {
            try {
                String kind = configurationElement.getAttribute("kind"); //$NON-NLS-1$
                if (kind.equals(IMigrationParticipant.REPRESENTATIONSFILE_KIND)) {
                    Object contribution = configurationElement.createExecutableExtension("class"); //$NON-NLS-1$
                    if (contribution instanceof IMigrationParticipant) {
                        delegatesParticipants.add((IMigrationParticipant) contribution);
                    }
                }

            } catch (CoreException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(Status.WARNING, SiriusPlugin.ID, Messages.AbstractSiriusMigrationService_contributionInstantiationErrorMsg, e));
            }
        }

        // We sort the migration participants in the wanted order and trigger those after.
        delegatesParticipants.sort(c);

        AirdResource modelResource = (AirdResource) ModelUtils.createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), set);
        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, System.getProperty("file.encoding"));
        RepresentationsFileResourceHandler resourceHandler = new RepresentationsFileResourceHandlerWithoutPostLoad("11.1.0.201606300900");
        options.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);

        try {
            modelResource.load(options);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        DAnalysis analysis = (DAnalysis) modelResource.getContents().get(0);
        for (IMigrationParticipant contribution : delegatesParticipants) {
            contribution.postLoad((XMLResource) modelResource, "11.1.0.201606300900");
        }

        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save should update the version.
        String version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

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
