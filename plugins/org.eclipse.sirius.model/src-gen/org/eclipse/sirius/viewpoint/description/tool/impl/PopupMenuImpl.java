/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Popup Menu</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.PopupMenuImpl#getMenuItemDescription <em>Menu Item
 * Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PopupMenuImpl extends AbstractToolDescriptionImpl implements PopupMenu {
    /**
     * The cached value of the '{@link #getMenuItemDescription() <em>Menu Item Description</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMenuItemDescription()
     * @generated
     * @ordered
     */
    protected EList<MenuItemDescription> menuItemDescription;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PopupMenuImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.POPUP_MENU;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<MenuItemDescription> getMenuItemDescription() {
        if (menuItemDescription == null) {
            menuItemDescription = new EObjectContainmentEList.Resolving<MenuItemDescription>(MenuItemDescription.class, this, ToolPackage.POPUP_MENU__MENU_ITEM_DESCRIPTION);
        }
        return menuItemDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.POPUP_MENU__MENU_ITEM_DESCRIPTION:
            return ((InternalEList<?>) getMenuItemDescription()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.POPUP_MENU__MENU_ITEM_DESCRIPTION:
            return getMenuItemDescription();
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
        case ToolPackage.POPUP_MENU__MENU_ITEM_DESCRIPTION:
            getMenuItemDescription().clear();
            getMenuItemDescription().addAll((Collection<? extends MenuItemDescription>) newValue);
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
        case ToolPackage.POPUP_MENU__MENU_ITEM_DESCRIPTION:
            getMenuItemDescription().clear();
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
        case ToolPackage.POPUP_MENU__MENU_ITEM_DESCRIPTION:
            return menuItemDescription != null && !menuItemDescription.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // PopupMenuImpl
