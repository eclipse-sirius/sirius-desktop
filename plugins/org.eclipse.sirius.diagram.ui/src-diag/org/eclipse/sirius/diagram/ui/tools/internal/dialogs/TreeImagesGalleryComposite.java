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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.business.api.image.GallerySelectable;
import org.eclipse.sirius.diagram.ui.business.api.image.ITreeImagesContentProvider;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.WorkspaceFileResourceChangeListener;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery.Gallery;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery.GalleryItem;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery.GalleryRendererUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.UIJob;

/**
 * Composite that provides a text widget, a tree viewer and a gallery to manage and display images in a directory or
 * tree structure. The content of the text widget is used to drive a PatternFilter that is on the viewer and the
 * gallery.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class TreeImagesGalleryComposite extends FilteredTree {

    /** The family of the job. */
    public static final String REFRESH_IMAGE_JOB_FAMILY = RefreshImageJob.class.getName();

    private static final String SLASH = "/"; //$NON-NLS-1$

    /**
     * Viewer's label provider.
     */
    private ILabelProvider labelProvider;

    /**
     * Viewer's content provider.
     */
    private ITreeImagesContentProvider contentProvider;

    /**
     * List of filters used in viewer.
     */
    private List<ViewerFilter> filters;

    /**
     * Viewer's input data.
     */
    private Object input;

    /**
     * The reference to the gallery used, to change its content or its display mode.
     */
    private GallerySelectable usedGallery;

    /**
     * The path of the image selected.
     */
    private String selectedImagePath;

    /**
     * A cache for images, to avoid recreating them on each filtering.
     */
    private Map<File, Image> thumbnailsImagesCache = new HashMap<>();

    /**
     * The Composite that contains the gallery.
     */
    private Composite galleryComposite;

    /**
     * The last text filter typed by the user.
     */
    private String lastUserFilter;

    /**
     * The loading image.
     */
    private Image loadingImage;

    private Object lastTreeSelection;

    private boolean galleryNeedsRefresh;

    private PatternFilter galleryPatternFilter;

    /**
     * Create a new instance of {@link TreeImagesGalleryComposite}.
     * 
     * @param parent
     *            the parent <code>Composite</code>
     * @param treeStyle
     *            the style bits for the <code>Tree</code>
     * @param filter
     *            the pattern filter to be used
     * @param labelProvider
     *            the label provider used by the viewer
     * @param contentProvider
     *            the content provider used by the viewer
     * @param viewerFilters
     *            the list of filters used by the viewer
     * @param input
     *            the viewer's input data
     */
    protected TreeImagesGalleryComposite(Composite parent, int treeStyle, PatternFilter filter, ILabelProvider labelProvider, ITreeImagesContentProvider contentProvider,
            List<ViewerFilter> viewerFilters, Object input) {
        super(parent, treeStyle, filter, true, true);

        this.labelProvider = labelProvider;
        this.contentProvider = contentProvider;
        this.filters = viewerFilters;
        this.input = input;
        this.selectedImagePath = null;
        this.lastUserFilter = StringUtil.EMPTY_STRING;
        this.galleryPatternFilter = new PatternFilter() {
            @Override
            public boolean isElementVisible(Viewer viewer, Object element) {
                String labelText = labelProvider.getText(element);

                if (labelText == null) {
                    return false;
                }
                return wordMatches(labelText);
            }
        };
        super.init(treeStyle, filter);
        setInitialText(Messages.ResourceSelectionDialog_ImageTreeComposite_filterText);
    }

    /**
     * Static method to create a new {@link TreeImagesGalleryComposite}.
     * 
     * @param parent
     *            the parent <code>Composite</code>
     * @param labelProvider
     *            the label provider used by the viewer
     * @param contentProvider
     *            the content provider used by the viewer
     * @param viewerFilters
     *            the list of filters used by the viewer
     * @param input
     *            the viewer's input data
     * @return the new created {@link TreeImagesGalleryComposite}
     */
    public static TreeImagesGalleryComposite createTreeImagesGalleryComposite(Composite parent, ILabelProvider labelProvider, ITreeImagesContentProvider contentProvider,
            List<ViewerFilter> viewerFilters, Object input) {
        return new TreeImagesGalleryComposite(parent, SWT.BORDER | SWT.SINGLE, new PatternFilter(), labelProvider, contentProvider, viewerFilters, input);
    }

    /**
     * {@inheritDoc} Overridden to initialize the new constructor parameters before initializing all controls.
     */
    @Override
    protected void init(int treeStyle, PatternFilter filter) {
    }

    @Override
    protected void createControl(Composite parent, int treeStyle) {
        GridLayout layout = new GridLayout(2, true);
        setLayout(layout);
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        setLayoutData(layoutData);

        if (showFilterControls) {
            filterComposite = new Composite(this, SWT.NONE);
            filterComposite.setLayout(new GridLayout());
            filterComposite.setFont(parent.getFont());

            createFilterControls(filterComposite);
            filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
        }

        // Tree
        treeComposite = new Composite(this, SWT.NONE);
        treeComposite.setLayout(new GridLayout());
        treeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        createTreeControl(treeComposite, treeStyle);

        // Gallery
        galleryComposite = new Composite(this, SWT.NONE);
        galleryComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        galleryComposite.setLayout(new GridLayout(1, false));
        createGalleryControl(galleryComposite);

        // Radios
        Composite radiosComposite = new Composite(galleryComposite, SWT.NONE);
        radiosComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
        radiosComposite.setLayout(new GridLayout(2, true));

        createRadiosControl(radiosComposite);
    }

    @Override
    protected Control createTreeControl(Composite parent, int style) {
        super.createTreeControl(parent, style);

        if (treeViewer != null) {
            treeViewer.setUseHashlookup(true);
        }
        treeViewer.setContentProvider(contentProvider);
        treeViewer.setLabelProvider(labelProvider);
        if (filters != null) {
            for (int i = 0; i != filters.size(); i++) {
                treeViewer.addFilter(filters.get(i));
            }
        }
        treeViewer.addDoubleClickListener(event -> {
            ISelection selection = event.getSelection();
            if (selection instanceof IStructuredSelection) {
                Object item = ((IStructuredSelection) selection).getFirstElement();
                if (treeViewer.getExpandedState(item)) {
                    treeViewer.collapseToLevel(item, 1);
                } else {
                    treeViewer.expandToLevel(item, 1);
                }
            }
        });
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                cancelRefresh();
                refreshGallery();
                if (!event.getStructuredSelection().isEmpty()) {
                    if (event.getStructuredSelection().getFirstElement() != lastTreeSelection) {
                        selectedImagePath = null;
                    }
                    lastTreeSelection = event.getStructuredSelection().getFirstElement();
                } else {
                    lastTreeSelection = null;
                }
            }
        });
        treeViewer.setInput(input);

        return treeViewer.getControl();
    }

    /**
     * Do the selection in the tree viewer and in the gallery according to the image path.
     * 
     * @param treeViewerSelectionData
     *            the item to select in the tree viewer
     * @param gallerySelectionData
     *            the item to select in the gallery
     * @return
     */
    public Optional<String> setSelection(Object treeViewerSelectionData, Object gallerySelectionData) {
        Optional<String> selectedPath = Optional.empty();
        if (treeViewerSelectionData != null) {
            treeViewer.setSelection(new StructuredSelection(treeViewerSelectionData), true);
        }

        if (gallerySelectionData != null && usedGallery.getItems().length > 0) {
            GalleryItem[] items = usedGallery.getItems()[0].getItems();
            for (GalleryItem galleryItem : items) {
                String text = galleryItem.getText();
                if (text.equals(labelProvider.getText(gallerySelectionData))) {
                    usedGallery.selectItem(galleryItem, true);
                }
            }
        }

        return selectedPath;
    }

    /**
     * Used to cancel refresh job.
     */
    protected void cancelRefresh() {
        Job.getJobManager().cancel(TreeImagesGalleryComposite.REFRESH_IMAGE_JOB_FAMILY);
    }

    /**
     * Creates and set up the gallery.
     * 
     * @param parent
     *            the parent <code>Composite</code>
     */
    protected void createGalleryControl(Composite parent) {
        usedGallery = new GallerySelectable(parent, SWT.BORDER | SWT.V_SCROLL);
        usedGallery.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        usedGallery.setLayoutData(layoutData);
        GalleryRendererUtils.setStandardGallery(parent, usedGallery);
        createGalleryListeners(usedGallery);
    }

    /**
     * Creates the listeners for the gallery.
     * 
     * @param gallery
     *            the listened gallery
     */
    protected void createGalleryListeners(Gallery gallery) {
        SelectionAdapter selectionListener = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.item != null) {
                    Object imageWrapper = ((GalleryItem) e.item).getData();
                    Optional<String> optImgPath = contentProvider.getPath(imageWrapper);
                    if (optImgPath.isPresent()) {
                        selectedImagePath = optImgPath.get().replace(File.separator, SLASH); // $NON-NLS-1$
                    }
                } else {
                    selectedImagePath = null;
                }
            }
        };
        gallery.addSelectionListener(selectionListener);
    }

    /**
     * Creates and set up the radios "List display" and "Grid display".
     * 
     * @param parent
     *            the parent <code>Composite</code>
     */
    protected void createRadiosControl(Composite parent) {
        Button listRadio = new Button(parent, SWT.RADIO);
        listRadio.setText(Messages.ResourceSelectionDialog_ImageTreeComposite_radioListDisplay);
        listRadio.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button button = (Button) e.getSource();
                if (button.getSelection()) {
                    GalleryRendererUtils.setListGallery(galleryComposite, usedGallery);
                }
            }
        });
        listRadio.setSelection(false);

        Button gridRadio = new Button(parent, SWT.RADIO);
        gridRadio.setText(Messages.ResourceSelectionDialog_ImageTreeComposite_radioGridDisplay);
        gridRadio.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Button button = (Button) e.getSource();
                if (button.getSelection()) {
                    GalleryRendererUtils.setStandardGallery(galleryComposite, usedGallery);
                }
            }
        });
        gridRadio.setSelection(true);
    }

    /**
     * Used to enable or disable the OK button according to the selection in the Gallery or Tree.
     * 
     * @param okButton
     *            reference to the OK button
     */
    public void createListenerForOKButton(Button okButton) {
        usedGallery.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                okButton.setEnabled(e.item != null);
            }
        });
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                okButton.setEnabled(getSelectedImagePath() != null);
            }
        });
    }

    /**
     * Returns the image files filtered if the filter is set, filtered from all available images, or only the images of
     * the current explorer selection.
     * 
     * @param filter
     *            the filter text
     * @return the set of objects wrapping references to image files
     */
    private Set<Object> getImagesToAdd(String filter) {
        Comparator<Object> comparator = new Comparator<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Comparable && o2 instanceof Comparable) {
                    return ((Comparable<Object>) o1).compareTo(o2);
                }
                return o1.toString().compareTo(o2.toString());
            }
        };
        Set<Object> imagesToAdd = new TreeSet<>(comparator);
        ITreeSelection structuredSelection = treeViewer.getStructuredSelection();
        if (!structuredSelection.isEmpty()) {
            for (Object object : structuredSelection.toArray()) {
                Set<Object> images = new TreeSet<>(comparator);
                for (Object element : contentProvider.getChildren(object)) {
                    if (contentProvider.getImageFile(element).isPresent()) {
                        images.add(element);
                    }
                }
                boolean isFilteringNeeded = filter != null && !(filter.isEmpty() || filter.equals(initialText));
                if (isFilteringNeeded) {
                    galleryPatternFilter.setPattern(filter);
                    for (Object image : images) {
                        if (galleryPatternFilter.isElementVisible(null, image)) {
                            imagesToAdd.add(image);
                        }
                    }
                } else {
                    imagesToAdd.addAll(images);
                }
            }
        }

        return imagesToAdd;
    }

    /**
     * Refreshes the gallery content.
     */
    public void refreshGallery() {
        String filterString = getFilterString();
        if (!lastUserFilter.equals(filterString) || lastTreeSelection != treeViewer.getStructuredSelection().getFirstElement() || galleryNeedsRefresh) {
            Set<Object> imagesToAdd = getImagesToAdd(filterString);
            usedGallery.removeAll();
            usedGallery.redraw();
            GalleryItem defaultCategory = new GalleryItem(usedGallery, SWT.NULL);
            for (Object imageFile : imagesToAdd) {
                createImageItem(defaultCategory, imageFile);
            }
            if (!lastUserFilter.equals(filterString) && usedGallery.getItems().length > 0 && selectedImagePath != null && lastTreeSelection != null) {
                GalleryItem[] items = usedGallery.getItems()[0].getItems();
                String[] segments = selectedImagePath.split("/"); //$NON-NLS-1$
                String imageName = segments[segments.length - 1];
                int nbSegments = segments.length;
                boolean similarFolder = false;
                Object treeNode = lastTreeSelection;
                // start at the second to last which corresponds to the last folder
                int index = nbSegments - 2;

                while (treeNode != null && index >= 0) {
                    String folderLabel = labelProvider.getText(treeNode);

                    similarFolder = folderLabel != null && (folderLabel.isEmpty() || folderLabel.equals(segments[index]));
                    treeNode = contentProvider.getParent(treeNode);
                    index--;
                }

                if (similarFolder) {
                    for (GalleryItem galleryItem : items) {
                        if (galleryItem != null && galleryItem.getText().equals(imageName)) {
                            usedGallery.selectItem(galleryItem, false);
                        }
                    }
                }
            }
            lastUserFilter = filterString;
        }
        setGalleryNeedsRefresh(false);
    }

    /**
     * Creates an image item into a gallery.
     * 
     * @param categoryItem
     *            the parent category
     * @param imageWrapper
     *            the object wrapping a reference to the image file
     */
    private void createImageItem(GalleryItem categoryItem, Object imageWrapper) {
        GalleryItem imageItem = new GalleryItem(categoryItem, SWT.NONE);
        Optional<File> optImgFile = contentProvider.getImageFile(imageWrapper);
        String imageName = labelProvider.getText(imageWrapper);
        if (optImgFile.isPresent() && !imageName.isEmpty()) {
            File imageFile = optImgFile.get();
            Image image = thumbnailsImagesCache.get(imageFile);
            if (image == null) {
                image = getLoadingImage();
                thumbnailsImagesCache.put(imageFile, image);
            }
            imageItem.setData(imageWrapper);
            imageItem.setImage(image);
            imageItem.setText(imageName);

            if (image == getLoadingImage()) {
                RefreshImageJob refreshJob = new RefreshImageJob(imageItem, imageWrapper);
                refreshJob.schedule();
            }
        }
    }

    @Override
    protected void textChanged() {
        super.textChanged();
        ISelection selection = treeViewer.getSelection();
        ITreeSelection structuredSelection = treeViewer.getStructuredSelection();
        if ((selection != null && !selection.isEmpty()) || (structuredSelection != null && !structuredSelection.isEmpty())) {
            refreshGallery();
        }
    }

    @Override
    public void dispose() {
        if (loadingImage != null) {
            if (!loadingImage.isDisposed()) {
                loadingImage.dispose();
            }
            loadingImage = null;
        }
        for (Image image : thumbnailsImagesCache.values()) {
            if (image != null && !image.isDisposed()) {
                image.dispose();
            }
        }
        thumbnailsImagesCache.clear();
        super.dispose();
    }

    /**
     * Get the gallery used in the composite.
     * 
     * @return the gallery
     */
    public GallerySelectable getGallery() {
        return usedGallery;
    }

    /**
     * Get the path of the selected image.
     * 
     * @return the path of the selected image
     */
    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    /**
     * Set the path of the selected image.
     * 
     * @param selectedImage
     *            the path of the image
     */
    public void setSelectedImagePath(String selectedImage) {
        this.selectedImagePath = selectedImage;
    }

    /**
     * Indicates if the gallery needs to be refreshed.
     * 
     * @param galleryNeedsRefresh
     *            the specified value
     */
    public void setGalleryNeedsRefresh(boolean galleryNeedsRefresh) {
        this.galleryNeedsRefresh = galleryNeedsRefresh;
    }

    /**
     * Lazy load the loading image.
     * 
     * @return the loading image, create one if necessary.
     */
    private Image getLoadingImage() {
        if (loadingImage == null) {
            ImageDescriptor desc = DiagramUIPlugin.Implementation.findImageDescriptor("/org.eclipse.sirius.diagram.ui/images/loading.png"); //$NON-NLS-1$
            loadingImage = desc.createImage();
        }
        return loadingImage;
    }

    /**
     * A job that will refresh the gallery by loading images from their file.
     * 
     * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
     *
     */
    public class RefreshImageJob extends UIJob {

        /**
         * The item to update.
         */
        private GalleryItem item;

        /**
         * The object wrapping a reference to the image file
         */
        private Object imageWrapper;

        /**
         * Creates a new instance of {@link RefreshImageJob}.
         * 
         * @param imageItem
         *            the item to update
         * @param imageWrapper
         *            the object wrapping a reference to the image file
         */
        public RefreshImageJob(GalleryItem imageItem, Object imageWrapper) {
            super(RefreshImageJob.class.getName());
            this.item = imageItem;
            this.imageWrapper = imageWrapper;
        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            Optional<File> optImgFile = contentProvider.getImageFile(imageWrapper);
            Optional<String> optImgPath = contentProvider.getPath(imageWrapper);
            if (!monitor.isCanceled() && !item.isDisposed() && optImgFile.isPresent() && optImgPath.isPresent()) {
                File imageFile = optImgFile.get();
                String imagePath = optImgPath.get();
                Image image = thumbnailsImagesCache.get(imageFile);
                if (image == getLoadingImage()) {
                    try {
                        if (WorkspaceImageFigure.isSvgImage(imagePath)) {
                            SVGFigureWithoutSiriusCache fig = new SVGFigureWithoutSiriusCache();
                            image = fig.getImage(imageFile);
                        } else {
                            ImageDescriptor desc = WorkspaceFileResourceChangeListener.getInstance().findImageDescriptor(imageFile);
                            image = desc.createImage();
                        }
                    } catch (MalformedURLException e) {
                    }
                    thumbnailsImagesCache.put(imageFile, image);
                }
                item.setImage(image);
                return Status.OK_STATUS;
            } else {
                return Status.CANCEL_STATUS;
            }
        }

        @Override
        public boolean belongsTo(Object family) {
            return TreeImagesGalleryComposite.REFRESH_IMAGE_JOB_FAMILY.equals(family);
        }

        /**
         * Used to load SVG image without adding it in Sirius cache.
         * 
         * @author gplouhinec
         *
         */
        private class SVGFigureWithoutSiriusCache extends SVGFigure {
            @Override
            protected Image getImage(Rectangle clientArea, Graphics graphics) {
                Image img = null;
                if (getTranscoder() != null) {
                    img = getTranscoder().render(this, clientArea, graphics, true);
                }
                return img;
            }

            /**
             * Loads an image from a given file.
             * 
             * @param imageFile
             *            the image file
             * @return the image
             */
            public Image getImage(File imageFile) {
                setURI(imageFile.toURI().toString());
                return getImage(new PrecisionRectangle(0, 0, 100, 100), null);
            }
        }
    }
}
