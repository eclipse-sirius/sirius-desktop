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
package org.eclipse.sirius.tests.unit.api.convert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.ui.tools.internal.actions.nature.ModelingToggleNatureAction;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.ISources;

import com.google.common.collect.Lists;

/**
 * Converts old Viewpoint Modeling Project to Sirius Modeling Project:
 * <UL>
 * <LI>nature,</LI>
 * <LI>automatic migration of representations file,</LI>
 * <LI>automatic migration of VSM file.</LI>
 * </UL>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ConvertViewpointModelingProjectToSiriusModelingProjectTest extends SiriusTestCase {
    /** The old Viewpoint nature id. */
    private static final String VIEWPOINT_MODELING_PROJECT_NATURE_ID = "fr.obeo.dsl.viewpoint.nature.modelingproject";

    private static final String PROJECT_NAME = "my.project.sample";

    private static final String DATA_UNIT_PATH = "data/unit/migration/do_not_migrate/FromVP6.8To/" + PROJECT_NAME;

    private static final String PROJECT_DESCRIPTION_FILE = ".project";

    private static final String REPRESENTATIONS_FILE = "representations.aird";

    private static final String VSM_FILE_NAME = "My.odesign";

    private static final String SEMANTIC_FILE_NAME = "My.ecore";

    @Override
    protected void setUp() throws Exception {
        super.createModelingProject = false;
        super.setUp();
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
    }

    public void testConvertOldViewpointModelingProject() {
        // Copy the project and its files into the folder of the workspace
        final File sourceProjectFolder = FileProvider.getDefault().getFile(SiriusTestsPlugin.PLUGIN_ID, new Path(DATA_UNIT_PATH));
        IPath targetProjectPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(PROJECT_NAME);
        final File targetProjectFolder = new File(targetProjectPath.toOSString());
        if (!targetProjectFolder.exists()) {
            assertTrue("Problem during creation of the folder corresponding to the project.", targetProjectFolder.mkdirs());
        }
        String sourceProjectPath = sourceProjectFolder.getAbsolutePath() + File.separator;
        try {
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + PROJECT_DESCRIPTION_FILE), new File(targetProjectPath.append(PROJECT_DESCRIPTION_FILE).toOSString()));
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + SEMANTIC_FILE_NAME), new File(targetProjectPath.append(SEMANTIC_FILE_NAME).toOSString()));
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + VSM_FILE_NAME), new File(targetProjectPath.append(VSM_FILE_NAME).toOSString()));
            EclipseTestsSupportHelper.INSTANCE.copyFile(new File(sourceProjectPath + REPRESENTATIONS_FILE), new File(targetProjectPath.append(REPRESENTATIONS_FILE).toOSString()));
        } catch (IOException e1) {
            fail("Problem during copy of data files: " + e1.getMessage());
        }

        // Import the existing project into the workspace
        IProject project = null;
        try {
            IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(targetProjectPath.append(PROJECT_DESCRIPTION_FILE)); //$NON-NLS-1$ 
            project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
            project.create(description, null);
            project.open(null);
        } catch (CoreException e) {
            fail("Import of the old Viewpoint modeling project fails: " + e.getMessage());
        }
        // Convert the old Viewpoint modeling project
        createAndExecuteConvertAction(project);

        // Check project is a Modeling project
        checkProjectNatureIsModeling(project);

        // Check that old nature is removed
        try {
            assertFalse("The old Viewpoint nature should ne removed.", project.getDescription().hasNature(VIEWPOINT_MODELING_PROJECT_NATURE_ID));
        } catch (CoreException e) {
            fail("Problem detected during testing existence of old Viewpoint nature: " + e.getMessage());
        }

        // check modeling project is loaded
        Session session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(project.getFullPath().append(REPRESENTATIONS_FILE).toOSString(), true), new NullProgressMonitor());
        assertNotNull("The modeling project should be loaded.", session);
        assertTrue("The session should be opened.", session.isOpen());

        // Check AIRD contents (should be migrated, so root should be DAnalysis)
        assertTrue("The root of the representations file should be a DAnalysis", session.getSessionResource().getContents().get(0) instanceof DAnalysis);
        DAnalysis mainDAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);

        // Check VSM contents (should be migrated, so root should be Group)
        assertTrue("The root of the VSM file should be a Group", mainDAnalysis.getOwnedViews().get(0).getViewpoint().eResource().getContents().get(0) instanceof Group);
    }

    private void checkProjectNatureIsModeling(IProject project) {
        try {
            IProjectNature nature = project.getNature(ModelingProject.NATURE_ID);
            assertEquals("The project must have a Modeling Project nature.", ModelingProject.class, nature.getClass());
        } catch (CoreException e) {
            fail("Problem during testing nature: " + e.getMessage());
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
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
