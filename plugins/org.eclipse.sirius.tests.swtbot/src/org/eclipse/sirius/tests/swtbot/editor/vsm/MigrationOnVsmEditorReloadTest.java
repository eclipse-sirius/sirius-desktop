/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.internal.migration.description.VSMExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceHandler;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.osgi.framework.Version;

/**
 * Tests VSM migration activation on VSM editor reload after an external change on the edited VSM.
 * 
 * @author mporhel
 */
public class MigrationOnVsmEditorReloadTest extends AbstractContentAssistTest {

    private static final String PATH = "data/unit/migration/do_not_migrate/vsm_migration_on_reload/";

    private static final String VSM_PROJECT_NAME = "org.eclipse.sirius.test.design";

    private static final String VSM = "test.odesign";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        designerPerspectives.openSiriusPerspective();

        // Load the target platform, if not already done, to allow compilation
        // of the VSP
        TestsUtil.setTargetPlatform(Activator.PLUGIN_ID);

        // Create VSM Project.
        ViewpointSpecificationProjectCreationTest.createViewpointSpecificationProject(bot, VSM_PROJECT_NAME, VSM);

        // Wait the end of the current build and/or refresh.
        waitJobsBuildOrRefresh();

    }

    /**
     * There is problem on linux with this test so we are waiting build or refresh jobs by joining them.
     */
    private void waitJobsBuildOrRefresh() throws InterruptedException, OperationCanceledException {
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_REFRESH, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_REFRESH, new NullProgressMonitor());
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
    }

    /**
     * Check that the migration is activated when the reloaded VSM requires it even if it was not required before the
     * resource change.
     * 
     * It simulates the replacement of the file by the user while the editor is opened.
     * 
     * @exception InterruptedException
     *                In case of problem during context initialization.
     * @exception OperationCanceledException
     *                In case of problem during context initialization.
     * @exception CoreException
     *                In case of problem during context initialization.
     */
    public void test_Migration_Initalization_On_VSM_Editor_Reload() throws InterruptedException, OperationCanceledException, CoreException {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        String vsmFilePath = VSM_PROJECT_NAME + "/description/" + VSM;
        URI vsmFileURI = URI.createPlatformResourceURI(vsmFilePath, false);

        // Check that the migration is not needed and not active for the just
        // created VSM
        checkVsmFileMigrationStatus(vsmFileURI, false);

        IStructuredSelection selection = (IStructuredSelection) ((CustomSiriusEditor) bot.activeEditor().getReference().getPart(false)).getSelection();
        assertTrue(selection.getFirstElement() instanceof DescriptionResourceImpl);
        final DescriptionResourceImpl vsmRes = (DescriptionResourceImpl) selection.getFirstElement();
        assertEquals(vsmFileURI, vsmRes.getURI());
        checkVSMResourceMigrationStatus(vsmRes, false);

        // Replace the file by a non migrated one
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + VSM, vsmFilePath);

        // Wait the end of the current build and/or refresh.
        waitJobsBuildOrRefresh();

        // Check that the migration is needed for the pasted VSM
        checkVsmFileMigrationStatus(vsmFileURI, true);

        // Check that the reload did not change the resource instance and
        // activated the migration
        selection = (IStructuredSelection) ((CustomSiriusEditor) bot.activeEditor().getReference().getPart(false)).getSelection();
        assertEquals(vsmRes, selection.getFirstElement());
        checkVSMResourceMigrationStatus(vsmRes, true);

        // Check we can navigate in the new loaded VSM
        bot.activeEditor().setFocus();
        SWTBotTreeItem viewpointItemBot = bot.activeEditor().bot().tree().expandNode("platform:/resource/" + vsmFilePath).expandNode("test").expandNode("VP");
        viewpointItemBot.getNode("Diag").select();
    }

    private void checkVSMResourceMigrationStatus(DescriptionResourceImpl vsmRes, boolean activeMigration) {
        String errorMessage = activeMigration ? "The migration should be active on the VSM resource" : "The migration should not be active on the VSM resource";

        assertEquals(errorMessage, activeMigration, vsmRes.getDefaultLoadOptions().get(XMLResource.OPTION_EXTENDED_META_DATA) instanceof VSMExtendedMetaData);
        assertEquals(errorMessage, activeMigration, vsmRes.getDefaultLoadOptions().get(XMLResource.OPTION_RESOURCE_HANDLER) instanceof VSMResourceHandler);

        assertEquals(errorMessage, activeMigration, vsmRes.getDefaultSaveOptions().get(XMLResource.OPTION_EXTENDED_META_DATA) instanceof VSMExtendedMetaData);
        assertEquals(errorMessage, activeMigration, vsmRes.getDefaultSaveOptions().get(XMLResource.OPTION_RESOURCE_HANDLER) instanceof VSMResourceHandler);

    }

    /**
     * Check that the data has the expected migration need.
     * 
     * It can be used to verify that a file has not be migrated before the test. And then it allows to check the effect
     * of the migration in the other test.
     * 
     * @param vsmFileURI
     *            the uri of the VSM file to check.
     * @param needsMigration
     *            indicates the expected migration need.
     * @return the loaded {@link Version} for convenience
     */
    protected void checkVsmFileMigrationStatus(URI vsmFileURI, boolean needsMigration) {
        VSMVersionSAXParser versionSaxPArser = new VSMVersionSAXParser(vsmFileURI);

        // Get the version before the migration.
        String sLoadedVersion = versionSaxPArser.getVersion(new NullProgressMonitor());

        // String version can be null for old models or models not created with
        // the tool.
        Version loadedVersion = Version.parseVersion(sLoadedVersion);
        assertNotNull("The parsed version is null, check the file: " + vsmFileURI.toPlatformString(true), loadedVersion);

        // Check that the migration service detect if the migration is needed.
        boolean migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(loadedVersion);

        if (needsMigration) {
            assertTrue("The current test case checks a migration behavior, please revert the manual migration on : " + vsmFileURI.toPlatformString(true), migrationIsNeeded);
        } else {
            assertFalse("The current test case expect a file which does not need migration : " + vsmFileURI.toPlatformString(true) + ". Current last version is "
                    + VSMMigrationService.getInstance().getLastMigrationVersion() + " and current VSM version is " + loadedVersion, migrationIsNeeded);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        closeAllEditors();

        super.tearDown();
    }
}
