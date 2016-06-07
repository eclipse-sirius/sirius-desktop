/*******************************************************************************
 * Copyright (c) 2015, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.filter;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorPart;

/**
 * Add specific test to reveal a potential problem about the listeners on the
 * diagram. This test is inspired from a Capella use case and the command is
 * similar to
 * {@link org.polarsys.capella.core.platform.sirius.ui.commands.DeleteRepresentationCommand}
 * structure.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DiagramListenersTests extends SiriusDiagramTestCase {

    String TEST_SEMANTIC_MODEL_FILENAME = "My.ecore"; //$NON-NLS-1$

    String TEST_MODELS_PROJECT_RELATIVE_PATH = "/data/unit/filter/mappingFilters/"; //$NON-NLS-1$

    String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_MODELS_PROJECT_RELATIVE_PATH + TEST_SEMANTIC_MODEL_FILENAME; //$NON-NLS-1$

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_MODELS_PROJECT_RELATIVE_PATH + "My.odesign"; //$NON-NLS-1$

    String REPRESENTATIONS_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_MODELS_PROJECT_RELATIVE_PATH + "representations.aird"; //$NON-NLS-1$

    boolean errorCatchActiveOldState;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_MODEL_PATH);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    public void testDeleteElementsAndCheckErrorLog() throws Exception {
        final DRepresentationDescriptor repDescriptor = (DRepresentationDescriptor) getRepresentationDescriptors("Diagram").toArray()[0];
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, repDescriptor.getRepresentation(), new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        errorCatchActiveOldState = isErrorCatchActive();
        setErrorCatchActive(true);
        try {

            final EObject semanticElement = repDescriptor.getTarget();

            // Execute a command that delete a semantic element after closing
            // corresponding editors and deleting corresponding representations.
            final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            domain.getCommandStack().execute(new AbstractCommand("Delete representation and close it") {
                CompoundCommand realCommand;

                @Override
                protected boolean prepare() {
                    realCommand = new CompoundCommand();
                    realCommand.append(new RecordingCommand(domain, "Delete representation and close it", null) {
                        @Override
                        protected void doExecute() {
                            closeActiveRepresentationEditor(repDescriptor.getRepresentation(), session);
                            // Delete the current representation.
                            if (DialectManager.INSTANCE.deleteRepresentation(repDescriptor, session)) {
                                // Notify changes.
                                SessionManager.INSTANCE.notifyRepresentationDeleted(session);
                            }
                        }
                    });
                    Command removeEPackageCmd = RemoveCommand.create(domain, semanticElement.eContainer(), EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, semanticElement);
                    realCommand.append(removeEPackageCmd);
                    return true;
                }

                @Override
                public void redo() {
                }

                @Override
                public void execute() {
                    realCommand.execute();
                }
            });
            TestsUtil.synchronizationWithUIThread();

            // Check if there is problem about "not found interpreter" in error
            // log
            if (doesAnErrorOccurs()) {
                String errors = getErrorLoggersMessage();
                if (errors.contains("Impossible to find an interpreter")) {
                    fail("This test logs error of kind \"Impossible to find an interpreter\": " + errors);
                }
            }
        } finally {
            setErrorCatchActive(errorCatchActiveOldState);
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    // Closes the opened editor about the current representation.
    private void closeActiveRepresentationEditor(DRepresentation representation_p, Session session_p) {
        IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session_p);
        if (null == editingSession) {
            return;
        }
        DialectEditor editor = editingSession.getEditor(representation_p);
        if (editor != null) {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            // We detach the editor here because sometimes cause of the
            // asyncExec of the closing, the detach could not be done during the
            // dispose of the editor
            editingSession.detachEditor(editor);
        }
    }
}
