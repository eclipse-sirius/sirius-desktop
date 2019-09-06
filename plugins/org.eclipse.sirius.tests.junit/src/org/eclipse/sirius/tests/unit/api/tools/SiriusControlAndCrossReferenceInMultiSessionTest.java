/*******************************************************************************
 * Copyright (c) 2015, 2019 THALES GLOBAL SERVICES.
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.control.SiriusUncontrolCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.DeleteRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.business.internal.session.danalysis.IResourceCollector;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Test semantics resources and crossReferenceAdapter on semantic resources when (un)controlling a semantic resource.
 * Test impact in the session itself and in an other session consuming the modified resources
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

    private static final String SEMANTIC_MODEL_LIB_C1 = "lib_C1.ecore";

    private static final String SEMANTIC_MODEL_LIB_PC2 = "lib_PC2.ecore";

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
        // |_RC1
        // |_RC2
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
        // Store locally the factory because the field is cleaned by the super.tearDown()
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
            assertEquals(2, analysis.getModels().size());
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
            assertEquals(1, analysis.getModels().size());
        }

        // 2 - Check LocalResourceCollector
        Resource resourcelib = semanticResources.iterator().next();
        Method method = getDAnalysisSessionImplCollectAllReferencedResourcesMethod();
        Collection<Resource> allReferencedResources = (Collection<Resource>) method.invoke(sessionLibrary, resourcelib);
        IResourceCollector lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());
        assertEquals(0, allReferencedResources.size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());

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
            assertEquals(3, analysis.getModels().size());
            assertTrue(analysis.getModels().get(2).eResource().toString().contains(SEMANTIC_MODEL_LIB_P1));
        }

        // 2 - Check LocalResourceCollector
        Iterator<Resource> resItrInConsumer = semanticResourcesInConsumer.iterator();
        Resource resourceConsumer = resItrInConsumer.next();
        Resource resourceLib_P1InConsumer = controlledResourcesInConsumer.get(0);

        Collection<Resource> resReferencedByConsumer = (Collection<Resource>) method.invoke(session, resourceConsumer);
        IResourceCollector lcrInConsumer = getResourceLocator(resourceConsumer);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcrInConsumer, session.getSemanticCrossReferencer());

        assertEquals(2, resReferencedByConsumer.size());

        // TODO : See Bug 461602, we should have:
        // assertEquals(2, resReferencedByConsumer.size());
        // assertTrue(resReferencedByConsumer.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_LIB_P1));
        // We should not have SEMANTIC_MODEL_LIB in the referenced resources it should have been replaced by
        // SEMANTIC_MODEL_LIB_P1.
        Collection<Resource> resReferencingLib = lcrInConsumer.getAllReferencingResources(resourceLib_P1InConsumer);
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 1, resReferencingLib.size());
        assertTrue(resReferencingLib.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_CONSUMER));
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcrInConsumer.getAllReferencedResources(resourceLib_P1InConsumer).size());

        // ######################################
        // B - Uncontrol the package
        SiriusUncontrolCommand unControlCmd = new SiriusUncontrolCommand(packageToControl, true, true, new NullProgressMonitor());
        TransactionUtil.getEditingDomain(mainPackage).getCommandStack().execute(unControlCmd);

        // --------- Library session checks --------
        // 1 - Check that uncontrol occurs on LibraryProject
        URI libModelUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB, true);
        assertEquals(libModelUri, packageToControl.eResource().getURI());
        assertEquals(packageToControl, mainPackage.getESubpackages().get(0));

        controlledResources = ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources();
        assertEquals("Bad controlled resources size", 0, controlledResources.size());
        semanticResources = sessionLibrary.getSemanticResources();
        assertEquals("Bad semantic resources size", 1, semanticResources.size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            // We should have 2 models here : see opening testSessionOpeningAfterControl
            assertEquals(1, analysis.getModels().size());
        }

        // 2 - Check LocalResourceCollector
        resourcelib = semanticResources.iterator().next();
        lcr = getResourceLocator(resourcelib);
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

        lcrInConsumer = getResourceLocator(resourceConsumer);
        resReferencedByConsumer = lcrInConsumer.getAllReferencedResources(resourceConsumer);
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_CONSUMER, 1, resReferencedByConsumer.size());
        assertTrue(resReferencedByConsumer.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_LIB));
        resReferencingLib = lcrInConsumer.getAllReferencingResources(resourceLibInConsumer);
        assertEquals(1, resReferencingLib.size());
        assertTrue(resReferencingLib.iterator().next().getURI().toString().contains(SEMANTIC_MODEL_CONSUMER));

        assertFalse("The LocalResourceCollector should not be set on resourceSet", Iterators.filter(resourceConsumer.getResourceSet().eAdapters().iterator(), IResourceCollector.class).hasNext());

        LinkedHashSet<Resource> collectedResources = new LinkedHashSet<>();
        collectedResources.addAll(((DAnalysisSessionImpl) session).getAllSessionResources());
        collectedResources.addAll(((DAnalysisSessionImpl) session).getSemanticResources());
        collectedResources.addAll(((DAnalysisSessionImpl) session).getControlledResources());
        for (Resource res : collectedResources) {
            assertTrue("The LocalResourceCollector should be installed on all managed resources", Iterators.filter(res.eAdapters().iterator(), IResourceCollector.class).hasNext());
        }
    }

    /**
     * @throws Exception
     */
    public void testSessionOpeningAfterModificationAndControls() throws Exception {
        session.close(new NullProgressMonitor());

        // Session library
        // P0
        // |_RC1
        // |_RC2
        // |_P1
        // ..|_PC1
        // ..|_PC2

        // ###################################### Control P1 ######################################

        EPackage mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        final EPackage packageToControl = mainPackage.getESubpackages().get(0);
        URI controlledModelUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_P1, true);
        SiriusControlCommand controlCmd = new SiriusControlCommand(packageToControl, controlledModelUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        TransactionUtil.getEditingDomain(mainPackage).getCommandStack().execute(controlCmd);

        assertEquals(controlledModelUri, packageToControl.eResource().getURI());
        assertEquals(packageToControl, mainPackage.getESubpackages().get(0));

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 1, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(1, analysis.getModels().size());
        }

        // ###################################### Check IResourceCollector state ######################################

        Resource resourcelib = sessionLibrary.getSemanticResources().iterator().next();
        Method collectAllReferencedResourcesMethod = getDAnalysisSessionImplCollectAllReferencedResourcesMethod();
        Collection<Resource> allReferencedResources = (Collection<Resource>) collectAllReferencedResourcesMethod.invoke(sessionLibrary, resourcelib);
        IResourceCollector lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());
        assertEquals(0, allReferencedResources.size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());

        // ###################################### Reopen the session ######################################

        sessionLibrary.save(new NullProgressMonitor());
        sessionLibrary.close(new NullProgressMonitor());
        assertTrue(SessionManager.INSTANCE.getSessions().isEmpty());
        String sessionLibraryId = sessionLibrary.getID();
        sessionLibrary = SessionManager.INSTANCE.getSession(URI.createURI(sessionLibraryId), new NullProgressMonitor());
        assertFalse(sessionLibrary.isOpen());
        sessionLibrary.open(new NullProgressMonitor());
        assertTrue(sessionLibrary.isOpen());

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 1, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(2, analysis.getModels().size());
        }

        mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        EPackage controlledPackage = mainPackage.getESubpackages().get(0);
        resourcelib = mainPackage.eResource();
        Resource packageFragmentlib = controlledPackage.eResource();

        // ###################################### Check IResourceCollector state ######################################

        lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 0, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 0, lcr.getAllReferencingResources(packageFragmentlib).size());

        // ###################################### Control RC1 ######################################
        // ###################################### Add RC1 to supertypes of PC1 ######################################
        EClass classToModify = (EClass) controlledPackage.getEClassifiers().get(0);
        final EClass classToControl = (EClass) mainPackage.getEClassifiers().get(0);
        URI controlledClassUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_C1, true);
        SiriusControlCommand controlClassCmd = new SiriusControlCommand(classToControl, controlledClassUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), classToModify, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), classToControl));

        assertEquals(controlledClassUri, classToControl.eResource().getURI());
        assertEquals(classToControl, mainPackage.getEClassifiers().get(0));
        assertTrue(classToModify.getESuperTypes().contains(classToControl));

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 2, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(2, analysis.getModels().size());
        }

        // ###################################### Check IResourceCollector state ######################################

        // TODO : See Bug 461602, result is broken if the control command is executed after the add super types command:
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 1, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 0, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 0, lcr.getAllReferencedResources(classToControl.eResource()).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 1, lcr.getAllReferencingResources(classToControl.eResource()).size());

        // ###################################### Reopen the session ######################################

        sessionLibrary.save(new NullProgressMonitor());
        sessionLibrary.close(new NullProgressMonitor());
        assertTrue(SessionManager.INSTANCE.getSessions().isEmpty());
        sessionLibrary = SessionManager.INSTANCE.getSession(URI.createURI(sessionLibraryId), new NullProgressMonitor());
        assertFalse(sessionLibrary.isOpen());
        sessionLibrary.open(new NullProgressMonitor());
        assertTrue(sessionLibrary.isOpen());

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 2, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(3, analysis.getModels().size());
        }

        mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        controlledPackage = mainPackage.getESubpackages().get(0);
        EClass controlledClass = (EClass) mainPackage.getEClassifiers().get(0);
        resourcelib = mainPackage.eResource();
        packageFragmentlib = controlledPackage.eResource();
        Resource classFragmentlib = controlledClass.eResource();

        // ###################################### Check IResourceCollector state ######################################

        lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 1, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 0, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 0, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 1, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals(0, ((Collection<Resource>) collectAllReferencedResourcesMethod.invoke(sessionLibrary, resourcelib)).size());
        assertEquals(1, ((Collection<Resource>) collectAllReferencedResourcesMethod.invoke(sessionLibrary, packageFragmentlib)).size());
        assertEquals(0, ((Collection<Resource>) collectAllReferencedResourcesMethod.invoke(sessionLibrary, classFragmentlib)).size());

        // ###################################### Add PC2 to supertypes of RC1 ######################################

        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(
                AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), controlledClass, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), controlledPackage.getEClassifiers().get(1)));
        assertEquals(1, controlledClass.getESuperTypes().size());

        // ###################################### Check IResourceCollector state ######################################

        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 2, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 2, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 2, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 2, lcr.getAllReferencingResources(classFragmentlib).size());
        assertTrue("The resource collector returns all the resources referenced directly or indirectly, including itself.",
                lcr.getAllReferencedResources(packageFragmentlib).contains(packageFragmentlib));
        assertTrue("The resource collector returns all the resources referenced directly or indirectly.", lcr.getAllReferencedResources(packageFragmentlib).contains(classFragmentlib));

        // ###################################### Reopen the session ######################################

        sessionLibrary.save(new NullProgressMonitor());
        sessionLibrary.close(new NullProgressMonitor());
        assertTrue(SessionManager.INSTANCE.getSessions().isEmpty());
        sessionLibrary = SessionManager.INSTANCE.getSession(URI.createURI(sessionLibraryId), new NullProgressMonitor());
        assertFalse(sessionLibrary.isOpen());
        sessionLibrary.open(new NullProgressMonitor());
        assertTrue(sessionLibrary.isOpen());

        mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        controlledPackage = mainPackage.getESubpackages().get(0);
        controlledClass = (EClass) mainPackage.getEClassifiers().get(0);
        resourcelib = mainPackage.eResource();
        packageFragmentlib = controlledPackage.eResource();
        classFragmentlib = controlledClass.eResource();

        // ###################################### Check IResourceCollector state ######################################

        lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 2, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 2, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 2, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 2, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals(0, ((Collection<Resource>) collectAllReferencedResourcesMethod.invoke(sessionLibrary, resourcelib)).size());
        assertEquals(2, ((Collection<Resource>) collectAllReferencedResourcesMethod.invoke(sessionLibrary, packageFragmentlib)).size());
        assertEquals(2, ((Collection<Resource>) collectAllReferencedResourcesMethod.invoke(sessionLibrary, classFragmentlib)).size());

        // ###################################### Control PC2 ######################################
        // ###################################### Add PC2 to supertypes of RC1 ######################################
        // ###################################### Introduce a loop between resources ################################
        // ###################################### Add RC1 to supertypes of PC2 ######################################

        URI controlledClass2Uri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_PC2, true);
        EClass classToControl2 = (EClass) controlledPackage.getEClassifiers().get(1);
        classToModify = (EClass) controlledPackage.getEClassifiers().get(0);
        controlClassCmd = new SiriusControlCommand(classToControl2, controlledClass2Uri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), classToModify, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), classToControl2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), classToControl2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), controlledClass));

        assertEquals(controlledClass2Uri, classToControl2.eResource().getURI());
        assertEquals(classToControl2, controlledPackage.getEClassifiers().get(1));
        assertTrue(classToModify.getESuperTypes().contains(classToControl2));
        assertTrue(classToControl2.getESuperTypes().contains(controlledClass));

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // ###################################### Check IResourceCollector state ######################################

        // TODO : See Bug 461602, result is broken
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 3, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 3, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 3, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 3, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_PC2, 3, lcr.getAllReferencedResources(classToControl2.eResource()).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_PC2, 3, lcr.getAllReferencingResources(classToControl2.eResource()).size());

        // ###################################### Reopen the session ######################################

        sessionLibrary.save(new NullProgressMonitor());
        sessionLibrary.close(new NullProgressMonitor());
        assertTrue(SessionManager.INSTANCE.getSessions().isEmpty());
        sessionLibrary = SessionManager.INSTANCE.getSession(URI.createURI(sessionLibraryId), new NullProgressMonitor());
        assertFalse(sessionLibrary.isOpen());
        try {
            sessionLibrary.open(new NullProgressMonitor());
        } catch (StackOverflowError e) {
            ((DAnalysisSessionImpl) sessionLibrary).setOpen(true);
            sessionLibrary.close(new NullProgressMonitor());
            fail("StackOverFlow occured during session opening. Check resource collector.");
        }
        assertTrue(sessionLibrary.isOpen());

        mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        controlledPackage = mainPackage.getESubpackages().get(0);
        controlledClass = (EClass) mainPackage.getEClassifiers().get(0);
        EClass controlledClass2 = (EClass) controlledPackage.getEClassifiers().get(1);
        resourcelib = mainPackage.eResource();
        packageFragmentlib = controlledPackage.eResource();
        classFragmentlib = controlledClass.eResource();
        Resource classFragmentlib2 = controlledClass2.eResource();

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(4, analysis.getModels().size());
        }

        // ###################################### Check IResourceCollector state ######################################

        lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 2, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 0, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 2, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 3, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_PC2, 2, lcr.getAllReferencedResources(classFragmentlib2).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_PC2, 3, lcr.getAllReferencingResources(classFragmentlib2).size());
    }

    /**
     * @throws Exception
     */
    public void testSessionOpeningAfterMultipleModificationsAndControls() throws Exception {
        session.close(new NullProgressMonitor());

        // Session library
        // P0
        // |_RC1
        // |_RC2
        // |_P1
        // ..|_PC1
        // ..|_PC2

        EPackage mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        EClass RC1 = (EClass) mainPackage.getEClassifiers().get(0);
        EClass RC2 = (EClass) mainPackage.getEClassifiers().get(1);
        EPackage P1 = mainPackage.getESubpackages().get(0);
        EClass PC1 = (EClass) P1.getEClassifiers().get(0);
        EClass PC2 = (EClass) P1.getEClassifiers().get(1);

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 0, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());
        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(1, analysis.getModels().size());
        }

        // ###################################### Add links between elements ######################################

        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC2));

        assertTrue(PC1.getESuperTypes().contains(RC1) && PC1.getESuperTypes().contains(PC2));
        assertTrue(PC2.getESuperTypes().contains(RC1) && PC2.getESuperTypes().contains(RC2));
        assertTrue(RC1.getESuperTypes().contains(RC2) && RC1.getESuperTypes().contains(PC2));
        assertTrue(RC2.getESuperTypes().contains(PC1));

        // ###################################### Control Resources ######################################

        // A -Control the package P1
        URI controlledModelUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_P1, true);
        SiriusControlCommand controlCmd = new SiriusControlCommand(P1, controlledModelUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        TransactionUtil.getEditingDomain(mainPackage).getCommandStack().execute(controlCmd);
        // B - Control the package RC1
        URI controlledClassUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_C1, true);
        SiriusControlCommand controlClassCmd = new SiriusControlCommand(RC1, controlledClassUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);
        // C - Control the package PC2
        URI controlledClass2Uri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_PC2, true);
        controlClassCmd = new SiriusControlCommand(PC2, controlledClass2Uri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);

        Resource resourcelib = mainPackage.eResource();
        Resource packageFragmentlib = P1.eResource();
        Resource classFragmentlib = RC1.eResource();
        Resource classFragmentlib2 = PC2.eResource();

        assertEquals(controlledModelUri, packageFragmentlib.getURI());
        assertEquals(controlledClassUri, classFragmentlib.getURI());
        assertEquals(controlledClass2Uri, classFragmentlib2.getURI());

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(1, analysis.getModels().size());
        }

        // ###################################### Check IResourceCollector state ######################################

        IResourceCollector lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());

        // TODO FIXME See Bug 461602, result is broken while the session has not been reopened.
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 0, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 0, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 0, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 0, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 0, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_PC2, 0, lcr.getAllReferencedResources(classFragmentlib2).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_PC2, 0, lcr.getAllReferencingResources(classFragmentlib2).size());

        // ###################################### Reopen the session ######################################

        sessionLibrary.save(new NullProgressMonitor());
        sessionLibrary.close(new NullProgressMonitor());
        assertTrue(SessionManager.INSTANCE.getSessions().isEmpty());
        sessionLibrary = SessionManager.INSTANCE.getSession(URI.createURI(sessionLibrary.getID()), new NullProgressMonitor());
        assertFalse(sessionLibrary.isOpen());
        try {
            sessionLibrary.open(new NullProgressMonitor());
        } catch (StackOverflowError e) {
            ((DAnalysisSessionImpl) sessionLibrary).setOpen(true);
            sessionLibrary.close(new NullProgressMonitor());
            fail("StackOverFlow occured during session opening. Check resource collector.");
        }
        assertTrue(sessionLibrary.isOpen());

        mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        RC1 = (EClass) mainPackage.getEClassifiers().get(0);
        RC2 = (EClass) mainPackage.getEClassifiers().get(1);
        P1 = mainPackage.getESubpackages().get(0);
        PC1 = (EClass) P1.getEClassifiers().get(0);
        PC2 = (EClass) P1.getEClassifiers().get(1);
        resourcelib = mainPackage.eResource();
        packageFragmentlib = P1.eResource();
        classFragmentlib = RC1.eResource();
        classFragmentlib2 = PC2.eResource();

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(4, analysis.getModels().size());
        }

        // ###################################### Check IResourceCollector state ######################################

        lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencedResources(classFragmentlib2).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencingResources(classFragmentlib2).size());
    }

    /**
     * @throws Exception
     */
    public void testSessionOpeningAfterMultipleControlsAndModifications() throws Exception {
        session.close(new NullProgressMonitor());

        // Session library
        // P0
        // |_RC1
        // |_RC2
        // |_P1
        // ..|_PC1
        // ..|_PC2

        EPackage mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        EClass RC1 = (EClass) mainPackage.getEClassifiers().get(0);
        EClass RC2 = (EClass) mainPackage.getEClassifiers().get(1);
        EPackage P1 = mainPackage.getESubpackages().get(0);
        EClass PC1 = (EClass) P1.getEClassifiers().get(0);
        EClass PC2 = (EClass) P1.getEClassifiers().get(1);

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 0, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // ###################################### Control Resources ######################################

        // A -Control the package P1
        URI controlledModelUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_P1, true);
        SiriusControlCommand controlCmd = new SiriusControlCommand(P1, controlledModelUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        TransactionUtil.getEditingDomain(mainPackage).getCommandStack().execute(controlCmd);
        // B - Control the package RC1
        URI controlledClassUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_C1, true);
        SiriusControlCommand controlClassCmd = new SiriusControlCommand(RC1, controlledClassUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);
        // C - Control the package PC2
        URI controlledClass2Uri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_PC2, true);
        controlClassCmd = new SiriusControlCommand(PC2, controlledClass2Uri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);

        Resource resourcelib = mainPackage.eResource();
        Resource packageFragmentlib = P1.eResource();
        Resource classFragmentlib = RC1.eResource();
        Resource classFragmentlib2 = PC2.eResource();

        assertEquals(controlledModelUri, packageFragmentlib.getURI());
        assertEquals(controlledClassUri, classFragmentlib.getURI());
        assertEquals(controlledClass2Uri, classFragmentlib2.getURI());

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(1, analysis.getModels().size());
        }

        // ###################################### Add links between elements ######################################

        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC2));

        assertTrue(PC1.getESuperTypes().contains(RC1) && PC1.getESuperTypes().contains(PC2));
        assertTrue(PC2.getESuperTypes().contains(RC1) && PC2.getESuperTypes().contains(RC2));
        assertTrue(RC1.getESuperTypes().contains(RC2) && RC1.getESuperTypes().contains(PC2));
        assertTrue(RC2.getESuperTypes().contains(PC1));

        // ###################################### Check IResourceCollector state ######################################

        IResourceCollector lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());

        // See Bug 461602, result is ok when control is done before modifications.
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencedResources(classFragmentlib2).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencingResources(classFragmentlib2).size());

        // ###################################### Reopen the session ######################################

        sessionLibrary.save(new NullProgressMonitor());
        sessionLibrary.close(new NullProgressMonitor());
        assertTrue(SessionManager.INSTANCE.getSessions().isEmpty());
        sessionLibrary = SessionManager.INSTANCE.getSession(URI.createURI(sessionLibrary.getID()), new NullProgressMonitor());
        assertFalse(sessionLibrary.isOpen());
        try {
            sessionLibrary.open(new NullProgressMonitor());
        } catch (StackOverflowError e) {
            ((DAnalysisSessionImpl) sessionLibrary).setOpen(true);
            sessionLibrary.close(new NullProgressMonitor());
            fail("StackOverFlow occured during session opening. Check resource collector.");
        }
        assertTrue(sessionLibrary.isOpen());

        mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        RC1 = (EClass) mainPackage.getEClassifiers().get(0);
        RC2 = (EClass) mainPackage.getEClassifiers().get(1);
        P1 = mainPackage.getESubpackages().get(0);
        PC1 = (EClass) P1.getEClassifiers().get(0);
        PC2 = (EClass) P1.getEClassifiers().get(1);
        resourcelib = mainPackage.eResource();
        packageFragmentlib = P1.eResource();
        classFragmentlib = RC1.eResource();
        classFragmentlib2 = PC2.eResource();

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(4, analysis.getModels().size());
        }

        // ###################################### Check IResourceCollector state ######################################

        lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());

        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencedResources(classFragmentlib2).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencingResources(classFragmentlib2).size());
    }

    public void testAddControlledResourceWithInterFragmentsReferencesAsSemanticResource() throws Exception {
        session.close(new NullProgressMonitor());

        // Session library
        // P0
        // |_RC1
        // |_RC2
        // |_P1
        // ..|_PC1
        // ..|_PC2

        EPackage mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        EClass RC1 = (EClass) mainPackage.getEClassifiers().get(0);
        EClass RC2 = (EClass) mainPackage.getEClassifiers().get(1);
        EPackage P1 = mainPackage.getESubpackages().get(0);
        EClass PC1 = (EClass) P1.getEClassifiers().get(0);
        EClass PC2 = (EClass) P1.getEClassifiers().get(1);

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 0, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // ###################################### Control Resources ######################################

        // A -Control the package P1
        URI controlledModelUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_P1, true);
        SiriusControlCommand controlCmd = new SiriusControlCommand(P1, controlledModelUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        TransactionUtil.getEditingDomain(mainPackage).getCommandStack().execute(controlCmd);
        // B - Control the package RC1
        URI controlledClassUri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_C1, true);
        SiriusControlCommand controlClassCmd = new SiriusControlCommand(RC1, controlledClassUri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);
        // C - Control the package PC2
        URI controlledClass2Uri = URI.createPlatformResourceURI("/" + LIBRARY_PROJECT + "/" + SEMANTIC_MODEL_LIB_PC2, true);
        controlClassCmd = new SiriusControlCommand(PC2, controlledClass2Uri, Collections.<DRepresentationDescriptor> emptySet(), null, true, new NullProgressMonitor());
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(controlClassCmd);

        Resource resourcelib = mainPackage.eResource();
        Resource packageFragmentlib = P1.eResource();
        Resource classFragmentlib = RC1.eResource();
        Resource classFragmentlib2 = PC2.eResource();

        assertEquals(controlledModelUri, packageFragmentlib.getURI());
        assertEquals(controlledClassUri, classFragmentlib.getURI());
        assertEquals(controlledClass2Uri, classFragmentlib2.getURI());

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(1, analysis.getModels().size());
        }

        // ###################################### Add links between elements ######################################

        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), RC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC1, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), PC2));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC1));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack()
                .execute(AddCommand.create(sessionLibrary.getTransactionalEditingDomain(), PC2, EcorePackage.eINSTANCE.getEClass_ESuperTypes(), RC2));

        assertTrue(PC1.getESuperTypes().contains(RC1) && PC1.getESuperTypes().contains(PC2));
        assertTrue(PC2.getESuperTypes().contains(RC1) && PC2.getESuperTypes().contains(RC2));
        assertTrue(RC1.getESuperTypes().contains(RC2) && RC1.getESuperTypes().contains(PC2));
        assertTrue(RC2.getESuperTypes().contains(PC1));

        // ###################################### Remove semantic resource ######################################

        sessionLibrary.save(new NullProgressMonitor());
        DeleteRepresentationCommand deleteRepresentationCommand = new DeleteRepresentationCommand(sessionLibrary,
                new HashSet<>(DialectManager.INSTANCE.getAllRepresentationDescriptors(sessionLibrary)));
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(deleteRepresentationCommand);
        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(new RemoveSemanticResourceCommand(sessionLibrary, resourcelib, new NullProgressMonitor(), true));
        sessionLibrary.save(new NullProgressMonitor());

        assertEquals("Bad semantic resources size", 0, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 0, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(0, analysis.getModels().size());
        }

        sessionLibrary.close(new NullProgressMonitor());
        assertTrue(SessionManager.INSTANCE.getSessions().isEmpty());

        sessionLibrary = SessionManager.INSTANCE.getSession(URI.createURI(sessionLibrary.getID()), new NullProgressMonitor());
        assertFalse(sessionLibrary.isOpen());
        sessionLibrary.open(new NullProgressMonitor());
        assertTrue(sessionLibrary.isOpen());

        assertEquals("Bad semantic resources size", 0, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 0, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(0, analysis.getModels().size());
        }

        // ###################################### Add semantic resource ######################################

        sessionLibrary.getTransactionalEditingDomain().getCommandStack().execute(new AddSemanticResourceCommand(sessionLibrary, resourcelib.getURI(), new NullProgressMonitor()));

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        // check models tag
        for (final DAnalysis analysis : ((DAnalysisSessionImpl) sessionLibrary).allAnalyses()) {
            assertEquals(4, analysis.getModels().size());
        }

        assertEquals("Bad semantic resources size", 1, sessionLibrary.getSemanticResources().size());
        assertEquals("Bad controlled resources size", 3, ((DAnalysisSessionEObjectImpl) sessionLibrary).getControlledResources().size());

        mainPackage = (EPackage) sessionLibrary.getSemanticResources().iterator().next().getContents().get(0);
        RC1 = (EClass) mainPackage.getEClassifiers().get(0);
        RC2 = (EClass) mainPackage.getEClassifiers().get(1);
        P1 = mainPackage.getESubpackages().get(0);
        PC1 = (EClass) P1.getEClassifiers().get(0);
        PC2 = (EClass) P1.getEClassifiers().get(1);
        resourcelib = mainPackage.eResource();
        packageFragmentlib = P1.eResource();
        classFragmentlib = RC1.eResource();
        classFragmentlib2 = PC2.eResource();

        // ###################################### Check IResourceCollector state ######################################

        IResourceCollector lcr = getResourceLocator(resourcelib);
        assertEquals("The LocalResourceCollector is expected to be the semantic cross referencer of the session.", lcr, sessionLibrary.getSemanticCrossReferencer());

        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencedResources(resourcelib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB, 4, lcr.getAllReferencingResources(resourcelib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencedResources(packageFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_P1, 4, lcr.getAllReferencingResources(packageFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencedResources(classFragmentlib).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_C1, 4, lcr.getAllReferencingResources(classFragmentlib).size());
        assertEquals("size of resources referenced by " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencedResources(classFragmentlib2).size());
        assertEquals("size of resources referencing " + SEMANTIC_MODEL_LIB_PC2, 4, lcr.getAllReferencingResources(classFragmentlib2).size());
    }

    private Method getDAnalysisSessionImplCollectAllReferencedResourcesMethod() throws NoSuchMethodException {
        Method collectAllReferencedResourcesMethod = DAnalysisSessionImpl.class.getDeclaredMethod("collectAllReferencedResources", Resource.class);
        collectAllReferencedResourcesMethod.setAccessible(true);
        return collectAllReferencedResourcesMethod;
    }

    private IResourceCollector getResourceLocator(Resource resourcelib) {
        UnmodifiableIterator<IResourceCollector> lcrIt = Iterators.filter(resourcelib.eAdapters().iterator(), IResourceCollector.class);
        assertTrue("The LocalResourceCollector is not set on the analyzed resource", lcrIt.hasNext());
        return lcrIt.next();
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
