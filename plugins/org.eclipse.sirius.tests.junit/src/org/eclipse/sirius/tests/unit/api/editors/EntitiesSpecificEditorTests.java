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
package org.eclipse.sirius.tests.unit.api.editors;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentEditor;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.ui.tools.api.properties.PropertiesService;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tools.api.ui.property.IPropertiesProvider;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Test the transient Sirius Ecore editor.
 */
public class EntitiesSpecificEditorTests extends SiriusDiagramTestCase implements EcoreModeler {

    private IProject project;

    private IFile file;

    private IEditorPart editor;

    private static final String EntitiesID = "org.eclipse.sirius.sample.ecore.design.editor.entities";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        project = createProject("test");
        int offset = SiriusTestsPlugin.PLUGIN_ID.length() + 1;
        String workspaceFile = copyFileIntoProject(project.getName(), EcoreModeler.PACKAGES_SEMANTIC_MODEL_PATH.substring(offset));
        file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspaceFile));
    }

    public void testOpen() throws Exception {
        try {
            openEditor();
            assertNotNull(editor);
        } finally {
            closeEditor();
        }
    }

    public void testSessionisCreated() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
            junit.framework.AssertionFailedError: expected:<0> but was:<1>
            at junit.framework.Assert.fail(Assert.java:57)
            at junit.framework.Assert.failNotEquals(Assert.java:329)
            at junit.framework.Assert.assertEquals(Assert.java:78)
            at junit.framework.Assert.assertEquals(Assert.java:234)
            at junit.framework.Assert.assertEquals(Assert.java:241)
            at junit.framework.TestCase.assertEquals(TestCase.java:409)
            at org.eclipse.sirius.tests.unit.api.editors.EntitiesSpecificEditorTests.testSessionisCreated(EntitiesSpecificEditorTests.java:75)
            */
            return;
        }
        assertEquals(0, SessionManager.INSTANCE.getSessions().size());
        openEditor();
        try {
            assertEquals(1, SessionManager.INSTANCE.getSessions().size());
        } finally {
            closeEditor();
        }
        assertEquals(0, SessionManager.INSTANCE.getSessions().size());
    }

    public void testDiagramContentSucced() throws Exception {
        openEditor();
        try {
            DRepresentation representation = ((DialectEditor) editor).getRepresentation();
            PropertiesService.getInstance().getPropertiesProvider().getProperty(IPropertiesProvider.KEY_AUTO_REFRESH);
            assertEquals(5, representation.getOwnedRepresentationElements().size());
        } finally {
            closeEditor();
        }
    }

    public void testIsDirtyWhenDiagramResourceChange() throws Exception {
        openEditor();
        try {
            assertTrue(editor.isDirty());
            final DRepresentation representation = ((DialectEditor) editor).getRepresentation();
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(representation);
            Command changeDRepNameCmd = SetCommand.create(domain, new DRepresentationQuery(representation).getRepresentationDescriptor(), ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__NAME,
                    "plop");
            domain.getCommandStack().execute(changeDRepNameCmd);
            assertTrue(editor.isDirty());
        } finally {
            closeEditor();
        }
    }

    private void openEditor() throws Exception {
        final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        editor = page.openEditor(new FileEditorInput(file), EntitiesID);
        TestsUtil.synchronizationWithUIThread();
    }

    private void closeEditor() throws Exception {
        ((IDocumentEditor) editor).close(false);
        TestsUtil.synchronizationWithUIThread();
        editor = null;
    }

    private IProject createProject(final String projectName) throws Exception {
        try {

            final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
            if (!project.exists()) {
                final IProjectDescription desc = project.getWorkspace().newProjectDescription(projectName);
                project.create(desc, null);
                project.open(null);
            }
        } catch (final CoreException e) {
            fail(e.getMessage());
        }
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        assertTrue("The project " + projectName + " was not created correctly.", project.exists());
        return project;
    }

    private String copyFileIntoProject(final String projectName, final String filePath) throws Exception {
        final String workspaceFilePath = projectName + File.separator + new Path(filePath).lastSegment();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, filePath, workspaceFilePath);
        return workspaceFilePath;
    }
}
