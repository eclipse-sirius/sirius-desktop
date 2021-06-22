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
package org.eclipse.sirius.tree.business.internal.metamodel.spec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.tree.description.impl.TreeItemCreationToolImpl;

/**
 * Specific implementation for model instances.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class TreeItemCreationToolSpec extends TreeItemCreationToolImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateToolImpl#getVariables()
     */
    @Override
    public EList<TreeVariable> getVariables() {
        if (variables == null) {
            variables = new TreeVariableContainmentEList(this, DescriptionPackage.TREE_ITEM_CREATION_TOOL__VARIABLES);
        }
        return variables;
    }
}
