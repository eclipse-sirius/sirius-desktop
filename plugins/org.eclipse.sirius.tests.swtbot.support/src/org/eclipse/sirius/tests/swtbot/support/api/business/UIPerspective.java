/**
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.business;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OpenedSessionCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemAvailableCondition;
import org.eclipse.sirius.tests.swtbot.support.internal.business.UISessionCreationWizard;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;

/**
 * Object to manage graphical operations on perspectives.
 * 
 * @author dlecan
 */
public class UIPerspective {

    private static final String VIEWPOINT = "Sirius";

    private static final String WIZARDS_LIST_TITLE = "New";

    private static final String REPRESENTATIONS_FILE_LABEL = "Representations File";

    /**
     * Inner session listener to track session changes
     * 
     * @author dlecan
     */
    private final class OpenedSessionListener extends SessionManagerListener.Stub implements ICondition {

        private Session openedSession;

        /**
         * Returns the openedSession.
         * 
         * @return The openedSession.
         */
        public Session getOpenedSession() {
            return openedSession;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notify(final Session updated, final int notification) {
            switch (notification) {
            case SessionListener.OPENED:
                openedSession = updated;
                break;
            default:
                // Nothing
                break;
            }
        }

        @Override
        public boolean test() throws Exception {
            return openedSession != null;
        }

        @Override
        public void init(SWTBot botInit) {
            // Nothing to do
        }

        @Override
        public String getFailureMessage() {
            return "No received OPENED notification.";
        }
    }

    private final SWTWorkbenchBot bot;

    /**
     * Constructor.
     * 
     * @param bot
     *            SWTBot
     */
    public UIPerspective(final SWTWorkbenchBot bot) {
        this.bot = bot;
    }

    /**
     * Create a project.
     * 
     * @param projectName
     *            name of the created project
     * @return Current {@link UIProject}.
     */
    public UIProject createProject(final String projectName) {
        final IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        try {
            if (!project.exists()) {
                project.create(projectDescription, new NullProgressMonitor());
            }
            project.open(new NullProgressMonitor());
        } catch (final CoreException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
        return new UIProject(projectName);
    }

    /**
     * Delete the project.
     * 
     * @param project
     *            name of the project to delete.
     * @deprecated use {@link EclipseTestsSupportHelper#deleteProject(String)}
     */
    @Deprecated
    public void deleteProject(final UIProject project) {
        deleteProject(project.getName());
    }

    /**
     * Delete the project.
     * 
     * @param projectName
     *            name of the project to delete.
     * @deprecated use {@link EclipseTestsSupportHelper#deleteProject(String)}
     */
    @Deprecated
    public void deleteProject(final String projectName) {
        EclipseTestsSupportHelper.INSTANCE.deleteProject(projectName);
    }

    /**
     * Open the session creation wizard.
     * 
     * @return Current {@link UIPerspective}.
     */
    public SessionChoice openSessionCreationWizard() {

        openRepresentationsFileWizard();

        return new UISessionCreationWizard();
    }

    /**
     * Open the session creation wizard from a specific semantic resource.
     * 
     * @param uiResource
     *            {@link UIResource} to use to openthe wizard.
     * @return Current {@link UIPerspective}.
     */
    public SessionChoice openSessionCreationWizardFromSemanticResource(final UIResource uiResource) {

        uiResource.getProject().selectResource(uiResource);

        openRepresentationsFileWizard();

        return new UISessionCreationWizard(uiResource);

    }

    /**
     * Opens the "New Representation Files" wizard through the File > New > Other... menu.
     */
    private void openRepresentationsFileWizard() {
        bot.menu("File").menu(UIPerspective.WIZARDS_LIST_TITLE).menu("Other...").click();

        bot.waitUntil(Conditions.shellIsActive(UIPerspective.WIZARDS_LIST_TITLE));

        SWTBot wizardListBot = bot.shell(UIPerspective.WIZARDS_LIST_TITLE).bot();
        wizardListBot.text().setText(UIPerspective.REPRESENTATIONS_FILE_LABEL);

        SWTBotTree wizardsTree = wizardListBot.tree();

        wizardsTree.setFocus();

        SWTBotTreeItem viewpointCategory = null;
        try {
            viewpointCategory = wizardsTree.getTreeItem(UIPerspective.VIEWPOINT);
        } catch (WidgetNotFoundException e) {
            // Accessing the tree item can fail when launching the test suite
            // with jenkins. Adding the following condition in a catch section
            // seems to fix it.
            bot.waitUntil(new TreeItemAvailableCondition(wizardsTree, UIPerspective.VIEWPOINT, true));
            viewpointCategory = wizardsTree.getTreeItem(UIPerspective.VIEWPOINT);
        }
        viewpointCategory.expand();

        bot.waitUntil(new TreeItemAvailableCondition(viewpointCategory, UIPerspective.REPRESENTATIONS_FILE_LABEL, true));
        SWTBotTreeItem representationsFileNode = viewpointCategory.getNode(UIPerspective.REPRESENTATIONS_FILE_LABEL);

        representationsFileNode.select();

        wizardListBot.button("Next >").click();

        bot.waitUntil(Conditions.shellIsActive("New Representations File"));
    }

    /**
     * Open directly a session.
     * 
     * @param uiLocalSessionResource
     *            <code>.aird</code> file to use to open the local session.
     * @return UI local session.
     */
    public UILocalSession openSessionFromFile(final UIResource uiLocalSessionResource) {
        return openSessionFromFile(uiLocalSessionResource, false);
    }

    /**
     * Open directly a session.
     * 
     * @param uiLocalSessionResource
     *            <code>.aird</code> file to use to open the local session.
     * @param useMoreThanOneSemanticFiles
     *            true if the session uses more than one semantic files (fragmented file, more complex use case, ...).
     * @return UI local session.
     */
    public UILocalSession openSessionFromFile(final UIResource uiLocalSessionResource, final boolean useMoreThanOneSemanticFiles) {
        // Need to wait later opening of session.
        final OpenedSessionListener openedSessionListener = new OpenedSessionListener();
        SessionManager.INSTANCE.addSessionsListener(openedSessionListener);

        // Open session
        ICondition addedToSessionManager = new OpenedSessionCondition(SessionManager.INSTANCE.getSessions().size() + 1);
        uiLocalSessionResource.openSession();
        SWTBotUtils.waitProgressMonitorClose(OpenRepresentationsFileJob.JOB_LABEL, OpenRepresentationsFileJob.JOB_LABEL, SWTBotUtils.CLOSE_PROGRESS_MONITOR_TIMEOUT, TimeUnit.SECONDS, false);
        // Ensure that the OpenRepresentationsFileJob is finished before continuing
        try {
            Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        } catch (OperationCanceledException | InterruptedException e) {
            fail("The session was not correctly opened: " + e.getMessage());
        }
        bot.waitUntil(addedToSessionManager);
        bot.waitUntil(openedSessionListener);
        SessionManager.INSTANCE.removeSessionsListener(openedSessionListener);

        Session openedSession = openedSessionListener.getOpenedSession();
        if (openedSession == null) {
            Assert.assertTrue("No session is opened!", SessionManager.INSTANCE.getSessions().size() > 0);
        }

        final Collection<Resource> semanticResources = openedSession.getSemanticResources();
        if (useMoreThanOneSemanticFiles) {
            MatcherAssert.assertThat("Semantic resource not found", semanticResources.size(), Matchers.not(Matchers.is(0)));
        } else {
            // Only one semantic resource should be found !
            if (semanticResources.size() > 1) {
                StringBuffer names = new StringBuffer();
                for (Resource resource : semanticResources) {
                    names.append(resource.getURI().toPlatformString(true));
                    names.append(", ");
                }
                names.delete(names.length() - 2, names.length());
                Assert.fail("Too many semantic resources, only one semantic resource is expected. List of semantic resources: " + names);
            }
        }

        Resource semanticResource = semanticResources.iterator().next();
        return new UILocalSession(UIResource.createFromResource(semanticResource), uiLocalSessionResource, openedSession);
    }

    /**
     * Get the opened session.
     * 
     * @param uiResource
     *            the aird resource (it could not exist)
     * @return UI local session.
     */
    public UILocalSession getOpenedSession(final UIResource uiResource) {

        final SessionManager sessionManager = SessionManager.INSTANCE;

        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !sessionManager.getSessions().isEmpty();
            }

            @Override
            public String getFailureMessage() {
                return "no session have been created";
            }
        });

        final Session session = sessionManager.getSessions().iterator().next();

        final Collection<Resource> semanticResources = session.getSemanticResources();

        final Resource semanticResource = semanticResources.iterator().next();
        final UIResource uiSemanticResource = UIResource.createFromResource(semanticResource);

        return new UILocalSession(uiSemanticResource, uiResource, session);

    }

}
