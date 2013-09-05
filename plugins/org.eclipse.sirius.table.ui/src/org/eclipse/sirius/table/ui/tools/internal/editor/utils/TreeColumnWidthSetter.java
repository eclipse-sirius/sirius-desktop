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

import org.eclipse.swt.widgets.TreeColumn;

/**
 * Change {@link TreeColumn}'s width, i.e. {@link TreeColumn#getWidth()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeColumnWidthSetter implements Runnable {

    private TreeColumn treeColumn;

    private int width;

    /**
     * Construct a {@link TreeColumnWidthSetter} to change a {@link TreeColumn}
     * 's width.
     * 
     * @param treeColumn
     *            the {@link TreeColumn} on which to change width
     * @param width
     *            the new width to set
     */
    public TreeColumnWidthSetter(TreeColumn treeColumn, int width) {
        this.treeColumn = treeColumn;
        this.width = width;
    }

    /**
     * Overridden to change {@link TreeColumn#getWidth()}.
     * 
     * {@inheritDoc}
     */
    public void run() {
        treeColumn.setWidth(width);
    }

}
