/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.model.business.internal.spec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.tree.description.impl.TreeItemDeletionToolImpl;

/**
 * Implementation od TreeItemDeletionTool.
 * 
 * @author nlepine
 * 
 */
public class TreeItemDeletionToolSpec extends TreeItemDeletionToolImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.impl.TreeItemToolImpl#getVariables()
     */
    @Override
    public EList<TreeVariable> getVariables() {
        if (variables == null) {
            variables = new TreeVariableContainmentEList(this, DescriptionPackage.TREE_ITEM_DELETION_TOOL__VARIABLES);
        }
        return variables;
    }

}
