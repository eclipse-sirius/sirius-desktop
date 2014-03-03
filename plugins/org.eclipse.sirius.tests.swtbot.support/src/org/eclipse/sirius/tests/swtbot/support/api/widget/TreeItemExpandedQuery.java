/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Query that tells if a {@link TreeItem} is expanded, i.e.
 * {@link TreeItem#getExpanded()}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemExpandedQuery extends RunnableWithResult.Impl<Boolean> {

    private final TreeItem treeItem;

    /**
     * Construct a {@link TreeItemExpandedQuery} to see if a {@link TreeItem} is
     * expanded.
     * 
     * @param treeItem
     *            the {@link TreeItem} on which to check expansion
     */
    public TreeItemExpandedQuery(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

    /**
     * Overridden to test {@link TreeItem#getExpanded()}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void run() {
        boolean expanded = treeItem.getExpanded();
        setResult(expanded);
    }

}
