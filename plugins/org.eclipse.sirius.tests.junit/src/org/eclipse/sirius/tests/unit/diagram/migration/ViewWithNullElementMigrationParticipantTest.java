/*******************************************************************************
 * Copyright (c) 2016, 2021 THALES GLOBAL SERVICES.
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.IMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileResourceHandler;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.migration.ViewWithNullElementMigrationParticipant;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.internal.migration.RepairGMFbendpointsMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.internal.helper.XmlHelper;
import org.eclipse.sirius.tests.unit.diagram.migration.NoteAttachmentMigrationTest.RepresentationsFileResourceHandlerWithoutPostLoad;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Ensure that Note, Text and Note Attachment whose element has not been set are fixed during migration.<br />
 * See {@link ViewWithNullElementMigrationParticipant} for more details.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public class ViewWithNullElementMigrationParticipantTest extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/migration/do_not_migrate/viewWithoutElement/";

    private static final String MODEL_FILE_NAME = "My.ecore";

    private static final String REPRESENTATIONS_FILE_NAME = "My.aird";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version migration = ViewWithNullElementMigrationParticipant.MIGRATION_VERSION;

        // Check that the migration is needed.
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(uri, true);

        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);

        Collection<String> xsiNil = XmlHelper.getXmlAttributes(uri, "xsi:nil");
        assertTrue("The test data must not contain any xsi:nil attributes", xsiNil.isEmpty());

    }

    /**
     * Check that Note, Text and Note Attachment whose element has not been set are fixed during migration when
     * DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant is triggered before
     * ViewWithNullElementMigrationParticipant.
     * 
     * Also test that running {@link RepairGMFbendpointsMigrationParticipant} on an edge without source does not provoke
     * any exception.
     */
    public void testNoteAttachmentCorrectlyRemovedAndBendpointRepairing() {
        Comparator<? super IMigrationParticipant> c = (o1, o2) -> o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
        checkMigration(c);
    }

    /**
     * Check that Note, Text and Note Attachment whose element has not been set are fixed during migration when
     * ViewWithNullElementMigrationParticipant is triggered before
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

        // We sort the migration participants in the wanted order and trigger
        // those after.
        delegatesParticipants.sort(c);

        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, System.getProperty("file.encoding"));
        RepresentationsFileResourceHandler resourceHandler = new RepresentationsFileResourceHandlerWithoutPostLoad(ViewWithNullElementMigrationParticipant.MIGRATION_VERSION.toString());
        options.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);

        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), set);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (IMigrationParticipant contribution : delegatesParticipants) {
            contribution.postLoad((XMLResource) analysis.eResource(), ViewWithNullElementMigrationParticipant.MIGRATION_VERSION.toString());
        }

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

        checkMigrationEffect(analysis);
    }

    private void checkMigrationEffect(DAnalysis analysis) {
        Collection<String> xsiNil = XmlHelper.getXmlAttributes(analysis.eResource().getURI(), "xsi:nil");
        assertEquals("The test data must now contain xsi:nil attributes", 8, xsiNil.size());

        Iterator<EObject> invalidViews = null;
        for (DView dView : analysis.getOwnedViews()) {
            for (DDiagram dDiagram : Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DDiagram.class)) {
                DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                    Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                    invalidViews = Iterators.filter(gmfDiagram.eAllContents(), Predicates.and(Predicates.instanceOf(View.class), new Predicate<EObject>() {
                        @Override
                        public boolean apply(EObject input) {
                            View view = (View) input;
                            String viewType = view.getType();
                            if (!view.isSetElement()) {
                                return ViewType.NOTE.equals(viewType) || ViewType.TEXT.equals(viewType) || ViewType.NOTEATTACHMENT.equals(viewType);
                            }
                            return false;
                        }
                    }));
                }
            }
        }
        assertTrue("No more invalid views should be found in the migrated aird.", !invalidViews.hasNext());
    }
}
