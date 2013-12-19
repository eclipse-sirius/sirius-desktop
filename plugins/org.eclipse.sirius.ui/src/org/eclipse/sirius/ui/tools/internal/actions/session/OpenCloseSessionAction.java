/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.session;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Open a session and wanted diagram when double click on aird files.
 * 
 * @author mporhel
 */
public class OpenCloseSessionAction extends SelectionListenerAction {

    private boolean open = true;

    private final String openText;

    private final String closeText;

    private final SelectionListenerAction openSessionAction;

    private final SelectionListenerAction closeSessionAction;

    /**
     * Constructor.
     * 
     * @param openText
     *            This text is displayed in the contextual menu in open mode.
     * @param closeText
     *            This text is displayed in the contextual menu in close mode.
     */
    public OpenCloseSessionAction(final String openText, String closeText) {
        super(openText);
        this.openText = openText;
        this.closeText = closeText;
        this.openSessionAction = new OpenSessionAction(openText);
        this.closeSessionAction = new CloseSessionsAction(closeText);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
     */
    @Override
    protected boolean updateSelection(final IStructuredSelection selection) {
        List<IFile> selectedFiles = getSelectedFiles();

        if (selectedFiles == null || selectedFiles.size() != 1 || ModelingProject.hasModelingProjectNature(selectedFiles.iterator().next().getProject())) {
            return false;
        }

        List<Session> openedSession = getSelectedOpenedSessions(selectedFiles);

        openSessionAction.selectionChanged(new StructuredSelection(selectedFiles));
        closeSessionAction.selectionChanged(new StructuredSelection(openedSession));

        open = true;
        setText(openText);
        if (!openedSession.isEmpty()) {
            open = false;
            setText(closeText);
        }

        return super.updateSelection(selection) && selectionIsOfType(IResource.FILE);
    }

    /**
     * {@inheritDoc}
     * 
     * @overrides
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        if (open) {
            openSessionAction.run();
        } else {
            closeSessionAction.run();
        }
    }

    private List<IFile> getSelectedFiles() {
        return Lists.newArrayList(Iterables.filter(getSelectedResources(), IFile.class));
    }

    private List<Session> getSelectedOpenedSessions(final Collection<IFile> selectedFiles) {
        final Set<URI> selectedUris = Sets.newHashSet();
        // Look for opened session. Do not expose open actionfor already opened
        // session.
        for (IFile selectedFile : selectedFiles) {
            if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(selectedFile.getFileExtension())) {
                selectedUris.add(URI.createPlatformResourceURI(selectedFile.getFullPath().toString(), true));
            }
        }

        final Collection<Session> openedSession = Sets.newHashSet();
        for (Session session : SessionManager.INSTANCE.getSessions()) {
            if (session.isOpen()) {
                for (Resource res : session.getAllSessionResources()) {
                    if (selectedUris.contains(res.getURI())) {
                        openedSession.add(session);
                    }
                }
            }
        }
        return Lists.newArrayList(openedSession);
    }

    /**
     * Returns the mode : open or close.
     * 
     * @return the mode : open or close.
     */
    public boolean isOpenMode() {
        return open;
    }
}
