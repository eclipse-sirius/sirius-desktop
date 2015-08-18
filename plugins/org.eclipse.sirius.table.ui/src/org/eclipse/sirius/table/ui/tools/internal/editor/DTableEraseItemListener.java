/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;

/**
 * Manage the specific background for selected cell. <BR>
 * There is a problem under Windows OS, the handleEvent method of the
 * SWT.MeasuerItem Listener is called twice : one with the old column and
 * another with the new column. The result is that there are two selected cells.
 * So we make some tests to correct this problem. We use lastSelectedLineDrawned
 * to remember the last line which was drawn with selected color.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableEraseItemListener implements Listener {
    private static final boolean IS_WINDOWS_OS = "win32".equals(SWT.getPlatform()); //$NON-NLS-1$

    private static final Color COLOR_LIST_SELECTED = PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);

    private static final Color COLOR_LIST_OTHER_CELLS_SELECTED = PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);

    /**
     * The listen treeViewer
     */
    private TreeViewer treeViewer;

    /**
     * The viewer manager associated to the treeViewer.
     */
    private DTableViewerManager tableViewerManager;

    /**
     * Use to remember the last line which was drawn with selected color.
     */
    private Widget lastSelectedLineDrawned;

    /**
     * Use to to remember the last cell (so column) which was drawn with
     * selected color.
     */
    private int lastSelectedColumnDrawned = -1;

    /**
     * Constructor.
     * 
     * @param tableViewerManager
     *            The viewer manager associated to the treeViewer.
     * @param treeViewer
     *            The listen treeViewer
     */
    public DTableEraseItemListener(final DTableViewerManager tableViewerManager, final TreeViewer treeViewer) {
        this.tableViewerManager = tableViewerManager;
        this.treeViewer = treeViewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(final Event event) {
        // CHECKSTYLE:OFF
        event.detail &= ~SWT.HOT;
        // CHECKSTYLE:ON
        if ((event.detail & SWT.SELECTED) == 0) {
            return; /* item not selected */
        }
        final int clientWidth = treeViewer.getTree().getClientArea().width;
        final GC gc = event.gc;
        final Color oldBackground = gc.getBackground();
        if (DTableEraseItemListener.IS_WINDOWS_OS) {
            if (event.index == tableViewerManager.getActiveColumn()) {
                // Make some tests to correct the the Windows problem.
                if (lastSelectedLineDrawned != null && lastSelectedColumnDrawned != -1 && !lastSelectedLineDrawned.equals(event.item)
                        && lastSelectedColumnDrawned == tableViewerManager.getActiveColumn()) {
                    gc.setBackground(DTableEraseItemListener.COLOR_LIST_OTHER_CELLS_SELECTED);
                } else {
                    // Draw the background of the selected cell to another color
                    gc.setBackground(DTableEraseItemListener.COLOR_LIST_SELECTED);
                }
                lastSelectedLineDrawned = event.item;
                lastSelectedColumnDrawned = tableViewerManager.getActiveColumn();
            } else {
                // Draw the background to selected for the other cells of the
                // line of the selected cell
                gc.setBackground(DTableEraseItemListener.COLOR_LIST_OTHER_CELLS_SELECTED);
            }
            gc.fillRectangle(event.x, event.y, event.width, event.height);
        } else {
            if (tableViewerManager.getActiveColumn() == 0) {
                // Draw the background to selected for all the cells of the line
                gc.setBackground(DTableEraseItemListener.COLOR_LIST_SELECTED);
                gc.fillRectangle(0, event.y, clientWidth, event.height);
            } else if (event.index == tableViewerManager.getActiveColumn()) {
                // Draw the background of the selected cell to another color
                gc.setBackground(DTableEraseItemListener.COLOR_LIST_SELECTED);
                gc.fillRectangle(event.x, event.y, event.width, event.height);
            } else {
                // Draw the background to selected for the other cells of the
                // line of the selected cell
                gc.setBackground(DTableEraseItemListener.COLOR_LIST_OTHER_CELLS_SELECTED);
                gc.fillRectangle(event.x, event.y, event.width, event.height);
            }
        }
        // Reset to previous background
        gc.setBackground(oldBackground);
        // CHECKSTYLE:OFF
        event.detail &= ~SWT.SELECTED;
        // CHECKSTYLE:ON
    }

}
