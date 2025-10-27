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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Tests for repair/migrate action for fragmented files.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class FragmentedFilesMigrationTest extends AbstractRepairMigrateTest {

    private static final String PATH = "/data/unit/migration/do_not_migrate/fragmentedMigration/";

    private static final String SEMANTIC_MODEL_FILENAME_1 = "fragmentedMigration.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_2 = "fragmentedMigration_p1.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_3 = "fragmentedMigration_p2.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_4 = "fragmentedMigration_p1_p1_1.ecore";

    private static final String SESSION_MODEL_FILENAME_1 = "fragmentedMigration.aird";

    private static final String SESSION_MODEL_FILENAME_2 = "fragmentedMigration_p1.aird";

    private static final String SESSION_MODEL_FILENAME_3 = "fragmentedMigration_p2.aird";

    private static final String SESSION_MODEL_FILENAME_4 = "fragmentedMigration_p1_p1_1.aird";

    private static final String SESSION_MODEL_FILENAME_5 = "fragmentedMigration4.13.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME_1, SEMANTIC_MODEL_FILENAME_2, SEMANTIC_MODEL_FILENAME_3, SEMANTIC_MODEL_FILENAME_4, SESSION_MODEL_FILENAME_1,
                SESSION_MODEL_FILENAME_2, SESSION_MODEL_FILENAME_3, SESSION_MODEL_FILENAME_4, SESSION_MODEL_FILENAME_5);
    }

    /**
     * Test that the migration of the fragmented files is done when the
     * migration of the parent is launch. Warning : This test doesn't test the
     * migration process but only that :
     * <UL>
     * <LI>the number of backup files during migration is OK,</LI>
     * <LI>and the parent aird can be openned.</LI>
     * 
     * @throws Exception
     */
    public void testMigrationWithFragmentedFile() throws Exception {
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.aird");

        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        assertEquals("It should be no backup file before migration.", 0, getNumberOfBackupFiles(project));

        TransactionalEditingDomain editingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(new ResourceSetImpl());
        ResourceSet rs = editingDomain.getResourceSet();

        // Check the parent semantic model
        URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore", true);
        checkParentSemanticModel(parentUri, rs, "root");

        runRepairProcess("fragmentedMigration.aird");

        // Check that 4 backup of aird files has been done.
        assertEquals("There should have 4 backup files after migration (one for selected aird file and three for the fragmented aird).", 4, getNumberOfBackupFiles(project));

        // Check again the parent semantic model
        checkParentSemanticModel(parentUri, rs, "root");

        URI newSessionResourceURI = getFileURI("fragmentedMigration.aird");
        Session newSession = SessionManager.INSTANCE.getSession(newSessionResourceURI, new NullProgressMonitor());
        newSession.open(new NullProgressMonitor());
        IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(newSession);
        uiSession.open();

        assertNotNull("we should be able to retrieve the new session from the model after migration.", newSession);
        uiSession.close();
        TestsUtil.emptyEventsFromUIThread();
        newSession.close(new NullProgressMonitor());
        uiSession = SessionUIManager.INSTANCE.getUISession(newSession);
        if (uiSession != null) {
            uiSession.close();
        }
    }

    /**
     * Test clean references of the migration process (remove unexisting
     * references)
     * <UL>
     * <LI>filters</LI>
     * <LI>rules</LI>
     * <LI>behaviors</LI>
     * </UL>
     * 
     * @throws Exception
     */
    public void testFiltersRulesBehaviorMigration() throws Exception {
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.aird");

        TransactionalEditingDomain editingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(new ResourceSetImpl());
        ResourceSet resourceSet = editingDomain.getResourceSet();
        // Check the parent semantic model
        URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.ecore", true);
        checkParentSemanticModel(parentUri, resourceSet, "p1");

        runRepairProcess("fragmentedMigration_p1.aird");

        // Check again the parent semantic model
        checkParentSemanticModel(parentUri, resourceSet, "p1");

        URI sessionResourceURI = getFileURI("fragmentedMigration_p1.aird");

        Session newSession = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        assertNotNull("We should be able to retrieve the new session from the model after migration.", newSession);
        newSession.open(new NullProgressMonitor());
        // Check that the representation have :
        // - only one filter activated (two before migration)
        // - 0 behavior (one before migration)
        // - only one rule (two before migration)
        final String diagramName = "p1 package entities";
        DSemanticDiagram diagram = getDiagramFromName(newSession, diagramName);
        assertNotNull("A diagram with name " + diagramName + " should exist in the session.", diagram);
        assertEquals("There must be 1 filter activated after migration", 1, diagram.getActivatedFilters().size());
        assertEquals("There must be 0 behavior activated after migration", 0, diagram.getActivateBehaviors().size());
        assertEquals("There must be 1 rule activated after migration", 1, diagram.getActivatedRules().size());
        // assertNotNull("The filter variables list should not be null.",
        // diagram.getFilterVariableHistory());
        // assertEquals("There must be 1 filter variables after migration", 1,
        // diagram.getFilterVariableHistory().getOwnedValues().size());
        newSession.close(new NullProgressMonitor());
        IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(newSession);
        if (uiSession != null) {
            uiSession.close();
            TestsUtil.emptyEventsFromUIThread();
        }
    }

    /**
     * Test the migration of the visibility from 4.13 to newer.
     * 
     * @throws Exception
     */
    public void testVisibilityMigration() throws Exception {
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration4.13.aird");

        TransactionalEditingDomain editingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(new ResourceSetImpl());
        ResourceSet resourceSet = editingDomain.getResourceSet();

        // Check the parent semantic model
        URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore", true);
        EPackage root = checkParentSemanticModel(parentUri, resourceSet, "root");

        String sessionResourceName = "fragmentedMigration4.13.aird";
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + sessionResourceName, true);

        runRepairProcess(sessionResourceName);

        // Check again the parent semantic model
        root = checkParentSemanticModel(parentUri, resourceSet, "root");

        Session newSession = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        assertNotNull("We should be able to retrieve the new session from the model after migration.", newSession);
        newSession.open(new NullProgressMonitor());

        // To get the semantic reference in context a newSession's ResourceSet
        root = (EPackage) newSession.getTransactionalEditingDomain().getResourceSet().getResource(parentUri, true).getContents().get(0);

        // Check that the node of package p2 is not visible
        final String diagramName = "root package entities";
        final DSemanticDiagram diagram = getDiagramFromName(newSession, diagramName);
        final EPackage package2 = Iterables.find(root.getESubpackages(), new Predicate<EPackage>() {
            @Override
            public boolean apply(EPackage input) {
                return "p2".equals(input.getName());
            }
        });
        assertNotNull("A package p2 should exist in the semantic model.", package2);
        DRepresentationElement p2Representation = getFirstRepresentationElement(diagram, package2);
        assertTrue("The representation of package p2 should be a DDiagramElement.", p2Representation instanceof DDiagramElement);
        int size = ((DDiagramElement) p2Representation).getGraphicalFilters().size();
        assertTrue("The package p2 should have exactly one graphical filter (size :" + size + ")", size == 1);
        assertTrue("The package p2 should have exactly one graphical filter of type HideFilter.", ((DDiagramElement) p2Representation).getGraphicalFilters().get(0) instanceof HideFilter);

        newSession.close(new NullProgressMonitor());
        IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(newSession);
        if (uiSession != null) {
            uiSession.close();
            TestsUtil.emptyEventsFromUIThread();
        }
    }

    /**
     * Test the migration with fragment in read only file. Simulate click ok to
     * passed files read only to writables mode.
     * 
     * @throws Exception
     */
    public void testMigrationWithReadOnlyFilePressOk() throws Exception {
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.aird");

        IFile p1Fragment = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.aird"));
        IFile p2Fragment = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.aird"));
        IFile p1P1Fragment = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.aird"));

        EclipseTestsSupportHelper.INSTANCE.setReadOnlyStatus(true, p1Fragment, p2Fragment, p1P1Fragment);
        try {
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
            assertEquals("It should be no backup file before migration.", 0, getNumberOfBackupFiles(project));

            TransactionalEditingDomain editingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(new ResourceSetImpl());
            ResourceSet rs = editingDomain.getResourceSet();

            // Check the parent semantic model
            URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore", true);
            checkParentSemanticModel(parentUri, rs, "root");

            // runRepairProcess("fragmentedMigration.aird");

            // Check that 4 backup of aird files has been done.
            // assertEquals("There should have 4 backup files after migration
            // (one for selected aird file and three for the fragmented aird).",
            // 4, getNumberOfBackupFiles(project));
        } finally {
            EclipseTestsSupportHelper.INSTANCE.setReadOnlyStatus(false, p1Fragment, p2Fragment, p1P1Fragment);
        }
    }

    /**
     * Test the migration with fragment in read only file. Simulate click No to
     * not change status file Read Only to Writable.
     */
    public void testMigrationWithReadOnlyFilePressCancel() {
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.aird");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.ecore");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.aird");

        IFile p1Fragment = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1.ecore"));
        IFile p2Fragment = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p2.ecore"));
        IFile p1P1Fragment = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/fragmentedMigration_p1_p1_1.ecore"));

        EclipseTestsSupportHelper.INSTANCE.setReadOnlyStatus(true, p1Fragment, p2Fragment, p1P1Fragment);

        try {
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
            assertEquals("It should be no backup file before migration.", 0, getNumberOfBackupFiles(project));

            TransactionalEditingDomain editingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(new ResourceSetImpl());
            ResourceSet rs = editingDomain.getResourceSet();

            // Check the parent semantic model
            URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/fragmentedMigration.ecore", true);
            checkParentSemanticModel(parentUri, rs, "root");

            // try {
            // runRepairProcess("fragmentedMigration.aird");
            // fail("The migration should be cancelled because of read-only
            // files");
            // } catch (Exception e) {
            //
            // } finally {
            // // The backup files are created
            // //
            // assertEquals("There should have 4 backup files after migration
            // (one for selected aird file and three for the fragmented aird).",
            // 0, getNumberOfBackupFiles(project));
            // }
        } finally {
            EclipseTestsSupportHelper.INSTANCE.setReadOnlyStatus(false, p1Fragment, p2Fragment, p1P1Fragment);
        }
    }

    /**
     * Check that the semantic model (the parent of the fragmentation) is OK and
     * return its root.
     * 
     * @return the root of the semantic model.
     */
    private EPackage checkParentSemanticModel(final URI uri, final ResourceSet rs, final String rootName) {
        Resource res = rs.getResource(uri, true);
        assertNotNull("Test input model could not be loaded (" + uri + ")", res);
        assertTrue("Invalid test input model : bad root class", res.getContents().get(0) instanceof EPackage);
        assertTrue("Invalid test input model : bad root name", rootName.equals(((EPackage) res.getContents().get(0)).getName()));
        return (EPackage) res.getContents().get(0);
    }

    /**
     * Count the number of backup in this project.
     * 
     * @param project
     *            The concern project.
     * @return the number of backup in this project.
     */
    private int getNumberOfBackupFiles(IProject project) {
        int result = 0;
        try {
            IResource[] members = project.members();
            for (int i = 0; i < members.length; i++) {
                IResource member = members[i];
                if (member.getName().endsWith("aird.old")) {
                    result++;
                }
            }
        } catch (CoreException e) {
            // do nothing
        }
        return result;
    }

    private void assertFileExists(String wksPath) {
        assertFileExists(null, wksPath);
    }

    private void assertFileExists(String message, String wksPath) {
        final boolean condition = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath)).exists();
        if (message == null) {
            assertTrue(condition);
        } else {
            assertTrue(message, condition);
        }
    }

    private DSemanticDiagram getDiagramFromName(final Session sessionToGo, final String name) {
        for (final DView dView : sessionToGo.getOwnedViews()) {
            for (final Iterator<DRepresentationDescriptor> iterator = new DViewQuery(dView).getLoadedRepresentationsDescriptors().iterator(); iterator.hasNext();) {
                DRepresentationDescriptor representationDescriptor = iterator.next();
                final DSemanticDiagram rep = (DSemanticDiagram) representationDescriptor.getRepresentation();
                if (name.equals(representationDescriptor.getName())) {
                    return rep;
                }
            }
        }
        return null;
    }
}
