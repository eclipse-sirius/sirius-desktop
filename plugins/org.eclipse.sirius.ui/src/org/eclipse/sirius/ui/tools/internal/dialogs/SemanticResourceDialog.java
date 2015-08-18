/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.dialogs;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Specific resource dialog to avoid representation file selection. The browse
 * workspace dialog will not display those representation files.
 * 
 * The processResource method will check the selected value and open an error
 * dialog if representation files are detected. They will be removed from
 * selection.
 * 
 * @author mporhel
 * 
 */
public class SemanticResourceDialog extends ResourceDialog {

    private static final String SEPARATOR = "  "; //$NON-NLS-1$

    /**
     * Default constructor.
     * 
     * @param parent
     *            the parent shell
     * @param title
     *            the dialog title
     * @param style
     *            the dialog style
     * 
     *            /** Default constructor.
     * 
     * @see ResourceDialog
     */
    public SemanticResourceDialog(Shell parent, String title, int style) {
        super(parent, title, style);
    }

    @Override
    protected boolean processResources() {
        final List<URI> representationFiles = Lists.newArrayList();
        final List<URI> urIs = getURIs();
        for (URI uri : urIs) {
            if (new FileQuery(uri.fileExtension()).isSessionResourceFile()) {
                representationFiles.add(uri);
            }
        }

        if (!representationFiles.isEmpty()) {
            String message = "Representation files can not be added as semantic resources";
            Status status = new Status(IStatus.WARNING, SiriusEditPlugin.ID, message + ": " + representationFiles);
            ErrorDialog.openError(getParentShell(), "Incorrect selection", message + ", they will be removed from the selection.", status);

            Iterables.removeAll(urIs, representationFiles);
            if (uriField != null && !uriField.isDisposed() && !urIs.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (URI uri : urIs) {
                    sb.append(uri);
                    sb.append(SEPARATOR);
                }
                uriField.setText(sb.toString().trim());
            }

            return false;
        }

        return true;
    }

    @Override
    protected void prepareBrowseWorkspaceButton(Button browseWorkspaceButton) {
        ViewerFilter filter = new PotentialSemanticResourceFilter();
        final List<ViewerFilter> filters = Collections.singletonList(filter);

        browseWorkspaceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (isMulti()) {
                    StringBuffer uris = new StringBuffer();

                    IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), null, null, true, null, filters);
                    for (int i = 0, len = files.length; i < len; i++) {
                        uris.append(URI.createPlatformResourceURI(files[i].getFullPath().toString(), true));
                        uris.append(SEPARATOR);
                    }
                    uriField.setText((uriField.getText() + SEPARATOR + uris.toString()).trim());
                } else {
                    IFile file = null;

                    if (isSave()) {
                        file = WorkspaceResourceDialog.openNewFile(getShell(), null, null, null, filters);
                    } else {
                        IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), null, null, false, null, filters);
                        if (files.length != 0) {
                            file = files[0];
                        }
                    }

                    if (file != null) {
                        uriField.setText(URI.createPlatformResourceURI(file.getFullPath().toString(), true).toString());
                    }
                }
            }
        });
    }

    /**
     * Exclude representation files.
     * 
     * @see {@link FileQuery#isSessionResourceFile()}
     * @author mporhel
     * 
     */
    private static class PotentialSemanticResourceFilter extends ViewerFilter {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            boolean isValid = true;
            if (element instanceof IFile) {
                // In the case of a file, we'll check that it is not a
                // representation file.
                isValid = !new FileQuery((IFile) element).isSessionResourceFile();
            }
            return isValid;
        }
    }
}
