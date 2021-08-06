/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.semantic;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.eclipse.sirius.business.api.resource.support.WorkspaceDragAndDropSupport;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementContainerWithInterpreterOperations;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

/**
 * Tests {@link DAnalysis#getModels()} updates according to scenario of VP-2770 and VP-3022.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DAnalysisModelsUpdateTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String PATH = "/data/unit/semantic/VP-2770/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-2770.ecore";

    private static final String SEMANTIC_RESOURCE_NAME_P3 = "p3.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-2770.aird";

    private static final String PATH_2 = "/data/unit/semantic/VP-3312/case1/";

    private static final String SEMANTIC_RESOURCE_NAME_2 = "My.ecore";

    private static final String SESSION_RESOURCE_NAME_2 = "My.aird";

    private static final String VSM_RESOURCE_NAME_2 = "My.odesign";

    private static final String REFERENCED_VSM_RESOURCE_NAME_2 = "MyReferenced.odesign";

    private static final String PATH_3 = "/data/unit/semantic/VP-3312/case2/";

    private static final String SEMANTIC_RESOURCE_NAME_3 = "My.ecore";

    private static final String SESSION_RESOURCE_NAME_3 = "My.aird";

    private static final String VSM_RESOURCE_NAME_3 = "My.odesign";

    private static final String MM_1_RESOURCE_NAME_3 = "OtherMetaModel.ecore";

    private static final String MM_2_RESOURCE_NAME_3 = "OtherMetaModel2.ecore";

    private EPackage rootEPackage;

    private EPackage p1EPackage;

    private EPackage p2EPackage;

    private EPackage p22EPackage;

    private IWorkspaceRoot workspaceRoot;

    private IProject temporaryProject;

    private URI mainSessionResourceURI;

    private URI p1FragmentedSemanticResourceURI;

    private URI p2FragmentedSemanticResourceURI;

    private URI p22FragmentedSemanticResourceURI;

    private URI p1FragmentedSessionResourceURI;

    private URI p2FragmentedSessionResourceURI;

    private URI p22FragmentedSessionResourceURI;

    private DAnalysis dAnalysisOfMainSessionResource;

    private DAnalysis dAnalysisOfP1SessionFragmentResource;

    private DAnalysis dAnalysisOfP2SessionFragmentResource;

    private DAnalysis dAnalysisOfP22SessionFragmentResource;

    private DRepresentation p1EPackageDRepresentation;

    private DRepresentation p2EPackageDRepresentation;

    private DRepresentation p22EPackageDRepresentation;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME_P3);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        initVariables();
    }

    private void initVariables() {
        Resource semanticResource = session.getSemanticResources().iterator().next();
        rootEPackage = (EPackage) semanticResource.getContents().get(0);

        workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        temporaryProject = workspaceRoot.getProject(TEMPORARY_PROJECT_NAME);

        p1EPackage = rootEPackage.getESubpackages().get(0);
        p2EPackage = rootEPackage.getESubpackages().get(1);
        p22EPackage = p2EPackage.getESubpackages().get(0);

        URI mainSemanticResourceURI = rootEPackage.eResource().getURI();
        p1FragmentedSemanticResourceURI = URI.createURI(mainSemanticResourceURI.trimFileExtension() + "_p1.ecore");
        p2FragmentedSemanticResourceURI = URI.createURI(mainSemanticResourceURI.trimFileExtension() + "_p2.ecore");
        p22FragmentedSemanticResourceURI = URI.createURI(mainSemanticResourceURI.trimFileExtension() + "_p2_p22.ecore");

        EObject sessionResourceRoot = session.getSessionResource().getContents().get(0);
        assertTrue(sessionResourceRoot instanceof DAnalysis);
        dAnalysisOfMainSessionResource = (DAnalysis) sessionResourceRoot;
        p1EPackageDRepresentation = Iterables.filter(DialectManager.INSTANCE.getRepresentations(p1EPackage, session), DDiagram.class).iterator().next();
        p2EPackageDRepresentation = Iterables.filter(DialectManager.INSTANCE.getRepresentations(p2EPackage, session), DDiagram.class).iterator().next();
        p22EPackageDRepresentation = Iterables.filter(DialectManager.INSTANCE.getRepresentations(p22EPackage, session), DDiagram.class).iterator().next();

        mainSessionResourceURI = session.getSessionResource().getURI();
        p1FragmentedSessionResourceURI = URI.createURI(mainSessionResourceURI.trimFileExtension() + "_p1.aird");
        p2FragmentedSessionResourceURI = URI.createURI(mainSessionResourceURI.trimFileExtension() + "_p2.aird");
        p22FragmentedSessionResourceURI = URI.createURI(mainSessionResourceURI.trimFileExtension() + "_p2_p22.aird");

    }

    @Override
    protected void tearDown() throws Exception {
        rootEPackage = null;
        p1EPackage = null;
        p2EPackage = null;
        workspaceRoot = null;
        temporaryProject = null;
        mainSessionResourceURI = null;
        p1FragmentedSemanticResourceURI = null;
        p2FragmentedSemanticResourceURI = null;
        p1FragmentedSessionResourceURI = null;
        p2FragmentedSessionResourceURI = null;
        dAnalysisOfMainSessionResource = null;
        dAnalysisOfP1SessionFragmentResource = null;
        dAnalysisOfP2SessionFragmentResource = null;
        p1EPackageDRepresentation = null;
        p2EPackageDRepresentation = null;
        super.tearDown();
    }

    /**
     * Test scenario of VP-2770 :
     * 
     * <ol>
     * <li>With "Always create an aird fragment on control" pref enabled, from a ecore containing a root EPackage owning
     * 2 child EPackages p1 and p2</li>
     * <li>Control (fragment) p1 and p2 each in its own fragment</li>
     * <li>Drag'n drop EPackage p2 from Model Explorer to "p1 package entities" diagram and save the session</li>
     * </ol>
     * 
     * @throws Exception
     */
    public void testDAnalysisModelsUpdate() throws Exception {
        // Checks before test that we have the expected resources in the
        // workspace
        IResource[] members = temporaryProject.members();
        assertEquals("The current project should contains 4 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + " and " + SESSION_RESOURCE_NAME, 4, members.length);
        assertEquals("The current project should contain .project ", ".project", members[0].getName());
        assertEquals("The current project should contain " + SESSION_RESOURCE_NAME, SESSION_RESOURCE_NAME, members[1].getName());
        assertEquals("The current project should contain " + SEMANTIC_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, members[2].getName());

        assertEquals("The DAnalysis.models reference of the main session resource before fragmentation should only reference root EPackage", Collections.singletonList(rootEPackage),
                dAnalysisOfMainSessionResource.getModels());

        fragment(p1EPackage, p1FragmentedSemanticResourceURI, p1EPackageDRepresentation, p1FragmentedSessionResourceURI);

        checkAfterFirstFragmentation();

        fragment(p2EPackage, p2FragmentedSemanticResourceURI, p2EPackageDRepresentation, p2FragmentedSessionResourceURI);

        checkAfterSecondFragmentation();

        // Test that drag'n drop p2 EPackage in diagram of p1 update the
        // DAnalysis.models reference of the session resource fragment of p1
        doDragAndDrop(p2EPackage, p1EPackage, p1EPackageDRepresentation);

        checkAfterDragAndDrop();
    }

    /**
     * Test scenario of VP-3022 :
     * 
     * <ol>
     * <li>Control (fragment) p22 and p222 each in its own fragment</li>
     * <li>Add inheritance E3 to class E2 (in p222) on semantic model VP-2770.ecore</li>
     * <li>Check model p3.ecore adding to p2.aird</li>
     * </ol>
     * 
     * @throws Exception
     */
    public void testDAnalysisModelsUpdateDependenciesToOtherModel() throws Exception {
        checkThatSemanticModelIsAddedToSession(false);
    }

    /**
     * Test scenario of VP-3022 :
     * 
     * <ol>
     * <li>Control (fragment) p22 and p222 each in its own fragment</li>
     * <li>Close session</li>
     * <li>Add inheritance E3 to class E2 (in p222) on semantic model VP-2770.ecore</li>
     * <li>Open session</li>
     * <li>Check model p3.ecore adding to p2.aird</li>
     * </ol>
     * 
     * @throws Exception
     */
    public void testDAnalysisModelsUpdateDependenciesToOtherModelWithSessionClose() throws Exception {
        checkThatSemanticModelIsAddedToSession(true);
    }

    /**
     * Test that a repair/migrate action on the result of the first test doesn't corrupts the DAnalysis.models
     * references.
     * 
     * @throws Exception
     */
    public void _testDAnalysisModelsPreservationOnRepairMigrate() throws Exception {
        testDAnalysisModelsUpdate();
        closeSession(session);

        final IFile mainSessionFile = getIFile(mainSessionResourceURI.lastSegment());

        PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                SiriusRepairProcess process = new SiriusRepairProcess(mainSessionFile, true);
                process.run(monitor);
                process.dispose();
            }
        });
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        initVariables();
        EObject p1SessionFragmentResourceRoot = session.getTransactionalEditingDomain().getResourceSet().getResource(p1FragmentedSessionResourceURI, true).getContents().get(0);
        assertTrue(p1SessionFragmentResourceRoot instanceof DAnalysis);
        dAnalysisOfP1SessionFragmentResource = (DAnalysis) p1SessionFragmentResourceRoot;

        EObject p2SessionFragmentResourceRoot = session.getTransactionalEditingDomain().getResourceSet().getResource(p2FragmentedSessionResourceURI, true).getContents().get(0);
        assertTrue(p2SessionFragmentResourceRoot instanceof DAnalysis);
        dAnalysisOfP2SessionFragmentResource = (DAnalysis) p2SessionFragmentResourceRoot;

        checkAfterDragAndDrop();
    }

    /**
     * This test check that after an opening of an aird that uses VSM that references another VSM not used by this aird,
     * the analysis has always only one models reference (VP-3312).
     * 
     * @throws Exception
     */
    public void testCaseWithVSMReferencingOtherVSMNotDirectlyUsedByAird() throws Exception {
        // Used a specific use case for this test
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH_2, SEMANTIC_RESOURCE_NAME_2, SESSION_RESOURCE_NAME_2, REFERENCED_VSM_RESOURCE_NAME_2, VSM_RESOURCE_NAME_2);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME_2, TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME_2, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME_2);
        // initSirius(DESIGN_VIEWPOINT_NAME);
        assertEquals("The session should contain only one semantic resource after opening.", 1, session.getSemanticResources().size());
    }

    /**
     * This test check that after an opening of an aird that uses VSM that not references all MM, the analysis has
     * always only one models reference (VP-3384).
     * 
     * Use case :
     * <UL>
     * <LI>MM1 has a class A extending class B of MM2.</LI>
     * <LI>VSM has a representation that references MM1 has metamodel (but not MM2).</LI>
     * </UL>
     * 
     * @throws Exception
     */
    public void testCaseWithVSMThatNotReferencingAllMM() throws Exception {
        // Used a specific use case for this test
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH_3, SEMANTIC_RESOURCE_NAME_3, SESSION_RESOURCE_NAME_3, VSM_RESOURCE_NAME_3, MM_1_RESOURCE_NAME_3, MM_2_RESOURCE_NAME_3);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME_3, TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME_3, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME_3);
        assertEquals("The session should contain only one semantic resource after opening.", 1, session.getSemanticResources().size());
    }

    /**
     * Checks that after the first fragmentation we have 2 new files and that DAnalysis.models references are corrects.
     * 
     * @throws Exception
     */
    private void checkAfterFirstFragmentation() throws Exception {
        EObject p1SessionFragmentResourceRoot = session.getTransactionalEditingDomain().getResourceSet().getResource(p1FragmentedSessionResourceURI, true).getContents().get(0);
        assertTrue(p1SessionFragmentResourceRoot instanceof DAnalysis);
        dAnalysisOfP1SessionFragmentResource = (DAnalysis) p1SessionFragmentResourceRoot;

        // Checks that 2 new resources has been created
        IResource[] members = temporaryProject.members();
        if (Boolean.getBoolean("createRepresentationInSeparateResource")) { //$NON-NLS-1$
            assertEquals("The current project should contains 7 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + ", " + SESSION_RESOURCE_NAME, 7, members.length);
        } else {
            assertEquals("The current project should contains 6 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + ", " + SESSION_RESOURCE_NAME, 6, members.length);
        }
        assertEquals("The current project should contain .project ", ".project", members[0].getName());
        assertEquals("The current project should contain " + SESSION_RESOURCE_NAME, SESSION_RESOURCE_NAME, members[1].getName());
        assertEquals("The current project should contain " + SEMANTIC_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, members[2].getName());
        assertEquals("The current project should contain " + p1FragmentedSessionResourceURI.lastSegment(), p1FragmentedSessionResourceURI.lastSegment(), members[3].getName());
        assertEquals("The current project should contain " + p1FragmentedSemanticResourceURI.lastSegment(), p1FragmentedSemanticResourceURI.lastSegment(), members[4].getName());

        // Checks that DAnalysis.models and DAnalysis.referencedAnalysis
        // references has been updated to reference newly created resources
        // content
        assertEquals("The DAnalysis.models reference of the main session resource after first fragmentation should yet only reference root EPackage", Collections.singletonList(rootEPackage),
                dAnalysisOfMainSessionResource.getModels());
        assertEquals("The DAnalysis.models reference of the p1 fragment session resource after first fragmentation should only reference p1 EPackage", Collections.singletonList(p1EPackage),
                dAnalysisOfP1SessionFragmentResource.getModels());

    }

    /**
     * Checks that after the p2 fragmentation we have 2 new files and that DAnalysis.models references are corrects.
     * 
     * @throws Exception
     */
    private void checkAfterP2Fragmentation() throws Exception {
        EObject p2SessionFragmentResourceRoot = session.getTransactionalEditingDomain().getResourceSet().getResource(p2FragmentedSessionResourceURI, true).getContents().get(0);
        assertTrue(p2SessionFragmentResourceRoot instanceof DAnalysis);
        dAnalysisOfP2SessionFragmentResource = (DAnalysis) p2SessionFragmentResourceRoot;

        // Checks that 2 new resources has been created
        IResource[] members = temporaryProject.members();
        if (Boolean.getBoolean("createRepresentationInSeparateResource")) { //$NON-NLS-1$
            assertEquals("The current project should contains 7 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + ", " + SESSION_RESOURCE_NAME, 7, members.length);
        } else {
            assertEquals("The current project should contains 6 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + ", " + SESSION_RESOURCE_NAME, 6, members.length);
        }
        assertEquals("The current project should contain .project ", ".project", members[0].getName());
        assertEquals("The current project should contain " + SESSION_RESOURCE_NAME, SESSION_RESOURCE_NAME, members[1].getName());
        assertEquals("The current project should contain " + SEMANTIC_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, members[2].getName());
        assertEquals("The current project should contain " + p2FragmentedSessionResourceURI.lastSegment(), p2FragmentedSessionResourceURI.lastSegment(), members[3].getName());
        assertEquals("The current project should contain " + p2FragmentedSemanticResourceURI.lastSegment(), p2FragmentedSemanticResourceURI.lastSegment(), members[4].getName());

        // Checks that DAnalysis.models and DAnalysis.referencedAnalysis
        // references has been updated to reference newly created resources
        // content
        assertEquals("The DAnalysis.models reference of the main session resource after first fragmentation should yet only reference root EPackage", Collections.singletonList(rootEPackage),
                dAnalysisOfMainSessionResource.getModels());
        assertEquals("The DAnalysis.models reference of the p2 fragment session resource after first fragmentation should only reference p2 EPackage", Collections.singletonList(p2EPackage),
                dAnalysisOfP2SessionFragmentResource.getModels());

    }

    /**
     * Checks that after the second fragmentation we have 2 new files and that DAnalysis.models references are corrects.
     * 
     * @throws Exception
     */
    private void checkAfterSecondFragmentation() throws Exception {
        EObject p2SessionFragmentResourceRoot = session.getTransactionalEditingDomain().getResourceSet().getResource(p2FragmentedSessionResourceURI, true).getContents().get(0);
        assertTrue(p2SessionFragmentResourceRoot instanceof DAnalysis);
        dAnalysisOfP2SessionFragmentResource = (DAnalysis) p2SessionFragmentResourceRoot;

        // Checks that 2 new resources has been created
        IResource[] members = temporaryProject.members();
        assertEquals("The current project should contains 8 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + ", " + SESSION_RESOURCE_NAME, 8, members.length);
        assertEquals("The current project should contain .project ", ".project", members[0].getName());
        assertEquals("The current project should contain " + SESSION_RESOURCE_NAME, SESSION_RESOURCE_NAME, members[1].getName());
        assertEquals("The current project should contain " + SEMANTIC_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, members[2].getName());
        assertEquals("The current project should contain " + p1FragmentedSessionResourceURI.lastSegment(), p1FragmentedSessionResourceURI.lastSegment(), members[3].getName());
        assertEquals("The current project should contain " + p1FragmentedSemanticResourceURI.lastSegment(), p1FragmentedSemanticResourceURI.lastSegment(), members[4].getName());
        assertEquals("The current project should contain " + p2FragmentedSessionResourceURI.lastSegment(), p2FragmentedSessionResourceURI.lastSegment(), members[5].getName());
        assertEquals("The current project should contain " + p2FragmentedSemanticResourceURI.lastSegment(), p2FragmentedSemanticResourceURI.lastSegment(), members[6].getName());

        // Checks that DAnalysis.models and DAnalysis.referencedAnalysis
        // references has been updated to reference newly created resources
        // content
        assertEquals("The DAnalysis.models reference of the main session resource after second fragmentation should yet only reference root EPackage", Collections.singletonList(rootEPackage),
                dAnalysisOfMainSessionResource.getModels());
        assertEquals("The DAnalysis.models reference of the p1 fragment session resource after second fragmentation should only reference p1 EPackage", Collections.singletonList(p1EPackage),
                dAnalysisOfP1SessionFragmentResource.getModels());
        assertEquals("The DAnalysis.models reference of the p2 fragment session resource after second fragmentation should only reference p2 EPackage", Collections.singletonList(p2EPackage),
                dAnalysisOfP2SessionFragmentResource.getModels());
    }

    /**
     * Checks that after p22 fragmentation (after p2 fragmentation) we have 2 new files and that DAnalysis.models
     * references are corrects.
     * 
     * @throws Exception
     */
    private void checkAfterP22Fragmentation() throws Exception {
        EObject p22SessionFragmentResourceRoot = session.getTransactionalEditingDomain().getResourceSet().getResource(p22FragmentedSessionResourceURI, true).getContents().get(0);
        assertTrue(p22SessionFragmentResourceRoot instanceof DAnalysis);
        dAnalysisOfP22SessionFragmentResource = (DAnalysis) p22SessionFragmentResourceRoot;

        // Checks that 2 new resources has been created
        IResource[] members = temporaryProject.members();
        assertEquals("The current project should contains 8 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + ", " + SESSION_RESOURCE_NAME, 8, members.length);
        assertEquals("The current project should contain .project ", ".project", members[0].getName());
        assertEquals("The current project should contain " + SESSION_RESOURCE_NAME, SESSION_RESOURCE_NAME, members[1].getName());
        assertEquals("The current project should contain " + SEMANTIC_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, members[2].getName());
        assertEquals("The current project should contain " + p2FragmentedSessionResourceURI.lastSegment(), p2FragmentedSessionResourceURI.lastSegment(), members[3].getName());
        assertEquals("The current project should contain " + p2FragmentedSemanticResourceURI.lastSegment(), p2FragmentedSemanticResourceURI.lastSegment(), members[4].getName());
        assertEquals("The current project should contain " + p22FragmentedSessionResourceURI.lastSegment(), p22FragmentedSessionResourceURI.lastSegment(), members[5].getName());
        assertEquals("The current project should contain " + p22FragmentedSemanticResourceURI.lastSegment(), p22FragmentedSemanticResourceURI.lastSegment(), members[6].getName());

        // Checks that DAnalysis.models and DAnalysis.referencedAnalysis
        // references has been updated to reference newly created resources
        // content
        assertEquals("The DAnalysis.models reference of the main session resource after second fragmentation should yet only reference root EPackage", Collections.singletonList(rootEPackage),
                dAnalysisOfMainSessionResource.getModels());
        assertEquals("The DAnalysis.models reference of the p2 fragment session resource after second fragmentation should only reference p2 EPackage", Collections.singletonList(p2EPackage),
                dAnalysisOfP2SessionFragmentResource.getModels());
        assertEquals("The DAnalysis.models reference of the p22 fragment session resource after second fragmentation should only reference p22 EPackage", Collections.singletonList(p22EPackage),
                dAnalysisOfP22SessionFragmentResource.getModels());
    }

    /**
     * Checks that after the drag'n drop the DAnalysis.models references are corrects.
     * 
     * @throws Exception
     */
    private void checkAfterDragAndDrop() {
        // Checks that DAnalysis.models and DAnalysis.referencedAnalysis
        // references has been updated to reference newly created resources
        // content
        assertEquals("The DAnalysis.models reference of the main session resource after second fragmentation should yet only reference root EPackage", Collections.singletonList(rootEPackage),
                dAnalysisOfMainSessionResource.getModels());
        Collection<EObject> expectedModels = new ArrayList<EObject>();
        expectedModels.add(p1EPackage);
        expectedModels.add(p2EPackage);
        assertEquals("The DAnalysis.models reference of the p1 fragment session resource after second fragmentation should only reference p1 EPackage", expectedModels,
                dAnalysisOfP1SessionFragmentResource.getModels());
        assertEquals("The DAnalysis.models reference of the p2 fragment session resource after second fragmentation should only reference p2 EPackage", Collections.singletonList(p2EPackage),
                dAnalysisOfP2SessionFragmentResource.getModels());

    }

    /**
     * Do a fragmentation.
     * 
     * @throws InterruptedException
     * @throws OperationCanceledException
     */
    private void fragment(EObject semanticRoot, URI semanticDest, DRepresentation representationDestination, URI representationDest) throws OperationCanceledException, InterruptedException {
        DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representationDestination).getRepresentationDescriptor();
        Command p1EPackageControlCommand = new SiriusControlCommand(semanticRoot, semanticDest, Collections.singleton(representationDescriptor), representationDest, true, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(p1EPackageControlCommand);
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IWorkspaceRoot.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            e.printStackTrace();
        }

        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
    }

    /**
     * Do a drag'n drop.
     */
    private void doDragAndDrop(EObject eObjectToDrag, EObject semanticDropTarget, DRepresentation dRepresentationDropTarget) {
        WorkspaceDragAndDropSupport workspaceDragAndDropSupport = new WorkspaceDragAndDropSupport();
        DragAndDropTarget p1EPackageDragAndDropTarget = (DragAndDropTarget) dRepresentationDropTarget;
        DragAndDropTargetDescription dragAndDropDescription = p1EPackageDragAndDropTarget.getDragAndDropDescription();
        EObject droppedElementForDropTool = workspaceDragAndDropSupport.convert(eObjectToDrag, session);

        ContainerDropDescription dropTool = DDiagramElementContainerWithInterpreterOperations.getBestDropDescription(dragAndDropDescription, droppedElementForDropTool, null, semanticDropTarget,
                p1EPackageDragAndDropTarget, DragSource.PROJECT_EXPLORER_LITERAL, null);
        Command dropCmd = getCommandFactory().buildDropInContainerCommandFromTool(p1EPackageDragAndDropTarget, droppedElementForDropTool, dropTool);
        session.getTransactionalEditingDomain().getCommandStack().execute(dropCmd);

    }

    private IFile getIFile(final String fileName) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getFile("/" + fileName);
    }

    private void checkThatSemanticModelIsAddedToSession(boolean closeSession) throws Exception {
        // Checks before test that we have the expected resources in the
        // workspace
        IResource[] members = temporaryProject.members();
        assertEquals("The current project should contains 4 files : .project, " + SEMANTIC_RESOURCE_NAME + ", " + SEMANTIC_RESOURCE_NAME_P3 + " and " + SESSION_RESOURCE_NAME, 4, members.length);
        assertEquals("The current project should contain .project ", ".project", members[0].getName());
        assertEquals("The current project should contain " + SESSION_RESOURCE_NAME, SESSION_RESOURCE_NAME, members[1].getName());
        assertEquals("The current project should contain " + SEMANTIC_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, members[2].getName());
        assertEquals("The current project should contain " + SEMANTIC_RESOURCE_NAME_P3, SEMANTIC_RESOURCE_NAME_P3, members[3].getName());

        assertEquals("The DAnalysis.models reference of the main session resource before fragmentation should only reference root EPackage", Collections.singletonList(rootEPackage),
                dAnalysisOfMainSessionResource.getModels());

        // Control (fragment) p22 and p222 each in its own fragment
        fragment(p2EPackage, p2FragmentedSemanticResourceURI, p2EPackageDRepresentation, p2FragmentedSessionResourceURI);

        checkAfterP2Fragmentation();

        fragment(p22EPackage, p22FragmentedSemanticResourceURI, p22EPackageDRepresentation, p22FragmentedSessionResourceURI);
        // TestsUtil.synchronizationWithUIThread();
        checkAfterP22Fragmentation();

        assertTrue("The session must be open", session.isOpen());
        session.save(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());

        URI mainAnalysisURI = session.getSessionResource().getURI();
        if (closeSession) {
            // Close session
            session.close(new NullProgressMonitor());
            assertFalse("The session must be closed", session.isOpen());
        }

        // Add inheritance E3 to class E2 on semantic model VP-2770.ecore
        addASuperTypeToSemanticElementInAnotherResourceSet();

        if (closeSession) {
            // Open Session
            session = SessionManager.INSTANCE.getSession(mainAnalysisURI, new NullProgressMonitor());
            if (!session.isOpen()) {
                session.open(new NullProgressMonitor());
            }
            assertTrue("The session must be open", session.isOpen());
        } else {
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        }

        // Check model p3.ecore adding to p2.aird
        Collection<Resource> semanticResources = session.getSemanticResources();
        assertEquals("The session should have a depedencies to " + SEMANTIC_RESOURCE_NAME + " and " + SEMANTIC_RESOURCE_NAME_P3, 2, semanticResources.size());
        boolean resourceFind = false;
        boolean resourceP3Find = false;
        for (Resource semanticResource : semanticResources) {
            if (semanticResource.getURI().path().contains(SEMANTIC_RESOURCE_NAME)) {
                resourceFind = true;
            } else if (semanticResource.getURI().path().contains(SEMANTIC_RESOURCE_NAME_P3)) {
                resourceP3Find = true;
            }
        }
        if (!resourceFind || !resourceP3Find) {
            fail("The session should have a depedencies to " + SEMANTIC_RESOURCE_NAME + " and " + SEMANTIC_RESOURCE_NAME_P3);
        }

    }

    private void addASuperTypeToSemanticElementInAnotherResourceSet() {
        TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        ResourceSet set = domain.getResourceSet();
        try {
            final EPackage ePackageP3InAnotherResourceSet = (EPackage) ModelUtils.load(URI.createPlatformResourceURI('/' + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME_P3, true), set);
            final EClassifier E3EClass = ePackageP3InAnotherResourceSet.getESubpackages().get(0).getESubpackages().get(0).getEClassifiers().get(0);

            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(p22FragmentedSemanticResourceURI, set);

            domain.getCommandStack().execute(new RecordingCommand(domain, "Add a superType") {
                @Override
                protected void doExecute() {
                    EClassifier E2EClass = ePackageInAnotherResourceSet.getEClassifiers().get(0);
                    if (E2EClass instanceof EClass && E3EClass instanceof EClass) {
                        ((EClass) E2EClass).getESuperTypes().add((EClass) E3EClass);
                    }
                }
            });
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        TestsUtil.synchronizationWithUIThread();
    }

}
