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
package org.eclipse.sirius.tests.unit.api.modelingproject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.internal.actions.creation.CreateRepresentationAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;

/**
 * Test about automatic session saving when no editor is opened.
 * 
 * @author mariot
 */
public class SaveWhenNoEditorsTests extends SiriusDiagramTestCase implements EcoreModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changePlatformUIPreference(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);

        TestsUtil.emptyEventsFromUIThread();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_SEMANTIC_MODEL_PROJECT_RELATIVE_PATH, TEMPORARY_PROJECT_NAME + "/" + TEST_SEMANTIC_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + TEST_SEMANTIC_MODEL_FILENAME, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    public void testRenameWithoutEditors() throws Exception {
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        rename(diagram);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    public void testRenameWithEditor() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.synchronizationWithUIThread();

        rename(diagram);
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.DIRTY, session.getStatus());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testRenameProjectWithoutEditor() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        TestsUtil.synchronizationWithUIThread();
        renameProject(diagram);
    }

    public void testRenameProjectWithEditor() throws Exception {
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        renameProject(diagram);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testOpenEditor() throws Exception {
        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        CreateRepresentationAction action = new CreateRepresentationAction(session, semanticModel, diagram.getDescription(), new LabelProvider()) {

            @Override
            protected String getRepresentationName() {
                return " a new representation for test";
            }
        };
        action.run();
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.DIRTY, session.getStatus());
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        SessionUIManager.INSTANCE.getUISession(session).close();
        TestsUtil.synchronizationWithUIThread();
    }

    private void renameProject(DDiagram diagram) throws Exception {
        IFile diagramFile = WorkspaceSynchronizer.getFile(diagram.eResource());
        IProject project = diagramFile.getProject();
        IPath path = project.getParent().getFullPath().append("newProjectName");
        project.move(path, true, new NullProgressMonitor());
        Thread.sleep(2000);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        assertFalse(session.isOpen());
        assertEquals(0, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences().length);
    }

    private void rename(final DRepresentation representation) throws Exception {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command setNamCmd = SetCommand.create(domain, representation, ViewpointPackage.Literals.DREPRESENTATION__NAME, "new name");
        domain.getCommandStack().execute(setNamCmd);
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
    }

}
