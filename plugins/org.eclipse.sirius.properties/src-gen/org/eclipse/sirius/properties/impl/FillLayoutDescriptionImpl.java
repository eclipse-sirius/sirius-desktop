/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Fill Layout Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl#getOrientation <em>Orientation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FillLayoutDescriptionImpl extends LayoutDescriptionImpl implements FillLayoutDescription {
    /**
     * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOrientation()
     * @generated
     * @ordered
     */
    protected static final FILL_LAYOUT_ORIENTATION ORIENTATION_EDEFAULT = FILL_LAYOUT_ORIENTATION.VERTICAL;

    /**
     * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOrientation()
     * @generated
     * @ordered
     */
    protected FILL_LAYOUT_ORIENTATION orientation = FillLayoutDescriptionImpl.ORIENTATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected FillLayoutDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.FILL_LAYOUT_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FILL_LAYOUT_ORIENTATION getOrientation() {
        return orientation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOrientation(FILL_LAYOUT_ORIENTATION newOrientation) {
        FILL_LAYOUT_ORIENTATION oldOrientation = orientation;
        orientation = newOrientation == null ? FillLayoutDescriptionImpl.ORIENTATION_EDEFAULT : newOrientation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.FILL_LAYOUT_DESCRIPTION__ORIENTATION, oldOrientation, orientation));
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
        case PropertiesPackage.FILL_LAYOUT_DESCRIPTION__ORIENTATION:
            return getOrientation();
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
        case PropertiesPackage.FILL_LAYOUT_DESCRIPTION__ORIENTATION:
            setOrientation((FILL_LAYOUT_ORIENTATION) newValue);
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
        case PropertiesPackage.FILL_LAYOUT_DESCRIPTION__ORIENTATION:
            setOrientation(FillLayoutDescriptionImpl.ORIENTATION_EDEFAULT);
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
        case PropertiesPackage.FILL_LAYOUT_DESCRIPTION__ORIENTATION:
            return orientation != FillLayoutDescriptionImpl.ORIENTATION_EDEFAULT;
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
        result.append(" (orientation: "); //$NON-NLS-1$
        result.append(orientation);
        result.append(')');
        return result.toString();
    }

} // FillLayoutDescriptionImpl
