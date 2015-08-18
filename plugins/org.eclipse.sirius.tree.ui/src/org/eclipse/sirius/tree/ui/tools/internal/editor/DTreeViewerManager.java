/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.AbstractToolAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.CreateToolItemAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.DeleteTreeItemsAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.EditorCreateTreeItemMenuAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.listeners.TreeItemExpansionManager;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.DTreeContentProvider;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.DTreeDecoratingLabelProvider;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.DTreeItemDropListener;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.DTreeItemEditingSupport;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.TreeUIUpdater;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTableViewerManager;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.ui.tools.internal.editor.DTableColumnViewerEditorActivationStrategy;
import org.eclipse.sirius.ui.tools.internal.editor.DTableTreeFocusListener;
import org.eclipse.sirius.ui.tools.internal.editor.DescriptionFileChangedNotifier;
import org.eclipse.sirius.ui.tools.internal.editor.SelectDRepresentationElementsListener;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.adapters.ModelDragTargetAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

/**
 * This class manages the tree viewer for display the DTree.
 * 
 * @author nlepine
 */
public class DTreeViewerManager extends AbstractDTableViewerManager {

    /** The key for the image which represents a delete action. */
    public static final String REFRESH_IMG = "tree/refresh"; //$NON-NLS-1$

    /** The key for the image which represents a delete action. */
    public static final String DELETE_IMG = "tree/delete"; //$NON-NLS-1$

    /** The key for the image which represents a delete action. */
    public static final String CREATE_TREE_ITEM_IMG = "tree/newTreeItem"; //$NON-NLS-1$

    static {
        imageRegistry.put(REFRESH_IMG, ImageDescriptor.createFromURL((URL) TreeUIPlugin.INSTANCE.getImage(REFRESH_IMG)));
        imageRegistry.put(DELETE_IMG, ImageDescriptor.createFromURL((URL) TreeUIPlugin.INSTANCE.getImage(DELETE_IMG)));
        imageRegistry.put(CREATE_TREE_ITEM_IMG, ImageDescriptor.createFromURL((URL) TreeUIPlugin.INSTANCE.getImage(CREATE_TREE_ITEM_IMG)));
    }

    private final ITreeCommandFactory treeCommandFactory;

    private TreeUIUpdater treeUIUpdater;

    private DescriptionFileChangedNotifier descriptionFileChangedNotifier;

    private DTreeContentProvider dTreeContentProvider;

    private DTreeMenuListener actualMenuListener;

    private final EditorCreateTreeItemMenuAction createTreeItemMenu = new EditorCreateTreeItemMenuAction();

    private SelectDRepresentationElementsListener selectTableElementsListener;

    /**
     * The constructor.
     * 
     * @param parent
     *            The parent composite
     * @param input
     *            The input DTable
     * @param editingDomain
     *            The transactional editing domain of this viewer
     * @param accessor
     *            The accessor for the model
     * @param tableCommandFactory
     *            The EMF command factory
     * @param treeEditor
     *            The associated editor
     */
    public DTreeViewerManager(final Composite parent, final DTree input, final TransactionalEditingDomain editingDomain, final ModelAccessor accessor, final ICommandFactory tableCommandFactory,
            final AbstractDTreeEditor treeEditor) {
        super(parent, input, editingDomain, accessor, tableCommandFactory, treeEditor);
        this.treeCommandFactory = (ITreeCommandFactory) tableCommandFactory;
        this.createTreeViewer(parent);
    }

    public static ImageRegistry getImageRegistry() {
        return imageRegistry;
    }

    /**
     * Creates the TreeViewer.
     * 
     * Problem for action on column header
     * 
     * @param composite
     *            the Container of the TreeViewer to create
     */
    @Override
    protected void createTreeViewer(final Composite composite) {
        // Create a composite to hold the children
        final GridData gridData = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(gridData);

        final TreeColumnLayout treeLayout = new TreeColumnLayout();
        composite.setLayout(treeLayout);

        // Create and setup the TreeViewer
        final int style = SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI;

        DTreeViewer dTreeViewer = new DTreeViewer(composite, style, getAccessor().getPermissionAuthority());
        treeViewer = dTreeViewer;
        new TreeItemExpansionManager(dTreeViewer.getTree(), getSession());

        // Add a focus listener to deactivate the EMF actions on the Tree
        treeViewer.getTree().addFocusListener(new DTableTreeFocusListener(treeEditor, treeViewer.getTree()));
        treeViewer.setUseHashlookup(true);

        // tableViewer.setSorter(new
        // ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));

        treeUIUpdater = new TreeUIUpdater(dTreeViewer, dRepresentation);
        selectTableElementsListener = new SelectDRepresentationElementsListener(treeEditor, false);
        descriptionFileChangedNotifier = new DescriptionFileChangedNotifier(this);

        dTreeContentProvider = new DTreeContentProvider();
        treeViewer.setContentProvider(dTreeContentProvider);

        // Wrap the LabelProvider in a DecoratingLabelProvider
        ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
        AdapterFactory adapterFactory = TreeUIPlugin.getPlugin().getItemProvidersAdapterFactory();
        DTreeDecoratingLabelProvider labelProvider = new DTreeDecoratingLabelProvider(adapterFactory, decorator);
        treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(labelProvider));

        fillMenu();
        initializeEditingSupport();
        initializeDragAndDropSupport();
        initializeKeyBindingSupport();

        // Create a new CellFocusManager
        final TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(treeViewer, new FocusCellOwnerDrawHighlighter(treeViewer));
        // Create a TreeViewerEditor with focusable cell
        TreeViewerEditor.create(treeViewer, focusCellManager, new DTableColumnViewerEditorActivationStrategy(treeViewer), ColumnViewerEditor.TABBING_HORIZONTAL
                | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);

        // The input for the table viewer is the instance of DTable
        treeViewer.setInput(dRepresentation);

        // Expands the line according to the model
        treeViewer.setExpandedElements(TreeHelper.getExpandedItems((DTree) dRepresentation).toArray());
    }

    private void initializeKeyBindingSupport() {
        treeViewer.getTree().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    DeleteTreeItemsAction deleteItemsAction = new DeleteTreeItemsAction(getEditingDomain(), getTreeCommandFactory());
                    deleteItemsAction.setItems(getSelectedItems());
                    if (deleteItemsAction.canExecute()) {
                        deleteItemsAction.run();
                    }
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {
            };
        });
    }

    private Session getSession() {
        return SessionManager.INSTANCE.getSession(((DTree) dRepresentation).getTarget());
    }

    /**
     * Initializes the Editing Support (allowing direct Edit) to associate to
     * the current Tree.
     */
    protected void initializeEditingSupport() {

        // Step 1 : get the TreeViewerColumn corresponding to this Tree
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.ADD_SWT_COLUMN_KEY);

        Option<ViewerColumn> optionViewerColumn = getViewerColumn();
        if (optionViewerColumn.some()) {
            ViewerColumn treeColumn = optionViewerColumn.get();

            // Step 2 : set the Editing support
            treeColumn.setEditingSupport(new DTreeItemEditingSupport(treeViewer, getEditingDomain(), getAccessor(), getTreeCommandFactory(), this.getEditor()));
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.ADD_SWT_COLUMN_KEY);
    }

    /**
     * Initializes Drag and Drop support for the current DTree.
     */
    protected void initializeDragAndDropSupport() {
        // If copy/paste operations should be supported, may add
        // " | DND.DROP_COPY" to the supported operations.
        int supportedOperations = DND.DROP_MOVE | DND.DROP_LINK;

        ISelectionProvider selectionProvider = this.treeViewer;
        this.treeViewer.addDragSupport(supportedOperations, new ByteArrayTransfer[] { LocalSelectionTransfer.getTransfer() }, new ModelDragTargetAdapter(selectionProvider));
        this.treeViewer.addDropSupport(supportedOperations, new ByteArrayTransfer[] { LocalSelectionTransfer.getTransfer() }, new DTreeItemDropListener(this.treeViewer, this.editingDomain,
                this.treeCommandFactory, this.accessor));
    }

    /**
     * Returns the viewer column associated to the treeViewer.
     * 
     * @return the viewer column of the treeViewer, or Options.newNone() if to
     *         viewer column found.
     */
    private Option<ViewerColumn> getViewerColumn() {
        Option<ViewerColumn> viewerColumn = Options.newNone();

        // We use reflection to access to the ColumnViewer.getViewerColumn(int)
        // method (package visibility)
        Class<?> columnViewerClass = ColumnViewer.class;
        Method method;
        try {
            method = columnViewerClass.getDeclaredMethod("getViewerColumn", Integer.TYPE); //$NON-NLS-1$
            method.setAccessible(true);
            Object invoke = method.invoke(treeViewer, 0);
            if (invoke instanceof ViewerColumn) {
                viewerColumn = Options.newSome((ViewerColumn) invoke);
            }
        } catch (SecurityException e) {
            // Nothing to do, method will return Options.NONE
        } catch (NoSuchMethodException e) {
            // Nothing to do, method will return Options.NONE
        } catch (IllegalArgumentException e) {
            // Nothing to do, method will return Options.NONE
        } catch (InvocationTargetException e) {
            // Nothing to do, method will return Options.NONE
        } catch (IllegalAccessException e) {
            // Nothing to do, method will return Options.NONE
        }

        return viewerColumn;
    }

    /**
     * Initialize a cache and add, if needed, the contextual menu for the table. <BR>
     * Cached the actions of creation and deletion in order to increase
     * performance and not calculate it on each contextual menu.<BR>
     */
    @Override
    public void fillMenu() {
        if (descriptionFileChanged) {
            descriptionFileChanged = false;
            final Map<TreeMapping, List<AbstractToolAction>> mappingToCreateActions = new HashMap<TreeMapping, List<AbstractToolAction>>();
            final List<AbstractToolAction> createActionsForTree = new ArrayList<AbstractToolAction>();

            calculateAvailableMenus(mappingToCreateActions, createActionsForTree);
            mgr.setRemoveAllWhenShown(true);
            if (actualMenuListener != null) {
                mgr.removeAll();
                actualMenuListener.setMappingToCreateActions(mappingToCreateActions);
                actualMenuListener.setCreateActionsForTree(createActionsForTree);
            } else {
                actualMenuListener = new DTreeMenuListener((DTree) dRepresentation, this, mappingToCreateActions, createActionsForTree);
                mgr.addMenuListener(actualMenuListener);
                //
                final Menu menu = mgr.createContextMenu(treeViewer.getControl());
                treeViewer.getControl().setMenu(menu);
                // // Add this line to have others contextual menus
                treeEditor.getSite().registerContextMenu(mgr, treeViewer);
            }
            getCreateTreeItemMenu().update(createActionsForTree);
        }
    }

    /**
     * Get the selected {@link DTreeItem item}.
     * 
     * @return the selected tree items or an empty collection
     */
    public Collection<DTreeItem> getSelectedItems() {
        Collection<DTreeItem> result = Lists.newArrayList();
        if (treeViewer.getTree().getSelectionCount() > 0) {
            for (TreeItem item : treeViewer.getTree().getSelection()) {
                Object data = item.getData();
                if (data instanceof DTreeItem) {
                    result.add((DTreeItem) data);
                }
            }
        }
        return result;
    }

    /**
     * Create the menus according to the {@link TreeMapping} and the associated
     * {@link CreateTool} and {@link DeleteTool}.
     * 
     */
    private void calculateAvailableMenus(final Map<TreeMapping, List<AbstractToolAction>> mappingToCreateActions, final List<AbstractToolAction> createActionsForTree) {

        final TreeDescription treeDescription = ((DTree) dRepresentation).getDescription();

        if (treeDescription != null) {
            // Actions on lines
            calculateAvailableMenusForLine(treeDescription.getSubItemMappings(), mappingToCreateActions, new ArrayList<TreeItemMapping>());

            // Actions on table
            final EList<? extends TreeItemCreationTool> createLineTools = treeDescription.getCreateTreeItem();
            for (final TreeItemCreationTool createTool : createLineTools) {
                final CreateToolItemAction createLineAction = new CreateToolItemAction(createTool, getEditingDomain(), getTreeCommandFactory());
                createLineAction.setTable((DTree) dRepresentation);
                createActionsForTree.add(createLineAction);
            }
        }

    }

    /**
     * Create the menus according to the {@link LineMapping} and the associated
     * {@link CreateTool} and {@link DeleteTool}.
     * 
     * @param lineMappings
     *            List of {@link LineMapping}
     * @param mappingToCreateActions
     *            A map which associates {@link TreeMapping} with the
     *            corresponding list of {@link AbstractToolAction} (
     *            {@link CreateLineAction} or {@link CreateTargetColumnAction})
     */
    private void calculateAvailableMenusForLine(final EList<TreeItemMapping> lineMappings, final Map<TreeMapping, List<AbstractToolAction>> mappingToCreateActions,
            final List<TreeItemMapping> processedLineMappings) {
        for (final TreeItemMapping treeItemMapping : lineMappings) {
            if (treeItemMapping != null && !mappingToCreateActions.keySet().contains(treeItemMapping)) {
                final EList<? extends TreeItemCreationTool> createTools = treeItemMapping.getCreate();
                List<AbstractToolAction> existingCreateTools = mappingToCreateActions.get(treeItemMapping);
                if (existingCreateTools == null) {
                    existingCreateTools = new ArrayList<AbstractToolAction>();
                }
                for (final TreeItemCreationTool createTool : createTools) {
                    existingCreateTools.add(new CreateToolItemAction(createTool, getEditingDomain(), getTreeCommandFactory()));
                }
                mappingToCreateActions.put(treeItemMapping, existingCreateTools);
            }
            if (treeItemMapping != null && !processedLineMappings.contains(treeItemMapping)) {
                processedLineMappings.add(treeItemMapping);
                calculateAvailableMenusForLine(treeItemMapping.getAllSubMappings(), mappingToCreateActions, processedLineMappings);
            }
        }
    }

    /**
     * Return the TableCommandFactory.
     * 
     * @return the TableCommandFactory
     */
    public ITreeCommandFactory getTreeCommandFactory() {
        return treeCommandFactory;
    }

    /**
     * Check the tree.
     * 
     * @param tree
     *            the tree to test
     * @return true if the tree is equals to the dTree of this manager, false
     *         otherwise
     */
    public boolean isSameTree(final DTree tree) {
        return ((DTree) dRepresentation).equals(tree);
    }

    /**
     * Changed descriptionFileChanged state.
     * 
     * @param modified
     *            Indicates whether the odesign file has changed since the last
     *            load menus
     */
    @Override
    public void setDescriptionFileChanged(final boolean modified) {
        descriptionFileChanged = modified;
    }

    /**
     * Return the menu which is display in the toolBar.
     * 
     * @return the menu for create lines on the root
     */
    public EditorCreateTreeItemMenuAction getCreateTreeItemMenu() {
        return createTreeItemMenu;
    }

    /**
     * Release resources.
     */
    @Override
    public void dispose() {
        descriptionFileChangedNotifier.dispose();
        descriptionFileChangedNotifier = null;
        treeUIUpdater.dispose();
        treeUIUpdater = null;
        selectTableElementsListener.dispose();
        selectTableElementsListener = null;
        dTreeContentProvider.dispose();
        dTreeContentProvider = null;
        super.dispose();
        createTreeItemMenu.dispose();
    }

}
