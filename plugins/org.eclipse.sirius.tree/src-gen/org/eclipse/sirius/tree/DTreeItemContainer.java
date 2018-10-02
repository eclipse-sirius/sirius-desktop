/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DTree Item Container</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.DTreeItemContainer#getOwnedTreeItems <em>Owned Tree Items</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.TreePackage#getDTreeItemContainer()
 * @model abstract="true"
 * @generated
 */
public interface DTreeItemContainer extends DSemanticDecorator {
    /**
     * Returns the value of the '<em><b>Owned Tree Items</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.tree.DTreeItem}. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.tree.DTreeItem#getContainer <em>Container</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Tree Items</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Tree Items</em>' containment reference list.
     * @see org.eclipse.sirius.tree.TreePackage#getDTreeItemContainer_OwnedTreeItems()
     * @see org.eclipse.sirius.tree.DTreeItem#getContainer
     * @model opposite="container" containment="true"
     * @generated
     */
    EList<DTreeItem> getOwnedTreeItems();

} // DTreeItemContainer
