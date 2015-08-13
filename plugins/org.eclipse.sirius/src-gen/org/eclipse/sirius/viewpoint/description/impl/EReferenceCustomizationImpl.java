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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>EReference Customization</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.EReferenceCustomizationImpl#getReferenceName
 * <em>Reference Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.EReferenceCustomizationImpl#getValue
 * <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EReferenceCustomizationImpl extends EStructuralFeatureCustomizationImpl implements EReferenceCustomization {
    /**
     * The default value of the '{@link #getReferenceName()
     * <em>Reference Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getReferenceName()
     * @generated
     * @ordered
     */
    protected static final String REFERENCE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReferenceName()
     * <em>Reference Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getReferenceName()
     * @generated
     * @ordered
     */
    protected String referenceName = EReferenceCustomizationImpl.REFERENCE_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected EObject value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EReferenceCustomizationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.EREFERENCE_CUSTOMIZATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setReferenceName(String newReferenceName) {
        String oldReferenceName = referenceName;
        referenceName = newReferenceName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EREFERENCE_CUSTOMIZATION__REFERENCE_NAME, oldReferenceName, referenceName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject getValue() {
        if (value != null && value.eIsProxy()) {
            InternalEObject oldValue = (InternalEObject) value;
            value = eResolveProxy(oldValue);
            if (value != oldValue) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.EREFERENCE_CUSTOMIZATION__VALUE, oldValue, value));
                }
            }
        }
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setValue(EObject newValue) {
        EObject oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EREFERENCE_CUSTOMIZATION__VALUE, oldValue, value));
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
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__REFERENCE_NAME:
            return getReferenceName();
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__VALUE:
            if (resolve) {
                return getValue();
            }
            return basicGetValue();
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
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__REFERENCE_NAME:
            setReferenceName((String) newValue);
            return;
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__VALUE:
            setValue((EObject) newValue);
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
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__REFERENCE_NAME:
            setReferenceName(EReferenceCustomizationImpl.REFERENCE_NAME_EDEFAULT);
            return;
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__VALUE:
            setValue((EObject) null);
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
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__REFERENCE_NAME:
            return EReferenceCustomizationImpl.REFERENCE_NAME_EDEFAULT == null ? referenceName != null : !EReferenceCustomizationImpl.REFERENCE_NAME_EDEFAULT.equals(referenceName);
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION__VALUE:
            return value != null;
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
        result.append(" (referenceName: "); //$NON-NLS-1$
        result.append(referenceName);
        result.append(')');
        return result.toString();
    }

} // EReferenceCustomizationImpl
