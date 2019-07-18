/*******************************************************************************
 * Copyright (c) 2015, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.control.SiriusUncontrolCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.business.internal.session.danalysis.IResourceCollector;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Test semantics resources and crossReferenceAdapter on semantic resources when
 * (un)controlling a semantic resource. Test impact in the session itself and in
 * an other session consuming the modified resources
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusControlAndCrossReferenceInMultiSessionTest extends SiriusTestCase {

    private static final String PATH = "/data/unit/control/multisession/";

    private static final String CONSUMER_PROJECT = "SiriusConsumer";

    private static final String SEMANTIC_MODEL_CONSUMER = "consumer.ecore";

    private static final String AIRD_CONSUMER_1 = "consumer.aird";

    private static final String LIBRARY_PROJECT = "SiriusLibrary";

    private static final String SEMANTIC_MODEL_LIB = "lib.ecore";

    private static final String SEMANTIC_MODEL_LIB_P1 = "lib_P1.ecore";

    private static final String AIRD_LIB_1 = "lib.aird";

    UICallBack originalSiriusUICallBack = SiriusEditPlugin.getPlugin().getUiCallback();

    private Session sessionLibrary;

    Object previousEcoreFactory;

    @Override
    protected void setUp() throws Exception {
        previousEcoreFactory = Registry.INSTANCE.getExtensionToFactoryMap().get("ecore");
        Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl() {
            @Override
            public Resource createResource(URI uri) {
                return new XMIResourceImpl(uri) {
                    @Override
                    protected boolean useUUIDs() {
                        return false;
                    }
                };
            }
        });

        // Session library
        // P0
        // |_P1
        // ..|_PC1
        // ..|_PC2

        // Consumer library
        // ConsumerRoot
        // |_C1
        // ..|_toLibrary -->PC1
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, LIBRARY_PROJECT, PATH, Arrays.asList(SEMANTIC_MODEL_LIB, AIRD_LIB_1));
        genericSetUp(Collections.singletonList(toURI(LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB, ResourceURIType.RESOURCE_PLATFORM_URI)), Collections.<URI> emptyList(), true,
                toURI(LIBRARY_PROJECT + "/" + AIRD_LIB_1, ResourceURIType.RESOURCE_PLATFORM_URI));
        sessionLibrary = session;

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, CONSUMER_PROJECT, PATH, Arrays.asList(SEMANTIC_MODEL_CONSUMER, AIRD_CONSUMER_1));
        genericSetUp(Collections.singletonList(toURI(CONSUMER_PROJECT + "/" + SEMANTIC_MODEL_CONSUMER, ResourceURIType.RESOURCE_PLATFORM_URI)), Collections.<URI> emptyList(), true,
                toURI(CONSUMER_PROJECT + "/" + AIRD_CONSUMER_1, ResourceURIType.RESOURCE_PLATFORM_URI));
    }

    @Override
    protected void tearDown() throws Exception {
        // Store locally the factory because the field is cleaned by the
        // super.tearDown()
        Object factory = previousEcoreFactory;
        super.tearDown();
        if (factory == null) {
            Registry.INSTANCE.getExtensionToFactoryMap().remove("ecore");
        } else {
            Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", factory);
        }
    }

    private void copyFilesToTestProject(String pluginID, String projetName, String pluginCommonPath, Collection<String> filePaths) {
        EclipseTestsSupportHelper.INSTANCE.createProject(projetName);
        for (final String path : filePaths) {
            final String pluginFilePath = pluginCommonPath + path;
            final String wksPath = projetName + "/" + path;
            EclipseTestsSupportHelper.INSTANCE.copyFile(pluginID, pluginFilePath, wksPath);
        }
    }

    /**
     * @throws Exception
     */
    public void testControlImpactOnSemanticResource() throws Exception {
        // Check test data
        EPackage mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        final EPackage packageToControl = mainPackage.getESubpackages().get(0);

        EPackage mainPackageInConsumer = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
        EClassifier eTypePC1 = ((EClass) mainPackageInConsumer.getEClassifier("C1")).getEStructuralFeature("toLibrary").getEType();
        assertFalse(eTypePC1.eIsProxy());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) session).allAnalyses()) {
            assertTrue(analysis.getModels().size() == 2);
        }

        // ######################################
        // A - Control the package P1
        URI controlledModelUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_P1, true);
        SiriusControlCommand controlCmd = new SiriusControlCommand(packageToControl, controlledModelUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        TransactionUtil.getEditingDomain(mainPackage).getCommandStack().execute(controlCmd);

        // --------- Library session checks --------
        // 1 - Check that control occurs on LibraryProject
        assertEquals(controlledModelUri, packageToControl.eResource().getURI());

        EList<Resource> controlledResources = ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources();
        assertEquals("Bad controlled resources size", 1, controlledResources.size());
        Collection<Resource> semanticResources = sessionLibrary.getSemanticResources();
        assertEquals("Bad semantic resources size", 1, semanticResources.size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertTrue(analysis.getModels().size() == 1);
        }

        // 2 - Check LocalResourceCollector
        Resource resourcelib = semanticResources.iterator().next();
        Method method = DAnalysisSessionImpl.class.getDeclaredMethod("collectAllReferencedResources", Resource.class);
        method.setAccessible(true);
        Collection<Resource> allReferencedResources = (Collection<Resource>) method.invoke(sessionLibrary, resourcelib);
        UnmodifiableIterator<IResourceCollector> lcrIt = Iterators.filter(resourcelib.getResourceSet().eAdapters().iterator(), IResourceCollector.class);
        assertTrue("The LocalResourceCollector is not set on resourceSet", lcrIt.hasNext());
        IResourceCollector lcr = lcrIt.next();
        assertEquals(0, allReferencedResources.size());

        // --------- Consumer session checks --------
        // 1 - Check that control occurs on ConsumerProject
        EList<Resource> controlledResourcesInConsumer = ((DAnalysisSessionEObjectImpl) session).getControlledResources();
        assertEquals("Bad controlled resources size", 1, controlledResourcesInConsumer.size());
        assertTrue(controlledResourcesInConsumer.get(0).toString().contains(SEMANTIC_MODEL_LIB_P1));
        Collection<Resource> semanticResourcesInConsumer = session.getSemanticResources();
        assertEquals("Bad semantic resources size", 2, semanticResourcesInConsumer.size());

        mainPackageInConsumer = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
        eTypePC1 = ((EClass) mainPackageInConsumer.getEClassifier("C1")).getEStructuralFeature("toLibrary").getEType();
        assertFalse(eTypePC1.eIsProxy());

        Resource eTypePC1Resource = eTypePC1.eResource();
        assertEquals(controlledModelUri, eTypePC1Resource.getURI());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) session).allAnalyses()) {
            assertTrue(analysis.getModels().size() == 3);
            assertTrue(analysis.getModels().get(2).eResource().toString().contains(SEMANTIC_MODEL_LIB_P1));
        }

        // 2 - Check LocalResourceCollector
        Iterator<Resource> resItrInConsumer = semanticResourcesInConsumer.iterator();
        Resource resourceConsumer = resItrInConsumer.next();
        Resource resourceLib_P1InConsumer = controlledResourcesInConsumer.get(0);

        Collection<Resource> resReferencedByConsumer = (Collection<Resource>) method.invoke(session, resourceConsumer);
        UnmodifiableIterator<IResourceCollector> lcrItInconsumer = Iterators.filter(resourceConsumer.getResourceSet().eAdapters().iterator(), IResourceCollector.class);
        assertTrue("The LocalResourceCollector is not set on resourceSet", lcrItInconsumer.hasNext());
        assertEquals(1, resReferencedByConsumer.size());

        IResourceCollector lcrInConsumer = lcrItInconsumer.next();
        assertTrue(resReferencedByConsumer.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_LIB_P1));

        Collection<Resource> resReferencingLib = lcrInConsumer.getAllReferencingResources(resourceLib_P1InConsumer);
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 1, resReferencingLib.size());
        assertTrue(resReferencingLib.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_CONSUMER));

        // ######################################
        // B - Uncontrol the package
        SiriusUncontrolCommand unControlCmd = new SiriusUncontrolCommand(packageToControl, true, true, new NullProgressMonitor());
        TransactionUtil.getEditingDomain(mainPackage).getCommandStack().execute(unControlCmd);

        // --------- Library session checks --------
        // 1 - Check that uncontrol occurs on LibraryProject
        URI libModelUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB, true);
        assertEquals(libModelUri, packageToControl.eResource().getURI());

        controlledResources = ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources();
        assertEquals("Bad controlled resources size", 0, controlledResources.size());
        semanticResources = sessionLibrary.getSemanticResources();
        assertEquals("Bad semantic resources size", 1, semanticResources.size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertTrue(analysis.getModels().size() == 1);
        }

        // 2 - Check LocalResourceCollector
        resourcelib = semanticResources.iterator().next();
        lcrIt = Iterators.filter(resourcelib.getResourceSet().eAdapters().iterator(), IResourceCollector.class);
        lcr = lcrIt.next();
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());

        // --------- Consumer session checks --------
        // 1 - Check that uncontrol occurs on ConsumerProject
        controlledResourcesInConsumer = ((DAnalysisSessionEObjectImpl) session).getControlledResources();
        assertEquals("Bad controlled resources size", 0, controlledResourcesInConsumer.size());
        semanticResourcesInConsumer = session.getSemanticResources();
        assertEquals("Bad semantic resources size", 2, semanticResourcesInConsumer.size());

        mainPackageInConsumer = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
        eTypePC1 = ((EClass) mainPackageInConsumer.getEClassifier("C1")).getEStructuralFeature("toLibrary").getEType();
        // eTypePC1 is proxy because the URI contains the previously controlled
        // resource lib_P1 that does not exists anymore
        assertTrue(eTypePC1.eIsProxy());

        eTypePC1Resource = eTypePC1.eResource();
        assertNull(eTypePC1Resource);

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) session).allAnalyses()) {
            assertEquals("Incorrect models size", 2, analysis.getModels().size());
            assertTrue(analysis.getModels().get(1).eResource().toString().contains(SEMANTIC_MODEL_LIB));
        }

        // 2 - Check LocalResourceCollector
        resItrInConsumer = semanticResourcesInConsumer.iterator();
        resourceConsumer = resItrInConsumer.next();
        Resource resourceLibInConsumer = resItrInConsumer.next();

        lcrItInconsumer = Iterators.filter(resourceConsumer.getResourceSet().eAdapters().iterator(), IResourceCollector.class);
        lcrInConsumer = lcrItInconsumer.next();
        resReferencedByConsumer = lcrInConsumer.getAllReferencedResources(resourceConsumer);
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_CONSUMER, 1, resReferencedByConsumer.size());
        // TODO : should be uncomment in 461602
        // assertTrue(resReferencedByConsumer.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_LIB));
        resReferencingLib = lcrInConsumer.getAllReferencingResources(resourceLibInConsumer);
        // assertEquals(1, resReferencingLib.size());
        // assertTrue(resReferencingLib.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_CONSUMER));
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
