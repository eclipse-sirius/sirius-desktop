/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Menu Item Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionImpl#getIcon
 * <em>Icon</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MenuItemDescriptionImpl extends AbstractToolDescriptionImpl implements MenuItemDescription {
    /**
     * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected static final String ICON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected String icon = MenuItemDescriptionImpl.ICON_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MenuItemDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.MENU_ITEM_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getIcon() {
        return icon;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIcon(String newIcon) {
        String oldIcon = icon;
        icon = newIcon;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MENU_ITEM_DESCRIPTION__ICON, oldIcon, icon));
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
        case ToolPackage.MENU_ITEM_DESCRIPTION__ICON:
            return getIcon();
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
        case ToolPackage.MENU_ITEM_DESCRIPTION__ICON:
            setIcon((String) newValue);
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
        case ToolPackage.MENU_ITEM_DESCRIPTION__ICON:
            setIcon(MenuItemDescriptionImpl.ICON_EDEFAULT);
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
        case ToolPackage.MENU_ITEM_DESCRIPTION__ICON:
            return MenuItemDescriptionImpl.ICON_EDEFAULT == null ? icon != null : !MenuItemDescriptionImpl.ICON_EDEFAULT.equals(icon);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (icon: "); //$NON-NLS-1$
        result.append(icon);
        result.append(')');
        return result.toString();
    }

} // MenuItemDescriptionImpl
