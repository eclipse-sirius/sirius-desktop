/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.control.SiriusUncontrolCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.control.AbstractControlTest;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.junit.Assert;

import com.google.common.collect.Lists;

public class SiriusControlTest extends AbstractControlTest {

    private static final String PATH = "/data/unit/control/";

    private static final String SEMANTIC_MODEL_FILENAME_1 = "base/chain.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_2 = "controlled/chain.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_3 = "controlled/chain_acceleo.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_4 = "VP-2070/root.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_5 = "VP-2070/part2/root.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_5_1 = "VP-2070/part2/root_p1.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_6 = "VP-2766/My.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_6_1 = "VP-2766/My_p1.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_6_2 = "VP-2766/My_p2.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_7 = "VP-2818/tc.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_8 = "448373/448373.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_8_1 = "448373/448373b.ecore";

    private static final String SESSION_MODEL_FILENAME_1 = "base/chain.aird";

    private static final String SESSION_MODEL_FILENAME_2 = "controlled/chain.aird";

    private static final String SESSION_MODEL_FILENAME_3 = "controlled/chain_acceleo.aird";

    private static final String SESSION_MODEL_FILENAME_4 = "VP-2070/root.aird";

    private static final String SESSION_MODEL_FILENAME_5 = "VP-2070/part2/root.aird";

    private static final String SESSION_MODEL_FILENAME_5_1 = "VP-2070/part2/root_p1.aird";

    private static final String SESSION_MODEL_FILENAME_6 = "VP-2766/My.aird";

    private static final String SESSION_MODEL_FILENAME_6_1 = "VP-2766/My_p1.aird";

    private static final String SESSION_MODEL_FILENAME_6_2 = "VP-2766/My_p2.aird";

    private static final String SESSION_MODEL_FILENAME_7 = "VP-2818/tc.aird";

    private static final String SESSION_MODEL_FILENAME_8 = "448373/representations.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_1);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_2, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_2);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_3, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_3);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_4, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_4);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_5, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_5);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_5_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_5_1);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_6, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_6);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_6_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_6_1);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_6_2, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_6_2);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_7, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_7);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_8, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_8);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_8_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_8_1);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_1);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_2, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_2);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_3, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_3);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_4, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_4);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_5, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_5);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_5_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_5_1);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_6, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_6);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_6_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_6_1);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_6_2, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_6_2);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_7, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_7);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_MODEL_FILENAME_8, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_8);
    }

    public void testControl() throws Exception {
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_1), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_1);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);
        try {
            assertFilesExist("/base/chain.ecore", "/base/chain.aird");
            assertFilesDoNotExist("/base/chain_acceleo.ecore", "/base/chain_acceleo.aird");
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());

            final EObject root = findPackageNamed("acceleo", semanticElt);
            final DRepresentation representation = new DViewQuery(session.getOwnedViews().iterator().next()).getLoadedRepresentations().get(0);
            final URI controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/base/chain_acceleo.ecore", true);
            final URI controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/base/chain_acceleo.aird", true);
            siriusControl(root, controlledModelUri, Collections.singleton(representation), controlledAirdUri);

            final EObject root2 = findPackageNamed("acceleo", semanticElt);
            assertNotNull(root2);
            assertSame(root, root2);
            assertEquals(controlledModelUri, root2.eResource().getURI());

            assertEquals(controlledAirdUri, representation.eResource().getURI());

            // Check that the created aird does not need migration (version tag
            // must be initialized)
            checkRepresentationFileMigrationStatus(controlledAirdUri, false);

            assertFilesExist("/base/chain.ecore", "/base/chain.aird", "/base/chain_acceleo.ecore", "/base/chain_acceleo.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    /**
     * Test validating that it is possible to control/uncontrol the second
     * semantic model of a modeling project.
     * 
     * @throws Exception
     */
    public void testControlUncontrolSecondModel() throws Exception {
        // Addition of 2 models as semantic resources
        ArrayList<String> semanticModelPaths = Lists.<String> newArrayList();
        semanticModelPaths.add(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_8);
        semanticModelPaths.add(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_8_1);
        genericSetUp(semanticModelPaths, Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_8);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        // The control will be done on a element of the second semantic resource
        Iterator<Resource> semanticResourceIterator = session.getSemanticResources().iterator();
        Resource semanticResource = semanticResourceIterator.next();
        Assert.assertEquals("The path of the first semantic resource is unexpected", "/resource/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_8, semanticResource.getURI().path());
        semanticResource = semanticResourceIterator.next();
        Assert.assertEquals("The path of the second semantic resource is unexpected", "/resource/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_8_1, semanticResource.getURI().path());

        EObject semanticElt = semanticResource.getContents().get(0);
        try {
            assertFilesExist("/" + SEMANTIC_MODEL_FILENAME_8, "/" + SEMANTIC_MODEL_FILENAME_8_1, "/" + SESSION_MODEL_FILENAME_8);
            String semantic_model_filename_8_1_controlled = ("/" + SEMANTIC_MODEL_FILENAME_8_1).replace(".ecore", "_controlled.ecore");
            String session_model_filename_8_1_controlled = ("/" + SESSION_MODEL_FILENAME_8).replace(".aird", "_controlled.aird");
            assertFilesDoNotExist(semantic_model_filename_8_1_controlled, session_model_filename_8_1_controlled);
            assertEquals("The resourceSet should be contains only 3 resources typed Aird and Ecore", 3, getResourceTypeAirdOrEcore(rs).size());

            final EObject root = findPackageNamed("p1", semanticElt);
            final DRepresentation representation = new DViewQuery(session.getOwnedViews().iterator().next()).getLoadedRepresentations().get(0);
            final URI controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + semantic_model_filename_8_1_controlled, true);
            final URI controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + session_model_filename_8_1_controlled, true);
            siriusControl(root, controlledModelUri, Collections.singleton(representation), controlledAirdUri);

            final EObject root2 = findPackageNamed("p1", semanticElt);
            assertNotNull(root2);
            assertSame(root, root2);
            assertEquals(controlledModelUri, root2.eResource().getURI());
            assertEquals(controlledAirdUri, representation.eResource().getURI());

            // Check that the created aird does not need migration (version tag
            // must be initialized)
            checkRepresentationFileMigrationStatus(controlledAirdUri, false);

            assertFilesExist("/" + SEMANTIC_MODEL_FILENAME_8, "/" + SEMANTIC_MODEL_FILENAME_8_1, "/" + SESSION_MODEL_FILENAME_8, semantic_model_filename_8_1_controlled,
                    session_model_filename_8_1_controlled);
            assertEquals("The resourceSet should be contains only 5 resources typed Aird and Ecore", 5, getResourceTypeAirdOrEcore(rs).size());

            // Uncontrol the controlled element
            Command vuc = new SiriusUncontrolCommand(root2, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);

            assertFilesExist("/" + SEMANTIC_MODEL_FILENAME_8, "/" + SEMANTIC_MODEL_FILENAME_8_1, "/" + SESSION_MODEL_FILENAME_8);
            assertEquals("The resourceSet should be contains only 3 resources typed Aird and Ecore", 3, getResourceTypeAirdOrEcore(rs).size());
            assertFilesDoNotExist(semantic_model_filename_8_1_controlled, session_model_filename_8_1_controlled);
        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    /**
     * The control command must add a models if an element of the controlled
     * representations point outside the semantic model.
     * 
     * @throws Exception
     */
    public void testControlWithAssertOnModelsRef() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            // Can cause a timeout.
            return;
        }
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_4), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_4);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);

        try {
            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird");
            assertFilesDoNotExist("/VP-2070/root_p1.ecore", "/VP-2070/root_p1.aird");
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());

            final EObject root = findPackageNamed("p1", semanticElt);
            final DRepresentation representation = new DViewQuery(session.getOwnedViews().iterator().next()).getLoadedRepresentations().get(0);
            final URI controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1.ecore", true);
            final URI controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1.aird", true);
            siriusControl(root, controlledModelUri, Collections.singleton(representation), controlledAirdUri);

            final EObject rootP1 = findPackageNamed("p1", semanticElt);
            assertNotNull(rootP1);
            assertSame(root, rootP1);
            assertEquals(controlledModelUri, rootP1.eResource().getURI());

            assertEquals(controlledAirdUri, representation.eResource().getURI());

            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird", "/VP-2070/root_p1.ecore", "/VP-2070/root_p1.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
            final Resource sessionControlledResource = session.getTransactionalEditingDomain().getResourceSet().getResource(controlledAirdUri, true);
            assertTrue("This resource must contains a DAnalysis.", sessionControlledResource.getContents().get(0) instanceof DAnalysis);
            assertEquals("Bad number of Models in root_p1.aird", 2, ((DAnalysis) sessionControlledResource.getContents().get(0)).getModels().size());
        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    /**
     * The control command must add a referencedAnalysis to the root aird but
     * not to the existing fragment.<BR>
     * This test also move a representation in the new fragment. VP-2739
     * 
     * @throws Exception
     */
    public void testControlWithAssertOnReferencedAnalysisWithRepresentation() throws Exception {
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_5), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_5);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);

        try {
            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_5, File.separator + SESSION_MODEL_FILENAME_5);
            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_5_1, File.separator + SESSION_MODEL_FILENAME_5_1);
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
            assertFilesDoNotExist("/VP-2070/part2/root_p2.ecore", "/VP-2070/part2/root_p2.aird");

            final EObject root = findPackageNamed("p2", semanticElt);
            DRepresentation representationP2 = null;
            for (DView dView : session.getOwnedViews()) {
                for (DRepresentationDescriptor representationDescriptor : new DViewQuery(dView).getLoadedRepresentationsDescriptors()) {
                    if ("p2 package entities".equals(representationDescriptor.getName())) {
                        representationP2 = representationDescriptor.getRepresentation();
                    }
                }
            }
            final URI controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/part2/root_p2.ecore", true);
            final URI controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/part2/root_p2.aird", true);
            siriusControl(root, controlledModelUri, Collections.singleton(representationP2), controlledAirdUri);

            final EObject rootP2 = findPackageNamed("p2", semanticElt);
            assertNotNull(rootP2);
            assertSame(root, rootP2);
            assertEquals(controlledModelUri, rootP2.eResource().getURI());

            assertEquals(controlledAirdUri, representationP2.eResource().getURI());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_5, File.separator + SESSION_MODEL_FILENAME_5, File.separator + SEMANTIC_MODEL_FILENAME_5_1,
                    File.separator + SESSION_MODEL_FILENAME_5_1, "/VP-2070/part2/root_p2.ecore", "/VP-2070/part2/root_p2.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());

            // Check the number of models and referencedAnalysis in root
            checkRepresentationsFileContent(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_5, true), semanticElt, 1, 2);

            // Check the number of models and referencedAnalysis in new fragment
            // (p2)
            checkRepresentationsFileContent(controlledAirdUri, rootP2, 1, 0);
        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    /**
     * The control command must add a referencedAnalysis to the root aird but
     * not to the existing fragment.<BR>
     * This test does not move a representation in the new fragment (the option
     * is activated to create the blank aird event if there is no representation
     * to move). VP-2739
     * 
     * @throws Exception
     */
    public void testControlWithAssertOnReferencedAnalysisWithoutRepresentation() throws Exception {
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_5), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_5);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());
        // Enable creation of blank aird
        // Activate programmatically the viewpoint preference : Creates an empty
        // aird fragment on control even if there is no selected representation
        changeSiriusPreference(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);
        try {
            EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);
            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_5, File.separator + SESSION_MODEL_FILENAME_5);
            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_5_1, File.separator + SESSION_MODEL_FILENAME_5_1);
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
            assertFilesDoNotExist("/VP-2070/part2/root_p3.ecore", "/VP-2070/part2/root_p3.aird");

            final EObject root = findPackageNamed("p3", semanticElt);
            final URI controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/part2/root_p3.ecore", true);
            final URI controlledAirdP3Uri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/part2/root_p3.aird", true);
            siriusControl(root, controlledModelUri, Collections.<DRepresentation> emptySet(), controlledAirdP3Uri);

            final EObject rootP3 = findPackageNamed("p3", semanticElt);
            assertNotNull(rootP3);
            assertSame(root, rootP3);
            assertEquals(controlledModelUri, rootP3.eResource().getURI());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_5, File.separator + SESSION_MODEL_FILENAME_5, File.separator + SEMANTIC_MODEL_FILENAME_5_1,
                    File.separator + SESSION_MODEL_FILENAME_5_1, "/VP-2070/part2/root_p3.ecore", "/VP-2070/part2/root_p3.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());
            // Check the number of models and referencedAnalysis in root
            checkRepresentationsFileContent(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_5, true), semanticElt, 1, 2);

            // Check the number of models and referencedAnalysis in original
            // fragment (p1)
            final Resource p1SemanticControlledResource = session.getTransactionalEditingDomain().getResourceSet()
                    .getResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SEMANTIC_MODEL_FILENAME_5_1, true), true);
            checkRepresentationsFileContent(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_5_1, true),
                    p1SemanticControlledResource.getContents().get(0), 2, 0);

            // Check the number of models and referencedAnalysis in new fragment
            // (p3)
            checkRepresentationsFileContent(controlledAirdP3Uri, rootP3, 1, 0);
        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    public void testUncontrol() throws Exception {
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_2), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_2);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);

        try {
            assertFilesExist("/controlled/chain.ecore", "/controlled/chain.aird", "/controlled/chain_acceleo.ecore", "/controlled/chain_acceleo.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());

            final EObject root = findPackageNamed("acceleo", semanticElt);
            Command vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);

            assertFilesExist("/controlled/chain.ecore", "/controlled/chain.aird");
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist("/controlled/chain_acceleo.ecore", "/controlled/chain_acceleo.aird"));
        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    private EObject findPackageNamed(final String name, final EObject root) {
        final TreeIterator<EObject> iter = root.eAllContents();
        while (iter.hasNext()) {
            final EObject current = iter.next();
            if (current instanceof EPackage && ((EPackage) current).getName().equals(name)) {
                return current;
            }
        }
        return null;
    }

    private void assertFilesDoNotExist(final String... wksPaths) throws Exception {
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        workspaceRoot.refreshLocal(IResource.DEPTH_INFINITE, null);
        for (String path : wksPaths) {
            path = TEMPORARY_PROJECT_NAME + path;
            assertFalse("Workspace file " + path + " should not exist.", workspaceRoot.getFile(new Path(path)).exists());
        }
    }

    private void assertFilesExist(final String... wksPaths) throws Exception {
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        workspaceRoot.refreshLocal(IResource.DEPTH_INFINITE, null);
        for (String path : wksPaths) {
            path = TEMPORARY_PROJECT_NAME + path;
            assertTrue("Workspace file " + path + " should exist.", workspaceRoot.getFile(new Path(path)).exists());
        }
    }

    private boolean fileDoesNotExist(final String... wksPaths) {
        long startTime = System.currentTimeMillis();
        long delay = 5000;
        while (System.currentTimeMillis() - startTime < delay) {
            boolean fileDoesNotExists = true;
            for (String path : wksPaths) {
                path = TEMPORARY_PROJECT_NAME + path;
                fileDoesNotExists = fileDoesNotExists && !ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).exists();
            }
            if (fileDoesNotExists) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the result of models and referencedAnalyis references after two
     * controls in order of the hierarchy of the semantic elements.<BR>
     * <B>Details</B> : With this semantic hierarchy : package root contains
     * package p1 and package p1 contains package p11,<BR>
     * with an aird on package root with a diagram on each package that
     * represents all semantic elements.
     * <UL>
     * <LI>control p1 --> result:
     * <UL>
     * <LI>root.aird has root_p1.aird as referenced analysis and only root.ecore
     * as models</LI>
     * <LI>root_p1.aird as no referenced analysis and root_p1.ecore and
     * root.ecore as models</LI>
     * </UL>
     * </LI>
     * <LI>control p11 --> result:
     * <UL>
     * <LI>root.aird has root_p1.aird as referenced analysis and only root.ecore
     * as models</LI>
     * <LI>root_p1.aird has root_p1_p11.aird as referenced analysis and
     * root_p1.ecore and root.ecore as models</LI>
     * <LI>root_p1_p11.aird has no referenced analysis and root_p1_p11.ecore,
     * root.ecore and root_p1.ecore as models</LI>
     * </UL>
     * </LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void test2ControlsSuccessivelyInOrderOfHerarchy() throws Exception {
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_4), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_4);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);

        try {
            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird");
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());
            assertFilesDoNotExist("/VP-2070/root_p1.ecore", "/VP-2070/root_p1.aird");
            assertFilesDoNotExist("/VP-2070/root_p1_p11.ecore", "/VP-2070/root_p1_p11.aird");

            // First control
            final EObject p1 = findPackageNamed("p1", semanticElt);
            final DRepresentation representation = new DViewQuery(session.getOwnedViews().iterator().next()).getLoadedRepresentations().get(0);
            final URI p1ControlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1.ecore", true);
            final URI p1ControlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1.aird", true);
            siriusControl(p1, p1ControlledModelUri, Collections.singleton(representation), p1ControlledAirdUri);

            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird", "/VP-2070/root_p1.ecore", "/VP-2070/root_p1.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
            // Check root.aird contents
            checkRepresentationsFileContent(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_4, true), semanticElt, 1, 1);

            // Check root_p1.aird contents
            Resource p1SemanticControlledResource = session.getTransactionalEditingDomain().getResourceSet().getResource(p1ControlledModelUri, true);
            checkRepresentationsFileContent(p1ControlledAirdUri, p1SemanticControlledResource.getContents().get(0), 2, 0);

            // Second control
            final EObject p11 = findPackageNamed("p11", semanticElt);
            DRepresentation representationP11 = null;
            for (DView dView : session.getOwnedViews()) {
                for (DRepresentationDescriptor representationDescriptor : new DViewQuery(dView).getLoadedRepresentationsDescriptors()) {
                    if ("p11 package entities".equals(representationDescriptor.getName())) {
                        representationP11 = representationDescriptor.getRepresentation();
                    }
                }
            }
            final URI p11ControlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1_p11.ecore", true);
            final URI p11ControlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1_p11.aird", true);
            siriusControl(p11, p11ControlledModelUri, Collections.singleton(representationP11), p11ControlledAirdUri);

            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird", "/VP-2070/root_p1.ecore", "/VP-2070/root_p1.aird", "/VP-2070/root_p1_p11.ecore", "/VP-2070/root_p1_p11.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());

            // Check root.aird contents
            checkRepresentationsFileContent(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_4, true), semanticElt, 1, 1);

            // Check root_p1.aird contents
            checkRepresentationsFileContent(p1ControlledAirdUri, p1SemanticControlledResource.getContents().get(0), 2, 1);

            // Check root_p1_p11.aird contents
            final Resource p11SemanticControlledResource = session.getTransactionalEditingDomain().getResourceSet().getResource(p11ControlledModelUri, true);
            checkRepresentationsFileContent(p11ControlledAirdUri, p11SemanticControlledResource.getContents().get(0), 3, 0);

        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    protected void checkRepresentationsFileContent(URI airdRootUri, EObject expectedSemanticRoot, int expectedNumberOfModelsReferences, int expectedNumberOfReferencedAnalysisReferences) {
        Resource representationsFileResource = session.getTransactionalEditingDomain().getResourceSet().getResource(airdRootUri, true);
        assertTrue("This resource must contains a DAnalysis.", representationsFileResource.getContents().get(0) instanceof DAnalysis);
        assertEquals("Bad number of Models in " + representationsFileResource.getURI().toString(), expectedNumberOfModelsReferences,
                ((DAnalysis) representationsFileResource.getContents().get(0)).getModels().size());
        assertEquals("The first models reference must be the root of the semantic controlled resource.", expectedSemanticRoot,
                ((DAnalysis) representationsFileResource.getContents().get(0)).getModels().get(0));
        assertEquals("Bad number of ReferencedAnalysis in " + representationsFileResource.getURI().toString(), expectedNumberOfReferencedAnalysisReferences,
                ((DAnalysis) representationsFileResource.getContents().get(0)).getReferencedAnalysis().size());
    }

    /**
     * Check the result of models and referencedAnalyis references after two
     * controls not in order of the hierarchy of the semantic elements.<BR>
     * <B>Details</B> : With this semantic hierarchy : package root contains
     * package p1 and package p1 contains package p11,<BR>
     * with an aird on package root with a diagram on each package that
     * represents all semantic elements.
     * <UL>
     * <LI>control p11 --> result:
     * <UL>
     * <LI>root.aird has root_p11.aird as referenced analysis and only
     * root.ecore as models</LI>
     * <LI>root_p11.aird as no referenced analysis and root_p11.ecore and
     * root.ecore as models</LI>
     * </UL>
     * </LI>
     * <LI>control p1 --> result:
     * <UL>
     * <LI>root.aird has root_p1.aird as referenced analysis and only root.ecore
     * as models</LI>
     * <LI>root_p1.aird has root_p11.aird as referenced analysis and
     * root_p1.ecore and root.ecore as models</LI>
     * <LI>root_p11.aird has no referenced analysis and root_p11.ecore,
     * root.ecore and root_p1.ecore as models</LI>
     * </UL>
     * </LI>
     * </UL>
     * 
     * @throws Exception
     *             in case of problem
     */
    public void test2ControlsSuccessivelyNotInOrderOfHerarchy() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            /*
             * Can cause time-outs.
             */
            return;
        }
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_4), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_4);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);

        try {
            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird");
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());
            assertFilesDoNotExist("/VP-2070/root_p1.ecore", "/VP-2070/root_p1.aird");
            assertFilesDoNotExist("/VP-2070/root_p1_p11.ecore", "/VP-2070/root_p1_p11.aird");

            // First control
            final EObject p11 = findPackageNamed("p11", semanticElt);
            DRepresentation representationP11 = null;
            for (DView dView : session.getOwnedViews()) {
                for (DRepresentationDescriptor representationDescriptor : new DViewQuery(dView).getLoadedRepresentationsDescriptors()) {
                    if ("p11 package entities".equals(representationDescriptor.getName())) {
                        representationP11 = representationDescriptor.getRepresentation();
                    }
                }
            }
            final URI p11ControlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p11.ecore", true);
            final URI p11ControlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p11.aird", true);
            siriusControl(p11, p11ControlledModelUri, Collections.singleton(representationP11), p11ControlledAirdUri);

            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird", "/VP-2070/root_p11.ecore", "/VP-2070/root_p11.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
            // Check root.aird contents
            checkRepresentationsFileContent(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_4, true), semanticElt, 1, 1);

            // Check root_p11.aird contents
            Resource p11SemanticControlledResource = session.getTransactionalEditingDomain().getResourceSet().getResource(p11ControlledModelUri, true);
            checkRepresentationsFileContent(p11ControlledAirdUri, p11SemanticControlledResource.getContents().get(0), 2, 0);

            // Second control
            final EObject p1 = findPackageNamed("p1", semanticElt);
            DRepresentation representationP1 = null;
            for (DView dView : session.getOwnedViews()) {
                for (DRepresentationDescriptor representationDescriptor : new DViewQuery(dView).getLoadedRepresentationsDescriptors()) {
                    if ("p1 package entities".equals(representationDescriptor.getName())) {
                        representationP1 = representationDescriptor.getRepresentation();
                    }
                }
            }
            final URI p1ControlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1.ecore", true);
            final URI p1ControlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2070/root_p1.aird", true);
            siriusControl(p1, p1ControlledModelUri, Collections.singleton(representationP1), p1ControlledAirdUri);

            assertFilesExist("/VP-2070/root.ecore", "/VP-2070/root.aird", "/VP-2070/root_p1.ecore", "/VP-2070/root_p1.aird", "/VP-2070/root_p11.ecore", "/VP-2070/root_p11.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());

            // Check root.aird contents
            checkRepresentationsFileContent(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_4, true), semanticElt, 1, 1);

            // Check root_p1.aird contents
            Resource p1SemanticControlledResource = session.getTransactionalEditingDomain().getResourceSet().getResource(p1ControlledModelUri, true);
            checkRepresentationsFileContent(p1ControlledAirdUri, p1SemanticControlledResource.getContents().get(0), 2, 1);

            // Check root_p11.aird contents
            checkRepresentationsFileContent(p11ControlledAirdUri, p11SemanticControlledResource.getContents().get(0), 3, 0);

        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    /**
     * VP-2766<BR>
     * Check that in this use case the correct representations file is deleted
     * during the uncontrol. See VP-2766 to see steps to arrive in this use
     * case.
     * 
     * @throws Exception
     */
    public void testUncontrolWithTwoRepresentationsFileLinkedToTheUncontrolledElement() throws Exception {
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + File.separator + SEMANTIC_MODEL_FILENAME_6), Collections.<String> emptyList(),
                TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_6);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);

        try {
            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_6, File.separator + SEMANTIC_MODEL_FILENAME_6_1, File.separator + SEMANTIC_MODEL_FILENAME_6_2,
                    File.separator + SESSION_MODEL_FILENAME_6, File.separator + SESSION_MODEL_FILENAME_6_1, File.separator + SESSION_MODEL_FILENAME_6_2);
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());

            final EObject root = findPackageNamed("p2", semanticElt);
            Command vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_6, File.separator + SEMANTIC_MODEL_FILENAME_6_1, File.separator + SESSION_MODEL_FILENAME_6,
                    File.separator + SESSION_MODEL_FILENAME_6_1);
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 5, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist(File.separator + SEMANTIC_MODEL_FILENAME_6_2, File.separator + SESSION_MODEL_FILENAME_6_2));
        } finally {
            session.close(new NullProgressMonitor());
        }
    }

    /**
     * VP-2818<BR>
     * Control many elements and unControl in the same order. 1 Time again this
     * operation. Test that the resources are removed from resource set.
     * <ol>
     * <li>Control P1</li>
     * <li>Control P1.1</li>
     * <li>Control P1.2</li>
     * <li>UnControl P1</li>
     * <li>UnControl P1.1</li>
     * <li>UnControl P1.2</li>
     * <li>Control P1</li>
     * <li>Control P1.1</li>
     * <li>Control P1.2</li>
     * <li>UnControl P1</li>
     * <li>UnControl P1.1</li>
     * <li>UNControl P1.2</li>
     * </ol>
     * 
     * @throws Exception
     */
    public void testControlUncontrolControlUncontrol() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + File.separator + SEMANTIC_MODEL_FILENAME_7), Collections.<String> emptyList(),
                TEMPORARY_PROJECT_NAME + File.separator + SESSION_MODEL_FILENAME_7);
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        // Disabling ui callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        EObject semanticElt = session.getSemanticResources().iterator().next().getContents().get(0);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);
        try {
            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + SESSION_MODEL_FILENAME_7);
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());

            // Control P1
            EObject root = findPackageNamed("P1", semanticElt);
            URI controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1.ecore", true);
            URI controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1.aird", true);

            siriusControl(root, controlledModelUri, new HashSet<DRepresentation>(), controlledAirdUri);

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.ecore", File.separator + SESSION_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());

            // Control P1.1
            root = findPackageNamed("P1.1", semanticElt);
            controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.1.ecore", true);
            controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.1.aird", true);

            siriusControl(root, controlledModelUri, new HashSet<DRepresentation>(), controlledAirdUri);

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.ecore", File.separator + "VP-2818/tc_P1_P1.1.ecore", File.separator + SESSION_MODEL_FILENAME_7,
                    File.separator + "VP-2818/tc_P1.aird", File.separator + "VP-2818/tc_P1_P1.1.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());

            // Control P1.2
            root = findPackageNamed("P1.2", semanticElt);
            controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.2.ecore", true);
            controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.2.aird", true);

            siriusControl(root, controlledModelUri, new HashSet<DRepresentation>(), controlledAirdUri);

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.ecore", File.separator + "VP-2818/tc_P1_P1.1.ecore",
                    File.separator + "VP-2818/tc_P1_P1.2.ecore", File.separator + SESSION_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.aird", File.separator + "VP-2818/tc_P1_P1.1.aird",
                    File.separator + "VP-2818/tc_P1_P1.2.aird");
            assertEquals("The resourceSet should be contains only 8 resources typed Aird and Ecore", 8, getResourceTypeAirdOrEcore(rs).size());

            // UnControl P1
            root = findPackageNamed("P1", semanticElt);
            SiriusUncontrolCommand vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1_P1.1.ecore", File.separator + "VP-2818/tc_P1_P1.2.ecore",
                    File.separator + SESSION_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1_P1.1.aird", File.separator + "VP-2818/tc_P1_P1.2.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist(File.separator + "VP-2818/tc_P1.ecore", File.separator + "VP-2818/tc_P1.aird"));

            // UnControl P1.1
            root = findPackageNamed("P1.1", semanticElt);
            vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1_P1.2.ecore", File.separator + SESSION_MODEL_FILENAME_7,
                    File.separator + "VP-2818/tc_P1_P1.2.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist(File.separator + "VP-2818/tc_P1_P1.1.ecore", File.separator + "VP-2818/tc_P1_P1.1.aird"));

            // UnControl P1.2
            root = findPackageNamed("P1.2", semanticElt);
            vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + SESSION_MODEL_FILENAME_7);
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist(File.separator + "VP-2818/tc_P1_P1.2.ecore", File.separator + "VP-2818/tc_P1_P1.2.aird"));

            // Control P1
            root = findPackageNamed("P1", semanticElt);
            controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1.ecore", true);
            controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1.aird", true);

            siriusControl(root, controlledModelUri, new HashSet<DRepresentation>(), controlledAirdUri);

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.ecore", File.separator + SESSION_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());

            // Control P1.1
            root = findPackageNamed("P1.1", semanticElt);
            controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.1.ecore", true);
            controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.1.aird", true);

            siriusControl(root, controlledModelUri, new HashSet<DRepresentation>(), controlledAirdUri);

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.ecore", File.separator + "VP-2818/tc_P1_P1.1.ecore", File.separator + SESSION_MODEL_FILENAME_7,
                    File.separator + "VP-2818/tc_P1.aird", File.separator + "VP-2818/tc_P1_P1.1.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());

            // Control P1.2
            root = findPackageNamed("P1.2", semanticElt);
            controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.2.ecore", true);
            controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/VP-2818/tc_P1_P1.2.aird", true);

            siriusControl(root, controlledModelUri, new HashSet<DRepresentation>(), controlledAirdUri);

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.ecore", File.separator + "VP-2818/tc_P1_P1.1.ecore",
                    File.separator + "VP-2818/tc_P1_P1.2.ecore", File.separator + SESSION_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1.aird", File.separator + "VP-2818/tc_P1_P1.1.aird",
                    File.separator + "VP-2818/tc_P1_P1.2.aird");
            assertEquals("The resourceSet should be contains only 8 resources typed Aird and Ecore", 8, getResourceTypeAirdOrEcore(rs).size());

            // UnControl P1
            root = findPackageNamed("P1", semanticElt);
            vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1_P1.1.ecore", File.separator + "VP-2818/tc_P1_P1.2.ecore",
                    File.separator + SESSION_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1_P1.1.aird", File.separator + "VP-2818/tc_P1_P1.2.aird");
            assertEquals("The resourceSet should be contains only 6 resources typed Aird and Ecore", 6, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist(File.separator + "VP-2818/tc_P1.ecore", File.separator + "VP-2818/tc_P1.aird"));

            // UnControl P1.1
            root = findPackageNamed("P1.1", semanticElt);
            vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + "VP-2818/tc_P1_P1.2.ecore", File.separator + SESSION_MODEL_FILENAME_7,
                    File.separator + "VP-2818/tc_P1_P1.2.aird");
            assertEquals("The resourceSet should be contains only 4 resources typed Aird and Ecore", 4, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist(File.separator + "VP-2818/tc_P1_P1.1.ecore", File.separator + "VP-2818/tc_P1_P1.1.aird"));

            // UnControl P1.2
            root = findPackageNamed("P1.2", semanticElt);
            vuc = new SiriusUncontrolCommand(root, true, true, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(vuc);
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

            assertFilesExist(File.separator + SEMANTIC_MODEL_FILENAME_7, File.separator + SESSION_MODEL_FILENAME_7);
            assertEquals("The resourceSet should be contains only 2 resources typed Aird and Ecore", 2, getResourceTypeAirdOrEcore(rs).size());
            assertTrue(fileDoesNotExist(File.separator + "VP-2818/tc_P1_P1.2.ecore", File.separator + "VP-2818/tc_P1_P1.2.aird"));

        } finally {
            session.close(new NullProgressMonitor());
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        }
    }
}
