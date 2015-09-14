/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Represents a grid of views.
 * 
 * @author ymortier
 */
public final class GridView {

    /** All columns. */
    private List<Column> columns;

    /**
     * Call the method {@link #create(View[][])} to create an instance.
     */
    private GridView() {
        // empty.
    }

    /**
     * Create a new instance of {@link GridView}.
     * 
     * @param views
     *            the grid.
     * @return the new instance.
     */
    public static GridView create(final View[][] views) {
        final GridView gridView = new GridView();
        int maxLength = -1;
        for (View[] view : views) {
            if (view.length > maxLength) {
                maxLength = view.length;
            }
        }
        //
        // copy the array.
        final View[][] copyView = new View[views.length][];
        System.arraycopy(views, 0, copyView, 0, views.length);
        //
        // equilibrate the array.
        for (int i = 0; i < views.length; i++) {
            copyView[i] = new View[maxLength];
            System.arraycopy(views[i], 0, copyView[i], 0, views[i].length);
        }
        //
        // Create columns
        gridView.columns = new ArrayList<Column>(copyView.length);
        for (int i = 0; i < views.length; i++) {
            final Column column = gridView.new Column(views[i], i);
            gridView.columns.add(column);
        }
        gridView.columns = Collections.unmodifiableList(gridView.columns);
        return gridView;
    }

    /**
     * Return the number of columns of the grid.
     * 
     * @return the number of columns of the grid.
     */
    public int getColumnsCount() {
        return this.columns.size();
    }

    /**
     * Return the number of lines.
     * 
     * @return the number of lines.
     */
    public int getLinesCount() {
        return getColumnsCount() > 0 ? this.getColumnAt(0).getViewsCount() : 0;
    }

    /**
     * Return the {@link Column} that is at the specified index.
     * 
     * @param index
     *            the index of the column.
     * @return the {@link Column} that is at the specified index.
     * @throws IndexOutOfBoundsException
     *             if the index is out ouf bounds.
     */
    public Column getColumnAt(final int index) throws IndexOutOfBoundsException {
        return this.columns.get(index);
    }

    /**
     * Return the view at the specified index.
     * 
     * @param columnIndex
     *            the index of the column.
     * @param lineIndex
     *            the index of the line.
     * @return the view at the specified index.
     * @throws IndexOutOfBoundsException
     *             if the <code>columnIndex</code> or <code>lineIndex</code> is
     *             out of bounds of the grid.
     */
    public View getViewAt(final int columnIndex, final int lineIndex) throws IndexOutOfBoundsException {
        return this.getColumnAt(columnIndex).getViewAt(lineIndex);
    }

    /**
     * Return an {@link Iterator} that iterates all columns of the grid. Each
     * elements is a {@link Column} that represents the column.
     * 
     * @return an {@link Iterator} that iterates all columns of the grid. Each
     *         elements is a {@link Column} that represents the column.
     */
    public Iterator<Column> iteratorColumns() {
        return this.columns.iterator();
    }

    /**
     * Represents a column of the grid.
     * 
     * @author ymortier
     */
    public class Column {

        /** The views of the column. */
        private List<View> views;

        /** The index of the column. */
        private int index;

        /**
         * Create a new {@link Column}.
         * 
         * @param views
         *            the views of the column.
         * @throws IllegalArgumentException
         *             if <code>index</code> is lesser than <code>0</code>.
         */
        Column(final View[] views, final int index) throws IllegalArgumentException {
            this.views = new ArrayList<View>(Arrays.asList(views));
            this.views = Collections.unmodifiableList(this.views);
            this.index = index;
        }

        /**
         * Return the left sibling of the specified view.
         * 
         * @param view
         *            a view of this column.
         * @return the left sibling of the specified view or <code>null</code>
         *         if the specified view has no left sibling.
         * @throws IllegalArgumentException
         *             if the view is not in the column.
         */
        public View getLeftSibling(final View view) throws IllegalArgumentException {
            if (!this.views.contains(view)) {
                throw new IllegalArgumentException(Messages.Column_wrongColumnViewError);
            }
            if (this.index == 0) {
                return null;
            }
            return GridView.this.getViewAt(this.index - 1, this.views.indexOf(view));
        }

        /**
         * Return the right sibling of the specified view.
         * 
         * @param view
         *            a view of this column.
         * @return the left sibling of the specified view or <code>null</code>
         *         if the specified view has no right sibling.
         * @throws IllegalArgumentException
         *             if the view is not in the column.
         */
        public View getRightSibling(final View view) throws IllegalArgumentException {
            if (!this.views.contains(view)) {
                throw new IllegalArgumentException(Messages.Column_wrongColumnViewError);
            }
            if (this.index + 1 == GridView.this.getColumnsCount()) {
                return null;
            }
            return GridView.this.getViewAt(this.index + 1, this.views.indexOf(view));
        }

        /**
         * Return the view that is at the specified index.
         * 
         * @param viewIndex
         *            the index of the view.
         * @return the view that is at the specified index.
         * @throws IndexOutOfBoundsException
         *             if <code>index</code> is out of bounds.
         */
        public View getViewAt(final int viewIndex) throws IndexOutOfBoundsException {
            return this.views.get(viewIndex);
        }

        /**
         * Return the index of the specified view or -1 if the view is not in
         * this column.
         * 
         * @param view
         *            the view.
         * @return the index of the specified view or -1 if the view is not in
         *         this column.
         */
        public int indexOf(final View view) {
            return this.views.indexOf(view);
        }

        /**
         * Return the number of views that are in the column.
         * 
         * @return the number of views that are in the column.
         */
        public int getViewsCount() {
            return this.views.size();
        }

        /**
         * Return the left sibling column.
         * 
         * @return the left sibling column.
         */
        public Column getLeftSibling() {
            if (this.index != 0) {
                return GridView.this.columns.get(index - 1);
            }
            return null;
        }

        /**
         * Returns the index of the column.
         * 
         * @return the index of the column.
         */
        public int getIndex() {
            return index;
        }
    }
}
