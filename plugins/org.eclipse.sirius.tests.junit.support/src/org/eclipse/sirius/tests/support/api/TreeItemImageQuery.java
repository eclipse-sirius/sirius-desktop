/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Query that get the image of a {@link TreeItem} , i.e.
 * {@link TreeItem#getImage()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemImageQuery extends RunnableWithResult.Impl<Image> {

    private final TreeItem treeItem;

    /**
     * Construct a {@link TreeItemImageQuery} to get the {@link TreeItem}'s
     * image.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to check expansion
     */
    public TreeItemImageQuery(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * Overridden to test {@link TreeItem#getImage()}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void run() {
        Image image = treeItem.getImage();
        setResult(image);
    }
}
