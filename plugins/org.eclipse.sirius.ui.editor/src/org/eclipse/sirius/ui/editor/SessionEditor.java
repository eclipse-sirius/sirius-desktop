/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Editor use to open Session file (.aird files) available from everywhere. This
 * editor shows different properties/data about session that can be modified :
 * <ul>
 * <li>Viewpoint selection
 * <li>Semantic model browsing and management
 * <li>Representation browsing and management.
 * </ul>
 * 
 * @author jmallet
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SessionEditor extends SharedHeaderFormEditor {

    /**
     * Session opened with editor.
     */
    private Session session;

    @Override
    protected void addPages() {
        try {
            IEditorInput editorInput = this.getEditorInput();
            if (editorInput instanceof FileEditorInput) {
                addPage(0, new DefaultSessionEditorPage(this, session));
            }
        } catch (PartInitException e) {
            ErrorDialog.openError(getSite().getShell(), MessageFormat.format(Messages.UI_SessionEditor_page_loading_error_message, new Object[0]), e.getMessage(), e.getStatus()); // $NON-NLS-1$
        }
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);

        IEditorInput editorInput = this.getEditorInput();
        IFile sessionResourceFile = ((FileEditorInput) editorInput).getFile();
        final URI sessionResourceURI = URI.createPlatformResourceURI(sessionResourceFile.getFullPath().toOSString(), true);
        try {
            // until we find a way to load session independently from the
            // editor, session loading blocks the editor opening with a progress
            // monitor.
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    SubMonitor subMonitor = SubMonitor.convert(monitor, 1);
                    subMonitor.beginTask(MessageFormat.format(Messages.UI_SessionEditor_session_loading_task_title, new Object[0]), 1);
                    session = SessionManager.INSTANCE.getSession(sessionResourceURI, subMonitor);
                    if (!session.isOpen()) {
                        session.open(monitor);
                    }
                    subMonitor.worked(1);
                    subMonitor.done();
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            ErrorDialog.openError(getSite().getShell(), MessageFormat.format(Messages.UI_SessionEditor_session_loading_error_message, new Object[0]), e.getMessage(), Status.CANCEL_STATUS); // $NON-NLS-1$
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        session = null;
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void doSaveAs() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isSaveAsAllowed() {
        // TODO Auto-generated method stub
        return false;
    }

}
