/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.session;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Check the loaded resources during different steps of session opening.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class OpenSessionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/session/open/";

    private static final String SEMANTIC_MODEL_FILENAME = "test.ecore";

    private static final String AIRD_MODEL_FILENAME = "representations.aird";

    private SessionManagerListener sessionManagerListener = null;

    @Override
    protected void setUp() throws Exception {

        sessionManagerListener = new SessionManagerListener.Stub() {
            @Override
            public void notify(Session updated, int notification) {
                super.notify(updated, notification);
                if (notification == SessionListener.OPENING) {
                    ResourceSet resourceSet = session.getTransactionalEditingDomain().getResourceSet();
                    EList<Resource> resources = resourceSet.getResources();
                    for (Resource resource : resources) {
                        assertFalse(SEMANTIC_MODEL_FILENAME + " semantic resource is loaded at SessionListener.OPENING notification", resource.getURI().toString().contains(SEMANTIC_MODEL_FILENAME));
                    }
                }
            }

            @Override
            public void notifyAddSession(Session newSession) {
                ResourceSet resourceSet = session.getTransactionalEditingDomain().getResourceSet();
                EList<Resource> resources = resourceSet.getResources();
                for (Resource resource : resources) {
                    assertFalse(SEMANTIC_MODEL_FILENAME + " semantic resource is loaded at notifyAddSession", resource.getURI().toString().contains(SEMANTIC_MODEL_FILENAME));
                }
            }
        };
        SessionManager.INSTANCE.addSessionsListener(sessionManagerListener);

        super.setUp();
    }

    /**
     * Test that semantic resources are not loaded during the beginning of the
     * session opening.
     * 
     * @throws Exception
     */
    public void testResourceLoadingAtOpenSession() throws Exception {

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, AIRD_MODEL_FILENAME, SEMANTIC_MODEL_FILENAME);
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + AIRD_MODEL_FILENAME, true);

        session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
    }

    @Override
    protected void tearDown() throws Exception {
        SessionManager.INSTANCE.removeSessionsListener(sessionManagerListener);
        super.tearDown();
    }

}
