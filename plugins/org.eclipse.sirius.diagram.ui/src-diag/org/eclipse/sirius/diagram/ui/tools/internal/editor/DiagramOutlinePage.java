/******************************************************************************
 * Copyright (c) 2002, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Dmitry Stadnik (Borland) - contribution for bugzilla 136582
 *    Obeo - Extracted from org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor.DiagramOutlinePage
 *           and adapted for Sirius.
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.common.ui.tools.api.util.IObjectActionDelegateWrapper;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.business.api.provider.DEdgeLabelItemProvider;
import org.eclipse.sirius.diagram.ui.business.api.provider.DNodeLabelItemProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline.OutlineContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;

import com.google.common.collect.Lists;

/**
 * <p>
 * This class originates in GMF but was adapted to be more powerful.
 * </p>
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class DiagramOutlinePage extends AbstractExtendedContentOutlinePage {

    /** The id of the outline. 0 */
    protected static final int ID_OUTLINE = 0;

    /** The id of the over view. 1 */
    protected static final int ID_OVERVIEW = 1;

    private static final String SIRIUS_DIAGRAM_OUTLINE_SHOW_OUTLINE = "sirius.diagram.outline.show.outline"; //$NON-NLS-1$

    private static final String SIRIUS_DIAGRAM_OUTLINE_SHOW_OVERVIEW = "sirius.diagram.outline.show.overview"; //$NON-NLS-1$

    /** The diagram workbench part. */
    protected IDiagramWorkbenchPart diagramWorkbenchPart;

    /** The outline treeViewer. */
    protected TreeViewer outlineViewer;

    /** The content provider for the treeViewer. */
    protected ITreeContentProvider contentProvider;

    /** The show outline action. */
    private IAction showOutlineAction;

    /** The show overview action. */
    private IAction showOverviewAction;

    /** The default mode */
    private int defaultMode = ID_OVERVIEW;

    /** The pagebook */
    private PageBook pageBook;

    /** The outline SWT control */
    private Control outline;

    /** The label provider for the treeViewer */
    private IBaseLabelProvider labelProvider;

    /** The viewer comparator for the treeViewer */
    private ViewerComparator viewerComparator;

    /** The input of the treeViewer */
    private Object input;

    /** The overview SWT control */
    private Canvas overview;

    /** The thumbnail for the overview */
    private Thumbnail thumbnail;

    /** A dispose listener to dispose the thumbnail */
    private DisposeListener disposeListener;

    /** A boolean to save if overview is initialized */
    private boolean overviewInitialized;

    /** */
    private GraphicalViewer graphicalViewer;

    /** */
    private final Collection<IObjectActionDelegateWrapper> menuContributions = Lists.newArrayList();

    private final Collection<DiagramOutlinePageListener> listeners = Lists.newArrayList();

    /**
     * Constructor.
     * 
     * @param input
     *            the input for the outline tree viewer
     * @param labelProvider
     *            the label provider for the outline tree viewer
     * @param contentProvider
     *            the content provider for the outline tree viewer
     * @param comparator
     *            the comparator to sort element in the tree viewer
     * @param viewer
     *            the graphical viewer the graphical viewer reference
     * @param menuContributions
     *            the popup menu contribution for the outline tree viewer
     */
    public DiagramOutlinePage(final Object input, final IBaseLabelProvider labelProvider, final ITreeContentProvider contentProvider, final ViewerComparator comparator, final GraphicalViewer viewer,
            final IObjectActionDelegateWrapper[] menuContributions) {
        super();
        this.input = input;
        this.graphicalViewer = viewer;
        this.contentProvider = contentProvider;
        this.labelProvider = labelProvider;
        this.viewerComparator = comparator;
        if (menuContributions != null) {
            Collections.addAll(this.menuContributions, menuContributions);
        }
    }

    /**
     * Add a listener.
     * 
     * @param listener
     *            the listener to add
     */
    public void addListener(final DiagramOutlinePageListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove a listener.
     * 
     * @param listener
     *            the listener to remove
     */
    public void removeListener(final DiagramOutlinePageListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Give a DiagramWorkbenchPart in order to be able to get the DDiagram
     * reference.
     * 
     * @param diagramWorkbenchPart
     *            the diagram workbench part
     */
    public void setDiagramWorkbenchPart(final IDiagramWorkbenchPart diagramWorkbenchPart) {
        this.diagramWorkbenchPart = diagramWorkbenchPart;
    }

    /**
     * Returns the main control. {@inheritDoc}
     * 
     * @see AbstractExtendedContentOutlinePage#getControl()
     */
    @Override
    public Control getControl() {
        return pageBook;
    }

    /**
     * Returns the {@link EditPartViewer} instance.
     * 
     * @return the {@link EditPartViewer} instance
     */
    protected EditPartViewer getViewer() {
        return graphicalViewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see AbstractExtendedContentOutlinePage#init(org.eclipse.ui.part.IPageSite)
     */
    @Override
    public void init(final IPageSite pageSite) {
        super.init(pageSite);

        final IActionBars bars = pageSite.getActionBars();
        // Toolbar refresh to solve linux defect RATLC525198
        bars.getToolBarManager().markDirty();
    }

    private Control createOutline(final Composite parent) {

        // create the FilteredtreeViewer
        return createFilteredTreeControl(parent);

    }

    /**
     * Create a filtered tree viewer.
     * 
     * @param parent
     *            the composite parent
     * @return the created control
     */
    protected Control createFilteredTreeControl(final Composite parent) {
        super.createControl(parent);
        return super.getControl();
    }

    /**
     * {@inheritDoc}
     * 
     * @see AbstractExtendedContentOutlinePage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {

        pageBook = SWTUtil.createPageBook(parent);

        createControls(pageBook);

        final IToolBarManager tbm = this.getSite().getActionBars().getToolBarManager();

        configureControls(tbm);

        showPage(defaultMode);
    }

    /**
     * this method is intended to be subclassed.
     * 
     * @param tbm
     *            the tool bar manager
     */
    protected void configureControls(final IToolBarManager tbm) {
        // configure and initialize outline
        configureOutline(tbm);
        initializeOutline();

        // configure and initialize overview
        configureOverview(tbm);
        initializeOverview();
    }

    /**
     * this method is intended to be subclassed.
     * 
     * @param pb
     *            the page book
     */
    protected void createControls(final PageBook pb) {
        // create the outline
        outline = createOutline(pb);
        outlineViewer = super.getTreeViewer();

        // create the context menu
        final MenuManager menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(final IMenuManager mgr) {
                menuManager.removeAll();
                for (IObjectActionDelegateWrapper menuContribution : menuContributions) {
                    if (menuContribution.isEnabled()) {
                        menuManager.add(menuContribution);
                    }
                }
            }
        });
        outlineViewer.getTree().setMenu(menuManager.createContextMenu(outlineViewer.getTree()));

        // create the overview
        overview = new Canvas(pb, SWT.NONE);
        if (contentProvider instanceof DiagramOutlinePageListener) {
            this.addListener((DiagramOutlinePageListener) contentProvider);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.outline.AbstractExtendedContentOutlinePage#dispose()
     */
    @Override
    public void dispose() {
        if (disposeListener != null) {
            if (getEditor() != null && !getEditor().isDisposed()) {
                getEditor().removeDisposeListener(disposeListener);
            }
            disposeListener = null;
        }

        IToolBarManager tbm = this.getSite().getActionBars().getToolBarManager();
        if (showOutlineAction != null) {
            tbm.remove(SIRIUS_DIAGRAM_OUTLINE_SHOW_OUTLINE);
            showOutlineAction = null;
        }
        if (showOverviewAction != null) {
            tbm.remove(SIRIUS_DIAGRAM_OUTLINE_SHOW_OVERVIEW);
            showOverviewAction = null;
        }

        if (thumbnail != null) {
            thumbnail.deactivate();
            thumbnail = null;
        }
        if (overview != null) {
            overview.dispose();
            overview = null;
        }
        overviewInitialized = false;

        pageBook.dispose();
        pageBook = null;
        contentProvider.dispose();
        contentProvider = null;
        labelProvider.dispose();
        labelProvider = null;
        viewerComparator = null;
        outlineViewer.getTree().dispose();
        outlineViewer = null;
        outline.dispose();
        outline = null;

        listeners.clear();
        menuContributions.clear();

        diagramWorkbenchPart = null;
        graphicalViewer = null;
        input = null;
        super.dispose();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.outline.AbstractExtendedContentOutlinePage#createStructuredSelectionWrapper(org.eclipse.jface.viewers.IStructuredSelection)
     */
    @Override
    protected IStructuredSelection createStructuredSelectionWrapper(IStructuredSelection originalSelection) {
        final List<Object> elements = new ArrayList<Object>(originalSelection.toList().size());
        final Iterator<?> it = originalSelection.toList().iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (obj instanceof EditPart) {
                final EditPart editPart = (EditPart) obj;
                // This method is overridden to take LabelEditParts in account
                if ((editPart instanceof LabelEditPart) && (editPart.getModel() instanceof View)) {
                    if (((View) (editPart.getModel())).getElement() instanceof DNode) {
                        DNode parentDNode = null;
                        parentDNode = (DNode) ((View) (editPart.getModel())).getElement();
                        if (this.contentProvider instanceof OutlineContentProvider) {
                            elements.add(new DNodeLabelItemProvider(((OutlineContentProvider) this.contentProvider).getAdapterFactory(), parentDNode));
                        }
                    } else if (((View) (editPart.getModel())).getElement() instanceof DEdge) {
                        DEdge parentDEdge = null;
                        parentDEdge = (DEdge) ((View) (editPart.getModel())).getElement();
                        if (this.contentProvider instanceof OutlineContentProvider) {
                            elements.add(new DEdgeLabelItemProvider(((OutlineContentProvider) this.contentProvider).getAdapterFactory(), parentDEdge));
                            // elements.add(new
                            // DEdgeBeginLabelItemProvider(((OutlineContentProvider)
                            // this.contentProvider).getAdapterFactory(),
                            // parentDEdge));
                            // elements.add(new
                            // DEdgeEndLabelItemProvider(((OutlineContentProvider)
                            // this.contentProvider).getAdapterFactory(),
                            // parentDEdge));
                        }
                    }
                } else {
                    if (editPart.getModel() instanceof View) {
                        final EObject element = ((View) (editPart.getModel())).getElement();
                        if (element != null) {
                            elements.add(element);
                        }
                    }
                }
            } else {
                elements.add(obj);
            }
        }
        return new StructuredSelection(elements);
    }

    /**
     * Configures the outline.
     * 
     * @param tbm
     *            The {@link IToolBarManager} instance
     */
    protected void configureOutline(final IToolBarManager tbm) {

        showOutlineAction = new Action() {
            @Override
            public void run() {
                showPage(ID_OUTLINE);
            }
        };
        showOutlineAction.setImageDescriptor(IDiagramOutlinePage.DESC_OUTLINE);
        showOutlineAction.setToolTipText(IDiagramOutlinePage.OUTLINE_VIEW_OUTLINE_TIP_TEXT);
        showOutlineAction.setId(SIRIUS_DIAGRAM_OUTLINE_SHOW_OUTLINE);
        tbm.add(showOutlineAction);
    }

    /**
     * Configures the overview.
     * 
     * @param tbm
     *            The {@link IToolBarManager} instance.
     */
    protected void configureOverview(final IToolBarManager tbm) {

        showOverviewAction = new Action() {
            @Override
            public void run() {
                showPage(ID_OVERVIEW);
            }
        };
        showOverviewAction.setImageDescriptor(IDiagramOutlinePage.DESC_OVERVIEW);
        showOverviewAction.setToolTipText(IDiagramOutlinePage.OUTLINE_VIEW_OVERVIEW_TIP_TEXT);
        showOverviewAction.setId(SIRIUS_DIAGRAM_OUTLINE_SHOW_OVERVIEW);
        tbm.add(showOverviewAction);
    }

    /**
     * Initializes the outline.
     */
    protected void initializeOutline() {
        outlineViewer.setContentProvider(this.contentProvider);
        outlineViewer.setLabelProvider(this.labelProvider);
        outlineViewer.setComparator(this.viewerComparator);
        outlineViewer.addSelectionChangedListener(this);
        outlineViewer.setInput(input);
    }

    /**
     * Initializes the overview.
     */
    protected void initializeOverview() {
        final LightweightSystem lws = new LightweightSystem(overview);
        final RootEditPart rep = graphicalViewer.getRootEditPart();
        final DiagramRootEditPart root = (DiagramRootEditPart) rep;
        thumbnail = new ScrollableThumbnail((Viewport) root.getFigure());
        thumbnail.setSource(root.getLayer(LayerConstants.SCALABLE_LAYERS));

        lws.setContents(thumbnail);
        disposeListener = new DisposeListener() {

            @Override
            public void widgetDisposed(final DisposeEvent e) {
                if (thumbnail != null) {
                    thumbnail.deactivate();
                    thumbnail = null;
                }
            }
        };
        getEditor().addDisposeListener(disposeListener);
        this.overviewInitialized = true;
    }

    /**
     * Uncheck the actions provided by this class.
     */
    protected void uncheckProvidedActions() {
        showOutlineAction.setChecked(false);
        showOverviewAction.setChecked(false);
        if (thumbnail != null) {
            thumbnail.setVisible(false);
        }
        if (input instanceof Diagram) {
            final Diagram diagram = (Diagram) input;
            final EObject element = diagram.getElement();
            if (element != null) {
                for (final DiagramOutlinePageListener listener : listeners) {
                    listener.deactivate(DiagramOutlinePageListener.OUTLINE);
                    listener.deactivate(DiagramOutlinePageListener.OVERVIEW);
                }
            }
        }
    }

    /**
     * Ask the pagebook to show the given control.
     * 
     * @param control
     *            the control
     */
    protected void showPage(final Control control) {
        pageBook.showPage(control);
    }

    /**
     * Show page with a specific ID, possible values are ID_OUTLINE and
     * ID_OVERVIEW.
     * 
     * @param id
     *            {@link DiagramOutlinePage#ID_OUTLINE} or
     *            {@link DiagramOutlinePage#ID_OVERVIEW}
     */
    protected void showPage(final int id) {
        switch (id) {

        case ID_OUTLINE:
            showOutlineAction.setChecked(true);
            showOverviewAction.setChecked(false);
            showPage(outline);
            if (thumbnail != null) {
                thumbnail.setVisible(false);
            }
            /* activation of the model adapter + refresh */
            if (input instanceof Diagram) {
                final Diagram diagram = (Diagram) input;
                final EObject element = diagram.getElement();
                if (element != null) {
                    for (final DiagramOutlinePageListener listener : listeners) {
                        listener.activate(DiagramOutlinePageListener.OUTLINE);
                        listener.deactivate(DiagramOutlinePageListener.OVERVIEW);
                    }
                }
            }
            break;

        case ID_OVERVIEW:
            if (!overviewInitialized) {
                initializeOverview();
            }
            showOutlineAction.setChecked(false);
            showOverviewAction.setChecked(true);
            showPage(overview);
            if (thumbnail != null) {
                thumbnail.setVisible(true);
            }
            /* deactivation of the model adapter */
            if (input instanceof Diagram) {
                final Diagram diagram = (Diagram) input;
                final EObject element = diagram.getElement();
                if (element != null) {
                    for (final DiagramOutlinePageListener listener : listeners) {
                        listener.deactivate(DiagramOutlinePageListener.OUTLINE);
                        listener.activate(DiagramOutlinePageListener.OVERVIEW);
                    }
                }
            }
            break;

        default:
            break;

        }
    }

    /**
     * Get the editor control.
     * 
     * @return <code>Control</code>
     */
    public Control getEditor() {
        return graphicalViewer.getControl();
    }

    /**
     * Warn selection change to the menu contributions. {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.outline.AbstractExtendedContentOutlinePage#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {

        super.selectionChanged(event);

        for (final IObjectActionDelegateWrapper action : menuContributions) {
            action.getWrappedAction().selectionChanged(null, new TreeSelectionWrapper((IStructuredSelection) (event.getSelection()), this.graphicalViewer));
            action.selectionChanged(new TreeSelectionWrapper((IStructuredSelection) (event.getSelection()), this.graphicalViewer));
        }
    }

    /**
     * A Wrapper of TreeSelection which store a {@link GraphicalViewer}
     * instance.
     * 
     * @author Mariot Chauvin (mchauvin)
     */
    public static class TreeSelectionWrapper implements IStructuredSelection {

        /** The {@link GraphicalViewer} instance */
        private GraphicalViewer viewer;

        /** The wrapped selection */
        private IStructuredSelection selection;

        /**
         * Constructor.
         * 
         * @param selection
         *            the wrapped selection
         * @param viewer
         *            the graphical viewer
         */
        public TreeSelectionWrapper(final IStructuredSelection selection, final GraphicalViewer viewer) {
            this.selection = selection;
            this.viewer = viewer;
        }

        /**
         * Get the graphical viewer.
         * 
         * @return the graphical viewer
         */
        public GraphicalViewer getViewer() {
            return this.viewer;
        }

        /**
         * Get the root edit part.
         * 
         * @return the root edit part
         */
        public RootEditPart getRoot() {
            return this.viewer.getRootEditPart();
        }

        /* wrapped methods */

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ISelection#isEmpty()
         */
        @Override
        public boolean isEmpty() {
            return selection.isEmpty();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IStructuredSelection#getFirstElement()
         */
        @Override
        public Object getFirstElement() {
            return selection.getFirstElement();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IStructuredSelection#iterator()
         */
        @Override
        public Iterator<?> iterator() {
            return selection.iterator();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IStructuredSelection#size()
         */
        @Override
        public int size() {
            return selection.size();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IStructuredSelection#toArray()
         */
        @Override
        public Object[] toArray() {
            return selection.toArray();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.IStructuredSelection#toList()
         */
        @Override
        public List<?> toList() {
            return selection.toList();
        }
    }

}
