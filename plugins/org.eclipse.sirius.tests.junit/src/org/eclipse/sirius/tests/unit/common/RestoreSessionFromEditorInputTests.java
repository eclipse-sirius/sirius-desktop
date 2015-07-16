/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.NavigationHistoryAction;

import com.google.common.collect.Lists;

/**
 * A test ensuring that restoring editors from memento work as expected
 * (especially when reopening an editor from Navigation History).
 * 
 * @author alagarde
 */
@SuppressWarnings("restriction")
public class RestoreSessionFromEditorInputTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_ROOT = "/vp-2067/2067.ecore";

    private static final String AIRD_ROOT = "/vp-2067/2067.aird";

    private static final String SEMANTIC_FRAGMENT = "/vp-2067/2067_p1p1.ecore";

    private static final String AIRD_FRAGMENT = "/vp-2067/2067_p1p1.aird";

    private DRepresentation controledRepresentation;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/control", SEMANTIC_ROOT, AIRD_ROOT, SEMANTIC_FRAGMENT, AIRD_FRAGMENT);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + SEMANTIC_ROOT), Collections.<String> emptySet(), TEMPORARY_PROJECT_NAME + AIRD_ROOT);

        controledRepresentation = ((DAnalysis) Lists.newArrayList(session.getAllSessionResources()).get(1).getContents().iterator().next()).getOwnedViews().get(0).getOwnedRepresentations().get(0);
    }

    /**
     * Ensures that the following scenario works as expected:
     * <ul>
     * <li>Open
     * <li>Open an editor on a representation contained in the air fragment</li>
     * <li>Close this editor</li>
     * <li>Restore this editor from NavigationHistory</li>
     * <li>Check that no new session was opened (a new session on the fragment
     * should not have been created, we should use the main session)</li>
     * </ul>
     */
    public void testRestoreSessionFromHistory() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * junit.framework.AssertionFailedError: Wrong initial state:
             * expecting only one active session expected:<1> but was:<2> at
             * junit.framework.Assert.fail(Assert.java:57) at
             * junit.framework.Assert.failNotEquals(Assert.java:329) at
             * junit.framework.Assert.assertEquals(Assert.java:78) at
             * junit.framework.Assert.assertEquals(Assert.java:234) at
             * junit.framework.TestCase.assertEquals(TestCase.java:401) at
             * org.eclipse.sirius.tests.unit.common.
             * RestoreSessionFromEditorInputTests.testRestoreSessionFromHistory(
             * RestoreSessionFromEditorInputTests.java:61)
             */
            return;
        }
        testRestoreSessionFromHistory(false);
    }

    /**
     * Same test as previous but close the session before restoring the editor.
     */
    public void testRestoreSessionFromHistoryWithClosedSession() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * junit.framework.AssertionFailedError: Wrong initial state:
             * expecting only one active session expected:<1> but was:<2> at
             * junit.framework.Assert.fail(Assert.java:57) at
             * junit.framework.Assert.failNotEquals(Assert.java:329) at
             * junit.framework.Assert.assertEquals(Assert.java:78) at
             * junit.framework.Assert.assertEquals(Assert.java:234) at
             * junit.framework.TestCase.assertEquals(TestCase.java:401) at
             * org.eclipse.sirius.tests.unit.common.
             * RestoreSessionFromEditorInputTests.testRestoreSessionFromHistory(
             * RestoreSessionFromEditorInputTests.java:61)
             */
            return;
        }
        testRestoreSessionFromHistory(true);
    }

    private void testRestoreSessionFromHistory(boolean closeSession) {
        assertEquals("Wrong initial state: expecting only one active session", 1, SessionManager.INSTANCE.getSessions().size());

        // Step 1: open editor on the controlled representation
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, controledRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Step 2: close editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        if (closeSession) {
            closeSession(session);
            assertTrue("Session should be closed and removed from the manager", SessionManager.INSTANCE.getSessions().isEmpty() && !session.isOpen());
        }

        // Step 3: restore editor from navigation history
        new NavigationHistoryAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), false).run();
        TestsUtil.synchronizationWithUIThread();
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        assertNotNull("An editor should have been opened through navigation history", activePage.getActiveEditor());

        // => This should not have created a new session but use the current one
        assertEquals("No new session should have been opened while restoring editor from navigation history", 1, SessionManager.INSTANCE.getSessions().size());
        Session currentSession = SessionManager.INSTANCE.getSessions().iterator().next();
        if (closeSession) {
            assertNotSame(session, currentSession);
        } else {
            assertEquals(session, currentSession);
        }
        activePage.closeAllEditors(false);
        TestsUtil.synchronizationWithUIThread();
    }
}
