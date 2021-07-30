/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.ui.tools.api.resource;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * This dialog can be used to select a workspace resource. A filter field allows user to filter the resources.
 * 
 * @author lredor
 * 
 */
@Deprecated
public class WorkspaceResourceDialogWithFilter extends org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog {

    /**
     * Constructs an instance of <code>WorkspaceResourceDialogWithFilter</code>.
     * 
     * @param parent
     *            The parent shell for the dialog
     * @param labelProvider
     *            the label provider to render the entries
     * @param contentProvider
     *            the content provider to evaluate the tree structure
     */
    public WorkspaceResourceDialogWithFilter(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
        super(parent, labelProvider, contentProvider);
    }

    /**
     * Open this dialog and return the list of selected IFolder.
     * 
     * @param parent
     *            The parent shell for the dialog
     * @param title
     *            The title for this dialog.
     * @param message
     *            The message for this dialog.
     * @param allowMultipleSelection
     *            Specifies if multiple selection is allowed.
     * @param initialSelection
     *            the initial selection in this selection dialog
     * @param viewerFilters
     *            List of filters to add to the tree viewer.
     * @return the list of selected IFile or an empty array if nothing is selected.
     */
    public static IContainer[] openFolderSelection(Shell parent, String title, String message, boolean allowMultipleSelection, Object[] initialSelection, List<ViewerFilter> viewerFilters) {
        WorkspaceResourceDialogWithFilter dialog = new WorkspaceResourceDialogWithFilter(parent, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
        dialog.setAllowMultiple(allowMultipleSelection);
        dialog.setTitle(title != null ? title : CommonUIPlugin.INSTANCE.getString("_UI_FolderSelection_title")); //$NON-NLS-1$
        dialog.setMessage(message);
        dialog.setShowNewFolderControl(true);

        dialog.addFilter(dialog.createDefaultViewerFilter(false));
        if (viewerFilters != null) {
            for (ViewerFilter viewerFilter : viewerFilters) {
                dialog.addFilter(viewerFilter);
            }
        }

        if (initialSelection != null) {
            dialog.setInitialSelections(initialSelection);
        }

        dialog.loadContents();
        return dialog.open() == Window.OK ? dialog.getSelectedContainers() : new IContainer[0];
    }

    /**
     * Open this dialog and return the list of selected IFile.
     * 
     * @param parent
     *            The parent shell for the dialog
     * @param title
     *            The title for this dialog.
     * @param message
     *            The message for this dialog.
     * @param allowMultipleSelection
     *            Specifies if multiple selection is allowed.
     * @param initialSelection
     *            the initial selection in this selection dialog
     * @param viewerFilters
     *            List of filters to add to the tree viewer.
     * @return the list of selected IFile or an empty array if nothing is selected.
     */
    public static IFile[] openFileSelection(Shell parent, String title, String message, boolean allowMultipleSelection, Object[] initialSelection, List<ViewerFilter> viewerFilters) {
        WorkspaceResourceDialogWithFilter dialog = new WorkspaceResourceDialogWithFilter(parent, new WorkbenchLabelProvider(), new WorkbenchContentProvider());
        dialog.setAllowMultiple(allowMultipleSelection);
        dialog.setTitle(title != null ? title : CommonUIPlugin.INSTANCE.getString("_UI_FileSelection_title")); //$NON-NLS-1$
        dialog.setMessage(message);

        dialog.addFilter(dialog.createDefaultViewerFilter(true));
        if (viewerFilters != null) {
            for (ViewerFilter viewerFilter : viewerFilters) {
                dialog.addFilter(viewerFilter);
            }
        }

        if (initialSelection != null) {
            dialog.setInitialSelections(initialSelection);
        }

        dialog.loadContents();
        return dialog.open() == Window.OK ? dialog.getSelectedFiles() : new IFile[0];
    }

    @Override
    protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
        FilteredTree filteredTree = new FilteredTree(parent, style, new PatternFilter(), true);
        TreeViewer fViewer = filteredTree.getViewer();
        fViewer.setUseHashlookup(true);
        return fViewer;
    }
}
