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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

public class DAnalysisSessionTests extends SiriusDiagramTestCase {

    private final int NB_ITERATIONS = 2; // 1000;

    private List<DRepresentation> representations = new ArrayList<DRepresentation>();

    private List<IEditorPart> editors = new ArrayList<IEditorPart>();

    /**
     * incorrect data.
     */
    public static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.junit/data/unit/session/benchmark.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/session/uml2.uml";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint("UML2 Benchmarks Acceleo");
        initViewpoint("UML2 Benchmarks OCL");
    }

    /**
     * Find a diagram description by name.
     * 
     * @param group
     *            group.
     * @param name
     *            name to look for.
     * @return the diagram description or null if not found.
     */
    protected DiagramDescription findDiagramDescription(final String name) {
        for (final Viewpoint vp : viewpoints) {
            for (final RepresentationDescription representation : vp.getOwnedRepresentations()) {
                if (representation instanceof DiagramDescription && ((DiagramDescription) representation).getName().equals(name)) {
                    return (DiagramDescription) representation;
                }
            }

        }
        return null;
    }

    /**
     * @param description
     */
    private void doCreateRepresentations(final DiagramDescription description, final int wantedRepresentations) {
        final Iterator<EObject> it = semanticModel.eAllContents();
        final TransactionalEditingDomain editingDomain = session.getTransactionalEditingDomain();
        final RepresentationDescription representationDescription = description;
        while (it.hasNext()) {
            final EObject cur = it.next();
            if (DialectManager.INSTANCE.canCreate(cur, representationDescription)) {
                CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session, representationDescription, cur, "Diagram for " + cur, new NullProgressMonitor());
                editingDomain.getCommandStack().execute(createRepresentationCommand);
                DRepresentation rep = createRepresentationCommand.getCreatedRepresentation();
                Assert.assertNotNull("The representation has not been created ! ", rep);
                representations.add(rep);
            }
        }
        Assert.assertEquals("We should have " + wantedRepresentations + " representations", wantedRepresentations, representations.size());
    }

    public void testWarmup() throws Exception {
        DiagramDescription description = findDiagramDescription("Acceleo Class Diagram");
        Assert.assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, description);
        doCreateRepresentations(description, 9);
        doCleanup();
        setUp();
        description = findDiagramDescription("OCL Class Diagram");
        Assert.assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, description);
        doCreateRepresentations(description, 9);
        doCleanup();
    }

    public void testAcceleoClassDiagramCreation() throws Exception {
        final DiagramDescription description = findDiagramDescription("Acceleo Class Diagram");
        Assert.assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, description);
        doCreateRepresentations(description, 9);
    }

    public void testAcceleoRefreshRepresentations() throws Exception {
        doRefreshRepresentations();
    }

    /**
     * Check the saving fails correctly (without NPE and with a message in error log) when the saving policy fails.
     */
    public void testSaveSessionWithErrorDuringSave() {
        // Open session
        doOpenSession();
        session.getSessionResource().eAdapters().add(new AdapterImpl() {

            @Override
            public void notifyChanged(Notification msg) {
                if (msg.getNotifier() == session.getSessionResource() && msg.getFeatureID(null) == Resource.RESOURCE__IS_MODIFIED) {
                    throw new RuntimeException("An adapter to have save fails.");
                }
            }

        });
        // Enable viewpoint
        doCreateViews();
        // Create the Acceleo diagrams to make the session dirty
        DiagramDescription description = findDiagramDescription("Acceleo Class Diagram");
        Assert.assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, description);
        doCreateRepresentations(description, 9);
        // Open all representations
        doOpenAllRepresentations();
        assertTrue("The session should be dirty.", editors.get(0).isDirty());
        // Try to save the session
        try {
            session.save(new NullProgressMonitor());
            fail("A RuntimeException should be thrown");
        } catch (RuntimeException e) {

        }
        doCleanup();
    }

    /**
     *
     */
    private void doRefreshRepresentations() {
        final TransactionalEditingDomain editingDomain = session.getTransactionalEditingDomain();
        for (int i = 0; i < NB_ITERATIONS; i++) {
            for (final DRepresentation rep : representations) {
                editingDomain.getCommandStack().execute(new RefreshRepresentationsCommand(editingDomain, defaultProgress, rep));
            }

        }
    }

    public void testAcceleoDiagramOpening() throws Exception {
        doOpenAllRepresentations();
    }

    /**
     *
     */
    private void doOpenAllRepresentations() {
        for (final DRepresentation rep : representations) {
            final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, rep, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            Assert.assertNotNull("Can't open editor", editor);
            editors.add(editor);
        }
    }

    public void testAcceleoCloseEditors() throws Exception {
        doCloseAllEditors();
    }

    /**
     *
     */
    private void doCloseAllEditors() {
        for (final IEditorPart editor : editors) {
            if (editor instanceof DiagramDocumentEditor) {
                ((DiagramDocumentEditor) editor).close(false);
            }
        }
    }

    public void testCleanup() throws Exception {
        doCleanup();
    }

    /**
     *
     */
    private void doCleanup() {
        final IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
        if (sessionUI != null) {
            SessionUIManager.INSTANCE.remove(sessionUI);
            sessionUI.close();
            TestsUtil.emptyEventsFromUIThread();
        }
        if (session != null) {
            doRemoveSession();
            doCloseSession();
            session = null;
        }
        representations = new ArrayList<DRepresentation>();
        editors = new ArrayList<IEditorPart>();
        viewpoints.clear();
    }

    private void doCloseSession() {
        session.close(new NullProgressMonitor());
        Assert.assertFalse("Can't close the session", session.isOpen());
    }

    private void doRemoveSession() {
        SessionManager.INSTANCE.remove(session);
        for (final Session session2 : SessionManager.INSTANCE.getSessions()) {
            Assert.assertFalse("Remove failed", session2.equals(session));
        }
    }

    public void testOCLClassDiagramCreation() throws Exception {
        final DiagramDescription description = findDiagramDescription("OCL Class Diagram");
        Assert.assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, description);
        doCreateRepresentations(description, 9);
    }

    public void testOCLRefreshRepresentations() throws Exception {
        doRefreshRepresentations();
    }

    public void testOCLDiagramOpening() throws Exception {
        doOpenAllRepresentations();
    }

    public void testOCLCloseEditors() throws Exception {
        doCloseAllEditors();
        doCleanup();
    }

    public void testOpenSession() throws Exception {
        doOpenSession();
        doCleanup();
    }

    private void doOpenSession() {
        if (!session.isOpen()) {
            session.open(new NullProgressMonitor());
        }

        Assert.assertTrue("Can't open the session", session.isOpen());
    }

    public void testSelectView() {
        final DiagramDescription description = findDiagramDescription("Acceleo Class Diagram");
        Assert.assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, description);
        doCreateRepresentations(description, 9);
        doSelectView();
        doUnselectViews();
        doCleanup();
    }

    private void doUnselectViews() {
        if (session.getSelectedViews().size() > 0) {
            final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            domain.getCommandStack().execute(new RecordingCommand(domain) {
                @Override
                protected void doExecute() {
                    for (final DView selectedView : session.getSelectedViews()) {
                        session.removeSelectedView(selectedView, new NullProgressMonitor());
                    }
                }
            });
        }
        Assert.assertTrue("We should have no selected view", session.getSelectedViews().size() == 0);
        Assert.assertTrue("We should have no selected viewpoint", session.getSelectedViewpoints(false).size() == 0);
    }

    private void doSelectView() {
        int prevSelectedViews = session.getSelectedViews().size();
        int prevSelectedSiriuss = session.getSelectedViewpoints(false).size();
        if (session.getOwnedViews().size() > 0) {
            final DView selectedView = session.getOwnedViews().iterator().next();
            if (session.getSelectedViews().contains(selectedView)) {
                prevSelectedSiriuss -= 1;
                prevSelectedViews -= 1;
            }
            final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            domain.getCommandStack().execute(new RecordingCommand(domain) {
                @Override
                protected void doExecute() {
                    session.addSelectedView(selectedView, new NullProgressMonitor());
                }
            });
            Assert.assertEquals("We should have one selected view", 1 + prevSelectedViews, session.getSelectedViews().size());
            Assert.assertEquals("We should have one selected viewpoint", 1 + prevSelectedSiriuss, session.getSelectedViewpoints(false).size());
        }
    }

    public void testCreateViews() {
        doOpenSession();
        doCreateViews();
        final int wantedViews = viewpoints.size();
        assertTrue("We should have 0 free views", 0 == computeFreeViews());
        assertEquals("We should have " + wantedViews + " owned views", wantedViews, session.getOwnedViews().size());
        assertEquals("We should have " + wantedViews + " selected views", wantedViews, session.getSelectedViews().size());
        assertEquals("We should have " + wantedViews + " selected viewpoints", wantedViews, session.getSelectedViewpoints(false).size());

        assertTrue("Owned views iteration order must be predicable and have no duplicate.", session.getOwnedViews() instanceof LinkedHashSet);
        assertTrue("Selected views iteration order must be predicable and have no duplicate.", session.getSelectedViews() instanceof LinkedHashSet);
        doCleanup();
    }

    private int computeFreeViews() {
        int freeViews = 0;
        for (final DView dView : session.getOwnedViews()) {
            if (dView.getViewpoint() == null) {
                freeViews++;
            }
        }
        return freeViews;
    }

    private void doCreateViews() {
        for (final Viewpoint vp : viewpoints) {
            activateViewpoint(vp.getName());
        }
        for (final DView dView : session.getOwnedViews()) {
            Assert.assertNotNull("Sirius should not be null", dView.getViewpoint());
            Assert.assertEquals("We should have 10 representations", 10, new DViewQuery(dView).getLoadedRepresentations().size());
        }
    }

    public void testSession() {
        doOpenSession();
        doCreateViews();
        doUnselectViews();
        doSelectView();
        doCleanup();
    }

    public void testUnloadAnalysisResourceManageyBySession() throws Exception {
        doOpenSession();
        doUnloadAnalysisResource();
        doCleanup();
    }

    private void doUnloadAnalysisResource() {
        Assert.assertTrue(session instanceof DAnalysisSession);
        final DAnalysisSession analysisSession = (DAnalysisSession) session;
        final Resource resource = analysisSession.getSessionResource();

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

        Assert.assertTrue(domain.getResourceSet().getResources().contains(resource));
        resource.unload();
    }

    public void testCreateWrongSession() throws Exception {
        boolean compareBeforeAndAfterSize = false;
        int beforeSize = 0;
        int afterSize = 0;
        try {
            beforeSize = SessionManager.INSTANCE.getSessions().size();
            URI sessionResourceURI = session.getSessionResource().getURI();
            URI semanticResourceURI = session.getSemanticResources().iterator().next().getURI();
            session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
            session.addSemanticResource(semanticResourceURI, new NullProgressMonitor());
            afterSize = SessionManager.INSTANCE.getSessions().size();
            compareBeforeAndAfterSize = true;
        } catch (final Exception e) {
            // Wanted behavior : Session initialization is not possible with
            // null analysis.
        } catch (AssertionError ae) {
            // We may also fail with an assertion error if they are enabled.
        }
        if (compareBeforeAndAfterSize) {
            Assert.assertEquals(beforeSize, afterSize);
        }
    }

    public void testCreateSessionFromEcoreResource() throws Exception {
        URI semanticUri = toURI(SEMANTIC_MODEL_PATH);
        try {
            session = SessionFactory.INSTANCE.createSession(semanticUri, new NullProgressMonitor());
            fail();
        } catch (final Exception e) {
            assertEquals(Messages.SessionFactoryImpl_ResourceTypeErrorMsg, e.getMessage());
        }
    }

    public void testAddRemoveSpecialResourceDoesNotUnloadIt() {
        doOpenSession();
        final Resource resource = EcorePackage.eINSTANCE.eResource();
        assertNotNull(resource);

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                session.addSemanticResource(resource.getURI(), new NullProgressMonitor());
                session.removeSemanticResource(resource, new NullProgressMonitor(), true);
            }
        });

        assertNotNull(resource);
        assertTrue("resource was unload", resource.isLoaded());
        doCleanup();
    }

    @Override
    protected void tearDown() throws Exception {
        doCleanup();
        /* Delete the temporary project */
        super.tearDown();
    }

}
