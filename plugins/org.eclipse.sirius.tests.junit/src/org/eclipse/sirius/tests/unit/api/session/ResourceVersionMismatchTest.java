/*******************************************************************************
 * Copyright (c) 2016, 2024 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.session;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.internal.corext.util.Messages;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.migration.AirdResourceVersionMismatchException;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.osgi.framework.Version;

/**
 * Test class for Bug 481846 about preventing loading of Sirius resource coming from a newer Sirius release.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResourceVersionMismatchTest extends SiriusTestCase {

    private static final String PATH = "/data/unit/resource/481846/";

    private static final String SESSION_MODEL_FILENAME = "481846.aird";

    private static final String SESSION_MODEL_FRAGMENT_FILENAME = "481846_P1.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "481846.ecore";

    private static final String SEMANTIC_MODEL_FRAGMENT_FILENAME = "481846_P1.ecore";

    private static final String ODESIGN_MODEL_FILENAME = "481846.odesign";

    private static final String VIEWPOINT_NAME = "Viewpoint_481846";

    private static final String SESSION_NOT_IN_SESSIONMANAGER = "The session should not be registered in the SessionManager";

    private static final String WARNING_LOGGED = "An warning should have been logged indicating that the viewpoint registry did not register the VSM because of the version mismatch";

    private static final String THROWN_EXCEPTION = "A {0} should be thrown because we try to open some {1} model coming from newer Sirius release";

    private URI sessionResourceURI;

    /**
     * If true, indicates that the session must be opened even if there a version mismatch.
     */
    private boolean forceOpeningSession = true;

    /**
     * The UICallBack as it is before starting this test class.
     */
    private UICallBack defaultUiCallback;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SESSION_MODEL_FRAGMENT_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FRAGMENT_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + ODESIGN_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + ODESIGN_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SESSION_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_MODEL_FRAGMENT_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FRAGMENT_FILENAME);
        sessionResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true);

        // Setting no UI callback
        defaultUiCallback = SiriusEditPlugin.getPlugin().getUiCallback();
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback() {
            @Override
            public boolean askSessionReopeningWithResourceVersionMismatch(AirdResourceVersionMismatchException e) {
                return forceOpeningSession;
            }
        });
    }

    /**
     * Test session opening when the main aird has a version from a more recent Sirius release </br>
     * Test also session opening ignoring this version mismatch.
     */
    public void testMainAirdResourceVersionMismatch() {
        // Setup a session with valid VSM and invalid main aird
        setupFileVersionMismatch(false, true, false);

        // check
        checkAirdVersionMismatch();
    }

    /**
     * Test session opening when an referenced aird has a version from a more recent Sirius release </br>
     * Test also session opening ignoring this version mismatch.
     */
    public void testReferencedAirdResourceVersionMismatch() {
        // Setup a session with valid VSM and invalid referenced aird
        setupFileVersionMismatch(false, false, true);

        // check
        checkAirdVersionMismatch();
    }

    private void checkAirdVersionMismatch() {
        // check opening the session anyway
        forceOpeningSession = true;
        Session session = SessionManager.INSTANCE.openSession(sessionResourceURI, new NullProgressMonitor(), SiriusEditPlugin.getPlugin().getUiCallback());
        assertNotNull("The session should have opened ignoring the aird version mismatch", session);
        session.close(new NullProgressMonitor());

        // check stopping session opening
        try {
            forceOpeningSession = false;
            platformProblemsListener.clearErrors();
            session = SessionManager.INSTANCE.openSession(sessionResourceURI, new NullProgressMonitor(), SiriusEditPlugin.getPlugin().getUiCallback());
            String[] args = { AirdResourceVersionMismatchException.class.getName(), "aird" };
            fail(Messages.format(THROWN_EXCEPTION, args));
        } catch (RuntimeException e) {
            assertEquals("The raised exception should be a " + AirdResourceVersionMismatchException.class.getName(), AirdResourceVersionMismatchException.class.getName(), e.getClass().getName());
        }
        assertTrue(SESSION_NOT_IN_SESSIONMANAGER, SessionManager.INSTANCE.getSessions().isEmpty());
    }

    /**
     * Test that the ViewpointRegistry does not register a VSM which has a version from a more recent Sirius release
     * </br>
     */
    public void testViewRegistryWithVSMResourceVersionMismatch() {
        // Initialize error/warning log and uncaught exception handlers
        platformProblemsListener.initLoggers();
        platformProblemsListener.setWarningCatchActive(true);
        platformProblemsListener.clearWarnings();

        // Setup a session with invalid VSM and valid aird
        setupFileVersionMismatch(true, false, false);

        // check that the viewpoint Registry has logged a warning
        assertTrue(WARNING_LOGGED, platformProblemsListener.doesAWarningOccurs());
        platformProblemsListener.clearWarnings();

        Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
        for (Viewpoint viewpoint : viewpoints) {
            assertNotEquals("The Viewpoint " + VIEWPOINT_NAME + " should not registered in the viewpoint registry", viewpoint.getName());
        }
    }

    /**
     * Test session opening when a selected VSM aird has a version from a more recent Sirius release </br>
     * Test also session opening ignoring this version mismatch.
     */
    public void testVSMResourceVersionMismatch() {
        // Setup a session with invalid VSM and valid aird
        setupFileVersionMismatch(true, false, false);

        // Test
        try {
            platformProblemsListener.clearErrors();
            SessionManager.INSTANCE.openSession(sessionResourceURI, new NullProgressMonitor(), SiriusEditPlugin.getPlugin().getUiCallback());
            fail(Messages.format(THROWN_EXCEPTION, new String[] { RuntimeException.class.getName(), "VSM" }));
        } catch (RuntimeException e) {
        }
        assertTrue(SESSION_NOT_IN_SESSIONMANAGER, SessionManager.INSTANCE.getSessions().isEmpty());
    }

    /**
     * Test session opening when a selected VSM and an aird have a version from a more recent Sirius release </br>
     * Test also session opening ignoring this version mismatch.
     */
    public void testVSMAndAirdResourceVersionMismatch() {
        // Setup a session with invalid VSM aird
        setupFileVersionMismatch(true, true, false);

        // Test
        try {
            SessionManager.INSTANCE.openSession(sessionResourceURI, new NullProgressMonitor(), SiriusEditPlugin.getPlugin().getUiCallback());
            String[] args = { AirdResourceVersionMismatchException.class.getName(), "VSM" };
            fail(Messages.format(THROWN_EXCEPTION, args));
        } catch (RuntimeException e) {
        }
        assertTrue(SESSION_NOT_IN_SESSIONMANAGER, SessionManager.INSTANCE.getSessions().isEmpty());
    }

    private void setupFileVersionMismatch(boolean setupInvalidVSM, boolean setupInvalidMainAird, boolean setupInvalidReferencedAird) {
        // If the aird or vsm need to be migrated, then, at save, the migration
        // version is set at the last one.
        // So, to avoid that the changed version (done below) gets overridden we
        // make sure the model does not need to be migrated.
        Session session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        session.save(new NullProgressMonitor());
        Viewpoint viewpoint = session.getSelectedViewpoints(false).iterator().next();
        saveVSM(viewpoint.eResource());
        session.close(new NullProgressMonitor());

        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        CommandStack commandStack = domain.getCommandStack();

        // Change VSM and Representations resources to a newer version
        if (setupInvalidVSM) {
            Viewpoint vp = session.getSelectedViewpoints(false).iterator().next();
            assertEquals("Bad viewpoint name", VIEWPOINT_NAME, vp.getName());
            EObject group = vp.eContainer();
            Version lastMigrationVersion = VSMMigrationService.getInstance().getLastMigrationVersion();
            Version newVersion = new Version(lastMigrationVersion.getMajor() + 1, lastMigrationVersion.getMinor(), lastMigrationVersion.getMicro(), lastMigrationVersion.getQualifier());
            Command changeVersionCmd = SetCommand.create(domain, group, DescriptionPackage.Literals.GROUP__VERSION, newVersion.toString());
            assertTrue(changeVersionCmd.canExecute());
            commandStack.execute(changeVersionCmd);

            saveVSM(group.eResource());
        }

        if (setupInvalidMainAird) {
            Resource sessionResource = session.getSessionResource();
            EObject dAnalysis = sessionResource.getContents().get(0);
            Version lastMigrationVersion = RepresentationsFileMigrationService.getInstance().getLastMigrationVersion();
            Version newVersion = new Version(lastMigrationVersion.getMajor() + 1, lastMigrationVersion.getMinor(), lastMigrationVersion.getMicro(), lastMigrationVersion.getQualifier());
            Command changeVersionCmd = SetCommand.create(domain, dAnalysis, ViewpointPackage.Literals.DANALYSIS__VERSION, newVersion.toString());
            assertTrue(changeVersionCmd.canExecute());
            commandStack.execute(changeVersionCmd);
        }

        if (setupInvalidReferencedAird) {
            Set<Resource> referencedSessions = session.getReferencedSessionResources();
            EObject dAnalysis = referencedSessions.iterator().next().getContents().get(0);;
            Version lastMigrationVersion = RepresentationsFileMigrationService.getInstance().getLastMigrationVersion();
            Version newVersion = new Version(lastMigrationVersion.getMajor() + 1, lastMigrationVersion.getMinor(), lastMigrationVersion.getMicro(), lastMigrationVersion.getQualifier());
            Command changeVersionCmd = SetCommand.create(domain, dAnalysis, ViewpointPackage.Literals.DANALYSIS__VERSION, newVersion.toString());
            assertTrue(changeVersionCmd.canExecute());
            commandStack.execute(changeVersionCmd);
        }

        session.save(new NullProgressMonitor());
        session.close(new NullProgressMonitor());
    }

    private void saveVSM(Resource resource) {
        try {
            resource.save(null);
        } catch (IOException e) {
            fail("The save of the viewpoint " + ODESIGN_MODEL_FILENAME + " failed.");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if (defaultUiCallback != null) {
            SiriusEditPlugin.getPlugin().setUiCallback(defaultUiCallback);
        }
        sessionResourceURI = null;
        super.tearDown();
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
