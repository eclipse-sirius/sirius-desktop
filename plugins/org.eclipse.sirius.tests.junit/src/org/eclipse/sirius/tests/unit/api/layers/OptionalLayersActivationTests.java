/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.layers;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class OptionalLayersActivationTests extends SiriusDiagramTestCase {

    private static final String VP_INIT_AND_ACTIVE = "init and active";

    private static final String VP_EXTENSION_AND_ACTIVE = "extension and active";

    private static final String VP_EXTENSION_AND_NOT_ACTIVE = "extension and not(active)";

    private static final String VP_INIT_AND_NOT_ACTIVE = "init and not(active)";

    /**
     * The root path where the .odesign files used for the tests are stored.
     */
    private static final String MODELERS_ROOT = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/layers_activation";

    private static final String MODELER_PATH = MODELERS_ROOT + "/optional_layers_activation.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/data/unit/modelers/ecore/";

    /**
     * The path of the semantic model used for the test.
     */
    private static final String SEMANTIC_MODEL_FILENAME = "test.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SEMANTIC_MODEL_PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
    }

    protected Collection<Viewpoint> loadGroup(final String modelerDescriptionPath) throws Exception {
        Group group = (Group) ModelUtils.load(URI.createPlatformPluginURI(modelerDescriptionPath, true), session.getTransactionalEditingDomain().getResourceSet());
        return group.getOwnedViewpoints();
    }

    public void testCreateNotActiveByDefault() throws Exception {

        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);

        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "/testCreateNotActiveByDefault.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        Command addSemCommand = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemCommand);

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();

        enableSirius(VP_INIT_AND_NOT_ACTIVE);
        assertEquals(1, findDiagram(session).getActivatedLayers().size());
    }

    public void testCreateActiveByDefault() throws Exception {
        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);

        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "/testCreateActiveByDefault.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        Command addSemCommand = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemCommand);

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();

        enableSirius(VP_INIT_AND_ACTIVE);
        assertEquals(2, findDiagram(session).getActivatedLayers().size());
    }

    public void testAddExtensionSiriusNotActiveByDefault() throws Exception {
        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);

        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "/testAddExtensionSiriusNotActiveByDefault.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        Command addSemCommand = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemCommand);

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();

        enableSirius(VP_INIT_AND_NOT_ACTIVE);
        assertEquals(1, findDiagram(session).getActivatedLayers().size());
        enableSirius(VP_EXTENSION_AND_NOT_ACTIVE);
        assertEquals(1, findDiagram(session).getActivatedLayers().size());
        deactivateViewpoint(VP_EXTENSION_AND_NOT_ACTIVE);
        assertEquals(1, findDiagram(session).getActivatedLayers().size());
    }

    public void testAddExtensionSiriusActiveByDefault() throws Exception {
        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);

        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "/testAddExtensionSiriusActiveByDefault.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        Command addSemCommand = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemCommand);

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();

        enableSirius(VP_INIT_AND_NOT_ACTIVE);
        assertEquals(1, findDiagram(session).getActivatedLayers().size());
        enableSirius(VP_EXTENSION_AND_ACTIVE);
        assertEquals(2, findDiagram(session).getActivatedLayers().size());
        deactivateViewpoint(VP_EXTENSION_AND_ACTIVE);
        assertEquals(1, findDiagram(session).getActivatedLayers().size());
    }

    public void testActivationStatusStoredInSession() throws Exception {
        URI semanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, true);

        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "/testActivationStatusStoredInSession.aird", true);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        viewpoints.addAll(loadGroup(MODELER_PATH));

        Command addSemCommand = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemCommand);

        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();

        enableSirius(VP_INIT_AND_ACTIVE);
        assertEquals(2, findDiagram(session).getActivatedLayers().size());

        // Explicitly disable the optional layer and save the new state
        final AdditionalLayer layer = findFirstAdditionalLayer();
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                findDiagram(session).getActivatedLayers().remove(layer);
            }
        });
        SessionUIManager.INSTANCE.getUISession(session).close(true);
        TestsUtil.emptyEventsFromUIThread();

        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        assertEquals(1, findDiagram(session).getActivatedLayers().size());
    }

    private AdditionalLayer findFirstAdditionalLayer() {
        for (final Layer l : findDiagram(session).getActivatedLayers()) {
            if (l instanceof AdditionalLayer) {
                return (AdditionalLayer) l;
            }
        }
        return null;
    }

    private void enableSirius(final String name) throws Exception {
        activateViewpoint(name);
    }

    protected Group getSiriussGroup(final String modelerPath, final TransactionalEditingDomain domain) throws IOException {
        return (Group) ModelUtils.load(URI.createPlatformPluginURI(modelerPath, true), domain.getResourceSet());
    }

    protected EPackage loadEcoreModel(final String semanticModelPath, final TransactionalEditingDomain domain) throws IOException {
        return (EPackage) ModelUtils.load(URI.createPlatformPluginURI(semanticModelPath, true), domain.getResourceSet());
    }

    private DDiagram findDiagram(final Session session) {
        for (final DView view : session.getOwnedViews()) {
            for (final DRepresentation repr : new DViewQuery(view).getLoadedRepresentations()) {
                if (repr instanceof DDiagram) {
                    return (DDiagram) repr;
                }
            }
        }
        return null;
    }
}
