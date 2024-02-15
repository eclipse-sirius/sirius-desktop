/*******************************************************************************
 * Copyright (c) 2018, 2024 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.TechnicalUidMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.internal.helper.XmlHelper;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.osgi.framework.Version;

/**
 * Test class to test the migration of to set IdentifiedElement.uid for DRepresentation instances.
 * 
 * @author lfasani
 */
public class TechnicalUidMigrationTest extends SiriusTestCase {

    private static final String DREPRESENTATION_INITIAL_XMIID = "_sH154InWEeiSiblR-QI2Dg";

    private static final String DREPRESENTATION_INITIAL_UID = "_sGbkoInWEeiSiblR-QI2Dg";

    private static final String PATH = "/data/unit/migration/do_not_migrate/technicalUid/";

    private static final String SESSION_RESOURCE_NAME = "technicalUid.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "technicalUid.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = TechnicalUidMigrationParticipant.MIGRATION_VERSION;

        // Check that the representation file migration is needed.
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(uri, true);
        assertTrue("The technical uid migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);

        // check the file contains DRepresentationContainer
        Collection<String> uidAttributes = XmlHelper.getXmlAttributes(uri, "uid");
        assertEquals("The uid attribute should be only on the DDiagram", 1, uidAttributes.size());
        Collection<String> xmiIdAttributes = XmlHelper.getXmlAttributes(uri, "xmi:id");
        assertEquals("The xmi:id attribute should on every elements", 19, xmiIdAttributes.size());
        assertTrue(xmiIdAttributes.contains(DREPRESENTATION_INITIAL_XMIID) && uidAttributes.contains(DREPRESENTATION_INITIAL_UID));
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }

    /**
     * Check the migration is done and does not produce errors during representation load.
     */
    public void testTechnicalUidMigrationDone() {
        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), set);
        } catch (IOException e) {
            failCheckData();
        }

        Collection<String> initialXmiIdAttributes = XmlHelper.getXmlAttributes(analysis.eResource().getURI(), "xmi:id");

        // Check that the migration was done.
        assertNotNull("Check the representation file test data.", analysis);

        // Check there are no elements typed as AnyType, ie. there is no unrecognized elements or attribute after the
        // migration.
        assertTrue("Check the migration logic.", ((XMLResource) analysis.eResource()).getEObjectToExtensionMap().isEmpty());

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            failCheckData();
        }
        // Save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // Check that the migration went well on the xml level
        Collection<String> uidAttributes = XmlHelper.getXmlAttributes(analysis.eResource().getURI(), "uid");
        assertEquals("Bad number of elements with uid attribute", 10, uidAttributes.size());

        Collection<String> xmiIdAttributes = XmlHelper.getXmlAttributes(analysis.eResource().getURI(), "xmi:id");
        assertEquals("Bad number of elements with xmi:id attribute", 9, xmiIdAttributes.size());

        List<String> newUids = uidAttributes.stream().filter(s -> !initialXmiIdAttributes.contains(s)).collect(Collectors.toList());
        assertTrue("Migration should not introduce new uids and all uid must come from the pevious xmi:id", newUids.isEmpty());

        assertTrue("There should be no xmi:id for the DRepresentation", initialXmiIdAttributes.contains(DREPRESENTATION_INITIAL_XMIID) && uidAttributes.contains(DREPRESENTATION_INITIAL_XMIID));
        assertTrue("The xmi:id of the DRepresentation must be serialized in the uid tag",
                !uidAttributes.contains(DREPRESENTATION_INITIAL_UID) && uidAttributes.contains(DREPRESENTATION_INITIAL_XMIID));

        // Check that old DRepresentation.uid has correctly been updated with the xmiid of that DRepresentation
        DRepresentationDescriptor dRepresentationDescriptor = analysis.getOwnedViews().get(0).getOwnedRepresentationDescriptors().get(0);
        assertTrue("The DRepresentationDescriptor.repPath should contain the uid of the DRepresentation",
                dRepresentationDescriptor.getRepPath().getResourceURI().toString().contains(DREPRESENTATION_INITIAL_XMIID));
        assertEquals("Bad DRepresentation.uid. It should equals the xmiid of the DRepresentation.", DREPRESENTATION_INITIAL_XMIID, dRepresentationDescriptor.getRepresentation().getUid());

        // Check that the DDiagram has been correctly retrieved from the GMF Diagram (xmi:id has been dropped in favor
        // of the uid of the representation).
        Diagram gmfDiag = (Diagram) new DRepresentationQuery(dRepresentationDescriptor.getRepresentation()).getAnnotation(CustomDataConstants.GMF_DIAGRAMS).iterator().next().getData();
        assertEquals("GMF Diagram is no more able to retrieve its DDiagram, check the migration effect.", dRepresentationDescriptor.getRepresentation(), gmfDiag.getElement());
        assertFalse(platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        // TODO Auto-generated method stub
        return null;
    }
}
