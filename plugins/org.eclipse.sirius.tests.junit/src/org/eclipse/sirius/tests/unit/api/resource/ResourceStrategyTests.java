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
package org.eclipse.sirius.tests.unit.api.resource;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.internal.resource.strategy.DefaultResourceStrategyImpl;
import org.eclipse.sirius.business.internal.resource.strategy.ResourceStrategy;
import org.eclipse.sirius.business.internal.resource.strategy.ResourceStrategyRegistry;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;

/**
 * Check ResourceStrategy mechanisms.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ResourceStrategyTests extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/resource/";

    private static final String REPRESENTATIONS_FILE_NAME = "representationsWithSemanticResourceTag.aird";

    private static final String MODEL_FILE_NAME = "SemRes1.ecore";

    private static final String MODEL_FILE_NAME2 = "SemRes2.ecore";

    private static final String MODEL_FILE_NAME_LIB = "LibrarySemRes1.ecore";

    private static final String MODEL_FILE_NAME_LIB2 = "LibrarySemRes2.ecore";

    private static final String OTHER_PROJECT_IN_WS = "SiriusLibrary";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        initSession();
    }

    private void initSession() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME, MODEL_FILE_NAME2);
        copyFiles(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, OTHER_PROJECT_IN_WS + File.separator, MODEL_FILE_NAME_LIB, MODEL_FILE_NAME_LIB2);

        genericSetUp(Collections.<URI> emptyList(), Collections.<URI> emptyList(), true, toURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, ResourceURIType.RESOURCE_PLATFORM_URI));
    }

    public void testResourcestrategyExtensionPoint() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint("org.eclipse.sirius.resourceStrategy");

        // Check that this IResourceStrategy has been used
        assertNotNull("The resourceStrategy Extension point is not found", extensionPoint);

    }

    public class TestResourceStrategy extends DefaultResourceStrategyImpl implements ResourceStrategy {

        public boolean isUsed;

        public String handledResourcePattern;

        public TestResourceStrategy(String handledResourcePattern) {
            this.handledResourcePattern = handledResourcePattern;
        }

        @Override
        public IStatus releaseResourceAtResourceSetDispose(Resource resource, IProgressMonitor monitor) {
            isUsed = true;
            return super.releaseResourceAtResourceSetDispose(resource, monitor);
        }

        @Override
        public boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType) {
            return resourceURI.segment(resourceURI.segmentCount() - 1).matches(handledResourcePattern);
        }

        @Override
        public boolean canHandle(Resource resource, ResourceStrategyType resourceStrategyType) {
            return resource.getURI().segment(resource.getURI().segmentCount() - 1).matches(handledResourcePattern);
        }
    };

    /**
     * Check the usage of the contributed resource strategy or the default
     * resource strategy.
     *
     * @throws Exception
     */
    public void testProvidedResourceStrategy() throws Exception {
        String handledResourcePattern = ".*aird";
        TestResourceStrategy testResourceStrategy = new TestResourceStrategy(handledResourcePattern);
        ResourceStrategyRegistry.getInstance().addResourceStrategy(testResourceStrategy);

        Resource semRes = session.getSemanticResources().iterator().next();
        Resource repRes = session.getAllSessionResources().iterator().next();
        session.close(new NullProgressMonitor());

        // Check that this IResourceStrategy has been used
        assertFalse("The resource is still loaded : " + repRes, repRes.isLoaded());
        assertTrue("The resource is unloaded : " + semRes, semRes.isLoaded());
        assertTrue("The IResourceStrategy is not be used", testResourceStrategy.isUsed);
    }

    /**
     * Check that the default resource strategy is used if the contributed
     * resource can't handle the resource.
     *
     * @throws Exception
     */
    public void testOptimizedResourceStrategyIsNotUsed() throws Exception {
        String handledResourcePattern = "NotHandled";
        TestResourceStrategy testResourceStrategy = new TestResourceStrategy(handledResourcePattern);
        ResourceStrategyRegistry.getInstance().addResourceStrategy(testResourceStrategy);

        session.close(new NullProgressMonitor());

        // Checks
        assertFalse("The TestResourceStrategy has been used as canHandle answers false", testResourceStrategy.isUsed);
    }

    /**
     * Check that the contributed resource strategy is used with controlled
     * resource
     *
     * @throws Exception
     */
    public void testResourceStrategyWithControlledResource() throws Exception {
        String handledResourcePattern = MODEL_FILE_NAME_LIB + "|" + MODEL_FILE_NAME_LIB2;
        TestResourceStrategy testResourceStrategy = new TestResourceStrategy(handledResourcePattern);
        ResourceStrategyRegistry.getInstance().addResourceStrategy(testResourceStrategy);

        EList<Resource> controlledResources = ((DAnalysisSessionImpl) session).getControlledResources();
        session.close(new NullProgressMonitor());

        // Checks
        for (Resource resource : controlledResources) {
            assertFalse("The resource is still loaded : " + resource, resource.isLoaded());
        }
    }

    /**
     * Check that, with DefaultOptimizedResourceStrategyImpl, all session
     * resources are released.
     * 
     * @throws Exception
     */
    public void testResourceReleasingAtSessionClosure() throws Exception {
        List<WeakReference<Resource>> weakReferences = new ArrayList<WeakReference<Resource>>();
        for (Resource resource : session.getTransactionalEditingDomain().getResourceSet().getResources()) {
            weakReferences.add(new WeakReference<Resource>(resource));
        }

        session.close(new NullProgressMonitor());

        // Check that session resources has been released
        gc();
        for (WeakReference<Resource> weakReference : weakReferences) {
            if (weakReference.get() != null) {
                assertNull("The resource " + weakReference.get().getURI() + " is retained in memory after DASI.close", weakReference.get());
            }
        }
    }

    /**
     * This method guarantees that garbage collection is done unlike
     * <code>{@link System#gc()}</code>
     */
    public void gc() {
        Object obj = new Object();
        WeakReference<Object> ref = new WeakReference<Object>(obj);
        obj = null;
        while (ref.get() != null) {
            System.gc();
        }
    }
}
