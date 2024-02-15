/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common.migration;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.junit.Assert;
import org.osgi.framework.Version;

/**
 * This abstract class is for migration test from an x.x version of Sirius to the current version. Basically, it only
 * checks that:
 * <UL>
 * <LI>the version of the data is the expected one</LI>
 * <LI>and the session is correctly opened without error or warning.</LI>
 * </UL>
 * It is possible to make specific tests on representations file or VSM data by overriding the methods
 * {@link #checkRepresentationsFile(DAnalysis)} and {@link #checkVSM(Group)}.
 * 
 * @author lredor
 */
public abstract class AbstractMigrationFromTest extends SiriusTestCase {

    private static final String COMMON_DATA_UNIT_PATH = "data/unit/migration/do_not_migrate/";

    private static final String PROJECT_NAME = "my.project.sample";

    private static final String PROJECT_DESCRIPTION_FILE = ".project";

    private static final String SEMANTIC_FILE_NAME = "My.ecore";

    private static final String REPRESENTATIONS_FILE_NAME = "representations.aird";

    private static final String VSM_FILE_NAME = "My.odesign";

    private UICallBack defaultUiCallBack;

    @Override
    protected void setUp() throws Exception {
        super.createModelingProject = false;
        super.setUp();
        defaultUiCallBack = SiriusEditPlugin.getPlugin().getUiCallback();
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
    }

    @Override
    protected void tearDown() throws Exception {
        SiriusEditPlugin.getPlugin().setUiCallback(defaultUiCallBack);
        super.tearDown();
    }

    /**
     * Return the folder name containing data. This folder should be in "data/unit/migration/do_not_migrate/" folder and
     * should contain the project "my.project.sample". Otherwise, it is possible to directly override
     * {@link #getDataUnitPath()}.
     * 
     * @return the folder name containing data
     */
    abstract protected String getFolderName();

    /**
     * Return the expected version of the data.
     * 
     * @return the expected version of the data.
     */
    abstract protected Version getExpectedVersion();

    /**
     * Return the expected version of the data in readable format (used for message).
     * 
     * @return the expected version of the data in readable format.
     */
    abstract protected String getExpectedVersionToString();

    /**
     * The path of the data to copy in the workspace.
     * 
     * @return The path of the data to copy in the workspace.
     */
    protected IPath getDataUnitPath() {
        return new Path(COMMON_DATA_UNIT_PATH).append(getFolderName()).append(PROJECT_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        IPath projectPath = initializeProject();
        // Check that the representations file data corresponds to the expected
        // version of Sirius
        Version representationsFileLoadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(projectPath.append(REPRESENTATIONS_FILE_NAME).toOSString(), true), true);
        assertTrue("The representations file data should correspond to Sirius " + getExpectedVersionToString() + ".",
                representationsFileLoadedVersion != null && getExpectedVersion().compareTo(representationsFileLoadedVersion) > 0);
        // Check that the VSM data corresponds to the expected version of Sirius
        Version vsmLoadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(projectPath.append(VSM_FILE_NAME).toOSString(), true), true);
        assertTrue("The VSM data should correspond to Sirius " + getExpectedVersionToString() + ".", vsmLoadedVersion != null && getExpectedVersion().compareTo(vsmLoadedVersion) > 0);
    }

    /**
     * Test that the session can be opened without errors and warnings.
     */
    public void testMigration() {
        // Initialize the warning logger just before importing the project
        platformProblemsListener.setWarningCatchActive(true);

        initializeProject();

        // check modeling project is loaded
        Session session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(PROJECT_NAME + File.separator + REPRESENTATIONS_FILE_NAME, true), new NullProgressMonitor());
        assertNotNull("The modeling project should be loaded.", session);
        assertTrue("The session should be opened.", session.isOpen());

        // Check AIRD contents (should be migrated, so root should be
        // DAnalysis)
        assertTrue("The root of the representations file should be a DAnalysis", session.getSessionResource().getContents().get(0) instanceof DAnalysis);
        DAnalysis mainDAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        checkRepresentationsFile(mainDAnalysis);

        // Check VSM contents (should be migrated, so root should be Group)
        assertTrue("The root of the VSM file should be a Group", mainDAnalysis.getOwnedViews().get(0).getViewpoint().eResource().getContents().get(0) instanceof Group);
        Group group = (Group) mainDAnalysis.getOwnedViews().get(0).getViewpoint().eResource().getContents().get(0);
        checkVSM(group);

        // Check warnings
        if (platformProblemsListener.doesAWarningOccurs()) {
            Assert.fail(platformProblemsListener.getWarningLoggersMessage());
        }
    }

    /**
     * Method to override to make specific test for each version.
     * 
     * @param dAnalysis
     *            The {@link DAnalysis} to check.
     */
    protected void checkRepresentationsFile(DAnalysis dAnalysis) {
        // Nothing to do by default.
    }

    /**
     * Method to override to make specific test for each version.
     * 
     * @param group
     *            The {@link Group} to check.
     */
    protected void checkVSM(Group group) {
        // Nothing to do by default.
    }

    /**
     * Import the project in the workspace and wait that the session is opened.
     * 
     * @return The path of the imported project.
     */
    private IPath initializeProject() {
        // Copy the project and its files into the folder of the workspace
        final File sourceProjectFolder = FileProvider.getDefault().getFile(SiriusTestsPlugin.PLUGIN_ID, getDataUnitPath());
        IPath targetProjectPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(PROJECT_NAME);
        final File targetProjectFolder = new File(targetProjectPath.toOSString());
        if (!targetProjectFolder.exists()) {
            assertTrue("Problem during creation of the folder corresponding to the project.", targetProjectFolder.mkdirs());
        }
        String sourceProjectPath = sourceProjectFolder.getAbsolutePath() + File.separator;
        try {
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + PROJECT_DESCRIPTION_FILE), new File(targetProjectPath.append(PROJECT_DESCRIPTION_FILE).toOSString()));
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + SEMANTIC_FILE_NAME), new File(targetProjectPath.append(SEMANTIC_FILE_NAME).toOSString()));
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + VSM_FILE_NAME), new File(targetProjectPath.append(VSM_FILE_NAME).toOSString()));
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + REPRESENTATIONS_FILE_NAME), new File(targetProjectPath.append(REPRESENTATIONS_FILE_NAME).toOSString()));
        } catch (IOException e1) {
            fail("Problem during copy of data files: " + e1.getMessage());
        }

        // Import the existing project into the workspace
        IProject project = null;
        try {
            IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(targetProjectPath.append(PROJECT_DESCRIPTION_FILE)); // $NON-NLS-1$
            project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
            project.create(description, null);
            project.open(null);
        } catch (CoreException e) {
            fail("Import of the Sirius " + getExpectedVersionToString() + " modeling project fails: " + e.getMessage());
        }
        try {
            Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        } catch (OperationCanceledException e) {
            fail("The loading session has been aborted: " + e.getMessage());
        } catch (InterruptedException e) {
            fail("The loading session has been aborted: " + e.getMessage());
        }
        return targetProjectPath;
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
