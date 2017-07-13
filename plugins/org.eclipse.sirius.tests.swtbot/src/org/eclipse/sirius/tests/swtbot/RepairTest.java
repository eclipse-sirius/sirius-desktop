/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.internal.actions.repair.RepresentationFilesNeedCloseSessionValidator;
import org.eclipse.sirius.ui.tools.internal.actions.repair.RepresentationFilesRepairAction;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests for the "Repair" action.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RepairTest extends AbstractScenarioTestCase {
    /**
     * Cannot use mocks here because {@link #logging(IStatus, String)} is called asynchronously. Thrown exception is
     * lost and doesn't stop
     * 
     * @author dlecan
     */
    private static class InnerLogListener implements ILogListener {

        private boolean hasFailed = false;

        private String message = "";

        @Override
        public void logging(final IStatus status, final String plugin) {

            // hasFailed = status.matches(IStatus.WARNING) ||
            // status.matches(IStatus.ERROR);
            hasFailed = status.matches(IStatus.ERROR);

            if (hasFailed) {
                // Remove this listener because we don't want to catch next
                // errors or warnings...
                Platform.removeLogListener(this);
                message = status.toString();

                if (status.getException() != null) {
                    final StringWriter sw = new StringWriter();
                    final PrintWriter pw = new PrintWriter(sw);
                    status.getException().printStackTrace(pw);
                    pw.close();
                    message += "\n" + sw.toString();
                }
            }
        }
    }

    private static final String DATA_UNIT_DIR = "data/unit/migration/";

    private static final String FILE_DIR_1 = "sc1";

    private static final String MODEL1 = "sc1.ecore";

    private static final String SESSION_FILE1 = "sc1.aird";

    private static final String FILE_DIR_2 = "sc2";

    private static final String MODEL2 = "sc2.ecore";

    private static final String SESSION_FILE2 = "sc2.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM2 = "sc2 package entities";

    private static final String FILE_DIR_3 = "sc3";

    private static final String MODEL3_1 = "sc3-1.ecore";

    private static final String SESSION_FILE3_1 = "sc3-1.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM3_1 = "sc3-1 package entities";

    private static final String MODEL3_2 = "sc3-2.ecore";

    private static final String SESSION_FILE3_2 = "sc3-2.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM3_2 = "sc3-2 package entities";

    private static final String FILE_DIR_4 = "sc4";

    private static final String MODEL4_1 = "sc4-1.ecore";

    private static final String SESSION_FILE4_1 = "sc4-1.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM4_1 = "sc4-1 package entities";

    private static final String MODEL4_2 = "sc4-2.ecore";

    private static final String SESSION_FILE4_2 = "sc4-2.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM4_2 = "sc4-2 package entities";

    private static final String FILE_DIR_5 = "sc5";

    private static final String MODEL5 = "sc5.ecore";

    private static final String SESSION_FILE5 = "sc5.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM5 = "sc5 package entities";

    private static final String MODEL5_1 = "sc5_1.ecore";

    private static final String SESSION_FILE5_1 = "sc5_1.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM5_1 = "pa package entities";

    private static final String MODEL5_2 = "sc5_2.ecore";

    private static final String SESSION_FILE5_2 = "sc5_2.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM5_2 = "pb package entities";

    private static final String REPRESENTATION_NAME_DIAGRAM = "Entities";

    private InnerLogListener logListener;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR + FILE_DIR_1 + "/", MODEL1, SESSION_FILE1);
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR + FILE_DIR_2 + "/", MODEL2, SESSION_FILE2);
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR + FILE_DIR_3 + "/", MODEL3_1, SESSION_FILE3_1, MODEL3_2, SESSION_FILE3_2);
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR + FILE_DIR_4 + "/", MODEL4_1, SESSION_FILE4_1, MODEL4_2, SESSION_FILE4_2);
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR + FILE_DIR_5 + "/", MODEL5, SESSION_FILE5, MODEL5_1, SESSION_FILE5_1, MODEL5_2, SESSION_FILE5_2);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        logListener = new InnerLogListener();
        Platform.addLogListener(logListener);
    }

    /**
     * Scenario 1 : Basic Repair
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Launch the action Repair model on sc1.aird</LI>
     * <LI>Check that a backup was done : sc1-timestamp.aird.old</LI>
     * <LI>Check that the representations are OK in the repaired file</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testBasicRepair() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("It should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE1));
        UIResource sessionAirdResource = new UIResource(designerProject, SESSION_FILE1);
        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource, repairActionLabel);
        // Wait the end of repair
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        assertNoProblemsLogged();
        // Check that there is a backup
        assertEquals("It should be one backup file after repair.", 1, getNumberOfBackupFiles(project, SESSION_FILE1));
        // Check that we can open the session
        designerPerspective.openSessionFromFile(sessionAirdResource);
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 2: repair with one session opened
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc2.aird</LI>
     * <LI>The session is opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Launch the action Repair model on an sc2.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click OK on the popup</LI>
     * <LI>Check that the session is closed</LI>
     * <LI>Check that a backup was done</LI>
     * <LI>Check that the representations are OK in the repaired file</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithOneSessionOpened() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("There should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE2));
        // Open the session
        final UIResource sessionAirdResource = new UIResource(designerProject, SESSION_FILE2);
        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        // Open a diagram of this session
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM2, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession.save();
        }
        launchRepairAction(sessionAirdResource);
        // Check that the session is opened
        assertEquals("One session should be opened.", 0, SessionManager.INSTANCE.getSessions().size());
        // Check that there is a backup
        assertEquals("It should be one backup file after repair.", 1, getNumberOfBackupFiles(project, SESSION_FILE2));
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 2 bis : repair with one session opened and cancel
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc2.aird</LI>
     * <LI>The session is opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Launch the action Repair model on an sc2.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click Cancel on the popup</LI>
     * <LI>Check that the session is always opened</LI>
     * <LI>Check that no backup was done</LI>
     * <LI>Check that the aird file is not changed</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithOneSessionOpenedAndCancel() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("It should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE2));
        // Open the session
        final UIResource sessionAirdResource = new UIResource(designerProject, SESSION_FILE2);
        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        // Open a diagram of this session
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM2, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession.save();
        }

        IFile airdFile = project.getFile(SESSION_FILE2);
        long stampBeforeRepair = airdFile.getModificationStamp();

        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource, repairActionLabel);
        //
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        bot.button("Cancel").click();
        // Check that the session is always opened
        assertEquals("The session should be always opened.", 1, SessionManager.INSTANCE.getSessions().size());
        // Check that there is no backup
        assertEquals("It should be no backup file after repair cancel.", 0, getNumberOfBackupFiles(project, SESSION_FILE2));
        // Check that the aird file has not changed
        assertTrue("The aird file wouldn't be changed", stampBeforeRepair == airdFile.getModificationStamp());
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 2 ter : repair with one session opened and cancel
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc2.aird</LI>
     * <LI>The session is opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Launch the action Repair model on an sc2.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click on the cross of the popup</LI>
     * <LI>Check that the session is always opened</LI>
     * <LI>Check that no backup was done</LI>
     * <LI>Check that the aird file is not changed</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithOneSessionOpenedAndCancelWithCloseOfPopup() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("It should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE2));
        // Open the session
        final UIResource sessionAirdResource = new UIResource(designerProject, SESSION_FILE2);
        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        // Open a diagram of this session
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM2, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession.save();
        }

        IFile airdFile = project.getFile(SESSION_FILE2);
        long stampBeforeRepair = airdFile.getModificationStamp();

        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource, repairActionLabel);
        //
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        // Close the popup with the cross
        bot.activeShell().close();
        // Check that the session is always opened
        assertEquals("The session should be always opened.", 1, SessionManager.INSTANCE.getSessions().size());
        // Check that there is no backup
        assertEquals("It should be no backup file after repair cancel.", 0, getNumberOfBackupFiles(project, SESSION_FILE2));
        // Check that the aird file has not changed
        assertTrue("The aird file wouldn't be changed", stampBeforeRepair == airdFile.getModificationStamp());
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 3 : repair with multiple sessions opened
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc3-1.aird</LI>
     * <LI>Open a session on another simple aird file, sc3-2.aird</LI>
     * <LI>The session are opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Launch the action Repair model on an sc3-1.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click OK on the popup</LI>
     * <LI>Check that the sessions are closed</LI>
     * <LI>Check that a backup was done</LI>
     * <LI>Check that the representations are OK in the repaired file</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithMultipleSessionsOpened() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("There should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE3_1));
        // Open the session
        final UIResource sessionAirdResource1 = new UIResource(designerProject, SESSION_FILE3_1);
        final UILocalSession localSession1 = designerPerspective.openSessionFromFile(sessionAirdResource1);
        // Open a diagram of this session
        openRepresentation(localSession1.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM3_1, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession1.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession1.save();
        }
        // Open the session
        final UIResource sessionAirdResource2 = new UIResource(designerProject, SESSION_FILE3_2);
        final UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        // Open a diagram of this session
        openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM3_2, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession2.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession2.save();
        }
        launchRepairAction(sessionAirdResource1);
        // Check that the sessions are opened
        assertEquals("Two sessions should be opened.", 0, SessionManager.INSTANCE.getSessions().size());
        SessionManager.INSTANCE.getExistingSession(URI.createPlatformResourceURI(SESSION_FILE3_1, true));
        SessionManager.INSTANCE.getExistingSession(URI.createPlatformResourceURI(SESSION_FILE3_1, true));

        // Check that there is a backup
        assertEquals("It should be one backup file after repair.", 1, getNumberOfBackupFiles(project, SESSION_FILE3_1));
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 3 bis : repair with multiple sessions opened and cancel
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc3-1.aird</LI>
     * <LI>Open a session on another simple aird file, sc3-2.aird</LI>
     * <LI>The session are opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Launch the action Repair model on an sc3-1.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click Cancel on the popup</LI>
     * <LI>Check that the session are always opened</LI>
     * <LI>Check that no backup was done</LI>
     * <LI>Check that the aird files are not changed</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithMultipleSessionsOpenedAndCancel() throws Exception {
        // Check that there is no session opened
        assertEquals("0 session should be opened.", 0, SessionManager.INSTANCE.getSessions().size());

        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("It should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE3_1));
        // Open the session
        final UIResource sessionAirdResource1 = new UIResource(designerProject, SESSION_FILE3_1);
        final UILocalSession localSession1 = designerPerspective.openSessionFromFile(sessionAirdResource1);
        // Open a diagram of this session
        openRepresentation(localSession1.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM3_1, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession1.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession1.save();
        }
        IFile airdFile1 = project.getFile(SESSION_FILE3_1);
        long stampBeforeRepair1 = airdFile1.getModificationStamp();

        // Open the session
        final UIResource sessionAirdResource2 = new UIResource(designerProject, SESSION_FILE3_2);
        final UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession2.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession2.save();
        }
        IFile airdFile2 = project.getFile(SESSION_FILE3_2);
        long stampBeforeRepair2 = airdFile2.getModificationStamp();

        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource1, repairActionLabel);
        //
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        bot.button("Cancel").click();
        // Check that the sessions are always opened
        assertEquals("The sessions should be always opened.", 2, SessionManager.INSTANCE.getSessions().size());
        // Check that there is no backup
        assertEquals("It should be no backup file after repair cancel.", 0, getNumberOfBackupFiles(project, SESSION_FILE3_1));
        // Check that the aird file has not changed
        assertTrue("The aird file wouldn't be changed", stampBeforeRepair1 == airdFile1.getModificationStamp());
        assertTrue("The aird file wouldn't be changed", stampBeforeRepair2 == airdFile2.getModificationStamp());
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 4 : repair with multiple opened sessions (at least one changed), and backup
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc4-1.aird</LI>
     * <LI>The session is opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Open a session on another simple aird file, sc4-2.aird</LI>
     * <LI>Modify one of the diagram of the second session to have this one in dirty mode</LI>
     * <LI>Launch the action Repair model on an sc3-1.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click OK on the popup</LI>
     * <LI>Check that the session are closed</LI>
     * <LI>Check that a backup was done</LI>
     * <LI>Check that the modification done in the diagram of the second session (sc4-2.aird) is always here.</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithMultipleSessionsOpenedWithOneDirtyAndSave() throws Exception {
        // Check that there is no session opened
        assertEquals("0 session should be opened.", 0, SessionManager.INSTANCE.getSessions().size());

        String nameOfPackageToRename = "pc";
        String newNameOfPackage = "pcNew";
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("It should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE4_1));
        // Open the session
        final UIResource sessionAirdResource1 = new UIResource(designerProject, SESSION_FILE4_1);
        final UILocalSession localSession1 = designerPerspective.openSessionFromFile(sessionAirdResource1);
        // Open a diagram of this session
        openRepresentation(localSession1.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_1, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession1.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession1.save();
        }
        // Open the session
        final UIResource sessionAirdResource2 = new UIResource(designerProject, SESSION_FILE4_2);
        UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        // Open a diagram of this session
        SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_2,
                DDiagram.class);
        changeNameOfPCPackage(editor, nameOfPackageToRename, newNameOfPackage);
        // Check that the session is dirty
        assertTrue("The session \"" + sessionAirdResource2.getName() + "\" must be dirty.", localSession2.getOpenedSession().getStatus() == SessionStatus.DIRTY);
        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource1, repairActionLabel);
        //
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        bot.button(IDialogConstants.YES_LABEL).click();
        // Wait the end of repair
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        assertNoProblemsLogged();
        // Check that two sessions are open
        assertEquals("No session should be opened.", 0, SessionManager.INSTANCE.getSessions().size());

        // Check that there is a backup
        assertEquals("It should be one backup file after repair.", 1, getNumberOfBackupFiles(project, SESSION_FILE4_1));
        localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_2, DDiagram.class);
        assertNotNull("The modification made in the second editor was lost.", editor.getEditPart(newNameOfPackage));
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 4 bis : repair with multiple opened sessions (at least one changed), and without backup
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc4-1.aird</LI>
     * <LI>The session is opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Open a session on another simple aird file, sc4-2.aird</LI>
     * <LI>Modify one of the diagram of the second session to have this one in dirty mode</LI>
     * <LI>Launch the action Repair model on an sc3-1.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click NO on the popup</LI>
     * <LI>Check that the session are closed</LI>
     * <LI>Check that a backup was done</LI>
     * <LI>Check that the modification done in the diagram of the second session (sc4-2.aird) is still longer here and
     * that the second session is dirty</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithMultipleSessionsOpenedWithOneDirtyAndWithoutSave() throws Exception {
        String nameOfPackageToRename = "pc";
        String newNameOfPackage = "pcNew";
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("It should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE4_1));
        // Open the session
        final UIResource sessionAirdResource1 = new UIResource(designerProject, SESSION_FILE4_1);
        final UILocalSession localSession1 = designerPerspective.openSessionFromFile(sessionAirdResource1);
        // Open a diagram of this session
        openRepresentation(localSession1.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_1, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession1.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession1.save();
        }
        // Open the session
        final UIResource sessionAirdResource2 = new UIResource(designerProject, SESSION_FILE4_2);
        UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        // Open a diagram of this session
        SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_2,
                DDiagram.class);
        changeNameOfPCPackage(editor, nameOfPackageToRename, newNameOfPackage);
        // Check that the session is dirty
        assertTrue("The session \"" + sessionAirdResource2.getName() + "\" must be dirty.", localSession2.getOpenedSession().getStatus() == SessionStatus.DIRTY);
        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource1, repairActionLabel);
        //
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        bot.button(IDialogConstants.NO_LABEL).click();
        // Wait the end of repair
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        assertNoProblemsLogged();
        // Check that two sessions are open
        assertEquals("No session should be opened.", 0, SessionManager.INSTANCE.getSessions().size());

        // Check that there is a backup
        assertEquals("It should be one backup file after repair.", 1, getNumberOfBackupFiles(project, SESSION_FILE4_1));
        localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_2, DDiagram.class);

        assertNotNull("The modification made in the second editor should be lost.", editor.getEditPart(nameOfPackageToRename));
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 4 ter : repair with multiple opened sessions (at least one changed), and cancel
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc4-1.aird</LI>
     * <LI>The session is opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Open a session on another simple aird file, sc4-2.aird</LI>
     * <LI>Modify one of the diagram of the second session to have this one in dirty mode</LI>
     * <LI>Launch the action Repair model on an sc3-1.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click Cancel on the popup</LI>
     * <LI>Check that the session are always opened</LI>
     * <LI>Check that no backup was done</LI>
     * <LI>Check that the aird files are not changed</LI>
     * <LI>Check that the modification is always here</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithMultipleSessionsOpenedWithOneDirtyAndCancel() throws Exception {
        String nameOfPackageToRename = "pc";
        String newNameOfPackage = "pcNew";
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertEquals("It should be no backup file before repair.", 0, getNumberOfBackupFiles(project, SESSION_FILE4_1));
        // Open the session
        final UIResource sessionAirdResource1 = new UIResource(designerProject, SESSION_FILE4_1);
        IFile airdFile1 = project.getFile(SESSION_FILE3_1);
        long stampBeforeRepair1 = airdFile1.getModificationStamp();
        final UILocalSession localSession1 = designerPerspective.openSessionFromFile(sessionAirdResource1);
        // Open a diagram of this session
        openRepresentation(localSession1.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_1, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession1.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession1.save();
        }
        // Open the session
        final UIResource sessionAirdResource2 = new UIResource(designerProject, SESSION_FILE4_2);
        UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        // Open a diagram of this session
        SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_2,
                DDiagram.class);
        changeNameOfPCPackage(editor, nameOfPackageToRename, newNameOfPackage);
        // Check that the session is dirty
        assertTrue("The session \"" + sessionAirdResource2.getName() + "\" must be dirty.", localSession2.getOpenedSession().getStatus() == SessionStatus.DIRTY);
        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource1, repairActionLabel);
        //
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        // Check that the sessions are always opened
        assertEquals("The session should be always opened.", 2, SessionManager.INSTANCE.getSessions().size());
        // Check that there is no backup
        assertEquals("It should be no backup file after repair cancel.", 0, getNumberOfBackupFiles(project, SESSION_FILE4_1));
        // Check that the aird file has not changed
        assertTrue("The aird file wouldn't be changed", stampBeforeRepair1 == airdFile1.getModificationStamp());
        // Check that the modification is always here
        assertNotNull("The modification made in the second editor was lost.", editor.getEditPart(newNameOfPackage));
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 4 ter : repair with multiple opened sessions (at least one changed), and cancel with the cross of the
     * popup
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open a session on a simple aird file, sc4-1.aird</LI>
     * <LI>The session is opened and not dirty (if it's dirty, save it)</LI>
     * <LI>Open a session on another simple aird file, sc4-2.aird</LI>
     * <LI>Modify one of the diagram of the second session to have this one in dirty mode</LI>
     * <LI>Launch the action Repair model on an sc3-1.aird</LI>
     * <LI>A popup warns the user that the session will be close</LI>
     * <LI>Click Cancel on the popup</LI>
     * <LI>Check that the session are always opened</LI>
     * <LI>Check that no backup was done</LI>
     * <LI>Check that the aird files are not changed</LI>
     * <LI>Check that the modification is always here</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithMultipleSessionsOpenedWithOneDirtyAndCancelWithCloseOfPopup() throws Exception {
        String nameOfPackageToRename = "pc";
        String newNameOfPackage = "pcNew";
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertNumberOfBackupFiles(0, project, SESSION_FILE4_1);
        // Open the session
        final UIResource sessionAirdResource1 = new UIResource(designerProject, SESSION_FILE4_1);
        IFile airdFile1 = project.getFile(SESSION_FILE3_1);
        long stampBeforeRepair1 = airdFile1.getModificationStamp();
        final UILocalSession localSession1 = designerPerspective.openSessionFromFile(sessionAirdResource1);
        // Open a diagram of this session
        openRepresentation(localSession1.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_1, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession1.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession1.save();
        }
        // Open the session
        final UIResource sessionAirdResource2 = new UIResource(designerProject, SESSION_FILE4_2);
        UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2);
        // Open a diagram of this session
        SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM4_2,
                DDiagram.class);
        changeNameOfPCPackage(editor, nameOfPackageToRename, newNameOfPackage);
        // Check that the session is dirty
        assertTrue("The session \"" + sessionAirdResource2.getName() + "\" must be dirty.", localSession2.getOpenedSession().getStatus() == SessionStatus.DIRTY);
        // Launch the repair action
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource1, repairActionLabel);
        //
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        // Close the popup with the cross
        bot.activeShell().close();
        assertNumberOfOpenedSessions(2);
        // Check that there is no backup
        assertEquals("It should be no backup file after repair cancel.", 0, getNumberOfBackupFiles(project, SESSION_FILE4_1));
        // Check that the aird file has not changed
        assertTrue("The aird file wouldn't be changed", stampBeforeRepair1 == airdFile1.getModificationStamp());
        // Check that the modification is always here
        assertNotNull("The modification made in the second editor was lost.", editor.getEditPart(newNameOfPackage));
        // Check if there is problem logged during this test
        assertNoProblemsLogged();
    }

    /**
     * Scenario 5 : repair with fragmented files<BR>
     * Contexte :
     * <UL>
     * <LI>sc5.aird file use the sc5.ecore as semantic resource</LI>
     * <LI>sc5-1.aird is a fragmented file (controlled from sc5.aird) and use sc5-1.ecore as semantic resource</LI>
     * <LI>sc5-2.aird is a fragmented file (controlled from sc5.aird) and use sc5-2.ecore as semantic resource</LI>
     * </UL>
     * Steps :
     * <UL>
     * <LI>Close all the opened sessions</LI>
     * <LI>Open the three aird files</LI>
     * <LI>The sessions are opened and not dirty (if they are dirty, save them)</LI>
     * <LI>Launch the action Repair model on the main aird file, sc5.aird</LI>
     * <LI>A popup warns the user that the sessions will be close</LI>
     * <LI>Click OK on the popup</LI>
     * <LI>Check that the session are closed</LI>
     * <LI>Check that a backup was done for the three files (sc5-timestamp.aird.old, sc5-1-timestamp.aird.old and
     * sc5-2-timestamp.aird.old)</LI>
     * <LI>Check that the representations are OK in the repaired files</LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRepairWithMultipleFragmentedSessionsOpened() throws Exception {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
        assertNumberOfBackupFiles(0, project, SESSION_FILE5);
        assertNumberOfBackupFiles(0, project, SESSION_FILE5_1);
        assertNumberOfBackupFiles(0, project, SESSION_FILE5_2);
        // Open the session
        final UIResource sessionAirdResource = new UIResource(designerProject, SESSION_FILE5);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        // Open a diagram of this session
        openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM5, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession.save();
        }
        // Open the session
        final UIResource sessionAirdResource1 = new UIResource(designerProject, SESSION_FILE5_1);
        final UILocalSession localSession1 = designerPerspective.openSessionFromFile(sessionAirdResource1, true);
        // Open a diagram of this session
        openRepresentation(localSession1.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM5_1, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession1.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession1.save();
        }
        // Open the session
        final UIResource sessionAirdResource2 = new UIResource(designerProject, SESSION_FILE5_2);
        final UILocalSession localSession2 = designerPerspective.openSessionFromFile(sessionAirdResource2, true);
        // Open a diagram of this session
        openRepresentation(localSession2.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM5_2, DDiagram.class);
        // Check that the session is not dirty (if dirty, save it)
        if (localSession2.getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            localSession2.save();
        }
        launchRepairAction(sessionAirdResource);
        // Check that the sessions are open
        assertNumberOfOpenedSessions(0);
        SessionManager.INSTANCE.getExistingSession(URI.createPlatformResourceURI(sessionAirdResource.getFullPath(), true));
        SessionManager.INSTANCE.getExistingSession(URI.createPlatformResourceURI(sessionAirdResource1.getFullPath(), true));
        SessionManager.INSTANCE.getExistingSession(URI.createPlatformResourceURI(sessionAirdResource2.getFullPath(), true));

        // Check that there is a backup
        assertNumberOfBackupFiles(3, project, SESSION_FILE5);
        assertNoProblemsLogged();
    }

    /**
     * Launch the repair action from the context menu of the specified aird resource.
     */
    private void launchRepairAction(UIResource sessionAirdResource) {
        String repairActionLabel = SiriusEditPlugin.getPlugin().getString("repairActionLabel");
        designerProject.mouseRigthClickOnResource(sessionAirdResource, repairActionLabel);
        bot.waitUntil(Conditions.shellIsActive(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL)));
        SWTBotShell message = bot.shell(RepresentationFilesNeedCloseSessionValidator.getMessageTitle(RepresentationFilesRepairAction.REPAIR_ACTION_LABEL));
        message.setFocus();
        bot.button("OK").click();
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        assertNoProblemsLogged();
    }

    /**
     * Check there is no log message.
     */
    private void assertNoProblemsLogged() {
        assertFalse("An error status has been detected by log listener: " + logListener.message, logListener.hasFailed);
    }

    /**
     * Check that we have the expected number of sessions currently opened.
     */
    private void assertNumberOfOpenedSessions(int expected) {
        assertEquals("Unexpected number of opened session", expected, SessionManager.INSTANCE.getSessions().size());
    }

    /**
     * Check that we have the expected number of backup files for the specified aird
     */
    private void assertNumberOfBackupFiles(int expected, IProject project, String airdFileName) {
        assertEquals("It should be no backup file before repair.", expected, getNumberOfBackupFiles(project, airdFileName));
    }

    private void changeNameOfPCPackage(SWTBotSiriusDiagramEditor editor, String oldName, String newName) {
        editor.getEditPart(oldName).parent().select();
        SWTBotUtils.waitAllUiEvents();

        /* retrieve the properties view */
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        final SWTBot propertiesViewBot = propertiesView.bot();
        final SWTBotTree tree = propertiesViewBot.tree();

        /* Change the name of the package by properties */
        final SWTBotTreeItem packageName = tree.getTreeItem(oldName).getNode("Name");
        packageName.select();
        packageName.click();
        ICondition done = new OperationDoneCondition();
        propertiesViewBot.text().setText(newName);
        tree.select(0);
        propertiesViewBot.waitUntil(done);
    }

    /**
     * Compute the number of backup files corresponding to this aird file in this project. A backup file starts with the
     * name of the original file and ends with "aird.old".
     * 
     * @param project
     *            The project containg the aird file
     * @param airdFileName
     *            The name of the file
     * @return the number of backup files.
     */
    private int getNumberOfBackupFiles(IProject project, String airdFileName) {
        int numberOfBackup = 0;
        try {
            String airdFileNameWithoutExtension = airdFileName.substring(0, airdFileName.lastIndexOf("."));
            IResource[] members = project.members();
            for (int i = 0; i < members.length; i++) {
                IResource member = members[i];
                if (member.getName().startsWith(airdFileNameWithoutExtension) && member.getName().contains("aird.old")) {
                    numberOfBackup++;
                }
            }
        } catch (CoreException e) {
            // Do nothing
        }
        return numberOfBackup;
    }

    @Override
    protected void tearDown() throws Exception {
        Platform.removeLogListener(logListener);
        super.tearDown();
    }

}
