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

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.sirius.business.internal.resource.ResourceModifiedFieldUpdater;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * A test class validating {@link ResourceStatus} and {@link ResourceSetSync}
 * behavior.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 * 
 */
public class ResourceSyncTestCase extends TestCase {

    ResourceSet set;

    TransactionalEditingDomain domain;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        new ResourceModifiedFieldUpdater((InternalTransactionalEditingDomain) domain, null);
        set = domain.getResourceSet();
        set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
    }

    protected void emptyEventsFromUIThread() {
        try {
            synchronizationWithUIThread();
        } catch (Exception e) {
            emptyEventsFromUIThread();
        }
    }

    protected void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
        }
    }

    public void testCreateResourceSync() throws Exception {

        Resource httpRes = set.createResource(URI.createURI("http://my.xmi"));

        assertEquals("we should not even know what is the status as we're not listening to this resourceset, that said an http resource should be readonly.", ResourceStatus.UNKNOWN,
                ResourceSetSync.getStatus(httpRes));

        installSynchronizer(domain);

        assertEquals("Now we should be read only.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(httpRes));
    }

    public void testNonDirtyForHTTP() throws Exception {

        installSynchronizer(domain);

        final Resource httpRes = set.createResource(URI.createURI("http://my.xmi"));

        assertEquals("We should be in read only.", ResourceStatus.UNKNOWN, ResourceSetSync.getStatus(httpRes));
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                httpRes.getContents().add(EcoreUtil.copy(EcorePackage.eINSTANCE));
            }
        });

        assertEquals("Now we should not be dirty as it's an http resource", ResourceStatus.CHANGED, ResourceSetSync.getStatus(httpRes));
    }

    public void testLoadingIsNotDirtyingWithGetResource() throws Exception {
        installSynchronizer(domain);

        Resource ecoreRes = set.getResource(URI.createPlatformPluginURI("org.eclipse.sirius.tests.junit/data/unit/acceleo/service.uml", true), true);

        assertTrue("Now we should not be dirty because of a loading.", ResourceSetSync.isReadOnly(ecoreRes));
    }

    protected void installSynchronizer(TransactionalEditingDomain domain) {
        ResourceSetSync.getOrInstallResourceSetSync(domain);

    }

    public void testLoadingIsNotDirtyingWithCreateResource() throws Exception {
        installSynchronizer(domain);

        Resource ecoreRes = set.createResource(URI.createPlatformPluginURI("org.eclipse.sirius.tests.junit/data/unit/acceleo/service.uml", true));

        assertTrue("Nothing is done, we should be read only.", ResourceSetSync.isReadOnly(ecoreRes));

        ecoreRes.load(Collections.EMPTY_MAP);

        assertTrue("Now we should not be dirty because of a loading.", ResourceSetSync.isReadOnly(ecoreRes));
    }

    /**
     * Create a project.
     * 
     * @param projectName
     *            name of the created project
     */
    public static void createProject(final String projectName) {
        final IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        try {
            if (!project.exists())
                project.create(projectDescription, new NullProgressMonitor());
            project.open(new NullProgressMonitor());
        } catch (final CoreException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete the project.
     * 
     * @param projectName
     *            name of the project to delete.
     */
    public static void deleteProject(final String projectName) {
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        try {
            if (project.exists())
                project.delete(true, new NullProgressMonitor());
        } catch (final CoreException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        ResourceSetSync.uninstallResourceSetSync(domain);
        domain.dispose();
        domain.getResourceSet();
    }
}
