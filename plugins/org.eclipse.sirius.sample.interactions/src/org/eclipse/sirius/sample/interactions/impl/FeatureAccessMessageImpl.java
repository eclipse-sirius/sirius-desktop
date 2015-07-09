/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.sample.interactions.FeatureAccessMessage;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Feature Access Message</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.FeatureAccessMessageImpl#isIsWrite
 * <em>Is Write</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.FeatureAccessMessageImpl#getFeature
 * <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureAccessMessageImpl extends MessageImpl implements FeatureAccessMessage {
    /**
     * The default value of the '{@link #isIsWrite() <em>Is Write</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsWrite()
     * @generated
     * @ordered
     */
    protected static final boolean IS_WRITE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsWrite() <em>Is Write</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsWrite()
     * @generated
     * @ordered
     */
    protected boolean isWrite = FeatureAccessMessageImpl.IS_WRITE_EDEFAULT;

    /**
     * The cached value of the '{@link #getFeature() <em>Feature</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFeature()
     * @generated
     * @ordered
     */
    protected EStructuralFeature feature;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected FeatureAccessMessageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.FEATURE_ACCESS_MESSAGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isIsWrite() {
        return isWrite;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIsWrite(boolean newIsWrite) {
        boolean oldIsWrite = isWrite;
        isWrite = newIsWrite;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.FEATURE_ACCESS_MESSAGE__IS_WRITE, oldIsWrite, isWrite));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EStructuralFeature getFeature() {
        if (feature != null && feature.eIsProxy()) {
            InternalEObject oldFeature = (InternalEObject) feature;
            feature = (EStructuralFeature) eResolveProxy(oldFeature);
            if (feature != oldFeature) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.FEATURE_ACCESS_MESSAGE__FEATURE, oldFeature, feature));
                }
            }
        }
        return feature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EStructuralFeature basicGetFeature() {
        return feature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFeature(EStructuralFeature newFeature) {
        EStructuralFeature oldFeature = feature;
        feature = newFeature;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.FEATURE_ACCESS_MESSAGE__FEATURE, oldFeature, feature));
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
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__IS_WRITE:
            return isIsWrite();
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__FEATURE:
            if (resolve) {
                return getFeature();
            }
            return basicGetFeature();
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
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__IS_WRITE:
            setIsWrite((Boolean) newValue);
            return;
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__FEATURE:
            setFeature((EStructuralFeature) newValue);
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
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__IS_WRITE:
            setIsWrite(FeatureAccessMessageImpl.IS_WRITE_EDEFAULT);
            return;
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__FEATURE:
            setFeature((EStructuralFeature) null);
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
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__IS_WRITE:
            return isWrite != FeatureAccessMessageImpl.IS_WRITE_EDEFAULT;
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE__FEATURE:
            return feature != null;
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
        result.append(" (isWrite: "); //$NON-NLS-1$
        result.append(isWrite);
        result.append(')');
        return result.toString();
    }
} // FeatureAccessMessageImpl
