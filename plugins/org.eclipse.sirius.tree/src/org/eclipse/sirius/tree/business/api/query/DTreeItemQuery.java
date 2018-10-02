/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.business.api.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;

/**
 * Query for {@link DTreeItem}.
 * 
 * @author cbrun
 */
public class DTreeItemQuery {

    private DTreeItem item;

    /**
     * Creates a new DTreeItemQuery.
     * 
     * @param item
     *            the DTreeItem on which create this query
     */
    public DTreeItemQuery(DTreeItem item) {
        this.item = item;
    }

    /**
     * Returns the parent Tree of the treeItem.
     * 
     * @return the Tree containing the treeItem
     */
    public Option<DTree> getParentTree() {
        EObject current = item;
        while (current != null) {
            if (current instanceof DTree) {
                return Options.newSome((DTree) current);
            }
            current = current.eContainer();
        }
        return Options.newNone();
    }

}
