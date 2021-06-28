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
package org.eclipse.sirius.tree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.viewpoint.impl.DSemanticDecoratorImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DTree Item Container</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.impl.DTreeItemContainerImpl#getOwnedTreeItems <em>Owned Tree Items</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DTreeItemContainerImpl extends DSemanticDecoratorImpl implements DTreeItemContainer {
    /**
     * The cached value of the '{@link #getOwnedTreeItems() <em>Owned Tree Items</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedTreeItems()
     * @generated
     * @ordered
     */
    protected EList<DTreeItem> ownedTreeItems;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DTreeItemContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TreePackage.Literals.DTREE_ITEM_CONTAINER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DTreeItem> getOwnedTreeItems() {
        if (ownedTreeItems == null) {
            ownedTreeItems = new EObjectContainmentWithInverseEList<>(DTreeItem.class, this, TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS, TreePackage.DTREE_ITEM__CONTAINER);
        }
        return ownedTreeItems;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedTreeItems()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS:
            return ((InternalEList<?>) getOwnedTreeItems()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS:
            return getOwnedTreeItems();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS:
            getOwnedTreeItems().clear();
            getOwnedTreeItems().addAll((Collection<? extends DTreeItem>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS:
            getOwnedTreeItems().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case TreePackage.DTREE_ITEM_CONTAINER__OWNED_TREE_ITEMS:
            return ownedTreeItems != null && !ownedTreeItems.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DTreeItemContainerImpl
