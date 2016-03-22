/*******************************************************************************
 * Copyright (c) 2008, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.ui.actions.SelectionListenerAction;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Open a session and wanted diagram when double click on aird files.
 * 
 * @author mporhel
 */
public class OpenSessionAction extends SelectionListenerAction {

    private Collection<IFile> selectedFiles;

    /**
     * Constructor.
     * 
     * @param text
     *            label of the action.
     */
    public OpenSessionAction(String text) {
        super(text);
    }

    /**
     * Looks for selected {@link IFile}s to open sessions.
     * 
     * {@inheritDoc}
     */
    @Override
    protected boolean updateSelection(IStructuredSelection selection) {
        if (selection != null) {
            this.selectedFiles = Sets.newLinkedHashSet(Iterables.filter(selection.toList(), IFile.class));
        }

        return super.updateSelection(selection) && selectedFiles != null && !selectedFiles.isEmpty();
    }

    /**
     * {@inheritDoc}
     * 
     * @overrides
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        try {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_SESSION_ACTION_KEY);
            for (IFile selectedFile : selectedFiles) {
                if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(selectedFile.getFileExtension())) {
                    URI sessionResourceURI = URI.createPlatformResourceURI(selectedFile.getFullPath().toOSString(), true);
                    ModelingProjectManager.INSTANCE.loadAndOpenRepresentationsFile(sessionResourceURI, true);
                }
            }
        } finally {
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_SESSION_ACTION_KEY);
        }
    }
}
