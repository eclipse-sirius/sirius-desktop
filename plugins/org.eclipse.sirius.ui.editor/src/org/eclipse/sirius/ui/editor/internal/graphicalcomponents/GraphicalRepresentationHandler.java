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
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.common.ui.tools.api.util.TreeItemWrapperContentProvider;
import org.eclipse.sirius.ui.editor.Messages;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointsFolderItem;
import org.eclipse.sirius.ui.tools.internal.views.common.action.DeleteRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.ManageSessionActionProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonContentProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.sorter.CommonItemSorter;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.DeleteActionHandler;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.RenameActionHandler;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateRepresentationWizard;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.dialogs.FilteredTree;

/**
 * This graphical component provides a {@link TreeViewer} showing all
 * representations belonging to the given session under corresponding viewpoints
 * objects. It also provides buttons to add or remove external semantic model
 * dependency to the session. This component also reacts to external session
 * changes regarding loading semantic models or selected viewpoints to always
 * display representations available to use from the session.
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
     * Sirius content provider providing expandable viewpoints showing
     * associated representations loaded and available from the session.
     */
    private SiriusCommonContentProvider siriusCommonContentProvider;

    /**
     * The button used to remove external semantic model reference and
     * representations from the session.
     */
    private Button removeSemanticModelOrRepresentationButton;

    /**
     * The viewer showing all viewpoints containing representations loaded from
     * the given session.
     */
    private TreeViewer treeViewer;

    /**
     * The {@link MenuManager} for this component.
     */
    private MenuManager menuManager;

    /**
     * The component providing actions available for representations element in
     * the viewer.
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
     * Initialize the component with the given session.
     * 
     * @param theSession
     *            the session used by the component to handle semantic models
     *            lifecycle.
     */
    public GraphicalRepresentationHandler(Session theSession) {
        super();
        this.session = theSession;
    }

    /**
     * Return the {@link TreeViewer} showing all representations usable under
     * their viewpoint.
     * 
     * @return the {@link TreeViewer} showing all representations usable under
     *         their viewpoint.
     */
    public TreeViewer getTreeViewer() {
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
     * it with representation from the session.
     * 
     * @param parentComposite
     *            the composite to be attached to.
     */
    public void createControl(Composite parentComposite) {
        treeViewer = createRepresentationExplorerNavigator(parentComposite);
        createRepresentationExplorerButton(parentComposite, treeViewer);
        SessionManager.INSTANCE.addSessionsListener(this);
    }

    /**
     * Update the representations viewer with the representation currently
     * available in the session.
     * 
     * @param theTreeViewer
     *            the viewer to update.
     */
    protected void updateViewerInput(TreeViewer theTreeViewer) {
        Object[] children = siriusCommonContentProvider.getChildren(session);
        theTreeViewer.setInput(Arrays.stream(children).filter(child -> child instanceof ViewpointsFolderItem).findFirst().get());
        theTreeViewer.expandToLevel(2);
    }

    /**
     * Create Representation explorer navigator.
     * 
     * @param parent
     *            the parent composite.
     * @return the {@link TreeViewer} allowing the navigation.
     */
    private TreeViewer createRepresentationExplorerNavigator(Composite parent) {
        Composite subComposite = new Composite(parent, SWT.NONE);
        subComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        subComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        representationTree = SWTUtil.createFilteredTree(subComposite, SWT.BORDER, new org.eclipse.ui.dialogs.PatternFilter());
        TreeViewer theTreeViewer = representationTree.getViewer();
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.minimumHeight = 200;
        theTreeViewer.getControl().setLayoutData(gridData);
        theTreeViewer.getTree().setHeaderVisible(false);
        theTreeViewer.getTree().setLinesVisible(false);
        final ITreeContentProvider contentProvider = new GroupingContentProvider(new TreeItemWrapperContentProvider());
        theTreeViewer.setContentProvider(contentProvider);
        siriusCommonContentProvider = new SiriusCommonContentProvider();
        theTreeViewer.setContentProvider(siriusCommonContentProvider);
        theTreeViewer.setLabelProvider(new SiriusCommonLabelProvider());

        updateViewerInput(theTreeViewer);

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
                if (firstElement instanceof RepresentationItemImpl) {
                    removeSemanticModelOrRepresentationButton.setEnabled(true);
                    deleteActionHandler.setEnabled(true);
                    renameActionHandler.setEnabled(true);
                } else {
                    removeSemanticModelOrRepresentationButton.setEnabled(false);
                    deleteActionHandler.setEnabled(false);
                    renameActionHandler.setEnabled(false);
                }
            }
        });

        theTreeViewer.setSorter(new CommonItemSorter());

        menuManager = new MenuManager();
        menuManager.addMenuListener(new IMenuListener() {

            @Override
            public void menuAboutToShow(IMenuManager manager) {
                manageSessionActionProvider.setContext(new ActionContext(theTreeViewer.getSelection()));
                manageSessionActionProvider.fillContextMenu(menuManager);
            }

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
     * Create control buttons allowing to add/remove representations.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRepresentationExplorerButton(Composite parent, final TreeViewer theTreeViewer) {
        Composite subComposite = new Composite(parent, SWT.NONE);
        subComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 24).create());
        subComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        Composite buttonsComposite = new Composite(subComposite, SWT.NONE);
        buttonsComposite.setLayout(new FillLayout(SWT.BEGINNING));
        addButton(buttonsComposite, Messages.UI_SessionEditor_representation_button_newRepresentation, () -> {
            CreateRepresentationWizard wizard = new CreateRepresentationWizard(session);
            wizard.init();
            final WizardDialog dialog = new WizardDialog(parent.getShell(), wizard);
            dialog.create();
            dialog.getShell().setText("Create Representation Wizard"); //$NON-NLS-1$
            dialog.open();
        });
        removeSemanticModelOrRepresentationButton = addButton(buttonsComposite, Messages.UI_SessionEditor_representation_button_removeRepresentation, () -> {
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
        Button button = new Button(parent, SWT.PUSH);
        button.setText(name);
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
    }

    @Override
    public void notifyAddSession(Session newSession) {
    }

    @Override
    public void notifyRemoveSession(Session removedSession) {
        siriusCommonContentProvider.removeRefreshViewerTriggers(removedSession);
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
                        if (!representationTree.isDisposed()) {
                            updateViewerInput(treeViewer);
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
                        if (!representationTree.isDisposed()) {
                            treeViewer.refresh();
                        }
                    }

                });
                break;

            case SessionListener.OPENED:
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (!representationTree.isDisposed()) {
                            treeViewer.refresh();
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
}
