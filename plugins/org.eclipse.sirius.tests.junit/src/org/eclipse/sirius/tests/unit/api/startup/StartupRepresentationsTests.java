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
package org.eclipse.sirius.tests.unit.api.startup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionHelper;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

public class StartupRepresentationsTests extends SiriusDiagramTestCase {
    /**
     * The root path where the .odesign files used for the tests are stored.
     */
    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/startup";

    private static final String SESSION_MODEL_PATH = "/data/unit/modelers/startup/";

    private static final String SEMANTIC_PATH = "/data/unit/modelers/ecore/";

    /**
     * The path of the semantic model used for the test.
     */
    private static final String SEMANTIC_MODEL_FILENAME = "test.ecore";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + SEMANTIC_PATH + SEMANTIC_MODEL_FILENAME;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testDiagramStartupNoInit_NewSession() throws Exception {
        createNewSession("diagram-startup-noinit.odesign", "testDiagramStartupNoInit_NewSession.aird");
        final int openedBefore = getOpenedEditors().size();
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("No editors should be opened.", openedBefore, getOpenedEditors().size());
    }

    public void testTableStartupNoInit_NewSession() throws Exception {
        createNewSession("table-startup-noinit.odesign", "testTableStartupNoInit_NewSession.aird");
        final int openedBefore = getOpenedEditors().size();
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("No editors should be opened.", openedBefore, getOpenedEditors().size());
    }

    public void testDiagramStartupInit_NewSession() throws Exception {
        createNewSession("diagram-startup-init.odesign", "testDiagramStartupInit_NewSession.aird");
        final int openedBefore = getOpenedEditors().size();
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("Startup diagram editor should be opened.", 1 + openedBefore, getOpenedEditors().size());
    }

    public void testTableStartupInit_NewSession() throws Exception {
        createNewSession("table-startup-init.odesign", "testTableStartupInit_NewSession.aird");
        final int openedBefore = getOpenedEditors().size();
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("Startup table editor should be opened.", 1 + openedBefore, getOpenedEditors().size());
    }

    public void testDiagramStartup_ExistingRepresentation() throws Exception {
        String sessionFilename = "diagram-startup.aird";
        openSession(sessionFilename);
        final int openedBefore = getOpenedEditors().size();
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        assertEquals("Existing 'showOnStartup' diagram should be opened", 1 + openedBefore, getOpenedEditors().size());
    }

    public void testTableStartup_ExistingRepresentation() throws Exception {
        String sessionFilename = "table-startup.aird";
        openSession(sessionFilename);
        final int openedBefore = getOpenedEditors().size();
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("Existing 'showOnStartup' table should be opened", 1 + openedBefore, getOpenedEditors().size());
    }

    private void createNewSession(final String modelerFileName, final String sessionModelFileName) throws Exception {
        URI semanticResourceURI = URI.createPlatformPluginURI(SEMANTIC_MODEL_PATH, true);
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + sessionModelFileName, true);

        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);
        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH + "/" + modelerFileName));

        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        Resource semanticResource = session.getSemanticResources().iterator().next();
        semanticModel = semanticResource.getContents().get(0);

        final Viewpoint viewpoint = viewpoints.iterator().next();
        activateViewpoint(viewpoint.getName());

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();
    }

    private Collection<Viewpoint> loadGroup(final String modelerDescriptionPath) throws Exception {
        Group group = (Group) ModelUtils.load(URI.createPlatformPluginURI(modelerDescriptionPath, true), session.getTransactionalEditingDomain().getResourceSet());
        return group.getOwnedViewpoints();
    }

    private void openSession(String sessionFileName) throws Exception {
        genericSetUp(Collections.singletonList(SEMANTIC_MODEL_PATH), Collections.<String> emptyList(), "/" + SiriusTestsPlugin.PLUGIN_ID + SESSION_MODEL_PATH + "/" + sessionFileName);
    }

    private Collection<IEditorReference> getOpenedEditors() {
        final IEditorReference[] refs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        if (refs == null) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(refs);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
