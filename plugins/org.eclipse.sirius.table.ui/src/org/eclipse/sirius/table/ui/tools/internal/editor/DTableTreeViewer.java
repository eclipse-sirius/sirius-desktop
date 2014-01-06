/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor;

import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.ui.tools.internal.editor.listeners.DLineExpansionChecker;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableColumnHeaderLabelProvider;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeViewer;
import org.eclipse.sirius.ui.tools.internal.editor.DTableColumnViewerEditorActivationStrategy;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * A specific TreeViewer for the specials needs of DTable :
 * <UL>
 * <LI>Pack the column,</LI>
 * <LI>Show the properties of a cell in the properties view.</LI>
 * </UL>
 * 
 * @author lredor
 */
public class DTableTreeViewer extends AbstractDTreeViewer {

    private DCell selectedCell;

    private DTableViewerManager manager;

    private Character firstEditionCharacter;

    private DLineExpansionChecker dLineExpansionChecker;

    /**
     * Creates a tree viewer on the given tree control. The viewer has no input,
     * no content provider, a default label provider, no sorter, and no filters.
     * 
     * @param parent
     *            the parent control
     * @param style
     *            the SWT style bits used to create the tree.
     * @param manager
     *            The parent {@link DTableViewerManager manager}
     */
    public DTableTreeViewer(final Composite parent, final int style, final DTableViewerManager manager) {
        super(parent, style);
        this.manager = manager;
        this.dLineExpansionChecker.setPermissionAuthority(manager.getAccessor().getPermissionAuthority());
        this.dLineExpansionChecker.setDTableViewerManager(manager);
    }

    /**
     * Overridden to add the {@link DLineExpansionChecker}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void hookControl(Control control) {
        dLineExpansionChecker = new DLineExpansionChecker(control);
        super.hookControl(control);
    }

    /**
     * Override the inputChanged to pack the columns.
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.AbstractTableViewer#internalRefresh(java.lang.Object,
     *      boolean)
     */
    @Override
    protected void inputChanged(final Object input, final Object oldInput) {
        if (input instanceof DTable) {
            TableHelper.fillCache((DTable) input);
        }
        try {
            super.inputChanged(input, oldInput);
        } finally {
            TableHelper.clearCache();
        }

    }

    /**
     * We activate the editor only if it's a normal click of mouse and we store
     * the selected cell.
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnViewer#triggerEditorActivationEvent(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
     */
    @Override
    protected void triggerEditorActivationEvent(final ColumnViewerEditorActivationEvent event) {
        if (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && !DTableColumnViewerEditorActivationStrategy.isActivationKey(event) && Character.isLetterOrDigit(event.character)) {
            // Save the first edition character to put it in the cellTextEditor
            setFirstEditionCharacter(event.character);
        } else {
            setFirstEditionCharacter(null);
        }
        super.triggerEditorActivationEvent(event);

        // Store the selected cell
        if (event.getSource() instanceof ViewerCell && ((ViewerCell) event.getSource()).getElement() instanceof DLine) {
            final Tree tree = (Tree) ((ViewerCell) event.getSource()).getViewerRow().getControl();
            final DLine line = (DLine) ((ViewerCell) event.getSource()).getElement();
            final int columnIndexSortedInSwtTable = ((ViewerCell) event.getSource()).getColumnIndex();
            final int[] order = tree.getColumnOrder();
            // Search index of the ordered column
            int columnIndexDisplayInSwtTable = -1;
            for (int i = 0; i < order.length && columnIndexDisplayInSwtTable == -1; i++) {
                if (order[i] == columnIndexSortedInSwtTable) {
                    columnIndexDisplayInSwtTable = i;
                }
            }
            final int columnIndexInTable = columnIndexDisplayInSwtTable - 1;
            Option<DCell> optionalCell = Options.newNone();
            if (columnIndexInTable != -1) {
                DColumn column = TableHelper.getTable(line).getColumns().get(columnIndexInTable);
                optionalCell = TableHelper.getCell(line, column);
            }
            if (!optionalCell.some()) {
                // This cell corresponds to the line header or isn't exist
                selectedCell = null;
                if (event.eventType == SWT.KeyDown && (event.keyCode == SWT.ARROW_LEFT || event.keyCode == SWT.ARROW_RIGHT)) {
                    // We update the selection for updating the properties view
                    // because it's update only for line change (Up or down key)
                    updateSelection(new StructuredSelection(line));
                }
            } else {
                selectedCell = optionalCell.get();
                if (event.eventType == SWT.KeyDown && (event.keyCode == SWT.ARROW_LEFT || event.keyCode == SWT.ARROW_RIGHT)) {
                    // We update the selection for updating the properties view
                    // because it's update only for line change (Up or down key)
                    updateSelection(new StructuredSelection(selectedCell));
                }
            }
        } else {
            selectedCell = null;
        }
    }

    /**
     * Fire a selection change on the cell after the selection change on the
     * line to show the properties of the cell in the "properties view".
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.StructuredViewer#updateSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    protected void updateSelection(final ISelection selection) {
        if (selectedCell != null) {
            // We change the selection to pass the DCell instead of the DLine
            final ISelection cellSelection = new StructuredSelection(selectedCell);
            final SelectionChangedEvent event = new SelectionChangedEvent(this, cellSelection);
            fireSelectionChanged(event);
        } else {
            super.updateSelection(selection);
        }
    }

    /**
     * Overridden to use {@link DTableFirstColumnLabelProvider} to recompute
     * text/image in case of <code>item</code> is a {@link TreeColumn}.
     * 
     * {@inheritDoc}
     */
    public void refreshItem(final Item item, final DRepresentationElement dRepresentationElement) {
        if (item instanceof TreeColumn) {
            preservingSelection(new Runnable() {
                public void run() {
                    TreeColumn treeColumn = (TreeColumn) item;

                    ILabelProvider dTableColumnHeaderLabelProvider = new DTableColumnHeaderLabelProvider();

                    String text = dTableColumnHeaderLabelProvider.getText(dRepresentationElement);
                    treeColumn.setText(text);

                    Image image = dTableColumnHeaderLabelProvider.getImage(dRepresentationElement);
                    treeColumn.setImage(image);
                }
            });

        } else {
            super.refreshItem(item, dRepresentationElement);
        }
    }

    /**
     * Add a new column in the table.
     * 
     * @param newColumn
     *            The new targetColumn to add
     */
    public void addNewColumn(final DTargetColumn newColumn) {
        manager.addNewColumn(newColumn, -1, true);
    }

    /**
     * Add a new column in the table.
     * 
     * @param position
     *            The position of the new column
     * @param newColumn
     *            The new targetColumn to add
     */
    public void addNewColumn(final int position, final DColumn newColumn) {
        manager.addNewColumn(newColumn, position, true);
    }

    /**
     * Remove a column from the table.
     * 
     * @param oldColumn
     *            The old targetColumn to remove
     */
    public void removeOldColumn(final DColumn oldColumn) {
        manager.removeOldColumn(oldColumn);
    }

    /**
     * Remove a column from the table.
     * 
     * @param position
     *            The position of the old column
     */
    public void removeOldColumn(final int position) {
        manager.removeOldColumn(position);
    }

    /**
     * Check the table.
     * 
     * @param table
     *            the table to test
     * @return true if the table is equals to the dTable of this treeViewer,
     *         false otherwise
     */
    public boolean isSameTable(final DTable table) {
        return manager.isSameTable(table);
    }

    /**
     * Add in the treeViewer the new columns of the DTable a,d remove in the
     * treeViewer the columns that are no longer in the DTable.
     */
    public void refreshColumns() {
        manager.refreshColumns();
    }

    /**
     * Add and initialize the contextual menu for the table.
     */
    public void fillMenu() {
        manager.fillMenu();
    }

    /**
     * Changed descriptionFileChanged state.
     * 
     * @param modified
     *            Indicates whether the odesign file has changed since the last
     *            load menus
     */
    public void setDescriptionFileChanged(final boolean modified) {
        manager.setDescriptionFileChanged(modified);
    }

    public Character getFirstEditionCharacter() {
        return firstEditionCharacter;
    }

    protected void setFirstEditionCharacter(final Character firstEditionCharacter) {
        this.firstEditionCharacter = firstEditionCharacter;
    }

    /**
     * Overridden to remove the {@link DLineExpansionChecker}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void handleDispose(DisposeEvent event) {
        dLineExpansionChecker.dispose();
        super.handleDispose(event);
    }
}
