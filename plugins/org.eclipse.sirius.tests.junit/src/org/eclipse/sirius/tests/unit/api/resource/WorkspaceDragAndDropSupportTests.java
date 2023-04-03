/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.resource;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.resource.support.WorkspaceDragAndDropSupport;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.junit.Assert;

/**
 * Tests on workspace drag and drop support based on entities diagram of ecore
 * modeler.
 * 
 * @author mchauvin
 */
public class WorkspaceDragAndDropSupportTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String FILE_NAME = "test.txt";

    private static final String FOLDER_NAME = "folder";

    private static final String PROJECT_NAME = "workspacetest";

    private WorkspaceDragAndDropSupport support;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
        support = new WorkspaceDragAndDropSupport();
    }

    public void testCreateModelFile() throws Exception {

        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        final IFile modelFile = createModelFile(ePackage);

        final EObject converted = support.convert(modelFile, session);
        Assert.assertTrue(converted instanceof DModel);
    }

    private IFile createModelFile(final EPackage ePackage) throws Exception {
        /* create resource and add it initialized model */
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        final IPath filePath = project.getFullPath().append("test.ecore");

        final URI fileUri = URI.createPlatformResourceURI(filePath.toString(), true);
        final Resource rs = session.getTransactionalEditingDomain().getResourceSet().createResource(fileUri);

        /* initialize the model */
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                rs.getContents().add(ePackage);
            }

        });
        /* save it */
        rs.save(new HashMap<>());
        return WorkspaceSynchronizer.getFile(rs);
    }

    public void testCreateDFile() throws Exception {
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        IFile file = createFile(project, FILE_NAME);
        /* drop several times to tests exception in loading */
        for (int i = 0; i < 10; i++) {
            int beforeDrop = session.getSemanticResources().size();
            EObject result = support.convert(file, session);
            int afterDrop = session.getSemanticResources().size();
            assertTrue(result instanceof DFile);
            assertEquals(FILE_NAME, ((DFile) result).getName());
            assertEquals(beforeDrop, afterDrop);
        }
        file.delete(true, new NullProgressMonitor());
    }

    public void testCreateDProject() throws Exception {
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        EObject result = support.convert(project, session);
        assertTrue(result instanceof DProject);
        assertEquals(PROJECT_NAME, ((DProject) result).getName());
    }

    public void testCreateDFolder() throws Exception {
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        IFolder folder = createFolder(project, FOLDER_NAME);
        EObject result = support.convert(folder, session);
        assertTrue(result instanceof DFolder);
        assertEquals(FOLDER_NAME, ((DFolder) result).getName());
        folder.delete(true, new NullProgressMonitor());
    }

    public void testRetrieveSessionFromProject() throws Exception {
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        EObject result = support.convert(project, session);
        assertRetrieveSession(result);
    }

    public void testRetrieveSessionFromFolder() throws Exception {
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        IFolder folder = createFolder(project, FOLDER_NAME);
        EObject result = support.convert(folder, session);
        assertRetrieveSession(result);
        folder.delete(true, new NullProgressMonitor());
    }

    public void testRetrieveSessionFromModelFile() throws Exception {
        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        final IFile modelFile = createModelFile(ePackage);
        EObject result = support.convert(modelFile, session);
        assertRetrieveSession(result);
    }

    public void testRetrieveSessionFromNonModelFile() throws Exception {
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        IFile file = createFile(project, FILE_NAME);
        EObject result = support.convert(file, session);
        assertRetrieveSession(result);
        file.delete(true, new NullProgressMonitor());
    }

    public void testRetrieveWorspaceFileFromModelFile() throws Exception {
        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        final IFile modelFile = createModelFile(ePackage);
        EObject result = support.convert(modelFile, session);
        assertEquals(modelFile, support.getWorkspaceFile((DFile) result));
        modelFile.delete(true, new NullProgressMonitor());
    }

    public void testRetrieveWorspaceFileFromNonModelFile() throws Exception {
        IProject project = EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT_NAME);
        IFile file = createFile(project, FILE_NAME);
        EObject result = support.convert(file, session);
        assertEquals(file, support.getWorkspaceFile((DFile) result));
        file.delete(true, new NullProgressMonitor());
    }

    public void testCreateDResourceHierarchy() throws Exception {
        IProject project = createProject(PROJECT_NAME);
        createFile(createFolder(project, FOLDER_NAME), FILE_NAME);
        EObject result = support.convert(project, session);
        assertTrue(result instanceof DResourceContainer);
        /* it should create the .project file and the folder */
        assertEquals(3, ((DResourceContainer) result).getMembers().size());
        assertEquals(1, ((DResourceContainer) ((DResourceContainer) result).getMembers().get(2)).getMembers().size());
    }

    private void assertRetrieveSession(final EObject eObject) throws Exception {
        assertEquals(session, SessionManager.INSTANCE.getSession(eObject));
    }

    private IProject createProject(String name) throws Exception {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IWorkspaceRoot root = workspace.getRoot();
        final IProject project = root.getProject(name);
        project.create(null);
        project.open(null);
        return project;
    }

    private IFolder createFolder(IContainer container, String name) throws Exception {
        IFolder folder = container.getFolder(new Path(name));
        folder.create(true, true, null);
        return folder;
    }

    private IFile createFile(IContainer container, String name) throws Exception {
        IFile file = container.getFile(new Path(name));
        byte[] content = new byte[2];
        content[0] = 1;
        content[0] = 2;
        file.create(new ByteArrayInputStream(content), true, null);
        return file;
    }

    @Override
    protected void tearDown() throws Exception {
        support = null;
        super.tearDown();
    }

}
