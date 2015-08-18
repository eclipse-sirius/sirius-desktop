/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.profiler.view;

import java.text.Collator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerEvent;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerListener;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.profiler.InitProfilerAction;

/**
 * This view displays informations in a tree of the
 * {@link org.eclipse.sirius.common.tools.api.profiler.TimeProfiler}.
 * 
 * @author mchauvin
 */
public class TimeProfilerTreeView extends ViewPart implements ProfilerListener {

    /** The tree viewer. */
    private TreeViewer viewer;

    /** The init action. */
    private InitProfilerAction initProfilerAction;

    /** The refresh action. */
    private IAction refreshAction;

    /** The print action. */
    private IAction printAction;

    private ViewerSorter categorySorter = new ViewerSorter() {
        @Override
        public int compare(final Viewer v, final Object e1, final Object e2) {
            if (e1 instanceof TimeProfilerViewItem && e2 instanceof TimeProfilerViewItem) {
                final String category1 = ((TimeProfilerViewItem) e1).getTask().getCategory();
                final String category2 = ((TimeProfilerViewItem) e2).getTask().getCategory();
                return Collator.getInstance().compare(category1, category2);
            }
            return super.compare(v, e1, e2);
        }
    };

    private ViewerSorter nameSorter = new ViewerSorter() {
        @Override
        public int compare(final Viewer v, final Object e1, final Object e2) {
            if (e1 instanceof TimeProfilerViewItem && e2 instanceof TimeProfilerViewItem) {
                final String task1 = ((TimeProfilerViewItem) e1).getTask().getName();
                final String task2 = ((TimeProfilerViewItem) e2).getTask().getName();
                return Collator.getInstance().compare(task1, task2);
            }
            return super.compare(v, e1, e2);
        }
    };

    private ViewerSorter timeSorter = new ViewerSorter() {
        @Override
        public int compare(final Viewer v, final Object e1, final Object e2) {
            int comparison;
            if (e1 instanceof TimeProfilerViewItem && e2 instanceof TimeProfilerViewItem) {
                final long time1 = ((TimeProfilerViewItem) e1).getTime().longValue();
                final long time2 = ((TimeProfilerViewItem) e2).getTime().longValue();
                final long tmp = time1 - time2;
                if (tmp > Integer.MAX_VALUE) {
                    comparison = 1;
                } else if (tmp < Integer.MIN_VALUE) {
                    comparison = -1;
                } else {
                    comparison = (int) tmp;
                }
            } else {
                comparison = super.compare(v, e1, e2);
            }
            return comparison;
        }
    };

    private ViewerSorter occurencesSorter = new ViewerSorter() {
        @Override
        public int compare(final Viewer v, final Object e1, final Object e2) {
            int comparison;
            if (e1 instanceof TimeProfilerViewItem && e2 instanceof TimeProfilerViewItem) {
                final int occurences1 = ((TimeProfilerViewItem) e1).getOccurences().intValue();
                final long occurences2 = ((TimeProfilerViewItem) e2).getOccurences().intValue();
                final long tmp = occurences1 - occurences2;
                if (tmp > Integer.MAX_VALUE) {
                    comparison = 1;
                } else if (tmp < Integer.MIN_VALUE) {
                    comparison = -1;
                } else {
                    comparison = (int) tmp;
                }
            } else {
                comparison = super.compare(v, e1, e2);
            }
            return comparison;
        }
    };

    private InverseViewerSorter inverseSorter = new InverseViewerSorter();

    /**
     * Create a new {@link TimeProfilerTreeView}.
     */
    public TimeProfilerTreeView() {
        DslCommonPlugin.PROFILER.addProfilerListener(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createPartControl(final Composite parent) {
        viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
        viewer.getTree().setLinesVisible(true);
        viewer.getTree().setHeaderVisible(true);
        final String[] columnNames = { "Task Category", "Task Name", "Time", "Occurences" };
        final int[] columnWidths = { 100, 100, 100, 100 };
        final int[] columnAlignments = { SWT.LEFT, SWT.LEFT, SWT.RIGHT, SWT.RIGHT };
        for (int i = 0; i < columnNames.length; i++) {
            final TreeViewerColumn treeColumn = new TreeViewerColumn(viewer, columnAlignments[i]);
            treeColumn.getColumn().setText(columnNames[i]);
            treeColumn.getColumn().setWidth(columnWidths[i]);
            final int index = i;
            treeColumn.getColumn().addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    setSorter(index);
                }
            });
        }

        viewer.setLabelProvider(new TimeProfilerTreeViewLabelProvider());
        viewer.setContentProvider(new TimeProfilerTreeViewContentProvider());

        // create actions.
        this.createActions();
        this.createContextMenu();
        this.createToolbarButtons();

        // sets the input.
        viewer.setInput(TreeItemWrapper.ROOT_ITEM);
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    @Override
    public void taskStarted(final ProfilerEvent event) {
        // do nothing.
    }

    @Override
    public void taskStopped(final ProfilerEvent event) {
        // do nothing.
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        initProfilerAction = new InitProfilerAction(viewer);
        initProfilerAction.setText("Reinit profiler");
        refreshAction = new Action() {
            @Override
            public void run() {
                viewer.refresh();
            }
        };
        refreshAction.setText("Refresh View");
        printAction = new Action() {
            @Override
            public void run() {
                // CHECKSTYLE:OFF
                System.out.println(DslCommonPlugin.PROFILER.getStatus());
                // CHECKSTYLE:ON
            }
        };
        printAction.setText("Print to console");
    }

    /**
     * Create the context menu.
     */
    private void createContextMenu() {
        final MenuManager menuManager = new MenuManager("#PopupMenu"); //$NON-NLS-1$
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(final IMenuManager manager) {
                TimeProfilerTreeView.this.fillContextMenu(manager);
            }
        });
    }

    /**
     * Fill the context menu.
     */
    private void fillContextMenu(final IMenuManager menuManager) {
        menuManager.add(this.initProfilerAction);
        menuManager.add(this.refreshAction);
        menuManager.add(this.printAction);
        menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    /**
     * Create toolbar buttons.
     */
    private void createToolbarButtons() {
        getViewSite().getActionBars().getToolBarManager().add(this.initProfilerAction);
        getViewSite().getActionBars().getToolBarManager().add(this.refreshAction);
        getViewSite().getActionBars().getToolBarManager().add(this.printAction);
    }

    @Override
    public void profilerReinited(final ProfilerEvent event) {
        this.viewer.refresh();
    }

    private void setSorter(final int column) {
        switch (column) {
        case 0:
            if (viewer.getSorter() != categorySorter) {
                viewer.setSorter(categorySorter);
            } else {
                this.inverseSorter.setSorter(categorySorter);
                viewer.setSorter(inverseSorter);
            }
            break;
        case 1:
            if (viewer.getSorter() != nameSorter) {
                viewer.setSorter(nameSorter);
            } else {
                this.inverseSorter.setSorter(nameSorter);
                viewer.setSorter(inverseSorter);
            }
            break;
        case 2:
            if (viewer.getSorter() != timeSorter) {
                viewer.setSorter(timeSorter);
            } else {
                this.inverseSorter.setSorter(timeSorter);
                viewer.setSorter(inverseSorter);
            }
            break;
        case 3:
            if (viewer.getSorter() != occurencesSorter) {
                viewer.setSorter(occurencesSorter);
            } else {
                this.inverseSorter.setSorter(occurencesSorter);
                viewer.setSorter(inverseSorter);
            }
            break;
        default:
            break;
        }
    }

    /**
     * The inverse sorter.
     * 
     * @author ymortier
     */
    private static class InverseViewerSorter extends ViewerSorter {

        /** The sorter to inverse. */
        private ViewerSorter sorter;

        /**
         * Set the sorter to inverse.
         * 
         * @param sorter
         *            the sorter to inverse.
         */
        public void setSorter(final ViewerSorter sorter) {
            this.sorter = sorter;
        }

        /**
         * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final Viewer v, final Object e1, final Object e2) {
            return this.sorter == null ? super.compare(v, e1, e2) : -this.sorter.compare(v, e1, e2);
        }
    }
}
