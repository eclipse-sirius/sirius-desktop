/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.tools.internal.viewpoint;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
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
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.Bundle;

/**
 * A graphic component providing a {@link CheckboxTableViewer} with a checkbox for each viewpoint activable/deactivable
 * regarding the given session. Also provides a browser allowing to see viewpoint description when one has focus.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ViewpointsSelectionGraphicalHandler {

    private static final String HTML_TAG_REGEXP = "<[^/>]*/>"; //$NON-NLS-1$

    private static final String HTML_TAG_REGEXP_WITHOUT_CLOSING_CHARACTER = "<[^>]*>"; //$NON-NLS-1$

    private static final String BROWSER_SUFFIX = "</b></center>"; //$NON-NLS-1$

    private static final String BROWSER_PREFIX = "<br><br><center><b>"; //$NON-NLS-1$

    /**
     * The layout of the main composite of this graphic component.
     */
    private GridLayout rootGridLayout;

    /** The browser for documentation */
    private Browser browser;

    /**
     * The composite enclosing all graphical parts of this component.
     */
    private Composite rootComposite;

    /**
     * The grid data for the browser component displaying viewpoint information.
     */
    private GridData browserGridData;

    /**
     * The viewer containing a checkbox for each viewpoint registered in the current runtime.
     */
    private CheckboxTableViewer viewer;

    /**
     * The layout data of the root component.
     */
    private GridData rootLayoutData;

    private GridData viewerGridData;

    /**
     * LayoutData of the error message top part of the browser component.
     */
    private GridData browserErrorMessageLayoutData;

    /**
     * The Text containing error message in the top part of the browser component.
     */
    private Text browserErrorMessageText;

    /**
     * The composite containing the text containing error message in the top part of the browser component.
     */
    private Composite browserErrorMessageComposite;

    /**
     * The root composite of the browser component.
     */
    private Composite browserRootComposite;

    private Composite browserReplacementComposite;

    private Text browserReplacementText;

    /**
     * Return the composite enclosing all graphical parts of this component.
     * 
     * @return the composite enclosing all graphical parts of this component.
     */
    public Composite getRootComposite() {
        return rootComposite;
    }

    /**
     * Return the browser providing descriptions for viewpoints taking focus.
     * 
     * @return the browser providing descriptions for viewpoints taking focus.
     */
    public Composite getBrowser() {
        if (browser != null) {
            return browser;
        } else {
            return browserRootComposite;
        }
    }

    /**
     * Returns the root composite of the browser component.
     * 
     * @return the root composite of the browser component.
     */
    public Composite getBrowserRootComposite() {
        return browserRootComposite;
    }

    /**
     * compute the semantic file extensions to restrict the choice of viewpoint based on the session.
     *
     * @param theSession
     *            the session
     * @return a collection of file extension
     */
    public Collection<String> computeSemanticFileExtensions(Session theSession) {
        final Collection<String> extensions = new HashSet<String>();
        for (final Resource resource : theSession.getSemanticResources()) {
            if (resource != null && resource.getURI() != null) {
                final String currentFileExtension = resource.getURI().fileExtension();
                if (currentFileExtension != null) {
                    extensions.add(currentFileExtension);
                }
            }
        }
        return extensions;
    }

    /**
     * Initialize graphic components.
     * 
     * @param parent
     *            the composite from which we attach this graphic component.
     * @param makeColumnsEqual
     *            If true makes the two columns of the main composite equals at layout level . If false makes the two
     *            columns not equals at layout level. Refresh the layout if a modification is done.
     */
    public void createControl(final Composite parent, boolean makeColumnsEqual) {
        rootComposite = new Composite(parent, SWT.NONE);
        rootGridLayout = GridLayoutFactory.swtDefaults().numColumns(2).equalWidth(makeColumnsEqual).create();
        rootComposite.setLayout(rootGridLayout);
        rootLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        rootComposite.setLayoutData(rootLayoutData);

        createTableViewer(rootComposite);

        createBrowser(rootComposite);
        setBrowserInput(null);

    }

    /**
     * Initialize the graphic component that is a browser allowing to see a description for each viewpoint that has the
     * focus.
     * 
     * @param parent
     *            the parent composite to be attached to.
     * @return the newly created {@link Browser}.
     */
    public Composite createBrowser(final Composite parent) {

        browserRootComposite = new Composite(parent, SWT.BORDER);
        browserRootComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        browserRootComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        browserErrorMessageComposite = new Composite(browserRootComposite, SWT.None);
        browserErrorMessageComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        browserErrorMessageLayoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
        browserErrorMessageComposite.setLayoutData(browserErrorMessageLayoutData);
        browserErrorMessageLayoutData.exclude = true;

        Composite browserComposite = new Composite(browserRootComposite, SWT.None);
        browserComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        browserComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        browserErrorMessageText = new Text(browserErrorMessageComposite, SWT.MULTI | SWT.WRAP);
        browserErrorMessageText.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
        browserErrorMessageText.setText(""); //$NON-NLS-1$
        browserErrorMessageText.setForeground(browserRootComposite.getDisplay().getSystemColor(SWT.COLOR_RED));
        try {
            browser = new Browser(browserComposite, SWT.FILL);
            browserGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
            // necessary to avoid bad interaction with expandable toolkit sections
            browserGridData.widthHint = 0;
            browserGridData.heightHint = 0;
            browser.setLayoutData(browserGridData);
            return browser;
        } catch (SWTError e) {
            if (e.code == SWT.ERROR_NO_HANDLES) {
                // Browser component could no be instantiated: log and fall back to a degraded mode using a plain text
                // widget
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, Messages.OpenViewpointSelectionBrowser_Error_Message, e));

                browserReplacementComposite = new Composite(browserComposite, SWT.None);
                browserReplacementComposite.setLayout(GridLayoutFactory.fillDefaults().create());
                browserGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
                // necessary to avoid bad interaction with expandable toolkit sections
                browserGridData.widthHint = 0;
                browserGridData.heightHint = 0;
                browserReplacementComposite.setLayoutData(browserGridData);

                browserReplacementText = new Text(browserReplacementComposite, SWT.MULTI | SWT.WRAP);
                browserReplacementText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                browserReplacementText.setText(""); //$NON-NLS-1$

                setBrowserErrorMessageText(Messages.No_Browser_Error_Message);
                return browserReplacementComposite;
            } else {
                throw e;
            }
        }

    }

    /**
     * Set an error message above the viewpoint browser.
     * 
     * @param errorMessage
     *            the error message to set.
     */
    public void setBrowserErrorMessageText(String errorMessage) {
        browserErrorMessageLayoutData.exclude = false;
        browserErrorMessageText.setText(errorMessage);
        browserErrorMessageComposite.setVisible(true);
        browserRootComposite.layout(true, true);
    }

    /**
     * Clear the error message above the viewpoint browser.
     */
    public void clearBrowserErrorMessageText() {
        browserErrorMessageLayoutData.exclude = true;
        browserErrorMessageText.setText(""); //$NON-NLS-1$
        browserErrorMessageComposite.setVisible(false);
        browserRootComposite.getParent().layout(true, true);
    }

    /**
     * Sets the minimum width the browser should have.
     * 
     * @param minwidth
     *            the minimum width to set.
     */
    public void setBrowserMinWidth(int minwidth) {
        if (browserGridData != null) {
            browserGridData.minimumWidth = minwidth;
        }
    }

    /**
     * Returns the viewer containing a checkbox for each viewpoint registered in the current runtime.
     * 
     * @return the viewer containing a checkbox for each viewpoint registered in the current runtime.
     */
    public CheckboxTableViewer getViewer() {
        return viewer;
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

        viewer = CheckboxTableViewer.newCheckList(parent, style);
        Table table = viewer.getTable();
        viewerGridData = new GridData(SWT.FILL, SWT.FILL, false, false);
        viewer.getControl().setLayoutData(viewerGridData);

        TableLayout layout = new TableLayout();
        table.setLayout(layout);
        table.setHeaderVisible(false);
        table.setLinesVisible(false);

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new ViewpointsTableLabelProvider());
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
            public int compare(Viewer theViewer, Object e1, Object e2) {
                final String e1label = new IdentifiedElementQuery((Viewpoint) e1).getLabel();
                final String e2label = new IdentifiedElementQuery((Viewpoint) e2).getLabel();
                return e1label.compareTo(e2label);
            }
        });
        return viewer;
    }

    /***
     * Set the browser input. A JFace like browser viewer would have been better.
     *
     * @param viewpoint
     *            the viewpoint to document
     */
    public void setBrowserInput(final Viewpoint viewpoint) {
        /* browser may be null if its creation fail */
        if (browser != null && viewpoint != null) {
            String content = getDocumentation(viewpoint.getEndUserDocumentation(), viewpoint, true);
            browser.setText(content);
        } else if (browserReplacementText != null && viewpoint != null) {
            String content = getDocumentation(viewpoint.getEndUserDocumentation(), viewpoint, false);
            browserReplacementText.setText(content);
        } else if (browser != null) {
            browser.setText(BROWSER_PREFIX + Messages.ViewpointsSelectionWizardPage_documentation_none + BROWSER_SUFFIX);
        } else if (browserReplacementText != null) {
            browserReplacementText.setText(BROWSER_PREFIX + Messages.ViewpointsSelectionWizardPage_documentation_none + BROWSER_SUFFIX);
        }
    }

    /***
     * Set the browser input from given representation description.
     * 
     * @param viewpoint
     *            the viewpoint to document
     * @param representationDescription
     *            the representation description to document
     */
    public void setBrowserInput(final Viewpoint viewpoint, final RepresentationDescription representationDescription) {

        /* browser may be null if its creation fail */
        if (browser != null && representationDescription != null && viewpoint != null) {
            String content = getRepresentationDescription(viewpoint, representationDescription, true);
            browser.setText(content);
        } else if (browserReplacementText != null && viewpoint != null) {
            String content = getRepresentationDescription(viewpoint, representationDescription, false);
            browserReplacementText.setText(content);
        } else if (browser != null) {
            browser.setText(BROWSER_PREFIX + Messages.ViewpointsSelectionWizardPage_documentation_none + BROWSER_SUFFIX);
        } else if (browserReplacementText != null) {
            browserReplacementText.setText(BROWSER_PREFIX + Messages.ViewpointsSelectionWizardPage_documentation_none + BROWSER_SUFFIX);
        }
    }

    private String getRepresentationDescription(final Viewpoint viewpoint, final RepresentationDescription representationDescription, boolean addHtmlContent) {
        String userDocumentation;
        EObject resource;
        if (!StringUtil.isEmpty(representationDescription.getEndUserDocumentation())) {
            userDocumentation = representationDescription.getEndUserDocumentation();
            resource = representationDescription;
        } else {
            userDocumentation = viewpoint.getEndUserDocumentation();
            resource = viewpoint;
        }
        return getDocumentation(userDocumentation, resource, addHtmlContent);
    }

    /**
     * Returns user documentation with HTML markers if supported and without if not.
     * 
     * @param endUserDocumentation
     *            the raw documentation
     * @param resource
     *            containing images and used to compute paths to show these images.
     * @param addHtmlContent
     *            true if HTML is supported. False otherwise.
     * @return user documentation with HTML markers if supported and without if not.
     */
    private String getDocumentation(String endUserDocumentation, EObject resource, boolean addHtmlContent) {
        String content;
        if (addHtmlContent && containsHTMLDocumentation(endUserDocumentation)) {
            content = getContentWhenHtml(endUserDocumentation, resource.eResource().getURI());
        } else if (addHtmlContent) {
            content = getContentWhenNoHtml(endUserDocumentation);
        } else {
            content = endUserDocumentation.replaceAll(HTML_TAG_REGEXP, "").replaceAll(HTML_TAG_REGEXP_WITHOUT_CLOSING_CHARACTER, ""); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return content;
    }

    /*
     * The following code (HTML handling ) and methods could move to another class.
     */
    private boolean containsHTMLDocumentation(String endUserDocumentation) {
        if (!StringUtil.isEmpty(endUserDocumentation)) {
            return endUserDocumentation.startsWith("<html>"); //$NON-NLS-1$
        }
        return false;
    }

    private String getContentWhenHtml(String endUserDocumentation, URI uri) {

        Set<String> urlToRewrite = new LinkedHashSet<>();
        extractUrlToRewrite(endUserDocumentation, urlToRewrite);

        return rewriteURLs(uri, endUserDocumentation, urlToRewrite);
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

    private String rewriteURLs(URI uri, String document, Set<String> urls) {

        String newDocument = document;

        for (final String url : urls) {
            newDocument = newDocument.replace(url, rewriteURL(uri, url));
        }

        StringBuilder css = new StringBuilder();
        appendCss(css);
        String headClose = "</head>"; //$NON-NLS-1$
        newDocument = newDocument.replace(headClose, css.append(headClose));

        return newDocument;
    }

    private String rewriteURL(URI uri, String url) {
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

    private String getContentWhenNoHtml(String endUserDocumentation) {
        StringBuilder content = new StringBuilder();
        return begin(content).head(content).body(content, endUserDocumentation).end(content);
    }

    private ViewpointsSelectionGraphicalHandler begin(StringBuilder content) {
        content.append("<html>"); //$NON-NLS-1$
        return this;
    }

    private ViewpointsSelectionGraphicalHandler head(StringBuilder content) {
        content.append("<head>"); //$NON-NLS-1$
        appendCss(content);
        content.append("</head>"); //$NON-NLS-1$
        return this;
    }

    private ViewpointsSelectionGraphicalHandler body(StringBuilder content, String endUserDocumentation) {
        content.append("<body>"); //$NON-NLS-1$

        if (!StringUtil.isEmpty(endUserDocumentation)) {
            content.append(endUserDocumentation);
        } else {
            content.append(Messages.ViewpointsSelectionWizardPage_documentation_none);
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
                    image = ViewpointHelper.getImage(vp);
                } else {
                    image = super.getImage(element);
                }
            }
            return image;
        }
    }

    /**
     * Sets the height the enclosing root composite of the viewpoints block must have.
     * 
     * @param height
     *            the height to set.
     */
    public void setHeight(int height) {
        viewerGridData.grabExcessVerticalSpace = false;
        viewerGridData.heightHint = height;
    }

}
