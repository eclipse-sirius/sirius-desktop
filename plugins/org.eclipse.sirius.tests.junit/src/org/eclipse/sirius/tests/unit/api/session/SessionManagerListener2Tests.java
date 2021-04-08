/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.makeThreadSafe;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.command.MoveRepresentationCommand;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPreferenceConstants;

/**
 * 
 * @author cbr
 */
public class SessionManagerListener2Tests extends SiriusDiagramTestCase implements EcoreModeler {

    private SessionManagerListener mock;

    private Session alternateSession;

    private EObject alternateSemanticModel;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Restore the default preference values of Sirius (not a customer
        // specific one)
        changePlatformUIPreference(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, ZOOM_SEMANTIC_MODEL_FOLDER_PATH, ZOOM_SEMANTIC_MODEL_NAME);
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PACKAGES_SEMANTIC_MODEL_FOLDER_PATH, PACKAGES_SEMANTIC_MODEL_NAME);

        /* Initialize session 2 */
        String alternativeSessionResourcePath = TEMPORARY_PROJECT_NAME + "/" + "alternativeSession.aird";
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + ZOOM_SEMANTIC_MODEL_NAME, MODELER_PATH, alternativeSessionResourcePath);
        alternateSession = session;
        alternateSemanticModel = alternateSession.getSemanticResources().iterator().next().getContents().get(0);
        initViewpoint(DESIGN_VIEWPOINT_NAME, alternateSession, true);
        TestsUtil.emptyEventsFromUIThread();

        String sessionResourcePath = TEMPORARY_PROJECT_NAME + "/" + "session.aird";
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + PACKAGES_SEMANTIC_MODEL_NAME, MODELER_PATH, sessionResourcePath);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
        session.save(new NullProgressMonitor());

        mock = createMock(SessionManagerListener.class);
        // by default in easymock 2.4 a mock wasn't allowed to be called in
        // multiple threads unless it was made thread-safe
        makeThreadSafe(mock, true);

        SessionManager.INSTANCE.addSessionsListener(mock);
    }

    @Override
    protected void tearDown() throws Exception {
        SessionManager.INSTANCE.removeSessionsListener(mock);
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        mock = null;
        alternateSession = null;
        alternateSemanticModel = null;
        super.tearDown();
    }

    public void testNoNotification() throws Exception {
        // start recording
        replay(mock);
        // stop recording
        verify(mock);
    }

    public void testDeleteAndUndo() throws Exception {
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE); /* delete */
        mock.notify(session, SessionListener.DIRTY); /* dirty */
        mock.notify(session, SessionListener.SYNC); /* sync */
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE); /* undo */
        mock.notify(session, SessionListener.DIRTY); /* dirty */
        mock.notify(session, SessionListener.SYNC); /* sync */
        // */
        // start recording
        replay(mock);

        final Resource resource = session.getSessionResource();
        final DAnalysis analysis = (DAnalysis) resource.getContents().get(0);
        final DView view = analysis.getOwnedViews().get(0);
        assertFalse(new DViewQuery(view).getLoadedRepresentations().isEmpty());

        Command removeRepresentationCmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DRepresentation dRepresentation = new DViewQuery(view).getLoadedRepresentations().get(0);
                session.getSessionResource().getContents().remove(dRepresentation);
                view.getOwnedRepresentationDescriptors().remove(new DRepresentationQuery(dRepresentation).getRepresentationDescriptor());
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(removeRepresentationCmd);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        // stop recording
        verify(mock);
    }

    public void testSaveSession() throws Exception {

        assertEquals(SessionStatus.SYNC, session.getStatus());
        /* set the session dirty */
        modifySessionResourceWithoutSaving(session);
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        // start recording
        mock.notify(session, SessionListener.SYNC);
        replay(mock);
        // stop recording

        session.save(new NullProgressMonitor());
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        verify(mock);
    }

    public void testNoMultipleSyncNotifications() throws Exception {
        assertEquals(SessionStatus.SYNC, session.getStatus());
        /* set the session dirty */
        modifySessionResourceWithoutSaving(session);
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        // start recording
        mock.notify(session, SessionListener.SYNC);
        replay(mock);
        // stop recording

        session.save(new NullProgressMonitor());
        session.save(new NullProgressMonitor());
        session.save(new NullProgressMonitor());
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        verify(mock);
    }

    public void testCreateRepresentationEvents() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        assertEquals("Bad number of selected viewpoint in current session.", 1, session.getSelectedViewpoints(false).size());
        Viewpoint selectedSirius = session.getSelectedViewpoints(false).iterator().next();
        // start recording
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.SEMANTIC_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.CLOSING);
        mock.notify(session, SessionListener.CLOSED);
        mock.notifyRemoveSession(session);
        mock.viewpointDeselected(selectedSirius);

        replay(mock);
        // stop recording

        DDiagram diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.save(new NullProgressMonitor());

        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();

        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        removeSemanticResource();

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        session.close(new NullProgressMonitor());

        verify(mock);

    }

    public void testCloseEventsNonDirty() throws Exception {
        assertEquals("Bad number of selected viewpoint in current session.", 1, session.getSelectedViewpoints(false).size());
        Viewpoint selectedSirius = session.getSelectedViewpoints(false).iterator().next();
        // start recording
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.SEMANTIC_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.CLOSING);
        mock.notify(session, SessionListener.CLOSED);
        mock.notifyRemoveSession(session);
        mock.viewpointDeselected(selectedSirius);

        replay(mock);
        // stop recording

        DDiagram diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();

        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        removeSemanticResource();
        TestsUtil.synchronizationWithUIThread();

        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        session.close(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        verify(mock);
    }

    private void modifySessionResourceWithoutSaving(final Session session) {

        SessionManager.INSTANCE.removeSessionsListener(mock);

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        DAnalysis analysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        DAnnotationEntry entry = DescriptionFactory.eINSTANCE.createDAnnotationEntry();
        Command addDAnnotationEntryCmd = AddCommand.create(session.getTransactionalEditingDomain(), analysis, ViewpointPackage.Literals.DANALYSIS__EANNOTATIONS, entry);
        session.getTransactionalEditingDomain().getCommandStack().execute(addDAnnotationEntryCmd);
        TestsUtil.synchronizationWithUIThread();

        SessionManager.INSTANCE.addSessionsListener(mock);
    }

    private void removeSemanticResource() {
        session.getTransactionalEditingDomain().getCommandStack()
                .execute(new RemoveSemanticResourceCommand(session, session.getSemanticResources().iterator().next(), new NullProgressMonitor(), true));
    }

    /**
     * Test that when two representations are moved successively, two
     * notifications <code>SessionListener.REPRESENTATION_CHANGE</code> are
     * received.
     * 
     * @throws Exception
     *             In case of failure
     */
    public void testTwoRepresentationsMovesSuccessively() throws Exception {
        assertEquals("Bad number of selected viewpoint in current session.", 1, session.getSelectedViewpoints(false).size());
        Viewpoint selectedSirius = session.getSelectedViewpoints(false).iterator().next();
        // start recording
        // For create representation
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        // For Session.addAnalysis
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);
        // For the first move (SELECTED_VIEWS_CHANGE_KIND is for
        // DAnalysisSession.addSelectedView
        // ,call in moveRepresentation)
        mock.notify(session, SessionListener.SELECTED_VIEWS_CHANGE_KIND);
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);

        // For the second move
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.REPRESENTATION_CHANGE);
        mock.notify(session, SessionListener.DIRTY);
        mock.notify(session, SessionListener.SYNC);

        // For the close
        mock.notify(session, SessionListener.CLOSING);
        mock.notify(session, SessionListener.CLOSED);
        mock.notifyRemoveSession(session);
        mock.viewpointDeselected(selectedSirius);

        replay(mock);
        // stop recording

        // Get the first analysis
        assertEquals("The session must contains one DAnalysis.", 1, session.getAllSessionResources().size());
        final DDiagram diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        assertTrue("The diagram must be a root object of the aird resource.", diagram.eContainer() == null);

        DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(diagram).getRepresentationDescriptor();
        final DAnalysis firstAnalysis = (DAnalysis) representationDescriptor.eContainer().eContainer();
        // Create a second analysis for the move
        final DAnalysis secondAnalysis = ViewpointFactory.eINSTANCE.createDAnalysis();
        final Resource representationsResource = EclipseTestsSupportHelper.INSTANCE.createResourceInProject(session.getTransactionalEditingDomain().getResourceSet(), TEMPORARY_PROJECT_NAME,
                "test.aird");
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                representationsResource.getContents().add(secondAnalysis);
                ((DAnalysisSession) session).addAnalysis(representationsResource);
            }
        });
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());

        Command moveRepresentationCmd = new MoveRepresentationCommand(session, secondAnalysis, Collections.<DRepresentationDescriptor> singletonList(representationDescriptor));
        session.getTransactionalEditingDomain().getCommandStack().execute(moveRepresentationCmd);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());

        moveRepresentationCmd = new MoveRepresentationCommand(session, firstAnalysis, Collections.<DRepresentationDescriptor> singletonList(representationDescriptor));
        session.getTransactionalEditingDomain().getCommandStack().execute(moveRepresentationCmd);
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.close(new NullProgressMonitor());

        verify(mock);
    }

    public void testOpenMultipleSessions() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        assertNotNull("Alternate Session has not been well initialized", alternateSession);
        assertNotNull("Alternate Domain has not been well initialized", alternateSession.getTransactionalEditingDomain());
        assertNotNull("Alternate semantic model has not been well initialized", alternateSemanticModel);

        // start recording
        mock.notify(session, SessionListener.SYNC);
        mock.notify(session, SessionListener.DIRTY);

        mock.notify(alternateSession, SessionListener.SYNC);
        mock.notify(alternateSession, SessionListener.DIRTY);

        mock.notify(session, SessionListener.SYNC);

        replay(mock);
        // stop recording

        modifySessionResourceWithoutSaving(session);
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        modifySessionResourceWithoutSaving(alternateSession);
        assertEquals(SessionStatus.DIRTY, alternateSession.getStatus());

        // Save session 1
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
        assertEquals(SessionStatus.DIRTY, alternateSession.getStatus());

        // Open representation on session 1
        EPackage rootPackage = (EPackage) semanticModel;
        final DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, rootPackage).toArray()[0];
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        assertEquals(SessionStatus.DIRTY, alternateSession.getStatus());

        // Save session 2
        alternateSession.save(new NullProgressMonitor());
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        assertEquals(SessionStatus.SYNC, alternateSession.getStatus());

        // Open representation on session 2
        EPackage alternateRootPackage = (EPackage) alternateSemanticModel;
        DDiagram alternateDiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, alternateRootPackage, alternateSession).toArray()[0];
        IEditorPart alternateEditor = DialectUIManager.INSTANCE.openEditor(alternateSession, alternateDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(alternateEditor);
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        assertEquals(SessionStatus.DIRTY, alternateSession.getStatus());

        // Save session 1
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
        assertEquals(SessionStatus.DIRTY, alternateSession.getStatus());

        verify(mock);
        DialectUIManager.INSTANCE.closeEditor(alternateEditor, false);
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
