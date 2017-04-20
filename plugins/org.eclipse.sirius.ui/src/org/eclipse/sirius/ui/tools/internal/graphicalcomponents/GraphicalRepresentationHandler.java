/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.graphicalcomponents;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointsSelectionGraphicalHandler;
import org.eclipse.sirius.ui.tools.internal.views.common.action.DeleteRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.ManageSessionActionProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonContentProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.sorter.CommonItemSorter;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.DeleteActionHandler;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.RenameActionHandler;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateRepresentationWizard;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SiriusRepresentationWithInactiveStatusLabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * This graphical component provides a {@link TreeViewer} showing all representations belonging to the given session
 * under corresponding representation descriptors under corresponding registered viewpoints objects.
 * 
 * This component have different optional functionalities that can be activated or not by construction:
 * 
 * - Add addition and removal representations buttons and key shortcut and viewpoint activation/deactivation mechanism.
 * 
 * - Add a browser showing informations about selected viewpoints and representation descriptions.
 * 
 * - Add custom label and content provider to customize what is shown in the viewer. The content provider must provide
 * only a composition of {@link ViewpointItemImpl}, {@link RepresentationDescriptionItemImpl} and
 * {@link RepresentationDescriptionItem}. Some items type can be not present.
 * 
 * - Filter {@link ViewpointItemImpl} that have no children computed by tree viewer content provider used that is either
 * the default one or a given optional provider.
 * 
 * - Use a {@link FormToolkit} to create graphic components.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class GraphicalRepresentationHandler implements SessionManagerListener {

    /**
     * Session from which representations are handled.
     */
    private Session session;

    /**
     * The Form Toolkit to use to create & configure the controls.
     */
    private FormToolkit toolkit;

    /**
     * Sirius content provider providing expandable viewpoints showing associated representations loaded and available
     * from the session.
     */
    private SiriusCommonContentProvider siriusCommonContentProvider;

    /**
     * The button used to remove external semantic model reference and representations from the session.
     */
    private Button removeSemanticModelOrRepresentationButton;

    /**
     * The viewer showing all viewpoints containing representations loaded from the given session.
     */
    private TreeViewer treeViewer;

    /**
     * The {@link MenuManager} for this component.
     */
    private MenuManager menuManager;

    /**
     * The component providing actions available for representations element in the viewer.
     */
    private ManageSessionActionProvider manageSessionActionProvider;

    /**
     * The filtered tree showing representations.
     */
    private FilteredTree representationTree;

    /**
     * Handler allowing to delete a representation.
     */
    private Action deleteActionHandler;

    /**
     * Handler allowing to rename a representation.
     */
    private Action renameActionHandler;

    /**
     * The graphic component contains the browser used to show information about viewpoints.
     */
    private ViewpointsSelectionGraphicalHandler viewpointsSelectionGraphicalHandler;

    /**
     * If true buttons allowing to add/delete representation are shown. If false they are not shown and cannot be use to
     * add/remove representation or activate/deactivate viewpoints.
     */
    private boolean showButtons;

    /**
     * If true any selection on a viewpoint or representation description in the tree viewer must update the browser
     * part with corresponding design description.
     */
    private boolean linkNavigatorAndBrowser;

    /**
     * If true key shortcut and menu action to add/delete representation are activated as well menus and double click
     * listener to activate/deactivate viewpoint. If false, these menu actions, key shortcut and double click listener
     * are not activated.
     */
    private boolean addUpdateControls;

    /**
     * Label provider to use to display style of viewpoints, representations descriptions and instances in the tree
     * viewer.
     */
    private ILabelProvider labelProvider;

    /**
     * Content provider showing viewpoints and/or representation description and or representation instances in the tree
     * viewer. It must provide only a composition of {@link ViewpointItemImpl},
     * {@link RepresentationDescriptionItemImpl} and {@link RepresentationDescriptionItem}. Some items type can be not
     * present.
     */
    private ITreeContentProvider contentProvider;

    /**
     * True if {@link ViewpointItemImpl} that have no children computed by tree viewer content provider used that is
     * either the default one or a given optional provider.
     */
    private boolean filterEmptyViewpoint;

    /**
     * This builder allow to build the graphical componant handling viewpoint and representation with wanted optional
     * functionalities available.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public static class GraphicalRepresentationHandlerBuilder {
        /**
         * The graphic component contains the browser used to show information about viewpoints.
         */
        private ViewpointsSelectionGraphicalHandler viewpointsSelectionGraphicalHandler;

        /**
         * Session from which representations are handled.
         */
        private Session session; // mandatory

        /**
         * The Form Toolkit to use to create & configure the controls.
         */
        private FormToolkit toolkit; // mandatory

        /**
         * If true buttons allowing to add/delete representation are shown. If false they are not shown and cannot be
         * use to add/remove representation or activate/deactivate viewpoints.
         */
        private boolean showButtons; // optional

        /**
         * If true any selection on a viewpoint or representation description in the tree viewer must update the browser
         * part with corresponding design description.
         */
        private boolean linkNavigatorAndBrowser; // optional

        /**
         * If true key shortcut and menu action to add/delete representation are activated as well menus and double
         * click listener to activate/deactivate viewpoint. If false, these menu actions, key shortcut and double click
         * listener are not activated.
         */
        private boolean addUpdateControls; // optional

        /**
         * Label provider to use to display style of viewpoints, representations descriptions and instances in the tree
         * viewer.
         */
        private ILabelProvider labelProvider; // optional

        /**
         * Content provider showing viewpoints and/or representation description and or representation instances in the
         * tree viewer. It must provide only a composition of {@link ViewpointItemImpl},
         * {@link RepresentationDescriptionItemImpl} and {@link RepresentationDescriptionItem}. Some items type can be
         * not present.
         */
        private ITreeContentProvider contentProvider; // optional

        /**
         * True if {@link ViewpointItemImpl} that have no children computed by tree viewer content provider used that is
         * either the default one or a given optional provider.
         */
        private boolean filterEmptyViewpoint; // optional

        /**
         * Construct a GraphicalRepresentationHandler allowing to visualize and select in a tree viewer all registered
         * viewpoints and their representation descriptions and instance.
         * 
         * @param theSession
         *            the session used by the component to handle semantic models lifecycle.
         */
        public GraphicalRepresentationHandlerBuilder(Session theSession) {
            this.session = theSession;
            viewpointsSelectionGraphicalHandler = null;
            /**
             * If true buttons allowing to add/delete representation, to activate/deactivate viewpoint and associated
             * key shortcut and menu actions are shown. If representations cannot be modified and viewpoints status
             * cannot be changed.
             */
            showButtons = false;
            linkNavigatorAndBrowser = false;
            addUpdateControls = false;
            filterEmptyViewpoint = false;
            labelProvider = null;
            contentProvider = null;
        }

        /**
         * Returns the builder with the functionality "Use a {@link FormToolkit} to create graphic components."
         * activated.
         * 
         * @param theToolkit
         *            the toolkit to use to create & configure the controls.
         * @return the builder with the functionality "Use a {@link FormToolkit} to create graphic components."
         *         activated.
         */
        public GraphicalRepresentationHandlerBuilder useToolkitToCreateGraphicComponents(FormToolkit theToolkit) {
            this.toolkit = theToolkit;
            return this;
        }

        /**
         * Returns the builder with the functionality "Add addition and removal representations buttons and key shortcut
         * and viewpoint activation/deactivation mechanism." activated.
         * 
         * @return the builder with the functionality "Add addition and removal representations buttons and key shortcut
         *         and viewpoint activation/deactivation mechanism." activated.
         */
        public GraphicalRepresentationHandlerBuilder activateRepresentationAndViewpointControls() {
            addUpdateControls = true;
            showButtons = true;
            return this;
        }

        /**
         * Returns the builder with the functionality "Add a browser showing informations about selected viewpoints and
         * representation descriptions." activated.
         * 
         * @return the builder with the functionality "Add a browser showing informations about selected viewpoints and
         *         representation descriptions." activated.
         */
        public GraphicalRepresentationHandlerBuilder activateBrowserWithViewpointAndRepresentationDescriptionInformation() {
            linkNavigatorAndBrowser = true;
            viewpointsSelectionGraphicalHandler = new ViewpointsSelectionGraphicalHandler();
            return this;
        }

        /**
         * Returns the builder with the functionality "Add custom label and content provider to customize what is shown
         * in the viewer. The content provider must provide only a composition of {@link ViewpointItemImpl},
         * {@link RepresentationDescriptionItemImpl} and {@link RepresentationDescriptionItem}. Some items type can be
         * not present." activated
         * 
         * @param theContentProvider
         *            Content provider showing viewpoints and/or representation description and or representation
         *            instances in the tree viewer. It must provide only a composition of {@link ViewpointItemImpl},
         *            {@link RepresentationDescriptionItemImpl} and {@link RepresentationDescriptionItem}. Some items
         *            type can be not present.
         * @param theLabelProvider
         *            Used to display style of viewpoints, representations descriptions and instances in the tree
         *            viewer.
         * @return the builder with the functionality "Add custom label and content provider to customize what is shown
         *         in the viewer. The content provider must provide only a composition of {@link ViewpointItemImpl},
         *         {@link RepresentationDescriptionItemImpl} and {@link RepresentationDescriptionItem}. Some items type
         *         can be not present." activated
         */
        public GraphicalRepresentationHandlerBuilder customizeContentAndLabel(ITreeContentProvider theContentProvider, ILabelProvider theLabelProvider) {
            labelProvider = theLabelProvider;
            contentProvider = theContentProvider;
            return this;
        }

        /**
         * Returns the builder with the functionality "Filter {@link ViewpointItemImpl} that have no children computed
         * by tree viewer content provider used that is either the default one or a given optional provider." activated.
         * 
         * @return the builder with the functionality "Filter {@link ViewpointItemImpl} that have no children computed
         *         by tree viewer content provider used that is either the default one or a given optional provider."
         *         activated.
         */
        public GraphicalRepresentationHandlerBuilder filterEmptyViewpoints() {
            filterEmptyViewpoint = true;
            return this;
        }

        /**
         * Builds a new instance of {@link GraphicalRepresentationHandler}.
         * 
         * @return a new instance of {@link GraphicalRepresentationHandler}.
         */
        public GraphicalRepresentationHandler build() {
            return new GraphicalRepresentationHandler(this);
        }

    }

    /**
     * Creates the GraphicalRepresentationHandler from the given builder.
     * 
     * @param builder
     *            the builder from which the new GraphicalRepresentationHandler is created.
     */
    public GraphicalRepresentationHandler(GraphicalRepresentationHandlerBuilder builder) {
        this.session = builder.session;
        this.toolkit = builder.toolkit;
        this.viewpointsSelectionGraphicalHandler = builder.viewpointsSelectionGraphicalHandler;
        this.showButtons = builder.showButtons;
        this.linkNavigatorAndBrowser = builder.linkNavigatorAndBrowser;
        this.addUpdateControls = builder.addUpdateControls;
        this.filterEmptyViewpoint = builder.filterEmptyViewpoint;
        this.contentProvider = builder.contentProvider;
        this.labelProvider = builder.labelProvider;
    }

    /**
     * Return the {@link TreeViewer} showing all representations usable under their viewpoint.
     * 
     * @return the {@link TreeViewer} showing all representations usable under their viewpoint.
     */
    public TreeViewer getTreeViewer() {
        return treeViewer;
    }

    /**
     * Launch a representation deletion or renaming if the right key is used and the selected element is a
     * representation.
     * 
     * @param event
     */
    private void handleKeyReleased(KeyEvent event) {
        if (event.stateMask != 0)
            return;

        int key = event.keyCode;
        if (key == SWT.DEL) {
            if (deleteActionHandler.isEnabled()) {
                deleteActionHandler.run();
            }
        } else if (key == SWT.F2) {
            if (renameActionHandler.isEnabled()) {
                renameActionHandler.run();
            }
        }

    }

    /**
     * Create the graphic composites and the {@link TreeViewer} and initialize it with representation from the session.
     * 
     * @param parentComposite
     *            the composite to be attached to.
     */
    public void createControl(Composite parentComposite) {
        viewpointsSelectionGraphicalHandler = new ViewpointsSelectionGraphicalHandler();
        Composite rootComposite = new Composite(parentComposite, SWT.NONE);
        GridLayout rootGridLayout = null;
        if (showButtons) {
            rootGridLayout = GridLayoutFactory.swtDefaults().numColumns(3).equalWidth(false).create();
        } else {
            rootGridLayout = GridLayoutFactory.swtDefaults().numColumns(2).equalWidth(true).create();
        }

        rootComposite.setLayout(rootGridLayout);
        GridData rootLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        rootComposite.setLayoutData(rootLayoutData);
        treeViewer = createRepresentationExplorerNavigator(rootComposite);
        GridData layoutData = (GridData) treeViewer.getTree().getLayoutData();
        // setting height hint avoids the composite to grow outside visible
        // port when too much item are present.
        layoutData.heightHint = 50;
        viewpointsSelectionGraphicalHandler.createBrowser(rootComposite);
        viewpointsSelectionGraphicalHandler.setBrowserMinWidth(200);
        SessionManager.INSTANCE.addSessionsListener(this);

    }

    /**
     * Update the representations viewer with the representation currently available in the session and expand items to
     * level 2 and set selection to the first item.
     * 
     * @param input
     *            the input used to update the viewer.
     */
    public void setViewerInput(Object input) {
        treeViewer.setInput(input);
        treeViewer.expandToLevel(2);
        if (treeViewer.getTree().getItemCount() > 0) {
            treeViewer.setSelection(new StructuredSelection(treeViewer.getTree().getItem(0).getData()));
        }

    }

    /**
     * Create Representation explorer navigator.
     * 
     * @param parent
     *            the parent composite.
     * @return the {@link TreeViewer} allowing the navigation.
     */
    private TreeViewer createRepresentationExplorerNavigator(Composite parent) {
        Composite subComposite = null;
        if (toolkit != null) {
            subComposite = toolkit.createComposite(parent, SWT.NONE);
        } else {
            subComposite = new Composite(parent, SWT.NONE);
        }
        subComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.widthHint = 350;
        subComposite.setLayoutData(layoutData);
        representationTree = SWTUtil.createFilteredTree(subComposite, SWT.BORDER | SWT.MULTI, new org.eclipse.ui.dialogs.PatternFilter());
        treeViewer = representationTree.getViewer();
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.minimumHeight = 200;
        treeViewer.getControl().setLayoutData(gridData);
        treeViewer.getTree().setHeaderVisible(false);
        treeViewer.getTree().setLinesVisible(false);
        final ITreeContentProvider contentProviderToUse;
        if (contentProvider != null) {
            contentProviderToUse = contentProvider;
        } else {
            siriusCommonContentProvider = new SiriusCommonContentProvider();
            contentProviderToUse = siriusCommonContentProvider;
        }
        treeViewer.setContentProvider(contentProviderToUse);
        if (labelProvider != null) {
            treeViewer.setLabelProvider(labelProvider);
        } else {
            treeViewer.setLabelProvider(new SiriusRepresentationWithInactiveStatusLabelProvider());
        }
        treeViewer.setSorter(new CommonItemSorter());

        if (addUpdateControls) {
            bindKeyToActions();
            initializeMenusAndActions();

            treeViewer.addDoubleClickListener(new ViewpointActivationAndRepresentationCreationDoubleClickListener());
        }

        if (showButtons) {
            createRepresentationExplorerButton(subComposite, treeViewer);
            treeViewer.addSelectionChangedListener(new UpdateButtonsAtSelectionChangeListener());
            treeViewer.addSelectionChangedListener(new UpdateButtonAtSelectionChangeListener());

        }

        if (linkNavigatorAndBrowser) {
            treeViewer.addSelectionChangedListener(new UpdateBrowserAtSelectionChangeListener());
        }

        if (filterEmptyViewpoint) {
            treeViewer.addFilter(new ViewerFilter() {

                @Override
                public boolean select(Viewer viewer, Object parentElement, Object element) {
                    // we don't show the viewpoint without representation description.
                    if (element instanceof ViewpointItem && contentProviderToUse.getChildren(element).length == 0) {
                        return false;
                    }
                    return true;
                }
            });
        }
        return treeViewer;
    }

    /**
     * Bind DEL and F2 keys to delete and rename actions.
     */
    private void bindKeyToActions() {
        deleteActionHandler = new DeleteActionHandler(treeViewer);
        renameActionHandler = new RenameActionHandler(treeViewer);
        treeViewer.getControl().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                handleKeyReleased(event);
            }
        });
    }

    /**
     * Initializes all menus and actions for representation blocks.
     */
    private void initializeMenusAndActions() {
        menuManager = new MenuManager();
        menuManager.addMenuListener(new IMenuListener() {

            @Override
            public void menuAboutToShow(IMenuManager manager) {
                manageSessionActionProvider.setContext(new ActionContext(treeViewer.getSelection()));
                manageSessionActionProvider.fillContextMenu(menuManager);
            }

        });
        Menu menu = menuManager.createContextMenu(treeViewer.getControl());
        menuManager.setRemoveAllWhenShown(true);
        manageSessionActionProvider = new ManageSessionActionProvider();
        manageSessionActionProvider.initFromViewer(treeViewer);
        treeViewer.getControl().setMenu(menu);
    }

    /**
     * Activate or deactivate the viewpoint of the given {@link ViewpointItemImpl} regarding the activation parameter.
     * 
     * @param viewpointItem
     *            the {@link ViewpointItemImpl} from which the viewpoint should be activated or deactivated.
     * @param changedViewpoint
     *            the viewpoint that will be deactivated or activated.
     * @param selectedViewpoints
     *            the viewpoint that are currently selected in the session.
     * @param activateViewpoint
     *            true if the viewpoint should be activated. False otherwise.
     */
    private void handleViewpointActivation(ViewpointItemImpl viewpointItem, Viewpoint changedViewpoint, Collection<Viewpoint> selectedViewpoints, boolean activateViewpoint) {
        treeViewer.getTree().setRedraw(false);
        final SortedMap<Viewpoint, Boolean> originalViewpointsMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
        Collection<Viewpoint> availableViewpoints = viewpointsSelectionGraphicalHandler.getAvailableViewpoints(session);
        for (final Viewpoint viewpoint : availableViewpoints) {
            boolean selected = false;

            for (Viewpoint selectedViewpoint : selectedViewpoints) {
                if (EqualityHelper.areEquals(selectedViewpoint, viewpoint)) {
                    selected = true;
                    break;
                }
            }
            originalViewpointsMap.put(viewpoint, Boolean.valueOf(selected));
        }
        SortedMap<Viewpoint, Boolean> newViewpointToSelectionStateMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
        newViewpointToSelectionStateMap.putAll(originalViewpointsMap);
        newViewpointToSelectionStateMap.put(changedViewpoint, !viewpointItem.isViewpointEnabledInSession());

        // we also deselect viewpoint that will be missing a
        // dependency if such element exists.
        Set<Viewpoint> viewpointsMissingDependencies = ViewpointHelper
                .getViewpointsMissingDependencies(newViewpointToSelectionStateMap.keySet().stream().filter(viewpoint -> newViewpointToSelectionStateMap.get(viewpoint)).collect(Collectors.toSet()));
        for (Viewpoint viewpointsMissingDependency : viewpointsMissingDependencies) {
            newViewpointToSelectionStateMap.put(viewpointsMissingDependency, false);
        }

        ViewpointHelper.applyNewViewpointSelection(originalViewpointsMap, newViewpointToSelectionStateMap, session, true);

        // if deactivation has been cancelled by user because an editor is
        // currently open with one of the viewpoint representations, then we
        // rollback all deactivation.
        if ((activateViewpoint && !viewpointItem.isViewpointEnabledInSession()) || (!activateViewpoint && viewpointItem.isViewpointEnabledInSession())) {
            ViewpointHelper.applyNewViewpointSelection(newViewpointToSelectionStateMap, originalViewpointsMap, session, false);
        }
        treeViewer.getTree().setRedraw(true);
        treeViewer.refresh();
        treeViewer.setSelection(new StructuredSelection(viewpointItem));
    }

    /**
     * Return an error message referencing all missing dependencies for the given viewpoint or null if no missing
     * dependencies exists.
     * 
     * @param viewpoint
     *            the viewpoint from which we check if it has missing dependencies among activated viewpoints.
     * @param selectedViewpoints
     *            the current activated viewpoints.
     * @return an error message referencing all missing dependencies for the given viewpoint or null if no missing
     *         dependencies exists.
     */
    protected String getMissingDependencyErrorMessage(Viewpoint viewpoint, Collection<Viewpoint> selectedViewpoints) {
        Set<Viewpoint> viewpoints = Sets.newHashSet(selectedViewpoints);
        viewpoints.add(viewpoint);
        Map<String, Collection<String>> missingDependencies = ViewpointSelection.getMissingDependencies(viewpoints);
        if (missingDependencies != null && missingDependencies.get(viewpoint.getName()) != null) {
            return MessageFormat.format(Messages.GraphicalRepresentationHandler_missingDependencies_requirements, viewpoint.getName(),
                    missingDependencies.get(viewpoint.getName()).stream().collect(Collectors.joining(", "))); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Create control buttons allowing to add/remove representations.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRepresentationExplorerButton(Composite parent, final TreeViewer theTreeViewer) {
        Composite subComposite = null;
        if (toolkit != null) {
            subComposite = toolkit.createComposite(parent, SWT.NONE);
        } else {
            subComposite = new Composite(parent, SWT.NONE);
        }
        subComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).create());
        GridData layoutData = new GridData(SWT.LEFT, SWT.TOP, false, false);
        layoutData.widthHint = 70;
        subComposite.setLayoutData(layoutData);
        Composite buttonsComposite = null;
        if (toolkit != null) {
            buttonsComposite = toolkit.createComposite(subComposite, SWT.NONE);
        } else {
            buttonsComposite = new Composite(subComposite, SWT.NONE);
        }
        FillLayout buttonsLayout = new FillLayout(SWT.BEGINNING);
        buttonsLayout.spacing = 5;
        buttonsComposite.setLayout(buttonsLayout);
        addButton(buttonsComposite, Messages.GraphicalRepresentationHandler_button_newRepresentation, () -> {
            CreateRepresentationWizard wizard = new CreateRepresentationWizard(session);
            wizard.init();
            final WizardDialog dialog = new WizardDialog(parent.getShell(), wizard);
            dialog.setMinimumPageSize(CreateRepresentationWizard.MIN_PAGE_WIDTH, CreateRepresentationWizard.MIN_PAGE_HEIGHT);
            dialog.create();
            dialog.getShell().setText(Messages.GraphicalRepresentationHandler_CreateRepresentationWizard_title);
            dialog.open();
        });
        removeSemanticModelOrRepresentationButton = addButton(buttonsComposite, Messages.GraphicalRepresentationHandler_button_removeRepresentation, () -> {
            if (theTreeViewer != null) {
                final IStructuredSelection selection = (IStructuredSelection) theTreeViewer.getSelection();
                Collection<?> selectedObjects = selection.toList();
                if (!selectedObjects.isEmpty()) {
                    Set<DRepresentationDescriptor> representationDescriptors = selectedObjects.stream().filter(object -> object instanceof RepresentationItemImpl)
                            .map(object -> ((RepresentationItemImpl) object).getDRepresentationDescriptor()).collect(Collectors.toSet());
                    DeleteRepresentationAction deleteRepresentationAction = new DeleteRepresentationAction(representationDescriptors);
                    deleteRepresentationAction.run();
                }
                theTreeViewer.refresh();
            }
        });
        removeSemanticModelOrRepresentationButton.setEnabled(false);
        deleteActionHandler.setEnabled(false);
        renameActionHandler.setEnabled(false);
    }

    /**
     * Helper method to add an action button to the view.
     * 
     * @param parent
     *            parent Composite where button is displayed
     * @param name
     *            name of the button
     * @param body
     *            action to launch when button is pushed
     * @return the newly created button.
     */
    protected Button addButton(Composite parent, final String name, final Runnable body) {
        Button button = null;
        if (toolkit != null) {
            button = toolkit.createButton(parent, name, SWT.PUSH);
        } else {
            button = new Button(parent, SWT.PUSH);
            button.setText(name);
        }
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (body != null) {
                    body.run();
                }
            }
        });
        return button;
    }

    /**
     * Dispose all listeners.
     */
    public void dispose() {
        SessionManager.INSTANCE.removeSessionsListener(this);
        session = null;
        treeViewer = null;
        manageSessionActionProvider = null;
        siriusCommonContentProvider = null;
        menuManager = null;
        toolkit = null;
    }

    @Override
    public void notifyAddSession(Session newSession) {
    }

    @Override
    public void notifyRemoveSession(Session removedSession) {
        if (siriusCommonContentProvider != null) {
            siriusCommonContentProvider.removeRefreshViewerTriggers(removedSession);
        }
    }

    @Override
    public void viewpointSelected(Viewpoint selectedSirius) {
    }

    @Override
    public void viewpointDeselected(Viewpoint deselectedSirius) {
    }

    @Override
    public void notify(Session updated, int notification) {
        if (session.equals(updated)) {
            switch (notification) {
            case SessionListener.DIRTY:
                PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                    if (!representationTree.isDisposed()) {
                        treeViewer.refresh();
                    }
                });
                break;

            case SessionListener.OPENED:
                PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
                    if (!representationTree.isDisposed()) {
                        treeViewer.refresh();
                        if (siriusCommonContentProvider != null) {
                            siriusCommonContentProvider.addRefreshViewerTrigger(updated);
                        }
                    }
                });

                break;

            default:
                // do nothing as we will be notified in other way
                break;
            }
        }
    }

    /**
     * Double click listener activating/deactivating viewpoints from viewpoints item and launching representation
     * creation wizard from representation descriptor.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class ViewpointActivationAndRepresentationCreationDoubleClickListener implements IDoubleClickListener {
        @Override
        public void doubleClick(DoubleClickEvent event) {
            if (event.getSelection() instanceof StructuredSelection) {
                StructuredSelection selection = (StructuredSelection) event.getSelection();

                if (selection.getFirstElement() instanceof ViewpointItemImpl) {
                    ViewpointItemImpl viewpointItem = (ViewpointItemImpl) selection.getFirstElement();
                    Viewpoint changedViewpoint = viewpointItem.getViewpoint();
                    boolean activateViewpoint = !viewpointItem.isViewpointEnabledInSession();
                    Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
                    if (activateViewpoint) {
                        String errorMessage = null;

                        errorMessage = getMissingDependencyErrorMessage(changedViewpoint, selectedViewpoints);
                        if (errorMessage == null) {
                            viewpointsSelectionGraphicalHandler.clearBrowserErrorMessageText();
                            handleViewpointActivation(viewpointItem, changedViewpoint, selectedViewpoints, activateViewpoint);
                        } else {
                            viewpointsSelectionGraphicalHandler.setBrowserErrorMessageText(errorMessage);
                        }
                    } else {
                        handleViewpointActivation(viewpointItem, changedViewpoint, selectedViewpoints, activateViewpoint);
                    }

                } else if (selection.getFirstElement() instanceof RepresentationDescriptionItemImpl) {
                    RepresentationDescriptionItemImpl representationDescriptionItem = (RepresentationDescriptionItemImpl) selection.getFirstElement();
                    ViewpointItemImpl viewpointItem = (ViewpointItemImpl) representationDescriptionItem.getParent();

                    treeViewer.getTree().setRedraw(false);
                    CreateRepresentationWizard wizard = new CreateRepresentationWizard(session, representationDescriptionItem);
                    wizard.init();
                    final WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
                    dialog.setMinimumPageSize(CreateRepresentationWizard.MIN_PAGE_WIDTH, CreateRepresentationWizard.MIN_PAGE_HEIGHT);
                    dialog.create();
                    dialog.getShell().setText(Messages.GraphicalRepresentationHandler_CreateRepresentationWizard_title);
                    dialog.open();

                    treeViewer.getTree().setRedraw(true);
                    treeViewer.refresh();
                    treeViewer.setSelection(new StructuredSelection(representationDescriptionItem));
                }
            }

        }
    }

    /**
     * This listener takes care of enabling/disabling buttons handling representations. It also update the browser
     * description regarding the viewpoint of the selected item.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class UpdateButtonsAtSelectionChangeListener implements ISelectionChangedListener {
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
            if (event.getSelection().isEmpty()) {
                removeSemanticModelOrRepresentationButton.setEnabled(false);
                deleteActionHandler.setEnabled(false);
                renameActionHandler.setEnabled(false);
            } else if (event.getSelection() instanceof TreeSelection) {
                TreeSelection selection = (TreeSelection) event.getSelection();

                // update buttons
                Iterator<?> selectionIte = selection.iterator();
                boolean allRepresentationItem = true;
                while (selectionIte.hasNext() && allRepresentationItem) {
                    Object object = selectionIte.next();
                    if (!(object instanceof RepresentationItemImpl)) {
                        allRepresentationItem = false;
                    }
                }
                if (allRepresentationItem) {
                    removeSemanticModelOrRepresentationButton.setEnabled(true);
                    deleteActionHandler.setEnabled(true);
                    renameActionHandler.setEnabled(true);
                } else {
                    removeSemanticModelOrRepresentationButton.setEnabled(false);
                    deleteActionHandler.setEnabled(false);
                    renameActionHandler.setEnabled(false);
                }
            }
        }
    }

    /**
     * This listener update browser description each time a new viewpoint or representation description or instance is
     * selected.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class UpdateBrowserAtSelectionChangeListener implements ISelectionChangedListener {
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
            if (event.getSelection() instanceof TreeSelection) {
                TreeSelection selection = (TreeSelection) event.getSelection();

                // update browser
                viewpointsSelectionGraphicalHandler.clearBrowserErrorMessageText();
                Object firstElement = ((IStructuredSelection) selection).getFirstElement();
                if (firstElement instanceof ViewpointItemImpl) {
                    viewpointsSelectionGraphicalHandler.setBrowserInput(((ViewpointItemImpl) firstElement).getViewpoint());
                } else if (firstElement instanceof RepresentationDescriptionItemImpl) {
                    RepresentationDescriptionItemImpl representationDescriptionItemImpl = (RepresentationDescriptionItemImpl) firstElement;
                    viewpointsSelectionGraphicalHandler.setBrowserInput(((ViewpointItemImpl) representationDescriptionItemImpl.getParent()).getViewpoint());
                } else if (firstElement instanceof RepresentationItemImpl) {
                    RepresentationItemImpl representationItem = (RepresentationItemImpl) firstElement;
                    RepresentationDescriptionItemImpl representationDescriptionItemImpl = (RepresentationDescriptionItemImpl) representationItem.getParent();
                    viewpointsSelectionGraphicalHandler.setBrowserInput(((ViewpointItemImpl) representationDescriptionItemImpl.getParent()).getViewpoint());
                }
            }
        }
    }

    /**
     * This listener updates enabling status of buttons used to create/remove representation or activate/deactivate
     * viewpoints regarding the current selection..
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class UpdateButtonAtSelectionChangeListener implements ISelectionChangedListener {
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
            if (event.getSelection().isEmpty()) {
                removeSemanticModelOrRepresentationButton.setEnabled(false);
                deleteActionHandler.setEnabled(false);
                renameActionHandler.setEnabled(false);
            }
        }
    }
}
