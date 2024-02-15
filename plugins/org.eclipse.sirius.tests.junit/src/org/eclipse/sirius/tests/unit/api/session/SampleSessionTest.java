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
package org.eclipse.sirius.tests.unit.api.session;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;

public class SampleSessionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/session/";

    private static final String MODELER_MODEL_FILENAME = "sample.odesign";

    private static final String SEMANTIC_MODEL_FILENAME = "sampleSession.uml";

    private static final String VIEWPOINT_NAME = "sampleSession";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + MODELER_MODEL_FILENAME;

    protected Collection<Viewpoint> loadGroup(final String modelerDescriptionPath) throws Exception {
        Group group = (Group) ModelUtils.load(URI.createPlatformPluginURI(modelerDescriptionPath, true), session.getTransactionalEditingDomain().getResourceSet());
        return group.getOwnedViewpoints();
    }

    /**
     * Try to open a session.
     * 
     * @throws Exception
     */
    public void testOpenSession() throws Exception {

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);

        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        semanticModel = session.getSemanticResources().iterator().next().getContents().get(0);

        final Viewpoint viewpoint = getSiriusByName(VIEWPOINT_NAME);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                SiriusResourceHelper.createViews(session, Collections.singletonList(viewpoint), session.getTransactionalEditingDomain().getResourceSet(), semanticModel);
            }
        });

        Assert.assertNotNull("The session is null", session);
        Assert.assertTrue("The session is closed", session.isOpen());

    }

    /**
     * Try to save a session in a thread which is not the UI thread.
     * 
     * @throws Exception
     *             if test fails
     */
    public void testSaveSessionInAnotherThreadThanUIThread() throws Exception {
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        final UncaughtExceptionHandler exceptionHandler = EasyMock.createMock(UncaughtExceptionHandler.class);
        // Nothing to replay, this handler must not be called at all
        EasyMock.replay(exceptionHandler);

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);

        final CountDownLatch latch = new CountDownLatch(1);

        final Thread t = new Thread() {
            @Override
            public void run() {
                session.save(new NullProgressMonitor());
                latch.countDown();
            }
        };
        t.start();

        // Above session.save() execute new task in thread UI
        // But thread UI is the current thread, so we need to poll all jobs
        // before testing anything else
        PlatformUI.getWorkbench().getDisplay().sleep();

        TestsUtil.synchronizationWithUIThread();

        Assert.assertTrue("Forked thread didn't end before timeout", latch.await(5, TimeUnit.SECONDS));

        EasyMock.verify(exceptionHandler);
    }

    /**
     * Try to open a diagram.
     * 
     * @throws Exception
     *             if test fails
     */
    public void testOpenDiagram() throws Exception {

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);

        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        semanticModel = session.getSemanticResources().iterator().next().getContents().get(0);

        final Viewpoint viewpoint = getSiriusByName(VIEWPOINT_NAME);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                SiriusResourceHelper.createViews(session, Collections.singletonList(viewpoint), session.getTransactionalEditingDomain().getResourceSet(), semanticModel);
            }
        });

        Assert.assertNotNull("The session is null", session);
        Assert.assertTrue("The session is closed", session.isOpen());

        Assert.assertFalse(session.getSessionResource().getContents().isEmpty());

        final Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(semanticModel, session);
        Assert.assertTrue("One and only one representation", representations.size() == 1);
        final DRepresentation representation = representations.iterator().next();
        Assert.assertNotNull(representation.eResource());

        if (representation instanceof DSemanticDiagram) {
            session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

                @Override
                protected void doExecute() {
                    ((DSemanticDiagram) representation).setTarget(semanticModel);
                }

            });
        }
        //
        // Opens the representation.
        final long start = new Date().getTime();
        DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final long end = new Date().getTime();

        Assert.assertTrue("The opening of the representation is too long", (end - start) < 15000);

    }

    /**
     * Test the session removed when a representation is opened. Test VP-2104.
     * 
     * @throws Exception
     *             if exception thrown
     */
    public void testDeleteSessionWithRepresentationOpened() throws Exception {
        // Activate error log
        genericSetUp();
        // Create the session and open the representation
        testOpenDiagram();

        // Remove the session as representation opened
        SessionManager.INSTANCE.remove(session);
        TestsUtil.emptyEventsFromUIThread();

        // Clean environment correctly.
        if (session != null) {
            final IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
            if (sessionUI != null) {
                SessionUIManager.INSTANCE.remove(sessionUI);
                sessionUI.close();
                TestsUtil.synchronizationWithUIThread();
            }
            if (session.isOpen()) {
                session.close(new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
            }
            session = null;
        }
        for (final Session managerSession : new ArrayList<Session>(SessionManager.INSTANCE.getSessions())) {
            if (managerSession.isOpen()) {
                managerSession.close(new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
            }
        }

        TestsUtil.emptyEventsFromUIThread();

    }

    /**
     * Test the session closing.
     * 
     * @throws Exception
     *             if exception thrown
     */
    public void testCloseSession() throws Exception {
        // Activate error log
        genericSetUp();
        // Create the session and open the representation
        testOpenDiagram();

        TestsUtil.emptyEventsFromUIThread();

        assertTrue("This test requires the error detection", platformProblemsListener.isErrorCatchActive());

        assertNotNull("Session should still been there.", session);
        assertTrue("Session should still been opened.", session.isOpen());
        IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
        assertNotNull("UI Session should still exists.", sessionUI);
        assertTrue("UI Session should still been opened.", sessionUI.isOpen());

        session.close(new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        assertFalse("Session should no more be opened.", session.isOpen());
        assertFalse("UI Session should no more be opened.", sessionUI.isOpen());
        sessionUI = SessionUIManager.INSTANCE.getUISession(session);
        assertNull("UI Session should no more exists.", sessionUI);

        for (final Session managerSession : new ArrayList<Session>(SessionManager.INSTANCE.getSessions())) {
            if (managerSession == session) {
                fail("Session should have been removed from manager on close.");
            }
        }

        // Check that a second session.close() call does not trigger platformProblemsListener.getErrors().
        session.close(new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        // Re-check that session is closed, ui session has not been recreated.
        assertFalse("Session should no more be opened.", session.isOpen());
        sessionUI = SessionUIManager.INSTANCE.getUISession(session);
        assertNull("UI Session should no more exists.", sessionUI);

        for (final Session managerSession : new ArrayList<Session>(SessionManager.INSTANCE.getSessions())) {
            if (managerSession == session) {
                fail("Session should have been removed from manager on close.");
            }
        }
    }

    /**
     * Returns the viewpoint having the given name.
     * 
     * @param viewpointName
     *            the name of the viewpoint.
     * @return the viewpoint having the given name.
     */
    protected Viewpoint getSiriusByName(final String viewpointName) {
        Viewpoint result = null;
        final Iterator<Viewpoint> iterSiriuss = viewpoints.iterator();
        while (iterSiriuss.hasNext() && result == null) {
            final Viewpoint currentSirius = iterSiriuss.next();
            if (currentSirius.getName() != null && currentSirius.getName().equals(viewpointName)) {
                result = currentSirius;
            }
        }
        Assert.assertNotNull("No viewpoint found. Looking for: " + viewpointName, result);
        return result;
    }
}
