/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.api.image;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ImageWorkspaceContentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ImageWorkspaceLabelProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.TreeImagesGalleryComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

/**
 * Dialog to display and select an image in a directory.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class ImageSelectionDialog extends Dialog {

    /**
     * Width of the tree viewer.
     */
    static final int TREE_WIDTH = 270;

    /**
     * Height of the tree viewer.
     */
    static final int TREE_HEIGHT = 210;

    /**
     * The composite that displays widgets.
     */
    protected TreeImagesGalleryComposite treeGalleryComposite;

    /**
     * The title of the dialog.
     */
    private String title;

    /**
     * List of filters used in viewer.
     */
    private List<ViewerFilter> filters;

    /**
     * Viewer's input data.
     */
    private Object root;

    /**
     * Viewer's content provider.
     */
    private ITreeImagesContentProvider contentProvider;

    /**
     * Viewer's label provider.
     */
    private ILabelProvider labelProvider;

    /**
     * Create a new instance of {@link ImageSelectionDialog}.
     * 
     * @param parentShell
     *            the parent shell
     */
    public ImageSelectionDialog(Shell parentShell) {
        super(parentShell);
        this.title = Messages.ResourceSelectionDialog_title;
        this.root = ResourcesPlugin.getWorkspace().getRoot();
        this.labelProvider = new ImageWorkspaceLabelProvider();
        this.contentProvider = new ImageWorkspaceContentProvider();
        this.filters = new ArrayList<>();
        this.filters.add(ImageFiltersUtils.createFileExtensionFilter());
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        treeGalleryComposite = TreeImagesGalleryComposite.createTreeImagesGalleryComposite(container, getLabelProvider(), getContentProvider(), getFilters(), getRoot());

        Tree treeWidget = treeGalleryComposite.getViewer().getTree();
        GridData data = (GridData) treeWidget.getLayoutData();
        data.widthHint = TREE_WIDTH;
        data.heightHint = TREE_HEIGHT;
        treeWidget.setLayoutData(data);
        treeWidget.setFont(parent.getFont());

        return container;
    }
    
    @Override
    protected Control createButtonBar(Composite parent) {
        Control buttonBar = super.createButtonBar(parent);
        Button okButton = getButton(OK);
        okButton.setEnabled(false);
        treeGalleryComposite.createListenerForOKButton(okButton);
        return buttonBar;
    }

    @Override
    protected boolean isResizable() {
        return true;
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        if (title != null) {
            shell.setText(title);
        }
    }

    @Override
    public boolean close() {
        Job.getJobManager().cancel(TreeImagesGalleryComposite.REFRESH_IMAGE_JOB_FAMILY);
        treeGalleryComposite.dispose();
        return super.close();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ViewerFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<ViewerFilter> filters) {
        this.filters = filters;
    }

    public Object getRoot() {
        return root;
    }

    public void setRoot(Object root) {
        this.root = root;
    }

    public ITreeImagesContentProvider getContentProvider() {
        return contentProvider;
    }

    public void setContentProvider(ITreeImagesContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    public ILabelProvider getLabelProvider() {
        return labelProvider;
    }

    public void setLabelProvider(ILabelProvider labelProvider) {
        this.labelProvider = labelProvider;
    }

    public String getImagePath() {
        return treeGalleryComposite.getSelectedImagePath();
    }
}
