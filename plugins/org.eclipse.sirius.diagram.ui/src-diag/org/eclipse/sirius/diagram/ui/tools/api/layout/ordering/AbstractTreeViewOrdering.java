/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * An abstract Tree like view ordering.
 * 
 * @author ymortier
 */
public abstract class AbstractTreeViewOrdering implements ViewOrdering {

    /** The views to sort. */
    private List<View> views;

    /** The resulted grid view. */
    private GridView gridView;

    /** Indicates that this instance is aware of user interaction. */
    private boolean userAwareCapable;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering#getSortedViews()
     */
    public List<View> getSortedViews() {
        return views;
    }

    /**
     * <code>true</code> if this instance is aware of user interaction.
     * 
     * @param userAwareCapable
     *            <code>true</code> if this instance is aware of user
     *            interaction.
     */
    public void setUserAwareCapable(final boolean userAwareCapable) {
        this.userAwareCapable = userAwareCapable;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#getSortedViewsAsGrid()
     */
    public GridView getSortedViewsAsGrid() {
        if (this.gridView == null) {
            buildTree();
        }
        return this.gridView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#isAbleToManageView(org.eclipse.gmf.runtime.notation.View)
     */
    public boolean isAbleToManageView(final View view) {
        return view instanceof Node;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#setViews(java.util.Collection)
     */
    public <T extends View> void setViews(final Collection<T> views) {
        this.views = Collections.<View> unmodifiableList(new ArrayList<T>(views));
        this.gridView = null;
    }

    /**
     * Return the views that are the roots of trees to display.
     * 
     * @param views
     *            the views.
     * @return the views that are the roots of trees to display.
     */
    public abstract List<View> getRoots(List<View> views);

    /**
     * Returns all children of the parent view.
     * 
     * @param parent
     *            the parent view.
     * @param views
     *            the candidates.
     * @return all children of the parent view.
     */
    public abstract List<View> getChildren(View parent, List<View> views);

    /**
     * Build the tree.
     */
    private void buildTree() {

        List<View> roots = this.getRoots(this.views);
        final UserAwareCapableOrdering userAwareCapableOrdering = new UserAwareCapableOrdering();
        userAwareCapableOrdering.setViews(roots);
        roots = new ArrayList<View>(userAwareCapableOrdering.getSortedViews());

        final AirTree dummyRoot = new AirTree(null);

        final Iterator<View> iterRoots = roots.iterator();
        while (iterRoots.hasNext()) {
            final View view = iterRoots.next();
            final AirTree airTree = new AirTree(view);
            airTree.setParent(dummyRoot);
        }

        final List<AirTree> currentViews = new ArrayList<AirTree>(dummyRoot.getChildren());
        while (!currentViews.isEmpty()) {
            final AirTree airTree = currentViews.get(0);
            List<View> childrenView = this.getChildren(airTree.getView(), this.views);
            if (userAwareCapable) {
                final UserAwareCapableOrdering ordering = new UserAwareCapableOrdering();
                ordering.setViews(childrenView);
                childrenView = ordering.getSortedViews();
            }
            final Iterator<View> iterChildrenView = childrenView.iterator();
            while (iterChildrenView.hasNext()) {
                final View currentChild = iterChildrenView.next();
                final AirTree childAirTree = new AirTree(currentChild);
                childAirTree.setParent(airTree);
                currentViews.add(childAirTree);
            }
            currentViews.remove(airTree);
        }
        final ExtendedGrid extendedGrid = getExtendedGrid(dummyRoot);
        final View[][] gridViews = (View[][]) extendedGrid.toArray(new View[extendedGrid.getNbColumns()][extendedGrid.getNbLines()]);
        this.gridView = GridView.create(gridViews);
        clear();
    }

    /**
     * Clears all the informations calculated for getting the views.
     */
    // Defined to avoid Memory leaks when referencing disposed Views
    protected void clear() {

    }

    /**
     * @param dummyRoot
     */
    private ExtendedGrid getExtendedGrid(final AirTree dummyRoot) {
        final ExtendedGrid extendedGrid = new ExtendedGrid();

        List<AirTree> currentNodes = dummyRoot.getChildren();
        //
        // index of the current line.
        int lineIndex = 0;
        while (!currentNodes.isEmpty()) {
            //
            // the next line.
            final List<AirTree> next = new LinkedList<AirTree>();
            //
            // appends a line to the grid.
            extendedGrid.appendLine();
            // iterate over the line.
            for (final AirTree node : currentNodes) {

                // the parent.
                final AirTree parent = node.getParent();
                //
                // index of the child into the parent's children list.
                final int childIndex = parent.getChildren().indexOf(node);

                final int childrenNumber = parent.getChildren().size();
                if (childIndex == 0) {
                    //
                    // compute the number of columns to add.
                    int nbColumnsToAdd = childrenNumber - (childrenNumber % 2);
                    if (lineIndex > 0) {
                        int parentColumnIndex = getParentColumnIndex(extendedGrid, parent, lineIndex);
                        extendedGrid.insertColumn(parentColumnIndex);
                        extendedGrid.insertColumn(parentColumnIndex);

                        for (int i = 0; i < nbColumnsToAdd; i++) {
                            parentColumnIndex = getParentColumnIndex(extendedGrid, parent, lineIndex);
                            if (i % 2 == 0) {
                                extendedGrid.insertColumn(parentColumnIndex);
                            } else {
                                extendedGrid.insertColumn(parentColumnIndex + 1);
                            }
                        }
                        extendedGrid.insertColumn(parentColumnIndex + 1);
                        extendedGrid.insertColumn(parentColumnIndex + 1);

                        parentColumnIndex = getParentColumnIndex(extendedGrid, parent, lineIndex);
                        extendedGrid.setData(node.getView(), getColumnIndex(parentColumnIndex, childrenNumber, childIndex), lineIndex);
                    } else {
                        nbColumnsToAdd = childrenNumber;
                        for (int i = 0; i < nbColumnsToAdd; i++) {
                            extendedGrid.appendColumn();
                        }
                        extendedGrid.setData(node.getView(), 0, lineIndex);
                    }
                } else {
                    int columnIndex;
                    if (lineIndex == 0) {
                        columnIndex = childIndex;
                    } else {
                        final int parentIndex = getParentColumnIndex(extendedGrid, parent, lineIndex);
                        columnIndex = getColumnIndex(parentIndex, childrenNumber, childIndex);
                        if (childrenNumber % 2 == 0 && (childrenNumber / 2) <= childIndex) {
                            columnIndex += 1;
                        }
                    }
                    extendedGrid.setData(node.getView(), columnIndex, lineIndex);
                }

                //
                // append children for the next line.
                next.addAll(node.getChildren());
            }
            currentNodes = next;
            lineIndex++;
        }
        // reduceGrid(extendedGrid, dummyRoot);
        return extendedGrid;
    }

    private int getColumnIndex(final int parentColumnIndex, final int childrenNumber, final int childIndex) {
        return parentColumnIndex - (int) Math.floor((double) childrenNumber / 2) + childIndex;
    }

    private int getParentColumnIndex(final ExtendedGrid extendedGrid, final AirTree parent, final int lineIndex) {
        return extendedGrid.indexOf(parent.getView(), lineIndex - 1);
    }

    /**
     * The tree.
     * 
     * @author ymortier
     */
    private static class AirTree {

        /** The children of this view. */
        private List<AirTree> children = new LinkedList<AirTree>();

        /** The current View. */
        private View view;

        /** The parent. */
        private AirTree parent;

        /**
         * Create a new {@link AirTree}.
         * 
         * @param view
         *            the view of the node.
         */
        AirTree(final View view) {
            this.view = view;
        }

        /**
         * Return the view.
         * 
         * @return the view.
         */
        public View getView() {
            return view;
        }

        /**
         * Return the children.
         * 
         * @return the children.
         */
        public List<AirTree> getChildren() {
            return children;
        }

        /**
         * Return the parent of this element.
         * 
         * @return the parent of this element.
         */
        public AirTree getParent() {
            return parent;
        }

        /**
         * Set the parent of this element.
         * 
         * @param parent
         *            the parent of this element.
         */
        public void setParent(final AirTree parent) {
            this.parent = parent;
            parent.children.add(this);
        }
    }

    private static final class UserAwareCapableOrdering extends AbstractNodeViewOrdering {

        /** The orientation. */
        private int orientation = PositionConstants.HORIZONTAL;

        /**
         * 
         * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractNodeViewOrdering#compare(org.eclipse.gmf.runtime.notation.Node,
         *      org.eclipse.gmf.runtime.notation.Node)
         */
        @Override
        public int compare(final Node node1, final Node node2) {
            final Location loc1 = (Location) node1.getLayoutConstraint();
            final Location loc2 = (Location) node2.getLayoutConstraint();

            if (this.orientation == PositionConstants.HORIZONTAL) {
                return loc1.getX() - loc2.getX();
            } else {
                return loc1.getY() - loc2.getY();
            }
        }

        /**
         * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractNodeViewOrdering#isAbleToManageNode(org.eclipse.gmf.runtime.notation.Node)
         */
        @Override
        public boolean isAbleToManageNode(final Node node) {
            return node.getLayoutConstraint() instanceof Location;
        }

    }

    /**
     * A super grid.
     * 
     * @author ymortier
     */
    private static class ExtendedGrid {

        /** the grid. */
        private List<List<Object>> grid;

        /** the number of columns. */
        private int nbColumns;

        /** The number of lines. */
        private int nbLines;

        /**
         * Creates an empty grid.
         */
        ExtendedGrid() {
            this.grid = new ArrayList<>();
            this.nbColumns = 0;
            this.nbLines = 0;
        }

        /**
         * Append a column.
         */
        public void appendColumn() {
            final ArrayList<Object> column = new ArrayList<Object>(nbLines);
            for (int i = 0; i < nbLines; i++) {
                column.add(null);
            }
            grid.add(column);
            nbColumns++;
        }

        /**
         * Insert the column before the column at the specified index.
         * 
         * @param index
         *            an index.
         */
        public void insertColumn(final int index) {
            if (index >= this.nbColumns) {
                this.appendColumn();
            } else {
                final ArrayList<Object> column = new ArrayList<Object>(nbLines);
                for (int i = 0; i < nbLines; i++) {
                    column.add(null);
                }
                grid.add(index, column);
                nbColumns++;
            }

        }

        /**
         * Appends a line.
         */
        public void appendLine() {
            final Iterator<List<Object>> iterColumns = this.grid.iterator();
            while (iterColumns.hasNext()) {
                final List<Object> column = iterColumns.next();
                column.add(null);
            }
            nbLines++;
        }

        /**
         * Set the data at the specified index.
         * 
         * @param data
         *            the data
         * @param columnIndex
         *            the column index.
         * @param lineIndex
         *            the line index.
         */
        public void setData(final Object data, final int columnIndex, final int lineIndex) {
            if (columnIndex >= nbColumns || columnIndex < 0) {
                throw new IllegalArgumentException();
            }
            if (lineIndex >= nbLines || lineIndex < 0) {
                throw new IllegalArgumentException();
            }
            final List<Object> column = grid.get(columnIndex);
            column.set(lineIndex, data);
        }

        /**
         * Return the number of columns.
         * 
         * @return the number of columns.
         */
        public int getNbColumns() {
            return nbColumns;
        }

        /**
         * Return the number of lines.
         * 
         * @return the number of lines.
         */
        public int getNbLines() {
            return nbLines;
        }

        /**
         * Return the column index of the specified data for the specified line.
         * 
         * @param data
         *            the data.
         * @param lineIndex
         *            the line.
         * @return the column index of the specified data for the specified line
         *         or -1 if the data is not in the line.
         */
        public int indexOf(final Object data, final int lineIndex) {
            int result = -1;
            for (int i = 0; i < this.grid.size() && result < 0; i++) {
                final List<Object> currentColumn = this.grid.get(i);
                if (currentColumn.get(lineIndex) != null && currentColumn.get(lineIndex).equals(data)) {
                    result = i;
                }
            }
            return result;
        }

        /**
         * TODO
         * 
         * @param array
         * @return
         */
        public Object[][] toArray(final Object[][] array) {
            for (int i = 0; i < nbColumns; i++) {
                final List<Object> column = grid.get(i);
                for (int j = 0; j < nbLines; j++) {
                    array[i][j] = column.get(j);
                }
            }
            return array;
        }
    }

}
