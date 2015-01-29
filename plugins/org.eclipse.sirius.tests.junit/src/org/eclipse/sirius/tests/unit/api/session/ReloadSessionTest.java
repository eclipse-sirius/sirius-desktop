/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.session;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Ensure that when a fragment is modified outside of Eclipse, the session is
 * not changed after a refresh on project. Correspond to VP-3654
 * 
 * @author jdupont
 * 
 */
public class ReloadSessionTest extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String PATH = "/data/unit/session/reload/";

    private static final String PATH_COPY = "/data/unit/session/reload/copy/";

    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String SEMANTIC_RESOURCE_FRAGRMENT_NAME = "My_E1.ecore";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private static final String SESSION_RESOURCE_FRAGMENT = "My_E1.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_FRAGRMENT_NAME, SESSION_RESOURCE_FRAGMENT);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
    }

    /**
     * Ensure that when a fragment is modified outside of Eclipse, the session
     * is the same after the reload.
     * <ol>
     * <li>Retrieve the Session</li>
     * <li>Modify from externally the fragment session. For this, copy/paste
     * file with same name but with blank space additional</li>
     * <li>The copy/paste launches a refresh on fragment session, so there is no
     * necessary need to launch a refresh on project</li>
     * <li>Check that the session is always the same</li>
     * </ol>
     * 
     * @throws Exception
     */
    public void testModifyExternallyFragmentSession() throws Exception {
        // Retrieve the session and stock it
        session = loadSession();

        final String pluginFilePath = PATH_COPY + SESSION_RESOURCE_FRAGMENT;
        final String wksPath = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FRAGMENT;
        // Modify externally session file (Copy the same file as existing in
        // project
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginFilePath, wksPath);
        TestsUtil.synchronizationWithUIThread();

        // Check the session is the same
        checkSessionIsCorrect(session);
    }

    private Session loadSession() {
        IFile airdFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME));
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(((IFile) airdFile).getFullPath().toString(), true), new NullProgressMonitor());
        if (!session.isOpen()) {
            session.open(new NullProgressMonitor());
        }
        return session;
    }

    private void checkSessionIsCorrect(Session session) {
        IFile airdFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME));
        Session sessionToCompare = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(((IFile) airdFile).getFullPath().toString(), true), new NullProgressMonitor());
        if (!sessionToCompare.isOpen()) {
            sessionToCompare.open(new NullProgressMonitor());
        }
        assertEquals("The session should be the same after refresh", session, sessionToCompare);
    }
}
