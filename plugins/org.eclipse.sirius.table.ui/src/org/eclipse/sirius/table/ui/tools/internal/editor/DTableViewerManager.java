/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.AbstractToolAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.CreateLineAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.CreateTargetColumnAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.DeleteLinesAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.DeleteTargetColumnAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.EditorCreateLineMenuAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.EditorCreateTargetColumnMenuAction;
import org.eclipse.sirius.table.ui.tools.internal.editor.listeners.DTableViewerListener;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DFeatureColumnEditingSupport;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableColumnHeaderLabelProvider;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableContentProvider;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableDecoratingLabelProvider;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableLineLabelProvider;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTargetColumnEditingSupport;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.TableUIUpdater;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTableViewerManager;
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * This class manages the tree viewer for display the DTable.
 * 
 * @author lredor
 */
public class DTableViewerManager extends AbstractDTableViewerManager {

    /** The key for the image which represents an export action. */
    public static final String EXPORT_IMG = "table/export"; //$NON-NLS-1$

    /** The key for the image which represents a hide action. */
    public static final String HIDE_IMG = "table/hide"; //$NON-NLS-1$

    /** The key for the image which represents a reveal action. */
    public static final String REVEAL_IMG = "table/reveal"; //$NON-NLS-1$

    /** The key for the image which represents a delete action. */
    public static final String DELETE_IMG = "table/delete"; //$NON-NLS-1$

    /** The key for the image which represents a createLine action. */
    public static final String CREATE_LINE = "table/newLine"; //$NON-NLS-1$

    /** The key for the image which represents a createColumn action. */
    public static final String CREATE_COLUMN = "table/newColumn"; //$NON-NLS-1$

    /** The key for the image which represents a delete action. */
    public static final String REFRESH_IMG = "table/refresh"; //$NON-NLS-1$

    /** The key for the image which represents a delete action. */
    public static final String SHOW_PROPERTIES_VIEW = "table/prop_ps"; //$NON-NLS-1$

    /** The key for the image which represents a sortByLine action. */
    public static final String SORT_BY_LINE = "table/sortByLine"; //$NON-NLS-1$

    /** The key for the image which represents a sortByColumn action. */
    public static final String SORT_BY_COLUMN = "table/sortByColumn"; //$NON-NLS-1$

    /** The key for the image which represents a hide/reveal action. */
    public static final String HIDE_REVEAL_IMG = "table/hideReveal"; //$NON-NLS-1$

    /**
     * Use to store the semantic column in SWT column.
     */
    public static final String TABLE_COLUMN_DATA = "org.eclipse.sirius.table.ui.dTableColumn"; //$NON-NLS-1$

    // The imageRegistry for the action images
    private static ImageRegistry imageRegistry = new ImageRegistry();

    static {
        imageRegistry.put(HIDE_IMG, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(HIDE_IMG)));
        imageRegistry.put(REVEAL_IMG, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(REVEAL_IMG)));
        imageRegistry.put(DELETE_IMG, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(DELETE_IMG)));
        imageRegistry.put(REFRESH_IMG, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(REFRESH_IMG)));
        imageRegistry.put(CREATE_LINE, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(CREATE_LINE)));
        imageRegistry.put(CREATE_COLUMN, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(CREATE_COLUMN)));
        imageRegistry.put(SHOW_PROPERTIES_VIEW, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(SHOW_PROPERTIES_VIEW)));
        imageRegistry.put(SORT_BY_LINE, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(SORT_BY_LINE)));
        imageRegistry.put(SORT_BY_COLUMN, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(SORT_BY_COLUMN)));
        imageRegistry.put(HIDE_REVEAL_IMG, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(HIDE_REVEAL_IMG)));
        imageRegistry.put(EXPORT_IMG, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(EXPORT_IMG)));
    }

    /**
     * the current active column.
     */
    private int activeColumn = -1;

    private DTableViewerListener tableViewerListener;

    private ITableCommandFactory tableCommandFactory;

    private EditorCreateLineMenuAction createLineMenu = new EditorCreateLineMenuAction();

    private EditorCreateTargetColumnMenuAction createTargetColumnMenu = new EditorCreateTargetColumnMenuAction();

    private TableUIUpdater tableUIUpdater;

    private DescriptionFileChangedNotifier descriptionFileChangedNotifier;

    private DTableContentProvider dTableContentProvider;

    private DTableMenuListener actualMenuListener;

    private SelectDRepresentationElementsListener selectTableElementsListener;

    /**
     * The constructor.
     * 
     * @param parent
     *            The parent composite
     * @param dTable
     *            The input DTable
     * @param domain
     *            The transactional editing domain of this viewer
     * @param accessor
     *            The accessor for the model
     * @param tableCommandFactory
     *            The EMF command factory
     * @param tableEditor
     *            The associated editor
     */
    public DTableViewerManager(final Composite parent, final DTable dTable, final TransactionalEditingDomain domain, final ModelAccessor accessor, final ITableCommandFactory tableCommandFactory,
            final AbstractDTableEditor tableEditor) {
        super(parent, dTable, domain, accessor, tableCommandFactory, tableEditor);
        this.tableCommandFactory = tableCommandFactory;
        // Initialize the resize/expand/collapse listener
        tableViewerListener = new DTableViewerListener(this, accessor, domain);

        this.createTreeViewer(parent);
    }

    public static ImageRegistry getImageRegistry() {
        return imageRegistry;
    }

    /**
     * Create the TreeViewer.
     * 
     * Problem for action on column header :
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=23103
     * 
     * @param composite
     *            the parent composite
     */
    @Override
    protected void createTreeViewer(final Composite composite) {
        // Create a composite to hold the children
        final GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_BOTH);
        composite.setLayoutData(gridData);
        final TreeColumnLayout treeLayout = new TreeColumnLayout();
        composite.setLayout(treeLayout);
        // Create and setup the TreeViewer
        final int style = SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.MULTI;
        treeViewer = new DTableTreeViewer(composite, style, this);
        // Add a focus listener to deactivate the EMF actions on the Tree
        treeViewer.getTree().addFocusListener(new DTableTreeFocusListener(treeEditor, treeViewer.getTree()));
        initializeDragSupport();
        sortListener = new DLinesSorter(getEditingDomain(), getEditor().getTableModel());
        // 1st column with line labels
        TreeViewerColumn headerTreeColumn = addFirstColumn(treeLayout);

        // Next columns
        int index = 1;
        for (final DColumn column : ((DTable) dRepresentation).getColumns()) {
            addNewColumn(column, index++);
        }
        treeViewer.setUseHashlookup(true);
        tableUIUpdater = new TableUIUpdater(this, dRepresentation);
        selectTableElementsListener = new SelectDRepresentationElementsListener(treeEditor, false);
        descriptionFileChangedNotifier = new DescriptionFileChangedNotifier(this);
        dTableContentProvider = new DTableContentProvider();
        treeViewer.setContentProvider(dTableContentProvider);
        // The input for the table viewer is the instance of DTable
        treeViewer.setInput(dRepresentation);

        treeViewer.getTree().setLinesVisible(true);
        treeViewer.getTree().setHeaderVisible(true);
        fillMenu();
        triggerColumnSelectedColumn();

        // Expands the line according to the model
        treeViewer.setExpandedElements(TableHelper.getExpandedLines((DTable) dRepresentation).toArray());

        // Pack after expand for resize column on all subLines
        for (int i = 0; i < treeViewer.getTree().getColumnCount(); i++) {
            // Do the pack only if the ColumnData is a ColumnWeightData
            final Object data = treeViewer.getTree().getColumn(i).getData(AbstractDTableViewerManager.LAYOUT_DATA);
            if (data instanceof ColumnWeightData) {
                treeViewer.getTree().getColumn(i).pack();
            }
        }
        treeViewer.addTreeListener(tableViewerListener);
        // Manage height of the lines, selected colors,
        triggerCustomDrawingTreeItems();

        // Create a new CellFocusManager
        final TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(treeViewer, new FocusCellOwnerDrawHighlighter(treeViewer));
        // Create a TreeViewerEditor with focusable cell
        TreeViewerEditor.create(treeViewer, focusCellManager, new DTableColumnViewerEditorActivationStrategy(treeViewer), ColumnViewerEditor.TABBING_HORIZONTAL
                | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
        // Set after the setInput to avoid layout call it several time for
        // nothing at opening
        headerTreeColumn.getColumn().addControlListener(tableViewerListener);
        initializeKeyBindingSupport();
    }

    private TreeViewerColumn addFirstColumn(TreeColumnLayout treeLayout) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.ADD_SWT_COLUMN_KEY);
        final TreeViewerColumn headerTreeColumn = new TreeViewerColumn(treeViewer, SWT.CENTER, 0);
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);
        headerTreeColumn.getColumn().setText(""); //$NON-NLS-1$
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);

        ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
        CellLabelProvider lineheaderColumnLabelProvider = new DTableLineLabelProvider(decorator) {
            /* Display gray background for the OS other than GTK. */
            @Override
            public Color getBackground(final Object element) {
                if (IS_GTK_OS) {
                    // We could desactivate the gray background color for Linux
                    // return super.getBackground(element);
                    return Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
                } else {
                    return Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
                }
            }
        };
        headerTreeColumn.setLabelProvider(lineheaderColumnLabelProvider);
        int headerColumnWidth = ((DTable) dRepresentation).getHeaderColumnWidth();
        if (headerColumnWidth != 0) {
            treeLayout.setColumnData(headerTreeColumn.getColumn(), new ColumnPixelData(headerColumnWidth));
            if (headerTreeColumn.getColumn().getWidth() != headerColumnWidth) {
                headerTreeColumn.getColumn().setWidth(headerColumnWidth);
            }
        } else {
            treeLayout.setColumnData(headerTreeColumn.getColumn(), new ColumnWeightData(1));
            if (IS_GTK_OS) {
                // Do not launch treeViewerColumn.getColumn().pack() here
                // for windows because the size is computed only with the
                // headerText and under windows this size is fixed with this
                // value for the next pack. Under linux it's better with this
                // pack() here.
                // Not really understood...
                headerTreeColumn.getColumn().pack();
            }
        }

        headerTreeColumn.getColumn().addListener(SWT.Selection, sortListener);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.ADD_SWT_COLUMN_KEY);

        return headerTreeColumn;
    }

    private void initializeKeyBindingSupport() {
        treeViewer.getTree().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    DeleteLinesAction deleteLinesAction = new DeleteLinesAction(getEditingDomain(), getTableCommandFactory());
                    deleteLinesAction.setLines(getSelectedLines());
                    if (deleteLinesAction.canExecute()) {
                        deleteLinesAction.run();
                    }
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {
            };
        });
    }

    /**
     * Initializes Drag support for the current DTable.
     */
    protected void initializeDragSupport() {
        int supportedOperations = DND.DROP_MOVE | DND.DROP_LINK;
        ISelectionProvider selectionProvider = this.treeViewer;
        this.treeViewer.addDragSupport(supportedOperations, new ByteArrayTransfer[] { LocalSelectionTransfer.getTransfer() }, new ModelDragTargetAdapter(selectionProvider));
    }

    /**
     * Initialize a cache and add, if needed, the contextual menu for the table. <BR>
     * Cached the actions of creation and deletion in order to increase
     * performance and not calculate it on each contextual menu.<BR>
     * Problem for action on column header :
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=23103 <BR>
     */
    @Override
    public void fillMenu() {
        if (descriptionFileChanged) {
            descriptionFileChanged = false;
            final Map<TableMapping, DeleteTargetColumnAction> mappingToDeleteActions = Maps.newHashMap();
            final Map<TableMapping, List<AbstractToolAction>> mappingToCreateActions = Maps.newHashMap();
            final List<AbstractToolAction> createActionsForTable = Lists.newArrayList();
            calculateAvailableMenus(mappingToDeleteActions, mappingToCreateActions, createActionsForTable);

            mgr.setRemoveAllWhenShown(true);
            if (actualMenuListener != null) {
                mgr.removeAll();
                actualMenuListener.setMappingToCreateActions(mappingToCreateActions);
                actualMenuListener.setMappingToDeleteActions(mappingToDeleteActions);
                actualMenuListener.setCreateActionsForTable(createActionsForTable);
            } else {
                actualMenuListener = new DTableMenuListener((DTable) dRepresentation, this, mappingToCreateActions, mappingToDeleteActions, createActionsForTable);
                mgr.addMenuListener(actualMenuListener);

                final Menu menu = mgr.createContextMenu(treeViewer.getControl());
                treeViewer.getControl().setMenu(menu);
                // Add this line to have others contextual menus
                treeEditor.getSite().registerContextMenu(mgr, treeViewer);
            }
            getCreateLineMenu().update(createActionsForTable);
            getCreateTargetColumnMenu().update(createActionsForTable);
        }
    }

    /**
     * Create the menus according to the {@link TableMapping} and the associated
     * {@link CreateTool} and {@link DeleteTool}.
     * 
     * @param mappingToDeleteActions
     *            A map which associates {@link TableMapping} with the
     *            corresponding {@link DeleteTargetColumnAction}
     * @param mappingToCreateActions
     *            A map which associates {@link TableMapping} with the
     *            corresponding list of {@link AbstractToolAction} (
     *            {@link CreateLineAction} or {@link CreateTargetColumnAction})
     * @param createActionsForTable
     *            A list of the actions for create lines under the table.
     */
    private void calculateAvailableMenus(final Map<TableMapping, DeleteTargetColumnAction> mappingToDeleteActions, final Map<TableMapping, List<AbstractToolAction>> mappingToCreateActions,
            final List<AbstractToolAction> createActionsForTable) {

        final TableDescription tableDescription = ((DTable) dRepresentation).getDescription();

        if (tableDescription != null) {
            // Actions on lines
            calculateAvailableMenusForLine(tableDescription.getAllLineMappings(), mappingToCreateActions, new ArrayList<LineMapping>());

            // Actions on table
            final EList<? extends CreateTool> createLineTools = tableDescription.getAllCreateLine();
            for (final CreateTool createTool : createLineTools) {
                final CreateLineAction createLineAction = new CreateLineAction(createTool, getEditingDomain(), getTableCommandFactory());
                createLineAction.setTable((DTable) dRepresentation);
                createActionsForTable.add(createLineAction);
            }
            if (tableDescription instanceof CrossTableDescription) {
                // Actions on target columns
                calculateAvailableMenusForColumn(((CrossTableDescription) tableDescription).getOwnedColumnMappings(), mappingToDeleteActions, mappingToCreateActions);
                // Actions on table
                final EList<? extends CreateTool> createColumnsTools = ((CrossTableDescription) tableDescription).getCreateColumn();
                for (final CreateTool createTool : createColumnsTools) {
                    final CreateTargetColumnAction createTargetColumnAction = new CreateTargetColumnAction(createTool, getEditingDomain(), getTableCommandFactory());
                    createTargetColumnAction.setTable((DTable) dRepresentation);
                    createActionsForTable.add(createTargetColumnAction);
                }
            }

        }

    }

    /**
     * Create the menus according to the {@link ElementColumnMapping} and the
     * associated {@link CreateTool} and {@link DeleteTool}.
     * 
     * @param columnMappings
     *            List of {@link ElementColumnMapping}
     * @param mappingToDeleteActions
     *            A map which associates {@link TableMapping} with the
     *            corresponding {@link DeleteTargetColumnAction}
     * @param mappingToCreateActions
     *            A map which associates {@link TableMapping} with the
     *            corresponding list of {@link AbstractToolAction} (
     *            {@link CreateLineAction} or {@link CreateTargetColumnAction})
     */
    private void calculateAvailableMenusForColumn(final EList<ElementColumnMapping> columnMappings, final Map<TableMapping, DeleteTargetColumnAction> mappingToDeleteActions,
            final Map<TableMapping, List<AbstractToolAction>> mappingToCreateActions) {
        for (final ElementColumnMapping mapping : columnMappings) {
            if (mapping != null && !mappingToDeleteActions.keySet().contains(mapping) && !mappingToCreateActions.keySet().contains(mapping)) {
                final DeleteTool deleteTool = mapping.getDelete();
                // We add an DeleteLineAction even if the DeleteTool is null
                // (it's an delete action without specific tasks)
                mappingToDeleteActions.put(mapping, new DeleteTargetColumnAction(deleteTool, getEditingDomain(), tableCommandFactory));
                final EList<? extends CreateTool> createTools = mapping.getCreate();
                List<AbstractToolAction> existingCreateTools = mappingToCreateActions.get(mapping);
                if (existingCreateTools == null) {
                    existingCreateTools = new ArrayList<AbstractToolAction>();
                }
                for (final CreateTool createTool : createTools) {
                    existingCreateTools.add(new CreateTargetColumnAction(createTool, getEditingDomain(), tableCommandFactory));
                }
                mappingToCreateActions.put(mapping, existingCreateTools);
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
     *            A map which associates {@link TableMapping} with the
     *            corresponding list of {@link AbstractToolAction} (
     *            {@link CreateLineAction} or {@link CreateTargetColumnAction})
     */
    private void calculateAvailableMenusForLine(final EList<LineMapping> lineMappings, final Map<TableMapping, List<AbstractToolAction>> mappingToCreateActions,
            final List<LineMapping> processedLineMappings) {
        for (final LineMapping lineMapping : lineMappings) {
            if (lineMapping != null && !mappingToCreateActions.keySet().contains(lineMapping)) {
                final EList<? extends CreateTool> createTools = lineMapping.getCreate();
                List<AbstractToolAction> existingCreateTools = mappingToCreateActions.get(lineMapping);
                if (existingCreateTools == null) {
                    existingCreateTools = new ArrayList<AbstractToolAction>();
                }
                for (final CreateTool createTool : createTools) {
                    existingCreateTools.add(new CreateLineAction(createTool, getEditingDomain(), getTableCommandFactory()));
                }
                mappingToCreateActions.put(lineMapping, existingCreateTools);
            }
            if (lineMapping != null && !processedLineMappings.contains(lineMapping)) {
                processedLineMappings.add(lineMapping);
                calculateAvailableMenusForLine(lineMapping.getAllSubLines(), mappingToCreateActions, processedLineMappings);
            }
        }
    }

    /**
     * Manage height of the lines, selected colors.
     */
    protected void triggerCustomDrawingTreeItems() {
        // Manage selected colors for cells
        treeViewer.getTree().addListener(SWT.EraseItem, new DTableEraseItemListener(this, treeViewer));
    }

    /**
     * Add a listener on the tree to listen the mouseDouwn or the key left-right
     * arrows and store the activeColumn.
     */
    protected void triggerColumnSelectedColumn() {
        treeViewer.getTree().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(final MouseEvent event) {
                int x = 0;
                for (int i = 0; i < treeViewer.getTree().getColumnCount(); i++) {
                    x += treeViewer.getTree().getColumn(i).getWidth();
                    if (event.x <= x) {
                        activeColumn = i;
                        break;
                    }
                }
            }

        });
        treeViewer.getTree().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.ARROW_LEFT && activeColumn > 0) {
                    activeColumn--;
                    treeViewer.getTree().showColumn(treeViewer.getTree().getColumn(activeColumn));
                } else if (e.keyCode == SWT.ARROW_RIGHT && activeColumn < treeViewer.getTree().getColumnCount() - 1) {
                    activeColumn++;
                    treeViewer.getTree().showColumn(treeViewer.getTree().getColumn(activeColumn));
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {
            };
        });

    }

    /**
     * Return the index of the active column.<BR>
     * Warning : The column 0 represents the line header.
     * 
     * @return the activeColumn
     */
    public int getActiveColumn() {
        return activeColumn;
    }

    /**
     * Get the selected {@link DTreeItem item}.
     * 
     * @return the selected tree items or an empty collection
     */
    public Collection<DLine> getSelectedLines() {
        Collection<DLine> result = Lists.newArrayList();
        if (treeViewer.getTree().getSelectionCount() > 0) {
            for (TreeItem item : treeViewer.getTree().getSelection()) {
                Object data = item.getData();
                if (data instanceof DLine) {
                    result.add((DLine) data);
                }
            }
        }
        return result;
    }

    /**
     * Return the TableCommandFactory.
     * 
     * @return the TableCommandFactory
     */
    public ITableCommandFactory getTableCommandFactory() {
        return tableCommandFactory;
    }

    /**
     * Add a new column in the table.
     * 
     * @param newColumn
     *            The new targetColumn
     * @param index
     *            the index at which to place the newly created column
     */
    private void addNewColumn(final DColumn newColumn, final int index) {
        addNewColumn(newColumn, index, false);
    }

    /**
     * Add a new column in the table.<BR>
     * 
     * @param newColumn
     *            The new targetColumn
     * @param index
     *            the index at which to place the newly created column
     * @param changeInput
     *            true if we must change the input of the SWT tree to null
     *            before adding the SWT column, false otherwise
     */
    public void addNewColumn(final DColumn newColumn, final int index, final boolean changeInput) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.ADD_SWT_COLUMN_KEY);

        if (treeViewer.getTree() != null) {
            for (TreeColumn tCol : treeViewer.getTree().getColumns()) {
                Object data = tCol.getData(TABLE_COLUMN_DATA);
                if (data != null && data.equals(newColumn)) {
                    return;
                }
            }
        }

        final TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.LEFT, index);
        if (newColumn.getLabel() != null) {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);

            TreeColumn treeColumn = treeViewerColumn.getColumn();

            ILabelProvider dTableColumnHeaderLabelProvider = new DTableColumnHeaderLabelProvider();

            String text = dTableColumnHeaderLabelProvider.getText(newColumn);
            treeColumn.setText(text);

            Image image = dTableColumnHeaderLabelProvider.getImage(newColumn);
            treeColumn.setImage(image);

            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);
        }
        if (newColumn.isVisible()) {
            if (newColumn.getWidth() != 0) {
                ((TreeColumnLayout) treeViewer.getControl().getParent().getLayout()).setColumnData(treeViewerColumn.getColumn(), new ColumnPixelData(newColumn.getWidth()));
                if (treeViewerColumn.getColumn().getWidth() != newColumn.getWidth()) {
                    treeViewerColumn.getColumn().setWidth(newColumn.getWidth());
                }
            } else {
                ((TreeColumnLayout) treeViewer.getControl().getParent().getLayout()).setColumnData(treeViewerColumn.getColumn(), new ColumnWeightData(1));
                if (IS_GTK_OS) {
                    // Do not launch treeViewerColumn.getColumn().pack() here
                    // for windows because the size is computed only with the
                    // headerText and under windows this size is fixed with this
                    // value for the next pack. Under linux it's better with
                    // this pack() here.
                    // Not really understood...
                    treeViewerColumn.getColumn().pack();
                }
            }
        } else {
            // Set the size of the column to 0
            ((TreeColumnLayout) treeViewer.getControl().getParent().getLayout()).setColumnData(treeViewerColumn.getColumn(), new ColumnPixelData(0));
        }

        // Add the LabelProvider with decorating feature
        ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
        DTableDecoratingLabelProvider labelProvider = new DTableDecoratingLabelProvider(newColumn, decorator);
        treeViewerColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(labelProvider));

        if (newColumn instanceof DFeatureColumn) {
            treeViewerColumn.setEditingSupport(new DFeatureColumnEditingSupport(treeViewer, (DFeatureColumn) newColumn, getEditingDomain(), getAccessor(), getTableCommandFactory(),
                    (AbstractDTableEditor) treeEditor));
        } else if (newColumn instanceof DTargetColumn) {
            treeViewerColumn.setEditingSupport(new DTargetColumnEditingSupport(treeViewer, (DTargetColumn) newColumn, getEditingDomain(), getAccessor(), tableCommandFactory,
                    (AbstractDTableEditor) treeEditor));
        }
        treeViewerColumn.getColumn().setData(TABLE_COLUMN_DATA, newColumn);
        treeViewerColumn.getColumn().addControlListener(tableViewerListener);
        treeViewerColumn.getColumn().addListener(SWT.Selection, sortListener);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.ADD_SWT_COLUMN_KEY);
    }

    /**
     * Remove a column from the table.
     * 
     * @param oldColumn
     *            The old column to remove
     */

    public void removeOldColumn(final DColumn oldColumn) {
        for (TreeColumn treeColumn : treeViewer.getTree().getColumns()) {
            DColumn columnData = (DColumn) treeColumn.getData(TABLE_COLUMN_DATA);
            if (columnData != null && columnData.equals(oldColumn)) {
                treeColumn.dispose();
                break;
            }
        }
    }

    /**
     * Return the corresponding editor.
     * 
     * @return a table editor
     */
    @Override
    public AbstractDTableEditor getEditor() {
        return (AbstractDTableEditor) treeEditor;
    }

    /**
     * Return the menu which is display in the toolBar.
     * 
     * @return the menu for create lines on the root
     */
    public EditorCreateLineMenuAction getCreateLineMenu() {
        return createLineMenu;
    }

    /**
     * Return the menu which is display in the toolBar.
     * 
     * @return the menu for create columns on the root
     */
    public EditorCreateTargetColumnMenuAction getCreateTargetColumnMenu() {
        return createTargetColumnMenu;
    }

    /**
     * Release resources.
     */
    @Override
    public void dispose() {
        treeViewer.removeTreeListener(tableViewerListener);
        tableViewerListener = null;
        descriptionFileChangedNotifier.dispose();
        descriptionFileChangedNotifier = null;
        tableUIUpdater.dispose();
        tableUIUpdater = null;
        selectTableElementsListener.dispose();
        selectTableElementsListener = null;
        dTableContentProvider.dispose();
        dTableContentProvider = null;
        super.dispose();
        createLineMenu.dispose();
        createLineMenu = null;
        createTargetColumnMenu.dispose();
        createTargetColumnMenu = null;
    }

}
