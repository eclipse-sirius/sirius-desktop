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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl;

/**
 * Implementation od TreeItemMapping.
 * 
 * @author nlepine
 * 
 */
public class TreeItemMappingSpec extends TreeItemMappingImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getAllSubMappings()
     */
    @Override
    public EList<TreeItemMapping> getAllSubMappings() {
        final Collection<TreeItemMapping> result = new ArrayList<TreeItemMapping>();
        result.addAll(getSubItemMappings());
        final Iterator<TreeItemMapping> it = getReusedTreeItemMappings().iterator();
        while (it.hasNext()) {
            final EObject eObj = it.next();
            if (eObj instanceof TreeItemMapping) {
                result.add((TreeItemMapping) eObj);
            }
        }
        return new EcoreEList.UnmodifiableEList<TreeItemMapping>(eInternalContainer(), DescriptionPackage.eINSTANCE.getTreeItemMapping_AllSubMappings(), result.size(), result.toArray());

    }

}
