/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.convert;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.internal.actions.nature.ModelingToggleNatureAction;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.ISources;

import com.google.common.collect.Lists;

/**
 * Test convert Project to Modeling Project and test remove Modeling Project
 * nature.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class ConvertProjectToModelingProjectTest extends SiriusTestCase {

    private static final String DATA_UNIT_PROJECT_MODELING = "data/unit/project/modeling/";

    private static final String REPRESENTATIONS2_AIRD = "representations2.aird";

    private static final String MODELING_PROJECT_NATURE = "Modeling project";

    private static final String[] NON_LOADABLE_FILES = { ".checkstyle", ".classpath", ".options", ".project", "about.ini", "build.xml", "classpath.xml", "generate.mtl", "Glossary.html", "image.svg",
            "MANIFEST.MF", "plugin.xml", "pom.xml", "project.xml", "resourcelocator.exsd", "test.docx", "unknownMM.ummfortest", "web.xml", "representations.aird.old", "vsm.odesign.old",
            "vsm.odesign" };

    @Override
    protected void setUp() throws Exception {
        super.createModelingProject = false;
        super.setUp();
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
    }

    /**
     * Test convert/Remove modeling project nature.
     * 
     * @throws CoreException
     */
    public void testConvertAndRemoveProjectToModelingProject() {
        // Create a project with no Modeling project nature
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        assertNotNull("The project should not be null", project);

        IFile representationFile = createRepresentationFile(project);
        // check representation file is created
        checkRepresentationFileExists(project);

        // create convert action
        createAndExecuteConvertAction(project);

        // check project is a Modeling project
        checkProjectNatureIsModeling(project);

        // check representation file is created1
        checkRepresentationFileExists(project);
        checkModelingProject(project, representationFile, 0);

        // Remove project Modeling nature
        createAndExecuteConvertAction(project);

        // check project is not a Modeling project
        checkProjectNatureIsNotModeling(project);

    }

    /**
     * Test convert/Remove modeling project nature.
     * 
     * @throws CoreException
     */
    public void testConverProjectToModelingProjectWithExistingResourcesAndAird() {
        // Create a project with no Modeling project nature
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        assertNotNull("The project should not be null", project);

        // create ecore file
        createEcoreFile(project, "test.ecore");

        // Create an interaction file.
        createInteractionFile(project, "test3.interactions");

        // create folder
        IFolder folder = createFolder(project);

        // create second ecore file
        createEcoreFileInFolder(folder, "test2.ecore");

        // create empty folder
        createFolder(project, "emptyFolder");

        // create representation file
        IFile representationFile = createRepresentationFile(project);
        // check representation file is created
        checkRepresentationFileExists(project);

        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(representationFile.getFullPath().toOSString(), true), new NullProgressMonitor());
        session.save(new NullProgressMonitor());

        // add semantic resource to session

        // create convert action
        createAndExecuteConvertAction(project);

        // check project is a Modeling project
        checkProjectNatureIsModeling(project);

        // check representation file is created
        checkRepresentationFileExists(project);
        checkFileExists(project, "test.ecore");
        checkFileExists(folder, "test2.ecore");
        checkFileExists(project, "test3.interactions");

        checkModelingProject(project, representationFile, 3);
    }

    /**
     * Test convert/Remove modeling project nature. Launch the conversion on
     * project with non-loadable files and files we do not want to load (files
     * in .svn folder, xx.aird.old, xx.odesign.old, ...).
     * 
     * @throws CoreException
     */
    public void testConverProjectToModelingProjectWithNonLoadableResources() {
        // Create a project with no Modeling project nature
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        assertNotNull("The project should not be null", project);

        // add resources to the project
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_PROJECT_MODELING, NON_LOADABLE_FILES);

        // Add a .svn folder and an ecore file in it.
        String ecoreFile = "ModelInSvnFolder.ecore";
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_PROJECT_MODELING + ecoreFile, TEMPORARY_PROJECT_NAME + "/.svn/" + ecoreFile);

        // create convert action
        createAndExecuteConvertAction(project);

        // check project is a Modeling project
        checkProjectNatureIsModeling(project);

        // check representation file is created
        IFile representationFile = checkRepresentationFileExists(project);
        // checkFileExists(project, "test.ecore");
        // checkFileExists(folder, "test2.ecore");

        // check modeling project is loaded
        checkModelingProject(project, representationFile, 0);
    }

    private IFile createRepresentationFile(IProject project) {
        try {
            ModelingProjectManager.INSTANCE.createLocalRepresentationsFile(project, new NullProgressMonitor());
        } catch (CoreException e) {
            fail(e.getMessage());
        }
        IFile representationFile = project.getFile(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME);
        checkRepresentationsFileDoesNotNeedMigration(representationFile);
        return representationFile;
    }

    private void checkRepresentationsFileDoesNotNeedMigration(IFile representationFile) {
        // Check that the created aird does not need migration (version tag
        // must be initialized)
        checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(representationFile.getFullPath().toOSString(), true), false);
    }

    private IFolder createFolder(IProject project) {
        return createFolder(project, "Folder");
    }

    private IFolder createFolder(IProject project, String folderName) {
        IFolder folder = project.getFolder(folderName);
        try {
            folder.create(true, true, new NullProgressMonitor());
        } catch (CoreException e2) {
            fail(e2.getMessage());
        }
        return folder;
    }

    private void createEcoreFile(IProject project, String fileName) {
        // create ecore file
        ResourceSet rs = new ResourceSetImpl();
        Resource resource = rs.createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + fileName, true));
        resource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());
        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (IOException e1) {
            fail(e1.getMessage());
        }
        checkFileExists(project, fileName);
    }

    private void createInteractionFile(IProject project, String fileName) {
        // create ecore file
        ResourceSet rs = new ResourceSetImpl();
        Resource resource = rs.createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + fileName, true));
        resource.getContents().add(InteractionsFactory.eINSTANCE.createModel());
        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (IOException e1) {
            fail(e1.getMessage());
        }
        checkFileExists(project, fileName);
    }

    private void createEcoreFileInFolder(IFolder folder, String fileName) {
        ResourceSet rs = new ResourceSetImpl();
        Resource resource = rs.createResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/Folder/" + fileName, true));
        resource.getContents().add(EcoreFactory.eINSTANCE.createEPackage());
        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (IOException e1) {
            fail(e1.getMessage());
        }
        checkFileExists(folder, fileName);
    }

    /**
     * Convert project without representation file
     */
    public void testConvertToModelingProjectWithoutRepresentationFileNorSemanticResources() {
        // Create a project with no Modeling project nature
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        assertNotNull("The project should not be null", project);
        IFile representationFile = project.getFile(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME);
        assertFalse("The representation file " + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME + " should not exist", representationFile.exists());

        // create convert action
        createAndExecuteConvertAction(project);

        // check project is a Modeling project
        checkProjectNatureIsModeling(project);

        // check representation file is created
        representationFile = checkRepresentationFileExists(project);
        checkRepresentationsFileDoesNotNeedMigration(representationFile);
        checkModelingProject(project, representationFile, 0);
    }

    /**
     * Test convert/Remove modeling project nature.
     * 
     * @throws CoreException
     */
    public void testConvertToModelingProjectWithoutRepresentationFileButSemanticResources() {
        // Create a project with no Modeling project nature
        // Create a project with no Modeling project nature
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        assertNotNull("The project should not be null", project);
        IFile representationFile = project.getFile(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME);
        assertFalse("The representation file " + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME + " should not exist", representationFile.exists());

        // create ecore file
        createEcoreFile(project, "test.ecore");

        // Create an interaction file.
        createInteractionFile(project, "test3.interactions");

        // create folder
        IFolder folder = createFolder(project);

        // create second ecore file
        createEcoreFileInFolder(folder, "test2.ecore");

        // create empty folder
        createFolder(project, "emptyFolder");

        // create convert action
        createAndExecuteConvertAction(project);

        // check project is a Modeling project
        checkProjectNatureIsModeling(project);

        // check representation file is created
        representationFile = checkRepresentationFileExists(project);
        checkFileExists(project, "test.ecore");
        checkFileExists(folder, "test2.ecore");
        checkFileExists(project, "test3.interactions");

        checkModelingProject(project, representationFile, 3);
    }

    private void checkModelingProject(IProject project, IFile representationFile, int expectedSemanticResources) {
        Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
        assertTrue(modelingProject.some());
        URI representationFileURI = URI.createPlatformResourceURI(representationFile.getFullPath().toOSString(), true);
        assertEquals(representationFileURI, modelingProject.get().getMainRepresentationsFileURI(new NullProgressMonitor()).get());

        Session modelingSession = modelingProject.get().getSession();
        assertNotNull("The modeling project should be loaded.", modelingSession);
        assertTrue("the session should be opened.", modelingSession.isOpen());
        assertEquals(modelingSession, SessionManager.INSTANCE.getSession(representationFileURI, new NullProgressMonitor()));

        // check the semantic resources
        assertEquals("The session should contain " + expectedSemanticResources + " semantic resource.", expectedSemanticResources, modelingSession.getSemanticResources().size());
        assertFalse("The session resource should not be empty.", modelingSession.getSessionResource().getContents().isEmpty());
        assertTrue("The session resource should not be empty.", modelingSession.getSessionResource().getContents().get(0) instanceof DAnalysis);
        DAnalysis mainDAnalysis = (DAnalysis) modelingSession.getSessionResource().getContents().get(0);
        assertEquals("The DAnalysis should contain " + expectedSemanticResources + " models.", expectedSemanticResources, mainDAnalysis.getModels().size());

        // check there are 1 session resource
        assertEquals("The session should contains one session resource.", 1, modelingSession.getAllSessionResources().size());
    }

    private IFile checkRepresentationFileExists(IProject project) {
        IFile representationFile = project.getFile(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME);
        assertNotNull("The representation file " + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME + " should exists", representationFile);
        try {
            representationFile.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
        } catch (CoreException e) {
            fail("Problem during refresh of the " + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME + ": " + e.getMessage());
        }
        try {
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        } catch (OperationCanceledException e) {
            fail("Fail during waiting of ResourceSyncClientNotifier job: " + e.getMessage());
        } catch (InterruptedException e) {
            fail("Fail during waiting of ResourceSyncClientNotifier job: " + e.getMessage());
        }
        assertTrue("The representation file " + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME + " should exists", representationFile.exists());
        return representationFile;
    }

    private IFile checkFileExists(IProject project, String name) {
        IFile representationFile = project.getFile(name);
        assertNotNull("The file " + name + " should exists", representationFile);
        assertTrue("The file " + name + " should exists", representationFile.exists());
        return representationFile;
    }

    private IFile checkFileExists(IFolder project, String name) {
        IFile representationFile = project.getFile(name);
        assertNotNull("The file " + name + " should exists", representationFile);
        assertTrue("The file " + name + " should exists", representationFile.exists());
        return representationFile;
    }

    /**
     * Convert project without representation file
     */
    public void testConvertToModelingProjectWith2RepresentationFile() {
        // Create a project with no Modeling project nature
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        assertNotNull("The project should not be null", project);
        try {
            ModelingProjectManager.INSTANCE.createLocalRepresentationsFile(project, new NullProgressMonitor());
            IFile representationFile = project.getFile(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME);
            EclipseTestsSupportHelper.INSTANCE.copyFile(TEMPORARY_PROJECT_NAME + "/" + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS2_AIRD);
            assertTrue("The representation file " + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME + " should exist", representationFile.exists());
            IFile representationFile2 = project.getFile(REPRESENTATIONS2_AIRD);

            assertTrue("The representation file " + REPRESENTATIONS2_AIRD + " should exist", representationFile2.exists());
        } catch (CoreException e) {
            fail(e.getMessage());
        }

        // create convert action
        createAndExecuteConvertAction(project);

        // check project is a Modeling project
        checkProjectNatureIsNotModeling(project);
    }

    private void checkProjectNatureIsNotModeling(IProject project) {
        try {
            IProjectNature nature;
            nature = project.getNature(ModelingProject.NATURE_ID);
            assertEquals("The project should not be a " + MODELING_PROJECT_NATURE, null, nature);
        } catch (CoreException e) {
            fail(e.getMessage());
        }
    }

    private void checkProjectNatureIsModeling(IProject project) {
        try {
            IProjectNature nature = project.getNature(ModelingProject.NATURE_ID);
            assertEquals("The project must be a " + MODELING_PROJECT_NATURE, ModelingProject.class, nature.getClass());
        } catch (CoreException e) {
            fail(e.getMessage());
        }
    }

    private void createAndExecuteConvertAction(IProject project) {
        try {
            ModelingToggleNatureAction toogleProject = new ModelingToggleNatureAction();
            EvaluationContext evaluationContext = new EvaluationContext(null, Lists.newArrayList(project));
            evaluationContext.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, new StructuredSelection(project));
            @SuppressWarnings("rawtypes")
            ExecutionEvent event = new ExecutionEvent(null, new HashMap(), null, evaluationContext);
            toogleProject.execute(event);
            project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
            Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
