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
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>EAttribute Customization</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.EAttributeCustomizationImpl#getAttributeName
 * <em>Attribute Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.EAttributeCustomizationImpl#getValue
 * <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EAttributeCustomizationImpl extends EStructuralFeatureCustomizationImpl implements EAttributeCustomization {
    /**
     * The default value of the '{@link #getAttributeName()
     * <em>Attribute Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAttributeName()
     * @generated
     * @ordered
     */
    protected static final String ATTRIBUTE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAttributeName()
     * <em>Attribute Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAttributeName()
     * @generated
     * @ordered
     */
    protected String attributeName = EAttributeCustomizationImpl.ATTRIBUTE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected String value = EAttributeCustomizationImpl.VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EAttributeCustomizationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.EATTRIBUTE_CUSTOMIZATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setAttributeName(String newAttributeName) {
        String oldAttributeName = attributeName;
        attributeName = newAttributeName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME, oldAttributeName, attributeName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setValue(String newValue) {
        String oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__VALUE, oldValue, value));
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
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME:
            return getAttributeName();
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__VALUE:
            return getValue();
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
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME:
            setAttributeName((String) newValue);
            return;
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__VALUE:
            setValue((String) newValue);
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
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME:
            setAttributeName(EAttributeCustomizationImpl.ATTRIBUTE_NAME_EDEFAULT);
            return;
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__VALUE:
            setValue(EAttributeCustomizationImpl.VALUE_EDEFAULT);
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
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__ATTRIBUTE_NAME:
            return EAttributeCustomizationImpl.ATTRIBUTE_NAME_EDEFAULT == null ? attributeName != null : !EAttributeCustomizationImpl.ATTRIBUTE_NAME_EDEFAULT.equals(attributeName);
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__VALUE:
            return EAttributeCustomizationImpl.VALUE_EDEFAULT == null ? value != null : !EAttributeCustomizationImpl.VALUE_EDEFAULT.equals(value);
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
        result.append(" (attributeName: "); //$NON-NLS-1$
        result.append(attributeName);
        result.append(", value: "); //$NON-NLS-1$
        result.append(value);
        result.append(')');
        return result.toString();
    }

} // EAttributeCustomizationImpl
