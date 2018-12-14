/*******************************************************************************
 * Copyright (c) 2010-2019 THALES GLOBAL SERVICES and others.
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

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.session.ReloadingPolicy;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.tools.internal.management.UpdateToolRecordingCommand;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;

/**
 * Session Dirty tests for ecore modeler
 * 
 * @author cbrun
 */
public class SessionWorkspaceSyncTests extends SiriusDiagramTestCase implements EcoreModeler {

    private boolean saveMode;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PACKAGES_SEMANTIC_MODEL_FOLDER_PATH + PACKAGES_SEMANTIC_MODEL_NAME,
                TEMPORARY_PROJECT_NAME + "/" + PACKAGES_SEMANTIC_MODEL_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + PACKAGES_SEMANTIC_MODEL_NAME, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
    }

    public void testCreateRepresentationMakesDirty() throws Exception {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    public void testCreateRepresentationMakesSave() throws Exception {

        // Restore the default preference values of Sirius (not a
        // customer specific one)
        changePlatformUIPreference(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);

        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());

    }

    /**
     * # VP-1184 - Delete aird file on dirty session.
     * 
     * Test whether session is correctly closed when delete aird file.
     * 
     * @throws Exception
     */
    public void testDeleteAirdDirtySession() throws Exception {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        /* disable the UI reloadingPolicy callback */
        session.setReloadingPolicy(new ReloadingPolicyImpl(new NoUICallback()));

        /* ReloadingPolicyImp with NoUICallBack to avoid wizard dialog open */
        assertSessionWithNoUICallBack();

        Resource analysisResource = session.getSessionResource();
        deleteWorkspaceResource(analysisResource);
        Thread.sleep(2000);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertFalse("The session should be closed !", session.isOpen());
        TestsUtil.synchronizationWithUIThread();
    }

    private void assertSessionWithNoUICallBack() throws Exception {
        // ReloadingPolicyImp with NoUICallBack to avoid wizard dialog open

        ReloadingPolicy reloadingPolicy = session.getReloadingPolicy();
        assertTrue(reloadingPolicy instanceof ReloadingPolicyImpl);

        Field declaredField = reloadingPolicy.getClass().getDeclaredField("callBack");
        declaredField.setAccessible(true);
        Object object = declaredField.get(reloadingPolicy);
        assertTrue(object == null || object instanceof NoUICallback);
    }

    protected void deleteWorkspaceResource(Resource ecoreRes) throws IOException, CoreException {
        URI eUri = ecoreRes.getURI();
        if (eUri.isPlatformResource()) {
            String platformString = eUri.toPlatformString(true);
            IResource file = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
            file.delete(true, new NullProgressMonitor());
        }
    }

    public void testCreateUndoSaveMakesNotDirty() throws Exception {
        session.save(new NullProgressMonitor());
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();
        session.getTransactionalEditingDomain().getCommandStack().undo();
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    public void testNoSemanticNotification() throws Exception {
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());

        SessionListener testListener = new SessionListener() {

            @Override
            public void notify(int changeKind) {
                if (changeKind == SEMANTIC_CHANGE) {
                    fail("We should not have semantic notification as we only changed the diagrams !");
                }

            }
        };
        session.addListener(testListener);
        getRepresentations(ENTITIES_DESC_NAME);
        session.save(new NullProgressMonitor());

        TestsUtil.synchronizationWithUIThread();

        session.removeListener(testListener);

    }

    public void testMultipleSameNotifications() throws Exception {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        TestsUtil.synchronizationWithUIThread();

        SessionListener testListener = new SessionListener() {
            int lastNotify = -1;

            @Override
            public void notify(int changeKind) {
                if (changeKind == lastNotify) {
                    fail("We should not have several identical notifications, notification kind:" + changeKind);
                }
                lastNotify = changeKind;

            }
        };

        session.save(new NullProgressMonitor());
        session.addListener(testListener);
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();

        session.removeListener(testListener);
    }

    public void testRepresentationsNotifications() throws Exception {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        TestsUtil.synchronizationWithUIThread();

        SessionListener testListener = new SessionListener() {
            @Override
            public void notify(int changeKind) {
                if (changeKind != SessionListener.DIRTY && changeKind != SessionListener.REPRESENTATION_CHANGE) {
                    fail("We should have had only a representation change notification, we had a :" + changeKind);
                }

            }
        };

        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        session.addListener(testListener);
        final DRepresentationDescriptor repDescriptor = (DRepresentationDescriptor) getRepresentationDescriptors(ENTITIES_DESC_NAME).toArray()[0];
        DDiagram diagram = (DDiagram) repDescriptor.getRepresentation();
        applyNodeCreationTool("Class", diagram, diagram);
        TestsUtil.synchronizationWithUIThread();

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.deleteRepresentation(repDescriptor, session);
            }
        });

        TestsUtil.synchronizationWithUIThread();

        session.removeListener(testListener);
    }

    public void testOpenRepresentationInEditor() throws Exception {

        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());

        EPackage rootPackage = (EPackage) semanticModel;
        final DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, rootPackage).toArray()[0];
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        session.save(new NullProgressMonitor());
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(true);
        TestsUtil.synchronizationWithUIThread();
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(SessionStatus.SYNC, session.getStatus());
        assertFalse(editor.isDirty());
        URI semanticResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/ecore.ecore", true);
        Resource ecoreRes = new ResourceSetImpl().createResource(semanticResourceURI);
        ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
        ecoreRes.save(Collections.emptyMap());
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        assertEquals(SessionStatus.DIRTY, session.getStatus());
        assertTrue(editor.isDirty());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOpenMultipleSessions() throws Exception {
        // Save session 1
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        // Open representation on session 1
        final DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        // Clear all the diagram elements (created during the creation of the
        // diagram)
        Command removeDiagramEltsCmd = RemoveCommand.create(session.getTransactionalEditingDomain(), diagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS,
                diagram.getOwnedDiagramElements());
        executeCommand(removeDiagramEltsCmd);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        // Initialize session 2
        final Session original = this.session;
        genericSetUp(ZOOM_SEMANTIC_MODEL_PATH, MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + "new2.aird");
        Session alternateSession = this.session;
        this.session = original;
        TestsUtil.synchronizationWithUIThread();

        EObject alternateSemanticModel = alternateSession.getSemanticResources().iterator().next().getContents().get(0);

        assertNotNull("Alternate Session has not been well initialized", alternateSession);
        assertNotNull("Alternate Domain has not been well initialized", alternateSession.getTransactionalEditingDomain());
        assertNotNull("Alternate semantic model has not been well initialized", alternateSemanticModel);
        initViewpoint(DESIGN_VIEWPOINT_NAME, alternateSession, true);
        TestsUtil.synchronizationWithUIThread();

        // Save session 2
        alternateSession.save(new NullProgressMonitor());
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        assertEquals(SessionStatus.SYNC, alternateSession.getStatus());

        // Open representation on session 2
        final DDiagram alternateDiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, alternateSession).toArray()[0];
        IEditorPart alternateEditor = DialectUIManager.INSTANCE.openEditor(alternateSession, alternateDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(alternateEditor);
        // Clear all the diagram elements (created during the creation of the
        // diagram)
        removeDiagramEltsCmd = RemoveCommand.create(session.getTransactionalEditingDomain(), alternateDiagram, DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS,
                diagram.getOwnedDiagramElements());
        executeCommand(removeDiagramEltsCmd);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        assertEquals(SessionStatus.DIRTY, alternateSession.getStatus());

        // Save session 1
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
        assertEquals(SessionStatus.DIRTY, alternateSession.getStatus());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.emptyEventsFromUIThread();
        DialectUIManager.INSTANCE.closeEditor(alternateEditor, false);
        TestsUtil.emptyEventsFromUIThread();
    }

    public void testReloadingWithOutsideModifications() throws Exception {
        /* create the resource -> we will not get a notification */
        session.save(new NullProgressMonitor());

        // disable the UI reloadingPolicy callback
        session.setReloadingPolicy(new ReloadingPolicyImpl(new NoUICallback()));

        /* reload automatically */
        // ReloadingPolicyImp with NoUICallBack to avoid wizard dialog open
        assertSessionWithNoUICallBack();

        /* save the resource again to clear the resource map */
        final Resource analysisResource = session.getSessionResource();
        analysisResource.setModified(true);

        IWorkspaceRunnable r = new IWorkspaceRunnable() {
            @Override
            public void run(IProgressMonitor monitor) throws CoreException {
                session.save(new NullProgressMonitor());
            }
        };
        ResourcesPlugin.getWorkspace().run(r, ResourcesPlugin.getWorkspace().getRoot(), 0, null);
        TestsUtil.synchronizationWithUIThread();

        DAnalysis analysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        final int numberOfViews = analysis.getOwnedViews().size();

        assertEquals("It should be only one DView in DAnalysis at the begining.", numberOfViews, 1);
        /* Open representation on session */
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        assertNotNull(editor);
        /*
         * As we just opened an editor we expect some UI events waiting in the
         * processing loop, notably Arrange requests.
         */
        TestsUtil.synchronizationWithUIThread();
        /* reload automatically */
        disableUICallBackOnDialectEditor((DialectEditor) editor);

        ResourceSet rset = new ResourceSetImpl();
        final Resource otherAnalysisResourceInstance = rset.getResource(analysisResource.getURI(), true);
        DAnalysis otherAnalysisRef = (DAnalysis) otherAnalysisResourceInstance.getContents().get(0);
        otherAnalysisRef.getOwnedViews().add(ViewpointFactory.eINSTANCE.createDView());
        otherAnalysisResourceInstance.setModified(true);
        otherAnalysisResourceInstance.save(Collections.emptyMap());
        TestsUtil.synchronizationWithUIThread();

        /* at this time session should have reload the resource */
        analysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        final int numberOfViewsAfterReload = analysis.getOwnedViews().size();
        assertEquals("It should be one DView more in DAnalysis after the modification outside the current resource set.", numberOfViews + 1, numberOfViewsAfterReload);

        while (session.getTransactionalEditingDomain().getCommandStack().getUndoCommand() instanceof UpdateToolRecordingCommand) {
            session.getTransactionalEditingDomain().getCommandStack().undo();
        }
        /*
         * reloading an aird should create a not undoable recording command to
         * set new analysis in session object
         */
        assertFalse("Reloading an aird should create a not undoable recording command to set new analysis in session object. So the command stack should not be undoable.",
                session.getTransactionalEditingDomain().getCommandStack().canUndo());

        /* editor should not be closed */
        editor.setFocus();
        assertNotNull(editor.getSite().getPage().getActiveEditor());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
    }

    public void testSessionResourceSetSyncInstallation() throws Exception {
        assertTrue("This test requires the session to be opened.", session.isOpen());
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        assertTrue("The current session should have a ResourceSetSync installed on its editing domain.", ResourceSetSync.getResourceSetSync(domain).isPresent());

        IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        assertNotNull("An editing session should exist.", uiSession);
        assertTrue("An editing session should exist and be opened.", uiSession.isOpen());

        session.close(new NullProgressMonitor());

        assertFalse("The session should closed.", session.isOpen());
        assertFalse("The editing session should be closed too", uiSession.isOpen());
        assertFalse("The resource set sync should have been removed during session closing.", ResourceSetSync.getResourceSetSync(domain).isPresent());
    }

}
