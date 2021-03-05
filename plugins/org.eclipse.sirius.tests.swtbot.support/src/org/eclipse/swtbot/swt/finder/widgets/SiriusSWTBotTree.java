/*******************************************************************************
 * Copyright (c) 2008, 2019 Ketan Padegaonkar and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ketan Padegaonkar - initial API and implementation
 *     Kristine Jetzke - Bug 420121
 *     Stephane Bouchet (Intel Corporation) - Bug 451547
 *     Patrick Tasse - Improve SWTBot menu API and implementation (Bug 479091)
 *     Aparna Argade - Bug 508710
 *******************************************************************************/
package org.eclipse.swtbot.swt.finder.widgets;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.ReferenceBy;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.SWTBotWidget;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.ArrayResult;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.IntResult;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.StringResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.MessageFormat;
import org.eclipse.swtbot.swt.finder.utils.StringUtils;
import org.eclipse.swtbot.swt.finder.utils.TableCollection;
import org.eclipse.swtbot.swt.finder.utils.TableRow;
import org.eclipse.swtbot.swt.finder.utils.internal.Assert;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;

// CHECKSTYLE:OFF

/**
 * Class copied from {@link SWTBotTree} (from SwtBot version 2.8). There is no change (except the 2 static methods of
 * the fact to deal with {@link SiriusSWTBotTreeItem}). This copy is just to benefit of the improvement corresponding to
 * bugzilla 571838.<BR>
 * 
 * @author Ketan Padegaonkar &lt;KetanPadegaonkar [at] gmail [dot] com&gt;
 * @author Joshua Gosse &lt;jlgosse [at] ca [dot] ibm [dot] com&gt;
 * @version $Id$
 */
@SWTBotWidget(clasz = Tree.class, preferredName = "tree", referenceBy = { ReferenceBy.LABEL })
public class SiriusSWTBotTree extends AbstractSWTBotControl<Tree> {

    /**
     * Constructs an instance of this object with the given tree.
     *
     * @param tree
     *            the widget.
     * @param description
     *            the description of the widget, this will be reported by {@link #toString()}
     * @throws WidgetNotFoundException
     *             if the widget is <code>null</code> or widget has been disposed.
     */
    public SiriusSWTBotTree(Tree tree, SelfDescribing description) throws WidgetNotFoundException {
        super(tree, description);
    }

    /**
     * Constructs an instance of this object with the given tree.
     *
     * @param tree
     *            the widget.
     * @throws WidgetNotFoundException
     *             if the widget is <code>null</code> or widget has been disposed.
     */
    public SiriusSWTBotTree(Tree tree) throws WidgetNotFoundException {
        this(tree, null);
    }

    /**
     * Method returning a {@link SiriusSWTBotTree} instead of a {@link SWTBotTree}, like the method
     * {@link SWTBot#tree()}.
     * 
     * @param bot
     *            The bot from which we want the tree.
     * @return the corresponding {@link SiriusSWTBotTree}
     */
    public static SiriusSWTBotTree tree(SWTBot bot) {
        return tree(bot, 0);
    }

    /**
     * Method returning a {@link SiriusSWTBotTree} instead of a {@link SWTBotTree}, like the method
     * {@link SWTBot#tree()}.
     * 
     * @param bot
     *            The bot from which we want the tree.
     * @param index
     *            the index of the widget.
     * @return the corresponding {@link SiriusSWTBotTree}
     */
    public static SiriusSWTBotTree tree(SWTBot bot, int index) {
        Matcher matcher = allOf(widgetOfType(Tree.class));
        return new SiriusSWTBotTree((Tree) bot.widget(matcher, index), matcher);
    }

    /**
     * Gets the number of rows in the tree.
     *
     * @return the number of rows in the tree
     */
    public int rowCount() {
        return syncExec(new IntResult() {
            @Override
            public Integer run() {
                return widget.getItems().length;
            }
        });
    }

    /**
     * Gets the column count of this tree.
     *
     * @return the number of columns in the tree
     */
    public int columnCount() {
        return syncExec(new IntResult() {
            @Override
            public Integer run() {
                return widget.getColumnCount();
            }
        });
    }

    /**
     * Gets the columns of this tree.
     * <p>
     * To interact with one of the columns, use {@link #header(String)}.
     *
     * @return the list of columns in the tree.
     */
    public List<String> columns() {
        final int columnCount = columnCount();
        return syncExec(new ListResult<String>() {
            @Override
            public List<String> run() {
                String columns[] = new String[columnCount];
                for (int i = 0; i < columnCount; i++)
                    columns[i] = widget.getColumn(i).getText();
                return new ArrayList<String>(Arrays.asList(columns));
            }
        });
    }

    /**
     * Gets the column matching the given label.
     *
     * @param label
     *            the header text.
     * @return the header of the tree.
     * @throws WidgetNotFoundException
     *             if the header is not found.
     * @since 2.1.2
     */
    public SWTBotTreeColumn header(final String label) throws WidgetNotFoundException {
        TreeColumn column = syncExec(new Result<TreeColumn>() {
            @Override
            public TreeColumn run() {
                TreeColumn[] columns = widget.getColumns();
                for (TreeColumn column : columns) {
                    if (column.getText().equals(label))
                        return column;
                }
                return null;
            }
        });
        return new SWTBotTreeColumn(column, widget);
    }

    /**
     * Gets the cell data for the given row/column index.
     *
     * @param row
     *            the row index.
     * @param column
     *            the column index.
     * @return the cell at the location specified by the row and column
     */
    public String cell(final int row, final int column) {
        int rowCount = rowCount();
        int columnCount = columnCount();

        Assert.isLegal(row < rowCount, "The row number (" + row + ") is more than the number of rows(" + rowCount + ") in the tree."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Assert.isLegal(column < columnCount, "The column number (" + column + ") is more than the number of column(" + columnCount //$NON-NLS-1$ //$NON-NLS-2$
                + ") in the tree."); //$NON-NLS-1$

        return syncExec(new StringResult() {
            @Override
            public String run() {
                TreeItem item = widget.getItem(row);
                return item.getText(column);
            }
        });
    }

    /**
     * Gets the cell data for the given row/column information.
     *
     * @param row
     *            the row index.
     * @param columnName
     *            the column name.
     * @return the cell in the tree at the specified row and column header.
     */
    public String cell(int row, String columnName) {
        List<String> columns = columns();
        Assert.isLegal(columns.contains(columnName), "The column `" + columnName + "' is not found."); //$NON-NLS-1$ //$NON-NLS-2$
        int columnIndex = columns.indexOf(columnName);
        if (columnIndex == -1)
            return ""; //$NON-NLS-1$
        return cell(row, columnIndex);
    }

    /**
     * Gets the current selection count.
     *
     * @return the number of selected items.
     */
    public int selectionCount() {
        return syncExec(new IntResult() {
            @Override
            public Integer run() {
                return widget.getSelectionCount();
            }
        });
    }

    /**
     * Gets the table collection representing the selection.
     *
     * @return the selection in the tree
     */
    public TableCollection selection() {
        final int columnCount = columnCount();

        return syncExec(new Result<TableCollection>() {
            @Override
            public TableCollection run() {
                final TableCollection selection = new TableCollection();
                TreeItem[] items = widget.getSelection();
                for (TreeItem item : items) {
                    TableRow tableRow = new TableRow();
                    if (columnCount == 0)
                        tableRow.add(item.getText());
                    else
                        for (int j = 0; j < columnCount; j++)
                            tableRow.add(item.getText(j));
                    selection.add(tableRow);
                }
                return selection;
            }
        });
    }

    /**
     * Selects the items matching the array list. Replaces the current selection. If there is more than one item to
     * select, the tree must have the SWT.MULTI style.
     *
     * @param items
     *            the items to select.
     * @return this same instance.
     */
    public SiriusSWTBotTree select(final String... items) {
        waitForEnabled();
        setFocus();
        if (items.length > 1) {
            assertMultiSelect();
        } else if (items.length == 0) {
            return unselect();
        }
        final List<TreeItem> selection = new ArrayList<TreeItem>();
        for (String item : items) {
            SiriusSWTBotTreeItem si = getTreeItem(item);
            selection.add(si.widget);
        }
        for (int i = 0; i < selection.size(); i++) {
            int stateMask = (i == 0) ? SWT.NONE : SWT.MOD1;
            notifySelect(selection.get(i), stateMask);
        }
        return this;
    }

    /**
     * Selects the items in the array. Useful for cases where you're selecting items whose names are not unique, or
     * items you've exposed one at a time while traversing the tree. Replaces the current selection. If there is more
     * than one item to select, the tree must have the SWT.MULTI style.
     *
     * @param items
     *            the items to select.
     * @return this same instance.
     */
    public SiriusSWTBotTree select(final SiriusSWTBotTreeItem... items) {
        assertEnabled();
        setFocus();
        if (items.length > 1) {
            assertMultiSelect();
        } else if (items.length == 0) {
            return unselect();
        }
        for (int i = 0; i < items.length; i++) {
            int stateMask = (i == 0) ? SWT.NONE : SWT.MOD1;
            notifySelect(items[i].widget, stateMask);
        }
        return this;
    }

    /**
     * Selects all expanded items in the tree. The tree must have the SWT.MULTI style.
     *
     * @since 2.8
     */
    public void selectAll() {
        assertMultiSelect();
        waitForEnabled();
        setFocus();
        TreeItem first = syncExec(new Result<TreeItem>() {
            @Override
            public TreeItem run() {
                if (widget.getItemCount() == 0) {
                    return null;
                }
                return widget.getItem(0);
            }
        });
        if (first == null) {
            return;
        }
        notifySelect(first, SWT.NONE);
        TreeItem last = syncExec(new Result<TreeItem>() {
            @Override
            public TreeItem run() {
                if (widget.getItemCount() == 0) {
                    return null;
                }
                TreeItem last = widget.getItem(widget.getItemCount() - 1);
                while (last.getExpanded() && last.getItemCount() > 0) {
                    last = last.getItem(last.getItemCount() - 1);
                }
                return last;
            }
        });
        if (last != null && last != first) {
            notifySelect(last, SWT.MOD2, selectAllRunnable());
        }
    }

    /**
     * Selects all expanded tree items.
     */
    private Runnable selectAllRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                widget.selectAll();
            }
        };
    }

    /**
     * Unselects the selection in the tree.
     *
     * @return this same instance.
     */
    public SiriusSWTBotTree unselect() {
        waitForEnabled();
        log.debug(MessageFormat.format("Unselecting all in {0}", this)); //$NON-NLS-1$
        TreeItem[] selection = syncExec(new ArrayResult<TreeItem>() {
            @Override
            public TreeItem[] run() {
                return widget.getSelection();
            }
        });
        for (TreeItem item : selection) {
            notifyUnselect(item);
        }
        return this;
    }

    /**
     * Unselects the given tree item.
     *
     * @param item
     *            tree item to unselect
     */
    private Runnable unselectRunnable(final TreeItem item) {
        return new Runnable() {
            @Override
            public void run() {
                widget.deselect(item);
            }
        };
    }

    /**
     * Selects the indices provided. Replaces the current selection. If there is more than one item to select, the tree
     * must have the SWT.MULTI style.
     *
     * @param indices
     *            the indices to select.
     * @return this same instance.
     */
    public SiriusSWTBotTree select(final int... indices) {
        waitForEnabled();
        setFocus();
        if (indices.length > 1) {
            assertMultiSelect();
        } else if (indices.length == 0) {
            return unselect();
        }
        final List<TreeItem> selection = new ArrayList<TreeItem>();
        for (int index : indices) {
            selection.add(getItem(index));
        }
        log.debug(MessageFormat.format("Selecting rows {0} in {1}", Arrays.toString(indices), this)); //$NON-NLS-1$ //$NON-NLS-2$
        for (int i = 0; i < selection.size(); i++) {
            int stateMask = (i == 0) ? SWT.NONE : SWT.MOD1;
            notifySelect(selection.get(i), stateMask);
        }
        return this;
    }

    /**
     * Selects a tree item
     *
     * @param item
     *            the tree item to select
     * @param add
     *            true to add to current selection
     */
    private Runnable selectRunnable(final TreeItem item, final boolean add) {
        return new Runnable() {
            @Override
            public void run() {
                if (add) {
                    widget.select(item);
                } else {
                    // removes earlier selection
                    widget.setSelection(item);
                }
            }
        };
    }

    /**
     * Notifies the tree widget about selection changes.
     *
     * @deprecated Should not be called by users
     */
    @Deprecated
    protected void notifySelect() {
    }

    /**
     * Unselects the specified item and sends notifications.
     *
     * @param unselected
     *            the tree item to unselect
     */
    private void notifyUnselect(TreeItem unselected) {
        notifySelect(unselected, SWT.MOD1, unselectRunnable(unselected));
    }

    /**
     * Selects the specified item and sends notifications.
     *
     * @param selected
     *            the tree item to select.
     * @param stateMask
     *            the state of the keyboard modifier keys.
     */
    private void notifySelect(TreeItem selected, int stateMask) {
        notifySelect(selected, stateMask, selectRunnable(selected, (stateMask & SWT.MOD1) != 0));
    }

    private void notifySelect(TreeItem selected, int stateMask, Runnable runnable) {
        SiriusSWTBotTreeItem item = new SiriusSWTBotTreeItem(selected);
        notify(SWT.MouseEnter);
        notify(SWT.MouseMove);
        notify(SWT.Activate);
        notify(SWT.FocusIn);
        notify(SWT.MouseDown, item.createMouseEvent(1, stateMask, 1));
        notify(SWT.Selection, item.createSelectionEvent(stateMask | SWT.BUTTON1), widget, runnable);
        notify(SWT.MouseUp, item.createMouseEvent(1, stateMask | SWT.BUTTON1, 1));
    }

    /**
     * Attempts to expand all nodes along the path specified by the node array parameter.
     *
     * @param nodes
     *            node path to expand
     * @return the last Tree item that was expanded.
     * @throws WidgetNotFoundException
     *             if any of the nodes on the path do not exist
     */
    public SiriusSWTBotTreeItem expandNode(String... nodes) throws WidgetNotFoundException {
        // TODO: this method should be made iterative instead of recursive
        Assert.isNotEmpty((Object[]) nodes);

        log.debug(MessageFormat.format("Expanding nodes {0} in tree {1}", StringUtils.join(nodes, ">"), this));

        waitForEnabled();
        SiriusSWTBotTreeItem item = getTreeItem(nodes[0]).expand();

        String[] tail = new String[nodes.length - 1];
        System.arraycopy(nodes, 1, tail, 0, nodes.length - 1);
        if (tail.length > 0) {
            item = item.expandNode(tail);
        }

        return item;
    }

    /**
     * Collapses the node matching the node information.
     *
     * @param nodeText
     *            the text on the node.
     * @return the Tree item that was expanded.
     * @throws WidgetNotFoundException
     *             if the node is not found.
     */
    public SiriusSWTBotTreeItem collapseNode(final String nodeText) throws WidgetNotFoundException {
        waitForEnabled();
        return getTreeItem(nodeText).collapse();
    }

    /**
     * Gets the visible row count.
     *
     * @return the number of visible rows
     */
    public int visibleRowCount() {
        return syncExec(new IntResult() {
            @Override
            public Integer run() {
                TreeItem[] items = widget.getItems();
                return getVisibleChildrenCount(items);
            }

            private int getVisibleChildrenCount(TreeItem item) {
                if (item.getExpanded())
                    return getVisibleChildrenCount(item.getItems());
                return 0;
            }

            private int getVisibleChildrenCount(TreeItem[] items) {
                int count = 0;
                for (TreeItem item : items) {
                    int j = getVisibleChildrenCount(item) + 1;
                    count += j;
                }
                return count;
            }
        });

    }

    /**
     * Expands the nodes as if the plus sign was clicked.
     *
     * @param nodeText
     *            the node to be expanded.
     * @param recursive
     *            if the expansion should be recursive.
     * @return the tree item that was expanded.
     * @throws WidgetNotFoundException
     *             if the node is not found.
     */
    public SiriusSWTBotTreeItem expandNode(final String nodeText, final boolean recursive) throws WidgetNotFoundException {
        waitForEnabled();
        return syncExec(new Result<SiriusSWTBotTreeItem>() {
            @Override
            public SiriusSWTBotTreeItem run() {
                SiriusSWTBotTreeItem item;
                try {
                    item = getTreeItem(nodeText);
                    expandNode(item);
                } catch (WidgetNotFoundException e) {
                    throw new RuntimeException(e);
                }
                return item;
            }

            private void expandNode(SiriusSWTBotTreeItem item) throws WidgetNotFoundException {
                item.expand();
                if (recursive)
                    expandTreeItem(item.widget);
            }

            private void expandTreeItem(TreeItem node) throws WidgetNotFoundException {
                TreeItem[] items = node.getItems();
                for (TreeItem item : items) {
                    expandNode(new SiriusSWTBotTreeItem(item));
                }
            }
        });
    }

    /**
     * Gets the tree item matching the given name.
     *
     * @param nodeText
     *            the text on the node.
     * @return the tree item with the specified text.
     * @throws WidgetNotFoundException
     *             if the node was not found.
     */
    public SiriusSWTBotTreeItem getTreeItem(final String nodeText) throws WidgetNotFoundException {
        try {
            new SWTBot().waitUntil(new DefaultCondition() {
                @Override
                public String getFailureMessage() {
                    return "Could not find node with text " + nodeText; //$NON-NLS-1$
                }

                @Override
                public boolean test() throws Exception {
                    return getItem(nodeText) != null;
                }
            });
        } catch (TimeoutException e) {
            throw new WidgetNotFoundException("Timed out waiting for tree item " + nodeText, e); //$NON-NLS-1$
        }
        return new SiriusSWTBotTreeItem(getItem(nodeText));
    }

    /**
     * Gets the item at the given index.
     *
     * @param index
     *            the index of the item to return
     * @return the item at the given index.
     */
    private TreeItem getItem(final int index) {
        return syncExec(new WidgetResult<TreeItem>() {
            @Override
            public TreeItem run() {
                return widget.getItem(index);
            }
        });
    }

    /**
     * Gets the item matching the given name.
     *
     * @param nodeText
     *            the text on the node.
     * @return the tree item with the specified text.
     */
    private TreeItem getItem(final String nodeText) {
        return syncExec(new WidgetResult<TreeItem>() {
            @Override
            public TreeItem run() {
                TreeItem[] items = widget.getItems();
                for (TreeItem item : items) {
                    if (item.getText().equals(nodeText))
                        return item;
                }
                return null;
            }
        });
    }

    /**
     * Gets all the items in the tree.
     *
     * @return the list of all tree items in the tree, or an empty list if there are none.
     * @since 1.0
     */
    public SiriusSWTBotTreeItem[] getAllItems() {
        return syncExec(new ArrayResult<SiriusSWTBotTreeItem>() {
            @Override
            public SiriusSWTBotTreeItem[] run() {
                TreeItem[] items = widget.getItems();
                SiriusSWTBotTreeItem[] result = new SiriusSWTBotTreeItem[items.length];

                for (int i = 0; i < items.length; i++)
                    try {
                        result[i] = new SiriusSWTBotTreeItem(items[i]);
                    } catch (WidgetNotFoundException e) {
                        return new SiriusSWTBotTreeItem[0];
                    }
                return result;
            }
        });
    }

    /**
     * Gets if this tree has items within it.
     *
     * @return <code>true</code> if the tree has any items, <code>false</code> otherwise.
     * @since 1.0
     */
    public boolean hasItems() {
        return syncExec(new BoolResult() {
            @Override
            public Boolean run() {
                return widget.getItemCount() > 0;
            }
        });
    }

    private void assertMultiSelect() {
        Assert.isLegal(hasStyle(widget, SWT.MULTI), "Tree does not support SWT.MULTI, cannot make multiple selections."); //$NON-NLS-1$
    }

    /**
     * Wait until the widget is enabled. Method added here to avoid this exception: java.lang.IllegalAccessError: tried
     * to access method org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot.waitForEnabled()V from class
     * org.eclipse.swtbot.swt.finder.widgets.SiriusSWTBotTreeItem at
     * org.eclipse.swtbot.swt.finder.widgets.SiriusSWTBotTreeItem.waitForEnabled(SiriusSWTBotTreeItem.java:859) at
     * org.eclipse.swtbot.swt.finder.widgets.SiriusSWTBotTreeItem.select(SiriusSWTBotTreeItem.java:361)
     */
    public void waitForEnabled() {
        super.waitForEnabled();
    }
}

// CHECKSTYLE:ON
