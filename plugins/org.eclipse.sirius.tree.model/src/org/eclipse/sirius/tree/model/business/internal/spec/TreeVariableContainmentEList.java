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

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.tree.model.business.internal.helper.TreeModelHelper;

/**
 * A specific {@link EObjectContainmentEList} for the {@link TableVariable}.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class TreeVariableContainmentEList extends EObjectContainmentEList<TreeVariable> {

    /**
     *
     */
    private static final long serialVersionUID = -1416381022166192302L;

    /**
     * The constructor.
     * 
     * @param owner
     *            The owner of this list
     * @param featureID
     *            The feature ID of the elements of this list
     */
    public TreeVariableContainmentEList(final InternalEObject owner, final int featureID) {
        super(TreeVariable.class, owner, featureID);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.notify.impl.NotifyingListImpl#addUnique(java.lang.Object)
     */
    @Override
    public void addUnique(final TreeVariable object) {
        // We remove the default tableVariable if it exists
        final TreeVariable tableVariableToAdd = object;
        final String name = tableVariableToAdd.getName();
        final TreeVariable variableWithSameName = TreeModelHelper.getVariable((TreeItemTool) owner, name);
        remove(variableWithSameName);
        super.addUnique(object);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.BasicEList#add(java.lang.Object)
     */
    @Override
    public boolean add(final TreeVariable object) {
        // We remove the default tableVariable if it exists
        final TreeVariable tableVariableToAdd = object;
        final String name = tableVariableToAdd.getName();
        final TreeVariable variableWithSameName = TreeModelHelper.getVariable((TreeItemTool) owner, name);
        remove(variableWithSameName);
        return super.add(object);
    }

}
