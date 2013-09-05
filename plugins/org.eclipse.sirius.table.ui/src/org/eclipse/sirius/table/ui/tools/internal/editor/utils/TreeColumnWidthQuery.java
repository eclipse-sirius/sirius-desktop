/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.utils;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * Query to get a {@link TreeColumn}'s width, i.e. {@link TreeColumn#getWidth()}
 * .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeColumnWidthQuery extends RunnableWithResult.Impl<Integer> {

    private TreeColumn treeColumn;

    /**
     * Construct a {@link TreeColumnWidthQuery} to get a {@link TreeColumn}'s
     * width.
     * 
     * @param treeColumn
     *            the {@link TreeColumn} on which to get width
     */
    public TreeColumnWidthQuery(TreeColumn treeColumn) {
        this.treeColumn = treeColumn;
    }

    /**
     * Overridden to get {@link TreeColumn#getWidth()}.
     * 
     * {@inheritDoc}
     */
    public void run() {
        int width = treeColumn.getWidth();
        setResult(width);
    }

}
