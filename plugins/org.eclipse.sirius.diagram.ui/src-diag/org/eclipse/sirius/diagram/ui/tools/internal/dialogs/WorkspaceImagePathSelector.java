/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.common.ui.tools.api.resource.WorkspaceResourceDialogWithFilter;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * A {@link SelectionAdapter} to select a image path in the workspace.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WorkspaceImagePathSelector extends SelectionAdapter {

    /** All supported image file extensions. */
    private static final List<String> IMAGE_FILE_EXTENSIONS = new ArrayList<String>();

    static {
        for (ImageFileFormat imageFileFormat : ImageFileFormat.VALUES) {
            IMAGE_FILE_EXTENSIONS.add(imageFileFormat.getName().toLowerCase());
        }
    }

    private Text workspacePathText;

    /**
     * Default constructor.
     * 
     * @param workspacePathText
     *            the {@link Text} field displaying the selected path
     */
    public WorkspaceImagePathSelector(Text workspacePathText) {
        this.workspacePathText = workspacePathText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        List<ViewerFilter> filters = new ArrayList<>();
        if (IMAGE_FILE_EXTENSIONS != null) {
            filters.add(new FileExtensionFilter(IMAGE_FILE_EXTENSIONS));
        }
        IFile[] selectedResources = WorkspaceResourceDialogWithFilter.openFileSelection(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.WorkspaceImagePathSelector_dialogTitle, Messages.WorkspaceImagePathSelector_dialogMessage, false,
                null, filters);
        if (selectedResources != null && selectedResources.length == 1) {
            workspacePathText.setText(selectedResources[0].getFullPath().makeRelative().toString());
        }
    }

    /**
     * A filter based on file extension.
     * 
     * @author lredor
     */
    private static class FileExtensionFilter extends ViewerFilter {
        /**
         * The list of extensions for which we want to keep file.
         */
        private List<String> extensions;

        FileExtensionFilter(List<String> extensions) {
            this.extensions = extensions;
        }

        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            boolean isValid = true;
            if (element instanceof IFile) {
                // In the case of a file, we'll check if its extension is one of
                // the expected
                IFile file = (IFile) element;
                String fileExtension = file.getFileExtension();
                isValid = fileExtension != null && extensions.contains(fileExtension.toLowerCase());
            } else if (element instanceof IContainer) {
                // In the case of a container, we'll probe its content to see if
                // it contains a valid file
                isValid = false;
                IContainer container = (IContainer) element;
                if (!container.isDerived()) {
                    try {
                        final IResource[] members = container.members();
                        for (IResource member : members) {
                            isValid = select(viewer, parentElement, member);
                            if (isValid) {
                                // we found a valid resource in this folder, it
                                // is useless to probe any further
                                break;
                            }
                        }
                    } catch (final CoreException e) {
                        // This shouldn't happen
                    }
                }
            }
            return isValid;
        }
    }
}
