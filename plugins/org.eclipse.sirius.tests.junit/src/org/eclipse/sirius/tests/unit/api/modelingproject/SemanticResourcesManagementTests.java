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
package org.eclipse.sirius.tests.unit.api.modelingproject;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.ViewpointFactory;

/**
 * Tests for the management of semantics resources in modeling project.
 * 
 * @author mchauvin
 */
public class SemanticResourcesManagementTests extends SiriusDiagramTestCase implements EcoreModeler {

    @Override
    protected void setUp() throws Exception {
        super.createModelingProject = true;
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

        TestsUtil.emptyEventsFromUIThread();
        /*
         * the DefaultModelingProjectResourceListener is automatically instantiated by the viewpoint.ui plug-in
         */
    }

    public void testAddASemanticResource() throws Exception {
        assertEquals(1, session.getSemanticResources().size());
        EclipseTestsSupportHelper.INSTANCE.copyFile(ZOOM_SEMANTIC_MODEL_PATH, "/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore");
        TestsUtil.synchronizationWithUIThread();
        Thread.sleep(1000);
        assertEquals(2, session.getSemanticResources().size());
    }

    public void _testCreatedRepresentationsFileNotAddedToSemanticResource() throws Exception {
        assertEquals(1, session.getSemanticResources().size());
        // We disabled the error catch during this test because it throw an
        // error because this project became invalid.
        platformProblemsListener.setErrorCatchActive(false);
        TestsUtil.synchronizationWithUIThread();
        try {
            final Resource resource = EclipseTestsSupportHelper.INSTANCE.createResourceInProject(new ResourceSetImpl(), TEMPORARY_PROJECT_NAME, "anotherAird.aird");
            TestsUtil.synchronizationWithUIThread();
            executeCommand(new RecordingCommand(session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                    resource.getContents().add(ViewpointFactory.eINSTANCE.createDAnalysis());
                }
            });
            resource.save(new HashMap<Object, Object>());
        } finally {
            TestsUtil.synchronizationWithUIThread();
            platformProblemsListener.setErrorCatchActive(true);
        }
        TestsUtil.synchronizationWithUIThread();
        assertEquals(1, session.getSemanticResources().size());
    }

    public void _testRemoveSemanticResource() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(ZOOM_SEMANTIC_MODEL_PATH, "/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore");
        TestsUtil.synchronizationWithUIThread();
        IFile newFile = WorkspaceSynchronizer.getFile((Resource) session.getSemanticResources().toArray()[1]);
        newFile.delete(true, null);
        TestsUtil.synchronizationWithUIThread();
        Thread.sleep(2000);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(1, session.getSemanticResources().size());
    }

    public void _testRenameSemanticResource() throws Exception {
        session.setReloadingPolicy(new ReloadingPolicyImpl(new NoUICallback()));
        EclipseTestsSupportHelper.INSTANCE.copyFile(ZOOM_SEMANTIC_MODEL_PATH, "/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore");
        Thread.sleep(2000);
        TestsUtil.synchronizationWithUIThread();
        IFile newFile = WorkspaceSynchronizer.getFile((Resource) session.getSemanticResources().toArray()[1]);
        newFile.move(newFile.getParent().getFullPath().append("newRenamedFile.ecore"), true, null);
        TestsUtil.synchronizationWithUIThread();
        Thread.sleep(8000);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(2, session.getSemanticResources().size());
        IFile newRenamedFile = WorkspaceSynchronizer.getFile((Resource) session.getSemanticResources().toArray()[1]);
        assertTrue(newRenamedFile.getName().equals("newRenamedFile.ecore"));
    }

    /**
     * This test check that when a semantic file is removed from a modeling project and then added again, this file is
     * correctly managed. That means considered as semantic models, loaded and not empty. See VP-2789 for original
     * issue.
     * 
     * In this test, we call a resolveAll to simulate what is done by the
     * {@link org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonContentProvider} in the real use
     * case.
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testRemoveAndAddSameSemanticResource() throws Exception {
        // Initialize test to have a correct context
        assertEquals(1, session.getSemanticResources().size());
        EclipseTestsSupportHelper.INSTANCE.copyFile(ZOOM_SEMANTIC_MODEL_PATH, "/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore");
        ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("The number of semantic resources after the first copy is not the expected one.", 2, session.getSemanticResources().size());
        // create a diagram on the new semantic root : jump over the first
        // semantic resource (to get the second one corresponding to
        // newFile.ecore)
        Iterator<Resource> semanticResourcesIterator = session.getSemanticResources().iterator();
        semanticResourcesIterator.next();
        Resource newFileResource = semanticResourcesIterator.next();
        assertEquals("The resource is not the good one.", "/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore", newFileResource.getURI().toPlatformString(true));
        createRepresentation("Entities", newFileResource.getContents().get(0));
        // remove newFile.ecore
        EclipseTestsSupportHelper.INSTANCE.deleteFile("/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore");
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        // launch a resolve all on the representations file resource to simulate
        // the original bug (see VP-2789 for more details)
        EcoreUtil.resolveAll(session.getAllSessionResources().iterator().next());
        // Add it again (the newFile.ecore
        EclipseTestsSupportHelper.INSTANCE.copyFile(ZOOM_SEMANTIC_MODEL_PATH, "/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore");
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
        Thread.sleep(2000);
        // check that this resource is OK (loaded and with something in
        // contents)
        assertEquals("The number of semantic resources after the second copy is not the expected one.", 2, session.getSemanticResources().size());
        // Jump over the first semantic resource (to get the second one
        // corresponding to newFile.ecore)
        semanticResourcesIterator = session.getSemanticResources().iterator();
        semanticResourcesIterator.next();
        newFileResource = semanticResourcesIterator.next();
        assertEquals("The resource is not the good one.", "/" + TEMPORARY_PROJECT_NAME + "/newFile.ecore", newFileResource.getURI().toPlatformString(true));
        assertTrue("The resource newFile.ecore should be loaded after the copy in that project.", newFileResource.isLoaded());
        assertFalse("The resource newFile.ecore should not be empty after the copy in that project.", newFileResource.getContents().isEmpty());
    }
}
