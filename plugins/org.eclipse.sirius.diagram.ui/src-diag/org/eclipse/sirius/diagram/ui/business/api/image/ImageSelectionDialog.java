/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES and others.
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ImageWorkspaceContentProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ImageWorkspaceLabelProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.TreeImagesGalleryComposite;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
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

    private static final String SLASH = "/"; //$NON-NLS-1$

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

    private boolean onlyCloseButton;

    private String currentImagePath;

    private EObject contextObject;

    private Text newPathText;

    /**
     * Create a new instance of {@link ImageSelectionDialog}.
     * 
     * @param parentShell
     *            the parent shell
     * @param contextObject
     *            the context EObject
     * @param onlyCloseButton
     *            if true there is only the close button instead of OK/Cancel
     * @param currentImagePath
     *            the path to the image as it is serialized in the model
     */
    public ImageSelectionDialog(Shell parentShell, EObject contextObject, boolean onlyCloseButton, String currentImagePath) {
        super(parentShell);
        this.title = Messages.ImageSelectionDialog_title;
        this.root = ResourcesPlugin.getWorkspace().getRoot();
        this.labelProvider = new ImageWorkspaceLabelProvider();
        this.contentProvider = new ImageWorkspaceContentProvider();
        this.filters = new ArrayList<>();
        this.filters.add(ImageFiltersUtils.createFileExtensionFilter());
        this.onlyCloseButton = onlyCloseButton;
        if (currentImagePath != null) {
            this.currentImagePath = URI.decode(currentImagePath.replace("\\", SLASH)); //$NON-NLS-1$
        }
        this.contextObject = contextObject;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite mainContainer = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(mainContainer, SWT.NONE);
        container.setLayout(getLayout());
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Object folderToSelect = null;
        Object imageToSelect = null;
        if (currentImagePath != null) {
            String[] imagePathSegments = currentImagePath.split(SLASH);
            Object[] matchingFolder = { null };
            Object[] matchingImage = { null };
            computeMatchingFolderAndImage(contentProvider.getChildren(getRoot()), imagePathSegments, 0, matchingFolder, matchingImage);
            // The message part is not displayed if the image corresponding to the currentImagePath has been found
            if (matchingImage[0] == null) {
                Object computeImageWithSameName = computeImageWithSameName(contentProvider.getChildren(getRoot()), imagePathSegments[imagePathSegments.length - 1]);
                if (computeImageWithSameName != null) {
                    createMessageComposite(container, null, computeImageWithSameName);

                    folderToSelect = contentProvider.getParent(computeImageWithSameName);
                    imageToSelect = computeImageWithSameName;
                } else {
                    createMessageComposite(container, matchingFolder[0], null);
                    folderToSelect = matchingFolder[0];
                }
            } else {
                folderToSelect = matchingFolder[0];
                imageToSelect = matchingImage[0];
            }
        }

        treeGalleryComposite = TreeImagesGalleryComposite.createTreeImagesGalleryComposite(container, getLabelProvider(), getContentProvider(), getFilters(), getRoot());

        Tree treeWidget = treeGalleryComposite.getViewer().getTree();
        GridData data = (GridData) treeWidget.getLayoutData();
        data.widthHint = TREE_WIDTH;
        data.heightHint = TREE_HEIGHT;
        treeWidget.setLayoutData(data);
        treeWidget.setFont(parent.getFont());

        createImagePathComposite(container);

        treeGalleryComposite.setSelection(folderToSelect, imageToSelect);

        return mainContainer;
    }

    /**
     * Try to find the image matching the current image path otherwise find the deeper common folder.
     * 
     * @param elements
     *            the elements on which to iterate recursively
     * @param imagePathSegments
     *            image path segments
     * @param imagePathIndex
     *            it corresponds to the depth of the current folder
     * @param currentMatchingFolder
     *            the resulting matching folder item
     * @param matchingImage
     *            the resulting matching image item
     * @return currentMatchingFolder with a non null object corresponding to the folder common part and matchingImage if
     *         the image has been found
     */
    private void computeMatchingFolderAndImage(Object[] elements, String[] imagePathSegments, int imagePathIndex, Object[] currentMatchingFolder, Object[] matchingImage) {
        if (imagePathIndex > imagePathSegments.length - 1) {
            return;
        }

        for (Object object : elements) {
            String text = labelProvider.getText(object);
            if (imagePathSegments[imagePathIndex].equals(text)) {
                // the current children matches the following segment of the imagePath
                if (imagePathSegments.length == imagePathIndex + 1) {
                    // the exact image path has been found
                    matchingImage[0] = object;
                } else {
                    currentMatchingFolder[0] = object;
                    computeMatchingFolderAndImage(contentProvider.getChildren(object), imagePathSegments, imagePathIndex + 1, currentMatchingFolder, matchingImage);
                }
                break;
            }
        }
    }

    /**
     * Try to find an image with the exact same short name.
     * 
     * @param elements
     *            the elements on which to iterate recursively
     * @param imageName
     *            the image name
     * @return the first image found with the same name as imageName
     */
    private Object computeImageWithSameName(Object[] elements, String imageName) {
        Object foundImage = null;
        for (Object object : elements) {
            String text = labelProvider.getText(object);
            if (imageName.equals(text) && contentProvider.getImageFile(object).isPresent()) {
                foundImage = object;
                break;
            } else {
                foundImage = computeImageWithSameName(contentProvider.getChildren(object), imageName);
                if (foundImage != null) {
                    break;
                }
            }
        }
        return foundImage;
    }

    private void createImagePathComposite(Composite parent) {
        if (currentImagePath != null) {
            Composite imagePathContainer = new Composite(parent, SWT.NONE);
            GridLayout gridLayout = new GridLayout(2, false);
            imagePathContainer.setLayout(gridLayout);
            imagePathContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

            Label currentPathLabel = new Label(imagePathContainer, SWT.NONE);
            currentPathLabel.setText("Current image path"); //$NON-NLS-1$
            final GridData anyElementData = new GridData();
            anyElementData.horizontalAlignment = GridData.FILL;
            anyElementData.grabExcessHorizontalSpace = true;
            Text currentPathText = new Text(imagePathContainer, SWT.BORDER);
            currentPathText.setEditable(false);
            currentPathText.setText(currentImagePath);
            currentPathText.setLayoutData(anyElementData);

            Label newPathLabel = new Label(imagePathContainer, SWT.NONE);
            newPathLabel.setText("New image path"); //$NON-NLS-1$
            newPathText = new Text(imagePathContainer, SWT.BORDER);
            newPathText.setEditable(false);
            newPathText.setLayoutData(anyElementData);

            treeGalleryComposite.getGallery().addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    String selectedImagePath = treeGalleryComposite.getSelectedImagePath();
                    selectedImagePath = selectedImagePath != null ? selectedImagePath : ""; //$NON-NLS-1$
                    newPathText.setText(selectedImagePath);
                }
            });

            treeGalleryComposite.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    String selectedImagePath = treeGalleryComposite.getSelectedImagePath();
                    selectedImagePath = selectedImagePath != null ? selectedImagePath : ""; //$NON-NLS-1$
                    newPathText.setText(selectedImagePath);
                }
            });
        }
    }

    private void createMessageComposite(Composite parent, Object folderObject, Object imageObject) {
        if (currentImagePath != null) {
            Composite messageContainer = new Composite(parent, SWT.FILL);
            GridLayout gridLayout = new GridLayout(2, false);
            messageContainer.setLayout(gridLayout);
            Label message = new Label(messageContainer, SWT.NONE);
            message.setLayoutData(new GridData(SWT.FILL));
            if (folderObject != null) {
                message.setText(MessageFormat.format(Messages.ImageSelectionDialog_imageNotFound_folderFound, new EditingDomainServices().getLabelProviderText(contextObject)));
            } else if (imageObject != null) {
                message.setText(MessageFormat.format(Messages.ImageSelectionDialog_imageNotFound_shortNameFound, new EditingDomainServices().getLabelProviderText(contextObject)));
            } else {
                message.setText(MessageFormat.format(Messages.ImageSelectionDialog_imageNotFound, new EditingDomainServices().getLabelProviderText(contextObject)));
            }
        }
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        if (onlyCloseButton) {
            // create only close button
            createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CLOSE_LABEL, true);
        } else {
            // create OK and Cancel buttons by default
            Button okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
            okButton.setEnabled(treeGalleryComposite.getSelectedImagePath() != null);
            treeGalleryComposite.createListenerForOKButton(okButton);

            createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        }
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
