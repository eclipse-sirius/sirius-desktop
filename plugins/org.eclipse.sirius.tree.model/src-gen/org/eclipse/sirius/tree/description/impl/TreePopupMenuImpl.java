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
package org.eclipse.sirius.tree.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef;
import org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Tree Popup Menu</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.impl.TreePopupMenuImpl#getMenuItemDescriptions <em>Menu Item
 * Descriptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TreePopupMenuImpl extends AbstractToolDescriptionImpl implements TreePopupMenu {
    /**
     * The cached value of the '{@link #getMenuItemDescriptions() <em>Menu Item Descriptions</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMenuItemDescriptions()
     * @generated
     * @ordered
     */
    protected EList<MenuItemOrRef> menuItemDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TreePopupMenuImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_POPUP_MENU;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<MenuItemOrRef> getMenuItemDescriptions() {
        if (menuItemDescriptions == null) {
            menuItemDescriptions = new EObjectContainmentEList<MenuItemOrRef>(MenuItemOrRef.class, this, DescriptionPackage.TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS);
        }
        return menuItemDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS:
            return ((InternalEList<?>) getMenuItemDescriptions()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS:
            return getMenuItemDescriptions();
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
        case DescriptionPackage.TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS:
            getMenuItemDescriptions().clear();
            getMenuItemDescriptions().addAll((Collection<? extends MenuItemOrRef>) newValue);
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
        case DescriptionPackage.TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS:
            getMenuItemDescriptions().clear();
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
        case DescriptionPackage.TREE_POPUP_MENU__MENU_ITEM_DESCRIPTIONS:
            return menuItemDescriptions != null && !menuItemDescriptions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // TreePopupMenuImpl
