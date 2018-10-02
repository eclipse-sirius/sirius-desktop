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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Menu Item Description Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionReferenceImpl#getItem
 * <em>Item</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MenuItemDescriptionReferenceImpl extends MinimalEObjectImpl.Container implements MenuItemDescriptionReference {
    /**
     * The cached value of the '{@link #getItem() <em>Item</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getItem()
     * @generated
     * @ordered
     */
    protected MenuItemDescription item;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MenuItemDescriptionReferenceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.MENU_ITEM_DESCRIPTION_REFERENCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MenuItemDescription getItem() {
        if (item != null && item.eIsProxy()) {
            InternalEObject oldItem = (InternalEObject) item;
            item = (MenuItemDescription) eResolveProxy(oldItem);
            if (item != oldItem) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE__ITEM, oldItem, item));
                }
            }
        }
        return item;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MenuItemDescription basicGetItem() {
        return item;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setItem(MenuItemDescription newItem) {
        MenuItemDescription oldItem = item;
        item = newItem;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE__ITEM, oldItem, item));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE__ITEM:
            if (resolve) {
                return getItem();
            }
            return basicGetItem();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE__ITEM:
            setItem((MenuItemDescription) newValue);
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
        case ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE__ITEM:
            setItem((MenuItemDescription) null);
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
        case ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE__ITEM:
            return item != null;
        }
        return super.eIsSet(featureID);
    }

} // MenuItemDescriptionReferenceImpl
