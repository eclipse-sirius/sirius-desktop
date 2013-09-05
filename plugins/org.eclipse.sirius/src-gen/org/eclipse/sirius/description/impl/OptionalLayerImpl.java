/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.OptionalLayer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Optional Layer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.impl.OptionalLayerImpl#isActiveByDefault
 * <em>Active By Default</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OptionalLayerImpl extends LayerImpl implements OptionalLayer {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The default value of the '{@link #isActiveByDefault()
     * <em>Active By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isActiveByDefault()
     * @generated
     * @ordered
     */
    protected static final boolean ACTIVE_BY_DEFAULT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isActiveByDefault()
     * <em>Active By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isActiveByDefault()
     * @generated
     * @ordered
     */
    protected boolean activeByDefault = ACTIVE_BY_DEFAULT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected OptionalLayerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.OPTIONAL_LAYER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isActiveByDefault() {
        return activeByDefault;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setActiveByDefault(boolean newActiveByDefault) {
        boolean oldActiveByDefault = activeByDefault;
        activeByDefault = newActiveByDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.OPTIONAL_LAYER__ACTIVE_BY_DEFAULT, oldActiveByDefault, activeByDefault));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.OPTIONAL_LAYER__ACTIVE_BY_DEFAULT:
            return isActiveByDefault();
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
        case DescriptionPackage.OPTIONAL_LAYER__ACTIVE_BY_DEFAULT:
            setActiveByDefault((Boolean) newValue);
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
        case DescriptionPackage.OPTIONAL_LAYER__ACTIVE_BY_DEFAULT:
            setActiveByDefault(ACTIVE_BY_DEFAULT_EDEFAULT);
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
        case DescriptionPackage.OPTIONAL_LAYER__ACTIVE_BY_DEFAULT:
            return activeByDefault != ACTIVE_BY_DEFAULT_EDEFAULT;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (activeByDefault: ");
        result.append(activeByDefault);
        result.append(')');
        return result.toString();
    }

} // OptionalLayerImpl
