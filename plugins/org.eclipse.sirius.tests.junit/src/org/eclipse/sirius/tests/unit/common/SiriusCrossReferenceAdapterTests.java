/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.internal.EMFTransactionStatusCodes;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.util.LazyCrossReferencer;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.sirius.tests.sample.component.ComponentFactory;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;

/**
 * Class containing tests to update of {@link SiriusCrossReferenceAdapter} after
 * CRUD action on resources through DAnalysisSessionImpl such
 * unload/reload/remove resource, close session.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusCrossReferenceAdapterTests extends SiriusTestCase {

    private TransactionalEditingDomain editingDomain;

    static final String FRAGMENT_FILE_NAME = "fragComponent.component";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setWarningCatchActive(true);

    }

    /**
     * Check that fragmented resource is not reloaded during its unload when it
     * has been externally modified.
     * 
     * @throws Exception
     */
    public void testDisablingCrossReferencerWhileReloadingResource() throws Exception {
        genericSetUp();

        initSemanticResource();

        // check that semantic crossRefAdapter is set on fragmented resource
        Resource fragmentedResource = ((DAnalysisSessionEObject) session).getControlledResources().get(0);
        assertNotNull(FRAGMENT_FILE_NAME + " should be part of session controlled resource", fragmentedResource);
        boolean found = false;
        for (Adapter adapter : fragmentedResource.eAdapters()) {
            if (adapter instanceof LazyCrossReferencer) {
                found = true;
                break;
            }
        }
        assertTrue("The LazyCrossReferencer adapter is not set on fragmented resource", found);

        // simulation an EXTERNAL CHANGE of fragmentResource
        File fragFile = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getFile(FRAGMENT_FILE_NAME).getLocation().toFile();
        fragFile.setLastModified(System.currentTimeMillis());

        ResourceSetSync resourceSetSync = ResourceSetSync.getResourceSetSync(editingDomain).get();
        resourceSetSync.statusChanged(fragmentedResource, ResourceSetSync.ResourceStatus.SYNC, ResourceSetSync.ResourceStatus.EXTERNAL_CHANGED);

        // check that no warning "loading resource while unloading it" has been
        // dispatched
        for (Iterator<IStatus> warning = warnings.values().iterator(); warning.hasNext();) {
            IStatus status = warning.next();
            if (status.getCode() == EMFTransactionStatusCodes.RELOAD_DURING_UNLOAD) {
                fail("Resource is being reloaded during its unload.");
            }
        }
    }

    /**
     * Check that fragmented resource is not reloaded during its unload when it
     * has been externally deleted.
     * 
     * @throws Exception
     */
    public void testDisablingCrossReferencerWhileDeletingResource() throws Exception {

        // create session with empty aird
        genericSetUp();

        // add semantic resources
        initSemanticResource();

        // simulation of DELETION of fragmentResource
        File fragFile = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getFile(FRAGMENT_FILE_NAME).getLocation().toFile();
        fragFile.delete();
        ResourceSetSync resourceSetSync = ResourceSetSync.getResourceSetSync(editingDomain).get();
        resourceSetSync.statusChanged(editingDomain.getResourceSet().getResources().get(2), ResourceSetSync.ResourceStatus.SYNC, ResourceSetSync.ResourceStatus.DELETED);
        // Warning : Avoid using ResourcesPlugin because event are sent too
        // late.
        // ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE,
        // null);

        assertTrue("Deleted controlled resource is still in session controlled resources.", ((DAnalysisSessionEObject) session).getControlledResources().size() == 0);

        // check that no warning "loading resource while unloading it" has been
        // dispatched
        for (Iterator<IStatus> warning = warnings.values().iterator(); warning.hasNext();) {
            IStatus status = warning.next();
            if (status.getCode() == EMFTransactionStatusCodes.RELOAD_DURING_UNLOAD) {
                fail("Resource is being reloaded during its unload.");
            }
        }
    }

    /**
     * Initialize semantic resources
     */
    private void initSemanticResource() {
        final ResourceSet rset = session.getTransactionalEditingDomain().getResourceSet();
        editingDomain = session.getTransactionalEditingDomain();

        // initialize model
        final Component componentRoot = ComponentFactory.eINSTANCE.createComponent();
        componentRoot.setName("compoRoot");
        componentRoot.setPayload(true);

        final Component component1 = ComponentFactory.eINSTANCE.createComponent();
        component1.setName("compo1");
        component1.setPayload(true);

        componentRoot.getChildren().add(component1);

        Component component2 = ComponentFactory.eINSTANCE.createComponent();
        component2.setName("compo2");
        component1.getChildren().add(component2);
        component2.setReference(component1);

        // create resources
        final URI fileMainComponentUri = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + "Maincomponent.component", true);
        final Resource rsMainComponent = rset.createResource(fileMainComponentUri);

        final URI fileFragComponentUri = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + FRAGMENT_FILE_NAME, true);
        final Resource rsFragComponent = rset.createResource(fileFragComponentUri);

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                // add content to resource
                rsMainComponent.getContents().add(componentRoot);
                rsFragComponent.getContents().add(component1);

                // add resources to session
                session.addSemanticResource(fileMainComponentUri, new NullProgressMonitor());

                // save session
                session.save(new NullProgressMonitor());
            }
        });
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
