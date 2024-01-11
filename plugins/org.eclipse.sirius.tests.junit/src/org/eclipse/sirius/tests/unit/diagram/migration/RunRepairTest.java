/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.OpenedSessionsCondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Basic repair tests.
 * 
 * @author dlecan
 */
public class RunRepairTest extends AbstractRepairMigrateTest {

    private static final String PLUGIN_PATH = "/data/unit/repair/";

    private static final String TC_2103_AIRD = "2103.aird";

    private static final String TC_2103_MODEL = "2103.ecore";

    private static final String TC_2077_AIRD = "2077/2077.aird";

    private static final String TC_2077_MODEL = "2077/2077.ecore";

    private static final String TC_2077_VSM = "2077/2077.odesign";

    private static final String TC_2316_AIRD_PB1_MAIN = "2316_pb1/My.aird";

    private static final String TC_2316_MODEL_PB1_MAIN = "2316_pb1/My.ecore";

    private static final String TC_2316_AIRD_PB1_FRAGMENT = "2316_pb1/My_p1.aird";

    private static final String TC_2316_MODEL_PB1_FRAGMENT = "2316_pb1/My_p1.ecore";

    private static final String TC_2316_AIRD_PB2_MAIN = "2316_pb2/My.aird";

    private static final String TC_2316_MODEL_PB2_MAIN = "2316_pb2/My.ecore";

    private static final String TC_2316_AIRD_PB2_FRAGMENT = "2316_pb2/My_p1.aird";

    private static final String TC_2316_MODEL_PB2_FRAGMENT = "2316_pb2/My_p1.ecore";

    private static final String TC_2316_MODEL_PB4 = "2316_pb4/My.ecore";

    private static final String TC_2316_AIRD_PB4 = "2316_pb4/My.aird";

    private static final String TC_VP_2035_AIRD = "VP-2035/My.aird";

    private static final String TC_VP_2035_MODEL = "VP-2035/My.ecore";

    private static final String TC_VP_2035_P1_AIRD = "VP-2035/My_p1.aird";

    private static final String TC_VP_2035_P1_MODEL = "VP-2035/My_p1.ecore";

    private static final String TC_VP_2035_P2_AIRD = "VP-2035/My_p1_p2.aird";

    private static final String TC_VP_2035_P2_MODEL = "VP-2035/My_p1_p2.ecore";

    private static final String TC_VP_2035_P3_AIRD = "VP-2035/My_p1_p2_p3.aird";

    private static final String TC_VP_2035_P3_MODEL = "VP-2035/My_p1_p2_p3.ecore";

    private static final String TC_VP_2069_ROOT_AIRD = "VP-2069/root.aird";

    private static final String TC_VP_2069_ROOT_MODEL = "VP-2069/root.ecore";

    private static final String TC_VP_2069_P1_AIRD = "VP-2069/root_p1.aird";

    private static final String TC_VP_2069_P1_MODEL = "VP-2069/root_p1.ecore";

    private static final String TC_2369_AIRD = "VP-2369/My.aird";

    private static final String TC_2369_MODEL = "VP-2369/My.ecore";

    private static final String TC_2369_VSM = "VP-2369/tc_breakdown.odesign";

    private static final String TC_2552_AIRD = "VP-2552/tc2552.aird";

    private static final String TC_2552_MODEL = "VP-2552/tc2552.ecore";

    private static final String TC_2552_VSM = "VP-2552/tc2552.odesign";

    private static final String TC_VP_2738_ROOT_AIRD = "/data/unit/control/VP-2070/part2/root.aird";

    private static final String TC_VP_2738_ROOT_MODEL = "/data/unit/control/VP-2070/part2/root.ecore";

    private static final String TC_VP_2738_P1_AIRD = "/data/unit/control/VP-2070/part2/root_p1.aird";

    private static final String TC_VP_2738_P1_MODEL = "/data/unit/control/VP-2070/part2/root_p1.ecore";

    private static final String[] FILES = { TC_2103_AIRD, TC_2103_MODEL, TC_2077_AIRD, TC_2077_MODEL, TC_2077_VSM, TC_2316_AIRD_PB1_MAIN, TC_2316_MODEL_PB1_MAIN, TC_2316_AIRD_PB1_FRAGMENT,
            TC_2316_MODEL_PB1_FRAGMENT, TC_2316_AIRD_PB2_MAIN, TC_2316_MODEL_PB2_MAIN, TC_2316_AIRD_PB2_FRAGMENT, TC_2316_MODEL_PB2_FRAGMENT, TC_2316_MODEL_PB4, TC_2316_AIRD_PB4, TC_VP_2035_AIRD,
            TC_VP_2035_MODEL, TC_VP_2035_P1_AIRD, TC_VP_2035_P1_MODEL, TC_VP_2035_P2_AIRD, TC_VP_2035_P2_MODEL, TC_VP_2035_P3_AIRD, TC_VP_2035_P3_MODEL, TC_VP_2069_ROOT_AIRD, TC_VP_2069_ROOT_MODEL,
            TC_VP_2069_P1_AIRD, TC_VP_2069_P1_MODEL, TC_2369_AIRD, TC_2369_MODEL, TC_2369_VSM, TC_2552_AIRD, TC_2552_MODEL, TC_2552_VSM };

    private static final String[] REUSED_FILES = { TC_VP_2738_ROOT_AIRD, TC_VP_2738_ROOT_MODEL, TC_VP_2738_P1_AIRD, TC_VP_2738_P1_MODEL };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp();
        if (session != null) {
            session.close(new NullProgressMonitor());
        }

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PLUGIN_PATH, FILES);

        for (String path : REUSED_FILES) {
            String wksPath = TEMPORARY_PROJECT_NAME + "/" + path;
            EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, path, wksPath);
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testMissingTransactionWhenDoingRepairMigrate() throws Exception {
        String path = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_2103_AIRD;

        List<EObject> rootElements = loadFile(path, EObject.class);
        DDiagramElementContainer dDiagramElement = (DDiagramElementContainer) new DViewQuery(((DAnalysis) rootElements.get(0)).getOwnedViews().get(0)).getLoadedRepresentations().get(0)
                .getOwnedRepresentationElements().get(0);

        // Remove this mapping to lead to transactional issues if transactions
        // are not correctly handled
        dDiagramElement.setActualMapping(null);
        saveFile(rootElements, path);

        runRepairProcess(TC_2103_AIRD);

        // Nothing to check
        // Just test that migration process can be run without error
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testContainerWithNullMappingWhenDoingRepairMigrate() throws Exception {
        String path = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_2077_AIRD;

        List<EObject> rootElements = loadFile(path, EObject.class);
        DDiagramElementContainer dDiagramElement = (DDiagramElementContainer) new DViewQuery(((DAnalysis) rootElements.get(0)).getOwnedViews().get(0)).getLoadedRepresentations().get(0)
                .getOwnedRepresentationElements().get(0);

        // Remove this mapping to lead to NPE issues
        dDiagramElement.setActualMapping(null);
        saveFile(rootElements, path);

        runRepairProcess(TC_2077_AIRD);

        // Nothing to check
        // Just test that migration process can be run without error
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramElementWithUnexistingTarget() throws Exception {
        setErrorCatchActive(true);
        runRepairProcess(TC_2316_AIRD_PB1_MAIN);
        setErrorCatchActive(false);
        // Nothing to check
        // Just test that migration process can be run without error
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAirdWithUnexistingModel() throws Exception {
        setErrorCatchActive(true);
        runRepairProcess(TC_2316_AIRD_PB2_MAIN);
        setErrorCatchActive(false);
        // Nothing to check
        // Just test that migration process can be run without error
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBlankAird() throws Exception {
        setErrorCatchActive(true);
        runRepairProcess(TC_2316_AIRD_PB4);
        setErrorCatchActive(false);
        // Check that one error occurs (because the representation files
        // contains no DAnalysis).
        assertTrue("One error should occur (because the representation files contains no DAnalysis)", errors.values().size() == 1 && errors.values().iterator().next().size() == 1);
        assertEquals("The error message is not the expected one.", SiriusRepairProcess.ERROR_MSG, errors.values().iterator().next().iterator().next().getMessage());
        // Clear the expected error
        clearErrors();
    }

    /**
     * Check if the XMI files are already valid after two migrations.
     * 
     * @throws Exception
     */
    public void testFragmentedMigration() throws Exception {
        setErrorCatchActive(true);
        /* set refresh on representation opening */
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        runRepairProcess(TC_VP_2035_AIRD);
        TestsUtil.emptyEventsFromUIThread();

        // Check that we can load all the aird files again
        assertLoadOK(TC_VP_2035_AIRD, "after first migration");
        assertLoadOK(TC_VP_2035_P1_AIRD, "after first migration");
        assertLoadOK(TC_VP_2035_P2_AIRD, "after first migration");
        assertLoadOK(TC_VP_2035_P3_AIRD, "after first migration");

        runRepairProcess(TC_VP_2035_AIRD);
        TestsUtil.emptyEventsFromUIThread();
        assertLoadOK(TC_VP_2035_AIRD, "after second migration");
        assertLoadOK(TC_VP_2035_P1_AIRD, "after second migration");
        assertLoadOK(TC_VP_2035_P2_AIRD, "after second migration");
        assertLoadOK(TC_VP_2035_P3_AIRD, "after second migration");

        setErrorCatchActive(false);
    }

    /**
     * Check that this use case is migrated a models references pointing to the
     * root semantic element is not added to the fragmented aird.
     * 
     * @throws Exception
     */
    public void testFragmentedMigrationNotAddUnecessaryModelsReferences() throws Exception {
        runRepairProcess(TC_VP_2069_ROOT_AIRD);
        TestsUtil.emptyEventsFromUIThread();

        // Check that there is only one Model in the DAnalysis of root.aird
        // (pointing to the root element root)
        String rootAirdPath = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_VP_2069_ROOT_AIRD;
        List<DAnalysis> rootData = loadFile(rootAirdPath, DAnalysis.class);
        assertEquals("Bad number of DAnalysis in " + TC_VP_2069_ROOT_AIRD, 1, rootData.size());
        assertEquals("Bad number of Models in " + TC_VP_2069_ROOT_AIRD, 1, rootData.get(0).getModels().size());
        assertTrue("Bad type for the Model of " + TC_VP_2069_ROOT_AIRD, rootData.get(0).getModels().get(0) instanceof EPackage);
        assertEquals("Bad model element in " + TC_VP_2069_ROOT_AIRD, "root", ((EPackage) rootData.get(0).getModels().get(0)).getName());

        // Check that there is only one Model in the DAnalysis of root_p1.aird
        // (pointing to the root element p1)
        String p1AirdPath = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_VP_2069_P1_AIRD;
        List<DAnalysis> p1Data = loadFile(p1AirdPath, DAnalysis.class);
        assertEquals("Bad number of DAnalysis in " + TC_VP_2069_P1_AIRD, 1, p1Data.size());
        assertEquals("Bad number of Models in " + TC_VP_2069_P1_AIRD, 1, p1Data.get(0).getModels().size());
        assertTrue("Bad type for the Model of " + TC_VP_2069_P1_AIRD, p1Data.get(0).getModels().get(0) instanceof EPackage);
        assertEquals("Bad model element in " + TC_VP_2069_P1_AIRD, "p1", ((EPackage) p1Data.get(0).getModels().get(0)).getName());
    }

    /**
     * Check that the fragment has always two models references after the
     * migration. VP-2738
     * 
     * @throws Exception
     */
    public void testFragmentedMigrationKeepModelsReferencesAfterMigration() throws Exception {
        setErrorCatchActive(true);
        runRepairProcess(TC_VP_2738_ROOT_AIRD);
        TestsUtil.emptyEventsFromUIThread();

        // Check that we can load all the aird files again
        assertLoadOK(TC_VP_2738_ROOT_AIRD, "after first migration");
        assertLoadOK(TC_VP_2738_P1_AIRD, "after first migration");

        // Check that there there are always two models references in the
        // fragment.
        String fragmentAirdPath = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_VP_2738_P1_AIRD;
        List<DAnalysis> rootData = loadFile(fragmentAirdPath, DAnalysis.class);
        assertEquals("Bad number of DAnalysis in " + TC_VP_2738_P1_AIRD, 1, rootData.size());
        assertEquals("Bad number of Models in " + TC_VP_2738_P1_AIRD, 2, rootData.get(0).getModels().size());

        setErrorCatchActive(false);
    }

    private void assertLoadOK(String projectRelativePath, String endMessage) {
        try {
            loadFile("/" + TEMPORARY_PROJECT_NAME + "/" + projectRelativePath, DAnalysis.class);
        } catch (IOException e) {
            fail("Problem during relaoding of " + projectRelativePath + " " + endMessage + " : " + e.getMessage());
        }
    }

    private <E> List<E> loadFile(final String path, Class<E> type) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createPlatformResourceURI(path, true);
        final Resource resource = ModelUtils.createResource(uri, resourceSet);
        resource.load(Collections.EMPTY_MAP);

        List<E> list = resource.getContents().stream().filter(type::isInstance).map(type::cast).collect(Collectors.toList());
        return list;
    }

    private void saveFile(final Collection<? extends EObject> rootElements, final String path) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createPlatformResourceURI(path, true);
        final Resource resource = ModelUtils.createResource(uri, resourceSet);
        resource.getContents().addAll(rootElements);
        resource.save(Collections.EMPTY_MAP);
    }

    /**
     * Check the the fold state is not lost during repair/migrate.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testMigrationOfBreakdownDiagram() throws Exception {
        // Launch migration
        setErrorCatchActive(true);
        runRepairProcess(TC_2369_AIRD);
        setErrorCatchActive(false);

        // Get the content of the migrated file
        String path = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_2369_AIRD;
        List<DAnalysis> data = loadFile(path, DAnalysis.class);
        DDiagram diagram = (DDiagram) new DViewQuery(data.get(0).getOwnedViews().get(0)).getLoadedRepresentations().get(0);
        // Check the fold status of the first incoming edge of some packages
        assertTrue("The package P_2 should be explicitly folded after the repair-migrate (as it was before).",
                new DDiagramElementQuery(((DNodeContainer) diagram.getOwnedDiagramElements().get(2)).getIncomingEdges().get(0)).isExplicitlyFolded());
        assertTrue("The package P_3 should be indirectly folded after the repair-migrate (as it was before).",
                !new DDiagramElementQuery(((DNodeContainer) diagram.getOwnedDiagramElements().get(1)).getIncomingEdges().get(0)).isExplicitlyFolded()
                        && new DDiagramElementQuery(((DNodeContainer) diagram.getOwnedDiagramElements().get(1)).getIncomingEdges().get(0)).isIndirectlyFolded());
        assertTrue("The package P_4 should be explicitly folded after the repair-migrate (as it was before).",
                new DDiagramElementQuery(((DNodeContainer) diagram.getOwnedDiagramElements().get(0)).getIncomingEdges().get(0)).isExplicitlyFolded());
        assertTrue("The package P_8 should be explicitly folded after the repair-migrate (as it was before).",
                new DDiagramElementQuery(((DNodeContainer) diagram.getOwnedDiagramElements().get(7)).getIncomingEdges().get(0)).isExplicitlyFolded());
    }

    /**
     * Check the problem of VP-2552. In this use case, the session is dirty
     * after a repair/migrate.<BR>
     * Use case precision : The label of the bordered node that will be filtered
     * must be hide by default (property "Hide Label By Default" of the style).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoDirtyAfterMigrationWithFilterApplied() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        /* set refresh on representation opening */
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        String pathModel = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_2552_MODEL;
        String pathAird = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_2552_AIRD;
        String pathVSM = "/" + TEMPORARY_PROJECT_NAME + "/" + TC_2552_VSM;
        String representationDescName = "tc2552";
        String hideEAttributsFilterName = "HideAttributes";

        genericSetUp(pathModel, pathVSM, pathAird);

        Collection<DRepresentation> representations = getRepresentations(representationDescName);
        assertFalse(representations.isEmpty());
        DDiagram diagram = (DDiagram) representations.iterator().next();
        assertNotNull(diagram);
        DiagramEditor editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Activate filter that hide attributes
        activateFilter(diagram, hideEAttributsFilterName);
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editor, true);
        TestsUtil.synchronizationWithUIThread();

        // Create new attribute
        RecordingCommand cmd = new RecordingCommand(session.getTransactionalEditingDomain(), "Create new attribute") {
            @Override
            protected void doExecute() {
                EPackage rootPackage = (EPackage) semanticModel;
                EClass firstClass = (EClass) rootPackage.getEClassifier("Class1");
                EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
                eAttribute.setName("newAttribute");
                firstClass.getEStructuralFeatures().add(eAttribute);
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

        // Close and save the session
        session.save(new NullProgressMonitor());
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        closeSession(session);
        TestsUtil.waitUntil(new OpenedSessionsCondition(0));

        // Migrate the aird
        runRepairProcess(TC_2552_AIRD);

        // Check that the session if not dirty after the opening of the
        // diagram
        createSession(getFileURI(TC_2552_AIRD));
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TestsUtil.waitUntil(new OpenedSessionsCondition(1));

        representations = getRepresentations(representationDescName);
        assertFalse("The session should be opened and contain at least one representation. Session: " + (session.isOpen() ? "opened" : "closed") + "representations: " + representations.size(),
                representations.isEmpty());
        diagram = (DDiagram) representations.iterator().next();
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("The session should not be dirty.", SessionStatus.SYNC, session.getStatus());
    }
}
