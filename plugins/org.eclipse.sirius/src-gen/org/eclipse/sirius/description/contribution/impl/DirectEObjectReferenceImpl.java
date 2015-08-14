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
package org.eclipse.sirius.description.contribution.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Direct EObject Reference</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.DirectEObjectReferenceImpl#getValue
 * <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DirectEObjectReferenceImpl extends EObjectImpl implements DirectEObjectReference {
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
    protected DirectEObjectReferenceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContributionPackage.Literals.DIRECT_EOBJECT_REFERENCE;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE, oldValue, value));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE, oldValue, value));
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
        case ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE:
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
        case ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE:
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
        case ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE:
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
        case ContributionPackage.DIRECT_EOBJECT_REFERENCE__VALUE:
            return value != null;
        }
        return super.eIsSet(featureID);
    }

} // DirectEObjectReferenceImpl
