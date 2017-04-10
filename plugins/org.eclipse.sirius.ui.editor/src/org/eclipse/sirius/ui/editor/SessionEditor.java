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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.editor.internal.pages.DefaultSessionEditorPage;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

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
public class SessionEditor extends SharedHeaderFormEditor implements ITabbedPropertySheetPageContributor, IModelExplorerView, SessionListener {
    /**
     * The editor's id.
     */
    public static final String EDITOR_ID = "org.eclipse.sirius.ui.editor.session"; //$NON-NLS-1$

    /**
     * Session opened with editor.
     */
    private Session session;

    private DefaultSessionEditorPage defaultPage;

    @Override
    protected void addPages() {
        try {
            defaultPage = new DefaultSessionEditorPage(this, session);
            addPage(0, defaultPage);
        } catch (PartInitException e) {
            ErrorDialog.openError(getSite().getShell(), MessageFormat.format(Messages.UI_SessionEditor_page_loading_error_message, new Object[0]), e.getMessage(), e.getStatus()); // $NON-NLS-1$
        }
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);

        IEditorInput editorInput = this.getEditorInput();
        URI sessionResourceURI = null;
        if (editorInput instanceof FileEditorInput) {
            // provided when opened from a no modeling project.
            IFile sessionResourceFile = ((FileEditorInput) editorInput).getFile();
            sessionResourceURI = URI.createPlatformResourceURI(sessionResourceFile.getFullPath().toOSString(), true);
        } else if (editorInput instanceof URIEditorInput) {
            // provided when opened from a modeling project.
            sessionResourceURI = ((URIEditorInput) editorInput).getURI();
        } else {
            ErrorDialog.openError(getSite().getShell(), MessageFormat.format(Messages.UI_SessionEditor_session_loading_error_message, new Object[0]),
                    MessageFormat.format(Messages.UI_SessionEditor_inputNotHandled_error_message, editorInput.getClass().getSimpleName()), Status.CANCEL_STATUS);
        }

        try {
            if (sessionResourceURI != null) {
                final URI sessionResourceURIFinal = sessionResourceURI;

                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(sessionResourceURIFinal.toPlatformString(true)));
                IProject project = file.getProject();
                if (ModelingProject.hasModelingProjectNature(project)) {
                    // we show the modeling project name
                    setPartName(sessionResourceURIFinal.segments()[1]);
                } else {
                    // we show as editor name the aird name and as description
                    // the aird full path relatively to the project.
                    setPartName(sessionResourceURIFinal.lastSegment());
                    setContentDescription(sessionResourceURIFinal.toPlatformString(true));
                }

                // until we find a way to load session independently from the
                // editor, session loading blocks the editor opening with a
                // progress monitor.
                PlatformUI.getWorkbench().getProgressService().busyCursorWhile((monitor) -> {

                    SubMonitor subMonitor = SubMonitor.convert(monitor, 1);
                    subMonitor.beginTask(MessageFormat.format(Messages.UI_SessionEditor_session_loading_task_title, new Object[0]), 1);
                    session = SessionManager.INSTANCE.getSession(sessionResourceURIFinal, subMonitor);
                    if (!session.isOpen()) {
                        session.open(monitor);
                    }
                    session.addListener(this);

                    subMonitor.worked(1);
                    subMonitor.done();
                });
            }
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
        if (pages != null) {
            for (int i = 0; i < pages.size(); i++) {
                Object page = pages.get(i);
                if (page instanceof IFormPage) {
                    ((IFormPage) page).doSave(monitor);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAdapter(Class type) {
        Object result = null;
        if (type == IPropertySheetPage.class) {
            IPropertySheetPage contributedPage = SiriusEditPlugin.getPlugin().getPropertySheetPage(this, getContributorId());
            if (contributedPage != null) {
                result = contributedPage;
            } else {
                result = new TabbedPropertySheetPage(this);
            }
        }
        if (result == null) {
            result = super.getAdapter(type);
        }
        return result;
    }

    @Override
    public void doSaveAs() {
        // not used
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public String getContributorId() {
        return ID;
    }

    @Override
    public void notify(int changeKind) {
        switch (changeKind) {
        case SessionListener.CLOSING:
            this.close(false);
            break;
        default:
            break;
        }
    }
}
