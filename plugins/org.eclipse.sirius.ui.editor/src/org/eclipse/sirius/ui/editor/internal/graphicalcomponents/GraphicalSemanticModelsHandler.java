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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
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
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
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
     * This will contain one
     * {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding
     * to each descriptor generated for the current selection by the item
     * provider. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected Collection<IAction> createSiblingActions;

    /**
     * This will contain one
     * {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to
     * each descriptor generated for the current selection by the item provider.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected Collection<IAction> createChildActions;

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
     * The {@link IActionBars} to populate with Ecore models actions.
     */
    private IActionBars actionBars;

    /**
     * A selection provider needed by EMF Ecore modification actions.
     */
    private ISelectionProvider selectionProvider;

    /**
     * The Tree showing semantic models and representations.
     */
    private Tree modelTree;

    /**
     * This listener allow to refresh models block when changes occur and to
     * select newly created element's tree item if such element exists.
     * Otherwise we select the first element of the tree viewer of the model
     * block.
     */
    private ResourceSetListenerChangeListener resourceSetListenerChangeListener;

    /**
     * This handler provides EMF Ecore models actions (like new child/sibling
     * creation, removal, copy, paste, undo/redo etc...) in contextual pop up
     * menu and Eclipse menu bar for a tree viewer containing Ecore resources.
     */
    private EcoreActionsHandler ecoreActionsHandler;

    /**
     * Command stack listener selecting the last affected object in the viewer
     * when a model element modification is done.
     */
    private CommandStackListener listener;

    /**
     * This site is used to get the shell when doing UI action from command
     * execution
     */
    private IWorkbenchSite site;

    /**
     * Initialize the component with the given session.
     * 
     * @param theSession
     *            the session used by the component to handle semantic models
     *            lifecycle.
     * @param toolkit
     *            The Form Toolkit to use to create & configure the controls.
     * @param theActionBars
     *            The {@link IActionBars} to populate with Ecore models actions.
     * @param theSelectionProvider
     *            A selection provider needed by EMF Ecore modification actions.
     * @param theSite
     *            This site is used to get the shell when doing UI action from
     *            command execution.
     */
    public GraphicalSemanticModelsHandler(Session theSession, FormToolkit toolkit, IActionBars theActionBars, ISelectionProvider theSelectionProvider, IWorkbenchSite theSite) {
        this.session = theSession;
        this.toolkit = toolkit;
        this.actionBars = theActionBars;
        this.selectionProvider = theSelectionProvider;
        this.site = theSite;
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

        resourceSetListenerChangeListener = new ResourceSetListenerChangeListener();
        session.getTransactionalEditingDomain().addResourceSetListener(resourceSetListenerChangeListener);
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
     *            the model Explorer Composite.
     */
    private CommonViewer createModelExplorerNavigator(Composite parent) {
        final FilteredCommonTree commonTree = new FilteredCommonTree(SEMANTIC_MODELS_VIEWER_ID, parent, SWT.MULTI | SWT.BORDER, false);
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

        theTreeViewer.setSorter(new CommonItemSorter());

        menuManager = new MenuManager();

        menuManager.setRemoveAllWhenShown(true);
        ecoreActionsHandler = new EcoreActionsHandler(theTreeViewer, actionBars, menuManager, selectionProvider);
        ecoreActionsHandler.initModelsActionsAndListeners();
        menuManager.addMenuListener((manager) -> {
            manageSessionActionProvider.setContext(new ActionContext(theTreeViewer.getSelection()));
            manageSessionActionProvider.fillContextMenu(menuManager);

        });
        Menu menu = menuManager.createContextMenu(theTreeViewer.getControl());
        manageSessionActionProvider = new ManageSessionActionProvider();
        manageSessionActionProvider.initFromViewer(theTreeViewer);
        theTreeViewer.getControl().setMenu(menu);

        theTreeViewer.addSelectionChangedListener((event) -> {
            if (event.getSelection().isEmpty()) {
                removeSemanticModelOrRepresentationButton.setEnabled(false);
            } else {
                TreeSelection selection = (TreeSelection) event.getSelection();
                // The tree allows only single selections so we pick the
                // first element.
                Object firstElement = selection.getFirstElement();
                IProject project = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(session.getSessionResource().getURI().toPlatformString(true))).getProject();
                boolean isRemovableElement = firstElement instanceof EObject || firstElement instanceof Resource;
                boolean enableAction = !ModelingProject.hasModelingProjectNature(project);
                enableAction = enableAction || (isExternalDependency(firstElement, selection) && isRemovableElement && checkResources(getSemanticResources(Lists.newArrayList(firstElement))));
                if (enableAction) {
                    removeSemanticModelOrRepresentationButton.setEnabled(true);
                } else {
                    removeSemanticModelOrRepresentationButton.setEnabled(false);
                }
            }
        });

        listener = new CommandStackListener() {
            @Override
            public void commandStackChanged(final EventObject event) {

                site.getShell().getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {

                        // Try to select the last affected objects.
                        Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
                        if (mostRecentCommand != null) {
                            setSelectionToViewer(mostRecentCommand.getAffectedObjects());
                        }
                    }
                });
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().addCommandStackListener(listener);
        return theTreeViewer;
    }

    /**
     * This sets the selection into whichever viewer is active.
     * 
     * @param selectionCollection
     *            the selection to apply to the viewer.
     */
    public void setSelectionToViewer(Collection<?> selectionCollection) {
        final Collection<?> theSelection = selectionCollection;
        // Make sure it's okay.
        if (theSelection != null && !theSelection.isEmpty()) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // Try to select the items in the current content viewer of
                    // the editor.
                    treeViewer.setSelection(new StructuredSelection(theSelection.toArray()), true);
                }
            };
            site.getShell().getDisplay().asyncExec(runnable);
        }
    }

    /**
     * This handler provides EMF Ecore models actions (like new child/sibling
     * creation, removal, copy, paste, undo/redo etc...) in contextual pop up
     * menu and Eclipse menu bar for a tree viewer containing Ecore resources.
     * 
     * It relies on session editing domain to retrieve the right descriptors for
     * each model kind present in the viewer for child/sibling creation and
     * necessary elements for other actions.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private class EcoreActionsHandler {

        /**
         * This is the action used to implement delete.
         */
        private DeleteAction deleteAction;

        /**
         * This is the action used to implement cut.
         */
        private CutAction cutAction;

        /**
         * This is the action used to implement copy.
         */
        private CopyAction copyAction;

        /**
         * This is the action used to implement paste.
         */
        private PasteAction pasteAction;

        /**
         * This is the action used to implement undo.
         */
        private UndoAction undoAction;

        /**
         * This is the action used to implement redo.
         */
        private RedoAction redoAction;

        /**
         * MenuManager providing new child actions.
         */
        private MenuManager submenuNewChildManager;

        /**
         * MenuManager providing new sibling actions.
         */
        private MenuManager submenuNewSiblingManager;

        /**
         * The {@link IActionBars} to populate with Ecore models actions.
         */
        private IActionBars actionBars;

        /**
         * A selection provider needed by actions.
         */
        private ISelectionProvider selectionProvider;

        /**
         * The tree viewer containing Ecore resources for which modification
         * actions should be available.
         */
        private TreeViewer treeViewer;

        /**
         * The {@link MenuManager} to populate with Ecore models actions.
         */
        private MenuManager menuManager;

        /**
         * Handler allowing to delete a representation.
         */
        private Action deleteActionHandler;

        /**
         * Handler allowing to rename a representation.
         */
        private Action renameActionHandler;

        /**
         * Initializes the component/
         * 
         * @param theTreeViewer
         *            The tree viewer containing Ecore resources for which
         *            modification actions should be available.
         * @param theActionBars
         *            The {@link IActionBars} to populate with Ecore models
         *            actions.
         * @param theMenuManager
         *            The {@link MenuManager} to populate with Ecore models
         *            actions.
         * @param theSelectionProvider
         *            a selection provider needed by actions.
         */
        EcoreActionsHandler(TreeViewer theTreeViewer, IActionBars theActionBars, MenuManager theMenuManager, ISelectionProvider theSelectionProvider) {
            this.treeViewer = theTreeViewer;
            this.actionBars = theActionBars;
            this.menuManager = theMenuManager;
            this.selectionProvider = theSelectionProvider;
        }

        /**
         * This method initializes all actions and listeners allowing to
         * populate contextual pop-up menu and action bar with Ecore models
         * actions like new child/sibling creation, removal, copy, paste,
         * undo/redo etc...
         */
        public void initModelsActionsAndListeners() {
            deleteActionHandler = new DeleteActionHandler(treeViewer);
            renameActionHandler = new RenameActionHandler(treeViewer);
            deleteActionHandler.setEnabled(false);
            renameActionHandler.setEnabled(false);

            initActionsAndActionBar();
            initMenuListener();
            initSelectionListener();

            treeViewer.getControl().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent event) {
                    handleKeyReleased(event);
                }
            });
        }

        /**
         * Initialize the selection listener updating actions regarding the
         * current selection of the viewer.
         */
        private void initSelectionListener() {
            treeViewer.addSelectionChangedListener((event) -> {
                TreeSelection selection = (TreeSelection) event.getSelection();
                if (event.getSelection().isEmpty()) {
                    deleteActionHandler.setEnabled(false);
                    renameActionHandler.setEnabled(false);
                } else {
                    // The tree allows only single selections so we pick the
                    // first element.
                    Object firstElement = selection.getFirstElement();
                    if (firstElement instanceof EObject && !(firstElement instanceof DRepresentationDescriptor)) {
                        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
                        actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), null);
                        deleteActionHandler.setEnabled(false);
                        renameActionHandler.setEnabled(false);
                    } else if (firstElement instanceof DRepresentationDescriptor) {
                        deleteActionHandler.setEnabled(true);
                        renameActionHandler.setEnabled(true);
                        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteActionHandler);
                        actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameActionHandler);
                    }
                    deleteAction.updateSelection(selection);
                    cutAction.updateSelection(selection);
                    copyAction.updateSelection(selection);
                    pasteAction.updateSelection(selection);
                    undoAction.update();
                    redoAction.update();
                    actionBars.updateActionBars();
                }
            });
        }

        /**
         * Launch a representation deletion or renaming if the right key is used
         * and the selected element is a representation.
         * 
         * @param event
         *            key event.
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
         * Initializes EMF Ecore actions allowing to modify models and fill the
         * given action bar with it.
         */
        private void initActionsAndActionBar() {
            ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
            deleteAction = createDeleteAction();
            deleteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

            cutAction = createCutAction();
            cutAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));

            copyAction = createCopyAction();
            copyAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));

            pasteAction = createPasteAction();
            pasteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));

            undoAction = createUndoAction();
            undoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));

            redoAction = createRedoAction();
            redoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));

            selectionProvider.addSelectionChangedListener(deleteAction);
            selectionProvider.addSelectionChangedListener(cutAction);
            selectionProvider.addSelectionChangedListener(copyAction);
            selectionProvider.addSelectionChangedListener(pasteAction);

            actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
            actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
            actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
            actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
            actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
            actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
            actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameActionHandler);
            actionBars.updateActionBars();
        }

        /**
         * Initialize the listener allowing to populate the given menu manager
         * with EMF ecore modification actions.
         */
        private void initMenuListener() {
            menuManager.addMenuListener((manager) -> {
                TreeSelection selection = (TreeSelection) treeViewer.getSelection();
                List<?> selectionList = selection.toList();

                Object firstElement = selection.getFirstElement();
                if (firstElement instanceof EObject && !(firstElement instanceof DRepresentationDescriptor)) {
                    if (selectionList.size() == 1) {
                        // // Add the edit menu actions.
                        Collection<?> newChildDescriptors = session.getTransactionalEditingDomain().getNewChildDescriptors(firstElement, null);
                        createChildActions = generateCreateChildActions(newChildDescriptors, selection);
                        Collection<?> newSiblingDescriptors = session.getTransactionalEditingDomain().getNewChildDescriptors(null, firstElement);
                        createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);
                        submenuNewChildManager = new MenuManager("&New Child"); //$NON-NLS-1$
                        menuManager.add(submenuNewChildManager);
                        submenuNewSiblingManager = new MenuManager("N&ew Sibling"); //$NON-NLS-1$
                        menuManager.add(submenuNewSiblingManager);
                        populateManager(submenuNewChildManager, createChildActions);
                        populateManager(submenuNewSiblingManager, createSiblingActions);
                    }

                    menuManager.add(new Separator());
                    menuManager.add(new ActionContributionItem(undoAction));
                    menuManager.add(new ActionContributionItem(redoAction));
                    menuManager.add(new Separator());
                    menuManager.add(new ActionContributionItem(cutAction));
                    menuManager.add(new ActionContributionItem(copyAction));
                    menuManager.add(new ActionContributionItem(pasteAction));
                    menuManager.add(new Separator());
                    menuManager.add(new ActionContributionItem(deleteAction));
                    menuManager.add(new Separator());
                }

            });
        }

        /**
         * Returns the action used to implement delete.
         * 
         * @see #deleteAction
         */
        private DeleteAction createDeleteAction() {
            return new DeleteAction(session.getTransactionalEditingDomain(), removeAllReferencesOnDelete());
        }

        /**
         * Returns the action used to implement cut.
         * 
         * @see #cutAction
         */
        private CutAction createCutAction() {
            return new CutAction(session.getTransactionalEditingDomain());
        }

        /**
         * Returns the action used to implement copy.
         * 
         * @see #copyAction
         */
        private CopyAction createCopyAction() {
            return new CopyAction(session.getTransactionalEditingDomain());
        }

        /**
         * Returns the action used to implement paste.
         * 
         * @see #pasteAction
         */
        private PasteAction createPasteAction() {
            return new PasteAction(session.getTransactionalEditingDomain());
        }

        /**
         * Returns the action used to implement undo.
         * 
         * @see #undoAction
         */
        private UndoAction createUndoAction() {
            return new UndoAction(session.getTransactionalEditingDomain());
        }

        /**
         * Returns the action used to implement redo.
         * 
         * @see #redoAction
         */
        private RedoAction createRedoAction() {
            return new RedoAction(session.getTransactionalEditingDomain());
        }

        /**
         * This determines whether or not the delete action should clean up all
         * references to the deleted objects. It is false by default, to provide
         * the same beahviour, by default, as in EMF 2.1 and before. You should
         * probably override this method to return true, in order to get the
         * new, more useful beahviour.
         * 
         */
        protected boolean removeAllReferencesOnDelete() {
            return false;
        }

        /**
         * This generates a
         * {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each
         * object in <code>descriptors</code>, and returns the collection of
         * these actions.
         * 
         */
        private Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
            Collection<IAction> actions = new ArrayList<IAction>();
            if (descriptors != null) {
                for (Object descriptor : descriptors) {
                    actions.add(new CreateChildAction(session.getTransactionalEditingDomain(), selection, descriptor));
                }
            }
            return actions;
        }

        /**
         * This generates a
         * {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each
         * object in <code>descriptors</code>, and returns the collection of
         * these actions.
         * 
         */
        private Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection) {
            Collection<IAction> actions = new ArrayList<IAction>();
            if (descriptors != null) {
                for (Object descriptor : descriptors) {
                    actions.add(new CreateSiblingAction(session.getTransactionalEditingDomain(), selection, descriptor));
                }
            }
            return actions;
        }

        /**
         * This populates the specified <code>manager</code> with
         * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
         * {@link org.eclipse.jface.action.IAction}s contained in the
         * <code>actions</code> collection, by inserting them before the
         * specified contribution item <code>contributionID</code>. If
         * <code>contributionID</code> is <code>null</code>, they are simply
         * added.
         * 
         * 
         */
        private void populateManager(IContributionManager manager, Collection<? extends IAction> actions) {
            if (actions != null) {
                for (IAction action : actions) {
                    manager.add(action);
                }
            }
        }
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
        session.getTransactionalEditingDomain().getCommandStack().removeCommandStackListener(listener);
        session.removeListener(this);
        SessionManager.INSTANCE.removeSessionsListener(this);
        if (session != null && session.getTransactionalEditingDomain() != null) {
            session.getTransactionalEditingDomain().removeResourceSetListener(resourceSetListenerChangeListener);
        }
        session = null;
        treeViewer = null;
        manageSessionActionProvider = null;
        siriusCommonContentModelProvider = null;
        if (menuManager != null) {
            menuManager.dispose();
        }
        menuManager = null;
        toolkit = null;
        ecoreActionsHandler = null;

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
            case SessionListener.SYNC:
            case SessionListener.DIRTY:
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (!modelTree.isDisposed()) {
                            treeViewer.refresh();
                            ISelection selection = treeViewer.getSelection();
                            if (selection.isEmpty()) {
                                selection = new StructuredSelection(treeViewer.getTree().getItem(0).getData());
                            }
                            treeViewer.setSelection(selection);
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

    /**
     * This listener allow to refresh models block when changes occur and to
     * select newly created element's tree item if such element exists.
     * Otherwise we select the first element of the tree viewer of the model
     * block.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private final class ResourceSetListenerChangeListener implements ResourceSetListener {
        @Override
        public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
            return null;
        }

        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {

            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

                @Override
                public void run() {
                    // we refresh the models tree viewer and select newly
                    // created element or first element is such element does not
                    // exist.
                    if (!modelTree.isDisposed()) {
                        treeViewer.refresh();
                    }
                }

            });
        }

        @Override
        public boolean isPrecommitOnly() {
            return false;
        }

        @Override
        public boolean isPostcommitOnly() {
            return true;
        }

        @Override
        public NotificationFilter getFilter() {
            return null;
        }

        @Override
        public boolean isAggregatePrecommitListener() {
            return false;
        }
    }
}
