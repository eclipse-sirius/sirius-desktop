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
package org.eclipse.sirius.ui.editor.internal.graphicalcomponents;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.ui.editor.Messages;
import org.eclipse.sirius.ui.tools.api.views.common.item.ProjectDependenciesItem;
import org.eclipse.sirius.ui.tools.internal.actions.analysis.AddModelDependencyAction;
import org.eclipse.sirius.ui.tools.internal.actions.analysis.RemoveSemanticResourceAction;
import org.eclipse.sirius.ui.tools.internal.views.common.item.NoDynamicProjectDependencies;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointsFolderItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.ManageSessionActionProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonContentProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.filter.FilteredCommonTree;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.sorter.CommonItemSorter;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.DeleteActionHandler;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.RenameActionHandler;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.INavigatorFilterService;
import org.eclipse.ui.navigator.NavigatorContentServiceFactory;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This graphical component provides a {@link CommonViewer} from CNF showing all
 * semantic models loaded in the given session and that can be associated to a
 * component allowing to select filters and content providers for this viewer
 * via CNF.
 * 
 * It also provides button to add or remove external semantic model dependency
 * to the session.
 * 
 * This component also reacts to external session changes regarding loading
 * semantic models to update the viewer list.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class GraphicalSemanticModelsHandler implements SessionListener, SessionManagerListener {
    /**
     * The common viewer id allowing to provide additional filters and content
     * providers by using CNF extension point.
     */
    public static final String SEMANTIC_MODELS_VIEWER_ID = "org.eclipse.sirius.ui.editor.internal.graphicalcomponents.semanticModelsViewer"; //$NON-NLS-1$

    /**
     * Session from which semantic models are handled.
     */
    private Session session;

    /**
     * The Form Toolkit to use to create & configure the controls.
     */
    private FormToolkit toolkit;

    /**
     * Sirius content provider providing expandable models loaded by the session
     * and representations.
     */
    private SiriusCommonContentProvider siriusCommonContentModelProvider;

    /**
     * The button used to remove external semantic model reference and
     * representations from the session.
     */
    private Button removeSemanticModelOrRepresentationButton;

    /**
     * The viewer showing all semantic models loaded from the given session.
     */
    private CommonViewer treeViewer;

    /**
     * The {@link MenuManager} for this component.
     */
    private MenuManager menuManager;

    /**
     * The component providing actions available for selected semantic and
     * representations element in its viewer.
     */
    private ManageSessionActionProvider manageSessionActionProvider;

    /**
     * The Tree showing semantic models and representations.
     */
    private Tree modelTree;

    /**
     * Handler allowing to delete a representation.
     */
    private Action deleteActionHandler;

    /**
     * Handler allowing to rename a representation.
     */
    private Action renameActionHandler;

    /**
     * Initialize the component with the given session.
     * 
     * @param theSession
     *            the session used by the component to handle semantic models
     *            lifecycle.
     * @param toolkit
     *            The Form Toolkit to use to create & configure the controls.
     */
    public GraphicalSemanticModelsHandler(Session theSession, FormToolkit toolkit) {
        this.session = theSession;
        this.toolkit = toolkit;
    }

    /**
     * Return the {@link TreeViewer} showing all semantic models.
     * 
     * @return the {@link TreeViewer} showing all semantic models.
     */
    public CommonViewer getTreeViewer() {
        return treeViewer;
    }

    /**
     * Launch a representation deletion or renaming if the right key is used and
     * the selected element is a representation.
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
     * Create the graphic composites and the {@link TreeViewer} and initialize
     * it with semantic models from the session.
     * 
     * @param parentComposite
     *            the composite to be attached to.
     */
    public void createControl(Composite parentComposite) {
        treeViewer = createModelExplorerNavigator(parentComposite);
        createModelExplorerButton(parentComposite, treeViewer);
        session.addListener(this);
        SessionManager.INSTANCE.addSessionsListener(this);
        siriusCommonContentModelProvider.addRefreshViewerTrigger(session);

        treeViewer.getTree().getHorizontalBar().setSelection(0);
    }

    /**
     * Create ModelExplorer control buttons.
     * 
     * @param parent
     *            the model Explorer Composite
     */
    private void createModelExplorerButton(Composite parent, final TreeViewer theTreeViewer) {
        Composite subComposite = toolkit.createComposite(parent, SWT.NONE);
        subComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).create());
        subComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        Composite buttonsComposite = toolkit.createComposite(subComposite, SWT.NONE);
        FillLayout buttonsLayout = new FillLayout(SWT.BEGINNING);
        buttonsLayout.spacing = 5;
        buttonsComposite.setLayout(buttonsLayout);
        addButton(buttonsComposite, Messages.UI_SessionEditor_new_semantic_model_action_label, () -> {
            new NewWizardAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()).run();
        });
        addButton(buttonsComposite, Messages.UI_SessionEditor_models_button_newSemanticModel, () -> {
            AddModelDependencyAction addModelDependencyAction = new AddModelDependencyAction(session);
            addModelDependencyAction.run();
        });
        removeSemanticModelOrRepresentationButton = addButton(buttonsComposite, Messages.UI_SessionEditor_models_button_removeSemanticModel, () -> {
            if (theTreeViewer != null) {
                final IStructuredSelection selection = (IStructuredSelection) theTreeViewer.getSelection();
                Collection<?> selectedObjects = selection.toList();
                if (!selectedObjects.isEmpty()) {
                    RemoveSemanticResourceAction removeSemanticResourceAction = new RemoveSemanticResourceAction(getSemanticResources(selectedObjects), session);
                    removeSemanticResourceAction.run();
                }
                theTreeViewer.refresh();
            }
        });
        removeSemanticModelOrRepresentationButton.setEnabled(false);
        deleteActionHandler.setEnabled(false);
        renameActionHandler.setEnabled(false);

    }

    /**
     * Return true if the resources can be deleted. I.e it is not a controlled
     * resource and no representation are based on a semantic element of the
     * resources.
     * 
     * @param toRemove
     *            all semantic resources to remove.
     * @return true if the resources can be deleted. I.e it is not a controlled
     *         resource and no representation are based on a semantic element of
     *         the resources. False otherwise.
     */
    private boolean checkResources(Collection<Resource> toRemove) {
        boolean okForRemove = true;
        if (session instanceof DAnalysisSessionEObject) {
            // Controlled resource should be removed with uncontrol command
            okForRemove = !Iterables.removeAll(toRemove, ((DAnalysisSessionEObject) session).getControlledResources());
        }

        if (okForRemove) {
            for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
                if (representation instanceof DSemanticDecorator) {
                    final DSemanticDecorator decorator = (DSemanticDecorator) representation;
                    if (decorator.getTarget() != null && toRemove.contains(decorator.getTarget().eResource())) {
                        okForRemove = false;
                        break;
                    }
                }
            }
        }
        return okForRemove;
    }

    /**
     * Create Model explorer navigator.
     * 
     * @param parent
     *            the model Explorer Composite
     */
    private CommonViewer createModelExplorerNavigator(Composite parent) {
        final FilteredCommonTree commonTree = new FilteredCommonTree(SEMANTIC_MODELS_VIEWER_ID, parent, SWT.MULTI, false);
        INavigatorContentService contentService = NavigatorContentServiceFactory.INSTANCE.createContentService(SEMANTIC_MODELS_VIEWER_ID, treeViewer);
        CommonViewer theTreeViewer = commonTree.getViewer();
        contentService.createCommonContentProvider();
        contentService.createCommonLabelProvider();
        modelTree = theTreeViewer.getTree();
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.widthHint = 300;
        theTreeViewer.getControl().setLayoutData(gridData);
        theTreeViewer.getTree().setHeaderVisible(false);
        theTreeViewer.getTree().setLinesVisible(false);
        siriusCommonContentModelProvider = new SiriusCommonContentProvider();

        updateViewerInput(theTreeViewer);

        INavigatorFilterService filterService = contentService.getFilterService();
        ViewerFilter[] visibleFilters = filterService.getVisibleFilters(true);
        for (int i = 0; i < visibleFilters.length; i++) {
            theTreeViewer.addFilter(visibleFilters[i]);
        }

        contentService.update();
        deleteActionHandler = new DeleteActionHandler(theTreeViewer);
        renameActionHandler = new RenameActionHandler(theTreeViewer);

        theTreeViewer.addSelectionChangedListener((event) -> {
            if (event.getSelection().isEmpty()) {
                removeSemanticModelOrRepresentationButton.setEnabled(false);
                deleteActionHandler.setEnabled(false);
                renameActionHandler.setEnabled(false);
            } else {
                TreeSelection selection = (TreeSelection) event.getSelection();
                // The tree allows only single selections so we pick the
                // first element.
                Object firstElement = selection.getFirstElement();
                if (isExternalDependency(firstElement, selection) && (firstElement instanceof EObject || firstElement instanceof Resource)
                        && checkResources(getSemanticResources(Lists.newArrayList(firstElement)))) {
                    removeSemanticModelOrRepresentationButton.setEnabled(true);
                } else {
                    if (firstElement instanceof DRepresentationDescriptor) {
                        deleteActionHandler.setEnabled(true);
                        renameActionHandler.setEnabled(true);
                    } else {
                        deleteActionHandler.setEnabled(false);
                        renameActionHandler.setEnabled(false);
                    }
                    removeSemanticModelOrRepresentationButton.setEnabled(false);

                }
            }
        });
        theTreeViewer.setSorter(new CommonItemSorter());

        menuManager = new MenuManager();
        menuManager.addMenuListener((manager) -> {
            manageSessionActionProvider.setContext(new ActionContext(theTreeViewer.getSelection()));
            manageSessionActionProvider.fillContextMenu(menuManager);

        });
        Menu menu = menuManager.createContextMenu(theTreeViewer.getControl());
        manageSessionActionProvider = new ManageSessionActionProvider();
        manageSessionActionProvider.initFromViewer(theTreeViewer);
        theTreeViewer.getControl().setMenu(menu);

        theTreeViewer.getControl().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                handleKeyReleased(event);
            }
        });

        return theTreeViewer;
    }

    /**
     * Returns true if the given element is an external dependency of the
     * session.
     * 
     * @param element
     *            the element from which we want to know if it is an external
     *            dependency.
     * @param selection
     *            the selection from which we want to know if it is an external
     *            dependency.
     * @return true if the given element is an external dependency of the
     *         session. False otherwise.
     */
    private boolean isExternalDependency(Object element, TreeSelection selection) {
        if (!(element instanceof ProjectDependenciesItem) && !(element instanceof DRepresentationDescriptor)) {
            TreePath pathsForSelection = selection.getPathsFor(element)[0];
            int segmentCount = pathsForSelection.getSegmentCount();
            for (int i = 0; i < segmentCount; i++) {
                Object segment = pathsForSelection.getSegment(i);
                if (segment instanceof ProjectDependenciesItem) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Update the semantic models viewer with the models currently loaded in the
     * session.
     * 
     * @param theTreeViewer
     *            the viewer to update.
     */
    protected void updateViewerInput(TreeViewer theTreeViewer) {
        Object[] children = siriusCommonContentModelProvider.getChildren(session);
        List<Object> childrenList = Arrays.stream(children).collect(Collectors.toList());

        Resource sessionResource = session.getSessionResource();
        IFile file = WorkspaceSynchronizer.getFile(sessionResource);
        ProjectDependenciesItem projectDependenciesItem = new NoDynamicProjectDependencies(file.getProject(), session);
        List<Object> directChildOfProjectDependency = Arrays.asList(siriusCommonContentModelProvider.getChildren(projectDependenciesItem));

        childrenList.add(projectDependenciesItem);

        // We put as input only the ProjectDependenciesItemImpl and all Ecore
        // resources not provided by this item.
        theTreeViewer.setInput(childrenList.stream().filter(input -> !(input instanceof ViewpointsFolderItemImpl) && !directChildOfProjectDependency.contains(input)).collect(Collectors.toSet()));
        theTreeViewer.expandToLevel(2);
        theTreeViewer.expandToLevel(projectDependenciesItem, 2);
    }

    /**
     * Return semantic resource form selection in treeViewer.
     * 
     * @param selection
     *            the selection from treeViewer
     * @return semantic resource from selection
     */
    private Collection<Resource> getSemanticResources(final Collection<?> selection) {
        Collection<Resource> semanticResources = new HashSet<Resource>();
        if (selection != null) {
            Iterator<?> iterator = selection.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                if (object instanceof Resource) {
                    semanticResources.add((Resource) object);
                } else if (object instanceof EObject) {
                    EObject eObject = (EObject) object;
                    Resource eResource = eObject.eResource();
                    if (eResource != null) {
                        semanticResources.add(eResource);
                    }
                }

            }
        }
        return semanticResources;
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
        Button button = toolkit.createButton(parent, name, SWT.PUSH);
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

    @Override
    public void notify(int changeKind) {
        switch (changeKind) {
        case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
        case SessionListener.SEMANTIC_CHANGE:
            PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
                updateViewerInput(treeViewer);
            });
            break;
        default:
            break;
        }
    }

    /**
     * Dispose all listeners.
     */
    public void dispose() {
        session.removeListener(this);
        SessionManager.INSTANCE.removeSessionsListener(this);
        session = null;
        treeViewer = null;
        manageSessionActionProvider = null;
        siriusCommonContentModelProvider = null;
        menuManager = null;
        toolkit = null;
    }

    @Override
    public void notifyAddSession(Session newSession) {
    }

    @Override
    public void notifyRemoveSession(Session removedSession) {
        siriusCommonContentModelProvider.removeRefreshViewerTriggers(removedSession);
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
            case SessionListener.REPRESENTATION_CHANGE:
            case SessionListener.SEMANTIC_CHANGE:
            case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
            case SessionListener.VSM_UPDATED:
            case SessionListener.REPLACED:
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (!modelTree.isDisposed()) {
                            treeViewer.refresh();
                            // we reset the selection to update the button
                            // activated
                            // state if needed.
                            ISelection selection = treeViewer.getSelection();
                            treeViewer.setSelection(selection);
                        }
                    }

                });

                break;

            case SessionListener.SYNC:
            case SessionListener.DIRTY:
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (!modelTree.isDisposed()) {
                            treeViewer.refresh();
                        }
                    }

                });
                break;

            case SessionListener.OPENED:
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (!modelTree.isDisposed()) {
                            treeViewer.refresh();
                            siriusCommonContentModelProvider.addRefreshViewerTrigger(updated);
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
}
