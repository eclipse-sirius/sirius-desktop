/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
