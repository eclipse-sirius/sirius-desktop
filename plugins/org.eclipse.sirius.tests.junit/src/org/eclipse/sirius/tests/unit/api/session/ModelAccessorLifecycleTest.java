/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;

/**
 * Check Permission Authority and Model Accessor during lifecycle of a Session.
 * 
 * @author Laurent Redor
 */
public class ModelAccessorLifecycleTest extends SiriusTestCase {
    private static final String PATH = "/data/unit/session/";

    private static final String MODELER_MODEL_FILENAME = "sample.odesign";

    private static final String SEMANTIC_MODEL_FILENAME = "sampleSession.uml";

    private static final String VIEWPOINT_NAME = "sampleSession";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + MODELER_MODEL_FILENAME;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, MODELER_MODEL_FILENAME);
    }

    /**
     * Check that permission does not change after the first Viewpoint
     * activation.
     * 
     * @throws Exception
     *             In case of problem.
     */
    public void testPermissionAfterEnablingFirstViewpoint() throws Exception {
        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);
        // Create a first session (without any Viewpoint selected)
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test.aird", true);
        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        // Add the semantic model to it.
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);
        semanticModel = session.getSemanticResources().iterator().next().getContents().get(0);
        // Check that the root of this semantic model is editable.
        assertTrue("The selected element must be editable.", session.getModelAccessor().getPermissionAuthority().canEditInstance(semanticModel));
        // Create a second session (without any Viewpoint selected)
        URI session2ResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test2.aird", true);
        SessionCreationOperation session2CreationOperation = new DefaultLocalSessionCreationOperation(session2ResourceURI, new NullProgressMonitor());
        session2CreationOperation.execute();
        // Check that the root of this semantic model is editable.
        assertTrue("The selected element must be editable.", session.getModelAccessor().getPermissionAuthority().canEditInstance(semanticModel));
        // Activate the read-only permission authority from the first session:
        // now semantic elements cannot be edited
        ((ReadOnlyPermissionAuthority) session.getModelAccessor().getPermissionAuthority()).activate();
        // Check that the root of this semantic model is not editable.
        assertFalse("The selected element must not be editable.", session.getModelAccessor().getPermissionAuthority().canEditInstance(semanticModel));
        // Select a viewpoint in the first session
        loadModeler(URI.createPlatformPluginURI(MODELER_PATH, true), session.getTransactionalEditingDomain());
        activateViewpoint(VIEWPOINT_NAME);
        // Check that the root of this semantic model is not editable.
        assertFalse("The selected element must not be editable.", session.getModelAccessor().getPermissionAuthority().canEditInstance(semanticModel));
    }

    /**
     * Check that permission authority does not change after another session
     * creation.
     * 
     * @throws Exception
     *             In case of problem.
     */
    public void testPermissionAuthorityAfterCreatingAnotherSession() throws Exception {
        // Create a first session (without any Viewpoint selected)
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test.aird", true);
        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        // Check permission authority
        assertEquals("The permission authority should be the same for the 2 ways to access to it.", session.getModelAccessor().getPermissionAuthority(),
                PermissionAuthorityRegistry.getDefault().getPermissionAuthority(session.getTransactionalEditingDomain().getResourceSet()));
        // Create a second session (without any Viewpoint selected)
        URI session2ResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "test2.aird", true);
        SessionCreationOperation session2CreationOperation = new DefaultLocalSessionCreationOperation(session2ResourceURI, new NullProgressMonitor());
        session2CreationOperation.execute();
        // Check permission authority
        assertEquals("The permission authority should be the same for the 2 ways to access to it.", session.getModelAccessor().getPermissionAuthority(),
                PermissionAuthorityRegistry.getDefault().getPermissionAuthority(session.getTransactionalEditingDomain().getResourceSet()));
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
