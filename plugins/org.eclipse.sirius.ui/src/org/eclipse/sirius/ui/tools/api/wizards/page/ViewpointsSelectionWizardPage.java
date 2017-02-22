/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.wizards.page;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.IViewpointStateListener;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.ViewpointStateChangeEvent;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.osgi.framework.Bundle;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A wizard page to select viewpoints.
 *
 * @author mchauvin
 */
public class ViewpointsSelectionWizardPage extends WizardPage {

    /**
     * The layout of the main composite of this graphic component.
     */
    private GridLayout rootGridLayout;

    /** The table viewer. */
    private TableViewer tableViewer;

    /** The browser for documentation */
    private Browser browser;

    private Composite pageComposite;

    /**
     * The list of selected viewpoints
     */
    private List<Viewpoint> viewpoints;

    /**
     * List of file extensions used to compute the available viewpoints.
     */
    private Collection<String> fileExtensions;

    /**
     * List of viewpoints names that must be activate by default (ie checked in the list).
     */
    private ArrayList<String> viewpointsNamesToActivateByDefault;

    /**
     * The grid data for the browser component displaying viewpoint information.
     */
    private GridData browserGridData;

    /**
     * The listeners that should be warn when a user tick or untick a viewpoint meaning it should be
     * activated/deactivated of the session.
     */
    private Set<IViewpointStateListener> viewpointStateListeners;

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code>.
     *
     * @param session
     *            the session
     */
    public ViewpointsSelectionWizardPage(final Session session) {
        super(Messages.ViewpointsSelectionWizardPage_title);
        this.setTitle(Messages.ViewpointsSelectionWizardPage_title);
        this.setMessage(Messages.ViewpointsSelectionWizardPage_message);
        this.fileExtensions = computeSemanticFileExtensions(session);
        this.viewpoints = Lists.newArrayList();
        this.viewpointsNamesToActivateByDefault = Lists.newArrayList();
    }

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code> with default viewpoints activation. This constructor
     * makes this page optional.
     *
     * @param session
     *            the session
     * @param viewpointsNamesToActivateByDefault
     *            list of viewpoints names to activate by default.
     */
    public ViewpointsSelectionWizardPage(final Session session, List<String> viewpointsNamesToActivateByDefault) {
        super(Messages.ViewpointsSelectionWizardPage_title);
        this.setTitle(Messages.ViewpointsSelectionWizardPage_title);
        this.setMessage(Messages.ViewpointsSelectionWizardPage_message);
        this.fileExtensions = computeSemanticFileExtensions(session);
        this.viewpoints = Lists.newArrayList();
        this.viewpointsNamesToActivateByDefault = Lists.newArrayList(viewpointsNamesToActivateByDefault);
    }

    /**
     * Add the given listener to the list used by this component.
     * 
     * @param viewpointStateListener
     *            the listener to add.
     */
    public void addViewpointStateListeners(IViewpointStateListener viewpointStateListener) {
        if (viewpointStateListeners != null) {
            viewpointStateListeners.add(viewpointStateListener);
        }
    }

    /**
     * Remove the given listener from the list used by this component.
     * 
     * @param viewpointStateListener
     *            the listener to remove.
     */
    public void removeViewpointStateListeners(IViewpointStateListener viewpointStateListener) {
        if (viewpointStateListeners != null) {
            viewpointStateListeners.remove(viewpointStateListener);
        }
    }

    /**
     * If true makes the two columns of the main composite equals at layout level if the layout exists. If false makes
     * the two columns not equals at layout level if the layout exists. Refresh the layout if a modification is done.
     * Should be called after control creation.
     * 
     * @param isEqual
     *            the equal characteristic to set.
     */
    public void setColumnWidthEquality(boolean isEqual) {
        if (rootGridLayout != null) {
            rootGridLayout.makeColumnsEqualWidth = isEqual;
            pageComposite.layout();
        }
    }

    /**
     * Set the minimum width of the browser part of the component by modifying the underlying grid data.
     * 
     * @param minWidth
     *            the minimum width the browser should have.
     */
    public void setBrowserMinWidth(int minWidth) {
        if (browserGridData != null) {
            browserGridData.minimumWidth = minWidth;
            pageComposite.layout();
        }
    }

    /**
     * compute the semantic file extensions to restrict the choice of viewpoint based on the session.
     *
     * @param session
     *            the session
     * @return a collection of file extension
     */
    protected Collection<String> computeSemanticFileExtensions(Session session) {
        final Collection<String> extensions = new HashSet<String>();
        for (final Resource resource : session.getSemanticResources()) {
            if (resource != null && resource.getURI() != null) {
                final String currentFileExtension = resource.getURI().fileExtension();
                if (currentFileExtension != null) {
                    extensions.add(currentFileExtension);
                }
            }
        }
        return extensions;
    }

    @Override
    public boolean isPageComplete() {
        String errorMessage = null;
        boolean complete = false;

        if (!viewpoints.isEmpty()) {
            Map<String, Collection<String>> missingDependencies = ViewpointSelection.getMissingDependencies(Sets.newHashSet(viewpoints));
            if (missingDependencies.isEmpty()) {
                complete = true;
            } else {
                errorMessage = ViewpointSelection.getMissingDependenciesErrorMessage(missingDependencies);
            }
        }

        setErrorMessage(errorMessage);
        return complete;
    }

    /**
     * Return the list of selected viewpoints of this page.
     *
     * @return the list of selected viewpoints
     */
    public List<Viewpoint> getViewpoints() {
        return viewpoints;
    }

    @Override
    public Composite getControl() {
        return pageComposite;
    }

    @Override
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);

        viewpointStateListeners = new HashSet<IViewpointStateListener>();

        pageComposite = new Composite(parent, SWT.NONE);
        rootGridLayout = GridLayoutFactory.swtDefaults().numColumns(2).equalWidth(true).create();
        pageComposite.setLayout(rootGridLayout);
        pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        this.tableViewer = createTableViewer(pageComposite);
        tableViewer.setInput(getAvailableViewpoints());

        this.browser = createBrowser(pageComposite);
        setBrowserInput(null);

        if (!viewpointsNamesToActivateByDefault.isEmpty()) {
            // Search the viewpoints to activate by their name
            for (int i = 0; i < tableViewer.getTable().getItemCount(); i++) {
                Object object = tableViewer.getElementAt(i);
                if (object instanceof Viewpoint && viewpointsNamesToActivateByDefault.contains(((Viewpoint) object).getName())) {
                    viewpoints.add((Viewpoint) object);
                }
            }
            if (!viewpoints.isEmpty() && tableViewer instanceof CheckboxTableViewer) {
                // Check all the default viewpoints
                ((CheckboxTableViewer) tableViewer).setCheckedElements(viewpoints.toArray(new Object[0]));
                // Set the focus on the first one
                tableViewer.setSelection(new StructuredSelection(viewpoints.get(0)));
            }
        }

        setControl(pageComposite);
    }

    private Browser createBrowser(final Composite parent) {

        try {
            Browser aBrowser = new Browser(parent, SWT.FILL);
            browserGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
            // necessary to avoid bad interaction with expandable toolkit sections
            browserGridData.widthHint = 0;
            browserGridData.heightHint = 0;
            aBrowser.setLayoutData(browserGridData);
            return aBrowser;
        } catch (SWTError error) {
            /*
             * the browser could not be created, do not display further information
             */
            return null;
        }

    }

    /**
     * Returns all registered viewpoints that define editors for metamodel of loaded session's semantic models.
     * 
     * @return all registered viewpoints that define editors for metamodel of loaded session's semantic models.
     */
    public Collection<Viewpoint> getAvailableViewpoints() {

        ViewpointRegistry registry = ViewpointRegistry.getInstance();

        return Collections2.filter(registry.getViewpoints(), new Predicate<Viewpoint>() {

            @Override
            public boolean apply(Viewpoint viewpoint) {
                for (final String ext : fileExtensions) {
                    if (new ViewpointQuery(viewpoint).handlesSemanticModelExtension(ext)) {
                        return true;
                    }
                }
                return false;
            }
        });

    }

    /**
     * Create the table viewer.
     *
     * @param parent
     *            the parent composite.
     * @return the table viewer.
     */
    private TableViewer createTableViewer(final Composite parent) {
        final int style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;

        CheckboxTableViewer viewer = CheckboxTableViewer.newCheckList(parent, style);
        Table table = viewer.getTable();
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
        viewer.getControl().setLayoutData(gridData);

        TableLayout layout = new TableLayout();
        table.setLayout(layout);
        table.setHeaderVisible(false);
        table.setLinesVisible(false);

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new ViewpointsTableLabelProvider());

        viewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(final CheckStateChangedEvent event) {
                if (event.getChecked()) {
                    viewpoints.add((Viewpoint) event.getElement());
                    for (IViewpointStateListener viewpointStateListener : viewpointStateListeners) {
                        viewpointStateListener.viewpointStateChange(new ViewpointStateChangeEvent((Viewpoint) event.getElement(), true));
                    }
                } else {
                    viewpoints.remove(event.getElement());
                    for (IViewpointStateListener viewpointStateListener : viewpointStateListeners) {
                        viewpointStateListener.viewpointStateChange(new ViewpointStateChangeEvent((Viewpoint) event.getElement(), false));
                    }
                }
                setPageComplete(isPageComplete());
            }
        });

        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    Object firstElement = ((IStructuredSelection) selection).getFirstElement();
                    if (firstElement instanceof Viewpoint) {
                        setBrowserInput((Viewpoint) firstElement);
                    }
                }
            }
        });

        viewer.setComparator(new ViewerComparator() {
            @Override
            public int compare(Viewer viewer, Object e1, Object e2) {
                final String e1label = new IdentifiedElementQuery((Viewpoint) e1).getLabel();
                final String e2label = new IdentifiedElementQuery((Viewpoint) e2).getLabel();
                return e1label.compareTo(e2label);
            }
        });
        return viewer;
    }

    /***
     * Set the browser input.A jface like browser viewer would have been better.
     *
     * @param viewpoint
     *            the viewpoint to document
     */
    protected void setBrowserInput(final Viewpoint viewpoint) {

        /* browser may be null if its creation fail */
        if (browser != null) {
            String content = null;
            if (containsHTMLDocumentation(viewpoint)) {
                content = getContentWhenHtml(viewpoint);
            } else {
                content = getContentWhenNoHtml(viewpoint);
            }
            browser.setText(content);
        }
    }

    /*
     * The following code (HTML handling ) and methods could move to another class.
     */
    private boolean containsHTMLDocumentation(Viewpoint viewpoint) {
        if (viewpoint != null) {
            final String doc = viewpoint.getEndUserDocumentation();
            if (!StringUtil.isEmpty(doc)) {
                return doc.startsWith("<html>"); //$NON-NLS-1$
            }
        }
        return false;
    }

    private String getContentWhenHtml(Viewpoint viewpoint) {

        final String document = viewpoint.getEndUserDocumentation();

        Set<String> urlToRewrite = Sets.newLinkedHashSet();
        extractUrlToRewrite(document, urlToRewrite);

        return rewriteURLs(viewpoint, document, urlToRewrite);
    }

    private void extractUrlToRewrite(String document, Set<String> urlToRewrite) {
        String imgSrcPattern = "img src=\""; //$NON-NLS-1$
        int patternStartIndex = document.indexOf(imgSrcPattern);
        if (patternStartIndex != -1) {
            int imgSrcStartIndex = patternStartIndex + imgSrcPattern.length();
            int imgSrcStopIndex = document.indexOf("\"", imgSrcStartIndex); //$NON-NLS-1$
            if (imgSrcStopIndex != -1) {
                String newToRewrite = document.substring(imgSrcStartIndex, imgSrcStopIndex);
                urlToRewrite.add(newToRewrite);
                extractUrlToRewrite(document.substring(imgSrcStopIndex), urlToRewrite);
            }
        }
    }

    private String rewriteURLs(Viewpoint viewpoint, String document, Set<String> urls) {

        String newDocument = document;

        for (final String url : urls) {
            newDocument = newDocument.replace(url, rewriteURL(viewpoint, url));
        }

        StringBuilder css = new StringBuilder();
        appendCss(css);
        String headClose = "</head>"; //$NON-NLS-1$
        newDocument = newDocument.replace(headClose, css.append(headClose));

        return newDocument;
    }

    private String rewriteURL(Viewpoint viewpoint, String url) {
        final URI uri = viewpoint.eResource().getURI();
        String pluginId = uri.segment(1);

        String rewrittenURL = ""; //$NON-NLS-1$

        if (uri.isPlatformPlugin()) {
            Bundle bundle = Platform.getBundle(pluginId);
            URL imageURL = bundle.getEntry(url);
            rewrittenURL = imageURL != null ? imageURL.toString() : rewrittenURL;
            if (imageURL != null) {
                try {
                    URL fileURL = FileLocator.toFileURL(imageURL);
                    rewrittenURL = fileURL.toString();
                } catch (IOException e) {
                    // do nothing
                }
            }

        } else {
            final IWorkspace workspace = ResourcesPlugin.getWorkspace();
            final IPath path = new Path("/" + pluginId + url); //$NON-NLS-1$
            if (workspace.getRoot().exists(path)) {
                IResource resource = workspace.getRoot().findMember(path);
                rewrittenURL = resource.getLocation().toFile().toURI().toString();
            }
        }

        return rewrittenURL;
    }

    private String getContentWhenNoHtml(Viewpoint viewpoint) {
        StringBuilder content = new StringBuilder();
        return begin(content).head(content).body(content, viewpoint).end(content);
    }

    private ViewpointsSelectionWizardPage begin(StringBuilder content) {
        content.append("<html>"); //$NON-NLS-1$
        return this;
    }

    private ViewpointsSelectionWizardPage head(StringBuilder content) {
        content.append("<head>"); //$NON-NLS-1$
        appendCss(content);
        content.append("</head>"); //$NON-NLS-1$
        return this;
    }

    private ViewpointsSelectionWizardPage body(StringBuilder content, Viewpoint viewpoint) {
        content.append("<body>"); //$NON-NLS-1$

        if (viewpoint == null) {
            content.append("<br><br><center><b>").append(Messages.ViewpointsSelectionWizardPage_documentation_title).append("</b></center>"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            final String endUserDocumentation = viewpoint.getEndUserDocumentation();
            if (!StringUtil.isEmpty(endUserDocumentation)) {
                content.append(viewpoint.getEndUserDocumentation());
            } else {
                content.append(Messages.ViewpointsSelectionWizardPage_documentation_none);
            }
        }
        content.append("</body>"); //$NON-NLS-1$
        return this;
    }

    private StringBuilder appendCss(StringBuilder content) {
        Font currentFont = JFaceResources.getDialogFont();
        FontData data = currentFont.getFontData()[0];
        String fontName = data.getName();
        int fontHeight = data.getHeight() + 3;
        content.append("<style type=\"text/css\">"); //$NON-NLS-1$
        content.append("body{font-family:" + fontName + ",Arial, sans-serif;}"); //$NON-NLS-1$ //$NON-NLS-2$
        content.append("body{font-size:" + fontHeight + "px;}"); //$NON-NLS-1$ //$NON-NLS-2$
        content.append("</style>"); //$NON-NLS-1$
        return content;
    }

    private String end(StringBuilder content) {
        content.append("</html>"); //$NON-NLS-1$
        return content.toString();
    }

    /**
     * return if the page is the current page.
     *
     * @return if the page is the current page.
     */
    public boolean isCurrentPageOnWizard() {
        return super.isCurrentPage();
    }

    private class ViewpointsTableLabelProvider extends AdapterFactoryLabelProvider implements ITableLabelProvider {

        ViewpointsTableLabelProvider() {
            super(ViewHelper.INSTANCE.createAdapterFactory());
        }

        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            Image image = null;
            if (columnIndex == 0) {
                if (element instanceof Viewpoint) {
                    final Viewpoint vp = (Viewpoint) element;
                    if (vp.getIcon() != null && vp.getIcon().length() > 0) {
                        final ImageDescriptor desc = SiriusEditPlugin.Implementation.findImageDescriptor(vp.getIcon());
                        if (desc != null) {
                            image = SiriusEditPlugin.getPlugin().getImage(desc);
                            image = getEnhancedImage(image, vp);
                        }
                    }
                    if (image == null) {
                        image = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.getPlugin().getItemImageDescriptor(vp));
                        image = getEnhancedImage(image, vp);
                    }
                } else {
                    image = super.getImage(element);
                }
            }
            return image;
        }

        private ImageDescriptor getOverlayedDescriptor(final Image baseImage, final String decoratorPath) {
            final ImageDescriptor decoratorDescriptor = SiriusEditPlugin.Implementation.getBundledImageDescriptor(decoratorPath);
            return new DecorationOverlayIcon(baseImage, decoratorDescriptor, IDecoration.BOTTOM_LEFT);
        }

        private Image getEnhancedImage(final Image image, final Viewpoint viewpoint) {
            // Add decorator if the viewpoint comes from workspace
            if (!ViewpointRegistry.getInstance().isFromPlugin(viewpoint) && image != null) {
                return SiriusEditPlugin.getPlugin().getImage(getOverlayedDescriptor(image, "icons/full/decorator/folder_close.gif")); //$NON-NLS-1$
            }
            return image;
        }

    }
}
