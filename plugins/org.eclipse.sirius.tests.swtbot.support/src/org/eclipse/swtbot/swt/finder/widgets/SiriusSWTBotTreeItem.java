/*******************************************************************************
 * Copyright (c) 2008, 2019 Ketan Padegaonkar and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ketan Padegaonkar - initial API and implementation
 *     Cédric Chabanois - http://swtbot.org/bugzilla/show_bug.cgi?id=10
 *     Ketan Patel - https://bugs.eclipse.org/bugs/show_bug.cgi?id=259720
 *     Kristine Jetzke - Bug 379185
 *     Aparna Argade(Cadence Design Systems, Inc.) - Bug 363916
 *     Stephane Bouchet (Intel Corporation) - Bug 451547
 *     Patrick Tasse - Improve SWTBot menu API and implementation (Bug 479091)
 *     Aparna Argade(Cadence Design Systems, Inc.) - Bug 496519
 *******************************************************************************/
package org.eclipse.swtbot.swt.finder.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.ArrayResult;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.IntResult;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.StringResult;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.MessageFormat;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.utils.TableRow;
import org.eclipse.swtbot.swt.finder.utils.TextDescription;
import org.eclipse.swtbot.swt.finder.utils.internal.Assert;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.hamcrest.SelfDescribing;

// CHECKSTYLE:OFF

/**
 * Class copied from {@link SWTBotTreeItem} (from SwtBot version 2.8). The only change concern the addition of method
 * {{@link #doubleClick(int)} as it is done in improvement corresponding to bugzilla 571838.<BR>
 * 
 * @author Ketan Padegaonkar &lt;KetanPadegaonkar [at] gmail [dot] com&gt;
 * @author Ketan Patel
 * @author Joshua Gosse &lt;jlgosse [at] ca [dot] ibm [dot] com&gt;
 * @version $Id$
 */
public class SiriusSWTBotTreeItem extends AbstractSWTBot<TreeItem> {

	/** The parent tree */
	private Tree	tree;

	/**
	 * @param treeItem the widget.
	 * @throws WidgetNotFoundException if the widget is <code>null</code> or widget has been disposed.
	 */
	public SiriusSWTBotTreeItem(final TreeItem treeItem) throws WidgetNotFoundException {
		this(treeItem, null);
	}

	/**
	 * @param treeItem the widget.
	 * @param description the description of the widget, this will be reported by {@link #toString()}
	 * @throws WidgetNotFoundException if the widget is <code>null</code> or widget has been disposed.
	 */
	public SiriusSWTBotTreeItem(final TreeItem treeItem, SelfDescribing description) throws WidgetNotFoundException {
		super(treeItem, description);
		this.tree = syncExec(new WidgetResult<Tree>() {
			@Override
			public Tree run() {
				return treeItem.getParent();
			}
		});
	}

	/**
	 * Returns the text stored at the given column index in the receiver, or empty string if the text has not been set.
	 * Throws an exception if the column is greater than the number of columns in the tree.
	 *
	 * @param column the column index.
	 * @return the cell at the location specified by the column
	 */
	public String cell(final int column) {
		if (column == 0) {
			return getText();
		}
        int columnCount = new SiriusSWTBotTree(tree).columnCount();
		Assert.isLegal(column < columnCount, java.text.MessageFormat.format(
				"The column index ({0}) is more than the number of column({1}) in the tree.", column, columnCount)); //$NON-NLS-1$
		return syncExec(new StringResult() {
			@Override
			public String run() {
				return widget.getText(column);
			}
		});
	}

	/**
	 * Returns the table row representation of cell values
	 *
	 * @return the cell values for this item
	 */
	public TableRow row() {
		return syncExec(new Result<TableRow>() {
			@Override
			public TableRow run() {
				int columnCount = tree.getColumnCount();
				TableRow tableRow = new TableRow();
				if (columnCount == 0)
					tableRow.add(widget.getText());
				else
					for (int j = 0; j < columnCount; j++)
						tableRow.add(widget.getText(j));
				return tableRow;
			}
		});
	}

	/**
	 * Returns the number of items contained in the receiver that are direct item children of the receiver.
	 *
	 * @return the number of items
	 */
	public int rowCount() {
		return syncExec(new IntResult() {
			@Override
			public Integer run() {
				return widget.getItemCount();
			}
		});
	}

	/**
	 * Gets the nodes at the given, zero-relative index in the receiver. Throws an exception if the index is out of
	 * range.
	 *
	 * @param row the index of the item to return
	 * @return the item at the given index
	 */
    public SiriusSWTBotTreeItem getNode(final int row) {
		int rowCount = rowCount();
		Assert.isLegal(row < rowCount,
				java.text.MessageFormat.format("The row number ({0}) is more than the number of rows({1}) in the tree.", row, rowCount)); //$NON-NLS-1$
        return syncExec(new Result<SiriusSWTBotTreeItem>() {
			@Override
            public SiriusSWTBotTreeItem run() {
                return new SiriusSWTBotTreeItem(widget.getItem(row));
			}
		});
	}

	/**
	 * Gets the cell data for the given row/column index.
	 *
	 * @param row the row index.
	 * @param column the column index.
	 * @return the cell at the location specified by the row and column
	 * @see #getNode(int)
	 * @see #cell(int)
	 */
	public String cell(final int row, final int column) {
		return getNode(row).cell(column);
	}

	/**
	 * Expands the tree item to simulate click the plus sign.
	 *
	 * @return the tree item, after expanding it.
	 */
    public SiriusSWTBotTreeItem expand() {
		waitForEnabled();

		if (isExpanded()) {
			log.warn(MessageFormat.format("Tree item {0} is already expanded. Won''t expand it again.", this).toString());
			return this;
		}

		preExpandCollapseNotify();
		syncExec(new VoidResult() {
			@Override
			public void run() {
				notifyTree(SWT.Expand, createEvent());
				widget.setExpanded(true);
			}
		});
		postExpandCollapseNotify();
		return this;
	}

	/**
	 * Collapses the tree item to simulate click the plus sign.
	 *
	 * @return the tree item, after collapsing it.
	 */
    public SiriusSWTBotTreeItem collapse() {
		waitForEnabled();

		if (!isExpanded()) {
			log.warn(MessageFormat.format("Tree item {0} is already collapsed. Won''t collapse it again.", this).toString());
			return this;
		}

		preExpandCollapseNotify();
		syncExec(new VoidResult() {
			@Override
			public void run() {
				notifyTree(SWT.Collapse, createEvent());
				widget.setExpanded(false);
			}
		});
		postExpandCollapseNotify();
		return this;
	}

	private void preExpandCollapseNotify() {
		notifyTree(SWT.Activate, super.createEvent());
		notifyTree(SWT.FocusIn, super.createEvent());
		notifyTree(SWT.MouseDown, createMouseEvent(1, SWT.NONE, 1));
	}

	private void postExpandCollapseNotify() {
		notifyTree(SWT.MouseUp, createMouseEvent(1, SWT.BUTTON1, 1));
	}

	private void notifyTree(int eventType, Event event) {
		notify(eventType, event, tree);
	}

	private void notifyTree(int eventType, Event event, Runnable runnable) {
		notify(eventType, event, tree, runnable);
	}

	@Override
	protected Event createEvent() {
		Event event = super.createEvent();
		event.widget = tree;
		event.item = widget;
		return event;
	}

	/**
	 * Gets the nodes of the tree item.
	 *
	 * @return the list of nodes in the treeitem.
	 */
	public List<String> getNodes() {
		return syncExec(new ListResult<String>() {
			@Override
			public List<String> run() {
				TreeItem[] items = widget.getItems();
				List<String> result = new ArrayList<String>(items.length);
				for (TreeItem item : items)
					result.add(item.getText());
				return result;
			}
		});

	}

	/**
	 * Expands the node matching the given node texts.
	 *
	 * @param nodes the text on the node.
	 * @return the last tree node that was expanded or <code>null</code> if none exists.
	 * @throws WidgetNotFoundException if any of the nodes on the path do not exist
	 */
    public SiriusSWTBotTreeItem expandNode(final String... nodes) {
		Assert.isNotEmpty((Object[]) nodes);
		waitForEnabled();

        SiriusSWTBotTreeItem item = this;
		for (String node : nodes)
			item = item.getNode(node).expand();

		return item;
	}

	/**
	 * Collapses the node matching the given node text.
	 *
	 * @param nodeText the text on the node.
	 * @return the node that was collapsed or <code>null</code> if not match exists.
	 */
    public SiriusSWTBotTreeItem collapseNode(final String nodeText) {
		waitForEnabled();
		return getNode(nodeText).collapse();
	}

	/**
	 * Gets the node matching the given node text and index.
	 *
	 * @param nodeText the text on the node.
	 * @param index the n'th node with the nodeText.
	 * @return the node with the specified text or <code>WidgetNotFoundException</code> if not match exists.
	 * @since 2.0
	 */
    public SiriusSWTBotTreeItem getNode(final String nodeText, final int index) {
        List<SiriusSWTBotTreeItem> nodes = getNodes(nodeText);
		Assert.isTrue(index < nodes.size(),
				MessageFormat.format("The index ({0}) was more than the number of nodes ({1}) in the tree.", index, nodes.size()));
		return nodes.get(index);
	}

	/**
	 * Gets all nodes matching the given node text.
	 *
	 * @param nodeText the text on the node.
	 * @return the nodes with the specified text or <code>WidgetNotFoundException</code> if not match exists.
	 * @since 2.0
	 */
    public List<SiriusSWTBotTreeItem> getNodes(final String nodeText) {
        List<SiriusSWTBotTreeItem> foundItems = syncExec(new ListResult<SiriusSWTBotTreeItem>() {
			@Override
            public List<SiriusSWTBotTreeItem> run() {
				TreeItem[] items = widget.getItems();
                List<SiriusSWTBotTreeItem> results = new ArrayList<SiriusSWTBotTreeItem>();
				for (TreeItem treeItem : items) {
					if (treeItem.getText().equals(nodeText))
                        results.add(new SiriusSWTBotTreeItem(treeItem, new TextDescription("Tree node with text: " + nodeText)));
				}
				return results;
			}
		});
		if (foundItems.isEmpty())
			throw new WidgetNotFoundException("Could not find node with text: " + nodeText); //$NON-NLS-1$
		return foundItems;
	}

	/**
	 * Gets the first node found matching the given node text.
	 *
	 * @param nodeText the text on the node.
	 * @return the first node with the specified text or <code>WidgetNotFoundException</code> if not match exists.
	 * @since 1.2
	 */
    public SiriusSWTBotTreeItem getNode(final String nodeText) {
		return getNode(nodeText, 0);
	}

	/**
	 * Selects the current tree item. Replaces the current selection.
	 *
	 * @return the current node.
	 * @since 1.0
	 */
    public SiriusSWTBotTreeItem select() {
		waitForEnabled();
		setFocus();
		notifySelect(widget, SWT.NONE);
		return this;
	}

	/**
	 * Unselects the current tree item. The tree must have the SWT.MULTI style.
	 *
	 * @return the current node.
	 * @since 2.8
	 */
    public SiriusSWTBotTreeItem unselect() {
		assertMultiSelect();
		if (!isSelected()) {
			return this;
		}
		waitForEnabled();
		setFocus();
		notifyUnselect(widget);
		return this;
	}

	/**
	 * Click on the tree at given coordinates
	 *
	 * @param x the x co-ordinate of the click
	 * @param y the y co-ordinate of the click
	 * @since 1.2
	 */
	@Override
	protected void clickXY(int x, int y) {
		log.debug(MessageFormat.format("Clicking on {0}", this).toString()); //$NON-NLS-1$
		notifyTree(SWT.MouseEnter, createMouseEvent(x, y, 0, SWT.NONE, 0));
		notifyTree(SWT.Activate, super.createEvent());
		setFocus();
		notifyTree(SWT.FocusIn, super.createEvent());
		notifyTree(SWT.MouseDown, createMouseEvent(x, y, 1, SWT.NONE, 1));
		notifyTree(SWT.Selection, createSelectionEvent(SWT.BUTTON1), selectRunnable(widget, false));
		notifyTree(SWT.MouseUp, createMouseEvent(x, y, 1, SWT.BUTTON1, 1));
		notifyTree(SWT.MouseExit, createMouseEvent(x, y, 0, SWT.NONE, 0));
		notifyTree(SWT.Deactivate, super.createEvent());
		notifyTree(SWT.FocusOut, super.createEvent());
		log.debug(MessageFormat.format("Clicked on {0}", this).toString()); //$NON-NLS-1$
	}

	/**
	 * Clicks on this node.
	 *
	 * @return the current node.
	 * @since 1.2
	 */
	@Override
    public SiriusSWTBotTreeItem click() {
		waitForEnabled();
		Point center = getCenter(getBounds());
		clickXY(center.x, center.y);
		return this;
	}

	/**
	 * Clicks on this node at the given column index.
	 *
	 * @return the current node.
	 * @since 2.0
	 */
    public SiriusSWTBotTreeItem click(final int column) {
		waitForEnabled();
		Point center = getCenter(getCellBounds(column));
		clickXY(center.x, center.y);
		return this;
	}

	/**
	 * Double clicks on this node.
	 *
	 * @return the current node.
	 * @since 1.2
	 */
    public SiriusSWTBotTreeItem doubleClick() {
		waitForEnabled();

		log.debug(MessageFormat.format("Double-clicking on {0}", this).toString()); //$NON-NLS-1$
		notifyTree(SWT.MouseEnter, createMouseEvent(0, SWT.NONE, 0));
		notifyTree(SWT.Activate, super.createEvent());
		setFocus();
		notifyTree(SWT.FocusIn, super.createEvent());
		notifyTree(SWT.MouseDown, createMouseEvent(1, SWT.NONE, 1));
		notifyTree(SWT.Selection, createSelectionEvent(SWT.BUTTON1), selectRunnable(widget, false));
		notifyTree(SWT.MouseUp, createMouseEvent(1, SWT.BUTTON1, 1));
		notifyTree(SWT.MouseDown, createMouseEvent(1, SWT.NONE, 2));
		notifyTree(SWT.MouseDoubleClick, createMouseEvent(1, SWT.NONE, 2));
		notifyTree(SWT.DefaultSelection, createSelectionEvent(SWT.BUTTON1));
		notifyTree(SWT.MouseUp, createMouseEvent(1, SWT.BUTTON1, 2));
		notifyTree(SWT.MouseExit, createMouseEvent(0, SWT.NONE, 0));
		notifyTree(SWT.Deactivate, super.createEvent());
		notifyTree(SWT.FocusOut, super.createEvent());
		log.debug(MessageFormat.format("Double-clicked on {0}", this).toString()); //$NON-NLS-1$
		return this;
	}

    /**
     * Double clicks on this node at the given column index.
     * 
     * @param column
     *            The index of the column
     *
     * @return the current node.
     * @since 3.1
     */
    public SiriusSWTBotTreeItem doubleClick(final int column) {
        waitForEnabled();
        Point center = getCenter(getCellBounds(column));

        doubleClickXY(center.x, center.y);
        return this;
    }

    /**
     * Double clicks on the tree at given coordinates.
     *
     * @param x
     *            the x coordinate of the click
     * @param y
     *            the y coordinate of the click
     * 
     * @since 3.1
     */
    @Override
    protected void doubleClickXY(int x, int y) {
        waitForEnabled();

        log.debug(MessageFormat.format("Double-clicking on {0}", this).toString()); //$NON-NLS-1$
        notifyTree(SWT.MouseEnter, createMouseEvent(x, y, 0, SWT.NONE, 0));
        notifyTree(SWT.Activate, super.createEvent());
        setFocus();
        notifyTree(SWT.FocusIn, super.createEvent());
        notifyTree(SWT.MouseDown, createMouseEvent(x, y, 1, SWT.NONE, 1));
        notifyTree(SWT.Selection, createSelectionEvent(SWT.BUTTON1), selectRunnable(widget, false));
        notifyTree(SWT.MouseUp, createMouseEvent(x, y, 1, SWT.BUTTON1, 1));
        notifyTree(SWT.MouseDown, createMouseEvent(x, y, 1, SWT.NONE, 2));
        notifyTree(SWT.MouseDoubleClick, createMouseEvent(x, y, 1, SWT.NONE, 2));
        notifyTree(SWT.DefaultSelection, createSelectionEvent(SWT.BUTTON1));
        notifyTree(SWT.MouseUp, createMouseEvent(x, y, 1, SWT.BUTTON1, 2));
        notifyTree(SWT.MouseExit, createMouseEvent(x, y, 0, SWT.NONE, 0));
        notifyTree(SWT.Deactivate, super.createEvent());
        notifyTree(SWT.FocusOut, super.createEvent());
        log.debug(MessageFormat.format("Double-clicked on {0}", this).toString()); //$NON-NLS-1$
    }

	@Override
	protected Control getDNDControl() {
		return tree;
	}

	@Override
	protected void dragStart() {
		setFocus();
		notifyTree(SWT.Activate, super.createEvent());
		notifyTree(SWT.FocusIn, super.createEvent());
		notifyTree(SWT.MouseDown, createMouseEvent(1, SWT.NONE, 1));
		notifyTree(SWT.Selection, createSelectionEvent(SWT.BUTTON1), selectRunnable(widget, false));
	}

	@Override
	protected Rectangle getBounds() {
		return syncExec(new Result<Rectangle>() {
			@Override
			public Rectangle run() {
				if (widget.isDisposed()) {
					return new Rectangle(0, 0, 0, 0);
				}
				return widget.getBounds();
			}
		});
	}

	/**
	 * Get the cell bounds. widget should be enabled before calling this method.
	 *
	 * @param column the tree column index
	 * @return the cell bounds
	 */
	private Rectangle getCellBounds(final int column) {
		return syncExec(new Result<Rectangle>() {
			@Override
			public Rectangle run() {
				return widget.getBounds(column);
			}
		});
	}

	/**
	 * Get the center of the given rectangle.
	 *
	 * @param bounds the rectangle
	 * @return the center.
	 */
	private Point getCenter(Rectangle bounds) {
		return new Point(bounds.x + (bounds.width / 2), bounds.y + (bounds.height / 2));
	}

	/**
	 * Selects the items matching the array provided. Replaces the current
	 * selection.
	 *
	 * @param items
	 *            the items to select.
	 * @return the current node.
	 * @since 1.0
	 */
    public SiriusSWTBotTreeItem select(final String... items) {
		waitForEnabled();
		setFocus();
		if (items.length > 1) {
			assertMultiSelect();
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
	 * Selects the indices provided. Replaces the current selection.
	 *
	 * @param indices
	 *            the indices to select.
	 * @return the current node.
	 * @since 2.6
	 */
    public SiriusSWTBotTreeItem select(final int... indices) {
		waitForEnabled();
		setFocus();
		if (indices.length > 1) {
			assertMultiSelect();
		}
		for (int i = 0; i < indices.length; i++) {
			assertIsLegalRowIndex(indices[i]);
		}
		final List<TreeItem> selection = new ArrayList<TreeItem>();
		for (int index : indices) {
			selection.add(getItem(index));
		}
		log.debug(MessageFormat.format("Selecting rows {0} in {1}", Arrays.toString(indices), this).toString()); //$NON-NLS-1$ //$NON-NLS-2$
		for (int i = 0; i < selection.size(); i++) {
			int stateMask = (i == 0) ? SWT.NONE : SWT.MOD1;
			notifySelect(selection.get(i), stateMask);
		}
		return this;
	}

	/**
	 * Selects the item matching the given name. Replaces the current selection.
	 *
	 * @param item
	 *            the items to select.
	 * @return the current node.
	 * @since 1.0
	 */
    public SiriusSWTBotTreeItem select(final String item) {
		return select(new String[] { item });
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
					tree.select(item);
				} else {
					// removes earlier selection
					tree.setSelection(item);
				}
			}
		};
	}

	/**
	 * Selects all expanded items that are descendants of the tree item. Replaces
	 * the current selection. Selects the tree item if it is collapsed or has no
	 * children. The tree must have the SWT.MULTI style.
	 *
	 * @since 2.8
	 */
	public void selectAll() {
		assertMultiSelect();
		waitForEnabled();
		setFocus();
		TreeItem[] items = syncExec(new ArrayResult<TreeItem>() {
			@Override
			public TreeItem[] run() {
				if (!widget.getExpanded()) {
					return new TreeItem[] { widget };
				}
				return getExpandedChildren(widget).toArray(new TreeItem[0]);
			}
		});
		if (items.length == 0) {
			return;
		}
		notifySelect(items[0], SWT.NONE);
		if (items.length > 1) {
			notifySelect(items[items.length - 1], SWT.MOD2, selectRunnable(items));
		}
	}

	private List<TreeItem> getExpandedChildren(TreeItem item) {
		List<TreeItem> items = new ArrayList<TreeItem>();
		for (TreeItem child : item.getItems()) {
			items.add(child);
			if (child.getExpanded() && child.getItemCount() > 0) {
				items.addAll(getExpandedChildren(child));
			}
		}
		return items;
	}

	/**
	 * Selects the specified items.
	 *
	 * @param items
	 *            the tree items to select
	 */
	private Runnable selectRunnable(final TreeItem[] items) {
		return new Runnable() {
			@Override
			public void run() {
				tree.setSelection(items);
			}
		};
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
				tree.deselect(item);
			}
		};
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
	 * @param selected  the tree item to select.
	 * @param stateMask the state of the keyboard modifier keys.
	 */
	private void notifySelect(TreeItem selected, int stateMask) {
		notifySelect(selected, stateMask, selectRunnable(selected, (stateMask & SWT.MOD1) != 0));
	}

	private void notifySelect(TreeItem selected, int stateMask, Runnable runnable) {
        SiriusSWTBotTreeItem item = new SiriusSWTBotTreeItem(selected);
		notifyTree(SWT.MouseEnter, item.createMouseEvent(0, SWT.NONE, 0));
		notifyTree(SWT.Activate, super.createEvent());
		notifyTree(SWT.FocusIn, super.createEvent());
		notifyTree(SWT.MouseDown, item.createMouseEvent(1, stateMask, 1));
		notifyTree(SWT.Selection, item.createSelectionEvent(stateMask | SWT.BUTTON1), runnable);
		notifyTree(SWT.MouseUp, item.createMouseEvent(1, stateMask | SWT.BUTTON1, 1));
	}

	@Override
	protected Event createSelectionEvent(int stateMask) {
		Event event = super.createSelectionEvent(stateMask);
		event.item = widget;
		return event;
	}

	@Override
	public String getText() {
		return SWTUtils.getText(widget);
	}

	@Override
	public SWTBotRootMenu contextMenu() {
		waitForEnabled();
		select();
		return contextMenu(tree);
	}

	/**
	 * Toggle the tree item.
	 *
	 * @since 1.3
	 */
	public void toggleCheck() {
		setChecked(!isChecked());
	}

	/**
	 * Check the tree item.
	 *
	 * @since 1.3
	 */
	public void check() {
		setChecked(true);
	}

	/**
	 * Uncheck the tree item.
	 *
	 * @since 1.3
	 */
	public void uncheck() {
		setChecked(false);
	}

	/**
	 * Gets if the checkbox button is checked.
	 *
	 * @return <code>true</code> if the checkbox is checked. Otherwise <code>false</code>.
	 * @since 1.3
	 */
	public boolean isChecked() {
		assertIsCheck();
		return syncExec(new BoolResult() {
			@Override
			public Boolean run() {
				return widget.getChecked();
			}
		});
	}

	/**
	 * Creates an event for CheckboxTreeItem case.
	 *
	 * @return an event that encapsulates {@link #widget} and {@link #display}.
	 */
	private Event createCheckEvent() {
		Event event = createSelectionEvent(SWT.BUTTON1);
		event.detail = SWT.CHECK;
		return event;
	}

	private void setChecked(final boolean checked) {
		waitForEnabled();
		assertIsCheck();
		syncExec(new VoidResult() {
			@Override
			public void run() {
				log.debug(MessageFormat.format("Setting state to {0} on: {1}", (checked ? "checked" : "unchecked"), widget.getText()).toString()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				widget.setChecked(checked);
			}
		});
		notifyCheck();
	}

	private void assertIsCheck() {
		Assert.isLegal(hasStyle(tree, SWT.CHECK), "The tree does not have the style SWT.CHECK"); //$NON-NLS-1$
	}

	/**
	 * notify listeners about checkbox state change.
	 *
	 * @since 1.3
	 */
	private void notifyCheck() {
		syncExec(new VoidResult() {
			@Override
			public void run() {
				tree.notifyListeners(SWT.Selection, createCheckEvent());
			}
		});
	}

	@Override
	protected void waitForEnabled() {
        new SiriusSWTBotTree(tree).waitForEnabled();
	}

	private void assertMultiSelect() {
		Assert.isLegal(hasStyle(tree, SWT.MULTI), "Tree does not support multi selection."); //$NON-NLS-1$
	}

	private void assertIsLegalRowIndex(final int rowIndex) {
		Assert.isLegal(rowIndex < rowCount(), "The row number: " + rowIndex + " does not exist."); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @return <code>true</code> if the item is selected, <code>false</code> otherwise.
	 * @since 2.0
	 */
	public boolean isSelected() {
		return UIThreadRunnable.syncExec(new BoolResult() {
			@Override
			public Boolean run() {
				return Arrays.asList(tree.getSelection()).contains(widget);
			}
		});
	}

	/**
	 * Gets if the item is expanded.
	 *
	 * @return <code>true</code> if the item is expanded, <code>false</code> otherwise.
	 * @since 2.0
	 */
	public boolean isExpanded() {
		waitForEnabled();
		return UIThreadRunnable.syncExec(new BoolResult() {
			@Override
			public Boolean run() {
				return widget.getExpanded();
			}
		});
	}

	/**
	 * Gets all the items in this tree node.
	 *
	 * @return all the items in this tree node.
	 */
    public SiriusSWTBotTreeItem[] getItems() {
        return syncExec(new ArrayResult<SiriusSWTBotTreeItem>() {
			@Override
            public SiriusSWTBotTreeItem[] run() {
				TreeItem[] items = widget.getItems();
                List<SiriusSWTBotTreeItem> children = new ArrayList<SiriusSWTBotTreeItem>();
				for (int i = 0; i < items.length; i++) {
					if (!items[i].isDisposed()) {
                        children.add(new SiriusSWTBotTreeItem(items[i]));
					}
				}
                return children.toArray(new SiriusSWTBotTreeItem[children.size()]);
			}
		});
	}

	@Override
	public Color foregroundColor() {
		return syncExec(new Result<Color>() {
			@Override
			public Color run() {
				return widget.getForeground();
			}
		});
	}

	@Override
	public Color backgroundColor() {
		return syncExec(new Result<Color>() {
			@Override
			public Color run() {
				return widget.getBackground();
			}
		});
	}

	/**
	 * Gets the tree item matching the given name.
	 *
	 * @param nodeText the text on the node.
	 * @return the tree item with the specified text.
	 * @throws WidgetNotFoundException if the node was not found.
	 */
    private SiriusSWTBotTreeItem getTreeItem(final String nodeText) throws WidgetNotFoundException {
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
	 * @param index the index of the item to return
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
	 * @param nodeText the text on the node.
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

	@Override
	public boolean isEnabled() {
		return syncExec(new BoolResult() {
			@Override
			public Boolean run() {
				return tree.isEnabled();
			}
		});
	}

	public boolean isGrayed() {
		assertIsCheck();
		return syncExec(new BoolResult() {
			@Override
			public Boolean run() {
				return widget.getGrayed();
			}
		});
	}

	@Override
	protected Rectangle absoluteLocation() {
		return UIThreadRunnable.syncExec(new Result<Rectangle>() {
			@Override
			public Rectangle run() {
				return display.map(widget.getParent(), null, widget.getBounds());
			}
		});
	}

	@Override
	public void setFocus() {
        new SiriusSWTBotTree(tree).setFocus();
	}

    // CHECKSTYLE:ON
}
