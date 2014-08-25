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
package org.eclipse.sirius.tests.swtbot;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;

/**
 * @author pcdavid
 */
public class SessionCreationTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "tc-1850.ecore";

    private static final String SESSION_FILE_1 = "tc-1850-1.aird";

    private static final String SESSION_FILE_2 = "tc-1850-2.aird";

    private static final String DATA_UNIT_DIR = "data/unit/session/sessionCreation/tc-1850/";

    private static final String URI_ROOT = "platform:/resource/DesignerTestProject";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE_1, SESSION_FILE_2);
    }

    /**
     * Tests that when a session is already opened on a given semantic model,
     * creating an new session (using the "New > Local session" wizard) adds the
     * new .aird file to the existing session instead of creating a second one.
     */
    /*
     * FIXME Test disabled for now: the second call to createNewLocalSession()
     * fails to open the .aird. This is not a problem of the test or test
     * framework, it is reproducible manually (although not always, especially
     * not during debugging...).
     */
    public void _testCreateLocalSessionOnAlreadyOpenedSemanticModel() {
        openSessionFromExistingAird("tc-1850-1.aird");
        assertExactlyOneSessionWithContents(new String[] { URI_ROOT + "/tc-1850-1.aird" }, new String[] { URI_ROOT + "/tc-1850.ecore" });

        createNewLocalSession("tc-1850.ecore");
        assertExactlyOneSessionWithContents(new String[] { URI_ROOT + "/tc-1850-1.aird", URI_ROOT + "/tc-1850.aird" }, new String[] { URI_ROOT + "/tc-1850.ecore" });
    }

    /**
     * Tests that when a session is already opened on a given semantic model,
     * opening a second one from a existing .aird file doesn't adds the new
     * .aird to the existing session but instead creates a second one.
     * 
     * Changed
     * test case for VP-1502 : one editing domain per session => one Session
     * (one EditingDomain) per session resource (*.aird).
     */
    public void testOpenLocalSessionOnAlreadyOpenedSemanticModel() {
        openSessionFromExistingAird("tc-1850-1.aird");
        assertExactlyOneSessionWithContents(new String[] { URI_ROOT + "/tc-1850-1.aird" }, new String[] { URI_ROOT + "/tc-1850.ecore" });
        bot.sleep(200);
        openSessionFromExistingAird("tc-1850-2.aird");

        final Collection<Session> openedSessions = SessionManager.INSTANCE.getSessions();
        assertEquals("a second session should exist.", 2, openedSessions.size());
    }

    private void assertExactlyOneSessionWithContents(final String[] analysisUris, final String[] semanticUris) {
        final Collection<Session> openedSessions = SessionManager.INSTANCE.getSessions();
        assertEquals("Only one session should exist.", 1, openedSessions.size());
        final Session theSession = openedSessions.iterator().next();
        assertTrue("The session is not of the right type.", theSession instanceof DAnalysisSession);
        assertHasAnalysisResources(theSession, analysisUris);
        assertHasSemanticResources(theSession, semanticUris);
    }

    private void assertHasAnalysisResources(final Session session, final String[] analysisUris) {
        assertEquals("The session should have exactly " + analysisUris.length + " analysis resource(s).", analysisUris.length, session.getAllSessionResources().size());
        for (final String uri : analysisUris) {
            assertHasAnalysisResource(uri, session);
        }
    }

    private void assertHasSemanticResources(final Session session, final String[] semanticUris) {
        assertEquals("The session should have exactly " + semanticUris.length + " semantic resource(s).", semanticUris.length, session.getSemanticResources().size());
        for (final String uri : semanticUris) {
            assertHasSemanticResource(uri, session);
        }
    }

    private void assertHasAnalysisResource(final String uri, final Session session) {
        for (final Resource res : session.getAllSessionResources()) {
            if (uri.equals(res.getURI().toString())) {
                return;
            }
        }
        fail("Analysis resource for " + uri + " not found in session " + session);
    }

    private void assertHasSemanticResource(final String uri, final Session session) {
        for (final Resource res : session.getSemanticResources()) {
            if (uri.equals(res.getURI().toString())) {
                return;
            }
        }
        fail("Semantic resource for " + uri + " not found in session " + session);
    }

    private void openSessionFromExistingAird(final String airdName) {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", airdName);
        designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    private UILocalSession createNewLocalSession(final String semanticResourceName) {
        final UIResource semanticUiResource = new UIResource(designerProject, "/", semanticResourceName);
        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(semanticUiResource);
        final UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectNoViewpoint();
        return localSession;
    }
}
