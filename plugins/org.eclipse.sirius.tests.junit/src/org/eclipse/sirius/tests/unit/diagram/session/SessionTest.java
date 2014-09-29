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
package org.eclipse.sirius.tests.unit.diagram.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;

/**
 * @author fmorel
 * 
 *         Test Model Content Eclipse View.
 */
public class SessionTest extends SiriusDiagramTestCase {

    private static final int MAX_NUMBER_OF_SESSION = 10;

    /** Path to the directory containing resources. */
    public static final String PLUGIN = "org.eclipse.sirius.tests.sample.docbook.design";

    public static final String PATH = "/sample/";

    private static final String SEMANTIC_MODEL_FILENAME = "simple.docbook";

    private static final String SESSION_MODEL_FILENAME = "test.aird";

    protected URI semanticResourceURI;

    protected URI sessionResourceURI;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(PLUGIN, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);
        sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true);
    }

    /**
     * Check that multiple session creation and session close works.
     * 
     * @throws Exception
     */
    public void testManyCreateAndCloseSession() throws Exception {
        List<CommandStack> commandStacks = new ArrayList<CommandStack>();
        Map<Session, List<Resource>> resources = new HashMap<Session, List<Resource>>();
        for (int i = 0; i < MAX_NUMBER_OF_SESSION; i++) {
            session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
            Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            CommandStack commandStack = session.getTransactionalEditingDomain().getCommandStack();
            commandStacks.add(commandStack);
            commandStack.execute(addSemanticResourceCmd);
            session.open(new NullProgressMonitor());
            List<Resource> sessionResources = new ArrayList<Resource>();
            sessionResources.add(session.getSessionResource());
            sessionResources.addAll(session.getSemanticResources());
            resources.put(session, sessionResources);

            assertTrue("The session has not been added to the session manager", SessionManager.INSTANCE.getSessions().contains(session));
            closeSession(session);
            assertFalse("The session has not been removed from the session manager", SessionManager.INSTANCE.getSessions().contains(session));
            assertFalse("The session should be closed", session.isOpen());
            assertEquals("After session closing, all resources should be unloaded and anymore referenced", Collections.emptyList(), domain.getResourceSet().getResources());
        }

        /* the following test is necessary to ensure that we do not leak */
        TestsUtil.synchronizationWithUIThread();
        for (CommandStack commandStack : commandStacks) {
            assertFalse("Normally, we should not undo anything because the stack should be empty after the closing of all the sessions (check to avoid memory leak).", commandStack.canUndo());
            assertNull("Normally, we should have nothing has most recent command because the stack should be empty after the closing of all the sessions (check to avoid memory leak).",
                    commandStack.getMostRecentCommand());
        }
        for (Entry<Session, List<Resource>> entry : resources.entrySet()) {
            TransactionalEditingDomain domain = entry.getKey().getTransactionalEditingDomain();
            List<Resource> value = entry.getValue();
            for (Resource resource : value) {
                assertEquals("After session closing, no more IUndoableOperations should persist in the DefaultOperationHistory with ResourceUndoContext referencing resource of the session", 0,
                        OperationHistoryFactory.getOperationHistory().getUndoHistory(new ResourceUndoContext(domain, resource)).length);
            }
        }
    }
}
