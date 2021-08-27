/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenu;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group Menu</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.GroupMenuImpl#getLocationURI <em>Location
 * URI</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.GroupMenuImpl#getPopupMenus <em>Popup Menus</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.impl.GroupMenuImpl#getItemDescriptions <em>Item
 * Descriptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupMenuImpl extends MenuItemDescriptionImpl implements GroupMenu {
    /**
     * The default value of the '{@link #getLocationURI() <em>Location URI</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getLocationURI()
     * @generated
     * @ordered
     */
    protected static final String LOCATION_URI_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLocationURI() <em>Location URI</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLocationURI()
     * @generated
     * @ordered
     */
    protected String locationURI = GroupMenuImpl.LOCATION_URI_EDEFAULT;

    /**
     * The cached value of the '{@link #getItemDescriptions() <em>Item Descriptions</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getItemDescriptions()
     * @generated
     * @ordered
     */
    protected EList<GroupMenuItem> itemDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GroupMenuImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.GROUP_MENU;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLocationURI() {
        return locationURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLocationURI(String newLocationURI) {
        String oldLocationURI = locationURI;
        locationURI = newLocationURI;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.GROUP_MENU__LOCATION_URI, oldLocationURI, locationURI));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PopupMenu> getPopupMenus() {
        // TODO: implement this method to return the 'Popup Menus' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<GroupMenuItem> getItemDescriptions() {
        if (itemDescriptions == null) {
            itemDescriptions = new EObjectContainmentEList.Resolving<>(GroupMenuItem.class, this, ToolPackage.GROUP_MENU__ITEM_DESCRIPTIONS);
        }
        return itemDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.GROUP_MENU__ITEM_DESCRIPTIONS:
            return ((InternalEList<?>) getItemDescriptions()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.GROUP_MENU__LOCATION_URI:
            return getLocationURI();
        case ToolPackage.GROUP_MENU__POPUP_MENUS:
            return getPopupMenus();
        case ToolPackage.GROUP_MENU__ITEM_DESCRIPTIONS:
            return getItemDescriptions();
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
        case ToolPackage.GROUP_MENU__LOCATION_URI:
            setLocationURI((String) newValue);
            return;
        case ToolPackage.GROUP_MENU__ITEM_DESCRIPTIONS:
            getItemDescriptions().clear();
            getItemDescriptions().addAll((Collection<? extends GroupMenuItem>) newValue);
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
        case ToolPackage.GROUP_MENU__LOCATION_URI:
            setLocationURI(GroupMenuImpl.LOCATION_URI_EDEFAULT);
            return;
        case ToolPackage.GROUP_MENU__ITEM_DESCRIPTIONS:
            getItemDescriptions().clear();
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
        case ToolPackage.GROUP_MENU__LOCATION_URI:
            return GroupMenuImpl.LOCATION_URI_EDEFAULT == null ? locationURI != null : !GroupMenuImpl.LOCATION_URI_EDEFAULT.equals(locationURI);
        case ToolPackage.GROUP_MENU__POPUP_MENUS:
            return !getPopupMenus().isEmpty();
        case ToolPackage.GROUP_MENU__ITEM_DESCRIPTIONS:
            return itemDescriptions != null && !itemDescriptions.isEmpty();
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (locationURI: "); //$NON-NLS-1$
        result.append(locationURI);
        result.append(')');
        return result.toString();
    }

} // GroupMenuImpl
