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
package org.eclipse.sirius.tests.unit.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.tests.support.api.TestsUtil;

/**
 * A test class validating Workspace resource status.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class WorkspaceResourceSyncTestCase extends ResourceSyncTestCase {

    private static final String PROJECT_NAME = "DesignerTestProject";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deleteProject(PROJECT_NAME);
        createProject(PROJECT_NAME);
        emptyEventsFromUIThread();
    }

    public void testCreateInWorkspaceAndLoad() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));
    }

    public void testCreateInWorkspaceAndLoadAndDeleteAndOverride() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

        deleteWorkspaceResource(ecoreRes);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should be DELETED.", ResourceStatus.DELETED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        assertEquals("As we saved the file again it should be in sync", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

    }

    public void testCreateInWorkspaceAndLoadAndDeleteProject() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));

        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

        deleteProject(PROJECT_NAME);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        synchronizationWithUIThread();
        assertEquals("Now we should be DELETED.", ResourceStatus.DELETED, ResourceSetSync.getStatus(ecoreRes));

    }

    public void testCreateInWorkspaceAndLoadAndDeleteAndRemoveFromResourceSet() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));

        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {

                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {

                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        deleteWorkspaceResource(ecoreRes);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        synchronizationWithUIThread();
        assertEquals("Now we should be conflicting deleted as we had changes to commit before the delete.", ResourceStatus.CONFLICTING_DELETED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.getResourceSet().getResources().remove(ecoreRes);
        assertEquals("As we removed the file from the resourceset, we should not know his state.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));

    }

    public void testCreateInWorkspaceAndLoadAndConflictingDeleteAndRemoveFromResourceSet() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {

                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

        deleteWorkspaceResource(ecoreRes);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        synchronizationWithUIThread();
        assertEquals("Now we should be deleted as the file no more exists.", ResourceStatus.DELETED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.getResourceSet().getResources().remove(ecoreRes);
        assertEquals("As we removed the file from the resourceset, we should not know his state.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));

    }

    public void testCreateInWorkspaceAndChange() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {

                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

        emptyResourceContentFromOutside(ecoreRes.getURI());
        synchronizationWithUIThread();
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("A change happened from the outside, we should not be in conflicting state.", ResourceStatus.EXTERNAL_CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("As we saved the file again it should be in sync", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

    }

    public void testCreateInWorkspaceAndConflictChange() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().clear();
            }
        });
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

        emptyResourceContentFromOutside(ecoreRes.getURI());
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        synchronizationWithUIThread();
        assertEquals("A change happened from the outside, we should be in conflicting state.", ResourceStatus.CONFLICTING_CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("As we saved the file again it should be in sync", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));
    }

    public void testListenEvenWhenNotNotifying() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        ResourceSetSync.getOrInstallResourceSetSync(domain);
        ResourceSetSync.getOrInstallResourceSetSync(domain).setNotificationIsRequired(false);

        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.save(Collections.EMPTY_MAP);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        assertEquals("Now we should not be dirty as we just saved.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

        deleteWorkspaceResource(ecoreRes);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        synchronizationWithUIThread();
        assertEquals("Now we should be deleted as the file no more exists.", ResourceStatus.DELETED, ResourceSetSync.getStatus(ecoreRes));

        ecoreRes.getResourceSet().getResources().remove(ecoreRes);
        assertEquals("As we removed the file from the resourceset, we should not know his state.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
    }

    public void testNotificationDirty() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        ResourceSyncClientForTests listenerTest = new ResourceSyncClientForTests();
        final Resource ecoreRes = createWorkspaceResource();
        ResourceSetSync.getOrInstallResourceSetSync(domain);

        try {
            ResourceSetSync.getOrInstallResourceSetSync(domain).registerClient(listenerTest);
            // dirty
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {
                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                }
            });
            // sync
            ecoreRes.save(Collections.EMPTY_MAP);
            // changed
            emptyResourceContentFromOutside(ecoreRes.getURI());
            // deleted
            deleteWorkspaceResource(ecoreRes);
            synchronizationWithUIThread();
            // saved override
            ecoreRes.save(Collections.EMPTY_MAP);
            // dirty
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {
                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));

                }
            });
            // empty from the outside : change conflict
            emptyResourceContentFromOutside(ecoreRes.getURI());
            // delete from the outside : deleted conflict
            deleteWorkspaceResource(ecoreRes);
            synchronizationWithUIThread();

            checkHistory(listenerTest.getStatusHistory());
            checkHistory(listenerTest.getBatchStatusHistory());
        } finally {
            ResourceSetSync.getOrInstallResourceSetSync(domain).unregisterClient(listenerTest);
        }
    }

    private void checkHistory(List<ResourceStatus> history) {
        Iterator<ResourceStatus> it = history.iterator();
        assertEquals("the first event should be the dirty one", ResourceStatus.CHANGED, it.next());
        assertEquals("then the model got saved", ResourceStatus.SYNC, it.next());
        assertEquals("then we had a change from the outside", ResourceStatus.EXTERNAL_CHANGED, it.next());
        assertEquals("and the file got deleted", ResourceStatus.DELETED, it.next());
        assertEquals("and the file got resaved", ResourceStatus.SYNC, it.next());
        assertEquals("and the file got rechanged from the inside", ResourceStatus.CHANGED, it.next());
        assertEquals("and the file got rechanged from the outside", ResourceStatus.CONFLICTING_CHANGED, it.next());
        assertEquals("and the file got deleted from the outside", ResourceStatus.CONFLICTING_DELETED, it.next());
        assertFalse("We still have events in the history but should not !", it.hasNext());
    }

    public void testNoMultipleNotifications() throws Exception {
        ResourceSyncClientForTests listenerTest = new ResourceSyncClientForTests();
        final Resource ecoreRes = createWorkspaceResource();
        ResourceSetSync.getOrInstallResourceSetSync(domain);

        try {
            ResourceSetSync.getOrInstallResourceSetSync(domain).registerClient(listenerTest);
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {
                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                }
            });
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {

                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                }
            });

            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {

                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                }
            });
            assertTrue("We had more than one event  ! we should only have one DIRTY", listenerTest.getStatusHistory().size() == 1);
            assertEquals("the first event should be the dirty one", ResourceStatus.CHANGED, listenerTest.getStatusHistory().iterator().next());
            assertTrue("We had more than one event  ! we should only have one DIRTY", listenerTest.getBatchStatusHistory().size() == 1);
            assertEquals("the first event should be the dirty one", ResourceStatus.CHANGED, listenerTest.getBatchStatusHistory().iterator().next());

        } finally {
            ResourceSetSync.getOrInstallResourceSetSync(domain).unregisterClient(listenerTest);
        }
    }

    public void testNoNotificationsWhenNotListening() throws Exception {
        ResourceSyncClientForTests listenerTest = new ResourceSyncClientForTests();
        final Resource ecoreRes = createWorkspaceResource();
        ResourceSetSync.getOrInstallResourceSetSync(domain);

        try {
            ResourceSetSync.getOrInstallResourceSetSync(domain).registerClient(listenerTest);
            ResourceSetSync.getOrInstallResourceSetSync(domain).setNotificationIsRequired(false);
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {
                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                    ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                }
            });

            assertTrue("We had more than 0 event and we were not listening !", listenerTest.getStatusHistory().size() == 0);
            assertTrue("We had more than 0 event and we were not listening !", listenerTest.getBatchStatusHistory().size() == 0);
        } finally {
            ResourceSetSync.getOrInstallResourceSetSync(domain).unregisterClient(listenerTest);
        }

    }

    public void testReadonlyResourceInPlugins() throws Exception {
        installSynchronizer(domain);
        Resource ecorePluginres = set.getResource(EcorePackage.eINSTANCE.eResource().getURI(), true);
        assertTrue("As the resource is in the plugin it should be readonly.", ResourceSetSync.isReadOnly(ecorePluginres));
    }

    public void testUndoTransactionDoesntMakeTheModelDirty() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        ecoreRes.save(Collections.EMPTY_MAP);
        assertEquals("We should be ready to do changes", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                throw new RuntimeException("any error occuring during the transaction");
            }
        });
        assertEquals("Now we should  not be dirty as we changed stuffs but canceled and all the changes should be undone.", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));

    }

    public void testModelDirtyIsStillDirtyAfterRollback() throws Exception {
        final Resource ecoreRes = createWorkspaceResource();
        assertEquals("Now we should not be dirty as we did not even created the file.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(ecoreRes));
        ecoreRes.save(Collections.EMPTY_MAP);
        assertEquals("We should be ready to do changes", ResourceStatus.SYNC, ResourceSetSync.getStatus(ecoreRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });
        assertEquals("Now we should  be dirty as we changed stuffs.", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));

        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ecoreRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
                throw new RuntimeException("any error occuring during the transaction");
            }
        });
        assertEquals("Now we should still be dirty even after the rollback", ResourceStatus.CHANGED, ResourceSetSync.getStatus(ecoreRes));
    }

    private void emptyResourceContentFromOutside(URI uri) throws IOException {
        Resource outRes = new ResourceSetImpl().getResource(uri, true);
        outRes.getContents().clear();
        outRes.save(Collections.EMPTY_MAP);
    }

    protected void deleteWorkspaceResource(Resource ecoreRes) throws IOException, CoreException {
        URI eUri = ecoreRes.getURI();
        if (eUri.isPlatformResource()) {
            String platformString = eUri.toPlatformString(true);
            IResource file = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
            file.delete(true, new NullProgressMonitor());
        }
    }

    private Resource createWorkspaceResource() {
        installSynchronizer(domain);

        Resource ecoreRes = set.createResource(URI.createPlatformResourceURI(PROJECT_NAME + "/ecore.ecore", true));
        return ecoreRes;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteProject(PROJECT_NAME);

    }

}

class ResourceSyncClientForTests implements ResourceSyncClient {

    private List<ResourceStatus> statuesHistory = new ArrayList<ResourceStatus>();

    private List<ResourceStatus> batchStatuesHistory = new ArrayList<ResourceStatus>();

    public void statusChanged(Resource resource, ResourceStatus oldStatus, ResourceStatus newStatus) {
        statuesHistory.add(newStatus);
    }

    public List<ResourceStatus> getStatusHistory() {
        return statuesHistory;
    }

    public List<ResourceStatus> getBatchStatusHistory() {
        return batchStatuesHistory;
    }

    public void statusesChanged(Collection<ResourceStatusChange> changes) {
        for (ResourceStatusChange change : changes) {
            batchStatuesHistory.add(change.getNewStatus());
        }
    }
}
